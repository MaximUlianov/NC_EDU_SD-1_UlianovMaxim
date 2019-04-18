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
  isEmpty:boolean = true;
  private subscriptions: Subscription[] = [];

  constructor(private service:WalletService,
              private loadingService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.loadWallets();
  }

  loadWallets(){
    this.subscriptions.push( this.service.getWallets().subscribe(wallets=>{
      this.loadingService.show();
      this.wallets = wallets as Wallet[];
      this.loadingService.hide();
      if(this.wallets != null){
        this.isEmpty = false;
      }
    }))

  }

  deleteWallet(name:string){

  }



}
