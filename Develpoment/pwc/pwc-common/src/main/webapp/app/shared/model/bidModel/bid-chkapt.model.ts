export interface IBidChkapt {
  wkut?: string;
  prjno?: string;
  idate?: Date;
  type?: string | null;
  scope?: string | null;
  arlt?: string | null;
  day?: number | null;
  deadline?: Date | null;
  fcost3?: number | null;
  afal?: string | null;
  item1?: string | null;
  item2?: string | null;
  aokDate?: Date | null;
  dsts?: string | null;
  createDate?: Date;
  createUser?: string | null;
  updateDate?: Date | null;
  updateUser?: string | null;
  fckdat?: Date | null;
  fokdat?: Date | null;
  ackdat?: Date | null;
  chker1?: string | null;
  chker2?: string | null;
  fcksdat?: Date | null;
  fckedat?: Date | null;
  acksdat?: Date | null;
  ackedat?: Date | null;
  fdays?: number | null;
  adays?: number | null;
}

export class BidChkapt implements IBidChkapt {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public idate?: Date,
    public type?: string | null,
    public scope?: string | null,
    public arlt?: string | null,
    public day?: number | null,
    public deadline?: Date | null,
    public fcost3?: number | null,
    public afal?: string | null,
    public item1?: string | null,
    public item2?: string | null,
    public aokDate?: Date | null,
    public dsts?: string | null,
    public createDate?: Date,
    public createUser?: string | null,
    public updateDate?: Date | null,
    public updateUser?: string | null,
    public fckdat?: Date | null,
    public fokdat?: Date | null,
    public ackdat?: Date | null,
    public chker1?: string | null,
    public chker2?: string | null,
    public fcksdat?: Date | null,
    public fckedat?: Date | null,
    public acksdat?: Date | null,
    public ackedat?: Date | null,
    public fdays?: number | null,
    public adays?: number | null
  ) {}
}
