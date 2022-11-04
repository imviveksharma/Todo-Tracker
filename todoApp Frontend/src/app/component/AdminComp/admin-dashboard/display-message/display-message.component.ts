import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { CardfetchService } from 'src/app/Services/cardfetch.service';

@Component({
  selector: 'app-display-message',
  templateUrl: './display-message.component.html',
  styleUrls: ['./display-message.component.css']
})
export class DisplayMessageComponent implements OnInit {
  public products : any = [];
  errorMessage: any;
  constructor(private fetch:CardfetchService,private toastr:ToastrService) { }

  ngOnInit(): void {
this.fetch.getTaskFromAdmin().subscribe(res=>{
  this.products = res;
 
})

  }
  removeItem(taskId:any){
    this.fetch.deleteFromAdmin(taskId).subscribe({
      next: data => {
        console.log(data);
        this.toastr.success("Message Deleted Successfully")
        location.reload();
     
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.toastr.error("Something Went Wrong")
      }
    });


}
}
