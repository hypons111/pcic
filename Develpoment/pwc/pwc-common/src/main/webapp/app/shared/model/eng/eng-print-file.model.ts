export interface IEngPrintFile {
  id?: string;
  version?: string,
  docNo?: string | null;
  enDocNo?: string | null;
  applyType?: string | null;
  applyItems?: string[] | null;
  chApplyItemsList?: string | null;
  idno?: string | null;
  chName?: string | null;
  enName?: string | null;
  chBirthDate?: string | null;
  enBirthDate?: string | null;
  chSex?: string | null;
  enSex?: string | null;
  enSexPrefix?: string | null;
  chSubject?: string;
  enSubject?: string;
  examNo?: string | null;
  enExamNo?: string | null;
  chReceiveDate?: string | null;
  enReceiveDate?: string | null;
  receiveNo?: string | null;
  receiveAddr?: string | null;
  letterTitle?: string | null;
  chPublicDate_pre?: string | null;
  contractorName?: string | null;
  contractorTel?: string | null;
  contractorEmail?: string | null;
}

export class EngPrintFile implements IEngPrintFile {
  constructor(
    public id?: string,
    public version?: string,
    public docNo?: string | null,
    public enDocNo?: string | null,
    public applyType?: string | null,
    public applyItems?: string[] | null,
    public chApplyItemsList?: string | null,
    public idno?: string | null,
    public chName?: string | null,
    public enName?: string | null,
    public chBirthDate?: string | null,
    public enBirthDate?: string | null,
    public chSex?: string | null,
    public enSex?: string | null,
    public enSexPrefix?: string | null,
    public chSubject?: string,
    public enSubject?: string,
    public examNo?: string | null,
    public enExamNo?: string | null,
    public chReceiveDate?: string | null,
    public enReceiveDate?: string | null,
    public receiveNo?: string | null,
    public receiveAddr?: string | null,
    public letterTitle?: string | null,
    public chPublicDate_pre?: string | null,
    public contractorName?: string | null,
    public contractorTel?: string | null,
    public contractorEmail?: string | null,
  ) {
  }
}
