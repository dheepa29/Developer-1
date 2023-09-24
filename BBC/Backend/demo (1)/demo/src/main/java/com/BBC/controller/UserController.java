package com.BBC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.BBC.service.UserService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@GetMapping("users/{EmployeeId}/{OTP}")
	@CrossOrigin(origins = "http://localhost:4200")
	public int UserLogin(@PathVariable("EmployeeId") String EmployeeId1 , @PathVariable("OTP") String OTP1) {
		int flag = userservice.loginValidation(EmployeeId1, OTP1);
		if(flag==0) {
			return 0;
		}
		
		return flag;
		
	}
	
}
