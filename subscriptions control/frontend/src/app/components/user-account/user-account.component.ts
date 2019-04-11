import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";

@Component({
  selector : 'app-user',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

  user: User;

  constructor() { }

  ngOnInit() {

  }



}
