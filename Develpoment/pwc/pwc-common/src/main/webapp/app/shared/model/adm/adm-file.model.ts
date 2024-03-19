export interface IAdmFile {
  id?: number;
  moduleType?: string;
  businessType?: string;
  sourceId?: string;
  fileType?: string;
  fileNameOri?: string;
  fileName?: string;
  fileRemark?: string | null;
  s3Url?: string;
  createUser?: string;
  createTime?: Date;
  updateUser?: string | null;
  updateTime?: Date | null;
}

export class AdmFile implements IAdmFile {
  constructor(
    public id?: number,
    public moduleType?: string,
    public businessType?: string,
    public sourceId?: string,
    public fileType?: string,
    public fileNameOri?: string,
    public fileName?: string,
    public fileRemark?: string | null,
    public s3Url?: string,
    public createUser?: string,
    public createTime?: Date,
    public updateUser?: string | null,
    public updateTime?: Date | null,
  ) {}
}