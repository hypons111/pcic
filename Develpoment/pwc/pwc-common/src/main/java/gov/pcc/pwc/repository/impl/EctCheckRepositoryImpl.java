package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EctCheckRepositoryCustom;
import gov.pcc.pwc.service.dto.ECT0201R1DTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.odsExporter.OdsExportService;
// import gov.pcc.pwc.signature.VerifySign;
import gov.pcc.pwc.utils.PropUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public class EctCheckRepositoryImpl implements EctCheckRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    private final SerialNumGenerator serialNumGenerator;

    private final OdsExportService odsExportService;

    public EctCheckRepositoryImpl(SqlExecutor sqlExecutor,OdsExportService odsExportService,SerialNumGenerator serialNumGenerator) {
        this.sqlExecutor = sqlExecutor;
        this.odsExportService = odsExportService;
        this.serialNumGenerator = serialNumGenerator;
    }

    @Override
    public Map<String, Object> uploadCertPdf(MultipartHttpServletRequest request) {
        String [] certName = {"", "結業證書", "回訓證明", "技師證書", "技師執業執照", "工程技術顧問公司登記證"};
        Map<String, Object> rs = new HashMap<String, Object>();
        if(!PropUtil.getPropValCheck()){
            rs.put("status", "faild");
            rs.put("message", "property 設定檔不存在");
            return rs;
        }
        String fileName = "", certNm = "", name = "", idno = "", isValid = "", certWord = "", verifyTime = "", verfiyInstruction = "", isValidStr= "", compName = "", compBand = "";
        int certificateType = 1;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
            Date today = new Date();
            String minyr = String.valueOf(Integer.parseInt(sdf1.format(today).substring(0,4)) - 1911);
            verifyTime = (minyr + (sdf1.format(today)).substring(4));
            fileName = request.getFile("certPdf").getName();
            String p = multipartToFile(request.getFile("certPdf"));
            File tempFile = new File(p);
            String ip = getIpAddr(request);
            if(tempFile != null){
                String hc = checkPdf(tempFile);
                if(hc != null){
                    String tdd = ((String.valueOf(sdf1.format(today))).substring(0, 10)).replaceAll("/", "-");
                    Instant s1 = sdf.parse((tdd + " 00:00:00")).toInstant();
                    Instant s2 = sdf.parse((tdd + " 23:59:59")).toInstant();
                    Query ckQry = Query
                            .builder()
                            .append("SELECT CK.ECT_CHECK_ID,PC.HASH_MESSAGE FROM ECT_CHECK AS CK LEFT JOIN ECT_PUBLIC AS PC")
                            .append(" ON CK.ECT_PUBLISH_ID = PC.ECT_PUBLISH_ID")
                            .append(" WHERE PC.HASH_MESSAGE = :HASH_MESSAGE AND CK.CREATE_TIME > :Std AND CK.CREATE_TIME < :Etd", hc, Timestamp.from(s1), Timestamp.from(s2))
                            .build();
                    List<ECT0201R1DTO> ckData = sqlExecutor.queryForList(ckQry,ECT0201R1DTO.class);
                    if(ckData.size() > 9){
                        rs.put("status", "faild");
                        rs.put("message", "已超過一日可查驗次數");
                        rs.put("fileName", "");
                        rs.put("certName", "");
                        rs.put("userName", "");
                        rs.put("idno", "");
                        rs.put("isValid", "");
                        rs.put("certWord", "");
                        rs.put("verifyTime", "");
                        rs.put("verfiyInstruction", "");
                        rs.put("certType", "");
                        rs.put("compName", "");
                        rs.put("compBand", "");
                        return rs;
                    }
                    Query certQry = Query
                            .builder()
                            .append("SELECT PC.FILE_NAME, PC.ECT_PUBLISH_ID, PC.CERTIFICATE_NO, PC.CERTIFICATE_STATE")
                            .append(", PC.CERTIFICATE_WORD, PC.CERTIFICATE_TYPE, PC.PUBLIC_YEAR_MONTH, PC.APPLY_ITEM")
                            .append(", DH.LAST_DOWNLOAD_TIME, PC.IS_VALID, PC.NAME, PC.CH_NAME_ROME, PC.QTCLASS_NAME")
                            .append(", PC.EN_NAME, PC.IDNO, PC.PRA_START_DATE, PC.PRA_EXPIRE_DATE")
                            .append(", PC.COMP_NAME, PC.COMP_BAN, PC.RP_NAME, PC.HASH_MESSAGE")
                            .append("FROM ECT_PUBLIC AS PC LEFT JOIN ECT_DOWNLOAD_HIS AS DH")
                            .append("ON PC.ECT_PUBLISH_ID = DH.ECT_PUBLISH_ID AND PC.CERTIFICATE_TYPE = DH.CERTIFICATE_TYPE ")
                            .append("WHERE HASH_MESSAGE = :HASH_MESSAGE ", hc)
                            .append("ORDER BY DH.LAST_DOWNLOAD_TIME DESC")
                            .build();
                    List<ECT0201R1DTO> cData = sqlExecutor.queryForList(certQry,ECT0201R1DTO.class);
                    if(cData.size() > 0){
                        ECT0201R1DTO ect0201r1dto = cData.get(0);
                        certificateType = ect0201r1dto.getCertificateType();
                        int applyItem = ect0201r1dto.getApplyItem();
                        String publicYearMonth = String.valueOf(ect0201r1dto.getPublicYearMonth());
                        String praExpireDate = String.valueOf(ect0201r1dto.getPraExpireDate());
                        String validDate = "";
                        if(certificateType == 1 || certificateType == 2){
                            String yyy = publicYearMonth.split("/")[0];
                            String newY = String.valueOf((Integer.parseInt(yyy) + 4));
                            validDate = (publicYearMonth.replace(yyy, newY));
                        }else if(certificateType == 4){
                            validDate = praExpireDate;
                        }else{
                            validDate = "9999/12/31";
                        }
                        long ectPublicId = ect0201r1dto.getEctPublishId();
                        long ectCheckId = getNewId();
                        Instant lastDownloadTime =Instant.now();
                        Instant createTime = Instant.now();
                        fileName = ect0201r1dto.getFileName();
                        certNm = certName[certificateType];
                        if(certificateType == 1){
                            if(applyItem == 1){
                                certNm = "換發:" + ect0201r1dto.getQtclassName() + "結業證明書";
                            }else if(applyItem == 2){
                                certNm = "補發:" + ect0201r1dto.getQtclassName() + "結業證明書";
                            }else{
                                certNm = "受訓:" + ect0201r1dto.getQtclassName() + certName[certificateType];
                            }
                        }else if(certificateType == 2){
                            if(applyItem == 1){
                                certNm = "換發:公共工程品質管理人員回訓班回訓證明書";
                            }else if(applyItem == 2){
                                certNm = "補發:公共工程品質管理人員回訓班回訓證明書";
                            }else{
                                certNm = "回訓:公共工程品質管理人員回訓班" + certName[certificateType];
                            }
                        }
                        name = ect0201r1dto.getName();
                        idno = ect0201r1dto.getIdno();
                        isValid = ect0201r1dto.getIsValid();
                        certWord = ect0201r1dto.getCertificateWord();
                        compName = ect0201r1dto.getCompName();
                        compBand = ect0201r1dto.getCompBan();
                        isValidStr = "無效";
                        verfiyInstruction = "驗證未通過，";
                        if(certificateType == 4){
                            String eD = parseWesternYear(String.valueOf(praExpireDate.replaceAll("/", "-")));
                            Date expD = null;
                            expD = sdf.parse((eD.replaceAll("/", "-") + " 23:59:59"));

                            if(expD.before(today) && ect0201r1dto.getCertificateState() == 1){
                                if(isValid.equals("1")){
                                    Query query = Query
                                            .builder()
                                            .append("UPDATE ECT_PUBLIC SET ")
                                            .append("IS_VALID=:isValid",3)
                                            .append("WHERE IDNO = :idno AND CERTIFICATE_NO = :etcPublicId", idno, ectPublicId)
                                            .build();
                                    sqlExecutor.update(query);
                                }
                                isValid = "3";
                            }
                        }else if(certificateType == 1 || certificateType == 2){

                            boolean expired = false;
                            String eD = parseWesternYear(String.valueOf(publicYearMonth)).replaceAll("/", "-");
                            eD = eD.substring(0, 7) + "-01";
                            Date expD = sdf.parse((eD + " 23:59:59"));
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(expD);

                            calendar.add(Calendar.YEAR, 4);
                            calendar.add(Calendar.MONTH, 1);
                            calendar.set(Calendar.DAY_OF_MONTH, 1);
                            calendar.add(Calendar.DATE, -1);
                            Date lastDayOfMonth = calendar.getTime();
                            if(lastDayOfMonth.before(today) && ect0201r1dto.getCertificateState() == 1){
                                expired = true;
                                if(isValid.equals("1")){
                                    Query query = Query
                                            .builder()
                                            .append("UPDATE ECT_PUBLIC SET ")
                                            .append("IS_VALID=:isValid",3)
                                            .append("WHERE IDNO = :idno AND CERTIFICATE_NO = :etcPublicId", idno, ectPublicId)
                                            .build();
                                    sqlExecutor.update(query);
                                }
                                isValid = "3";
                            }
                        }
                        if(isValid.equals("1")){
                            isValidStr = "";
                            verfiyInstruction = "驗證無誤";
                        }else if(isValid.equals("2")){
                            isValidStr = "該證書已註銷";
                            verfiyInstruction = "驗證未通過，";
                        }else if(isValid.equals("3")){
                            isValidStr = "該證書已逾期";
                            verfiyInstruction = "驗證未通過，";
                        }
                        java.sql.Date sql1 = null;
                        java.sql.Date sql2 = null;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                        if(ect0201r1dto.getPraStartDate() != null && !ect0201r1dto.getPraStartDate().equals("")){
                            String pStartD = ect0201r1dto.getPraStartDate();
                            if(pStartD.indexOf("-") > -1){
                                pStartD.replaceAll("-", "/");
                            }
                            String y0 = pStartD.split("/")[0];
                            if(y0.length() < 4){
                                String y1 = "";
                                y1 = String.valueOf(Integer.parseInt(y0) + 1911);
                                pStartD = pStartD.replaceAll(y0, y1);
                            }
                            Date parsed1 = format.parse(pStartD);
                            sql1 = new java.sql.Date(parsed1.getTime());
                        }
                        if(ect0201r1dto.getPraExpireDate() != null && !ect0201r1dto.getPraExpireDate().equals("")){
                            String pEndD = ect0201r1dto.getPraExpireDate();
                            if(pEndD.indexOf("-") > -1){
                                pEndD.replaceAll("-", "/");
                            }
                            String y0 = pEndD.split("/")[0];
                            if(y0.length() < 4){
                                String y1 = "";
                                y1 = String.valueOf(Integer.parseInt(y0) + 1911);
                                pEndD = pEndD.replaceAll(y0, y1);
                            }
                            Date parsed2 = format.parse(pEndD);
                            sql2 = new java.sql.Date(parsed2.getTime());
                        }

                        Query qy = Query
                                .builder()
                                .append("INSERT INTO ECT_CHECK (ECT_CHECK_ID, FILE_NAME, ECT_PUBLISH_ID, CERTIFICATE_NO")
                                .append(", CERTIFICATE_TYPE, PUBLIC_YEAR_MONTH, APPLY_ITEM, LAST_DOWNLOAD_TIME, IS_VALID")
                                .append(", IS_VALID_MSG, NAME, CH_NAME_ROME, EN_NAME, IDNO, VALID_DATE, PRA_START_DATE, PRA_EXPIRE_DATE")
                                .append(", COMP_NAME, COMP_BAN, RP_NAME, SOURCE_IP, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)")
                                .append("VALUES (")
                                .append(":ECT_CHECK_ID, :FILE_NAME, :ECT_PUBLISH_ID, :CERTIFICATE_NO,", ectCheckId, fileName, ect0201r1dto.getEctPublishId(), ect0201r1dto.getCertificateNo())
                                .append(":CERTIFICATE_TYPE, :PUBLIC_YEAR_MONTH, :APPLY_ITEM, :LAST_DOWNLOAD_TIME, :IS_VALID,", certificateType, publicYearMonth, ect0201r1dto.getApplyItem(), Timestamp.from(lastDownloadTime), Integer.parseInt(isValid))
                                .append(":IS_VALID_MSG, :NAME, :CH_NAME_ROME, :EN_NAME, :IDNO, :VALID_DATE, :PRA_START_DATE, :PRA_EXPIRE_DATE,", isValidStr, name, ect0201r1dto.getChNameRome(), ect0201r1dto.getEnName(), idno, validDate, sql1, sql2)
                                .append(":COMP_NAME, :COMP_BAN, :RP_NAME, :SOURCE_IP, :CREATE_USER, :CREATE_TIME, :UPDATE_USER, :UPDATE_TIME)", compName, compBand, ect0201r1dto.getRpName(), ip, idno, Timestamp.from(createTime), null, null)
                                .build();
                        sqlExecutor.insert(qy);
                        if(ect0201r1dto.getCertificateState() == 1 && isValid.equals("1")){

                            rs.put("status", "success");
                            rs.put("message", "本電子證書經驗證無誤!");
                        }else{
                            rs.put("status", "faild");
                            rs.put("message", "本電子證書經驗證未通過!");
                        }
                    }else{
                        isValidStr = "電子簽章不符合";
                        verfiyInstruction = "驗證未通過，";
                        rs.put("status", "faild");
                        rs.put("message", "本電子證書經驗證未通過!");
                    }
                }else{
                    isValidStr = "憑證已逾期";
                    verfiyInstruction = "驗證未通過，";
                    rs.put("status", "faild");
                    rs.put("message", "本電子證書經驗證未通過!");
                }


                tempFile.deleteOnExit();
            }else{
                rs.put("status", "faild");
                rs.put("message", "無上傳檔案");
            }
        } catch (ParseException e) {
            rs.put("status", "faild");
            rs.put("message", "發生錯誤");
            e.printStackTrace();
        }
        rs.put("fileName", fileName);
        rs.put("certName", certNm);
        rs.put("userName", name);
        rs.put("idno", idno);
        if(isValid.equals("1")){
            rs.put("isValid", "有");
        }else{
            rs.put("isValid", "");
        }
        rs.put("certWord", certWord);
        rs.put("verifyTime", verifyTime);
        rs.put("certType", String.valueOf(certificateType));
        rs.put("compName", compName);
        rs.put("compBand", compBand);
        rs.put("verfiyInstruction", verfiyInstruction + isValidStr);
        return rs;
    }

    protected long getNewId(){
        long id = 0;
        Query certQry = Query
                .builder()
                .append("SELECT TOP(1) ECT_CHECK_ID FROM ECT_CHECK ORDER BY ECT_CHECK_ID DESC")
                .build();
        List<ECT0201R1DTO> cData = sqlExecutor.queryForList(certQry,ECT0201R1DTO.class);
        if(cData.size() > 0 && cData.get(0) != null){
            id = (cData.get(0).getEctCheckId() + 1);
            return id;
        }
        return id;
    }

    protected String checkPdf(File file){
        try{
            // VerifySign verifySign = new VerifySign();
            // boolean hasSign = verifySign.hasSign(file);
            // if(hasSign){
                // return verifySign.vfSign(file);
            // }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String multipartToFile(MultipartFile file){
        try{
            File f =new File("/pics");
            if(!f.exists()){
                f.mkdirs();
            }
            String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
            String fN = "check." + fileType;

            String realpath = PropUtil.getPropVal("ect.cer.photopath") + fN;
            Path filepath = Paths.get(realpath);
            OutputStream os = Files.newOutputStream(filepath);
            os.write(file.getBytes());
            os.close();
            return realpath;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String parseWesternYear(String bd){
        String rs = "";
        if(bd.indexOf("-") > -1){
            bd = bd.replaceAll("-","/");
        }
        String[] ymd = bd.split("/");
        if(ymd.length > 0){
            String y = ymd[0];
            int iy = (Integer.parseInt(y) + 1911);
            String yr = String.valueOf(iy);
            rs = yr + "/" + ymd[1];
            if(ymd.length > 2){
                rs += "/" + ymd[2];
            }
        }
        return rs;
    }

    private String getIpAddr(MultipartHttpServletRequest request) {
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
}
