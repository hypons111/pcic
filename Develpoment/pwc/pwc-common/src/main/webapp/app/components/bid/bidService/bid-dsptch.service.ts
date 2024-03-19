import axios from 'axios';
import { IBidDsptch } from '@/shared/model/bidModel/bid-dsptch.model';

const baseApiUrl = 'bid-prj-dsptches';

export default class BidDsptchService {
  public getPrjFalsByCriteria(criteria): Promise<any> {
    return new Promise<IBidDsptch>((resolve, reject) => {
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

  public saveBidPrjFals(bidDsptchDTO: IBidDsptch): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidDsptchDTO)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingPrjFals(bidDsptchDTO: IBidDsptch): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/updateOne`, bidDsptchDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getOneBidPrjFals(bidDsptchDTO: IBidDsptch): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, bidDsptchDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
