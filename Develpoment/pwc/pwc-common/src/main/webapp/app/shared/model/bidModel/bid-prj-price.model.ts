export interface IBidPrjPrice {
  wkut?: string;
  prjno?: string;
  year?: string;
  pUnit?: string;
  grade?: string;
  pType?: string | null;
  pName?: string | null;
  createDate?: Date;
  createUser?: string;
  updateDate?: Date | null;
  updateUser?: string | null;
  uIp?: string | null;
  pSdate?: Date | null;
  pEdate?: Date | null;
}

export class BidPrjPrice implements IBidPrjPrice {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public year?: string,
    public pUnit?: string,
    public grade?: string,
    public pType?: string | null,
    public pName?: string | null,
    public createDate?: Date,
    public createUser?: string,
    public updateDate?: Date | null,
    public updateUser?: string | null,
    public uIp?: string | null,
    public pSdate?: Date | null,
    public pEdate?: Date | null
  ) {}
}
