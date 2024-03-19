import axios from 'axios';

const baseApiUrl = 'bid-utils';

export default class BidUtilService {
  /**
   * 透過執行機關代碼，撈出他的查核單位列表
   *
   * @param wkutCode 執行機關代碼
   * @returns List<AdmAgentDTO>
   */
  public findBidExamineUnit(wkutCode: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/findBidExamineUnit/${wkutCode}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
  /**
   * 透過執行機關代碼，撈出他的主管機關及主辦機關列表
   *
   * @param wkutCode 執行機關代碼
   * @returns List<BidUnitDTO>
   */
  public findBidManageUnit(wkutCode: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/findBidManageUnit/${wkutCode}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
