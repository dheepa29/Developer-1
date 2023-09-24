package com.BBC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BBC.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT * FROM invoice i WHERE (:customerId IS NULL OR i.customer_id = :customerId) AND (:status IS NULL OR i.status = :status)", nativeQuery = true)
	List<Invoice> findByCustomerIdAndStatus(String customerId, String status);
    
    Optional<Invoice> findById(Long id);

}
