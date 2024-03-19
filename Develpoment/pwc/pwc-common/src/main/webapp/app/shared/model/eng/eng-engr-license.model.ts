import { IAdmFile } from "../adm/adm-file.model";

export interface IEngEngrLicense {
  engEngrLicenseId?: string;
  engEngrLicenseVersion?: number;
  engEngrLicenseNo?: string | null;
  receiveDate?: Date | null;
  engrLicenseDocNo?: string | null;
  engrId?: string | null;
  identityType?: string | null;
  idno?: string | null;
  chName?: string | null;
  chNameRome?: string | null;
  enName?: string | null;
  birthDate?: Date | null;
  sex?: string | null;
  mobileTel?: string | null;
  email?: string | null;
  permanentAddrZip?: string | null;
  permanentAddrCity?: string | null;
  permanentAddrTown?: string | null;
  permanentAddrOther?: string | null;
  contactAddrZip?: string | null;
  contactAddrCity?: string | null;
  contactAddrTown?: string | null;
  contactAddrOther?: string | null;
  execType?: string | null;
  compId?: string | null;
  compType?: string | null;
  compIdno?: string | null;
  compChName?: string | null;
  compEnName?: string | null;
  compTel1?: string | null;
  compTel2?: string | null;
  compFax?: string | null;
  compAddrZip?: string | null;
  compAddrCity?: string | null;
  compAddrTown?: string | null;
  compAddrOther?: string | null;
  compAssrEn?: string | null;
  applyItem?: string | null;
  applyType?: string | null;
  receiveNo?: string | null;
  publicDate?: Date | null;
  effectDateStart?: Date | null;
  effectDateEnd?: Date | null;
  remark?: string | null;
  receiveType?: string | null;
  receiveAddrZip?: string | null;
  receiveAddrCity?: string | null;
  receiveAddrTown?: string | null;
  receiveAddrOther?: string | null;
  certificateIdList?: string | null;
  subjectList?: string | null;
  isMore7Year?: string | null;
  isXcaCert?: string | null;
  changeItems?: string | null;
  changeItemsDesc?: string | null;
  contractorId?: string | null;
  status?: string | null;
  reviewStaff?: string | null;
  reviewTime?: Date | null;
  reviewDesc?: string | null;
  creatUser?: string | null;
  creatTime?: Date | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  transferFrom?: string | null;
  transferTime?: Date | null;

  applyItems?: string[] | null;
  picFile?: File | null;
  picFileInfo?: IAdmFile | null;
  appendixFiles?: File[] | null;
  appendixFilesInfo?: IAdmFile[] | null;
}

