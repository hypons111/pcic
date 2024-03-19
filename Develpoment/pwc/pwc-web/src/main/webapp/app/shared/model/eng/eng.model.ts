import { IEngEngrCertificate, EngEngrCertificate } from "./eng-engr-certificate.model";
import { IEngEbillingCheck, EngEbillingCheck } from "./eng-ebilling-check.model"

export interface IEng {
  engEngrCertificateDTO?: IEngEngrCertificate;
  engEbillingCheckDTO?: IEngEbillingCheck;
}

export class Eng implements IEng {
  constructor(
    public engEngrCertificateDTO?: IEngEngrCertificate,
    public engEbillingCheckDTO?: IEngEbillingCheck
  ) {
    this.engEngrCertificateDTO = new EngEngrCertificate();
    this.engEbillingCheckDTO = new EngEbillingCheck();
  }
}