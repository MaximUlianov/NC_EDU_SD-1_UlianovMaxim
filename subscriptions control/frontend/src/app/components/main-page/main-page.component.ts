import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  correctLogin: boolean = true;
  correctPassword: boolean = true;
  emailPattern = /[0-9a-z_]+@[0-9a-z]+\.[a-z]{2,5}$/i;
  email:string = "you@example.com";
  password:string = "0000000000";

  constructor() {
    setInterval(()=>{
      if(this.emailPattern.test(this.email)){
        console.log("norm");
        this.correctLogin = true;
      }
      else{
        this.correctLogin = false;
      }
      if(this.password.length <= 20 && this.password.length > 5){
        this.correctPassword = true;
      }
      else{
        this.correctPassword = false;
      }
    }, 20);
  }

  modalCloseEvent(){
    this.email = "example@gmail.com";
    this.password = "0000000000";
  }

  ngOnInit() {
  }

}
