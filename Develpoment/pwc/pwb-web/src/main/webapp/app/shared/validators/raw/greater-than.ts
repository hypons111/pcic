import { unref } from 'vue-demi';
import dayjs from 'dayjs';

/**
 * Check if value is greater than given value.
 * @param {*} givenValue
 * @return {function(*=): boolean}
 */
export default function (givenValue: any) {
  return (value: any) => {
    const compareValue = unref(value);
    const greaterValue = unref(givenValue);
    if (compareValue && greaterValue) {
      if (compareValue instanceof Date && greaterValue instanceof Date) {
        return dayjs(compareValue).isAfter(greaterValue);
      }
      return Number(compareValue) > Number(greaterValue);
    }
    return true;
  };
}
