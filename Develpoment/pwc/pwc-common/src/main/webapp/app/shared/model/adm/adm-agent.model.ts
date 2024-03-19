export interface IAdmAgent {
  orgId?: string | null;
  pccOrgId?: string | null;
  orgName?: string | null;
  orgNameEn?: string | null;
  addr?: string | null;
  tel?: string | null;
  fax?: string | null;
  descript?: string | null;
  adminOrgId?: string | null;
  orgLevel?: string | null;
  cutMark?: string | null;
  oldOrgId?: string | null;
  oldOrgName?: string | null;
  updateUser?: string | null;
  updateTime?: string | null;
}

export class AdmAgent implements IAdmAgent {
  constructor(
    public orgId?: string | null,
    public pccOrgId?: string | null,
    public orgName?: string | null,
    public orgNameEn?: string | null,
    public addr?: string | null,
    public tel?: string | null,
    public fax?: string | null,
    public descript?: string | null,
    public adminOrgId?: string | null,
    public orgLevel?: string | null,
    public cutMark?: string | null,
    public oldOrgId?: string | null,
    public oldOrgName?: string | null,
    public updateUser?: string | null,
    public updateTime?: string | null
  ) {}
}
