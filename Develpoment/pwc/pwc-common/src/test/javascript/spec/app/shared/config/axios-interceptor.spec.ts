import * as setupAxiosConfig from '@/shared/config/axios-interceptor';
import { SERVER_API_URL } from '@/constants';

jest.mock('@u3u/vue-hooks', () => {
  return {
    useStore: jest.fn(() => {
      return {
        value: {
          getters: {
            currentFunctionId: 'STS915W',
          },
        },
      };
    }),
  };
});

describe('Axios interceptor', () => {
  it('should use localStorage to provide bearer', () => {
    localStorage.setItem('jhi-authenticationToken', 'auth');
    const result = setupAxiosConfig.onRequestSuccess(() => console.log('A problem occured'));

    expect(result.headers.Authorization).toBe('Bearer auth');
    expect(result.url.indexOf(SERVER_API_URL)).toBeGreaterThan(-1);
  });

  it('should use sessionStorage to provide bearer', () => {
    sessionStorage.setItem('jhi-authenticationToken', 'auth');
    const result = setupAxiosConfig.onRequestSuccess(() => console.log('A problem occured'));

    expect(result.headers.Authorization).toBe('Bearer auth');
    expect(result.url.indexOf(SERVER_API_URL)).toBeGreaterThan(-1);
  });
});
