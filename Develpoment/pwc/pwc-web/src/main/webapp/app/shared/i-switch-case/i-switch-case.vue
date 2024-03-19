<template>
  <div class="text-center">
    <!-- 上一筆 -->
    <i-button type="arrow-bar-left" @click="switchCase(false)" />
    <!-- 下一筆 -->
    <i-button type="arrow-bar-right" @click="switchCase(true)" />
  </div>
</template>
<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { defineComponent, ref, toRefs, unref } from '@vue/composition-api';

export default defineComponent({
  name: 'ISwitchCase',
  props: {
    criteria: {
      required: true,
      type: Object,
    },
    apiUrl: {
      required: true,
      type: String,
    },
  },
  setup(props, context) {
    const { criteria, apiUrl } = toRefs(props);
    const $bvModal = useBvModal();
    const notificationService = useNotification();
    const data = ref(null);

    async function switchCase(isNext: boolean) {
      const { currentPage, perPage, index, totalItems } = unref(criteria);

      if (isNext) {
        if (currentPage * perPage + index === totalItems - 1) {
          $bvModal.msgBoxOk('無下一筆');
          return;
        } else if (index === perPage - 1) {
          unref(criteria).index = 0;
          unref(criteria).currentPage += 1;
          data.value = (await getData()).slice(0, perPage);
        } else {
          unref(criteria).index += 1;
        }
      } else {
        if (currentPage * perPage + index === 0) {
          $bvModal.msgBoxOk('無上一筆');
          return;
        } else if (index === 0) {
          unref(criteria).index = perPage - 1;
          unref(criteria).currentPage -= 1;
          data.value = (await getData()).slice(0, perPage);
        } else {
          unref(criteria).index -= 1;
        }
      }

      if (!unref(data)) {
        data.value = (await getData()).slice(0, perPage);
      }

      console.log('第' + (unref(criteria).currentPage * perPage + unref(criteria).index + 1) + '筆');
      context.emit('changeCase', unref(data)[unref(criteria).index]);
      window.scroll({ top: 0, left: 0, behavior: 'smooth' });
    }

    function getData(): Promise<any> {
      return new Promise<any>(resolve => {
        axios
          .post(unref(apiUrl), unref(criteria))
          .then((res: AxiosResponse) => {
            resolve(res.data.content);
          })
          .catch(notificationErrorHandler(notificationService));
      });
    }

    return {
      switchCase,
    };
  },
});
</script>
