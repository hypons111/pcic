<template>
  <div>
    <div v-if="type === 'popover'" :id="id">{{ partOfValue }} <i v-if="value.length > limitLength" style="color: grey"> ...(略)</i></div>
    <div v-if="type === 'hint'" :id="id">
      {{ fold ? partOfValue : value }}
      <i v-if="value.length > limitLength">
        <b-link @click="fold = !fold">{{ fold ? ' ...(展開)' : '(收合)' }}</b-link></i
      >
    </div>
    <!-- <span v-if="tag === 'span'" :id="id">{{ partOfValue }} <i v-if="value.length > limitLength"> ...(略)</i></span>
    <p v-if="tag === 'p'" :id="id">{{ partOfValue }} <i v-if="value.length > limitLength"> ...(略)</i></p> -->

    <b-popover
      v-if="type === 'popover' && value.length > limitLength"
      placement="right"
      triggers="hover"
      variant="dark"
      :target="id"
      :content="value"
    ></b-popover>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref, toRefs, unref } from '@vue/composition-api';

export default defineComponent({
  name: 'IPartOfMessage',
  props: {
    id: {
      required: true,
      type: String,
    },
    tag: {
      required: false,
      type: String,
      default: 'div',
    },
    // popover：hover時跳出視窗顯示全文；hint：點擊展開或收合
    type: {
      required: false,
      type: String,
      default: 'popover',
    },
    value: {
      required: false,
      type: String,
      default: '',
    },
    limitLength: {
      required: false,
      type: Number,
      default: 50,
    },
  },
  setup(props) {
    const { value, limitLength } = toRefs(props);
    const partOfValue = ref(props.value);
    const fold = ref(false);

    if (unref(value).length > unref(limitLength)) {
      partOfValue.value = unref(partOfValue).substring(0, unref(limitLength));
      fold.value = true;
    }

    return {
      partOfValue,
      fold,
    };
  },
});
</script>
