<template>
  <component :is="loginView"></component>
</template>

<script lang="ts">
import { useGetters } from '@u3u/vue-hooks';
import TestLoginForm from './test-login-form.vue';
import DevLoginForm from './dev-login-form.vue';
import { inject, ref } from '@vue/composition-api';
import AccountService from '@/account/account.service';

export default {
  name: 'loginForm',
  components: {
    TestLoginForm,
    DevLoginForm,
  },
  setup() {
    const loginView = ref('');
    const accountService = inject<() => AccountService>('accountService')();
    accountService.retrieveProfiles().then(() => {
      const activeProfiles = useGetters(['activeProfiles']).activeProfiles.value;
      const profile = activeProfiles.find(v => v === 'test');
      loginView.value = profile !== undefined ? 'testLoginForm' : 'devLoginForm';
    });
    return { loginView };
  },
};
</script>
