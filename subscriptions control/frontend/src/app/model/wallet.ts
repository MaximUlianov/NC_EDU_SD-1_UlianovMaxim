import {SubscriptionMod} from "./subscriptionMod";

export class Wallet{

  public id:number;
  public walletName:string;
  public sum:number;
  public limit:number;
  public subscriptions:SubscriptionMod[];
  public cashSub:boolean;
  public negBalance:boolean;
  public showBalance:boolean;


  constructor() {
  }

}
