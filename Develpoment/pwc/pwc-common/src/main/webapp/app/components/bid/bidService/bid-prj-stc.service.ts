import axios from 'axios';
import { IBidPrjStc } from '@/shared/model/bidModel/bid-prj-stc.model';
const baseApiUrl = 'bid-prj-stcs';

export default class BidPrjStcService {
  public getBidPrjStcByCriteria(criteria): Promise<any> {
    return new Promise<IBidPrjStc>((resolve, reject) => {
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

  public saveBidPrjStc2(formData: FormData): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, formData)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingStc2(formData: FormData): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/updateOne`, formData)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
  public getOneBidPrjStc(bidPrjStcDTO: IBidPrjStc): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, bidPrjStcDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
