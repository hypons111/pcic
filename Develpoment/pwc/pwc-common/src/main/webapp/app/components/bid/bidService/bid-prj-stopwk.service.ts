import axios from 'axios';
import { IBidPrjStopwk, BidPrjStopwk } from '@/shared/model/bidModel/bid-prj-stopwk.model';

const baseApiUrl = 'bid-prj-stopwks';

export default class BidPrjStopwkService {
  public getBidProjectStopwkByKey(wkut: string, prjno: string, startDate: Date): Promise<any> {
    const queryKey = {
      wkut: wkut,
      prjno: prjno,
      startDate: startDate,
    };
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, queryKey)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public saveBidProjectStopwk(bidPrjStopwk: IBidPrjStopwk): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidPrjStopwk)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findProjectAllStopwk(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getProjectAllStopwk`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingBidPrjStopwk(bidPrjStopwk: IBidPrjStopwk): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, bidPrjStopwk)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  /**
   * 取得報表
   */
  public printPrjStopwkStopReportPDF(bidPrjStopwkPK): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/prjStopwkReport`, bidPrjStopwkPK, { responseType: 'blob' })
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  /**
   * 取得復工報表
   */
  public printPrjStopwkRecoveryReportPDF(bidPrjStopwkPK): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/prjStopwkRecoveryReport`, bidPrjStopwkPK, { responseType: 'blob' })
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
