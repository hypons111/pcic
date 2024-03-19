<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          編輯災損情形
        </h5>
      </div>
      <div class="card-body col-10">
        <div>
          <div>
            <b-form-row class="align-items-top">
              <i-form-group-check
                class="col-12"
                label-cols="2"
                content-cols="4"
                label="復建工程編號："
              >
                <b-form-input disabled v-model.trim="$v.projectNo.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <i-form-group-check
                class="col-12"
                label-cols="2"
                content-cols="10"
                label="工程屬性："
              >
                <b-form-radio v-model="$v.radioSelect.$model" name="kind-radios" value="A">水利　</b-form-radio>
                <b-form-radio v-model="$v.radioSelect.$model" name="kind-radios" value="B">道路　</b-form-radio>
                <b-form-radio v-model="$v.radioSelect.$model" name="kind-radios" value="C">建築　</b-form-radio>
                <b-form-radio v-model="$v.radioSelect.$model" name="kind-radios" value="D">橋樑　</b-form-radio>
                <b-form-radio v-model="$v.radioSelect.$model" name="kind-radios" value="E">其他　</b-form-radio>
                <td>
                  <b-form-input v-model="$v.analysisA3List.$model[0] = 'E0000'" hidden/>
                  <b-form-input v-if="$v.radioSelect.$model=='E'" v-model="$v.analysisA3List.$model[1]" placeholder="請輸入其他工程屬性"></b-form-input>
                </td>
              </i-form-group-check>
            </b-form-row>
            <div v-if="$v.radioSelect.$model=='A'">
              <table border="1" cellpadding="0" cellspaceing="0">
              <tr>
                <th colspan="1" rowspan="2" class="text-center">災損情形<br>（可複選）</th>
                <th colspan="9" class="text-center">致災原因（可複選）</th>
                <th colspan="1" rowspan="2" class="text-center">現況（原保護形式）和破壞範圍<br>及嚴重程度（文字描述僅供參考<br>，請依實際狀況說明）</th>
              </tr>
              <tr>
                <th class="text-center">豪雨沖刷</th>
                <th class="text-center">基礎掏空破壞</th>
                <th class="text-center">基礎深度不足</th>
                <th class="text-center">凹岸沖刷</th>
                <th class="text-center">堤防或護岸高度不足</th>
                <th class="text-center">地震</th>
                <th class="text-center">流木或石塊等異物撞擊</th>
                <th class="text-center">介面不連續造成弱面破壞</th>
                <th class="text-center">其他</th>
              </tr>
              
              <tr>
                <th width="21%">1. 提防損壞</th>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0101"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0102"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0103"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0104"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0105"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0106"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0107"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0108"/></td>
                <td width="23%">
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="A0188"/>
				  <b-form-input v-model="$v.analysisA3List.$model[2] = 'A0188'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[3]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[4] = 'A0199'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[5]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>2. 護岸損壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0201"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0202"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0203"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0204"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0205"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0206"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0207"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0208"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="A0288"/>
				  <b-form-input v-model="$v.analysisA3List.$model[6] = 'A0288'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[7]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[8] = 'A0299'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[9]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>3. 河道內結構物(固床工、<br>　跌水工、丁壩等)損壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0301"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0302"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0303"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0304"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0305"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0306"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0307"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0308"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="A0388"/>
				  <b-form-input v-model="$v.analysisA3List.$model[10] = 'A0388'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[11]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[12] = 'A0399'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[13]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>4. 河床破壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0401"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0402"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0403"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0404"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0405"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0406"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0407"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0408"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="A0488"/>
				  <b-form-input v-model="$v.analysisA3List.$model[14] = 'A0488'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[15]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[16] = 'A0499'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[17]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>5. 溢流造成堤內淹水</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0501"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0502"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0503"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0504"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0505"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0506"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0507"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0508"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="A0588"/>
				  <b-form-input v-model="$v.analysisA3List.$model[18] = 'A0588'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[19]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[20] = 'A0599'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[21]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>6. 下水道破壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0601"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0602"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0603"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0604"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0605"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0606"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0607"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0608"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="A0688"/>
				  <b-form-input v-model="$v.analysisA3List.$model[22] = 'A0688'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[23]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[24] = 'A0699'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[25]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>
				       <b-form-input v-model="$v.analysisA3List.$model[26] = 'A0700'" hidden/>
				7. 其他<b-form-input v-model="$v.analysisA3List.$model[27]" placeholder="其他原因"></b-form-input>
				           
				</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0701"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0702"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0703"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0704"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0705"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0706"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0707"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="A0708"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="A0788"/>
				  <b-form-input v-model="$v.analysisA3List.$model[28] = 'A0788'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[29]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[30] = 'A0799'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[31]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              </table>
            </div>
