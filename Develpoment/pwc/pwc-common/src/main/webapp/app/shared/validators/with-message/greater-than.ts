import greaterThan from '@/shared/validators/raw/greater-than';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { Ref } from 'vue-demi';

/**
 * Check if a value is greater than given value.
 * @param {Date | Number | Ref<Date> | Ref<Number>} givenValue
 * @returns {NormalizedValidator}
 */
export default function (givenValue: Date | number | Ref<Date> | Ref<number>, isEqual: boolean = false) {
  return {
    $validator: greaterThan(givenValue, isEqual),
    $message: ({ $params }: any) => {
      let value = $params.givenValue;
      if ($params.givenValue instanceof Date) {
        value = formatDate($params.givenValue, '/');
      }
      return isEqual ? `輸入的值必須大於或等於 ${value}` : `輸入的值必須大於 ${value}`;
    },
    $params: { givenValue },
  };
}
