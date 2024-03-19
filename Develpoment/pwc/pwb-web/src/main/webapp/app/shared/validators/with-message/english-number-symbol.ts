import englishNumberSymbol from '@/shared/validators/raw/english-number-symbol';

/**
 * Check if input is valid.
 * @param {*} givenValue
 * @return {function(*=): boolean}
 */

export default function (givenValue: any) {
  return {
    $validator: englishNumberSymbol(givenValue),
    $message: '請輸入正確格式(英文、數字、符號)。',
    $params: { givenValue },
  };
}