<!-- ==================================================================== -->
            <div v-if="$v.radioSelect.$model=='B'">
              <table border="1" cellpadding="0" cellspaceing="0">
              <tr>
                <th colspan="1" rowspan="2" class="text-center">災損情形<br>（可複選）</th>
                <th colspan="7" class="text-center">致災原因（可複選）</th>
                <th colspan="1" rowspan="2" class="text-center">現況（原保護形式）和破壞範圍<br>及嚴重程度（文字描述僅供參考<br>，請依實際狀況說明）</th>
              </tr>
              <tr>
                <th class="text-center">豪雨沖刷</th>
                <th class="text-center">下邊坡沖刷流失或滑動</th>
                <th class="text-center">地滑</th>
                <th class="text-center">豪雨造成排水不良</th>
                <th class="text-center">土石流</th>
                <th class="text-center">地震</th>
                <th class="text-center">其他</th>
              </tr>
              <tr>
                <th width="21%">1. 道路流失</th>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0101"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0102"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0103"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0104"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0105"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0106"/></td>
                <td width="23%">
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="B0188"/>
				  <b-form-input v-model="$v.analysisA3List.$model[32] = 'B0188'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[33]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[34] = 'B0199'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[35]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>2. 道路損壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0201"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0202"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0203"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0204"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0205"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0206"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="B0288"/>
				  <b-form-input v-model="$v.analysisA3List.$model[36] = 'B0288'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[37]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[38] = 'B0299'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[39]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>3. 上邊坡損壞：邊坡滑動</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0301"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0302"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0303"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0304"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0305"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0306"/></td>
                <td>
				  <b-form-checkbox v-model="$v.analysisA2List.$model" value="B0388"/>
				  <b-form-input v-model="$v.analysisA3List.$model[40] = 'B0388'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[41]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[42] = 'B0399'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[43]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>4. 上邊坡損壞：地錨或格梁護坡破壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0401"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0402"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0403"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0404"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0405"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0406"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="B0488"/>
				  <b-form-input v-model="$v.analysisA3List.$model[44] = 'B0488'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[45]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[46] = 'B0499'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[47]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>5. 上邊坡損壞：一般護坡損壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0501"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0502"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0503"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0504"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0505"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0506"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="B0588"/>
				  <b-form-input v-model="$v.analysisA3List.$model[48] = 'B0588'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[49]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[50] = 'B0599'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[51]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>6. 下邊坡損壞：地錨擋土牆破壞(或滑動)</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0601"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0602"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0603"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0604"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0605"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0606"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="B0688"/>
				  <b-form-input v-model="$v.analysisA3List.$model[52] = 'B0688'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[53]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[54] = 'B0699'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[55]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
			        <tr>
                <th>7. 下邊坡損壞：一般擋土牆破壞(或滑動)</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0701"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0702"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0703"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0704"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0705"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0706"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="B0788"/>
				  <b-form-input v-model="$v.analysisA3List.$model[56] = 'B0788'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[57]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[58] = 'B0799'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[59]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>
				       <b-form-input v-model="$v.analysisA3List.$model[60] = 'B0800'" hidden/>
				8. 其他<b-form-input v-model="$v.analysisA3List.$model[61]" placeholder="其他原因"></b-form-input>
				</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0801"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0802"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0803"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0804"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0805"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="B0806"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="B0888"/>
				  <b-form-input v-model="$v.analysisA3List.$model[62] = 'B0888'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[63]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[64] = 'B0899'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[65]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              </table>
            </div>
