export interface IEngCourseDtl {
  engCourseId?: string;
  engCourseDetailId?: number;
  courseName?: string | null;
  courseDate?: Date | null;
  timeStart?: string | null;
  timeEnd?: string | null;
  law?: string | null;
  point?: number | null;
  subjectList?: string | null;
  // teacherName?: string | null;
  // teacherHighestEdu?: string | null;
  // teacherJob?: string | null;
  // teacherExperience?: string | null;
  remark?: string | null;
  isReview?: boolean | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;

  timeStartArr?: string[] | null;
  timeEndArr?: string[] | null;
  subjectArr?: string[] | null;
  teacherArr?: string[] | null;
  isEdit?: boolean | null;
  _showDetails?: boolean | null;
  checkValidityArr?: (() => Promise<boolean>)[] | null;
}

export class EngCourseDtl implements IEngCourseDtl {
  constructor(
    public engCourseId?: string,
    public engCourseDetailId?: number,
    public courseName?: string | null,
    public courseDate?: Date | null,
    public timeStart?: string | null,
    public timeEnd?: string | null,
    public law?: string | null,
    public point?: number | null,
    public subjectList?: string | null,
    // public teacherName?: string | null,
    // public teacherHighestEdu?: string | null,
    // public teacherJob?: string | null,
    // public teacherExperience?: string | null,
    public remark?: string | null,
    public isReview?: boolean | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null,

    public timeStartArr?: string[] | null,
    public timeEndArr?: string[] | null,
    public subjectArr?: string[] | null,
    public teacherArr?: string[] | null,
    public isEdit?: boolean | null,
    public _showDetails?: boolean | null,
    public checkValidityArr?: (() => Promise<boolean>)[] | null,
  ) {
    this.timeStartArr = ['08', '00'];
    this.timeEndArr = ['09', '00'];
    this.subjectArr = [];
    this.teacherArr = [];
    this.isEdit = false;
    this._showDetails = true;
    this.checkValidityArr = [];
  }
}
