export interface IProInnovativeProductProUserDTO {
  id?: string;
  acceptedOrganization?: string | null;
  orgName?: string | null;
  userId?: string | null;
  innovateType?: string | null;
  innovateFunction?: string | null;
  innovateQuality?: string | null;
  innovateProve?: string | null;
  innovateCaseName?: string | null;
  status?: string | null;
  statusInnovativeProduct?: string | null;
  statusOrgSuggestion?: string | null;
  innovateName?: string | null;
  patent?: string | null;
  patentStartDate?: Date | null;
  patentEndDate?: Date | null;
  constructCost?: string | null;
  maintainCost?: string | null;
  communicateDate?: string | null;
  communicateDocNumber?: string | null;
  innovateCaseVendor?: string | null;
  innovateCaseScale?: string | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  createUser?: string | null;
  createTime?: Date | null;
  // 新增的
  time?: Date | null;
  writeCondition?: string | null;
  communicateType?: object | null;
  companyName?: string | null;
}

export class ProInnovativeProductProUserDTO implements IProInnovativeProductProUserDTO {
  constructor(
    public id?: string,
    public acceptedOrganization?: string | null,
    public orgName?: string | null,
    public userId?: string | null,
    public innovateType?: string | null,
    public innovateFunction?: string | null,
    public innovateQuality?: string | null,
    public innovateProve?: string | null,
    public innovateCaseName?: string | null,
    public status?: string | null,
    public statusInnovativeProduct?: string | null,
    public statusOrgSuggestion?: string | null,
    public innovateName?: string | null,
    public patent?: string | null,
    public patentStartDate?: Date | null,
    public patentEndDate?: Date | null,
    public constructCost?: string | null,
    public maintainCost?: string | null,
    public communicateDate?: string | null,
    public communicateDocNumber?: string | null,
    public innovateCaseVendor?: string | null,
    public innovateCaseScale?: string | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public createUser?: string | null,
    public createTime?: Date | null,
    // 新增的
    public time?: Date | null,
    public writeCondition?: string | null,
    public communicateType?: object | null,
    public companyName?: string | null
  ) {}
}
