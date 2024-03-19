import axios, { AxiosResponse } from 'axios';
import { IBidProject, BidProject } from '@/shared/model/bidModel/bid-project.model';

const baseApiUrl = 'bid-projects';

export default class VwBidProjectService {
  public findBidProjectInfoByWkutAndPrjno(wkut: string, prjno: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`bid-projectInfo/${wkut}/${prjno}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findBidProjectByWkutAndPrjno(wkut: string, prjno: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${wkut}/${prjno}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  //各個單位，回傳的是一個bidUnit10陣列
  public findBidProjectSCUT(wkut: string, prjno: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      const projectPK = {
        wkut: wkut,
        prjno: prjno,
      };
      axios
        .post(`${baseApiUrl}/scut`, projectPK)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  /**
   * @param wkut pk
   * @param prjno pk
   * @returns List<BidUnit10 >
   */
  public findBidProjectCCUT(wkut: string, prjno: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      const projectPK = {
        wkut: wkut,
        prjno: prjno,
      };
      axios
        .post(`${baseApiUrl}/ccut`, projectPK)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
  /**
   * @param wkut pk
   * @param prjno pk
   * @returns List<BidUnit10 >
   */
  public findBidProjectDUST(wkut: string, prjno: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      const projectPK = {
        wkut: wkut,
        prjno: prjno,
      };
      axios
        .post(`${baseApiUrl}/dust`, projectPK)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  /**
   * @param criteria 對應後端的project criteria
   * @returns list<BidProject DTO>
   */
  public findProjectListByCriteria(criteria): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/criteria/list`, criteria)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  /**
   * @param criteria 對應後端的project criteria
   * @returns list<BidProject DTO>
   */
  public findProjectByCriteria(criteria): Promise<any> {
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

  public saveBidProject(bidProject: IBidProject): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, bidProject)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdateBidProject(bidProject: IBidProject): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}`, bidProject)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public updateBidProject(entity: FormData): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
