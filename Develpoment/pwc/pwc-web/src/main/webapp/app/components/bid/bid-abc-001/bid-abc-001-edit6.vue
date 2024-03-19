<template>
  <div>
    <div v-if="formStatusRef === FormStatusEnum.DELETE || formStatusRef === FormStatusEnum.MODIFY">
      <!-- <div class="card">
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </div> -->

      <div class="card">
        <b-row class="card-header">
          <b-col cols="4">
            <h5 class="m-0">修改查核缺失</h5>
          </b-col>
          <!-- <b-col cols="4"> 查核日期:110/09/01</b-col> -->
        </b-row>
        <!-- <div class="card-header">
        <h5 class="m-0">查核委員編輯</h5>
      </div> -->
        <div class="card-body col-12">
          <!-- 查核日期-->
          <b-form-row>
            <i-form-group-check :labelStar="true" :label="'查核日期'" :item="$v.checkDate">
              <b-form-input v-model="$v.checkDate.$model"></b-form-input>
              <div class="col-8">
                <div class="d-inline"></div>
              </div>
            </i-form-group-check>
            <!-- 主辦機關-->
            <i-form-group-check :label="'主辦機關'" :item="$v.checkUnit">
              <b-form-input v-model="$v.checkUnit.$model"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <!-- 參與人員-->
          <b-form-row>
            <i-form-group-check :label="'參與人員'" :item="$v.checker">
              <b-form-input v-model="$v.checker.$model" :state="validateState($v.checker)"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 缺失編號-->
          <b-form-row>
            <i-form-group-check :label="'缺失編號'" :item="$v.errNum">
              <b-form-input v-model="$v.errNum.$model" :state="validateState($v.errNum)"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 缺失等級-->
          <b-form-row>
            <i-form-group-check :label="'缺失等級'" :item="$v.errFlag">
              <b-form-radio-group content-cols-md="12" v-model="$v.errFlag.$model">
                <b-form-radio value="A">嚴重</b-form-radio>
                <b-form-radio value="B">中等</b-form-radio>
                <b-form-radio value="C">輕微</b-form-radio>
              </b-form-radio-group>
              <span style="color: blue">重大缺失系指渉結構及使用安全等之缺失，應依工程施工查核作業辦法第八條辦理</span>
            </i-form-group-check>
          </b-form-row>
          <!-- 缺失內容 -->
          <b-form-row>
            <i-form-group-check :label="'缺失內容'" :item="$v.errMain">
              <b-form-textarea rows="3" max-rows="4" v-model="$v.errMain.$model" :state="validateState($v.errMain)"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <!-- 扣點數 -->
          <b-form-row>
            <i-form-group-check :label="'扣點數'" :item="$v.roveFlag">
              <b-form-select v-model="$v.roveFlag.$model" :state="validateState($v.roveFlag)"></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <!-- 記點對象 -->
          <b-form-row v-if="formStatusRef === FormStatusEnum.MODIFY">
            <i-form-group-check :label="'記點對象'" :item="$v.roveTo">
              <b-form-select v-model="$v.roveTo.$model"></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <!-- 併扣PCM廠商 -->
          <b-form-row v-if="formStatusRef === FormStatusEnum.MODIFY">
            <i-form-group-check :label="'併扣PCM廠商'" :item="$v.rovePcm">
              <b-form-radio-group content-cols-md="12" v-model="$v.rovePcm.$model">
                <b-form-radio value="A">是</b-form-radio>
                <b-form-radio value="B">否</b-form-radio>
              </b-form-radio-group>
            </i-form-group-check>
          </b-form-row>
          <!-- 併扣監造廠商 -->
          <b-form-row v-if="formStatusRef === FormStatusEnum.MODIFY">
            <i-form-group-check :label="'併扣監造廠商'" :item="$v.roveSc">
              <b-form-radio-group content-cols-md="12" v-model="$v.roveSc.$model">
                <b-form-radio value="A">是</b-form-radio>
                <b-form-radio value="B">否</b-form-radio>
              </b-form-radio-group>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-table-simple hover small border caption-top responsive>
          <caption></caption>
          <b-thead> </b-thead>
          <b-tbody>
            <b-tr>
              <b-td>
                <b-form-row>
                  <i-form-group-check :label="'查核日期:'" :item="$v.checkDate">
                    <b-form-row>
                      <i-date-picker v-model="$v.checkDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
                    </b-form-row>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
              <b-td>
                <b-form-row>
                  <i-form-group-check :label="'主辦機關:'" :item="$v.checkUnit">
                    <b-form-input v-model="$v.checkUnit.$model"></b-form-input>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
            </b-tr>
            <b-tr>
              <b-form-row>
                <i-form-group-check :label="'參與人員'" :item="$v.checker">
                  <b-form-input v-model="$v.checker.$model" :state="validateState($v.checker)"></b-form-input>
                </i-form-group-check> </b-form-row
            ></b-tr>
          </b-tbody>
        </b-table-simple> -->

          <!-- <b-table-simple hover small border caption-top>
          <caption></caption>
          <b-thead> </b-thead>
          <b-tbody>
            <b-tr>
              <b-form-row>
                <i-form-group-check :label="'缺失編號'" :item="$v.errNum">
                  <b-form-input v-model="$v.errNum.$model" :state="validateState($v.errNum)"></b-form-input>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
            <b-tr>
              <b-form-row>
                <i-form-group-check :label="'缺失等級'" :item="$v.errFlag">
                  <b-form-radio-group content-cols-md="12" v-model="$v.errFlag.$model">
                    <b-form-radio value="A">嚴重</b-form-radio>
                    <b-form-radio value="B">中等</b-form-radio>
                    <b-form-radio value="C">輕微</b-form-radio>
                  </b-form-radio-group>
                  <span style="color: blue">重大缺失系指渉結構及使用安全等之缺失，應依工程施工查核作業辦法第八條辦理</span>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
            <b-tr>
              <b-form-row>
                <i-form-group-check :label="'缺失內容'" :item="$v.errMain">
                  <b-form-textarea rows="3" max-rows="4" v-model="$v.errMain.$model" :state="validateState($v.errMain)"></b-form-textarea>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
            <b-tr>
              <b-form-row>
                <i-form-group-check :label="'扣點數'" :item="$v.roveFlag">
                  <b-form-select v-model="$v.roveFlag.$model" :state="validateState($v.roveFlag)"></b-form-select>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
            <b-tr>
              <b-form-row>
                <i-form-group-check :label="'記點對象'" :item="$v.roveTo">
                  <b-form-select v-model="$v.roveTo.$model"></b-form-select>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
            <b-tr>
              <b-form-row>
                <i-form-group-check :label="'併扣PCM廠商'" :item="$v.rovePcm">
                  <b-form-radio-group content-cols-md="12" v-model="$v.rovePcm.$model">
                    <b-form-radio value="A">是</b-form-radio>
                    <b-form-radio value="B">否</b-form-radio>
                  </b-form-radio-group>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
            <b-tr>
              <b-form-row>
                <i-form-group-check :label="'併扣監造廠商'" :item="$v.roveSc">
                  <b-form-radio-group content-cols-md="12" v-model="$v.roveSc.$model">
                    <b-form-radio value="A">是</b-form-radio>
                    <b-form-radio value="B">否</b-form-radio>
                  </b-form-radio-group>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
          </b-tbody>
        </b-table-simple> -->

          <div class="text-center mt-2" v-if="formStatusRef === FormStatusEnum.CREATE || formStatusRef === FormStatusEnum.MODIFY">
            <i-button type="save"></i-button>
            <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
          </div>
          <div class="text-center mt-2" v-if="formStatusRef === FormStatusEnum.DELETE">
            <i-button type="x-lg"></i-button>
            <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
          </div>
        </div>
      </div>
    </div>

    <div v-if="formStatusRef === FormStatusEnum.CREATE">
      <div class="card">
        <b-row class="card-header">
          <b-col cols="4">
            <h5 class="m-0">新增查核缺失</h5>
          </b-col>
        </b-row> 
        <div class="card-body col-12">      
                <b-form-row>
                  <i-form-group-check :label="'查核日期:'" :item="$v.checkDate">
                    <b-form-row>
                      <i-date-picker v-model="$v.checkDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
                    </b-form-row>
                  </i-form-group-check>
                   <i-form-group-check :label="'主辦機關:'" :item="$v.checkUnit">
                    <b-form-input v-model="$v.checkUnit.$model"></b-form-input>
                  </i-form-group-check>
                </b-form-row>         
              <b-form-row>
                <i-form-group-check :label="'參與人員'" :item="$v.checker">
                  <b-form-input v-model="$v.checker.$model" :state="validateState($v.checker)"></b-form-input>
                </i-form-group-check> </b-form-row>
          <b-table-simple  >
          <caption></caption>
            <b-thead>
                <b-tr>
                  <b-th style="context: center">序號</b-th>
                  <b-th style="context: center">缺失編號</b-th>
                  <b-th style="context: center">缺失等級</b-th>
                  <b-th style="context: center">缺失內容</b-th>
                  <b-th style="context: center">扣點數</b-th>
                </b-tr>
              </b-thead>
          <b-tbody>
            <b-tr>
              <b-td>1</b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
               </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
              <b-td>
                <b-form-row>
             <i-form-group-check  :item="$v.errMain">
              <b-form-textarea  v-model="$v.errMain.$model" :state="validateState($v.errMain)"></b-form-textarea>
             </i-form-group-check>
             </b-form-row>
              </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
            </b-tr>
            <b-tr>
              <b-td>2</b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
               </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
              <b-td>
                <b-form-row>
             <i-form-group-check  :item="$v.errMain">
              <b-form-textarea  v-model="$v.errMain.$model" :state="validateState($v.errMain)"></b-form-textarea>
             </i-form-group-check>
             </b-form-row>
              </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
            </b-tr>
            <b-tr>
              <b-td>3</b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
               </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
              <b-td>
                <b-form-row>
             <i-form-group-check  :item="$v.errMain">
              <b-form-textarea  v-model="$v.errMain.$model" :state="validateState($v.errMain)"></b-form-textarea>
             </i-form-group-check>
             </b-form-row>
              </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
            </b-tr>
            <b-tr>
              <b-td>4</b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
               </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
              <b-td>
                <b-form-row>
             <i-form-group-check  :item="$v.errMain">
              <b-form-textarea  v-model="$v.errMain.$model" :state="validateState($v.errMain)"></b-form-textarea>
             </i-form-group-check>
             </b-form-row>
              </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
            </b-tr>
            <b-tr>
              <b-td>5</b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
               </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
              <b-td>
                <b-form-row>
             <i-form-group-check  :item="$v.errMain">
              <b-form-textarea  v-model="$v.errMain.$model" :state="validateState($v.errMain)"></b-form-textarea>
             </i-form-group-check>
             </b-form-row>
              </b-td>
              <b-td> <b-form-row>
                  <i-form-group-check  :item="$v.checkUnit">
                    <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
            </b-tr>
            <b-tr>
            </b-tr>
          </b-tbody>
        </b-table-simple>

          <div class="text-center mt-2" v-if="formStatusRef === FormStatusEnum.CREATE">
            <i-button type="save"></i-button>
            <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
            <i-button type="x-circle"></i-button>
          </div>
        </div>
      </div>
    </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import { handleBack } from '@/router/router';
