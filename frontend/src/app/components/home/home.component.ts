import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  userData:any;
  salaryData:any;

  ngOnInit(): void {
    
    this.userData = JSON.parse(
      '{"id": 0, "name": "John", "surname": "Doe", "username": "johndoe", "salaryId": 0}'
    );

    this.salaryData = JSON.parse(
      '{"id": 0, "amount": 1000.0, "start": "SomeDate", "end": "SomeDate", "lastUpdate": "SomeDate"}'
    );

    console.log(this.userData);
    console.log(this.salaryData);

  }

}
