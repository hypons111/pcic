import axios from 'axios';
import { IBidPrjFals } from '@/shared/model/bidModel/bid-prj-fals.model';
const baseApiUrl = 'bid-prj-falss';

export default class BidPrjFalsService {
  public getPrjFalsByCriteria(criteria): Promise<any> {
    return new Promise<IBidPrjFals>((resolve, reject) => {
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

  public saveBidPrjFals(bidPrjFalsDTO: IBidPrjFals): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidPrjFalsDTO)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingPrjFals(bidPrjFalsDTO: IBidPrjFals): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/updateOne`, bidPrjFalsDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getOneBidPrjFals(bidPrjFalsDTO: IBidPrjFals): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, bidPrjFalsDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
