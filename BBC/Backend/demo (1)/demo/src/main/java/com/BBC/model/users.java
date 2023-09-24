package com.BBC.model;

public class users {

	String Name ;
	String EmployeeId ;
	String OTP ;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	public users(String name, String employeeId, String oTP) {
		super();
		Name = name;
		EmployeeId = employeeId;
		OTP = oTP;
	}
	public users() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "users [Name=" + Name + ", EmployeeId=" + EmployeeId + ", OTP=" + OTP + "]";
	}
	
	
	
}
