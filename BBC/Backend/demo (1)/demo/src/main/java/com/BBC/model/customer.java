package com.BBC.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class customer {

	@Id
	String customerid;
	String name;
	double unitconsumption;
	Date billduedate;
	String email;
	String telephone;
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUnitconsumption() {
		return unitconsumption;
	}
	public void setUnitconsumption(double unitconsumption) {
		this.unitconsumption = unitconsumption;
	}
	public Date getBillduedate() {
		return billduedate;
	}
	public void setBillduedate(Date billduedate) {
		this.billduedate = billduedate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public customer(String customerid, String name, double unitconsumption, Date billduedate, String email,
			String telephone) {
		super();
		this.customerid = customerid;
		this.name = name;
		this.unitconsumption = unitconsumption;
		this.billduedate = billduedate;
		this.email = email;
		this.telephone = telephone;
	}
	public customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "customer [customerid=" + customerid + ", name=" + name + ", unitconsumption=" + unitconsumption
				+ ", billduedate=" + billduedate + ", email=" + email + ", telephone=" + telephone + "]";
	}

	
}
