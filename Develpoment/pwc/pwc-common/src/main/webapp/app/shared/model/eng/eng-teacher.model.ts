export interface IEngTeacher {
  engTeacherId?: string;
  teacherName?: string;
  teacherHighestEdu?: string | null;
  teacherJob?: string | null;
  teacherExperience?: string | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;

  isEdit?: boolean | null;
  checkValidityArr?: (() => Promise<boolean>)[] | null;
}

export class EngTeacher implements IEngTeacher {
  constructor(
    public engTeacherId?: string,
    public teacherName?: string,
    public teacherHighestEdu?: string | null,
    public teacherJob?: string | null,
    public teacherExperience?: string | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null,

    public isEdit?: boolean | null,
    public checkValidityArr?: (() => Promise<boolean>)[] | null,
  ) {
    this.isEdit = false;
    this.checkValidityArr = [];
  }
}
