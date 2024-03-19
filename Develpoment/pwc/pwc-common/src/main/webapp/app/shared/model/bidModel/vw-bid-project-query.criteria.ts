export interface IVwBidProjectQueryCriteria {
  queryType?: string | null;
  queryCase?: string | null;
  prjno?: string | null;
  prjName?: string | null;
  wkut?: string | null;
  add92?: string | null;
  placeCode?: string | null;
  specificUnitName?: string | null;
  typeCodeFirstNotEqual?: string | null;
  typeCodeFirstEqual?: string | null;
  bdgtUpperBound?: number;
  bdgtLowerBound?: number;
  aokAeDateCase?: string | null;
  aokDateUpperBound?: Date | null;
}

export class VwBidProjectQueryCriteria implements IVwBidProjectQueryCriteria {
  constructor(
    public queryType?: string | null,
    public queryCase?: string | null,
    public prjno?: string | null,
    public prjName?: string | null,
    public wkut?: string | null,
    public add92?: string | null,
    public placeCode?: string | null,
    public specificUnitName?: string | null,
    public typeCodeFirstNotEqual?: string | null,
    public typeCodeFirstEqual?: string | null,
    public bdgtUpperBound?: number,
    public bdgtLowerBound?: number,
    public aokAeDateCase?: string | null,
    public aokDateUpperBound?: Date | null
  ) {}
}
