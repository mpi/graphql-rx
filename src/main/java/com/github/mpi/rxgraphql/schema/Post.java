package com.github.mpi.rxgraphql.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post{

    String id;
    String title;
    String body;
    String publicationDate;
    
    @JsonProperty("author")
    String author;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getPublicationDate() {
        return publicationDate;
    }
}
