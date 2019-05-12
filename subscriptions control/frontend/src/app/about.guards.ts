import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {TokenStorage} from "./authorization-config/token-provider";

const TOKEN_KEY = 'AuthToken';
@Injectable()
export class AboutGuard implements CanActivate{

  constructor(private router:Router, private tokenUtil:TokenStorage){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : Observable<boolean> | boolean{

    if (window.localStorage.getItem(TOKEN_KEY) == null){
      this.router.navigate(['main']);
      return false;
    }
    else
      return true;
  }
}
