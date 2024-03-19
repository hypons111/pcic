export interface IProInnovativeProductProUserCriteria {
  proInnovativeProductId?: string | null;
  innovateName?: string | null;
  innovateType?: string | null;
  companyName?: string | null;
  statusInnovativeProduct?: string | null;
  statusOrgSuggestion?: string | null;
  writeCondition?: string | null;
  orgName?: string | null;
}
export class ProInnovativeProductProUserCriteria implements IProInnovativeProductProUserCriteria {
  constructor(
    public proInnovativeProductId?: string | null,
    public innovateName?: string | null,
    public innovateType?: string | null,
    public companyName?: string | null,
    public statusInnovativeProduct?: string | null,
    public statusOrgSuggestion?: string | null,
    public writeCondition?: string | null,
    public orgName?: string | null
  ) {}
}
