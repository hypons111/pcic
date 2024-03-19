export interface IBidPrjStopwk {
  wkut?: string;
  prjno?: string;
  startDate?: Date;
  respons?: string | null;
  duty?: string | null;
  prmNo?: string | null;
  negoUnit?: string | null;
  stopReson?: string | null;
  negoLevel?: string | null;
  column1?: string | null;
  column2?: string | null;
  column3?: string | null;
  column4?: string | null;
  column5?: string | null;
  remark?: string | null;
  eRemark?: string | null;
  uIp?: string | null;
  createUser?: string;
  updateUser?: string | null;
  confOut?: number | null;
  noCntDay?: number | null;
  createDate?: Date;
  updateDate?: Date | null;
  peDate?: Date | null;
  endDate?: Date | null;
}

export class BidPrjStopwk implements IBidPrjStopwk {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public startDate?: Date,
    public respons?: string | null,
    public duty?: string | null,
    public prmNo?: string | null,
    public negoUnit?: string | null,
    public stopReson?: string | null,
    public negoLevel?: string | null,
    public column1?: string | null,
    public column2?: string | null,
    public column3?: string | null,
    public column4?: string | null,
    public column5?: string | null,
    public remark?: string | null,
    public eRemark?: string | null,
    public uIp?: string | null,
    public createUser?: string,
    public updateUser?: string | null,
    public confOut?: number | null,
    public noCntDay?: number | null,
    public createDate?: Date,
    public updateDate?: Date | null,
    public peDate?: Date | null,
    public endDate?: Date | null
  ) {}
}
