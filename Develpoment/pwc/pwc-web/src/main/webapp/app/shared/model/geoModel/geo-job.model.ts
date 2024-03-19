export interface IGeoJob {
  companyName?: string | null;
  id?: number;
  geoJobId?: number | null;
  salaryRange?: string | null;
  workPlaceList?: string | null;
  workPlaceDesc?: string | null;
  otherComment?: string | null;
  publishDateStart?: Date | null;
  publishDateEnd?: Date | null;
  publishStatus?: string | null;
  jobTitle?: string | null;
  isApprove?: string | null;
  jobType?: string | null;
  type1List?: string | null;
  type2List?: string | null;
  type3List?: string | null;
  personCount?: number | null;
  rejectReason?: string | null;
  isPublic?: string | null;
  mobileTel?: string | null;
  contactTel?: string | null;
  contactPerson?: string | null;
  createUser?: string | null;
  createTime?: Date | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  transferDate?: Date | null;
  transferFrom?: string | null;
  jobKind?: string | null;

  type1ListShow?: string[] | null;
  type2ListShow?: string[] | null;
  type3ListShow?: string[] | null;
  workPlaceListShow?: string[] | null;
}

export class GeoJob implements IGeoJob {
  constructor(
    public companyName?: string | null,
    public id?: number,
    public geoJobId?: number | null,
    public salaryRange?: string | null,
    public workPlaceList?: string | null,
    public workPlaceDesc?: string | null,
    public otherComment?: string | null,
    public publishDateStart?: Date | null,
    public publishDateEnd?: Date | null,
    public publishStatus?: string | null,
    public jobTitle?: string | null,
    public isApprove?: string | null,
    public jobType?: string | null,
    public type1List?: string | null,
    public type2List?: string | null,
    public type3List?: string | null,
    public personCount?: number | null,
    public rejectReason?: string | null,
    public isPublic?: string | null,
    public mobileTel?: string | null,
    public contactTel?: string | null,
    public contactPerson?: string | null,
    public createUser?: string | null,
    public createTime?: Date | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public transferDate?: Date | null,
    public transferFrom?: string | null,
    public jobKind?: string | null,

    public type1ListShow?: string[] | null,
    public type2ListShow?: string[] | null,
    public type3ListShow?: string[] | null,
    public workPlaceListShow?: string[] | null
  ) {
    this.type1ListShow = [];
    this.type2ListShow = [];
    this.type3ListShow = [];
    this.workPlaceListShow = [];
  }
}
