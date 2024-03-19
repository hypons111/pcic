import { action } from '@storybook/addon-actions';
import DualListBox from '@/shared/dual-list-box.vue';

export default {
  title: 'Component/DualListBox',
  component: DualListBox,
  argTypes: {
    source: {},
    destination: {},
    label: {
      name: 'label',
      type: { name: 'string', required: false },
      defaultValue: 'name',
      description: '要用來顯示的屬性名稱',
    },
    disabled: {},
  },
};

const Template = (args, { argTypes }) => ({
  components: { DualListBox },
  props: Object.keys(argTypes),
  template: "<dual-list-box :source='source' :destination='destination' label='name' @onChangeList='onChangeList' :disabled='disabled'/>",
});

export const Main = Template.bind({});
Main.args = {
  source: [
    { name: 'WHITE', code: '#FFFFFF' },
    { name: 'SILVER', code: '#C0C0C0' },
    { name: 'GRAY', code: '#808080' },
  ],
  destination: [
    { name: 'BLACK', code: '#000000' },
    { name: 'RED', code: '#FF0000' },
  ],
  // Dual-List-Box 對 source 跟 destination 裡的元素的異動全部都是透過 event callback 來設定，
  // 所以要把異動設回去餵進去的物件才會實際產生作用
  onChangeList: ({ source, destination }) => {
    // XXX 為了跟 Storybook 整合才要這樣子做，千萬別在自己的 Component 裡做這樣做
    // 正常情況下只要設定這樣就好:
    // model.source = source; model.destination = destination;
    Main.args.source.splice(0, Math.max(source.length, Main.args.source.length), ...source);
    Main.args.destination.splice(0, Math.max(destination.length, Main.args.destination.length), ...destination);

    // 餵給 Storybook action addon，方便在 Storybook 裡顯示
    action('changeList').call(Main, { source, destination });
  },
};
