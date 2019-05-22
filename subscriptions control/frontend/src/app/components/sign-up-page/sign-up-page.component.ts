import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {UserRegistrationService} from "../../service/registration/user-registration.service";
import {Router} from "@angular/router";
import {moment} from "ngx-bootstrap/chronos/test/chain";

@Component({
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.css'],
})
export class SignUpPageComponent implements OnInit {

  termsAccepted:boolean;
  isUsernameExists:boolean;
  isEmailExists:boolean;
  isMatch:boolean;
  isMatchAfterClicked:boolean;
  isSuccess:boolean;

  confPassword:string;
  maxBirthday:string;

  servMessage:any;
  user:User;

  constructor(private _userService:UserRegistrationService, private router:Router) {
    this.user = null;
  }

  ngOnInit() {
    this.maxBirthday = moment(new Date()).format('YYYY-MM-DD');
    this.user = new User();
    this.isMatchAfterClicked = true;
    this.user = this._userService.getUser();
  }

  onSubmit() {
    if (this.termsAccepted) {
      if (this.user.password == this.confPassword) {
        this.isMatchAfterClicked = true;
        this._userService.addUser(this.user).subscribe(data => {
          this.servMessage = data;
          if (this.servMessage.response == 'username exists') {
            this.isSuccess = false;
            this.isUsernameExists = true;
            this.termsAccepted = false;
          } else if (this.servMessage.response == 'email exists') {
            this.isSuccess = false;
            this.isEmailExists = true;
            this.termsAccepted = false;
          } else {
            this.isSuccess = true;
            this.isUsernameExists = false;
            this.isEmailExists = false;
          }

        });
      }
      else{
        this.isSuccess = false;
        this.isMatchAfterClicked = false;
        this.termsAccepted = false;
      }
    }
    else {
      alert('You should accept terms');
    }
  }

  checkPasswordFieldsMatching(){
    if(this.user.password == this.confPassword){
      this.isMatch = true;
    }
    else{
      this.isMatch = false;
    }
  }

  redirect(){
    this.router.navigate(['main']);
  }
}
