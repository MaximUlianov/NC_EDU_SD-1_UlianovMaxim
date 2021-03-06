import {Component, OnInit} from '@angular/core';
import {Wallet} from "../../model/wallet";
import {WalletService} from "../../service/wallets/wallet.service";
import {Subscription} from "rxjs";
import {WOW} from "wowjs/dist/wow.min";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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
  isGoodRechargeSum:boolean = true;
  recharge:number;
  isLimit:boolean = false;
  form:FormGroup;

  private subscriptions: Subscription[] = [];


  constructor(private service:WalletService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      name: [null, Validators.required],
      sum: [null, Validators.required],
    });
    new WOW().init();
    this.walletBalance = new Wallet();
    this.wallet = new Wallet();
    this.loadWallets();
  }

  loadWallets(){
    this.service.getWallets().subscribe(wallets=>{
      this.wallets = wallets as Wallet[];
      if(this.wallets.length > 0){
        this.isEmpty = false;
      }
      else if(this.wallets.length == 0){
        this.isEmpty = true;
      }
    });
  }

  addWallet(){
    this.wallet.walletName = this.form.get('name').value;
    this.wallet.sum = this.form.get('sum').value;
    if(this.wallet.sum > 0) {
      this.service.addWallet(this.wallet).subscribe(data => {
        this.loadWallets();
      });
    }
    else{
      this.isGoodRechargeSum = false;
      setTimeout(()=>{this.isGoodRechargeSum = true;},3000);
    }
  }

  deleteWallet(id:number){
      this.service.deleteWallet(id).subscribe(data=>{
        this.loadWallets();
      });
  }

  rechargeWallet(wallet:Wallet){
    if(this.recharge > 0) {
      wallet.sum = this.recharge;
      this.isGoodRechargeSum = true;
      this.service.rechargeWallet(wallet).subscribe(data => {
        this.loadWallets();
        this.recharge = null;

      });
    }else{
      this.isGoodRechargeSum = false;
      setTimeout(()=>{this.isGoodRechargeSum = true;},5000);
    }
  }

  showBalance(id:number){
    this.service.getBalance(id).subscribe(data=>{
      this.walletBalance = data as Wallet;
      this.wallets.find(x => x.id == id).sum = this.walletBalance.sum;
      this.wallets.find(x => x.id == id).showBalance = true;
    });
  }

  showBalanceBtn(id:number){
    this.wallets.find(x => x.id == id).showBalance = false;
    this.walletBalance = new Wallet();
  }

}
