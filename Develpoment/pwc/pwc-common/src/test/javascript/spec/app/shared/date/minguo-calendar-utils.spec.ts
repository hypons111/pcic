import { formatDate, formatDateTime, parseDate, useMinguoDate } from '@/shared/date/minguo-calendar-utils';
import { ref } from '@vue/composition-api';

describe('Minguo calendar utils', () => {
  it('should be 1100101', () => {
    const date = new Date(2021, 0, 1);
    const formatString = '1100101';
    expect(formatDate(date)).toBe(formatString);
  });

  it('should be 1100101', () => {
    const date = new Date(2021, 0, 1);
    const formatString = '110/01/01';
    expect(formatDate(date, '/')).toBe(formatString);
  });

  it('should be 110/01/01 10:10:10', () => {
    const date = new Date(2021, 0, 1, 10, 10, 10);
    const formatString = '110/01/01 10:10:10';
    expect(formatDateTime(date, '/')).toBe(formatString);
  });

  it('should be date of 20210101', () => {
    const parsedDate = new Date(2021, 0, 1);
    const formatString = '110/01/01';
    const date = ref<Date>(null);
    parseDate(formatString, date);
    expect(date.value.getFullYear()).toBe(parsedDate.getFullYear());
    expect(date.value.getMonth()).toBe(parsedDate.getMonth());
    expect(date.value.getDate()).toBe(parsedDate.getDate());
  });

  it('should be reactive', () => {
    const state = useMinguoDate();

    state.dateText = '1100101';

    expect(state.date.getFullYear()).toBe(2021);
    expect(state.date.getMonth()).toBe(0);
    expect(state.date.getDate()).toBe(1);
  });
});
