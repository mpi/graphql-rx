package com.github.mpi.rxgraphql.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {
    
    String id;
    @JsonProperty("author")
    String author;
    String commentDate;
    String body;
    
    public String getId() {
        return id;
    }
    public String getCommentDate() {
        return commentDate;
    }
    public String getBody() {
        return body;
    }
}
