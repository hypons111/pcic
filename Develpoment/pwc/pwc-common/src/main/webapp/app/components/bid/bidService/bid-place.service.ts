import axios from 'axios';

export default class BidPlaceService {
  baseApiUrl = 'bid-places';

  public findMapInfoByPlaceCode(placeCode: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${this.baseApiUrl}/map-info/${placeCode}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findProjectCountByCriteria(criteria: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${this.baseApiUrl}/criteria`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findAllBidPlaces(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${this.baseApiUrl}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

}
