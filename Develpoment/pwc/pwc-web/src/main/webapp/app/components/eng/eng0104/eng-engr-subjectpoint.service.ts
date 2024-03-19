import axios from 'axios';

import { IEngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';

const baseApiUrl = '/eng-engr-subjectpoints';

export default class EngEngrSubjectpointService {
  public find(id: string): Promise<IEngEngrSubjectpoint> {
    return new Promise<IEngEngrSubjectpoint>((resolve, reject) => {
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

  public delete(id: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IEngEngrSubjectpoint): Promise<IEngEngrSubjectpoint> {
    return new Promise<IEngEngrSubjectpoint>((resolve, reject) => {
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

  public update(entity: IEngEngrSubjectpoint): Promise<IEngEngrSubjectpoint> {
    return new Promise<IEngEngrSubjectpoint>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.engEngrSubjectpointId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: IEngEngrSubjectpoint): Promise<IEngEngrSubjectpoint> {
    return new Promise<IEngEngrSubjectpoint>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.engEngrSubjectpointId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
