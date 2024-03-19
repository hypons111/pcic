import axios from 'axios';
import { Store } from 'vuex';
import VueRouter from 'vue-router';
import { User } from '@/shared/model/user.model';
import TaxType from '@/account/tax-type';

export default class AccountService {
  constructor(private store: Store<any>, private router: VueRouter) {
    this.init();
  }

  public init(): void {
    this.retrieveProfiles();
  }

  public retrieveProfiles(): Promise<boolean> {
    return new Promise(resolve => {
      axios
        .get<any>('api/profiles', { baseURL: '' })
        .then(res => {
          if (res.data && res.data.activeProfiles) {
            this.store.commit('setRibbonOnProfiles', res.data['display-ribbon-on-profiles']);
            this.store.commit('setActiveProfiles', res.data['activeProfiles']);
          }
          resolve(true);
        })
        .catch(() => resolve(false));
    });
  }

  public retrieveAccount(): Promise<boolean> {
    return new Promise(resolve => {
      axios
        .get<any>('account')
        .then(response => {
          this.store.commit('authenticate');
          const account = response.data;
          if (account) {
            account.taxType = TaxType.of(account.taxType);
            this.store.commit('authenticated', account);
            if (sessionStorage.getItem('requested-url')) {
              const url = sessionStorage.getItem('requested-url') === '/login' ? '/' : sessionStorage.getItem('requested-url');
              this.router.replace(url);
              sessionStorage.removeItem('requested-url');
            }
          } else {
            this.store.commit('logout');
            this.router.push('/', () => {});
            sessionStorage.removeItem('requested-url');
          }
          resolve(true);
        })
        .catch(() => {
          this.store.commit('logout');
          resolve(false);
        });
    });
  }

  public hasAnyAuthorityAndCheckAuth(authorities: any): Promise<boolean> {
    return this.checkAuth().then(value => {
      if (value) {
        return this.hasAnyAuthority(authorities);
      } else {
        return Promise.resolve(false);
      }
    });
  }

  public checkAuth(): Promise<boolean> {
    if (!this.authenticated || !this.userAuthorities) {
      const token = localStorage.getItem('jhi-authenticationToken') || sessionStorage.getItem('jhi-authenticationToken');
      if (!this.store.getters.account && !this.store.getters.logon && token) {
        return this.retrieveAccount();
      } else {
        return new Promise(resolve => {
          resolve(false);
        });
      }
    }
    return Promise.resolve(true);
  }

  public hasAnyAuthority(authorities: any): Promise<boolean> {
    if (typeof authorities === 'string') {
      authorities = [authorities];
    }

    for (let i = 0; i < authorities.length; i++) {
      if (this.userAuthorities.includes(authorities[i])) {
        return new Promise(resolve => {
          resolve(true);
        });
      }
    }

    return new Promise(resolve => {
      resolve(false);
    });
  }

  public get authenticated(): boolean {
    return this.store.getters.authenticated;
  }

  /**
   * 取得使用者物件。
   * <p>
   * 如果還沒有認證通過，可能會是 {@code null}。
   */
  public get user(): User | null {
    return this.store.getters.account;
  }

  public get userAuthorities(): any {
    return this.store.getters.account.authorities;
  }

  public set currentFunctionId(functionId: string) {
    this.store.commit('setCurrentFunctionId', functionId);
  }

  public get currentFunctionId(): string {
    return this.store.getters.account.currentFunctionId;
  }
}
