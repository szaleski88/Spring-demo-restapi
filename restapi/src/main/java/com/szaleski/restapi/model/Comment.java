package com.szaleski.restapi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    private long id;

    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

}
