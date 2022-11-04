package com.niit.TrashService.controller;

import com.niit.TrashService.exception.TrashNotFoundException;
import com.niit.TrashService.exception.TaskAlreadyExistsException;
import com.niit.TrashService.model.TaskList;
import com.niit.TrashService.model.User;
import com.niit.TrashService.service.TrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v4")
public class TrashController {
    TrashService trashService;

    @Autowired
    public TrashController(TrashService trashService) {
        this.trashService = trashService;
    }

    @PostMapping("/registertrash")
    public ResponseEntity saveUser(@RequestBody User user) {
        User createdUser = trashService.saveUser(user);
        return new ResponseEntity("User Created", HttpStatus.CREATED);
    }

    @PutMapping("/trash/{email}")
    public ResponseEntity<?> saveTaskToTrash(@RequestBody TaskList taskList, @PathVariable String email) throws TrashNotFoundException, TaskAlreadyExistsException {
        try {
            System.out.println("Data is saving in trash");
            return new ResponseEntity<>(trashService.saveToTrash(email,taskList), HttpStatus.CREATED);
        } catch (TrashNotFoundException e) {
            throw new TrashNotFoundException();
        } catch (TaskAlreadyExistsException e){
            throw new TaskAlreadyExistsException();
        } catch (Exception e) {
            System.out.println("Task Already Present");
            return new ResponseEntity<>("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/trash/{email}")
    public ResponseEntity<?> getFromTask(@PathVariable String email) {
        return new ResponseEntity<>(trashService.getFromTask(email), HttpStatus.OK);
    }
    @GetMapping("/trash")
    public ResponseEntity<?> getAllContents()
    {
        try{
            return new ResponseEntity<>(trashService.getAllTrash(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return  new ResponseEntity<>("try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deletefromtrash/{email}/{taskId}")
    public ResponseEntity<?> deleteFromTask(@PathVariable String email, @PathVariable long taskId) throws TrashNotFoundException {
        return new ResponseEntity<>(trashService.deleteFromTask(email,taskId), HttpStatus.OK);
    }

}
