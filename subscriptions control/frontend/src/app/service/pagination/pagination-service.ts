import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";


const TOKEN_KEY = 'AuthToken';
@Injectable()
export class PaginationService {

  constructor(private http:HttpClient) {

  }

  getTotalPagesSubscr(size:number):Observable<any>{
    return this.http.get('api/product/totalPages?perPage=' + size);
  }

  getTotalPagesUsers(size:number):Observable<any>{
    return this.http.get('api/user/admin/totalPages?perPage=' + size);
  }

  getTotalPagesCompanies(size:number):Observable<any>{
    return this.http.get('api/company/admin/totalPages?perPage=' + size);
  }

  getProducts(page:number, perPage:number):Observable<any>{
    return this.http.get('api/product/' + page + "/" + perPage);
  }

  getUsers(page:number, perPage:number):Observable<any>{
    return this.http.get('api/user/admin/' + page + "/" + perPage, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  getCompanies(page:number, perPage:number):Observable<any>{
    return this.http.get('api/company/admin/' + page + "/" + perPage, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }




}
