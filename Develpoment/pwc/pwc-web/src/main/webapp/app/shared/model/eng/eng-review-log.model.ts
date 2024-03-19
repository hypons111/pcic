export interface IEngReviewLog {
  engReviewLogId?: string;
  externalId?: string;
  externalVersion?: number | null;
  reviewCaseType?: string;
  itemNo?: number;
  reviewStatus?: string;
  reviewStaff?: string;
  reviewTime?: Date;
  reviewDesc?: string | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;
}

export class EngReviewLog implements IEngReviewLog {
  constructor(
    public engReviewLogId?: string,
    public externalId?: string,
    public externalVersion?: number | null,
    public reviewCaseType?: string,
    public itemNo?: number,
    public reviewStatus?: string,
    public reviewStaff?: string,
    public reviewTime?: Date,
    public reviewDesc?: string | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null
  ) {}
}
