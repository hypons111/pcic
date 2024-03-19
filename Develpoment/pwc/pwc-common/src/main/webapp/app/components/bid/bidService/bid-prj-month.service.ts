import axios from 'axios';

import { IBidPrjMonth } from '@/shared/model/bidModel/bid-prj-month.model';

const baseApiUrl = 'bid-prj-months';

export default class BidPrjMonthService {
  public getLatestPrjMonthByProjectKey(wkut: string, prjno: string): Promise<any> {
    return new Promise<IBidPrjMonth>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/latest/${wkut}/${prjno}`)
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

  public getCertainPrjMonthByProjectKey(wkut: string, prjno: string, year: string, month: string): Promise<any> {
    return new Promise<IBidPrjMonth>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${wkut}/${prjno}/${year}/${month}`)
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
  //傳回page的prjMonth
  public getAllPrjMonthByProjectKey(prjMonthCriteria: object): Promise<any> {
    return new Promise<IBidPrjMonth>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/criteria`, prjMonthCriteria)
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
  //傳回list的prjMonth
  public getAllPrjMonthListByProjectKey(prjMonthCriteria: object): Promise<any> {
    return new Promise<IBidPrjMonth>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/list-by-criteria`, prjMonthCriteria)
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
  //第一個方法傳回的是responseEntity(有帶header),這個單純是物件或是null
  public getPureLatestPrjMonthByProjectKey(wkut: string, prjno: string): Promise<any> {
    return new Promise<IBidPrjMonth>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/pureLatest/${wkut}/${prjno}`)
        .then(res => {
          if (res !== null) {
            resolve(res.data);
          } else {
            resolve(null);
          }
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  //新增project month 各月分配
  public saveProjectMonth(bidPrjMonth: IBidPrjMonth) {
    return new Promise<IBidPrjMonth>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidPrjMonth)
        .then(res => {
          if (res !== null) {
            resolve(res.data);
          } else {
            resolve(null);
          }
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  //更新部分project month
  public partiallyUpdateProjectMonth(bidPrjMonth: IBidPrjMonth) {
    return new Promise<IBidPrjMonth>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}`, bidPrjMonth)
        .then(res => {
          if (res !== null) {
            resolve(res.data);
          } else {
            resolve(null);
          }
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  //產生報表
  public printMonthPredictReport(projectPK): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/printMonthPredictReport`, projectPK, { responseType: 'blob' })
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
