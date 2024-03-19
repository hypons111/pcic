package gov.pcc.pwc.report.bid.bidAae003;

import gov.pcc.pwc.domain.BidPrjScequip;
import gov.pcc.pwc.domain.BidPrjSubco;
import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class BidAae003 extends AbstractReport {

    public BidAae003() {
        super(BidReport.BidAae003);
    }

    private BidPrjSubcoDTO bidPrjSubcoDTO;

    private VwBidProjectDTO vwBidProjectDTO;

    private BidProjectDTO bidProjectDTO;

    private BidUnit10DTO bidUnit10DTO;

    private BidPlaceDTO bidPlaceDTO;

    private List<BidPrjScequip> bidPrjScequip;

    private String place;

    private List<BidPrjSubco> bidPrjSubco;

    public List<BidPrjSubco> getBidPrjSubco() {
        return bidPrjSubco;
    }

    public void setBidPrjSubco(List<BidPrjSubco> bidPrjSubco) {
        this.bidPrjSubco = bidPrjSubco;
    }

    public BidPrjSubcoDTO getBidPrjSubcoDTO() {
        return bidPrjSubcoDTO;
    }

    public void setBidPrjSubcoDTO(BidPrjSubcoDTO bidPrjSubcoDTO) {
        this.bidPrjSubcoDTO = bidPrjSubcoDTO;
    }

    public VwBidProjectDTO getVwBidProjectDTO() {
        return vwBidProjectDTO;
    }

    public void setVwBidProjectDTO(VwBidProjectDTO vwBidProjectDTO) {
        this.vwBidProjectDTO = vwBidProjectDTO;
    }

    public BidProjectDTO getBidProjectDTO() {
        return bidProjectDTO;
    }

    public void setBidProjectDTO(BidProjectDTO bidProjectDTO) {
        this.bidProjectDTO = bidProjectDTO;
    }

    public BidUnit10DTO getBidUnit10DTO() {
        return bidUnit10DTO;
    }

    public void setBidUnit10DTO(BidUnit10DTO bidUnit10DTO) {
        this.bidUnit10DTO = bidUnit10DTO;
    }

    public BidPlaceDTO getBidPlaceDTO() {
        return bidPlaceDTO;
    }

    public void setBidPlaceDTO(BidPlaceDTO bidPlaceDTO) {
        this.bidPlaceDTO = bidPlaceDTO;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<BidPrjScequip> getBidPrjScequip() {
        return bidPrjScequip;
    }

    public void setBidPrjScequip(List<BidPrjScequip> bidPrjScequip) {
        this.bidPrjScequip = bidPrjScequip;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();


        map.put("name", bidProjectDTO.getName());
        map.put("prjno", bidProjectDTO.getPrjno());
        map.put("place", place);
        map.put("fcsDate", parseLocalDateToYYYMMDD(bidProjectDTO.getAsDate()));
        map.put("ceDate", parseLocalDateToYYYMMDD(bidProjectDTO.getCeDate()));

        String ctsumStr = "未填(千元)";
        BigDecimal ctsum = bidProjectDTO.getCtSum();
        if (ctsum != null) {
            ctsumStr = numberFormat(ctsum, "#,##0.000") + " (千元)";
        }
        map.put("fcost", ctsumStr);

        String ckpmoneyStr = "";
        BigDecimal ckpmoney = bidProjectDTO.getCkpMoney();
        if (ckpmoney != null) {
            ckpmoneyStr = numberFormat(ckpmoney, "#,##0.000") + "(千元)";
        }
        map.put("ckpMoney", ckpmoneyStr);

        String wkut = bidUnit10DTO.getName();
        map.put("wkut", wkut);

        map.put("man", bidProjectDTO.getMan());
        map.put("tel", bidProjectDTO.getTel());

        System.out.println("map!!!!!!!!!!!!!!!!!!!!" + map);

        return map;
    }

    // 表頭參數 DTO 轉成 MAP，才能做數據處理，對應ireport的parameter
    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();

        List<String> spcwList = new ArrayList<>();

        if (bidPrjSubco != null && bidPrjSubco.size() > 0) {
            for (BidPrjSubco item : bidPrjSubco) {

                if (item.getSpcw01() != null) {
                    if (item.getSpcw01().equals("Y")) {
                        spcwList.add("鋼構工程");
                    }
                }

                if (item.getSpcw02() != null) {
                    if (item.getSpcw02().equals("Y")) {
                        spcwList.add("擋土支撐及土方工程");
                    }
                }

                if (item.getSpcw03() != null) {
                    if (item.getSpcw03().equals("Y")) {
                        spcwList.add("基礎工程");
                    }
                }

                if (item.getSpcw04() != null) {
                    if (item.getSpcw04().equals("Y")) {
                        spcwList.add("施工塔架吊裝及模板工程");
                    }
                }

                if (item.getSpcw05() != null) {
                    if (item.getSpcw05().equals("Y")) {
                        spcwList.add("預拌混凝土工程");
                    }
                }

                if (item.getSpcw06() != null) {
                    if (item.getSpcw06().equals("Y")) {
                        spcwList.add("營建鑽探工程");
                    }
                }

                if (item.getSpcw07() != null) {
                    if (item.getSpcw07().equals("Y")) {
                        spcwList.add("地下管線工程");
                    }
                }

                if (item.getSpcw08() != null) {
                    if (item.getSpcw08().equals("Y")) {
                        spcwList.add("帷幕牆工程");
                    }
                }

                if (item.getSpcw09() != null) {
                    if (item.getSpcw09().equals("Y")) {
                        spcwList.add("庭園、景觀工程");
                    }
                }

                if (item.getSpcw10() != null) {
                    if (item.getSpcw10().equals("Y")) {
                        spcwList.add("環境保護工程");
                    }
                }

                if (item.getSpcw11() != null) {
                    if (item.getSpcw11().equals("Y")) {
                        spcwList.add("防水工程");
                    }
                }

                if (item.getSpcw12() != null) {
                    if (item.getSpcw12().equals("Y")) {
                        spcwList.add("其他經中央主管機關會同主管機關增訂或變更，並公告之項目");
                    }
                }

                String spcwData = "";

                if (spcwList.size() > 0) {
                    for (int e = 0; e < spcwList.size(); e++) {
                        spcwData += spcwList.get(e) + "\n";
                    }
                }

                Map<String, Object> map = new HashMap<>();
                List<String> ecodeList = new ArrayList<>();


                map.put("subcoNo", item.getSubcoNo());
                map.put("subCcut", item.getSubCcut());
                map.put("subcoMemo", item.getSubcoMemo());

                for (BidPrjScequip tool : bidPrjScequip) {
                    ecodeList.add(tool.geteCode());
                }

                String ecodeData = "";

                if (ecodeList.size() > 0) {
                    for (int e = 0; e < ecodeList.size(); e++) {
                        ecodeData += ecodeList.get(e) + "\n";
                    }

                    System.out.println("ecodeData" + ecodeData);
                    map.put("ecode", ecodeData);
                } else {
                    map.put("ecode", " ");
                }

                String subMoneyStr = "";
                BigDecimal subMoney = BigDecimal.valueOf(item.getSubBdgt1());
                if (subMoney != null) {
                    subMoneyStr = numberFormat(subMoney, "#,##0.000") + "(千元)";
                }
                map.put("subBdgt1", subMoneyStr);

                map.put("spcwX", spcwData);

                list.add(map);

            }


        }


        return list;
    }

}
