package kz.bitlab.com.example.trello.controllers;

import kz.bitlab.com.example.trello.entities.Folders;
import kz.bitlab.com.example.trello.entities.TaskCategories;
import kz.bitlab.com.example.trello.entities.Tasks;
import kz.bitlab.com.example.trello.repositories.TasksRepository;
import kz.bitlab.com.example.trello.services.FoldesService;
import kz.bitlab.com.example.trello.services.TaskCategoriesService;
import kz.bitlab.com.example.trello.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    private FoldesService foldesService;

    @Autowired
    private TasksService tasksService;

    @Autowired
    private TaskCategoriesService taskCategoriesService;

    @GetMapping(value = "/")
    public String mainPage(Model model){
        List<Folders> folders = foldesService.getFolders();
        model.addAttribute("foldersModel", folders);
        return "main";
    }
    @PostMapping(value = "/addFolder")
    public String addFolder(Folders folders){
        foldesService.addFolder(folders);
        return "redirect:/";
    }

    @PostMapping(value = "/addTask/{folderId}")
    public String addTask(@RequestParam String title,
                          @RequestParam String description,
                          @PathVariable Long folderId){
        tasksService.addNewTaskAndAssignFolder(title, description, folderId);
        return "redirect:/folderDetails/" + folderId;
    }

    @GetMapping(value = "/folderDetails/{id}")
    public String folderDetails(@PathVariable(name = "id") Long id,
                         Model model){
        Folders folders = foldesService.getFolder(id);

        List <Tasks> tasks = tasksService.getTasksByFolderId(id);
        model.addAttribute("taskModel",tasks);

        List<TaskCategories> taskCategory = taskCategoriesService.getTaskCategories();
        List<TaskCategories> category = folders.getTaskCategories();
        taskCategory.removeAll(category);

        model.addAttribute("folder", folders);
        model.addAttribute("categoryModel",taskCategory);
        model.addAttribute("folderCategory",category);

        return "folderDetails";
    }

    @PostMapping(value = "/assignCategory")
    public String assignCategory(@RequestParam(name = "folders_id") Long foldersId,
                                @RequestParam(name = "task_category_id") Long categoryId){
        foldesService.addTaskCategory(foldersId,categoryId);
        return "redirect:/folderDetails/" + foldersId;
    }

    @PostMapping(value = "/unAssaignCategory")
    public String unAssignCategory(@RequestParam(name = "folders_id") Long foldersId,
                                   @RequestParam(name = "task_category_id") Long categoryId){
        foldesService.deleteTaskCategory(foldersId,categoryId);
        return "redirect:/folderDetails/" + foldersId;
    }

    @GetMapping(value = "/taskDetails/{id}")
    public String taskDetails(@PathVariable(name = "id") Long id,
                              Model model){
        Tasks tasks = tasksRepository.findById(id).orElse(null);
        if (tasks !=null){
            model.addAttribute("task",tasks);
        }
        return "taskDetails";
    }

    @GetMapping("/taskDetails/{folderid}/{id}")
    public String taskdetails(@PathVariable(name = "folderid") Long folder_id,
                              @PathVariable(name = "id") Long id,
                              Model model){
        Tasks task = tasksRepository.findById(id).orElse(null);
        if(task!=null){
            model.addAttribute("task", task);
            model.addAttribute("folder_id", folder_id);
        }
        return "taskDetails";
    }

    @PostMapping("/edittask")
    public String edittask(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "task_id") Long task_id,
            @RequestParam(name = "status") int status,
            @RequestParam(name = "folder_id") Long folder_id){
        Tasks  tasks1 = tasksService.saveTask(name, description, task_id, status, folder_id);
        if (tasks1!=null){
            return "redirect:/folderDetails/" + folder_id;
        }
        return "redirect:/";
    }

    @GetMapping("/deletetask/{folderid}/{id}")
    public String deletetask(
            @PathVariable(name = "folderid") Long folder_id,
            @PathVariable(name = "id") Long id){
        tasksService.deleteTask(id);
        return "redirect:/folderDetails/" + folder_id;
    }
}
