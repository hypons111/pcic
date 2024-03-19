export interface IProNoCommunicateListDTO {
  id?: number;
  proOrgSuggestionId?: number;
  noCommunicateType?: string | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  createUser?: string | null;
  createTime?: Date | null;
}

export class ProNoCommunicateListDTO implements IProNoCommunicateListDTO {
  constructor(
    public id?: number,
    public proOrgSuggestionId?: number,
    public noCommunicateType?: string | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public createUser?: string | null,
    public createTime?: Date | null
  ) {}
}
