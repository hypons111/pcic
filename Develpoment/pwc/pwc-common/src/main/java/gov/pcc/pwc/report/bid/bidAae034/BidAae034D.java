package gov.pcc.pwc.report.bid.bidAae034;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae034D extends AbstractReport {

    public BidAae034D() {
        super(BidReport.BidAae034D);
    }

    private BidAae034Ds01 bidAae034Ds01 = new BidAae034Ds01();
    private BidAae034Ds02 bidAae034Ds02 = new BidAae034Ds02();
    private BidAae034Ds03 bidAae034Ds03 = new BidAae034Ds03();

    private BidProjectInfoDTO bidProjectDTO;

    private BidGovCheckDTO bidGovCheckDTO;

    private List<BidGovCheckerrDTO> bidGovCheckerrDTOs;

    public BidProjectInfoDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectInfoDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public BidGovCheckDTO getBidGovCheckDTO() {
        return bidGovCheckDTO;
    }

    public void setBidGovCheckDTO(BidGovCheckDTO bidGovCheckDTO) {
        this.bidGovCheckDTO = bidGovCheckDTO;
    }

    public List<BidGovCheckerrDTO> getBidGovCheckerrDTOs() {
        return bidGovCheckerrDTOs;
    }

    public void setBidGovCheckerrDTOs(List<BidGovCheckerrDTO> bidGovCheckerrDTOs) {
        this.bidGovCheckerrDTOs = bidGovCheckerrDTOs;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("subreport1", bidAae034Ds01.getJasperReport());
        map.put("subreport2", bidAae034Ds02.getJasperReport());
        map.put("subreport3", bidAae034Ds03.getJasperReport());
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        Map<String, Object> map = new HashMap<>();
        map.put("name", bidProjectDTO.getName());
        map.put("checkDate", this.parseLocalDateToYYYMMDD(bidGovCheckDTO.getCheckDate()));

        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        int seq1 = 1;
        for (BidGovCheckerrDTO bidGovCheckerrDTO : bidGovCheckerrDTOs) {
            Map<String, Object> map1 = new HashMap<>();
            String errMain = bidGovCheckerrDTO.getErrMain();
            if (StringUtils.isNotBlank(errMain)) {
                String errname = seq1 + "." + errMain + "(" + bidGovCheckerrDTO.getErrNum() + ")";
                int roveFlag = bidGovCheckerrDTO.getRoveFlag();
                if (roveFlag > 0) {
                    errname = errname + "(扣" + roveFlag + "點)";
                } else if (roveFlag < 0) {
                    errname = errname + "(加" + roveFlag * -1 + "點)";
                }
                map1.put("errName", errname);
                seq1++;
            } else {
                map1.put("errName", "(未填)");
            }
            list1.add(map1);
        }
        bidAae034Ds01.setList(list1);
        map.put("list1", bidAae034Ds01.toDataSource());

        String detel5 = bidGovCheckDTO.getDetel5();
        if (StringUtils.isNotBlank(detel5)) {
            String[] lines = detel5.split("\\R");
            List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
            int seq2 = 1;
            for (String str : lines) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("detel5", seq2 + "." + str);
                seq2++;
                list2.add(map2);
            }
            bidAae034Ds02.setList(list2);
            map.put("list2", bidAae034Ds02.toDataSource());
        }
        String detel3 = bidGovCheckDTO.getDetel3();
        if (StringUtils.isNotBlank(detel3)) {
            String[] lines = detel3.split("\\R");
            List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
            int seq3 = 1;
            for (String str : lines) {
                Map<String, Object> map3 = new HashMap<>();
                map3.put("detel3", seq3 + "." + str);
                seq3++;
                list3.add(map3);
            }
            bidAae034Ds03.setList(list3);
            map.put("list3", bidAae034Ds03.toDataSource());
        }
        list.add(map);
        return list;
    }

}
