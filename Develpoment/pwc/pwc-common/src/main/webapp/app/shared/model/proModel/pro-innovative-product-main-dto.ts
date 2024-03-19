import { editTypeEnum } from '@/shared/enum';
import { AdmFile, IAdmFile } from '@/shared/model/adm/adm-file.model';

export interface IProInnovativeProductMainDTO {
  proInnovativeProductId?: string;
  createTime?: Date | null;
  innovateName?: string | null;
  orgName?: string | null;
  communicateDate?: Date | null;
  communicateDocNumber?: string | null;
  innovateType?: string | null;
  innovateFunction?: string | null;
  innovateQuality?: string | null;
  innovateProve?: string | null;
  patent?: string | null;
  patentStartDate?: Date | null;
  patentEndDate?: Date | null;
  constructCost?: string | null;
  maintainCost?: string | null;
  innovateCaseName?: string | null;
  innovateCaseVendor?: string | null;
  innovateCaseScale?: string | null;
  statusIP?: string | null;
  time?: Date | null;
  type?: string | null;
  addr?: string | null;
  outline?: string | null;
  memo?: string | null;
  other?: string | null;
  commReason?: string | null;
  // 新增的
  name?: string | null;
  compIdno?: string | null;
  addressCity?: object | null;
  addressTown?: string | null;
  addressVillage?: string | null;
  addressLin?: string | null;
  addressRoad?: object | null;
  chName?: string | null;
  title?: string | null;
  workTel?: string | null;
  communicateType?: object | null;
  statusInnovativeProduct?: string | null;
  statusOrgSuggestion?: string | null;
  writeCondition?: string | null;
  picFile?: File | null;
  appendixFilesMap?: File | null;
  appendixFilesInfo?: IAdmFile[] | null;
  createUser?: string | null;
  updateUser?: string | null;
  updateTime?: string | null;
  proAdmFile?: IAdmFile | null;
  acceptedOrganization?: string | null;
}

export class ProInnovativeProductMainDTO implements IProInnovativeProductMainDTO {
  constructor(
    public proInnovativeProductId?: string,
    public createTime: Date | null = new Date(),
    public innovateName?: string | null,
    public orgName?: string | null,
    public communicateDate?: Date | null,
    public communicateDocNumber?: string | null,
    public innovateType: string | null = '0',
    public innovateFunction?: string | null,
    public innovateQuality?: string | null,
    public innovateProve?: string | null,
    public patent?: string | null,
    public patentStartDate?: Date | null,
    public patentEndDate?: Date | null,
    public constructCost?: string | null,
    public maintainCost?: string | null,
    public innovateCaseName?: string | null,
    public innovateCaseVendor?: string | null,
    public innovateCaseScale?: string | null,
    public statusIP?: string | null,
    public time?: Date | null,
    public type?: string | null,
    public addr?: string | null,
    public outline?: string | null,
    public memo?: string | null,
    public other?: string | null,
    public commReason?: string | null,
    // 新增的
    public name?: string | null,
    public compIdno?: string | null,
    public addressCity?: object | null,
    public addressTown?: string | null,
    public addressVillage?: string | null,
    public addressLin?: string | null,
    public addressRoad?: object | null,
    public chName?: string | null,
    public title?: string | null,
    public workTel?: string | null,
    public communicateType?: object | null,
    public statusInnovativeProduct?: string | null,
    public statusOrgSuggestion?: string | null,
    public writeCondition?: string | null,
    public picFile?: File | null,
    public appendixFilesMap?: File | null,
    public appendixFilesInfo?: IAdmFile[] | null,
    public createUser?: string | null,
    public updateUser?: string | null,
    public updateTime?: string | null,
    public proAdmFile?: IAdmFile | null,
    public acceptedOrganization?: string | null
  ) {
    this.statusIP = editTypeEnum.DRAFT;
    this.appendixFilesInfo = [];
    this.proAdmFile = new AdmFile();
  }
}
