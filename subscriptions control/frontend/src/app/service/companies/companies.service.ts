import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Company} from "../../model/company";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class CompaniesService {

  constructor(private http:HttpClient) {
  }

  deleteCompany(id:number):Observable<any>{
    return this.http.delete('api/company?id='+ id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  editCompany(company:Company):Observable<any>{
    let params = JSON.stringify(company);
    return this.http.put('api/company', params, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }
}
