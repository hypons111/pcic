export interface IBidOversee {
  seqNo?: string;
  seqDate?: Date | null;
  seqFlag?: string | null;
  usrname?: string | null;
  usraddr?: string | null;
  usrtel?: string | null;
  usrfax?: string | null;
  usrmail?: string | null;
  usrflag01?: string | null;
  usrflag02?: string | null;
  usrflag03?: string | null;
  usrflag04?: string | null;
  usrflag05?: string | null;
  usrflag06?: string | null;
  usrflag07?: string | null;
  usrmaster?: string | null;
  usrmemo?: string | null;
  engcode?: string | null;
  engflag?: string | null;
  engzon?: string | null;
  engaddr?: string | null;
  comcode?: string | null;
  commail?: string | null;
  comtel?: string | null;
  comman?: string | null;
  comusr?: string | null;
  engname?: string | null;
  dealDate?: Date | null;
  endClose?: string | null;
  endDateP?: Date | null;
  endDate?: Date | null;
  endNo?: string | null;
  viewNum?: number | null;
  projflag?: string | null;
  delayMemo?: string | null;
  endMemo?: string | null;
  sendDate?: Date | null;
  eendDate?: Date | null;
  wkut?: string | null;
  prjno?: string | null;
  usrflag08?: string | null;
  se?: string | null;
  iengpx?: number | null;
  iengpy?: number | null;
  ipeoWhat?: string | null;
  ipeoPrjno?: string | null;
  createDate?: Date;
  createUser?: string;
  updateDate?: Date | null;
  updateUser?: string | null;
}

export class BidOversee implements IBidOversee {
  constructor(
    public seqNo?: string,
    public seqDate?: Date | null,
    public seqFlag?: string | null,
    public usrname?: string | null,
    public usraddr?: string | null,
    public usrtel?: string | null,
    public usrfax?: string | null,
    public usrmail?: string | null,
    public usrflag01?: string | null,
    public usrflag02?: string | null,
    public usrflag03?: string | null,
    public usrflag04?: string | null,
    public usrflag05?: string | null,
    public usrflag06?: string | null,
    public usrflag07?: string | null,
    public usrmaster?: string | null,
    public usrmemo?: string | null,
    public engcode?: string | null,
    public engflag?: string | null,
    public engzon?: string | null,
    public engaddr?: string | null,
    public comcode?: string | null,
    public commail?: string | null,
    public comtel?: string | null,
    public comman?: string | null,
    public comusr?: string | null,
    public engname?: string | null,
    public dealDate?: Date | null,
    public endClose?: string | null,
    public endDateP?: Date | null,
    public endDate?: Date | null,
    public endNo?: string | null,
    public viewNum?: number | null,
    public projflag?: string | null,
    public delayMemo?: string | null,
    public endMemo?: string | null,
    public sendDate?: Date | null,
    public eendDate?: Date | null,
    public wkut?: string | null,
    public prjno?: string | null,
    public usrflag08?: string | null,
    public se?: string | null,
    public iengpx?: number | null,
    public iengpy?: number | null,
    public ipeoWhat?: string | null,
    public ipeoPrjno?: string | null,
    public createDate?: Date,
    public createUser?: string,
    public updateDate?: Date | null,
    public updateUser?: string | null
  ) {}
}
