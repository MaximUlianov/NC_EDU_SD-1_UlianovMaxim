import {Wallet} from "./wallet";
import {Product} from "./product";

export class SubscriptionMod {

  public id:number;
  public start:Date;
  public end:Date;
  public locked:boolean;
  public wallet:Wallet;
  public product:Product;

  constructor() {
    this.wallet = new Wallet();
    this.product = new Product();
  }
}
