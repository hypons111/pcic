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
import GeoIn02Query from '@/components/geo/geo-in-02/geo-in-02-query.vue';
import GeoIn02EditInfo from '@/components/geo/geo-in-02/geo-in-02-edit-info.vue';
// import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoIn02',
  components: {
    GeoIn02Query,
    GeoIn02EditInfo,
  },
  setup() {
    const currentChildComponent = ref('');
    onMounted(() => {
      changeChildComponent('GeoIn02Query', false);
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
