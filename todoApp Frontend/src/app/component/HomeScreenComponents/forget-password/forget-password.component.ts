import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { EmailserviceService } from 'src/app/Services/emailservice.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {

  constructor(private email:EmailserviceService,private toastr: ToastrService) { }

  ngOnInit(): void {
  }
  ForgetPasswordForm =new FormGroup({
    email: new FormControl('',[Validators.required , Validators.email])

  })
  subject:any;
  message:any;
  user:any;
  getPasswordCheck(){
    const data=this.ForgetPasswordForm.value.email;
   console.log(data);
   this.email.getPassword(data).subscribe((response)=>{
    this.user=response;
    this.subject="Password Recovery"
    this.message="Your Email Id is "+ data +" And Password is "+this.user;
    this.email.sendMail(data,this.subject,this.message).subscribe();
this.toastr.success("Password is sent to your Mail.")
   })
}
  
}
