package com.github.mpi.rxgraphql.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

@Repository
public class AuthorRepository {

    private List<AuthorData> storage = new ArrayList<>();
    private Subject<String, String> task = new SerializedSubject<>(PublishSubject.create());
    private Observable<AuthorData> results;
    
    public AuthorRepository() {
        
        PublishSubject<AuthorData> done = PublishSubject.create();
        results = done.share();
        task
            .buffer(1, TimeUnit.MILLISECONDS, 100)
//            .onBackpressureBuffer(100)
            .subscribeOn(Schedulers.trampoline())
            .filter(list -> !list.isEmpty())
            .doOnNext(ids -> System.err.println("Executing query for: " + ids + " " + Thread.currentThread().getName()))
            .flatMap(list -> Observable.from(storage).filter(a -> list.contains(a.email)))
//            .subscribeOn(Schedulers.io())
            .subscribe(a -> done.onNext(a));
    }
    
    public Observable<AuthorData> loadByEmail(String authorEmail) {
        
        System.err.println("Loading authorByEmail: " + authorEmail);

        return results
                .filter(a -> a.email.equals(authorEmail))
                .doOnSubscribe(() -> task.onNext(authorEmail))
                .take(1);
    }
    
    @PostConstruct void populate(){
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
