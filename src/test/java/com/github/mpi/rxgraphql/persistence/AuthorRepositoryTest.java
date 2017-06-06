package com.github.mpi.rxgraphql.persistence;

import com.github.mpi.rxgraphql.persistence.AuthorRepository;
import com.github.mpi.rxgraphql.persistence.AuthorRepository.AuthorData;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class AuthorRepositoryTest {

    private AuthorRepository authors;

    @Before
    public void setUp() {

        authors = new AuthorRepository();
        authors.populate();
    }
    
    @Test
    public void test() throws InterruptedException {

        Observable<AuthorData> a1 = authors.loadByEmail("michal.piotrkowski@pragmatists.pl");
        Observable<AuthorData> a2 = authors.loadByEmail("zbigniew.artemiuk@pragmatists.pl");
        
        Observable<AuthorData> b = a1.mergeWith(a2);
        
        b
        .observeOn(Schedulers.trampoline())
        .subscribe(a -> {
            System.err.println(a.getFullName() + ": " + Thread.currentThread().getName());
        });
        
        Thread.currentThread().sleep(2000);
    }
    
    @Test
    public void shouldname() throws Exception {

        // given:
        Observable.range(1, 10)
            .scan(0, (acc, v) -> v)
            .skip(1)
            .subscribe(v -> System.err.println(v));
        
        // when:
        // then:
    }
}
