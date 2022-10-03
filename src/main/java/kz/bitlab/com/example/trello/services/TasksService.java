package kz.bitlab.com.example.trello.services;

import kz.bitlab.com.example.trello.entities.Tasks;

import java.util.List;

public interface TasksService {
    Tasks addNewTaskAndAssignFolder(String title, String description, Long folderId);
    Tasks saveTask(String name, String description, Long task_id, int status, Long folder_id);
    List<Tasks> getTasks();

    List<Tasks> getTasksByFolderId(Long folderId);
    void deleteTask(Long id);
}
