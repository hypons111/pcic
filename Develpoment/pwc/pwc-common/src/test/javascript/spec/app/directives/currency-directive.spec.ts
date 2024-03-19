import { createLocalVue, shallowMount } from '@vue/test-utils';
import CompositionApi, { defineComponent } from '@vue/composition-api';
import 'mousetrap';
import 'mousetrap/plugins/global-bind/mousetrap-global-bind';
import { CurrencyDirective } from '@/directives/currency-directive';

const UseCurrencyDirectiveComponent = defineComponent({
  name: 'UseCurrencyDirectiveComponent',
  template: `
    <div>
    <input type="text" id="currency" v-c-currency/>
    </div>`,
});

describe('CurrencyDirective', () => {
  const localVue = createLocalVue();
  localVue.use(CompositionApi);
  localVue.directive('c-currency', CurrencyDirective);
  const wrapper = shallowMount(UseCurrencyDirectiveComponent, {
    localVue,
  });
  it('should format to currency style when blur ', async () => {
    const textInput = wrapper.find('#currency');
    await textInput.setValue('1000');
    await textInput.trigger('blur');
    const inputElement: HTMLInputElement = textInput.element as HTMLInputElement;
    expect(inputElement.value).toBe('1,000');
  });

  it('should un-format when focus ', async () => {
    const textInput = wrapper.find('#currency');
    await textInput.setValue('1000');
    await textInput.trigger('blur');
    await textInput.trigger('focus');
    const inputElement: HTMLInputElement = textInput.element as HTMLInputElement;
    expect(inputElement.value).toBe('1000');
  });
});
