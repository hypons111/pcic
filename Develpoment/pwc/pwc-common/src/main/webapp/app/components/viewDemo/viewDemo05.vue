<template>
  <div>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-inform" role="tabpanel" aria-labelledby="nav-inform-tab">
        <section class="container pt-2">
          <div class="card-header">
            <b-form-row class="col-12 border border-dark" >
              <i-form-group-check
                class="col-12"
                label-cols="2"
                content-cols="10"
                :label="$t('button.upload') + '：'"
              >
                <b-col class="col-10  pt-4">
                  <b-form-file
                    v-model="$v.inputFile.$model"
                    multiple
                    plain
                  >
                  </b-form-file> 
                </b-col>
                <b-col class="col-2 pt-4">                    
                  <b-button variant="outline-secondary"  @click="putSaveForm">上傳附件</b-button>   
                </b-col>  
              </i-form-group-check>             
            </b-form-row>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useValidation } from '@/shared/form';
import { computed, onActivated, reactive, ref, onMounted } from '@vue/composition-api';
import { guildTypeFormatter } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { CmsComp } from '@/shared/model/cms-comp.model';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { parseRocDate, formatDateString, formatDate } from '@/shared/date/minguo-calendar-utils';
import { required } from '@/shared/validators';
import { navigateByNameAndParams } from '@/router/router';
import DualFormInvalidFeedback from '@/shared/form/dual-form-invalid-feedback.vue';
import { handleBack } from '@/router/router';
import { accountStore } from '@/shared/config/store/account-store';
import { kindFormatter } from '@/shared/formatter/common';

export default {
  name: 'viewDemo05',
  components: {
    DualFormInvalidFeedback,
  },
  props: {
    isReload: {
      required: false,
    },
  },
  setup(props, context) {
    let formDefault = {
     inputFile: [],
   }

   const form = reactive(Object.assign({}, formDefault));

   const rules = {
     inputFile: {},
   }

   const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

   const notificationService: NotificationService = useNotification();

  //  function uploadAppendixFiles() {
  //     const param = new FormData();
  //     param.append('form', new Blob([JSON.stringify(form)], { type: 'application/json' }));
  //     form.inputFile.forEach(element => {
  //       param.append('files', element);
  //     });
  //     axios
  //       // 就算不加 { 'Content-Type': 'multipart/form-data' }, 以FormData傳遞時, 瀏覽器也會自動加上
  //       .put('/adm-files/proUpload', param, { headers: { 'Content-Type': 'multipart/form-data' } })
  //       // .put('/adm-files/uploadFiles', param)
  //       .then(() => {
  //         notificationService.success(`上傳成功`);
  //       })
  //       .catch(notificationErrorHandler(notificationService));
  //   }

    const headers = {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    };

    const putSaveForm = () => {
      //加入判斷 判斷是info或down
      let url = '/service/excel-upload';
      const formData = new FormData();
      // const tempForm = Object.assign({}, form);
      // delete tempForm.inputFile;
      Array.from(form.inputFile).forEach(file => {
        formData.append('uploadFile', file);
      });

      axios
        .put(url, formData, headers)
        .then(() => {
          // notificationService.success(`上傳成功`);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    };


    return {
      $v,
      putSaveForm,
    };
  },
};
</script>

<style scoped>
.widthClassForTitle {
  width: 14%;
  word-break: break-all;
}
.widthClassForDescription {
  width: 53%;
  word-break: break-all;
}
.widthClassForCreateUserAndUpdateDate {
  width: 10%;
  word-break: break-all;
}
.widthClassForOperation {
  width: 13%;
  word-break: break-all;
}

.nav-tabs .nav-link {
  width: 50%;
  font-size: 19px;
  line-height: 28px;
  opacity: 0.45;
  color: #000;
  background: #fff;
  border: 0;
  padding: 0.6rem;
  letter-spacing: 0.125rem;
}

.filter {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 1rem 1.5rem;
  background: rgba(0, 0, 0, 0.05);
  border-radius: 0.25rem;
  margin-bottom: 1.25rem;
}
.filter .input-group {
  margin-right: 1rem;
  display: flex;
  align-items: center;
}
.input-group label {
  font-size: 1.15rem;
  color: #333;
  letter-spacing: 0.15rem;
  white-space: nowrap;
  margin-right: 0.5rem;
}
.input-theme {
  font-size: 1rem;
  line-height: 1.5rem;
  height: 40px;
  min-width: 120px;
  border: 1px solid #bdbdbd;
  border-radius: 0.25rem;
  padding: 4px;
  margin: 4px;
  flex: auto;
}
.href-style {
  font-weight: normal;
}
.table-style-td {
  max-width: 700px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.table-style-td:hover {
  overflow: auto;
  white-space: pre-wrap;
}
.card {
  background: rgba(0, 0, 0, 0.03);
}
</style>
