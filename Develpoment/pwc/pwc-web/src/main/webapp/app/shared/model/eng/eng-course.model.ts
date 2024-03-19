export interface IEngCourse {
  engCourseId?: string;
  applyDate?: Date | null;
  compId?: string | null;
  adminCompName?: string | null;
  courseName?: string | null;
  dateStart?: Date | null;
  dateEnd?: Date | null;
  uploadDeadline?: Date | null;
  place?: string | null;
  contactPerson?: string | null;
  contactTel?: string | null;
  contactEmail?: string | null;
  status?: string;
  reviewStaff?: string | null;
  reviewTime?: Date | null;
  reviewDesc?: string | null;
  webUrl?: string | null;
  docNo?: string | null;
  isNeedCheck?: string | null;
  isMedia?: string | null;
  remark?: string | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;

  compType?: string | null;
  compName?: string | null;
}

export class EngCourse implements IEngCourse {
  constructor(
    public engCourseId?: string,
    public applyDate?: Date | null,
    public compId?: string | null,
    public adminCompName?: string | null,
    public courseName?: string | null,
    public dateStart?: Date | null,
    public dateEnd?: Date | null,
    public uploadDeadline?: Date | null,
    public place?: string | null,
    public contactPerson?: string | null,
    public contactTel?: string | null,
    public contactEmail?: string | null,
    public status?: string,
    public reviewStaff?: string | null,
    public reviewTime?: Date | null,
    public reviewDesc?: string | null,
    public webUrl?: string | null,
    public docNo?: string | null,
    public isNeedCheck?: string | null,
    public isMedia?: string | null,
    public remark?: string | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null,

    public compType?: string | null,
    public compName?: string | null,
  ) { }
}
