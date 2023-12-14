import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/loginService';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginResponse:string = "";
  public username:string = "";
  public password:string = "";

  constructor(private loginService:LoginService, private router:Router) { }

  ngOnInit(): void {
  }

  login(){
    this.loginService.login(this.username, this.password).subscribe(data => {
      //Set token
      localStorage.setItem(environment.TOKEN_NAME, data.token);
      //Redirect to profile or whatever page
      this.router.navigate(['/details']);
    }, error => {
      switch (error.status){
        case 401:
          this.loginResponse = "Wrong username or password";
          break;
        default:
          this.loginResponse = "Server unreachable, try again later";
          break; 
      }
    });
  }

}
