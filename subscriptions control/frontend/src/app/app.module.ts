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
import {UserAccountComponent} from './components/user-account/user-account.component';
import {MainPageService} from "./service/main-page/main-page.service";
import {LogInComponent} from './components/log-in/log-in.component';
import {LogUserService} from "./service/registration/log-user.service";
import {TokenStorage} from "./authorization-config/token-provider";


const appRoutes: Routes = [
  {path:'main', component: MainPageComponent},
  {path:'reg', component: SignUpPageComponent},
  {path:'main/reg', redirectTo:'reg'},
  {path:'', redirectTo:'/main', pathMatch:'full'},
  {path:'user', component: UserAccountComponent},
  {path:'main/user', redirectTo:'user'},
  {path:'main/logIn', component:LogInComponent},
  {path:'main/logIn/reg',redirectTo:'reg' }
];

@NgModule({
  declarations: [
    AppComponent,
    BillingAccountsComponent,
    MainPageComponent,
    SignUpPageComponent,
    UserAccountComponent,
    LogInComponent
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
  providers: [UserRegistrationService, MainPageService, LogUserService, TokenStorage],
  bootstrap: [AppComponent],

})
export class AppModule {}
