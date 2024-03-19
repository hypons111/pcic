<template>
  <div>
    <section class="container">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      <div class="card" v-if="formStatusRef == formStatusEnum.INFO">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            查核懲處狀況紀錄
          </h5>
        </div>
        <div class="card-body">
          <i-table
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(checkString)="row">
              <p style="white-space: pre-wrap; margin-bottom: 0" v-text="row.item.checkString"></p>
            </template>
            <template #cell(roveString)="row">
              <p style="white-space: pre-wrap; margin-bottom: 0" v-text="row.item.roveString"></p>
            </template>
            <template #cell(action)="row">
              <i-button type="pencil-square" @click="toEditView(row.item)"></i-button>
            </template>
          </i-table>
        </div>
      </div>
      <div class="card" v-if="formStatusRef == formStatusEnum.MODIFY">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            查核懲處資料
          </h5>
        </div>
        <div class="card-body">
          <b-form-row class="align-items-center">
            <b-col cols="6" class="text-right"
              ><span>查核日期： {{ $v.checkDate.$model | dateToString }}</span></b-col
            >
            <b-col cols="6" class="text-left"
              ><span>查核單位： {{ $v.name.$model }}</span></b-col
            >
          </b-form-row>
          <b-form-row class="align-items-center">
            <b-col cols="6" class="text-center border"><span>獎懲類別</span></b-col>
            <b-col cols="2" class="text-center border"><span>查核單位建議</span></b-col>
            <b-col cols="4" class="text-center border"><span>工程執行機關實際懲處</span></b-col>
          </b-form-row>
          <b-form-row class="align-items-center border">
            <b-col cols="6">
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>函請主辦機關依法檢討相關人員責任：</span></b-col>
              </b-form-row>
            </b-col>
            <b-col cols="2">
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk0.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
            </b-col>
            <b-col cols="4">
              <b-form-row class="align-items-center">
                <b-col class="text-center border"><span>否</span></b-col>
              </b-form-row>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center border">
            <b-col cols="2" class="text-right"><span>對主辦機關</span></b-col>
            <b-col cols="4">
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>人員懲處：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>人員獎勵：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>通知機關另為適當處置並副知審計機關：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>函送監察院：</span></b-col>
              </b-form-row>
            </b-col>
            <b-col cols="2" class="text-center border">
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk1.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk14.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk12a.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk13.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
            </b-col>
            <b-col cols="4" class="text-center border">
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk1a">{{ $v.roveChk1a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk1a.$model" :options="formOptions.type" :state="validateState($v.roveChk1a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk14a">{{ $v.roveChk14a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk14a.$model" :options="formOptions.type" :state="validateState($v.roveChk14a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"><span>否</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"><span>否</span></b-col>
              </b-form-row>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center border">
            <b-col cols="2" class="text-right"><span>對專案管理</span></b-col>
            <b-col cols="4">
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>辦理扣款：</span></b-col>
              </b-form-row>
            </b-col>
            <b-col cols="2" class="text-center border">
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk7.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
            </b-col>
            <b-col cols="4" class="text-center border">
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk7a">{{ $v.roveChk7a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk7a.$model" :options="formOptions.type" :state="validateState($v.roveChk7a)" />
                  </div>
                </b-col>
              </b-form-row>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center border">
            <b-col cols="2" class="text-right"><span>對承欖廠商</span></b-col>
            <b-col cols="4">
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>依政府採購法第101條至第103條規定辦理：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>撤換工地負責人：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>撤換品管人員：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>撤換安衛人員：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>檢討工地主任責任：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>檢討專任工程人員責任：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>缺失嚴重部分拆除重做：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>辦理扣款：</span></b-col>
              </b-form-row>
            </b-col>
            <b-col cols="2" class="text-center border">
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk2.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk3.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk32.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk33.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk37.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk34.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk35.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk36.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
            </b-col>
            <b-col cols="4" class="text-center border">
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk2a">{{ $v.roveChk2a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk2a.$model" :options="formOptions.type" :state="validateState($v.roveChk2a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk3a">{{ $v.roveChk3a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk3a.$model" :options="formOptions.type" :state="validateState($v.roveChk3a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk32a">{{ $v.roveChk32a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk32a.$model" :options="formOptions.type" :state="validateState($v.roveChk32a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk33a">{{ $v.roveChk33a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk33a.$model" :options="formOptions.type" :state="validateState($v.roveChk33a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk37a">{{ $v.roveChk37a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk37a.$model" :options="formOptions.type" :state="validateState($v.roveChk37a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk34a">{{ $v.roveChk34a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk34a.$model" :options="formOptions.type" :state="validateState($v.roveChk34a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk35a">{{ $v.roveChk35a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk35a.$model" :options="formOptions.type" :state="validateState($v.roveChk35a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk36a">{{ $v.roveChk36a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk36a.$model" :options="formOptions.type" :state="validateState($v.roveChk36a)" />
                  </div>
                </b-col>
              </b-form-row>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center border">
            <b-col cols="2" class="text-right"><span>對監造廠商</span></b-col>
            <b-col cols="4">
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>檢討建築師責任：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>檢討技師責任：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>委辦監造撤換監造現場人員：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>自辦監造懲處監工人員：</span></b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-right border"><span>辦理扣款：</span></b-col>
              </b-form-row>
            </b-col>
            <b-col cols="2" class="text-center border">
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk4.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk42.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk43.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk45.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border"
                  ><span>{{ $v.roveChk44.$model | yesNoFilter }}</span></b-col
                >
              </b-form-row>
            </b-col>
            <b-col cols="4" class="text-center border">
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk4a">{{ $v.roveChk4a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk4a.$model" :options="formOptions.type" :state="validateState($v.roveChk4a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk42a">{{ $v.roveChk42a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk42a.$model" :options="formOptions.type" :state="validateState($v.roveChk42a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk43a">{{ $v.roveChk43a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk43a.$model" :options="formOptions.type" :state="validateState($v.roveChk43a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk45a">{{ $v.roveChk45a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk45a.$model" :options="formOptions.type" :state="validateState($v.roveChk45a)" />
                  </div>
                </b-col>
              </b-form-row>
              <b-form-row class="align-items-center">
                <b-col class="text-center border">
                  <span v-if="formDefault.roveChk44a">{{ $v.roveChk44a.$model | yesNoFilter }}</span>
                  <div v-else>
                    <b-form-radio-group v-model="$v.roveChk44a.$model" :options="formOptions.type" :state="validateState($v.roveChk44a)" />
                  </div>
                </b-col>
              </b-form-row>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center">
            <b-col cols="2" class="text-right"><span>實際獎懲狀況摘要</span></b-col>
            <b-col class="text-center">
              <b-form-textarea rows="3" v-model="$v.detel4.$model"></b-form-textarea>
            </b-col>
          </b-form-row>
          <b-row class="mt-2 justify-content-center" align-content="center">
            <i-button class="mr-2" type="save" @click="submitForm"> </i-button>
            <i-button class="mr-2" type="x-circle" @click="reset"> </i-button>
            <i-button class="mr-2" type="arrow-left" @click="toQueryView"> </i-button>
          </b-row>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { useValidation, validateState } from '@/shared/form';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { onBeforeMount, computed, reactive, ref, toRef, watch, Ref } from '@vue/composition-api';

export default {
  name: 'bidAad001EditInfo',
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  components: {
    bidProjectInfo,
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '', // 執行機關代碼
      prjno: '', // 標案編號
      checkDate: '', // 查核日期
      name: '', // 查核小組
      roveChk0: '', // 函請主辦機關依法檢討相關人員責任
      roveChk1: '', // 對主辦機關-人員懲處-查核單位建議
      roveChk1a: '', // 對主辦機關-人員懲處-工程執行機關實際懲處
      roveChk14: '', // 對主辦機關-人員獎勵-查核單位建議
      roveChk14a: '', // 對主辦機關-人員獎勵-工程執行機關實際懲處
      roveChk12a: '', // 對主辦機關-通知機關另為適當處置並副知審計機關-查核單位建議
      roveChk13: '', // 對主辦機關-函送監察院-查核單位建議
      roveChk7: '', // 對專案管理單位-辦理扣款-查核單位建議
      roveChk7a: '', // 對專案管理單位-辦理扣款-工程執行機關實際懲處
      roveChk2: '', // 對承欖廠商-依政府採購法第101條至第103條規定辦理-查核單位建議
      roveChk2a: '', // 對承欖廠商-依政府採購法第101條至第103條規定辦理-工程執行機關實際懲處
      roveChk3: '', // 對承欖廠商-撤換工地負責人-查核單位建議
      roveChk3a: '', // 對承欖廠商-撤換工地負責人-工程執行機關實際懲處
      roveChk32: '', // 對承欖廠商-撤換品管人員-查核單位建議
      roveChk32a: '', // 對承欖廠商-撤換品管人員-工程執行機關實際懲處
      roveChk33: '', // 對承欖廠商-撤換安衛人員-查核單位建議
      roveChk33a: '', // 對承欖廠商-撤換安衛人員-工程執行機關實際懲處
      roveChk37: '', // 對承欖廠商-檢討工地主任責任-查核單位建議
      roveChk37a: '', // 對承欖廠商-檢討工地主任責任-工程執行機關實際懲處
      roveChk34: '', // 對承欖廠商-檢討專任工程人員責任-查核單位建議
      roveChk34a: '', // 對承欖廠商-檢討專任工程人員責任-工程執行機關實際懲處
      roveChk35: '', // 對承欖廠商-缺失嚴重部分拆除重做-查核單位建議
      roveChk35a: '', // 對承欖廠商-缺失嚴重部分拆除重做-工程執行機關實際懲處
      roveChk36: '', // 對承欖廠商-辦理扣款-查核單位建議
      roveChk36a: '', // 對承欖廠商-辦理扣款-工程執行機關實際懲處
      roveChk4: '', // 對監造廠商-檢討建築師責任-查核單位建議
      roveChk4a: '', // 對監造廠商-檢討建築師責任-工程執行機關實際懲處
      roveChk42: '', // 對監造廠商-檢討技師責任-查核單位建議
      roveChk42a: '', // 對監造廠商-檢討技師責任-工程執行機關實際懲處
      roveChk43: '', // 對監造廠商-委辦監造撤換監造現場人員-查核單位建議
      roveChk43a: '', // 對監造廠商-委辦監造撤換監造現場人員-工程執行機關實際懲處
      roveChk45: '', // 對監造廠商-自辦監造懲處監工人員-查核單位建議
      roveChk45a: '', // 對監造廠商-自辦監造懲處監工人員-工程執行機關實際懲處
      roveChk44: '', // 對監造廠商-自辦監造懲處監工人員-查核單位建議
      roveChk44a: '', // 對監造廠商-自辦監造懲處監工人員-工程執行機關實際懲處
      detel4: '',
    };

    // 表單物件驗證規則
    const rules = {
      wkut: {}, // 執行機關代碼
      prjno: {}, // 標案編號
      checkDate: {}, // 查核日期
      name: {}, // 查核小組
      roveChk0: {}, // 函請主辦機關依法檢討相關人員責任
      roveChk1: {}, // 對主辦機關-人員懲處-查核單位建議
      roveChk1a: {}, // 對主辦機關-人員懲處-工程執行機關實際懲處
      roveChk14: {}, // 對主辦機關-人員獎勵-查核單位建議
      roveChk14a: {}, // 對主辦機關-人員獎勵-工程執行機關實際懲處
      roveChk12a: {}, // 對主辦機關-通知機關另為適當處置並副知審計機關-查核單位建議
      roveChk13: {}, // 對主辦機關-函送監察院-查核單位建議
      roveChk7: {}, // 對專案管理單位-辦理扣款-查核單位建議
      roveChk7a: {}, // 對專案管理單位-辦理扣款-工程執行機關實際懲處
      roveChk2: {}, // 對承欖廠商-依政府採購法第101條至第103條規定辦理-查核單位建議
      roveChk2a: {}, // 對承欖廠商-依政府採購法第101條至第103條規定辦理-工程執行機關實際懲處
      roveChk3: {}, // 對承欖廠商-撤換工地負責人-查核單位建議
      roveChk3a: {}, // 對承欖廠商-撤換工地負責人-工程執行機關實際懲處
      roveChk32: {}, // 對承欖廠商-撤換品管人員-查核單位建議
      roveChk32a: {}, // 對承欖廠商-撤換品管人員-工程執行機關實際懲處
      roveChk33: {}, // 對承欖廠商-撤換安衛人員-查核單位建議
      roveChk33a: {}, // 對承欖廠商-撤換安衛人員-工程執行機關實際懲處
      roveChk37: {}, // 對承欖廠商-檢討工地主任責任-查核單位建議
      roveChk37a: {}, // 對承欖廠商-檢討工地主任責任-工程執行機關實際懲處
      roveChk34: {}, // 對承欖廠商-檢討專任工程人員責任-查核單位建議
      roveChk34a: {}, // 對承欖廠商-檢討專任工程人員責任-工程執行機關實際懲處
      roveChk35: {}, // 對承欖廠商-缺失嚴重部分拆除重做-查核單位建議
      roveChk35a: {}, // 對承欖廠商-缺失嚴重部分拆除重做-工程執行機關實際懲處
      roveChk36: {}, // 對承欖廠商-辦理扣款-查核單位建議
      roveChk36a: {}, // 對承欖廠商-辦理扣款-工程執行機關實際懲處
      roveChk4: {}, // 對監造廠商-檢討建築師責任-查核單位建議
      roveChk4a: {}, // 對監造廠商-檢討建築師責任-工程執行機關實際懲處
      roveChk42: {}, // 對監造廠商-檢討技師責任-查核單位建議
      roveChk42a: {}, // 對監造廠商-檢討技師責任-工程執行機關實際懲處
      roveChk43: {}, // 對監造廠商-委辦監造撤換監造現場人員-查核單位建議
      roveChk43a: {}, // 對監造廠商-委辦監造撤換監造現場人員-工程執行機關實際懲處
      roveChk45: {}, // 對監造廠商-自辦監造懲處監工人員-查核單位建議
      roveChk45a: {}, // 對監造廠商-自辦監造懲處監工人員-工程執行機關實際懲處
      roveChk44: {}, // 對監造廠商-自辦監造懲處監工人員-查核單位建議
      roveChk44a: {}, // 對監造廠商-自辦監造懲處監工人員-工程執行機關實際懲處
      detel4: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 下拉選單選項
    const formOptions = reactive({
      type: [
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
    });

    // 執行結果
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields: [
        {
          key: 'checkDate',
          label: '查核日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            var date = new Date(value);
            const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return year + '/' + month + '/' + day;
          },
        },
        {
          key: 'name',
          label: '查核小組',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'detel3',
          label: '查核意見',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checkString',
          label: '建議懲處',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'roveString',
          label: '實際懲處',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = newValue => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          dataPromise.value = axios
            .post('/bid-gov-check/criteria-jpa', newValue) //後端分頁
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toEditView = (bidGovCheck: IbidGovCheck) => {
      formStatusRef.value = formStatusEnum.MODIFY;
      createDefaultValue(bidGovCheck);
    };

    const toQueryView = () => {
      formStatusRef.value = formStatusEnum.INFO;
      handleQuery(projectInfo);
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              putForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const putForm = () => {
      let url: string;
      form.updateUser = 'SYS';
      form.updateDate = new Date();
      form.createUser = 'SYS';
      form.createDate = new Date();
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/bid-gov-check/${form.wkut}/${form.prjno}/${form.checkDate}`;
      }
      axios
        .put(url, form)
        .then(({ data }) => {
          createDefaultValue(data);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery(newValue);
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      formStatusEnum,
      formStatusRef,
      formDefault,
      $v,
      reset,
      formOptions,
      validateState,
      queryStatus,
      dataPromise,
      table,
      handleQuery,
      toEditView,
      toQueryView,
      submitForm,
    };
  },
  filters: {
    yesNoFilter: (value: string) => {
      if (value === 'Y') {
        return '是';
      } else {
        return '否';
      }
    },
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
  },
};
</script>
<style></style>
