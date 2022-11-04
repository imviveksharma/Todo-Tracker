import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AccountServiceService } from 'src/app/Services/account-service.service';
import { EmailserviceService } from 'src/app/Services/emailservice.service';

import { MatDialog } from '@angular/material/dialog';
import { ForgetPasswordComponent } from '../forget-password/forget-password.component';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  logindata?:any;
  messageOnSubmit:any;
  isloggedin:boolean | undefined;
subject="Login Successful";
message="You have successfully login in our portal. If you have not done it Contact immediately. ";

  constructor(private route:Router,private api:AccountServiceService,private es:EmailserviceService,private snackbar:MatSnackBar,private toastr: ToastrService,public dailog:MatDialog) {
    window.localStorage.clear();
   }

   loginFormGroup = new FormGroup({
    email: new FormControl('',[Validators.required , Validators.email]),
    password: new FormControl('',[Validators.required,Validators.minLength(3)])
  });
  get password() {
    return this.loginFormGroup.get('password')
  }

  get email() {
    return this.loginFormGroup.get('email')
  }
  
  loginCheck(){
    this.logindata= this.loginFormGroup.value;
    console.log(this.logindata);
    this.api.checkUser(this.logindata).subscribe(
      response => {
        console.log("Login Successfull");
        console.log(this.logindata);


        this.api.loginUser(this.logindata);

        
        this.toastr.success("You have succesfully logged in");
        this.es.sendMail(this.logindata.email,this.subject,this.message).subscribe();
        window.localStorage.setItem('email', this.logindata.email);
        
        console.log(this.logindata.email);
        // this.snackbar.open("Login Successfull",'',{duration:3000, verticalPosition:'top'});
        this.route.navigate(['dashboard']);
      },
      error => {
        console.log("Login error");
        console.log(error);
        this.toastr.error("some thing went wrong try again!!!!");
        return;
      }
    );
  }

  forgot(){
    const dailogRef = this.dailog.open(ForgetPasswordComponent, { width: '25%', panelClass: 'bg-color' });
  }
 
  ngOnInit(): void {
  }
}