<!-- ==================================================================== -->
            <div v-if="$v.radioSelect.$model=='C'">
              <table border="1" cellpadding="0" cellspaceing="0">
              <tr>
                <th colspan="1" rowspan="2" class="text-center">災損情形<br>（可複選）</th>
                <th colspan="8" class="text-center">致災原因（可複選）</th>
                <th colspan="1" rowspan="2" class="text-center">現況（原保護形式）和破壞範圍<br>及嚴重程度（文字描述僅供參考<br>，請依實際狀況說明）</th>
              </tr>
              <tr>
                <th class="text-center">強風破壞</th>
                <th class="text-center">基礎掏空</th>
                <th class="text-center">坡地災害（崩塌、土石流）</th>
                <th class="text-center">地震</th>
                <th class="text-center">滲水</th>
                <th class="text-center">異物撞擊</th>
                <th class="text-center">超載</th>
                <th class="text-center">其他</th>
              </tr>
              <tr>
                <th width="21%">1. 房屋主體結構：房屋傾斜</th>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0101"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0102"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0103"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0104"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0105"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0106"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0107"/></td>
                <td width="23%">
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="C0188"/>
				  <b-form-input v-model="$v.analysisA3List.$model[66] = 'C0188'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[67]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[68] = 'C0199'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[69]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>2. 房屋主體結構：如柱、梁、牆、樓版、基礎</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0201"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0202"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0203"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0204"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0205"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0206"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0207"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="C0288"/>
				  <b-form-input v-model="$v.analysisA3List.$model[70] = 'C0288'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[71]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[72] = 'C0299'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[73]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>3. 土建設施</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0301"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0302"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0303"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0304"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0305"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0306"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0307"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="C0388"/>
				  <b-form-input v-model="$v.analysisA3List.$model[74] = 'C0388'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[75]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[76] = 'C0399'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[77]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>4. 裝修材及設施(如牆面磁磚或粉刷、門窗、地磚等)</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0401"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0402"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0403"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0404"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0405"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0406"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0407"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="C0488"/>
				  <b-form-input v-model="$v.analysisA3List.$model[78] = 'C0488'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[79]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[80] = 'C0499'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[81]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>5. 屋頂設施</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0501"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0502"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0503"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0504"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0505"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0506"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0507"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="C0588"/>
				  <b-form-input v-model="$v.analysisA3List.$model[82] = 'C0588'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[83]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[84] = 'C0599'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[85]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>6. 水電設備</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0601"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0602"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0603"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0604"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0605"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0606"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0607"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="C0688"/>
				  <b-form-input v-model="$v.analysisA3List.$model[86] = 'C0688'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[87]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[88] = 'C0699'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[89]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
			        <tr>
                <th>7. 電梯設備</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0701"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0702"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0703"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0704"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0705"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0706"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0707"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="C0788"/>
				  <b-form-input v-model="$v.analysisA3List.$model[90] = 'C0788'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[91]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[92] = 'C0799'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[93]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>   
				       <b-form-input v-model="$v.analysisA3List.$model[94] = 'C0800'" hidden/>
				8. 其他<b-form-input v-model="$v.analysisA3List.$model[95]" placeholder="其他原因">
				</b-form-input></th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0801"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0802"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0803"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0804"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0805"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0806"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="C0807"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="C0888"/>
				  <b-form-input v-model="$v.analysisA3List.$model[96] = 'C0888'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[97]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[98] = 'C0899'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[99]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              </table>
            </div>
<!-- ==================================================================== -->
		    <div v-if="$v.radioSelect.$model=='D'">
              <table border="1" cellpadding="0" cellspaceing="0">
              <tr>
                <th colspan="1" rowspan="2" class="text-center">災損情形<br>（可複選）</th>
                <th colspan="6" class="text-center">致災原因（可複選）</th>
                <th colspan="1" rowspan="2" class="text-center">現況（原保護形式）和破壞範圍<br>及嚴重程度（文字描述僅供參考<br>，請依實際狀況說明）</th>
              </tr>
              <tr>
                <th class="text-center">豪雨沖刷或淤積</th>
                <th class="text-center">土石流或河川沖刷</th>
                <th class="text-center">流木或石塊撞擊</th>
                <th class="text-center">地滑</th>
                <th class="text-center">地震</th>
                <th class="text-center">其他</th>
              </tr>
              <tr>
                <th width="21%">1. 上部結構：落橋或變位</th>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0101"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0102"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0103"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0104"/></td>
                <td width="2%"><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0105"/></td>
                <td width="23%">
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0188"/>
				  <b-form-input v-model="$v.analysisA3List.$model[100] = 'D0188'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[101]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[102] = 'D0199'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[103]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>2. 上部結構：流失</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0201"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0202"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0203"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0204"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0205"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0288"/>
				  <b-form-input v-model="$v.analysisA3List.$model[104] = 'D0288'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[105]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[106] = 'D0299'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[107]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>3. 下部結構：橋台損壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0301"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0302"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0303"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0304"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0305"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0388"/>
				  <b-form-input v-model="$v.analysisA3List.$model[108] = 'D0388'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[109]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[110] = 'D0399'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[111]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>4. 下部結構：橋台基礎沖刷</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0401"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0402"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0403"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0404"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0405"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0488"/>
				  <b-form-input v-model="$v.analysisA3List.$model[112] = 'D0488'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[113]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[114] = 'D0499'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[115]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>5. 下部結構：引道損壞或沖刷</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0501"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0502"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0503"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0504"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0505"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0588"/>
				  <b-form-input v-model="$v.analysisA3List.$model[116] = 'D0588'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[117]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[118] = 'D0599'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[119]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>6. 下部結構：橋墩損壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0601"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0602"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0603"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0604"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0605"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0688"/>
				  <b-form-input v-model="$v.analysisA3List.$model[120] = 'D0688'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[121]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[122] = 'D0699'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[123]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
			        <tr>
                <th>7. 下部結構：橋墩基礎沖刷</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0701"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0702"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0703"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0704"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0705"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0788"/>
				  <b-form-input v-model="$v.analysisA3List.$model[124] = 'D0788'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[125]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[126] = 'D0799'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[127]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
			        <tr>
                <th>8. 下部結構：橋墩保護措施損壞</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0801"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0802"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0803"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0804"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0805"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0888"/>
				  <b-form-input v-model="$v.analysisA3List.$model[128] = 'D0888'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[129]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[130] = 'D0899'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[131]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              <tr>
                <th>
				       <b-form-input v-model="$v.analysisA3List.$model[132] = 'D0900'" hidden/>
				9. 其他<b-form-input v-model="$v.analysisA3List.$model[133]" placeholder="其他原因"></b-form-input>
				</th>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0901"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0902"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0903"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0904"/></td>
                <td><b-form-checkbox v-model="$v.analysisA2List.$model" value="D0905"/></td>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="D0988"/>
				  <b-form-input v-model="$v.analysisA3List.$model[134] = 'D0988'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[135]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[136] = 'D0999'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[137]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              </table>
            </div>
