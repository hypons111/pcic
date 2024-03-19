export interface IEngEbillingCheck {
  engEbillingCheckId?: string;
  docType?: string;
  docId?: string;
  isClose?: string;
  code1?: string;
  code2?: string;
  code3?: string;
  price?: number;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;
}

export class EngEbillingCheck implements IEngEbillingCheck {
  constructor(
    public engEbillingCheckId?: string,
    public docType?: string,
    public docId?: string,
    public isClose?: string,
    public code1?: string,
    public code2?: string,
    public code3?: string,
    public price?: number,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null
  ) { }
}
