export interface IBidPrjModify {
  wkut?: string;
  prjno?: string;
  chgDate?: Date;
  mdyPress?: string | null;
  okNo?: string | null;
  mdyReson?: string | null;
  mdyRemark?: string | null;
  chgRate?: number | null;
  oldAddAmt?: number | null;
  oldSubtractAmt?: number | null;
  oldAmt?: number | null;
  addAmt?: number | null;
  subtractAmt?: number | null;
  newAmt?: number | null;
  oldDate?: Date | null;
  newDate?: Date | null;
  dlyd1?: number | null;
  dlyd2?: number | null;
  dlyd3?: number | null;
  dlyd4?: number | null;
  dlyd5?: number | null;
  nDays?: number | null;
  createDate?: Date | null;
  createUser?: string | null;
  updateDate?: Date | null;
  updateUser?: string | null;
  dsts?: string | null;
  uIp?: string | null;
  mergeName?: string | null;
  mergeNo?: string | null;
  mergectSum?: number | null;
  remark?: string | null;
  remark1?: string | null;
  remark2?: string | null;
}

export class BidPrjModify implements IBidPrjModify {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public chgDate?: Date,
    public mdyPress?: string | null,
    public okNo?: string | null,
    public mdyReson?: string | null,
    public mdyRemark?: string | null,
    public chgRate?: number | null,
    public oldAddAmt?: number | null,
    public oldSubtractAmt?: number | null,
    public oldAmt?: number | null,
    public addAmt?: number | null,
    public subtractAmt?: number | null,
    public newAmt?: number | null,
    public oldDate?: Date | null,
    public newDate?: Date | null,
    public dlyd1?: number | null,
    public dlyd2?: number | null,
    public dlyd3?: number | null,
    public dlyd4?: number | null,
    public dlyd5?: number | null,
    public nDays?: number | null,
    public createDate?: Date | null,
    public createUser?: string | null,
    public updateDate?: Date | null,
    public updateUser?: string | null,
    public dsts?: string | null,
    public uIp?: string | null,
    public mergeName?: string | null,
    public mergeNo?: string | null,
    public mergectSum?: number | null,
    public remark?: string | null,
    public remark1?: string | null,
    public remark2?: string | null
  ) {}
}
