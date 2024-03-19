export interface IEngEngrSubjectpoint {
  engEngrSubjectpointId?: string;
  applyDate?: Date | null;
  engrId?: string | null;
  theme?: string | null;
  content?: string | null;
  law?: string | null;
  point?: number | null;
  subjectList?: string | null;
  subject?: string | null;
  checkDesc?: string | null;
  remark?: string | null;
  contractorId?: string | null;
  contractorName?: string | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;

  chName?: string | null;
  enName?: string | null;
  idno?: string | null;
}

export class EngEngrSubjectpoint implements IEngEngrSubjectpoint {
  constructor(
    public engEngrSubjectpointId?: string,
    public applyDate?: Date | null,
    public engrId?: string | null,
    public theme?: string | null,
    public content?: string | null,
    public law?: string | null,
    public point?: number | null,
    public subjectList?: string | null,
    public subject?: string | null,
    public checkDesc?: string | null,
    public remark?: string | null,
    public contractorId?: string | null,
    public contractorName?: string | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null,

    public chName?: string | null,
    public enName?: string | null,
    public idno?: string | null,
  ) {
    this.applyDate = new Date()
  }
}

export interface IEng0601 {
  engEngrSubjectpointId?: string;
  applyDate?: Date | null;
  engrId?: string | null;
  theme?: string | null;
  content?: string | null;
  law?: string | null;
  point?: number | null;
  subjectList?: string | null;
  subject?: string | null;
  checkDesc?: string | null;
  remark?: string | null;
  contractorId?: string | null;
  contractorName?: string | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;

  chName?: string | null;
  enName?: string | null;
  idno?: string | null;
}