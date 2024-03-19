import { IEngCourseDtlTeacher } from "./eng-course-dtl-teacher.model";
import { IEngCourseDtl } from "./eng-course-dtl.model";
import { IEngCourse } from "./eng-course.model";
import { IEngTeacher } from "./eng-teacher.model";

export interface IEng0401 {
  engCourseDTO?: IEngCourse;
  engCourseDtlDTOList?: IEngCourseDtl[] | null;
  engCourseDtlTeacherDTOList?: IEngCourseDtlTeacher[] | null;
  engTeacherDTOList?: IEngTeacher[] | null;
}

export class Eng0401 implements IEng0401 {
  constructor(
    public engCourseDTO?: IEngCourse,
    public engCourseDtlDTOList?: IEngCourseDtl[] | null,
    public engCourseDtlTeacherDTOList?: IEngCourseDtlTeacher[] | null,
    public engTeacherDTOList?: IEngTeacher[] | null,
  ) { }
}