import axios from 'axios';
import { IBidPrjMilestone } from '@/shared/model/bidModel/bid-prj-milestone.model';

const baseApiUrl = 'bid-prj-milestones';

export default class BidPrjMilestoneService {
  /**
   * find all bidPRjMilestone By Criteria
   * */

  public getBidPrjMilestoneByCriteria(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
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

  public saveBidPrjMilestone(bidPrjMilestoneDTO: IBidPrjMilestone): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/insert`, bidPrjMilestoneDTO)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateExistingBidPrjModify(bidPrjMilestoneDTO: IBidPrjMilestone): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/update`, bidPrjMilestoneDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getOneBidPrjModify(bidPrjMilestoneDTO: IBidPrjMilestone): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/getOne`, bidPrjMilestoneDTO)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
