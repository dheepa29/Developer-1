import { Component } from '@angular/core';
import { InvoiceService } from '../invoice.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {

  
  invoices: any[] = [];
  customerId: string = '';
  status: string = '';

  constructor(private invoiceService: InvoiceService ) {}

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
}
