<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            詳細資訊
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 負責人姓名 -->
            <i-form-group-check :label="$t('label.masterName') + '：'"
            >
            {{ data.masterName }}
            </i-form-group-check>
            <!-- 統一編號 -->
            <i-form-group-check :label="$t('label.compIdno') + '：'" 
            >
            {{ data.compIdno }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 公司名稱 -->
            <i-form-group-check :label="$t('label.comName') + '：'"
            >
            {{ data.compName }}
            </i-form-group-check>
            <!-- 狀態 -->
            <i-form-group-check :label="$t('label.status') + '：'" 
            >
            {{ data.status }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 公司所在地 -->
            <i-form-group-check :label="$t('label.compAddr') + '：'"
            >
            {{ data.compAddrOther }}
            </i-form-group-check>
            <!-- 聯絡電話 -->
            <i-form-group-check :label="$t('label.contactInformationTel') + '：'" 
            >
            {{ data.compTel1 }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 登記證號碼 -->
            <i-form-group-check :label="$t('label.cstLicenseDocNo') + '：'"
            >
            {{ data.licenseNo }}
            </i-form-group-check>
            <!-- 最後一次發證(照)日期 -->
            <i-form-group-check :label="$t('label.lastPublicDate') + '：'" 
            >
            {{ data.publicDate}}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 營業範圍 -->
            <i-form-group-check :label="$t('label.saleAreaList') + '：'"
            >
            {{ data.saleAreaList }}
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
    </section>
    <br/>
    <section>
      <!-- 得標機構 -->
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            公司技師名冊
          </h5>
        </div>
        <div class="card-body">
          <b-table sticky-header
          :items="table.data" 
          :fields="table.fields" 
          bordered 
          responsive="sm">
          
          </b-table>
        </div>
      </div>
    </section>
    <br/>
    <b-form-row>
      <b-col offset="5">
        <!-- 返回 -->
        <i-button type="arrow-left" @click="toLast"/>
      </b-col>
    </b-form-row>
    <br/>
    <b-form-row>
      <b-col offset="5">  
        <!-- 上一筆 -->
        <i-button type="arrow-bar-left" />
        <!-- 下一筆 -->
        <i-button type="arrow-bar-right"  />
      </b-col>
    </b-form-row>
  </div>
</template>

<script lang="ts">
import { watch, toRef, toRefs, ref, unref, defineComponent, inject, onMounted, reactive} from '@vue/composition-api';
import { Eng0703, IEng0703 } from "@/shared/model/eng/eng0703.model"
import { EngManage } from "@/shared/model/eng/eng-manage.model"
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import { string } from 'prop-types';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { formOptionsFormatter } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import i18n from '@/shared/i18n';

export default {
  name: 'Eng0818Info',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(prop) {
    const admSysCodeService = new AdmSysCodeService();
    const isReload = ref(false);
    let formDefault = new EngManage();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      masterName: {},
      compIdno: {},
      compName: {},
      applyItem: {},
      compAddrZip: {},
      compAddrCity: {},
      compAddrTown: {},
      compAddrOther: {},
      compTel1: {},
      cstLicenseDocNo: {},
      publicDate: {},
      saleAreaList: {},
    }

    const { $v, checkValidity, reset } = 
    useValidation(rules, form, formDefault);

    onMounted(() => {
      table.data = data2;
    })

    function toLast() {
      navigateByNameAndParams('Eng0818Query', {
        isReload: isReload.value, isNotKeepAlive: true,
      })
    }

    const table = reactive({
      fields: [
        {  // 項次
          key: 'engYearreportEngrId',
          label: i18n.t('label.engYearreportEngrId'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 姓名
          key: 'chName',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 執照字號
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 執業科別
          key: 'subject',
          label: i18n.t('label.execSubject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 據七年經驗
          key: 'isMore7Year',
          label: i18n.t('label.haveSevenYear'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 懲戒情形
          key: 'disciplineStatus',
          label: i18n.t('label.disciplineStatus'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
    })

    const data = {
      masterName: '蔣封文',
      compIdno: '86627729',
      compName: '康地科技顧問股份有限公司',
      status: '換發',
      compAddrZip: '',
      compAddrCity: '',
      compAddrTown: '',
      compAddrOther: '臺南市東區崇明五街29號1樓',
      compTel1: '06-2388022',
      licenseNo: '工程技顧登字第000014號',
      publicDate: '110/05/03',
      saleAreaList: '交通工程',
    }

    const data2 = [
      {
        engYearreportEngrId: 1,
        chName: '蔣封文',
        engrLicenseDocNo: '技證字第007551號',
        subject: '交通工程科 技證字第009376號',
        isMore7Year: '否',
        disciplineStatus: '無',
      },
      {
        engYearreportEngrId: 2,
        chName: '石家豪',
        engrLicenseDocNo: '技證字第007151號',
        subject: '交通工程科 技證字第007613號',
        isMore7Year: '是',
        disciplineStatus: '無',
      }
    ]
    
    return {
      data,
      data2,
      $v,
      table,
      toLast
    }
  }
}
</script>