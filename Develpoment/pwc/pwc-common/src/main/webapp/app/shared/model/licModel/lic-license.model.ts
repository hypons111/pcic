export interface IlicLicense {
  licenseMainId?: string;
  projectName?: string;
  licenseCategory?: string;
  licenseName?: string;
  reviewOrg?: string;
  expiredDate?: Date;
  expectDate?: Date;
  realDate?: Date;
  procDate?: string;
  handlingSituation?: string;
  procStatus?: string;
  procTimes?: string;
  reviewRemark?: string;
}

export class LicLicense implements IlicLicense {
  constructor(
    public licenseMainId?: string,
    public projectName?: string,
    public licenseCategory?: string,
    public licenseName?: string,
    public reviewOrg?: string,
    public expiredDate?: Date,
    public expectDate?: Date,
    public realDate?: Date,
    public procDate?: string,
    public handlingSituation?: string,
    public procStatus?: string,
    public procTimes?: string,
    public reviewRemark?: string
  ) {}
}
