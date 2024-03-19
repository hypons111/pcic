export interface IAdmHoliday {
  id?: string;
  holidayDate?: Date;
  holidayName?: string;
  updateUser?: string;
  updateTime?: Date;
}

export class AdmHoliday implements IAdmHoliday {
  constructor(
    public id?: string,
    public holidayDate?: Date,
    public holidayName?: string,
    public updateUser?: string,
    public updateTime?: Date
  ) {}

  public setDefault() {
    return new AdmHoliday(undefined, undefined, undefined, 'SYS', new Date());
  }
}
