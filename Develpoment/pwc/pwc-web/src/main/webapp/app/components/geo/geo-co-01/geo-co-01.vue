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
import { onMounted, reactive, Ref, ref } from '@vue/composition-api';
import GeoCo01Query from '@/components/geo/geo-co-01/geo-co-01-query.vue';
import GeoCo01EditInfo from '@/components/geo/geo-co-01/geo-co-01-edit-info.vue';
// import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoCo01',
  components: {
    GeoCo01Query,
    GeoCo01EditInfo,
  },
  setup() {
    const currentChildComponent = ref('');
    onMounted(() => {
      changeChildComponent('GeoCo01Query', false);
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
      // if(formStatusRef) alert("changeChildComponent : " + formStatusRef.value);
      if (componentName === 'GeoCo01Query') {
        isReload.value = param;
        // cmsComp.value = Object.assign(param);
      } else if (componentName === 'GeoCo01EditInfo') {
        isReload.value = false;
        // cmsComp.value = Object.assign(param);
        cmsComp.value = param;
        formStatus.value = formStatusRef;
      }
      currentChildComponent.value = componentName;
    }

    return { currentChildComponent, isReload, cmsComp, formStatus, changeChildComponent };
  },
};
</script>
