import axios from 'axios';
const baseApiUrl = 'bid-prj-rescis';

export default class BidPrjRescisService {
  /**
   *
   * @param criteria bidPrjRescis的主鍵
   * @returns DTO or Null
   */
  public getLatestBidPrjRescis(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/find-latest`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
