import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AccountServiceService } from 'src/app/Services/account-service.service';
import { CardfetchService } from 'src/app/Services/cardfetch.service';
import { AddMessageComponent } from './add-message/add-message.component';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  email:any;
  searchKey: any;
  public searchTerm !: string;
  public totalItem : number = 0;
  taskList: any[]=[];

  constructor(public dailog:MatDialog,private breakpointObserver: BreakpointObserver,private fetch:CardfetchService,private route:Router,private account:AccountServiceService) { }
  ngOnInit() {
   

   

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
  openDailog() {
    const dailogRef = this.dailog.open(AddMessageComponent, { width: '25%', panelClass: 'bg-color' });
  }
 DisplayMessage(){
this.route.navigate(['deleteadmin']);

 }

  logoutAdmin(){
    this.account.logoutAdmin();
    this.route.navigate(['./admin'])
  }


}


