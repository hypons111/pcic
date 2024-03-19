import { AxiosResponse } from 'axios';
import FileSaver from 'file-saver';
import { formatDateTime } from '@/shared/date/minguo-calendar-utils';

// 狀態0、1或Y、N或boolean或'true'、'false'轉啟用、停用
export function statusFormatter(status: any): string {
  if (status === '1' || status === 'Y' || status === 'true' || status == true) {
    return '啟用';
  } else if (status === '0' || status === 'N' || status === 'false' || status == false) {
    return '停用';
  } else {
    return status;
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

// 狀態Y、N、S、T轉成功、失敗、執行中、未執行
export function executeFormatter(value: any): string {
  if (value === 'Y') {
    return '成功';
  } else if (value === 'N') {
    return '失敗';
  } else if (value === 'S') {
    return '執行中';
  } else if (value === 'T') {
    return '未執行';
  } else {
    return value;
  }
}

// 狀態YN改true、false
export function statusConvertToBoolean(status: string): boolean {
  return status === 'Y';
}

// 數字加上千分位，可依需求決定小數位數
export function commaFormatter(value: string | number, decimalPlaces?: number) {
  if (value && !Number.isNaN(value)) {
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

export function functionCategoryFormatter(type: string): string {
  if (type === '0') {
    return '選單';
  }
  if (type === '1') {
    return '可執行功能';
  }
}

export function sysCategoryFormatter(type: string): string {
  if (type === '1') {
    return '工程雲服務網';
  }
  if (type === '2') {
    return '工程雲後臺管理';
  }
  if (type === '3') {
    return '全球資訊網';
  }
  if (type === '4') {
    return '全球資訊網後臺管理';
  }
}

export function userTypeFormatter(type: string): string {
  if (type === 'A') {
    return '機關帳號';
  }
  if (type === 'C') {
    return '公司法人帳號';
  }
  if (type === 'I') {
    return '個人帳號';
  }
}

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
  if (unitId === '9') {
    return '訴願審議委員會';
  }
  if (unitId === '10') {
    return '採購申訴審議委員會';
  }
  if (unitId === '11') {
    return '工程技術鑑定委員會';
  }
  if (unitId === '12') {
    return '技師懲戒及覆審委員會';
  }
  if (unitId === '13') {
    return '中央採購稽核小組';
  }
  if (unitId === '14') {
    return '國會聯絡組';
  }
  if (unitId === '15') {
    return '資訊推動小組';
  }
}

export function cronFornatter(cron: string): string {
  if (cron === '0 0 0/1 * * ?') {
    return '每1小時執行';
  }
  if (cron === '0 0 0/2 * * ?') {
    return '每2小時執行';
  }
  if (cron === '0 0 0/6 * * ?') {
    return '每6小時執行';
  }
  if (cron === '0 0 0 * * ?') {
    return '每日00:00執行';
  }
  if (cron === '0 0 1 * * ?') {
    return '每日01:00執行';
  }
  if (cron === '0 0 2 * * ?') {
    return '每日02:00執行';
  }
  if (cron === '0 0 3 * * ?') {
    return '每日03:00執行';
  }
  if (cron === '0 0 4 * * ?') {
    return '每日04:00執行';
  }
  if (cron === '0 0 5 * * ?') {
    return '每日05:00執行';
  }
  if (cron === '0 0 6 * * ?') {
    return '每日06:00執行';
  }
  if (cron === '0 0 7 * * ?') {
    return '每日07:00執行';
  }
  if (cron === '0 0 8 * * ?') {
    return '每日08:00執行';
  }
  if (cron === '0 0 9 * * ?') {
    return '每日09:00執行';
  }
  if (cron === '0 0 10 * * ?') {
    return '每日10:00執行';
  }
  if (cron === '0 0 11 * * ?') {
    return '每日11:00執行';
  }
  if (cron === '0 0 12 * * ?') {
    return '每日12:00執行';
  }
  if (cron === '0 0 13 * * ?') {
    return '每日13:00執行';
  }
  if (cron === '0 0 14 * * ?') {
    return '每日14:00執行';
  }
  if (cron === '0 0 15 * * ?') {
    return '每日15:00執行';
  }
  if (cron === '0 0 16 * * ?') {
    return '每日16:00執行';
  }
  if (cron === '0 0 17 * * ?') {
    return '每日17:00執行';
  }
  if (cron === '0 0 18 * * ?') {
    return '每日18:00執行';
  }
  if (cron === '0 0 19 * * ?') {
    return '每日19:00執行';
  }
  if (cron === '0 0 20 * * ?') {
    return '每日20:00執行';
  }
  if (cron === '0 0 21 * * ?') {
    return '每日21:00執行';
  }
  if (cron === '0 0 22 * * ?') {
    return '每日22:00執行';
  }
  if (cron === '0 0 23 * * ?') {
    return '每日23:00執行';
  }
}


export function businessCategoryFormatter(type: string): string {
  if (type === '1') {
    return '技師顧問';
  }
  if (type === '2') {
    return '價格資料庫';
  }
  if (type === '3') {
    return '技術資料庫';
  }
  if (type === '4') {
    return '標案管理';
  }
}

/**
 * 下載導出文件
 * @param response AxiosResponse
 */
export function downloadFile(response: AxiosResponse) {
  const blob = response.data;
  const content = String(response.headers['content-disposition']);
  const fileName = decodeURI(
    content
      .substr(content.lastIndexOf('filename*=') + 17)
      .replace(/"/g, '')
      .replace(/\+/g, '')
  );
  const extName = fileName.substring(fileName.lastIndexOf('.'));
  try {
    const file = new File([blob], fileName, { type: `text/${extName};charset=utf-8` });
    FileSaver.saveAs(file, fileName);
  } catch (err) {
    const textFileAsBlob = new Blob([blob], { type: `text/${extName}` });
    // @ts-ignore
    window.navigator.msSaveBlob(textFileAsBlob, fileName);
  }
}

export function dateTimeFormatter(value, key, item) {
  return formatDateTime(value, '/');
}

export function sexFormatter(sex: string): string {
  if (sex === 'M') {
    return '男';
  } else if (sex === 'F') {
    return '女';
  } else {
    return '未知';
  }
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
