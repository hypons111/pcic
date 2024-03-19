export interface IEngDocNo {
  engDocNoType?: string;
  engDocNoNo?: string;
  updateTime?: Date;
  updateUser?: string;
  createTime?: Date;
  createUser?: string;
  transferFrom?: string | null;
  transferTime?: Date | null;
}

export class EngDocNo implements IEngDocNo {
  constructor(
    public engDocNoType?: string,
    public engDocNoNo?: string,
    public updateTime?: Date,
    public updateUser?: string,
    public createTime?: Date,
    public createUser?: string,
    public transferFrom?: string | null,
    public transferTime?: Date | null
  ) { }
}
