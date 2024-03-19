export interface Ilicense_main {
  id?: string;
  planName?: string;
  lic_class?: string;
  name?: string;
  organ_review?: string;
  date_reserve?: Date;
  date_due?: Date;
  date_actual?: Date;
}

export class License_main implements Ilicense_main {
  constructor(
    public id?: string,
    public planName?: string,
    public lic_class?: string,
    public licenseName?: string,
    public organ_review?: string,
    public date_reserve?: Date,
    public date_due?: Date,
    public date_actual?: Date
  ) {}
}
