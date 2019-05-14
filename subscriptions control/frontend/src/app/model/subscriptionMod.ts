import {Category} from "./category";
import {Company} from "./company";

export class SubscriptionMod {

  public id:number;
  public subscriptionName:string;
  public costPerMonth:number;
  public category:Category;
  public company:Company;
  public locked:boolean;
  public negBalance:boolean;
  public isInUserSubscr:boolean;
  constructor() {
  }
}
