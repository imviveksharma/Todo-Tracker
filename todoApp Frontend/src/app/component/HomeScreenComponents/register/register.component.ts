import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AccountServiceService } from 'src/app/Services/account-service.service';
import { EmailserviceService } from 'src/app/Services/emailservice.service';




@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  registerdata?:any;
  
   
   subject="TODO Registration Successful";
   message="You have successfully registered your email id with our todo application.";

  constructor(private api:AccountServiceService,private route:Router,private es:EmailserviceService,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
  }
  registerFormGroup = new FormGroup({
    name:new FormControl('',[Validators.required,Validators.minLength(3),Validators.pattern('^[a-zA-Z ]*$')]),
    email: new FormControl('',[Validators.required , Validators.email]),
    password: new FormControl('',[Validators.required,Validators.minLength(6)]),
    phoneNo:new FormControl('',[Validators.required , Validators.minLength(10),Validators.pattern("^[0-9]*$")]),
  });
  get name() {
    return this.registerFormGroup.get('name')
  }
  
  get email() {
    return this.registerFormGroup.get('email')
  }

  get password() {
    return this.registerFormGroup.get('password')
  }

  get phoneNo() {
    return this.registerFormGroup.get('phoneNo')
  }

  onSubmit(): void { 
    this.registerdata= this.registerFormGroup.value;
    this.api.storeUser(this.registerdata).subscribe({
      next: data=>{
        console.log(data);
        this.es.sendMail(this.registerdata.email,this.subject,this.message).subscribe();
        this.snackbar.open("Registration Successfull",'',{duration:3000, verticalPosition:'top'});
        this.snackbar.open("Redirecting to Login Page",'',{duration:2000, verticalPosition:'top'});
        this.route.navigate(['login'])
      },error: err=>{
             console.log("err")
      }
    })
    }
   
 
}
