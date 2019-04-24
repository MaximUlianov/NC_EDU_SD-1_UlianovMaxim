import {Wallet} from "./wallet";
import {SubscriptionMod} from "./subscriptionMod";

export class UserAccount{

  id:number;
  first_name:string;
  last_name:string;
  username:string;
  country:string;
  birthday:Date;
  wallets:Wallet[];
  subscriptions:SubscriptionMod[];

  constructor(){

  }
}
