import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {UserRegistrationService} from "../../service/registration/user-registration.service";
import {Router} from "@angular/router";
import {moment} from "ngx-bootstrap/chronos/test/chain";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.css'],
})
export class SignUpPageComponent implements OnInit {

  isUsernameExists:boolean;
  isEmailExists:boolean;
  isSuccess:boolean;

  maxBirthday:string;

  servMessage:any;
  user:User;

  form:FormGroup;

  constructor(private _userService:UserRegistrationService, private router:Router, private formBuilder: FormBuilder) {
    this.user = null;
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      firstName: [null, [Validators.required, Validators.minLength(4)]],
      lastName: [null, Validators.required],
      username: [null, Validators.required],
      birthday: [null, Validators.required],
      country: [null, Validators.required],
      role: [null, Validators.required],
      email: [null, [Validators.required,
        Validators.pattern(/^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/)]],
      password: [null, [Validators.required, Validators.minLength(8)]],
      confirmPassword: [null, Validators.required],
      terms: [null, [Validators.required, Validators.requiredTrue]],
    });
    this.maxBirthday = moment(new Date()).format('YYYY-MM-DD');
    this.user = new User();
    this.user = this._userService.getUser();
  }

  sendUser(){
    this._userService.addUser(this.user).subscribe(data => {
      this.servMessage = data;
      if (this.servMessage.response == 'username exists') {
        this.isSuccess = false;
        this.isUsernameExists = true;
        setTimeout(()=>{
          this.isUsernameExists = false;
        }, 4000);
      } else if (this.servMessage.response == 'email exists') {
        this.isSuccess = false;
        this.isEmailExists = true;
        setTimeout(()=>{
          this.isEmailExists = false;
        }, 4000);
      } else{
        this.isSuccess = true;
        this.isUsernameExists = false;
        this.isEmailExists = false;
      }
    });
  }

  onSubmit(){
    this.user.first_name = this.form.get('firstName').value;
    this.user.last_name = this.form.get('lastName').value;
    this.user.username = this.form.get('username').value;
    this.user.birthday = this.form.get('birthday').value;
    this.user.country = this.form.get('country').value;
    this.user.role = this.form.get('role').value;
    this.user.email = this.form.get('email').value;
    this.user.password = this.form.get('password').value;
    this.sendUser()
  }

  redirect(){
    this.router.navigate(['main']);
  }

  isFieldValid(field: string) {
    return this.form.get(field).touched && !this.form.get(field).valid;
  }

  isPasswordsEqual(){
    return this.form.get('confirmPassword').value != this.form.get('password').value;
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }
}
