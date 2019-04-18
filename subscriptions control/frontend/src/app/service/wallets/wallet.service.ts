import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Wallet} from "../../model/wallet";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class WalletService{

  constructor(private http:HttpClient){}

  getWallets():Observable<Wallet[]>{
    return this.http.get<Wallet[]>('api/wallet');
  }

  deleteWallet(walletName:string):Observable<void>{
    return this.http.delete<void>('api/wallet' + walletName);
  }

}
