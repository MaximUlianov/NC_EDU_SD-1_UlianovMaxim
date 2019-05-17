import {Component, OnInit} from '@angular/core';
import {Wallet} from "../../model/wallet";
import {WalletService} from "../../service/wallets/wallet.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-wallets',
  templateUrl: './wallets.component.html',
  styleUrls: ['./wallets.component.css']
})
export class WalletsComponent implements OnInit {

  wallets:Wallet[];
  wallet:Wallet;
  walletBalance:Wallet;
  isEmpty:boolean = true;
  recharge:number;
  isLimit:boolean = false;
  balanceClicked:boolean;

  private subscriptions: Subscription[] = [];


  constructor(private service:WalletService,
              private loadingService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.walletBalance = new Wallet();
    this.wallet = new Wallet();
    this.loadWallets();
  }

  loadWallets(){
    this.subscriptions.push( this.service.getWallets().subscribe(wallets=>{
      this.wallets = wallets as Wallet[];
      if(this.wallets.length > 0){
        this.isEmpty = false;
      }
      else if(this.wallets.length == 0){
        this.isEmpty = true;
      }
    }))

  }

  addWallet(){
    this.service.addWallet(this.wallet).subscribe(data=>{

    });
    this.loadWallets();
    window.location.reload();
  }

  deleteWallet(id:number){
      this.service.deleteWallet(id).subscribe(data=>{

      });
      this.loadWallets();
      window.location.reload();
  }

  rechargeWallet(wallet:Wallet, sum:number){
    wallet.sum = sum;
    this.service.rechargeWallet(wallet).subscribe(data=>{

    });
    this.loadWallets();
    window.location.reload();
  }

  setCashSub(wallet:Wallet){
    this.service.setCashSub(wallet).subscribe(data=>{

    });
    window.location.reload();
  }

  setLimit(){
    this.isLimit = !this.isLimit;
  }

  showBalance(id:number){
    this.service.getBalance(id).subscribe(data=>{
      this.walletBalance = data as Wallet;
    });
    this.balanceClicked = true;
  }

  showBalanceBtn(){
    this.balanceClicked = false;
    this.walletBalance = new Wallet();
  }

}
