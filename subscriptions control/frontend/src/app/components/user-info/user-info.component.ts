import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user/user.service";
import {UserAccount} from "../../model/user.account";
import {SubscriptionMod} from "../../model/subscriptionMod";
import {PaginationService} from "../../service/pagination/pagination-service";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  username:string;
  subscriptions:SubscriptionMod[];
  showSubscriptions:boolean = false;
  users:UserAccount[];
  isEmpty:boolean;
  searchType:string;
  searchParameter:string;
  userId:number;

  showPagination:boolean = true;
  pagesCount:number;
  usersPerPage:number = 6;
  pageNumber:number = 1;

  constructor(private uService:UserService,
              private pagination:PaginationService) { }

  ngOnInit() {
    this.searchType = 'Search by...';
    this.loadPagesNumber();
    this.loadUsers();
  }

  loadUsers(){
    this.pagination.getUsers(this.pageNumber, this.usersPerPage).subscribe(data=>{
      this.users = data as UserAccount[];
      if(this.users.length != 0){
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
    });
  }

  loadUserSubscriptions(id:number, username:string){
    if(this.showSubscriptions == true){
      this.closeTable();
    }
    else {
      this.username = username;
      this.userId = id;
      this.uService.getUserSubscriptionsByAdmin(id).subscribe(data => {
        this.subscriptions = data as SubscriptionMod[];
        this.showSubscriptions = true;
      });
    }
  }

  closeTable(){
    this.username = '';
    this.subscriptions = null;
    this.showSubscriptions = false;
  }

  searchUser(){
    if(this.searchType != 'Search by...') {
      if(this.searchParameter.length == 0){
        this.loadUsers();
      }
      else {
        this.uService.searchUser(this.searchType, this.searchParameter).subscribe(data => {
          this.users = data as UserAccount[];
          if(this.users.length == 0){
            this.isEmpty = true;
          }
          else if(this.users.length > 0){
            this.isEmpty = false;
          }
        });
      }
    }
  }


  loadPagesNumber(){
    this.pagination.getTotalPagesUsers(this.usersPerPage).subscribe(data=>{
      this.pagesCount = data as number;
      if(this.pagesCount == 0){
        this.isEmpty = true;
      }
    });
  }

  goOn1Page(){
    this.pageNumber = 1;
    this.loadUsers();
  }

  goOnLastPage(){
    this.pageNumber = this.pagesCount;
    this.loadUsers();
  }

  goOnPreviousePage(){
    this.pageNumber--;
    if(this.pageNumber <= 0){
      this.pageNumber++;
    }
    this.loadUsers();
  }

  goOnNextPage(){
    this.pageNumber++;
    if(this.pageNumber > this.pagesCount){
      this.pageNumber--;
    }
    this.loadUsers();
  }

  blockSubscription(subscriptionId:number){
    this.uService.blockSubscription(this.userId, subscriptionId).subscribe(data=>{

    });
    this.loadUserSubscriptions(this.userId, this.username);
  }

  unblockSubscription(subId:number){
    this.uService.unblockSubscription(this.userId, subId).subscribe(data=>{

    });
    this.loadUserSubscriptions(this.userId, this.username);
  }


}
