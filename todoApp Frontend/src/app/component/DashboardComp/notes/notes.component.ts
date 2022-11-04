import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';


import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CardfetchService } from 'src/app/Services/cardfetch.service';

import { __makeTemplateObject } from 'tslib';


import { EditComponent } from '../edit/edit.component';
import { MainCompComponent } from '../main-comp/main-comp.component';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
p:any;

  email: any;
  productList: any[] = [];
  countproducts:any[] =[];
  errorMessage: any;
  filterCategory: any;
  searchKey: string = "";
  public searchTerm !: string;
  constructor(private toastr: ToastrService,public dailog: MatDialog, private breakpointObserver: BreakpointObserver, private fetch: CardfetchService, private route: Router, private snackbar: MatSnackBar) { }

  ngOnInit() {
    this.email = localStorage.getItem("email");
    console.log(this.email);
    this.fetch.getTaskFromToDo(this.email).subscribe(res => {
      this.productList = res;
      this.countproducts=res.length;
      console.log(this.countproducts);
    }
    );
    this.fetch.getTaskFromToDo(this.email)
      .subscribe(res => {
        this.productList = res;
        this.filterCategory = res;
        console.log(this.productList)
      });
    this.fetch.search.subscribe((val: any) => {
      this.searchKey = val;
    })

  }
  openDailog() {
    const dailogRef = this.dailog.open(MainCompComponent, { width: '25%', panelClass: 'bg-color' });
  }

  openDailog1() {
    const dailogRef1 = this.dailog.open(EditComponent, { width: '25%', panelClass: 'bg-color' });
  }
  deletetaskFromToDo(email: any, taskId: any) {
    this.fetch.deleteTaskFromTodo(email, taskId).subscribe({
      next: data => {
        console.log(data);
        this.toastr.success("Task Deleted Succesfully");
        // location.reload();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.toastr.error("Try Again!!!!");
      }
    });
  }
  deletetask(item: any) {
    this.fetch.deleteTask(item).subscribe(
      response => {
        console.log(item)
        this.toastr.success("Task send to Trash");
        // this.snackbar.open("Sent to Trash", '', { duration: 3000 });
        location.reload();
        console.log(response);
      },
      error => {
        console.log(item)
        this.toastr.error("Try Again!!!!");
        console.log("Something Went Wrong")
        console.log(error);
      }
    );
  }

  taskCompleted(item: any) {
    this.fetch.taskCompleted(item).subscribe(
      response => {
        console.log(item)
        this.toastr.success("You can check this task in Archieve Section");
        location.reload();
        console.log(response);
      },
      error => {
        console.log(item)
        console.log("Something Went Wrong")
        console.log(error);
      }
    );
  }

  filter(category: string) {
    this.filterCategory = this.productList
      .filter((a: any) => {
        if (a.category == category || category == '') {
          return a;
        }
      })
  }

onCallingEdit(taskId:any){
  console.log("hello")
  console.log(taskId);
  window.localStorage.setItem('taskId', taskId);





}

}
