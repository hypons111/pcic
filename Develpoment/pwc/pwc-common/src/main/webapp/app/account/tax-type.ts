export default class TaxType {
  static readonly B = new TaxType('B', '營業稅');
  static readonly P = new TaxType('P', '營所稅');
  static readonly I = new TaxType('I', '綜所稅');

  private constructor(public readonly value: string, public readonly description: any) {}

  static of(value: string) {
    let taxType;
    Object.keys(TaxType).forEach(v => {
      if (TaxType[v].value === value) {
        taxType = TaxType[v];
      }
    });
    return taxType;
  }
}
