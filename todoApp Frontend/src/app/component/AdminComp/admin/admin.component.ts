import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AccountServiceService } from 'src/app/Services/account-service.service';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
hide=true;
  logForm:FormGroup;
  constructor(private api:AccountServiceService,private router:Router,private snackbar:MatSnackBar) {this.logForm=new FormGroup({
    email:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required,Validators.minLength(6)]),
    },
    );}

  ngOnInit(): void {
  }
  checkData(){
    const logData = this.logForm.value;
      console.log(logData);
      this.api.checkAdmin(logData).subscribe(()=>{
        this.snackbar.open("Login Successfull",'',{duration:3000, verticalPosition:'top'});
        this.api.loginAdmin(logData);
        this.router.navigate(['admin-dashboard']);
    
      },error=>{
        this.snackbar.open(" Wrong Id Or Password",'',{duration:3000, verticalPosition:'top'});
      });
      
  }
}
