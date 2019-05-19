import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubscriptionMod} from "../../model/subscriptionMod";
import {Category} from "../../model/category";
import {Company} from "../../model/company";
import {Product} from "../../model/product";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class SubscriptionsService{

  constructor(private http:HttpClient){}


  addCategory(categ:Category):Observable<any>{
    let param = JSON.stringify(categ);
    return this.http.post('api/category', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  addProduct(prod:Product):Observable<any>{
    let param = JSON.stringify(prod);
    return this.http.post('api/product', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
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

  getProductsByCategory(id:number):Observable<any>{
    return this.http.get('api/category/products?id=' + id);
  }

  getProductsByCompany(id:number):Observable<any>{
    return this.http.get('api/company/products?id=' + id);
  }

  getProductsByPartOfName(value:string):Observable<any>{
    return this.http.get('api/product?value=' + value);
  }

  deleteProduct(id:number):Observable<any>{
    return this.http.delete('api/product?id='+ id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  deleteSubscriptions(id:number):Observable<any>{
    return this.http.delete('api/subscriptions/user?id='+ id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }



  subscribe(subscription:SubscriptionMod):Observable<any>{
    let param = JSON.stringify(subscription);
    return this.http.post('api/subscriptions/user', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  setSale(product:Product){
    let param = JSON.stringify(product);
    return this.http.post('api/product/sale', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

}
