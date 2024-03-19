import { AdmFile, IAdmFile } from '@/shared/model/adm/adm-file.model';

export interface IProRecommendListDTO {
  proRecommendListId?: number;
  proInnovativeProductId?: string | null;
  recommendType?: string | null;
  recommendName?: string | null;
  recommendTel?: string | null;
  updateUser?: string | null;
  updateTime?: Date | null;
  createUser?: string | null;
  createTime?: Date | null;
  checked?: Boolean | null;
  fileDatas?: File | null;
  proAdmFile?: IAdmFile | null;
}

export class ProRecommendListDTO implements IProRecommendListDTO {
  constructor(
    public proRecommendListId?: number,
    public proInnovativeProductId?: string | null,
    public recommendType?: string | null,
    public recommendName?: string | null,
    public recommendTel?: string | null,
    public updateUser?: string | null,
    public updateTime?: Date | null,
    public createUser?: string | null,
    public createTime?: Date | null,
    public checked?: Boolean | null,
    public fileDatas?: File | null,
    public proAdmFile?: IAdmFile | null
  ) {}
}
