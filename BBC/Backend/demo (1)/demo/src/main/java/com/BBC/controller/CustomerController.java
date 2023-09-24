package com.BBC.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BBC.model.Invoice;
import com.BBC.model.customer;
import com.BBC.service.CustomerRepo;
import com.BBC.serviceImpl.CustomerServiceImpl;
import com.BBC.serviceImpl.InvoiceService;

import CustomerHelper.helper;
import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	
	@Autowired
    private CustomerServiceImpl customerserviceimpl;
	
	@Autowired
    private InvoiceService invoiceservice;

    @PostMapping("/customer/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (helper.checkExcelFormat(file)) {
            //true

            this.customerserviceimpl.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping("/customer")
    public List<customer> getAllProduct() {
        return this.customerserviceimpl.getAllProducts();
    }
    
    @GetMapping("/invoice")
    public List<Invoice> getAllInvoice() {
        return this.invoiceservice.getAllInvoice();
    }
    
 // New endpoint to filter invoices based on customerId and status
    @GetMapping("/invoice/filter")
    public ResponseEntity<List<Invoice>> getFilteredInvoices(
            @RequestParam(value = "customerId", required = false) String customerId,
            @RequestParam(value = "status", required = false) String status) {
    	
    

        // Call your service method to retrieve filtered invoices
        List<Invoice> filteredInvoices = invoiceservice.findInvoicesByCustomerIdAndStatus(customerId, status);
        
        

        return ResponseEntity.ok(filteredInvoices);
    }
    
    @PutMapping("/invoice/{invoiceId}/status")
    public ResponseEntity<?> updateInvoiceStatus(
            @PathVariable Long invoiceId,
            @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("status");
        
        try {
        	Invoice updatedInvoice = invoiceservice.updateStatus(invoiceId, newStatus);
            return ResponseEntity.ok(updatedInvoice);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<customer> createCustomer(@RequestBody customer customer) {
        customer savedCustomer = customerserviceimpl.savecustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }


}
