import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {

  constructor(private http:HttpClient) { }


  registerUrl="http://localhost:9000/api/v2/user";
  storeUser(registerdata:any): Observable<any>{
    return this.http.post<any>(`${this.registerUrl}`,registerdata);
   }

   loginUrl="http://localhost:9000/api/v1/login";
   checkUser(loginData:any):Observable<any>{
    return this.http.post<any>(`${this.loginUrl}`,loginData)
   }

   loginAdminUrl="http://localhost:9000/api/v3/adminlogin";
   checkAdmin(logData:any):Observable<any>{
    return this.http.post<any>(`${this.loginAdminUrl}`,logData)
   }

   registerUrl1="http://localhost:9000/api/v2/userArchive";
   storeUser1(email: string ): Observable<any>{
    return this.http.post<any>(`${this.registerUrl1}`,{email});
   }


   
   loginUser(credentials:any)
   {
    localStorage.setItem("token",credentials)
    return true;
   }
   //  to check user is logged in or not
   isLoggedIn()
   {
   let token=localStorage.getItem("token");
   if(token==undefined || token=='' || token==null){
     return false;
   } else {
     return true;
   }
   }
   
   // for logout user
   logout()
   {
   localStorage.removeItem('token')
   return true;
   }
   
   // for get token 
   
   getToken(){
     return localStorage.getItem("token")
   }


   loginAdmin(admindata:any){
    localStorage.setItem("token1",admindata)
     return true;
   }
 isAdminLoggedIn()
   {
     let token=localStorage.getItem("token1");
     if(token==undefined || token=='' || token==null){
       return false;
     } else {
       return true;
     }
 }
 logoutAdmin()
   {
   localStorage.removeItem('token1')
   return true;
   }











}

