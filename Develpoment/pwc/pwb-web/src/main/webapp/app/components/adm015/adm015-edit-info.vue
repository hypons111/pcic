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
              <i-form-group-check :label="$t('label.adm015.id') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.id }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm015.userId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.userId }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm015.chName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.chName }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm015.sysCategory') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.sysCategory | sysCategoryFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm015.jwtToken') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.jwtToken }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm015.result') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.result | loginResultFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm015.sourceIp') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.sourceIp }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm015.descript') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.descript }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm015.browser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.browser }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm015.createUserSys') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.createUserSys }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm015.createTime') + '：'">
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
import { accountStore } from '@/shared/config/store/account-store';
import { dateTimeFormatter, executeFormatter, sysCategoryFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'Adm015EditInfo',
  props: {
    id: {
      type: String,
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
      userId: '',
      chName: '',
      chNameRome: '',
      sysCategory: '',
      jwtToken: '',
      result: '',
      sourceIp: '',
      descript: '',
      browser: '',
      createUserSys: '',
      createTime: '',
    };
    const isReload = ref(false);
    // 建立一份新的表單物件
    const form = reactive(Object.assign({}, formDefault));

    const queryOptions = reactive({
      sysCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: '1', text: '工程會後台' },
        { value: '2', text: '技師資料庫' },
        { value: '3', text: '價格資料庫' },
      ],
      resultOpts: [
        { value: '', text: '請選擇' },
        { value: 'Y', text: '成功' },
        { value: 'N', text: '失敗' },
      ],
    });
    // 使用者資訊
    const userUnit = computed(() => {
      let account = context.root.$store.getters.account;
      if (account) {
        return account.unit;
      } else {
        return '';
      }
    });

    // @ts-ignore
    const userIdentity = accountStore.state.userIdentity;

    const notificationService: NotificationService = useNotification();

    function initLoginHisInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-login-his/${props.id}`)
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
      navigateByNameAndParams('adm015', { isReload: isReload.value });
    }

    // 變更表單操作狀態
    function changeFormStatus(status: formStatusEnum) {
      formStatusRef.value = status;
    }

    const propsOrgId = toRefs(props).orgId;

    watch(
      propsOrgId,
      () => {
        initLoginHisInfo();
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
