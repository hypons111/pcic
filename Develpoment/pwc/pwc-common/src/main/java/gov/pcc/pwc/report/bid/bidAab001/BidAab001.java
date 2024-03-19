package gov.pcc.pwc.report.bid.bidAab001;


import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAab001 extends AbstractReport {

    public BidAab001() {
        super(BidReport.BidAab001);
    }

    private BidProjectInfoDTO bidProjectDTO;

    private List<BidPrjMonthDTO> bidPrjMonthDTOs;

    public BidProjectInfoDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectInfoDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public List<BidPrjMonthDTO> getBidPrjMonthDTOs() {
        return bidPrjMonthDTOs;
    }

    public void setBidPrjMonthDTOs(List<BidPrjMonthDTO> bidPrjMonthDTOs) {
        this.bidPrjMonthDTOs = bidPrjMonthDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this.transBean2Map(bidProjectDTO));

        String planName = bidProjectDTO.getPlnName();
        if (StringUtils.isNotBlank(planName)) {
            map.put("planName", "(未填)");
        }

        String planNo = bidProjectDTO.getPlnNo();
        if (StringUtils.isNotBlank(planNo)) {
            map.put("planName", "(未填)");
        }

        BigDecimal bdgt1 = bidProjectDTO.getBdgt1();
        if (bdgt1 != null) {
            map.put("bdgt1", numberFormat(bdgt1, "#,##0.000#") + "千元");
        }

        BigDecimal ctSum = bidProjectDTO.getCtSum();
        if (ctSum != null) {
            map.put("ctSum", numberFormat(ctSum, "#,##0.000#") + "千元");
        }

        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        for(BidPrjMonthDTO dto : bidPrjMonthDTOs){
            Map<String, Object> map = new HashMap<>();
            map.put("yearAndMonth", dto.getYr()+"/"+dto.getMonth());
            map.put("ptrate",dto.getPtrate()!= null? (numberFormat(dto.getPtrate(), "#,##0.00#")+"%"):"(未填)");
            map.put("pexp",dto.getPexp()!=null? (numberFormat(dto.getPexp(), "#,##0.00#")+"%"):"(未填)") ;
            map.put("item",dto.getItem());
            list.add(map);
        }
        return list;
    }

}
