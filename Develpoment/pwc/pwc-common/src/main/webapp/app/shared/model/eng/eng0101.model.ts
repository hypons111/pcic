import { IEngEngrCertificate } from "./eng-engr-certificate.model";
import { IEngEbillingCheck } from "./eng-ebilling-check.model"
import { IEngEbillingCheckDtl } from './eng-ebilling-check-dtl.model';

export interface IEng0101 {
  engEngrCertificateDTO?: IEngEngrCertificate;
  engEbillingCheckDTO?: IEngEbillingCheck | null;
  engEbillingCheckDtlDTOList?: IEngEbillingCheckDtl[] | null;
}

export class Eng0101 implements IEng0101 {
  constructor(
    public engEngrCertificateDTO?: IEngEngrCertificate,
    public engEbillingCheckDTO?: IEngEbillingCheck | null,
    public engEbillingCheckDtlDTOList?: IEngEbillingCheckDtl[] | null,
  ) { }
}