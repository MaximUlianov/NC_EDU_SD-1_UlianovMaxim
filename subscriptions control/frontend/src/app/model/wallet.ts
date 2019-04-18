export class Wallet{

  private name:string;
  private sum:number;


  constructor() {
  }


  get _name(): string {
    return this.name;
  }

  get _sum(): number {
    return this.sum;
  }
}
