import { IAdmFile } from "../adm/adm-file.model";

export interface IEngEngrCertificate {
  engEngrCertificateId?: string;
  engEngrCertificateVersion?: number;
  engEngrCertificateNo?: string;
  status?: string | null;
  reviewStaff?: string | null;
  reviewTime?: Date | null;
  reviewDesc?: string | null;
  newFrom?: string | null;
  applyItemsList?: string | null;
  applyType?: string | null;
  applyDate?: Date | null;
  engrId?: string | null;
  idno?: string | null;
  chName?: string | null;
  chNameRome?: string | null;
  enName?: string | null;
  birthDate?: Date | null;
  sex?: string | null;
  contactTelOffice?: string | null;
  contactTelHome?: string | null;
  mobileTel?: string | null;
  fax?: string | null;
  email?: string | null;
  permanentAddrZip?: string | null;
  permanentAddrCity?: string | null;
  permanentAddrTown?: string | null;
  permanentAddrOther?: string | null;
  contactAddrZip?: string | null;
  contactAddrCity?: string | null;
  contactAddrTown?: string | null;
  contactAddrOther?: string | null;
  identityType?: string;
  addrForeign?: string | null;
  subject?: string;
  engrCertificateDocNo?: string | null;
  engrCertificateDocNoOld?: string | null;
  examType?: string;
  examNo?: string | null;
  examNoOld?: string | null;
  receiveDate?: Date | null;
  receiveNo?: string | null;
  publicDate?: Date | null;
  receiveType?: string | null;
  receiveAddrZip?: string | null;
  receiveAddrCity?: string | null;
  receiveAddrTown?: string | null;
  receiveAddrOther?: string | null;
  contractorId?: string | null;
  contractorName?: string | null;
  remark?: string | null;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;

  applyItems?: string[] | null;
  picFile?: File | null;
  picFileInfo?: IAdmFile | null;
  appendixFiles?: File[] | null;
  appendixFilesInfo?: IAdmFile[] | null;
}

export class EngEngrCertificate implements IEngEngrCertificate {
  constructor(
    public engEngrCertificateId?: string,
    public engEngrCertificateVersion?: number,
    public engEngrCertificateNo?: string | null,
    public status?: string | null,
    public reviewStaff?: string | null,
    public reviewTime?: Date | null,
    public reviewDesc?: string | null,
    public newFrom?: string | null,
    public applyItemsList?: string | null,
    public applyType?: string | null,
    public applyDate?: Date | null,
    public engrId?: string | null,
    public idno?: string | null,
    public chName?: string | null,
    public chNameRome?: string | null,
    public enName?: string | null,
    public birthDate?: Date | null,
    public sex?: string | null,
    public contactTelOffice?: string | null,
    public contactTelHome?: string | null,
    public mobileTel?: string | null,
    public fax?: string | null,
    public email?: string | null,
    public permanentAddrZip?: string | null,
    public permanentAddrCity?: string | null,
    public permanentAddrTown?: string | null,
    public permanentAddrOther?: string | null,
    public contactAddrZip?: string | null,
    public contactAddrCity?: string | null,
    public contactAddrTown?: string | null,
    public contactAddrOther?: string | null,
    public identityType?: string,
    public addrForeign?: string | null,
    public subject?: string,
    public engrCertificateDocNo?: string | null,
    public engrCertificateDocNoOld?: string | null,
    public examType?: string,
    public examNo?: string | null,
    public examNoOld?: string | null,
    public receiveDate?: Date | null,
    public receiveNo?: string | null,
    public publicDate?: Date | null,
    public receiveType?: string | null,
    public receiveAddrZip?: string | null,
    public receiveAddrCity?: string | null,
    public receiveAddrTown?: string | null,
    public receiveAddrOther?: string | null,
    public contractorId?: string | null,
    public contractorName?: string | null,
    public remark?: string | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null,

    public applyItems?: string[] | null,
    public picFile?: File | null,
    public picFileInfo?: IAdmFile | null,
    public appendixFiles?: File[] | null,
    public appendixFilesInfo?: IAdmFile[] | null,
  ) {
    this.applyDate = new Date();
    this.appendixFiles = [];
    this.appendixFilesInfo = [];
  }
}


export class EngEngrExam2 implements IEngEngrCertificate {
  constructor(
    public engEngrCertificateId?: string,
    public engEngrCertificateVersion?: number,
    public engEngrCertificateNo?: string | null,
    public status?: string | null,
    public reviewStaff?: string | null,
    public reviewTime?: Date | null,
    public reviewDesc?: string | null,
    public newFrom?: string | null,
    public applyItemsList?: string | null,
    public applyType?: string | null,
    public applyDate?: Date | null,
    public engrId?: string | null,
    public idno?: string | null,
    public chName?: string | null,
    public chNameRome?: string | null,
    public enName?: string | null,
    public birthDate?: Date | null,
    public sex?: string | null,
    public contactTelOffice?: string | null,
    public contactTelHome?: string | null,
    public mobileTel?: string | null,
    public fax?: string | null,
    public email?: string | null,
    public permanentAddrZip?: string | null,
    public permanentAddrCity?: string | null,
    public permanentAddrTown?: string | null,
    public permanentAddrOther?: string | null,
    public contactAddrZip?: string | null,
    public contactAddrCity?: string | null,
    public contactAddrTown?: string | null,
    public contactAddrOther?: string | null,
    public identityType?: string,
    public addrForeign?: string | null,
    public subject?: string,
    public engrCertificateDocNo?: string | null,
    public engrCertificateDocNoOld?: string | null,
    public examType?: string,
    public examNo?: string | null,
    public examNoOld?: string | null,
    public receiveDate?: Date | null,
    public receiveNo?: string | null,
    public publicDate?: Date | null,
    public receiveType?: string | null,
    public receiveAddrZip?: string | null,
    public receiveAddrCity?: string | null,
    public receiveAddrTown?: string | null,
    public receiveAddrOther?: string | null,
    public contractorId?: string | null,
    public contractorName?: string | null,
    public remark?: string | null,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null,

    public applyItems?: string[] | null,
    public picFile?: File | null,
    public picFileInfo?: IAdmFile | null,
    public appendixFiles?: File[] | null,
    public appendixFilesInfo?: IAdmFile[] | null,

    public engEngrExamId?: string,
    public examNo1?: string | null,
    public examNo2?: string | null,
    public ranking?: number | null,
    public name?: string | null,
    public birthday?: Date | null,
    public education?: string | null,
    public addrZip?: string | null,
    public addrCity?: string | null,
    public addrTown?: string | null,
    public addrOther?: string | null,
    public importDate?: Date | null,
  ) {
    this.applyDate = new Date();
    this.appendixFiles = [];
    this.appendixFilesInfo = [];
  }
}