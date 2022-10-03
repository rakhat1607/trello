package kz.bitlab.com.example.trello.services.impl;

import kz.bitlab.com.example.trello.entities.Folders;
import kz.bitlab.com.example.trello.entities.TaskCategories;
import kz.bitlab.com.example.trello.repositories.FoldersRepository;
import kz.bitlab.com.example.trello.repositories.TaskCategoriesRepository;
import kz.bitlab.com.example.trello.services.TaskCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoriesServiceImpl implements TaskCategoriesService {
    @Autowired
    private TaskCategoriesRepository taskCategoriesRepository;

    @Autowired
    private FoldersRepository foldersRepository;

    @Override
    public TaskCategories addTaskCategories(TaskCategories taskCategories) {
        return taskCategoriesRepository.save(taskCategories);
    }

    @Override
    public TaskCategories saveTaskCategories(TaskCategories taskCategories) {
        TaskCategories checkTaskCategories = taskCategoriesRepository.findById(taskCategories.getId()).orElse(null);
        if (checkTaskCategories!=null){
            checkTaskCategories.setName(taskCategories.getName());
            return taskCategoriesRepository.save(checkTaskCategories);
        }
        return null;
    }

    @Override
    public List<TaskCategories> getTaskCategories() {
        return taskCategoriesRepository.findAll();
    }

    @Override
    public void deleteTaskCategories(Long id) {
        taskCategoriesRepository.deleteById(id);
    }

}
