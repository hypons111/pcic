export interface IBidDsptch {
  wkut?: string; // 執行單位
  prjno?: string; //標案編號
  dspNo?: string; //通知編號
  dspSdat?: Date; //通知日期
  ccut1Tel?: string; //承攬廠商電話
  ccut1Fax?: string; //承攬廠商傳真
  ccut1er?: string; //承攬廠商工地負責人
  ccut1erTel?: string; //承攬廠商手機
  scutTel?: string; //監造單位電話
  scutFax?: string; //監造單位傳真
  scuter?: string; //監造單位工地負責人
  scuterTel?: string; //監造單位手機
  dspSpeed?: string; //
  dspItem?: string; //
  dspPmon?: number; //
  dspPedat?: Date; //
  dspAedat?: Date; //
  dspF1dat?: Date; //
  dspAmon?: number; //
  dspF2dat?: Date; //
  sender?: string; //
  sendtel?: string; //
  sendfax?: string; //
  createDate?: Date; //建立日期
  createUser?: string; //建立人員
  updateDate?: Date; //更新日期
  updateUser?: string; //更新人員
  ctSum?: number;
  ctSumF?: number;
}
export class BidDsptch implements IBidDsptch {
  constructor(
    public wkut?: string, // 執行單位
    public prjno?: string, //標案編號
    public dspNo?: string, //通知編號
    public dspSdat?: Date, //通知日期
    public ccut1Tel?: string, //承攬廠商電話
    public ccut1Fax?: string, //承攬廠商傳真
    public ccut1er?: string, //承攬廠商工地負責人
    public ccut1erTel?: string, //承攬廠商手機
    public scutTel?: string, //監造單位電話
    public scutFax?: string, //監造單位傳真
    public scuter?: string, //監造單位工地負責人
    public scuterTel?: string, //監造單位手機
    public dspSpeed?: string, //
    public dspItem?: string, //
    public dspPmon?: number, //
    public dspPedat?: Date, //
    public dspAedat?: Date, //
    public dspF1dat?: Date, //
    public dspAmon?: number, //
    public dspF2dat?: Date, //
    public sender?: string, //
    public sendtel?: string, //
    public sendfax?: string, //
    public createDate?: Date, //建立日期
    public createUser?: string, //建立人員
    public updateDate?: Date, //更新日期
    public updateUser?: string, //更新人員
    public ctSum?: number,
    public ctSumF?: number
  ) {}
}
