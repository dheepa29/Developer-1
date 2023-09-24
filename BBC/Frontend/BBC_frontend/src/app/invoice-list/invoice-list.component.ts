import { Component } from '@angular/core';
import { InvoiceService } from '../invoice.service';
import { ModalService } from '../modal.service';
import { MatDialog } from '@angular/material/dialog';
import { InvoiceDetailComponent } from '../invoice-detail/invoice-detail.component';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css']
})
export class InvoiceListComponent {

  invoices: any[] = [];
  customerId: string = '';
  status: string = '';

  constructor(private invoiceService: InvoiceService , public modalService: ModalService , public dialog: MatDialog) {}

  search(): void {
    this.invoiceService.getInvoices(this.customerId, this.status).subscribe(
      (data) => {
        this.invoices = data;
      },
      (error) => {
        console.error('Error fetching invoices:', error);
      }
    );
  }

  selectedInvoice: any; // Property to store the selected invoice
showModal = false; // Property to control whether the modal is displayed

// Method to open the modal with the selected invoice
openModal(invoice: any) {
  const dialogRef = this.dialog.open(InvoiceDetailComponent, {
    width: '500px', // Adjust the width as needed
    data: { invoiceData: invoice } ,
     position: { top: '10%' }
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
  });




}
}