export class EngEngrLicense implements IEngEngrLicense {
  constructor(
    public engEngrLicenseId?: string,
    public engEngrLicenseVersion?: number,
    public engEngrLicenseNo?: string | null,
    public receiveDate?: Date | null,
    public engrLicenseDocNo?: string | null,
    public engrId?: string | null,
    public identityType?: string | null,
    public idno?: string | null,
    public chName?: string | null,
    public chNameRome?: string | null,
    public enName?: string | null,
    public birthDate?: Date | null,
    public sex?: string | null,
    public mobileTel?: string | null,
    public email?: string | null,
    public permanentAddrZip?: string | null,
    public permanentAddrCity?: string | null,
    public permanentAddrTown?: string | null,
    public permanentAddrOther?: string | null,
    public contactAddrZip?: string | null,
    public contactAddrCity?: string | null,
    public contactAddrTown?: string | null,
    public contactAddrOther?: string | null,
    public execType?: string | null,
    public compId?: string | null,
    public compType?: string | null,
    public compIdon?: string | null,
    public compChName?: string | null,
    public compEnName?: string | null,
    public compTel1?: string | null,
    public compTel2?: string | null,
    public compFax?: string | null,
    public compAddrZip?: string | null,
    public compAddrCity?: string | null,
    public compAddrTown?: string | null,
    public compAddrOther?: string | null,
    public compAssrEn?: string | null,
    public applyItem?: string | null,
    public applyType?: string | null,
    public receiveNo?: string | null,
    public publicDate?: Date | null,
    public effectDateStart?: Date | null,
    public effectDateEnd?: Date | null,
    public remark?: string | null,
    public receiveType?: string | null,
    public receiveAddrZip?: string | null,
    public receiveAddrCity?: string | null,
    public receiveAddrTown?: string | null,
    public receiveAddrOther?: string | null,
    public certificateIdList?: string | null,
    public subjectList?: string | null,
    public isMore7Year?: string | null,
    public isXcaCert?: string | null,
    public changeItems?: string | null,
    public changeItemsDesc?: string | null,
    public contractorId?: string | null,
    public status?: string | null,
    public reviewStaff?: string | null,
    public reviewTime?: Date | null,
    public reviewDesc?: string | null,
    public creatUser?: string | null,
    public creatTime?: Date | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public transferFrom?: string | null,
    public transferTime?: Date | null,

    public applyItems?: string[] | null,
    public picFile?: File | null,
    public picFileInfo?: IAdmFile | null,
    public appendixFiles?: File[] | null,
    public appendixFilesInfo?: IAdmFile[] | null,
  ) {
    this.execType = '1';
    this.applyItem = '0';


    // this.engEngrLicenseId = '1900010001';
    //     this.engEngrLicenseVersion = 1;
    //     this.engEngrLicenseNo = '';
    //     // this.receiveDate = '';
    //     this.engrLicenseDocNo = '000348';
    //     this.engrId = '00012299';
    //     this.identityType = '0';
    //     this.idno = 'F228456464';
    //     this.chName = '林孟儒';
    //     this.chNameRome = '';
    //     this.enName = 'LIN, MENG-JU';
    //     // this.birthDate = '';
    //     this.sex = '0';
    //     this.mobileTel = '0933724869';
    //     this.email = 'jkl34032@gmail.com';
    //     this.permanentAddrZip = '114';
    //     this.permanentAddrCity = '台北市';
    //     this.permanentAddrTown = '內湖區';
    //     this.permanentAddrOther = '陽光街323號';
    //     this.contactAddrZip = '';
    //     this.contactAddrCity = '';
    //     this.contactAddrTown = '';
    //     this.contactAddrOther = '';
    //     this.execType = '2';
    //     this.compId = '28412550';
    //     this.compType = '';
    //     // this.compIdno = '';
    //     this.compChName = '台灣世曦工程顧問股份有限公司';
    //     this.compEnName = '';
    //     this.compTel1 = '0287973567*1131';
    //     this.compTel2 = '';
    //     this.compFax = '';
    //     this.compAddrZip = '100';
    //     this.compAddrCity = '臺北市';
    //     this.compAddrTown = '中正區';
    //     this.compAddrOther = '汀洲路1段350號21樓';
    //     this.compAssrEn = '';
    //     this.applyItem = '';
    //     this.applyType = '';
    //     this.receiveNo = '';
    //     // this.publicDate = '';
    //     // this.effectDateStart = '';
    //     // this.effectDateEnd = '';
    //     this.remark = '';
    //     this.receiveType = '0';
    //     this.receiveAddrZip = '100';
    //     this.receiveAddrCity = '臺北市';
    //     this.receiveAddrTown = '中正區';
    //     this.receiveAddrOther = '汀洲路1段350號21樓';
    //     this.certificateIdList = '';
    //     this.subjectList = '';
    //     this.isMore7Year = '';
    //     this.isXcaCert = '';
    //     this.changeItems = '';
    //     this.changeItemsDesc = '';
    //     this.contractorId = '';
    //     this.status = '';
    //     this.reviewStaff = '';
    //     // this.reviewTime = '';
    //     this.reviewDesc = '';
    //     this.creatUser = '';
    //     // this.creatTime = '';
    //     this.updateUser = '';
    //     // this.updateTime = '';
    //     this.transferFrom = '';
    //     // this.transferTime = '';
  }
}
