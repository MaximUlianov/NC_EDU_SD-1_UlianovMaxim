import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LogUser} from "../../model/logUser";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class LogUserService{
  private _user:LogUser = new LogUser();
  private token:string;


  constructor(private http:HttpClient, private router:Router){}

  getToken(user:LogUser){
    let param = JSON.stringify(user);
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post('/api/token/generate-token', param, {headers});
  }

  getUserInfo():Observable<any>{
    return this.http.get('api/user/email', {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }


}
