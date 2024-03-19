<template>
  <div>
    <b-table class="mb-0" small bordered :items="table.disciplineDtls" :fields="table.fields">
      <!-- 項次 -->
      <template #cell(index)="{ index }">
        <div>{{ index + 1 }}</div>
      </template>

      <!-- 違反法條 -->
      <template #cell(breakLawList)="{ item }">
        <b-form-checkbox-group v-if="item.isEdit" v-model="item.breakLawArr">
          <div v-for="option in formOptions.breakLawList" :key="option.value" class="col-6 d-inline-block">
            <b-form-checkbox :value="option.value">
              {{ option.text }}
            </b-form-checkbox>
          </div>
        </b-form-checkbox-group>
        <div v-else>{{ formOptionsFormatter(formOptions.breakLawList, item.breakLawList) }}</div>
      </template>

      <!-- 懲戒類型 / 次數或月數 -->
      <template #head(disciplineTypeAndTimes)>
        {{ $t('label.disciplineType') }}
        <br />
        次數或月數
      </template>
      <template #cell(disciplineTypeAndTimes)="{ item }">
        <div v-if="item.isEdit">
          <b-form-select v-model="item.disciplineType" :options="formOptions.disciplineType">
            <template #first>
              <option :value="undefined">-- 請選擇 --</option>
            </template>
          </b-form-select>
          <b-row class="mt-4">
            <b-col>
              <b-form-select v-model="item.countType" :options="formOptions.countType">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </b-col>
            <b-col>
              <b-form-select
                v-model="item.count"
                :options="item.countType === '1' ? formOptions.count1 : item.countType === '2' ? formOptions.count2 : []"
              >
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </b-col>
            <!-- <b-form-select class="col-5" v-model="item.countType" :options="formOptions.countType">
              <template #first>
                <option :value="undefined">-- 請選擇 --</option>
              </template>
            </b-form-select>
            <b-form-select
              class="offset-1 col-5"
              v-model="item.count"
              :options="item.countType === '1' ? formOptions.count1 : item.countType === '2' ? formOptions.count2 : []"
            >
              <template #first>
                <option :value="undefined">-- 請選擇 --</option>
              </template>
            </b-form-select> -->
          </b-row>
        </div>
        <div v-else>
          {{ formOptionsFormatter(formOptions.disciplineType, item.disciplineType) }}
          <br />
          {{ formOptionsFormatter(formOptions.countType, item.countType) }}{{ item.count ? '：' + item.count : '' }}
        </div>
      </template>

      <!-- 執行日期 -->
      <template #cell(execDate)="{ item }">
        <i-date-picker v-if="item.isEdit" v-model="item.execDateStart"></i-date-picker>
        <div v-else>{{ item.execDateStart | dateToString }}</div>
        <div>至</div>
        <i-date-picker v-if="item.isEdit" v-model="item.execDateEnd"></i-date-picker>
        <div v-else>{{ item.execDateEnd | dateToString }}</div>
      </template>

      <!-- 動作 -->
      <template #cell(action)="{ item }">
        <!-- 編輯 -->
        <div v-if="formStatus !== FormStatusEnum.READONLY && !item.isEdit" :class="{ invisible: isEditingDetail }">
          <i-button size="sm" class="mb-1" type="pencil-square" @click="editDetail(item)"></i-button>
        </div>
        <!-- 刪除 -->
        <div v-if="formStatus !== FormStatusEnum.READONLY && !item.isEdit" :class="{ invisible: isEditingDetail }">
          <i-button size="sm" class="mb-1" type="trash" @click="deleteDetail(item)"></i-button>
        </div>
        <!-- 暫存 -->
        <div v-if="item.isEdit">
          <i-button size="sm" class="mb-1" type="tag" @click="saveDetail(item)"></i-button>
        </div>
        <!-- 取消 -->
        <div v-if="item.isEdit">
          <i-button size="sm" type="arrow-counterclockwise" @click="cancelAction(item)"></i-button>
        </div>
      </template>

      <!-- table底下(新增按鈕) -->
      <template #table-caption>
        <div v-if="formStatus === FormStatusEnum.EDIT && !isEditingDetail">
          <!-- 新增 -->
          <i-button
            v-if="!table.disciplineDtls || table.disciplineDtls.length === 0"
            type="file-earmark-plus"
            @click="addDetail"
          ></i-button>
          <!-- 繼續新增 -->
          <i-button v-else type="plus-circle" @click="addDetail"></i-button>
        </div>
      </template>
    </b-table>
  </div>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useBvModal } from '@/shared/modal';
