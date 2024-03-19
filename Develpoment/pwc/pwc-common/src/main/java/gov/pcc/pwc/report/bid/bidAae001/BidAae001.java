package gov.pcc.pwc.report.bid.bidAae001;

import gov.pcc.pwc.report.AbstractReport;
import gov.pcc.pwc.report.bid.BidReport;
import gov.pcc.pwc.service.dto.*;
import org.codehaus.plexus.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAae001 extends AbstractReport {

    public BidAae001() {
        super(BidReport.BidAae001);
    }

    private BidAae001s01 bidAae001s01 = new BidAae001s01();
    private BidAae001s02 bidAae001s02 = new BidAae001s02();
    private BidAae001s03 bidAae001s03 = new BidAae001s03();
    private BidAae001s04 bidAae001s04 = new BidAae001s04();
    private BidAae001s05 bidAae001s05 = new BidAae001s05();

    private BidProjectBaseDTO bidProjectBaseDTO;

    public BidProjectBaseDTO getBidProjectBaseDTO() {
        return bidProjectBaseDTO;
    }

    public void setBidProjectBaseDTO(BidProjectBaseDTO bidProjectBaseDTO) {
        this.bidProjectBaseDTO = bidProjectBaseDTO;
    }

    @Override
    public Map<String, Object> toJasperParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("subreport1", bidAae001s01.getJasperReport());
        map.put("subreport2", bidAae001s02.getJasperReport());
        map.put("subreport3", bidAae001s03.getJasperReport());
        map.put("subreport4", bidAae001s04.getJasperReport());
        map.put("subreport5", bidAae001s05.getJasperReport());
        return map;
    }

    @Override
    protected List<Map<String, Object>> convertToDataList() {
        List<Map<String, Object>> list = super.convertToDataList();
        Map<String, Object> map = new HashMap<>();
        String name = bidProjectBaseDTO.getName();
        String nameStr = name;
        String name0 = bidProjectBaseDTO.getName0();
        String dOpen = bidProjectBaseDTO.getdOpen();
        if (StringUtils.isNotBlank(name0) && !name0.equals(name)) {
            nameStr = nameStr + "\n" + "(決標公告之標的名稱：" + name0 + ")";
        }
        if (StringUtils.isNotBlank(dOpen)) {
            nameStr = nameStr + "\n" + "本案執行機關依政府資訊公開法第十八條第" + dOpen + "款規定不對外公開";
        }
        map.put("name", empty2String(nameStr));
        map.put("wkutName", empty2String(bidProjectBaseDTO.getWkutName()));
        map.put("prjno", empty2String(bidProjectBaseDTO.getPrjno()));
        map.put("man", empty2String(bidProjectBaseDTO.getMan()));
        map.put("tel", empty2String(bidProjectBaseDTO.getTel()));
        map.put("plnName", empty2String(bidProjectBaseDTO.getPlnName()));
        String plnNo = bidProjectBaseDTO.getPlnNo();
        String plnUnicode = bidProjectBaseDTO.getPlnUnicode();
        if (StringUtils.isNotBlank(plnUnicode)) {
            plnNo = plnNo + "\n" + "GPMNET計畫名稱：" + plnUnicode;
        }
        map.put("plnNo", empty2String(plnNo));
        map.put("subNo", empty2String(bidProjectBaseDTO.getSubNo()));
        map.put("isAdd92Name", empty2String(bidProjectBaseDTO.getIsAdd92Name()));
        map.put("bosName", empty2String(bidProjectBaseDTO.getBosName()));
        map.put("depName", empty2String(bidProjectBaseDTO.getDepName()));
        String bos2NameStr = "";
        String bos2Name = bidProjectBaseDTO.getBos2Name();
        String bos2bName = bidProjectBaseDTO.getBos2bName();
        String bos2cName = bidProjectBaseDTO.getBos2cName();
        String bos2dName = bidProjectBaseDTO.getBos2dName();
        if (StringUtils.isNotBlank(bos2Name)) {
            bos2NameStr = bos2NameStr + bidProjectBaseDTO.getBos2Name()
                    + "  " + bidProjectBaseDTO.getBos2Rate() + "%\n";
        }
        if (StringUtils.isNotBlank(bos2bName)) {
            bos2NameStr = bos2NameStr + bidProjectBaseDTO.getBos2bName()
                    + "  " + bidProjectBaseDTO.getBos2bRate() + "%\n";
        }
        if (StringUtils.isNotBlank(bos2cName)) {
            bos2NameStr = bos2NameStr + bidProjectBaseDTO.getBos2cName()
                    + "  " + bidProjectBaseDTO.getBos2cRate() + "%\n";
        }
        if (StringUtils.isNotBlank(bos2dName)) {
            bos2NameStr = bos2NameStr + bidProjectBaseDTO.getBos2dName()
                    + "  " + bidProjectBaseDTO.getBos2dRate() + "%(地方配合款)\n";
        }
        map.put("bos2Name", bos2NameStr.trim());
        String lnkCode = bidProjectBaseDTO.getLnkCode();
        String lnkCode3 = bidProjectBaseDTO.getLnkCode3();
        if (StringUtils.isNotBlank(lnkCode3)) {
            lnkCode = lnkCode + "決標公告單位代碼：" + lnkCode3;
        }
        map.put("lnkCode", empty2String(lnkCode));
        map.put("deptName", empty2String(bidProjectBaseDTO.getDeptName()));
        String isUnionStr = "";
        String isUnion = bidProjectBaseDTO.getIsUnion();
        String specPurch = bidProjectBaseDTO.getSpecPurch();
        String isIntl = bidProjectBaseDTO.getIsIntl();
        if (StringUtils.equalsIgnoreCase("Y", isUnion)) {
            isUnionStr = "本案為統包";
        }
        if (StringUtils.equalsIgnoreCase("Y", specPurch)) {
            isUnionStr = isUnionStr + "／" + "本案採：特殊採購";
        }
        if (StringUtils.equalsIgnoreCase("Y", isIntl)) {
            isUnionStr = isUnionStr + "／" + "本案採國際競圖辦理";
        }
        map.put("isUnion", isUnionStr);
        map.put("typeName", empty2String(bidProjectBaseDTO.getTypeName()));
        String untndStr = "";
        String untnd = bidProjectBaseDTO.getUntnd();
        if (StringUtils.equalsIgnoreCase("Y", untnd)) {
            untndStr = untndStr + "適用營造業法";
        }
        if (StringUtils.equalsIgnoreCase("N", untnd)) {
            untndStr = untndStr + "非屬營造業法規定辦理";
        }
        map.put("untnd", untndStr);
        String isSpcwStr = "";
        String isSpcw = bidProjectBaseDTO.getIsSpcw();
        if (StringUtils.equalsIgnoreCase("Y", untnd) && StringUtils.equalsIgnoreCase("Y", isSpcw)) {
            isSpcwStr = isSpcwStr + "專業工程項目：";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw01())) {
            isSpcwStr = isSpcwStr + "鋼構工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw02())) {
            isSpcwStr = isSpcwStr + "擋土支撐及土方工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw03())) {
            isSpcwStr = isSpcwStr + "基礎工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw04())) {
            isSpcwStr = isSpcwStr + "施工塔架吊裝及模板工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw05())) {
            isSpcwStr = isSpcwStr + "預拌混凝土工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw06())) {
            isSpcwStr = isSpcwStr + "營建鑽探工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw07())) {
            isSpcwStr = isSpcwStr + "地下管線工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw08())) {
            isSpcwStr = isSpcwStr + "帷幕牆工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw09())) {
            isSpcwStr = isSpcwStr + "庭園、景觀工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw10())) {
            isSpcwStr = isSpcwStr + "環境保護工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw11())) {
            isSpcwStr = isSpcwStr + "防水工程  ";
        }
        if (StringUtils.equalsIgnoreCase("Y", bidProjectBaseDTO.getSpcw12())) {
            isSpcwStr = isSpcwStr + "其他經中央主管機關會同主管機關增訂或變更，並公告之項目";
        }
        map.put("isSpcw", isSpcwStr);
        map.put("contain", empty2String(bidProjectBaseDTO.getContain()));
        map.put("benf", empty2String(bidProjectBaseDTO.getBenf()));
        map.put("wKindName", empty2String(bidProjectBaseDTO.getwKindName()));
        map.put("wDays", bidProjectBaseDTO.getwDays());
        map.put("wRemk", empty2String(bidProjectBaseDTO.getwRemk()));
        map.put("placeName", empty2String(bidProjectBaseDTO.getPlaceName()));
        map.put("place", empty2String(bidProjectBaseDTO.getPlace()));
        String tm2Str = "";
        String tm2x = bidProjectBaseDTO.getTm2x();
        String tm2y = bidProjectBaseDTO.getTm2y();
        if (StringUtils.isNotBlank(tm2x) && StringUtils.isNotBlank(tm2y)) {
            tm2Str = tm2Str + " X 座標 " + tm2x + "  Y 座標 " + tm2y;
        }
        map.put("tm2", empty2String(tm2Str));
        String totAmtStr = "(未填)千元";
        BigDecimal totAmt = bidProjectBaseDTO.getTotAmt();
        if (totAmt != null) {
            totAmtStr = numberFormat(totAmt, "#,##0.000") + " 千元";
        }
        map.put("totAmt", totAmtStr);
        String bdgt1Str = "(未填)千元";
        BigDecimal bdgt1 = bidProjectBaseDTO.getBdgt1();
        if (bdgt1 != null) {
            bdgt1Str = numberFormat(bdgt1, "#,##0.000") + " 千元";
        }
        map.put("bdgt1", bdgt1Str);
        String igtctStr = "(無)千元";
        BigDecimal igtct = bidProjectBaseDTO.getIgtct();
        if (igtct != null) {
            igtctStr = numberFormat(igtct, "#,##0.000") + " 千元";
        }
        map.put("igtct", igtctStr);
        String othctStr = "(無)千元";
        BigDecimal othct = bidProjectBaseDTO.getOthct();
        if (othct != null) {
            othctStr = numberFormat(othct, "#,##0.000") + " 千元";
        }
        map.put("othct", othctStr);
        String rsvctStr = "(無)千元";
        BigDecimal rsvct = bidProjectBaseDTO.getRsvct();
        if (rsvct != null) {
            rsvctStr = numberFormat(rsvct, "#,##0.000") + " 千元";
        }
        map.put("rsvct", rsvctStr);
        String pollStr = "(無)千元";
        BigDecimal poll = bidProjectBaseDTO.getPoll();
        if (poll != null) {
            pollStr = numberFormat(poll, "#,##0.000") + " 千元";
        }
        map.put("poll", pollStr);
        map.put("pollNo", empty2String(bidProjectBaseDTO.getPollNo()));
        map.put("drutName", empty2String(bidProjectBaseDTO.getDrutName()));
        String drctStr = "(未填)千元";
        BigDecimal drct = bidProjectBaseDTO.getDrct();
        if (drct != null) {
            drctStr = numberFormat(drct, "#,##0.000") + " 千元";
        }
        map.put("drct", drctStr);
        // 設計單位
        String dsutStr = "";
        String dsctStr = "";
        int dsutCnt = 1;
        for (BidProjectUnitDTO dsut : bidProjectBaseDTO.getDsuts()) {
            if (dsutCnt == 1) {
                dsutStr = dsut.getDsutName();
                if (StringUtils.equalsIgnoreCase("Y", isUnion)
                        && (StringUtils.equalsIgnoreCase("Y", dsut.getCcwkA()) || StringUtils.equalsIgnoreCase("Y", dsut.getCcwkB()))) {
                    dsctStr = "(統包案已含於契約內)千元";
                } else {
                    dsctStr = numberFormat(dsut.getDsct(), "#,##0.000") + " 千元";
                }
            } else {
                dsutStr = dsutStr + "\n" + "設計單位" + dsutCnt + "：" + dsut.getDsutName();
                if (StringUtils.equalsIgnoreCase("Y", isUnion)
                        && (StringUtils.equalsIgnoreCase("Y", dsut.getCcwkA()) || StringUtils.equalsIgnoreCase("Y", dsut.getCcwkB()))) {
                    dsctStr = dsctStr + "\n" + "設計費用" + dsutCnt + "：(統包案已含於契約內)千元";
                } else {
                    dsctStr = dsctStr + "\n" + "設計費用" + dsutCnt + "：" + numberFormat(dsut.getDsct(), "#,##0.000") + " 千元";
                }
            }
            dsutCnt++;
        }
        map.put("dsut", dsutStr);
        map.put("dsct", dsctStr);
        // 監造單位
        String scutStr = "";
        String scctStr = "";
        int scutCnt = 1;
        for (BidProjectUnitDTO scut : bidProjectBaseDTO.getScuts()) {
            if (scutCnt == 1) {
                scutStr = scut.getScutName();
                scctStr = numberFormat(scut.getScct(), "#,##0.000") + " 千元";
            } else {
                scutStr = scutStr + "\n" + "監造單位" + scutCnt + "：" + scut.getScutName();
                scctStr = scctStr + "\n" + "監造費用" + scutCnt + "：" + numberFormat(scut.getScct(), "#,##0.000") + " 千元";
            }
            scutCnt++;
        }
        map.put("scut", scutStr);
        map.put("scct", scctStr);
        map.put("pcmutName", empty2String(bidProjectBaseDTO.getPcmutName()));
        // 承造廠商
        String ccutStr = "";
        String cratStr = "";
        int ccutCnt = 1;
        for (BidProjectUnitDTO ccut : bidProjectBaseDTO.getCcuts()) {
            if (ccutCnt == 1) {
                ccutStr = ccut.getCcutName();
                cratStr = numberFormat(ccut.getCrat(), "##0.00") + "%";
                if (StringUtils.equalsIgnoreCase("Y", isUnion)) {
                    ccutStr = ccutStr + "\n" + "工作內容：";
                    cratStr = cratStr + "\n";
                    if (StringUtils.equalsIgnoreCase("Y", ccut.getCcwkA())) {
                        ccutStr = ccutStr + "基本設計  ";
                    }
                    if (StringUtils.equalsIgnoreCase("Y", ccut.getCcwkB())) {
                        ccutStr = ccutStr + "細部設計  ";
                    }
                    if (StringUtils.equalsIgnoreCase("Y", ccut.getCcwkC())) {
                        ccutStr = ccutStr + "施工  ";
                    }
                }
            } else {
                ccutStr = ccutStr + "\n" + "承造廠商" + ccutCnt + "：" + ccut.getCcutName();
                cratStr = cratStr + "\n" + "承造比重" + ccutCnt + "：" + numberFormat(ccut.getCrat(), "##0.00") + "%";
                if (StringUtils.equalsIgnoreCase("Y", isUnion)) {
                    ccutStr = ccutStr + "\n" + "工作內容：";
                    cratStr = cratStr + "\n";
                    if (StringUtils.equalsIgnoreCase("Y", ccut.getCcwkA())) {
                        ccutStr = ccutStr + "基本設計 ";
                    }
                    if (StringUtils.equalsIgnoreCase("Y", ccut.getCcwkB())) {
                        ccutStr = ccutStr + "細部設計 ";
                    }
                    if (StringUtils.equalsIgnoreCase("Y", ccut.getCcwkC())) {
                        ccutStr = ccutStr + "施工 ";
                    }
                }
            }
            ccutCnt++;
        }
        map.put("ccut", ccutStr);
        map.put("crat", cratStr);
        List<BidPrjGdfkDTO> bidPrjGdfkDTOs = bidProjectBaseDTO.getBidPrjGdfkDTOs();
        if (bidPrjGdfkDTOs.size() > 0) {
            bidAae001s01.setBidPrjGdfkDTOs(bidPrjGdfkDTOs);
            map.put("list1", bidAae001s01.toDataSource());
        }
        List<BidPrjSubcoDTO> bidPrjSubcoDTOs = bidProjectBaseDTO.getBidPrjSubcoDTOs();
        if (bidPrjSubcoDTOs.size() > 0) {
            bidAae001s02.setBidPrjSubcoDTOs(bidPrjSubcoDTOs);
            map.put("list2", bidAae001s02.toDataSource());
        }
        String pdsDateStr = "(未填)";
        LocalDate pdsDate = bidProjectBaseDTO.getPdsDate();
        if (pdsDate != null) {
            pdsDateStr = parseLocalDateToYYYMMDD(pdsDate);
        }
        map.put("pdsDate", pdsDateStr);
        String dsDateStr = "(未填)";
        LocalDate dsDate = bidProjectBaseDTO.getDsDate();
        if (pdsDate != null) {
            dsDateStr = parseLocalDateToYYYMMDD(dsDate);
        }
        map.put("dsDate", dsDateStr);
        String adsDateStr = "(未填)";
        LocalDate adsDate = bidProjectBaseDTO.getAdsDate();
        if (adsDate != null) {
            adsDateStr = parseLocalDateToYYYMMDD(adsDate);
        }
        map.put("adsDate", adsDateStr);
        String opDateStr = "(未填)";
        LocalDate opDate = bidProjectBaseDTO.getOpDate();
        if (opDate != null) {
            opDateStr = parseLocalDateToYYYMMDD(opDate);
        }
        map.put("opDate", opDateStr);
        String aopDateStr = "(未填)";
        LocalDate aopDate = bidProjectBaseDTO.getAopDate();
        if (aopDate != null) {
            aopDateStr = parseLocalDateToYYYMMDD(aopDate) + "(第" + empty2String(bidProjectBaseDTO.getAopCnt()) + "次公告)";
        }
        map.put("aopDate", aopDateStr);
        String pctDateStr = "(未填)";
        LocalDate pctDate = bidProjectBaseDTO.getPctDate();
        if (pctDate != null) {
            pctDateStr = parseLocalDateToYYYMMDD(pctDate);
        }
        map.put("pctDate", pctDateStr);
        String actDateStr = "(未填)";
        LocalDate actDate = bidProjectBaseDTO.getActDate();
        if (actDate != null) {
            actDateStr = parseLocalDateToYYYMMDD(actDate);
        }
        map.put("actDate", actDateStr);
        map.put("ctmethName", empty2String(bidProjectBaseDTO.getCtmethName()));
        map.put("actmthName", empty2String(bidProjectBaseDTO.getActmthName()));
        String mctmthName = empty2String(bidProjectBaseDTO.getMctmthName());
        String ccnt3 = bidProjectBaseDTO.getCcnt3();
        if (StringUtils.equalsIgnoreCase("Y", ccnt3)) {
            mctmthName = mctmthName + "  " + "本案採複數決標";
        }
        map.put("mctmthName", mctmthName);
        map.put("tctmthName", empty2String(bidProjectBaseDTO.getTctmthName()));
        String ctSumStr = "(未填)千元";
        BigDecimal ctSum = bidProjectBaseDTO.getCtSum();
        if (ctSum != null) {
            ctSumStr = numberFormat(ctSum, "#,##0.000") + " 千元";
        }
        BigDecimal ctSum0 = bidProjectBaseDTO.getCtSum0();
        BigDecimal ctSumF = bidProjectBaseDTO.getCtSumF();
        if (ctSum0 != null && ctSum.compareTo(ctSum0) != 0) {
            ctSumStr = ctSumStr + "\n" + "(決標公告之決標金額：" + numberFormat(ctSum0, "#,##0.000") + " 千元)";
        }
        if (ctSumF != null) {
            ctSumStr = ctSumStr + "\n" + "變更設計後之契約金額：" + numberFormat(ctSumF, "#,##0.000") + " 千元";
        }
        map.put("ctSum", ctSumStr);
        String paysStr = "(未填)千元";
        BigDecimal pays = bidProjectBaseDTO.getPays();
        if (pays != null) {
            paysStr = numberFormat(pays, "#,##0.000") + " 千元";
        }
        map.put("pays", paysStr);
        String paymthStr = "";
        String paymth = bidProjectBaseDTO.getPaymth();
        if (StringUtils.equalsIgnoreCase("1", paymth)) {
            paymthStr = "分期估驗計價付款";
        } else if (StringUtils.equalsIgnoreCase("2", paymth)) {
            paymthStr = "驗收後一次計價付款";
        } else if (StringUtils.equalsIgnoreCase("3", paymth)) {
            paymthStr = "其他";
        }
        map.put("paymth", paymthStr);
        map.put("ctmo", empty2String(bidProjectBaseDTO.getCtmo()));
        List<BidPrjFalsDTO> bidPrjFalsDTOs = bidProjectBaseDTO.getBidPrjFalsDTOs();
        if (bidPrjFalsDTOs.size() > 0) {
            bidAae001s03.setBidPrjFalsDTOs(bidPrjFalsDTOs);
            map.put("list3", bidAae001s03.toDataSource());
        }
        String csDateStr = "(未填)";
        LocalDate csDate = bidProjectBaseDTO.getCsDate();
        if (csDate != null) {
            csDateStr = parseLocalDateToYYYMMDD(csDate);
        }
        map.put("csDate", csDateStr);
        String asDateStr = "(未填)";
        LocalDate asDate = bidProjectBaseDTO.getAsDate();
        LocalDate asuDate = bidProjectBaseDTO.getAsuDate();
        if (StringUtils.equalsIgnoreCase("Y", isUnion) && asuDate != null) {
            asDateStr = parseLocalDateToYYYMMDD(asuDate);
        } else if (StringUtils.equalsIgnoreCase("Y", isUnion) && asuDate == null) {
            asDateStr = "(尚未動工)";
        } else {
            if (asDate != null) {
                asDateStr = parseLocalDateToYYYMMDD(asDate);
            }
        }
        map.put("asDate", asDateStr);
        String issDateStr = "(未填)";
        LocalDate issStartDate = bidProjectBaseDTO.getIssStartDate();
        LocalDate issEndDate = bidProjectBaseDTO.getIssEndDate();
        if (issStartDate != null && issEndDate != null) {
            issDateStr = "自" + parseLocalDateToYYYMMDD(issStartDate) + "至"
                    + parseLocalDateToYYYMMDD(issEndDate);
        }
        map.put("issDate", issDateStr);
        String issCostStr = "(未填)";
        BigDecimal issCost = bidProjectBaseDTO.getIssCost();
        if (issCost != null) {
            issCostStr = numberFormat(issCost, "#,##0.000") + " 千元";
        }
        map.put("issCost", issCostStr);
        map.put("issNo", empty2String(bidProjectBaseDTO.getIssNo()));
        map.put("issRmk", empty2String(bidProjectBaseDTO.getIssRmk()));
        List<BidPrjModifyDTO> bidPrjModifyDTOs = bidProjectBaseDTO.getBidPrjModifyDTOs();
        if (bidPrjModifyDTOs.size() > 0) {
            bidAae001s04.setBidPrjModifyDTOs(bidPrjModifyDTOs);
            map.put("list4", bidAae001s04.toDataSource());
        }
        String ceDateStr = "(未填)";
        LocalDate ceDate = bidProjectBaseDTO.getCeDate();
        if (ceDate != null) {
            ceDateStr = parseLocalDateToYYYMMDD(ceDate);
        }
        LocalDate ceDateF = bidProjectBaseDTO.getCeDateF();
        if (ceDateF != null && !ceDateF.equals(ceDate)) {
            ceDateStr = ceDateStr + "\n變更後：" + parseLocalDateToYYYMMDD(ceDateF);
        }
        map.put("ceDate", ceDateStr);
        String aeDateStr = "";
        LocalDate aeDate = bidProjectBaseDTO.getAeDate();
        if (aeDate != null) {
            aeDateStr = parseLocalDateToYYYMMDD(aeDate) + "\n";
        } else {
            aeDateStr = "尚未完工" + "\n";
        }
        if (StringUtils.contains(name, "已解約")) {
            aeDateStr = aeDateStr + "實際解約日期：" + parseLocalDateToYYYMMDD(aeDate);
        }
        map.put("aeDate", aeDateStr.trim());
        map.put("gyResn", empty2String(bidProjectBaseDTO.getGyResn()));
        String aokDateStr = "(未填)";
        LocalDate aokDate = bidProjectBaseDTO.getAokDate();
        if (aokDate != null) {
            aokDateStr = parseLocalDateToYYYMMDD(aokDate);
        }
        map.put("aokDate", aokDateStr);
        String bgbSumtStr = "(未填)千元";
        BigDecimal bgbSumt = bidProjectBaseDTO.getBgbSumt();
        if (bgbSumt != null) {
            bgbSumtStr = numberFormat(bgbSumt, "#,##0.000") + " 千元";
        }
        map.put("bgbSumt", bgbSumtStr);
        String bgbDateStr = "(未填)";
        LocalDate bgbDate = bidProjectBaseDTO.getBgbDate();
        if (bgbDate != null) {
            bgbDateStr = parseLocalDateToYYYMMDD(bgbDate);
        }
        map.put("bgbDate", bgbDateStr);
        List<BidPrjStopwkDTO> bidPrjStopwkDTOs = bidProjectBaseDTO.getBidPrjStopwkDTOs();
        if (bidPrjStopwkDTOs.size() > 0) {
            bidAae001s05.setBidPrjStopwkDTOs(bidPrjStopwkDTOs);
            map.put("list5", bidAae001s05.toDataSource());
        }
        String fcsDateStr = "(未填)";
        LocalDate fcsDate = bidProjectBaseDTO.getFcsDate();
        if (fcsDate != null) {
            fcsDateStr = parseLocalDateToYYYMMDD(fcsDate);
        }
        map.put("fcsDate", fcsDateStr);
        String afcsDateStr = "(未填)";
        LocalDate afcsDate = bidProjectBaseDTO.getAfcsDate();
        if (afcsDate != null) {
            afcsDateStr = parseLocalDateToYYYMMDD(afcsDate);
        }
        map.put("afcsDate", afcsDateStr);
        String fcost2Str = "(未填)";
        BigDecimal fcost2 = bidProjectBaseDTO.getFcost2();
        if (fcost2 != null) {
            fcost2Str = numberFormat(fcost2, "#,##0.000") + " 千元";
        }
        map.put("fcost2", fcost2Str);
        String fcostStr = "(未填)";
        BigDecimal fcost = bidProjectBaseDTO.getFcost();
        if (fcost != null) {
            fcostStr = numberFormat(fcost, "#,##0.000") + " 千元";
        }
        map.put("fcost", fcostStr);
        map.put("ckpUnitName", empty2String(bidProjectBaseDTO.getCkpUnitName()));
        String ckpDateStr = "(未填)";
        LocalDate ckpDate = bidProjectBaseDTO.getCkpDate();
        if (ckpDate != null) {
            ckpDateStr = parseLocalDateToYYYMMDD(ckpDate);
        }
        map.put("ckpDate", ckpDateStr);
        map.put("ckpNum", empty2String(bidProjectBaseDTO.getCkpNum()));
        map.put("ckaUnitName", empty2String(bidProjectBaseDTO.getCkaUnitName()));
        String ckaDateStr = "(未填)";
        LocalDate ckaDate = bidProjectBaseDTO.getCkaDate();
        if (ckaDate != null) {
            ckaDateStr = parseLocalDateToYYYMMDD(ckaDate);
        }
        map.put("ckaDate", ckaDateStr);
        map.put("ckaNum", empty2String(bidProjectBaseDTO.getCkaNum()));
        map.put("ckdUnitName", empty2String(bidProjectBaseDTO.getCkdUnitName()));
        String ckdDateStr = "(未填)";
        LocalDate ckdDate = bidProjectBaseDTO.getCkdDate();
        if (ckdDate != null) {
            ckdDateStr = parseLocalDateToYYYMMDD(ckdDate);
        }
        map.put("ckdDate", ckdDateStr);
        map.put("ckdNum", empty2String(bidProjectBaseDTO.getCkdNum()));
        String tec1str = "";
        String tec2str = "";
        for (BidPrjPeDTO bidPrjPeDTO : bidProjectBaseDTO.getBidPrjPeDTOs()) {
            String jobType = bidPrjPeDTO.getJobType();
            String job = bidPrjPeDTO.getJob();
            String pname = bidPrjPeDTO.getPname();
            String duty = bidPrjPeDTO.getDuty();
            LocalDate startDate = bidPrjPeDTO.getStartDate();
            LocalDate endDate = bidPrjPeDTO.getEndDate();
            if (StringUtils.equalsIgnoreCase("1", jobType)) {
                if (StringUtils.equalsIgnoreCase("1", job)) {
                    tec1str = tec1str + pname + "主任建築師";
                } else if (StringUtils.equalsIgnoreCase("2", job)) {
                    tec1str = tec1str + pname + "主任技師";
                } else if (StringUtils.equalsIgnoreCase("3", job)) {
                    tec1str = tec1str + pname + "專業工程人員";
                }
                tec1str = tec1str + " " + parseLocalDateToYYYMMDD(startDate, "") + "起";
                if (StringUtils.equalsIgnoreCase("1", duty)) {
                    tec1str = tec1str + "\n";
                } else if (StringUtils.equalsIgnoreCase("2", duty)) {
                    tec1str = tec1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "離職\n";
                } else if (StringUtils.equalsIgnoreCase("3", duty)) {
                    tec1str = tec1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "完工\n";
                } else if (StringUtils.equalsIgnoreCase("4", duty)) {
                    tec1str = tec1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "撤換\n";
                }
            } else if (StringUtils.equalsIgnoreCase("3", jobType)) {
                String jobKind1 = bidPrjPeDTO.getJobKind1();
                String jobKind2 = bidPrjPeDTO.getJobKind2();
                String jobKind3 = bidPrjPeDTO.getJobKind3();
                String jobKind4 = bidPrjPeDTO.getJobKind4();
                if (StringUtils.equalsIgnoreCase("Y", jobKind1)) {
                    tec2str = tec2str + "規劃";
                }
                if (StringUtils.equalsIgnoreCase("Y", jobKind2)) {
                    tec2str = tec2str + "設計";
                }
                if (StringUtils.equalsIgnoreCase("Y", jobKind3)) {
                    tec2str = tec2str + "專案管理";
                }
                if (StringUtils.equalsIgnoreCase("Y", jobKind4)) {
                    tec2str = tec2str + "監造";
                }
                tec2str = tec2str + "專技人員：";
                if (StringUtils.equalsIgnoreCase("1", job)) {
                    tec2str = tec2str + pname + "建築師";
                } else if (StringUtils.equalsIgnoreCase("2", job)) {
                    tec2str = tec2str + pname + "技師";
                } else if (StringUtils.equalsIgnoreCase("3", job)) {
                    tec2str = tec2str + pname + "專業工程人員";
                }
                tec2str = tec2str + " " + parseLocalDateToYYYMMDD(startDate, "") + "起";
                if (StringUtils.equalsIgnoreCase("1", duty)) {
                    tec2str = tec2str + "\n";
                } else if (StringUtils.equalsIgnoreCase("2", duty)) {
                    tec2str = tec2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "離職\n";
                } else if (StringUtils.equalsIgnoreCase("3", duty)) {
                    tec2str = tec2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "完工\n";
                } else if (StringUtils.equalsIgnoreCase("4", duty)) {
                    tec2str = tec2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "撤換\n";
                }
            }
        }
        if (StringUtils.isBlank(tec1str)) {
            tec1str = "承造廠商專任工程人員：(未填)" + "\n";
        } else {
            tec1str = "承造廠商專任工程人員：" + tec1str;
        }
        if (StringUtils.isBlank(tec2str)) {
            tec2str = "規劃設計監造專技人員：(未填)";
        }
        map.put("tecstr", tec1str + tec2str.trim());
        String wkmstr = "";
        for (BidPrjWkmengDTO bidPrjWkmengDTO : bidProjectBaseDTO.getBidPrjWkmengDTOs()) {
            String fullTime = bidPrjWkmengDTO.getFullTime();
            String job = bidPrjWkmengDTO.getJob();
            String pname = bidPrjWkmengDTO.getPname();
            String duty = bidPrjWkmengDTO.getDuty();
            LocalDate startDate = bidPrjWkmengDTO.getStartDate();
            LocalDate endDate = bidPrjWkmengDTO.getEndDate();
            if (StringUtils.equalsIgnoreCase("N", fullTime)) {
                wkmstr = wkmstr + "非專職";
            }
            if (StringUtils.equalsIgnoreCase("1", job)) {
                wkmstr = wkmstr + "工地主任";
            } else if (StringUtils.equalsIgnoreCase("2", job)) {
                wkmstr = wkmstr + "技術士";
            } else if (StringUtils.equalsIgnoreCase("3", job)) {
                wkmstr = wkmstr + "安衛人員";
            } else if (StringUtils.equalsIgnoreCase("4", job)) {
                wkmstr = wkmstr + "專案管理經理";
            } else if (StringUtils.equalsIgnoreCase("5", job)) {
                wkmstr = wkmstr + "專案管理人員";
            } else if (StringUtils.equalsIgnoreCase("6", job)) {
                wkmstr = wkmstr + "工地負責人";
            } else if (StringUtils.equalsIgnoreCase("7", job)) {
                wkmstr = wkmstr + "古蹟修赴工地負責人";
            } else if (StringUtils.equalsIgnoreCase("8", job)) {
                wkmstr = wkmstr + "傳統匠師";
            } else if (StringUtils.equalsIgnoreCase("Z", job)) {
                wkmstr = wkmstr + "其他合約規定人員";
            }
            wkmstr = wkmstr + "：" + pname + " " + parseLocalDateToYYYMMDD(startDate, "") + "起";
            for (BidWkmengReentryDTO bidWkmengReentryDTO : bidPrjWkmengDTO.getBidWkmengReentryDTOs()) {
                LocalDate reEndDate = bidWkmengReentryDTO.getReEndDate();
                LocalDate reStartDate = bidWkmengReentryDTO.getReStartDate();
                wkmstr = wkmstr + " " + parseLocalDateToYYYMMDD(reEndDate, "") + "離職"
                        + " " + parseLocalDateToYYYMMDD(reStartDate, "") + "復職";
            }
            if (StringUtils.equalsIgnoreCase("1", duty)) {
                wkmstr = wkmstr + "\n";
            } else if (StringUtils.equalsIgnoreCase("2", duty)) {
                wkmstr = wkmstr + " " + parseLocalDateToYYYMMDD(endDate, "") + "離職\n";
            } else if (StringUtils.equalsIgnoreCase("3", duty)) {
                wkmstr = wkmstr + " " + parseLocalDateToYYYMMDD(endDate, "") + "完工\n";
            } else if (StringUtils.equalsIgnoreCase("4", duty)) {
                wkmstr = wkmstr + " " + parseLocalDateToYYYMMDD(endDate, "") + "撤換\n";
            }
        }
        if (StringUtils.isBlank(wkmstr)) {
            wkmstr = "工地相關人員：(未填)";
        }
        map.put("wkmstr", wkmstr.trim());
        String ckp1str = "";
        String ckp2str = "";
        for (BidPrjQcDTO bidPrjQcDTO : bidProjectBaseDTO.getBidPrjQcDTOs()) {
            String jobType = bidPrjQcDTO.getJobType();
            String dsts = bidPrjQcDTO.getDsts();
            String pname = bidPrjQcDTO.getName();
            String duty = bidPrjQcDTO.getDuty();
            LocalDate startDate = bidPrjQcDTO.getStartDate();
            LocalDate endDate = bidPrjQcDTO.getEndDate();
            if (StringUtils.equalsIgnoreCase("1", jobType)) {
                if (StringUtils.equalsIgnoreCase("Y", dsts)) {
                    ckp1str = ckp1str + "專職";
                } else if (StringUtils.equalsIgnoreCase("A", dsts)) {
                    ckp1str = ckp1str + "專任於本標案";
                } else if (StringUtils.equalsIgnoreCase("N", dsts)) {
                    ckp1str = ckp1str + "非專職";
                }
                ckp1str = ckp1str + "品管人員：" + pname + " " + parseLocalDateToYYYMMDD(startDate, "") + "起";
                for (BidQcReentryDTO bidQcReentryDTO : bidPrjQcDTO.getBidQcReentryDTOs()) {
                    LocalDate reEndDate = bidQcReentryDTO.getReEndDate();
                    LocalDate reStartDate = bidQcReentryDTO.getReStartDate();
                    ckp1str = ckp1str + " " + parseLocalDateToYYYMMDD(reEndDate, "") + "離職"
                            + " " + parseLocalDateToYYYMMDD(reStartDate, "") + "復職";
                }
                if (StringUtils.equalsIgnoreCase("1", duty)) {
                    ckp1str = ckp1str + "\n";
                } else if (StringUtils.equalsIgnoreCase("2", duty)) {
                    ckp1str = ckp1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "離職\n";
                } else if (StringUtils.equalsIgnoreCase("3", duty)) {
                    ckp1str = ckp1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "完工\n";
                } else if (StringUtils.equalsIgnoreCase("4", duty)) {
                    ckp1str = ckp1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "查核丙等被撤換\n";
                } else if (StringUtils.equalsIgnoreCase("5", duty)) {
                    ckp1str = ckp1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "查核缺失改善成效不佳被更換\n";
                } else if (StringUtils.equalsIgnoreCase("6", duty)) {
                    ckp1str = ckp1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "未能實際工地執行工作被更換\n";
                } else if (StringUtils.equalsIgnoreCase("7", duty)) {
                    ckp1str = ckp1str + " " + parseLocalDateToYYYMMDD(endDate, "") + "未能確實執行工作被更換\n";
                }
            } else if (StringUtils.equalsIgnoreCase("2", jobType)) {
                if (StringUtils.equalsIgnoreCase("Y", dsts)) {
                    ckp2str = ckp2str + "專職";
                } else if (StringUtils.equalsIgnoreCase("N", dsts)) {
                    ckp2str = ckp2str + "非專職";
                }
                ckp2str = ckp2str + "監造現場人員：" + pname + " " + parseLocalDateToYYYMMDD(startDate, "") + "起";
                for (BidQcReentryDTO bidQcReentryDTO : bidPrjQcDTO.getBidQcReentryDTOs()) {
                    LocalDate reEndDate = bidQcReentryDTO.getReEndDate();
                    LocalDate reStartDate = bidQcReentryDTO.getReStartDate();
                    ckp2str = ckp2str + " " + parseLocalDateToYYYMMDD(reEndDate, "") + "離職"
                            + " " + parseLocalDateToYYYMMDD(reStartDate, "") + "復職";
                }
                if (StringUtils.equalsIgnoreCase("1", duty)) {
                    ckp2str = ckp2str + "\n";
                } else if (StringUtils.equalsIgnoreCase("2", duty)) {
                    ckp2str = ckp2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "離職\n";
                } else if (StringUtils.equalsIgnoreCase("3", duty)) {
                    ckp2str = ckp2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "完工\n";
                } else if (StringUtils.equalsIgnoreCase("4", duty)) {
                    ckp2str = ckp2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "查核丙等被撤換\n";
                } else if (StringUtils.equalsIgnoreCase("5", duty)) {
                    ckp2str = ckp2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "查核缺失改善成效不佳被更換\n";
                } else if (StringUtils.equalsIgnoreCase("6", duty)) {
                    ckp2str = ckp2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "未能實際工地執行工作被更換\n";
                } else if (StringUtils.equalsIgnoreCase("7", duty)) {
                    ckp2str = ckp2str + " " + parseLocalDateToYYYMMDD(endDate, "") + "未能確實執行工作被更換\n";
                }
            }
        }
        if (StringUtils.isBlank(ckp1str)) {
            ckp1str = "品管人員：(未填)";
        }
        if (StringUtils.isBlank(ckp2str)) {
            ckp2str = "監造現場人員：(未填)";
        }
        map.put("ckp1str", ckp1str.trim());
        map.put("ckp2str", ckp2str.trim());
        String isCkpStr = "";
        String isCkp = bidProjectBaseDTO.getIsCkp();
        String isCkp2 = bidProjectBaseDTO.getIsCkp2();
        String isCkp3 = bidProjectBaseDTO.getIsCkp3();
        String semset = bidProjectBaseDTO.getSemset();
        if (actDate != null && !actDate.isBefore(LocalDate.of(2005, 2, 15)) && StringUtils.equalsIgnoreCase("Y", isCkp)) {
            isCkpStr = isCkpStr + "施工廠商缺失懲罰機制已納入合約\n";
        }
        if (StringUtils.equalsIgnoreCase("N", isCkp)) {
            isCkpStr = isCkpStr + "施工廠商缺失懲罰機制尚未納入合約\n";
        }
        if (StringUtils.equalsIgnoreCase("Y", isCkp2)) {
            isCkpStr = isCkpStr + "監造廠商缺失懲罰機制已納入合約\n";
        }
        if (StringUtils.equalsIgnoreCase("Y", isCkp3)) {
            isCkpStr = isCkpStr + "專案管理廠商缺失懲罰機制已納入合約\n";
        }
        if (StringUtils.equalsIgnoreCase("2", semset)) {
            isCkpStr = isCkpStr + "工地型預拌混凝土設備設置已納入合約\n";
        }
        map.put("isCkp", isCkpStr.trim());
        list.add(map);
        return list;
    }

}
