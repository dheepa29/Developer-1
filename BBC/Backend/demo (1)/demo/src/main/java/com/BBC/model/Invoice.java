package com.BBC.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Invoice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "customer_id")
    private String customerId;
    private String email;
    private double amountDue;
    private Date dueDate;
    private String status ;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Invoice(Long id, String name, String customerId, String email, double amountDue, Date dueDate , String status) {
		super();
		this.id = id;
		this.name = name;
		this.customerId = customerId;
		this.email = email;
		this.amountDue = amountDue;
		this.dueDate = dueDate;
		this.status = status;
	}
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", name=" + name + ", customerId=" + customerId + ", email=" + email
				+ ", amountDue=" + amountDue + ", dueDate=" + dueDate + ",status=" + status + "]";
	}
    
    

}
