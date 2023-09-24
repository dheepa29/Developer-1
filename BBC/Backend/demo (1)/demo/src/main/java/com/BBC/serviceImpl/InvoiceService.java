package com.BBC.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BBC.model.Invoice;
import com.BBC.model.customer;
import com.BBC.service.InvoiceRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public void generateAndSaveInvoice(customer customer) {
        double unitConsumed = customer.getUnitconsumption();
        double ratePerUnit = 41.50;
        Date dueDate = customer.getBillduedate();

        double amountDue = unitConsumed * ratePerUnit;

        // Apply discount for payment before the due date
        if (isPaymentBeforeDueDate(dueDate)) {
            amountDue *= 0.95; // 5% discount
        }

        // Create an invoice
        Invoice invoice = new Invoice();
        invoice.setName("Invoice for " + customer.getName());
        invoice.setCustomerId(customer.getCustomerid());
        invoice.setEmail(customer.getEmail());
        invoice.setAmountDue(amountDue);
        invoice.setDueDate(dueDate);
        invoice.setStatus("NP");

        // Save the invoice
        invoiceRepository.save(invoice);
    }

    private boolean isPaymentBeforeDueDate(Date dueDate) {
        // Implement logic to check if the current date is before the due date
        Date currentDate = new Date();
        return currentDate.before(dueDate);
    }
    
    public List<Invoice> getAllInvoice() {
        return this.invoiceRepository.findAll();
    }
    
 // Method to find invoices by customerId and status
    public List<Invoice> findInvoicesByCustomerIdAndStatus(String customerId, String status) {
    	
    	System.out.println("Service method called with customerId=" + customerId + " and status=" + status);
        // Use Spring Data JPA query methods to construct the query

        // Example: Find invoices by customerId and status
        return invoiceRepository.findByCustomerIdAndStatus(customerId, status);
    }
    
    public Invoice updateStatus(Long invoiceId, String newStatus) {
        // Retrieve the invoice by ID
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);

        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();

            // Update the status
            invoice.setStatus(newStatus);

            // Save the updated invoice
            return invoiceRepository.save(invoice);
        } else {
            throw new EntityNotFoundException("Invoice not found with ID: " + invoiceId);
        }
    }
}

