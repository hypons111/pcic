package gov.pcc.pwc.service;

import com.microsoft.sqlserver.jdbc.StringUtils;
import gov.pcc.pwc.domain.AdmAgent;
import gov.pcc.pwc.utils.PccOrgIdUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class OpenDataService {
    private static final Logger log = LoggerFactory.getLogger(OpenDataService.class);
    private static final String ORG_LIST_OPEN_DATA_URL = "https://svrorg.dgpa.gov.tw/file/orglist.csv";

    public List<AdmAgent> parseOrgListData() throws IOException {
        String csvData = "";
        try {
            csvData = getOrgListData();
        } catch (Exception e1) {
            throw new RuntimeException("行政院所屬中央及地方機關代碼 取得失敗, 錯誤原因: " + e1.getMessage(), e1);
        }

        List<AdmAgent> orgListData = new ArrayList<>();

        String[] FILE_HEADER = { "機關代碼", "機關名稱", "機關英文名稱", "郵遞區號", "機關地址", "機關電話", "主管機關代碼", "主管機關名稱", "傳真", "機關生效日期", "機關裁撤日期", "機關層級", "裁撤註記", "新機關代碼", "新機關名稱", "新機關生效日", "舊機關代碼", "舊機關名稱" };
        CSVFormat csvFileFormat = CSVFormat.EXCEL.withHeader(FILE_HEADER);

        try (CSVParser csvFileParser = new CSVParser(new StringReader(csvData), csvFileFormat);) {
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            for (int i = 1; i < csvRecords.size()-1 ; i++) {
                CSVRecord record = csvRecords.get(i);
                String orgId = record.get("機關代碼").toUpperCase().trim(); // 人事行政總處機關代碼
                String pccOrgId = PccOrgIdUtil.convertOrgId2PccOrgId(record.get("機關代碼").toUpperCase().trim()); // 工程會機關代碼
                String orgName = record.get("機關名稱");
                String orgNameEn = record.get("機關英文名稱");
                String addr = record.get("機關地址");
                String tel = record.get("機關電話");
                String fax = null;
                String adminOrgId = null;
                String orgLevel = null;
                String cutMark = null;
                String oldOrgId = null; // 舊人事行政總處機關代碼
                String oldOrgName = null;
                try {
                    fax = record.get("傳真"); // 機關傳真
                    adminOrgId = record.get("主管機關代碼");
                    orgLevel = record.get("機關層級");
                    cutMark = record.get("裁撤註記");
                    oldOrgName = record.get("舊機關名稱");
                    oldOrgId = record.get("舊機關代碼");
                } catch (Exception e) {
                    log.debug("資料為空值，跳過處理");
                }
                // descript EXCEL裡沒有...
                // updateTime 物件內加入
                // updateUser 物件內加入

                AdmAgent agent = new AdmAgent();
                agent.setOrgId(orgId);
                agent.setPccOrgId(pccOrgId);
                agent.setOrgName(orgName);
                agent.setOrgNameEn(orgNameEn);
                agent.setAddr(addr);
                agent.setTel(tel);
                agent.setFax(fax);
                agent.setAdminOrgId(adminOrgId);
                if (StringUtils.isInteger(orgLevel)) {
                    BigDecimal orgLevelInt = new BigDecimal(orgLevel);
                    agent.setOrgLevel(orgLevelInt);
                }
                if (("是").equals(cutMark)) {
                    agent.setCutMark("Y");
                }
                agent.setOldOrgId(oldOrgId);
                agent.setOldOrgName(oldOrgName);
                agent.setUpdateTime(Instant.now());
                agent.setUpdateUser("SYS");

                orgListData.add(agent);
            }
        } catch (IOException e) {
            throw new RuntimeException("行政院所屬中央及地方機關代碼 解析失敗, 錯誤原因: " + e.getMessage(), e);
        }
        return orgListData;
    }

    public String getOrgListData() throws Exception {
        String orgListDataStr = "";
        InputStream inputStream = null;
        try (CloseableHttpClient httpClient = createHttpsClient();) {

            HttpGet httpGet = new HttpGet(ORG_LIST_OPEN_DATA_URL);
            HttpResponse httpResponse = httpClient.execute(httpGet);

            inputStream = httpResponse.getEntity().getContent();
            orgListDataStr = IOUtils.toString(inputStream, "Big5");
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return orgListDataStr;
    }

    /**
     * 解決 PKIX path building failed 問題
     *
     * @return HttpClient
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private CloseableHttpClient createHttpsClient() throws NoSuchAlgorithmException, KeyManagementException {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        } };

        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, trustAllCerts, null);

        LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(ctx);

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
        return httpclient;
    }


}
