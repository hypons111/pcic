package gov.pcc.pwc.report.bid.bidAae023;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae023s03 extends AbstractReport {
    public BidAae023s03() {
        super(BidReport.BidAae023s03);
    }
    private List<BidPrjMonthDTO> monthDTOList;

    public List<BidPrjMonthDTO> getMonthDTOList() {
        return monthDTOList;
    }

    public void setMonthDTOList(List<BidPrjMonthDTO> monthDTOList) {
        this.monthDTOList = monthDTOList;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();

        String syr ="";
        String mMonth="";
        String  remark="";
        String ptrate="";
        String atrate="";
        String rateCount="";

        // 各月份累積執行狀況
        if(monthDTOList != null && monthDTOList.size() >0){

            for (BidPrjMonthDTO item : monthDTOList) {
                Map<String, Object> map = new HashMap<>();
                // PK 不可能未填
                syr = item.getSyr();
                mMonth = item.getMonth();

                // 需檢查未填
                String remarkCheck = item.getRemark();
                if(remarkCheck != null){
                    remark = remarkCheck;
                }else{
                    remark = "(未填)";
                }

                BigDecimal ptrateCheck = item.getPtrate();
                if(ptrateCheck != null){
                    ptrate = numberFormat(ptrateCheck,"#,##0.00#")+"%";
                }else {
                    ptrate = "-";
                }

                BigDecimal atrateCheck = item.getAtrate();
                if(atrateCheck != null){
                    atrate = numberFormat(atrateCheck,"#,##0.00#")+"%";
                }else {
                    atrate = "-";
                }

                if(ptrateCheck != null && atrateCheck != null){
                        BigDecimal rateCountCheck = atrateCheck.subtract(ptrateCheck);
                        rateCount = numberFormat(rateCountCheck, "#,##0.00#") + "%";
                }else {
                    rateCount = "-";
                }


                map.put("syr", syr);
                map.put("mMonth", mMonth);
                map.put("remark", remark);
                map.put("ptrate", ptrate);
                map.put("atrate", atrate);
                map.put("rateCount", rateCount);
                list.add(map);
            }

        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("syr", "(未填)");
            map.put("mMonth", "(未填)");
            map.put("remark", "(未填)");
            map.put("ptrate", "(未填)");
            map.put("atrate", "(未填)");
            map.put("rateCount", "(未填)");
            list.add(map);
        }

        return list;
    }
}
