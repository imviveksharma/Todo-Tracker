import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CardfetchService } from 'src/app/Services/cardfetch.service';


@Component({
  selector: 'app-trash',
  templateUrl: './trash.component.html',
  styleUrls: ['./trash.component.css']
})
export class TrashComponent implements OnInit {

  email: any;
  productList: any[] = [];
  errorMessage: any;
  // public productList: any;
  // email: any;
  // errorMessage: any;
  filterCategory: any;
  searchKey: string = "";
  public searchTerm !: string;
  constructor(private breakpointObserver: BreakpointObserver, private fetch: CardfetchService, private route: Router) { }

  ngOnInit() {
    this.email = localStorage.getItem("email");
    console.log(this.email);
    this.fetch.getTaskFromTrash(this.email).subscribe(res => {
      console.log(res);
      this.productList = res;
    });

    this.fetch.getTaskFromTrash(this.email)
      .subscribe(res => {
        this.productList = res;
        this.filterCategory = res;


        console.log(this.productList)
      });
    this.fetch.search.subscribe((val: any) => {
      this.searchKey = val;
    })

  }

  deletetaskFromTrash(email: any, taskId: any) {
    this.fetch.deleteTaskFromTrash(email, taskId).subscribe({
      next: data => {
        console.log(data);
        location.reload();
      },
      error: err => {
        this.errorMessage = err.error.message;

      }
    });
  }
  filter(category: string) {
    this.filterCategory = this.productList
      .filter((a: any) => {
        if (a.category == category || category == '') {
          return a;
        }
      })
  }
  RecoverDeletedTask(item: any) {
    this.fetch.RecoverDeletedTask(item).subscribe(
      response => {
        console.log(item)

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


}
