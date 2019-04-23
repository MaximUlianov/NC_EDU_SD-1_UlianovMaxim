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
import {AvailableSubscriptionsComponent} from './components/available-subscriptions/available-subscriptions.component';
import {AboutGuard} from "./about.guards";


const appRoutes: Routes = [
  {path:'main', component: MainPageComponent},
  {path:'reg', component: SignUpPageComponent},
  {path:'wallets', component:WalletsComponent, canActivate:[AboutGuard]},
  {path:'subscriptions', component:SubscriptionsComponent, canActivate:[AboutGuard]},
  {path:'avSubscriptions', component:AvailableSubscriptionsComponent},

  {path:'main/reg', redirectTo:'reg', pathMatch:'full'},
  {path:'', redirectTo:'/main', pathMatch:'full'},
  {path:'main/wallets',redirectTo:'wallets', pathMatch:'full'},
  {path:'main/subscriptions',redirectTo:'subscriptions', pathMatch:'full'},
  {path:'wallets/subscriptions', redirectTo:'subscriptions', pathMatch:'full'},
  {path:'subscriptions/wallets', redirectTo:'wallets', pathMatch:'full'},
  {path:'subscriptions/avSubscriptions', redirectTo:'avSubscriptions', pathMatch:'full'}
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
    TermsComponent,
    AvailableSubscriptionsComponent,
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
              AboutGuard
              ],
  bootstrap: [AppComponent],

})
export class AppModule {}
