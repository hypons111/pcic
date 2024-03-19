import { AdmFunctionExtend } from '@/shared/model/adm-function-extend.model';

export interface IAdmFunction {
  functionId?: string;
  functionName?: string;
  functionDescript?: string;
  functionPath?: string;
  functionCategory?: string;
  sysCategory?: string;
  sortNo?: number;
  masterFunctionId?: string;
  status?: string;
  updateUser?: string;
  updateTime?: Date;
  roleFunctionExtendList?: string;
  roleFunctionExtendListCheckBox?: AdmFunctionExtend[];
}

export class AdmFunction implements IAdmFunction {
  constructor(
    public functionId?: string,
    public functionName?: string,
    public functionDescript?: string,
    public functionPath?: string,
    public functionCategory?: string,
    public sysCategory?: string,
    public sortNo?: number,
    public masterFunctionId?: string,
    public status?: string,
    public updateUser?: string,
    public updateTime?: Date,
    public roleFunctionExtendList?: string,
    public roleFunctionExtendListCheckBox?: AdmFunctionExtend[]
  ) {}

  public setDefault() {
    return new AdmFunction('', '', '', '', '', '', 99, '', '', '', new Date(), '', []);
  }
}
