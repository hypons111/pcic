import axios from 'axios';

const baseApiUrl = 'adm-agents';

export default class AdmAgentService {
  public findAllwithOrgIdAndOrgNameByKeyWord(keyWord: string): Promise<any> {
    const config = { headers: { Accept: 'application/json', 'Content-Type': 'application/json' } };
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/orgId-and-orgName/keyWord`, { keyWord: keyWord }, config)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
