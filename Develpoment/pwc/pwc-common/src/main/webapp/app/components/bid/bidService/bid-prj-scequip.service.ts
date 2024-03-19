import axios from "axios";
import {IBidPrjSubco} from "@/shared/model/bidModel/bid-prj-subco.model";
import {IBidPrjScequip} from "@/shared/model/bidModel/bid-prj-scequip.model";


const baseApiUrl = 'bid-prj-scequip';

export default class BidPrjScequipService {

  public findPrjScequipAllData(wkut, prjno,subcoNo): Promise<any> {
    const criteria = {
      wkut: wkut,
      prjno: prjno,
      subCcut:subcoNo
    };
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getScequipList`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }


  public saveBidProjectScequip(bidPrjScequipDTO: IBidPrjScequip): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidPrjScequipDTO)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingBidPrjScequip(bidPrjScequipDTO: IBidPrjScequip): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, bidPrjScequipDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }



}
