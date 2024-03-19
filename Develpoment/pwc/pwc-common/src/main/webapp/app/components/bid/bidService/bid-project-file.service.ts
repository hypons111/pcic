import axios from 'axios';

const baseApiUrl = 'bid-project-files';

export default class BidProjectFileService {
  public findBidProjectFileById(queryKey): Promise<any> {
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
}
