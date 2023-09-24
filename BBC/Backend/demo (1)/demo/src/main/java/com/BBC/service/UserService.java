package com.BBC.service;

import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

	public int loginValidation(String EmployeeId , String OTP);
	
}
