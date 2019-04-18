import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {Router} from '@angular/router';

import 'rxjs/add/operator/do';
import {Observable} from "rxjs";
import {TokenStorage} from "./token-provider";
import {tap} from "rxjs/operators";

const TOKEN_HEADER_KEY = 'Authorization';


@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    let authReq = req;
    if (this.token.getToken() != null) {
    authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
    }
    return next.handle(authReq).pipe(tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
        }
      },(err:any)=>{
          console.log('req url :: ' + req.url);
          console.error('Interseptor error', err);
          if(err instanceof HttpErrorResponse){
            if(err.status == 401){
              this.router.navigate(['main']);
            }
          }
        }
    ));
  }

}
