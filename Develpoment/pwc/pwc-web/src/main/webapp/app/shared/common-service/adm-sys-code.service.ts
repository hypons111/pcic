import axios from 'axios';
import { IAdmSysCode } from '@/shared/model/adm/adm-sys-code.model';
import { IAdmSysCodeCriteria } from '@/shared/common-service/adm-sys-code.criteria';
import { Ref, unref } from '@vue/composition-api';
import { useStore } from '@u3u/vue-hooks';

const baseApiUrl = '/adm-sys-codes';
// /criteria-jpa
export default class AdmSysCodeService {
  /**
   *
   * @param criteria 要去ADM SYS CODE 撈的條件
   * @param options  傳進來的select選項，從撈出的SYS CODE 之dataKey設定為該選項的value，SYS CODE 之value變成該選項的text
   * @returns 處理完後的選項，其實也可以不用接
   */
  public getOptionsByCriteria(criteria: IAdmSysCodeCriteria, options: any[]): any[] {
    axios
      .post(`${baseApiUrl}/criteria-jpa`, criteria)
      .then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          options.splice(0, options.length);
          data.content.forEach(item => {
            const selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.dataKey;
            selectOption.text = item.value;

            options.push(selectOption);
          });
        }
        return options;
      })
      .catch(err => {
        return [];
      });
    return options;
  }

  /**
   *
   * @param criteria 要去ADM SYS CODE 撈的條件
   * @param options  傳進來的select選項，從撈出的SYS CODE 之dataKey設定為該選項的value，SYS CODE 之value變成該選項的text
   * @returns promise，可以在處理完select選項後在 .then裡面在客製化
   */
  public getPromiseOptionsByCriteria(criteria: IAdmSysCodeCriteria, options: any[]): Promise<any[]> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/criteria-jpa`, criteria)
        .then(({ data }) => {
          options.splice(0, options.length);
          data.content.forEach(item => {
            const selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.dataKey;
            selectOption.text = item.value;

            options.push(selectOption);
          });
          resolve(options);
        })
        .catch(err => {
          return [];
        });
    });
  }

  public getAllAdmSysCodes(): Promise<IAdmSysCode[]> {
    return new Promise<IAdmSysCode[]>((resolve, reject) => {
      axios
        .get('/adm-sys-codes')
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err)
        });
    })
  }

  // public async setFormOptions(formOptions: Ref<any> | any | null, moduleType: string) {
  //   await this.getAllAdmSysCodes()
  //     .then((data) => {
  //       Object.keys(unref(formOptions)).forEach(key => {
  //         formOptions[key].splice(0, formOptions[key].length,
  //           ...data
  //             .filter(item => item.moduleType === moduleType && item.dataType === key)
  //             .map(item => {
  //               return { value: item.dataKey, text: item.value };
  //             })
  //         )
  //       })
  //     })
  // }

  public setFormOptions(formOptions: Ref<any> | any | null, moduleType: string) {
    const admSysCode = useStore().value.getters.admSysCode;
    Object.keys(unref(formOptions)).forEach(key => {
      if (Array.isArray(formOptions[key]) && formOptions[key].length === 0) {
        formOptions[key].splice(0, formOptions[key].length,
          ...admSysCode
            .filter(item => item.moduleType === moduleType && item.dataType.toLowerCase().replace(/(_\w)/g, m => m[1].toUpperCase()) === key) // dataType轉駝峰
            .map(item => ({ value: item.dataKey, text: item.value }))
        )
      }
    })
  }

}
