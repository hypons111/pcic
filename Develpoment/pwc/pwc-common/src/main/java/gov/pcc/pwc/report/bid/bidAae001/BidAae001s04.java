package gov.pcc.pwc.report.bid.bidAae001;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import org.codehaus.plexus.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae001s04 extends AbstractReport {

    public BidAae001s04() {
        super(BidReport.BidAae001s04);
    }

    private List<BidPrjModifyDTO> bidPrjModifyDTOs;

    public List<BidPrjModifyDTO> getBidPrjModifyDTOs() {
        return bidPrjModifyDTOs;
    }

    public void setBidPrjModifyDTOs(List<BidPrjModifyDTO> bidPrjModifyDTOs) {
        this.bidPrjModifyDTOs = bidPrjModifyDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        for (BidPrjModifyDTO bidPrjModifyDTO : bidPrjModifyDTOs) {
            Map<String, Object> map = new HashMap<>();
            String mdyPressStr = "";
            if (StringUtils.equalsIgnoreCase("Y", bidPrjModifyDTO.getMdyPress())) {
                String newAmtStr = "(未填)";
                BigDecimal newAmt = bidPrjModifyDTO.getNewAmt();
                if (newAmt != null) {
                    newAmtStr = numberFormat(newAmt);
                }
                String oldAmtStr = "(未填)";
                BigDecimal oldAmt = bidPrjModifyDTO.getOldAmt();
                if (oldAmt != null) {
                    oldAmtStr = numberFormat(oldAmt);
                }
                mdyPressStr = "變更設計後金額：" + newAmtStr + " 千元(原 " + oldAmtStr + " 千元)\n";
            }
            LocalDate newDate = bidPrjModifyDTO.getNewDate();
            if (newDate != null) {
                mdyPressStr = mdyPressStr + "變更設計後預定完工日期：" + parseLocalDateToYYYMMDD(newDate) + "\n"
                        + "准延天數：" +  bidPrjModifyDTO.getnDays() + " 天　(原 "
                        + parseLocalDateToYYYMMDD(bidPrjModifyDTO.getOldDate()) + ")";
            }
            map.put("mdyPress", mdyPressStr.trim());
            String mergeNameStr = "";
            String mergeName = bidPrjModifyDTO.getMergeName();
            if (StringUtils.isBlank(mergeName)) {
                mergeNameStr = parseLocalDateToYYYMMDD(bidPrjModifyDTO.getChgDate()) + " "
                        + bidPrjModifyDTO.getOkNo();
            } else {
                mergeNameStr = parseLocalDateToYYYMMDD(bidPrjModifyDTO.getChgDate()) + " "
                        + bidPrjModifyDTO.getOkNo() + "\n" + mergeName;
            }
            map.put("mergeName", mergeNameStr);
            list.add(map);
        }
        return list;
    }

}
