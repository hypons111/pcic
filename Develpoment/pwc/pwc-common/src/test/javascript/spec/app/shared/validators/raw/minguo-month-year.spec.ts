import { minguoYearMonth } from '@/shared/validators/raw/mingou-year-month';

describe('Minguo year month validator', () => {
  it('should validate 10001', () => {
    expect(minguoYearMonth('10001')).toBe(true);
  });

  it('should validate 00212', () => {
    expect(minguoYearMonth('00212')).toBe(true);
  });

  it('should invalidate 11013', () => {
    expect(minguoYearMonth('11013')).toBe(false);
  });

  it('should invalidate 110', () => {
    expect(minguoYearMonth('110')).toBe(false);
  });

  it('should invalidate 00001', () => {
    expect(minguoYearMonth('00001')).toBe(false);
  });

  it('should validate undefined', () => {
    expect(minguoYearMonth(undefined)).toBe(true);
  });

  it('should validate null', () => {
    expect(minguoYearMonth(null)).toBe(true);
  });

  it('should validate empty string', () => {
    expect(minguoYearMonth('')).toBe(true);
  });
});
