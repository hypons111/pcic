export interface IProNoCommunicateDTO {
  noCommunicateType?: string | null;
}
export class ProNoCommunicateDTO implements IProNoCommunicateDTO {
  constructor(public noCommunicateType?: string | null) {}
}
