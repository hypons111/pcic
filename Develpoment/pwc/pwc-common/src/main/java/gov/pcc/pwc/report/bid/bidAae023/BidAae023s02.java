package gov.pcc.pwc.report.bid.bidAae023;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjPay2DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae023s02 extends AbstractReport {

    public BidAae023s02() {
        super(BidReport.BidAae023s02);
    }
    private List<BidPrjPay2DTO> pay2DTOList;

    public List<BidPrjPay2DTO> getPay2DTOList() {
        return pay2DTOList;
    }

    public void setPay2DTOList(List<BidPrjPay2DTO> pay2DTOList) {
        this.pay2DTOList = pay2DTOList;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();

        // 歷次撥款狀況
        String year ="";
        String month ="";
        String trueMon ="";
        String payDate ="";
        String payMon ="";
        String payMonS ="";
        String payRmk ="";

        if(pay2DTOList != null && pay2DTOList.size() >0){

            for (BidPrjPay2DTO item : pay2DTOList) {
                Map<String, Object> map = new HashMap<>();
                // PK 不可能未填
                year = item.getYear();
                month = item.getMonth();

                // 需檢查未填
                BigDecimal trueMonCheck = item.getTrueMon();
                if(trueMonCheck != null){
                    trueMon = numberFormat(trueMonCheck,"#,##0.000#")+ " 千元";
                }else {
                    trueMon = "(未填)";
                }

                LocalDate payDateCheck = item.getPayDate();
                if(payDateCheck != null){
                    payDate = parseLocalDateToYYYMMDD(payDateCheck);
                }else {
                    payDate = "(未填)";
                }

                BigDecimal payMonCheck = item.getPayMon();
                if(payMonCheck != null){
                    payMon = numberFormat(payMonCheck,"#,##0.000#")+ " 千元";
                }else {
                    payMon = "(未填)";
                }

                BigDecimal payMonSCheck = item.getPayMonS();
                if(payMonSCheck != null){
                    payMonS = numberFormat(payMonSCheck,"#,##0.000#")+ " 千元";
                }else {
                    payMonS = "(未填)";
                }

                String payRmkCheck = item.getPayRmk();
                if(payRmkCheck != null){
                    payRmk = payRmkCheck;
                }else {
                    payRmk = "(未填)";
                }

                map.put("year", year);
                map.put("month", month);
                map.put("trueMon", trueMon);
                map.put("payDate", payDate);
                map.put("payMon", payMon);
                map.put("payMonS", payMonS);
                map.put("payRmk", payRmk);
                list.add(map);

            }
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("year", "(未填)");
            map.put("month", "(未填)");
            map.put("trueMon", "(未填)");
            map.put("payDate", "(未填)");
            map.put("payMon", "(未填)");
            map.put("payMonS", "(未填)");
            map.put("payRmk", "(未填)");
            list.add(map);
        }

        return list;
    }
}
