package gov.pcc.pwc.report.bid.bidAae009;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjPayDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae009A extends AbstractReport {

    public BidAae009A() {
        super(BidReport.BidAae009A);
    }

    private BidProjectInfoDTO bidProjectDTO;

    private List<BidPrjPayDTO> bidPrjPayDTOs;

    public BidProjectInfoDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectInfoDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public List<BidPrjPayDTO> getBidPrjPayDTOs() {
        return bidPrjPayDTOs;
    }

    public void setBidPrjPayDTOs(List<BidPrjPayDTO> bidPrjPayDTOs) {
        this.bidPrjPayDTOs = bidPrjPayDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this.transBean2Map(bidProjectDTO));

        String plnName = bidProjectDTO.getPlnName();
        if (StringUtils.isBlank(plnName)) {
            map.put("plnName", "(未填)");
        }

        String plnNo = bidProjectDTO.getPlnNo();
        if (StringUtils.isBlank(plnNo)) {
            map.put("plnNo", "(未填)");
        }

        String bosName = bidProjectDTO.getBosName();
        if (StringUtils.isBlank(bosName)) {
            map.put("bosName", "(未填)");
        }

        String deptName = bidProjectDTO.getDeptName();
        if (StringUtils.isBlank(deptName)) {
            map.put("deptName", "(未填)");
        }

        BigDecimal bdgt1 = bidProjectDTO.getBdgt1();
        if (bdgt1 != null) {
            map.put("bdgt1", numberFormat(bdgt1, "#,##0.000") + "千元");
        } else {
            map.put("bdgt1", "(未填)");
        }

        BigDecimal ctSum = bidProjectDTO.getCtSum();
        if (ctSum != null) {
            map.put("ctSum", numberFormat(ctSum, "#,##0.000") + "千元");
        } else {
            map.put("ctSum", "(未填)");
        }

        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        for (BidPrjPayDTO dto : bidPrjPayDTOs) {
            Map<String, Object> map = new HashMap<>();
            String monNo = dto.getMonNo();
            map.put("monNo", StringUtils.leftPad(monNo, 3, '0'));

            BigDecimal payMonT = dto.getPayMonT();
            if (payMonT != null) {
                map.put("payMonT", this.numberFormat(payMonT, "#,##0.000"));
            } else {
                map.put("payMonT", "(未填)");
            }

            BigDecimal payMonU = dto.getPayMonU();
            if (payMonU != null) {
                map.put("payMonU", this.numberFormat(payMonU, "#,##0.000"));
            } else {
                map.put("payMonU", "(未填)");
            }

            LocalDate trueDate = dto.getTrueDate();
            map.put("trueDateStr", parseLocalDateToYYYMMDD(trueDate, ""));

            BigDecimal trueMon = dto.getTrueMon();
            if (trueMon != null) {
                map.put("trueMon", numberFormat(trueMon, "#,##0.000"));
            } else {
                map.put("trueMon", "(未填)");
            }

            LocalDate tenDate = dto.getTenDate();
            map.put("tenDateStr", parseLocalDateToYYYMMDD(tenDate, ""));

            BigDecimal tenMon = dto.getTenMon();
            if (tenMon != null) {
                map.put("tenMon", numberFormat(tenMon, "#,##0.000"));
            } else {
                map.put("tenMon", "(未填)");
            }

            LocalDate payDate = dto.getPayDate();
            map.put("payDateStr", parseLocalDateToYYYMMDD(payDate, ""));

            BigDecimal payMon = dto.getPayMon();
            if (payMon != null) {
                map.put("payMon", numberFormat(payMon, "#,##0.000"));
            } else {
                map.put("payMon", "(未填)");
            }

            BigDecimal payMonS = dto.getPayMonS();
            if (payMonS != null) {
                map.put("payMonS", numberFormat(payMonS, "#,##0.000"));
            } else {
                map.put("payMonS", "(未填)");
            }

            BigDecimal notPay = empty2Zero(payMonT).subtract(empty2Zero(payMonS));
            map.put("notPay", numberFormat(notPay, "#,##0.000"));

            map.put("payRemark", dto.getPayRemark());

            list.add(map);
        }
        return list;
    }

}
