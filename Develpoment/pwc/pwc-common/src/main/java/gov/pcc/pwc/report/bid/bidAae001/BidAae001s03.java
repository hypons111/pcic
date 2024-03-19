package gov.pcc.pwc.report.bid.bidAae001;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjFalsDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae001s03 extends AbstractReport {

    public BidAae001s03() {
        super(BidReport.BidAae001s03);
    }

    private List<BidPrjFalsDTO> bidPrjFalsDTOs;

    public List<BidPrjFalsDTO> getBidPrjFalsDTOs() {
        return bidPrjFalsDTOs;
    }

    public void setBidPrjFalsDTOs(List<BidPrjFalsDTO> bidPrjFalsDTOs) {
        this.bidPrjFalsDTOs = bidPrjFalsDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        for (BidPrjFalsDTO bidPrjFalsDTO : bidPrjFalsDTOs) {
            Map<String, Object> map = new HashMap<>();
            map.put("newDate", bidPrjFalsDTO.getYear() + "年"
                    + bidPrjFalsDTO.getMonth() + "月修正預定開工日期：原預定開工日期："
                    + parseLocalDateToYYYMMDD(bidPrjFalsDTO.getOldDate()) + "，修正為："
                    + parseLocalDateToYYYMMDD(bidPrjFalsDTO.getNewDate()));
            map.put("mftName", empty2String(bidPrjFalsDTO.getMftName()) + "\n" + empty2String(bidPrjFalsDTO.getMfas()));
            map.put("mfrt", empty2String(bidPrjFalsDTO.getMfrt()));
            list.add(map);
        }
        return list;
    }

}
