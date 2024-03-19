import { createLocalVue, mount } from '@vue/test-utils';
import VueCompositionAPI, { reactive } from '@vue/composition-api';
import 'mousetrap';
import 'mousetrap/plugins/global-bind/mousetrap-global-bind';
import IFormInput from '@/shared/form/i-form-input.vue';
import { lessThan } from '@/shared/validators';
import { BFormGroup, BFormInput, BFormInvalidFeedback } from 'bootstrap-vue';

const UseIFormInputComponent = {
  name: 'UseIFormInputComponent',
  components: { IFormInput },
  template: `
    <b-form-group>
    <i-form-input type="text" v-model="state.textInput" id="myInput"
                  :rules="rules"></i-form-input>
    </b-form-group>`,
  setup() {
    const state = reactive({
      textInput: '',
    });
    const rules = reactive({
      lessThan: lessThan(50),
    });
    return { state, rules };
  },
};

describe('IFormInput.vue', () => {
  const localVue = createLocalVue();
  localVue.use(VueCompositionAPI);
  localVue.component('i-form-input', IFormInput);
  localVue.component('b-form-input', BFormInput);
  localVue.component('b-form-group', BFormGroup);
  localVue.component('b-form-invalid-feedback', BFormInvalidFeedback);

  const wrapper = mount(UseIFormInputComponent, {
    localVue,
  });

  it('it should display error message when value is invalid', async () => {
    const input = wrapper.find('#myInput');
    const inputElement: HTMLInputElement = input.element as HTMLInputElement;
    inputElement.value = '51';
    await input.trigger('blur');
    const div = wrapper.find('.invalid-feedback');
    expect(div.text()).toContain('輸入的值必須小於 50');
  });
});
