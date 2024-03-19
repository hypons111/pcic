export interface IAdmPostAddr {
  zip?: string | null;
  city?: string | null;
  cityCode?: string | null;
  area?: string | null;
  areaCode?: string | null;
  address?: string | null;
}

export class AdmPostAddr implements IAdmPostAddr {
  constructor(
    public zip?: string | null,
    public city?: string | null,
    public cityCode?: string | null,
    public area?: string | null,
    public areaCode?: string | null,
    public address?: string | null
  ) { }
}
