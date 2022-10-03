package kz.bitlab.com.example.trello.services.impl;

import kz.bitlab.com.example.trello.entities.Comments;
import kz.bitlab.com.example.trello.repositories.CommentsRepository;
import kz.bitlab.com.example.trello.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Comments addComment(Comments comments) {
        return commentsRepository.save(comments);
    }

    @Override
    public List<Comments> getComments() {
        return commentsRepository.findAll();
    }

    @Override
    public void deleteComment(Long id) {
        commentsRepository.deleteById(id);
    }
}
