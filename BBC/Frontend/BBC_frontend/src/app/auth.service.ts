import { Injectable } from '@angular/core';
import { UserserviceService } from './userservice.service';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements CanActivate {

  // constructor() { }

  constructor(private userService: UserserviceService, private router: Router) {}

  canActivate(): boolean {
    if (this.userService.auth) {
      return true; // User is authenticated, allow access to HomeComponent
    } else {
      this.router.navigate(['/login']); // Redirect to login page if not authenticated
      return false; // User is not authenticated, deny access to HomeComponent
    }
  }
}
