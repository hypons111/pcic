export interface IrevMain {
  reviewType?: string;
  createDate?: string;
  caseName?: string;
  status?: string;
  adminOrg?: string;
  org?: string;
  contactPerson?: string;
  classify?: string;
  pccUnit?: string;
  receiptDate?: string;
  id?: string;


}

export class RevMain implements IrevMain {
  constructor(
    public reviewType?: string,
    public createDate?: string,
    public caseName?: string,
    public status?: string,
    public adminOrg?: string,
    public org?: string,
    public contactPerson?: string,
    public classify?: string,
    public pccUnit?: string,
    public receiptDate?: string,
    public id?: string,


  ) { }
}
