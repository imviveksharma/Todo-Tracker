package com.niit.EmailService.controller;

import com.niit.EmailService.model.Email;
import com.niit.EmailService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v5")
public class EmailController {
@Autowired
private EmailService emailService;


    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody Email email){
       boolean result= this.emailService.sendEmail(email.getTo(),email.getSubject(),email.getMessage());
       if(result){
           return ResponseEntity.ok("Email Sent successfully");
       }else{
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not Sent");
       }

    }
}
