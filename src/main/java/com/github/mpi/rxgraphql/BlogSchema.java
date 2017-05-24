package com.github.mpi.rxgraphql;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.SchemaParser;
import com.github.mpi.rxgraphql.schema.Author;
import com.github.mpi.rxgraphql.schema.Blog;
import com.github.mpi.rxgraphql.schema.BlogPosts;
import com.github.mpi.rxgraphql.schema.Comment;
import com.github.mpi.rxgraphql.schema.CommentAuthor;
import com.github.mpi.rxgraphql.schema.Post;
import com.github.mpi.rxgraphql.schema.PostAuthor;
import com.github.mpi.rxgraphql.schema.PostComments;
import com.github.mpi.rxgraphql.schema.Query;

import graphql.schema.GraphQLSchema;

@Component
public class BlogSchema {

    @Autowired
    private BlogPosts blogPosts;
    @Autowired
    private PostComments postComments;
    @Autowired
    private PostAuthor postAuthor;
    @Autowired
    private CommentAuthor commentAuthor;
    
    @Bean
    public GraphQLSchema schema() {
        return SchemaParser.newParser()
                .schemaString(loadSchema("blog.graphqls"))
                .dictionary(Blog.class, Author.class, Comment.class, Post.class)
                .resolvers(new Query(), blogPosts, postComments, postAuthor, commentAuthor)
                .build()
                .makeExecutableSchema();
    }
    
    

    private String loadSchema(String file) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("graphql-schema/" + file);
        try {
            return IOUtils.toString(in);
        } catch (IOException e) {
            throw new RuntimeException("Could not load schema!");
        }
    }

}