import FormStatusEnum from '@/shared/enum';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';

export default {
  name: 'bidAbc001Edit6',
  props: {
    bidProjectModifyKeyProp: {
      // type: Object
      required: false,
    },
    formStatus: {
      required: false,
      type: String,
    },
  },
  components: {
    bidProjectInfo,
  },

  setup(props, context) {
    const bidProjectModifyKeyProps: Ref<any> = toRef(props, 'bidProjectModifyKeyProp');
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料
    console.log(formStatusRef, 'formStatusRef5454');
    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
      DELETE = '刪除',
    }

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      checkDate: '111/07/07', //查核日期
      checkUnit: '新北市政府', //主辦關機關
      checker: '陳曉東', //參與人員
      errNum: '5.09.08 無工程告示牌或內容未符合規定', // 缺失編號
      errFlag: 'C', //缺失等級
      errMain: '工程告示牌內容為符合最新規定', //缺失內容
      roveFlag: '', //扣點數
      roveTo: '', //記點對象
      rovePcm: '', //併扣PCM廠商
      roveSc: '', //併扣監造廠商
    };

    const rules = {
      checkDate: { required: required },
      checkUnit: { required: required },
      checker: { required: required },
      errNum: { required: required },
      errFlag: { required: required },
      errMain: { required: required },
      roveFlag: { required: required },
      roveTo: { required: required },
      rovePcm: { required: required },
      roveSc: { required: required },
    };

    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 是否需要重新查詢
    const isReload = ref(false);

    //回上一頁
    function backToPreviousPage() {
      reset();
      handleBack({ isReload: isReload.value });
      isReload.value = false;
    }

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

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };

    watch(
      bidProjectModifyKeyProps,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    return {
      $v,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      projectInfo,
      FormStatusEnum,
      formStatusRef,
    };
  },
};
</script>

<style></style>
