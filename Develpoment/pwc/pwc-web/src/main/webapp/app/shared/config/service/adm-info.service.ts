import axios from "axios";
import { Store } from "vuex";

export default class AdmInfoService {
  constructor(private store: Store<any>) {
    this.getAdmSysCode();
    this.getAdmPostAddr();
  }

  private getAdmSysCode() {
    axios
      .get('api/service/adm-sys-codes')
      .then(res => {
        this.store.dispatch('handleAdmSysCode', res.data);
      })
      .catch(err => console.log(err));
  }

  private getAdmPostAddr() {
    axios
      .get('api/service/adm-post-addrs')
      .then(res => {
        this.store.dispatch('handleAdmPostAddr', res.data);
      })
      .catch(err => console.log(err));
  }
}