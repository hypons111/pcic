import { IEngEngrCertificate } from "./eng-engr-certificate.model";
import { IEngReviewLog } from "./eng-review-log.model";

export interface IEngReviewAndFunction {
  engReviewLogDTO?: IEngReviewLog;
  engEngrCertificateDTO?: IEngEngrCertificate;
}

export class EngReviewAndFunction implements IEngReviewAndFunction {
  constructor(
    public engReviewLogDTO?: IEngReviewLog,
    public engEngrCertificateDTO?: IEngEngrCertificate
  ){}
}