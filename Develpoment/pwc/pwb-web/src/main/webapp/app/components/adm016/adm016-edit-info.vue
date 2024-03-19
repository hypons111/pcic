<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視後臺登入歷程
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check :label="$t('label.adm016.id') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.id }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm016.functionId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.functionId }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm016.functionName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.functionName }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm016.functionExtendId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.functionExtendId }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm016.sysCategory') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.sysCategory | sysCategoryFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm016.jwtToken') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.jwtToken }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm016.tableName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.tableName }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm016.tableKey') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.tableKey }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm016.updateValue') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.updateValue }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm016.recordContent') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.recordContent }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm016.createUserSys') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.createUserSys }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm016.createTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.createTime | dateTimeFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-button-toolbar class="float-right">
              <i-button class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>
          </b-form>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { validateState } from '@/shared/form';
import { computed, reactive, ref, toRefs, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import { dateTimeFormatter, executeFormatter, sysCategoryFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'Adm016EditInfo',
  props: {
    id: {
      required: true,
    },
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef = ref(formStatusEnum.INFO);

    // modal物件
    const $bvModal = useBvModal();
    // 建立表單物件預設值
    let formDefault = {
      id: '',
      functionId: '',
      functionName: '',
      functionExtendName: '',
      sysCategory: '',
      jwtToken: '',
      tableName: '',
      tableKey: '',
      updateValue: '',
      recordContent: '',
      createUserSys: '',
      createTime: '',
    };
    const isReload = ref(false);
    // 建立一份新的表單物件
    const form = reactive(Object.assign({}, formDefault));
    // 使用者資訊
    const userUnit = computed(() => {
      let account = context.root.$store.getters.account;
      if (account) {
        return account.unit;
      } else {
        return '';
      }
    });

    const notificationService: NotificationService = useNotification();

    const queryOptions = reactive({
      sysCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: '1', text: '工程雲服務網' },
        { value: '2', text: '工程雲後臺管理' },
        { value: '3', text: '全球資訊網' },
        { value: '4', text: '全球資訊網後臺管理' },
      ],
    });

    function initFunctionUsedRecordInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-function-used-records/${props.id}`)
        .then(({ data }) => {
          if (data && data['id']) {
            Object.assign(formDefault, data);
            Object.assign(form, formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 返回列表
    function toQueryView() {
      navigateByNameAndParams('adm016', { isReload: isReload.value });
    }

    // 變更表單操作狀態
    function changeFormStatus(status: formStatusEnum) {
      formStatusRef.value = status;
    }

    const propsId = toRefs(props).id;

    watch(
      propsId,
      () => {
        initFunctionUsedRecordInfo();
        changeFormStatus(formStatusEnum.INFO);
      },
      { immediate: true }
    );

    return {
      formStatusEnum,
      formStatusRef,
      userUnit,
      toQueryView,
      validateState,
      changeFormStatus,
      form,
      queryOptions,
    };
  },
  filters: {
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
    loginResultFilter: (value: any) => executeFormatter(value),
    sysCategoryFilter: (value: string) => sysCategoryFormatter(value),
  },
};
</script>
