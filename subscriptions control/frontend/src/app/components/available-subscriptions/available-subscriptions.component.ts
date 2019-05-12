import {Component, OnInit} from '@angular/core';
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {SubscriptionMod} from "../../model/subscriptionMod";
import {TokenStorage} from "../../authorization-config/token-provider";
import {Category} from "../../model/category";
import {Wallet} from "../../model/wallet";
import {WalletService} from "../../service/wallets/wallet.service";
import {PaginationService} from "../../service/pagination/pagination-service";
import {Company} from "../../model/company";

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
              private pagination:PaginationService) { }

  ngOnInit() {
    this.loadCategories();
    this.loadCompanies()
    this.checkForUserRole();
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
    })
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
        this.isEmpty = false;
      }
    });
    this.showPagination = false;
  }

  loadAllAvSubscriptions(){
    this.pagination.getSubscriptions(this.pageNumber, this.subscrPerPage).subscribe(data=>{
      this.subscriptions = data as SubscriptionMod[];
        if(this.subscriptions.length != 0){
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

  subscribe(){
    this.service.subscribe(this.subscriptionId, this.walletId).subscribe(data=>{
    });
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
          this.isEmpty = false;
        }
      });
      this.showPagination = false;
    }
  }

}
