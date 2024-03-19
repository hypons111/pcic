package gov.pcc.pwc.report.bid.bidaae022;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae022 extends AbstractReport {

    public BidAae022() {
        super(BidReport.BidAae022);
    }



    private BidProjectDTO bidProjectDTO;

    private BidPrjSegmsetDTO bidPrjSegmsetDTO;

    private String agreeReson;


    public BidProjectDTO getBidProjectDTO() {return bidProjectDTO;}

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {this.bidProjectDTO = bidProjectDTO;}

    public BidPrjSegmsetDTO getBidPrjSegmsetDTO() {return bidPrjSegmsetDTO;}

    public void setBidPrjSegmsetDTO(BidPrjSegmsetDTO bidPrjSegmsetDTO) {this.bidPrjSegmsetDTO = bidPrjSegmsetDTO;}

    public String getAgreeReson() {return agreeReson;}

    public void setAgreeReson(String agreeReson) {this.agreeReson = agreeReson;}

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
        map.put("agreeDate", parseLocalDateToYYYMMDD(bidPrjSegmsetDTO.getAgreeDate()));
        map.put("agreeNum",bidPrjSegmsetDTO.getAgreeNum() );
        map.put("agreeReson",agreeReson );
        String agreeValueStr = "";
        BigDecimal agreeValue = bidPrjSegmsetDTO.getAgreeValue();
        if (agreeValue != null) {
            agreeValueStr = numberFormat(agreeValue, "#,##0.000");
        }
        map.put("agreeValue",agreeValueStr);
        map.put("tearDownDate",parseLocalDateToYYYMMDD(bidPrjSegmsetDTO.getTearDownDate()));
        String totalValueStr = "";
        BigDecimal totalValue = bidPrjSegmsetDTO.getAgreeValue();
        if (totalValue != null) {
            totalValueStr = numberFormat(totalValue, "#,##0.000");
        }
        map.put("totalValue", totalValueStr);
        map.put("valueRemark", bidPrjSegmsetDTO.getValueRemark());
        list.add(map);
        return list;
    }

}
