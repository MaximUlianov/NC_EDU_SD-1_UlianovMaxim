import {Component, OnInit} from '@angular/core';
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {SubscriptionMod} from "../../model/subscriptionMod";

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

  subscriptions:SubscriptionMod[];
  isEmpty:boolean = true;


  constructor(private service:SubscriptionsService,
              private spinner:Ng4LoadingSpinnerService) { }

  ngOnInit() {
      this.loadSubscriptions();
  }

  loadSubscriptions(){
    this.service.getUserSubscriptions().subscribe(subscr=>{
      this.subscriptions = subscr as SubscriptionMod[];
      if(this.subscriptions.length > 0) {
        this.isEmpty = false;
      }
      else if(this.subscriptions.length == 0){
        this.isEmpty = true;
      }
    })
  }

  deleteSubscription(id:number){
    id = this.subscriptions.find(x => x.id == id).product.id;
    this.service.deleteSubscriptions(id).subscribe(data=>{

    });
    this.loadSubscriptions();
    window.location.reload();
  }

}
