<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">公共工程施工廠商履約計分情形計分紀錄</h5>
        </div>
        <div class="card-body col-12">
          <div class="container">
            <!-- 第一列 -->
            <div class="card mt-2 mb-2">
              <div>
                <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                  <b-row class="mt-2">
                    <i-form-group-check :label="'承辦計分人員'" :labelStar="true" :item="$v.senter">
                      <b-form-input v-model="$v.senter.$model" :state="validateState($v.senter)" lazy trim></b-form-input>
                    </i-form-group-check>
                  </b-row>
                  <b-row>
                    <i-form-group-check :label="'連絡電話'" :item="$v.sentel">
                      <i-tel v-model="$v.sentel.$model" :state="validateState($v.sentel)" showType showExt></i-tel>
                    </i-form-group-check>
                  </b-row>
                  <b-row>
                    <i-form-group-check :label="'發文日期'" :item="$v.confDate">
                      <i-date-picker v-model="$v.confDate.$model" lazy></i-date-picker>
                    </i-form-group-check>
                    <i-form-group-check :label="'計分情況'">
                      <b-form-select v-model="$v.recnt.$model">
                        <b-form-select-option value="1">初次計分</b-form-select-option>
                        <b-form-select-option value="2">第1次修正計分</b-form-select-option>
                        <b-form-select-option value="3">第2次修正計分</b-form-select-option>
                        <b-form-select-option value="4">第3次修正計分</b-form-select-option>
                        <b-form-select-option value="5">第4次修正計分</b-form-select-option>
                      </b-form-select>
                    </i-form-group-check>
                  </b-row>
                  <b-row>
                    <i-form-group-check :label="'發文文號'" :item="$v.confNumber">
                      <b-form-input v-model="$v.confNumber.$model" :state="validateState($v.confNumber)" lazy trim></b-form-input>
                    </i-form-group-check>
                    <div class="pt-2">請將書面通知廠商文號(如 工收總字第123456789號)於計分完成發文後填入本欄</div>
                  </b-row>
                </div>
                <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                  <b-row class="mt-2">
                    <i-form-group-check :label="'承辦計分人員'" :labelStar="true" :item="$v.senter">
                      <b-form-input plaintext disabled v-model="$v.senter.$model"></b-form-input>
                    </i-form-group-check>
                  </b-row>
                  <b-row>
                    <i-form-group-check :label="'連絡電話'" :item="$v.sentel">
                      <b-form-input plaintext disabled v-model="$v.sentel.$model"></b-form-input>
                    </i-form-group-check>
                  </b-row>
                  <b-row>
                    <i-form-group-check :label="'發文日期'" :item="$v.confDate">
                      <i-date-picker v-model="$v.confDate.$model" disabled></i-date-picker>
                    </i-form-group-check>
                    <i-form-group-check :label="'計分情況'">
                      <b-form-select v-model="$v.recnt.$model" disabled>
                        <b-form-select-option value="1">初次計分</b-form-select-option>
                        <b-form-select-option value="2">第1次修正計分</b-form-select-option>
                        <b-form-select-option value="3">第2次修正計分</b-form-select-option>
                        <b-form-select-option value="4">第3次修正計分</b-form-select-option>
                        <b-form-select-option value="5">第4次修正計分</b-form-select-option>
                      </b-form-select>
                    </i-form-group-check>
                  </b-row>
                  <b-row>
                    <i-form-group-check :label="'發文文號'" :item="$v.confNumber">
                      <b-form-input plaintext disabled v-model="$v.confNumber.$model"></b-form-input>
                    </i-form-group-check>
                    <div class="pt-2">請將書面通知廠商文號(如 工收總字第123456789號)於計分完成發文後填入本欄</div>
                  </b-row>
                </div>
              </div>
            </div>
            <!-- 第二列 -->
            <div class="d-flex justify-content-between">
              <div>
                <b-button variant="info" @click="toIlluPage"><h3>說明</h3></b-button>
              </div>
              <div>
                <div v-if="currentFormStatus === formStatusEnum.CREATE">
                  <h3>履約情形計分提報日期{{ formatDateInChinese(new Date()) }}</h3>
                </div>
                <div v-else-if="currentFormStatus === formStatusEnum.INFO || currentFormStatus === formStatusEnum.MODIFY">
                  <h3>履約情形計分提報日期{{ formatDateInChinese($v.idate.$model) }}</h3>
                </div>
              </div>
              <div><h3>基本分數</h3></div>
            </div>
            <!-- 第三列 -->
            <div>
              <b-table-simple table-class="text-center" bordered>
                <b-thead head-variant="info">
                  <b-tr>
                    <b-th style="width: 10%">計分指標</b-th>
                    <b-th style="width: 10%">計分項目</b-th>
                    <b-th style="width: 10%">計分基準</b-th>
                    <b-th style="width: 30%">履約事實</b-th>
                    <b-th style="width: 15%">加減分</b-th>
                  </b-tr>
                </b-thead>
                <b-tbody>
                  <!-- 如期履約情形 -->
                  <b-tr>
                    <b-td class="align-middle" rowspan="2">如期履約情形</b-td>
                    <b-td class="align-middle">提前竣工或如期完成</b-td>
                    <b-td class="align-middle"><b-button variant="info" @click="toBenchMarkPage">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <!-- A1 -->
                      <div>
                        <div>一、</div>
                        <div>
                          ◎ 實際開工日期：{{ formatDateInChinese(new Date(bidProjectDTO.asDate ? new Date(bidProjectDTO.asDate) : null)) }}
                        </div>
                        <div>
                          ◎ 應竣工日期(包含依合約規定展延部分)：
                          {{
                            formatDateInChinese(
                              new Date(
                                bidProjectDTO.ceDateF
                                  ? new Date(bidProjectDTO.ceDateF)
                                  : bidProjectDTO.ceDate
                                  ? new Date(bidProjectDTO.ceDate)
                                  : null
                              )
                            )
                          }}
                        </div>
                        <div>
                          ◎ 實際竣工日期：{{ formatDateInChinese(new Date(bidProjectDTO.aeDate ? new Date(bidProjectDTO.aeDate) : null)) }}
                        </div>
                        <div>◎ 實際提前竣工天數：{{ bidProjectDTO.fastDay }}天</div>
                        <div>
                          ◎ 最終核定工期天數：
                          <span v-if="bidProjectDTO.finWays == null || bidProjectDTO.finWays == 0"> (不合理) </span>
                          <span v-else> {{ bidProjectDTO.finWays }} 天 </span>
                        </div>
                        <div>
                          ◎ 提前竣工比例：
                          <span v-if="bidProjectDTO.finWays > 0 || bidProjectDTO.fastDay > 0">
                            {{ ((bidProjectDTO.fastDay / bidProjectDTO.finWays) * 100).toFixed(2) }}
                          </span>
                          <span v-else> -- </span>
                        </div>
                        <div>二、</div>
                        <div>◎ 調整權數： {{ getRatioByProjectCtsum(bidProjectDTO.ctSum) }}</div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-input type="number" v-model="$v.kb01.$model" :state="validateState($v.kb01)" lazy trim></b-form-input>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kb01.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle">逾期竣工減分</b-td>
                    <b-td class="align-middle"> <b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <!-- A2 -->
                      <div>
                        <div>一、</div>
                        <div>
                          ◎ 實際開工日期：{{
                            bidProjectDTO.asDate
                              ? formatDateInChinese(new Date(bidProjectDTO.asDate ? new Date(bidProjectDTO.asDate) : null))
                              : '(未填)'
                          }}
                        </div>
                        <div>
                          ◎ 應竣工日期(包含依合約規定展延部分)：
                          {{
                            formatDateInChinese(
                              new Date(
                                bidProjectDTO.ceDateF
                                  ? new Date(bidProjectDTO.ceDateF)
                                  : bidProjectDTO.ceDate
                                  ? new Date(bidProjectDTO.ceDate)
                                  : null
                              )
                            )
                          }}
                        </div>
                        <div>
                          ◎ 實際竣工日期：{{ formatDateInChinese(new Date(bidProjectDTO.aeDate ? new Date(bidProjectDTO.aeDate) : null)) }}
                        </div>
                        <div>
                          ◎ 最終核定工期天數：
                          <span v-if="bidProjectDTO.finWays == null || bidProjectDTO.finWays == 0"> (不合理) </span>
                          <span v-else> {{ bidProjectDTO.finWays }} 天 </span>
                        </div>
                        <div>◎ 逾期違約金天數：{{ bidProjectDTO.overDayY }}天</div>
                        <div>
                          ◎ 逾期違約金天數比例：
                          <span
                            v-if="
                              (bidProjectDTO.overDayY >= 0 && bidProjectDTO.overDayY) ||
                              (bidProjectDTO.finWays && bidProjectDTO.finWays > 0)
                            "
                          >
                            <span v-if="bidProjectDTO.wDays > 0">
                              {{ ((bidProjectDTO.overDayY / bidProjectDTO.finWays) * 100).toFixed(3) }}
                            </span>
                            <span
                              v-else-if="
                                dateDiffinDays(
                                  new Date(bidProjectDTO.ceDateF ? bidProjectDTO.ceDateF : bidProjectDTO.ceDate),
                                  bidProjectDTO.asDate
                                ) > 0
                              "
                            >
                              {{ ((bidProjectDTO.overDayY / bidProjectDTO.finWays) * 100).toFixed(3) }}
                            </span>
                            <span v-else> ________% </span>
                          </span>
                          <span v-else> ________% </span>
                        </div>
                        <div>◎ 調整權數： {{ getRatioByProjectCtsum(bidProjectDTO.ctSum) }}</div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >減分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-input type="number" v-model="$v.kb02.$model" :state="validateState($v.kb02)" lazy trim></b-form-input>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kb02.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- 履約成本及違約金 -->
                  <b-tr>
                    <b-td class="align-middle" rowspan="2">履約成本及違約金</b-td>
                    <b-td class="align-middle">提出替代方案</b-td>
                    <b-td class="align-middle"><b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <!-- B1 -->
                      <div>
                        <div>
                          ◎是否有施工廠商於履約期間主動提出技術、工法、材料或設備之替代方案，經主辦機關依政府採購法第35條規定評估後採行之情形：
                          {{ isSubstitudePropose === 'Y' ? '是' : '否' }}
                        </div>
                        <div v-if="isSubstitudePropose === 'Y'">
                          <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                            <b-form-select v-model="$v.qc01.$model">
                              <b-form-select-option :value="null">請選擇</b-form-select-option>
                              <b-form-select-option value="Y">是</b-form-select-option>
                              <b-form-select-option value="N">否</b-form-select-option>
                            </b-form-select>
                          </div>
                          <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                            <b-form-select v-model="$v.qc01.$model" disabled>
                              <b-form-select-option :value="null">請選擇</b-form-select-option>
                              <b-form-select-option value="Y">是</b-form-select-option>
                              <b-form-select-option value="N">否</b-form-select-option>
                            </b-form-select>
                          </div>
                        </div>
                        <div>(上述資料為系統中月執進度填報，如有替代工法請先至B5填報其他月分補填)</div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <div v-if="isSubstitudePropose === 'Y'">
                          <b-form-select
                            v-model="$v.kc01.$model"
                            :options="createPointSelect(0, 1, 1)"
                            :state="validateState($v.kc01)"
                          ></b-form-select>
                        </div>
                        <div v-else>
                          <b-form-select
                            v-model="$v.kc01.$model"
                            :options="createPointSelect(0, 0, 1)"
                            :state="validateState($v.kc01)"
                          ></b-form-select>
                        </div>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kc01.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle">違約金金額</b-td>
                    <b-td class="align-middle"> <b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <!-- B2 -->
                      <div>
                        <div>
                          ◎ 最終契約金額： {{ commaFormatter(bidProjectDTO.ctSumF ? bidProjectDTO.ctSumF : bidProjectDTO.ctSum) }}千元
                        </div>
                        <div>◎ 逾期違約金額：{{ commaFormatter(bidProjectDTO.overFee) }}千元</div>
                        <div>◎ 其他違約金：{{ commaFormatter(bidProjectDTO.otherFee) }}千元</div>
                        <div>
                          ◎ 違約金比率：
                          {{
                            (
                              ((bidProjectDTO.otherFee ? bidProjectDTO.otherFee : 0) /
                                (bidProjectDTO.ctSumF ? bidProjectDTO.ctSumF : bidProjectDTO.ctSum)) *
                              100
                            ).toFixed(3)
                          }}%
                        </div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >減分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.kc02.$model"
                          :options="createPointSelect(0, 2.5, 0.5)"
                          :state="validateState($v.kc02)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kc02.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- 施工品質 -->
                  <!-- 施工品質1 -->
                  <b-tr>
                    <b-td class="align-middle" rowspan="9">施工品質</b-td>
                    <b-td class="align-middle" rowspan="2">初驗、複驗及驗收過程之缺失與改善天數</b-td>
                    <b-td class="align-middle"><b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle" rowspan="2">
                      <!-- C1 -->
                      <div>
                        <div>
                          ◎ 初驗缺失改善通知日期：
                          <span> {{ formatDateInChinese(bidChkaptDTO.fcksdat ? new Date(bidChkaptDTO.fcksdat) : null) }}</span>
                        </div>
                        <div>
                          ◎ 初驗完成改善日期：
                          <span> {{ formatDateInChinese(bidChkaptDTO.fckedat ? new Date(bidChkaptDTO.fckedat) : null) }}</span>
                        </div>
                        <div>
                          ◎ 初驗實際改善天數：
                          {{ fchkcnt }}
                          日
                        </div>
                        <div>
                          ◎ 複驗缺失改善通知日期：
                          <span> {{ formatDateInChinese(bidChkaptDTO.acksdat ? new Date(bidChkaptDTO.acksdat) : null) }}</span>
                        </div>
                        <div>
                          ◎ 複驗缺失完成改善日期：
                          <span> {{ formatDateInChinese(bidChkaptDTO.ackedat ? new Date(bidChkaptDTO.ackedat) : null) }}</span>
                        </div>
                        <div>
                          ◎ 複驗實際改善天數：
                          {{ achkcnt }}日
                        </div>
                        <div>◎ 累計改善天數：{{ fachkcnt }}日</div>
                        <div>◎ 調整權數：{{ getRd01RatioByProjectCtsum(bidProjectDTO.ctSum) }}</div>
                        <div>(驗收資料未填，請先至B6填報)</div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.kd01.$model"
                          :options="createPointSelect(0, 2, 1)"
                          :state="validateState($v.kd01)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd01.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle"><b-button variant="info">減分基準</b-button> </b-td>
                    <b-td class="align-middle"
                      >減分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-input type="number" v-model="$v.kd02.$model" :state="validateState($v.kd02)" lazy trim></b-form-input>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd02.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- C2 -->
                  <!-- 施工品質2 -->
                  <b-tr>
                    <b-td class="align-middle">施工作業</b-td>
                    <b-td class="align-middle"><b-button variant="info">減分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <div>
                          <div>
                            <b-row class="d-flex align-items-center">
                              <div>◎ 主辦機關及監造單位現場取樣試驗報告共</div>
                              <div class="d-inline position-relative">
                                <b-form-input v-model="$v.qd03at.$model" :state="validateState($v.qd03at)" lazy trim></b-form-input>
                              </div>
                              <div>次</div>
                            </b-row>
                            <b-row class="d-flex align-items-center pt-2">
                              ，其中不合格紀錄次數：
                              <div class="d-inline">
                                <b-form-input v-model="$v.qd03a.$model" :state="validateState($v.qd03a)" lazy trim></b-form-input>
                              </div>
                              次
                            </b-row>
                          </div>
                          <div>
                            <b-row class="d-flex align-items-center pt-2">
                              施工廠商於檢驗停留點應檢附之自主檢查表與監造單位現場查驗內容比對，發現有明顯不確實次數：
                              <div class="d-inline">
                                <b-form-input v-model="$v.qd03b.$model" :state="validateState($v.qd03b)" lazy trim></b-form-input>
                              </div>
                              次
                            </b-row>
                          </div>
                          <div>
                            <b-row class="d-flex align-items-center pt-2">
                              ◎ 施工廠商於檢驗停留點未經檢驗或同意，即進行下一道工序次數：
                              <div class="d-inline">
                                <b-form-input v-model="$v.qd03c.$model" :state="validateState($v.qd03c)" lazy trim></b-form-input>
                              </div>
                              次
                            </b-row>
                          </div>
                          <div>
                            <b-row class="d-flex align-items-center pt-2">
                              ◎工程查核或各級機關工程督導小組督導，有丙等情形或重大缺失，工地負責人、工地主任、品管人員或勞安人員經主辦機關通知廠商更換調離工地者：
                              <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                                <div class="d-inline">
                                  <b-form-input
                                    class="d-inline"
                                    v-model="$v.qd03d.$model"
                                    :state="validateState($v.qd03d)"
                                    lazy
                                    trim
                                  ></b-form-input>
                                </div>
                              </div>
                              <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                                {{ $v.qd03d.$model }}
                              </div>

                              人次
                            </b-row>
                          </div>
                          <div>◎ 調整權數： {{ getRd03RateByProjectFinWay(bidProjectDTO.finWay) }}</div>
                          <div>(上述資料為系統中各月執進度填報數字加總，如有缺漏請先至B5填報其他月分補填)</div>
                        </div>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        <div>
                          <div>
                            <b-row class="d-flex align-items-center">
                              <div>◎ 主辦機關及監造單位現場取樣試驗報告共{{ $v.qd03at.$model }}次</div>
                            </b-row>
                            <b-row class="d-flex align-items-center pt-2"> ，其中不合格紀錄次數：{{ $v.qd03a.$model }}次 </b-row>
                          </div>
                          <div>
                            <b-row class="d-flex align-items-center pt-2">
                              施工廠商於檢驗停留點應檢附之自主檢查表與監造單位現場查驗內容比對，發現有明顯不確實次數：{{
                                $v.qd03b.$model
                              }}次
                            </b-row>
                          </div>
                          <div>
                            <b-row class="d-flex align-items-center pt-2">
                              ◎ 施工廠商於檢驗停留點未經檢驗或同意，即進行下一道工序次數：{{ $v.qd03c.$model }}次
                            </b-row>
                          </div>
                          <div>
                            <b-row class="d-flex align-items-center pt-2">
                              ◎工程查核或各級機關工程督導小組督導，有丙等情形或重大缺失，工地負責人、工地主任、品管人員或勞安人員經主辦機關通知廠商更換調離工地者：{{
                                $v.qd03d.$model
                              }}人次
                            </b-row>
                          </div>
                          <div>◎ 調整權數： {{ getRd03RateByProjectFinWay(bidProjectDTO.finWay) }}</div>
                          <div>(上述資料為系統中各月執進度填報數字加總，如有缺漏請先至B5填報其他月分補填)</div>
                        </div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >減分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-input type="number" v-model="$v.kd03.$model" :state="validateState($v.kd03)" lazy trim></b-form-input>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd03.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- C3 -->
                  <!-- 施工品質3 -->
                  <b-tr>
                    <b-td class="align-middle" rowspan="2">施工查核 </b-td>
                    <b-td class="align-middle"><b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle" rowspan="2">
                      <div>
                        <div>◎ 查核分數</div>
                        <div v-for="(item, index) in govChkInfoArr" :key="index">
                          <div>{{ item }}</div>
                        </div>
                        <div>◎ 平均成績:{{ averageScore }}分</div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <!-- 不要1 -->
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.kd04.$model"
                          :options="createPointSelect(0, 4, 1)"
                          :state="validateState($v.kd04)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd04.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle"><b-button variant="info">減分基準</b-button> </b-td>
                    <b-td class="align-middle"
                      >減分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.kd05.$model"
                          :options="createPointSelect(0, 5, 0.5)"
                          :state="validateState($v.kd05)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd05.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- C4 -->
                  <!-- 施工品質4 -->
                  <b-tr>
                    <b-td class="align-middle" rowspan="2">專任工程人員參與情形 </b-td>
                    <b-td class="align-middle"><b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <div>
                        <div v-if="isUntdn">
                          ◎本案屬適用營造業法之工程
                          <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                            <b-row class="d-flex align-items-center pt-2">
                              ◎專任工程人員於施工期間親自赴工地現場督察且留存督察紀錄，平均每月：
                              <div class="d-inline">
                                <b-form-input v-model="$v.qd06.$model" :state="validateState($v.qd06)" lazy trim></b-form-input>
                              </div>
                              次(且需每月至少1次)
                            </b-row>
                          </div>
                          <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                            <b-row class="d-flex align-items-center pt-2">
                              ◎專任工程人員於施工期間親自赴工地現場督察且留存督察紀錄，平均每月：{{ $v.qd06.$model }} 次(且需每月至少1次)
                            </b-row>
                          </div>
                        </div>
                        <div v-else>◎本案非屬適用營造業法之工程</div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <div v-if="isUntdn">
                          <b-form-select
                            v-model="$v.kd06.$model"
                            :options="createPointSelect(0, 2, 1)"
                            :state="validateState($v.kd06)"
                          ></b-form-select>
                        </div>
                        <div v-else>
                          <b-form-select
                            v-model="$v.kd06.$model"
                            :options="createPointSelect(0, 0, 1)"
                            :state="validateState($v.kd06)"
                          ></b-form-select>
                        </div>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd06.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle"><b-button variant="info">減分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <div>
                        <div>◎最終核定工期{{ bidProjectDTO.finWay ? bidProjectDTO.finWay : bidProjectDTO.wDays }} 天</div>
                        <b-row class="d-flex align-items-center pt-2">
                          <div v-if="isUntdn">
                            <div v-if="isCivilEngineering">◎本案屬土木包工業承攬之工程</div>
                            <div v-else>
                              <div>◎本案屬適用營造業法之工程</div>
                              <div>
                                ◎平均每月
                                <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                                  <div class="d-inline">
                                    <b-form-input v-model="$v.qd07.$model" :state="validateState($v.qd07)" lazy trim></b-form-input>
                                  </div>
                                </div>
                                <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                                  {{ $v.qd07.$model }}
                                </div>

                                次
                              </div>
                            </div>
                          </div>
                          <div v-else>◎本案非屬適用營造業法之工程</div>
                        </b-row>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >減分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <div v-if="isUntdn">
                          <b-form-select
                            v-model="$v.kd07.$model"
                            :options="createPointSelect(0, 0.5, 0.5)"
                            :state="validateState($v.kd07)"
                          ></b-form-select>
                        </div>
                        <div v-else>
                          <b-form-select
                            v-model="$v.kd07.$model"
                            :options="createPointSelect(0, 0, 0.5)"
                            :state="validateState($v.kd07)"
                          ></b-form-select>
                        </div>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd07.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- C5 -->
                  <!-- 施工品質5 -->
                  <b-tr>
                    <b-td class="align-middle">技術士參與情形 </b-td>
                    <b-td class="align-middle"><b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <div>
                        <b-row class="d-flex align-items-center pt-2">
                          <div>
                            ◎設置技術士人數：

                            <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                              <div class="d-inline">
                                <b-form-input v-model="$v.qd08.$model" :state="validateState($v.qd08)" lazy trim></b-form-input>人
                              </div>
                            </div>
                            <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                              {{ $v.qd08.$model }}
                            </div>
                          </div>
                        </b-row>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分

                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.kd08.$model"
                          :options="createPointSelect(0, 2, 0.5)"
                          :state="validateState($v.kd08)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd08.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- C6 -->
                  <!-- 施工品質6 -->
                  <b-tr>
                    <b-td class="align-middle">品質優良 </b-td>
                    <b-td class="align-middle"><b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <div>
                        <div>◎得獎情形(文字簡述得獎名稱及等第)：</div>

                        <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                          <b-form-textarea v-model="$v.qd09.$model" :state="validateState($v.qd09)" lazy trim></b-form-textarea>
                        </div>
                        <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                          <b-form-textarea v-model="$v.qd09.$model" :state="validateState($v.qd09)" disabled></b-form-textarea>
                        </div>

                        <div>◎ 金質獎特優年度：{{ priceGradeAYear }}年</div>
                        <div>◎ 金質獎優等年度：{{ priceGradeBYear }}年</div>
                        <div>◎ 金質獎入圍或佳作年度：{{ priceGradeCYear }}年</div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.kd09.$model"
                          :options="createPointSelect(0, 5, 1)"
                          :state="validateState($v.kd09)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kd09.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- D1 -->
                  <!-- 安衛環保 -->
                  <b-tr>
                    <b-td class="align-middle" rowspan="4">安衛環保</b-td>
                    <b-td class="align-middle" rowspan="3">職業安全衛生與職業災害</b-td>
                    <b-td class="align-middle"><b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <div>
                        <div>◎ 得獎情形(文字簡述得獎名稱及等第)：</div>

                        <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                          <b-form-textarea v-model="$v.qe01a.$model" :state="validateState($v.qe01a)" lazy trim></b-form-textarea>
                        </div>
                        <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                          <b-form-textarea v-model="$v.qe01a.$model" :state="validateState($v.qe01a)" disabled lazy trim></b-form-textarea>
                        </div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <!-- 不要1 -->
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.ke01.$model"
                          :options="createPointSelect(0, 2, 0.5)"
                          :state="validateState($v.ke01)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.ke01.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle"> <b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle" rowspan="2">
                      <div>
                        <div>
                          <b-row class="d-flex align-items-center pt-2">
                            施工期間發生依法應通知目的事業主管機關檢查機構之職業災害：合計
                            <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                              <b-form-input v-model="$v.qe03a.$model" :state="validateState($v.qe03a)" lazy trim></b-form-input>
                            </div>
                            <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                              {{ $v.qe03a.$model }}
                            </div>

                            次
                          </b-row>
                        </div>
                        <div>
                          <b-row class="d-flex align-items-center pt-2">
                            ◎ 曾發生死亡災害事件：合計死亡
                            <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                              <b-form-input v-model="$v.qe03b.$model" :state="validateState($v.qe03b)" lazy trim></b-form-input>
                            </div>
                            <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                              {{ $v.qe03b.$model }}
                            </div>
                            人
                          </b-row>
                        </div>
                        <div>
                          ◎實際履約日數：{{
                            bidProjectDTO.aworkDay ? (bidProjectDTO.aworkDay !== 0 ? bidProjectDTO.aworkDay : '(不合理)') : '(不合理)'
                          }}天
                        </div>
                        <div>
                          <div v-for="(item, index) in prjSafeMessageArray" :key="index">
                            <div>{{ item }}</div>
                          </div>
                        </div>
                      </div>
                    </b-td>
                    <b-td>
                      加分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select v-model="$v.ke02.$model" :options="noDisasterScore" :state="validateState($v.ke02)"></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.ke02.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle"> <b-button variant="info">減分基準</b-button> </b-td>
                    <b-td class="align-middle"
                      >減分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.ke03.$model"
                          :options="createPointSelect(0, 10, 1)"
                          :state="validateState($v.ke03)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.ke03.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle">環保</b-td>
                    <b-td class="align-middle"> <b-button variant="info">減分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <!-- 安衛環保 環保 D2 -->
                      <div>
                        <div>(文字簡述受裁罰日期及原因)</div>
                        <div>
                          <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                            <b-form-textarea v-model="$v.qe04.$model" :state="validateState($v.qe04)" lazy trim></b-form-textarea>
                          </div>
                          <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                            <b-form-textarea v-model="$v.qe04.$model" :state="validateState($v.qe04)" disabled lazy trim></b-form-textarea>
                          </div>
                        </div>
                        <div>
                          ◎實際履約日數：{{
                            bidProjectDTO.aworkDay ? (bidProjectDTO.aworkDay !== 0 ? bidProjectDTO.aworkDay : '(不合理)') : '(不合理)'
                          }}天
                        </div>
                        <div>
                          <div v-for="(item, index) in prjEnviMessageArray" :key="index">
                            <div>{{ item }}</div>
                          </div>
                        </div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select v-model="$v.ke04.$model" :options="noEnviScore" :state="validateState($v.ke04)"></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.ke04.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- 民眾反映及是否停權 -->
                  <b-tr>
                    <b-td class="align-middle" rowspan="2">民眾反映及是否停權</b-td>
                    <b-td class="align-middle">民眾通報缺失</b-td>
                    <b-td class="align-middle"><b-button variant="info">加分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <div>
                        <div>(文字簡述受通報日期及原因)</div>
                        <div>
                          <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                            <b-form-textarea v-model="$v.qf01.$model" :state="validateState($v.qf01)" lazy trim></b-form-textarea>
                          </div>
                          <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                            <b-form-textarea v-model="$v.qf01.$model" :state="validateState($v.qf01)" disabled></b-form-textarea>
                          </div>
                        </div>
                        <div>
                          ◎實際履約日數：{{
                            bidProjectDTO.aworkDay ? (bidProjectDTO.aworkDay !== 0 ? bidProjectDTO.aworkDay : '(不合理)') : '(不合理)'
                          }}天
                        </div>
                        <div>{{ overSeeMessage }}</div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >加分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select v-model="$v.kf01.$model" :options="noEnviScore" :state="validateState($v.kf01)"></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kf01.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="align-middle">依政府採購法第101條至第103條規定被刊登政府採購公報之廠商</b-td>
                    <b-td class="align-middle"><b-button variant="info">減分基準</b-button> </b-td>
                    <b-td class="text-left align-middle">
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        ◎ 屬政府採購法第103條第1項第
                        <b-form-select v-model="$v.qf02a.$model" :state="validateState($v.qf02a)">
                          <b-form-select-option value="1">1款</b-form-select-option>
                          <b-form-select-option value="2">2款</b-form-select-option>
                          <b-form-select-option value="3">3款</b-form-select-option>
                        </b-form-select>
                        之情形。

                        <div>
                          ◎ 是否有解除契約情形
                          <b-form-select v-model="$v.qf02b.$model" :state="validateState($v.qf02b)">
                            <b-form-select-option value="Y">是</b-form-select-option>
                            <b-form-select-option value="N">否</b-form-select-option>
                          </b-form-select>
                        </div>
                        <div>
                          是否有終止契約情形
                          <b-form-select v-model="$v.qf02c.$model" :state="validateState($v.qf02c)">
                            <b-form-select-option value="Y">是</b-form-select-option>
                            <b-form-select-option value="N">否</b-form-select-option>
                          </b-form-select>
                        </div>
                        <div>
                          終止契約時施工進度%
                          <b-form-input v-model="$v.qf02d.$model" :state="validateState($v.qf02d)" lazy trim></b-form-input>
                        </div>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        ◎ 屬政府採購法第103條第1項第
                        <b-form-select v-model="$v.qf02a.$model" :state="validateState($v.qf02a)" disabled>
                          <b-form-select-option value="1">1款</b-form-select-option>
                          <b-form-select-option value="2">2款</b-form-select-option>
                          <b-form-select-option value="3">3款</b-form-select-option>
                        </b-form-select>
                        之情形。

                        <div>
                          ◎ 是否有解除契約情形
                          <b-form-select v-model="$v.qf02b.$model" :state="validateState($v.qf02b)" disabled>
                            <b-form-select-option value="Y">是</b-form-select-option>
                            <b-form-select-option value="N">否</b-form-select-option>
                          </b-form-select>
                        </div>
                        <div>
                          是否有終止契約情形
                          <b-form-select v-model="$v.qf02c.$model" :state="validateState($v.qf02c)" disabled>
                            <b-form-select-option value="Y">是</b-form-select-option>
                            <b-form-select-option value="N">否</b-form-select-option>
                          </b-form-select>
                        </div>
                        <div>
                          終止契約時施工進度%
                          <b-form-input v-model="$v.qf02d.$model" :state="validateState($v.qf02d)" disabled></b-form-input>
                        </div>
                      </div>
                    </b-td>
                    <b-td class="align-middle"
                      >減分
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-select
                          v-model="$v.kf02.$model"
                          :options="createPointSelect(0, 10, 5)"
                          :state="validateState($v.kf02)"
                        ></b-form-select>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        {{ $v.kf02.$model }}
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- 總分 -->
                  <b-tr>
                    <b-td colspan="5" class="text-right"> 總分： {{ totalScore.toFixed(3) }} </b-td>
                  </b-tr>
                  <!-- 備註 -->
                  <b-tr>
                    <b-td>備註</b-td>
                    <b-td colspan="4">
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-textarea v-model="$v.item.$model" :state="validateState($v.item)" lazy trim></b-form-textarea>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        <b-form-textarea v-model="$v.item.$model" :state="validateState($v.item)" disabled></b-form-textarea>
                      </div>
                    </b-td>
                  </b-tr>
                  <!-- 修正計分原因 -->
                  <b-tr>
                    <b-td>修正計分原因</b-td>
                    <b-td colspan="4">
                      <div v-if="currentFormStatus === formStatusEnum.CREATE || currentFormStatus === formStatusEnum.MODIFY">
                        <b-form-textarea v-model="$v.rermk.$model" :state="validateState($v.rermk)" lazy trim></b-form-textarea>
                      </div>
                      <div v-else-if="currentFormStatus === formStatusEnum.INFO">
                        <b-form-textarea v-model="$v.rermk.$model" :state="validateState($v.rermk)" disabled></b-form-textarea>
                      </div>
                    </b-td>
                  </b-tr>
                </b-tbody>
              </b-table-simple>

              <!-- 功能列 -->
              <div class="d-flex justify-content-center">
                <div v-if="formStatusEnum.CREATE === currentFormStatus">
                  <i-button type="save" @click="submitFormData(formStatusEnum.CREATE)"> </i-button>
                </div>
                <div v-else-if="formStatusEnum.MODIFY === currentFormStatus">
                  <i-button type="save" @click="submitFormData(formStatusEnum.MODIFY)"> </i-button>
                </div>
                <div v-else-if="formStatusEnum.INFO === currentFormStatus">
                  <i-button type="trash" @click="submitFormData(formStatusEnum.INFO)"> </i-button>
                </div>

                <i-button type="x-circle" @click="reset"></i-button>
                <i-button type="arrow-left" @click="backToPreviousPage"></i-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated, computed, unref } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidPrjMonthService from '@/components/bid/bidService/bid-prj-month.service';
