package com.github.mpi.rxgraphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import graphql.execution.RxExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

@SpringBootApplication
public class RxGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(RxGraphqlApplication.class, args);
	}
	
	@Bean
	static public ServletRegistrationBean graphQLServletRegistrationBean(GraphQLSchema schema) {
	    return new ServletRegistrationBean(new SimpleGraphQLServlet(schema, new RxExecutionStrategy()), "/v1/graphql");
	}
}
