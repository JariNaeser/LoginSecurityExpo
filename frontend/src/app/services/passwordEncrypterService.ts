import { Injectable } from '@angular/core';
import * as cryptoJS from 'crypto-js';

@Injectable({
    providedIn: 'root'
})

export class PasswordEncrypterService {

    constructor() { }

    public encrypt(password:string){
        return cryptoJS.SHA256(password).toString();
    }
}