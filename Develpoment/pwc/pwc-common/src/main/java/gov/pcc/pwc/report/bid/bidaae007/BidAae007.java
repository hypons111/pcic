package gov.pcc.pwc.report.bid.bidaae007;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae007 extends AbstractReport {

    public BidAae007() {
        super(BidReport.BidAae007);
    }

    private VwBidProjectDTO vwBidProjectDTO;

    private BidProjectDTO bidProjectDTO;

    private BidUnit10DTO wkutDTO;

    private BidPrjEnviDTO bidPrjEnviDTO;

    private BidProjectInfoDTO bidProjectInfoDTO;

    private String ccut;

    private String scut;

    private String typeCode;

    private String placeCode;

    private String ctUnit;

    private String sType;


    public BidUnit10DTO getWkutDTO() {return wkutDTO;}

    public void setWkutDTO(BidUnit10DTO wkutDTO) {this.wkutDTO = wkutDTO;}

    public VwBidProjectDTO getVwBidProjectDTO() {return vwBidProjectDTO;}

    public void setVwBidProjectDTO(VwBidProjectDTO vwBidProjectDTO) {this.vwBidProjectDTO = vwBidProjectDTO;}

    public BidProjectDTO getBidProjectDTO() {return bidProjectDTO;}

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {this.bidProjectDTO = bidProjectDTO;}

    public BidPrjEnviDTO getBidPrjEnviDTO() {return bidPrjEnviDTO;}

    public void setBidPrjEnviDTO(BidPrjEnviDTO bidPrjEnviDTO) {this.bidPrjEnviDTO = bidPrjEnviDTO;}



    public BidProjectInfoDTO getBidProjectInfoDTO() {return bidProjectInfoDTO;}

    public void setBidProjectInfoDTO(BidProjectInfoDTO bidProjectInfoDTO) {this.bidProjectInfoDTO = bidProjectInfoDTO;}

    public String getCcut() {return ccut;}

    public void setCcut(String ccut) {this.ccut = ccut;}

    public String getScut() {return scut;}

    public void setScut(String scut) {this.scut = scut;}

    public String getTypeCode() {return typeCode;}

    public void setTypeCode(String typeCode) {this.typeCode = typeCode;}

    public String getPlaceCode() {return placeCode;}

    public void setPlaceCode(String placeCode) {this.placeCode = placeCode;}

    public String getCtUnit() {return ctUnit;}

    public void setCtUnit(String ctUnit) {this.ctUnit = ctUnit;}

    public String getsType() {return sType;}

    public void setsType(String sType) {this.sType = sType;}

//    public List<BidUnit10DTO> getCcuts() {return ccuts;}

//    public void setCcuts(List<BidUnit10DTO> ccuts) {this.ccuts = ccuts;}



    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }


    // 表頭參數 DTO 轉成 MAP，才能做數據處理，對應ireport的parameter
    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        Map<String, Object> map = new HashMap<>();

        String wkut = wkutDTO.getName();
        if(wkut == null ){
            map.put("wkutName","(未填)");
        }else {
            map.put("wkutName",wkut);
        }



//        map.put("wkutName", bidProjectDTO.getWkut());
        map.put("name", bidProjectDTO.getName());
        String ctsumStr = "(未填)千元";
        BigDecimal ctsum = bidProjectDTO.getCtSum();
        if (ctsum != null) {
            ctsumStr = numberFormat(ctsum, "#,##0.000") + " 千元";
        }
        map.put("ctSum", ctsumStr);
//        map.put("ctSum", bidProjectDTO.getCtSum());
        String ctsumFStr = "(未填)千元";
        BigDecimal ctsumF = bidProjectDTO.getCtSumF();
        if (ctsumF != null) {
            ctsumFStr = numberFormat(ctsumF, "#,##0.000") + " 千元";
        }
        map.put("ctSumF", ctsumFStr);
//        map.put("ctSumF", bidProjectDTO.getCtSumF());
        map.put("place", bidProjectDTO.getPlace());
        map.put("typeCode", typeCode);
        map.put("cntid", bidProjectDTO.getCntid());
        map.put("placeCode", placeCode);
//        String ccut = "";
//        for (BidUnit10DTO bidUnit10DTO : ccut) {
//            ccut = ccut + bidUnit10DTO.getName() + "\n";
//        }
        map.put("ccut", ccut.trim());
        map.put("sDate", parseLocalDateToYYYMMDD(bidPrjEnviDTO.getSdate()));
        map.put("ctUnit", ctUnit);
        map.put("violateFact", bidPrjEnviDTO.getViolateFact());
        map.put("violateDate", bidPrjEnviDTO.getViolateDate() != null ? parseLocalDateToYYYMMDD(bidPrjEnviDTO.getViolateDate()) : "(未填)");
        map.put("prmTitle", bidPrjEnviDTO.getPrmTitle());
        map.put("stype", sType);
        map.put("prmNo", bidPrjEnviDTO.getPrmNo());
//        map.put("ccut", ccut);
//        map.put("scut", scut);
        map.put("ceDate", bidProjectDTO.getCeDate() != null ? parseLocalDateToYYYMMDD(bidProjectDTO.getCeDate()) : "(未填)");
        map.put("contain", bidProjectDTO.getContain());
//        String scut = "";
//        for (BidUnit10DTO bidUnit10DTO : scuts) {
//            scut = scut + bidUnit10DTO.getName() + "\n";
//        }
        map.put("scut", scut.trim());
        list.add(map);
        return list;
    }

}
