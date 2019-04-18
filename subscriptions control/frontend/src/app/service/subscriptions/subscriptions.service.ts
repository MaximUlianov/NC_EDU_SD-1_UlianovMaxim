import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubscriptionModel} from "../../model/subscription.model";

@Injectable()
export class SubscriptionsService{

  constructor(private http:HttpClient){}


  getSubscriptions():Observable<SubscriptionModel[]>{
    return this.http.get<SubscriptionModel[]>('api/subscriptions');
  }

  deleteSubscriptions(subscriptionName:string):Observable<void>{
    return this.http.delete<void>('api/subscriptions'+ subscriptionName);
  }

}
