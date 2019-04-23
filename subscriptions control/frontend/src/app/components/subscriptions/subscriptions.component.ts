import {Component, OnInit} from '@angular/core';
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

  isEmpty:boolean = true;


  constructor(private service:SubscriptionsService,
              private spinner:Ng4LoadingSpinnerService) { }

  ngOnInit() {
      //this.loadSubscriptions();
  }

 /* loadSubscriptions(){
    this.service.getSubscriptions().subscribe(subscr=>{
      this.spinner.show();
      this.subscriptions = subscr as SubscriptionMod[];
      if(this.subscriptions != null) {
        this.isEmpty = false;
      }
      this.spinner.hide();
    })
  }
*/
  deleteSubscription(subscriptionName:string){
    this.service.deleteSubscriptions(subscriptionName);
  }

}
