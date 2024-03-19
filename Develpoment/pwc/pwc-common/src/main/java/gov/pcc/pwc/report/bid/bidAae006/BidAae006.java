package gov.pcc.pwc.report.bid.bidAae006;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.AdmSysCodeService;
import gov.pcc.pwc.service.BidPlaceService;
import gov.pcc.pwc.service.dto.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae006 extends AbstractReport {



    public BidAae006() {
        super(BidReport.BidAae006);
    }

    private VwBidProjectDTO vwBidProjectDTO;

    private BidProjectDTO bidProjectDTO;

    private BidPrjSafeDTO bidPrjSafeDTO;

    private String ccut;

    private String scut;

    private String typeCode;

    private String placeCode;

    private String ctUnit;

    private String sType;



    public VwBidProjectDTO getVwBidProjectDTO() {
        return vwBidProjectDTO;
    }

    public void setVwBidProjectDTO(VwBidProjectDTO vwBidProjectDTO) {
        this.vwBidProjectDTO = vwBidProjectDTO;
    }

    public BidProjectDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public BidPrjSafeDTO getBidPrjSafeDTO() {
        return bidPrjSafeDTO;
    }

    public void setBidPrjSafeDTO(BidPrjSafeDTO bidPrjSafeDTO) {
        this.bidPrjSafeDTO = bidPrjSafeDTO;
    }

    public String getCcut() {
        return ccut;
    }

    public void setCcut(String ccut) {
        this.ccut = ccut;
    }

    public String getScut() {
        return scut;
    }

    public void setScut(String scut) {
        this.scut = scut;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getCtUnit() {
        return ctUnit;
    }

    public void setCtUnit(String ctUnit) {
        this.ctUnit = ctUnit;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    // 表頭參數 DTO 轉成 MAP，才能做數據處理，對應ireport的parameter
    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this.transBean2Map(vwBidProjectDTO));
        map.putAll(this.transBean2Map(bidProjectDTO));
        map.putAll(this.transBean2Map(bidPrjSafeDTO));

        String name = vwBidProjectDTO.getProjectName();
        if (StringUtils.isNotBlank(name)) {
            map.put("name", name);
        }

        // 工程類別
        map.put("typeCode", typeCode);


        // 契約金額
        BigDecimal ctSum = vwBidProjectDTO.getCtSum();
        if (ctSum != null) {
            map.put("ctSum", numberFormat(ctSum, "#,##0.000") + "千元");
        }

        //契約金額 => 變更設計後
        BigDecimal ctSumF = vwBidProjectDTO.getCtSumF();
        if(ctSumF != null){
            map.put("ctSumF", this.numberFormat(ctSumF, "#,##0.000") + "千元");
        }
        String ctnid = bidProjectDTO.getCntid();
        map.put("ctnid",ctnid);

        // 縣市鄉鎮
        map.put("placeCode", placeCode);


        // 保險日期起
        LocalDate issStartDate = bidProjectDTO.getIssStartDate();
        if(issStartDate != null){
            map.put("issStartDate", parseLocalDateToYYYMMDD(issStartDate));
        }else {
            map.put("issStartDate", "年月日");
        }


        // 保險日期迄
        LocalDate issEndDate = bidProjectDTO.getIssEndDate();
        if(issEndDate != null){
            map.put("issEndDate", parseLocalDateToYYYMMDD(issEndDate));
        }else {
            map.put("issEndDate", "年月日");
        }


        // 保險金額
        BigDecimal issCost = bidProjectDTO.getIssCost();
        if(issCost != null){
            map.put("issCost", this.numberFormat(issCost, "#,##0.000") + "千元");
        }

        // 保險號碼
        String issNo = bidProjectDTO.getIssNo();
        if(StringUtils.isNotBlank(issNo)){
            map.put("issNo",issNo);
        }else {
            map.put("issNo","(未填)");
        }

        // 後續加保狀況
        String issRmk = bidProjectDTO.getIssRmk();
        if(StringUtils.isNotBlank(issRmk)){
            map.put("issRmk",issRmk);
        }

        // 承包商
        if(ccut != null && ccut.length() >0){
            map.put("ccut",ccut);
        }else {
            map.put("ccut","(未填)");
        }

        // 監造單位
        if(scut != null && scut.length() > 0){
            map.put("scut",scut);
        }else {
            map.put("scut","(未填)");
        }

        // 發生日期
        LocalDate sDate = bidPrjSafeDTO.getSdate();
        if(sDate != null) {
            map.put("sDate", parseLocalDateToYYYMMDD(sDate));
        }

        // 管轄單位
        map.put("ctUnit", ctUnit);

        // 災害類型
        map.put("sType", sType);

        // 罹災時災情
        String disasterScript = bidPrjSafeDTO.getDisasterScript();
        if(StringUtils.isNotBlank(disasterScript)){
            map.put("disasterScript",disasterScript);
        }else {
            map.put("disasterScript","(未填)");
        }

        // 提報勞動部日期
        LocalDate peDate = bidPrjSafeDTO.getPeDate();
        if(peDate != null){
            map.put("peDate", parseLocalDateToYYYMMDD(peDate));
        }else {
            map.put("peDate","(未填)");
        }

        // 提報文號
        String prmNo = bidPrjSafeDTO.getPrmNo();
        if(StringUtils.isNotBlank(prmNo)){
            map.put("prmNo", prmNo);
        }else {
            map.put("prmNo","(未填)");
        }

        // 勞動部核定日期
        LocalDate meDate = bidPrjSafeDTO.getMeDate();
        if(meDate != null){
            map.put("meDate", parseLocalDateToYYYMMDD(meDate));
        }else {
            map.put("meDate","(未填)");
        }

        // 核定文號
        String mrmNo = bidPrjSafeDTO.getMrmNo();
        if(StringUtils.isNotBlank(mrmNo)){
            map.put("mrmNo", mrmNo);
        }else {
            map.put("mrmNo","(未填)");
        }

        // 備註
        String remark = bidPrjSafeDTO.getRemark();
        if(StringUtils.isNotBlank(remark)){
            map.put("remark", remark);
        }else {
            map.put("remark","(未填)");
        }

        return map;
    }


}
