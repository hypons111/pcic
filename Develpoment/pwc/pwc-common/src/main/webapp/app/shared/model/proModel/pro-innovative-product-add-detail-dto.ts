import { IProEnginListDTO } from '@/shared/model/proModel/pro-engin-list.model';
import { IProRecommendListDTO } from '@/shared/model/proModel/pro-recommend-list.model';
import { IProInnovativeProductDTO, ProInnovativeProductDTO } from '@/shared/model/proModel/pro-innovative-product.model';

export interface IProInnovativeProductAddDetail {
  proInnovativeProductDTO?: IProInnovativeProductDTO | null;
  proEnginListDTOs?: IProEnginListDTO[] | null;
  proRecommendListDTOs?: IProRecommendListDTO[] | null;
  enginTypes?: string[] | null;
}
export class ProInnovativeProductAddDetail implements IProInnovativeProductAddDetail {
  constructor(
    public proInnovativeProductDTO?: IProInnovativeProductDTO | null,
    public proEnginListDTOs?: IProEnginListDTO[] | null,
    public proRecommendListDTOs?: IProRecommendListDTO[] | null,
    public enginTypes?: string[] | null
  ) {
    this.proInnovativeProductDTO = new ProInnovativeProductDTO();
    this.proEnginListDTOs = [];
    this.proRecommendListDTOs = [];
  }
}
