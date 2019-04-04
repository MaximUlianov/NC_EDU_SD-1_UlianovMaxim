export class User {
  first_name: string;
  last_name: string;
  username: string;
  country: string;
  email:string;
  password:string;

  constructor(){
    this.first_name = null;
    this.last_name = null;
    this.username = null;
    this.country = null;
    this.email = null;
    this.password = null;
  }

  static cloneBase(user: User): User {
    let clonedUser: User = new User();
    clonedUser.email = user.email;
    clonedUser.password = user.password;
    clonedUser.first_name = user.first_name;
    clonedUser.last_name = user.last_name;
    clonedUser.username = user.username;
    clonedUser.country = user.country;
    return clonedUser;
  }


}
