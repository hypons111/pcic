import axios from "axios";
import {IBidPrjModify} from "@/shared/model/bidModel/bid-prj-modify.model";
import {IBidPrjSubco} from "@/shared/model/bidModel/bid-prj-subco.model";

const baseApiUrl = 'bid-prj-subco';



export default class BidPrjSubcoService {


  public findPrjSubcoAllData(wkut, prjno): Promise<any> {
    const criteria = {
      wkut: wkut,
      prjno: prjno,
    };
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getListByCretiria`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }


  /**
   * @param keys for query certain Prj given Month PrjFal data
   * @returns list of Equip
   */
  public getBidProjectSubcoByKey(wkut: string, prjno: string, subcoNo: string): Promise<any> {
    const queryKey = {
      wkut: wkut,
      prjno: prjno,
      subcoNo: subcoNo,
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

  public findProjectAllSubco(wkut: string, prjno: string): Promise<any> {

    const criteria = {
      wkut: wkut,
      prjno: prjno,
    };

    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getProjectSubcoPage`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public saveBidProjectSubco(bidPrjSubcoDTO: IBidPrjSubco): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidPrjSubcoDTO)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingBidPrjSubco(bidPrjSubcoDTO: IBidPrjSubco): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, bidPrjSubcoDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

}

