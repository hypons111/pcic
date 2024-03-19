//責任歸屬
const prj_fal_RESPONS = [
  { key: 'A', value: '甲方' },
  { key: 'B', value: '乙方' },
  { key: 'C', value: '甲乙雙方' },
];
//處理方式
const prj_fal_METHER = [
  { key: '1', value: '通知廠商限期改善' },
  { key: '2', value: '通知連帶保證廠商履約' },
  { key: '3', value: '以監督付款方式，由分包廠商繼續施工' },
  { key: '4', value: '終止或解除契約，重行招標' },
  { key: '5', value: '其他經機關認定並訂明於契約之方式' },
];

export default class BidPrFalFormatter {
  public falReasonConverter(reason) {
    return prj_fal_RESPONS.find(ele => ele.key === reason).value;
  }

  public falMetherConverter(mether) {
    return prj_fal_METHER.find(ele => ele.key === mether).value;
  }
}
