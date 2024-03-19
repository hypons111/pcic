import axios, { AxiosResponse } from 'axios';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { IEng0101 } from '@/shared/model/eng/eng0101.model';

const baseApiUrl = '/eng-engr-certificates';

export default class EngEngrCertificateService {

  public find(entity: IEngEngrCertificate): Promise<IEng0101> {
    return new Promise<IEng0101>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/eng0101`, entity)
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public findNextVersionOne(entity: IEngEngrCertificate): Promise<IEngEngrCertificate> {
    return new Promise<IEngEngrCertificate>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/next-version/${entity.engEngrCertificateId}/${entity.engEngrCertificateVersion}`)
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public switchCase(entity: any): Promise<IEng0101> {
    return new Promise<IEng0101>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/switch-case`, entity)
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: FormData): Promise<IEng0101> {
    return new Promise<IEng0101>((resolve, reject) => {
      axios
        // 就算不加 { 'Content-Type': 'multipart/form-data' }, 以FormData傳遞時, 瀏覽器也會自動加上
        .post(`${baseApiUrl}`, entity, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: FormData): Promise<IEng0101> {
    return new Promise<IEng0101>((resolve, reject) => {
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
