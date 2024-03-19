package gov.pcc.pwc.report.bid.bidAae020;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae020s01b extends AbstractReport {
    public BidAae020s01b() {
        super(BidReport.BidAae020s01b);
    }

    private List<BidPrjModifyDTO> modifyDTOList;

    public List<BidPrjModifyDTO> getModifyDTOList() {
        return modifyDTOList;
    }

    public void setModifyDTOList(List<BidPrjModifyDTO> modifyDTOList) {
        this.modifyDTOList = modifyDTOList;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();

        int num = 1;
        // 序號
        String cnt = "";
        // 變更設計後金額(後)
        String newAmt = "";
        // 變更設計後金額(原)
        String oldAmt = "";
        // 變更日期
        String chgDate = "";
        // 核准變更文號
        String okNo = "";
        // 併入名稱
        String mergeName = "";

        if (modifyDTOList != null && modifyDTOList.size() > 0) {
            for (BidPrjModifyDTO item : modifyDTOList) {
                Map<String, Object> map = new HashMap<>();
                if (item.getNewAmt() != null && item.getNewAmt().compareTo(item.getOldAmt()) == 0) {
                    // 序號
                    cnt = num + "";
                    map.put("cnt", cnt);
                    // 變更設計後金額(後)
                    newAmt = numberFormat(item.getNewAmt(), "#,##0.000#") + " 千元";
                    map.put("newAmt", newAmt);
                    // 變更設計後金額(原)
                    oldAmt = numberFormat(item.getOldAmt(), "#,##0.000#")+ " 千元";
                    map.put("oldAmt", oldAmt);
                    // 變更日期
                    chgDate = parseLocalDateToYYYMMDD(item.getChgDate(),"");
                    map.put("chgDate", chgDate);
                    // 核准變更文號
                    okNo = item.getOkNo();
                    map.put("okNo", okNo);
                    // 併入名稱
                    mergeName = item.getMergeName();
                    map.put("mergeName", mergeName);

                    num++;
                    list.add(map);
                }

            }
        }

        return list;
    }

}
