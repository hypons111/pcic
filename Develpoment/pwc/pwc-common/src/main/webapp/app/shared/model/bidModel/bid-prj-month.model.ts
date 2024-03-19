export interface IBidPrjMonth {
  wkut?: string;
  prjno?: string;
  yr?: string;
  syr?: string;
  month?: string;
  yearAndMonthDate?: Date | null;
  ypexp?: number | null;
  rate?: number | null;
  pexp?: number | null;
  ptrate?: number | null;
  item?: string | null;
  yaexp?: number | null;
  arat?: number | null;
  aexp?: number | null;
  atrate?: number | null;
  remark?: string | null;
  sumt?: number | null;
  stus?: string | null;
  mdsts?: string | null;
  sumdat?: string | null;
  nopayamt?: number | null;
  nopayresn?: string | null;
  resumt?: number | null;
  trdt?: Date | null;
  nopaydetel?: string | null;
  meetCnt?: number | null;
  meterChk?: number | null;
  meterChkF?: number | null;
  workChk?: number | null;
  workChkF?: number | null;
  replMeth?: string | null;
  teckind3?: number | null;
  enviCnt?: number | null;
  enviFee?: number | null;
  wsafeCnt?: number | null;
  wsafeFee?: number | null;
  sorc1?: number | null;
  sorc1b?: number | null;
  sorc1c?: number | null;
  sorc1d?: number | null;
  sorc2?: number | null;
  sorc3?: number | null;
  sorc4?: number | null;
  sorc5?: number | null;
  spcw01?: string | null;
  spcw02?: string | null;
  spcw03?: string | null;
  spcw04?: string | null;
  spcw05?: string | null;
  spcw06?: string | null;
  spcw07?: string | null;
  spcw08?: string | null;
  spcw09?: string | null;
  spcw10?: string | null;
  spcw11?: string | null;
  spcw12?: string | null;
  equp01?: string | null;
  equp02?: string | null;
  equp03?: string | null;
  equp04?: string | null;
  equp05?: string | null;
  equp06?: string | null;
  equp07?: string | null;
  equp08?: string | null;
  equp09?: string | null;
  equp10?: string | null;
  equp11?: string | null;
  cosend?: number | null;
  aTrate?: number | null;
  wdayeds?: number | null;
  sorc3b?: number | null;
  sorc3c?: number | null;
  sorc3d?: number | null;
  sorc3e?: number | null;
  sorc3a?: number | null;
  sorc6?: number | null;
  sorc3b2?: number | null;
  sorc3b3?: number | null;
  sorc3b4?: number | null;
  sorc3b5?: number | null;
  sorc3c1?: number | null;
  sorc3d1?: number | null;
  sorc3d3?: number | null;
  sorc3d4?: number | null;
  teckind4?: number | null;
  sorc7?: number | null;
  sorc7use?: number | null;
  sorc7oth?: number | null;
  sorc7tac?: number | null;
  sorc7self?: number | null;
  sorc8?: number | null;
  payArr?: string | null;
  sorc7use2?: number | null;
  sorc7use3?: number | null;
  sfrWkr?: number | null;
  sorc9?: number | null;
  dsts?: string | null;
  confOut?: number | null;
  confOut2?: number | null;
  createDate?: Date;
  createUser?: string;
  updateDate?: Date | null;
  updateUser?: string | null;
  uIp?: string | null;
  udt2?: string | null;
}

export class BidPrjMonth implements IBidPrjMonth {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public yr?: string,
    public syr?: string,
    public month?: string,
    public yearAndMonthDate?: Date | null,
    public ypexp?: number | null,
    public rate?: number | null,
    public pexp?: number | null,
    public ptrate?: number | null,
    public item?: string | null,
    public yaexp?: number | null,
    public arat?: number | null,
    public aexp?: number | null,
    public atrate?: number | null,
    public remark?: string | null,
    public sumt?: number | null,
    public stus?: string | null,
    public mdsts?: string | null,
    public sumdat?: string | null,
    public nopayamt?: number | null,
    public nopayresn?: string | null,
    public resumt?: number | null,
    public trdt?: Date | null,
    public nopaydetel?: string | null,
    public meetCnt?: number | null,
    public meterChk?: number | null,
    public meterChkF?: number | null,
    public workChk?: number | null,
    public workChkF?: number | null,
    public replMeth?: string | null,
    public teckind3?: number | null,
    public enviCnt?: number | null,
    public enviFee?: number | null,
    public wsafeCnt?: number | null,
    public wsafeFee?: number | null,
    public sorc1?: number | null,
    public sorc1b?: number | null,
    public sorc1c?: number | null,
    public sorc1d?: number | null,
    public sorc2?: number | null,
    public sorc3?: number | null,
    public sorc4?: number | null,
    public sorc5?: number | null,
    public spcw01?: string | null,
    public spcw02?: string | null,
    public spcw03?: string | null,
    public spcw04?: string | null,
    public spcw05?: string | null,
    public spcw06?: string | null,
    public spcw07?: string | null,
    public spcw08?: string | null,
    public spcw09?: string | null,
    public spcw10?: string | null,
    public spcw11?: string | null,
    public spcw12?: string | null,
    public equp01?: string | null,
    public equp02?: string | null,
    public equp03?: string | null,
    public equp04?: string | null,
    public equp05?: string | null,
    public equp06?: string | null,
    public equp07?: string | null,
    public equp08?: string | null,
    public equp09?: string | null,
    public equp10?: string | null,
    public equp11?: string | null,
    public cosend?: number | null,
    public aTrate?: number | null,
    public wdayeds?: number | null,
    public sorc3b?: number | null,
    public sorc3c?: number | null,
    public sorc3d?: number | null,
    public sorc3e?: number | null,
    public sorc3a?: number | null,
    public sorc6?: number | null,
    public sorc3b2?: number | null,
    public sorc3b3?: number | null,
    public sorc3b4?: number | null,
    public sorc3b5?: number | null,
    public sorc3c1?: number | null,
    public sorc3d1?: number | null,
    public sorc3d3?: number | null,
    public sorc3d4?: number | null,
    public teckind4?: number | null,
    public sorc7?: number | null,
    public sorc7use?: number | null,
    public sorc7oth?: number | null,
    public sorc7tac?: number | null,
    public sorc7self?: number | null,
    public sorc8?: number | null,
    public payArr?: string | null,
    public sorc7use2?: number | null,
    public sorc7use3?: number | null,
    public sfrWkr?: number | null,
    public sorc9?: number | null,
    public dsts?: string | null,
    public confOut?: number | null,
    public confOut2?: number | null,
    public createDate?: Date,
    public createUser?: string,
    public updateDate?: Date | null,
    public updateUser?: string | null,
    public uIp?: string | null,
    public udt2?: string | null
  ) {}
}
