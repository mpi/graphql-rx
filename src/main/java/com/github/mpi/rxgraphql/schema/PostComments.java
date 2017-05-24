package com.github.mpi.rxgraphql.schema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.github.mpi.rxgraphql.persistence.AuthorRepository;
import com.github.mpi.rxgraphql.persistence.CommentRepository;
import com.github.mpi.rxgraphql.util.Convert;

import rx.Observable;

@Component
public class PostComments implements GraphQLResolver<Post>{

    @Autowired
    private CommentRepository comments;
    
    public Observable<List<Comment>> comments(Post post){
        return comments.loadForPost(post.id)
                .map(Convert.to(Comment.class))
                .toList();
    }
    
    @Autowired
    private AuthorRepository authors;
    
    public Observable<Author> author(Post post){
        return authors.loadByEmail(post.author)
                .map(Convert.to(Author.class));
    }
}
