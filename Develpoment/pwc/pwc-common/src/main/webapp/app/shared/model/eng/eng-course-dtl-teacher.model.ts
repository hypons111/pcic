export interface IEngCourseDtlTeacher {
  engCourseId?: string;
  engCourseDetailId?: number;
  engCourseDetailTeacherId?: number;
  engTeacherId?: string | null;
  teacherName?: string | null;
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
}

export class EngCourseDtlTeacher implements IEngCourseDtlTeacher {
  constructor(
    public engCourseId?: string,
    public engCourseDetailId?: number,
    public engCourseDetailTeacherId?: number,
    public engTeacherId?: string | null,
    public teacherName?: string | null,
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
  ) {
    this.isEdit = false;
  }
}
