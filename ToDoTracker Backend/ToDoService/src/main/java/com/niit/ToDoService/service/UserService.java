package com.niit.ToDoService.service;

import com.niit.ToDoService.exception.TaskNotFoundException;
import com.niit.ToDoService.exception.UserAlreadyExistsException;

import com.niit.ToDoService.exception.UserNotFoundException;
import com.niit.ToDoService.model.ArchiveList;
import com.niit.ToDoService.model.TempTaskList;
import com.niit.ToDoService.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;


    User addToTask(String email, TempTaskList tempTaskList);


    User addToArchive(String email, ArchiveList archiveList);


    Boolean deleteFromTask(String email, long taskId) throws TaskNotFoundException;

    Boolean deleteFromArchive(String email, long taskId) throws TaskNotFoundException;

    List<TempTaskList> getFromTask(String email);

    List<ArchiveList> getFromArchive(String email);
    TempTaskList getFromTaskById(String email,long taskId) throws TaskNotFoundException;

    List<User> getAllDetail();

    User updateTask(String email, TempTaskList tempTaskList,long taskId) throws UserNotFoundException;

    String getFromUser(String email);
}
