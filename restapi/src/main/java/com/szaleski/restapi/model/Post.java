package com.szaleski.restapi.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

    @Id
    private long id;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

}
