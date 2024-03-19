import {
  helpers,
  alpha as vuelidateAlpha,
  alphaNum as vuelidateAlphaNum,
  and as vuelidateAnd,
  between as vuelidateBetween,
  decimal as vuelidateDecimal,
  email as vuelidateEmail,
  integer as vuelidateInteger,
  ipAddress as vuelidateIpAddress,
  macAddress as vuelidateMacAddress,
  maxLength as vuelidateMaxLength,
  maxValue as vuelidateMaxValue,
  minLength as vuelidateMinLength,
  minValue as vuelidateMinValue,
  not as vuelidateNot,
  numeric as vuelidateNumeric,
  or as vuelidateOr,
  required as vuelidateRequired,
  requiredIf as vuelidateRequiredIf,
  requiredUnless as vuelidateRequiredUnless,
  sameAs as vuelidateSameAs,
  url as vuelidateUrl,
} from '@vuelidate/validators';
import { ValidationRule } from '@vuelidate/core';
import { Ref } from 'vue-demi';

export const alpha = helpers.withMessage('請輸入英文字', vuelidateAlpha);
export const alphaNum = helpers.withMessage('請輸入英數字', vuelidateAlphaNum);
export const and = (...validators: ValidationRule[]) => helpers.withMessage('輸入的值不符合所有的檢核規則', vuelidateAnd(...validators));
export const between = (min: number | Ref<number>, max: number | Ref<number>) =>
  helpers.withMessage(`輸入的值必須介於 ${min} 與 ${max} 之間`, vuelidateBetween(min, max));
export const decimal = helpers.withMessage('請輸入數字', vuelidateDecimal);
export const email = helpers.withMessage('輸入的值不是合法的 E-Mail', vuelidateEmail);
export const integer = helpers.withMessage('請輸入整數', vuelidateInteger);
export const ipAddress = helpers.withMessage('輸入的值不是合法的 IP Address', vuelidateIpAddress);
export const macAddress = (separator: string | Ref<string>) =>
  helpers.withMessage('輸入的值不是合法的 MAC Address', vuelidateMacAddress(separator));
export const maxLength = (max: number | Ref<number>, errorMessage?: string) =>
  helpers.withMessage(errorMessage ? errorMessage : `輸入的長度超過 ${max}`, vuelidateMaxLength(max));
export const maxValue = (max: number | Ref<number> | string | Ref<string>) =>
  helpers.withMessage(`輸入的值不能大於 ${max}`, vuelidateMaxValue(max));
export const minLength = (min: number | Ref<number>) => helpers.withMessage(`輸入的長度不足 ${min}`, vuelidateMinLength(min));
export const minValue = (min: number | Ref<number> | string | Ref<string>) =>
  helpers.withMessage(`輸入的值不能小於 ${min}`, vuelidateMinValue(min));
export const not = (validator: ValidationRule) => helpers.withMessage('輸入的值不符合檢核規則', vuelidateNot(validator));
export const numeric = helpers.withMessage('請輸入數字', vuelidateNumeric);
export const or = (validator: ValidationRule) => helpers.withMessage('輸入的值不符合部份的檢核規則', vuelidateOr(validator));
export const required = helpers.withMessage('請輸入值', vuelidateRequired);
export const requiredIf = (prop: boolean | string | (() => boolean | Promise<boolean>)) =>
  helpers.withMessage('請輸入值', vuelidateRequiredIf(prop));
export const requiredUnless = (prop: boolean | string | (() => boolean | Promise<boolean>)) =>
  helpers.withMessage('請輸入值', vuelidateRequiredUnless(prop));
export const sameAs = <E = unknown>(equalTo: E, otherName?: string) =>
  helpers.withMessage(`值必須與 ${otherName} 的值相同`, vuelidateSameAs(equalTo));
export const url = helpers.withMessage('輸入的值不是合法的網址', vuelidateUrl);
