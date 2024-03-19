export interface IAdmFunctionExtend {
  functionExtendId?: string;
  functionExtendName?: string;
  functionExtendDescript?: string;
  updateUser?: string;
  updateTime?: Date;
}

export class AdmFunctionExtend implements IAdmFunctionExtend {
  constructor(
    public functionExtendId?: string,
    public functionExtendName?: string,
    public functionExtendDescript?: string,
    public updateUser?: string,
    public updateTime?: Date
  ) {}

  public setDefault() {
    return new AdmFunctionExtend('', '', '', '', new Date());
  }
}