import BidChkaptService from '@/components/bid/bidService/bid-chkapt.service';
import BidKpifinService from '@/components/bid/bidService/bid-kpifin-service';
import BidGovChkService from '@/components/bid/bidService/bid-gov-check.service';
import BidPrjPriceService from '@/components/bid/bidService/bid-prj-price.service';
import BidPrjSafeService from '@/components/bid/bidService/bid-prj-safe.service';
import BidPrjEnviService from '@/components/bid/bidService/bid-prj-envi.service';
import BidOverSeeService from '@/components/bid/bidService/bid-oversee.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { navigateByNameAndParams } from '@/router/router';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import NotificationService from '@/shared/notification/notification-service';
import { required, numeric, maxValue } from '@/shared/validators';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { IBidKpifin, BidKpifin } from '@/shared/model/bidModel/bid-kpifin.model';
import { IBidProject, BidProject } from '@/shared/model/bidModel/bid-project.model';
import { IBidChkapt, BidChkapt } from '@/shared/model/bidModel/bid-chkapt.model';
import { formatDateInChinese, dateDiffinDays } from '@/shared/date/minguo-calendar-utils';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'bidAad008Edit',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    bidKpifinKey: {
      type: Object,
      required: false,
    },
    formStatus: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidKpifinKeyProp: Ref<any> = toRef(props, 'bidKpifinKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    //在keep alive的時候保留原本的project資料
    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    setTempProjectKey();

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
      INFO = 'info',
    }

    const bidProjectService = new BidProjectService();
    const bidKpifinService = new BidKpifinService();
    const bidPrjMonthService = new BidPrjMonthService();
    const bidChkaptService = new BidChkaptService();
    const bidGovChkService = new BidGovChkService();
    const bidPrjPriceService = new BidPrjPriceService();
    const bidPrjSafeService = new BidPrjSafeService();
    const bidPrjEnviService = new BidPrjEnviService();
    const bidOverSeeService = new BidOverSeeService();

    let bidSysCodeTable = new BidSysCodeTable();
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const notificationService: NotificationService = useNotification();

    let currentFormStatus = ref(formStatusEnum.CREATE);
    let formDefault: IBidKpifin = new BidKpifin();

    const rules = {
      idate: {},
      seq: {},
      flag: {},
      confDate: {},
      confNumber: {},
      ka02: {},
      kb01: { numeric: numeric },
      kb02: { numeric: numeric },
      kc01: { numeric: numeric },
      kc02: { numeric: numeric },
      kd01: { numeric: numeric },
      kd02: { numeric: numeric },
      kd03: { numeric: numeric },
      kd04: { numeric: numeric },
      kd05: { numeric: numeric },
      kd06: { numeric: numeric },
      kd07: { numeric: numeric },
      kd08: { numeric: numeric },
      kd09: { numeric: numeric },
      ke01: { numeric: numeric },
      ke02: { numeric: numeric },
      ke03: { numeric: numeric },
      ke04: { numeric: numeric },
      kf01: { numeric: numeric },
      kf02: { numeric: numeric },
      kscore: {},
      qa02: {},
      qc01: {},
      qc03: {},
      qd03a: {},
      qd03b: {},
      qd03c: {},
      qd03d: {},
      qd06: {},
      qd07: {},
      qd08: {},
      qd09: {},
      qe01a: {},
      qe01b: {},
      qe021s: {},
      qe021e: {},
      qe021y: {},
      qe022s: {},
      qe022e: {},
      qe022y: {},
      qe023s: {},
      qe023e: {},
      qe023y: {},
      qe03a: {},
      qe03b: {},
      qe04: {},
      qe041s: {},
      qe041e: {},
      qe041y: {},
      qe042s: {},
      qe042e: {},
      qe042y: {},
      qe043s: {},
      qe043e: {},
      qe043y: {},
      qf01: {},
      qf011s: {},
      qf011e: {},
      qf011y: {},
      qf012s: {},
      qf012e: {},
      qf012y: {},
      qf013s: {},
      qf013e: {},
      qf013y: {},
      qf02: {},
      qf02a: {},
      qf02b: {},
      qf02c: {},
      qf02d: {},
      item: {},
      senter: {},
      sentel: {},
      admser: {},
      dsts: {},
      recnt: {},
      rermk: {},
      qd03at: {},
      publ: {},
    };

    let form = reactive(Object.assign({}, formDefault));
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //最後的總分
    let baseScore = 77;
    let totalScore = computed(
      () =>
        baseScore +
        (Number(form.kb01 ?? 0) +
          (form.kc01 ?? 0) +
          (form.kd01 ?? 0) +
          (form.kd04 ?? 0) +
          (form.kd06 ?? 0) +
          (form.kd09 ?? 0) +
          (form.ke01 ?? 0) +
          (form.ke02 ?? 0) +
          (form.ke04 ?? 0) +
          (form.kf01 ?? 0)) -
        (Number(form.kb02 ?? 0) +
          (form.kc02 ?? 0) +
          Number(form.kd02 ?? 0) +
          Number(form.kd03 ?? 0) +
          (form.kd05 ?? 0) +
          (form.kd07 ?? 0) +
          (form.kd08 ?? 0) +
          (form.ke03 ?? 0) +
          (form.kf02 ?? 0))
    );
    //處理各個框框裡面要撈的其他table的資料
    let bidProjectDTO = reactive(new BidProject());

    //處理初驗、複驗及驗收過程之缺失與改善天數
    let bidChkaptDTO = reactive(new BidChkapt());
    let fchkcnt = ref(0);
    let achkcnt = ref(0);
    let fachkcnt = ref(0);
    function handleChkaptData(bidChkaptDTO: IBidChkapt) {
      if (bidChkaptDTO.fcksdat && bidChkaptDTO.fckedat) {
        let dayDiff = dateDiffinDays(bidChkaptDTO.fcksdat, bidChkaptDTO.fckedat);
        if (bidChkaptDTO.fdays && dayDiff > bidChkaptDTO.fdays) {
          fchkcnt.value = dayDiff;
        }
      } else {
        fchkcnt.value = 0;
      }

      if (bidChkaptDTO.acksdat && bidChkaptDTO.ackedat) {
        let dayDiff = dateDiffinDays(bidChkaptDTO.acksdat, bidChkaptDTO.ackedat);
        if (bidChkaptDTO.adays && dayDiff > bidChkaptDTO.adays) {
          achkcnt.value = dayDiff;
        }
      } else {
        achkcnt.value = 0;
      }

      fachkcnt.value = fchkcnt.value + achkcnt.value;
    }
    //判斷完工實際履約日數

    //處理施工品質 施工查核
    let averageScore = ref('');
    let govChkInfoArr = reactive([]);
    //施工查核 的 查核分數
    function getCheckScore(wkut, prjno) {
      let showGovChkInfoArr = [];
      let checkCount = 0;
      let scoreS = 0;
      let gradeCount = 0; //規格書有寫但不知道要幹嘛
      bidGovChkService.getGovCheckListByCriteria({ wkut: wkut, prjno: prjno }).then(govChkDTOlist => {
        // console.log(govChkDTOlist);
        if (govChkDTOlist && govChkDTOlist.length > 0) {
          govChkDTOlist.forEach(govChkDTO => {
            if (govChkDTO.score > 40) {
              showGovChkInfoArr.push(govChkDTO.score + '分(' + formatDateInChinese(govChkDTO.checkDate) + ')');
              checkCount++;
              scoreS += govChkDTO.score;
              if (govChkDTO.score < 70) {
                gradeCount++;
              }
            } else {
              showGovChkInfoArr.push('不計分(' + formatDateInChinese(govChkDTO.checkDate ? new Date(govChkDTO.checkDate) : null) + ')');
            }
          });
        } else {
          showGovChkInfoArr.push('(無)分( 年 月 日)');
        }
        //把後端撈出來的東西給前端的變數
        averageScore.value = (checkCount !== 0 ? scoreS / checkCount : 0).toFixed(3);
        govChkInfoArr.splice(0, govChkInfoArr.length, ...showGovChkInfoArr);
      });
    }

    //是否有施工廠商於履約期間主動提出技術、工法、材料或設備之替代方案，經主辦機關依政府採購法第35條規定評估後採行之情形
    const isSubstitudePropose = ref('');
    //判斷 是否有施工廠商於履約期間主動提出技術、工法、材料或設備之替代方案，經主辦機關依政府採購法第35條規定評估後採行之情形
    function judgeSubstitudePropose(wkut, prjno, aeDate) {
      if (!(prjno && prjno.length > 0)) return;
      if (!(wkut && wkut.length > 0)) return;
      const criteria = {
        wkut: wkut,
        prjno: prjno,
      };
      let rc01 = 0;
      bidPrjMonthService.getAllPrjMonthListByProjectKey(criteria).then(prjMonthArray => {
        if (prjMonthArray && prjMonthArray.length > 0) {
          // console.log(prjMonthArray);
          prjMonthArray.forEach(item => {
            if (item.replMeth === 'Y' && new Date(parseInt(item.syr) + 1911, parseInt(item.month) - 1) < new Date(aeDate)) {
              rc01 += 1;
            }
          });
          if (rc01 > 0) isSubstitudePropose.value = 'Y';
          else isSubstitudePropose.value = 'N';
        }
      });
    }

    //判斷 營造業法之工程 和 任工程人員於施工期間親自赴工地現場督察
    let isCivilEngineering = ref(false);
    let isUntdn = ref(false);
    //判斷 營造業法之工程 和 任工程人員於施工期間親自赴工地現場督察
    function checkConstructionIndustry(bidPrjDTO: IBidProject) {
      if (bidPrjDTO.untnd === 'Y') {
        isUntdn.value = true;
      }
      bidProjectService.findBidProjectCCUT(bidPrjDTO.wkut, bidPrjDTO.prjno).then(bidUnitDTOs => {
        // console.log('bidUnitDTOs', bidUnitDTOs);
        let regexp = new RegExp(/.*\u571f\u6728\u5305\u5de5.*/); //%土木包工%

        if (bidUnitDTOs && bidUnitDTOs.length > 0) {
          bidUnitDTOs.forEach(bidUnitDTO => {
            if (regexp.test(bidUnitDTO.name)) {
              isCivilEngineering.value = true;
            }
          });
        }
      });
    }

    //判斷得獎年度
    let priceGradeAYear = ref('');
    let priceGradeBYear = ref('');
    let priceGradeCYear = ref('');
    //判斷獎
    function checkProjectPrice(bidPrjDTO: IBidProject) {
      bidProjectService.findBidProjectCCUT(bidPrjDTO.wkut, bidPrjDTO.prjno).then(bidUnitDTOs => {
        //用DTO的code建一個比對用的array
        let compareArray = [];
        if (bidUnitDTOs && bidUnitDTOs.length > 0) {
          compareArray = compareArray.splice(0, compareArray.length, ...bidUnitDTOs.map(bidUnitDTO => bidUnitDTO.code));
        }

        bidPrjPriceService.getBidPrjPRiceByCriteria({ wkut: bidPrjDTO.wkut, prjno: bidPrjDTO.prjno }).then(priceDTOs => {
          if (priceDTOs && priceDTOs.length > 0) {
            //走訪整個priceDTO陣列，如果某個DTO 的 punit在 compareArray裡，就把它拿出來做事
            priceDTOs.forEach(priceDTO => {
              let findPriceDTO = null;
              findPriceDTO = compareArray.find(priceDTO.pUnit);
              if (findPriceDTO != undefined) {
                switch (findPriceDTO.grade) {
                  case 'A':
                    priceGradeAYear.value += findPriceDTO.year + '年, ';
                    break;
                  case 'B':
                    priceGradeBYear.value += findPriceDTO.year + '年, ';
                    break;
                  case 'C':
                    priceGradeCYear.value += findPriceDTO.year + '年, ';
                    break;
                  default:
                    break;
                }
              }
            });
            if (priceGradeAYear.value.length == 0) priceGradeAYear.value = '(無)';
            if (priceGradeBYear.value.length == 0) priceGradeBYear.value = '(無)';
            if (priceGradeCYear.value.length == 0) priceGradeCYear.value = '(無)';
          }
        });
      });
    }

    //判斷prjSafej公安資料  && 判斷project的aworkday && 無職災加分的選項

    let prjSafeMessageArray = reactive([]);
    let noDisasterScore = reactive([]);
    function checkProjectSafe(bidPrjDTO: IBidProject) {
      let prjSafeOutputArray = [];
      let safeCount = 0; //safe的數量
      let safeDateSum = 0; //發生前累計工期的總和
      let lastWdayed = -1; //上一筆的wayed
      bidPrjSafeService.getBidPrjSafeByCriteria({ wkut: bidPrjDTO.wkut, prjno: bidPrjDTO.prjno }).then(prjSafeDTOs => {
        if (prjSafeDTOs && prjSafeDTOs.length > 0) {
          prjSafeDTOs.forEach(prjSafeDTO => {
            safeCount++;
            prjSafeOutputArray.push(
              formatDateInChinese(new Date(prjSafeDTO.sdate)) +
                `(累計工期天)${prjSafeDTO.wdayed ? prjSafeDTO.wdayed : '--'}發生工安事件，` +
                `死${prjSafeDTO.diedNum}人，傷${prjSafeDTO.hurtNum}人。`
            );

            if (prjSafeDTO.wdayed !== null || prjSafeDTO.wdayed !== undefined) {
              if (safeCount === 1) {
                prjSafeOutputArray.push(`(連續未發生天數${prjSafeDTO.wdayed}天)`);
                safeDateSum += prjSafeDTO.wdayed;
              } else {
                prjSafeOutputArray.push(
                  `(連續未發生天數 ${prjSafeDTO.wdayed} -(${lastWdayed} +1)= (${prjSafeDTO.wdayed - lastWdayed + 1}天)`
                );
                if (prjSafeDTO.wdayed - lastWdayed - 1 > safeDateSum) {
                  safeDateSum = prjSafeDTO.wdayed - lastWdayed - 1;
                }
              }
            } else {
              prjSafeOutputArray.push('(已累計工期未填，請先於D3補填)');
            }
            lastWdayed = prjSafeDTO.wdayed;
          });
        }
        let prjAworkDay = bidPrjDTO.aworkDay ? bidPrjDTO.aworkDay : 0;
        //判斷完safe 之後拿到wdayed
        prjSafeOutputArray.push(
          `完工實際履約日數：${prjAworkDay}天(連續未發生天數 ${prjAworkDay}-${lastWdayed + 1}=${prjAworkDay - lastWdayed - 1}天)`
        );

        if (prjAworkDay - lastWdayed - 1 > safeDateSum) {
          safeDateSum = prjAworkDay - lastWdayed - 1;
        }
        bidChkaptService.getLatestBidChkaptByCriteria({ wkut: bidPrjDTO.wkut, prjno: bidPrjDTO.prjno }).then(chkaptData => {
          if (new Date(chkaptData.aokDate) >= new Date('2018-07-01')) {
            prjSafeOutputArray.push(`(連續未發生最多天數${safeDateSum}天)`);
          }
          //產分加分的選項
          let scoreArray = generateNoDisasterScore(bidPrjDTO, new Date(chkaptData.aokDate ? chkaptData.aokDate : null));
          noDisasterScore.splice(0, noDisasterScore.length, ...scoreArray);
        });
        if (safeCount === 0) {
          prjSafeOutputArray.push('本案系統中無工安記錄，如有漏填請先於D3補填)。');
        } else {
          prjSafeOutputArray.push('(工安記錄如有漏填請先於D3補填)。');
        }
        prjSafeMessageArray.splice(0, prjSafeMessageArray.length, ...prjSafeOutputArray);
      });
    }

    let prjEnviMessageArray = reactive([]);
    let noEnviScore = reactive([]);
    // bidPrjEnviService
    //判斷 prjEnv的資料 跟prjSafe 87%像
    function checkProjectEnvi(bidPrjDTO: IBidProject) {
      let prjEnviOutputArray = [];
      let envCount = 0; //envi的數量
      let enviDateSum = 0; //發生前累計工期的總和
      let lastWdayed = -1; //上一筆的wayed
      bidPrjEnviService.getBidPrjEnviByCriteria({ wkut: bidPrjDTO.wkut, prjno: bidPrjDTO.prjno }).then(prjEnviDTOs => {
        if (prjEnviDTOs && prjEnviDTOs.length > 0) {
          prjEnviDTOs.forEach(prjEnviDTO => {
            envCount++;
            prjEnviOutputArray.push(
              formatDateInChinese(new Date(prjEnviDTO.sdate)) +
                `(累計工期${prjEnviDTO.wdayed ? prjEnviDTO.wdayed : '--'}天)` +
                `${prjEnviDTO.prmNo ? prjEnviDTO.prmNo : ''}。`
            );

            if (prjEnviDTO.wdayed !== null || prjEnviDTO.wdayed !== undefined) {
              if (envCount === 1) {
                prjEnviOutputArray.push(`(連續未發生天數${prjEnviDTO.wdayed}天)`);
                enviDateSum += prjEnviDTO.wdayed;
              } else {
                prjEnviOutputArray.push(
                  `(連續未發生天數 ${prjEnviDTO.wdayed} -(${lastWdayed} +1)= (${prjEnviDTO.wdayed - lastWdayed + 1}天)`
                );
                if (prjEnviDTO.wdayed - lastWdayed - 1 > enviDateSum) {
                  enviDateSum = prjEnviDTO.wdayed - lastWdayed - 1;
                }
              }
            } else {
              prjEnviOutputArray.push('(已累計工期未填，請先於D3補填)');
            }
            lastWdayed = prjEnviDTO.wdayed;
          });
        }
        let prjAworkDay = bidPrjDTO.aworkDay ? bidPrjDTO.aworkDay : 0;
        //判斷完envi 之後拿到wdayed
        prjEnviOutputArray.push(`完工實際履約日數：${prjAworkDay}天`);
        prjEnviOutputArray.push(`(連續未發生天數 ${prjAworkDay}-${lastWdayed + 1}=${prjAworkDay - lastWdayed - 1}天)`);

        if (prjAworkDay - lastWdayed - 1 > enviDateSum) {
          enviDateSum = prjAworkDay - lastWdayed - 1;
        }
        bidChkaptService.getLatestBidChkaptByCriteria({ wkut: bidPrjDTO.wkut, prjno: bidPrjDTO.prjno }).then(chkaptData => {
          if (new Date(chkaptData.aokDate) >= new Date('2018-07-01')) {
            prjEnviOutputArray.push(`(連續未發生最多天數${enviDateSum}天)`);
          }
          //產分加分的選項
          let scoreArray = generateNoDisasterScore(bidPrjDTO, new Date(chkaptData.aokDate ? chkaptData.aokDate : null));
          noEnviScore.splice(0, noEnviScore.length, ...scoreArray);
        });
        if (envCount === 0) {
          prjEnviOutputArray.push('(本案系統中無環保裁處記錄，如有漏填請先於D3補填)。');
        } else {
          prjEnviOutputArray.push('(環保裁處記錄如有漏填請先於D3補填)。');
        }
        prjEnviMessageArray.splice(0, prjEnviMessageArray.length, ...prjEnviOutputArray);
      });
    }

    //處理全民督工的內容
    let overSeeMessage = ref('');
    function checkPrjOverSee(bidPrjDTO: IBidProject) {
      let overSeeDateString = '';
      bidOverSeeService.getBidOverseeByCriteria({ wkut: bidPrjDTO.wkut, prjno: bidPrjDTO.prjno }).then(bidOverSees => {
        if (bidOverSees && bidOverSees.length > 0) {
          bidOverSees.forEach(bidOverSee => {
            if (bidOverSee.seqDate) {
              overSeeDateString = overSeeDateString + formatDateInChinese(new Date(bidOverSee.seqDate)) + ',';
            }
          });
        }
        if (overSeeDateString !== '') {
          overSeeMessage.value = '全民督工被通報日期：' + overSeeDateString;
        } else {
          overSeeMessage.value = '(本案無全民督工被通報記錄)';
        }
      });
    }

    //產分無職災 & ENVI 加分的選項
    //回傳選項的陣列
    function generateNoDisasterScore(bidPrjDTO: IBidProject, chkAptAokDate: Date): any[] {
      let rfMax = 0;
      let scorceArray = [];
      rfMax = Math.floor((bidPrjDTO.aworkDay ? bidPrjDTO.aworkDay : bidPrjDTO.finWays) / 30);
      if (rfMax === 0) rfMax = 1;
      if (chkAptAokDate > new Date('2018-07-01')) {
        for (let i = 0; i <= 30; i += 3) {
          if (i <= rfMax * 3) {
            const selectOption = {
              value: 0,
              text: '',
            };
            selectOption.value = i * 0.1;
            selectOption.text = (i * 0.1).toFixed(1).toString() + '分';
            scorceArray.push(selectOption);
          }
        }
      } else {
        for (let i = 0; i <= 30; i++) {
          if (i <= rfMax) {
            const selectOption = {
              value: 0,
              text: '',
            };
            selectOption.value = i * 0.1;
            selectOption.text = (i * 0.1).toFixed(1).toString() + '分';
            scorceArray.push(selectOption);
          }
        }
      }
      return scorceArray;
    }
    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
      bidProjectService.findBidProjectByWkutAndPrjno(wkut, prjno).then(result => {
        Object.assign(bidProjectDTO, result);
        afterGetProjectData(result);
      });
    }
    /**
     * 處理處理初驗、複驗及驗收過程之缺失與改善天數
     */
    function getLastestChkapt(wkut, prjno) {
      const criteria = {
        wkut: wkut,
        prjno: prjno,
      };

      bidChkaptService.getLatestBidChkaptByCriteria(criteria).then(chkaptData => {
        Object.assign(bidChkaptDTO, chkaptData);
        handleChkaptData(chkaptData);
      });
    }

    //定義拿到project之後要做的事，所有的邏輯都包裝在這裡
    function afterGetProjectData(bidPrjDTO: IBidProject) {
      //處理 履約成本及違約金	提出替代方案
      judgeSubstitudePropose(bidPrjDTO.wkut, bidPrjDTO.prjno, bidPrjDTO.aeDate);
      //拿到chkapt dto
      getLastestChkapt(bidPrjDTO.wkut, bidPrjDTO.prjno);
      //拿到govCheck的 dto list
      getCheckScore(bidPrjDTO.wkut, bidPrjDTO.prjno);
      //判斷營造法
      checkConstructionIndustry(bidPrjDTO);
      //判斷得獎
      checkProjectPrice(bidPrjDTO);
      //判斷公安
      checkProjectSafe(bidPrjDTO);
      //判斷環境衛生
      checkProjectEnvi(bidPrjDTO);
    }

    function setTempProjectKey() {
      if (bidProjectKeyProp.value) {
        tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
        tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      }
    }

    //產生分數的下拉式選單
    function createPointSelect(start: number, end: number, interval: number): any[] {
      const pointArray = [];
      for (let i = start; i <= end; i += interval) {
        const selectOption = {
          value: 0,
          text: '',
        };
        selectOption.value = i;
        selectOption.text = i.toFixed(1).toString() + '分';
        pointArray.push(selectOption);
      }
      return pointArray;
    }

    //送出表單前的處理
    function processDataToSendBackEnd(bidKpifinData: IBidKpifin) {
      form.kscore = totalScore.value;
    }

    function processCreateNew(bidKpifinData: IBidKpifin) {
      bidKpifinData.wkut = tempProjectKey.wkut;
      bidKpifinData.prjno = tempProjectKey.prjno;
      bidKpifinData.idate = new Date();
      return bidKpifinData;
    }

    //送出kpifin
    function submitFormData(curFormStatus: formStatusEnum) {
      // console.log(form);
      checkValidity().then(bool => {
        if (bool) {
          processDataToSendBackEnd(form);
          switch (curFormStatus) {
            case formStatusEnum.CREATE:
              form = processCreateNew(form);
              bidKpifinService.saveBidKpifin(form).then(result => {
                // console.log(result);
                navigateByNameAndParams('bidAad008InfoMenu', {
                  bidProjectKey: { wkut: form.wkut, prjno: form.prjno },
                });
              });
              break;
            case formStatusEnum.MODIFY:
              bidKpifinService.updateBidKpifin(form).then(result => {
                // console.log(result);
                navigateByNameAndParams('bidAad008InfoMenu', {
                  bidProjectKey: { wkut: form.wkut, prjno: form.prjno },
                });
              });
              break;
            case formStatusEnum.INFO:
              bidKpifinService
                .deleteBidKpifin({
                  wkut: form.wkut,
                  prjno: form.prjno,
                  idate: form.idate,
                  seq: form.seq,
                })
                .then(result => {
                  // console.log(result);
                  notificationService.info('資料刪除成功');
                  navigateByNameAndParams('bidAad008InfoMenu', {
                    bidProjectKey: { wkut: form.wkut, prjno: form.prjno },
                  });
                });
              break;
          }
        } else {
          //前端驗證沒過
        }
      });
    }

    //回前一頁
    function backToPreviousPage() {
      navigateByNameAndParams('bidAad008InfoMenu', {
        bidProjectKey: { wkut: tempProjectKey.wkut, prjno: tempProjectKey.prjno },
      });
    }

    //往說明頁
    function toIlluPage() {
      navigateByNameAndParams('bidAad008Illustration', {
        bidProjectKey: bidProjectKeyProp.value,
        bidKpifinKey: bidKpifinKeyProp.value,
        formStatus: formStatusProp.value,
      });
    }
    //往基準頁
    function toBenchMarkPage() {
      navigateByNameAndParams('bidAad008BenchMark', {
        bidProjectKey: bidProjectKeyProp.value,
        bidKpifinKey: bidKpifinKeyProp.value,
        formStatus: formStatusProp.value,
      });
    }

    //處理剛接到的kpifin資料
    function handleBEKpifinData(kpifinData: IBidKpifin) {
      kpifinData.idate = new Date(kpifinData.idate);
      kpifinData.confDate = new Date(kpifinData.confDate);
    }

    function getRatioByProjectCtsum(ctsum: number): number {
      let rb01 = 0;
      if (ctsum > 5000000) {
        rb01 = 2.0;
      } else if (ctsum > 3000000) {
        rb01 = 1.8;
      } else if (ctsum > 1000000) {
        rb01 = 1.5;
      } else if (ctsum > 500000) {
        rb01 = 1.2;
      } else if (ctsum > 200000) {
        rb01 = 1.0;
      } else if (ctsum > 50000) {
        rb01 = 0.8;
      } else if (ctsum > 10000) {
        rb01 = 0.7;
      } else if (ctsum > 1000) {
        rb01 = 0.6;
      } else {
        rb01 = 0.0;
      }
      return rb01;
    }

    function getRd01RatioByProjectCtsum(ctsum: number): number {
      let rd01 = 0;
      if (ctsum > 5000000) {
        rd01 = 0.6;
      } else if (ctsum > 3000000) {
        rd01 = 0.7;
      } else if (ctsum > 1000000) {
        rd01 = 0.8;
      } else if (ctsum > 500000) {
        rd01 = 1.0;
      } else if (ctsum > 200000) {
        rd01 = 1.2;
      } else if (ctsum > 50000) {
        rd01 = 1.5;
      } else if (ctsum > 10000) {
        rd01 = 1.8;
      } else {
        rd01 = 2.0;
      }
      return rd01;
    }
    function getRd03RateByProjectFinWay(finWay: number): number {
      let rd03rat = 0;
      if (finWay > 1825) {
        rd03rat = 0.5;
      } else if (finWay > 1460) {
        rd03rat = 0.6;
      } else if (finWay > 1095) {
        rd03rat = 0.7;
      } else if (finWay > 730) {
        rd03rat = 0.8;
      } else if (finWay > 365) {
        rd03rat = 0.9;
      } else {
        rd03rat = 1.0;
      }
      return rd03rat;
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        setTempProjectKey();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );
    watch(
      bidKpifinKeyProp,
      bidKpifinKeyValue => {
        if (bidKpifinKeyValue) {
          switch (formStatusProp.value.formStatus) {
            case formStatusEnum.CREATE:
              currentFormStatus.value = formStatusEnum.CREATE;
              Object.assign(formDefault, new BidKpifin());
              reset();
              Object.assign(form, formDefault);
              form.wkut = bidKpifinKeyValue.wkut;
              form.prjno = bidKpifinKeyValue.prjno;
              delete form['id'];
              break;
            case formStatusEnum.MODIFY:
              currentFormStatus.value = formStatusEnum.MODIFY;
              bidKpifinService
                .getBidKpifinByKey({
                  wkut: bidKpifinKeyProp.value.wkut,
                  prjno: bidKpifinKeyProp.value.prjno,
                  idate: bidKpifinKeyProp.value.idate,
                  seq: bidKpifinKeyProp.value.seq,
                })
                .then(bidKpifinData => {
                  handleBEKpifinData(bidKpifinData);
                  Object.assign(form, bidKpifinData);
                  Object.assign(formDefault, bidKpifinData);
                  reset();
                });

              break;
            case formStatusEnum.INFO:
              currentFormStatus.value = formStatusEnum.INFO;
              bidKpifinService
                .getBidKpifinByKey({
                  wkut: bidKpifinKeyProp.value.wkut,
                  prjno: bidKpifinKeyProp.value.prjno,
                  idate: bidKpifinKeyProp.value.idate,
                  seq: bidKpifinKeyProp.value.seq,
                })
                .then(bidKpifinData => {
                  handleBEKpifinData(bidKpifinData);
                  Object.assign(form, bidKpifinData);
                  Object.assign(formDefault, bidKpifinData);
                  reset();
                });

              break;
          }
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
      } else if (tempProjectKey.wkut.length > 0) {
      }
    });

    return {
      projectInfo,
      bidProjectKeyProp,
      $v,
      form,
      bidProjectDTO,
      currentFormStatus,
      formStatusEnum,
      isSubstitudePropose, //是否提出替代方案 從prj month 來的
      bidChkaptDTO,
      fchkcnt, //初驗實際改善天數
      achkcnt, //複驗實際改善天數
      fachkcnt, //累計改善天數...就是上面兩個加起來
      averageScore, //從gov check 撈出來的平均成績
      govChkInfoArr,
      isUntdn, //使否為營造業法之工程(從project 判斷)
      isCivilEngineering, //是否為土木工程(從project 的ccut判斷)
      priceGradeAYear, //獎項年度
      priceGradeBYear, //獎項年度
      priceGradeCYear, //獎項年度
      prjSafeMessageArray, //無職災的字串陣列
      prjEnviMessageArray, //環保情形字串陣列
      noDisasterScore, //產分無職災加分的選項
      noEnviScore, //無環保加分,全民督工加分
      overSeeMessage, //全民督工訊息
      totalScore,
      reset,
      backToPreviousPage,
      createPointSelect,
      useValidation,
      validateState,
      submitFormData,
      formatDateInChinese,
      dateDiffinDays,
      getRatioByProjectCtsum,
      getRd01RatioByProjectCtsum,
      getRd03RateByProjectFinWay,
      commaFormatter,
      toIlluPage, //往說明頁
      toBenchMarkPage, //往基準頁
    };
  },
};
</script>
<style scoped></style>
