export interface IEngCaseTotalDTO {
  engCaseId?: string | null;
  caseNo?: string | null;
  tenderNo?: string | null;
  awardNo?: string | null;
  caseType?: string | null;
  caseName?: string;
  orgId?: string | null;
  orgName?: string | null;
  execOrgId?: string | null;
  execOrgName?: string | null;
  contactPerson?: string | null;
  contactTel?: string | null;
  entrustItem?: string | null;
  tenderDate?: Date | null;

  tenderDateStart?: Date | null;
  tenderDateEnd?: Date | null;

  tenderPrice?: number | null;
  execDateStart?: Date;
  execDateEnd?: Date;
  projectType?: string;
  recordRanges?: string[] | null;
  recordRangeList?: string | null;
  recordRangeOther?: string | null;
  recordItems?: string[] | null;
  recordItemsList?: string | null;
  recordItemsOther?: string | null;
  remark?: string | null;
  itemNo?: number | null;
  compId?: string | null;
  compIdno?: string | null;
  compName?: string | null;
  compLicenseNo?: string | null;
  engrId?: string | null;
  engrName?: string | null;
  engrNameRome?: string | null;
  engEngrCertificateIdList?: string | null;
  docNo?: string | null;
  closeDate?: Date | null;
  status?: string | null;
  expectDate?: Date | null;
  realTime?: string | null;
  recordContent?: string | null;
  recordComment?: string | null;
  signDate?: Date | null;
  noSignItem?: number;

  updateTime?: Date | null;
  updateUser?: string | null;
  createTime?: Date | null;
  createUser?: string | null;
  transferFrom?: string | null;
  transferTime?: Date | null;


}

export class EngCaseTotalDTO implements IEngCaseTotalDTO {
  public engCaseId?: string | null;
  public caseNo?: string | null;
  public tenderNo?: string | null;
  public awardNo?: string | null;
  public caseType?: string | null;
  public caseName?: string;
  public orgId?: string | null;
  public orgName?: string | null;
  public execOrgId?: string | null;
  public execOrgName?: string | null;
  public contactPerson?: string | null;
  public contactTel?: string | null;
  public entrustItem?: string | null;
  public tenderDate?: Date | null;

  public tenderDateStart?: Date | null;
  public tenderDateEnd?: Date | null;
  public tenderPrice?: number | null;
  public execDateStart?: Date;
  public execDateEnd?: Date;
  public projectType?: string;
  public recordRanges?: string[] | null;
  public recordRangeList?: string | null;
  public recordRangeOther?: string | null;
  public recordItems?: string[] | null;
  public recordItemsList?: string | null;
  public recordItemsOther?: string | null;
  public remark?: string | null;
  public itemNo?: number | null;
  public compId?: string | null;
  public compIdno?: string | null;
  public compName?: string | null;
  public compLicenseNo?: string | null;
  public engrId?: string | null;
  public engrName?: string | null;
  public engrNameRome?: string | null;
  public engEngrCertificateIdList?: string | null;
  public docNo?: string | null;
  public closeDate?: Date | null;
  public status?: string | null;
  public expectDate?: Date | null;
  public realTime?: string | null;
  public recordContent?: string | null;
  public recordComment?: string | null;
  public signDate?: Date | null;
  public noSignItem?: number = 0;

  public updateTime?: Date | null;
  public updateUser?: string | null;
  public createTime?: Date | null;
  public createUser?: string | null;
  public transferFrom?: string | null;
  public transferTime?: Date | null;

  public compList?: Array<Comp>;
  public engrList?: Array<Engr>;
}

export class Comp {
  public compId?: string | null;
  public compName?: string | null;
} 

export class Engr {
  public engrName?: string | null;
  public subject?: string | null;
  public recordRangeList?: string | null;
  public recordItemsList?: string | null;
  public execDateStart?: Date | null;
  public execDateEnd?: Date | null;
  public signDate?: Date | null;
  public closeDate?: Date | null;
  public updateTime?: Date | null;
  public recordItemsOther?: string | null;
  public recordRangeOther?: string | null;

} 

