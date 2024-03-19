import { unref } from 'vue-demi';
import dayjs from 'dayjs';

/**
 * Check if value is less than given value.
 * @param {*} givenValue
 * @return {function(*=): boolean}
 */
export default function (givenValue: any) {
  return (value: any) => {
    const compareValue = unref(value);
    const lessValue = unref(givenValue);
    if (compareValue && lessValue) {
      if (compareValue instanceof Date && lessValue instanceof Date) {
        return dayjs(compareValue).isBefore(lessValue);
      }
      return Number(compareValue) < Number(lessValue);
    }
    return true;
  };
}
