export interface IBidGovPrecheck {
  wkut?: string;
  prjno?: string;
  checkDate?: Date;
  checkDateEnd?: Date | null;
  checkUnit?: string | null;
  checkSubunit?: string | null;
  checker1?: string | null;
  checkLeader?: string | null;
  checkMember?: string | null;
  dataStatus?: string | null;
  method?: string | null;
  checkPurp?: string | null;
  checkerTel?: string | null;
  can?: string | null;
  column1?: string | null;
  createDate?: Date | null;
  createUser?: string | null;
  updateDate?: Date | null;
  updateUser?: string | null;
}

export class BidGovPrecheck implements IBidGovPrecheck {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public checkDate?: Date,
    public checkDateEnd?: Date | null,
    public checkUnit?: string | null,
    public checkSubunit?: string | null,
    public checker1?: string | null,
    public checkLeader?: string | null,
    public checkMember?: string | null,
    public dataStatus?: string | null,
    public method?: string | null,
    public checkPurp?: string | null,
    public checkerTel?: string | null,
    public can?: string | null,
    public column1?: string | null,
    public createDate?: Date | null,
    public createUser?: string | null,
    public updateDate?: Date | null,
    public updateUser?: string | null
  ) {}
}
