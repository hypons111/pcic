package gov.pcc.pwc.report.bid.bidAaa004;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAaa004Stop extends AbstractReport {

    public BidAaa004Stop() {
        super(BidReport.BidAaa004Stop);
    }

    private BidProjectInfoDTO bidProjectInfoDTO;

    private BidProjectDTO bidProjectDTO;

    private BidPrjStopwkDTO bidPrjStopwkDTO;

    private List<AdmSysCodeDTO> admSysCodeDTOs;

    private BidUnit10DTO ccut;

    private BidUnit10DTO scut;

    public BidProjectInfoDTO getBidProjectInfoDTO() {
        return bidProjectInfoDTO;
    }

    public void setBidProjectInfoDTO(BidProjectInfoDTO bidProjectInfoDTO) {
        this.bidProjectInfoDTO = bidProjectInfoDTO;
    }

    public BidProjectDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public BidPrjStopwkDTO getBidPrjStopwkDTO() {
        return bidPrjStopwkDTO;
    }

    public void setBidPrjStopwkDTO(BidPrjStopwkDTO bidPrjStopwkDTO) {
        this.bidPrjStopwkDTO = bidPrjStopwkDTO;
    }

    public List<AdmSysCodeDTO> getAdmSysCodeDTOs() {
        return admSysCodeDTOs;
    }

    public void setAdmSysCodeDTOs(List<AdmSysCodeDTO> admSysCodeDTOs) {
        this.admSysCodeDTOs = admSysCodeDTOs;
    }

    public BidUnit10DTO getCcut() {
        return ccut;
    }

    public void setCcut(BidUnit10DTO ccut) {
        this.ccut = ccut;
    }

    public BidUnit10DTO getScut() {
        return scut;
    }

    public void setScut(BidUnit10DTO scut) {
        this.scut = scut;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this.transBean2Map(bidProjectInfoDTO));
        map.putAll(this.transBean2Map(bidPrjStopwkDTO));//put all 新的會蓋掉舊的

        String place = bidProjectDTO.getPlace();
        if (!StringUtils.isNotBlank(place)) {
            map.put("place", "(未填)");
        }

        if (!StringUtils.isNotBlank(bidProjectDTO.getwRemk())) {
            map.put("wRemk", " ");
        }

        BigDecimal ctSum = bidProjectInfoDTO.getCtSum();
        if (ctSum != null) {
            map.put("ctSum", numberFormat(ctSum, "#,##0.000#") + "千元");
        }

        String cntid = bidProjectInfoDTO.getCntid();
        if (StringUtils.isNotBlank(cntid)) {
            map.put("cntid", "(未填)");
        }

        BigDecimal bdgt1 = bidProjectInfoDTO.getBdgt1();
        if (bdgt1 != null) {
            map.put("bdgt1", numberFormat(bdgt1, "#,##0.000#") + "千元");
        }

        map.put("startDate", parseLocalDateToYYYMMDD(bidPrjStopwkDTO.getStartDate()));


        map.put("csDate", bidProjectDTO.getCsDate() != null ? parseLocalDateToYYYMMDD(bidProjectDTO.getCsDate()) : "(未填)");
        map.put("ceDate", bidProjectDTO.getCeDate() != null ? parseLocalDateToYYYMMDD(bidProjectDTO.getCeDate()) : "(未填)");
        map.put("asDate", bidProjectDTO.getAsDate() != null ? parseLocalDateToYYYMMDD(bidProjectDTO.getAsDate()) : "(未填)");
        map.put("peDate", bidPrjStopwkDTO.getPeDate() != null ? parseLocalDateToYYYMMDD(bidPrjStopwkDTO.getPeDate()) : "(未填)");


        //取得 sysCode的value
        String negoLevel = bidPrjStopwkDTO.getNegoLevel();
        AdmSysCodeDTO foundSysCodeDTO = this.admSysCodeDTOs.stream().filter(admSysCodeDTO -> negoLevel.equals(admSysCodeDTO.getDataKey())).findFirst().orElse(null);
        String negoLevelValue = foundSysCodeDTO.getValue();
        map.put("negoLevelValue", negoLevelValue);


        if (ccut != null && StringUtils.isNotBlank(ccut.getName())) {
            map.put("ccutName", ccut.getName());
        } else {
            map.put("ccutName", "(未填)");
        }

        if (scut != null && StringUtils.isNotBlank(scut.getName())) {
            map.put("scutName", scut.getName());
        } else {
            map.put("scutName", "(未填)");
        }


        return map;
    }


}
