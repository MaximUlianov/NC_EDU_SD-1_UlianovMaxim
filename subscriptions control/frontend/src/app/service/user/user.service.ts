import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class UserService {

  constructor(private http:HttpClient){}

  getAllUsers():Observable<any>{
    return this.http.get('api/user/admin', {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  getUserSubscriptionsByAdmin(id:number):Observable<any>{
    return this.http.get('api/user/subscriptions?id=' + id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  searchUser(type:string, parameter:string):Observable<any>{
    return this.http.get('api/user/search?type=' + type + '&value=' + parameter, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}})
  }

  blockSubscription(userId:number, subscriptionId:number):Observable<any>{
    let arr:number[] = [userId, subscriptionId];
    let param = JSON.stringify(arr);
    return this.http.post('api/user/subscriptions/block', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  unblockSubscription(userId:number, subscriptionId:number):Observable<any>{
    let arr:number[] = [userId, subscriptionId];
    let param = JSON.stringify(arr);
    return this.http.post('api/user/subscriptions/unblock', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }


}
