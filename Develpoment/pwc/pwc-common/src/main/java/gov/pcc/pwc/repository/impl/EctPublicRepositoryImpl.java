package gov.pcc.pwc.repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.report.PccPdfExporter;
import gov.pcc.pwc.repository.custom.EctPublicRepositoryCustom;
import gov.pcc.pwc.service.criteria.ECT0101R2QueryCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.odsExporter.OdsExportService;
// import gov.pcc.pwc.signature.PdfSigner;
// import gov.pcc.pwc.signature.VerifySign;
import gov.pcc.pwc.utils.PropUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EctPublicRepositoryImpl implements EctPublicRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final OdsExportService odsExportService;

    public EctPublicRepositoryImpl(SqlExecutor sqlExecutor,OdsExportService odsExportService,SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.odsExportService = odsExportService;
        this.serialNumGenerator = serialNumGenerator;
    }

    static final char DBC_CHAR_START = 33;
    static final char DBC_CHAR_END = 126;
    static final char SBC_CHAR_START = 65281;
    static final char SBC_CHAR_END = 65374;
    static final int CONVERT_STEP = 65248;
    static final char SBC_SPACE = 12288;
    static final char DBC_SPACE = ' ';

    @Override
    public Map<String, Object> getECT0101R2(ECT0101R2QueryCriteria criteria) {
        Map<String, Object> rs = new HashMap<String, Object>();
        String sts = "success";
        Query uDataQry = Query
                .builder()
                .append("SELECT CLASS AS CLASSES, * FROM ECT_PUBLIC")
                .appendWhen(criteria.getCertType().equals("1"), " WHERE IDNO = :idno AND CERTIFICATE_TYPE != 5", criteria.getIdno())
                .appendWhen(criteria.getCertType().equals("5"), " WHERE COMP_BAN = :idno AND CERTIFICATE_TYPE = 5", criteria.getIdno())
                .append(" ORDER BY PUBLIC_YEAR_MONTH DESC" )
                .build();
        List<EctPublicDTO> uData = sqlExecutor.queryForList(uDataQry,EctPublicDTO.class);
        Map<String, Object> response = new HashMap<String, Object>();
        try{
            if(uData.size() > 0){
                if(criteria.getCertType().equals("1")){
                    String bd = "";
                    if(uData.get(0).getBirthDate() != null){
                        Date b = Date.from(uData.get(0).getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                        String tmpbd = formatter.format(b);
                        String yr = String.valueOf((Integer.parseInt(tmpbd.substring(0,4)))-1911);
                        if(yr.length() < 3){
                            yr = "0" + yr;
                        }
                        bd = yr + tmpbd.substring(4);
                    }
                    response.put("name", uData.get(0).getName());
                    response.put("idno", uData.get(0).getIdno());
                    response.put("birthDay", bd);
                    response.put("sex", uData.get(0).getSex());
                    List<Map<String, Object>> cert1 = new ArrayList<Map<String, Object>>();
                    List<Map<String, Object>> cert2 = new ArrayList<Map<String, Object>>();
                    List<Map<String, Object>> cert3 = new ArrayList<Map<String, Object>>();
                    List<Map<String, Object>> cert4 = new ArrayList<Map<String, Object>>();
                    int cout1 = 0;
                    int cout2 = 0;
                    int cout3 = 0;
                    int cout4 = 0;
                    for(int x = 0 ; x < uData.size() ; x++){
                        EctPublicDTO c0 = uData.get(x);
                        Integer type = c0.getCertificateType();
                        String fc = "";
                        if(c0.getFileCreateTime() != null){
                            Date b = Date.from(c0.getFileCreateTime());
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                            fc = formatter.format(b);
                        }
                        if(type == 1){
                            boolean expired = false;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String eD = parseWesternYear(String.valueOf(c0.getPublicYearMonth())).replaceAll("/", "-");
                            eD = eD.substring(0, 7) + "-01";
                            Date expD = sdf.parse((eD + " 23:59:59"));
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(expD);

                            calendar.add(Calendar.YEAR, 4);
                            calendar.add(Calendar.MONTH, 1);
                            calendar.set(Calendar.DAY_OF_MONTH, 1);
                            calendar.add(Calendar.DATE, -1);
                            Date lastDayOfMonth = calendar.getTime();

                            Date today = new Date();
                            if(lastDayOfMonth.before(today) && c0.getCertificateState() == 1){
                                expired = true;
                                if(c0.getIsValid().equals("1")){
                                    Query query = Query
                                            .builder()
                                            .append("UPDATE ECT_PUBLIC SET ")
                                            .append("IS_VALID=:isValid",3)
                                            .append("WHERE IDNO = :idno AND CERTIFICATE_NO = :etcPublicId", c0.getIdno(), c0.getEctPublishId())
                                            .build();
                                    sqlExecutor.update(query);
                                }
                            }
                            cout1++;
                            Map<String, Object> tmp1 = new HashMap<String, Object>();
                            tmp1.put("seqId", cout1);
                            tmp1.put("state", c0.getCertificateState());
                            tmp1.put("classes", "第" + c0.getClasses() + "期");
                            tmp1.put("qtclassName", c0.getQtclassName());
                            tmp1.put("certificateWord", c0.getCertificateWord());
                            tmp1.put("applyItem", c0.getApplyItem());
                            tmp1.put("fileName", c0.getFileName());
                            tmp1.put("fileCreateTime", fc);
                            if(expired){
                                tmp1.put("isValid", "3");
                            }else{
                                tmp1.put("isValid", c0.getIsValid());
                            }
                            tmp1.put("extPublishId", c0.getEctPublishId());
                            cert1.add(tmp1);
                        }else if(type == 2){
                            boolean expired = false;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String eD = parseWesternYear(String.valueOf(c0.getPublicYearMonth())).replaceAll("/", "-");
                            eD = eD.substring(0, 7) + "-01";
                            Date expD = sdf.parse((eD + " 23:59:59"));
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(expD);

                            calendar.add(Calendar.YEAR, 4);
                            calendar.add(Calendar.MONTH, 1);
                            calendar.set(Calendar.DAY_OF_MONTH, 1);
                            calendar.add(Calendar.DATE, -1);
                            Date lastDayOfMonth = calendar.getTime();

                            Date today = new Date();
                            if(lastDayOfMonth.before(today) && c0.getCertificateState() == 1){
                                expired = true;
                                if(c0.getIsValid().equals("1")){
                                    Query query = Query
                                            .builder()
                                            .append("UPDATE ECT_PUBLIC SET ")
                                            .append("IS_VALID=:isValid",3)
                                            .append("WHERE IDNO = :idno AND CERTIFICATE_NO = :etcPublicId", c0.getIdno(), c0.getEctPublishId())
                                            .build();
                                    sqlExecutor.update(query);
                                }
                            }
                            cout2++;
                            Map<String, Object> tmp2 = new HashMap<String, Object>();
                            tmp2.put("seqId", cout2);
                            tmp2.put("state", c0.getCertificateState());
                            tmp2.put("classes", "第" + c0.getClasses() + "期");
                            tmp2.put("qtclassName", c0.getQtclassName());
                            tmp2.put("certificateWord", c0.getCertificateWord());
                            tmp2.put("applyItem", c0.getApplyItem());
                            tmp2.put("fileName", c0.getFileName());
                            tmp2.put("fileCreateTime", fc);
                            if(expired){
                                tmp2.put("isValid", "3");
                            }else{
                                tmp2.put("isValid", c0.getIsValid());
                            }
                            tmp2.put("extPublishId", c0.getEctPublishId());
                            cert2.add(tmp2);
                        }else if(type == 3){
                            cout3++;
                            Map<String, Object> tmp3 = new HashMap<String, Object>();
                            tmp3.put("seqId", cout3);
                            tmp3.put("state", c0.getCertificateState());
                            tmp3.put("publicYearMonth", c0.getPublicYearMonth());
                            tmp3.put("classificationCname", c0.getClassificationCname());
                            tmp3.put("certificateWord", c0.getCertificateWord());
                            tmp3.put("applyItem", c0.getApplyItem());
                            tmp3.put("fileName", c0.getFileName());
                            tmp3.put("fileCreateTime", fc);
                            tmp3.put("isValid", c0.getIsValid());
                            tmp3.put("extPublishId", c0.getEctPublishId());
                            cert3.add(tmp3);
                        }else if(type == 4){
                            boolean expired = false;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String eD = parseWesternYear(String.valueOf(c0.getPraExpireDate())).replaceAll("/", "-");
                            Date expD = sdf.parse((eD + " 23:59:59"));
                            Date today = new Date();
                            if(expD.before(today) && c0.getCertificateState() == 1){
                                expired = true;
                                if(c0.getIsValid().equals("1")){
                                    Query query = Query
                                            .builder()
                                            .append("UPDATE ECT_PUBLIC SET ")
                                            .append("IS_VALID=:isValid",3)
                                            .append("WHERE IDNO = :idno AND CERTIFICATE_NO = :etcPublicId", c0.getIdno(), c0.getEctPublishId())
                                            .build();
                                    sqlExecutor.update(query);
                                }
                            }
                            cout4++;
                            Map<String, Object> tmp4 = new HashMap<String, Object>();
                            tmp4.put("seqId", cout4);
                            tmp4.put("state", c0.getCertificateState());
                            tmp4.put("publicYearMonth", c0.getPublicYearMonth());
                            tmp4.put("praComp", c0.getPraComp());
                            tmp4.put("certificateWord", c0.getCertificateWord());
                            tmp4.put("applyItem", c0.getApplyItem());
                            tmp4.put("fileName", c0.getFileName());
                            tmp4.put("fileCreateTime", fc);
                            if(expired){
                                tmp4.put("isValid", "3");
                            }else{
                                tmp4.put("isValid", c0.getIsValid());
                            }
                            tmp4.put("extPublishId", c0.getEctPublishId());
                            cert4.add(tmp4);
                        }
                    }
                    response.put("cert1", cert1);
                    response.put("cert2", cert2);
                    response.put("cert3", cert3);
                    response.put("cert4", cert4);
                }else{
                    response.put("name", uData.get(0).getRpName());
                    response.put("idno", uData.get(0).getCompBan());
                    response.put("company", uData.get(0).getCompName());
                    List<Map<String, Object>> cert5 = new ArrayList<Map<String, Object>>();
                    int cout5 = 0;
                    for(int x = 0 ; x < uData.size() ; x++) {
                        EctPublicDTO c0 = uData.get(x);
                        Integer type = c0.getCertificateType();
                        String fc = "";
                        if(c0.getFileCreateTime() != null){
                            Date b = Date.from(c0.getFileCreateTime());
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                            fc = formatter.format(b);
                        }
                        if (type == 5) {
                            cout5++;
                            Map<String, Object> tmp5 = new HashMap<String, Object>();
                            tmp5.put("seqId", cout5);
                            tmp5.put("state", c0.getCertificateState());
                            tmp5.put("publicYearMonth", c0.getPublicYearMonth());
                            tmp5.put("compName", c0.getCompName());
                            tmp5.put("certificateWord", c0.getCertificateWord());
                            tmp5.put("applyItem", c0.getApplyItem());
                            tmp5.put("fileName", c0.getFileName());
                            tmp5.put("fileCreateTime", fc);
                            tmp5.put("isValid", c0.getIsValid());
                            tmp5.put("extPublishId", c0.getEctPublishId());
                            cert5.add(tmp5);
                        }
                    }
                    response.put("cert5", cert5);
                }
                rs.put("status", sts);
                rs.put("response", response);
            }else{
                sts = "faild";
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
                rs.put("status", sts);
                rs.put("response", response);
            }
        }catch(Exception e){
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
            rs.put("status", sts);
            rs.put("response", response);
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Map<String, Object> applyect(MultipartHttpServletRequest request){
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
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = format.format(date);
        String savePath = ((currentDateTime.substring(0, 10)).replaceAll("-", "/"));

        if(certificateNo != null){
            if(!(certificateNo.trim()).equals("")){
                if((certificateNo.trim()).matches("\\d+")){
                    String reason = request.getParameter("reason");
                    if(reason != null && !reason.equals("")){
                        updateEctStatus(2, 2, idno, ("," + certificateNo), null, null, null, reason);
                    }else{
                        rs.put("resultCode", "2");
                        rs.put("resultMsg", "未填寫註銷原因");
                        return rs;
                    }
                }else{
                    rs.put("resultCode", "2");
                    rs.put("resultMsg", "註銷證書編號有誤");
                    return rs;
                }
            }
        }

        if(applyItem.equals("0")){
            if(checkCertExist(certificateWord)){
                rs.put("resultCode", "2");
                rs.put("resultMsg", "證書已存在, 請先註銷再重新核發");
                return rs;
            }
            String ectid = addNewCert(request);
            if(ectid != ""){
                String fnm = createPdf(request, savePath, ectid);
                if(fnm.equals("_,_")){
                    rs.put("resultCode", "2");
                    rs.put("resultMsg", "PDF產證失敗，參數有誤或電子簽章有誤");
                }else{
                    updateEctStatus(1, 1, idno, ectid, currentDateTime, fnm.split(",")[0], fnm.split(",")[1], null);
                    rs.put("resultCode", "1");
                    List<Map<String, Object>> rpdl = new ArrayList<Map<String, Object>>();
                    Map<String, Object> replayData = new HashMap<String, Object>();

                    replayData.put("certificateNo", ectid.split(",")[1]);
                    rpdl.add(replayData);
                    rs.put("replayData", rpdl);
                }
            }else{
                rs.put("resultCode", "2");
                rs.put("resultMsg", "上傳失敗");
            }
        }else if(applyItem.equals("1") || applyItem.equals("2")){
            Map<String, String> p = checkPersonStatus(idno);
            String isDead = p.get("isDead");
            String isCorrect = p.get("isCorrect");
            if(isDead.equals("N") && isCorrect.equals("Y")){




                String ectid = addNewCert(request);
                if(ectid != ""){
                    String fnm = createPdf(request, savePath, ectid);
                    if(fnm.equals("_,_")){
                        rs.put("resultCode", "2");
                        rs.put("resultMsg", "PDF產證失敗，參數有誤或電子簽章有誤");
                    }else{
                        updateEctStatus(1, 1, idno, ectid, currentDateTime, fnm.split(",")[0], fnm.split(",")[1], null);
                        rs.put("resultCode", "1");
                        List<Map<String, Object>> rpdl = new ArrayList<Map<String, Object>>();
                        Map<String, Object> replayData = new HashMap<String, Object>();

                        replayData.put("certificateNo", ectid.split(",")[1]);
                        rpdl.add(replayData);
                        rs.put("replayData", rpdl);
                    }
                }else{
                    rs.put("resultCode", "2");
                    rs.put("resultMsg", "上傳失敗");
                }
            }else{
                if(isCorrect.equals("N")){
                    rs.put("resultCode", "2");
                    rs.put("resultMsg", "資料不正確");
                }else{
                    rs.put("resultCode", "2");
                    rs.put("resultMsg", "戶政系統已註記死亡");
                }
            }
        }
        return rs;
    }

    @Override
    public Map<String, Object> queryect(MultipartHttpServletRequest request){
        Map<String, Object> rs = new HashMap<String, Object>();
        String serviceId = request.getParameter("serviceId");
        String serviceToken = request.getParameter("serviceToken");

        String certificateNo = request.getParameter("certificateNo");
        String certificateType = request.getParameter("certificateType");
        String idno = request.getParameter("idNo");
        String compBan = request.getParameter("compBan");
        String apiCallNo = genApiCallNo();
        rs.put("apiCallNo", apiCallNo);
        rs.put("serviceId", serviceId);
        if(!isValidService(serviceId, serviceToken)){
            rs.put("resultCode", "9");
            rs.put("resultMsg", "服務權杖認證結果無權限");
            return rs;
        }
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        if(certificateNo != null && certificateNo != ""){
            Query uDataQry = Query
                    .builder()
                    .append("SELECT ECT_PUBLISH_ID, CERTIFICATE_NO, APPLY_ITEM, CERTIFICATE_STATE, FILE_NAME, IS_VALID FROM ECT_PUBLIC")
                    .append("WHERE CERTIFICATE_NO = :certificateNo", certificateNo)

                    .appendWhen(idno != null && idno != "","AND  IDNO = :IDNO", idno)
                    .appendWhen(certificateType != null && certificateType != "","AND CERTIFICATE_TYPE = :certificateType", certificateType)
                    .appendWhen(compBan != null && compBan != "", "AND COMP_BAN = :COMP_BAN", compBan)
                    .build();
            List<EctPublicDTO> cData = sqlExecutor.queryForList(uDataQry,EctPublicDTO.class);
            if(cData.size() > 0 && cData.get(0) != null){
                for(int x = 0 ; x < cData.size() ; x++){
                    Map<String, Object> tmp = new HashMap<String, Object>();

                    tmp.put("certificateNo", cData.get(x).getCertificateNo());
                    tmp.put("applyItem", cData.get(x).getApplyItem());
                    tmp.put("certificateState", cData.get(x).getCertificateState());
                    tmp.put("ectFileName", cData.get(x).getFileName());
                    tmp.put("isValid", cData.get(x).getIsValid());
                    ls.add(tmp);
                }
                rs.put("resultCode","1");
                rs.put("resultMsg","成功");
            }else{
                rs.put("resultCode","0");
                rs.put("resultMsg","查無資料");
            }
        }else{
            rs.put("resultCode","2");
            rs.put("resultMsg","失敗");
        }
        rs.put("replayData",ls);
        return rs;
    }

    @Override
    public Map<String, Object> revokeect(MultipartHttpServletRequest request){
        Map<String, Object> rs = new HashMap<String, Object>();
        String serviceId = request.getParameter("serviceId");
        String serviceToken = request.getParameter("serviceToken");
        String ectId = request.getParameter("ectId");
        String certificateNo = request.getParameter("certificateNo");
        String certificateType = request.getParameter("certificateType");
        String reason = request.getParameter("reason");
        String apiCallNo = genApiCallNo();
        rs.put("apiCallNo", apiCallNo);
        rs.put("serviceId", serviceId);
        if(!isValidService(serviceId, serviceToken)){
            rs.put("resultCode", "9");
            rs.put("resultMsg", "服務權杖認證結果無權限");
            return rs;
        }
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        if(((ectId != null && ectId != "") || (certificateNo != null && certificateNo != "")) && certificateType != null){
            Query query = Query
                    .builder()
                    .append("UPDATE ECT_PUBLIC SET ")
                    .append("CERTIFICATE_STATE=:status, IS_VALID=:isValid",2, 2)
                    .appendWhen(reason != null,", REASON = :reason", reason)
                    .append("WHERE CERTIFICATE_TYPE = :certificateType",certificateType)
                    .appendWhen(ectId != null,"AND ECT_PUBLISH_ID = :ectId", ectId)
                    .appendWhen(certificateNo != null,"AND CERTIFICATE_NO = :certificateNo", certificateNo)
                    .build();
            sqlExecutor.update(query);

            Query uDataQry = Query
                    .builder()
                    .append("SELECT ECT_PUBLISH_ID, CERTIFICATE_NO, CERTIFICATE_STATE, IS_VALID FROM ECT_PUBLIC")
                    .append("WHERE CERTIFICATE_TYPE = :CERTIFICATE_TYPE", certificateType)
                    .appendWhen(ectId != null,"AND ECT_PUBLISH_ID = :ectId", ectId)
                    .appendWhen(certificateNo != null,"AND CERTIFICATE_NO = :certificateNo", certificateNo)
                    .build();
            List<EctPublicDTO> cData = sqlExecutor.queryForList(uDataQry,EctPublicDTO.class);
            if(cData.size() > 0 && cData.get(0) != null){
                for(int x = 0 ; x < cData.size() ; x++){
                    Map<String, Object> tmp = new HashMap<String, Object>();

                    tmp.put("certificateNo", cData.get(x).getCertificateNo());
                    tmp.put("certificateState", cData.get(x).getCertificateState());
                    tmp.put("isValid", cData.get(x).getIsValid());
                    ls.add(tmp);
                }
                rs.put("resultCode","1");
                rs.put("resultMsg","成功");
            }else{
                rs.put("resultCode","0");
                rs.put("resultMsg","查無資料");
            }
        }else{
            rs.put("resultCode","2");
            rs.put("resultMsg","失敗");
        }
        rs.put("replayData",ls);
        return rs;
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

        if(serviceId != null && serviceId != "" && serviceToken != null && serviceToken != ""){
            if(serviceId.equals(service_Id) && serviceToken.equals(service_Token)){
                return true;
            }
        }
        return false;
    }

    private boolean checkCertExist(String certificateWord){
        Query uDataQry = Query
                .builder()
                .append("SELECT * FROM ECT_PUBLIC")
                .append("WHERE CERTIFICATE_WORD = :certificateWord AND CERTIFICATE_STATE = '1'", certificateWord)
                .build();
        List<EctPublicDTO> cData = sqlExecutor.queryForList(uDataQry,EctPublicDTO.class);
        if(cData.size() > 0){
            return true;
        }
        return false;
    }

    private List<String> checkCert(String certificateWord){
        List<String> ids = new ArrayList<String>();
        Query uDataQry = Query
                .builder()
                .append("SELECT ECT_PUBLISH_ID FROM ECT_PUBLIC")
                .append("WHERE CERTIFICATE_WORD = :certificateWord AND (CERTIFICATE_STATE=0 OR CERTIFICATE_STATE=1)", certificateWord)
                .build();
        List<EctPublicDTO> cData = sqlExecutor.queryForList(uDataQry,EctPublicDTO.class);
        if(cData.size() > 0){
            for(int x = 0 ; x < cData.size() ; x++){
                String id = cData.get(x).getEctPublishId().toString();
                ids.add(id);
            }
            return ids;
        }

        return ids;
    }

    private Map<String, Object> getOldCertData(String certificateWord){
        Map<String, Object> ids = new HashMap<String, Object>();
        Query uDataQry = Query
                .builder()
                .append("SELECT TOP (1) EXAM_NUM, EXAM_ENUM, CERTIFICATE_WORD, PUBLIC_YEAR_MONTH FROM ECT_PUBLIC")
                .append("WHERE CERTIFICATE_WORD = :certificateWord ORDER BY CREATE_TIME DESC", certificateWord)
                        
                .build();
        List<EctPublicDTO> cData = sqlExecutor.queryForList(uDataQry,EctPublicDTO.class);
        if(cData.size() > 0 && cData.get(0) != null){
            ids.put("examNum", cData.get(0).getExamNum());
            ids.put("examEnum", cData.get(0).getExamEnum());
            ids.put("publishYearMonth", cData.get(0).getPublicYearMonth());
            ids.put("certificateWord", cData.get(0).getCertificateWord());
            return ids;
        }

        return ids;
    }

    //還未有API
    protected Map<String, String> checkPersonStatus(String idno){
        String isDead = "N";
        String isCorrect = "Y";
        Map<String, String> r = new HashMap<String, String>();
        r.put("isDead", isDead);
        r.put("isCorrect", isCorrect);
        return r;
    }

    protected void updateEctStatus(int status, int isValid, String idno, String etcPublicId, String currentDateTime, String fileName, String hf, String reason){
        int etcPid = Integer.parseInt(etcPublicId.substring((etcPublicId.indexOf(",")) + 1).trim());
        Query query = Query
                .builder()
                .append("UPDATE ECT_PUBLIC SET ")
                .append("CERTIFICATE_STATE=:status, IS_VALID=:isValid",status, isValid)
                .appendWhen(currentDateTime != null, " ,FILE_CREATE_TIME = :fileCreateTime", currentDateTime)
                .appendWhen(fileName != null, " ,FILE_NAME = :fileName", fileName)
                .appendWhen(hf != null, " ,HASH_MESSAGE = :hashMessage", hf)
                .appendWhen(reason != null, " ,REASON = :reason", reason)
                .appendWhen(currentDateTime != null, "WHERE IDNO = :idno AND ECT_PUBLISH_ID = :etcPublicId", idno, etcPid)
                .appendWhen(currentDateTime == null, "WHERE IDNO = :idno AND CERTIFICATE_NO = :etcPublicId", idno, etcPid)
                .build();
        sqlExecutor.update(query);
    }

    protected String addNewCert(MultipartHttpServletRequest request){
        Query qrry = Query
                .builder()
                .append("SELECT MAX(ECT_PUBLISH_ID) AS ECT_PUBLISH_ID FROM ECT_PUBLIC")
                .build();
        List<EctPublicDTO> idData = sqlExecutor.queryForList(qrry, EctPublicDTO.class);
        long newId = 0;
        if(idData.size() > 0 && idData.get(0).getEctPublishId() != null){
            newId = (idData.get(0).getEctPublishId() + 1);
        }
        String fileName = "_";
        if(request.getFile("perPhotoName") != null){
            fileName = saveImg(request.getFile("perPhotoName"), String.valueOf(request.getParameter("idNo")));
        }
        String certTp = String.valueOf(request.getParameter("certificateType"));
        String bd = "", std = "", edd = "";
        if(request.getParameter("birthDate") != null){
            bd = parseWesternYear(String.valueOf(request.getParameter("birthDate")));
        }
        if(request.getParameter("startDate") != null){
            std = parseWesternYear(String.valueOf(request.getParameter("startDate")));
        }
        if(request.getParameter("endDate") != null){
            edd = parseWesternYear(String.valueOf(request.getParameter("endDate")));
        }
        Query qy = Query
                .builder()
                .append("INSERT INTO ECT_PUBLIC (ECT_PUBLISH_ID, CERTIFICATE_NO, CERTIFICATE_TYPE, APPLY_ITEM, CERTIFICATE_STATE, CERTIFICATE_WORD, PUBLIC_YEAR_MONTH, CERTIFICATE_TEXT, PUBLIC_TEXT, IS_VALID, IS_DIE")
                .appendWhen(certTp.equals("1"), ", NAME, CH_NAME_ROME, EN_NAME, IDNO, BIRTH_DATE, SEX, QTY_CODE, QTY_NAME, CLASS, QTCLASS_NAME, START_DATE, END_DATE, CLASS_HOUR, PER_PHOTO_NAME")
                .appendWhen(certTp.equals("2"), ", NAME, CH_NAME_ROME, EN_NAME, IDNO, BIRTH_DATE, SEX, QTY_CODE, QTY_NAME, CLASS, QTCLASS_NAME, START_DATE, END_DATE, CLASS_HOUR, PER_PHOTO_NAME")
                .appendWhen(certTp.equals("3"), ", NAME, EN_NAME, IDNO, BIRTH_DATE, SEX, CLASSIFICATION_CNAME, CLASSIFICATION_ENAME, PER_PHOTO_NAME, EXAM_NUM, EXAM_ENUM")
                .appendWhen(certTp.equals("4"), ", NAME, EN_NAME, IDNO, BIRTH_DATE, SEX, PER_PHOTO_NAME, PRA_APPROACH, PRA_COMP, PRA_COMP_ADDR, PRA_CLASS_BUSINESS_SCOPE, PRA_START_DATE, PRA_EXPIRE_DATE")
                .appendWhen(certTp.equals("5"), ", COMP_NAME, COMP_ADDR, COMP_BAN, RP_NAME, IDNO, BUSINESS_CONTENT")
                .append(") VALUES (:ECT_PUBLISH_ID, :CERTIFICATE_NO, :CERTIFICATE_TYPE, :APPLY_ITEM, :CERTIFICATE_STATE, :CERTIFICATE_WORD, :PUBLIC_YEAR_MONTH, :CERTIFICATE_TEXT, :PUBLIC_TEXT, :IS_VALID, :IS_DIE",
                        newId, newId, request.getParameter("certificateType"), request.getParameter("applyItem"), 0, request.getParameter("certificateWord"), request.getParameter("publicYearMonth"), request.getParameter("certificateText"), request.getParameter("publicText"), 0, 0)
                .appendWhen(certTp.equals("1"), ", :NAME, :CH_NAME_ROME, :EN_NAME, :IDNO, :BIRTH_DATE, :SEX, :QTY_CODE, :QTY_NAME, :CLASS, :QTCLASS_NAME, :START_DATE, :END_DATE, :CLASS_HOUR, :PER_PHOTO_NAME",
                        request.getParameter("name"), request.getParameter("chNameRome"), request.getParameter("enName"), request.getParameter("idNo"), bd, request.getParameter("sex"), request.getParameter("qtyCode"), request.getParameter("qtyName"), request.getParameter("class"), request.getParameter("qtclassName"), std, edd, request.getParameter("classHour"), fileName)
                .appendWhen(certTp.equals("2"), ", :NAME, :CH_NAME_ROME, :EN_NAME, :IDNO, :BIRTH_DATE, :SEX, :QTY_CODE, :QTY_NAME, :CLASS, :QTCLASS_NAME, :START_DATE, :END_DATE, :CLASS_HOUR, :PER_PHOTO_NAME",
                        request.getParameter("name"), request.getParameter("chNameRome"), request.getParameter("enName"), request.getParameter("idNo"), bd, request.getParameter("sex"), request.getParameter("qtyCode"), request.getParameter("qtyName"), request.getParameter("class"), request.getParameter("qtclassName"), std, edd, request.getParameter("classHour"), fileName)
                .appendWhen(certTp.equals("3"), ", :NAME, :EN_NAME, :IDNO, :BIRTH_DATE, :SEX, :CLASSIFICATION_CNAME, :CLASSIFICATION_ENAME, :PER_PHOTO_NAME, :EXAM_NUM, :EXAM_ENUM",
                        request.getParameter("name"), request.getParameter("enName"), request.getParameter("idNo"), bd, request.getParameter("sex"), request.getParameter("classificationCname"), request.getParameter("classificationEname"), fileName, request.getParameter("examNum"), request.getParameter("examEnum"))
                .appendWhen(certTp.equals("4"), ", :NAME, :EN_NAME, :IDNO, :BIRTH_DATE, :SEX, :PER_PHOTO_NAME, :PRA_APPROACH, :PRA_COMP, :PRA_COMP_ADDR, :PRA_CLASS_BUSINESS_SCOPE, :PRA_START_DATE, :PRA_EXPIRE_DATE",
                        request.getParameter("name"), request.getParameter("enName"), request.getParameter("idNo"), bd, request.getParameter("sex"), fileName, request.getParameter("praApproach"), request.getParameter("praComp"), request.getParameter("praCompAddr"), request.getParameter("praClassBusinessScope"), request.getParameter("praStartDate"), request.getParameter("praExpireDate"))
                .appendWhen(certTp.equals("5"), ", :COMP_NAME, :COMP_ADDR, :COMP_BAN, :RP_NAME, :IDNO, :BUSINESS_CONTENT",
                        request.getParameter("compName"), request.getParameter("compAddr"), request.getParameter("compBan"), request.getParameter("rpName"), request.getParameter("idNo"), request.getParameter("businessContent"))
                .append(")")
                .build();
        sqlExecutor.insert(qy);
        String pk = "";
        Query uDataQry = Query
                .builder()
                .append("SELECT ECT_PUBLISH_ID FROM ECT_PUBLIC")
                .append("WHERE CERTIFICATE_WORD = :certificateWord AND CERTIFICATE_STATE = 0", request.getParameter("certificateWord"))
                .build();
        List<EctPublicDTO> cData = sqlExecutor.queryForList(uDataQry,EctPublicDTO.class);
        if(cData.size() > 0){
            pk = fileName + "," + String.valueOf(cData.get(0).getEctPublishId());
            return pk;
        }
        return pk;
    }

    protected String createPdf(MultipartHttpServletRequest request, String savePath, String ectid){
        try{
            Map<String, Object> params = new HashMap<String, Object>();
            String [] enMonth = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            String jasperName = null;
            String fileName = null;
            String preFileName = null;
            String certType = String.valueOf(request.getParameter("certificateType"));
            String applyType = "";
			if (String.valueOf(request.getParameter("applyItem")).equals("1")){
				applyType = "換";
			} else if (String.valueOf(request.getParameter("applyItem")).equals("2")){
				applyType = "補";
			} else {
				applyType = "核";
			}
            String sex = String.valueOf(request.getParameter("sex")).equals("1") ? "男" : "女";
            String certWord = String.valueOf(request.getParameter("certificateWord"));
            
            Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher mat = pat.matcher(certWord);
            String certNo = mat.replaceAll("");

            String fName = "";
            if(ectid.split(",").length > 1){
                fName = ectid.split(",")[0];
            }
            String birthday = String.valueOf(request.getParameter("birthDate"));
            String[] bd = String.valueOf(birthday).split("/");
            if(bd.length > 1){
                bd[1] = String.valueOf(Integer.parseInt(bd[1]));
                if(bd.length > 2){
                    bd[2] = String.valueOf(Integer.parseInt(bd[2]));
                }
            }
            String[] pym = upperNum(request.getParameter("publicYearMonth")).split("/");
            if(pym.length>1){
                pym[1] = String.valueOf(Integer.parseInt(pym[1]));
                if(pym.length>2){
                    pym[2] = String.valueOf(Integer.parseInt(pym[2]));
                }
            }
            if(certType.equals("1")){
                jasperName = "CertType1";
                fileName = 
                        PropUtil.getPropVal("ect.cer.savepath") + "1/"+ savePath;
                preFileName = "/QMC_";
                checkDir(fileName);
                if(!applyType.equals("核")){
                    certWord += "（" + applyType + "）";
                }
                params.put("certificateWord", request.getParameter("certificateText"));
                params.put("name", request.getParameter("name"));
                params.put("sex", sex);
                params.put("idno", request.getParameter("idNo"));
                params.put("birthDate", upperDate(String.valueOf(request.getParameter("birthDate"))));
                params.put("startDate", upperDate(String.valueOf(request.getParameter("startDate"))));
                params.put("endDate", upperDate(String.valueOf(request.getParameter("endDate"))));
                params.put("host", request.getParameter("qtyName"));
                params.put("classes", transFull(request.getParameter("class")));
                params.put("className", request.getParameter("qtclassName"));
                params.put("hour", String.valueOf(request.getParameter("classHour")));
                params.put("createDate", request.getParameter("publicText"));
                params.put("imgUrl", PropUtil.getPropVal("ect.cer.photopath") + fName);
                params.put("backgroundUrl", PropUtil.getPropVal("ect.cer.backgroundpath125"));
            }else if(certType.equals("2")){
                jasperName = "CertType2";
                fileName = 
                        PropUtil.getPropVal("ect.cer.savepath") + "2/" + savePath;
                preFileName = "/QMR_";
                checkDir(fileName);
                if(!applyType.equals("核")){
                    certWord += "（" + applyType + "）";
                }
                params.put("certificateWord", request.getParameter("certificateText"));
                params.put("name", request.getParameter("name"));
                params.put("sex", sex);
                params.put("idno", request.getParameter("idNo"));
                params.put("birthDate", upperDate(String.valueOf(request.getParameter("birthDate"))));
                params.put("startDate", upperDate(String.valueOf(request.getParameter("startDate"))));
                params.put("endDate", upperDate(String.valueOf(request.getParameter("endDate"))));
                params.put("host", request.getParameter("qtyName"));
                params.put("classes", transFull(request.getParameter("class")));
                params.put("hour", String.valueOf(request.getParameter("classHour")));

                params.put("createDate", request.getParameter("publicText"));
                params.put("qClassName", request.getParameter("qtclassName"));
                params.put("imgUrl", PropUtil.getPropVal("ect.cer.photopath") + fName);
                params.put("backgroundUrl", PropUtil.getPropVal("ect.cer.backgroundpath125"));
            }else if(certType.equals("3")){
                jasperName = "CertType3";
                fileName = 
                        PropUtil.getPropVal("ect.cer.savepath") + "3/" + savePath;
                preFileName = "/EEC_";
                checkDir(fileName);
                params.put("certificateWord", request.getParameter("certificateText"));
                params.put("certNo", certNo);
                String chPym = (String.valueOf(Integer.parseInt(pym[0])) + "年" + String.valueOf(Integer.parseInt(pym[1])) + "月");
                if(pym.length > 2){
                    chPym += (String.valueOf(Integer.parseInt(pym[2])) + "日");
                }

                String changeC1 = "", changeC2 = "", changeN1 = "", changeN2 = "";
                if(!applyType.equals("核")){
                    Map<String, Object> oldDt = getOldCertData(String.valueOf(request.getParameter("certificateWord")));
                    System.out.println("applyType: " + applyType);
                    if(oldDt.get("certificateWord") != null && oldDt.get("publishYearMonth") != null && oldDt.get("examNum") != null && oldDt.get("examEnum") != null){
                        String oym = String.valueOf(oldDt.get("publishYearMonth"));
                        String [] oyms = oym.split("/");
                        String om = (enMonth[(Integer.parseInt(oyms[1]))]).toUpperCase();
                        if(oyms.length > 2){
                            om += " " + oyms[2];
                        }
                        om +=  ", " + String.valueOf((Integer.parseInt(oyms[0])) + 1911);
                        String ocw = String.valueOf(oldDt.get("certificateWord"));
                        Matcher mat2 = pat.matcher(ocw);
                        String certNo2 = mat2.replaceAll("");
                        changeC1 = "(原" + String.valueOf(oldDt.get("examNum")) +")";
                        changeC2 = "(" + upperDate(oym) + "核發之同號技師證書因遺失併案作廢)";
                        changeN1 = "(Original" + String.valueOf(oldDt.get("examEnum")) + ")";
                        changeN2 = "(The Professional Engineer Certificate No." + certNo2 + " issued on " + om + " is void due to lost)";
                    }
                }
                params.put("changeC1", changeC1);
                params.put("changeC2", changeC2);
                params.put("changeN1", changeN1);
                params.put("changeN2", changeN2);

                params.put("cnCreateDate", request.getParameter("publicText"));
                params.put("cnName", request.getParameter("name"));
                params.put("cnSex", sex);
                params.put("cnBirthDate", "民國" + String.valueOf(Integer.parseInt(bd[0])) + "年" + String.valueOf(Integer.parseInt(bd[1])) + "月" + String.valueOf(Integer.parseInt(bd[2])) + "日");
                params.put("idno", request.getParameter("idNo"));
                params.put("cnSubject", request.getParameter("classificationCname"));
                params.put("cnPassCertNo", request.getParameter("examNum"));
                params.put("enName", request.getParameter("enName"));
                params.put("enSex", String.valueOf(request.getParameter("sex")).equals("1") ? "Male" : "Female");
                String enBd = enMonth[(Integer.parseInt(bd[1]))] + " " + String.valueOf(Integer.parseInt(bd[2])) + ", " + String.valueOf(Integer.parseInt(bd[0]) + 1911);
                params.put("enBirthDate", enBd);
                params.put("classification", request.getParameter("classificationEname"));
                params.put("examNum", request.getParameter("examEnum"));
                String enPym = (enMonth[(Integer.parseInt(pym[1]))]).toUpperCase();
                if(pym.length > 2){
                    enPym += " " + pym[2];
                }
                enPym +=  ", " + String.valueOf((Integer.parseInt(pym[0])) + 1911);
                params.put("enCreateDate", enPym);
                params.put("imgUrl", PropUtil.getPropVal("ect.cer.photopath") + fName);
                params.put("backgroundUrl", PropUtil.getPropVal("ect.cer.backgroundpath3"));
            }else if(certType.equals("4")){
                jasperName = "CertType4";
                fileName = 
                        PropUtil.getPropVal("ect.cer.savepath") + "4/" + savePath;
                preFileName = "/EEL_";
                String json_str = String.valueOf(request.getParameter("praClassBusinessScope"));
                Map<String, String> jmp = jsonStrTransfer(json_str);
                if(jmp.get("status").equals("1")){
                    String scope = jmp.get("scope");
                    String praScope = jmp.get("praScope");
                    checkDir(fileName);
                    params.put("certificateWord", request.getParameter("certificateText"));
                    params.put("name", request.getParameter("name"));
                    params.put("sex", sex);
                    params.put("idno", request.getParameter("idNo"));
                    params.put("birthDate", "民國" + String.valueOf(Integer.parseInt(bd[0])) + "年" + String.valueOf(Integer.parseInt(bd[1])) + "月" + String.valueOf(Integer.parseInt(bd[2])) + "日");
                    params.put("praApproach", request.getParameter("praApproach"));
                    params.put("praComp", request.getParameter("praComp"));
                    params.put("address", request.getParameter("praCompAddr"));
                    params.put("scope", scope != null ? scope : "");
                    String [] praSt = request.getParameter("praStartDate").split("/");
                    String [] praEp = request.getParameter("praExpireDate").split("/");
                    params.put("period", String.valueOf(Integer.parseInt(praSt[0])) + "年" + String.valueOf(Integer.parseInt(praSt[1])) + "月" + String.valueOf(Integer.parseInt(praSt[2])) +"日至" + String.valueOf(Integer.parseInt(praEp[0])) + "年" + String.valueOf(Integer.parseInt(praEp[1])) + "月" + String.valueOf(Integer.parseInt(praEp[2])) + "日止");
                    String createD = (String.valueOf(Integer.parseInt(pym[0])) + "年" + String.valueOf(Integer.parseInt(pym[1])) + "月");
                    if(pym.length > 2){
                        createD += (String.valueOf(Integer.parseInt(pym[2])) + "日");
                    }
                    createD += ("（" + applyType + "發）");
                    params.put("createDate",  request.getParameter("publicText"));
                    params.put("praScope", praScope != null ? praScope : "");
                    params.put("imgUrl", PropUtil.getPropVal("ect.cer.photopath") + fName);
                    params.put("backgroundUrl", PropUtil.getPropVal("ect.cer.backgroundpath4"));
                }else{
                    return "_,_";
                }
            }else if(certType.equals("5")) {
                jasperName = "CertType5";
                fileName = 
                        PropUtil.getPropVal("ect.cer.savepath") + "5/" + savePath;
                preFileName = "/ECL_";
                checkDir(fileName);
                if(!applyType.equals("核")){
                    certWord += "（" + applyType + "發）";
                }
                params.put("certificateWord", request.getParameter("certificateText"));
                params.put("name", request.getParameter("rpName"));
                params.put("address", request.getParameter("compAddr"));
                params.put("idno", request.getParameter("idNo"));
                params.put("compName", request.getParameter("compName"));
                params.put("applyType", request.getParameter("compName") + "申請" + applyType + "發");
                params.put("compBand", request.getParameter("compBan"));
                params.put("scope", request.getParameter("businessScope"));
                params.put("content", request.getParameter("businessContent"));
                String createDate = (String.valueOf(Integer.parseInt(pym[0])) + "年" + String.valueOf(Integer.parseInt(pym[1])) + "月");
                if(pym.length > 2){
                    createDate += (String.valueOf(Integer.parseInt(pym[2])) + "日");
                }
                if(!applyType.equals("核")){
                    createDate += "（" + applyType + "發）";
                }
                params.put("createDate", request.getParameter("publicText"));
                params.put("backgroundUrl", PropUtil.getPropVal("ect.cer.backgroundpath125"));
            }
            if(jasperName != null && fileName != null){
                FileOutputStream fos1 = new FileOutputStream(fileName + preFileName + certNo + "_" + ectid.split(",")[1] + ".pdf");
                genReport(jasperName, fos1, params,fileName+ preFileName + certNo + "_" + ectid.split(",")[1] + ".pdf");

                genReport(jasperName + "_wm1", fos1, params,fileName+ preFileName + certNo + "_" + ectid.split(",")[1] + "_v1.pdf");
                genReport(jasperName + "_wm2", fos1, params,fileName+ preFileName + certNo + "_" + ectid.split(",")[1] + "_v2.pdf");
                if(certType.equals("4")){
                    genReport(jasperName + "_wm3", fos1, params,fileName+ preFileName + certNo + "_" + ectid.split(",")[1] + "_v3.pdf");//view_expired
                }
                fos1.close();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String td = formatter.format(new Date());
                System.out.println("B E F O R E _ H C :" + (preFileName + certNo + "_" + ectid.split(",")[1] + "_" + td));
                String hc = String.valueOf((preFileName + certNo + "_" + ectid.split(",")[1] + "_" + td).hashCode());
                System.out.println("H C :" + hc);
                boolean srs = signPdf(fileName + preFileName + certNo + "_" + ectid.split(",")[1] + ".pdf", hc);
                if(srs){
                    return (preFileName.replace("/", "") + certNo + "_" + ectid.split(",")[1] + ".pdf" + "," + hc);
                }else{
                    return "_,_";
                }
            }
        }catch(Exception e){
            System.out.println("出錯"+e.getMessage());
            e.printStackTrace();
        }
        return "_,_";
    }

    private void checkDir(String dir){
        System.out.println("DIR:"+dir);
        File file =new File(dir);
        if(!file.exists()){
            System.out.println("N O");
            file.mkdirs();
        }else {
            System.out.println("Y E S");
        }
    }

    public static String transFull(String src){
        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == DBC_SPACE) {
                buf.append(SBC_SPACE);
            } else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) {
                buf.append((char) (ca[i] + CONVERT_STEP));
            } else {
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    private String upperNum(String num){
        String[] numbers = {"０","１","２","３","４","５","６","７","８","９"};


        for(int x = 0 ; x < 10 ; x++){
            String curr = String.valueOf(x);
            num.replaceAll(curr, numbers[x]);
        }
        return num;
    }

    private String upperDate(String date){
        String[] ymd = date.split("/");
        String rs = "";
        String[] numbers = {"Ｏ","一","二","三","四","五","六","七","八","九"};
        String[] months = {"","一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
        String[] days = {"","一日","二日","三日","四日","五日","六日","七日","八日","九日","十日","十一日","十二日"
                ,"十三日","十四日","十五日","十六日","十七日","十八日","十九日","二十日","二十一日","二十二日","二十三日"
                ,"二十四日","二十五日","二十六日","二十七日","二十八日","二十九日","三十日","三十一日"};
        int y = Integer.parseInt(ymd[0]);
        String [] ys = String.valueOf(y).split("");
        if(y < 100){
            if(ys.length > 1){
                if(!ys[0].equals("1")){
                    int idx = Integer.parseInt(ys[0]);
                    rs += numbers[idx];
                    if(!ys[1].equals("0")){
                        rs += "十";
                    }
                }else{
                    rs += "一";
                }
                int idx2 = Integer.parseInt(ys[1]);
                rs += numbers[idx2];
            }else{
                int idx3 = Integer.parseInt(ys[0]);
                rs += numbers[idx3];
            }
            rs += "年";

        }else{
            for(int x = 0 ; x < ys.length ; x++){
                int idx = Integer.parseInt(ys[x]);
                rs += numbers[idx];
            }
            rs += "年";
        }
        int m = Integer.parseInt(ymd[1]);
        rs += months[m];
        if(ymd.length > 2){
            int d = Integer.parseInt(ymd[2]);
            rs += days[d];
        }
        return rs;
    }

    private String parseMinKuoYear(String bd){
        String rs = "";
        String[] ymd = bd.split("/");
        String yr = String.valueOf(Integer.parseInt(ymd[0])-1911);
        rs = yr + "/" + ymd[1] + "/" + ymd[2];
        return rs;
    }

    private String parseWesternYear(String bd){
        String rs = "";
        String[] ymd = bd.split("/");
        if(ymd.length > 0){
            String y = ymd[0];
            int iy = (Integer.parseInt(y) + 1911);
            String yr = String.valueOf(iy);
            rs = yr + "/" + ymd[1] ;
            if(ymd.length > 2) {
                rs += "/" + ymd[2];
            }
        }
        return rs;
    }

    private Map<String, String> jsonStrTransfer(String jsonStr){
        Map<String, String> rs = new HashMap<String, String>();
        try{
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            Map<String, List<Object>> jsonMap = gson.fromJson(jsonStr,Map.class);
            List<Object> arObj = jsonMap.get("praClassBusinessScope");
            String scope = "", praScope = "";
            for(int x = 0 ; x < arObj.size() ; x++){
                Map<String, Object> map = (Map<String, Object>) arObj.get(x);
                String sc = String.valueOf(map.get("scope"));
                String psc = String.valueOf(map.get("content"));
                if(scope != ""){
                    scope = scope + "、";
                }
                scope = scope + sc;
                if(!praScope.equals("")){
                    praScope += "<br>";
                }
                praScope += (sc.split(" ")[0] + "執業範圍:<br>" + psc);
            }
            rs.put("status", "1");
            rs.put("scope", scope != null ? scope : "");
            rs.put("praScope", praScope != null ? praScope : "");
        }catch(Exception e){
            rs.put("status", "2");
            rs.put("scope", "");
            rs.put("praScope", "");
            e.printStackTrace();
        }
        return rs;
    }

    private String saveImg(MultipartFile file, String imgName){
        try{
            checkDir("/pics");
            String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
            String fN = imgName + "." + fileType;
            String realpath = PropUtil.getPropVal("ect.cer.photopath") + fN;
            Path filepath = Paths.get(realpath);
            OutputStream os = Files.newOutputStream(filepath);
            os.write(file.getBytes());
            os.close();
            return fN;
        }catch(Exception e){
           e.printStackTrace();
            return null;
        }
    }

    protected boolean signPdf(String path, String f){
        try{
            // PdfSigner pdfSigner = new PdfSigner();
            // VerifySign verifySign = new VerifySign();
            // File file = new File(path);
            // byte[] fileContent = Files.readAllBytes(file.toPath());
            // byte[] signedPdf = pdfSigner.signPdf(fileContent, f);
            // Path path2 = Paths.get(path);
            // Files.write(path2, signedPdf);
            // verifySign.hasSign(file);
            // verifySign.vfSign(file);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected FileOutputStream genReport(String jasperName, FileOutputStream os, Map dataMap, String fileName) {
        try {
            if (fileName == null){
                fileName = jasperName;
            }
            doReport(os, jasperName, dataMap, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return os;
    }

    public void doReport(FileOutputStream os, String jasperName, Map inputData, String fileName) throws JRException, IOException {
        JRDataSource jrDataSource = new JREmptyDataSource();
        doReport(os, jasperName, null, inputData, jrDataSource, fileName);
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
    public void doReport(FileOutputStream os, String jasperName, SimpleExporterConfiguration exportConf, Map inputData, JRDataSource jrDataSource, String fileName) throws JRException, IOException {















        try {
            JasperPrint jasperPrint = createWebReport(jasperName, inputData, jrDataSource);
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            final PccPdfExporter pdf = new PccPdfExporter();
            pdf.setExporterInput(new SimpleExporterInput(jasperPrint));
            pdf.setExporterOutput(new SimpleOutputStreamExporterOutput(fos));
            pdf.exportReport();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private JasperPrint createWebReport(String jasperName, Map inputData, JRDataSource jrDataSource) throws JRException {
        JasperPrint print;
        JasperDesign design = JRXmlLoader.load(getJrxmlFile(jasperName));

        JasperReport report = JasperCompileManager.compileReport(design);
        print = JasperFillManager.fillReport(report, inputData, jrDataSource);
        return print;
    }

    public InputStream getJrxmlFile(String jasperName) {
        InputStream jrxmlFile = null;
        String jrxmlFilePath = "/ireport/jrxml/" + jasperName + ".jrxml";
        jrxmlFile = this.getClass().getResourceAsStream(jrxmlFilePath);
        return jrxmlFile;
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
