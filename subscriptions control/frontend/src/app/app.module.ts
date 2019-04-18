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
import {MainPageService} from "./service/main-page/main-page.service";
import {LogInComponent} from './components/log-in/log-in.component';
import {LogUserService} from "./service/registration/log-user.service";
import {TokenStorage} from "./authorization-config/token-provider";
import {NavbarComponent} from './components/navbar/navbar.component';
import {WalletsComponent} from './components/wallets/wallets.component';
import {SubscriptionsComponent} from './components/subscriptions/subscriptions.component';
import {WalletService} from "./service/wallets/wallet.service";
import {SubscriptionsService} from "./service/subscriptions/subscriptions.service";
import {TermsComponent} from './components/terms/terms.component';


const appRoutes: Routes = [
  {path:'main', component: MainPageComponent},
  {path:'reg', component: SignUpPageComponent},
  {path:'main/reg', redirectTo:'reg', pathMatch:'full'},
  {path:'', redirectTo:'/main', pathMatch:'full'},
  {path:'wallet', component:WalletsComponent},
  {path:'main/wallet',redirectTo:'wallet', pathMatch:'full'},
  {path:'subscriptions', component:SubscriptionsComponent},
  {path:'main/subscriptions',redirectTo:'wallet', pathMatch:'full'}

];

@NgModule({
  declarations: [
    AppComponent,
    BillingAccountsComponent,
    MainPageComponent,
    SignUpPageComponent,
    LogInComponent,
    NavbarComponent,
    WalletsComponent,
    SubscriptionsComponent,
    TermsComponent
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
  providers: [UserRegistrationService, MainPageService, LogUserService, TokenStorage,
              WalletService,
              SubscriptionsService,
              ],
  bootstrap: [AppComponent],

})
export class AppModule {}
