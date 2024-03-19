
export interface IBidPrjScequip {

  wkut?: string | null;
  prjno?: string | null;
  subCcut?: string | null;
  eCode?: string | null;
  eCnt?: string | null;
  eRemark?: string | null;
  createDate?: Date | null;
  createUser?: string | null;
  updateDate?: Date | null;
  updateUser?: string | null;
  confA?: string | null;
  confB?: string | null;
  uIp?: string | null;
}


export class BidPrjScequip implements IBidPrjScequip {

  constructor(
    public wkut?: string | null,
    public prjno?: string | null,
    public subCcut?: string | null,
    public eCode?: string | null,
    public eCnt?: string | null,
    public eRemark?: string | null,
    public createDate?: Date | null,
    public createUser?: string | null,
    public updateDate?: Date | null,
    public updateUser?: string | null,
    public confA?: string | null,
    public confB?: string | null,
    public uIp?: string | null
  ){}

}
