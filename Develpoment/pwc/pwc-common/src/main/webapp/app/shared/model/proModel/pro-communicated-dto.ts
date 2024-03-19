export interface IProCommunicatedDTO {
  updateTimePos?: Date | null;
  personName?: string | null;
}
export class ProCommunicatedDTO implements IProCommunicatedDTO {
  constructor(public updateTimePos?: Date | null, public personName?: string | null) {}
}
