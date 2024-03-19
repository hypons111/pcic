import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';

export default class BidCommonService {
  constructor() {}
  private bidSysCodeTable = new BidSysCodeTable();
  /**
   * 在 BidTypeCodeTable 這個 class裡面從adm sys code 撈出一次 type code出來
   * 有了這個talbe後把type code 轉成對應的中文
   * @param typeCode type code 的代碼
   * @returns 對應的中文的 promise
   */
  //BID_003 工程類別
  public bidProjectTypeCodeConverter(typeCode: string): Promise<string> {
    return new Promise((resolve, reject) => {
      this.bidSysCodeTable.getBidSysCodeWithParam('BID_003').then(data => {
        const result = data.find(obj => {
          return obj.dataKey === typeCode;
        });
        if (result) {
          resolve(result.value);
        }
      });
    });
  }
  //BID_007 實際招標方式
  public bidActualTenderAwardCodeConverter(typeCode: string): Promise<string> {
    return new Promise((resolve, reject) => {
      this.bidSysCodeTable.getBidSysCodeWithParam('BID_007').then(data => {
        const result = data.find(obj => {
          return obj.dataKey === typeCode;
        });
        if (result) {
          resolve(result.value);
        }
      });
    });
  }
  //BID_030 機具名稱
  public bidEquipCodeConverter(typeCode: string): Promise<string> {
    return new Promise((resolve, reject) => {
      this.bidSysCodeTable.getBidSysCodeWithParam('BID_030').then(data => {
        const result = data.find(obj => {
          return obj.dataKey === typeCode;
        });
        if (result) {
          resolve(result.value);
        }
      });
    });
  }

  //BID_036 落後原因
  public bidProjectFalCodeConverter(typeCode: string): Promise<string> {
    return new Promise((resolve, reject) => {
      this.bidSysCodeTable.getBidSysCodeWithParam('BID_036').then(data => {
        const result = data.find(obj => {
          return obj.dataKey === typeCode;
        });
        if (result) {
          resolve(result.value);
        }
      });
    });
  }

  //BID_034 查核督導型式
  public bidGovCheckRoveChk5Converter(typeCode: string): Promise<string> {
    return new Promise((resolve, reject) => {
      this.bidSysCodeTable.getBidSysCodeWithParam('BID_047').then(data => {
        const result = data.find(obj => {
          return obj.dataKey === typeCode;
        });
        if (result) {
          resolve(result.value);
        }
      });
    });
  }


}
