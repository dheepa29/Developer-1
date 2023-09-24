package com.BBC.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.BBC.model.customer;
import com.BBC.service.CustomerRepo;


import CustomerHelper.helper;

@Service
public class CustomerServiceImpl {
	
	@Autowired
    private CustomerRepo customerRepo;
	
	@Autowired
    private InvoiceService invoiceService;

    public void save(MultipartFile file) {

        try {
            List<customer> products = helper.convertExcelToListOfProduct(file.getInputStream());
            this.customerRepo.saveAll(products);
            
            for (customer savedCustomer : products) {
                invoiceService.generateAndSaveInvoice(savedCustomer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<customer> getAllProducts() {
        return this.customerRepo.findAll();
    }
    
    public customer savecustomer(customer cus) {
    
    	invoiceService.generateAndSaveInvoice(cus);
    	return this.customerRepo.save(cus);
    }

}
