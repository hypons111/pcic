export interface IEngMember {
  engMemberId?: string;
  type?: string;
  no?: string | null;
  chName?: string | null;
  enName?: string | null;
  idno?: string | null;
  birthDate?: Date | null;
  sex?: string | null;
  tel1?: string | null;
  tel2?: string | null;
  tel3?: string | null;
  fax?: string | null;
  permanentAddrZip?: string | null;
  permanentAddrCity?: string | null;
  permanentAddrTown?: string | null;
  permanentAddrOther?: string | null;
  contactAddrZip?: string | null;
  contactAddrCity?: string | null;
  contactAddrTown?: string | null;
  contactAddrOther?: string | null;
  email1?: string | null;
  email2?: string | null;
  isGovMember?: string | null;
  isEngr?: string | null;
  identityType?: string | null;
  addrForeign?: string | null;
  userId?: string | null;
  jobTypeList?: string | null;
  licenseId?: string | null;
  licenseNo?: string | null;
  compId?: string | null;
  subjectList?: string | null;
  execStatus?: string | null;
  remark?: string | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;
}

export class EngMember implements IEngMember {
  constructor(
    public engMemberId?: string,
    public type?: string,
    public no?: string | null,
    public chName?: string | null,
    public enName?: string | null,
    public idno?: string | null,
    public birthDate?: Date | null,
    public sex?: string | null,
    public tel1?: string | null,
    public tel2?: string | null,
    public tel3?: string | null,
    public fax?: string | null,
    public permanentAddrZip?: string | null,
    public permanentAddrCity?: string | null,
    public permanentAddrTown?: string | null,
    public permanentAddrOther?: string | null,
    public contactAddrZip?: string | null,
    public contactAddrCity?: string | null,
    public contactAddrTown?: string | null,
    public contactAddrOther?: string | null,
    public email1?: string | null,
    public email2?: string | null,
    public isGovMember?: string | null,
    public isEngr?: string | null,
    public identityType?: string | null,
    public addrForeign?: string | null,
    public userId?: string | null,
    public jobTypeList?: string | null,
    public licenseId?: string | null,
    public licenseNo?: string | null,
    public compId?: string | null,
    public subjectList?: string | null,
    public execStatus?: string | null,
    public remark?: string | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null
  ) { }
}
