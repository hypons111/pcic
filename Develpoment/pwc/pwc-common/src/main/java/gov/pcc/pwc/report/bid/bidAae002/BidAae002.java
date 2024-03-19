package gov.pcc.pwc.report.bid.bidAae002;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae002 extends AbstractReport {

    public BidAae002() {
        super(BidReport.BidAae002);
    }

    private BidProjectInfoDTO bidProjectInfoDTO;

    private BidProjectDTO bidProjectDTO;

    private List<BidUnit10DTO> ccuts;

    private List<BidUnit10DTO> scuts;

    public BidProjectInfoDTO getBidProjectInfoDTO() {
        return bidProjectInfoDTO;
    }

    public void setBidProjectInfoDTO(BidProjectInfoDTO bidProjectInfoDTO) {
        this.bidProjectInfoDTO = bidProjectInfoDTO;
    }

    public BidProjectDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public List<BidUnit10DTO> getCcuts() {
        return ccuts;
    }

    public void setCcuts(List<BidUnit10DTO> ccuts) {
        this.ccuts = ccuts;
    }

    public List<BidUnit10DTO> getScuts() {
        return scuts;
    }

    public void setScuts(List<BidUnit10DTO> scuts) {
        this.scuts = scuts;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        Map<String, Object> map = new HashMap<>();
        map.put("wkutName", bidProjectInfoDTO.getWkutName());
        map.put("name", bidProjectInfoDTO.getName());
        map.put("place", bidProjectDTO.getPlace());
        map.put("ctsum", numberFormat(bidProjectInfoDTO.getCtSum()));
        map.put("cntid", bidProjectInfoDTO.getCntid());
        map.put("asDate", parseLocalDateToYYYMMDD(bidProjectInfoDTO.getAsDate()));
        String ccut = "";
        for (BidUnit10DTO bidUnit10DTO : ccuts) {
            ccut = ccut + bidUnit10DTO.getName() + "\n";
        }
        map.put("ccut", ccut.trim());
        map.put("wDays", bidProjectDTO.getwDays());
        map.put("csDate", parseLocalDateToYYYMMDD(bidProjectDTO.getCsDate()));
        map.put("ceDate", parseLocalDateToYYYMMDD(bidProjectDTO.getCeDate()));
        map.put("contain", bidProjectDTO.getContain());
        String scut = "";
        for (BidUnit10DTO bidUnit10DTO : scuts) {
            scut = scut + bidUnit10DTO.getName() + "\n";
        }
        map.put("scut", scut.trim());
        map.put("contain", bidProjectDTO.getContain());
        map.put("deptName", bidProjectInfoDTO.getDeptName());

        list.add(map);
        return list;
    }

}
