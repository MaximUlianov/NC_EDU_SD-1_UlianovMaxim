import {Component, OnInit} from '@angular/core';
import {MainPageService} from "../../service/main-page/main-page.service";
import {Router} from "@angular/router";
import {TokenStorage} from "../../authorization-config/token-provider";

const USER_KEY = "USER";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username:any;
  isAuthorized:boolean;
  isRed:boolean;

  constructor(private service:MainPageService, private router:Router, private token:TokenStorage) { }

  ngOnInit() {
    this.getUsername();
  }

  onChange(state:any){
      this.getUsername();
  }

  getUsername(){
      this.username = JSON.parse(localStorage.getItem(USER_KEY));
      if(this.username != null) {
        this.isAuthorized = true;
      }
  }

  logOut(){
    this.token.signOut();
    this.isAuthorized = false;
    this.username = null;
  }

}
