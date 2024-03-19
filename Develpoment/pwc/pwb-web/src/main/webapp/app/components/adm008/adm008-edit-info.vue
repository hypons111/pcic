<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視機關資料
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check :label="$t('label.adm008.orgId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.orgId }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm008.pccOrgId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.pccOrgId }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm008.orgName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.orgName }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm008.orgNameEn') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.orgNameEn }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm008.addr') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.addr }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm008.descript') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.descript }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm008.tel') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.tel }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm008.fax') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.fax }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm008.adminOrgId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.adminOrgId }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm008.orgLevel') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.orgLevel }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm008.oldOrgId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.oldOrgId }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm008.oldOrgName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.oldOrgName }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm008.cutMark') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.cutMark | yesNoFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm007.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm007.updateTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.updateTime | dateTimeFilter }}
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
import { dateTimeFormatter, yesNoFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { AdmAgent } from '@/shared/model/adm-agent.model';

export default {
  name: 'Adm008EditInfo',
  props: {
    orgId: {
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
    let formDefault: AdmAgent = new AdmAgent().setDefault();
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

    function initUserAccountInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-agents/${props.orgId}`)
        .then(({ data }) => {
          if (data && data['orgId']) {
            Object.assign(formDefault, data);
            Object.assign(form, formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 返回列表
    function toQueryView() {
      navigateByNameAndParams('adm008', { isReload: isReload.value });
    }

    // 變更表單操作狀態
    function changeFormStatus(status: formStatusEnum) {
      formStatusRef.value = status;
    }

    const propsOrgId = toRefs(props).orgId;

    watch(
      propsOrgId,
      () => {
        initUserAccountInfo();
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
    };
  },
  filters: {
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
    yesNoFilter: (value: any) => yesNoFormatter(value),
  },
};
</script>
