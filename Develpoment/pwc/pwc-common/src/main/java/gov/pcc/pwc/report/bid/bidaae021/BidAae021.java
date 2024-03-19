package gov.pcc.pwc.report.bid.bidaae021;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae021 extends AbstractReport {

    public BidAae021() {
        super(BidReport.BidAae021);
    }

    private VwBidProjectDTO vwBidProjectDTO;

    private BidProjectDTO bidProjectDTO;

    private BidUnit10DTO bidUnit10DTO;

    private BidPrjFrwkrDTO bidPrjFrwkrDTO;

    private BidPlaceDTO bidPlaceDTO;

    private String ccut;

    private String scut;

    private String frKnd1;

    private String frKnd2;

    private String frKnd3;

    private String place;


    public BidUnit10DTO getBidUnit10DTO() {return bidUnit10DTO;}

    public void setBidUnit10DTO(BidUnit10DTO bidUnit10DTO) {this.bidUnit10DTO = bidUnit10DTO;}

    public VwBidProjectDTO getVwBidProjectDTO() {return vwBidProjectDTO;}

    public void setVwBidProjectDTO(VwBidProjectDTO vwBidProjectDTO) {this.vwBidProjectDTO = vwBidProjectDTO;}

    public BidProjectDTO getBidProjectDTO() {return bidProjectDTO;}

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {this.bidProjectDTO = bidProjectDTO;}

    public BidPrjFrwkrDTO getBidPrjFrwkrDTO() {return bidPrjFrwkrDTO;}

    public void setBidPrjFrwkrDTO(BidPrjFrwkrDTO bidPrjFrwkrDTO) {this.bidPrjFrwkrDTO = bidPrjFrwkrDTO;}

    public BidPlaceDTO getBidPlaceDTO() {return bidPlaceDTO;}

    public void setBidPlaceDTO(BidPlaceDTO bidPlaceDTO) {this.bidPlaceDTO = bidPlaceDTO;}

    public String getCcut() {return ccut;}

    public void setCcut(String ccut) {this.ccut = ccut;}

    public String getScut() {return scut;}

    public void setScut(String scut) {this.scut = scut;}

    public String getFrKnd1() {return frKnd1;}

    public void setFrKnd1(String frKnd1) {this.frKnd1 = frKnd1;}

    public String getFrKnd2() {return frKnd2;}

    public void setFrKnd2(String frKnd2) {this.frKnd2 = frKnd2;}

    public String getFrKnd3() {return frKnd3;}

    public void setFrKnd3(String frKnd3) {this.frKnd3 = frKnd3;}

    public String getPlace() {return place;}

    public void setPlace(String place) {this.place = place;}

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


        map.put("name", bidProjectDTO.getName());
        map.put("prjNo", bidProjectDTO.getPrjno());
        map.put("place", place);
        map.put("asDate", parseLocalDateToYYYMMDD(bidProjectDTO.getAsDate()));
        map.put("ceDate", parseLocalDateToYYYMMDD(bidProjectDTO.getCeDate()));
        String ctsumStr = "未填(千元)";
        BigDecimal ctsum = bidProjectDTO.getCtSum();
        if (ctsum != null) {
            ctsumStr = numberFormat(ctsum, "#,##0.000") + " (千元)";
        }
        map.put("ctSum", ctsumStr);
        String ckpmoneyStr = "";
        BigDecimal ckpmoney = bidProjectDTO.getCkpMoney();
        if (ckpmoney != null) {
            ckpmoneyStr = numberFormat(ckpmoney, "#,##0.000")+"(千元)";
        }
        map.put("ckpmoney", ckpmoneyStr);
        String wkut = bidUnit10DTO.getName();
        map.put("wkut",wkut);
        map.put("man", bidProjectDTO.getMan());
        map.put("tel", bidProjectDTO.getTel());
        map.put("scut",scut);
        map.put("ccut",ccut);
        map.put("wrkmo", bidProjectDTO.getWorkMemo());
        map.put("sDate", parseLocalDateToYYYMMDD(bidPrjFrwkrDTO.getSdate()));
        map.put("frWkr", bidPrjFrwkrDTO.getFrWkr().toString()+"人");
        map.put("frType", bidPrjFrwkrDTO.getFrType());
        map.put("frPno", bidPrjFrwkrDTO.getFrPno());
        map.put("frRmk", bidPrjFrwkrDTO.getFrRmk());
        String frRateStr = "";
        BigDecimal frRate = bidPrjFrwkrDTO.getFrRate();
        if (frRate != null) {
            frRateStr = numberFormat(frRate, "#,##0.00")+"%";
        }
        map.put("frRate", frRateStr);
        map.put("frKnd1", frKnd1);
        map.put("frKnd2", frKnd2);
        map.put("frKnd3", frKnd3);
        list.add(map);
        return list;
    }

}
