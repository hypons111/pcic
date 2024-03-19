import axios from 'axios';

const baseApiUrl = 'bid-prj-fals';

export default class BidPrFalService {
  /**
   * @param keys for query certain Prj given Month PrjFal data
   * @returns list of Equip
   */
  public getMonthlyPrjEquip(wkut: string, prjno: string, year: string, month: string): Promise<any> {
    const queryKey = {
      wkut: wkut,
      prjno: prjno,
      year: year,
      month: month,
    };
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/monthlyPrjFal`, queryKey)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          if (err.response && err.response.status === 404) {
            resolve(null);
          }
          reject(err);
        });
    });
  }
}
