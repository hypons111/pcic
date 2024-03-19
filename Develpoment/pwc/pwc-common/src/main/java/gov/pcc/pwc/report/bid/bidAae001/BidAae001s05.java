package gov.pcc.pwc.report.bid.bidAae001;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjStopwkDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae001s05 extends AbstractReport {

    public BidAae001s05() {
        super(BidReport.BidAae001s05);
    }

    private List<BidPrjStopwkDTO> bidPrjStopwkDTOs;

    public List<BidPrjStopwkDTO> getBidPrjStopwkDTOs() {
        return bidPrjStopwkDTOs;
    }

    public void setBidPrjStopwkDTOs(List<BidPrjStopwkDTO> bidPrjStopwkDTOs) {
        this.bidPrjStopwkDTOs = bidPrjStopwkDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        for (BidPrjStopwkDTO bidPrjStopwkDTO : bidPrjStopwkDTOs) {
            Map<String, Object> map = new HashMap<>();
            String startDateStr = "(未填)";
            LocalDate startDate = bidPrjStopwkDTO.getStartDate();
            if (startDate != null) {
                startDateStr = parseLocalDateToYYYMMDD(startDate);
            }
            map.put("startDate", startDateStr);
            String endDateStr = "(未填)";
            LocalDate endDate = bidPrjStopwkDTO.getEndDate();
            if (endDate != null) {
                endDateStr = parseLocalDateToYYYMMDD(endDate);
            } else {
                endDateStr = "(尚未復工)";
            }
            map.put("endDate", endDateStr);
            map.put("prmNo", empty2String(bidPrjStopwkDTO.getPrmNo()));
            map.put("remark", empty2String(bidPrjStopwkDTO.getRemark()));
            list.add(map);
        }
        return list;
    }

}
