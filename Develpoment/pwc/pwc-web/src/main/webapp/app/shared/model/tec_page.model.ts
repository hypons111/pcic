export interface Itec_page {
  pageId?: string;
  pageStart?: Date;
  pageTitle?: string;
  contentType?: string;
  contentTitle?: string;
  content?: string;
}

export class Tec_page implements Itec_page {
  constructor(
    public pageId?: string,
    public pageStart?: Date,
    public pageTitle?: string,
    public contentType?: string,
    public content?: string,
    public contentTitle?: string
  ) {}
}
