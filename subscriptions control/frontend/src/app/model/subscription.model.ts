export class SubscriptionModel{
  private name: string;
  private category:string;


  constructor() {
  }


  get _name(): string {
    return this.name;
  }

  get _category(): string {
    return this.category;
  }
}
