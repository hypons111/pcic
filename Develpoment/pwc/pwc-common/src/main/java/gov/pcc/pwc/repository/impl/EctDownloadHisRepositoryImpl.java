package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EctDownloadHisRepositoryCustom;
import gov.pcc.pwc.service.criteria.ECT0101R2Criteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.odsExporter.OdsExportService;
import gov.pcc.pwc.utils.PropUtil;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

public class EctDownloadHisRepositoryImpl implements EctDownloadHisRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final OdsExportService odsExportService;

    public EctDownloadHisRepositoryImpl(SqlExecutor sqlExecutor,OdsExportService odsExportService,SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.odsExportService = odsExportService;
        this.serialNumGenerator = serialNumGenerator;
    }

    @Override
    public void doDownloadRecord(ECT0101R2Criteria criteria, HttpServletRequest request) {
        Long ectPublishId = criteria.getEctPublishId();
        String ip = getIpAddr(request);
        Query hisQry = Query
                .builder()
                .append("SELECT CREATE_TIME, ECT_PUBLISH_ID, CERTIFICATE_TYPE, CERTIFICATE_NO FROM ECT_DOWNLOAD_HIS")
                .append(" WHERE ECT_PUBLISH_ID = :ectPublishId ORDER BY CREATE_TIME DESC", ectPublishId)
                .build();
        List<EctDownloadHisDTO> hisData = sqlExecutor.queryForList(hisQry,EctDownloadHisDTO.class);
        Integer certificateType = null;
        Long certificateNo = null;
        Instant lastDownloadTime =Instant.now();
        Instant createTime = Instant.now();
        if(hisData.size() > 0){
            certificateType = hisData.get(0).getCertificateType();
            certificateNo = hisData.get(0).getCertificateNo();
            lastDownloadTime = hisData.get(0).getCreateTime();
        }else{
            Query certQry = Query
                    .builder()
                    .append("SELECT * FROM ECT_PUBLIC")
                    .append(" WHERE ECT_PUBLISH_ID = :ectPublishId ", ectPublishId)
                    .build();
            List<EctPublicDTO> cData = sqlExecutor.queryForList(certQry,EctPublicDTO.class);
            certificateType = cData.get(0).getCertificateType();
            certificateNo = cData.get(0).getCertificateNo();
        }

        Query lastQry = Query
                .builder()
                .append("SELECT MAX(ECT_DOWNLOAD_HIS_ID) AS ECT_DOWNLOAD_HIS_ID FROM ECT_DOWNLOAD_HIS")
                .build();
        List<EctDownloadHisDTO> lastData = sqlExecutor.queryForList(lastQry,EctDownloadHisDTO.class);
        long newNnId = 0;
        if(lastData.size() > 0 && lastData.get(0).getEctDownloadHisId() != null) {
            newNnId = (lastData.get(0).getEctDownloadHisId() + 1);
        }
        String cUser = criteria.getIdno() != null ? criteria.getIdno() : "";
        Query qy = Query
                .builder()
                .append("INSERT INTO ECT_DOWNLOAD_HIS (ECT_DOWNLOAD_HIS_ID, CERTIFICATE_TYPE, ECT_PUBLISH_ID, CERTIFICATE_NO, LAST_DOWNLOAD_TIME, SOURCE_IP,")
                .append("CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)")
                .append("VALUES (")
                .append(":newNnId, :certificateType, :ectPublishId, :certificateNo,", newNnId, certificateType, ectPublishId, certificateNo)
                .append(":lastDownloadTime, :sourceIp, :createUser,", Timestamp.from(lastDownloadTime), ip, cUser)
                .append(":createTime, :updateUser, :updateTime)", Timestamp.from(createTime), null, null)
                .build();
        sqlExecutor.insert(qy);
    }

    @Override
    public Map<String, Object> getECT0301(@NotNull ECT0101R2Criteria criteria) {
        Map<String, Object> rs = new HashMap<String, Object>();
        String sts = "success";

        Query dDataQry = Query
                .builder()
                .append("SELECT * FROM(SELECT TOP 100 PERCENT DH.*, PC.NAME, PC.RP_NAME, PC.IDNO, PC.COMP_BAN, CAST(PC.BIRTH_DATE AS DATE) AS BIRTH_DATE,")
                .append(" PC.SEX, PC.CLASS AS CLASSES, PC.QTCLASS_NAME, PC.CERTIFICATE_WORD, PC.APPLY_ITEM, PC.CLASSIFICATION_CNAME, PC.PRA_COMP,")
                .append(" PC.CERTIFICATE_STATE, PC.PUBLIC_YEAR_MONTH, PC.COMP_NAME FROM ECT_PUBLIC PC")
                .append(" RIGHT JOIN ECT_DOWNLOAD_HIS DH on PC.ECT_PUBLISH_ID = DH.ECT_PUBLISH_ID AND PC.CERTIFICATE_TYPE = DH.CERTIFICATE_TYPE")
                .appendWhen(criteria.getCertType().equals("1"), " WHERE PC.IDNO = :idno AND PC.CERTIFICATE_TYPE = 1", criteria.getIdno())
                .appendWhen(criteria.getCertType().equals("5"), " WHERE PC.COMP_BAN = :idno AND PC.CERTIFICATE_TYPE = 1", criteria.getIdno())
                .append(" ORDER BY PC.CLASS ASC, DH.CREATE_TIME DESC) AS A" )
                .append(" UNION SELECT * FROM(SELECT TOP 100 PERCENT DH.*, PC.NAME, PC.RP_NAME, PC.IDNO, PC.COMP_BAN, CAST(PC.BIRTH_DATE AS DATE) AS BIRTH_DATE,")
                .append(" PC.SEX, PC.CLASS AS CLASSES, PC.QTCLASS_NAME, PC.CERTIFICATE_WORD, PC.APPLY_ITEM, PC.CLASSIFICATION_CNAME, PC.PRA_COMP,")
                .append(" PC.CERTIFICATE_STATE, PC.PUBLIC_YEAR_MONTH, PC.COMP_NAME FROM ECT_PUBLIC PC")
                .append(" RIGHT JOIN ECT_DOWNLOAD_HIS DH on PC.ECT_PUBLISH_ID = DH.ECT_PUBLISH_ID AND PC.CERTIFICATE_TYPE = DH.CERTIFICATE_TYPE")
                .appendWhen(criteria.getCertType().equals("1"), " WHERE PC.IDNO = :idno AND PC.CERTIFICATE_TYPE != 1", criteria.getIdno())
                .appendWhen(criteria.getCertType().equals("5"), " WHERE PC.COMP_BAN = :idno AND PC.CERTIFICATE_TYPE != 1", criteria.getIdno())
                .append(" ORDER BY PC.CERTIFICATE_WORD ASC, DH.CREATE_TIME DESC) AS B" )
                .build();
        List<ECT0301DTO> dData = sqlExecutor.queryForList(dDataQry,ECT0301DTO.class);
        Map<String, Object> response = new HashMap<String, Object>();
        if(dData.size() > 0){
            if(criteria.getCertType().equals("1")){
                String bd = "";
                if(dData.get(0).getBirthDate() != null){
                    Date b = Date.from(dData.get(0).getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                    String tmpbd = formatter.format(b);
                    String yr = String.valueOf((Integer.parseInt(tmpbd.substring(0,4)))-1911);
                    if(yr.length() < 3){
                        yr = "0" + yr;
                    }
                    bd = yr + tmpbd.substring(4);

                }
                response.put("name", dData.get(0).getName());
                response.put("idno", dData.get(0).getIdno());
                response.put("birthDay", bd);
                response.put("sex", dData.get(0).getSex());
                List<Map<String, Object>> cert1 = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> cert2 = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> cert3 = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> cert4 = new ArrayList<Map<String, Object>>();
                int cout1 = 0;
                int cout2 = 0;
                int cout3 = 0;
                int cout4 = 0;
                List<ECT0301DTO> dData1 = getDlHisList("1", criteria);
                List<ECT0301DTO> dData2 = getDlHisList("2", criteria);
                List<ECT0301DTO> dData3 = getDlHisList("3", criteria);
                List<ECT0301DTO> dData4 = getDlHisList("4", criteria);
                for(int a = 0 ; a < dData1.size() ; a++){
                    ECT0301DTO d0 = dData1.get(a);
                    String downloadTime = "";
                    if(d0.getCreateTime() != null){
                        Date dt = Date.from(d0.getCreateTime());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        String ymd = formatter.format(dt);
                        String yy = String.valueOf(Integer.parseInt(ymd.substring(0,4)) - 1911);
                        downloadTime = yy + ymd.substring(4);
                    }
                    cout1++;
                    Map<String, Object> tmp1 = new HashMap<String, Object>();
                    tmp1.put("seqId", cout1);
                    tmp1.put("state", d0.getCertificateState());
                    tmp1.put("classes", "第" + d0.getClasses() + "期");
                    tmp1.put("qtclassName", d0.getQtclassName());
                    tmp1.put("certificateWord", d0.getCertificateWord());
                    tmp1.put("applyItem", d0.getApplyItem());
                    tmp1.put("downloadTime", downloadTime);
                    cert1.add(tmp1);
                }
                for(int b = 0 ; b < dData2.size() ; b++){
                    ECT0301DTO d0 = dData2.get(b);
                    String downloadTime = "";
                    if(d0.getCreateTime() != null){
                        Date dt = Date.from(d0.getCreateTime());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        String ymd = formatter.format(dt);
                        String yy = String.valueOf(Integer.parseInt(ymd.substring(0,4)) - 1911);
                        downloadTime = yy + ymd.substring(4);
                    }
                    cout2++;
                    Map<String, Object> tmp2 = new HashMap<String, Object>();
                    tmp2.put("seqId", cout2);
                    tmp2.put("state", d0.getCertificateState());
                    tmp2.put("classes", "第" + d0.getClasses() + "期");
                    tmp2.put("qtclassName", d0.getQtclassName());
                    tmp2.put("certificateWord", d0.getCertificateWord());
                    tmp2.put("applyItem", d0.getApplyItem());
                    tmp2.put("downloadTime", downloadTime);
                    cert2.add(tmp2);
                }
                for(int c = 0 ; c < dData3.size() ; c++){
                    ECT0301DTO d0 = dData3.get(c);
                    String downloadTime = "";
                    if(d0.getCreateTime() != null){
                        Date dt = Date.from(d0.getCreateTime());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        String ymd = formatter.format(dt);
                        String yy = String.valueOf(Integer.parseInt(ymd.substring(0,4)) - 1911);
                        downloadTime = yy + ymd.substring(4);
                    }
                    cout3++;
                    Map<String, Object> tmp3 = new HashMap<String, Object>();
                    tmp3.put("seqId", cout3);
                    tmp3.put("state", d0.getCertificateState());
                    tmp3.put("publicYearMonth", d0.getPublicYearMonth());
                    tmp3.put("classificationCname", d0.getClassificationCname());
                    tmp3.put("certificateWord", d0.getCertificateWord());
                    tmp3.put("applyItem", d0.getApplyItem());
                    tmp3.put("downloadTime", downloadTime);
                    cert3.add(tmp3);
                }
                for(int d = 0 ; d < dData4.size() ; d++){
                    ECT0301DTO d0 = dData4.get(d);
                    String downloadTime = "";
                    if(d0.getCreateTime() != null){
                        Date dt = Date.from(d0.getCreateTime());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        String ymd = formatter.format(dt);
                        String yy = String.valueOf(Integer.parseInt(ymd.substring(0,4)) - 1911);
                        downloadTime = yy + ymd.substring(4);
                    }
                    cout4++;
                    Map<String, Object> tmp4 = new HashMap<String, Object>();
                    tmp4.put("seqId", cout4);
                    tmp4.put("state", d0.getCertificateState());
                    tmp4.put("publicYearMonth", d0.getPublicYearMonth());
                    tmp4.put("praComp", d0.getPraComp());
                    tmp4.put("certificateWord", d0.getCertificateWord());
                    tmp4.put("applyItem", d0.getApplyItem());
                    tmp4.put("downloadTime", downloadTime);
                    cert4.add(tmp4);
                }

                response.put("cert1", cert1);
                response.put("cert2", cert2);
                response.put("cert3", cert3);
                response.put("cert4", cert4);
            }else{
                response.put("name", dData.get(0).getRpName());
                response.put("idno", dData.get(0).getCompBan());
                response.put("company", dData.get(0).getCompName());
                List<Map<String, Object>> cert5 = new ArrayList<Map<String, Object>>();
                int cout5 = 0;
                List<ECT0301DTO> dData5 = getDlHisList("5", criteria);
                for(int e = 0 ; e < dData5.size() ; e++){
                    ECT0301DTO d0 = dData5.get(e);
                    String downloadTime = "";
                    if(d0.getCreateTime() != null){
                        Date dt = Date.from(d0.getCreateTime());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String ymd = formatter.format(dt);
                        String yy = String.valueOf(Integer.parseInt(ymd.substring(0,4)) - 1911);
                        downloadTime = yy + ymd.substring(4);
                    }
                    cout5++;
                    Map<String, Object> tmp5 = new HashMap<String, Object>();
                    tmp5.put("seqId", cout5);
                    tmp5.put("state", d0.getCertificateState());
                    tmp5.put("publicYearMonth", d0.getPublicYearMonth());
                    tmp5.put("compName", d0.getCompName());
                    tmp5.put("certificateWord", d0.getCertificateWord());
                    tmp5.put("applyItem", d0.getApplyItem());
                    tmp5.put("downloadTime", downloadTime);
                    cert5.add(tmp5);
                }
                response.put("cert5", cert5);
            }
            rs.put("status", sts);
            rs.put("response", response);
        }else{
            if(criteria.getCertType().equals("1")){
                response.put("name", "");
                response.put("idno", "");
                response.put("birthDay", "");
                response.put("sex", "");
                List<Map<String, Object>> cert1 = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> cert2 = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> cert3 = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> cert4 = new ArrayList<Map<String, Object>>();
                response.put("cert1", cert1);
                response.put("cert2", cert2);
                response.put("cert3", cert3);
                response.put("cert4", cert4);
            }else{
                response.put("name", "");
                response.put("idno", "");
                response.put("company", "");
                List<Map<String, Object>> cert5 = new ArrayList<Map<String, Object>>();
                response.put("cert5", cert5);
            }
            sts = "faild";
            rs.put("status", sts);
            rs.put("response", response);
        }
        return rs;
    }

    @Override
    public Map<String, Object> getDownloadFile(MultipartHttpServletRequest request){
        Map<String, Object> rs = new HashMap<String, Object>();
        if(!PropUtil.getPropValCheck()){
            rs.put("resultCode", "2");
            rs.put("resultMsg", "property 設定檔不存在");
            return rs;
        }
        String serviceId = request.getParameter("serviceId");
        String serviceToken = request.getParameter("serviceToken");
        String certificateNo = request.getParameter("certificateNo");
        String certificateWord = request.getParameter("certificateWord");
        String applyItem = request.getParameter("applyItem");
        String idno = request.getParameter("idNo");
        String apiCallNo = genApiCallNo();
        rs.put("apiCallNo", apiCallNo);
        rs.put("serviceId", serviceId);
        if(!isValidService(serviceId, serviceToken)){
            rs.put("resultCode", "9");
            rs.put("resultMsg", "服務權杖認證結果無權限");
            return rs;
        }
        int certId = Integer.parseInt(String.valueOf(request.getParameter("ectId")));
        Query dDataQry = Query
                .builder()
                .append("SELECT * FROM ECT_PUBLIC WHERE ECT_PUBLISH_ID = :ECT_PUBLISH_ID", certId)
                .append("AND CERTIFICATE_STATE = 1 AND IS_VALID = 1")
                .build();
        List<EctPublicDTO> dData = sqlExecutor.queryForList(dDataQry,EctPublicDTO.class);
        if(dData.size() > 0 && dData.get(0) != null){
            String fileCreateDate = "", ymd = "";
            if(dData.get(0).getCreateTime() != null && dData.get(0).getFileName() != null && dData.get(0).getCertificateType() != null){
                Date b = Date.from(dData.get(0).getCreateTime());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                fileCreateDate = formatter.format(b);
                ymd = fileCreateDate.substring(0,10);
                String ct = String.valueOf(dData.get(0).getCertificateType());
                String fn = String.valueOf(dData.get(0).getFileName());
                String downloadFilePath = PropUtil.getPropVal("ect.cer.savepath") + ct + "/" + ymd + "/" + fn;
                rs.put("path", downloadFilePath);
                rs.put("fileName", fn);
                rs.put("resultCode", "1");
                rs.put("resultMsg", "");
            }else{
                rs.put("resultCode", "0");
                rs.put("resultMsg", "查無資料");
            }
        }else{
            rs.put("resultCode", "2");
            rs.put("resultMsg", "失敗");
        }
        return rs;
    }

    protected List<ECT0301DTO> getDlHisList(String certType, ECT0101R2Criteria criteria){
        if(certType.equals("1")){
            Query dDataQry = Query
                    .builder()
                    .append("SELECT ROW_NUMBER() OVER(")
                    .append( " ORDER BY PC.CLASS ASC, DH.CREATE_TIME DESC" )
                    .append(") AS seqId, DH.*, PC.NAME, PC.RP_NAME, PC.IDNO, PC.COMP_BAN, CAST(PC.BIRTH_DATE AS DATE) AS BIRTH_DATE,")
                    .append(" PC.SEX, PC.CLASS AS CLASSES, PC.QTCLASS_NAME, PC.CERTIFICATE_WORD, PC.APPLY_ITEM, PC.CLASSIFICATION_CNAME, PC.PRA_COMP,")
                    .append(" PC.CERTIFICATE_STATE, PC.PUBLIC_YEAR_MONTH, PC.COMP_NAME FROM ECT_DOWNLOAD_HIS DH")
                    .append(" LEFT JOIN ECT_PUBLIC PC ON PC.ECT_PUBLISH_ID = DH.ECT_PUBLISH_ID AND PC.CERTIFICATE_TYPE = DH.CERTIFICATE_TYPE")
                    .append( " WHERE PC.IDNO = :idno AND PC.CERTIFICATE_TYPE = :certType", criteria.getIdno(), Integer.parseInt(certType))
                    .append( " ORDER BY PC.CLASS ASC, DH.CREATE_TIME DESC" )
                    .build();
            return sqlExecutor.queryForList(dDataQry,ECT0301DTO.class);
        }else if(certType.equals("5")){
            Query dDataQry = Query
                    .builder()
                    .append("SELECT ROW_NUMBER() OVER(")
                    .append(" ORDER BY PC.CERTIFICATE_WORD ASC, DH.CREATE_TIME DESC" )
                    .append(") AS seqId, DH.*, PC.NAME, PC.RP_NAME, PC.IDNO, PC.COMP_BAN, CAST(PC.BIRTH_DATE AS DATE) AS BIRTH_DATE,")
                    .append(" PC.SEX, PC.CLASS AS CLASSES, PC.QTCLASS_NAME, PC.CERTIFICATE_WORD, PC.APPLY_ITEM, PC.CLASSIFICATION_CNAME, PC.PRA_COMP,")
                    .append(" PC.CERTIFICATE_STATE, PC.PUBLIC_YEAR_MONTH, PC.COMP_NAME FROM ECT_DOWNLOAD_HIS DH")
                    .append(" LEFT JOIN ECT_PUBLIC PC ON PC.ECT_PUBLISH_ID = DH.ECT_PUBLISH_ID AND PC.CERTIFICATE_TYPE = DH.CERTIFICATE_TYPE")
                    .append(" WHERE PC.COMP_BAN = :idno AND PC.CERTIFICATE_TYPE = 5", criteria.getIdno())
                    .append(" ORDER BY PC.CERTIFICATE_WORD ASC, DH.CREATE_TIME DESC" )
                    .build();
            return sqlExecutor.queryForList(dDataQry,ECT0301DTO.class);
        }else{
            Query dDataQry = Query
                    .builder()
                    .append("SELECT ROW_NUMBER() OVER(")
                    .append(" ORDER BY PC.CERTIFICATE_WORD ASC, DH.CREATE_TIME DESC" )
                    .append(") AS seqId, DH.*, PC.NAME, PC.RP_NAME, PC.IDNO, PC.COMP_BAN, CAST(PC.BIRTH_DATE AS DATE) AS BIRTH_DATE,")
                    .append(" PC.SEX, PC.CLASS AS CLASSES, PC.QTCLASS_NAME, PC.CERTIFICATE_WORD, PC.APPLY_ITEM, PC.CLASSIFICATION_CNAME, PC.PRA_COMP,")
                    .append(" PC.CERTIFICATE_STATE, PC.PUBLIC_YEAR_MONTH, PC.COMP_NAME FROM ECT_DOWNLOAD_HIS DH")
                    .append(" LEFT JOIN ECT_PUBLIC PC ON PC.ECT_PUBLISH_ID = DH.ECT_PUBLISH_ID AND PC.CERTIFICATE_TYPE = DH.CERTIFICATE_TYPE")
                    .append(" WHERE PC.IDNO = :idno AND PC.CERTIFICATE_TYPE = :certType", criteria.getIdno(), Integer.parseInt(certType))
                    .append(" ORDER BY PC.CERTIFICATE_WORD ASC, DH.CREATE_TIME DESC" )
                    .build();
            return sqlExecutor.queryForList(dDataQry,ECT0301DTO.class);
        }
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected boolean isValidService(String serviceId, String serviceToken){
        String service_Id = "", service_Token = "";
        Query qsys1 = Query
                .builder()
                .append("SELECT SYS_VALUE AS serviceId FROM PWC_DB.DBO.ADM_SYS_PARAMETER WHERE SYS_TYPE ='ECT' AND SYS_NAME = 'API_SERVICE_ID' AND STATUS='Y'")
                .build();
        List<EctSysParamDTO> sys1 = sqlExecutor.queryForList(qsys1,EctSysParamDTO.class);
        if(sys1.size() > 0 && String.valueOf(sys1.get(0).getServiceId()) != ""){
            service_Id = String.valueOf(sys1.get(0).getServiceId());
        }
        Query qsys2 = Query
                .builder()
                .append("SELECT SYS_VALUE AS serviceToken FROM PWC_DB.DBO.ADM_SYS_PARAMETER WHERE SYS_TYPE ='ECT' AND SYS_NAME = 'API_SERVICE_TOKEN' AND STATUS='Y'")
                .build();
        List<EctSysParamDTO> sys2 = sqlExecutor.queryForList(qsys2,EctSysParamDTO.class);
        if(sys2.size() > 0 && String.valueOf(sys2.get(0).getServiceId()) != ""){
            service_Token = String.valueOf(sys2.get(0).getServiceToken());
        }

        if(serviceId.equals(service_Id) && serviceToken.equals(service_Token)){
            return true;
        }
        return false;
    }

    private String genApiCallNo(){
        String ml = String.valueOf(System.currentTimeMillis());
        String rtn = ml.substring((ml.length()-8), (ml.length()));
        System.out.println(rtn);
        int year = (Calendar.getInstance().get(Calendar.YEAR));
        String myr = String.valueOf((year-1911));
        return (myr+rtn);
    }
}
