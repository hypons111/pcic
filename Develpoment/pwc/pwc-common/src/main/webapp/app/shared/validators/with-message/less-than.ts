import lessThan from '@/shared/validators/raw/less-than';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { Ref } from 'vue-demi';

/**
 * Check if a value is greater than given value.
 * @param {Date | Number | Ref<Date> | Ref<Number>} givenValue
 * @returns {NormalizedValidator}
 */
export default function (givenValue: Date | number | Ref<Date> | Ref<number>) {
  return {
    $validator: lessThan(givenValue),
    $message: ({ $params }: any) => {
      let value = $params.givenValue;
      if ($params.givenValue instanceof Date) {
        value = formatDate($params.givenValue);
      }
      return `輸入的值必須小於 ${value}`;
    },
    $params: { givenValue },
  };
}
