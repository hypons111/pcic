import { AdmFunction } from '@/shared/model/adm-function.model';

export interface IAdmRole {
  roleId?: string;
  roleName?: string;
  roleDescript?: string;
  businessCategory?: string;
  updateUser?: string;
  updateTime?: Date;
  roleFunctionList?: string;
  roleFunctionListDisplay?: Array<string>;
  admFunctionDTOs?: AdmFunction[];
}

export class AdmRole implements IAdmRole {
  constructor(
    public roleId?: string,
    public roleName?: string,
    public roleDescript?: string,
    public businessCategory?: string,
    public updateUser?: string,
    public updateTime?: Date,
    public roleFunctionList?: string,
    public roleFunctionListDisplay?: Array<string>,
    public admFunctionDTOs?: AdmFunction[]
  ) {}

  setDefault() {
    return new AdmRole('', '', '', '', '', new Date(), '', [], []);
  }
}
