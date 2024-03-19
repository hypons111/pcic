export interface Iproject_basic {
  id?: string;
  planName?: string;
  proj_class_UID?: number;
  date_begin?: Date;
  date_end?: Date;
  organ_exec_UID?: number;
  organ_exec?: string;
  organ_manage_UID?: number;
  organ_manage?: string;
  date_feas?: Date;
  date_cons?: Date;
  IDT?: Date;
  deleted?: number;
  locked?: number;
  member_UID?: number;
  file?: number;
  date_work?: Date;
  total_budget?: string;
  execname?: string;
  exectel?: string;
  status?: number;
  memo?: string;
  planType?: string;
  planStatus?: string;
}

export class Project_basic implements Iproject_basic {
  constructor(
    public id?: string,
    public planName?: string,
    public proj_class_UID?: number,
    public date_begin?: Date,
    public date_end?: Date,
    public organ_exec_UID?: number,
    public organ_exec?: string,
    public organ_manage_UID?: number,
    public organ_manage?: string,
    public date_feas?: Date,
    public date_cons?: Date,
    public IDT?: Date,
    public deleted?: number,
    public locked?: number,
    public member_UID?: number,
    public file?: number,
    public date_work?: Date,
    public total_budget?: string,
    public execname?: string,
    public exectel?: string,
    public status?: number,
    public memo?: string,
    public planType?: string,
    public planStatus?: string
  ) {}
}
