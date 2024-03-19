<template>
  <b-form-input
    class="col-4"
    lazy
    trim
    v-if="vDataProp"
    v-model="vDataProp[inputDataProp.modelKey].$model"
    @blur="onInputEvent('blurEvent')"
    @keyup.enter="onInputEvent('keyUpEvent')"
    :state="validateState(vDataProp[inputDataProp.modelKey])"
  >
  </b-form-input>
</template>

<script lang="ts">
import { validateState } from '@/shared/form';
import nationalId from '@/shared/validators/raw/national-id';
import { defineComponent, reactive } from '@vue/composition-api';
import { BFormInput } from 'bootstrap-vue';
import { includes as _includes, keys as _keys, size as _size, trim as _trim, upperFirst as _upperFirst } from 'lodash';

export default defineComponent({
  name: 'IFormNationalIdInput',
  components: { BFormInput },
  props: {
    inputData: {
      required: true,
      type: Object,
      default: () => {},
    },
    vData: {
      required: true,
      type: Object,
      default: () => {},
    },
  },
  setup(props, context) {
    const inputDataProp = reactive(props.inputData);
    const vDataProp = reactive(props.vData);

    function onInputEvent(eventName) {
      let $modelProp = vDataProp[inputDataProp.modelKey].$model;
      const isValid = nationalId($modelProp);
      if (!isValid || _size(_trim($modelProp)) === 0) return;
      vDataProp[inputDataProp.modelKey].$model = _upperFirst($modelProp);
      const inputDataPropKeys = _keys(inputDataProp),
        hasEventsKey = _includes(inputDataPropKeys, 'events');
      if (hasEventsKey) {
        const eventsKeys = _keys(inputDataProp.events),
          hasEventName = _includes(eventsKeys, eventName);
        if (hasEventName) context.emit(inputDataProp.events[eventName]);
      }
    }

    return {
      inputDataProp,
      vDataProp,
      validateState,
      onInputEvent,
    };
  },
});
</script>
