import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubscriptionMod} from "../../model/subscriptionMod";
import {Category} from "../../model/category";
import {Company} from "../../model/company";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class SubscriptionsService{

  constructor(private http:HttpClient){}


  addCategory(categ:Category):Observable<any>{
    let param = JSON.stringify(categ);
    return this.http.post('api/subscriptions/category', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  addSubscription(subsc:SubscriptionMod):Observable<any>{
    let param = JSON.stringify(subsc);
    return this.http.post('api/subscriptions', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  addCompany(comp:Company):Observable<any>{
    let param = JSON.stringify(comp);
    return this.http.post('api/company', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }



  getCategories():Observable<any>{
    return this.http.get('api/category');
  }

  getCompanies():Observable<any>{
    return this.http.get('api/company');
  }

  getUserSubscriptions():Observable<any>{
    return this.http.get('api/subscriptions/user', {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  getSubscrByCategory(id:number):Observable<any>{
    return this.http.get('api/subscriptions/category?id=' + id);
  }

  getSubscrByCompany(id:number):Observable<any>{
    return this.http.get('api/subscriptions/company?id=' + id);
  }

  getSubscrByPartOfName(name:string):Observable<any>{
    return this.http.get('api/subscriptions?name=' + name);
  }

  deleteSubscriptionGlobally(id:number):Observable<any>{
    return this.http.delete('api/subscriptions?id='+ id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  deleteSubscriptions(id:number):Observable<any>{
    return this.http.delete('api/subscriptions/user?id='+ id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  subscribe(id:number, wallet:number):Observable<any>{
    let params:number[] = [id, wallet];
    let param = JSON.stringify(params);

    return this.http.post('api/subscriptions/user', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

}
