export interface IProOrgSuggestionDTO {
  id?: number;
  proInnovativeProductId?: string | null;
  userId?: string | null;
  type?: string | null;
  addr?: string | null;
  time?: Date | null;
  outline?: string | null;
  memo?: string | null;
  other?: string | null;
  status?: string | null;
  commReason?: string | null;
  writeCondition?: string | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  createUser?: string | null;
  createTime?: Date | null;
}

export class ProOrgSuggestionDTO implements IProOrgSuggestionDTO {
  constructor(
    public id?: number,
    public proInnovativeProductId?: string | null,
    public userId?: string | null,
    public type?: string | null,
    public addr?: string | null,
    public time?: Date | null,
    public outline?: string | null,
    public memo?: string | null,
    public other?: string | null,
    public status?: string | null,
    public commReason?: string | null,
    public writeCondition?: string | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public createUser?: string | null,
    public createTime?: Date | null
  ) {}
}
