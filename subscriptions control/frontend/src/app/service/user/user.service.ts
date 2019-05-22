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

  getUserWalletsByAdmin(id:number):Observable<any>{
    return this.http.get('api/user/wallets?id=' + id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
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

  getUserHistory(id:number):Observable<any>{
    return this.http.get('api/user/audit?id=' + id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}})
  }

  searchHistoryByDates(id:number, from:Date, to:Date):Observable<any>{
    let fy = new Date(from).getFullYear();
    let fm = new Date(from).getMonth() + 1;
    let fd = new Date(from).getDate();
    let ty = new Date(to).getFullYear();
    let tm = new Date(to).getMonth() + 1;
    let td = new Date(to).getDate();
    return this.http.get('api/user/audit/search?id=' + id + '&from=' + fy + "_" + fm + "_" + fd
      + "&to=" + ty + "_" + tm + "_" + td, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}})
  }

}
