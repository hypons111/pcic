export interface IGeoMember {
  geoMemberId?: number;
  userName?: string | null;
  workTel?: string | null;
  idno?: string | null;
  userId?: string | null;
  applyDate?: Date | null;
  jobDemand?: string | null;
  sex?: string | null;
  birthday?: Date | null;
  email?: string | null;
  homeTel?: string | null;
  fax?: string | null;
  mobileTel?: string | null;
  examineeDegree?: string | null;
  highestDegree?: string | null;
  firstexamDate?: Date | null;
  firstexamCertNo?: string | null;
  firstexamSubject?: string | null;
  isApprove?: boolean | null;
  jobType?: string | null;
  salaryRange?: string | null;
  workPlace?: string | null;
  remark?: string | null;
  isEnable?: boolean | null;
  ranking?: string | null;
  serialNo?: string | null;
  updateTime?: Date | null;
  jobKind?: string | null;
  addrZip?: string | null;
  addrCity?: string | null;
  addrTown?: string | null;
  addrOther?: string | null;
}

export class GeoMember implements IGeoMember {
  constructor(
    public geoMemberId?: number,
    public userName?: string | null,
    public workTel?: string | null,
    public contactAddr?: string | null,
    public idno?: string | null,
    public userId?: string | null,
    public applyDate?: Date | null,
    public jobDemand?: string | null,
    public sex?: string | null,
    public birthday?: Date | null,
    public email?: string | null,
    public homeTel?: string | null,
    public fax?: string | null,
    public mobileTel?: string | null,
    public examineeDegree?: string | null,
    public highestDegree?: string | null,
    public firstexamDate?: Date | null,
    public firstexamCertNo?: string | null,
    public firstexamSubject?: string | null,
    public isApprove?: boolean | null,
    public jobType?: string | null,
    public salaryRange?: string | null,
    public workPlace?: string | null,
    public remark?: string | null,
    public isEnable?: boolean | null,
    public ranking?: string | null,
    public serialNo?: string | null,
    public jobKind?: string | null,
    public addrZip?: string | null,
    public addrCity?: string | null,
    public addrTown?: string | null,
    public addrOther?: string | null
  ) {
    this.isApprove = this.isApprove ?? false;
    this.isEnable = this.isEnable ?? false;
  }
}
