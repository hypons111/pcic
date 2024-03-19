import axios from 'axios';

const baseApiUrl = 'bid-prj-equips';

export default class BidPrEquipService {
  /**
   * @param keys for query single Prj single Month Equip data
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
        .post(`${baseApiUrl}/monthlyEquip`, queryKey)
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
