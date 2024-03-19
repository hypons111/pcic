import { ErrorStatus } from '@/shared/http/error-status';

import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import { notificationErrorHandler, resolveHttpErrorMessage } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';

describe('HTTP response helper', () => {
  let mock;
  let notificationService: NotificationService;
  let problem;
  beforeAll(() => {
    mock = new MockAdapter(axios);
    notificationService = new NotificationService(null);
    problem = {
      detail: 'test error',
      message: 'test error',
      path: '/test',
      status: 400,
      title: 'test error',
      type: 'https://www.jhipster.tech/problem/problem-with-code',
      level: 'ERROR',
    };
  });
  afterEach(() => {
    mock.reset();
  });

  it('should resolve correct message', () => {
    mock.onGet(`/test`).reply(400, problem);
    axios
      .get('/test')
      .then(data => {
        console.log(data);
      })
      .catch(error => {
        const resolved: ErrorStatus = resolveHttpErrorMessage(error);
        expect(resolved.level).toBe(problem.level);
        expect(resolved.message).toBe(problem.message);
      });
  });

  it('should call notificationService.danger', () => {
    mock.onGet(`/test`).reply(400, problem);
    const notificationSpy = jest.spyOn(notificationService, 'danger').mockReturnValue();
    axios
      .get('/test')
      .then(data => {
        console.log(data);
      })
      .catch(error => {
        const callback = notificationErrorHandler(notificationService);
        callback(error);
        expect(notificationSpy).toBeCalledTimes(1);
      });
  });

  it('should call notificationService.danger twice', () => {
    mock.onGet(`/test`).reply(400, problem);
    const notificationSpy = jest.spyOn(notificationService, 'danger').mockReturnValue();

    const request1 = axios.get('/test');
    const request2 = axios.get('/test');

    axios
      .all([request1, request2])
      .then(() => {})
      .catch(errors => {
        const callback = notificationErrorHandler(notificationService);
        callback(errors);
        expect(notificationSpy).toBeCalledTimes(2);
      });
  });
});
