import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css']
})
export class CustomerFormComponent {

  customerForm: FormGroup = new FormGroup({});
  customer: any = {
    customerid: '',
    name: '',
    unitconsumption: 0,
    billduedate: null,
    email: '',
    telephone: ''
  };

  constructor(
    public dialogRef: MatDialogRef<CustomerFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private formBuilder: FormBuilder,private http: HttpClient
  ) {
    // Initialize the customer form with validation rules
    this.customerForm = this.formBuilder.group({
      customerid: ['', Validators.required],
      name: ['', Validators.required],
      unitconsumption: [0, Validators.required],
      billduedate: [null, Validators.required],
      email: ['', Validators.required],
      telephone: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.customerForm.valid) {
      // Get the customer data from the form
      const customerData = this.customerForm.value;
      console.log(customerData);

      // Create a new customer using the API
      this.createCustomer(customerData);
    } else {
      // Handle form validation errors here
    }
  }

  createCustomer(customerData: any) {
    // Define the API endpoint URL
    const apiUrl = 'http://localhost:8080/create';

    // Define headers (optional)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    // Send a POST request to create the customer
    this.http.post(apiUrl, customerData, { headers }).subscribe(
      (response) => {
        // Customer creation successful, close the modal
        this.dialogRef.close();
      },
      (error) => {
        // Handle error here (e.g., display an error message)
        console.error('Error creating customer:', error);
      }
    );
  }

  closeDialog() {
    // Close the dialog without saving
    this.dialogRef.close();
  }

}


