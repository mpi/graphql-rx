package com.github.mpi.rxgraphql.schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.github.mpi.rxgraphql.persistence.AuthorRepository;
import com.github.mpi.rxgraphql.util.Convert;

import rx.Observable;

@Component
public class CommentAuthor implements GraphQLResolver<Comment>{

    @Autowired
    private AuthorRepository authors;
    
    public Observable<Author> author(Comment comment){
        return authors.loadByEmail(comment.author)
                .map(Convert.to(Author.class));
    }
    
}
