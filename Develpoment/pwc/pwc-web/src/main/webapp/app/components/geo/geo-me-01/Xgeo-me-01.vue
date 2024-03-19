<template>
  <div>
    <keep-alive>
      <component
        :is="currentChildComponent"
        :isReload="isReload"
        :cmsComp="cmsComp"
        :formStatus="formStatus"
        @changeComponent="changeChildComponent"
      ></component>
    </keep-alive>
  </div>
</template>

<script lang="ts">
import { onMounted, Ref, ref } from '@vue/composition-api';
import GeoMn01EditInfo from '@/components/geo/geo-in-02/geo-in-02-edit-info.vue';
// import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoMe01',
  components: {
    GeoMn01EditInfo,
  },
  setup() {
    const currentChildComponent = ref('');
    onMounted(() => {
      changeChildComponent('GeoMn01EditInfo', false);
    });

    const cmsComp = ref(null);

    const isReload = ref(false);

    const formStatus = ref(null);

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    function changeChildComponent(componentName: string, param: any, formStatusRef?: Ref<formStatusEnum>) {
      if (componentName === 'GeoIn02Query') {
        isReload.value = param;
        // cmsComp.value = Object.assign(param);
      } else if (componentName === 'GeoIn02EditInfo') {
        isReload.value = false;
        cmsComp.value = param;
        formStatus.value = formStatusRef;
      }
      currentChildComponent.value = componentName;
    }

    return { currentChildComponent, isReload, cmsComp, formStatus, changeChildComponent };
  },
};
</script>
