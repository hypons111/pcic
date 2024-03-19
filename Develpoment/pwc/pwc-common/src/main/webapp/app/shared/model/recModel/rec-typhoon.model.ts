export interface IrecTyphoon {
  typhoonNo?: string;
  typhoonName?: string;
  applyStartTime?: Date;
  applyEndTime?: Date;
  isLocalOnly?: boolean;
  finishDate1000?: Date;
  finishDate5000?: Date;
  bidProjectImportDate?: Date;
  centerApprovedDate?: Date;
  centerApprovedProgress?: string;
  isEditSubmitData?: boolean;
  isDenyBudgetChange?: boolean;
}

export class RecTyphoon implements IrecTyphoon {
  constructor(
    public typhoonNo?: string,
    public typhoonName?: string,
    public applyStartTime?: Date,
    public applyEndTime?: Date,
    public isLocalOnly?: boolean,
    public finishDate1000?: Date,
    public finishDate5000?: Date,
    public bidProjectImportDate?: Date,
    public centerApprovedDate?: Date,
    public centerApprovedProgress?: string,
    public isEditSubmitData?: boolean,
    public isDenyBudgetChange?: boolean
  ) {}
}
