import axios from 'axios';

import { IGeoQuestion } from '@/shared/model/geoModel/geo-question.model';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';

const baseApiUrl = '/geo-questions';

export default class GeoQuestionService {
  private notificationService = useNotification();

  public find(id: string): Promise<IGeoQuestion> {
    return new Promise<IGeoQuestion>((resolve, reject) => {
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

  public create(entity: IGeoQuestion): Promise<IGeoQuestion> {
    return new Promise<IGeoQuestion>((resolve, reject) => {
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

  public update(entity: IGeoQuestion): Promise<IGeoQuestion> {
    return new Promise<IGeoQuestion>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
          this.notificationService.success(`${entity.id}:修改成功`);
        })
        .catch(notificationErrorHandler(this.notificationService));
    });
  }

  public partialUpdate(entity: IGeoQuestion): Promise<IGeoQuestion> {
    return new Promise<IGeoQuestion>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
