import axios from 'axios';

const baseApiUrl = 'bid-plnbas';

export default class BidPlnUnitbService {
  public findAllByCriteria(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getPages`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
