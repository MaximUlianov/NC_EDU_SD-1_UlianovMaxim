import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {ModalModule} from 'ngx-bootstrap/modal';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from "@angular/router";
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

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
import {NgxPaginationModule} from "ngx-pagination";
import {UserInfoComponent} from './components/user-info/user-info.component';
import {UserService} from "./service/user/user.service";
import {Interceptor} from "./authorization-config/interceptor";
import {AddSubscriptionComponent} from './components/add-subscription/add-subscription.component';
import {PaginationService} from "./service/pagination/pagination-service";
import {CompanyComponent} from './components/company/company.component';
import {AdminGuard} from "./admin.guard";
import {CompaniesService} from "./service/companies/companies.service";
import {HistoryComponent} from './components/history/history.component';
import {FieldErrorComponent} from './components/field-error/field-error.component';

const appRoutes: Routes = [
  {path:'main', component: MainPageComponent},
  {path:'reg', component: SignUpPageComponent},
  {path:'wallets', component:WalletsComponent, canActivate:[AboutGuard]},
  {path:'subscriptions', component:SubscriptionsComponent, canActivate:[AboutGuard]},
  {path:'avSubscriptions', component:AvailableSubscriptionsComponent},
  {path:'users', component:UserInfoComponent, canActivate:[AdminGuard]},
  {path:'companies', component:CompanyComponent, canActivate:[AdminGuard]},
  {path:'history', component:HistoryComponent, canActivate:[AboutGuard]},

  {path:'main/reg', redirectTo:'reg', pathMatch:'full'},
  {path:'', redirectTo:'/main', pathMatch:'full'},
  {path:'main/wallets',redirectTo:'wallets', pathMatch:'full'},
  {path:'main/subscriptions',redirectTo:'subscriptions', pathMatch:'full'},
  {path:'wallets/subscriptions', redirectTo:'subscriptions', pathMatch:'full'},
  {path:'subscriptions/wallets', redirectTo:'wallets', pathMatch:'full'},
  {path:'subscriptions/avSubscriptions', redirectTo:'avSubscriptions', pathMatch:'full'},
  {path:'avSubscriptions/subscriptions', redirectTo:'subscriptions', pathMatch:'full'},
  {path:'avSubscriptions/wallets', redirectTo:'wallets', pathMatch:'full'},
  {path:'main/avSubscriptions',redirectTo:'avSubscriptions', pathMatch:'full'},
  {path:'main/users', redirectTo:'users', pathMatch:'full'},
  {path:'users/avSubscriptions',redirectTo:'avSubscriptions', pathMatch:'full'},
  {path:'avSubscriptions/users',redirectTo:'users', pathMatch:'full'},
  {path:'main/companies', redirectTo:'companies', pathMatch:'full'},
  {path:'companies/users', redirectTo:'users', pathMatch:'full'},
  {path:'users/companies', redirectTo:'companies', pathMatch:'full'},
  {path:'companies/avSubscriptions', redirectTo:'avSubscriptions', pathMatch:'full'},
  {path:'avSubscriptions/companies', redirectTo:'companies', pathMatch:'full'},
  {path:'main/history', redirectTo:'history', pathMatch:'full'},
  {path:'subscriptions/history', redirectTo:'history', pathMatch:'full'},
  {path:'history/subscriptions', redirectTo:'subscriptions', pathMatch:'full'},
  {path:'history/avSubscriptions', redirectTo:'avSubscriptions', pathMatch:'full'},
  {path:'avSubscriptions/history', redirectTo:'history', pathMatch:'full'},
  {path:'history/wallets', redirectTo:'wallets', pathMatch:'full'},
  {path:'wallets/history', redirectTo:'history', pathMatch:'full'},


];

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    SignUpPageComponent,
    LogInComponent,
    NavbarComponent,
    WalletsComponent,
    SubscriptionsComponent,
    TermsComponent,
    AvailableSubscriptionsComponent,
    UserInfoComponent,
    AddSubscriptionComponent,
    CompanyComponent,
    HistoryComponent,
    FieldErrorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    NgxPaginationModule,
  ],
  providers: [UserRegistrationService, MainPageService, LogUserService, TokenStorage,
              WalletService,
              SubscriptionsService,
              AboutGuard,
              AdminGuard,
              UserService,
              PaginationService,
              CompaniesService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }
              ],
  bootstrap: [AppComponent],

})
export class AppModule {}
