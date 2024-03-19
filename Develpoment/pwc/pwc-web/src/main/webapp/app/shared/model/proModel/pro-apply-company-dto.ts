export interface IProApplyCompanyDTO {
  name?: string | null;
  compIdno?: string | null;
  rpName?: string | null;
  addressCity?: string | null;
  addressTown?: string | null;
  addressVillage?: string | null;
  addressLin?: string | null;
  addressRoad?: string | null;
  title?: string | null;
  workTel?: string | null;
}

export class ProApplyCompanyDTO implements IProApplyCompanyDTO {
  constructor(
    public name?: string | null,
    public compIdno?: string | null,
    public rpName?: string | null,
    public addressCity?: string | null,
    public addressTown?: string | null,
    public addressVillage?: string | null,
    public addressLin?: string | null,
    public addressRoad?: string | null,
    public title?: string | null,
    public workTel?: string | null
  ) {}
}
