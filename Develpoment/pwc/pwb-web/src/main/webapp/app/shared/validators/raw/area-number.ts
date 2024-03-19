import { unref } from 'vue-demi';

/**
 * Check if tel number is valid.
 * @param {*} givenValue
 * @return {function(*=): boolean}
 */

export default function (givenValue: any) {
  return (value: any) => {
    const regex = new RegExp(/^(0[2-8])$/, 'g');
    if (givenValue) {
      return regex.test(value);
    } else {
      return false;
    }
  };
}
