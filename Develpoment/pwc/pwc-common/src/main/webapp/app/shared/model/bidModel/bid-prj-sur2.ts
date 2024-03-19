export interface IBidPrjSur2 {
  wkut?: string; // 執行單位
  prjno?: string; // 標案編號
  disNo?: string; // 核定預算項目
  amnta?: number | null; // 使用地方災害準備金數
  amntb?: number | null; // 使用中央補助款數
  amnt?: number | null; // 建立日期
  createDate?: Date; // 建立人員
  createUser?: string; // 建立日期
  updateDate?: Date | null; // 更新日期
  updateUser?: string | null; // 更新人員
  dsts?: string | null; // 資料狀況
  confOut?: string | null; // 預定執行單位資料確認
  uIp?: string | null; // 更新IP
  disName?: string | null; // 災害工程項目及內容名稱
  veri3Budg?: number | null; // 審查小組審議金額
}

export class BidPrjSur2 implements IBidPrjSur2 {
  constructor(
    public wkut?: string, // 執行單位
    public prjno?: string, // 標案編號
    public disNo?: string, // 核定預算項目
    public amnta?: number | null, // 使用地方災害準備金數
    public amntb?: number | null, // 使用中央補助款數
    public amnt?: number | null, // 建立日期
    public createDate?: Date, // 建立人員
    public createUser?: string, // 建立日期
    public updateDate?: Date | null, // 更新日期
    public updateUser?: string | null, // 更新人員
    public dsts?: string | null, // 資料狀況
    public confOut?: string | null, // 預定執行單位資料確認
    public uIp?: string | null, // 更新IP
    public disName?: string | null, // 災害工程項目及內容名稱
    public veri3Budg?: number | null // 審查小組審議金額
  ) {}
}
