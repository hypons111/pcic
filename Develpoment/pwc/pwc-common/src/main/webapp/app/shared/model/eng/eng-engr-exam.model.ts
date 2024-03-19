export interface IEngEngrExam {
  engEngrExamId?: string;
  examNo1?: string | null;
  examNo2?: string | null;
  subject?: string | null;
  ranking?: number | null;
  name?: string | null;
  idno?: string | null;
  sex?: string | null;
  birthday?: Date | null;
  education?: string | null;
  addrZip?: string | null;
  addrCity?: string | null;
  addrTown?: string | null;
  addrOther?: string | null;
  importDate?: Date | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;
}

export class EngEngrExam implements IEngEngrExam {
  constructor(
    public engEngrExamId?: string,
    public examNo1?: string | null,
    public examNo2?: string | null,
    public subject?: string | null,
    public ranking?: number | null,
    public name?: string | null,
    public idno?: string | null,
    public sex?: string | null,
    public birthday?: Date | null,
    public education?: string | null,
    public addrZip?: string | null,
    public addrCity?: string | null,
    public addrTown?: string | null,
    public addrOther?: string | null,
    public importDate?: Date | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null
  ) { }
}
