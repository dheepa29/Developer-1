import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CustomerFormComponent } from '../customer-form/customer-form.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent  {
  customers: any[] = []; // Your customer data goes here
  filteredCustomers: any[] = [];
  searchText: string = '';
  displayedColumns: string[] = ['id', 'name']; // Define the columns you want to display

  constructor(private httpClient: HttpClient , public dialog: MatDialog) { }

  ngOnInit(): void {
    // Initialize your customers array with data (e.g., from an API)
    this.fetchCustomers();
      // Add more customer data as needed
    

    // Initially, show all customers
    this.filteredCustomers = [...this.customers];
  }

  fetchCustomers() {
    this.httpClient.get<any[]>('http://localhost:8080/customer')
      .subscribe(
        (response) => {
          this.customers = response; // Assign fetched data to the customers array
          this.filteredCustomers = [...this.customers]; // Initially, show all customers
        },
        (error) => {
          console.error('Error fetching customer data:', error);
        }
      );
  }

  filterCustomers() {
    // Filter customers based on the search text
    this.filteredCustomers = this.customers.filter(customer =>
      customer.customerid.includes(this.searchText)
    );
  }

  addCustomer() {
    // Open the customer form dialog
    const dialogRef = this.dialog.open(CustomerFormComponent, {
      width: '500px', // Adjust the width as needed
      height:'400px',
      // position: { top: '10%' },
      data: {
        customer: {} // Pass any initial data if required
      }
    });

    // Listen for the dialog's close event
    dialogRef.afterClosed().subscribe(result => {
      // Handle the result if needed (e.g., refresh the customer list)
    });
  }
}
