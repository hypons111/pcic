export interface IBidPrjSafe {
  wkut?: string; // 執行單位
  prjno?: string; // 標案編號
  sdate?: Date; // 發生日期
  sdateNew?: Date; // 發生日期新值
  ccnt?: string | null; // 罹災人員隸屬單位
  respons?: string | null; // 可歸責廠商1
  respons2?: string | null; // 可歸責廠商2
  ctUnit?: string | null; // 管轄單位
  stype?: string | null; // 災害類型
  media?: string | null; // 媒介物
  diedNum?: number | null; //死亡人數
  hurtNum?: number | null; // 受傷人數
  disasterScript?: string | null; //罹災時災情
  wdayed?: number | null; // 發生前累計工期
  remark?: string | null; // 備註
  peDate?: Date | null; // 提報勞動部日期
  prmNo?: string; // 提報文號
  mrmno?: string | null; // 核定文號
  meDate?: Date | null; // 勞動部核定日期
  createDate?: Date | null; //新增日期
  createUser?: string | null; //新增人員
  updateDate?: Date | null; //更新日期
  updateUser?: string | null; //更新人員
  uIp?: string | null; //更新IP
  confOut?: string | null; // 預定執行單位資料
  ctrNo?: string | null; // 管控編號
  ccut?: string | null; // 發生廠商
}

export class BidPrjSafe implements IBidPrjSafe {
  constructor(
    public wkut?: string, // 執行單位
    public prjno?: string, // 標案編號
    public sdate?: Date, // 發生日期
    public sdateNew?: Date, // 發生日期新值
    public ccnt?: string | null, // 罹災人員隸屬單位
    public respons?: string | null, // 可歸責廠商1
    public respons2?: string | null, // 可歸責廠商2
    public ctUnit?: string | null, // 管轄單位
    public stype?: string | null, // 災害類型
    public media?: string | null, // 媒介物
    public diedNum?: number | null, //死亡人數
    public hurtNum?: number | null, // 受傷人數
    public disasterScript?: string | null, //罹災時災情
    public wdayed?: number | null, // 發生前累計工期
    public remark?: string | null, // 備註
    public peDate?: Date | null, // 提報勞動部日期
    public prmNo?: string, // 提報文號
    public mrmno?: string | null, // 核定文號
    public meDate?: Date | null, // 勞動部核定日期
    public createDate?: Date | null, //新增日期
    public createUser?: string | null, //新增人員
    public updateDate?: Date | null, //更新日期
    public updateUser?: string | null, //更新人員
    public uIp?: string | null, //更新IP
    public confOut?: string | null, // 預定執行單位資料
    public ctrNo?: string | null, // 管控編號
    public ccut?: string | null // 發生廠商
  ) {}
}
