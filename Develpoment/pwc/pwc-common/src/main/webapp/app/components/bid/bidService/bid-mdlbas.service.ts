import axios from 'axios';
import { IBidMdlbas } from '@/shared/model/bidModel/bid-mdlbas.model';
const baseApiUrl = 'bid-mdlbas';

export default class BidMdlbasService {
  public getDisNoByCriteria(criteria): Promise<any> {
    return new Promise<IBidMdlbas>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/criteria`, criteria)
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
