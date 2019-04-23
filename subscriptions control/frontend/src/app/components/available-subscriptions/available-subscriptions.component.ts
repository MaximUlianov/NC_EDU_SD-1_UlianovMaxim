import {Component, OnInit} from '@angular/core';
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {SubscriptionMod} from "../../model/subscriptionMod";

@Component({
  selector: 'app-available-subscriptions',
  templateUrl: './available-subscriptions.component.html',
  styleUrls: ['./available-subscriptions.component.css']
})
export class AvailableSubscriptionsComponent implements OnInit {

  categories:any;
  subscriptions:SubscriptionMod[];
  constructor(private service:SubscriptionsService) { }

  ngOnInit() {
    this.loadCategories();
    this.loadAllAvSubscriptions();
  }

  loadCategories(){
    this.service.getCategories().subscribe(data=>{
      this.categories = data;
    })
  }

  loadAllAvSubscriptions(){
    this.service.getSubscriptions().subscribe(data=>{
      this.subscriptions = data as SubscriptionMod[];
      }
    );
  }

  subscribe(id:number){
    this.service.subscribe(id).subscribe(data=>{

    });
  }

}
