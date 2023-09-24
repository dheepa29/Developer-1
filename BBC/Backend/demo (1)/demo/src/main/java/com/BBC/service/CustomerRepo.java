package com.BBC.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BBC.model.customer;

public interface CustomerRepo extends JpaRepository<customer,Integer> {

}
