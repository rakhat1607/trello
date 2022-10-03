package kz.bitlab.com.example.trello.repositories;

import kz.bitlab.com.example.trello.entities.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskCategoriesRepository extends JpaRepository <TaskCategories, Long> {
}
