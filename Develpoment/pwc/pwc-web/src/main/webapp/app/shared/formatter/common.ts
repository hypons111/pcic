import axios, { AxiosResponse } from 'axios';
import FileSaver from 'file-saver';
import { formatDateTime } from '@/shared/date/minguo-calendar-utils';
import { isArray as _isArray, size as _size, forEach as _forEach, keys as _keys, includes as _includes, replace as _replace, findIndex as _findIndex } from 'lodash';

// 狀態0、1轉啟用、停用
export function statusFormatter(status: string): string {
  if (status === '1') {
    return '啟用';
  } else if (status === '0') {
    return '停用';
  } else {
    return status;
  }
}

//ADM_ACCOUNT狀態轉啟用、停用
export function admAccountStatusFormatter(accountStatus: string): string {
  accountStatus === 'Y' ? (accountStatus = '啟用') : (accountStatus = '停用');
  return accountStatus;
}

// 狀態YN改true、false
export function statusConvertToBoolean(status: string): boolean {
  return status === 'Y';
}

// 數字加上千分位，可依需求決定小數位數
export function commaFormatter(value: string | number, decimalPlaces?: number) {
  if ((value === 0 || value) && !Number.isNaN(value)) {
    // 加上千分位符號
    let valueString = String(value);
    if (decimalPlaces && decimalPlaces > 0) {
      valueString = Number(value).toFixed(decimalPlaces);
    }
    return valueString.replace(/^(-?\d+?)((?:\d{3})+)(?=\.\d+$|$)/, function (all, pre, groupOf3Digital) {
      return pre + groupOf3Digital.replace(/\d{3}/g, ',$&');
    });
  }
  return value;
}

export function guildTypeFormatter(type: string): string {
  if (type === '1') {
    return '公會';
  }
  if (type === '2') {
    return '全聯會';
  }
  if (type === '3') {
    return '顧問公會';
  }
}

/**
 * for ts error mssaveblob does not exist on type navigator
 */
declare global {
  interface Navigator {
    msSaveBlob?: (blob: any, defaultName?: string) => boolean;
  }
}
/**
 * 下載導出文件
 * @param response AxiosResponse
 */

