import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../model/user";


@Injectable({
  providedIn: 'root'
})
// Data service
export class BillingAccountService { //todo create interface

  constructor(private http: HttpClient) {
  }

  // Ajax request for billing account data
  getBillingAccounts(): Observable<User[]> {
    return this.http.get<User[]>('/api/ba');
  }

  saveBillingAccount(billingAccount: User): Observable<User> {
    return this.http.post<User>('/api/ba', billingAccount);
  }

  deleteBillingAccount(billingAccountId: string): Observable<void> {
    return this.http.delete<void>('/api/ba/' + billingAccountId);
  }

}
