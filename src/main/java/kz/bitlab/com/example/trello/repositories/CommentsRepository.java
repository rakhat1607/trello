package kz.bitlab.com.example.trello.repositories;

import kz.bitlab.com.example.trello.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
