import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class SubscriptionsService{

  constructor(private http:HttpClient){}


  getCategories():Observable<any>{
    return this.http.get('api/category');
  }

  getUserSubscriptions():Observable<any>{
    return this.http.get('api/subscriptions/user', {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  getSubscriptions():Observable<any>{
    return this.http.get('api/subscriptions');
  }

  deleteSubscriptions(id:number):Observable<any>{
    return this.http.delete('api/subscriptions/user/'+ id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  subscribe(id:number):Observable<any>{
    let param = JSON.stringify(id);
    return this.http.post('api/subscriptions', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

}