<!-- ==================================================================== -->		
			<div v-if="$v.radioSelect.$model=='E'">
              <table border="1" cellpadding="0" cellspaceing="0">
              <tr>
                <th colspan="1" width="25%" class="text-center">災損情形</th>
                <th colspan="1" width="25%" class="text-center">致災原因</th>
                <th colspan="1" width="50%" class="text-center">現況（原保護形式）和破壞範圍<br>及嚴重程度（文字描述僅供參考<br>，請依實際狀況說明）</th>
              </tr>
              <tr>
                <th>
				       <b-form-input v-model="$v.analysisA3List.$model[138] = 'E0100'" hidden/>
				1. 其他<b-form-input v-model="$v.analysisA3List.$model[139]" placeholder="其他原因"></b-form-input>
				</th>
                <td>
                  <b-form-checkbox v-model="$v.analysisA2List.$model" value="E0188"/>
				  <b-form-input v-model="$v.analysisA3List.$model[140] = 'E0188'" hidden/>
                  <b-form-input v-model="$v.analysisA3List.$model[141]" placeholder="其他原因"></b-form-input>
                </td>
                <td>
				  <b-form-input v-model="$v.analysisA3List.$model[142] = 'E0199'" hidden/>
                  <b-form-textarea v-model="$v.analysisA3List.$model[143]" rows="4" maxlength="4000" placeholder="1.既有構造物(例xxx橋)上或下游左、右岸約xxx公尺
2.毀損長度約xxx公尺，堤高x公尺"></b-form-textarea>
                </td>
              </tr>
              </table>
            </div>
<!-- ==================================================================== -->
            <div class="button-float-right">
              <b-button-toolbar class="mt-5" v-if="formStatusRef === 'modify'">
                <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
                <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
                <i-button class="ml-1" type="trash" @click="deleteAnalysis"></i-button>
              </b-button-toolbar>
              <b-button-toolbar class="mt-5" v-else-if="formStatusRef === 'create'">
                <i-button class="ml-1" type="save" @click="submitForm('create')"></i-button>
                <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs, toRef, onActivated, onMounted} from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler, notificationResponseHandler } from '@/shared/http/http-response-helper';
import { useGetters } from '@u3u/vue-hooks';
import { handleBack } from '@/router/router';

