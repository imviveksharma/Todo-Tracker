import { BreakpointObserver } from '@angular/cdk/layout';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { AccountServiceService } from 'src/app/Services/account-service.service';
import { CardfetchService } from 'src/app/Services/cardfetch.service';
import { EmailserviceService } from 'src/app/Services/emailservice.service';
import { MainCompComponent } from '../main-comp/main-comp.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  email:any;
  searchKey: any;
  public searchTerm !: string;
  public totalItem : number = 0;
  taskList: any[]=[];
  name:any;
  NameList: any;
  constructor(private http:HttpClient,public dailog:MatDialog,private breakpointObserver: BreakpointObserver,private fetch:CardfetchService,private route:Router,private account:AccountServiceService,private emailService:EmailserviceService) { }
  ngOnInit() {
    
    this.fetch.getTaskFromAdmin().subscribe((res)=>{
      this.totalItem = res.length;
    });

  

    this.email=localStorage.getItem("email");
    console.log(this.email);

    this.emailService.getName(this.email).subscribe((x:any)=>{this.name=x;
      console.log(this.name);
    });


    this.fetch.search.subscribe((val: any) => {
      this.searchKey = val;
    });

    this.fetch.getAdminTask().subscribe(res=>{
      this.taskList =res ;
    });


  }
  
  value = '';
  @ViewChild('sidenav') sidenav: MatSidenav | undefined ;
  isExpanded = false;
  isShowing = false;

  mouseenter() {
    if (!this.isExpanded) {
      this.isShowing = true;
    }
  }

  mouseleave() {
    if (!this.isExpanded) {
      this.isShowing = false;
    }
  }
  search(event:any){
    this.searchTerm = (event.target as HTMLInputElement).value;
    console.log(this.searchTerm);
    this.fetch.search.next(this.searchTerm);
  }
  
  refresh(){
    location.reload();
  }
  resetStorage(){
    localStorage.removeItem('email')
  }

  logoutUser()
  {
  this.account.logout()
  location.reload()
  this.route.navigate(['./login'])
  }

}
