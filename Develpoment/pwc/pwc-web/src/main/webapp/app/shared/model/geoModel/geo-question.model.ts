export interface IGeoQuestion {
  id?: number;
  geoQuestionId?: string | null;
  roleId?: string | null;
  question?: string | null;
  reply?: string | null;
  transferTime?: Date | null;
  transferFrom?: string | null;
  isDel?: string | null;
  delDate?: Date | null;
  delAccId?: string | null;
  createUser?: string | null;
  createTime?: Date | null;
  updateUser?: string | null;
  updateTime?: Date | null;
}

export class GeoQuestion implements IGeoQuestion {
  constructor(
    public id?: number,
    public geoQuestionId?: string | null,
    public roleId?: string | null,
    public question?: string | null,
    public reply?: string | null,
    public transferTime?: Date | null,
    public transferFrom?: string | null,
    public isDel?: string | null,
    public delDate?: Date | null,
    public delAccId?: string | null,
    public createUser?: string | null,
    public createTime?: Date | null,
    public updateUser?: string | null,
    public updateTime?: Date | null
  ) {}
}
