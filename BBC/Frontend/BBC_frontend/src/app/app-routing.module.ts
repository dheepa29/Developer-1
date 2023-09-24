import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';
import { PaymentComponent } from './payment/payment.component';
import { CustomerComponent } from './customer/customer.component';
import { AuthService } from './auth.service';

const routes: Routes = [
  {path: 'login', component:LoginComponent},
  {path: 'customermodule', component:CustomerComponent},
  {path: 'Payment', component:PaymentComponent},
  {path: 'invoicelist', component:InvoiceListComponent},
  {path: 'home', component:HomeComponent , canActivate: [AuthService]},
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
