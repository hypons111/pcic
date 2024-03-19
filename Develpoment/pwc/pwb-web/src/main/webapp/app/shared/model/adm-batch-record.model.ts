export interface IAdmBatchRecord {
  id?: number;
  batchServiceNo?: string;
  batchServiceName?: string;
  batchServiceCategory?: string;
  triggerTime?: Date;
  finishTime?: Date;
  result?: string;
}

export class AdmBatchRecord implements IAdmBatchRecord {
  constructor(
    public id?: number,
    public batchServiceNo?: string,
    public batchServiceName?: string,
    public batchServiceCategory?: string,
    public triggerTime?: Date,
    public finishTime?: Date,
    public result?: string
  ) {}
}
