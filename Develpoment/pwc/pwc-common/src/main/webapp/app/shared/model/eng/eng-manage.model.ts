import { sameAs } from './../../validators/with-message/vuelidate';
import { Time } from "highcharts";
import { IAdmFile } from "../adm/adm-file.model";

export interface IEngManage {
    newFrom?: string | null;
    contractorId?: string | null;
    applyType?: boolean | null;
    createUser?: string | null;
    engCompId?: string | null;
    type?: string | null;
    pccOrgId?: string | null;
    // user_id?: string | null;
    chName?: string | null;
    enName?: string | null;
    compIdno?: string | null;
    engCstLicenseId?: string | null;
    licenseNo?: string | null;
    establishDate?: Date | null;
    capital?: number | null; // 原本是decimal
    subjectList?: string | null;
    subjects?: string[] | null;
    masterName?: string | null;
    masterNameRome?: string | null;
    isMasterEngr?: boolean | null;
    engrIdList?: string | null;
    isUse?: boolean | null;
    remark?: string | null;
    updateTime?: Date | null;
    updateUser?: string | null;
    createTime?: Date | null;
    careateUser?: string | null;
    transferFrom?: string | null;
    transferTime?: Date | null;
    itemNo?: number;
    personName?: string | null;
    personNameRome?: string | null;
    title?: string | null;
    desc?: string | null;
    tel1?: string | null;
    tel2?: string | null;
    tel3?: string | null;
    fax?: string | null;
    mail1?: string | null;
    mail2?: string | null
    address1Zip?: string | null;
    address1City?: string | null;
    address1Town?: string | null;
    address1Other?: string | null;
    isDefault?: boolean | null;
    longitude?: number | null;
    latitude?: number | null;
    // geographys?: 
    engMemberId?: string | null;
    no?: string | null;
    // idno?: string | null;
    // birthDate?: Date | null;
    sex?: boolean | null;
    // permanentAddrZip?: string | null;
    // permanentAddrCity?: string | null;
    // permanentAddrTown?: string | null;
    // permanentAddrOther?: string | null;
    // contactAddrZip?: string | null;
    // contactAddrCity?: string | null;
    // contactAddrTown?: string | null;
    email1?: string | null;
    email2?: string | null;
    isGovMember?: boolean | null;
    isEngr?: boolean | null;
    identityType?: string | null;
    addrForeign?: string | null;
    userId?: string | null;
    jobTypeList?: string | null;
    licenseId?: string | null;
    compId?: string | null;
    execStatus?: string | null;
    engCstLicenseVersion?: number | null;
    engCstLicenseNo?: string | null;
    cstLicenseDocNo?: string | null;
    receiveDate?: Date | null;
    masterId?: string | null;
    masterNameEng?: string | null;
    masterIdno?: string | null;
    masterBirthdate?: Date | null;
    masterSex?: boolean | null;
    masterMobileTel?: string | null;
    // masterPermanentAddrZip?: string | null;
    // masterPermanentAddrCity?: string | null;
    // masterPermanentAddrTown?: string | null;
    // masterPermanentAddrOther?: string | null;
    // masterContactAddrZip?: string | null;
    // masterContactAddrCity?: string | null;
    // masterContactAddrTown?: string | null;
    // masterContactAddrOther?: string | null;
    masterEmail?: string | null;
    masterIdentityType?: string | null;
    compName?: string | null;
    compTel1?: string | null;
    compTel2?: string | null;
    compFax?: string | null;
    // compAddrZip?: string | null;
    // compAddrCity?: string | null;
    // compAddrTown?: string | null;
    // compAddrOther?: string | null;
    applyItemsList?: string | null;
    applyItem?: string | null;
    applyDate?: Date | null;
    receiveNo?: string | null;
    contactId?: string | null;
    publicDate?: Date | null;
    areaMasterOrg?: string | null;
    saleAreaList?: string | null;
    agreeLow1?: string | null;
    agreeLow2?: string | null;
    receiveType?: string | null;
    // receiveAddrZip?: string | null;
    // receiveAddrCity?: string | null;
    // receiveAddrTown?: string | null;
    // receiveAddrOther?: string | null;
    status?: string | null;
    reviewStaff?: string | null;
    reviewTime?: Date | null;
    reviewDesc?: string | null;
    changeItems?: string | null;
    changeItemsDesc?: string | null;
    transferDate?: Date | null;
    masterIsEngr?: boolean | null;

    applyItems?: string[] | null;
    picFile?: File | null;
    picFileInfo?: IAdmFile | null;
    appendixFiles?: File[] | null;
    appendixFilesInfo?: IAdmFile[] | null;
}   