import { ref, reactive, watch, onMounted, computed, defineComponent, toRefs, inject } from '@vue/composition-api';
import { FormStatusEnum } from '@/shared/enum';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import _ from 'lodash';

interface propsType {
  disciplineDtls: any[];
  formStatus: FormStatusEnum;
}

export default defineComponent<propsType>({
  name: 'Eng0105DisciplineDetail',
  props: {
    disciplineDtls: {
      required: false,
    },
    formStatus: {
      required: false,
    },
  },
  setup(props, context) {
    let { formStatus } = toRefs(props);
    const $bvModal = useBvModal(); //modal物件
    const isEditingDetail = ref(false); //是否添加新detail的判斷
    const admSysCodeService = new AdmSysCodeService();
    let detailTemp = undefined; // 紀錄修改前的detail資料

    const formOptions = reactive({
      breakLawList: [],
      disciplineType: [],
      countType: [],
      count1: [1, 2, 3, 4],
      count2: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24],
    });

    //為物件添加屬性,方便前端做數據操作
    onMounted(() => {
      table.disciplineDtls = props.disciplineDtls.slice().map(item => ({
        ...item,
        execDateStart: new Date(item.execDateStart),
        execDateEnd: new Date(item.execDateEnd),
        breakLawArr: item.breakLawList.split(','),
        isEdit: false,
      }));
      table.maxDetailId = Math.max(...table.disciplineDtls.map(i => i.engEngrDisciplineDetailId).filter(i => i));
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const table: {
      fields: any;
      disciplineDtls: any[];
      maxDetailId: number;
    } = reactive({
      fields: [
        {
          // 項次
          key: 'index',
          label: i18n.t('label.index'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 違反法條
          key: 'breakLawList',
          label: i18n.t('label.breakLaw'),
          sortable: false,
          thStyle: 'width:45%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 懲戒類型 / 次數或月數
          key: 'disciplineTypeAndTimes',
          sortable: false,
          thStyle: 'width:25%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 執行日期
          key: 'execDate',
          label: i18n.t('label.execDate2'),
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
      ],
      disciplineDtls: undefined,
      maxDetailId: undefined,
    });

    // 檢視時不顯示動作欄位
    if (formStatus.value !== FormStatusEnum.READONLY) {
      table.fields.push({
        // 動作
        key: 'action',
        label: i18n.t('label.action'),
        sortable: false,
        thStyle: 'width:10%',
        thClass: 'text-center align-middle',
        tdClass: 'text-center align-middle',
      });
    }

    function addDetail() {
      const disciplineDtl = {
        engEngrDisciplineId: undefined,
        engEngrDisciplineDetailId: undefined,
        breakLawList: undefined,
        breakLawArr: undefined,
        disciplineType: undefined,
        countType: undefined,
        count: undefined,
        execDateStart: undefined,
        execDateEnd: undefined,
        isEdit: false,
      };
      disciplineDtl.isEdit = true;
      table.disciplineDtls.push(disciplineDtl);
      isEditingDetail.value = true;
    }

    function editDetail(item) {
      item.isEdit = true;
      isEditingDetail.value = true;
      detailTemp = Object.assign({}, item);
    }

    function saveDetail(item) {
      if (!item.engEngrDisciplineDetailId) {
        table.maxDetailId += 1;
        item.engEngrDisciplineDetailId = table.maxDetailId;
      }
      item.breakLawList = item.breakLawArr.join(',');
      item.isEdit = false;
      isEditingDetail.value = false;
    }

    function deleteDetail(item) {
      $bvModal.msgBoxConfirm('是否確認刪除此筆懲戒事由？').then((isOK: boolean) => {
        if (isOK) {
          table.disciplineDtls = table.disciplineDtls.filter(i => i.engEngrDisciplineDetailId !== item.engEngrDisciplineDetailId);
        }
      });
    }

    function cancelAction(item) {
      if (item.engEngrDisciplineDetailId) {
        Object.assign(item, detailTemp);
        item.isEdit = false;
      } else {
        table.disciplineDtls = table.disciplineDtls.filter(i => i.engEngrDisciplineDetailId);
      }
      isEditingDetail.value = false;
    }

    return {
      FormStatusEnum,
      table,
      formOptions,
      formOptionsFormatter,
      addDetail,
      editDetail,
      saveDetail,
      deleteDetail,
      cancelAction,
      isEditingDetail,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>

<style scoped>
</style>
