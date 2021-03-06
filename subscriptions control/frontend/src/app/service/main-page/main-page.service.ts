import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class MainPageService{
  private token:string;

  constructor(private http:HttpClient) {

  }

  getCompanies():Observable<any>{
    return this.http.get('api/company/admin/1/3');
  }

  getUsername():Observable<any>{
    return this.http.get('api/user/username',{headers:{'Authorization':localStorage.getItem(TOKEN_KEY)}});
  }

}
