package com.niit.ToDoService.service;

import com.niit.ToDoService.exception.TaskNotFoundException;
import com.niit.ToDoService.exception.UserAlreadyExistsException;
import com.niit.ToDoService.exception.UserNotFoundException;
import com.niit.ToDoService.model.ArchiveList;
import com.niit.ToDoService.model.TempTaskList;
import com.niit.ToDoService.model.User;
import com.niit.ToDoService.proxy.UserAuthProxy;
import com.niit.ToDoService.proxy.UserTrashProxy;
import com.niit.ToDoService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserAuthProxy userAuthProxy;
    private UserTrashProxy userTrashProxy;

    @Autowired

    public UserServiceImpl(UserRepository userRepository, UserAuthProxy userAuthProxy, UserTrashProxy userTrashProxy) {
        this.userRepository = userRepository;
        this.userAuthProxy = userAuthProxy;
        this.userTrashProxy = userTrashProxy;
    }


    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        ResponseEntity response = userAuthProxy.saveUser(user);
        ResponseEntity response1 = userTrashProxy.saveUser(user);
//        if (response.getStatusCodeValue() == 201 && response1.getStatusCodeValue()==201) {
            return userRepository.save(user);
//        } else
//            return null;
    }

    @Override
    public User addToTask(String email, TempTaskList tempTaskList) {

        User user = userRepository.findById(email).get();
        if(user.getList() == null) {
            user.setList(Arrays.asList(tempTaskList));
        }
        else {
            user.getList().add(tempTaskList);
        }
        return userRepository.save(user);
    }

    @Override
    public User addToArchive(String email, ArchiveList archiveList) {

        User user = userRepository.findById(email).get();
        if(user.getArchive() == null) {
            user.setArchive(Arrays.asList(archiveList));
        }
        else {
            user.getArchive().add(archiveList);
        }
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteFromTask(String email, long taskId) throws TaskNotFoundException {
        User user = userRepository.findById(email).get();
        user.getList().removeIf(x -> Objects.equals(x.getTaskId(), taskId));
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean deleteFromArchive(String email, long taskId) throws TaskNotFoundException {
        User user = userRepository.findById(email).get();
        user.getArchive().removeIf(x -> Objects.equals(x.getTaskId(), taskId));
        userRepository.save(user);
        return true;
    }

    @Override
    public List<TempTaskList> getFromTask(String email) {
        return userRepository.findById(email).get().getList();
    }

    @Override
    public List<ArchiveList> getFromArchive(String email) {
        return userRepository.findById(email).get().getArchive();
    }

    @Override
    public TempTaskList getFromTaskById(String email, long taskId) throws TaskNotFoundException {
        User user = userRepository.findById(email).get();
        if (user.getList() == null) {
            throw new TaskNotFoundException();
        } else {
            List<TempTaskList> taskList = user.getList();
            TempTaskList task1 = taskList.stream().filter(a -> a.getTaskId() == taskId).collect(Collectors.toList()).get(0);
            return task1;
        }
    }





    @Override
    public List<User> getAllDetail() {
        return userRepository.findAll();
    }

    @Override
    public User updateTask(String email, TempTaskList tempTaskList, long taskId) throws UserNotFoundException {
        if (userRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(email).get();

        List<TempTaskList> taskList = user.getList();
        TempTaskList task1 = taskList.stream().filter(a -> a.getTaskId() == taskId).collect(Collectors.toList()).get(0);
        int index = taskList.indexOf(task1);
        tempTaskList.setTaskId(taskId);
        taskList.set(index, tempTaskList);
        user.setList(taskList);
        return userRepository.save(user);
    }

    @Override
    public String getFromUser(String email) {
        return userRepository.findById(email).get().getName();
    }
}
