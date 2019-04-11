import {Component, OnInit} from '@angular/core';
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

  user: LogUser;


  constructor(private service:LogUserService, private router: Router, private token: TokenStorage) { }

  ngOnInit() {
    this.user = new LogUser();
  }


  autorUser(){
    this.service.getToken(this.user).subscribe(
      data=>{
        this.token.saveToken(data);
        this.router.navigate(['user']);
      }
    );
  }


}
