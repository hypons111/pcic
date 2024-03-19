import { DirectiveBinding } from 'vue/types/options';
import { VNode } from 'vue';

const defaultLocale = 'zh-TW';

const format = (el: HTMLElement, bind: DirectiveBinding, vnode: VNode) => {
  if (el instanceof HTMLInputElement) {
    const input = el as HTMLInputElement;
    input.addEventListener('blur', e => {
      if (!isNaN(Number(input.value))) {
        input.value = new Intl.NumberFormat(defaultLocale).format(Number(input.value));
      }
    });
    input.addEventListener('focus', e => {
      const number = parseLocaleNumber(input.value);
      if (!isNaN(number)) {
        input.value = number.toFixed();
      }
    });
  }
};

// https://stackoverflow.com/a/29273131
/**
 * Parse a localized number to a float.
 * @param {string} stringNumber - the localized number
 * @param {string} locale - [optional] the locale that the number is represented in. Omit this parameter to use the current locale.
 */
function parseLocaleNumber(stringNumber, locale: string = defaultLocale) {
  const thousandSeparator = Intl.NumberFormat(locale).format(11111).replace(/\d/g, '');
  const decimalSeparator = Intl.NumberFormat(locale).format(1.1).replace(/\d/g, '');

  return parseFloat(stringNumber.replace(new RegExp('\\' + thousandSeparator, 'g'), '').replace(new RegExp('\\' + decimalSeparator), '.'));
}

const CurrencyDirective = {
  bind(el, bind, vnode) {
    format(el, bind, vnode);
  },
};

export { CurrencyDirective };
