import {Component, OnInit} from '@angular/core';
import {Category} from "../../model/category";
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {Company} from "../../model/company";
import {Product} from "../../model/product";

@Component({
  selector: 'app-add-subscription',
  templateUrl: './add-subscription.component.html',
  styleUrls: ['./add-subscription.component.css']
})
export class AddSubscriptionComponent implements OnInit {

  product:Product;
  category:Category;
  company:Company;
  allCategories:Category[];
  companies:Company[];

  constructor(private service:SubscriptionsService) { }

  ngOnInit() {
    this.category = new Category();
    this.product = new Product();
    this.product.category = new Category();
    this.product.company = new Company();
    this.company = new Company();
    this.loadCategories();
    this.loadCompanies();
  }

  loadCategories(){
    this.service.getCategories().subscribe(data=>{
      this.allCategories = data as Category[];
    });
  }

  loadCompanies(){
    this.service.getCompanies().subscribe(data=>{
      this.companies = data as Company[];
    });
  }

  addSubscr(){
    this.service.addProduct(this.product).subscribe(data=>{

    });
    window.location.reload();
  }

  addCateg(){
    this.service.addCategory(this.category).subscribe(data=>{

    });
    window.location.reload();
  }

  addCompany(){
    this.service.addCompany(this.company).subscribe(data=>{

    });
    window.location.reload();
  }

}
