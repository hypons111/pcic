export interface IBidUnit10 {
  id?: string;
  name?: string | null;
  addr?: string | null;
  tel?: string | null;
  fax?: string | null;
  cdty?: string | null;
  type?: string | null;
  dsts?: string | null;
  newcode?: string | null;
  name0?: string | null;
  can01?: string | null;
  hotLine?: string | null;
  capital?: number | null;
  rejsd?: string | null;
  rejed?: string | null;
  createDate?: Date | null;
  createUser?: string | null;
  updateDate?: Date | null;
  updateUser?: string | null;
}

export class BidUnit10 implements IBidUnit10 {
  constructor(
    public id?: string,
    public name?: string | null,
    public addr?: string | null,
    public tel?: string | null,
    public fax?: string | null,
    public cdty?: string | null,
    public type?: string | null,
    public dsts?: string | null,
    public newcode?: string | null,
    public name0?: string | null,
    public can01?: string | null,
    public hotLine?: string | null,
    public capital?: number | null,
    public rejsd?: string | null,
    public rejed?: string | null,
    public createDate?: Date | null,
    public createUser?: string | null,
    public updateDate?: Date | null,
    public updateUser?: string | null
  ) {}
}
