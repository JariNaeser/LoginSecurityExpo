import { identifierName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
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
	public userData:Array<any> | undefined;

  	constructor(private userService : UserService) { }

  	ngOnInit(): void {
		this.userService.getUserData(1).subscribe(data => {
			//Success
			this.id = data.id;
			this.name = data.name;
			this.salary = data.salary;
			this.surname = data.surname;
			this.username = data.username;
			this.role = data.role;
		});   
		
		this.userService.getAllUsersData().subscribe(data => {
			this.userData = data;
		});
  	}

}
