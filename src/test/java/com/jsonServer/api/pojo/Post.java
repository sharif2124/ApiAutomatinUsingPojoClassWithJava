package com.jsonServer.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Post {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("author")
    public String author;

    /**
     * No args constructor for use in serialization
     *
     */
    public Post() {
    }

    /**
     *
     * @param author
     * @param id
     * @param title
     */
    public Post(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    public Post( String title, String author) {
        this.title = title;
        this.author = author;
    }

}