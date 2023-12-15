import { identifierName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/userService';

@Component({
	selector: 'app-details',
  	templateUrl: './details.component.html',
  	styleUrls: ['./details.component.css']
})

export class DetailsComponent implements OnInit {

	public id:number = -1;
	public name:string = "NO_NAME";
	public salary:number = -1;
	public surname:string = "NO_SURNAME";
	public username:string = "NO_USERNAME";
	public role:string = "NO_ROLE";
	public usersData:Array<any> | undefined;
	public userDataFetchFail:string = '';
	public usersDataFetchFail:string = '';
	public userIsAdmin:boolean = false;
	public userDataLoadedSuccessfully:boolean = false;
	public usersDataLoadedSuccessfully:boolean = false;

  	constructor(private userService : UserService, private authService : AuthService) { }

  	ngOnInit(): void {

		this.userIsAdmin = this.authService.getUserType() === "ROLE_ADMIN";

		this.userService.getUserData(this.authService.getUserId()).subscribe(data => {
			//Success
			this.userDataLoadedSuccessfully = true;
			this.userDataFetchFail = '';
			this.id = data.id;
			this.name = data.name;
			this.salary = data.salary;
			this.surname = data.surname;
			this.username = data.username;
			this.role = data.role;
		}, error => {
			this.userDataLoadedSuccessfully = false;
			this.userDataFetchFail = 'Failed to fetch data from the server.';
		});   
		
		this.userService.getAllUsersData().subscribe(data => {
			this.usersDataLoadedSuccessfully = true;
			this.usersDataFetchFail = '';
			this.usersData = data;
		}, error => {
			this.usersDataLoadedSuccessfully = false;
			this.usersDataFetchFail = 'Failed to fetch data from the server.';
		});
  	}

}
