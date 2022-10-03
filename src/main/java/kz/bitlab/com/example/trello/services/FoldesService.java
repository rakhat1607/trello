package kz.bitlab.com.example.trello.services;

import kz.bitlab.com.example.trello.entities.Folders;

import java.util.List;

public interface FoldesService {
    Folders addFolder(Folders folders);
    Folders saveFolder(Folders folders);

    Folders addTaskCategory(Long foldersId, Long categoryId);
    Folders deleteTaskCategory(Long foldersId, Long categoryId);
    List<Folders> getFolders();

    Folders getFolder(Long id);
    void deleteFolder(Long id);
}
