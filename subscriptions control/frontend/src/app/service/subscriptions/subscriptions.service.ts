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

  getSubscriptions():Observable<any>{
    return this.http.get('api/subscriptions');
  }

  deleteSubscriptions(subscriptionName:string):Observable<any>{
    return this.http.delete('api/subscriptions'+ subscriptionName);
  }

  subscribe(id:number):Observable<any>{
    let param = JSON.stringify(id);
    return this.http.post('api/subscriptions', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

}
