package com.github.mpi.rxgraphql.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import rx.Observable;

@Repository
public class CommentRepository {
    
    private List<CommentData> storage = new ArrayList<>();
    
    public Observable<CommentData> loadForPost(String id) {
        System.err.println("Loading commentsForPost: " + id);
        return Observable.from(storage)
                .filter(c -> c.postId.equals(id));
    }

   
    @PostConstruct
    private void populate(){
        storage.add(new CommentData("cyah24", "cmwe22", "michal.piotrkowski@pragmatists.pl", "2017/05/19", "Thanks!"));
        storage.add(new CommentData("ustr76", "cmwe22", "anonymous@dmail.dom", "2017/05/18", "Nice article!"));
        storage.add(new CommentData("ay47as", "aaor9d", "jdoe@qwerty.jd", "2017/05/10", "Waiting for next part!"));
    }
    
    public static class CommentData {
        String id;
        String postId;
        String author;
        String commentDate;
        String body;
        
        public CommentData(String id, String postId, String author, String commentDate, String body) {
            this.id = id;
            this.postId = postId;
            this.author = author;
            this.commentDate = commentDate;
            this.body = body;
        }

        public String getId() {
            return id;
        }

        public String getPostId() {
            return postId;
        }

        public String getAuthor() {
            return author;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public String getBody() {
            return body;
        }
    }

}