export default {
  name: 'rec0303-edit-analysis',
  props: {
    recProject: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    //傳入
    const recProjectProp = toRefs(props).recProject;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.CREATE);

    //空值表單
    const formDefault = {
      radioSelect: 'A',
      projectNo: '',
      analysisNo: '',
      analysisA1: '', //工程屬性
      analysisA2: '', //災損細項
      analysisA3: '', //災損其他
      analysisA4: '', //災損描述
      analysisA2List: [], // 災損細項
      analysisA3List: [], // 災損其他
      analysisA4List: [], // 災損描述
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      radioSelect: {},
      projectNo: {notNull: required},
      analysisNo: {},
      analysisA1: {},
      analysisA2: {},
      analysisA3: {},
      analysisA4: {},
      analysisA2List: {}, // 災損項目
      analysisA3List: {}, // 災損項目
      analysisA4List: {}, // 災損描述
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //創建初始值
    const createDefaultValue = (data: any) => {
      Object.assign(formDefault, data);
      reset();
    };

    //變更表單操作狀態，當form狀態不為查看時重置
    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    //修改表單狀態
    const changeformStatusForwardPropEdit = () => {
      formStatusRef.value = formStatusEnum.MODIFY;
    };

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

     //返回查詢畫面
    const toQueryView = () => {
      changeFormStatus(formStatusEnum.CREATE);
      createDefaultValue(formDefault);
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    //依條件查詢災損情形
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          axios
            .post('/rec-analysis/criteria-jpa', form)
            .then(({ data }) => {
              if (data.content.length !== 0) {
                changeFormStatus(formStatusEnum.MODIFY);
                createDefaultValue(data.content.slice(0, data.content.length)[0]); //將值存入
                formDefault.analysisA2List = data.content.slice(0, data.content.length)[0].analysisA2.split(",");
                formDefault.analysisA3List = data.content.slice(0, data.content.length)[0].analysisA3.split("###");
                formDefault.analysisA4List = data.content.slice(0, data.content.length)[0].analysisA4.split(",");
                reset();
              } else {
                changeFormStatus(formStatusEnum.CREATE);
                Object.assign(form, formDefault);
                createDefaultValue(form);
              }
            })
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //新增&修改送出前檢查訊息
    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            putForm();
            $bvModal.msgBoxOk('資料異動成功');
          }
          if (submitMethod === 'create') {
            //提交新增
            insertProject();
            $bvModal.msgBoxOk('資料新增成功');
          }
        } else {
          if (form.projectNo == '') {
            $bvModal.msgBoxOk('請先建立復建工程資料。');
          } else {
            $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
          }
        }
      });
    };

    //修改
    const putForm = () => {
      form.updateUser = userIdentity;
      form.updateTime = new Date();
      form.analysisA2 = form.analysisA2List.join(',');
      form.analysisA3 = form.analysisA3List.join('###');
      form.analysisA4 = form.analysisA4List.join('###');
      let url: string;
        url = `/rec-analysis/${form.analysisNo}`;
      axios
        .put(url, form)
        .then(({ data }) => {
          //填入值
          createDefaultValue(data);
          formDefault.analysisA2List = data.analysisA2.split(",");
          formDefault.analysisA3List = data.analysisA3.split("###");
          formDefault.analysisA4List = data.analysisA4.split("###");
          reset();
          isReload.value = true;
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    //刪除
    const deleteAnalysis = () => {
      $bvModal.msgBoxConfirm('刪除此災損情形資料，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-analysis/${form.analysisNo}`;
          axios
            .delete(url)
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally(handleQuery())
            .catch(notificationErrorHandler(notificationService));
            formDefault.analysisA2List=[];
            formDefault.analysisA3List=[];
            formDefault.analysisA4List=[];
        }
      });
    };

    //新增
    const insertProject = () => {
      //4個系統用欄位
      form.createUser = userIdentity;
      form.updateUser = userIdentity;
      form.createTime = new Date();
      form.updateTime = new Date();
      form.analysisA2 = form.analysisA2List.join(',');
      form.analysisA3 = form.analysisA3List.join('###');
      form.analysisA4 = form.analysisA4List.join('###');
      
      axios
        .post(`/rec-analysis`, form)
        .then(({ data }) => {
          isReload.value = true;
          createDefaultValue(data); //不再新增時將值放入
          formDefault.analysisA2List = data.analysisA2.split(",");
          formDefault.analysisA3List = data.analysisA3.split("###");
          formDefault.analysisA4List = data.analysisA4.split("###");
          reset();
          changeFormStatus(formStatusEnum.MODIFY);
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    watch(
      recProjectProp,
      () => {
          formDefault.analysisA2List=[];
          formDefault.analysisA3List=[];
          formDefault.analysisA4List=[];
          form.projectNo = recProjectProp.value['projectNo']; //傳入 projectNo
          formDefault.projectNo = recProjectProp.value['projectNo']; //傳入 projectNo
          handleQuery();
      },
      { immediate: true},
    );
      
    return {
      $v,
      formStatusEnum,
      formStatusRef,
      changeFormStatus,
      reset,
      validateState,
      submitForm,
      deleteAnalysis,
      userIdentity,
      changeformStatusForwardPropEdit,
      checkValidity,
      recProjectProp,
      formDefault,
      form
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>
