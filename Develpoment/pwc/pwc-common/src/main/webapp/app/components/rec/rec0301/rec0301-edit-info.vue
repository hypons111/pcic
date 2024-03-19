<template>
  <b-card no-body>
    <b-card-header>
      <h5 class="m-0">
        <font-awesome-icon icon="project-diagram"></font-awesome-icon>
        災後復建提報
      </h5>
    </b-card-header>
    <b-card-body>
      <b-tabs content-class="mt-4">
        <b-tab title="復建工程" :active="activeTab(0)" @click="changeTabIndex(0)" :disabled="tabDisabled[0]">
          <rec-0303-edit-project :recProject="recProjectProp" :formStatusForward="formStatusForwardProp" ref="rec0303EditProject" />
        </b-tab>
        <b-tab title="災損情形" :active="activeTab(1)" @click="changeTabIndex(1)" :disabled="tabDisabled[1]">
          <rec-0303-edit-analysis :recProject="recProjectProp" ref="rec0303EditAnalysis"/>
        </b-tab>
        <b-tab title="災損地區" :active="activeTab(2)" @click="changeTabIndex(2)" :disabled="tabDisabled[2]">
          <rec-0303-query-area :recProject="recProjectProp" ref="rec0303QueryArea"/>
        </b-tab>
        <b-tab title="復建工程(檢視)" :active="activeTab(3)" @click="changeTabIndex(3)" :disabled="tabDisabled[3]">
          <rec-0304-view-project :recProject="recProjectProp" ref="rec0304ViewProject"/>
        </b-tab>
      </b-tabs>
      <div class="layout">
        <keep-alive>
          <router-view />
        </keep-alive>
      </div>

      <div class="text-center mt-4">
        <i-button type="arrow-left" @click="toQueryView"></i-button>
        <i-button type="chevron-bar-left" @click="tabIndex > 0 ? tabIndex-- : ''"></i-button>
        <i-button type="chevron-bar-right" @click="nextTab"></i-button>
      </div>

      <div class="text-center mt-2">
        <div class="text-muted mt-1">目前分頁：{{ tabIndex + 1 }} / 4</div>
      </div>
    </b-card-body>
  </b-card>
</template>

<script lang="ts">
import { reactive, toRefs, ref} from '@vue/composition-api';
import { handleBack } from '@/router/router';
import Rec0303EditProject from '@/components/rec/rec0303/rec0303-edit-project.vue';
import Rec0303QueryArea from '@/components/rec/rec0303/rec0303-query-area.vue';
import Rec0303EditAnalysis from '@/components/rec/rec0303/rec0303-edit-analysis.vue';
import Rec0304ViewProject from '@/components/rec/rec0304/rec0304-view-project.vue';

export default {
  name: 'rec0301-edit-info',
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    recProject: {
      type: Object,
      required: false,
    },
  },
  components: {
    Rec0303EditProject,
    Rec0303QueryArea,
    Rec0303EditAnalysis,
    Rec0304ViewProject,
  },

  setup(props) {

    const recProjectProp = toRefs(props).recProject;
    const formStatusForwardProp = toRefs(props).formStatusForward;
    const isReload = ref(false);
    const tabIndex = ref(0);
    const tabDisabled = reactive([false, false,false]);
    const engReviewLog = ref(null);

    const activeTab = (index: number) => {
      if (tabIndex.value === index) {
        return true;
      } else {
        return false;
      }
    };

    const changeTabIndex = (index: number) => {
      tabIndex.value = index;
    };

    const nextTab = () => {
      switch (tabIndex.value) {
        case 0:
          tabDisabled[1] = false;
          tabIndex.value++;
          break;
        case 1:
          tabDisabled[2] = false;
          tabIndex.value++;
          break;
        case 2:
          tabIndex.value++;
      }
    };

    const toQueryView = () => {
      handleBack({ isReload: isReload.value});
      isReload.value = false;
    };

    return {
      tabIndex,
      activeTab,
      changeTabIndex,
      nextTab,
      tabDisabled,
      engReviewLog,
      toQueryView,
      recProjectProp,
      formStatusForwardProp,
    };
  },
};
</script>

<style>
.nav-tabs .nav-link {
  color: #007bff;
}
.nav-tabs .nav-link.active {
  font-weight: bold;
}
</style>
