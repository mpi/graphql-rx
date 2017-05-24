package com.github.mpi.rxgraphql.util;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import rx.functions.Func1;

public class Convert {

    private static ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(Feature.IGNORE_UNKNOWN, true);
    
    static public <T, R> Func1<R, T> to(Class<T> target){
        return obj -> MAPPER.convertValue(obj, target);
    }
    
}
