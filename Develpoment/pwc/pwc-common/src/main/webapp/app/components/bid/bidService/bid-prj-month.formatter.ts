import { IBidPrjMonth } from '@/shared/model/bidModel/bid-prj-month.model';

const professionalEngineeringTable = [
  { spcw01: '鋼構工程' },
  { spcw02: '擋土支撐及土方工程' },
  { spcw03: '基礎工程' },
  { spcw04: '施工塔架吊裝及模板工程' },
  { spcw05: '預拌混凝土工程' },
  { spcw06: '營建鑽探工程' },
  { spcw07: '地下管線工程' },
  { spcw08: '帷幕牆工程' },
  { spcw09: '庭園、景觀工程' },
  { spcw10: '環境保護工程' },
  { spcw11: '防水工程' },
  { spcw12: '其他經中央主管機關會同主管機關增訂或變更，並公告之項目' },
];

const buildingMaterialsTable = [
  { sorc1: { materialName: '預拌混凝土(M033102)', materialUnit: '立方米' } },
  { sorc1b: { materialName: '機拌混凝土(M033101)', materialUnit: '立方米' } },
  { sorc1c: { materialName: '瀝青混凝土(M027420)', materialUnit: '立方米' } },
  { sorc7use: { materialName: '內含AC刨除料', materialUnit: '立方米' } },
  { sorc3c1: { materialName: '內含轉爐石爐碴', materialUnit: '噸' } },
  { sorc3d1: { materialName: '內含電弧爐氧化碴', materialUnit: '立方米' } },
  { sorc6: { materialName: '低密度再生透水混凝土(M03341)', materialUnit: '立方米' } },
  { sorc3b2: { materialName: '內含焚化爐底渣', materialUnit: '噸' } },
  { sorc3e: { materialName: 'CLSM(M03377)', materialUnit: '立方米' } },
  { sorc3d3: { materialName: '內含電弧爐氧化碴', materialUnit: '立方米' } },
  { sorc3b3: { materialName: '內含焚化爐底渣', materialUnit: '噸' } },
  { sorc1d: { materialName: '水泥砂漿(M040611)', materialUnit: '立方米' } },
  { sorc2: { materialName: '砂(M040610)', materialUnit: '立方米' } },
  { sorc3: { materialName: '級配(M023191)', materialUnit: '立方米' } },
  { sorc7use2: { materialName: '內含AC刨除料', materialUnit: '立方米' } },
  { sorc3b4: { materialName: '內含焚化爐底渣', materialUnit: '噸' } },
  { sorc3d4: { materialName: '內含電弧爐氧化碴', materialUnit: '立方米' } },
  { sorc3a: { materialName: '土石方(M02331)', materialUnit: '立方米' } },
  { sorc7use3: { materialName: '內含AC刨除料', materialUnit: '立方米' } },
  { sorc3b5: { materialName: '內含焚化爐底渣', materialUnit: '噸' } },
  { sorc4: { materialName: '鋼筋(M032100)', materialUnit: '噸' } },
  { sorc5: { materialName: '鋼板型鋼(M051240)', materialUnit: '噸' } },
];
function isGreaterThanRefDate(minGouYear: string, month: string, refDate: Date): boolean {
  return new Date(parseInt(minGouYear) + 1911, parseInt(month) - 1) >= refDate;
}

export default class BidPrjMonthFormatter {
  public professionalEngineeringProjectFormat(prjMonth: IBidPrjMonth): string {
    let stringBuilder = '';

    professionalEngineeringTable.forEach(data => {
      const key = Object.keys(data)[0];
      if (prjMonth[key] == 'Y') {
        stringBuilder += Object.values(data)[0];
        stringBuilder += ',';
      }
    });
    if (stringBuilder === '') return '無';
    else return stringBuilder;
  }

  public onSiteManagementFormat(prjMonth: IBidPrjMonth): string[] {
    const stringArr = [];
    if (prjMonth.wdayeds) {
      stringArr.push(`總累計已用工期共${prjMonth.wdayeds}天`);
    }

    if (prjMonth.teckind3 > 0) {
      stringArr.push(`本月廠商專任工程人員親赴工地督導且留存督導紀錄共${prjMonth.teckind3}次`);
    } else if (prjMonth.atrate < 100 && isGreaterThanRefDate(prjMonth.syr, prjMonth.month, new Date(2015, 0))) {
      stringArr.push('本月廠商專任工程人員本月無親赴工地督導之紀錄');
    }

    if (prjMonth.teckind4 > 0) {
      stringArr.push(`本月施工廠商指派技術士於公共工程施工日誌留存簽名紀錄共${prjMonth.teckind4}人`);
    } else if (prjMonth.atrate < 100 && isGreaterThanRefDate(prjMonth.syr, prjMonth.month, new Date(2017, 10))) {
      stringArr.push('本月施工廠商未指派技術士參與');
    }

    if (prjMonth.workChk > 0) {
      stringArr.push(`本月監造現場取樣試驗報告共${prjMonth.workChk}次`);
      if (prjMonth.workChkF > 0) {
        stringArr.push(`不合格紀錄${prjMonth.workChkF}次`);
      } else {
        stringArr.push('全部合格');
      }
    }

    if (prjMonth.meterChkF > 0) {
      stringArr.push(`本月施工廠商於檢驗停留點未經檢驗或同意，即進行下一道工序次數共${prjMonth.meterChkF}次`);
    }

    if (prjMonth.meterChk > 0) {
      stringArr.push(`本月施工廠商於檢驗停留點應檢附之自主檢查表與監造單位現場查驗內容比對，發現有錯誤次數共${prjMonth.meterChk}次`);
    }

    if (prjMonth.enviCnt > 0) {
      stringArr.push(`本月環保裁罰，共${prjMonth.enviCnt}次`);
    }
    if (prjMonth.wsafeCnt > 0) {
      stringArr.push(`本月職業災害，共${prjMonth.wsafeCnt}次`);
    }
    if (prjMonth.replMeth == 'Y') {
      stringArr.push('本月廠商主動提出替代方案，本機關依政府採購法第35條規定評估後採行。');
    }

    buildingMaterialsTable.forEach(element => {
      const key = Object.keys(element)[0];
      if (prjMonth[key] > 0) {
        stringArr.push(
          `${Object.values(Object.values(element)[0])[0]}累計進場量 ${prjMonth[key]} ${Object.values(Object.values(element)[0])[1]}。`
        );
      }
    });
    if (prjMonth.sorc7 > 0) {
      stringArr.push(`AC刨除料 累計產出量　${prjMonth.sorc7} '立方米。`);
      if (prjMonth.sorc7self > 0) {
        stringArr.push(`AC刨除料 累計再利用於本工程使用量 ${prjMonth.sorc7self} '立方米。`);
      }
      if (prjMonth.sorc7oth > 0) {
        stringArr.push(`AC刨除料 累計規劃由他工程使用量 ${prjMonth.sorc7oth} '立方米。`);
      }
      if (prjMonth.sorc7tac > 0) {
        stringArr.push(`AC刨除料 累計運至AC廠數量 ${prjMonth.sorc7tac} '立方米。`);
      }
    }

    if (prjMonth.sorc8 > 0) {
      stringArr.push(`營建剩餘土石方 累計產出量 ${prjMonth.sorc8} '立方米。`);
    }
    return stringArr;
  }
}
