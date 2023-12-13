import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    constructor(private http: HttpClient) {}

    public getUserData(id:number):Observable<any> {
        return this.http.get(
            environment.serverIP + "user/" + id
        );
    }

    public getAllUsersData():Observable<any> {
        return this.http.get(
            environment.serverIP + "user/all"
        );
    }

}