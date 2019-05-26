import {Component, OnInit} from '@angular/core';
import {Company} from "../../model/company";
import {PaginationService} from "../../service/pagination/pagination-service";
import {CompaniesService} from "../../service/companies/companies.service";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  company:Company;
  companies:Company[];

  isEmpty:boolean;
  isEditWindow:boolean;
  showPagination:boolean;

  currPage:number;
  pagesCount:number;
  perPage:number;


  constructor(private pagination:PaginationService,
              private cService:CompaniesService) { }

  ngOnInit() {
    this.company = new Company();
    this.currPage = 1;
    this.perPage = 6;
    this.loadPagesNumber();
    this.loadCompanies();
  }


  loadCompanies(){
    this.pagination.getCompanies(this.currPage, this.perPage).subscribe(data=>{
      this.companies = data as Company[];
      if(this.companies.length != 0){
        if(this.pagesCount == 1){
          this.showPagination = false;
        }
        else{
          this.showPagination = true;
        }
        this.isEmpty = false;
      }
      else {
        this.showPagination = false;
        this.isEmpty = true;
      }
    });

  }

  deleteCompany(id:number){
    this.currPage = 1;
    this.cService.deleteCompany(id).subscribe(data=>{
      this.loadPagesNumber();
      this.loadCompanies();
    });
  }

  openEditWindow(company:Company){
    this.company = company;
    this.isEditWindow = true;
  }

  editCompany(){
    this.cService.editCompany(this.company).subscribe(data=>{
      this.loadCompanies();
    });
  }

  closeEdit(){
    this.company = null;
    this.isEditWindow = false;
  }


  loadPagesNumber(){
    this.pagination.getTotalPagesCompanies(this.perPage).subscribe(data=>{
      this.pagesCount = data as number;
      if(this.pagesCount == 0){
        this.isEmpty = true;
      }
      else {
        this.isEmpty = false;
      }
    });
  }

  goOn1Page(){
    this.currPage = 1;
    this.loadCompanies();
  }

  goOnLastPage(){
    this.currPage = this.pagesCount;
    this.loadCompanies();
  }

  goOnPreviousePage(){
    this.currPage--;
    if(this.currPage <= 0){
      this.currPage++;
    }
    this.loadCompanies();
  }

  goOnNextPage(){
    this.currPage++;
    if(this.currPage > this.pagesCount){
      this.currPage--;
    }
    this.loadCompanies();
  }




}
