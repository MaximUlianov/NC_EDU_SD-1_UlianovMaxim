import {Component, OnInit} from '@angular/core';
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {TokenStorage} from "../../authorization-config/token-provider";
import {Category} from "../../model/category";
import {Wallet} from "../../model/wallet";
import {WalletService} from "../../service/wallets/wallet.service";
import {PaginationService} from "../../service/pagination/pagination-service";
import {Company} from "../../model/company";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Product} from "../../model/product";
import {SubscriptionMod} from "../../model/subscriptionMod";

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
  products:Product[];
  userSubscrProducts:SubscriptionMod[];

  role:number;
  searchStr:string;
  sale:number;

  pagesCount:number;
  subscrPerPage:number = 6;
  pageNumber:number = 1;

  isEmpty:boolean = false;
  showPagination:boolean = true;

  subscription:SubscriptionMod;



  constructor(private service:SubscriptionsService,
              private tokenUtil:TokenStorage,
              private wService:WalletService,
              private pagination:PaginationService,
              private spinnerService: Ng4LoadingSpinnerService) { }

  ngOnInit() {
    this.subscription = new SubscriptionMod();
    this.loadCategories();
    this.loadCompanies();
    this.checkForUserRole();
    if(this.role == 1){
      this.loadUserProducts();
    }
    this.loadPagesNumber();
    this.loadAllAvProducts();
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
      this.loadAllAvProducts();
    }
    this.service.getProductsByCompany(id).subscribe(data=>{
      this.products = data as Product[];
      if(this.products.length == 0){
        this.isEmpty = true;
      }
      else{
        if(this.role == 1) {
          for (let i in this.userSubscrProducts) {
            for (let y in this.products) {
              if (this.userSubscrProducts[i].id == this.products[y].id) {
                this.products[y].isInUserProd = true;
              }
            }
          }
        }
        this.isEmpty = false;
      }
    });
    this.showPagination = false;
  }

  loadAllAvProducts(){
    if(this.pageNumber < 0 || this.pageNumber > this.pagesCount){
      this.pageNumber = 1;
    }

    this.pagination.getProducts(this.pageNumber, this.subscrPerPage).subscribe(data=>{
      this.products = data as Product[];
        if(this.products.length != 0){
          if(this.role == 1) {
            for (let i in this.userSubscrProducts) {
              for (let y in this.products) {
                if (this.userSubscrProducts[i].product.id == this.products[y].id) {
                  this.products[y].isInUserProd = true;
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

  loadUserProducts(){
    this.service.getUserSubscriptions().subscribe(data=>{
      this.userSubscrProducts = data as SubscriptionMod[];
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
    this.subscription.product = this.products.find(x => x.id == id);
    this.wService.getWallets().subscribe(data=>{
      this.wallets = data as Wallet[];
    })
  }

  subscribe() {
    if (this.products.find(x => x.id == this.subscription.product.id).costPerMonth/30 > this.wallets.find(x => x.id == this.subscription.wallet.id).sum) {
      alert('Not enough cash to subscribe');
    } else {
      this.service.subscribe(this.subscription).subscribe(data => {

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
    this.service.deleteProduct(id).subscribe(data=>{
    });
    window.location.reload();
  }

  goOn1Page(){
    this.pageNumber = 1;
    this.loadAllAvProducts();
  }

  goOnLastPage(){
    this.pageNumber = this.pagesCount;
    this.loadAllAvProducts();
  }

  goOnPreviousePage(){
    this.pageNumber--;
    if(this.pageNumber <= 0){
      this.pageNumber++;
    }
    this.loadAllAvProducts();
  }

  goOnNextPage(){
    this.pageNumber++;
    if(this.pageNumber > this.pagesCount){
      this.pageNumber--;
    }
    this.loadAllAvProducts();
  }

  getSubscrByCategory(id:number){
    if(id == 0){
      this.pageNumber = 1;
      this.loadAllAvProducts();
    }
    this.service.getProductsByCategory(id).subscribe(data=>{
      this.products = data as Product[];
      if(this.products.length == 0){
        this.isEmpty = true;
      }
      else{
        if(this.role == 1) {
          for (let i in this.userSubscrProducts) {
            for (let y in this.products) {
              if (this.userSubscrProducts[i].product.id == this.products[y].id) {
                this.products[y].isInUserProd = true;
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
      this.loadAllAvProducts();
    } else {
      this.service.getProductsByPartOfName(this.searchStr).subscribe(data => {
        this.products = data as Product[];
        if (this.products.length == 0) {
          this.isEmpty = true;
        } else {
          if(this.role == 1) {
            for (let i in this.userSubscrProducts) {
              for (let y in this.products) {
                if (this.userSubscrProducts[i].product.id == this.products[y].id) {
                  this.products[y].isInUserProd = true;
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

  setSale(id:number){
    let prod = new Product();
    prod.id = id;
    prod.sale = this.sale;
    this.service.setSale(prod).subscribe(data=>{

    });
    window.location.reload();
  }

}
