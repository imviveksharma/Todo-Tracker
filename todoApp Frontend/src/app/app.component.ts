import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'todoApp';
  loader = true;
  ngOnInit(){
    setTimeout(()=>{
      this.loader =false;
    },1900);
  }
}
