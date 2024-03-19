export interface IBidPrjMilestone {
  wkut?: string; // 標案編號
  prjno?: string; //填報日期
  applyDate?: Date; // 填報日期
  milestoneNo?: string; //序號
  milestoneName?: string | null; //里程碑名稱
  milestoneType?: string | null; // 類別
  milestoneStatus?: string | null; //完成狀態
  estimateDate?: Date | null; //預定完成日期
  actualDate?: Date | null; //實際完成日期
  remark?: string | null; //屆期未完原因目前辦理情形
  createDate?: Date; //建立日期
  createUser?: string; //建立人員
  updateDate?: Date | null; //更新日期
  updateUser?: string | null; //更新人員
}
export class BidPrjMilestone implements IBidPrjMilestone {
  constructor(
    public wkut?: string, // 標案編號
    public prjno?: string, //填報日期
    public applyDate?: Date, // 填報日期
    public milestoneNo?: string, //序號
    public milestoneName?: string | null, //里程碑名稱
    public milestoneType?: string | null, // 類別
    public milestoneStatus?: string | null, //完成狀態
    public estimateDate?: Date | null, //預定完成日期
    public actualDate?: Date | null, //實際完成日期
    public remark?: string | null, //屆期未完原因目前辦理情形
    public createDate?: Date, //建立日期
    public createUser?: string, //建立人員
    public updateDate?: Date | null, //更新日期
    public updateUser?: string | null //更新人員
  ) {}
}
