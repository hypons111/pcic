import axios from 'axios';
import { IBidGovPrecheck, BidGovPrecheck } from '@/shared/model/bidModel/bid-gov-precheck.model';

const baseApiUrl = 'bid-gov-prechecks';

export default class BidGovPrecheckService {
  public getGovPrecheckPageByCriteria(criteira): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/findPageByCriteria`, criteira)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public saveBidGovPrecheck(bidGovPrecheck: IBidGovPrecheck): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidGovPrecheck)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistBidGovPrecheck(bidGovPrecheck: IBidGovPrecheck): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, bidGovPrecheck)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getOneBidGovPrecheck(bidGovPrecheckPK): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, bidGovPrecheckPK)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public deleteBidGovPrecheck(bidGovPrecheckPK): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/deleteOne`, bidGovPrecheckPK)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  /**
   *
   * @param criteira wkut and prjno
   * @returns dto or null
   */
  public findProjectLatestBidGovPrecheck(criteira): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/latest`, criteira)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
