export interface IProOsPersonListDTO {
  id?: number;
  proOrgSuggestionId?: number;
  personType?: string | null;
  personName?: string | null;
  personTitle?: string | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  createUser?: string | null;
  createTime?: Date | null;
}

export class ProOsPersonListDTO implements IProOsPersonListDTO {
  constructor(
    public id?: number,
    public proOrgSuggestionId?: number,
    public personType?: string | null,
    public personName?: string | null,
    public personTitle?: string | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public createUser?: string | null,
    public createTime?: Date | null
  ) {}
}
