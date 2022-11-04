package com.niit.UserAuthentication.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.UserNotFoundException;
import com.niit.UserAuthentication.service.SecurityTokenGenerator;
import com.niit.UserAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @HystrixCommand(fallbackMethod = "fallbackLogin",commandKey = "loginKey",groupKey = "login")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMillisecounds",value = "10000")
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
        System.out.println("");
        Map<String, String> map = null;
        try {
            User userObj = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (userObj.getEmail().equals(user.getEmail())) {
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Try after sometime !!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    public ResponseEntity fallbackLogin(@RequestBody User user) {

        String msg = "login failed";
        responseEntity = new ResponseEntity(msg, HttpStatus.GATEWAY_TIMEOUT);
        return responseEntity;
    }

    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return responseEntity = new ResponseEntity("User Created", HttpStatus.CREATED);
    }

    @GetMapping("/displayalluser")
    public ResponseEntity getAllUsers(HttpServletRequest request) {
        List<User> list = userService.getAllUsers();
        responseEntity = new ResponseEntity(list, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/password/{email}")
    public ResponseEntity<?> getPassword(@PathVariable String email) {
        return new ResponseEntity<>(userService.getPassword(email), HttpStatus.OK);
    }
}