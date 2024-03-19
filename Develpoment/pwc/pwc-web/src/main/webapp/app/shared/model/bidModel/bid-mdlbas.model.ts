export interface IBidMdlbas {
  wkut?: string; // 執行單位
  disNo?: string; //序號
  disName?: string; //災害工程項目及內容名稱
  disPlce?: string; //地點
  workType?: string; //工程種類
  estBudg?: number; //初步查估復建經費
  workItem?: string; //內容數量單價
  veriBudg?: number; //複查復建經費
  veri2Budg?: number; //中央主管機關建議金額
  veri2Item?: string; //中央主管機關審查意見
  veri3Budg?: number; //審查小組審議金額
  veri3Item?: string; //審查小組審議意見
  tm2x?: string; //二度分帶X座標
  tm2y?: string; //二度分帶Y座標
  notyBudg?: string; //尚未分配至標案金額
  veri3Budg0?: number; //審查小組原審議金額
  disRmk?: string; //修改原因
  sActdt?: Date; //修改後發包期限
  sActdt0?: Date; //核定發包期限
  sAedat?: Date; //修改後完工期限
  sAedat0?: Date; //核定完工期限
  chgRmk?: string; //文號備註
  delResn?: string; //修改原因類別
  delResn2?: string; //修改原因類別2
  createDate?: Date; //建立日期
  createUser?: string; //建立人員
  updateDate?: Date; //更新日期
  updateUser?: string; //更新人員
}
export class BidMdlbas implements IBidMdlbas {
  constructor(
    public wkut?: string, // 執行單位
    public disNo?: string, //序號
    public disName?: string, //災害工程項目及內容名稱
    public disPlce?: string, //地點
    public workType?: string, //工程種類
    public estBudg?: number, //初步查估復建經費
    public workItem?: string, //內容數量單價
    public veriBudg?: number, //複查復建經費
    public veri2Budg?: number, //中央主管機關建議金額
    public veri2Item?: string, //中央主管機關審查意見
    public veri3Budg?: number, //審查小組審議金額
    public veri3Item?: string, //審查小組審議意見
    public tm2x?: string, //二度分帶X座標
    public tm2y?: string, //二度分帶Y座標
    public notyBudg?: string, //尚未分配至標案金額
    public veri3Budg0?: number, //審查小組原審議金額
    public disRmk?: string, //修改原因
    public sActdt?: Date, //修改後發包期限
    public sActdt0?: Date, //核定發包期限
    public sAedat?: Date, //修改後完工期限
    public sAedat0?: Date, //核定完工期限
    public chgRmk?: string, //文號備註
    public delResn?: string, //修改原因類別
    public delResn2?: string, //修改原因類別2
    public createDate?: Date, //建立日期
    public createUser?: string, //建立人員
    public updateDate?: Date, //更新日期
    public updateUser?: string //更新人員
  ) {}
}
