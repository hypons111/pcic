<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-body">
          <b-form-row class="align-items-center">
            <b-col cols="1"></b-col>
            <b-col cols="4" class="text-left label"
              ><label>{{ $t('label.intro') + '/' + $t('label.tec_intro') }}</label></b-col
            >
          </b-form-row>

          <b-form-row class="align-items-center">
            <b-col cols="1"></b-col>
            <b-col cols="4" class="text-left label"
              ><label>{{ preparedObj.pageTitle }}</label></b-col
            >
          </b-form-row>

          <b-form-row class="align-items-center">
            <b-col cols="1"></b-col>
            <b-col cols="10" class="text-left label"
              ><label>{{ preparedObj.content }}</label></b-col
            >
          </b-form-row>
        </div>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { onBeforeMount, reactive, ref } from '@vue/composition-api';
import axios from 'axios';
import { Tec_page } from '@/shared/model/tec_page.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';

export default {
  name: 'tec0201RQuery',
  setup() {
    onBeforeMount(() => {
      //掛載前執行
      handleQuery();
    });

    //準備好的form
    const form = reactive(
      Object.assign(
        {},
        {
          pageTitle: '',
          content: '',
        }
      )
    );

    const dataPromise = ref(null);

    const notificationService: NotificationService = useNotification();

    //先準備好一個物件，將取出的值放入
    const preparedObj: Tec_page = reactive(Object.assign({}, form));

    const handleQuery = () => {
      dataPromise.value = axios
        .post('/service/tec-pages/tec-intro', form)
        .then(({ data }) => {
          //將值放進預備好的物件
          Object.assign(preparedObj, data);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    return {
      preparedObj,
      handleQuery,
    };
  },
};
</script>

<style scoped>
.align-items-center {
  padding-top: 5px;
  padding-bottom: 5px;
}
</style>
