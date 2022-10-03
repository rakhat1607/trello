package kz.bitlab.com.example.trello.services.impl;

import kz.bitlab.com.example.trello.entities.Folders;
import kz.bitlab.com.example.trello.entities.TaskCategories;
import kz.bitlab.com.example.trello.repositories.FoldersRepository;
import kz.bitlab.com.example.trello.repositories.TaskCategoriesRepository;
import kz.bitlab.com.example.trello.services.FoldesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderServiceImpl implements FoldesService {

    @Autowired
    private FoldersRepository foldersRepository;

    @Autowired
    private TaskCategoriesRepository taskCategoriesRepository;


    @Override
    public Folders addFolder(Folders folders) {
        return foldersRepository.save(folders);
    }

    @Override
    public Folders saveFolder(Folders folders) {
        Folders checkFolder = foldersRepository.findById(folders.getId()).orElse(null);
        if (checkFolder!=null){
            checkFolder.setName(folders.getName());
            return foldersRepository.save(checkFolder);
        }
        return null;
    }

    @Override
    public List<Folders> getFolders() {
        return foldersRepository.findAll();
    }

    @Override
    public Folders getFolder(Long id) {
        return foldersRepository.findById(id).orElseThrow();
    }

    @Override
    public Folders addTaskCategory(Long foldersId, Long categoryId) {
        Folders folders = foldersRepository.findById(foldersId).orElseThrow();
        TaskCategories taskCategory = taskCategoriesRepository.findById(categoryId).orElseThrow();
        List<TaskCategories> taskCategories = folders.getTaskCategories();
        if (taskCategories==null){
            taskCategories = new ArrayList<>();
        }
        taskCategories.add(taskCategory);
        folders.setTaskCategories(taskCategories);

        return foldersRepository.save(folders);
    }

    @Override
    public Folders deleteTaskCategory(Long foldersId, Long categoryId) {
        Folders folders = foldersRepository.findById(foldersId).orElseThrow();
        TaskCategories taskCategory = taskCategoriesRepository.findById(categoryId).orElseThrow();
        List<TaskCategories> taskCategories = folders.getTaskCategories();
        if (taskCategory==null){
            taskCategories = new ArrayList<>();
        }
        taskCategories.remove(taskCategory);
        folders.setTaskCategories(taskCategories);

        return foldersRepository.save(folders);
    }

    @Override
    public void deleteFolder(Long id) {
        foldersRepository.deleteById(id);
    }
}
