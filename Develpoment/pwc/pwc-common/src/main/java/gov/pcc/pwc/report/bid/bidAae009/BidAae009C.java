package gov.pcc.pwc.report.bid.bidAae009;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjPayDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae009C extends AbstractReport {

    public BidAae009C() {
        super(BidReport.BidAae009C);
    }

    private BidProjectInfoDTO bidProjectDTO;

    private BidPrjPayDTO bidPrjPayDTO;

    BigDecimal bvalInd1;

    BigDecimal cvalInd1;

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

    public BigDecimal getBvalInd1() {
        return bvalInd1;
    }

    public void setBvalInd1(BigDecimal bvalInd1) {
        this.bvalInd1 = bvalInd1;
    }

    public BigDecimal getCvalInd1() {
        return cvalInd1;
    }

    public void setCvalInd1(BigDecimal cvalInd1) {
        this.cvalInd1 = cvalInd1;
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

        String avalStr = "(未填)";
        BigDecimal payMon = bidPrjPayDTO.getPayMon();
        BigDecimal aval = BigDecimal.ZERO;
        if (payMon != null) {
            avalStr = "當期估驗款 " + numberFormat(payMon.multiply(BigDecimal.valueOf(1000))) + " 元 * 80%";
            aval = payMon.multiply(BigDecimal.valueOf(1000*0.8));
        }
        map.put("avalStr", avalStr);

        LocalDate payDate = bidPrjPayDTO.getPayDate();
        map.put("payDateYYYMM", parseLocalDateToYYYMM(payDate));
        map.put("bval", bvalInd1.toString());

        LocalDate actDate = bidProjectDTO.getActDate();
        map.put("actDateYYYMM", "___年度__月份");
        if (payDate != null) {
            map.put("actDateYYYMM", parseLocalDateToYYYMM(actDate));
        }
        map.put("cval", cvalInd1.toString());

        BigDecimal dval = ((bvalInd1.divide(cvalInd1).subtract(BigDecimal.ONE))
                .multiply(BigDecimal.valueOf(100)))
                .setScale(3, RoundingMode.HALF_UP);
        map.put("dval", numberFormat(dval, "##0.000"));

        BigDecimal eval = BigDecimal.ZERO;
        BigDecimal pays = empty2Zero(bidProjectDTO.getPays());
        BigDecimal ctSum = empty2Zero(bidProjectDTO.getCtSum());
        if (ctSum.compareTo(BigDecimal.ZERO) > 0) {
            eval = pays.divide(ctSum)
                    .multiply(BigDecimal.valueOf(100))
                    .setScale(2, RoundingMode.HALF_UP);;
        }
        map.put("eval", numberFormat(eval, "##0.00"));

        BigDecimal fval = BigDecimal.valueOf(1.05);
        map.put("fval", fval.toString());

        String gvalStr = "";
        BigDecimal dabs = dval.abs();
        BigDecimal gval = BigDecimal.ZERO;
        if (dabs.compareTo(BigDecimal.valueOf(2.5)) > 0) {
            gval = aval.multiply(BigDecimal.ONE.subtract(eval.multiply(BigDecimal.valueOf(0.01))))
                    .multiply(dabs.subtract(BigDecimal.valueOf(2.5)))
                    .multiply(BigDecimal.valueOf(0.01))
                    .multiply(fval)
                    .setScale(0, RoundingMode.HALF_UP);
            gvalStr = numberFormat(gval) + " 元";
        } else {
            gvalStr = "指數增減率 D 之絕對值 " + numberFormat(dabs, "##0.00") + " % 未達 2.5% 不需物調";
        }
        map.put("gvalStr", gvalStr);

        list.add(map);
        return list;
    }

}
