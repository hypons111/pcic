import axios from 'axios';
export default class BidTypeCodeTable {
  baseApiUrl = '/adm-sys-codes';
  typeCodeCriteria: any = { moduleType: 'BID' };
  tpyeCodeMappingArray: any[] = [];

  public constructor() {}
  //從DB裡面撈data,回傳一個promise來改這個class的table
  public initData(): Promise<any[]> {
    return new Promise((resolve, reject) => {
      axios
        .post(`${this.baseApiUrl}/criteria-jpa`, this.typeCodeCriteria)
        .then(({ data }) => {
          resolve(data.content);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getBidSysCode(): Promise<any[]> {
    return new Promise((resolve, reject) => {
      if (this.tpyeCodeMappingArray.length > 0) {
        resolve(this.tpyeCodeMappingArray);
      } else {
        this.initData()
          .then(data => {
            if (data && data.length > 0) {
              this.tpyeCodeMappingArray.splice(0, this.tpyeCodeMappingArray.length, ...data);
            }
            resolve(data);
          })
          .catch(err => {
            reject(err);
          });
      }
    });
  }

  //回傳的陣列，拿他的dataKey和value組出下拉式選單
  public getBidSysCodeWithParam(givenDataType: string): Promise<any[]> {
    return new Promise((resolve, reject) => {
      if (this.tpyeCodeMappingArray.length > 0) {
        resolve(
          this.tpyeCodeMappingArray.filter(item => {
            return item.dataType === givenDataType;
          })
        );
      } else {
        this.initData()
          .then(data => {
            if (data && data.length > 0) {
              this.tpyeCodeMappingArray.splice(0, this.tpyeCodeMappingArray.length, ...data);
            }
            const returnArray = this.tpyeCodeMappingArray.filter(item => {
              return item.dataType === givenDataType;
            });
            resolve(returnArray);
          })
          .catch(err => {
            reject(err);
          });
      }
    });
  }
}
