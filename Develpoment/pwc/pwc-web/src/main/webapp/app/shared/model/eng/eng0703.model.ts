import { IEngManage } from './eng-manage.model';
import { IEngEngrCertificate } from "./eng-engr-certificate.model";
import { IEngEbillingCheck } from "./eng-ebilling-check.model"
import { IEngEbillingCheckDtl } from './eng-ebilling-check-dtl.model';

export interface IEng0703 {
  engManageDTO?: IEngManage
  // engEngrCertificateDTO?: IEngEngrCertificate;
  // engEbillingCheckDTO?: IEngEbillingCheck | null;
  // engEbillingCheckDtlDTOList?: IEngEbillingCheckDtl[] | null;
}

export class Eng0703 implements IEng0703 {
  constructor(
    public engManageDTO?: IEngManage,
    // public engEngrCertificateDTO?: IEngEngrCertificate,
    // public engEbillingCheckDTO?: IEngEbillingCheck | null,
    // public engEbillingCheckDtlDTOList?: IEngEbillingCheckDtl[] | null,
  ) { }
}