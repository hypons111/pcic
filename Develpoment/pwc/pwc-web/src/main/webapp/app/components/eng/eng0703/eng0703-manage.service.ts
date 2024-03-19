import axios, { AxiosResponse } from 'axios';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { IEng0703 } from '@/shared/model/eng/eng0703.model';

// service之後要拿掉
const baseApiUrl = '/eng-eng0703-r2/';

export default class EngManageService {

  // 業管新增公會資料
  public createEng0502(entity: FormData): Promise<IEng0703> {
    return new Promise<IEng0703>((resolve, reject) => {
      axios
        // 就算不加 { 'Content-Type': 'multipart/form-data' }, 以FormData傳遞時, 瀏覽器也會自動加上
        .post("/eng-eng0502-c1", entity, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  // 業管編輯公會資料
  public updateEng0502(entity: FormData): Promise<IEng0703> {
    return new Promise<IEng0703>((resolve, reject) => {
      axios
        .put("/eng-eng0502-u1", entity, 
        { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  // 查詢該公會資料
  public viewEng0502(id: string): Promise<IEng0703> {
    return new Promise<IEng0703>((resolve, reject) => {
      axios.get('/eng-eng0502-r2/' + id)
      .then((res: AxiosResponse) => {
        resolve(res.data);
      })
      .catch(err => {
        reject(err);
      })
    })
  }

  // 編輯自己公會資料
  public updateEng0506(entity: FormData): Promise<IEng0703> {
    return new Promise<IEng0703>((resolve, reject) => {
      axios
        .put("/eng-eng0506-u1", entity, 
        { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  // 檢視自己公會資料
  public viewEng0506(id: string): Promise<IEng0703> {
    return new Promise<IEng0703>((resolve, reject) => {
      axios
        .get('/eng-eng0506-r1/' + id)
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
  // 檢視單筆主管機關
  public findEng0703(id: string): Promise<IEng0703> {
    return new Promise<IEng0703>((resolve, reject) => {
      axios
        .get(baseApiUrl + id) // 記得加上id
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  // 新增主管機關
  public createEng0703(entity: FormData): Promise<IEng0703> {
    return new Promise<IEng0703>((resolve, reject) => {
      axios
        // 就算不加 { 'Content-Type': 'multipart/form-data' }, 以FormData傳遞時, 瀏覽器也會自動加上
        .post("/eng-eng0703-c1", entity, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  // 編輯主管機關
  public updateEng0703(entity: FormData): Promise<IEng0703> {
    return new Promise<IEng0703>((resolve, reject) => {
      axios
        .put("/eng-eng0703-u1", entity, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((res: AxiosResponse) => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
