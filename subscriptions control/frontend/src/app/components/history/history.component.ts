import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user/user.service";
import {Audit} from "../../model/audit";

const USER_KEY = "USER";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  from:Date;
  to:Date;
  history:Audit[];
  showTable:boolean;
  id:any;

  constructor(private uService:UserService) { }

  ngOnInit() {
    this.id = JSON.parse(localStorage.getItem(USER_KEY));
    this.loadUserHistory();
  }

  loadUserHistory(){
    this.uService.getUserHistory(this.id.id).subscribe(data=>{
      this.history = data as Audit[];
      if(this.history.length != 0){
        this.showTable = true;
      }
      else{
        this.showTable = false;
      }
    })
  }

  searchByDates(){
    this.uService.searchHistoryByDates(this.id.id, this.from, this.to).subscribe(data=>{
      this.history = data as Audit[];
      if(this.history.length != 0){
        this.showTable = true;
      }
      else{
        this.showTable = false;
      }
    })
  }
}
