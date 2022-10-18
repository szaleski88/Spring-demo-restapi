package com.szaleski.restapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.szaleski.restapi.config.LoginCredentials;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldLoginAndGetContent() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setPassword("test");
        loginCredentials.setUsername("test");

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(objectMapper.writeValueAsString(loginCredentials)))
                                     .andDo(print())
                                     .andExpect(status().isOk())
                                     .andReturn();

        // Then
        String token = mvcResult.getResponse().getHeader("Authorization");

        mockMvc.perform(MockMvcRequestBuilders.get("/secured").header("Authorization", token))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string("secured"));

        mockMvc.perform(MockMvcRequestBuilders.get("/secured"))
               .andDo(print())
               .andExpect(status().is(401));

    }

}