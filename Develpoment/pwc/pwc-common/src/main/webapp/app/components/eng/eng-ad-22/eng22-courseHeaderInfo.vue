<template>
  <div>
    <b-form-row class="align-items-center">
      <b-form-group
        class="col-sm-6"
        label-class="label"
        label-cols-md="4"
        content-cols-md="8"
        :label="$t('label.company')+$t('label.name')+':'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="courseHeadInfo.belongOf"></b-form-input>
      </b-form-group>
      <b-form-group
        class="col-sm-6"
        label-cols-md="4"
        label-class="label"
        content-cols-md="8"
        :label="$t('label.belongUnit')+'：'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="courseHeadInfo.belongOf"></b-form-input>
      </b-form-group>
    </b-form-row>

    <b-form-row class="align-items-center">
      <b-form-group
        class="col-sm-6"
        label-cols-md="4"
        label-class="label"
        content-cols-md="8"
        :label="$t('label.course')+$t('label.name')+'：'"
        label-align-md="right"
      >
        <b-form-textarea no-resize plaintext disabled :value="courseHeadInfo.name"></b-form-textarea>
      </b-form-group>
    </b-form-row>

    <b-form-row class="align-items-center">
      <b-form-group
        class="col-sm-6"
        label-cols-md="4"
        label-class="label"
        content-cols-md="8"
        :label="$t('label.createDate')+'：'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="formatDate(courseHeadInfo.createDate, '/')"></b-form-input>
      </b-form-group>

      <b-form-group
        class="col-sm-6"
        label-cols-md="4"
        label-class="label"
        content-cols-md="8"
        :label="$t('label.course')+$t('label.date')+'：'"
        label-align-md="right"
      >
        <b-form-input
          plaintext
          disabled
          :value="formatDate(courseHeadInfo.date_Start, '/') + '至' + formatDate(courseHeadInfo.date_End, '/')"
        >
        </b-form-input>
      </b-form-group>
    </b-form-row>

    <b-form-row class="align-items-center">
      <b-form-group
        class="col-sm-6"
        label-cols-md="4"
        label-class="label"
        content-cols-md="8"
        :label="$t('label.course')+$t('label.place')+'：'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="courseHeadInfo.place"></b-form-input>
      </b-form-group>
      <b-form-group
        class="col-sm-6"
        label-cols-md="4"
        label-class="label"
        content-cols-md="8"
        :label="$t('label.uploadDeadline')+$t('label.date')+'：'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="formatDate(courseHeadInfo.UploadDeadline, '/')"></b-form-input>
      </b-form-group>
    </b-form-row>

    <b-form-row class="align-items-center">
      <b-form-group
        class="col-sm-6"
        label-cols-md="4"
        label-class="label"
        content-cols-md="8"
        :label="$t('label.contactName')+'：'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="courseHeadInfo.contact_Name"></b-form-input>
      </b-form-group>

      <b-form-group
        class="col-sm-6"
        label-cols-md="4"
        label-class="label"
        content-cols-md="8"
        :label="$t('label.contactTel')+'：'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="courseHeadInfo.contact_Tel"></b-form-input>
      </b-form-group>
    </b-form-row>

    <b-form-row class="align-items-center pb-0 mb-0">
      <b-form-group
        class="col-sm-12"
        label-class="label"
        label-cols-md="2"
        content-cols-md="6"
        :label="$t('label.email')+'：'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="courseHeadInfo.contact_Mail"></b-form-input>
      </b-form-group>
    </b-form-row>

    <b-form-row class="align-items-center">
      <b-form-group
        class="col-sm-12"
        label-cols-md="2"
        label-class="label"
        content-cols-md="6"
        :label="$t('label.webUrl')+'：'"
        label-align-md="right"
      >
        <b-form-input plaintext disabled :value="courseHeadInfo.webUrl"></b-form-input>
        <!-- <b-form-textarea no-resize plaintext disabled :value="courseHeadInfo.webUrl"></b-form-textarea> -->
      </b-form-group>
    </b-form-row>

    <b-form-row class="align-items-center">
      <b-form-group
        class="col-sm-12"
        label-cols-md="2"
        label-class="label"
        content-cols-md="6"
        :label="$t('label.remark')+'：'"
        label-align-md="right"
      >
        <!-- <b-form-input plaintext disabled :value="courseHeadInfo.remark"></b-form-input> -->
        <b-form-textarea no-resize plaintext disabled :value="courseHeadInfo.remark"></b-form-textarea>
      </b-form-group>
    </b-form-row>
  </div>
</template>
<script lang="ts">
import { computed } from '@vue/composition-api';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
export default {
  name: 'eng22-courseHeaderInfo',
  props: {
    courseHead: {
      required: true,
    },
  },
  setup(props, context) {
    const courseHeadInfo = computed(() => props.courseHead);
    // courseHeadInfo.value.remark = " Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptates quasi deserunt expedita commodi minima aperiam quaerat. Neque suscipit et quo perferendis quis exercitationem similique corporis veritatis! Ipsum, architecto laboriosam? Alias nobis nulla officiis. Vero dicta odio quis illo aut? Vel laborum eum facilis expedita quidem, nulla dignissimos eligendi nihil nemo!";
    return {
      courseHeadInfo,
      formatDate,
    };
  },
};
</script>

<style scoped>
.form-group {
  height: auto;
}
</style>
