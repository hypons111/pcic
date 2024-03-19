import axios from 'axios';

import { IVwBidProject } from '@/shared/model/bidModel/vw-bid-project.model';
import { VwBidProjectQueryCriteria } from '@/shared/model/bidModel/vw-bid-project-query.criteria';

const baseApiUrl = 'vw-bid-projects';

export default class VwBidProjectService {
  public findByCriteria(criteria: VwBidProjectQueryCriteria): Promise<any> {
    return new Promise<IVwBidProject>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/criteria`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findWkutByAdd92(add92: string): Promise<any> {
    return new Promise<IVwBidProject>((resolve, reject) => {
      const add92json = { add92: add92 };
      axios
        .post(`${baseApiUrl}/add92`, add92json)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
