<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          測試用
        </h5>
      </div>
      <div class="card-body">
        <b-form-row>
          <!-- :label-cols="6" :content-cols="6" -->
          <i-form-group-check :label="'颱風'" :item="$v.typhoonValue" :labelStar="true">
            <i-group-select-checkbox
              v-model="$v.typhoonValue.$model"
              :options="options.typhoonList"
              :isDropdown="true"
            ></i-group-select-checkbox>
          </i-form-group-check>

          <i-form-group-check :label="'地區'" :item="$v.area" :labelStar="true">
            <i-group-select-checkbox v-model="$v.area.$model" :options="options.areaList" :isDropdown="true"></i-group-select-checkbox>
          </i-form-group-check>
        </b-form-row>

        <i-apexcharts :type="'multi-line'" :items="items" :data="data"></i-apexcharts>

        <div>typhoon value:{{ form.typhoonValue }}</div>
        <div>area value:{{ form.area }}</div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { reactive, mounted } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
export default {
  setup() {
    const formDefault = {
      typhoonValue: [],
      area: [],
      sung: '',
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      typhoonValue: {},
      area: {},
      sung: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //物件裡再放List
    const options = reactive({
      typhoonList: [
        {
          parents: '-----1月颱風',
          child: [
            { value: '1', text: 'xx颱風' },
            { value: '2', text: 'yy颱風' },
            { value: '3', text: 'zz颱風' },
          ],
        },
        {
          parents: '-----2月颱風',
          child: [
            { value: '5', text: '5號颱風' },
            { value: '6', text: '6號颱風' },
            { value: '7', text: '7號颱風' },
          ],
        },
      ],
      areaList: [
        {
          parents: '-----北部地區',
          child: [
            { value: '1', text: '台北' },
            { value: '2', text: '桃園' },
          ],
        },
        {
          parents: '-----南部地區',
          child: [
            { value: '5', text: '高雄' },
            { value: '6', text: '屏東' },
          ],
        },
      ],
    });

    // const items = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '11月'];
    // const data = [30, 42, 37, 50.5, 60, 63, 80, 60, 63, 80];

    const items = ['2001', '2002', '2003', '2004', '2005', '2006', '2007', '2008', '2009', '2010'];

    const data = [
      {
        name: '項目1',
        data: [45, 52, 38, 24, 33, 26, 21, 20, 6, 8],
      },
      {
        name: '項目2',
        data: [35, 0, 62, 42, 13, 18, 29, 37, 36, 51],
      },
      {
        name: '項目3',
        data: [127, 57, 74, 99, 75, 38, 62, 47, 82, 56],
      },
    ];

    return {
      options,
      $v,
      form,
      items,
      data,
    };
  },
};
</script>
