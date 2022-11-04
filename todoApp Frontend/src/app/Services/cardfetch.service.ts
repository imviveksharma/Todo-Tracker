import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CardfetchService {

  public search = new BehaviorSubject<string>("");
  public productList = new BehaviorSubject<any>([]);



  constructor(private http: HttpClient) { }



  AUTH_API2 = "http://localhost:9000/api/v2/addtotask/"

  AUTH_AP1 = "http://localhost:9000/api/v2/getfromtask/"

  getProducts(){
    return this.productList.asObservable();
  }

  getTaskFromToDo(email: any) {
    return this.http.get(this.AUTH_AP1 + email)
      .pipe(map((res: any) => {
        return res;
      }))
  }
 







  AUTH_AP11 = "http://localhost:9000/api/v2/gettask/"
  // getTaskFromToDoWithId(email:any,taskId:any){
  //   return this.http.get(this.AUTH_AP11+email+taskId)
  //   .pipe(map((res:any)=>{
  //     return res;
  //   }))
  // }
  // getProductData(email:any,taskId:any): Observable<any> {
  //   return this.http.get<any>(this.AUTH_AP11 + email+taskId);
  // }



  AUTH_AP5 = "http://localhost:9000/api/v4/trash/"
  getTaskFromTrash(email: any) {
    return this.http.get(this.AUTH_AP5 + email)
      .pipe(map((res: any) => {
        return res;
      }))
  }

  AUTH_AP12 = "http://localhost:9000/api/v2/getfromarchive/"
  getTaskFromArchive(email: any) {
    return this.http.get(this.AUTH_AP12 + email)
      .pipe(map((res: any) => {
        return res;
      }))
  }

  AUTH_AP13 = "http://localhost:9000/api/v3/getfromtask/admin@gmail.com"
  getTaskFromAdmin() {
    return this.http.get(this.AUTH_AP13 )
      .pipe(map((res: any) => {
        return res;
      }))
  }


  addProductAdmin(taskTitle: any, category: any, description: any, priority: any, date: any): Observable<any> {
    let email = window.localStorage.getItem('email');
    var taskId = new Date().getTime();
    console.log(taskId);
    return this.http.put(this.AUTH_API2 + email, { taskId, taskTitle, category, description, priority, date }
    );
  }
  AUTH_AP14 = "http://localhost:9000/api/v3/addtotask/admin@gmail.com"
  addMessage(taskTitle: any) {
    var taskId = new Date().getTime();
    return this.http.put(this.AUTH_AP14, { taskId,taskTitle }
    );


  }
  deleteTaskFromTodo(email: any, taskId: any) {
    console.log(email);
    console.log(taskId);
    return this.http.delete("http://localhost:9000/api/v2/deletefromtask/" + email + "/" + taskId);
  }
  url1="http://localhost:9000/api/v2/gettask/"
  url6: string = "http://localhost:9000/api/v2/addtotask/"
  url8: string = "http://localhost:9000/api/v2/addtoArchive/"
  gettaskData(email: any, taskId: any): Observable<any> {
    return this.http.get<any>(this.url1 + email + '/' + taskId);
  }
  url5: string = "http://localhost:9000/api/v4/trash/"
  deleteTask(task: any) {
    let email = window.localStorage.getItem('email');
    return this.http.put<any>(this.url5 + email, task);
  }
  deleteTaskFromTrash(email: any, taskId: any) {
    return this.http.delete("http://localhost:9000/api/v4/deletefromtrash/" + email + "/" + taskId);
  }

  deleteTaskFromArchive(taskId: any) {
    let email = window.localStorage.getItem('email');
    return this.http.delete("http://localhost:9000/api/v2/deletefromarchive/" + email + "/" + taskId);
  }
  // getProductData(id: any): Observable<any> {
  //   return this.http.get<any>(this.AUTH_API2 + 'vehicle'+ '/' + id);
  // }
  deleteFromAdmin( taskId: any){
    let email="admin@gmail.com"
  
  return this.http.delete("http://localhost:9000/api/v3/deletefromtask/" +email +"/"+ taskId);
  
  }
 
  RecoverDeletedTask(task: any) {
    let email = window.localStorage.getItem('email');
    return this.http.put<any>(this.url6 + email, task);
  }
  
  taskCompleted(task: any) {
    let email = window.localStorage.getItem('email');
    return this.http.put<any>(this.url8 + email, task);
  }

  getAdminTask(){
    return this.http.get<any>("http://localhost:9000/api/v3/getfromtask/admin@gmail.com")
    .pipe(map((res:any)=>{
      return res;
    }))
  }
}
