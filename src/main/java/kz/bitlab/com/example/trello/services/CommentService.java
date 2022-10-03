package kz.bitlab.com.example.trello.services;

import kz.bitlab.com.example.trello.entities.Comments;

import java.util.List;

public interface CommentService {
    Comments addComment(Comments comments);
    List <Comments> getComments();
    void deleteComment(Long id);
}
