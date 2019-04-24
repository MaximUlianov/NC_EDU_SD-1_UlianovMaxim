import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user/user.service";
import {UserAccount} from "../../model/user.account";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  users:UserAccount[];
  isEmpty:boolean;

  constructor(private service:UserService) { }

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers(){
    this.service.getAllUsers().subscribe(data=>{
      this.users = data as UserAccount[];
      if(this.users.length == 0){
        this.isEmpty = true;
      }
      else if(this.users.length > 0){
        this.isEmpty = false;
      }
    });
  }

}
