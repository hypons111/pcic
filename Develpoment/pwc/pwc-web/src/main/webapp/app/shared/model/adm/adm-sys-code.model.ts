export interface IAdmSysCode {
  moduleType?: string;
  dataType?: string;
  dataKey?: string;
  sortNo?: number;
  value?: string;
  codeDescript?: string;
  enCodeDescript?: string;
  remark?: string;
  isValid?: boolean;
  validFromDate?: Date;
  isModify?: boolean;
  attr1?: string;
  attr2?: string;
  createUser?: string;
  createTime?: Date;
  updateUser?: string;
  updateTime?: Date;
}

export class AdmSysCode implements IAdmSysCode {
  constructor(
    public moduleType?: string,
    public dataType?: string,
    public dataKey?: string,
    public sortNo?: number,
    public value?: string,
    public codeDescript?: string,
    public enCodeDescript?: string,
    public remark?: string,
    public isValid?: boolean,
    public validFromDate?: Date,
    public isModify?: boolean,
    public attr1?: string,
    public attr2?: string,
    public createUser?: string,
    public createTime?: Date,
    public updateUser?: string,
    public updateTime?: Date
  ) { }
}
