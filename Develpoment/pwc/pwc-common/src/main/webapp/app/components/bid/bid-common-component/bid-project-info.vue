<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">標案資料</h5>
      </div>
      <div class="card-body col-11">
        <b-row>
          <!-- 標案名稱 -->
          <i-form-group-check :label="$t('label.projectName') + '：'" :label-cols="2" :content-cols="10" class="col-12">
            {{ projectInfo.name }}
          </i-form-group-check>
        </b-row>
        <b-row>
          <!-- 編號 類別 -->
          <i-form-group-check :label="$t('label.prjno') + '：'">
            {{ projectInfo.prjno }}
          </i-form-group-check>
          <i-form-group-check :label="$t('label.typeCode') + '：'">
            {{ typeCodeValue }}
          </i-form-group-check>
        </b-row>
        <!-- 執行單位 -->
        <b-row>
          <i-form-group-check :label="$t('label.wkut') + '：'" :label-cols="2" :content-cols="10" class="col-12">
            {{ projectInfo.wkutName }}
          </i-form-group-check>
        </b-row>

        <!-- 主辦機關 主管機關 -->
        <b-row>
          <i-form-group-check :label="$t('label.dept') + '：'">
            {{ projectInfo.deptName }}
          </i-form-group-check>
          <i-form-group-check :label="$t('label.bos') + '：'">
            {{ projectInfo.bosName }}
          </i-form-group-check>
        </b-row>
        <!-- 發包預算 決標金額 -->
        <b-row>
          <i-form-group-check :label="$t('label.bdgt1') + '：'"> {{ projectInfo.bdgt1 | commaFilter }}千元 </i-form-group-check>
          <i-form-group-check :label="$t('label.ctSum') + '：'"> {{ projectInfo.ctSum | commaFilter }}千元 </i-form-group-check>
        </b-row>
        <!-- 變更設計後金額 預付款 -->
        <b-row>
          <i-form-group-check :label="'變更設計後金額：'"> {{ projectInfo.bdgt1 | commaFilter }}千元 </i-form-group-check>
          <i-form-group-check :label="'預付款：'"> {{ projectInfo.pay | commaFilter }}千元 </i-form-group-check>
        </b-row>

        <!-- 實際決標日期 實際開工日期 -->
        <b-row>
          <i-form-group-check :label="$t('label.actDate') + '：'">
            {{ formatDate(projectInfo.actDate ? new Date(projectInfo.actDate) : null, '/') }}
          </i-form-group-check>
          <i-form-group-check :label="$t('label.asDate') + '：'">
            {{ formatDate(projectInfo.asDate ? new Date(projectInfo.asDate) : null, '/') }}
          </i-form-group-check>
        </b-row>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, toRef, ref, onMounted, watch } from '@vue/composition-api';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { commaFormatter } from '@/shared/formatter/common';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
export default {
  name: 'bidProjectInfo',
  props: {
    projectInfoProp: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const projectInfo: Ref<any> = toRef(props, 'projectInfoProp');
    const bidCommonService = new BidCommonService();
    const bidSysCodeTable = new BidSysCodeTable();
    const typeCodeValue = ref('');
    const actmthValue = ref('');

    onMounted(() => {
      bidSysCodeTable.getBidSysCodeWithParam('BID_003').then(typeCodeDataArr => {
        let returnObj = typeCodeDataArr.find(element => {
          return element.dataKey === projectInfo.value.typeCode;
        });
        if (returnObj) {
          typeCodeValue.value = returnObj.value;
        } else {
          typeCodeValue.value = projectInfo.value.typeCode;
        }
      });
    });

    return { projectInfo, typeCodeValue, formatDate };
  },
  filters: {
    commaFilter: (value: any) => commaFormatter(value, 3),
  },
};
</script>
