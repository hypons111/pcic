export interface IAdmSysCodeCriteria {
  moduleType?: string | null;
  dataType?: string | null;
  dataKey?: string | null;
  isValid?: string | null;
  isModify?: string | null;
}
export class AdmSysCodeCriteria implements IAdmSysCodeCriteria {
  constructor(
    public moduleType?: string | null,
    public dataType?: string | null,
    public dataKey?: string | null,
    public isValid?: string | null,
    public isModify?: string | null
  ) {}
}
