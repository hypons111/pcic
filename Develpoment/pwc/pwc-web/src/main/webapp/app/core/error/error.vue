<template>
  <div>
    <div class="row">
      <div class="col-md-2">
        <span class="hipster img-fluid rounded"></span>
      </div>
      <div class="col-md-8">
        <h1>錯誤!</h1>

        <div v-if="errorMessage">
          <div class="alert alert-danger">{{ errorMessage }}</div>
        </div>
        <div v-if="error403" class="alert alert-danger">您沒有存取這個頁面的權限。</div>
        <div v-if="error404" class="alert alert-warning">頁面不存在。</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./error.component.ts"></script>
