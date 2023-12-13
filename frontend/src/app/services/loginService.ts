import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { PasswordEncrypterService } from './passwordEncrypterService';
import { Observable } from 'rxjs';


@Injectable({
    providedIn: 'root'
})
export class LoginService{

    constructor(private http: HttpClient, private passwordEncrypterService:PasswordEncrypterService) {}

    public login(username:string, password:string):Observable<any> {
        return this.http.post(
            environment.serverIP + "login",
            {
                "username": username,
                "password": this.passwordEncrypterService.encrypt(password)
            }
        )
    }

    public getToken(){
        return localStorage.getItem(environment.TOKEN_NAME);
    }

}