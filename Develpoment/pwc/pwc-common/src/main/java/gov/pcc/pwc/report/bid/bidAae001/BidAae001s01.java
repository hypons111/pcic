package gov.pcc.pwc.report.bid.bidAae001;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjGdfkDTO;
import org.apache.maven.shared.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae001s01 extends AbstractReport {

    public BidAae001s01() {
        super(BidReport.BidAae001s01);
    }

    private List<BidPrjGdfkDTO> bidPrjGdfkDTOs;

    public List<BidPrjGdfkDTO> getBidPrjGdfkDTOs() {
        return bidPrjGdfkDTOs;
    }

    public void setBidPrjGdfkDTOs(List<BidPrjGdfkDTO> bidPrjGdfkDTOs) {
        this.bidPrjGdfkDTOs = bidPrjGdfkDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        for (BidPrjGdfkDTO bidPrjGdfkDTO : bidPrjGdfkDTOs) {
            Map<String, Object> map = new HashMap<>();
            String useTypeStr = "";
            String useType = bidPrjGdfkDTO.getUseType();
            if (StringUtils.equalsIgnoreCase("A", useType)) {
                useTypeStr = "本案工程採監督付款繼續施作";
            } else if (StringUtils.equalsIgnoreCase("B", useType)) {
                useTypeStr = "本案工程採連帶保證繼續施作";
            }
            map.put("useType", useTypeStr);
            map.put("startDate", parseLocalDateToYYYMMDD(bidPrjGdfkDTO.getStartDate()));
            map.put("prmNo", empty2String(bidPrjGdfkDTO.getPrmNo()));
            map.put("nccUnitName", empty2String(bidPrjGdfkDTO.getNccUnitName()));
            map.put("lctSum", numberFormat(bidPrjGdfkDTO.getLctSum(), "#,##0.000"));
            map.put("remark", empty2String(bidPrjGdfkDTO.getRemark()));
            list.add(map);
        }
        return list;
    }

}
