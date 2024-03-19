import axios from 'axios';
const baseApiUrl = 'bid-prj-prices';

export default class BidPrjPriceService {
  /**
   *
   * @param criteira 用wkut 和 prjno 去撈整個DTO
   * @returns DTO
   */
  public getBidPrjPRiceByCriteria(criteira): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getListByCriteria`, criteira)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
