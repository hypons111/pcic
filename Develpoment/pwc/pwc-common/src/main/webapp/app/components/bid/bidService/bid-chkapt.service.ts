import axios from 'axios';
const baseApiUrl = 'bid-chkapts';

export default class BidChkaptService {
  /**
   *
   * @param criteira 用wkut 和 prjno 去撈最近一筆的
   * @returns DTO
   */
  public getLatestBidChkaptByCriteria(criteira): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/latest-one-by-criteria`, criteira)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
