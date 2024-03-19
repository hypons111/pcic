export interface IAdmAccountContact {
  userId?: string;
  pccOrgId?: string;
  unitId?: string;
  compIdno?: string;
  no?: bigint;
  userPadHash?: string;
  status?: string;
  auditStatus?: string;
  userType?: string;
  updateUser?: string;
  padUpdateTime?: Date;
  updateTime?: Date;
  minguoUpdateTimeString?: string;
  roleIdList?: string;
  roleIdListDisplay?: Array<string>;

  chName?: string;
  chNameRome?: string;
  title?: string;
  birthday?: Date;
  idno?: string;
  sex?: string;
  workTel?: string;
  areaNumber?: string;
  telNumber?: string;
  extendNumber?: string;
  mobileTel?: string;
  mobileTel4?: string;
  mobileTel6?: string;
  email?: string;
  homeAddrZip?: string;
  homeAddrCity?: string;
  homeAddrTown?: string;
  homeAddrOther?: string;
  workAddrZip?: string;
  workAddrCity?: string;
  workAddrTown?: string;
  workAddrOther?: string;
}

export class AdmAccountContact implements IAdmAccountContact {
  constructor(
    public userId?: string,
    public pccOrgId?: string,
    public unitId?: string,
    public compIdno?: string,
    public no?: bigint,
    public userPadHash?: string,
    public status?: string,
    public auditStatus?: string,
    public userType?: string,
    public updateUser?: string,
    public padUpdateTime?: Date,
    public updateTime?: Date,
    public minguoUpdateTimeString?: string,
    public roleIdList?: string,
    public roleIdListDisplay?: Array<string>,

    public chName?: string,
    public chNameRome?: string,
    public title?: string,
    public birthday?: Date,
    public idno?: string,
    public sex?: string,
    public workTel?: string,
    public areaNumber?: string,
    public telNumber?: string,
    public extendNumber?: string,
    public mobileTel?: string,
    public mobileTel4?: string,
    public mobileTel6?: string,
    public email?: string,
    public homeAddrZip?: string,
    public homeAddrCity?: string,
    public homeAddrTown?: string,
    public homeAddrOther?: string,
    public workAddrZip?: string,
    public workAddrCity?: string,
    public workAddrTown?: string,
    public workAddrOther?: string
  ) { }
}
