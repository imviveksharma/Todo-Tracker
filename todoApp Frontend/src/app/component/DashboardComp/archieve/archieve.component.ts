import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CardfetchService } from 'src/app/Services/cardfetch.service';


@Component({
  selector: 'app-archieve',
  templateUrl: './archieve.component.html',
  styleUrls: ['./archieve.component.css']
})
export class ArchieveComponent implements OnInit {

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
    this.fetch.getTaskFromArchive(this.email).subscribe(res => {
      console.log(res);
      this.productList = res;
    });

    this.fetch.getTaskFromArchive(this.email)
      .subscribe(res => {
        this.productList = res;
        this.filterCategory = res;

        // this.productList.forEach((a: any) => {
        //   if(a.category ==="women's clothing" || a.category ==="men's clothing"){
        //     a.category ="fashion"
        //   }
        //   Object.assign(a, { quantity: 1, total: a.price });
        // });
        console.log(this.productList)
      });
    this.fetch.search.subscribe((val: any) => {
      this.searchKey = val;
    })

  }
  deletetaskFromArchive(taskId: any) {
    this.fetch.deleteTaskFromArchive(taskId).subscribe({
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

 
}
