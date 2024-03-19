import axios from 'axios';
import { IBidPrjSur2 } from '@/shared/model/bidModel/bid-prj-sur2';
import { IBidPrjPay2 } from '@/shared/model/bidModel/bid-prj-pay2.model';

const baseApiUrl = 'bid-prj-sur-2-s';

export default class BidPrjSur2Service {
  public getBidPrjSur2ByCriteria(criteria): Promise<any> {
    return new Promise<IBidPrjSur2>((resolve, reject) => {
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
  public getOneBidPrjSur2(bidProject: IBidPrjSur2): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, bidProject)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public saveBidPrjPay2(bidPrjSur2DTO: IBidPrjSur2): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidPrjSur2DTO)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingPay2(bidPrjSur2DTO: IBidPrjSur2): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/updateOne`, bidPrjSur2DTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
