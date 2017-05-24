package com.github.mpi.rxgraphql.schema;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import rx.Observable;

public class Query implements GraphQLRootResolver {

//    public Observable<String> hello(String name) {
//        return Observable.just(String.format("Hello %s!", name));
//    }
    
    public Observable<Blog> blog(){
        return Observable.just(new Blog());
    }

}
