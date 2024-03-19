export interface IBidPrjPay2 {
  wkut?: string; // 執行單位
  prjno?: string; // 標案編號
  date?: Date; // 請撥年月
  year?: string; // 請撥年度
  month?: string; // 請撥月份
  trueMon?: number | null; // 本次請撥金額
  payMonT?: number | null; // 應撥總金額
  payMonU?: number | null; // 截至上次已撥總金額
  tenDate?: Date | null; // 本次預定撥款日期
  tenMon?: number | null; // 本次預定撥款金額
  payDate?: Date | null; // 本次實際撥款日期
  payMon?: number | null; // 本次實際撥款金額
  payMonS?: number | null; // 累計已撥總金額
  chkNo?: string | null; // 核撥文號
  payRmk?: string | null; // 備註
  createDate?: Date; // 建立日期
  createUser?: string; // 建立人員
  updateDate?: Date | null; // 更新日期
  updateUser?: string | null; // 更新人員
  trueDate?: Date; // 本次請撥日期
  dsts?: string | null; // 資料狀態
  confOut?: number | null; // 預定執行單位資料確認
  uIp?: string | null; // 更新IP
}
export class BidPrjPay2 implements IBidPrjPay2 {
  constructor(
    public wkut?: string, // 執行單位
    public prjno?: string, // 標案編號
    public date?: Date, // 請撥年月
    public year?: string, // 請撥年度
    public month?: string, // 請撥月份
    public trueMon?: number | null, // 本次請撥金額
    public payMonT?: number | null, // 應撥總金額
    public payMonU?: number | null, // 截至上次已撥總金額
    public tenDate?: Date | null, // 本次預定撥款日期
    public tenMon?: number | null, // 本次預定撥款金額
    public payDate?: Date | null, // 本次實際撥款日期
    public payMon?: number | null, // 本次實際撥款金額
    public payMonS?: number | null, // 累計已撥總金額
    public chkNo?: string | null, // 核撥文號
    public payRmk?: string | null, // 備註
    public createDate?: Date, // 建立日期
    public createUser?: string, // 建立人員
    public updateDate?: Date | null, // 更新日期
    public updateUser?: string | null, // 更新人員
    public trueDate?: Date, // 本次請撥日期
    public dsts?: string | null, // 資料狀態
    public confOut?: number | null, // 預定執行單位資料確認
    public uIp?: string | null // 更新IP
  ) {}
}
