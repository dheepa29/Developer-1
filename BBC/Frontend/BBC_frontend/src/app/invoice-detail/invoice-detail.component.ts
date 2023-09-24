import { HttpClient } from '@angular/common/http';
import { Component, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

// import * as jsPDF from 'jspdf';
import { jsPDF } from 'jspdf';







@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html',
  styleUrls: ['./invoice-detail.component.css']
})
export class InvoiceDetailComponent {
  // http: any;

  // private apiUrl = 'http://localhost:8080/invoice/${invoiceId}/status';
  private apiUrl = 'http://localhost:8080/invoice';
  showMarkAsPaidButton: boolean = false;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any , public dialogRef: MatDialogRef<InvoiceDetailComponent> , private http: HttpClient) {}

  showModal = false; // Boolean to control whether the modal is displayed
  ngOnInit() {
    console.log('InvoiceDetailComponent initialized with data:', this.data);

    // Check the status of the invoice
    if (this.data && this.data.invoiceData && this.data.invoiceData.status === 'P') {
      // Invoice is paid, display "Get Receipt" button
      this.showMarkAsPaidButton = false;
    } else {
      // Invoice is not paid, display "Mark as Paid" button
      this.showMarkAsPaidButton = true;
    }
  }
  

  // Method to open the modal
  openModal() {
    this.showModal = true;
  }

  // Method to close the modal
  closeModal(): void {
    this.dialogRef.close();
  }

  markAsPaid() {
    // Update the status in the database by sending an HTTP PUT request
    const invoiceId = this.data.invoiceData.id; // Replace with your invoice identifier
    const newStatus = 'P';
    
    
    this.http.put( `${this.apiUrl}/${invoiceId}/status`, { status: newStatus })
      .subscribe(
        (response) => {
          // Handle success, you can update the UI as needed
          this.data.invoiceData.status = newStatus;
          this.dialogRef.close();
        },
        (error) => {
          // Handle error
          console.error('Error updating status:', error);
        }
      );
  }

  generatePDF() {
    const doc = new jsPDF();

    // Add Payment Date and Payment Mode
  const today = new Date();
  const paymentDate = today.toLocaleDateString();
  const paymentMode = "Manual";

    
    // Add content to the PDF
  doc.text('Invoice Details:', 10, 10);
  doc.text(`Name: ${this.data.invoiceData.name}`, 10, 20);
  doc.text(`Customer ID: ${this.data.invoiceData.customerId}`, 10, 30);
  doc.text(`Email: ${this.data.invoiceData.email}`, 10, 40);
  doc.text(`Amount Due: ${this.data.invoiceData.amountDue}`, 10, 50);
  doc.text(`Due Date: ${this.data.invoiceData.dueDate}`, 10, 60);
  doc.text(`Status: ${this.data.invoiceData.status}`, 10, 70);


  doc.text(`Payment Date: ${paymentDate}`, 10, 80); // Adjusted the y-coordinate
  doc.text(`Payment Mode: ${paymentMode}`, 10, 90); // Adjusted the y-coordinate


    // Save the PDF or open it in a new tab
    doc.save('invoice_receipt.pdf');
  }
  
}
