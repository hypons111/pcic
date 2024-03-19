import axios from 'axios';

const baseApiUrl = 'bid-project-units';
export default class BidProjectUnitService {
  public findBidCcutByKeyWorld(wkut: string, prjno: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/ccut/${wkut}/${prjno}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findBidCcntByKeyWorld(wkut: string, prjno: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/ccnt/${wkut}/${prjno}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findUnitsByWkutAndPrjnoAndType(wkut: string, prjno: string, type: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/units/${wkut}/${prjno}/${type}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
