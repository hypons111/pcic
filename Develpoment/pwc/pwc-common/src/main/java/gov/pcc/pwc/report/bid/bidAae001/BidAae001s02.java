package gov.pcc.pwc.report.bid.bidAae001;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;
import org.apache.maven.shared.utils.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae001s02 extends AbstractReport {

    public BidAae001s02() {
        super(BidReport.BidAae001s02);
    }

    private List<BidPrjSubcoDTO> bidPrjSubcoDTOs;

    public List<BidPrjSubcoDTO> getBidPrjSubcoDTOs() {
        return bidPrjSubcoDTOs;
    }

    public void setBidPrjSubcoDTOs(List<BidPrjSubcoDTO> bidPrjSubcoDTOs) {
        this.bidPrjSubcoDTOs = bidPrjSubcoDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        for (BidPrjSubcoDTO bidPrjSubcoDTO : bidPrjSubcoDTOs) {
            Map<String, Object> map = new HashMap<>();
            map.put("subcoNo", "分包(協力)廠商" + bidPrjSubcoDTO.getSubcoNo() + "：" + bidPrjSubcoDTO.getSubCcutName());
            String metStr = "";
            if (empty2Zero(bidPrjSubcoDTO.getMet01v()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet01())) {
                metStr = metStr + "預拌混凝土供應量 " + bidPrjSubcoDTO.getMet01v() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet01bv()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet01b())) {
                metStr = metStr + "機拌混凝土供應量 " + bidPrjSubcoDTO.getMet01bv() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet01cv()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet01c())) {
                metStr = metStr + "瀝青混凝土供應量 " + bidPrjSubcoDTO.getMet01cv() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet01dv()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet01d())) {
                metStr = metStr + "水泥砂漿供應量 " + bidPrjSubcoDTO.getMet01dv() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet02v()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet02())) {
                metStr = metStr + "砂供應量 " + bidPrjSubcoDTO.getMet02v() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet02bv()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet02b())) {
                metStr = metStr + "級配供應量 " + bidPrjSubcoDTO.getMet02bv() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet02fv()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet02f())) {
                metStr = metStr + "clsm供應量 " + bidPrjSubcoDTO.getMet02fv() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet04v()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet04())) {
                metStr = metStr + "土石方供應量 " + bidPrjSubcoDTO.getMet04v() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet05v()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet05())) {
                metStr = metStr + "低密度再生透水混凝土供應量 " + bidPrjSubcoDTO.getMet05v() + " 立方米" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet03v()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet03())) {
                metStr = metStr + "鋼筋供應量 " + bidPrjSubcoDTO.getMet03v() + " 噸" + "\n";
            }
            if (empty2Zero(bidPrjSubcoDTO.getMet03bv()).compareTo(BigDecimal.ZERO) > 0 || StringUtils.equalsIgnoreCase("Y", bidPrjSubcoDTO.getMet03b())) {
                metStr = metStr + "鋼板型鋼供應量 " + bidPrjSubcoDTO.getMet03bv() + " 噸" + "\n";
            }
            map.put("met", metStr.trim());
            list.add(map);
        }
        return list;
    }

}
