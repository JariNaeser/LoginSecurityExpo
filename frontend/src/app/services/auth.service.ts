import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';

@Injectable()
export class AuthService {
    
  constructor(private jwtHelper: JwtHelperService) {}

  public getToken(): string{
    const token = localStorage.getItem(environment.TOKEN_NAME);
    return token === null ? '' : token;
  }

  public isAuthenticated(): boolean {
    return this.getToken() != '';
  }

  public getUserType(): string{
    return this.isAuthenticated() ? this.jwtHelper.decodeToken(this.getToken()).userType : undefined;
  }

  public getUsername(): string{
    return this.isAuthenticated() ? this.jwtHelper.decodeToken(this.getToken()).sub : undefined;
  }

  public getUserId(): number{
    return this.isAuthenticated() ? this.jwtHelper.decodeToken(this.getToken()).userId : undefined;
  }

  public isExpired(){
    return this.jwtHelper.isTokenExpired(this.getToken());
  }
}