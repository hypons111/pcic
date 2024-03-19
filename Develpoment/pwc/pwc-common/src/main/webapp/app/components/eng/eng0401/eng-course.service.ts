import axios from 'axios';

import { IEngCourse } from '@/shared/model/eng/eng-course.model';
import { IEng0401 } from '@/shared/model/eng/eng0401.model';

const baseApiUrl = '/eng-courses';

export default class EngCourseService {
  public find(id: string): Promise<IEng0401> {
    return new Promise<IEng0401>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/eng0401/${id}`)
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

  public create(entity: IEngCourse): Promise<IEngCourse> {
    return new Promise<IEngCourse>((resolve, reject) => {
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

  public update(entity: IEngCourse): Promise<IEngCourse> {
    return new Promise<IEngCourse>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.engCourseId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: IEngCourse): Promise<IEngCourse> {
    return new Promise<IEngCourse>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.engCourseId}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
