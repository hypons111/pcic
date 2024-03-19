export interface IAdmImportRecord {
  id?: number;
  processDate?: Date;
  processStartTime?: Date;
  processEndTime?: Date;
  processFileName?: string;
  originalDataCount?: number;
  processedDataCount?: number;
  insertSuccessDataCount?: number;
  insertFailDataCount?: number;
  updateSuccessDataCount?: number;
  updateFailDataCount?: number;
  deleteSuccessDataCount?: number;
  deleteFailDataCount?: number;
  unprocessedDataCount?: number;
  caseSuccessDataCount?: number;
  caseFailDataCount?: number;
  interfacingSuccessDataCount?: number;
  interfacingFailDataCount?: number;
  failedDataMemo?: string;
}

export class AdmImportRecord implements IAdmImportRecord {
  constructor(
    public id?: number,
    public processDate?: Date,
    public processStartTime?: Date,
    public processEndTime?: Date,
    public processFileName?: string,
    public originalDataCount?: number,
    public processedDataCount?: number,
    public insertSuccessDataCount?: number,
    public insertFailDataCount?: number,
    public updateSuccessDataCount?: number,
    public updateFailDataCount?: number,
    public deleteSuccessDataCount?: number,
    public deleteFailDataCount?: number,
    public unprocessedDataCount?: number,
    public caseSuccessDataCount?: number,
    public caseFailDataCount?: number,
    public interfacingSuccessDataCount?: number,
    public interfacingFailDataCount?: number,
    public failedDataMemo?: string
  ) {}
}
