<template>
  <div>    
      <section class="container pt-2">
        <div class="card">
          <div class="card-body col-11">
            <b-form-row>
             <!-- 機構查詢 -->
              <i-form-group-check  :label="$t('label.agencyName') + '：'">
                <b-form-input v-model="$v.orgName.$model" type="text" :disabled="true"></b-form-input>
              </i-form-group-check>
              <i-form-group-check  :label="$t('label.agencyId') + '：'">
                <b-form-input v-model="$v.pccOrgId.$model" type="text" :disabled="true"></b-form-input>
              </i-form-group-check>           
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <b-button v-b-modal.query-agent-modal variant="info">機構查詢</b-button>    
              </b-col>
            </b-form-row>
           
              <b-modal
                id="query-agent-modal"
                size="lg"
                title="機關查詢"
                header-bg-variant="secondary"
                header-text-variant="light"
              >
                <b-form-row>
                  <!-- 機關 -->
                  <i-form-group-check class="col-12" label-cols="2" labelStar :label="$t('label.agency') + '：'">
                    <b-col cols="4" class="text-left">
                      <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="D">機關名稱</b-form-radio>
                      <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="E">機關代碼</b-form-radio>                      
                    </b-col>
                    <b-col cols="8" class="text-left">
                      <i-tree-select
                        v-model="$v.wkutNormal.$model"
                        placeholder="關鍵字查詢(至少輸入四碼)"
                        :multiple="false"
                        :async="true"
                        :load-options="loadOptions"
                      ></i-tree-select>
                    </b-col>
                  </i-form-group-check>
                </b-form-row>

                <b-form-row>
                  <!-- 機關資訊 -->
                  <i-form-group-check class="col-12" label-cols="2" :label="$t('label.agencyInfo') + '：'">
                    <b-form-row class="col-12 border">
                      <b-col class="col-12">{{ $t('label.agencyName') }} : {{ $v.orgName.$model }} </b-col>
                      <b-col class="col-12 pt-2">{{ $t('label.agencyAddr') }} : {{ $v.addr.$model }}</b-col>
                      <b-col class="col-12 pt-2">{{ $t('label.agencyTel') }} : {{ $v.tel.$model }}</b-col>
                      <b-col class="col-12 pt-2">{{ $t('label.agencyFax') }} : {{ $v.fax.$model }}</b-col>
                    </b-form-row>
                  </i-form-group-check>
                </b-form-row>
                <template #modal-footer="{ cancel }">
                  <b-button @click="cancel()" variant="info">OK</b-button>
                </template>
              </b-modal>
          </div>
        </div>
      </section> 
  </div>
</template>

<script lang="ts">
import { useValidation } from '@/shared/form';
import { computed, onActivated, reactive, ref, onMounted, watch } from '@vue/composition-api';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { required } from '@/shared/validators';
import { navigateByNameAndParams } from '@/router/router';
import ItreeSelect from '@/shared/i-tree-select/i-tree-select.vue';
import { ASYNC_SEARCH } from '@riophae/vue-treeselect';

export default {
  name: 'viewDemo06',
  components: {
    'i-tree-select': ItreeSelect,
  },
  props: {
    isReload: {
      required: false,
    },
  },
  setup(props, context) {

    let formDefault = {
     pccOrgId: '',
     orgName: '',
     addr: '',
     tel: '',
     fax: '',
     wkutNormal: null,
     radioType: 'D',
    //  inputFile: [],
   }

   const form = reactive(Object.assign({}, formDefault));

   const rules = {
      pccOrgId: {},
      orgName: {},
      addr: {},
      tel: {},
      fax: {},
      wkutNormal: {},
      radioType: {},
    //  inputFile: {},
   }

   const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

   const notificationService: NotificationService = useNotification();

    //利用tree select 自帶的API來動態產生選項
    function loadOptions({ action, searchQuery, callback }) {
      //判斷注音
      let pattern = /[\u3105-\u3129\u02CA\u02C7\u02CB\u02D9]/;
      if (action === ASYNC_SEARCH && !pattern.test(searchQuery) && searchQuery.toString().length > 3) {
        const config = { headers: { Accept: 'application/json', 'Content-Type': 'application/json' } };
        const agentName = new Promise((resolve, reject) => {
          axios
            .post('/service/adm-agent-findAll', { keyWord: searchQuery.toString(), type: form.radioType }, config)
            .then(res => {
              resolve(res.data);
            })
            .catch(err => {
              reject(err);
            });
        }).then(res => {
          const options = [];
          res.forEach(ele => {
            const agentOption = { id: '', label: '' };
            if (form.radioType === 'D') {
              agentOption.id = ele.orgId;
              agentOption.label = ele.orgName;
              form.userId = ele.pccOrgId;
            } else {
              agentOption.id = ele.orgId;
              agentOption.label = ele.orgId;
              form.userId = ele.pccOrgId;
            }
            options.push(agentOption);
          });
          callback(null, options);
        });
      }
    }

    watch(
      () => form.wkutNormal,
      newValue => {
        console.log(form.wkutNormal);
        if (form.wkutNormal !== undefined && form.wkutNormal !== null) {
          findAdmAgent();
        } else {
          reset();
        }
      }
    );

    function findAdmAgent() {
      let url = '/service/adm-agent-info';
      axios
        .post(url, form)
        .then(({ data }) => {
          Object.assign(form, data);
        })
        .catch(notificationErrorHandler(notificationService));
    }


    return {
      $v,
      reset,
      loadOptions,
    };
  },
};
</script>

<style>
</style>
