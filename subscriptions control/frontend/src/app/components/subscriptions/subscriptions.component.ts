import {Component, OnInit} from '@angular/core';
import {SubscriptionModel} from "../../model/subscription.model";
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

  subscriptions:SubscriptionModel[];
  isEmpty:boolean = true;
  private subscription: Subscription[] = [];


  constructor(private service:SubscriptionsService,
              private spinner:Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.loadSubscriptions();
  }

  loadSubscriptions(){
    this.subscription.push(this.service.getSubscriptions().subscribe(subscr=>{
      this.spinner.show();
      this.subscriptions = subscr as SubscriptionModel[];
      if(this.subscriptions != null) {
        this.isEmpty = false;
      }
      this.spinner.hide();
    }))
  }

  deleteSubscription(subscriptionName:string){
    this.service.deleteSubscriptions(subscriptionName);
  }

}
