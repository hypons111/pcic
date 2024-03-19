export interface IUnit {
  id: string;
  name: string;
}

export class Unit implements IUnit {
  constructor(public id: string, public name: string) {}
}

export interface IUser {
  id: string;
  name: string;
  unit: IUnit;
  idn: string;
  email: string;
  roles: string[];
  authorities: string[];
}

export class User implements IUser {
  constructor(
    public id: string,
    public name: string,
    public unit: IUnit,
    public email: string,
    public idn: string,
    public roles: string[],
    public authorities: string[]
  ) {}
}
