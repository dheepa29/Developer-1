import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class InvoiceService {
  private apiUrl = 'http://localhost:8080/invoice/filter'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  getInvoices(customerId: string, status: string): Observable<any> {
    let params = new HttpParams();
    if (customerId) {
      params = params.set('customerId', customerId);
    }
    if (status) {
      params = params.set('status', status);
    }
    const apiUrlWithParams = `${this.apiUrl}?${params.toString()}`;
    console.log('API URL with Params:', apiUrlWithParams);
  
    return this.http.get(apiUrlWithParams);
  }
  
}
