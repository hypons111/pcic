export interface IAdmFunctionUsedRecord {
  id?: number;
  sysCategory?: string;
  jwtToken?: string;
  functionId?: string;
  functionExtendId?: string;
  tableName?: string;
  tableKey?: string;
  updateValue?: string;
  recordContent?: string | null;
  createTime?: Date;
  createUserSys?: string;
}

export class AdmFunctionUsedRecord implements IAdmFunctionUsedRecord {
  constructor(
    public id?: number,
    public sysCategory?: string,
    public jwtToken?: string,
    public functionId?: string,
    public functionExtendId?: string,
    public tableName?: string,
    public tableKey?: string,
    public updateValue?: string,
    public recordContent?: string | null,
    public createTime?: Date,
    public createUserSys?: string,
  ) {}
}
