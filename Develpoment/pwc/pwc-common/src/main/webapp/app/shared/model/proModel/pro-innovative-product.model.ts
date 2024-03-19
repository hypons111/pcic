import { IProCommunicatedDTO } from '@/shared/model/proModel/pro-communicated-dto';

export interface IProInnovativeProductDTO {
  proInnovativeProductId?: string | null;
  acceptedOrganization?: string | null;
  userId?: string | null;
  innovateType?: string | null;
  innovateFunction?: string | null;
  innovateQuality?: string | null;
  innovateProve?: string | null;
  innovateCaseName?: string | null;
  status?: string | null;
  innovateName?: string | null;
  patent?: string | null;
  patentStartDate?: Date | null;
  patentEndDate?: Date | null;
  constructCost?: string | null;
  maintainCost?: string | null;
  communicateDate?: Date | null;
  communicateDocNumber?: string | null;
  innovateCaseVendor?: string | null;
  innovateCaseScale?: string | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  createUser?: string | null;
  createTime?: Date | null;
  // 新增的
  statusIp?: string | null;
  statusOs?: string | null;
  time?: Date | null;
}

export class ProInnovativeProductDTO implements IProInnovativeProductDTO {
  constructor(
    public proInnovativeProductId?: string | null,
    public acceptedOrganization?: string | null,
    public userId?: string | null,
    public innovateType?: string | null,
    public innovateFunction?: string | null,
    public innovateQuality?: string | null,
    public innovateProve?: string | null,
    public innovateCaseName?: string | null,
    public status?: string | null,
    public innovateName?: string | null,
    public patent?: string | null,
    public patentStartDate?: Date | null,
    public patentEndDate?: Date | null,
    public constructCost?: string | null,
    public maintainCost?: string | null,
    public communicateDate?: Date | null,
    public communicateDocNumber?: string | null,
    public innovateCaseVendor?: string | null,
    public innovateCaseScale?: string | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public createUser?: string | null,
    public createTime?: Date | null,
    // 新增的
    public statusIp?: string | null,
    public statusOs?: string | null,
    public time?: Date | null
  ) {}
}
