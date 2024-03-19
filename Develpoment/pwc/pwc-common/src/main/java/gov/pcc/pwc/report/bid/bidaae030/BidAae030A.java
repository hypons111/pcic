package gov.pcc.pwc.report.bid.bidaae030;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae030A extends AbstractReport {

    public BidAae030A() {
        super(BidReport.BidAae030A);
    }

    private VwBidProjectDTO vwBidProjectDTO;

    private BidProjectDTO bidProjectDTO;

    private BidUnit10DTO bidUnit10DTO;

    private List<BidPrjWksorcDTO> bidPrjWksorcDTO;

    private BidPlaceDTO bidPlaceDTO;

    private String ccut;

    private String scut;

    private String place;


    public BidUnit10DTO getBidUnit10DTO() {return bidUnit10DTO;}

    public void setBidUnit10DTO(BidUnit10DTO bidUnit10DTO) {this.bidUnit10DTO = bidUnit10DTO;}

    public VwBidProjectDTO getVwBidProjectDTO() {return vwBidProjectDTO;}

    public void setVwBidProjectDTO(VwBidProjectDTO vwBidProjectDTO) {this.vwBidProjectDTO = vwBidProjectDTO;}

    public BidProjectDTO getBidProjectDTO() {return bidProjectDTO;}

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {this.bidProjectDTO = bidProjectDTO;}

    public List<BidPrjWksorcDTO> getBidPrjWksorcDTO() {return bidPrjWksorcDTO;}

    public void setBidPrjWksorcDTO(List<BidPrjWksorcDTO> bidPrjWksorcDTO) {this.bidPrjWksorcDTO = bidPrjWksorcDTO;}

    public BidPlaceDTO getBidPlaceDTO() {return bidPlaceDTO;}

    public void setBidPlaceDTO(BidPlaceDTO bidPlaceDTO) {this.bidPlaceDTO = bidPlaceDTO;}

    public String getCcut() {return ccut;}

    public void setCcut(String ccut) {this.ccut = ccut;}

    public String getScut() {return scut;}

    public void setScut(String scut) {this.scut = scut;}

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

        if(bidPrjWksorcDTO != null && bidPrjWksorcDTO.size() >0) {
            for (BidPrjWksorcDTO item : bidPrjWksorcDTO) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put("syr", item.getSyr());
                map1.put("month", item.getMonth());
                map1.put("aperson02", item.getPerson02());
                map1.put("aworker01", item.getWorker01());
                map1.put("aworker02", item.getWorker02());
                map1.put("aworker03", item.getWorker03());
                map1.put("aworker04", item.getWorker04());
                map1.put("aworker05", item.getWorker05());
                map1.put("aworker06", item.getWorker06());
                map1.put("aworker07", item.getWorker07());
                map1.put("aworker08", item.getWorker08());
                map1.put("aworker09", item.getWorker09());
                map1.put("aworker10", item.getWorker10());
                map1.put("aworker11", item.getWorker11());
                map1.put("aworker12", item.getWorker12());
                map1.putAll(map);
                list.add(map1);
            }
        }
        return list;
    }

}
