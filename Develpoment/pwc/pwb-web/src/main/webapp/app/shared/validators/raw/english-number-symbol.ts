/**
 * Check if input is valid.
 * @param {*} givenValue
 * @return {function(*=): boolean}
 */

export default function (givenValue: any) {
  return (value: any) => {
    const regex = new RegExp(/^[a-zA-Z\d\s-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/\\]*$/i, 'g');
    if (givenValue.value == '') {
      return true;
    }
    if (givenValue) {
      return regex.test(value);
    } else {
      return true;
    }
  };
}
