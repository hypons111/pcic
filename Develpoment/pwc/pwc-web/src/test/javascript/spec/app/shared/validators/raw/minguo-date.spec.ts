import { minguoDate } from '@/shared/validators/raw/minguo-date';
import customParseFormat from 'dayjs/plugin/customParseFormat';
import dayjs from 'dayjs';
import { ref } from '@vue/composition-api';

describe('Minguo date validator', () => {
  beforeAll(() => {
    dayjs.extend(customParseFormat);
  });

  it('should accept 1000101', () => {
    expect(minguoDate(ref('1000101'))).toBe(true);
  });

  it('should accept 1000101', () => {
    expect(minguoDate('1000101')).toBe(true);
  });

  it('should accept 1090229', () => {
    expect(minguoDate('1090229')).toBe(true);
  });

  it('should invalidate 1100229', () => {
    expect(minguoDate('1100229')).toBe(false);
  });

  it('should reject 1101131', () => {
    expect(minguoDate('1101131')).toBe(false);
  });

  it('should accept undefined', () => {
    expect(minguoDate(undefined)).toBe(true);
  });

  it('should accept null', () => {
    expect(minguoDate(null)).toBe(true);
  });

  it('should accept empty string', () => {
    expect(minguoDate('')).toBe(true);
  });

  it('should reject 000 as year', () => {
    expect(minguoDate('0000101')).toBe(false);
  });

  it('should reject negative year value', () => {
    expect(minguoDate('-0010101')).toBe(false);
  });
});
