import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {UserRegistrationService} from "../../service/registration/user-registration.service";
import {Router} from "@angular/router";

@Component({
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.css'],
})
export class SignUpPageComponent implements OnInit {

  user:User;

  constructor(private _userService:UserRegistrationService, private router:Router) {
    this.user = null;
  }

  ngOnInit() {
    this.user = this._userService.getUser();
  }

  onSubmit(){
    this._userService.addUser(this.user);
    this.router.navigate( ['main'] );
  }

}
