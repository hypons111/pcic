import { AxiosError } from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { Problem } from '@/shared/http/problem';
import { ErrorStatus } from './error-status';

const MESSAGE_CODE_TYPE = 'https://www.jhipster.tech/problem/problem-with-code';
const CONSTRAINT_VIOLATION_TYPE = 'https://www.jhipster.tech/problem/constraint-violation';

export function notificationErrorHandler(notificationService: NotificationService) {
  return (error: AxiosError | AxiosError[]) => {
    let errors: AxiosError[];
    if (Array.isArray(error)) {
      errors = error;
    } else {
      errors = [];
      errors.push(error);
    }

    errors.forEach(value => {
      const statusCode = resolveHttpErrorMessage(value);
      const message = statusCode.message;
      if (statusCode.level === 'ERROR') {
        notificationService.danger(message);
      } else if (statusCode.level === 'WARN') {
        notificationService.warning(message);
      } else if (statusCode.level === 'INFO') {
        notificationService.info(message);
      } else if (statusCode.level === 'SUCCESS') {
        notificationService.success(message);
      }
    });
  };
}
export function notificationErrorHandlerWithErrorParam(error: AxiosError | AxiosError[], notificationService: NotificationService) {
  let errors: AxiosError[];
  if (Array.isArray(error)) {
    errors = error;
  } else {
    errors = [];
    errors.push(error);
  }

  errors.forEach(value => {
    const statusCode = resolveHttpErrorMessage(value);
    const message = statusCode.message;
    if (statusCode.level === 'ERROR') {
      notificationService.danger(message);
    } else if (statusCode.level === 'WARN') {
      notificationService.warning(message);
    } else if (statusCode.level === 'INFO') {
      notificationService.info(message);
    } else if (statusCode.level === 'SUCCESS') {
      notificationService.success(message);
    }
  });
}

export function resolveHttpErrorMessage(error: AxiosError): ErrorStatus {
  let problem: Problem = error.response.data;
  if (error.response) {
    if (error.response.data instanceof ArrayBuffer) {
      const responseString = new TextDecoder('UTF-8').decode(error.response.data);
      problem = JSON.parse(responseString);
    } else {
      problem = error.response.data;
    }
    return resolveMessage(problem);
  }
}

function resolveMessage(problem: Problem): ErrorStatus {
  let message;
  if (problem.type === MESSAGE_CODE_TYPE) {
    return {
      code: problem.title,
      message: problem.detail,
      level: problem.level || 'ERROR',
    };
  } else if (problem.type === CONSTRAINT_VIOLATION_TYPE) {
    // TODO: extract more detail information
    return {
      message: '資料有誤，請修正後再重試',
      level: 'ERROR',
    };
  } else {
    if (problem.path) {
      message = `與伺服器 (${problem.path}) 溝通時發生錯誤 (${problem.status})，請稍後再試。如果問題一直無法改善，請洽系統管理人員。`;
    } else {
      message = `與伺服器溝通時發生錯誤 (${problem.status})，請稍後再試。如果問題一直無法改善，請洽系統管理人員。`;
    }
    return {
      message: message,
      level: 'ERROR',
    };
  }
}
