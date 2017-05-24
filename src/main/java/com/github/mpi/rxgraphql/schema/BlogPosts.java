package com.github.mpi.rxgraphql.schema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.github.mpi.rxgraphql.persistence.PostRepository;
import com.github.mpi.rxgraphql.util.Convert;

import rx.Observable;

@Component
public class BlogPosts implements GraphQLResolver<Blog>{

    @Autowired
    private PostRepository posts;
    
    public Observable<List<Post>> posts(Blog blog){
        return posts.loadAll()
                .map(Convert.to(Post.class))
                .toList();
    }
    
}
