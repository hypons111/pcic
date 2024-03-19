<template>
  <div>
    <b-tabs content-class="mt-4" v-model="tabIndex" @input="changeTab">
      <b-tab title="Q&A管理">
        <keep-alive>
          <component
            :is="currentChildComponent"
            :isReload="isReload"
            :geoQuestion="geoQuestion"
            @changeComponent="changeChildComponent"
          ></component>
        </keep-alive>
      </b-tab>
      <b-tab title="備註設定">
        <geo0402Create ref="geo0402Create" @changeComponent="changeChildComponent" />
      </b-tab>
    </b-tabs>
  </div>
</template>

<script lang="ts">
import { onMounted, Ref, ref } from '@vue/composition-api';
import geo0402Query from '@/components/geo/geo0402/geo0402-query.vue';
import geo0402Edit from '@/components/geo/geo0402/geo0402u1.vue';
import geo0402Create from '@/components/geo/geo0402/geo0402c1.vue';

export default {
  name: 'geo0402',
  components: {
    geo0402Query,
    geo0402Edit,
    geo0402Create,
  },
  setup() {
    const currentChildComponent = ref('');
    onMounted(() => {
      changeChildComponent('geo0402Query', false);
    });

    const geo0402Create = ref(null);

    const geoQuestion = ref(null);

    const isReload = ref(false);

    const tabIndex = ref(0);

    function changeChildComponent(componentName: string, param: any) {
      tabIndex.value = 0;
      if (componentName === 'geo0402Query') {
        isReload.value = param;
      } else if (componentName === 'geo0402Edit') {
        isReload.value = false;
        geoQuestion.value = param;
      }
      currentChildComponent.value = componentName;
    }

    function changeTab(tabIndex) {
      if (tabIndex === 0) {
        geo0402Create.value.reset();
      }
    }

    return { geo0402Create, currentChildComponent, isReload, geoQuestion, changeChildComponent, tabIndex, changeTab };
  },
};
</script>
