<template>
  <div class="row justify-content-center">
    <div class="col-md-8">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h4 class="p-0 m-0">
            <font-awesome-icon icon="user"></font-awesome-icon>
            測試登入
          </h4>
        </div>
        <div class="card-body">
          <b-form role="form" v-on:submit.prevent="doLogin()">
            <b-form-group label="使用者帳號" label-for="id">
              <b-form-input
                id="id"
                type="text"
                name="id"
                autofocus
                v-model="$v.id.$model"
                data-cy="id"
                :state="validateState($v.id)"
              ></b-form-input>
              <b-form-invalid-feedback v-for="(error, index) of $v.id.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>

            <b-form-group label="使用者密碼" label-for="password">
              <b-form-input
                id="password"
                type="password"
                name="password"
                v-model="$v.password.$model"
                data-cy="password"
                :state="validateState($v.password)"
              ></b-form-input>
              <b-form-invalid-feedback v-for="(error, index) of $v.password.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>
            <div>
              <b-button data-cy="submit" type="submit" variant="primary">登入</b-button>
            </div>
          </b-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { inject, reactive } from '@vue/composition-api';
import AccountService from '@/account/account.service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';

export default {
  name: 'testLoginForm',
  setup() {
    const notificationService = useNotification();

    const formDefaults = {
      id: '',
      password: '',
    };

    // 建立表單物件 reactive
    // 記得建立一份新的物件，不然預設值會跟著被改動
    const form = reactive(Object.assign({}, formDefaults));

    const rules = {
      id: { required },
      password: { required },
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefaults);

    const accountService = inject<() => AccountService>('accountService')();

    async function doLogin() {
      const isFormCorrect = await checkValidity();

      if (!isFormCorrect) {
        return;
      }

      axios
        .post('authenticate', { username: form.id, password: form.password })
        .then(result => {
          const bearerToken = result.headers.authorization;
          if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
            const jwt = bearerToken.slice(7, bearerToken.length);
            sessionStorage.setItem('jhi-authenticationToken', jwt);
            localStorage.removeItem('jhi-authenticationToken');
          }
          accountService.retrieveAccount();
        })
        .catch(() => notificationService.danger('登入時發生錯誤，請檢查帳號密碼是否正確。'));
    }

    return { form, doLogin, $v, reset, validateState };
  },
};
</script>
