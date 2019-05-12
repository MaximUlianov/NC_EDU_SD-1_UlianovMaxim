import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Wallet} from "../../model/wallet";
import {HttpClient} from "@angular/common/http";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class WalletService{

  constructor(private http:HttpClient){}

  getWallets():Observable<any>{
    return this.http.get('api/wallets', {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  deleteWallet(id:number):Observable<any>{
    return this.http.delete('api/wallets?id=' + id, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  addWallet(wallet:Wallet):Observable<any>{
    let param = JSON.stringify(wallet);
    return this.http.post('api/wallets', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});
  }

  rechargeWallet(wallet:Wallet):Observable<any>{
    let param = JSON.stringify(wallet);
    return this.http.post('api/wallets/recharge', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});

  }

  setCashSub(wallet:Wallet){
    let param = JSON.stringify(wallet);
    return this.http.post('api/wallets/set_sub', param, {headers:{'Authorization':localStorage.getItem(TOKEN_KEY), 'Content-Type':'application/json'}});

  }



}
