import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user/user.service";
import {UserAccount} from "../../model/user.account";
import {PaginationService} from "../../service/pagination/pagination-service";
import {Audit} from "../../model/audit";
import {Wallet} from "../../model/wallet";
import {WalletService} from "../../service/wallets/wallet.service";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  username:string;
  wallets:Wallet[];
  showWallets:boolean = false;
  showHistory:boolean = false;
  users:UserAccount[];
  history:Audit[];
  isEmpty:boolean;
  isWalletsEmpty:boolean;
  searchType:string;
  searchParameter:string;
  userId:number;

  showPagination:boolean = true;
  pagesCount:number;
  usersPerPage:number = 6;
  pageNumber:number = 1;

  constructor(private uService:UserService,
              private wService:WalletService,
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

  loadUserWallets(id:number, username:string){
      this.username = username;
      this.userId = id;
      this.uService.getUserWalletsByAdmin(id).subscribe(data => {
        this.wallets = data as Wallet[];
        this.showWallets = true;
        if(this.wallets.length == 0){
          this.isWalletsEmpty = true;
        }
        else{
          this.isWalletsEmpty= false;
        }
      });
  }

  closeTableWallets(){
    this.wallets = null;
    this.showWallets = false;
  }

  closeTableHist(){
    this.username = '';
    this.history = null;
    this.showHistory = false;
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

  deleteWallet(walletId:number){
    this.wService.deleteWallet(walletId).subscribe(data=>{
      this.loadUserWallets(this.userId, this.username);
    });
  }

  loadUserHistory(id:number, username:string){
    this.username = username;
    this.uService.getUserHistory(id).subscribe(data=>{
      this.history = data as Audit[];
      this.showHistory = true;
    })
  }

}
