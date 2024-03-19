<template>
  <div class="row justify-content-center mt-3">
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
            <b-form-group label="使用者帳號" label-for="id" description="
                機關帳號：工程會自訂機關代碼簡碼+ '-' +流水號，例如：9.99-0。 若機關為工程會，則帳號為 工程會機關代碼(3.60)+'-' +流水號，例如：3.60-6。
                公司法人帳號：公司統編 + '- + 流水號。
                個人帳號：限英數字，禁用.跟-。">
              <b-form-input id="id" type="text" name="id" autofocus v-model="form.userId" data-cy="id"></b-form-input>
            </b-form-group>
            <!-- <b-form-group label="帳號類型" label-for="userType" description="A：機關帳號 C：公司法人帳號 I：個人帳號">
              <b-form-select id="userType" :options="queryOptions.userTypeOpts" name="userType" autofocus v-model="form.userType" data-cy="userType"></b-form-select>
            </b-form-group>
            <b-form-group label="工程會機關代碼" label-for="pccOrgId" description="詳細對應請至ADM_AGENT資料表查看">
              <b-form-input id="pccOrgId" type="text" name="pccOrgId" autofocus v-model="form.pccOrgId" data-cy="pccOrgId"></b-form-input>
            </b-form-group>
            <b-form-group label="公司統編" label-for="compIdno">
              <b-form-input id="compIdno" type="text" name="compIdno" autofocus v-model="form.compIdno" data-cy="compIdno"></b-form-input>
            </b-form-group>
            <b-form-group label="使用者姓名" label-for="name">
              <b-form-input id="name" type="text" name="name" autofocus v-model="form.name" data-cy="name"></b-form-input>
            </b-form-group>
            <b-form-group label="工程會單位代碼" label-for="unitId" description="測試方便暫保留，正式情況不會有，須由機關帳號裡擷取。 詳細對應請至ADM_UNIT資料表查詢。">
              <b-form-input id="unitId" type="text" name="unitId" autofocus v-model="form.unit.id" data-cy="unitId"></b-form-input>
            </b-form-group>
            <b-form-group label="工程會單位名稱" label-for="unitName" description="測試方便暫保留，正式情況不會有">
              <b-form-input id="unitName" type="text" name="unitName" autofocus v-model="form.unit.name" data-cy="unitName"></b-form-input>
            </b-form-group>
            <b-form-group label="身分證字號" label-for="idn">
              <b-form-input id="idn" type="text" name="idn" autofocus v-model="form.idn" data-cy="idn"></b-form-input>
            </b-form-group>
            <b-form-group label="E-mail 位址" label-for="email">
              <b-form-input id="email" type="text" name="email" autofocus v-model="form.email" data-cy="email"></b-form-input>
            </b-form-group>
            <b-form-group label="IP 位址" label-for="ip">
              <b-form-input id="ip" type="text" name="ip" autofocus v-model="form.ip" data-cy="ip"></b-form-input>
            </b-form-group> -->
            <div>
              <b-button data-cy="submit" type="submit" variant="primary">登入</b-button>
            </div>
            <b-form-group>
              <i-tree-view v-show="!useNewMenuInject.data" ref="menuTree" @selectNode="selectFunction($event)" :model="data"></i-tree-view>
              <!--              <div v-for="lifeCycle in lifeCycles" :key="lifeCycle.value">-->
              <!--                <b-form-checkbox-group v-model="selection.lifeCycle" stacked>-->
              <!--                  <b-form-checkbox :value="lifeCycle.value" @change="lifeCycleChange">-->
              <!--                    {{ lifeCycle.text }}-->
              <!--                  </b-form-checkbox>-->
              <!--                </b-form-checkbox-group>-->
              <!--                <div v-for="parent in parentMap.get(lifeCycle.value)" :key="parent.id" class="ml-4">-->
              <!--                  <b-form-checkbox-group v-model="selection.parent" stacked>-->
              <!--                    <b-form-checkbox :value="parent.id" @change="parentChange">-->
              <!--                      {{ parent.label }}-->
              <!--                    </b-form-checkbox>-->
              <!--                  </b-form-checkbox-group>-->
              <!--                  <div v-for="item in childMap.get(parent.id)" :key="item.value" class="ml-4">-->
              <!--                    <b-form-checkbox-group v-model="selection.child" inline>-->
              <!--                      <b-form-checkbox :value="item.value" @change="childChange">-->
              <!--                        {{ item.value }}-->
              <!--                      </b-form-checkbox>-->
              <!--                    </b-form-checkbox-group>-->
              <!--                  </div>-->
              <!--                </div>-->
              <!--              </div>-->
            </b-form-group>
          </b-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { inject, reactive, ref } from '@vue/composition-api';
import AccountService from '@/account/account.service';
import axios from 'axios';
import MenuService from '@/core/menu/menu-service';
import ExpandedMenu from '@/core/menu/expanded-menu';
import ITreeView from '@/shared/i-tree-view/i-tree-view.vue';

