import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit(): void {
    localStorage.removeItem(environment.TOKEN_NAME);
    this.router.navigate(['/']);
  }

}
