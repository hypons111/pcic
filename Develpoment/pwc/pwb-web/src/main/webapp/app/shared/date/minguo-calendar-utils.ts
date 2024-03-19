import { computed, ComputedRef, reactive } from '@vue/composition-api';
import { Ref, isRef } from 'vue-demi';

export function computedDate(value: Date | Ref<Date>): ComputedRef<string> {
  return computed({
    get: () => formatDate(value),
    set: (text: string) => parseDate(text, value),
  });
}

export function computedDateReactive(state: any, field: string): ComputedRef<string> {
  return computed({
    get: () => formatDate(state[field]),
    set: (text: string) => parseDate(text, state, field),
  });
}

export function formatDate(value: Ref<Date> | Date | null, delimiter?: string): string {
  const date: Date | null = unwrap(value);
  if (date) {
    const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    if (delimiter) {
      return `${year}${delimiter}${month}${delimiter}${day}`;
    }
    return `${year}${month}${day}`;
  }
  return '';
}

export function formatTime(value: Ref<Date> | Date | null, delimiter?: string): string {
  const date: Date | null = unwrap(value);
  if (date) {
    return new Intl.DateTimeFormat('en', {
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
      hour12: false,
    }).format(date);
  }
  return '';
}

export function formatDateTime(value: Ref<Date> | Date | null, delimiter?: string): string {
  const date: Date | null = unwrap(value);
  if (date) {
    return (
      formatDate(value, delimiter) +
      ' ' +
      new Intl.DateTimeFormat('en', {
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false,
      }).format(date)
    );
  }
  return '';
}

export function parseDate(text: string, reference: Ref<Date> | any, field?: string): void {
  let date = null;
  let textString = text;
  if (text.indexOf('/') > 0) {
    textString = text.replace('/', '').replace('/', '');
  }
  if (textString.length === 7) {
    const year = textString.substring(0, 3);
    const month = textString.substring(3, 5);
    const day = textString.substring(5);
    date = new Date(Number(year) + 1911, Number(month) - 1, Number(day));
  }
  if (isRef(reference)) {
    reference.value = date;
  } else if (field) {
    reference[field] = date;
  } else {
    throw new Error('Neither reference is a Ref, nor field is given.');
  }
}

export function parseRocDate(text: string, delimiter?: string): Date {
  if (!delimiter) {
    delimiter = '/';
  }
  const dateString = formatMinguoDate(text, delimiter).replace('/', '').replace('/', '');
  const year = dateString.substring(0, 4);
  const month = dateString.substring(4, 6);
  const day = dateString.substring(6);
  return new Date(Number(year), Number(month) - 1, Number(day));
}

export function dateFormatter(value: Date | Ref<Date>, key: any, item: any) {
  return formatDate(value, '/');
}

export function dateTimeFormatter(value: Date | Ref<Date>, key: any, item: any) {
  return formatDateTime(value, '/');
}

export function useMinguoDate() {
  // @ts-ignore
  const state = reactive({
    date: null,
    dateText: computed({
      get: (): string => formatDate(state.date),
      set: (text: string): void => parseDate(text, state, 'date'),
    }),
  });
  return state;
}

export function formatMinguoDate(value: Ref<string> | string | null, delimiter?: string) {
  let date: string | null;
  if (isRef(value)) {
    date = value.value;
  } else {
    date = value;
  }
  if (!delimiter) {
    delimiter = '/';
  }
  if (date) {
    const dateArray = date.split(delimiter);
    if (dateArray.length === 1) {
      dateArray[0] = date.substr(0, 3);
      dateArray[1] = date.substr(3, 2);
      dateArray[2] = date.substr(5, 2);
    }
    const year = (Number(dateArray[0]) + 1911).toString().padStart(4, '0');
    const month = dateArray[1].toString().padStart(2, '0');
    const day = dateArray[2].toString().padStart(2, '0');
    return `${year}${delimiter}${month}${delimiter}${day}`;
  }
  return '';
}

function unwrap(value: Ref<Date> | Date | null): Date | null {
  let date: Date | null;
  if (isRef(value)) {
    date = value.value;
  } else {
    date = value;
  }
  return date;
}
