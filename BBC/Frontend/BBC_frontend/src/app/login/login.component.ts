import { Component } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  model : any = {};
  getData: boolean = false;
  constructor(private userservice : UserserviceService , private router : Router){}

  ngOnInit() {}

  loginUser(){
    var EmployeeId = this.model.EmployeeId;
    var OTP = this.model.OTP;

    this.userservice.getUserData(EmployeeId, OTP).subscribe((res)=>{

      console.log(res);
      const result = res === 1; // Convert 1 to true and 0 to false
      
      console.log(result);
      this.getData = result;

      if(this.getData== true){
        this.router.navigate(["/home"])
      }else{
        alert('Invalid EmployeeId /OTP');
      }
    })
  }
}
