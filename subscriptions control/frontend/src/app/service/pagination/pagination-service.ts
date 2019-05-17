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

  getProducts(page:number, size:number):Observable<any>{
    return this.http.get('api/product/' + page + "/" + size);
  }

  getUsers(page:number, size:number):Observable<any>{
    return this.http.get('api/user/admin/' + page + "/" + size, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }




}
