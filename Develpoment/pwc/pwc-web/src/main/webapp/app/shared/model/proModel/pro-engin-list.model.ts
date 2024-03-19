import { IProCommunicatedDTO } from '@/shared/model/proModel/pro-communicated-dto';

export interface IProEnginListDTO {
  proEnginListId?: number;
  proInnovativeProductId?: string | null;
  enginType?: string | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  createUser?: string | null;
  createTime?: Date | null;
}

export class ProEnginListDTO implements IProEnginListDTO {
  constructor(
    public proEnginListId?: number,
    public proInnovativeProductId?: string | null,
    public enginType?: string | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public createUser?: string | null,
    public createTime?: Date | null
  ) {}
}
