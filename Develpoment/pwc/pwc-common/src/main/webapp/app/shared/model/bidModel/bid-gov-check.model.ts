export interface IBidGovCheck {
  wkut?: string;
  prjno?: string;
  checkDate?: Date;
  checkUnit?: string | null;
  checker?: string | null;
  detel1?: string | null;
  detel2?: string | null;
  detel3?: string | null;
  dataStatus?: string | null;
  score?: number | null;
  chkunt?: string | null;
  engFlag?: string | null;
  sorea?: string | null;
  scoreb?: number | null;
  scorec?: number | null;
  deadDate?: Date | null;
  roveFlag?: string | null;
  roveGNum?: string | null;
  roveBNum?: string | null;
  roveChk1?: string | null;
  roveChk2?: string | null;
  roveChk3?: string | null;
  roveChk4?: string | null;
  scorea?: number | null;
  chkflag?: string | null;
  oChker?: string | null;
  roveChk5?: string | null;
  roveChk6?: string | null;
  lChker?: string | null;
  unChker?: string | null;
  coChker?: string | null;
  testFlag?: string | null;
  roveChk42?: string | null;
  roveChk43?: string | null;
  roveChk32?: string | null;
  roveChk33?: string | null;
  roveChk34?: string | null;
  roveChk35?: string | null;
  roveChk36?: string | null;
  roveChk44?: string | null;
  roveChk45?: string | null;
  chkLead?: string | null;
  roveChk1a?: string | null;
  roveChk2a?: string | null;
  roveChk3a?: string | null;
  roveChk32a?: string | null;
  roveChk33a?: string | null;
  roveChk34a?: string | null;
  roveChk35a?: string | null;
  roveChk36a?: string | null;
  roveChk4a?: string | null;
  roveChk42a?: string | null;
  roveChk43a?: string | null;
  roveChk44a?: string | null;
  roveChk45a?: string | null;
  udt2?: Date | null;
  detel4?: string | null;
  roveChk12?: string | null;
  roveChk12a?: string | null;
  roveChk13?: string | null;
  roveChk13a?: string | null;
  roveChk37?: string | null;
  roveChk37a?: string | null;
  testFlag2?: string | null;
  testFlag3?: string | null;
  testFlag4?: string | null;
  testFlag5?: string | null;
  testFlag6?: string | null;
  roveChk14?: string | null;
  roveChk14a?: string | null;
  roveGDat?: Date | null;
  testMemo?: string | null;
  detel0?: string | null;
  ov7day?: string | null;
  endDate?: Date | null;
  roveChk0?: string | null;
  can?: string | null;
  roveChk5a?: string | null;
  detel5?: string | null;
  fgop1?: number | null;
  fgop2?: number | null;
  fgop3?: number | null;
  fgop4?: number | null;
  fgop5?: number | null;
  testFlag7?: string | null;
  testFlag8?: string | null;
  testFlag9?: string | null;
  testFlaga?: string | null;
  testFlagb?: string | null;
  testFlagc?: string | null;
  testFlagd?: string | null;
  testFlage?: string | null;
  testFlagf?: string | null;
  testFlagg?: string | null;
  isckp?: string | null;
  isckp2?: string | null;
  isckp3?: string | null;
  roveChk7?: string | null;
  roveChk7a?: string | null;
  testCo?: string | null;
  testDate?: Date | null;
  testNum?: string | null;
  chkunitOld?: string | null;
  testFlagh?: string | null;
  testFlagz?: string | null;
  createDate?: Date | null;
  createUser?: string | null;
  updateDate?: Date | null;
  updateUser?: string | null;
}

export class BidGovCheck implements IBidGovCheck {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public checkDate?: Date,
    public checkUnit?: string | null,
    public checker?: string | null,
    public detel1?: string | null,
    public detel2?: string | null,
    public detel3?: string | null,
    public dataStatus?: string | null,
    public score?: number | null,
    public chkunt?: string | null,
    public engFlag?: string | null,
    public sorea?: string | null,
    public scoreb?: number | null,
    public scorec?: number | null,
    public deadDate?: Date | null,
    public roveFlag?: string | null,
    public roveGNum?: string | null,
    public roveBNum?: string | null,
    public roveChk1?: string | null,
    public roveChk2?: string | null,
    public roveChk3?: string | null,
    public roveChk4?: string | null,
    public scorea?: number | null,
    public chkflag?: string | null,
    public oChker?: string | null,
    public roveChk5?: string | null,
    public roveChk6?: string | null,
    public lChker?: string | null,
    public unChker?: string | null,
    public coChker?: string | null,
    public testFlag?: string | null,
    public roveChk42?: string | null,
    public roveChk43?: string | null,
    public roveChk32?: string | null,
    public roveChk33?: string | null,
    public roveChk34?: string | null,
    public roveChk35?: string | null,
    public roveChk36?: string | null,
    public roveChk44?: string | null,
    public roveChk45?: string | null,
    public chkLead?: string | null,
    public roveChk1a?: string | null,
    public roveChk2a?: string | null,
    public roveChk3a?: string | null,
    public roveChk32a?: string | null,
    public roveChk33a?: string | null,
    public roveChk34a?: string | null,
    public roveChk35a?: string | null,
    public roveChk36a?: string | null,
    public roveChk4a?: string | null,
    public roveChk42a?: string | null,
    public roveChk43a?: string | null,
    public roveChk44a?: string | null,
    public roveChk45a?: string | null,
    public udt2?: Date | null,
    public detel4?: string | null,
    public roveChk12?: string | null,
    public roveChk12a?: string | null,
    public roveChk13?: string | null,
    public roveChk13a?: string | null,
    public roveChk37?: string | null,
    public roveChk37a?: string | null,
    public testFlag2?: string | null,
    public testFlag3?: string | null,
    public testFlag4?: string | null,
    public testFlag5?: string | null,
    public testFlag6?: string | null,
    public roveChk14?: string | null,
    public roveChk14a?: string | null,
    public roveGDat?: Date | null,
    public testMemo?: string | null,
    public detel0?: string | null,
    public ov7day?: string | null,
    public endDate?: Date | null,
    public roveChk0?: string | null,
    public can?: string | null,
    public roveChk5a?: string | null,
    public detel5?: string | null,
    public fgop1?: number | null,
    public fgop2?: number | null,
    public fgop3?: number | null,
    public fgop4?: number | null,
    public fgop5?: number | null,
    public testFlag7?: string | null,
    public testFlag8?: string | null,
    public testFlag9?: string | null,
    public testFlaga?: string | null,
    public testFlagb?: string | null,
    public testFlagc?: string | null,
    public testFlagd?: string | null,
    public testFlage?: string | null,
    public testFlagf?: string | null,
    public testFlagg?: string | null,
    public isckp?: string | null,
    public isckp2?: string | null,
    public isckp3?: string | null,
    public roveChk7?: string | null,
    public roveChk7a?: string | null,
    public testCo?: string | null,
    public testDate?: Date | null,
    public testNum?: string | null,
    public chkunitOld?: string | null,
    public testFlagh?: string | null,
    public testFlagz?: string | null,
    public createDate?: Date | null,
    public createUser?: string | null,
    public updateDate?: Date | null,
    public updateUser?: string | null,
  ) {}
}
