package kz.bitlab.com.example.trello.services;

import kz.bitlab.com.example.trello.entities.TaskCategories;

import java.util.List;

public interface TaskCategoriesService {
    TaskCategories addTaskCategories(TaskCategories taskCategories);
    TaskCategories saveTaskCategories(TaskCategories taskCategories);
    List<TaskCategories> getTaskCategories();
    void deleteTaskCategories(Long id);

}
