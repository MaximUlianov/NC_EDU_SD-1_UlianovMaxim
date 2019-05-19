import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {TokenStorage} from "./authorization-config/token-provider";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class AdminGuard implements CanActivate{

  constructor(private router:Router, private tokenUtil:TokenStorage){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : Observable<boolean> | boolean {

    if (localStorage.getItem(TOKEN_KEY) != null) {
      let tokenInfo = this.tokenUtil.getDecodedAccessToken(localStorage.getItem(TOKEN_KEY));
      if (tokenInfo.scopes == 'ROLE_ADMIN') {
        return true;
      } else {
        this.router.navigate(['main']);
      }
    }
    else {
      this.router.navigate(['main']);
      return false;
    }

  }
}
