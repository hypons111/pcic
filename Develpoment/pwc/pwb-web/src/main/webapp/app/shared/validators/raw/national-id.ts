import { helpers } from '@vuelidate/validators';

export default function (idno: string): boolean {
  if (!helpers.req(idno)) {
    return true;
  }

  idno = idno.replace(/\s+/g, '');
  if (!/^[A-Z, a-z]{1}[1-2]{1}[0-9]{8}$/.test(idno)) {
    return false;
  }

  const locationMap = [10, 11, 12, 13, 14, 15, 16, 17, 34, 18, 19, 20, 21, 22, 35, 23, 24, 25, 26, 27, 28, 29, 32, 30, 31, 33];
  /* A, B, C, D, E, F, G, H, I, J, K, L, M,
     N, O, P, Q, R, S, T, U, V, W, X, Y, Z */
  const digit = locationMap[idno.toUpperCase().charCodeAt(0) - 'A'.charCodeAt(0)];
  let checksum = 0;
  checksum += Math.floor(digit / 10);
  checksum += (digit % 10) * 9;
  /* i: index; p: permission value */
  /* this loop sums from [1] to [8] */
  /* permission value decreases */
  for (let i = 1, p = 8; i <= 8; i++, p--) {
    checksum += parseInt(idno.charAt(i)) * p;
  }
  // add the last number
  checksum += parseInt(idno.charAt(9));
  return !(checksum % 10);
}
