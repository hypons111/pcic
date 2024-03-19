export interface IVwBidProject {
  id?: number;
  wkut?: string | null;
  wkutName?: string | null;
  prjno?: string | null;
  projectName?: string | null;
  iAdd92?: string | null;
  pntCtrl?: string | null;
  typeCode?: string | null;
  isUnion?: string | null;
  placeCode?: string | null;
  placeName?: string | null;
  place?: string | null;
  bosCode?: string | null;
  depCode?: string | null;
  isBg?: string | null;
  plnCode?: string | null;
  plnNo?: string | null;
  plnUnicod?: string | null;
  bdgt1?: number | null;
  aopDate?: Date | null;
  aokDate?: Date | null;
  actDate?: Date | null;
  asDate?: Date | null;
  aeDate?: Date | null;
  pcmut?: string | null;
  mctmth?: string | null;
  drut?: string | null;
  ctSum?: number | null;
  ctSumF?: number | null;
  pays?: number | null;

}

export class VwBidProject implements IVwBidProject {
  constructor(
    public id?: number,
    public wkut?: string | null,
    public wkutName?: string | null,
    public prjno?: string | null,
    public projectName?: string | null,
    public iAdd92?: string | null,
    public pntCtrl?: string | null,
    public typeCode?: string | null,
    public isUnion?: string | null,
    public placeCode?: string | null,
    public placeName?: string | null,
    public place?: string | null,
    public bosCode?: string | null,
    public depCode?: string | null,
    public isBg?: string | null,
    public plnCode?: string | null,
    public plnNo?: string | null,
    public plnUnicod?: string | null,
    public bdgt1?: number | null,
    public aopDate?: Date | null,
    public aokDate?: Date | null,
    public actDate?: Date | null,
    public asDate?: Date | null,
    public aeDate?: Date | null,
    public pcmut?: string | null,
    public mctmth?: string | null,
    public drut?: string | null,
    public ctSum?: number | null,
    public ctSumF?: number | null,
    public pays?: number | null,
  ) {}
}
