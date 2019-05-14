import {Component, OnInit} from '@angular/core';
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {SubscriptionMod} from "../../model/subscriptionMod";
import {TokenStorage} from "../../authorization-config/token-provider";
import {Category} from "../../model/category";
import {Wallet} from "../../model/wallet";
import {WalletService} from "../../service/wallets/wallet.service";
import {PaginationService} from "../../service/pagination/pagination-service";
import {Company} from "../../model/company";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";

@Component({
  selector: 'app-available-subscriptions',
  templateUrl: './available-subscriptions.component.html',
  styleUrls: ['./available-subscriptions.component.css']
})
export class AvailableSubscriptionsComponent implements OnInit {
  TOKEN_KEY:string = 'AuthToken';
  categories:Category[];
  wallets:Wallet[];
  companies:Company[];
  subscriptions:SubscriptionMod[];
  userSubscriptions:SubscriptionMod[];
  pagesCount:number;
  subscrPerPage:number = 6;
  pageNumber:number = 1;
  role:number;
  isEmpty:boolean = false;
  showPagination:boolean = true;
  subscriptionId:number;
  walletId:number;
  searchStr:string;

  constructor(private service:SubscriptionsService,
              private tokenUtil:TokenStorage,
              private wService:WalletService,
              private pagination:PaginationService,
              private spinnerService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.loadCategories();
    this.loadCompanies();
    this.checkForUserRole();
    if(this.role == 1){
      this.loadUserSubscriptions();
    }
    this.loadPagesNumber();
    this.loadAllAvSubscriptions();
  }

  checkForUserRole() {
    if(localStorage.getItem(this.TOKEN_KEY) != null) {
      let tokenInfo = this.tokenUtil.getDecodedAccessToken(localStorage.getItem(this.TOKEN_KEY));
      if (tokenInfo.scopes == 'ROLE_ADMIN') {
        this.role = 0;
      } else if (tokenInfo.scopes == 'ROLE_USER') {
        this.role = 1;
      }
    }
    else{
      this.role = 2;
    }
  }

  loadCategories(){
    this.service.getCategories().subscribe(data=>{
      this.categories = data as Category[];
    });
  }

  loadCompanies(){
    this.service.getCompanies().subscribe(data=>{
      this.companies = data as Company[];
    });
  }

  getSubscrByCompany(id:number){
    if(id == 0){
      this.pageNumber = 1;
      this.loadAllAvSubscriptions();
    }
    this.service.getSubscrByCompany(id).subscribe(data=>{
      this.subscriptions = data as SubscriptionMod[];
      if(this.subscriptions.length == 0){
        this.isEmpty = true;
      }
      else{
        if(this.role == 1) {
          for (let i in this.userSubscriptions) {
            for (let y in this.subscriptions) {
              if (this.userSubscriptions[i].id == this.subscriptions[y].id) {
                this.subscriptions[y].isInUserSubscr = true;
              }
            }
          }
        }
        this.isEmpty = false;
      }
    });
    this.showPagination = false;
  }

  loadAllAvSubscriptions(){
    if(this.pageNumber < 0 || this.pageNumber > this.pagesCount){
      this.pageNumber = 1;
    }

    this.pagination.getSubscriptions(this.pageNumber, this.subscrPerPage).subscribe(data=>{
      this.subscriptions = data as SubscriptionMod[];
        if(this.subscriptions.length != 0){
          if(this.role == 1) {
            for (let i in this.userSubscriptions) {
              for (let y in this.subscriptions) {
                if (this.userSubscriptions[i].id == this.subscriptions[y].id) {
                  this.subscriptions[y].isInUserSubscr = true;
                }
              }
            }
          }
          this.isEmpty = false;
          if(this.pagesCount == 1) {
            this.showPagination = false;
          }
          else {
            this.showPagination = true;
          }
        }
        else{
          this.isEmpty = true;
          this.showPagination = false;
        }

      }
    );
  }

  loadUserSubscriptions(){
    this.service.getUserSubscriptions().subscribe(data=>{
      this.userSubscriptions = data as SubscriptionMod[];
    });
  }

  loadPagesNumber(){
    this.pagination.getTotalPagesSubscr(this.subscrPerPage).subscribe(data=>{
      this.pagesCount = data as number;
      if(this.pagesCount == 0){
        this.isEmpty = true;
      }
    });
  }

  loadWallets(id:number){
    this.subscriptionId = id;
    this.wService.getWallets().subscribe(data=>{
      this.wallets = data as Wallet[];
    })
  }

  subscribe() {
    if (this.subscriptions.find(x => x.id == this.subscriptionId).costPerMonth > this.wallets.find(x => x.id == this.walletId).sum) {
      alert('Not enough cash to subscribe');
    } else {
      this.service.subscribe(this.subscriptionId, this.walletId).subscribe(data => {

      });
      window.location.reload();
    }
  }

  unsubscribe(id:number){
    if(confirm('Do you really want to unsubscribe?')) {
      this.service.deleteSubscriptions(id).subscribe(data => {

      });
      window.location.reload();
    }
  }

  deleteSubscription(id:number){
    this.service.deleteSubscriptionGlobally(id).subscribe(data=>{
    });
    window.location.reload();
  }

  goOn1Page(){
    this.pageNumber = 1;
    this.loadAllAvSubscriptions();
  }

  goOnLastPage(){
    this.pageNumber = this.pagesCount;
    this.loadAllAvSubscriptions();
  }

  goOnPreviousePage(){
    this.pageNumber--;
    if(this.pageNumber <= 0){
      this.pageNumber++;
    }
    this.loadAllAvSubscriptions();
  }

  goOnNextPage(){
    this.pageNumber++;
    if(this.pageNumber > this.pagesCount){
      this.pageNumber--;
    }
    this.loadAllAvSubscriptions();
  }

  getSubscrByCategory(id:number){
    if(id == 0){
      this.pageNumber = 1;
      this.loadAllAvSubscriptions();
    }
    this.service.getSubscrByCategory(id).subscribe(data=>{
      this.subscriptions = data as SubscriptionMod[];
      if(this.subscriptions.length == 0){
        this.isEmpty = true;
      }
      else{
        if(this.role == 1) {
          for (let i in this.userSubscriptions) {
            for (let y in this.subscriptions) {
              if (this.userSubscriptions[i].id == this.subscriptions[y].id) {
                this.subscriptions[y].isInUserSubscr = true;
              }
            }
          }
        }
        this.isEmpty = false;
      }
    });
    this.showPagination = false;

  }

  searchSubscr() {
    if (this.searchStr.length == 0) {
      this.pageNumber = 1;
      this.loadAllAvSubscriptions();
    } else {
      this.service.getSubscrByPartOfName(this.searchStr).subscribe(data => {
        this.subscriptions = data as SubscriptionMod[];
        if (this.subscriptions.length == 0) {
          this.isEmpty = true;
        } else {
          if(this.role == 1) {
            for (let i in this.userSubscriptions) {
              for (let y in this.subscriptions) {
                if (this.userSubscriptions[i].id == this.subscriptions[y].id) {
                  this.subscriptions[y].isInUserSubscr = true;
                }
              }
            }
          }
          this.isEmpty = false;
        }
      });
      this.showPagination = false;
    }
  }

}
