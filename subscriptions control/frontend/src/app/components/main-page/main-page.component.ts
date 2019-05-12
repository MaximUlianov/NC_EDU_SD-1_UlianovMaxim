import {Component, OnInit} from '@angular/core';
import {MainPageService} from "../../service/main-page/main-page.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Company} from "../../model/company";
import {WOW} from 'wowjs/dist/wow.min.js'

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit{

  companies:Company[];

  constructor(private service:MainPageService,
              private spinner:Ng4LoadingSpinnerService) {}


  ngOnInit() {
    this.loadCompanies();
    new WOW().init();
  }

  loadCompanies(){
    this.service.getCompanies().subscribe(data=>{
      this.companies = data as Company[];
    });
  }

}
