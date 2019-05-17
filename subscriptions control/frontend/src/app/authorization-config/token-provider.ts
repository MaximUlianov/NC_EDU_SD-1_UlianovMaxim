import {Injectable} from '@angular/core';
import * as jwt_decode from "jwt-decode";

const TOKEN_KEY = 'AuthToken';

@Injectable()
export class TokenStorage {


  constructor() { }

  signOut() {
    window.localStorage.removeItem(TOKEN_KEY);
    if(window.localStorage.length != 0) {
      window.localStorage.clear();
    }
  }

  public saveToken(token:any) {

    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token.token);
  }

  public getToken(): string {
    return window.localStorage.getItem(TOKEN_KEY);
  }

  public getDecodedAccessToken(token: string): any {
    try{
      return jwt_decode(token);
    }
    catch(Error){
      return null;
    }
  }
}
