export interface IAdmBatchService {
  batchServiceNo?: string;
  batchServiceName?: string;
  batchServiceCategory?: string;
  isRegular?: string;
  batchServiceProg?: string;
  batchServiceProgMethod?: string;
  batchServiceStatus?: string;
  batchExeStatus?: string;
  batchFrequency?: string;
  updateUser?: string;
  updateTime?: Date;
}

export class AdmBatchService implements IAdmBatchService {
  constructor(
    public batchServiceNo?: string,
    public batchServiceName?: string,
    public batchServiceCategory?: string,
    public isRegular?: string,
    public batchServiceProg?: string,
    public batchServiceProgMethod?: string,
    public batchServiceStatus?: string,
    public batchExeStatus?: string,
    public batchFrequency?: string,
    public updateUser?: string,
    public updateTime?: Date
  ) {}

  public setDefault() {
    return new AdmBatchService('', '', '', '', '', '','', 'T', '', 'SYS', new Date());
  }
}