export function downloadFile(response: AxiosResponse) {
  const blob = response.data;
  if (blob.size == 0) return;

  const content = String(response.headers['content-disposition']);
  const fileName = decodeURI(
    content
      .substring(content.lastIndexOf('filename*=') + 17)
      .replace(/"/g, '')
      .replace(/\+/g, '')
  );
  const extName = fileName.substring(fileName.lastIndexOf('.'));
  try {
    const file = new File([blob], fileName, { type: `text/${extName};charset=utf-8` });
    FileSaver.saveAs(file, fileName);
  } catch (err) {
    const textFileAsBlob = new Blob([blob], { type: `text/${extName}` });
    if (window.navigator['msSaveBlob']) {
      window.navigator['msSaveBlob'](textFileAsBlob, fileName);
    }
  }
}

export function downloadFile1(response: AxiosResponse) {
  const blob = response.data;
  const content = String(response.headers['content-disposition']);
  const fileName = decodeURI(
    content
      .substring(content.lastIndexOf('filename=') + 9)
      .replace(/"/g, '')
      .replace(/\+/g, '')
  );
  const extName = fileName.substring(fileName.lastIndexOf('.'));
  try {
    const file = new File([blob], fileName, { type: `text/${extName};charset=utf-8` });
    FileSaver.saveAs(file, fileName);
  } catch (err) {
    const textFileAsBlob = new Blob([blob], { type: `text/${extName}` });
    window.navigator.msSaveBlob(textFileAsBlob, fileName);
  }
}

export function downloadDocument(pathName: string) {
  return new Promise<any>(() => {
    axios
      .post(`/adm-files/document`, pathName, { headers: { 'Content-Type': 'application/json;charset=utf-8' }, responseType: 'blob' })
      .then((response: AxiosResponse) => downloadFile(response));
  });
}

export function openPdfWindow(pathName: string) {
  return new Promise<any>(() => {
    axios
      .post(`/adm-files/document`, pathName, { headers: { 'Content-Type': 'application/json;charset=utf-8' }, responseType: 'blob' })
      .then((response: AxiosResponse) => {
        var file = new Blob([response.data], { type: 'application/pdf' });
        var fileURL = URL.createObjectURL(file);
        window.open(fileURL);
      });
  });
}

export function dateTimeFormatter(value, key, item) {
  return formatDateTime(value, '/');
}

export function parseJSON(json: string) {
  if (json) {
    try {
      return JSON.parse(json);
    } catch (e) {
      return null;
    }
  }
  return null;
}

export function formOptionsFormatter(formOptions: { value: string | boolean; text: string }[], value: string | boolean) {
  if (typeof value === 'string') {
    const valueArr: string[] = value.split(',');
    const textArr: string[] = [];
    valueArr.forEach((element: string) => {
      const opt = formOptions.find(item => item.value === element);
      if (opt) textArr.push(opt.text);
    });
    return textArr.join('、');
  } else if (typeof value === 'boolean') {
    const opt = formOptions.find(item => item.value === value);
    return opt.text;
  } else {
    return '';
  }
}

// 家慶改版，過濾空白text
export function formOptionsFormatterNoAir(
  formOptions: { value: string | boolean | number; text: string }[],
  value: string | boolean | number
) {
  if (typeof value === 'string') {
    const valueArr: string[] = value.split(',');
    const textArr: string[] = [];
    valueArr.forEach((element: string) => {
      const opt = formOptions.find(item => item.value === element);
      if (opt && opt.text !== '') textArr.push(opt.text);
    });
    return textArr.join('、');
  } else if (typeof value === 'boolean') {
    const opt = formOptions.find(item => item.value === value);
    return opt.text;
  } else if (typeof value === 'number') {
    const opt = formOptions.find(item => item.value == value);
    return opt.text;
  } else {
    return '';
  }
}

// 狀態0、1或Y、N或boolean或'true'、'false'轉是、否
export function yesNoFormatter(value: any): string {
  if (value === '1' || value === 'Y' || value === 'true' || value == true) {
    return '是';
  } else if (value === '0' || value === 'N' || value === 'false' || value == false) {
    return '否';
  } else {
    return value;
  }
}

// 狀態0、1轉不限制、特定權限
export function jurisdictionFormatter(value: any): string {
  if (value === '1') {
    return '特定權限';
  } else if (value === '0') {
    return '不限制';
  } else {
    return value;
  }
}

// 狀態0、1轉不限制、特定權限
export function moduleFormatter(value: any): string {
  if (value === '1') {
    return '特定模組';
  } else if (value === '0') {
    return '不指定';
  } else {
    return value;
  }
}

// 公告位置
export function locationFormatter(value: any): string {
  if (value === 'A') {
    return '服務網, 雲端系統 ';
  } else if (value === 'B') {
    return '服務網';
  } else if (value === 'C') {
    return '雲端系統';
  } else {
    return value;
  }
}

// 公告類別
export function kindFormatter(value: any): string {
  if (value === '1') {
    return '新聞稿';
  } else if (value === '2') {
    return '公告事項2';
  } else if (value === '3') {
    return '即時新聞澄清';
  } else {
    return value;
  }
}

//單位
export function pccUnitFormatter(unitId: string): string {
  if (unitId === '1') {
    return '本會委員會';
  }
  if (unitId === '2') {
    return '企劃處';
  }
  if (unitId === '3') {
    return '技術處';
  }
  if (unitId === '4') {
    return '工程管理處';
  }
  if (unitId === '5') {
    return '秘書處';
  }
  if (unitId === '6') {
    return '人事室';
  }
  if (unitId === '7') {
    return '主計室';
  }
  if (unitId === '8') {
    return '法規委員會';
  }
}

export function checkClassForMobile(fields: any): any {
  if (!_isArray(fields)) return fields;
  if (_size(fields) === 0) return fields;
  const noMobileShowKey = _findIndex(fields, f => { return _includes(_keys(f), 'mobileShow'); }) === -1;
  if (noMobileShowKey) return fields;
  _forEach(fields, f => {
    const keys = _keys(f);
    const isTableNoField = _includes(keys, 'key') && f.key === 'tableNo';
    if (isTableNoField) {
      f.thStyle = 'width:1%;max-width:28px;min-width:28px;';
    } else {
      const hasMobileShow = _includes(keys, 'mobileShow') && f.mobileShow;
      const thClassHasDDashNone = _includes(keys, 'thClass') && _includes(f.thClass, 'd-none');
      const tdClassHasDDashNone = _includes(keys, 'tdClass') && _includes(f.tdClass, 'd-none');
    if (hasMobileShow) {
      if (thClassHasDDashNone) f.thClass = _replace(f.thClass, 'd-none', '');
      if (tdClassHasDDashNone) f.tdClass = _replace(f.tdClass, 'd-none', '');
    } else {
      if (!thClassHasDDashNone) f.thClass += ' d-none';
      if (!tdClassHasDDashNone) f.tdClass += ' d-none';
    }
    }
  });
  return fields;
}
