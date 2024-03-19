export interface IAdmCodeTable {
  lang?: string;
  category?: string;
  code?: string;
  text?: string;
}

export class AdmCodeTable implements IAdmCodeTable {
  constructor(public lang?: string, public category?: string, public code?: string, public text?: string) {}

  public setDefault() {
    return new AdmCodeTable('', '', '', '');
  }
}
