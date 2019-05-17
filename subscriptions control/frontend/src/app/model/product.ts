import {Category} from "./category";
import {Company} from "./company";

export class Product {

  public id:number;
  public name:string;
  public description:string;
  public costPerMonth:number;
  public sale:number;
  public category:Category;
  public company:Company;
  public isInUserProd:boolean;

  constructor() {
  }
}
