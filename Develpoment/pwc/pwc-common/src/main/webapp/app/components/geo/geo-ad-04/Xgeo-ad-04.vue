<template>
  <div>
    <!--    <keep-alive>-->
    <component
      :is="currentChildComponent"
      :isReload="isReload"
      :cmsComp="cmsComp"
      :formStatus="formStatus"
      @changeComponent="changeChildComponent"
    ></component>
    <!--    </keep-alive>-->
  </div>
</template>

<script lang="ts">
import { onMounted, Ref, ref } from '@vue/composition-api';
import GeoIn03Query from '@/components/geo/geo-in-03/geo-in-03-query.vue';
// import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoIn03',
  components: {
    GeoIn03Query,
  },
  setup() {
    const currentChildComponent = ref('');
    onMounted(() => {
      changeChildComponent('GeoIn03Query', false);
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
