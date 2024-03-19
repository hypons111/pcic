import VueCompositionAPI, { computed, reactive, ref } from '@vue/composition-api';
import { email, lessThan, greaterThan, sameAs } from '@/shared/validators';
import { useValidation, validateState } from '@/shared/form';
import { createLocalVue, mount } from '@vue/test-utils';
import { BFormGroup, BFormInput, BFormInvalidFeedback } from 'bootstrap-vue';

const UseBasicValidationComponent = {
  name: 'UseBasicValidationComponent',
  template: `
    <b-form-group label-cols-md="2" content-cols-md="8" label="名稱"
                  label-for="name" label-align-md="right">
    <b-form-input id="name" type="text" v-model="$v.name.$model"
                  :state="validateState($v.name)">
    </b-form-input>
    <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">
      {{ error.$message }}
    </b-form-invalid-feedback>
    </b-form-group>`,
  setup() {
    const formDefaults = { name: '', email: '' };
    const form = reactive(Object.assign({}, formDefaults));
    const rules = {
      name: { email },
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefaults);
    return { $v, reset, validateState };
  },
};

const UseCrossFieldValidationComponent = {
  name: 'UseCrossFieldValidationComponent',
  template: `
    <div>
    <b-form-group label-cols-md="2" content-cols-md="8" label="欄位1"
                  label-for="field1" label-align-md="right">
      <b-form-input id="field1" type="text" v-model="$v.field1.$model"
                    :state="validateState($v.field1)">
      </b-form-input>
      <b-form-invalid-feedback v-for="(error, index) of $v.field1.$errors" :key="index">
        {{ error.$message }}
      </b-form-invalid-feedback>
    </b-form-group>
    <b-form-group label-cols-md="2" content-cols-md="8" label="欄位2"
                  label-for="field2" label-align-md="right">
      <b-form-input id="field2" type="text" v-model="$v.field2.$model"
                    :state="validateState($v.field2)">
      </b-form-input>
      <b-form-invalid-feedback v-for="(error, index) of $v.field2.$errors" :key="index">
        {{ error.$message }}
      </b-form-invalid-feedback>
    </b-form-group>
    </div>`,
  setup() {
    const formDefaults = { field1: null, field2: null };

    const form = reactive(Object.assign({}, formDefaults));
    const rules = {
      field1: { lessThan: lessThan(computed(() => form.field2)) },
      field2: { greatThan: greaterThan(computed(() => form.field1)) },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefaults, ['field1', 'field2']);
    return { $v, reset, validateState };
  },
};

const ChildValidationComponent = {
  name: 'ChildValidationComponent',
  template: `
    <div>
    <b-form-group label-cols-md="2" content-cols-md="8" label="密碼"
                  label-for="password" label-align-md="right">
      <b-form-input id="password" type="text" v-model="$v.password.$model"
                    :state="validateState($v.password)">
      </b-form-input>
      <b-form-invalid-feedback v-for="(error, index) of $v.password.$errors" :key="index">
        {{ error.$message }}
      </b-form-invalid-feedback>
    </b-form-group>
    <b-form-group label-cols-md="2" content-cols-md="8" label="密碼確認"
                  label-for="passwordConfirm" label-align-md="right">
      <b-form-input id="passwordConfirm" type="text" v-model="$v.passwordConfirm.$model"
                    :state="validateState($v.passwordConfirm)">
      </b-form-input>
      <b-form-invalid-feedback v-for="(error, index) of $v.passwordConfirm.$errors" :key="index">
        {{ error.$message }}
      </b-form-invalid-feedback>
    </b-form-group>
    </div>`,
  setup() {
    const formDefaults = { password: '', passwordConfirm: '' };
    const form = reactive(Object.assign({}, formDefaults));
    const rules = {
      password: { sameAs: sameAs(computed(() => form.passwordConfirm)) },
      passwordConfirm: { sameAs: sameAs(computed(() => form.password)) },
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefaults, ['password', 'passwordConfirm']);
    return { $v, reset, validateState };
  },
};

const ParentValidationComponent = {
  name: 'ParentValidationComponent',
  components: {
    UseCrossFieldValidationComponent,
    ChildValidationComponent,
  },
  template: `
    <div>
    <button type="button" @click="change()" id="changeButton">Change</button>
    <keep-alive>
      <component v-bind:is="currentComponent"></component>
    </keep-alive>

    </div>`,
  setup() {
    const currentComponent = ref('use-cross-field-validation-component');
    function change() {
      currentComponent.value = 'child-validation-component';
    }
    return { currentComponent, change };
  },
};

describe('validations', () => {
  const localVue = createLocalVue();
  localVue.use(VueCompositionAPI);
  localVue.component('b-form-input', BFormInput);
  localVue.component('b-form-group', BFormGroup);
  localVue.component('b-form-invalid-feedback', BFormInvalidFeedback);

  it('it should display error message when value is not a valid email', async () => {
    const wrapper = mount(UseBasicValidationComponent, {
      localVue,
    });
    const input = wrapper.find('#name');
    const inputElement: HTMLInputElement = input.element as HTMLInputElement;
    inputElement.value = 'abc';
    await input.trigger('blur');
    const div = wrapper.find('.invalid-feedback');
    expect(div.text()).toContain('輸入的值不是合法的 E-Mail');
  });

  it('it should display error message when valid cross field', async () => {
    const wrapper = mount(UseCrossFieldValidationComponent, {
      localVue,
    });
    const input1 = wrapper.find('#field1');
    const inputElement1: HTMLInputElement = input1.element as HTMLInputElement;

    const input2 = wrapper.find('#field2');
    const inputElement2: HTMLInputElement = input2.element as HTMLInputElement;

    inputElement1.value = '50';
    await input1.trigger('blur');

    inputElement2.value = '30';
    await input2.trigger('blur');
    const div = wrapper.find('.invalid-feedback');
    expect(div.text()).toContain('輸入的值必須小於 30');
  });

  it('it should display error message when multiple keep alive child components', async () => {
    const wrapper = mount(ParentValidationComponent, {
      localVue,
    });

    const field1 = wrapper.find('#field1');
    const field1Element1: HTMLInputElement = field1.element as HTMLInputElement;

    const field2 = wrapper.find('#field2');
    const field1Element2: HTMLInputElement = field2.element as HTMLInputElement;

    field1Element1.value = '50';
    await field1.trigger('blur');

    field1Element2.value = '30';
    await field2.trigger('blur');

    let div = wrapper.find('.invalid-feedback');
    expect(div.text()).toContain('輸入的值必須小於 30');

    const changeButton = wrapper.find('#changeButton');
    const changeButtonElement = changeButton.element as HTMLButtonElement;
    await changeButtonElement.click();

    const password = wrapper.find('#password');
    const passwordElement: HTMLInputElement = password.element as HTMLInputElement;

    const passwordConfirm = wrapper.find('#passwordConfirm');
    const passwordConfirmElement: HTMLInputElement = passwordConfirm.element as HTMLInputElement;

    passwordElement.value = 'aaa';
    await password.trigger('blur');

    passwordConfirmElement.value = 'bbb';
    await password.trigger('blur');

    div = wrapper.find('.invalid-feedback');
    // TODO fix sameAs arguments
    expect(div.text()).toContain('值必須與 undefined 的值相同');
  });
});
