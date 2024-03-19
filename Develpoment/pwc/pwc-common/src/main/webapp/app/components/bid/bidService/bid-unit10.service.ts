import axios from 'axios';

import { IBidUnit10 } from '@/shared/model/bidModel/bid-unit-10.model';

const baseApiUrl = 'bid-unit-10';

export default class BidUnit10Service {
  public findAllwithNameAndCodeByKeyWord(keyWord: string): Promise<any> {
    const config = { headers: { Accept: 'application/json', 'Content-Type': 'application/json' } };
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/name-and-code/keyWord`, { keyWord: keyWord }, config)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  /**
   * 用code找bidUnit10的name
   * @param keyWord 單位code
   * @returns 單位name
   */
  public findUnitNameByCode(keyWord: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${keyWord}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  /**
   * 用code找admUnit的name
   * @param orgId 單位code
   * @returns 單位name
   */
  public findAdmAgentyCode(orgId: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`adm-agent/${orgId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findAllBidUnit10s(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findAllByCriteria(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getPages`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
