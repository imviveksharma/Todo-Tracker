package com.niit.ToDoService.controller;

import com.niit.ToDoService.exception.TaskNotFoundException;
import com.niit.ToDoService.exception.UserAlreadyExistsException;
import com.niit.ToDoService.exception.UserNotFoundException;
import com.niit.ToDoService.model.ArchiveList;
import com.niit.ToDoService.model.TempTaskList;
import com.niit.ToDoService.model.User;
import com.niit.ToDoService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2")
public class UserController {
    private ResponseEntity responseEntity;
    private UserService userService;

    @Autowired
    public UserController(UserService userService){this.userService=userService;}

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }
    @PutMapping("/addtotask/{email}")
    public ResponseEntity<?> addToTask(@PathVariable String email, @RequestBody TempTaskList tempTaskList) {
        return new ResponseEntity<>(userService.addToTask(email,tempTaskList), HttpStatus.OK);
    }
    @PutMapping("/addtoArchive/{email}")
    public ResponseEntity<?> addToArchive(@PathVariable String email, @RequestBody ArchiveList archiveList) {
        return new ResponseEntity<>(userService.addToArchive(email,archiveList), HttpStatus.OK);
    }
    @GetMapping("/getfromtask/{email}")
    public ResponseEntity<?> getFromTask(@PathVariable String email) {
        return new ResponseEntity<>(userService.getFromTask(email), HttpStatus.OK);
    }
    @GetMapping("/getfromuser/{email}")
    public ResponseEntity<?> getFromUser(@PathVariable String email){
        return new ResponseEntity<>(userService.getFromUser(email),HttpStatus.OK);
    }
    @GetMapping("/getfromarchive/{email}")
    public ResponseEntity<?> getFromArchive(@PathVariable String email) {
        return new ResponseEntity<>(userService.getFromArchive(email), HttpStatus.OK);
    }

    @GetMapping("/gettask/{email}/{taskId}")
    public ResponseEntity<?> getFromTaskById(@PathVariable String email, @PathVariable long taskId) throws TaskNotFoundException {
        return new ResponseEntity<>(userService.getFromTaskById(email,taskId), HttpStatus.OK);
    }


    @DeleteMapping("/deletefromtask/{email}/{taskId}")
    public ResponseEntity<?> deleteFromTask(@PathVariable String email, @PathVariable long taskId) throws TaskNotFoundException {
        return new ResponseEntity<>(userService.deleteFromTask(email,taskId), HttpStatus.OK);
    }
    @DeleteMapping("/deletefromarchive/{email}/{taskId}")
    public ResponseEntity<?> deleteFromArchive(@PathVariable String email, @PathVariable long taskId) throws TaskNotFoundException {
        return new ResponseEntity<>(userService.deleteFromArchive(email,taskId), HttpStatus.OK);
    }

    @PutMapping("/update/{email}/{taskId}")
    public ResponseEntity<User> updateTask(@PathVariable String email,@RequestBody TempTaskList tempTaskList,@PathVariable long taskId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateTask(email,tempTaskList,taskId),HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        try{
            responseEntity=new ResponseEntity<>(userService.getAllDetail(),HttpStatus.OK);
        } catch (Exception e) {
            responseEntity=new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
