export interface IBidPrjStc {
  wkut?: string; // 執行單位
  prjno?: string; // 標案編號
  checkDate?: Date; // 檢核日期
  admFileId?: number; // ADM_FILE資料表鍵值
  file?: File;
  yndA01?: string; //項目1之檢核結果
  resonA01?: string; //項目1之檢討結果及原因備案
  dateA01?: Date; //項目1之預定完成日期
  yndA02?: string; //項目2之檢核結果
  resonA02?: string; //項目2之檢討結果及原因備案
  dateA02?: Date; //項目2之預定完成日期
  yndA03?: string; //項目3之檢核結果
  resonA03?: string; //項目3之檢討結果及原因備案
  dateA03?: Date; //項目3之預定完成日期
  yndA04?: string; //項目4之檢核結果
  resonA04?: string; //項目4之檢討結果及原因備案
  dateA04?: Date; //項目4之預定完成日期
  yndA05?: string; //項目5之檢核結果
  resonA05?: string; //項目5之檢討結果及原因備案
  dateA05?: Date; //項目5之預定完成日期
  yndA06?: string; //項目6之檢核結果
  resonA06?: string; //項目6之檢討結果及原因備案
  dateA06?: Date; //項目6之預定完成日期
  yndA07?: string; //項目7之檢核結果
  resonA07?: string; //項目7之檢討結果及原因備案
  dateA07?: Date; //項目7之預定完成日期
  yndA08?: string; //項目8之檢核結果
  resonA08?: string; //項目8之檢討結果及原因備案
  dateA08?: Date; //項目8之預定完成日期
  yndA09?: string; //項目9之檢核結果
  resonA09?: string; //項目9之檢討結果及原因備案
  dateA09?: Date; //項目9之預定完成日期
  yndA10?: string; //項目10之檢核結果
  resonA10?: string; //項目10之檢討結果及原因備案
  dateA10?: Date; //項目10之預定完成日期
  yndA11?: string; //項目11之檢核結果
  resonA11?: string; //項目11之檢討結果及原因備案
  dateA11?: Date; //項目11之預定完成日期
  yndA12?: string; //項目12之檢核結果
  resonA12?: string; //項目12之檢討結果及原因備案
  dateA12?: Date; //項目12之預定完成日期
  yndA13?: string; //項目13之檢核結果
  resonA13?: string; //項目13之檢討結果及原因備案
  dateA13?: Date; //項目13之預定完成日期
  yndA14?: string; //項目14之檢核結果
  resonA14?: string; //項目14之檢討結果及原因備案
  dateA14?: Date; //項目14之預定完成日期
  yndA15?: string; //項目15之檢核結果
  resonA15?: string; //項目15之檢討結果及原因備案
  dateA15?: Date; //項目15之預定完成日期
  createDate?: Date; //建立日期
  createUser?: string; //建立人員
  updateDate?: Date; //更新日期
  updateUser?: string; //更新人員
}

export class BidPrjStc implements IBidPrjStc {
  constructor(
    public wkut?: string, // 執行單位
    public prjno?: string, // 標案編號
    public checkDate?: Date, // 檢核日期
    public admFileId?: number, // ADM_FILE資料表鍵值
    public yndA01?: string, //項目1之檢核結果
    public resonA01?: string, //項目1之檢討結果及原因備案
    public dateA01?: Date, //項目1之預定完成日期
    public yndA02?: string, //項目2之檢核結果
    public resonA02?: string, //項目2之檢討結果及原因備案
    public dateA02?: Date, //項目2之預定完成日期
    public yndA03?: string, //項目3之檢核結果
    public resonA03?: string, //項目3之檢討結果及原因備案
    public dateA03?: Date, //項目3之預定完成日期
    public yndA04?: string, //項目4之檢核結果
    public resonA04?: string, //項目4之檢討結果及原因備案
    public dateA04?: Date, //項目4之預定完成日期
    public yndA05?: string, //項目5之檢核結果
    public resonA05?: string, //項目5之檢討結果及原因備案
    public dateA05?: Date, //項目5之預定完成日期
    public yndA06?: string, //項目6之檢核結果
    public resonA06?: string, //項目6之檢討結果及原因備案
    public dateA06?: Date, //項目6之預定完成日期
    public yndA07?: string, //項目7之檢核結果
    public resonA07?: string, //項目7之檢討結果及原因備案
    public dateA07?: Date, //項目7之預定完成日期
    public yndA08?: string, //項目8之檢核結果
    public resonA08?: string, //項目8之檢討結果及原因備案
    public dateA08?: Date, //項目8之預定完成日期
    public yndA09?: string, //項目9之檢核結果
    public resonA09?: string, //項目9之檢討結果及原因備案
    public dateA09?: Date, //項目9之預定完成日期
    public yndA10?: string, //項目10之檢核結果
    public resonA10?: string, //項目10之檢討結果及原因備案
    public dateA10?: Date, //項目10之預定完成日期
    public yndA11?: string, //項目11之檢核結果
    public resonA11?: string, //項目11之檢討結果及原因備案
    public dateA11?: Date, //項目11之預定完成日期
    public yndA12?: string, //項目12之檢核結果
    public resonA12?: string, //項目12之檢討結果及原因備案
    public dateA12?: Date, //項目12之預定完成日期
    public yndA13?: string, //項目13之檢核結果
    public resonA13?: string, //項目13之檢討結果及原因備案
    public dateA13?: Date, //項目13之預定完成日期
    public yndA14?: string, //項目14之檢核結果
    public resonA14?: string, //項目14之檢討結果及原因備案
    public dateA14?: Date, //項目14之預定完成日期
    public yndA15?: string, //項目15之檢核結果
    public resonA15?: string, //項目15之檢討結果及原因備案
    public dateA15?: Date, //項目15之預定完成日期
    public createDate?: Date, //建立日期
    public createUser?: string, //建立人員
    public updateDate?: Date, //更新日期
    public updateUser?: string //更新人員
  ) {}
}
