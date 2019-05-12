import {Component, OnInit} from '@angular/core';
import {Category} from "../../model/category";
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {SubscriptionMod} from "../../model/subscriptionMod";
import {Company} from "../../model/company";

@Component({
  selector: 'app-add-subscription',
  templateUrl: './add-subscription.component.html',
  styleUrls: ['./add-subscription.component.css']
})
export class AddSubscriptionComponent implements OnInit {

  subscription:SubscriptionMod;
  category:Category;
  company:Company;
  allCategories:Category[];
  companies:Company[];

  constructor(private service:SubscriptionsService) { }

  ngOnInit() {
    this.category = new Category();
    this.subscription = new SubscriptionMod();
    this.subscription.category = new Category();
    this.subscription.company = new Company();
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
    this.service.addSubscription(this.subscription).subscribe(data=>{

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
