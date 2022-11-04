import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { CardfetchService } from 'src/app/Services/cardfetch.service';


@Component({
  selector: 'app-add-message',
  templateUrl: './add-message.component.html',
  styleUrls: ['./add-message.component.css']
})
export class AddMessageComponent implements OnInit {
  errorMessage: any;

  constructor(private fetch:CardfetchService,private toastr:ToastrService) { }

  ngOnInit(): void {
  }

  AddMessage = new FormGroup({
    taskTitle: new FormControl('', [Validators.required]),
  
  })


  Message(){
this.fetch.addMessage(this.AddMessage.value.taskTitle).subscribe({
  next: data => {
    console.log(data);
    this.toastr.success("Message Saved Successfully")
    location.reload();
 
  },
  error: err => {
    this.errorMessage = err.error.message;
    this.toastr.error("Something Went Wrong")
  }
});
  }
}
