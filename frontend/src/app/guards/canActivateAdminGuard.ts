import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { environment } from '../../environments/environment';


@Injectable()
export class CanActivateAdminGuard implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {}
  
  canActivate(): boolean {
    if (!this.auth.isAuthenticated() || this.auth.getUserType() == undefined || this.auth.getUserType() != "ROLE_ADMIN") {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}