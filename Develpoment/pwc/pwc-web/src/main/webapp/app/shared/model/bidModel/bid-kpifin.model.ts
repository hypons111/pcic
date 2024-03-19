export interface IBidKpifin {
  wkut?: string;
  prjno?: string;
  idate?: Date;
  seq?: number;
  flag?: string | null;
  confDate?: Date | null;
  confNumber?: string | null;
  ka02?: number | null;
  kb01?: number | null;
  kb02?: number | null;
  kc01?: number | null;
  kc02?: number | null;
  kd01?: number | null;
  kd02?: number | null;
  kd03?: number | null;
  kd04?: number | null;
  kd05?: number | null;
  kd06?: number | null;
  kd07?: number | null;
  kd08?: number | null;
  kd09?: number | null;
  ke01?: number | null;
  ke02?: number | null;
  ke03?: number | null;
  ke04?: number | null;
  kf01?: number | null;
  kf02?: number | null;
  kscore?: number | null;
  qa02?: string | null;
  qc01?: string | null;
  qc03?: string | null;
  qd03a?: string | null;
  qd03b?: string | null;
  qd03c?: string | null;
  qd03d?: string | null;
  qd06?: string | null;
  qd07?: string | null;
  qd08?: string | null;
  qd09?: string | null;
  qe01a?: string | null;
  qe01b?: string | null;
  qe021s?: string | null;
  qe021e?: string | null;
  qe021y?: string | null;
  qe022s?: string | null;
  qe022e?: string | null;
  qe022y?: string | null;
  qe023s?: string | null;
  qe023e?: string | null;
  qe023y?: string | null;
  qe03a?: string | null;
  qe03b?: string | null;
  qe04?: string | null;
  qe041s?: string | null;
  qe041e?: string | null;
  qe041y?: string | null;
  qe042s?: string | null;
  qe042e?: string | null;
  qe042y?: string | null;
  qe043s?: string | null;
  qe043e?: string | null;
  qe043y?: string | null;
  qf01?: string | null;
  qf011s?: string | null;
  qf011e?: string | null;
  qf011y?: string | null;
  qf012s?: string | null;
  qf012e?: string | null;
  qf012y?: string | null;
  qf013s?: string | null;
  qf013e?: string | null;
  qf013y?: string | null;
  qf02?: string | null;
  qf02a?: string | null;
  qf02b?: string | null;
  qf02c?: string | null;
  qf02d?: number | null;
  item?: string | null;
  senter?: string | null;
  sentel?: string | null;
  admser?: string | null;
  dsts?: string | null;
  recnt?: number | null;
  rermk?: string | null;
  qd03at?: string | null;
  publ?: string | null;
  createDate?: Date;
  createUser?: string;
  updateDate?: Date | null;
  updateUser?: string | null;
  column1?: string | null;
}

export class BidKpifin implements IBidKpifin {
  constructor(
    public wkut?: string,
    public prjno?: string,
    public idate?: Date,
    public seq?: number,
    public flag?: string | null,
    public confDate?: Date | null,
    public confNumber?: string | null,
    public ka02?: number | null,
    public kb01?: number | null,
    public kb02?: number | null,
    public kc01?: number | null,
    public kc02?: number | null,
    public kd01?: number | null,
    public kd02?: number | null,
    public kd03?: number | null,
    public kd04?: number | null,
    public kd05?: number | null,
    public kd06?: number | null,
    public kd07?: number | null,
    public kd08?: number | null,
    public kd09?: number | null,
    public ke01?: number | null,
    public ke02?: number | null,
    public ke03?: number | null,
    public ke04?: number | null,
    public kf01?: number | null,
    public kf02?: number | null,
    public kscore?: number | null,
    public qa02?: string | null,
    public qc01?: string | null,
    public qc03?: string | null,
    public qd03a?: string | null,
    public qd03b?: string | null,
    public qd03c?: string | null,
    public qd03d?: string | null,
    public qd06?: string | null,
    public qd07?: string | null,
    public qd08?: string | null,
    public qd09?: string | null,
    public qe01a?: string | null,
    public qe01b?: string | null,
    public qe021s?: string | null,
    public qe021e?: string | null,
    public qe021y?: string | null,
    public qe022s?: string | null,
    public qe022e?: string | null,
    public qe022y?: string | null,
    public qe023s?: string | null,
    public qe023e?: string | null,
    public qe023y?: string | null,
    public qe03a?: string | null,
    public qe03b?: string | null,
    public qe04?: string | null,
    public qe041s?: string | null,
    public qe041e?: string | null,
    public qe041y?: string | null,
    public qe042s?: string | null,
    public qe042e?: string | null,
    public qe042y?: string | null,
    public qe043s?: string | null,
    public qe043e?: string | null,
    public qe043y?: string | null,
    public qf01?: string | null,
    public qf011s?: string | null,
    public qf011e?: string | null,
    public qf011y?: string | null,
    public qf012s?: string | null,
    public qf012e?: string | null,
    public qf012y?: string | null,
    public qf013s?: string | null,
    public qf013e?: string | null,
    public qf013y?: string | null,
    public qf02?: string | null,
    public qf02a?: string | null,
    public qf02b?: string | null,
    public qf02c?: string | null,
    public qf02d?: number | null,
    public item?: string | null,
    public senter?: string | null,
    public sentel?: string | null,
    public admser?: string | null,
    public dsts?: string | null,
    public recnt?: number | null,
    public rermk?: string | null,
    public qd03at?: string | null,
    public publ?: string | null,
    public createDate?: Date,
    public createUser?: string,
    public updateDate?: Date | null,
    public updateUser?: string | null,
    public column1?: string | null
  ) {
    // this.kb01 = 0;
    // this.kb02 = 0;
    // this.kc01 = 0;
    // this.kc02 = 0;
    // this.kd01 = 0;
    // this.kd02 = 0;
    // this.kd03 = 0;
    // this.kd04 = 0;
    // this.kd05 = 0;
    // this.kd06 = 0;
    // this.kd07 = 0;
    // this.kd08 = 0;
    // this.kd09 = 0;
    // this.ke01 = 0;
    // this.ke02 = 0;
    // this.ke03 = 0;
    // this.ke04 = 0;
    // this.kf01 = 0;
    // this.kf02 = 0;
  }
}
