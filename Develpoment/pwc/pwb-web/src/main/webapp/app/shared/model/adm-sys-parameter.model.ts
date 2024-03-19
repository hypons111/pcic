export interface IAdmSysParameter {
  sysName?: string;
  sysType?: string;
  sysValue?: string;
  descript?: string;
  status?: string;
  updateUser?: string;
  updateTime?: Date;
}

export class AdmSysParameter implements IAdmSysParameter {
  constructor(
    public sysName?: string,
    public sysType?: string,
    public sysValue?: string,
    public descript?: string,
    public status?: string,
    public updateUser?: string,
    public updateTime?: Date
  ) {}

  public setDefault() {
    return new AdmSysParameter('', '', '', '', '', '', null);
  }
}
