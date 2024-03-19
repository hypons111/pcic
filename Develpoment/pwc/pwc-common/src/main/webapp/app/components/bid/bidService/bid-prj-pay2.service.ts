import axios from 'axios';
import { IBidPrjPay2 } from '@/shared/model/bidModel/bid-prj-pay2.model';

const baseApiUrl = 'bid-prj-pay-2-s';

export default class BidPrjPay2Service {
  public getBidPrjPay2ByCriteria(criteria): Promise<any> {
    return new Promise<IBidPrjPay2>((resolve, reject) => {
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

  public saveBidPrjPay2(bidPrjPay2DTO: IBidPrjPay2): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidPrjPay2DTO)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingPay2(bidPrjPay2DTO: IBidPrjPay2): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/updateOne`, bidPrjPay2DTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getOneBidPrjPay2(bidPrjPay2DTO: IBidPrjPay2): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, bidPrjPay2DTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
