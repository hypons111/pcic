import axios from 'axios';
const baseApiUrl = 'bid-gov-check';

export default class BidGovCheckService {
  public getGovCheckPageByCriteria(criteira): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getPageByCriteria`, criteira)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getGovCheckListByCriteria(criteira): Promise<any> {
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
