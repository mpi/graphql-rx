package com.github.mpi.rxgraphql.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import rx.Observable;

@Repository
public class AuthorRepository {

    private List<AuthorData> storage = new ArrayList<>();
    
    public Observable<AuthorData> loadByEmail(String authorEmail) {
        System.err.println("Loading authorByEmail: " + authorEmail);
        return Observable.from(storage)
                .filter(a -> a.email.equals(authorEmail));
    }
    
    @PostConstruct
    private void populate(){
        storage.add(new AuthorData("michal.piotrkowski@pragmatists.pl", "Michał Piotrkowski"));
        storage.add(new AuthorData("michal.lipski@pragmatists.pl", "Michał Lipski"));
        storage.add(new AuthorData("krzysztof.jelski@pragmatists.pl", "Krzysztof Jelski"));
        storage.add(new AuthorData("zbigniew.artemiuk@pragmatists.pl", "Zbigniew Artemiuk"));
        storage.add(new AuthorData("anonymous@dmail.dom", null));
        storage.add(new AuthorData("jdoe@qwerty.jd", "John Doe"));
    }
    
    public static class AuthorData {
        
        private String email;
        private String fullName;
        
        public AuthorData(String email, String fullName) {
            this.email = email;
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public String getFullName() {
            return fullName;
        }
    }

}
