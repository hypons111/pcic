package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidProjectRepositoryCustom;
import gov.pcc.pwc.service.dto.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

public class BidProjectRepositoryImpl implements BidProjectRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidProjectRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

//    private final Logger log = LoggerFactory.getLogger();

    @Override
    public Optional<BidProjectInfoDTO> findBidProjectInfoByWkutAndPrjno(String wkut, String prjno) {

        Query query = Query
                .builder()
                .append("SELECT bp.WKUT,bu.name as WKUT_NAME,bp.PRJNO,bp.NAME,bp.TYPE_CODE,bp.BOS2_CODE,bp.BOS2B_CODE,bp.BOS2C_CODE, ")
                .append("bp.BOS2D_CODE,bp.BOS_CODE,bp.DEP_CODE,bp.MAN,bp.TEL, ")
                .append("bp.BOS2_RATE,bp.BOS2B_RATE,bp.BOS2C_RATE,bp.BOS2C_RATE, ") //新增Rate
                .append("bp.BDGT1, ")
                .append("bp.CT_SUM,bp.ACT_DATE,bp.ACTMTH,bp.CT_SUM_F,bp.CNTID, ")
                .append("bp.AS_DATE,bp.PAYS,bp.PLN_CODE,bp.PLN_NO,")
                .append(" bos.NAME as BOS_NAME,bos2.NAME as BOS2_NAME,bos2b.NAME as bos2b_name,")
                .append(" dep.NAME as DEPT_NAME,pln.NAME as PLN_NAME")
                .append("FROM BID_PROJECT bp")
                .append("LEFT JOIN BID_UNIT10 bos ON bp.BOS_CODE  = bos.CODE")
                .append("LEFT JOIN BID_UNIT10 bos2 ON bp.BOS2_CODE  = bos2.CODE")
                .append("LEFT JOIN BID_UNIT10 bos2b ON bp.BOS2B_CODE  = bos2b.CODE")
                .append("LEFT JOIN BID_UNIT10 dep ON bp.DEP_CODE  = dep.CODE")
                .append("LEFT JOIN BID_UNIT10 bu ON bp.WKUT = bu.CODE ")
                .append("LEFT JOIN BID_UNIT10 pln ON bp.PLN_CODE = pln.CODE ")
                .append("WHERE 1=1")
                .append("AND bp.WKUT = :wkut",wkut)
                .append("AND bp.PRJNO = :prjno",prjno)
                .build();
        List<BidProjectInfoDTO> sqlResult = sqlExecutor.queryForList(query, BidProjectInfoDTO.class);
        return sqlResult.stream().findFirst();
        /*
        SELECT bp.WKUT,bu.name as WKUT_NAME,bp.PRJNO,bp.NAME,bp.TYPE_CODE,bp.BOS2B_CODE,bp.BOS2C_CODE,
           bp.BOS2D_CODE,bp.BOS_CODE,bp.DEP_CODE,bp.MAN,bp.TEL,bp.CCUT1, bp.CRAT1,
           bp.CCUT2,bp.CRAT2,bp.CCUT3,bp.CRAT3,bp.BDGT1, bp.CT_SUM,bp.ACT_DATE,bp.AS_DATE,bp.CT_SUM_F,bp.CNTID,bp.ACTMTH,bp.AS_DATE,bp.PAYS,
           bos.NAME as BOS_NAME,
           bos2.NAME as BOS2_NAME,
           bos2b.NAME as bos2b_name,
           dep.NAME as DEPT_NAME,
           ccut.NAME as CCUT1_NAME
        FROM BID_PROJECT bp
        LEFT JOIN BID_UNIT10 bos ON bp.BOS_CODE  = bos.CODE
        LEFT JOIN BID_UNIT10 bos2 ON bp.BOS2_CODE  = bos2.CODE
        LEFT JOIN BID_UNIT10 bos2b ON bp.BOS2B_CODE  = bos2b.CODE
        LEFT JOIN BID_UNIT10 dep ON bp.DEP_CODE  = dep.CODE
        LEFT JOIN BID_UNIT10 ccut ON bp.CCUT1  = ccut.CODE
        LEFT JOIN BID_UNIT10 bu ON bp.WKUT = bu.CODE
        WHERE 1=1
        AND bp.WKUT = '9876543210' AND bp.PRJNO = '1999999'
         */
    }

    @Override
    public List<BidUnit10DTO> findProjectUnit(String wkut, String prjno, String type) {
        if(StringUtils.isBlank(wkut) ||StringUtils.isBlank(prjno) || StringUtils.isBlank(type)){
            return null;
        }
        //針對不同的type，on的條件不一樣
        String diffCase ="";  //"on bpu.CCUT = bu.CODE ";
        StringBuilder sb = new StringBuilder();
        switch(type) {
            case "1":
                sb.append("on bpu.DSUT = bu.CODE");
                break;
            case "2":
                sb.append("on bpu.SCUT = bu.CODE");
                break;
            case "3":
                sb.append("on bpu.CCUT = bu.CODE");
                break;
            default:
                return null;
        }
        Query query = Query
                .builder()
                .append("select * from BID_UNIT10 bu  inner join BID_PROJECT_UNIT bpu ")
                .append(sb.toString())
                .append("WHERE bpu.TYPE = :type",type)
                .append("and bpu.WKUT =:wkut",wkut)
                .append("and bpu.PRJNO =:prjno",prjno)
                .append("ORDER by bpu.SEQ ")
                .build();
        return sqlExecutor.queryForList(query, BidUnit10DTO.class);
    }

    @Override
    public BidProjectBaseDTO findBidProjectBaseByWkutAndPrjno(String wkut, String prjno) {
        Query query = Query
                .builder()
                .append("SELECT ")
                .append("PRJ.NAME, PRJ.NAME0, PRJ.D_OPEN, PRJ.WKUT, WKUT.NAME WKUT_NAME, ")
                .append("PRJ.PRJNO, PRJ.MAN, PRJ.TEL, PRJ.PLN_CODE, PLN.NAME PLN_NAME, ")
                .append("PRJ.PLN_NO, PRJ.PLN_UNICODE, PRJ.SUB_NO, PRJ.IS_ADD92, ADD92.VALUE IS_ADD92_NAME, ")
                .append("PRJ.BOS_CODE, BOS.NAME BOS_NAME, PRJ.DEP_CODE, DEP.NAME DEP_NAME, PRJ.BOS2_CODE, ")
                .append("BOS2.NAME BOS2_NAME, PRJ.BOS2_RATE, PRJ.BOS2B_CODE, BOS2B.NAME BOS2B_NAME, PRJ.BOS2B_RATE, ")
                .append("PRJ.BOS2C_CODE, BOS2C.NAME BOS2C_NAME, PRJ.BOS2C_RATE, PRJ.BOS2D_CODE, BOS2D.NAME BOS2D_NAME, ")
                .append("PRJ.BOS2D_RATE, PRJ.LNK_CODE, PRJ.DEPT_NAME, PRJ.LNK_CODE3, PRJ.IS_UNION, ")
                .append("PRJ.SPEC_PURCH, PRJ.IS_INTL, PRJ.TYPE_CODE, TYPE.VALUE TYPE_NAME, PRJ.UNTND, ")
                .append("PRJ.IS_SPCW, PRJ.SPCW01, PRJ.SPCW02, PRJ.SPCW03, PRJ.SPCW04, ")
                .append("PRJ.SPCW05, PRJ.SPCW06, PRJ.SPCW07, PRJ.SPCW08, PRJ.SPCW09, ")
                .append("PRJ.SPCW10, PRJ.SPCW11, PRJ.SPCW12, PRJ.CONTAIN, PRJ.BENF, ")
                .append("PRJ.W_KIND, WKIND.VALUE W_KIND_NAME, PRJ.W_DAYS, PRJ.W_REMK, PRJ.PLACE_CODE, ")
                .append("PLACE.PLACE_NAME PLACE_NAME, PRJ.PLACE, PRJ.TM2X, PRJ.TM2Y, PRJ.TOT_AMT, ")
                .append("PRJ.BDGT1, PRJ.IGTCT, PRJ.OTHCT, PRJ.RSVCT, PRJ.POLL, ")
                .append("PRJ.POLL_NO, PRJ.DRUT, DRUT.NAME DRUT_NAME, PRJ.DRCT, PRJ.PCMUT, ")
                .append("PCMUT.NAME PCMUT_NAME, PRJ.PDS_DATE, PRJ.DS_DATE, PRJ.ADS_DATE, PRJ.OP_DATE, ")
                .append("PRJ.AOP_DATE, PRJ.AOP_CNT, PRJ.PCT_DATE, PRJ.ACT_DATE, PRJ.CTMETH, ")
                .append("CTMETH.VALUE CTMETH_NAME, PRJ.ACTMTH, ACTMTH.VALUE ACTMTH_NAME, PRJ.MCTMTH, MCTMTH.VALUE MCTMTH_NAME, ")
                .append("PRJ.CCNT3, PRJ.TCTMTH, TCTMTH.VALUE TCTMTH_NAME, PRJ.CT_SUM, PRJ.CT_SUM0, ")
                .append("PRJ.CT_SUM_F, PRJ.PAYS, PRJ.PAYMTH, PRJ.CTMO, PRJ.CS_DATE, ")
                .append("PRJ.ASU_DATE, PRJ.AS_DATE, PRJ.ISS_START_DATE, PRJ.ISS_END_DATE, PRJ.ISS_COST, ")
                .append("PRJ.ISS_NO, PRJ.ISS_RMK, PRJ.CE_DATE, PRJ.CE_DATE_F, PRJ.AE_DATE, ")
                .append("PRJ.GY_RESN, PRJ.AOK_DATE, PRJ.BGB_SUMT, PRJ.BGB_DATE, PRJ.FCS_DATE, ")
                .append("PRJ.AFCS_DATE, PRJ.FCOST2, PRJ.FCOST, PRJ.CKP_UNIT, CKP.NAME CKP_UNIT_NAME, ")
                .append("PRJ.CKP_DATE, PRJ.CKP_NUM, PRJ.CKA_UNIT, CKA.NAME CKA_UNIT_NAME, PRJ.CKA_DATE, ")
                .append("PRJ.CKA_NUM, PRJ.CKD_UNIT, CKD.NAME CKD_UNIT_NAME, PRJ.CKD_DATE, PRJ.CKD_NUM, ")
                .append("PRJ.IS_CKP, PRJ.IS_CKP2, PRJ.IS_CKP3, PRJ.SEMSET ")
                .append("FROM BID_PROJECT PRJ ")
                .append("LEFT JOIN BID_UNIT10 WKUT ON PRJ.WKUT = WKUT.CODE ")
                .append("LEFT JOIN BID_UNIT10 PLN ON PRJ.PLN_CODE = PLN.CODE ")
                .append("LEFT JOIN ADM_SYS_CODE ADD92 ON ADD92.MODULE_TYPE = 'BID' ")
                .append("AND ADD92.DATA_TYPE = 'BID_001' ")
                .append("AND PRJ.IS_ADD92 = ADD92.DATA_KEY ")
                .append("LEFT JOIN BID_UNIT10 BOS ON PRJ.BOS_CODE = BOS.CODE ")
                .append("LEFT JOIN BID_UNIT10 DEP ON PRJ.DEP_CODE = DEP.CODE ")
                .append("LEFT JOIN BID_UNIT10 BOS2 ON PRJ.BOS2_CODE = BOS2.CODE ")
                .append("LEFT JOIN BID_UNIT10 BOS2B ON PRJ.BOS2B_CODE = BOS2B.CODE ")
                .append("LEFT JOIN BID_UNIT10 BOS2C ON PRJ.BOS2C_CODE = BOS2C.CODE ")
                .append("LEFT JOIN BID_UNIT10 BOS2D ON PRJ.BOS2D_CODE = BOS2D.CODE ")
                .append("LEFT JOIN ADM_SYS_CODE TYPE ON TYPE.MODULE_TYPE = 'BID' ")
                .append("AND TYPE.DATA_TYPE = 'BID_003' ")
                .append("AND PRJ.TYPE_CODE = TYPE.DATA_KEY ")
                .append("LEFT JOIN ADM_SYS_CODE WKIND ON WKIND.MODULE_TYPE = 'BID' ")
                .append("AND WKIND.DATA_TYPE = 'BID_005' ")
                .append("AND PRJ.W_KIND = WKIND.DATA_KEY ")
                .append("LEFT JOIN ADM_SYS_CODE CTMETH ON CTMETH.MODULE_TYPE = 'BID' ")
                .append("AND CTMETH.DATA_TYPE = 'BID_006' ")
                .append("AND PRJ.CTMETH = CTMETH.DATA_KEY ")
                .append("LEFT JOIN ADM_SYS_CODE ACTMTH ON ACTMTH.MODULE_TYPE = 'BID' ")
                .append("AND ACTMTH.DATA_TYPE = 'BID_007' ")
                .append("AND PRJ.ACTMTH = ACTMTH.DATA_KEY ")
                .append("LEFT JOIN ADM_SYS_CODE MCTMTH ON MCTMTH.MODULE_TYPE = 'BID' ")
                .append("AND MCTMTH.DATA_TYPE = 'BID_009' ")
                .append("AND PRJ.ACTMTH = MCTMTH.DATA_KEY ")
                .append("LEFT JOIN ADM_SYS_CODE TCTMTH ON TCTMTH.MODULE_TYPE = 'BID' ")
                .append("AND TCTMTH.DATA_TYPE = 'BID_008' ")
                .append("AND PRJ.ACTMTH = TCTMTH.DATA_KEY ")
                .append("LEFT JOIN BID_PLACE PLACE ON PRJ.PLACE_CODE = PLACE.PLACE_CODE ")
                .append("LEFT JOIN BID_UNIT10 DRUT ON PRJ.DRUT = DRUT.CODE ")
                .append("LEFT JOIN BID_UNIT10 PCMUT ON PRJ.PCMUT = PCMUT.CODE ")
                .append("LEFT JOIN BID_UNIT10 CKP ON PRJ.CKP_UNIT = CKP.CODE ")
                .append("LEFT JOIN BID_UNIT10 CKA ON PRJ.CKA_UNIT = CKA.CODE ")
                .append("LEFT JOIN BID_UNIT10 CKD ON PRJ.CKD_UNIT = CKD.CODE ")
                .append("WHERE 1=1 ")
                .append("AND PRJ.WKUT = :wkut", wkut)
                .append("AND PRJ.PRJNO = :prjno", prjno)
                .build();
        List<BidProjectBaseDTO> sqlResult = sqlExecutor.queryForList(query, BidProjectBaseDTO.class);
        BidProjectBaseDTO bidProjectBaseDTO = sqlResult.stream().findFirst().orElse(null);
        if (bidProjectBaseDTO != null) {
            Query query11 = Query
                    .builder()
                    .append("SELECT BPU.*, BU.NAME DSUT_NAME FROM BID_PROJECT_UNIT BPU ")
                    .append("LEFT JOIN BID_UNIT10 BU ON BPU.DSUT = BU.CODE ")
                    .append("WHERE 1=1 ")
                    .append("AND BPU.WKUT = :wkut", wkut)
                    .append("AND BPU.PRJNO = :prjno", prjno)
                    .append("AND BPU.TYPE = :type", "1")
                    .append("ORDER by SEQ ")
                    .build();
            List<BidProjectUnitDTO> dsuts = sqlExecutor.queryForList(query11, BidProjectUnitDTO.class);
            bidProjectBaseDTO.setDsuts(dsuts);

            Query query12 = Query
                    .builder()
                    .append("SELECT BPU.*, BU.NAME SCUT_NAME FROM BID_PROJECT_UNIT BPU ")
                    .append("LEFT JOIN BID_UNIT10 BU ON BPU.SCUT = BU.CODE ")
                    .append("WHERE 1=1 ")
                    .append("AND BPU.WKUT = :wkut", wkut)
                    .append("AND BPU.PRJNO = :prjno", prjno)
                    .append("AND BPU.TYPE = :type", "2")
                    .append("ORDER by SEQ ")
                    .build();
            List<BidProjectUnitDTO> scuts = sqlExecutor.queryForList(query12, BidProjectUnitDTO.class);
            bidProjectBaseDTO.setScuts(scuts);

            Query query13 = Query
                    .builder()
                    .append("SELECT BPU.*, BU.NAME CCUT_NAME FROM BID_PROJECT_UNIT BPU ")
                    .append("LEFT JOIN BID_UNIT10 BU ON BPU.CCUT = BU.CODE ")
                    .append("WHERE 1=1 ")
                    .append("AND BPU.WKUT = :wkut", wkut)
                    .append("AND BPU.PRJNO = :prjno", prjno)
                    .append("AND BPU.TYPE = :type", "3")
                    .append("ORDER by SEQ ")
                    .build();
            List<BidProjectUnitDTO> ccuts = sqlExecutor.queryForList(query13, BidProjectUnitDTO.class);
            bidProjectBaseDTO.setCcuts(ccuts);

            Query query2 = Query
                    .builder()
                    .append("SELECT BPG.*, BU.NAME NCC_UNIT_NAME FROM BID_PRJ_GDFK BPG ")
                    .append("LEFT JOIN BID_UNIT10 BU ON BPG.NCC_UNIT = BU.CODE ")
                    .append("WHERE 1=1 ")
                    .append("AND WKUT = :wkut", wkut)
                    .append("AND PRJNO = :prjno", prjno)
                    .build();
            List<BidPrjGdfkDTO> bidPrjGdfkDTOs = sqlExecutor.queryForList(query2, BidPrjGdfkDTO.class);
            bidProjectBaseDTO.setBidPrjGdfkDTOs(bidPrjGdfkDTOs);

            Query query3 = Query
                    .builder()
                    .append("SELECT BPS.*, BU.NAME SUB_CCUT_NAME FROM BID_PRJ_SUBCO BPS ")
                    .append("LEFT JOIN BID_UNIT10 BU ON BPS.SUB_CCUT = BU.CODE ")
                    .append("WHERE 1=1 ")
                    .append("AND WKUT = :wkut", wkut)
                    .append("AND PRJNO = :prjno", prjno)
                    .build();
            List<BidPrjSubcoDTO> bidPrjSubcoDTOs = sqlExecutor.queryForList(query3, BidPrjSubcoDTO.class);
            bidProjectBaseDTO.setBidPrjSubcoDTOs(bidPrjSubcoDTOs);

            Query query4 = Query
                    .builder()
                    .append("SELECT BPF.*, MFT.VALUE MFT_NAME FROM BID_PRJ_FALS BPF ")
                    .append("LEFT JOIN ADM_SYS_CODE MFT ON MFT.MODULE_TYPE = 'BID' ")
                    .append("AND MFT.DATA_TYPE = 'BID_016' ")
                    .append("AND BPF.MFT = MFT.DATA_KEY ")
                    .append("WHERE 1=1 ")
                    .append("AND WKUT = :wkut", wkut)
                    .append("AND PRJNO = :prjno", prjno)
                    .build();
            List<BidPrjFalsDTO> bidPrjFalsDTOs = sqlExecutor.queryForList(query4, BidPrjFalsDTO.class);
            bidProjectBaseDTO.setBidPrjFalsDTOs(bidPrjFalsDTOs);

            Query query5 = Query
                    .builder()
                    .append("SELECT * FROM BID_PRJ_MODIFY ")
                    .append("WHERE 1=1 ")
                    .append("AND WKUT = :wkut", wkut)
                    .append("AND PRJNO = :prjno", prjno)
                    .build();
            List<BidPrjModifyDTO> bidPrjModifyDTOs = sqlExecutor.queryForList(query5, BidPrjModifyDTO.class);
            bidProjectBaseDTO.setBidPrjModifyDTOs(bidPrjModifyDTOs);

            Query query6 = Query
                    .builder()
                    .append("SELECT * FROM BID_PRJ_STOPWK ")
                    .append("WHERE 1=1 ")
                    .append("AND WKUT = :wkut", wkut)
                    .append("AND PRJNO = :prjno", prjno)
                    .build();
            List<BidPrjStopwkDTO> bidPrjStopwkDTOs = sqlExecutor.queryForList(query6, BidPrjStopwkDTO.class);
            bidProjectBaseDTO.setBidPrjStopwkDTOs(bidPrjStopwkDTOs);

            Query query7 = Query
                    .builder()
                    .append("SELECT * FROM BID_PRJ_PE ")
                    .append("WHERE 1=1 ")
                    .append("AND WKUT = :wkut", wkut)
                    .append("AND PRJNO = :prjno", prjno)
                    .build();
            List<BidPrjPeDTO> bidPrjPeDTOs = sqlExecutor.queryForList(query7, BidPrjPeDTO.class);
            bidProjectBaseDTO.setBidPrjPeDTOs(bidPrjPeDTOs);

            Query query8 = Query
                    .builder()
                    .append("SELECT BPQ.*, BP.NAME FROM BID_PRJ_QC BPQ ")
                    .append("LEFT JOIN BID_PE BP ON BPQ.ID = BP.ID ")
                    .append("WHERE 1=1 ")
                    .append("AND WKUT = :wkut", wkut)
                    .append("AND PRJNO = :prjno", prjno)
                    .build();
            List<BidPrjQcDTO> bidPrjQcDTOs = sqlExecutor.queryForList(query8, BidPrjQcDTO.class);
            for (BidPrjQcDTO bidPrjQcDTO : bidPrjQcDTOs) {
                Query query81 = Query
                        .builder()
                        .append("SELECT * FROM BID_QC_REENTRY ")
                        .append("WHERE 1=1 ")
                        .append("AND ID = :id", bidPrjQcDTO.getId())
                        .build();
                List<BidQcReentryDTO> bidQcReentryDTOs = sqlExecutor.queryForList(query81, BidQcReentryDTO.class);
                bidPrjQcDTO.setBidQcReentryDTOs(bidQcReentryDTOs);
            }
            bidProjectBaseDTO.setBidPrjQcDTOs(bidPrjQcDTOs);

            Query query9 = Query
                    .builder()
                    .append("SELECT * FROM BID_PRJ_WKMENG ")
                    .append("WHERE 1=1 ")
                    .append("AND WKUT = :wkut", wkut)
                    .append("AND PRJNO = :prjno", prjno)
                    .build();
            List<BidPrjWkmengDTO> bidPrjWkmengDTOs = sqlExecutor.queryForList(query9, BidPrjWkmengDTO.class);
            for (BidPrjWkmengDTO bidPrjWkmengDTO : bidPrjWkmengDTOs) {
                Query query91 = Query
                        .builder()
                        .append("SELECT * FROM BID_WKMENG_REENTRY ")
                        .append("WHERE 1=1 ")
                        .append("AND ID = :id", bidPrjWkmengDTO.getId())
                        .build();
                List<BidWkmengReentryDTO> bidWkmengReentryDTOs = sqlExecutor.queryForList(query91, BidWkmengReentryDTO.class);
                bidPrjWkmengDTO.setBidWkmengReentryDTOs(bidWkmengReentryDTOs);
            }
            bidProjectBaseDTO.setBidPrjWkmengDTOs(bidPrjWkmengDTOs);
        }
        return bidProjectBaseDTO;
    }



}
