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

public class BidAae009B extends AbstractReport {

    public BidAae009B() {
        super(BidReport.BidAae009B);
    }

    private BidProjectInfoDTO bidProjectDTO;

    private BidPrjPayDTO bidPrjPayDTO;

    public BidProjectInfoDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectInfoDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public BidPrjPayDTO getBidPrjPayDTO() {
        return bidPrjPayDTO;
    }

    public void setBidPrjPayDTO(BidPrjPayDTO bidPrjPayDTO) {
        this.bidPrjPayDTO = bidPrjPayDTO;
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
        map.putAll(this.transBean2Map(bidProjectDTO));
        map.putAll(this.transBean2Map(bidPrjPayDTO));

        LocalDate trueDate = bidPrjPayDTO.getTrueDate();
        map.put("trueDateYYYMMDD", parseLocalDateToYYYMMDD(trueDate));

        LocalDate payDate = bidPrjPayDTO.getPayDate();
        if (payDate != null) {
            map.put("payDateYYYMM", parseLocalDateToYYYMM(payDate));
        } else {
            map.put("payDateYYYMM", "___年度__月份");
        }

        String monNo = bidPrjPayDTO.getMonNo();
        map.put("monNo", StringUtils.leftPad(monNo, 3, '0'));

        String cntid = bidProjectDTO.getCntid();
        if (StringUtils.isNotBlank(cntid)) {
            map.put("cntid", "合約編號：  " + cntid);
        } else {
            map.put("cntid", "未定合約");
        }

        BigDecimal payMonT = bidPrjPayDTO.getPayMonT();
        if (payMonT != null) {
            map.put("payMonT", numberFormat(payMonT.multiply(BigDecimal.valueOf(1000))));
        } else {
            map.put("payMonT", "(未填)");
        }

        BigDecimal payMonU = bidPrjPayDTO.getPayMonU();
        if (payMonU != null) {
            map.put("payMonU", numberFormat(payMonU.multiply(BigDecimal.valueOf(1000))));
        } else {
            map.put("payMonU", "(未填)");
        }

        BigDecimal payMon = bidPrjPayDTO.getPayMon();
        if (payMon != null) {
            map.put("payMon", numberFormat(payMon.multiply(BigDecimal.valueOf(1000))));
        } else {
            map.put("payMon", "(未填)");
        }

        BigDecimal payMonS = bidPrjPayDTO.getPayMonS();
        if (payMonS != null) {
            map.put("payMonS", numberFormat(payMonS.multiply(BigDecimal.valueOf(1000))));
        } else {
            map.put("payMonS", "(未填)");
        }

        BigDecimal notPay = empty2Zero(payMonT).subtract(empty2Zero(payMonS));
        map.put("notPay", numberFormat(notPay.multiply(BigDecimal.valueOf(1000))));

        list.add(map);
        return list;
    }

}
