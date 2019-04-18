import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LogUser} from "../../model/logUser";
import {LogUserService} from "../../service/registration/log-user.service";
import {Router} from "@angular/router";
import {TokenStorage} from "../../authorization-config/token-provider";

@Component({
  selector:'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  @Output() isAuthorized = new EventEmitter<boolean>();
  user: LogUser;

  constructor(private service:LogUserService, private tokens: TokenStorage, private router:Router) { }

  ngOnInit() {
    this.user = new LogUser();
  }


  autorUser(){
    this.service.getToken(this.user).subscribe(
      data=>{
        this.tokens.saveToken(data);
        this.isAuthorized.emit(true);
      }
    );
  }






}
