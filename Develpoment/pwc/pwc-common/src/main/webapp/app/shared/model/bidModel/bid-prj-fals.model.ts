export interface IBidPrjFals {
  wkut?: string; // 執行單位
  disNo?: string; //序號
  falsDate?: Date; // 資料年月
  year?: string; //年度
  month?: string; //月份
  syr?: string; //日歷年
  mft?: string; //未開工原因
  mfas?: string; //未開工原因說明
  mfrt?: string; //解決辦法
  mfru?: string; //待協調事項及涉及機關名稱
  oldDate?: Date; //目前預定開工日
  newDate?: Date; //修正後預定開工日
  createDate?: Date; //建立日期
  createUser?: string; //建立人員
  updateDate?: Date; //更新日期
  updateUser?: string; //更新人員
  dsts?: string; //資料狀態
  uIp?: string; //更新IP
  actDate?: Date; // 實際決標日期
  csDate?: Date; // 目前預定開工日
}
export class BidPrjFals implements IBidPrjFals {
  constructor(
    public wkut?: string, // 執行單位
    public disNo?: string, //序號
    public falsDate?: Date, // 資料年月
    public year?: string, //年度
    public month?: string, //月份
    public syr?: string, //日歷年
    public mft?: string, //未開工原因
    public mfas?: string, //未開工原因說明
    public mfrt?: string, //解決辦法
    public mfru?: string, //待協調事項及涉及機關名稱
    public oldDate?: Date, //目前預定開工日
    public newDate?: Date, //修正後預定開工日
    public createDate?: Date, //建立日期
    public createUser?: string, //建立人員
    public updateDate?: Date, //更新日期
    public updateUser?: string, //更新人員
    public dsts?: string, //資料狀態
    public uIp?: string, //更新IP
    public actDate?: Date, // 實際決標日期
    public csDate?: Date // 目前預定開工日
  ) {}
}
