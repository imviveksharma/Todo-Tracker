import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailserviceService {

  constructor(private http:HttpClient) { }

  emailUrl="http://localhost:9000/api/v5/sendemail";
  sendMail(to:any,subject:string,message:string):Observable<any>{
    return this.http.post<any>(`${this.emailUrl}`,{to,subject,message});
  }
  PasswordApi="http://localhost:9000/api/v1/password/";
  getPassword(email:any){
    console.log(email);
return this.http.get(this.PasswordApi+email,{responseType:'text'});
  }
NameApi="http://localhost:9000/api/v2/getfromuser/"
getName(email:any){
  return this.http.get(this.NameApi+email,{responseType:'text'});
}


    
}
