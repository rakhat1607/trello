package kz.bitlab.com.example.trello.services.impl;

import kz.bitlab.com.example.trello.entities.Folders;
import kz.bitlab.com.example.trello.entities.Tasks;
import kz.bitlab.com.example.trello.repositories.FoldersRepository;
import kz.bitlab.com.example.trello.repositories.TasksRepository;
import kz.bitlab.com.example.trello.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TaskServiceImpl implements TasksService {
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private FoldersRepository foldersRepository;

    @Override
    public Tasks addNewTaskAndAssignFolder(String title, String description, Long folderId) {
        Tasks tasks = new Tasks();
        tasks.setTitle(title);
        tasks.setDescription(description);
        tasks.setStatus(0);
        tasks.setFolder(foldersRepository.findById(folderId).orElseThrow());
        return tasksRepository.save(tasks);
    }

    @Override
    public Tasks saveTask(String name,String description,Long task_id, int status, Long folder_id) {
        Tasks task = tasksRepository.findById(task_id).orElse(null);
        if(task!=null) {
            task.setTitle(name);
            task.setDescription(description);
            task.setStatus(status);

            return tasksRepository.save(task);
        }
        return null;
    }

    @Override
    public List<Tasks> getTasks() {
        return tasksRepository.findAll();
    }

    @Override
    public List<Tasks> getTasksByFolderId(Long folderId) {
        return tasksRepository.findAllByFolderId(folderId);
    }

    @Override
    public void deleteTask(Long id) {
        tasksRepository.deleteById(id);
    }
}
