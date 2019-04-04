import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {ModalModule} from 'ngx-bootstrap/modal';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from "@angular/router";
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';

import {BillingAccountsComponent} from './components/billing-accounts/billing-accounts.component';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {MainPageComponent} from './components/main-page/main-page.component';
import {SignUpPageComponent} from './components/sign-up-page/sign-up-page.component';
import {UserRegistrationService} from "./service/registration/user-registration.service";


const appRoutes: Routes = [
  {path:'main', component: MainPageComponent},
  {path:'main/reg', component: SignUpPageComponent},
  {path:'', redirectTo:'/main', pathMatch:'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    BillingAccountsComponent,
    MainPageComponent,
    SignUpPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserRegistrationService],
  bootstrap: [AppComponent],

})
export class AppModule {}
