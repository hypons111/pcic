<template>
  <div class="row justify-content-center mt-3">
    <div class="col-md-8">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h4 class="p-0 m-0">
            <font-awesome-icon icon="user"></font-awesome-icon>
            雲端系統線上註冊
          </h4>
        </div>
        <div class="card-body">
          <div class="d-flex justify-content-center">
            <b-button size="lg" variant="primary" style="margin-right:10px" @click="apply('1')">機關管理者帳號</b-button>
            <b-button size="lg" variant="primary" style="margin-right:10px" @click="apply('2')">公司法人管理者帳號</b-button>
            <b-button size="lg" variant="primary" @click="apply('3')">個人帳號</b-button>
          </div>
        <div class="d-flex justify-content-center mt-5">
          <b-button-toolbar>
            <i-button type="arrow-bar-left" @click="toQueryView"></i-button>
          </b-button-toolbar>
        </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script lang="ts">
import { reactive, ref } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams, handleBack } from '@/router/router';

export default {
  name: 'pwc0101',
  setup() {

    function apply(num){
      if(num === "1"){
        navigateByNameAndParams('pwc0301', {isnotkeepAlive: true });
      } else if (num === "2"){
        navigateByNameAndParams('pwc0302', {isnotkeepAlive: true });
      } else if (num === "3"){
        navigateByNameAndParams('pwc0303Login', {isnotkeepAlive: true });
      }
    }

    // 是否需要重新查詢
    const isReload = ref(false);

    //回上頁
    const toQueryView = () => {
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    return{
      apply,
      toQueryView
    }
  }
}
</script>