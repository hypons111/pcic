export interface ICmsComp {
  id: string;
  type: string;
  name: string;
  useStatus: string;
  chairman: string;
  secretaryGeneral: string;
  tel1: string;
  tel2: string;
  chairmanTel: string;
  fax: string;
  address: string;
  mail: string;
  subjectList: string;
  remark: string;
  createDate: Date;
  modifyDate: Date;
}

export class CmsComp implements ICmsComp {
  constructor(
    public id: string,
    public type: string,
    public name: string,
    public useStatus: string,
    public chairman: string,
    public secretaryGeneral: string,
    public tel1: string,
    public tel2: string,
    public chairmanTel: string,
    public fax: string,
    public address: string,
    public mail: string,
    public subjectList: string,
    public remark: string,
    public createDate: Date,
    public modifyDate: Date
  ) {}
}
