export class LogUser{

  private email:string;
  private password:string;
  private role:string;


  constructor() {
  }

  get _email(): string {
    return this.email;
  }

  set _email(value: string) {
    this.email = value;
  }

  get _password(): string {
    return this.password;
  }

  set _password(value: string) {
    this.password = value;
  }

  get _role(): string {
    return this.role;
  }

  set _role(value: string) {
    this.role = value;
  }
}
