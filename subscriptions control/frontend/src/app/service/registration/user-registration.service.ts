import {Injectable} from "@angular/core";
import {User} from "../../model/user";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable()
export class UserRegistrationService{
  private _user:User = new User();
  private _user2:User = new User();

  constructor(private http: HttpClient){}

  getUser(): User {
    return this._user;
  }

  addUser(_user:User):Observable<any>{
    let param = JSON.stringify(_user);
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<User>('/api/user/signUp', param, {headers});
  }
}
