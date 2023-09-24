import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {

  Employee : String = '';
  auth : boolean = false;

  constructor(private http:HttpClient) { }

  getUserData(EmployeeId: any, OTP: any){
    this.Employee = EmployeeId;
    this.auth = true;
    return this.http.get('http://localhost:8080/users/'+EmployeeId+'/'+ OTP);
  }
}
