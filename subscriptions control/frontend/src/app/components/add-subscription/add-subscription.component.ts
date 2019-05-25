import {Component, OnInit} from '@angular/core';
import {Category} from "../../model/category";
import {SubscriptionsService} from "../../service/subscriptions/subscriptions.service";
import {Company} from "../../model/company";
import {Product} from "../../model/product";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-subscription',
  templateUrl: './add-subscription.component.html',
  styleUrls: ['./add-subscription.component.css']
})
export class AddSubscriptionComponent implements OnInit {

  product:Product;
  category:Category;
  company:Company;
  allCategories:Category[];
  companies:Company[];

  form:FormGroup;
  formCateg:FormGroup;
  formCompany:FormGroup;

  constructor(private service:SubscriptionsService,
              private formBuilder: FormBuilder)
  {}

  ngOnInit() {
    this.form = this.formBuilder.group({
      name: [null, Validators.required],
      description: [null, Validators.required],
      cost: [null, Validators.required],
      company: [null, Validators.required],
      category: [null, Validators.required]
    });
    this.formCateg = this.formBuilder.group({
      nameCateg: [null, [Validators.required, Validators.pattern(/[a-zA-Z]/)]]
    });
    this.formCompany = this.formBuilder.group({
      nameComp: [null, Validators.required],
      descriptionComp: [null, Validators.required],
    });
    this.category = new Category();
    this.product = new Product();
    this.product.category = new Category();
    this.product.company = new Company();
    this.company = new Company();
    this.loadCategories();
    this.loadCompanies();
  }

  loadCategories(){
    this.service.getCategories().subscribe(data=>{
      this.allCategories = data as Category[];
    });
  }

  loadCompanies(){
    this.service.getCompanies().subscribe(data=>{
      this.companies = data as Company[];
    });
  }

  addSubscr(){
    this.product.name = this.form.get('name').value;
    this.product.description = this.form.get('description').value;
    this.product.costPerMonth = this.form.get('cost').value;
    this.product.category.name = this.form.get('category').value;
    this.product.company.name = this.form.get('company').value;
    if(this.product.company.name != 'Choose company' &&
      this.product.category.name != 'Choose category') {
      this.service.addProduct(this.product).subscribe(data => {

      });
      window.location.reload();
    }
  }

  addCateg(){
    this.category.name = this.formCateg.get('nameCateg').value;
    this.service.addCategory(this.category).subscribe(data=>{

    });
    window.location.reload();
  }

  addCompany(){
    this.company.name = this.formCompany.get('nameComp').value;
    this.company.description = this.formCompany.get('descriptionComp').value;
    this.service.addCompany(this.company).subscribe(data=>{

    });
    window.location.reload();
  }

  isFieldValid(field: string) {
    return this.form.get(field).touched && !this.form.get(field).valid;
  }

  isFieldCategValid(field: string) {
    return this.formCateg.get(field).touched && !this.formCateg.get(field).valid;
  }

  isFieldsCompValid(field: string) {
    return this.formCompany.get(field).touched && !this.formCompany.get(field).valid;
  }


  displayCompanyCss(){
    return {
      'has-error': this.isCompanyValid(),
      'has-feedback': this.isCompanyValid()
    };
  }

  displayCategoryCss(){
    return {
      'has-error': this.isCategoryValid(),
      'has-feedback': this.isCategoryValid()
    };
  }

  displayCategCss(field:string){
    return {
      'has-error': this.isFieldCategValid(field),
      'has-feedback': this.isFieldCategValid(field)
    };
  }

  displayCompCss(field:string){
    return {
      'has-error': this.isFieldsCompValid(field),
      'has-feedback': this.isFieldsCompValid(field)
    };
  }

  isCompanyValid(){
    return this.form.get('company').value == 'Choose company';
  }

  isCategoryValid(){
    return this.form.get('category').value == 'Choose category';
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }


}
