import axios from 'axios';

const baseApiUrl = 'bid-gov-checkerrs';

export default class BidGovCheckerrService {
  public findAllByCriteria(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/findAllByCriteria`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
