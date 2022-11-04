package com.niit.TrashService.service;

import com.niit.TrashService.exception.TrashNotFoundException;
import com.niit.TrashService.exception.TaskAlreadyExistsException;
import com.niit.TrashService.model.TaskList;
import com.niit.TrashService.model.User;
import com.niit.TrashService.repository.TrashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TrashServiceImpl implements TrashService{

    TrashRepository trashRepository;
    @Autowired
    public TrashServiceImpl(TrashRepository trashRepository)
    {
        this.trashRepository = trashRepository;
    }
    @Override
    public User saveUser(User user) {
        return trashRepository.save(user);
    }

    @Override
    public User saveToTrash(String email, TaskList taskList) throws TrashNotFoundException, TaskAlreadyExistsException {
        User user =trashRepository.findById(email).get();
        if(user.getList() == null) {
            user.setList(Arrays.asList(taskList));
        }
        else {
            user.getList().add(taskList);
        }
        return trashRepository.save(user);
    }
    @Override
    public List<User> getAllTrash() throws Exception {
        try {
            return trashRepository.findAll();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<TaskList> getFromTask(String email) {
        return trashRepository.findById(email).get().getList();
    }

    @Override
    public Boolean deleteFromTask(String email, long taskId) throws TrashNotFoundException {
        User user = trashRepository.findById(email).get();
        user.getList().removeIf(x -> Objects.equals(x.getTaskId(), taskId));
        trashRepository.save(user);
        return true;
    }
}

