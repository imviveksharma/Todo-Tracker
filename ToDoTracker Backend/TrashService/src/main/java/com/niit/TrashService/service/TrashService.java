package com.niit.TrashService.service;

import com.niit.TrashService.exception.TrashNotFoundException;
import com.niit.TrashService.exception.TaskAlreadyExistsException;
import com.niit.TrashService.model.TaskList;
import com.niit.TrashService.model.User;

import java.util.List;

public interface TrashService {

    User saveUser(User user);
    User saveToTrash(String email , TaskList taskList) throws TrashNotFoundException, TaskAlreadyExistsException;
    List<User> getAllTrash() throws Exception;
    List<TaskList> getFromTask(String email);
    Boolean deleteFromTask(String email, long taskId) throws TrashNotFoundException;
}
