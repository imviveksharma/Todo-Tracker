package com.niit.AdminService.controller;

import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Task;
import com.niit.AdminService.exception.AdminNotFoundException;
import com.niit.AdminService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v3")
public class AdminController {
    private ResponseEntity responseEntity;
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }

    @PostMapping("/adminregister")
    public ResponseEntity saveUser(@RequestBody Admin admin)
    {
        Admin createAdmin = adminService.saveAdmin(admin);
        return responseEntity = new ResponseEntity("Added Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/adminlogin")
    public ResponseEntity loginUser(@RequestBody Admin admin)throws AdminNotFoundException
    {
        try
        {
            Admin adminObj = adminService.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
            responseEntity=new ResponseEntity(HttpStatus.OK);
        }
        catch (AdminNotFoundException adminNotFoundException)
        {
            throw new AdminNotFoundException();
        }
        catch (Exception exception)
        {
            responseEntity=new ResponseEntity("Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/addtotask/{email}")
    public ResponseEntity<?> addToTask(@PathVariable String email, @RequestBody Task task) {
        return new ResponseEntity<>(adminService.addToTask(email,task), HttpStatus.OK);
    }


    @GetMapping("/getfromtask/{email}")
    public ResponseEntity<?> getFromTask(@PathVariable String email) {
        return new ResponseEntity<>(adminService.getFromTask(email), HttpStatus.OK);
    }

    @DeleteMapping("/deletefromtask/{email}/{taskId}")
    public ResponseEntity<?> deleteFromTask(@PathVariable String email, @PathVariable long taskId) {
        return new ResponseEntity<>(adminService.deleteFromTask(email,taskId), HttpStatus.OK);
    }
}
