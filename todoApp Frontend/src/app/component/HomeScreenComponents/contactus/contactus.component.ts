import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EmailserviceService } from 'src/app/Services/emailservice.service';

@Component({
  selector: 'app-contactus',
  templateUrl: './contactus.component.html',
  styleUrls: ['./contactus.component.css']
})
export class ContactusComponent implements OnInit {
 
  
  
  constructor( private email:EmailserviceService) { }

  ngOnInit(): void {
  }
  ContactUsForm = new FormGroup({
    name:new FormControl('',[Validators.required,Validators.minLength(3),Validators.pattern('^[a-zA-Z ]*$')]),
    email: new FormControl('',[Validators.required , Validators.email]),
    text: new FormControl('',[Validators.required,Validators.minLength(6)]),
    message:new FormControl('',[Validators.required ])
  });

  subject:any;
  message:any;

  SendMessageToUser(){
   const form=this.ContactUsForm.value;
this.subject="Support"
 this.message="Hey "+(form.name)+". Thanks for contacting us. We will contacting to you shortly.";
   console.log(this.message)
    this.email.sendMail(form.email,this.subject,this.message).subscribe();
  }
}
