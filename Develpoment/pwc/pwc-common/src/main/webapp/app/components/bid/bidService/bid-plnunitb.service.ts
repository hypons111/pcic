import axios from 'axios';

const baseApiUrl = 'bid-plnunitbs';

export default class BidPlnUnitbService {
  public findAllByCriteria(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getAll`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
