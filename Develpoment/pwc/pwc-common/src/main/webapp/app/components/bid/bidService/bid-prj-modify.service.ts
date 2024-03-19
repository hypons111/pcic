import axios from 'axios';
import { IBidPrjModify, BidPrjModify } from '@/shared/model/bidModel/bid-prj-modify.model';

const baseApiUrl = 'bid-prj-modifies';

export default class BidPrModifyService {
  /**
   * @param keys for query certain Prj given Month PrjFal data
   * @returns list of Equip
   */
  public getBidProjectModifyByKey(wkut: string, prjno: string, chgDate: Date): Promise<any> {
    const queryKey = {
      wkut: wkut,
      prjno: prjno,
      chgDate: chgDate,
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

  public saveBidProjectModify(bidPrjModifyDTO: IBidPrjModify): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidPrjModifyDTO)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findProjectAllModify(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getProjectModifyPage`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingBidPrjModify(bidPrjModifyDTO: IBidPrjModify): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, bidPrjModifyDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public mergeProjectAndUpdateModify(bidPrjModifyDTO: IBidPrjModify, mergeNo: string) {
    console.log('mergeNo', mergeNo);
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/mergeProject/${mergeNo}`, bidPrjModifyDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  //取得報表bidAaa003報表
  public printPrjModifyReportPDF(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/prjModifyReport`, criteria, { responseType: 'blob' })
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