export default {
  name: 'devLoginForm',
  components: {
    ITreeView,
  },
  setup() {
    const form = reactive({
      userId: '',
      // chName: '',
      // userType: '',
      // pccOrgId: '',
      // compIdno: '',
      // unitId: '',
      // unitName: '',
      // idno: '',
      // email: '',
      // sourceIp: '',
    });

    const accountService = inject<() => AccountService>('accountService')();
    const menuService: MenuService = inject('menuService');

    const useNewMenuInject = inject('useNewMenu');
    const { data } = useNewMenuInject.data ? menuService.retrieveAppMenu_() : menuService.retrieveAppMenu();

    const menuTree = ref(null);

    const lifeCycles = reactive([]);
    const parentMap = reactive(new Map());
    const childMap = reactive(new Map());

    const selection = reactive({
      lifeCycle: [],
      parent: [],
      child: [],
    });

    const queryOptions = reactive({
      userTypeOpts: [
        { value: '', text: '請選擇' },
        { value: 'A', text: '機關帳號' },
        { value: 'C', text: '公司法人帳號' },
        { value: 'I', text: '個人帳號' },
      ],
    });

    function mapSelection(m: ExpandedMenu) {
      return {
        text: m.label,
        value: m.functionId,
      };
    }

    // watch(data, value => {
    //   const expandedMenus: ExpandedMenu[] = flattenAndTransformMenus(value);
    //   lifeCycles.push(
    //     ...expandedMenus
    //       .filter(m => !m.parentId)
    //       .map(p => {
    //         return {
    //           text: p.label,
    //           value: p.id,
    //         };
    //       })
    //   );
    //   lifeCycles.forEach(lifeCycle => {
    //     const parent = expandedMenus.filter(m => m.parentId === lifeCycle.value);
    //     parent.forEach(chs => {
    //       if (parentMap.get(lifeCycle.value)) {
    //         parentMap.get(lifeCycle.value).push(chs);
    //       } else {
    //         parentMap.set(lifeCycle.value, [chs]);
    //       }
    //     });
    //     selection.lifeCycle.push(lifeCycle.value);
    //   });
    //   parentMap.forEach(parent => {
    //     parent.forEach(p => {
    //       const ch = expandedMenus.filter(m => m.parentId === p.id);
    //       if (ch.filter(c => c.functionId).length === 0) {
    //         ch.forEach(chs => {
    //           const nested = expandedMenus.filter(m => m.parentId === chs.id);
    //           if (childMap.get(p.id)) {
    //             childMap.get(p.id).push(...nested.map(mapSelection));
    //           } else {
    //             childMap.set(p.id, nested.map(mapSelection));
    //           }
    //         });
    //       } else {
    //         childMap.set(p.id, ch.map(mapSelection));
    //       }
    //     });
    //     selection.parent.push(parent.id);
    //   });
    //   lifeCycleChange(selection.lifeCycle);
    // });

    function childChange(event) {
      selection.child.splice(0, selection.child.length, ...event);
      selection.child.forEach(c => {
        childMap.forEach((value: [], key) => {
          if (value.find(v => v['value'] === c)) {
            if (!selection.parent.includes(key)) {
              selection.parent.push(key);
            }
          }
        });
      });
    }

    function parentChange(event) {
      selection.child.splice(0, selection.child.length);
      event.forEach(p => {
        if (p && childMap.get(p)) {
          childMap.get(p).forEach(c => selection.child.push(c.value));
        }
        parentMap.forEach((value: [], key) => {
          if (value.find(v => v['id'] === p)) {
            if (!selection.lifeCycle.includes(key)) {
              selection.lifeCycle.push(key);
            }
          }
        });
      });
    }

    function lifeCycleChange(event) {
      selection.parent.splice(0, selection.parent.length);
      event.forEach(l => {
        parentMap.get(l).forEach(p => {
          selection.parent.push(p.id);
        });
      });
      parentChange(selection.parent);
    }

    function doLogin() {
      const authorities = menuTree.value.getCheckedNode();
      axios.post('account', { ...form, authorities: authorities }).then(() =>
        axios.post('authenticate', { username: form.userId, password: 'REMOVE_ME' }).then(result => {
          const bearerToken = result.headers.authorization;
          if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
            const jwt = bearerToken.slice(7, bearerToken.length);
            sessionStorage.setItem('jhi-authenticationToken', jwt);
            localStorage.removeItem('jhi-authenticationToken');
          }
          accountService.retrieveAccount();
        })
      );
    }

    function selectFunction(functions) {
      selection.child.splice(0, selection.child.length, ...functions);
    }

    return {
      form,
      menuTree,
      data,
      lifeCycles,
      parentMap,
      childMap,
      selection,
      queryOptions,
      useNewMenuInject,
      parentChange,
      childChange,
      lifeCycleChange,
      doLogin,
      selectFunction,
    };
  },
};
</script>
