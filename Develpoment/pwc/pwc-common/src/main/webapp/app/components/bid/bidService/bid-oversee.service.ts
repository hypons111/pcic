import axios from 'axios';
const baseApiUrl = 'bid-oversees';

export default class BidOverseeService {
  /**
   *
   * @param criteira 用wkut 和 prjno 去撈整個DTO
   * @returns DTO
   */
  public getBidOverseeByCriteria(criteira): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/list-by-criteria`, criteira)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
