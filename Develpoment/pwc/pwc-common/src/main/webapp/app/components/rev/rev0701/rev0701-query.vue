<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <i-form-group-check :labelStar="true" label="本會收文日期:" :dual1="$v.receiptDateStart" :dual2="$v.receiptDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.receiptDateStart.$model" :state="$v.receiptDateStart"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.receiptDateEnd.$model" :state="$v.receiptDateEnd"></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="2" content-cols="10" label="計畫類別:">
                <b-input-group>
                  <b-form-checkbox v-model="$v.check6.$model" :true-value="true" :false-value="false">可行性評估　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check7.$model" :true-value="true" :false-value="false">新興建設計畫　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check8.$model" :true-value="true" :false-value="false">基本設計　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check9.$model" :true-value="true" :false-value="false">修正計畫　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check10.$model" :true-value="true" :false-value="false">年度先期　</b-form-checkbox>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <i-button class="mt-1 ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                公共建設計畫總計
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <div class="container" v-if="queryStatus">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th rowspan="2" class="text-center">工程類別</th>
                <th rowspan="2" class="text-center">總案件數</th>
                <th colspan="6" class="text-center">程序面缺失</th>
                <th colspan="4" class="text-center">技術面缺失</th>
                <th colspan="3" class="text-center">經費面缺失</th>
                <th class="text-center">其他缺失</th>
              </tr>
              <tr>
                <th class="text-center">未依原預定期程送審件數(%)</th>
                <th class="text-center">送審前該個案工程已發包件數(%)</th>
                <th class="text-center">非屬應送本會審議之案件件數(%)</th>
                <th class="text-center">計畫程序未完備件數(%)</th>
                <th class="text-center">工程內容與計畫核定內容不符件數(%)</th>
                <th class="text-center">提送資料項目未齊全件數(%)</th>
                <th class="text-center">基本資料調查未詳實件數(%)</th>
                <th class="text-center">工程量體或數量不合理件數(%)</th>
                <th class="text-center">空間或設施配置不當件數(%)</th>
                <th class="text-center">設計規範引用不當或錯誤、工期安排未妥適件數(%)</th>
                <th class="text-center">經費架構比例不合理件數(%)</th>
                <th class="text-center">單價與數量編列內容未詳實件數(%)</th>
                <th class="text-center">大宗營建材料單價不合理件數(%)</th>
                <th class="text-center">其他</th>
              </tr>
              <tr v-for="(item,key) in table.data">
                <td class="text-left">{{item.projectType}}</td>
                <td class="text-right">{{item.cntAllDefect}}</td>
                <td class="text-right">{{item.cntProcDefect11}}({{(item.cntProcDefect11 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect12}}({{(item.cntProcDefect12 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect13}}({{(item.cntProcDefect13 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect14}}({{(item.cntProcDefect14 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect15}}({{(item.cntProcDefect15 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect16}}({{(item.cntProcDefect16 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect21}}({{(item.cntTechDefect21 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect22}}({{(item.cntTechDefect22 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect23}}({{(item.cntTechDefect23 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect24}}({{(item.cntTechDefect24 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect31}}({{(item.cntBudgetDefect31 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect32}}({{(item.cntBudgetDefect32 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect33}}({{(item.cntBudgetDefect33 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntOtherDefect4}}({{(item.cntOtherDefect4 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
              </tr>
              <tr>
                <td>合計</td>
                <td class="text-right">{{cntAllDefectAll}}</td>
                <td class="text-right">{{cntProcDefect11All}}({{(cntProcDefect11All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect12All}}({{(cntProcDefect12All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect13All}}({{(cntProcDefect13All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect14All}}({{(cntProcDefect14All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect15All}}({{(cntProcDefect15All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect16All}}({{(cntProcDefect16All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect21All}}({{(cntTechDefect21All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect22All}}({{(cntTechDefect22All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect23All}}({{(cntTechDefect23All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect24All}}({{(cntTechDefect24All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect31All}}({{(cntBudgetDefect31All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect32All}}({{(cntBudgetDefect32All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect33All}}({{(cntBudgetDefect33All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntOtherDefect4All}}({{(cntOtherDefect4All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check6.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                可行性評估
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <div class="container" v-if="queryStatus && $v.check6.$model">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th rowspan="2" class="text-center">工程類別</th>
                <th rowspan="2" class="text-center">總案件數</th>
                <th colspan="6" class="text-center">程序面缺失</th>
                <th colspan="4" class="text-center">技術面缺失</th>
                <th colspan="3" class="text-center">經費面缺失</th>
                <th class="text-center">其他缺失</th>
              </tr>
              <tr>
                <th class="text-center">未依原預定期程送審件數(%)</th>
                <th class="text-center">送審前該個案工程已發包件數(%)</th>
                <th class="text-center">非屬應送本會審議之案件件數(%)</th>
                <th class="text-center">計畫程序未完備件數(%)</th>
                <th class="text-center">工程內容與計畫核定內容不符件數(%)</th>
                <th class="text-center">提送資料項目未齊全件數(%)</th>
                <th class="text-center">基本資料調查未詳實件數(%)</th>
                <th class="text-center">工程量體或數量不合理件數(%)</th>
                <th class="text-center">空間或設施配置不當件數(%)</th>
                <th class="text-center">設計規範引用不當或錯誤、工期安排未妥適件數(%)</th>
                <th class="text-center">經費架構比例不合理件數(%)</th>
                <th class="text-center">單價與數量編列內容未詳實件數(%)</th>
                <th class="text-center">大宗營建材料單價不合理件數(%)</th>
                <th class="text-center">其他</th>
              </tr>
              <tr v-for="(item,key) in table.data">
                <td class="text-left">{{item.projectType}}</td>
                <td class="text-right">{{item.cntAllDefect}}</td>
                <td class="text-right">{{item.cntProcDefect11}}({{(item.cntProcDefect11 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect12}}({{(item.cntProcDefect12 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect13}}({{(item.cntProcDefect13 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect14}}({{(item.cntProcDefect14 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect15}}({{(item.cntProcDefect15 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect16}}({{(item.cntProcDefect16 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect21}}({{(item.cntTechDefect21 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect22}}({{(item.cntTechDefect22 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect23}}({{(item.cntTechDefect23 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect24}}({{(item.cntTechDefect24 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect31}}({{(item.cntBudgetDefect31 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect32}}({{(item.cntBudgetDefect32 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect33}}({{(item.cntBudgetDefect33 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntOtherDefect4}}({{(item.cntOtherDefect4 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
              </tr>
              <tr>
                <td>合計</td>
                <td class="text-right">{{cntAllDefectAll}}</td>
                <td class="text-right">{{cntProcDefect11All}}({{(cntProcDefect11All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect12All}}({{(cntProcDefect12All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect13All}}({{(cntProcDefect13All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect14All}}({{(cntProcDefect14All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect15All}}({{(cntProcDefect15All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect16All}}({{(cntProcDefect16All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect21All}}({{(cntTechDefect21All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect22All}}({{(cntTechDefect22All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect23All}}({{(cntTechDefect23All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect24All}}({{(cntTechDefect24All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect31All}}({{(cntBudgetDefect31All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect32All}}({{(cntBudgetDefect32All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect33All}}({{(cntBudgetDefect33All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntOtherDefect4All}}({{(cntOtherDefect4All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check7.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                新興建設計畫
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <div class="container" v-if="queryStatus && $v.check7.$model">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th rowspan="2" class="text-center">工程類別</th>
                <th rowspan="2" class="text-center">總案件數</th>
                <th colspan="6" class="text-center">程序面缺失</th>
                <th colspan="4" class="text-center">技術面缺失</th>
                <th colspan="3" class="text-center">經費面缺失</th>
                <th class="text-center">其他缺失</th>
              </tr>
              <tr>
                <th class="text-center">未依原預定期程送審件數(%)</th>
                <th class="text-center">送審前該個案工程已發包件數(%)</th>
                <th class="text-center">非屬應送本會審議之案件件數(%)</th>
                <th class="text-center">計畫程序未完備件數(%)</th>
                <th class="text-center">工程內容與計畫核定內容不符件數(%)</th>
                <th class="text-center">提送資料項目未齊全件數(%)</th>
                <th class="text-center">基本資料調查未詳實件數(%)</th>
                <th class="text-center">工程量體或數量不合理件數(%)</th>
                <th class="text-center">空間或設施配置不當件數(%)</th>
                <th class="text-center">設計規範引用不當或錯誤、工期安排未妥適件數(%)</th>
                <th class="text-center">經費架構比例不合理件數(%)</th>
                <th class="text-center">單價與數量編列內容未詳實件數(%)</th>
                <th class="text-center">大宗營建材料單價不合理件數(%)</th>
                <th class="text-center">其他</th>
              </tr>
              <tr v-for="(item,key) in table.data">
                <td class="text-left">{{item.projectType}}</td>
                <td class="text-right">{{item.cntAllDefect}}</td>
                <td class="text-right">{{item.cntProcDefect11}}({{(item.cntProcDefect11 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect12}}({{(item.cntProcDefect12 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect13}}({{(item.cntProcDefect13 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect14}}({{(item.cntProcDefect14 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect15}}({{(item.cntProcDefect15 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect16}}({{(item.cntProcDefect16 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect21}}({{(item.cntTechDefect21 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect22}}({{(item.cntTechDefect22 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect23}}({{(item.cntTechDefect23 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect24}}({{(item.cntTechDefect24 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect31}}({{(item.cntBudgetDefect31 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect32}}({{(item.cntBudgetDefect32 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect33}}({{(item.cntBudgetDefect33 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntOtherDefect4}}({{(item.cntOtherDefect4 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
              </tr>
              <tr>
                <td>合計</td>
                <td class="text-right">{{cntAllDefectAll}}</td>
                <td class="text-right">{{cntProcDefect11All}}({{(cntProcDefect11All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect12All}}({{(cntProcDefect12All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect13All}}({{(cntProcDefect13All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect14All}}({{(cntProcDefect14All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect15All}}({{(cntProcDefect15All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect16All}}({{(cntProcDefect16All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect21All}}({{(cntTechDefect21All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect22All}}({{(cntTechDefect22All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect23All}}({{(cntTechDefect23All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect24All}}({{(cntTechDefect24All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect31All}}({{(cntBudgetDefect31All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect32All}}({{(cntBudgetDefect32All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect33All}}({{(cntBudgetDefect33All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntOtherDefect4All}}({{(cntOtherDefect4All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check8.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                基本計畫
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <div class="container" v-if="queryStatus && $v.check8.$model">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th rowspan="2" class="text-center">工程類別</th>
                <th rowspan="2" class="text-center">總案件數</th>
                <th colspan="6" class="text-center">程序面缺失</th>
                <th colspan="4" class="text-center">技術面缺失</th>
                <th colspan="3" class="text-center">經費面缺失</th>
                <th class="text-center">其他缺失</th>
              </tr>
              <tr>
                <th class="text-center">未依原預定期程送審件數(%)</th>
                <th class="text-center">送審前該個案工程已發包件數(%)</th>
                <th class="text-center">非屬應送本會審議之案件件數(%)</th>
                <th class="text-center">計畫程序未完備件數(%)</th>
                <th class="text-center">工程內容與計畫核定內容不符件數(%)</th>
                <th class="text-center">提送資料項目未齊全件數(%)</th>
                <th class="text-center">基本資料調查未詳實件數(%)</th>
                <th class="text-center">工程量體或數量不合理件數(%)</th>
                <th class="text-center">空間或設施配置不當件數(%)</th>
                <th class="text-center">設計規範引用不當或錯誤、工期安排未妥適件數(%)</th>
                <th class="text-center">經費架構比例不合理件數(%)</th>
                <th class="text-center">單價與數量編列內容未詳實件數(%)</th>
                <th class="text-center">大宗營建材料單價不合理件數(%)</th>
                <th class="text-center">其他</th>
              </tr>
              <tr v-for="(item,key) in table.data">
                <td class="text-left">{{item.projectType}}</td>
                <td class="text-right">{{item.cntAllDefect}}</td>
                <td class="text-right">{{item.cntProcDefect11}}({{(item.cntProcDefect11 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect12}}({{(item.cntProcDefect12 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect13}}({{(item.cntProcDefect13 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect14}}({{(item.cntProcDefect14 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect15}}({{(item.cntProcDefect15 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect16}}({{(item.cntProcDefect16 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect21}}({{(item.cntTechDefect21 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect22}}({{(item.cntTechDefect22 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect23}}({{(item.cntTechDefect23 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect24}}({{(item.cntTechDefect24 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect31}}({{(item.cntBudgetDefect31 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect32}}({{(item.cntBudgetDefect32 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect33}}({{(item.cntBudgetDefect33 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntOtherDefect4}}({{(item.cntOtherDefect4 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
              </tr>
              <tr>
                <td>合計</td>
                <td class="text-right">{{cntAllDefectAll}}</td>
                <td class="text-right">{{cntProcDefect11All}}({{(cntProcDefect11All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect12All}}({{(cntProcDefect12All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect13All}}({{(cntProcDefect13All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect14All}}({{(cntProcDefect14All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect15All}}({{(cntProcDefect15All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect16All}}({{(cntProcDefect16All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect21All}}({{(cntTechDefect21All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect22All}}({{(cntTechDefect22All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect23All}}({{(cntTechDefect23All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect24All}}({{(cntTechDefect24All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect31All}}({{(cntBudgetDefect31All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect32All}}({{(cntBudgetDefect32All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect33All}}({{(cntBudgetDefect33All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntOtherDefect4All}}({{(cntOtherDefect4All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check9.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                修正計畫
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <div class="container" v-if="queryStatus && $v.check9.$model">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th rowspan="2" class="text-center">工程類別</th>
                <th rowspan="2" class="text-center">總案件數</th>
                <th colspan="6" class="text-center">程序面缺失</th>
                <th colspan="4" class="text-center">技術面缺失</th>
                <th colspan="3" class="text-center">經費面缺失</th>
                <th class="text-center">其他缺失</th>
              </tr>
              <tr>
                <th class="text-center">未依原預定期程送審件數(%)</th>
                <th class="text-center">送審前該個案工程已發包件數(%)</th>
                <th class="text-center">非屬應送本會審議之案件件數(%)</th>
                <th class="text-center">計畫程序未完備件數(%)</th>
                <th class="text-center">工程內容與計畫核定內容不符件數(%)</th>
                <th class="text-center">提送資料項目未齊全件數(%)</th>
                <th class="text-center">基本資料調查未詳實件數(%)</th>
                <th class="text-center">工程量體或數量不合理件數(%)</th>
                <th class="text-center">空間或設施配置不當件數(%)</th>
                <th class="text-center">設計規範引用不當或錯誤、工期安排未妥適件數(%)</th>
                <th class="text-center">經費架構比例不合理件數(%)</th>
                <th class="text-center">單價與數量編列內容未詳實件數(%)</th>
                <th class="text-center">大宗營建材料單價不合理件數(%)</th>
                <th class="text-center">其他</th>
              </tr>
              <tr v-for="(item,key) in table.data">
                <td class="text-left">{{item.projectType}}</td>
                <td class="text-right">{{item.cntAllDefect}}</td>
                <td class="text-right">{{item.cntProcDefect11}}({{(item.cntProcDefect11 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect12}}({{(item.cntProcDefect12 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect13}}({{(item.cntProcDefect13 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect14}}({{(item.cntProcDefect14 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect15}}({{(item.cntProcDefect15 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect16}}({{(item.cntProcDefect16 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect21}}({{(item.cntTechDefect21 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect22}}({{(item.cntTechDefect22 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect23}}({{(item.cntTechDefect23 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect24}}({{(item.cntTechDefect24 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect31}}({{(item.cntBudgetDefect31 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect32}}({{(item.cntBudgetDefect32 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect33}}({{(item.cntBudgetDefect33 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntOtherDefect4}}({{(item.cntOtherDefect4 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
              </tr>
              <tr>
                <td>合計</td>
                <td class="text-right">{{cntAllDefectAll}}</td>
                <td class="text-right">{{cntProcDefect11All}}({{(cntProcDefect11All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect12All}}({{(cntProcDefect12All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect13All}}({{(cntProcDefect13All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect14All}}({{(cntProcDefect14All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect15All}}({{(cntProcDefect15All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect16All}}({{(cntProcDefect16All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect21All}}({{(cntTechDefect21All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect22All}}({{(cntTechDefect22All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect23All}}({{(cntTechDefect23All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect24All}}({{(cntTechDefect24All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect31All}}({{(cntBudgetDefect31All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect32All}}({{(cntBudgetDefect32All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect33All}}({{(cntBudgetDefect33All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntOtherDefect4All}}({{(cntOtherDefect4All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check10.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                年度先期
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <div class="container" v-if="queryStatus && $v.check10.$model">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th rowspan="2" class="text-center">工程類別</th>
                <th rowspan="2" class="text-center">總案件數</th>
                <th colspan="6" class="text-center">程序面缺失</th>
                <th colspan="4" class="text-center">技術面缺失</th>
                <th colspan="3" class="text-center">經費面缺失</th>
                <th class="text-center">其他缺失</th>
              </tr>
              <tr>
                <th class="text-center">未依原預定期程送審件數(%)</th>
                <th class="text-center">送審前該個案工程已發包件數(%)</th>
                <th class="text-center">非屬應送本會審議之案件件數(%)</th>
                <th class="text-center">計畫程序未完備件數(%)</th>
                <th class="text-center">工程內容與計畫核定內容不符件數(%)</th>
                <th class="text-center">提送資料項目未齊全件數(%)</th>
                <th class="text-center">基本資料調查未詳實件數(%)</th>
                <th class="text-center">工程量體或數量不合理件數(%)</th>
                <th class="text-center">空間或設施配置不當件數(%)</th>
                <th class="text-center">設計規範引用不當或錯誤、工期安排未妥適件數(%)</th>
                <th class="text-center">經費架構比例不合理件數(%)</th>
                <th class="text-center">單價與數量編列內容未詳實件數(%)</th>
                <th class="text-center">大宗營建材料單價不合理件數(%)</th>
                <th class="text-center">其他</th>
              </tr>
              <tr v-for="(item,key) in table.data">
                <td class="text-left">{{item.projectType}}</td>
                <td class="text-right">{{item.cntAllDefect}}</td>
                <td class="text-right">{{item.cntProcDefect11}}({{(item.cntProcDefect11 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect12}}({{(item.cntProcDefect12 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect13}}({{(item.cntProcDefect13 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect14}}({{(item.cntProcDefect14 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect15}}({{(item.cntProcDefect15 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntProcDefect16}}({{(item.cntProcDefect16 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect21}}({{(item.cntTechDefect21 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect22}}({{(item.cntTechDefect22 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect23}}({{(item.cntTechDefect23 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntTechDefect24}}({{(item.cntTechDefect24 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect31}}({{(item.cntBudgetDefect31 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect32}}({{(item.cntBudgetDefect32 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntBudgetDefect33}}({{(item.cntBudgetDefect33 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
                <td class="text-right">{{item.cntOtherDefect4}}({{(item.cntOtherDefect4 / item.cntAllDefect * 100).toFixed(1)}}{{item.cntAllDefect!=0 ?'%':''}})</td>
              </tr>
              <tr>
                <td>合計</td>
                <td class="text-right">{{cntAllDefectAll}}</td>
                <td class="text-right">{{cntProcDefect11All}}({{(cntProcDefect11All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect12All}}({{(cntProcDefect12All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect13All}}({{(cntProcDefect13All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect14All}}({{(cntProcDefect14All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect15All}}({{(cntProcDefect15All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntProcDefect16All}}({{(cntProcDefect16All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect21All}}({{(cntTechDefect21All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect22All}}({{(cntTechDefect22All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect23All}}({{(cntTechDefect23All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntTechDefect24All}}({{(cntTechDefect24All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect31All}}({{(cntBudgetDefect31All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect32All}}({{(cntBudgetDefect32All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntBudgetDefect33All}}({{(cntBudgetDefect33All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
                <td class="text-right">{{cntOtherDefect4All}}({{(cntOtherDefect4All / cntAllDefectAll * 100).toFixed(1)}}{{cntAllDefectAll!=0 ?'%':''}})</td>
              </tr>
            </table>
          </b-collapse>
        </div>

      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';

export default {
  components: { iButton },
  name: 'rev0601Query',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      // if (isReloadProp.value) {
      //   handleQuery();
      // }
    });

    onMounted(() => {
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      receiptDateStart:'',
      receiptDateEnd:'',
      check6: '',
      check7: '',
      check8: '',
      check9: '',
      check10: '',
    };

    const rules = {
      receiptDateStart:{notnull: required},
      receiptDateEnd:{notnull: required},
      check6: {},
      check7: {},
      check8: {},
      check9: {},
      check10: {},
    };

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'create' ,isNotKeepAlive: true  });
    };

    //轉到編輯頁面
    const toEditView = (licWorkshopMember: any) => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'modify', licWorkshopMember: licWorkshopMember, isNotKeepAlive: true });
    };

    //加總案件數
    const cntAllDefectAll = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntAllDefect);
      return counter
    });

    //加總未依原預定期程送審件數
    const cntProcDefect11All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntProcDefect11);
      return counter
    });

    //加總
    const cntProcDefect12All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntProcDefect12);
      return counter
    });

    //加總
    const cntProcDefect12All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntProcDefect12);
      return counter
    });

    //加總
    const cntProcDefect13All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntProcDefect13);
      return counter
    });

    //加總
    const cntProcDefect14All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntProcDefect14);
      return counter
    });

    //加總
    const cntProcDefect15All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntProcDefect15);
      return counter
    });

    //加總
    const cntProcDefect16All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntProcDefect16);
      return counter
    });

    //加總
    const cntTechDefect21All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntTechDefect21);
      return counter
    });

    //加總
    const cntTechDefect22All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntTechDefect22);
      return counter
    });

    //加總
    const cntTechDefect23All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntTechDefect23);
      return counter
    });

    //加總
    const cntTechDefect24All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntTechDefect24);
      return counter
    });

    //加總
    const cntBudgetDefect31All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntBudgetDefect31);
      return counter
    });

    //加總
    const cntBudgetDefect32All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntBudgetDefect32);
      return counter
    });

    //加總
    const cntBudgetDefect33All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntBudgetDefect33);
      return counter
    });

    //加總
    const cntOtherDefect4All = computed(()=>{
      let counter=0;
      table.data.map((item)=>counter += item.cntOtherDefect4);
      return counter
    });

    const table = reactive({
      data: undefined,
    });

    //依條件查詢工作小組成員
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
      //   if (isValid) {
      //     //重置條件
      //     table.data = undefined;
      //     axios
      //       .post('/rec-projects/criteria-jpa', form)
      //       .then(({ data }) => {
      //         table.data = data.content.slice(0, data.content.length);
      //         table.totalItems = data.totalElements;
      //       })
      //       .finally()
      //       .catch(notificationErrorHandler(notificationService));
      //   }
      // });
      queryStatus.value=true;
      table.data = [
        {
          projectType : '農業建設',
          cntAllDefect : 27,
          cntProcDefect11 : 0,
          cntProcDefect12 : 0,
          cntProcDefect13 : 0,
          cntProcDefect14 : 5,
          cntProcDefect15 : 0,
          cntProcDefect16 : 0,
          cntTechDefect21 : 0,
          cntTechDefect22 : 0,
          cntTechDefect23 : 0,
          cntTechDefect24 : 0,
          cntBudgetDefect31 : 0,
          cntBudgetDefect32 : 0,
          cntBudgetDefect33 : 0,
          cntOtherDefect4 : 0,
        },
        {
          projectType : '下水道',
          cntAllDefect : 0,
          cntProcDefect11 : 0,
          cntProcDefect12 : 0,
          cntProcDefect13 : 0,
          cntProcDefect14 : 0,
          cntProcDefect15 : 0,
          cntProcDefect16 : 0,
          cntTechDefect21 : 0,
          cntTechDefect22 : 0,
          cntTechDefect23 : 0,
          cntTechDefect24 : 0,
          cntBudgetDefect31 : 0,
          cntBudgetDefect32 : 0,
          cntBudgetDefect33 : 0,
          cntOtherDefect4 : 0,
        },
        {
          projectType : '都市開發',
          cntAllDefect : 36,
          cntProcDefect11 : 0,
          cntProcDefect12 : 0,
          cntProcDefect13 : 0,
          cntProcDefect14 : 0,
          cntProcDefect15 : 0,
          cntProcDefect16 : 0,
          cntTechDefect21 : 0,
          cntTechDefect22 : 0,
          cntTechDefect23 : 0,
          cntTechDefect24 : 0,
          cntBudgetDefect31 : 0,
          cntBudgetDefect32 : 0,
          cntBudgetDefect33 : 0,
          cntOtherDefect4 : 0,
        },
      ]
    })};

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      stepVisible,
      queryStatus,
      validateState,
      checkValidity,
      handleQuery,
      reset,
      handlePaginationChanged,
      table,
      toCreateView,
      toEditView,
      cntAllDefectAll,
      cntProcDefect11All,
      cntProcDefect12All,
      cntProcDefect13All,
      cntProcDefect14All,
      cntProcDefect15All,
      cntProcDefect16All,
      cntTechDefect21All,
      cntTechDefect22All,
      cntTechDefect23All,
      cntTechDefect24All,
      cntBudgetDefect31All,
      cntBudgetDefect32All,
      cntBudgetDefect33All,
      cntOtherDefect4All,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>
