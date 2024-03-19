package gov.pcc.pwc.report.bid.bidAae023;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidMdlbasDTO;
import gov.pcc.pwc.service.dto.BidPrjSur2DTO;
import gov.pcc.pwc.service.dto.BidProjectUnitDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae023s01 extends AbstractReport {
    public BidAae023s01() {
        super(BidReport.BidAae023s01);
    }

    private List<BidPrjSur2DTO> sur2DTOList;

    public List<BidPrjSur2DTO> getSur2DTOList() {
        return sur2DTOList;
    }

    public void setSur2DTOList(List<BidPrjSur2DTO> sur2DTOList) {
        this.sur2DTOList = sur2DTOList;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();


        // 核定編號
        String disNo ="";
        // 災害工程項目及內容名稱
        String disName ="";
        // 使用預算數(千元)
        String amnt = "" ;

        if(sur2DTOList != null && sur2DTOList.size() >0) {

            for (BidPrjSur2DTO item : sur2DTOList) {
                Map<String, Object> map = new HashMap<>();
                // PK 不可能未填
                disNo = item.getDisNo();
                // 需檢查未填
                BigDecimal amntNum = item.getAmnt();
                String disNameStr = item.getDisName();

                if(amntNum != null){
                    amnt = numberFormat(amntNum,"#,##0.000#")+ " 千元";
                }else {
                    amnt = "(未填)";
                }

                if(disNameStr != null){
                    disName = disNameStr;
                }else {
                    disName = "(未填)";
                }
                map.put("disNo", disNo);
                map.put("amnt", amnt);
                map.put("disName",disName);

                list.add(map);
            }
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("disNo", "(未填)");
            map.put("amnt", "(未填)");
            map.put("disName", "(未填)");
            list.add(map);
        }

        return list;
    }
}
