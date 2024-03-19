<template>
    <label>({{ orgName }})</label>
</template>

<script>
import BidUnit10Servie from '@/components/bid/bidService/bid-unit10.service';
import { watch, reactive, ref, toRef } from '@vue/composition-api';

export default {
  name: 'bidUnit10Name',
  props: {
    orgId: {
      type: String,
      required: false,
      default: '',
    },
  },
  setup(props, context) {
    const orgIdProp = toRef(props, 'orgId');

    const bidUnit10Service = new BidUnit10Servie();

    const orgName = ref('');

    const bidUnits = reactive([]);

    bidUnit10Service.findAllBidUnit10s().then(returnArray => {
      bidUnits.splice(0, bidUnits.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.code;
          item.text = ele.name;
          bidUnits.push(item);
        });
      }
      fetchOrgName(orgIdProp.value);
    });

    function fetchOrgName(orgId) {
      if (orgId && (orgId.length === 8 || orgId.length === 10)) {
        let obj = bidUnits.find(element => {
          return element.value === orgId;
        });
        if (obj !== undefined) {
          orgName.value = obj.text;
        } else {
          orgName.value = '';
        }
      } else {
        orgName.value = '';
      }
    }

    watch(
      orgIdProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchOrgName(newValue);
        }
      },
      { immediate: true }
    );
    return {
      orgName,
    };
  },
  filters: {
  },
};
</script>
