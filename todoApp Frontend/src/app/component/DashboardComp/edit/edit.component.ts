import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CardfetchService } from 'src/app/Services/cardfetch.service';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  email: any;
  taskId: any;
  toastr: any;
  constructor(private activatedRoute: ActivatedRoute,private http: HttpClient,private route:Router,private fetch: CardfetchService) { }

  ngOnInit(): void {
    this.email = localStorage.getItem("email");
    console.log(this.email);
this.taskId=localStorage.getItem("taskId");
console.log("inside Edit component")
console.log(this.taskId);

    this.activatedRoute.paramMap.subscribe(params => {
     
      this.fetch.gettaskData(this.email,this.taskId).subscribe(data => {
      this.AddTaskform.setValue(data)

      })
    })
  }

  AddTaskform = new FormGroup({
    taskId:new FormControl('',[Validators.required]),
    taskTitle: new FormControl('', [Validators.required]),
    category: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    priority: new FormControl('', [Validators.required]),
    date: new FormControl('', [Validators.required])
  })


  url="http://localhost:9000/api/v2/update/"
  edittask() {
    const Data = this.AddTaskform.value;
    const endPoints = this.AddTaskform.value.taskId
    this.http.put(this.url +this.email +'/' + endPoints, Data).subscribe(data => {
      console.log(data)
      location.reload();
      this.AddTaskform.reset();
      this.toastr.success("Data is updated");
    })
  }

}
