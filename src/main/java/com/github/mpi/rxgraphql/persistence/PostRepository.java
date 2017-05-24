package com.github.mpi.rxgraphql.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import rx.Observable;

@Repository
public class PostRepository {

    private List<PostData> storage = new ArrayList<>();

    public Observable<PostData> loadAll() {
        System.err.println("Loading allPosts");
        return Observable.from(storage);
    }
    
    @PostConstruct
    private void populate(){
        storage.add(new PostData("cmwe22", "Higher-order functions in Lodash", "michal.piotrkowski@pragmatists.pl", "2017/05/11", "In this article I would like to explain concept of higher-order functions and how they are omni-present in my favorite Javascript library: Lodash..."));
        storage.add(new PostData("ustr76", "5 metrics that will improve when your team adopts TDD", "krzysztof.jelski@pragmatists.pl", "2017/04/27", "You’ve already heard of Test-Driven Development. Finally you get down to trying it out for real. You even manage to convince your whole team to adopt it with you..."));
        storage.add(new PostData("ay47as", "Refactoring from anemic model to DDD", "zbigniew.artemiuk@pragmatists.pl", "2017/04/18", "In our last post we took a closer look into the difference between an application with anemic model and one desgined with rules of DDD..."));
        storage.add(new PostData("bxr87p", "Test Doubles – Fakes, Mocks and Stubs.", "michal.lipski@pragmatists.pl", "2017/03/30", "In automated testing it is common to use objects that look and behave like their production equivalents, but are actually simplified."));
    }
    
    public static class PostData {

        String id;
        String title;
        String body;
        String publicationDate;
        String author;
        
        public PostData(String id, String title, String author, String publicationDate, String body) {
            super();
            this.id = id;
            this.title = title;
            this.publicationDate = publicationDate;
            this.author = author;
            this.body = body;
        }

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

        public String getAuthor() {
            return author;
        }
    }    
}
