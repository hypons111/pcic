package gov.pcc.pwc.repository;

import gov.pcc.pwc.db.SqlExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Data SQL repository for the AdmUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public class CorsRepository  {
    private final SqlExecutor sqlExecutor;

    private RestTemplate restTemplate = new RestTemplate();

    public CorsRepository(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

        public String findAllParkingSpace() {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity(null, headers);
            ResponseEntity response = restTemplate.exchange(
                    "https://data.ntpc.gov.tw/api/datasets/54A507C4-C038-41B5-BF60-BBECB9D052C6/json",
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            int code = response.getStatusCodeValue();
            if (code == 200) {
                return response.getBody().toString();
            } else{
                return "fail";
            }
        }

}
