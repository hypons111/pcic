import { IEngEbillingCheckDtl } from "./eng-ebilling-check-dtl.model";
import { IEngEbillingCheck } from "./eng-ebilling-check.model";
import { IEngEngrLicense } from "./eng-engr-license.model";

export interface IEng0102 {
  engEngrLicenseDTO?: IEngEngrLicense;
  engEbillingCheckDTO?: IEngEbillingCheck | null;
  engEbillingCheckDtlDTOList?: IEngEbillingCheckDtl[] | null;
}

export class Eng0102 implements IEng0102 {
  constructor(
    public engEngrLicenseDTO?: IEngEngrLicense,
    public engEbillingCheckDTO?: IEngEbillingCheck | null,
    public engEbillingCheckDtlDTOList?: IEngEbillingCheckDtl[] | null,
  ) { }
}
