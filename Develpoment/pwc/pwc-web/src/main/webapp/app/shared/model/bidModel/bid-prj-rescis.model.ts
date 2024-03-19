export interface IBidPrjRescis {
  wkut?: string;
  prjno?: string;
  stopDate?: Date;
  contractType?: string | null;
  prmNo?: string | null;
  stopReson?: string | null;
  blong?: string | null;
  stopRemark?: string | null;
  contractItem?: string | null;
  isRedo?: string | null;
  redoDate?: Date | null;
  redoPrjno?: string | null;
  noRedoReson?: string | null;
  createDate?: Date;
  createUser?: string;
  updateDate?: Date | null;
  updateUser?: string | null;
}

export class BidPrjRescis implements IBidPrjRescis {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public stopDate?: Date,
    public contractType?: string | null,
    public prmNo?: string | null,
    public stopReson?: string | null,
    public blong?: string | null,
    public stopRemark?: string | null,
    public contractItem?: string | null,
    public isRedo?: string | null,
    public redoDate?: Date | null,
    public redoPrjno?: string | null,
    public noRedoReson?: string | null,
    public createDate?: Date,
    public createUser?: string,
    public updateDate?: Date | null,
    public updateUser?: string | null
  ) {}
}
