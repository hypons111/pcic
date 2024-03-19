export enum RoleEnum {
  USER = '使用者',
  ADMIN = '管理員',
  // Tim加的
  COMP = '顧問公司',
}

export enum FormStatusEnum {
  READONLY = '檢視',
  EDIT = '編輯',
  CREATE = '1',       // 新增
  CHANGE_APPLY = '2', // 變更申請
  REMAKE = '3',       // 新增另案
  PATCH = '4',        // 補登
  REVIEW = '審核'
}

export enum MemberTypeEnum {
  ENGR = '0',           // 技師
  CONTRACTOR = '1',     // 承辦人
  MASTER = '2',         // 公司負責人
  REPRESENTATIVE = '3'  // 其它團體代表人
}

export enum CompTypeEnum {
  OFFICE = '0',             // 事務所
  COMPANY = '1',            // 顧問公司
  GUILD = '2',              // 公會
  REGIONAL_AUTHORITY = '3', // 區域主管機關
  GOV_ORGANIZATION = '4',   // 政府組織 
  FEDERATION = '5',         // 全聯會
  COMP_GUILD = '6',         // 顧問公會
  DISCIPLINE_UNIT = '7',    // 懲戒移送單位
  OTHER_GROUPS = '8',       // 其他團體
  ARCHITECT_OFFICE = '9',   // 建築師事務所
}

export enum EbillingChecksDocTypeEnum {
  ENGR_CERTIFICATE = '1', // 技師證書
  ENGR_LICENSE = '2',     // 技師執業執照
  CST_LICENSE = '3',      // 顧問公司許可登記
}

export enum ReviewCaseTypeEnum {
  ENGR_CERTIFICATE = '1', // 技師證書
  ENGR_LICENSE = '2',     // 技師執業執照
  CST_LICENSE = '3',      // 顧問公司許可登記
  ENGR_DISCIPLINE = '4',  // 技師懲戒
  COURSE = '5',           // 課程主檔
  ENGRCLASS_RECORD = '6', // 技師上課紀錄
  ENGR_SUBJECTPOINT = '7',// 技師書面積分審查
  COMP_CHECK = '8',       // 事務所/公司查核
  YEARREPORT = '9',       // 年度業務報告
}

export enum StatusEnum {
  TEMP = '0',       // 暫存
  RETURN = '1',     // 核退
  REVIEW1 = '2',    // 待審核(一關)
  REVIEW2_1 = '3',  // 待審核(二關)
  REVIEW2_2 = '4',  // 審核中(二關)
  CLOSE = '5',      // 結案
  DISCARD = '6',    // 作廢
}

export enum ReviewStatusEnum {
  APPLY = '1',    // 申請
  REAPPLY = '2',  // 重新申請
  APPROVE = '3',  // 核准
  RETURN = '4',   // 核退
  CANCEL = '5',   // 取消核准
  DISCARD = '6',  // 作廢
}

export enum ApplyTypeEnum {
  PAPER_APPLY = '0',   // 紙本申請
  ONLINE_APPLY = '1',  // 線上申請
}

export enum ApplyItemEnum {
  NEW_APPLY = '0',  // 新申請
  APPLY_EN = '1',   // 申請英文證明書
  REISSUE = '2',    // 補發
  ABOLISH = '4',    // 廢止
}

export enum ReceiveTypeEnum {
  PERMANENT = '0',  // 戶籍地址
  CONTACT = '1',    // 通訊地址
  COMPANY = '2',    // 公司地址
  SELF = '3',       // 自取
  OTHER = '4',      // 其他地址
}

export enum SexEnum {
  FEMALE = '0',   // 女
  MALE = '1',  // 男
}
