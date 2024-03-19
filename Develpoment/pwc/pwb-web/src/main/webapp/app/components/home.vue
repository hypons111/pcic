<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="welcome text-secondary">公共工程雲後台管理系統首頁</div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, reactive, toRefs, inject, computed } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import AccountService from '@/account/account.service';
import { dateTimeFormatter, downloadFile } from '@/shared/formatter/common';

export default {
  name: 'Home',
  setup() {
    const accountService = inject<() => AccountService>('accountService')();
    const { user } = accountService;
    const organId = computed(() => {
      if (user && user.unit) {
        return user.unit.id;
      }
      return '';
    });
    const notificationService: NotificationService = useNotification();

    return {};
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

.welcome {
  font-size: 50px;
  font-weight: bold;
  margin: 50px;
  text-align: center;
}
</style>
