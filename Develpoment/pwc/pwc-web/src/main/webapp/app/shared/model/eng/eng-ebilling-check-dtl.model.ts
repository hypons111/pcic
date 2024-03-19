export interface IEngEbillingCheckDtl {
  engEbillingCheckId?: string;
  itemNo?: number;
  payType?: string;
  payDate?: Date;
  payAccount?: string;
  amount?: number;
  sourceType?: string;
  sourceRowData?: string | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;
}

export class EngEbillingCheckDtl implements IEngEbillingCheckDtl {
  constructor(
    public engEbillingCheckId?: string,
    public itemNo?: number,
    public payType?: string,
    public payDate?: Date,
    public payAccount?: string,
    public amount?: number,
    public sourceType?: string,
    public sourceRowData?: string | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null
  ) {}
}
