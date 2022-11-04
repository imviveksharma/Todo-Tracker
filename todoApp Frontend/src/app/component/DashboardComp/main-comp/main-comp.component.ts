import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CardfetchService } from 'src/app/Services/cardfetch.service';




@Component({
  selector: 'app-main-comp',
  templateUrl: './main-comp.component.html',
  styleUrls: ['./main-comp.component.css']
})
export class MainCompComponent implements OnInit {
  errorMessage: any;
  email: any;
  currentDate: any = new Date();
  date!: Date;
  ordinaryDateSelected!: Date;

  constructor(private toastr: ToastrService,private fetch:CardfetchService,private route:Router,private snackbar:MatSnackBar) { }
  ngOnInit(): void {
    this.email = localStorage.getItem("email");
    console.log(this.email);
  }

  AddTaskform = new FormGroup({
    taskTitle: new FormControl('', [Validators.required]),
    category: new FormControl('', [Validators.required]),
    description: new FormControl(''),
    priority: new FormControl('', [Validators.required]),
    date: new FormControl('')
  })

  Add(): void {

  this.fetch.addProductAdmin(this.AddTaskform.value.taskTitle, this.AddTaskform.value.category,
    this.AddTaskform.value.description, this.AddTaskform.value.priority,
    this.AddTaskform.value.date).subscribe({
      next: data => {
        console.log(data);
        this.snackbar.open("Task Added",'',{duration:3000});
        this.toastr.success("Your task is succesfully added");
        location.reload();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.toastr.error("Your task is not added please add again");
      }
    });
}
}
