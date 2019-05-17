import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LogUser} from "../../model/logUser";
import {LogUserService} from "../../service/registration/log-user.service";
import {Router} from "@angular/router";
import {TokenStorage} from "../../authorization-config/token-provider";
import {User} from "../../model/user";

const USER_KEY = "USER";

@Component({
  selector:'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  @Output() isAuthorized = new EventEmitter<boolean>();
  user: LogUser;
  userInfo:User;
  constructor(private service:LogUserService, private tokens: TokenStorage, private router:Router) { }

  ngOnInit() {
    this.user = new LogUser();
  }

  authorizeUser(){
    this.service.getToken(this.user).subscribe(
      data=>{
        this.tokens.saveToken(data);
        this.service.getUserInfo().subscribe(data=>{
          this.userInfo = data as User;
          localStorage.setItem(USER_KEY, JSON.stringify(this.userInfo));
          this.isAuthorized.emit(true);
        });
      }
    );
  }
}