export class EngManage implements IEngManage {
  constructor(
    public newFrom?: string | null,
    public contractorId?: string | null,
    public applyType?: boolean | null,
    public createUser?: string,
    
    public engCompId?: string,
    public pccOrgId?: string | null,
    public type?: string,
    // public user_id?: string | null,
    public chName?: string | null,
    public enName?: string | null,
    public compIdno?: string | null,
    public engCstLicenseId?: string | null,
    public licenseNo?: string | null,
    public establishDate?: Date | null,
    public capital?: number | null, // 原本是decimal
    public subjectList?: string | null,
    public subjects?: string[] | null,
    public masterName?: string | null,
    public masterNameRome?: string | null,
    public isMasterEngr?: boolean | null,
    public engrIdList?: string | null,
    public isUse?: boolean | null,
    public remark?: string | null,
    public updateTime?: Date | null,
    public updateUser?: string | null,
    public createTime?: Date | null,
    public careateUser?: string | null,
    public transferFrom?: string | null,
    public transferTime?: Date | null,
    public itemNo?: number,
    public personName?: string | null,
    public personNameRome?: string | null,
    public title?: string | null,
    public desc?: string | null,
    public tel1?: string | null,
    public tel2?: string | null,
    public tel3?: string | null,
    public fax?: string | null,
    public mail1?: string | null,
    public mail2?: string | null,
    public address1Zip?: string | null,
    public address1City?: string | null,
    public address1Town?: string | null,
    public address1Other?: string | null,
    // public address2City?: string | null,
    // public address2Town?: string | null,
    // public address2Other?: string | null,
    // public address3City?: string | null,
    // public address3Town?: string | null,
    // public address3Other?: string | null,
    public isDefault?: boolean | null,
    public longitude?: number | null,
    public latitude?: number | null,
    // geographys?: 
    public engMemberId?: string | null,
    public no?: string | null,
    // public idno?: string | null,
    // public birthDate?: Date | null,
    public sex?: boolean | null,
    // public permanentAddrZip?: string | null,
    // public permanentAddrCity?: string | null,
    // public permanentAddrTown?: string | null,
    // public permanentAddrOther?: string | null,
    // public contactAddrZip?: string | null,
    // public contactAddrCity?: string | null,
    // public contactAddrTown?: string | null,
    public email1?: string | null,
    public email2?: string | null,
    public isGovMember?: boolean | null,
    public isEngr?: boolean | null,
    public identityType?: string | null,
    public addrForeign?: string | null,
    public userId?: string | null,
    public jobTypeList?: string | null,
    public licenseId?: string | null,
    public compId?: string | null,
    public execStatus?: string | null,
    public engCstLicenseVersion?: number | null,
    public engCstLicenseNo?: string | null,
    public cstLicenseDocNo?: string | null,
    public receiveDate?: Date | null,
    public masterId?: string | null,
    public masterNameEng?: string | null,
    public masterIdno?: string | null,
    public masterBirthdate?: Date | null,
    public masterSex?: boolean | null,
    public masterMobileTel?: string | null,
    // public masterPermanentAddrZip?: string | null,
    // public masterPermanentAddrCity?: string | null,
    // public masterPermanentAddrTown?: string | null,
    // public masterPermanentAddrOther?: string | null,
    // public masterContactAddrZip?: string | null,
    // public masterContactAddrCity?: string | null,
    // public masterContactAddrTown?: string | null,
    // public masterContactAddrOther?: string | null,
    public masterEmail?: string | null,
    public masterIdentityType?: string | null,
    public compName?: string | null,
    public compTel1?: string | null,
    public compTel2?: string | null,
    public compFax?: string | null,
    // public compAddrZip?: string | null,
    // public compAddrCity?: string | null,
    // public compAddrTown?: string | null,
    // public compAddrOther?: string | null,
    public applyItemsList?: string | null,
    public applyItem?: string | null,
    public applyDate?: Date | null,
    public receiveNo?: string | null,
    public contactId?: string | null,
    public publicDate?: Date | null,
    public areaMasterOrg?: string | null,
    public saleAreaList?: string | null,
    public agreeLow1?: string | null,
    public agreeLow2?: string | null,
    public receiveType?: string | null,
    // public receiveAddrZip?: string | null,
    // public receiveAddrCity?: string | null,
    // public receiveAddrTown?: string | null,
    // public receiveAddrOther?: string | null,
    public status?: string | null,
    public reviewStaff?: string | null,
    public reviewTime?: Date | null,
    public reviewDesc?: string | null,
    public changeItems?: string | null,
    public changeItemsDesc?: string | null,
    public transferDate?: Date | null,
    public masterIsEngr?: boolean | null,

    public applyItems?: string[] | null,
    public picFile?: File | null,
    public picFileInfo?: IAdmFile | null,
    public appendixFiles?: File[] | null,
    public appendixFilesInfo?: IAdmFile[] | null,
    
  ) {
    // this.applyDate = new Date();
    this.appendixFiles = [];
    this.appendixFilesInfo = [];
  }
}