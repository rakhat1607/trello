package kz.bitlab.com.example.trello.repositories;

import kz.bitlab.com.example.trello.entities.Folders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FoldersRepository extends JpaRepository<Folders, Long> {
}
