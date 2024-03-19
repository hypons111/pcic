<template>
  <div>
    <keep-alive>
      <component :is="currentChildComponent" :isReload="isReload" :cmsComp="cmsComp" @changeComponent="changeChildComponent"></component>
    </keep-alive>
  </div>
</template>

<script lang="ts">
import { onMounted, ref } from '@vue/composition-api';
import Ogn01Query from '@/components/ogn01/ogn01-query.vue';
import Ogn01EditInfo from '@/components/ogn01/ogn01-edit-info.vue';

export default {
  name: 'ogn01',
  components: {
    Ogn01Query,
    Ogn01EditInfo,
  },
  setup() {
    const currentChildComponent = ref('');
    onMounted(() => {
      changeChildComponent('Ogn01Query', false);
    });

    const cmsComp = ref(null);

    const isReload = ref(false);

    function changeChildComponent(componentName: string, param: any) {
      if (componentName === 'Ogn01EditInfo') {
        isReload.value = false;
        cmsComp.value = param;
      } else if (componentName === 'Ogn01Query') {
        isReload.value = param;
      }
      currentChildComponent.value = componentName;
    }

    return { currentChildComponent, isReload, cmsComp, changeChildComponent };
  },
};
</script>
