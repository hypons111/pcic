import axios from 'axios';
import { IBidKpifin, BidKpifin } from '@/shared/model/bidModel/bid-kpifin.model';

const baseApiUrl = 'bid-kpifins';

export default class BidPrjKpifinService {
  public getBidKpifinByKey(kpifinKey): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, kpifinKey)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getBidKpifinListByCriteria(wkut, prjno): Promise<any> {
    const criteria = {
      wkut: wkut,
      prjno: prjno,
    };
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/list-by-criteria`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public saveBidKpifin(bidKpifin: IBidKpifin): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidKpifin)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateBidKpifin(bidKpifin: IBidKpifin): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, bidKpifin)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public deleteBidKpifin(bidKpifinPK): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/deleteOne`, bidKpifinPK)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public printBidKpifinReport(bidKpifinPK): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/report`, bidKpifinPK, { responseType: 'blob' })
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
