import { IProInnovativeProductMainDTO, ProInnovativeProductMainDTO } from '@/shared/model/proModel/pro-innovative-product-main-dto';
import { IProEnginListDTO } from '@/shared/model/proModel/pro-engin-list.model';
import { IProRecommendListDTO } from '@/shared/model/proModel/pro-recommend-list.model';
import { IProCommunicatedDTO, ProCommunicatedDTO } from '@/shared/model/proModel/pro-communicated-dto';
import { IProNoCommunicateDTO } from '@/shared/model/proModel/pro-no-communicate-dto';
import { IProApplyCompanyDTO, ProApplyCompanyDTO } from '@/shared/model/proModel/pro-apply-company-dto';
import { IProOrgSuggestionDTO, ProOrgSuggestionDTO } from '@/shared/model/proModel/pro-org-suggestion.model';

export interface IProInnovativeProductSumSearchDetail {
  proInnovativeProductMainDTO?: IProInnovativeProductMainDTO | null;
  proEnginListDTOs?: IProEnginListDTO[] | null;
  proRecommendListDTOs?: IProRecommendListDTO[] | null;
  proCommunicatedDTOs?: IProCommunicatedDTO[] | null;
  proNoCommunicateDTOs?: IProNoCommunicateDTO[] | null;
  enginTypes?: string[] | null;
  recommendTypes?: string[] | null;
  proApplyCompanyDTO?: IProApplyCompanyDTO | null;
  proOrgSuggestionDTO?: IProOrgSuggestionDTO | null;
  noCommunicateTypes?: string[] | null;
  personNames?: string[] | null;
}
export class ProInnovativeProductSumSearchDetail implements IProInnovativeProductSumSearchDetail {
  constructor(
    public proInnovativeProductMainDTO?: IProInnovativeProductMainDTO | null,
    public proEnginListDTOs?: IProEnginListDTO[] | null,
    public proRecommendListDTOs?: IProRecommendListDTO[] | null,
    public proCommunicatedDTOs?: IProCommunicatedDTO[] | null,
    public proNoCommunicateDTOs?: IProNoCommunicateDTO[] | null,
    public enginTypes?: string[] | null,
    public recommendTypes?: string[] | null,
    public proApplyCompanyDTO?: IProApplyCompanyDTO | null,
    public proOrgSuggestionDTO?: IProOrgSuggestionDTO | null,
    public noCommunicateTypes?: string[] | null,
    public personNames?: string[] | null
  ) {
    //根據資料類型給予型態
    this.proInnovativeProductMainDTO = new ProInnovativeProductMainDTO();
    this.proEnginListDTOs = [];
    this.proRecommendListDTOs = [];
    this.proCommunicatedDTOs = [];
    this.proNoCommunicateDTOs = [];
    this.proApplyCompanyDTO = new ProApplyCompanyDTO();
    this.proOrgSuggestionDTO = new ProOrgSuggestionDTO();
    // this.proNoCommunicateDTOs = [];
  }
}
