import { unref } from 'vue-demi';
import dayjs from 'dayjs';

/**
 * Check if value is greater than given value.
 * @param {*} givenValue
 * @return {function(*=): boolean}
 */
export default function (givenValue: any, isEqual: boolean = false) {
  return (value: any) => {
    const compareValue = unref(value);
    const greaterValue = unref(givenValue);
    if (compareValue && greaterValue) {
      if (compareValue instanceof Date && greaterValue instanceof Date) {
        return isEqual ? dayjs(compareValue).isAfter(greaterValue) || dayjs(compareValue).isSame(greaterValue) : dayjs(compareValue).isAfter(greaterValue);
      }
      return isEqual ? Number(compareValue) > Number(greaterValue) || Number(compareValue) == Number(greaterValue) : Number(compareValue) > Number(greaterValue);
    }
    return true;
  };
}
