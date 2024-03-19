import axios from 'axios';

import { IGeoMember } from '@/shared/model/geoModel/geo-member.model';

const baseApiUrl = 'api/geo-members';

export default class GeoMemberService {
  public find(id: number): Promise<IGeoMember> {
    return new Promise<IGeoMember>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(geoMemberId: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${geoMemberId}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IGeoMember): Promise<IGeoMember> {
    return new Promise<IGeoMember>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IGeoMember): Promise<IGeoMember> {
    return new Promise<IGeoMember>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.geoMemberId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: IGeoMember): Promise<IGeoMember> {
    return new Promise<IGeoMember>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.geoMemberId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
