export interface IAdmAgent {
  orgId?: string;
  pccOrgId?: string;
  orgName?: string;
  orgNameEn?: string;
  addr?: string;
  tel?: string;
  fax?: string;
  descript?: string;
  adminOrgId?: string;
  orgLevel?: number;
  cutMark?: string;
  oldOrgId?: string;
  oldOrgName?: string;
  updateUser?: string;
  updateTime?: Date;
}

export class AdmAgent implements IAdmAgent {
  constructor(
    public orgId?: string,
    public pccOrgId?: string,
    public orgName?: string,
    public orgNameEn?: string,
    public addr?: string,
    public tel?: string,
    public fax?: string,
    public descript?: string,
    public adminOrgId?: string,
    public orgLevel?: number,
    public cutMark?: string,
    public oldOrgId?: string,
    public oldOrgName?: string,
    public updateUser?: string,
    public updateTime?: Date
  ) {}

  public setDefault() {
    return new AdmAgent('', '', '', '', '', '', '', '', '', 0, '', '', '', 'SYS', new Date());
  }
}
