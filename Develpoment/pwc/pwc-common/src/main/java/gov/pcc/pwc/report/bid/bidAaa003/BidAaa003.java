package gov.pcc.pwc.report.bid.bidAaa003;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAaa003 extends AbstractReport {

    public BidAaa003() {
        super(BidReport.BidAaa003);
    }

    private BidProjectInfoDTO bidProjectDTO;

    private List<BidPrjModifyDTO> bidPrjModifyDTO;

    private List<AdmSysCodeDTO>  admSysCodeDTOs;

    public BidProjectInfoDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectInfoDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public List<BidPrjModifyDTO> getBidPrjModifyDTO() {
        return bidPrjModifyDTO;
    }

    public void setBidPrjModifyDTO(List<BidPrjModifyDTO> bidPrjModifyDTO) {
        this.bidPrjModifyDTO = bidPrjModifyDTO;
    }

    public List<AdmSysCodeDTO> getAdmSysCodeDTOs() {
        return admSysCodeDTOs;
    }

    public void setAdmSysCodeDTOs(List<AdmSysCodeDTO> admSysCodeDTOs) {
        this.admSysCodeDTOs = admSysCodeDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this.transBean2Map(bidProjectDTO));

        String planName = bidProjectDTO.getPlnName();
        if (StringUtils.isNotBlank(planName)) {
            map.put("planName", "(未填)");
        }

        String planNo = bidProjectDTO.getPlnNo();
        if (StringUtils.isNotBlank(planNo)) {
            map.put("planName", "(未填)");
        }

        BigDecimal bdgt1 = bidProjectDTO.getBdgt1();
        if (bdgt1 != null) {
            map.put("bdgt1", numberFormat(bdgt1, "#,##0.000#") + "千元");
        }

        BigDecimal ctSum = bidProjectDTO.getCtSum();
        if (ctSum != null) {
            map.put("ctSum", numberFormat(ctSum, "#,##0.000#") + "千元");
        }

        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        Integer count = 0;
        for(BidPrjModifyDTO dto :bidPrjModifyDTO){
            count++;
            Map<String, Object> map = new HashMap<>();
            String okNo = dto.getOkNo();
            map.put("okNo", okNo);
            map.put("count", count);

            String mdyPress = dto.getMdyPress();
            String mdyPressValue = "";
            if("Y".equals(mdyPress)){
                mdyPressValue = "有簽報辦理變更設計";
            }else if("N".equals(mdyPress)){
                mdyPressValue = "單純工期展延";
            }
            map.put("mdyPressValue",mdyPressValue);

            LocalDate chgDate = dto.getChgDate();

            map.put("chgDate",parseLocalDateToYYYMMDD(chgDate));
            map.put("mdyRemark", dto.getMdyRemark());

            //取得 sysCode的value
            String mdyReson = dto.getMdyReson();
            AdmSysCodeDTO foundSysCodeDTO = this.admSysCodeDTOs.stream().filter(admSysCodeDTO -> mdyReson.equals(admSysCodeDTO.getDataKey())).findFirst().orElse(null);
            String mdyReasonValue = foundSysCodeDTO.getValue();

            map.put("mdyReasonValue", mdyReasonValue);


            BigDecimal oldAmt = dto.getOldAmt();
            if (oldAmt != null) {
                map.put("oldAmt", this.numberFormat(oldAmt, "#,##0.000"));
            } else {
                map.put("oldAmt", "(未填)");
            }

            BigDecimal newAmt = dto.getNewAmt();
            if (newAmt != null) {
                map.put("newAmt", this.numberFormat(newAmt, "#,##0.000"));
            } else {
                map.put("newAmt", "(未填)");
            }

            BigDecimal subAmt;
            if (oldAmt != null && newAmt != null){
                subAmt = newAmt.subtract(oldAmt).abs();
                map.put("subAmt", this.numberFormat(subAmt, "#,##0.000"));

            }else{
                map.put("subAmt", "(未填)");
            }

            map.put("oldDate", dto.getOldDate()!=null?  parseLocalDateToYYYMMDD(dto.getOldDate()):"(未填)");
            map.put("newDate", dto.getNewDate()!=null?  parseLocalDateToYYYMMDD(dto.getNewDate()):"(未填)");
            map.put("nDays", dto.getnDays());
            list.add(map);

        }
        return list;
    }

}
