package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjMonth;
import gov.pcc.pwc.repository.BidPrjMonthRepository;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import gov.pcc.pwc.service.mapper.BidPrjMonthMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BidPrjMonthResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjMonthResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_YR = "AAA";
    private static final String UPDATED_YR = "BBB";

    private static final String DEFAULT_MNTH = "AA";
    private static final String UPDATED_MNTH = "BB";

    private static final String DEFAULT_SYR = "AAA";
    private static final String UPDATED_SYR = "BBB";

    private static final BigDecimal DEFAULT_YPEXP = new BigDecimal(1);
    private static final BigDecimal UPDATED_YPEXP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PEXP = new BigDecimal(1);
    private static final BigDecimal UPDATED_PEXP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PTRATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PTRATE = new BigDecimal(2);

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_YAEXP = new BigDecimal(1);
    private static final BigDecimal UPDATED_YAEXP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ARAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ARAT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AEXP = new BigDecimal(1);
    private static final BigDecimal UPDATED_AEXP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ATRATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ATRATE = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SUMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUMT = new BigDecimal(2);

    private static final String DEFAULT_STUS = "AA";
    private static final String UPDATED_STUS = "BB";

    private static final String DEFAULT_MDSTS = "A";
    private static final String UPDATED_MDSTS = "B";

    private static final String DEFAULT_SUMDAT = "AAAAAAA";
    private static final String UPDATED_SUMDAT = "BBBBBBB";

    private static final BigDecimal DEFAULT_NOPAYAMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NOPAYAMT = new BigDecimal(2);

    private static final String DEFAULT_NOPAYRESN = "AA";
    private static final String UPDATED_NOPAYRESN = "BB";

    private static final BigDecimal DEFAULT_RESUMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_RESUMT = new BigDecimal(2);

    private static final LocalDate DEFAULT_TRDT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRDT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOPAYDETEL = "AAAAAAAAAA";
    private static final String UPDATED_NOPAYDETEL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MEET_CNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MEET_CNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_METER_CHK = new BigDecimal(1);
    private static final BigDecimal UPDATED_METER_CHK = new BigDecimal(2);

    private static final BigDecimal DEFAULT_METER_CHK_F = new BigDecimal(1);
    private static final BigDecimal UPDATED_METER_CHK_F = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORK_CHK = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORK_CHK = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORK_CHK_F = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORK_CHK_F = new BigDecimal(2);

    private static final String DEFAULT_REPL_METH = "AA";
    private static final String UPDATED_REPL_METH = "BB";

    private static final BigDecimal DEFAULT_TECKIND_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_TECKIND_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ENVI_CNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ENVI_CNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ENVI_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ENVI_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WSAFE_CNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_WSAFE_CNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WSAFE_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_WSAFE_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_1_B = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_1_B = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_1_C = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_1_C = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_1_D = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_1_D = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_5 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_5 = new BigDecimal(2);

    private static final String DEFAULT_SPCW_01 = "A";
    private static final String UPDATED_SPCW_01 = "B";

    private static final String DEFAULT_SPCW_02 = "A";
    private static final String UPDATED_SPCW_02 = "B";

    private static final String DEFAULT_SPCW_03 = "A";
    private static final String UPDATED_SPCW_03 = "B";

    private static final String DEFAULT_SPCW_04 = "A";
    private static final String UPDATED_SPCW_04 = "B";

    private static final String DEFAULT_SPCW_05 = "A";
    private static final String UPDATED_SPCW_05 = "B";

    private static final String DEFAULT_SPCW_06 = "A";
    private static final String UPDATED_SPCW_06 = "B";

    private static final String DEFAULT_SPCW_07 = "A";
    private static final String UPDATED_SPCW_07 = "B";

    private static final String DEFAULT_SPCW_08 = "A";
    private static final String UPDATED_SPCW_08 = "B";

    private static final String DEFAULT_SPCW_09 = "A";
    private static final String UPDATED_SPCW_09 = "B";

    private static final String DEFAULT_SPCW_10 = "A";
    private static final String UPDATED_SPCW_10 = "B";

    private static final String DEFAULT_SPCW_11 = "A";
    private static final String UPDATED_SPCW_11 = "B";

    private static final String DEFAULT_SPCW_12 = "A";
    private static final String UPDATED_SPCW_12 = "B";

    private static final String DEFAULT_EQUP_01 = "A";
    private static final String UPDATED_EQUP_01 = "B";

    private static final String DEFAULT_EQUP_02 = "A";
    private static final String UPDATED_EQUP_02 = "B";

    private static final String DEFAULT_EQUP_03 = "A";
    private static final String UPDATED_EQUP_03 = "B";

    private static final String DEFAULT_EQUP_04 = "A";
    private static final String UPDATED_EQUP_04 = "B";

    private static final String DEFAULT_EQUP_05 = "A";
    private static final String UPDATED_EQUP_05 = "B";

    private static final String DEFAULT_EQUP_06 = "A";
    private static final String UPDATED_EQUP_06 = "B";

    private static final String DEFAULT_EQUP_07 = "A";
    private static final String UPDATED_EQUP_07 = "B";

    private static final String DEFAULT_EQUP_08 = "A";
    private static final String UPDATED_EQUP_08 = "B";

    private static final String DEFAULT_EQUP_09 = "A";
    private static final String UPDATED_EQUP_09 = "B";

    private static final String DEFAULT_EQUP_10 = "A";
    private static final String UPDATED_EQUP_10 = "B";

    private static final String DEFAULT_EQUP_11 = "A";
    private static final String UPDATED_EQUP_11 = "B";

    private static final BigDecimal DEFAULT_COSEND = new BigDecimal(1);
    private static final BigDecimal UPDATED_COSEND = new BigDecimal(2);

    private static final BigDecimal DEFAULT_A_TRATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_A_TRATE = new BigDecimal(2);

    private static final Integer DEFAULT_WDAYEDS = 1;
    private static final Integer UPDATED_WDAYEDS = 2;

    private static final BigDecimal DEFAULT_SORC_3_B = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_B = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_C = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_C = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_D = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_D = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_E = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_E = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_A = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_A = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_6 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_6 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_B_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_B_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_B_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_B_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_B_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_B_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_B_5 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_B_5 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_C_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_C_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_D_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_D_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_D_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_D_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_D_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_D_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TECKIND_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_TECKIND_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7_USE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_USE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7_OTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_OTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7_TAC = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_TAC = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7_SELF = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_SELF = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_8 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_8 = new BigDecimal(2);

    private static final String DEFAULT_PAY_ARR = "A";
    private static final String UPDATED_PAY_ARR = "B";

    private static final BigDecimal DEFAULT_SORC_7_USE_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_USE_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7_USE_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_USE_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SFR_WKR = new BigDecimal(1);
    private static final BigDecimal UPDATED_SFR_WKR = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_9 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_9 = new BigDecimal(2);

    private static final String DEFAULT_DSTS = "A";
    private static final String UPDATED_DSTS = "B";

    private static final BigDecimal DEFAULT_CONF_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONF_OUT_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT_2 = new BigDecimal(2);

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_UDT_2 = "AAAAAAA";
    private static final String UPDATED_UDT_2 = "BBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-months";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjMonthRepository bidPrjMonthRepository;

    @Autowired
    private BidPrjMonthMapper bidPrjMonthMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjMonthMockMvc;

    private BidPrjMonth bidPrjMonth;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjMonth createEntity(EntityManager em) {
        BidPrjMonth bidPrjMonth = new BidPrjMonth()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .year(DEFAULT_YR)
            .month(DEFAULT_MNTH)
            .syr(DEFAULT_SYR)
            .item(DEFAULT_ITEM)
            .ptrate(DEFAULT_PTRATE)
            .pexp(DEFAULT_PEXP)
            .rate(DEFAULT_RATE)
            .ypexp(DEFAULT_YPEXP)
            .yaexp(DEFAULT_YAEXP)
            .arat(DEFAULT_ARAT)
            .aexp(DEFAULT_AEXP)
            .atrate(DEFAULT_ATRATE)
            .remark(DEFAULT_REMARK)
            .sumt(DEFAULT_SUMT)
            .stus(DEFAULT_STUS)
            .mdsts(DEFAULT_MDSTS)
            .sumdat(DEFAULT_SUMDAT)
            .nopayamt(DEFAULT_NOPAYAMT)
            .nopayresn(DEFAULT_NOPAYRESN)
            .resumt(DEFAULT_RESUMT)
            .trdt(DEFAULT_TRDT)
            .nopaydetel(DEFAULT_NOPAYDETEL)
            .meetCnt(DEFAULT_MEET_CNT)
            .meterChk(DEFAULT_METER_CHK)
            .meterChkF(DEFAULT_METER_CHK_F)
            .workChk(DEFAULT_WORK_CHK)
            .workChkF(DEFAULT_WORK_CHK_F)
            .replMeth(DEFAULT_REPL_METH)
            .teckind3(DEFAULT_TECKIND_3)
            .enviCnt(DEFAULT_ENVI_CNT)
            .enviFee(DEFAULT_ENVI_FEE)
            .wsafeCnt(DEFAULT_WSAFE_CNT)
            .wsafeFee(DEFAULT_WSAFE_FEE)
            .sorc1(DEFAULT_SORC_1)
            .sorc1b(DEFAULT_SORC_1_B)
            .sorc1c(DEFAULT_SORC_1_C)
            .sorc1d(DEFAULT_SORC_1_D)
            .sorc2(DEFAULT_SORC_2)
            .sorc3(DEFAULT_SORC_3)
            .sorc4(DEFAULT_SORC_4)
            .sorc5(DEFAULT_SORC_5)
            .spcw01(DEFAULT_SPCW_01)
            .spcw02(DEFAULT_SPCW_02)
            .spcw03(DEFAULT_SPCW_03)
            .spcw04(DEFAULT_SPCW_04)
            .spcw05(DEFAULT_SPCW_05)
            .spcw06(DEFAULT_SPCW_06)
            .spcw07(DEFAULT_SPCW_07)
            .spcw08(DEFAULT_SPCW_08)
            .spcw09(DEFAULT_SPCW_09)
            .spcw10(DEFAULT_SPCW_10)
            .spcw11(DEFAULT_SPCW_11)
            .spcw12(DEFAULT_SPCW_12)
            .equp01(DEFAULT_EQUP_01)
            .equp02(DEFAULT_EQUP_02)
            .equp03(DEFAULT_EQUP_03)
            .equp04(DEFAULT_EQUP_04)
            .equp05(DEFAULT_EQUP_05)
            .equp06(DEFAULT_EQUP_06)
            .equp07(DEFAULT_EQUP_07)
            .equp08(DEFAULT_EQUP_08)
            .equp09(DEFAULT_EQUP_09)
            .equp10(DEFAULT_EQUP_10)
            .equp11(DEFAULT_EQUP_11)
            .cosend(DEFAULT_COSEND)
            .atrate(DEFAULT_A_TRATE)
            .wdayeds(DEFAULT_WDAYEDS)
            .sorc3b(DEFAULT_SORC_3_B)
            .sorc3c(DEFAULT_SORC_3_C)
            .sorc3d(DEFAULT_SORC_3_D)
            .sorc3e(DEFAULT_SORC_3_E)
            .sorc3a(DEFAULT_SORC_3_A)
            .sorc6(DEFAULT_SORC_6)
            .sorc3b2(DEFAULT_SORC_3_B_2)
            .sorc3b3(DEFAULT_SORC_3_B_3)
            .sorc3b4(DEFAULT_SORC_3_B_4)
            .sorc3b5(DEFAULT_SORC_3_B_5)
            .sorc3c1(DEFAULT_SORC_3_C_1)
            .sorc3d1(DEFAULT_SORC_3_D_1)
            .sorc3d3(DEFAULT_SORC_3_D_3)
            .sorc3d4(DEFAULT_SORC_3_D_4)
            .teckind4(DEFAULT_TECKIND_4)
            .sorc7(DEFAULT_SORC_7)
            .sorc7use(DEFAULT_SORC_7_USE)
            .sorc7oth(DEFAULT_SORC_7_OTH)
            .sorc7tac(DEFAULT_SORC_7_TAC)
            .sorc7self(DEFAULT_SORC_7_SELF)
            .sorc8(DEFAULT_SORC_8)
            .payArr(DEFAULT_PAY_ARR)
            .sorc7use2(DEFAULT_SORC_7_USE_2)
            .sorc7use3(DEFAULT_SORC_7_USE_3)
            .sfrWkr(DEFAULT_SFR_WKR)
            .sorc9(DEFAULT_SORC_9)
            .dsts(DEFAULT_DSTS)
            .confOut(DEFAULT_CONF_OUT)
            .confOut2(DEFAULT_CONF_OUT_2)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER);
        return bidPrjMonth;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjMonth createUpdatedEntity(EntityManager em) {
        BidPrjMonth bidPrjMonth = new BidPrjMonth()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YR)
            .month(UPDATED_MNTH)
            .syr(UPDATED_SYR)
            .item(UPDATED_ITEM)
            .ptrate(UPDATED_PTRATE)
            .pexp(UPDATED_PEXP)
            .rate(UPDATED_RATE)
            .ypexp(UPDATED_YPEXP)
            .yaexp(UPDATED_YAEXP)
            .arat(UPDATED_ARAT)
            .aexp(UPDATED_AEXP)
            .atrate(UPDATED_ATRATE)
            .remark(UPDATED_REMARK)
            .sumt(UPDATED_SUMT)
            .stus(UPDATED_STUS)
            .mdsts(UPDATED_MDSTS)
            .sumdat(UPDATED_SUMDAT)
            .nopayamt(UPDATED_NOPAYAMT)
            .nopayresn(UPDATED_NOPAYRESN)
            .resumt(UPDATED_RESUMT)
            .trdt(UPDATED_TRDT)
            .nopaydetel(UPDATED_NOPAYDETEL)
            .meetCnt(UPDATED_MEET_CNT)
            .meterChk(UPDATED_METER_CHK)
            .meterChkF(UPDATED_METER_CHK_F)
            .workChk(UPDATED_WORK_CHK)
            .workChkF(UPDATED_WORK_CHK_F)
            .replMeth(UPDATED_REPL_METH)
            .teckind3(UPDATED_TECKIND_3)
            .enviCnt(UPDATED_ENVI_CNT)
            .enviFee(UPDATED_ENVI_FEE)
            .wsafeCnt(UPDATED_WSAFE_CNT)
            .wsafeFee(UPDATED_WSAFE_FEE)
            .sorc1(UPDATED_SORC_1)
            .sorc1b(UPDATED_SORC_1_B)
            .sorc1c(UPDATED_SORC_1_C)
            .sorc1d(UPDATED_SORC_1_D)
            .sorc2(UPDATED_SORC_2)
            .sorc3(UPDATED_SORC_3)
            .sorc4(UPDATED_SORC_4)
            .sorc5(UPDATED_SORC_5)
            .spcw01(UPDATED_SPCW_01)
            .spcw02(UPDATED_SPCW_02)
            .spcw03(UPDATED_SPCW_03)
            .spcw04(UPDATED_SPCW_04)
            .spcw05(UPDATED_SPCW_05)
            .spcw06(UPDATED_SPCW_06)
            .spcw07(UPDATED_SPCW_07)
            .spcw08(UPDATED_SPCW_08)
            .spcw09(UPDATED_SPCW_09)
            .spcw10(UPDATED_SPCW_10)
            .spcw11(UPDATED_SPCW_11)
            .spcw12(UPDATED_SPCW_12)
            .equp01(UPDATED_EQUP_01)
            .equp02(UPDATED_EQUP_02)
            .equp03(UPDATED_EQUP_03)
            .equp04(UPDATED_EQUP_04)
            .equp05(UPDATED_EQUP_05)
            .equp06(UPDATED_EQUP_06)
            .equp07(UPDATED_EQUP_07)
            .equp08(UPDATED_EQUP_08)
            .equp09(UPDATED_EQUP_09)
            .equp10(UPDATED_EQUP_10)
            .equp11(UPDATED_EQUP_11)
            .cosend(UPDATED_COSEND)
            .atrate(UPDATED_A_TRATE)
            .wdayeds(UPDATED_WDAYEDS)
            .sorc3b(UPDATED_SORC_3_B)
            .sorc3c(UPDATED_SORC_3_C)
            .sorc3d(UPDATED_SORC_3_D)
            .sorc3e(UPDATED_SORC_3_E)
            .sorc3a(UPDATED_SORC_3_A)
            .sorc6(UPDATED_SORC_6)
            .sorc3b2(UPDATED_SORC_3_B_2)
            .sorc3b3(UPDATED_SORC_3_B_3)
            .sorc3b4(UPDATED_SORC_3_B_4)
            .sorc3b5(UPDATED_SORC_3_B_5)
            .sorc3c1(UPDATED_SORC_3_C_1)
            .sorc3d1(UPDATED_SORC_3_D_1)
            .sorc3d3(UPDATED_SORC_3_D_3)
            .sorc3d4(UPDATED_SORC_3_D_4)
            .teckind4(UPDATED_TECKIND_4)
            .sorc7(UPDATED_SORC_7)
            .sorc7use(UPDATED_SORC_7_USE)
            .sorc7oth(UPDATED_SORC_7_OTH)
            .sorc7tac(UPDATED_SORC_7_TAC)
            .sorc7self(UPDATED_SORC_7_SELF)
            .sorc8(UPDATED_SORC_8)
            .payArr(UPDATED_PAY_ARR)
            .sorc7use2(UPDATED_SORC_7_USE_2)
            .sorc7use3(UPDATED_SORC_7_USE_3)
            .sfrWkr(UPDATED_SFR_WKR)
            .sorc9(UPDATED_SORC_9)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .confOut2(UPDATED_CONF_OUT_2)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        return bidPrjMonth;
    }

    @BeforeEach
    public void initTest() {
        bidPrjMonth = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjMonth() throws Exception {
        int databaseSizeBeforeCreate = bidPrjMonthRepository.findAll().size();
        // Create the BidPrjMonth
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);
        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjMonth in the database
        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjMonth testBidPrjMonth = bidPrjMonthList.get(bidPrjMonthList.size() - 1);
        assertThat(testBidPrjMonth.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjMonth.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjMonth.getYr()).isEqualTo(DEFAULT_YR);
        assertThat(testBidPrjMonth.getMonth()).isEqualTo(DEFAULT_MNTH);
        assertThat(testBidPrjMonth.getSyr()).isEqualTo(DEFAULT_SYR);
        assertThat(testBidPrjMonth.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testBidPrjMonth.getPtrate()).isEqualTo(DEFAULT_PTRATE);
        assertThat(testBidPrjMonth.getPexp()).isEqualTo(DEFAULT_PEXP);
        assertThat(testBidPrjMonth.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testBidPrjMonth.getYpexp()).isEqualTo(DEFAULT_YPEXP);
        assertThat(testBidPrjMonth.getYaexp()).isEqualTo(DEFAULT_YAEXP);
        assertThat(testBidPrjMonth.getArat()).isEqualTo(DEFAULT_ARAT);
        assertThat(testBidPrjMonth.getAexp()).isEqualTo(DEFAULT_AEXP);
        assertThat(testBidPrjMonth.getAtrate()).isEqualTo(DEFAULT_ATRATE);
        assertThat(testBidPrjMonth.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBidPrjMonth.getSumt()).isEqualTo(DEFAULT_SUMT);
        assertThat(testBidPrjMonth.getStus()).isEqualTo(DEFAULT_STUS);
        assertThat(testBidPrjMonth.getMdsts()).isEqualTo(DEFAULT_MDSTS);
        assertThat(testBidPrjMonth.getSumdat()).isEqualTo(DEFAULT_SUMDAT);
        assertThat(testBidPrjMonth.getNopayamt()).isEqualTo(DEFAULT_NOPAYAMT);
        assertThat(testBidPrjMonth.getNopayresn()).isEqualTo(DEFAULT_NOPAYRESN);
        assertThat(testBidPrjMonth.getResumt()).isEqualTo(DEFAULT_RESUMT);
        assertThat(testBidPrjMonth.getTrdt()).isEqualTo(DEFAULT_TRDT);
        assertThat(testBidPrjMonth.getNopaydetel()).isEqualTo(DEFAULT_NOPAYDETEL);
        assertThat(testBidPrjMonth.getMeetCnt()).isEqualByComparingTo(DEFAULT_MEET_CNT);
        assertThat(testBidPrjMonth.getMeterChk()).isEqualByComparingTo(DEFAULT_METER_CHK);
        assertThat(testBidPrjMonth.getMeterChkF()).isEqualByComparingTo(DEFAULT_METER_CHK_F);
        assertThat(testBidPrjMonth.getWorkChk()).isEqualByComparingTo(DEFAULT_WORK_CHK);
        assertThat(testBidPrjMonth.getWorkChkF()).isEqualByComparingTo(DEFAULT_WORK_CHK_F);
        assertThat(testBidPrjMonth.getReplMeth()).isEqualTo(DEFAULT_REPL_METH);
        assertThat(testBidPrjMonth.getTeckind3()).isEqualByComparingTo(DEFAULT_TECKIND_3);
        assertThat(testBidPrjMonth.getEnviCnt()).isEqualByComparingTo(DEFAULT_ENVI_CNT);
        assertThat(testBidPrjMonth.getEnviFee()).isEqualTo(DEFAULT_ENVI_FEE);
        assertThat(testBidPrjMonth.getWsafeCnt()).isEqualByComparingTo(DEFAULT_WSAFE_CNT);
        assertThat(testBidPrjMonth.getWsafeFee()).isEqualTo(DEFAULT_WSAFE_FEE);
        assertThat(testBidPrjMonth.getSorc1()).isEqualTo(DEFAULT_SORC_1);
        assertThat(testBidPrjMonth.getSorc1b()).isEqualTo(DEFAULT_SORC_1_B);
        assertThat(testBidPrjMonth.getSorc1c()).isEqualTo(DEFAULT_SORC_1_C);
        assertThat(testBidPrjMonth.getSorc1d()).isEqualTo(DEFAULT_SORC_1_D);
        assertThat(testBidPrjMonth.getSorc2()).isEqualTo(DEFAULT_SORC_2);
        assertThat(testBidPrjMonth.getSorc3()).isEqualTo(DEFAULT_SORC_3);
        assertThat(testBidPrjMonth.getSorc4()).isEqualTo(DEFAULT_SORC_4);
        assertThat(testBidPrjMonth.getSorc5()).isEqualTo(DEFAULT_SORC_5);
        assertThat(testBidPrjMonth.getSpcw01()).isEqualTo(DEFAULT_SPCW_01);
        assertThat(testBidPrjMonth.getSpcw02()).isEqualTo(DEFAULT_SPCW_02);
        assertThat(testBidPrjMonth.getSpcw03()).isEqualTo(DEFAULT_SPCW_03);
        assertThat(testBidPrjMonth.getSpcw04()).isEqualTo(DEFAULT_SPCW_04);
        assertThat(testBidPrjMonth.getSpcw05()).isEqualTo(DEFAULT_SPCW_05);
        assertThat(testBidPrjMonth.getSpcw06()).isEqualTo(DEFAULT_SPCW_06);
        assertThat(testBidPrjMonth.getSpcw07()).isEqualTo(DEFAULT_SPCW_07);
        assertThat(testBidPrjMonth.getSpcw08()).isEqualTo(DEFAULT_SPCW_08);
        assertThat(testBidPrjMonth.getSpcw09()).isEqualTo(DEFAULT_SPCW_09);
        assertThat(testBidPrjMonth.getSpcw10()).isEqualTo(DEFAULT_SPCW_10);
        assertThat(testBidPrjMonth.getSpcw11()).isEqualTo(DEFAULT_SPCW_11);
        assertThat(testBidPrjMonth.getSpcw12()).isEqualTo(DEFAULT_SPCW_12);
        assertThat(testBidPrjMonth.getEqup01()).isEqualTo(DEFAULT_EQUP_01);
        assertThat(testBidPrjMonth.getEqup02()).isEqualTo(DEFAULT_EQUP_02);
        assertThat(testBidPrjMonth.getEqup03()).isEqualTo(DEFAULT_EQUP_03);
        assertThat(testBidPrjMonth.getEqup04()).isEqualTo(DEFAULT_EQUP_04);
        assertThat(testBidPrjMonth.getEqup05()).isEqualTo(DEFAULT_EQUP_05);
        assertThat(testBidPrjMonth.getEqup06()).isEqualTo(DEFAULT_EQUP_06);
        assertThat(testBidPrjMonth.getEqup07()).isEqualTo(DEFAULT_EQUP_07);
        assertThat(testBidPrjMonth.getEqup08()).isEqualTo(DEFAULT_EQUP_08);
        assertThat(testBidPrjMonth.getEqup09()).isEqualTo(DEFAULT_EQUP_09);
        assertThat(testBidPrjMonth.getEqup10()).isEqualTo(DEFAULT_EQUP_10);
        assertThat(testBidPrjMonth.getEqup11()).isEqualTo(DEFAULT_EQUP_11);
        assertThat(testBidPrjMonth.getCosend()).isEqualByComparingTo(DEFAULT_COSEND);
        assertThat(testBidPrjMonth.getAtrate()).isEqualTo(DEFAULT_A_TRATE);
        assertThat(testBidPrjMonth.getWdayeds()).isEqualTo(DEFAULT_WDAYEDS);
        assertThat(testBidPrjMonth.getSorc3b()).isEqualTo(DEFAULT_SORC_3_B);
        assertThat(testBidPrjMonth.getSorc3c()).isEqualTo(DEFAULT_SORC_3_C);
        assertThat(testBidPrjMonth.getSorc3d()).isEqualTo(DEFAULT_SORC_3_D);
        assertThat(testBidPrjMonth.getSorc3e()).isEqualTo(DEFAULT_SORC_3_E);
        assertThat(testBidPrjMonth.getSorc3a()).isEqualTo(DEFAULT_SORC_3_A);
        assertThat(testBidPrjMonth.getSorc6()).isEqualTo(DEFAULT_SORC_6);
        assertThat(testBidPrjMonth.getSorc3b2()).isEqualTo(DEFAULT_SORC_3_B_2);
        assertThat(testBidPrjMonth.getSorc3b3()).isEqualTo(DEFAULT_SORC_3_B_3);
        assertThat(testBidPrjMonth.getSorc3b4()).isEqualTo(DEFAULT_SORC_3_B_4);
        assertThat(testBidPrjMonth.getSorc3b5()).isEqualTo(DEFAULT_SORC_3_B_5);
        assertThat(testBidPrjMonth.getSorc3c1()).isEqualTo(DEFAULT_SORC_3_C_1);
        assertThat(testBidPrjMonth.getSorc3d1()).isEqualTo(DEFAULT_SORC_3_D_1);
        assertThat(testBidPrjMonth.getSorc3d3()).isEqualTo(DEFAULT_SORC_3_D_3);
        assertThat(testBidPrjMonth.getSorc3d4()).isEqualTo(DEFAULT_SORC_3_D_4);
        assertThat(testBidPrjMonth.getTeckind4()).isEqualByComparingTo(DEFAULT_TECKIND_4);
        assertThat(testBidPrjMonth.getSorc7()).isEqualTo(DEFAULT_SORC_7);
        assertThat(testBidPrjMonth.getSorc7use()).isEqualTo(DEFAULT_SORC_7_USE);
        assertThat(testBidPrjMonth.getSorc7oth()).isEqualTo(DEFAULT_SORC_7_OTH);
        assertThat(testBidPrjMonth.getSorc7tac()).isEqualTo(DEFAULT_SORC_7_TAC);
        assertThat(testBidPrjMonth.getSorc7self()).isEqualTo(DEFAULT_SORC_7_SELF);
        assertThat(testBidPrjMonth.getSorc8()).isEqualTo(DEFAULT_SORC_8);
        assertThat(testBidPrjMonth.getPayArr()).isEqualTo(DEFAULT_PAY_ARR);
        assertThat(testBidPrjMonth.getSorc7use2()).isEqualTo(DEFAULT_SORC_7_USE_2);
        assertThat(testBidPrjMonth.getSorc7use3()).isEqualTo(DEFAULT_SORC_7_USE_3);
        assertThat(testBidPrjMonth.getSfrWkr()).isEqualTo(DEFAULT_SFR_WKR);
        assertThat(testBidPrjMonth.getSorc9()).isEqualTo(DEFAULT_SORC_9);
        assertThat(testBidPrjMonth.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjMonth.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjMonth.getConfOut2()).isEqualByComparingTo(DEFAULT_CONF_OUT_2);
        assertThat(testBidPrjMonth.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjMonth.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjMonth.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjMonth.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    void createBidPrjMonthWithExistingId() throws Exception {
        // Create the BidPrjMonth with an existing ID
        bidPrjMonth.setWkut("9876543210");
        bidPrjMonth.setPrjno("1999999");
        bidPrjMonth.setYr("111");
        bidPrjMonth.setMonth("4");
        bidPrjMonth.setSyr("111");
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);

        int databaseSizeBeforeCreate = bidPrjMonthRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjMonth in the database
        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjMonthRepository.findAll().size();
        // set the field null
        bidPrjMonth.setWkut(null);

        // Create the BidPrjMonth, which fails.
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);

        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjMonthRepository.findAll().size();
        // set the field null
        bidPrjMonth.setPrjno(null);

        // Create the BidPrjMonth, which fails.
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);

        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkYrIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjMonthRepository.findAll().size();
        // set the field null
        bidPrjMonth.setYr(null);

        // Create the BidPrjMonth, which fails.
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);

        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMnthIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjMonthRepository.findAll().size();
        // set the field null
        bidPrjMonth.setMonth(null);

        // Create the BidPrjMonth, which fails.
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);

        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSyrIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjMonthRepository.findAll().size();
        // set the field null
        bidPrjMonth.setSyr(null);

        // Create the BidPrjMonth, which fails.
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);

        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjMonthRepository.findAll().size();
        // set the field null
        bidPrjMonth.setCreateDate(null);

        // Create the BidPrjMonth, which fails.
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);

        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjMonthRepository.findAll().size();
        // set the field null
        bidPrjMonth.setCreateUser(null);

        // Create the BidPrjMonth, which fails.
        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);

        restBidPrjMonthMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBidPrjMonths() throws Exception {
        // Initialize the database
        bidPrjMonthRepository.saveAndFlush(bidPrjMonth);

        // Get all the bidPrjMonthList
        restBidPrjMonthMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].yr").value(hasItem(DEFAULT_YR)))
            .andExpect(jsonPath("$.[*].mnth").value(hasItem(DEFAULT_MNTH)))
            .andExpect(jsonPath("$.[*].syr").value(hasItem(DEFAULT_SYR)))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].ptrate").value(hasItem(DEFAULT_PTRATE.doubleValue())))
            .andExpect(jsonPath("$.[*].pexp").value(hasItem(DEFAULT_PEXP.doubleValue())))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].ypexp").value(hasItem(DEFAULT_YPEXP.doubleValue())))
            .andExpect(jsonPath("$.[*].yaexp").value(hasItem(DEFAULT_YAEXP.doubleValue())))
            .andExpect(jsonPath("$.[*].arat").value(hasItem(DEFAULT_ARAT.doubleValue())))
            .andExpect(jsonPath("$.[*].aexp").value(hasItem(DEFAULT_AEXP.doubleValue())))
            .andExpect(jsonPath("$.[*].atrate").value(hasItem(DEFAULT_ATRATE.doubleValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].sumt").value(hasItem(DEFAULT_SUMT.doubleValue())))
            .andExpect(jsonPath("$.[*].stus").value(hasItem(DEFAULT_STUS)))
            .andExpect(jsonPath("$.[*].mdsts").value(hasItem(DEFAULT_MDSTS)))
            .andExpect(jsonPath("$.[*].sumdat").value(hasItem(DEFAULT_SUMDAT)))
            .andExpect(jsonPath("$.[*].nopayamt").value(hasItem(DEFAULT_NOPAYAMT.doubleValue())))
            .andExpect(jsonPath("$.[*].nopayresn").value(hasItem(DEFAULT_NOPAYRESN)))
            .andExpect(jsonPath("$.[*].resumt").value(hasItem(DEFAULT_RESUMT.doubleValue())))
            .andExpect(jsonPath("$.[*].trdt").value(hasItem(DEFAULT_TRDT)))
            .andExpect(jsonPath("$.[*].nopaydetel").value(hasItem(DEFAULT_NOPAYDETEL)))
            .andExpect(jsonPath("$.[*].meetCnt").value(hasItem(sameNumber(DEFAULT_MEET_CNT))))
            .andExpect(jsonPath("$.[*].meterChk").value(hasItem(sameNumber(DEFAULT_METER_CHK))))
            .andExpect(jsonPath("$.[*].meterChkF").value(hasItem(sameNumber(DEFAULT_METER_CHK_F))))
            .andExpect(jsonPath("$.[*].workChk").value(hasItem(sameNumber(DEFAULT_WORK_CHK))))
            .andExpect(jsonPath("$.[*].workChkF").value(hasItem(sameNumber(DEFAULT_WORK_CHK_F))))
            .andExpect(jsonPath("$.[*].replMeth").value(hasItem(DEFAULT_REPL_METH)))
            .andExpect(jsonPath("$.[*].teckind3").value(hasItem(sameNumber(DEFAULT_TECKIND_3))))
            .andExpect(jsonPath("$.[*].enviCnt").value(hasItem(sameNumber(DEFAULT_ENVI_CNT))))
            .andExpect(jsonPath("$.[*].enviFee").value(hasItem(DEFAULT_ENVI_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].wsafeCnt").value(hasItem(sameNumber(DEFAULT_WSAFE_CNT))))
            .andExpect(jsonPath("$.[*].wsafeFee").value(hasItem(DEFAULT_WSAFE_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc1").value(hasItem(DEFAULT_SORC_1.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc1b").value(hasItem(DEFAULT_SORC_1_B.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc1c").value(hasItem(DEFAULT_SORC_1_C.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc1d").value(hasItem(DEFAULT_SORC_1_D.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc2").value(hasItem(DEFAULT_SORC_2.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3").value(hasItem(DEFAULT_SORC_3.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc4").value(hasItem(DEFAULT_SORC_4.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc5").value(hasItem(DEFAULT_SORC_5.doubleValue())))
            .andExpect(jsonPath("$.[*].spcw01").value(hasItem(DEFAULT_SPCW_01)))
            .andExpect(jsonPath("$.[*].spcw02").value(hasItem(DEFAULT_SPCW_02)))
            .andExpect(jsonPath("$.[*].spcw03").value(hasItem(DEFAULT_SPCW_03)))
            .andExpect(jsonPath("$.[*].spcw04").value(hasItem(DEFAULT_SPCW_04)))
            .andExpect(jsonPath("$.[*].spcw05").value(hasItem(DEFAULT_SPCW_05)))
            .andExpect(jsonPath("$.[*].spcw06").value(hasItem(DEFAULT_SPCW_06)))
            .andExpect(jsonPath("$.[*].spcw07").value(hasItem(DEFAULT_SPCW_07)))
            .andExpect(jsonPath("$.[*].spcw08").value(hasItem(DEFAULT_SPCW_08)))
            .andExpect(jsonPath("$.[*].spcw09").value(hasItem(DEFAULT_SPCW_09)))
            .andExpect(jsonPath("$.[*].spcw10").value(hasItem(DEFAULT_SPCW_10)))
            .andExpect(jsonPath("$.[*].spcw11").value(hasItem(DEFAULT_SPCW_11)))
            .andExpect(jsonPath("$.[*].spcw12").value(hasItem(DEFAULT_SPCW_12)))
            .andExpect(jsonPath("$.[*].equp01").value(hasItem(DEFAULT_EQUP_01)))
            .andExpect(jsonPath("$.[*].equp02").value(hasItem(DEFAULT_EQUP_02)))
            .andExpect(jsonPath("$.[*].equp03").value(hasItem(DEFAULT_EQUP_03)))
            .andExpect(jsonPath("$.[*].equp04").value(hasItem(DEFAULT_EQUP_04)))
            .andExpect(jsonPath("$.[*].equp05").value(hasItem(DEFAULT_EQUP_05)))
            .andExpect(jsonPath("$.[*].equp06").value(hasItem(DEFAULT_EQUP_06)))
            .andExpect(jsonPath("$.[*].equp07").value(hasItem(DEFAULT_EQUP_07)))
            .andExpect(jsonPath("$.[*].equp08").value(hasItem(DEFAULT_EQUP_08)))
            .andExpect(jsonPath("$.[*].equp09").value(hasItem(DEFAULT_EQUP_09)))
            .andExpect(jsonPath("$.[*].equp10").value(hasItem(DEFAULT_EQUP_10)))
            .andExpect(jsonPath("$.[*].equp11").value(hasItem(DEFAULT_EQUP_11)))
            .andExpect(jsonPath("$.[*].cosend").value(hasItem(sameNumber(DEFAULT_COSEND))))
            .andExpect(jsonPath("$.[*].aTrate").value(hasItem(DEFAULT_A_TRATE.doubleValue())))
            .andExpect(jsonPath("$.[*].wdayeds").value(hasItem(DEFAULT_WDAYEDS.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3b").value(hasItem(DEFAULT_SORC_3_B.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3c").value(hasItem(DEFAULT_SORC_3_C.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3d").value(hasItem(DEFAULT_SORC_3_D.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3e").value(hasItem(DEFAULT_SORC_3_E.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3a").value(hasItem(DEFAULT_SORC_3_A.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc6").value(hasItem(DEFAULT_SORC_6.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3b2").value(hasItem(DEFAULT_SORC_3_B_2.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3b3").value(hasItem(DEFAULT_SORC_3_B_3.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3b4").value(hasItem(DEFAULT_SORC_3_B_4.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3b5").value(hasItem(DEFAULT_SORC_3_B_5.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3c1").value(hasItem(DEFAULT_SORC_3_C_1.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3d1").value(hasItem(DEFAULT_SORC_3_D_1.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3d3").value(hasItem(DEFAULT_SORC_3_D_3.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc3d4").value(hasItem(DEFAULT_SORC_3_D_4.doubleValue())))
            .andExpect(jsonPath("$.[*].teckind4").value(hasItem(sameNumber(DEFAULT_TECKIND_4))))
            .andExpect(jsonPath("$.[*].sorc7").value(hasItem(DEFAULT_SORC_7.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc7use").value(hasItem(DEFAULT_SORC_7_USE.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc7oth").value(hasItem(DEFAULT_SORC_7_OTH.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc7tac").value(hasItem(DEFAULT_SORC_7_TAC.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc7self").value(hasItem(DEFAULT_SORC_7_SELF.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc8").value(hasItem(DEFAULT_SORC_8.doubleValue())))
            .andExpect(jsonPath("$.[*].payArr").value(hasItem(DEFAULT_PAY_ARR)))
            .andExpect(jsonPath("$.[*].sorc7use2").value(hasItem(DEFAULT_SORC_7_USE_2.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc7use3").value(hasItem(DEFAULT_SORC_7_USE_3.doubleValue())))
            .andExpect(jsonPath("$.[*].sfrWkr").value(hasItem(DEFAULT_SFR_WKR.doubleValue())))
            .andExpect(jsonPath("$.[*].sorc9").value(hasItem(DEFAULT_SORC_9.doubleValue())))
            .andExpect(jsonPath("$.[*].dsts").value(hasItem(DEFAULT_DSTS)))
            .andExpect(jsonPath("$.[*].confOut").value(hasItem(sameNumber(DEFAULT_CONF_OUT))))
            .andExpect(jsonPath("$.[*].confOut2").value(hasItem(sameNumber(DEFAULT_CONF_OUT_2))))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)));
    }

    @Test
    @Transactional
    void getBidPrjMonth() throws Exception {
//        // Initialize the database
//        bidPrjMonthRepository.saveAndFlush(bidPrjMonth);
//
//        // Get the bidPrjMonth
//        restBidPrjMonthMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidPrjMonth.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.yr").value(DEFAULT_YR))
//            .andExpect(jsonPath("$.mnth").value(DEFAULT_MNTH))
//            .andExpect(jsonPath("$.syr").value(DEFAULT_SYR))
//            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
//            .andExpect(jsonPath("$.ptrate").value(DEFAULT_PTRATE.doubleValue()))
//            .andExpect(jsonPath("$.pexp").value(DEFAULT_PEXP.doubleValue()))
//            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE.doubleValue()))
//            .andExpect(jsonPath("$.ypexp").value(DEFAULT_YPEXP.doubleValue()))
//            .andExpect(jsonPath("$.yaexp").value(DEFAULT_YAEXP.doubleValue()))
//            .andExpect(jsonPath("$.arat").value(DEFAULT_ARAT.doubleValue()))
//            .andExpect(jsonPath("$.aexp").value(DEFAULT_AEXP.doubleValue()))
//            .andExpect(jsonPath("$.atrate").value(DEFAULT_ATRATE.doubleValue()))
//            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
//            .andExpect(jsonPath("$.sumt").value(DEFAULT_SUMT.doubleValue()))
//            .andExpect(jsonPath("$.stus").value(DEFAULT_STUS))
//            .andExpect(jsonPath("$.mdsts").value(DEFAULT_MDSTS))
//            .andExpect(jsonPath("$.sumdat").value(DEFAULT_SUMDAT))
//            .andExpect(jsonPath("$.nopayamt").value(DEFAULT_NOPAYAMT.doubleValue()))
//            .andExpect(jsonPath("$.nopayresn").value(DEFAULT_NOPAYRESN))
//            .andExpect(jsonPath("$.resumt").value(DEFAULT_RESUMT.doubleValue()))
//            .andExpect(jsonPath("$.trdt").value(DEFAULT_TRDT))
//            .andExpect(jsonPath("$.nopaydetel").value(DEFAULT_NOPAYDETEL))
//            .andExpect(jsonPath("$.meetCnt").value(sameNumber(DEFAULT_MEET_CNT)))
//            .andExpect(jsonPath("$.meterChk").value(sameNumber(DEFAULT_METER_CHK)))
//            .andExpect(jsonPath("$.meterChkF").value(sameNumber(DEFAULT_METER_CHK_F)))
//            .andExpect(jsonPath("$.workChk").value(sameNumber(DEFAULT_WORK_CHK)))
//            .andExpect(jsonPath("$.workChkF").value(sameNumber(DEFAULT_WORK_CHK_F)))
//            .andExpect(jsonPath("$.replMeth").value(DEFAULT_REPL_METH))
//            .andExpect(jsonPath("$.teckind3").value(sameNumber(DEFAULT_TECKIND_3)))
//            .andExpect(jsonPath("$.enviCnt").value(sameNumber(DEFAULT_ENVI_CNT)))
//            .andExpect(jsonPath("$.enviFee").value(DEFAULT_ENVI_FEE.doubleValue()))
//            .andExpect(jsonPath("$.wsafeCnt").value(sameNumber(DEFAULT_WSAFE_CNT)))
//            .andExpect(jsonPath("$.wsafeFee").value(DEFAULT_WSAFE_FEE.doubleValue()))
//            .andExpect(jsonPath("$.sorc1").value(DEFAULT_SORC_1.doubleValue()))
//            .andExpect(jsonPath("$.sorc1b").value(DEFAULT_SORC_1_B.doubleValue()))
//            .andExpect(jsonPath("$.sorc1c").value(DEFAULT_SORC_1_C.doubleValue()))
//            .andExpect(jsonPath("$.sorc1d").value(DEFAULT_SORC_1_D.doubleValue()))
//            .andExpect(jsonPath("$.sorc2").value(DEFAULT_SORC_2.doubleValue()))
//            .andExpect(jsonPath("$.sorc3").value(DEFAULT_SORC_3.doubleValue()))
//            .andExpect(jsonPath("$.sorc4").value(DEFAULT_SORC_4.doubleValue()))
//            .andExpect(jsonPath("$.sorc5").value(DEFAULT_SORC_5.doubleValue()))
//            .andExpect(jsonPath("$.spcw01").value(DEFAULT_SPCW_01))
//            .andExpect(jsonPath("$.spcw02").value(DEFAULT_SPCW_02))
//            .andExpect(jsonPath("$.spcw03").value(DEFAULT_SPCW_03))
//            .andExpect(jsonPath("$.spcw04").value(DEFAULT_SPCW_04))
//            .andExpect(jsonPath("$.spcw05").value(DEFAULT_SPCW_05))
//            .andExpect(jsonPath("$.spcw06").value(DEFAULT_SPCW_06))
//            .andExpect(jsonPath("$.spcw07").value(DEFAULT_SPCW_07))
//            .andExpect(jsonPath("$.spcw08").value(DEFAULT_SPCW_08))
//            .andExpect(jsonPath("$.spcw09").value(DEFAULT_SPCW_09))
//            .andExpect(jsonPath("$.spcw10").value(DEFAULT_SPCW_10))
//            .andExpect(jsonPath("$.spcw11").value(DEFAULT_SPCW_11))
//            .andExpect(jsonPath("$.spcw12").value(DEFAULT_SPCW_12))
//            .andExpect(jsonPath("$.equp01").value(DEFAULT_EQUP_01))
//            .andExpect(jsonPath("$.equp02").value(DEFAULT_EQUP_02))
//            .andExpect(jsonPath("$.equp03").value(DEFAULT_EQUP_03))
//            .andExpect(jsonPath("$.equp04").value(DEFAULT_EQUP_04))
//            .andExpect(jsonPath("$.equp05").value(DEFAULT_EQUP_05))
//            .andExpect(jsonPath("$.equp06").value(DEFAULT_EQUP_06))
//            .andExpect(jsonPath("$.equp07").value(DEFAULT_EQUP_07))
//            .andExpect(jsonPath("$.equp08").value(DEFAULT_EQUP_08))
//            .andExpect(jsonPath("$.equp09").value(DEFAULT_EQUP_09))
//            .andExpect(jsonPath("$.equp10").value(DEFAULT_EQUP_10))
//            .andExpect(jsonPath("$.equp11").value(DEFAULT_EQUP_11))
//            .andExpect(jsonPath("$.cosend").value(sameNumber(DEFAULT_COSEND)))
//            .andExpect(jsonPath("$.aTrate").value(DEFAULT_A_TRATE.doubleValue()))
//            .andExpect(jsonPath("$.wdayeds").value(DEFAULT_WDAYEDS.doubleValue()))
//            .andExpect(jsonPath("$.sorc3b").value(DEFAULT_SORC_3_B.doubleValue()))
//            .andExpect(jsonPath("$.sorc3c").value(DEFAULT_SORC_3_C.doubleValue()))
//            .andExpect(jsonPath("$.sorc3d").value(DEFAULT_SORC_3_D.doubleValue()))
//            .andExpect(jsonPath("$.sorc3e").value(DEFAULT_SORC_3_E.doubleValue()))
//            .andExpect(jsonPath("$.sorc3a").value(DEFAULT_SORC_3_A.doubleValue()))
//            .andExpect(jsonPath("$.sorc6").value(DEFAULT_SORC_6.doubleValue()))
//            .andExpect(jsonPath("$.sorc3b2").value(DEFAULT_SORC_3_B_2.doubleValue()))
//            .andExpect(jsonPath("$.sorc3b3").value(DEFAULT_SORC_3_B_3.doubleValue()))
//            .andExpect(jsonPath("$.sorc3b4").value(DEFAULT_SORC_3_B_4.doubleValue()))
//            .andExpect(jsonPath("$.sorc3b5").value(DEFAULT_SORC_3_B_5.doubleValue()))
//            .andExpect(jsonPath("$.sorc3c1").value(DEFAULT_SORC_3_C_1.doubleValue()))
//            .andExpect(jsonPath("$.sorc3d1").value(DEFAULT_SORC_3_D_1.doubleValue()))
//            .andExpect(jsonPath("$.sorc3d3").value(DEFAULT_SORC_3_D_3.doubleValue()))
//            .andExpect(jsonPath("$.sorc3d4").value(DEFAULT_SORC_3_D_4.doubleValue()))
//            .andExpect(jsonPath("$.teckind4").value(sameNumber(DEFAULT_TECKIND_4)))
//            .andExpect(jsonPath("$.sorc7").value(DEFAULT_SORC_7.doubleValue()))
//            .andExpect(jsonPath("$.sorc7use").value(DEFAULT_SORC_7_USE.doubleValue()))
//            .andExpect(jsonPath("$.sorc7oth").value(DEFAULT_SORC_7_OTH.doubleValue()))
//            .andExpect(jsonPath("$.sorc7tac").value(DEFAULT_SORC_7_TAC.doubleValue()))
//            .andExpect(jsonPath("$.sorc7self").value(DEFAULT_SORC_7_SELF.doubleValue()))
//            .andExpect(jsonPath("$.sorc8").value(DEFAULT_SORC_8.doubleValue()))
//            .andExpect(jsonPath("$.payArr").value(DEFAULT_PAY_ARR))
//            .andExpect(jsonPath("$.sorc7use2").value(DEFAULT_SORC_7_USE_2.doubleValue()))
//            .andExpect(jsonPath("$.sorc7use3").value(DEFAULT_SORC_7_USE_3.doubleValue()))
//            .andExpect(jsonPath("$.sfrWkr").value(DEFAULT_SFR_WKR.doubleValue()))
//            .andExpect(jsonPath("$.sorc9").value(DEFAULT_SORC_9.doubleValue()))
//            .andExpect(jsonPath("$.dsts").value(DEFAULT_DSTS))
//            .andExpect(jsonPath("$.confOut").value(sameNumber(DEFAULT_CONF_OUT)))
//            .andExpect(jsonPath("$.confOut2").value(sameNumber(DEFAULT_CONF_OUT_2)))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjMonth() throws Exception {
        // Get the bidPrjMonth
        restBidPrjMonthMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjMonth() throws Exception {
        // Initialize the database
//        bidPrjMonthRepository.saveAndFlush(bidPrjMonth);
//
//        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();
//
//        // Update the bidPrjMonth
//        BidPrjMonth updatedBidPrjMonth = bidPrjMonthRepository.findById(new BidPrjMonthPrimaryKey(bidPrjMonth.getWkut(),bidPrjMonth.getPrjno(),bidPrjMonth.getYr(),bidPrjMonth.getMnth())).get();
//        // Disconnect from session so that the updates on updatedBidPrjMonth are not directly saved in db
//        em.detach(updatedBidPrjMonth);
//        updatedBidPrjMonth
//            .wkut(UPDATED_WKUT)
//            .prjno(UPDATED_PRJNO)
//            .yr(UPDATED_YR)
//            .mnth(UPDATED_MNTH)
//            .syr(UPDATED_SYR)
//            .item(UPDATED_ITEM)
//            .ptrate(UPDATED_PTRATE)
//            .pexp(UPDATED_PEXP)
//            .rate(UPDATED_RATE)
//            .ypexp(UPDATED_YPEXP)
//            .yaexp(UPDATED_YAEXP)
//            .arat(UPDATED_ARAT)
//            .aexp(UPDATED_AEXP)
//            .atrate(UPDATED_ATRATE)
//            .remark(UPDATED_REMARK)
//            .sumt(UPDATED_SUMT)
//            .stus(UPDATED_STUS)
//            .mdsts(UPDATED_MDSTS)
//            .sumdat(UPDATED_SUMDAT)
//            .nopayamt(UPDATED_NOPAYAMT)
//            .nopayresn(UPDATED_NOPAYRESN)
//            .resumt(UPDATED_RESUMT)
//            .trdt(UPDATED_TRDT)
//            .nopaydetel(UPDATED_NOPAYDETEL)
//            .meetCnt(UPDATED_MEET_CNT)
//            .meterChk(UPDATED_METER_CHK)
//            .meterChkF(UPDATED_METER_CHK_F)
//            .workChk(UPDATED_WORK_CHK)
//            .workChkF(UPDATED_WORK_CHK_F)
//            .replMeth(UPDATED_REPL_METH)
//            .teckind3(UPDATED_TECKIND_3)
//            .enviCnt(UPDATED_ENVI_CNT)
//            .enviFee(UPDATED_ENVI_FEE)
//            .wsafeCnt(UPDATED_WSAFE_CNT)
//            .wsafeFee(UPDATED_WSAFE_FEE)
//            .sorc1(UPDATED_SORC_1)
//            .sorc1b(UPDATED_SORC_1_B)
//            .sorc1c(UPDATED_SORC_1_C)
//            .sorc1d(UPDATED_SORC_1_D)
//            .sorc2(UPDATED_SORC_2)
//            .sorc3(UPDATED_SORC_3)
//            .sorc4(UPDATED_SORC_4)
//            .sorc5(UPDATED_SORC_5)
//            .spcw01(UPDATED_SPCW_01)
//            .spcw02(UPDATED_SPCW_02)
//            .spcw03(UPDATED_SPCW_03)
//            .spcw04(UPDATED_SPCW_04)
//            .spcw05(UPDATED_SPCW_05)
//            .spcw06(UPDATED_SPCW_06)
//            .spcw07(UPDATED_SPCW_07)
//            .spcw08(UPDATED_SPCW_08)
//            .spcw09(UPDATED_SPCW_09)
//            .spcw10(UPDATED_SPCW_10)
//            .spcw11(UPDATED_SPCW_11)
//            .spcw12(UPDATED_SPCW_12)
//            .equp01(UPDATED_EQUP_01)
//            .equp02(UPDATED_EQUP_02)
//            .equp03(UPDATED_EQUP_03)
//            .equp04(UPDATED_EQUP_04)
//            .equp05(UPDATED_EQUP_05)
//            .equp06(UPDATED_EQUP_06)
//            .equp07(UPDATED_EQUP_07)
//            .equp08(UPDATED_EQUP_08)
//            .equp09(UPDATED_EQUP_09)
//            .equp10(UPDATED_EQUP_10)
//            .equp11(UPDATED_EQUP_11)
//            .cosend(UPDATED_COSEND)
//            .atrate(UPDATED_A_TRATE)
//            .wdayeds(UPDATED_WDAYEDS)
//            .sorc3b(UPDATED_SORC_3_B)
//            .sorc3c(UPDATED_SORC_3_C)
//            .sorc3d(UPDATED_SORC_3_D)
//            .sorc3e(UPDATED_SORC_3_E)
//            .sorc3a(UPDATED_SORC_3_A)
//            .sorc6(UPDATED_SORC_6)
//            .sorc3b2(UPDATED_SORC_3_B_2)
//            .sorc3b3(UPDATED_SORC_3_B_3)
//            .sorc3b4(UPDATED_SORC_3_B_4)
//            .sorc3b5(UPDATED_SORC_3_B_5)
//            .sorc3c1(UPDATED_SORC_3_C_1)
//            .sorc3d1(UPDATED_SORC_3_D_1)
//            .sorc3d3(UPDATED_SORC_3_D_3)
//            .sorc3d4(UPDATED_SORC_3_D_4)
//            .teckind4(UPDATED_TECKIND_4)
//            .sorc7(UPDATED_SORC_7)
//            .sorc7use(UPDATED_SORC_7_USE)
//            .sorc7oth(UPDATED_SORC_7_OTH)
//            .sorc7tac(UPDATED_SORC_7_TAC)
//            .sorc7self(UPDATED_SORC_7_SELF)
//            .sorc8(UPDATED_SORC_8)
//            .payArr(UPDATED_PAY_ARR)
//            .sorc7use2(UPDATED_SORC_7_USE_2)
//            .sorc7use3(UPDATED_SORC_7_USE_3)
//            .sfrWkr(UPDATED_SFR_WKR)
//            .sorc9(UPDATED_SORC_9)
//            .dsts(UPDATED_DSTS)
//            .confOut(UPDATED_CONF_OUT)
//            .confOut2(UPDATED_CONF_OUT_2)
//            .createDate(UPDATED_CREATE_DATE)
//            .createUser(UPDATED_CREATE_USER)
//            .updateDate(UPDATED_UPDATE_DATE)
//            .updateUser(UPDATED_UPDATE_USER);
//        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(updatedBidPrjMonth);
//
//        restBidPrjMonthMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjMonthDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
//        BidPrjMonth testBidPrjMonth = bidPrjMonthList.get(bidPrjMonthList.size() - 1);
//        assertThat(testBidPrjMonth.getWkut()).isEqualTo(UPDATED_WKUT);
//        assertThat(testBidPrjMonth.getPrjno()).isEqualTo(UPDATED_PRJNO);
//        assertThat(testBidPrjMonth.getYr()).isEqualTo(UPDATED_YR);
//        assertThat(testBidPrjMonth.getMnth()).isEqualTo(UPDATED_MNTH);
//        assertThat(testBidPrjMonth.getSyr()).isEqualTo(UPDATED_SYR);
//        assertThat(testBidPrjMonth.getItem()).isEqualTo(UPDATED_ITEM);
//        assertThat(testBidPrjMonth.getPtrate()).isEqualTo(UPDATED_PTRATE);
//        assertThat(testBidPrjMonth.getPexp()).isEqualTo(UPDATED_PEXP);
//        assertThat(testBidPrjMonth.getRate()).isEqualTo(UPDATED_RATE);
//        assertThat(testBidPrjMonth.getYpexp()).isEqualTo(UPDATED_YPEXP);
//        assertThat(testBidPrjMonth.getYaexp()).isEqualTo(UPDATED_YAEXP);
//        assertThat(testBidPrjMonth.getArat()).isEqualTo(UPDATED_ARAT);
//        assertThat(testBidPrjMonth.getAexp()).isEqualTo(UPDATED_AEXP);
//        assertThat(testBidPrjMonth.getAtrate()).isEqualTo(UPDATED_ATRATE);
//        assertThat(testBidPrjMonth.getRemark()).isEqualTo(UPDATED_REMARK);
//        assertThat(testBidPrjMonth.getSumt()).isEqualTo(UPDATED_SUMT);
//        assertThat(testBidPrjMonth.getStus()).isEqualTo(UPDATED_STUS);
//        assertThat(testBidPrjMonth.getMdsts()).isEqualTo(UPDATED_MDSTS);
//        assertThat(testBidPrjMonth.getSumdat()).isEqualTo(UPDATED_SUMDAT);
//        assertThat(testBidPrjMonth.getNopayamt()).isEqualTo(UPDATED_NOPAYAMT);
//        assertThat(testBidPrjMonth.getNopayresn()).isEqualTo(UPDATED_NOPAYRESN);
//        assertThat(testBidPrjMonth.getResumt()).isEqualTo(UPDATED_RESUMT);
//        assertThat(testBidPrjMonth.getTrdt()).isEqualTo(UPDATED_TRDT);
//        assertThat(testBidPrjMonth.getNopaydetel()).isEqualTo(UPDATED_NOPAYDETEL);
//        assertThat(testBidPrjMonth.getMeetCnt()).isEqualByComparingTo(UPDATED_MEET_CNT);
//        assertThat(testBidPrjMonth.getMeterChk()).isEqualByComparingTo(UPDATED_METER_CHK);
//        assertThat(testBidPrjMonth.getMeterChkF()).isEqualByComparingTo(UPDATED_METER_CHK_F);
//        assertThat(testBidPrjMonth.getWorkChk()).isEqualByComparingTo(UPDATED_WORK_CHK);
//        assertThat(testBidPrjMonth.getWorkChkF()).isEqualByComparingTo(UPDATED_WORK_CHK_F);
//        assertThat(testBidPrjMonth.getReplMeth()).isEqualTo(UPDATED_REPL_METH);
//        assertThat(testBidPrjMonth.getTeckind3()).isEqualByComparingTo(UPDATED_TECKIND_3);
//        assertThat(testBidPrjMonth.getEnviCnt()).isEqualByComparingTo(UPDATED_ENVI_CNT);
//        assertThat(testBidPrjMonth.getEnviFee()).isEqualTo(UPDATED_ENVI_FEE);
//        assertThat(testBidPrjMonth.getWsafeCnt()).isEqualByComparingTo(UPDATED_WSAFE_CNT);
//        assertThat(testBidPrjMonth.getWsafeFee()).isEqualTo(UPDATED_WSAFE_FEE);
//        assertThat(testBidPrjMonth.getSorc1()).isEqualTo(UPDATED_SORC_1);
//        assertThat(testBidPrjMonth.getSorc1b()).isEqualTo(UPDATED_SORC_1_B);
//        assertThat(testBidPrjMonth.getSorc1c()).isEqualTo(UPDATED_SORC_1_C);
//        assertThat(testBidPrjMonth.getSorc1d()).isEqualTo(UPDATED_SORC_1_D);
//        assertThat(testBidPrjMonth.getSorc2()).isEqualTo(UPDATED_SORC_2);
//        assertThat(testBidPrjMonth.getSorc3()).isEqualTo(UPDATED_SORC_3);
//        assertThat(testBidPrjMonth.getSorc4()).isEqualTo(UPDATED_SORC_4);
//        assertThat(testBidPrjMonth.getSorc5()).isEqualTo(UPDATED_SORC_5);
//        assertThat(testBidPrjMonth.getSpcw01()).isEqualTo(UPDATED_SPCW_01);
//        assertThat(testBidPrjMonth.getSpcw02()).isEqualTo(UPDATED_SPCW_02);
//        assertThat(testBidPrjMonth.getSpcw03()).isEqualTo(UPDATED_SPCW_03);
//        assertThat(testBidPrjMonth.getSpcw04()).isEqualTo(UPDATED_SPCW_04);
//        assertThat(testBidPrjMonth.getSpcw05()).isEqualTo(UPDATED_SPCW_05);
//        assertThat(testBidPrjMonth.getSpcw06()).isEqualTo(UPDATED_SPCW_06);
//        assertThat(testBidPrjMonth.getSpcw07()).isEqualTo(UPDATED_SPCW_07);
//        assertThat(testBidPrjMonth.getSpcw08()).isEqualTo(UPDATED_SPCW_08);
//        assertThat(testBidPrjMonth.getSpcw09()).isEqualTo(UPDATED_SPCW_09);
//        assertThat(testBidPrjMonth.getSpcw10()).isEqualTo(UPDATED_SPCW_10);
//        assertThat(testBidPrjMonth.getSpcw11()).isEqualTo(UPDATED_SPCW_11);
//        assertThat(testBidPrjMonth.getSpcw12()).isEqualTo(UPDATED_SPCW_12);
//        assertThat(testBidPrjMonth.getEqup01()).isEqualTo(UPDATED_EQUP_01);
//        assertThat(testBidPrjMonth.getEqup02()).isEqualTo(UPDATED_EQUP_02);
//        assertThat(testBidPrjMonth.getEqup03()).isEqualTo(UPDATED_EQUP_03);
//        assertThat(testBidPrjMonth.getEqup04()).isEqualTo(UPDATED_EQUP_04);
//        assertThat(testBidPrjMonth.getEqup05()).isEqualTo(UPDATED_EQUP_05);
//        assertThat(testBidPrjMonth.getEqup06()).isEqualTo(UPDATED_EQUP_06);
//        assertThat(testBidPrjMonth.getEqup07()).isEqualTo(UPDATED_EQUP_07);
//        assertThat(testBidPrjMonth.getEqup08()).isEqualTo(UPDATED_EQUP_08);
//        assertThat(testBidPrjMonth.getEqup09()).isEqualTo(UPDATED_EQUP_09);
//        assertThat(testBidPrjMonth.getEqup10()).isEqualTo(UPDATED_EQUP_10);
//        assertThat(testBidPrjMonth.getEqup11()).isEqualTo(UPDATED_EQUP_11);
//        assertThat(testBidPrjMonth.getCosend()).isEqualByComparingTo(UPDATED_COSEND);
//        assertThat(testBidPrjMonth.getAtrate()).isEqualTo(UPDATED_A_TRATE);
//        assertThat(testBidPrjMonth.getWdayeds()).isEqualTo(UPDATED_WDAYEDS);
//        assertThat(testBidPrjMonth.getSorc3b()).isEqualTo(UPDATED_SORC_3_B);
//        assertThat(testBidPrjMonth.getSorc3c()).isEqualTo(UPDATED_SORC_3_C);
//        assertThat(testBidPrjMonth.getSorc3d()).isEqualTo(UPDATED_SORC_3_D);
//        assertThat(testBidPrjMonth.getSorc3e()).isEqualTo(UPDATED_SORC_3_E);
//        assertThat(testBidPrjMonth.getSorc3a()).isEqualTo(UPDATED_SORC_3_A);
//        assertThat(testBidPrjMonth.getSorc6()).isEqualTo(UPDATED_SORC_6);
//        assertThat(testBidPrjMonth.getSorc3b2()).isEqualTo(UPDATED_SORC_3_B_2);
//        assertThat(testBidPrjMonth.getSorc3b3()).isEqualTo(UPDATED_SORC_3_B_3);
//        assertThat(testBidPrjMonth.getSorc3b4()).isEqualTo(UPDATED_SORC_3_B_4);
//        assertThat(testBidPrjMonth.getSorc3b5()).isEqualTo(UPDATED_SORC_3_B_5);
//        assertThat(testBidPrjMonth.getSorc3c1()).isEqualTo(UPDATED_SORC_3_C_1);
//        assertThat(testBidPrjMonth.getSorc3d1()).isEqualTo(UPDATED_SORC_3_D_1);
//        assertThat(testBidPrjMonth.getSorc3d3()).isEqualTo(UPDATED_SORC_3_D_3);
//        assertThat(testBidPrjMonth.getSorc3d4()).isEqualTo(UPDATED_SORC_3_D_4);
//        assertThat(testBidPrjMonth.getTeckind4()).isEqualByComparingTo(UPDATED_TECKIND_4);
//        assertThat(testBidPrjMonth.getSorc7()).isEqualTo(UPDATED_SORC_7);
//        assertThat(testBidPrjMonth.getSorc7use()).isEqualTo(UPDATED_SORC_7_USE);
//        assertThat(testBidPrjMonth.getSorc7oth()).isEqualTo(UPDATED_SORC_7_OTH);
//        assertThat(testBidPrjMonth.getSorc7tac()).isEqualTo(UPDATED_SORC_7_TAC);
//        assertThat(testBidPrjMonth.getSorc7self()).isEqualTo(UPDATED_SORC_7_SELF);
//        assertThat(testBidPrjMonth.getSorc8()).isEqualTo(UPDATED_SORC_8);
//        assertThat(testBidPrjMonth.getPayArr()).isEqualTo(UPDATED_PAY_ARR);
//        assertThat(testBidPrjMonth.getSorc7use2()).isEqualTo(UPDATED_SORC_7_USE_2);
//        assertThat(testBidPrjMonth.getSorc7use3()).isEqualTo(UPDATED_SORC_7_USE_3);
//        assertThat(testBidPrjMonth.getSfrWkr()).isEqualTo(UPDATED_SFR_WKR);
//        assertThat(testBidPrjMonth.getSorc9()).isEqualTo(UPDATED_SORC_9);
//        assertThat(testBidPrjMonth.getDsts()).isEqualTo(UPDATED_DSTS);
//        assertThat(testBidPrjMonth.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
//        assertThat(testBidPrjMonth.getConfOut2()).isEqualByComparingTo(UPDATED_CONF_OUT_2);
//        assertThat(testBidPrjMonth.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
//        assertThat(testBidPrjMonth.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
//        assertThat(testBidPrjMonth.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//        assertThat(testBidPrjMonth.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjMonth() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();
//        bidPrjMonth.setId(count.incrementAndGet());
//
//        // Create the BidPrjMonth
//        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjMonthMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjMonthDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjMonth() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();
//        bidPrjMonth.setId(count.incrementAndGet());
//
//        // Create the BidPrjMonth
//        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjMonthMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjMonth() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();
//        bidPrjMonth.setId(count.incrementAndGet());
//
//        // Create the BidPrjMonth
//        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjMonthMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjMonthWithPatch() throws Exception {
        // Initialize the database
        bidPrjMonthRepository.saveAndFlush(bidPrjMonth);

        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();

        // Update the bidPrjMonth using partial update
//        BidPrjMonth partialUpdatedBidPrjMonth = new BidPrjMonth();
//        partialUpdatedBidPrjMonth.setWkut(bidPrjMonth.getWkut());
//        partialUpdatedBidPrjMonth.setPrjno(bidPrjMonth.getPrjno());
//        partialUpdatedBidPrjMonth.setYr(bidPrjMonth.getYr());
//        partialUpdatedBidPrjMonth.setMnth(bidPrjMonth.getMnth());
//
//        partialUpdatedBidPrjMonth
//            .wkut(UPDATED_WKUT)
//            .prjno(UPDATED_PRJNO)
//            .mnth(UPDATED_MNTH)
//            .pexp(UPDATED_PEXP)
//            .arat(UPDATED_ARAT)
//            .aexp(UPDATED_AEXP)
//            .remark(UPDATED_REMARK)
//            .sumt(UPDATED_SUMT)
//            .stus(UPDATED_STUS)
//            .nopayamt(UPDATED_NOPAYAMT)
//            .nopayresn(UPDATED_NOPAYRESN)
//            .trdt(UPDATED_TRDT)
//            .meterChkF(UPDATED_METER_CHK_F)
//            .workChk(UPDATED_WORK_CHK)
//            .replMeth(UPDATED_REPL_METH)
//            .teckind3(UPDATED_TECKIND_3)
//            .enviFee(UPDATED_ENVI_FEE)
//            .wsafeCnt(UPDATED_WSAFE_CNT)
//            .sorc1d(UPDATED_SORC_1_D)
//            .sorc2(UPDATED_SORC_2)
//            .sorc4(UPDATED_SORC_4)
//            .spcw02(UPDATED_SPCW_02)
//            .spcw05(UPDATED_SPCW_05)
//            .spcw07(UPDATED_SPCW_07)
//            .spcw09(UPDATED_SPCW_09)
//            .equp01(UPDATED_EQUP_01)
//            .equp02(UPDATED_EQUP_02)
//            .equp03(UPDATED_EQUP_03)
//            .equp04(UPDATED_EQUP_04)
//            .equp09(UPDATED_EQUP_09)
//            .equp11(UPDATED_EQUP_11)
//            .cosend(UPDATED_COSEND)
//            .atrate(UPDATED_A_TRATE)
//            .sorc3b(UPDATED_SORC_3_B)
//            .sorc3c(UPDATED_SORC_3_C)
//            .sorc3e(UPDATED_SORC_3_E)
//            .sorc6(UPDATED_SORC_6)
//            .sorc3b3(UPDATED_SORC_3_B_3)
//            .sorc3b4(UPDATED_SORC_3_B_4)
//            .sorc3d1(UPDATED_SORC_3_D_1)
//            .sorc7(UPDATED_SORC_7)
//            .sorc7use2(UPDATED_SORC_7_USE_2)
//            .sorc7use3(UPDATED_SORC_7_USE_3)
//            .sorc9(UPDATED_SORC_9)
//            .confOut(UPDATED_CONF_OUT)
//            .confOut2(UPDATED_CONF_OUT_2)
//            .updateDate(UPDATED_UPDATE_DATE)
//            .updateUser(UPDATED_UPDATE_USER);
//
//        restBidPrjMonthMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjMonth.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjMonth))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
//        BidPrjMonth testBidPrjMonth = bidPrjMonthList.get(bidPrjMonthList.size() - 1);
//        assertThat(testBidPrjMonth.getWkut()).isEqualTo(UPDATED_WKUT);
//        assertThat(testBidPrjMonth.getPrjno()).isEqualTo(UPDATED_PRJNO);
//        assertThat(testBidPrjMonth.getYr()).isEqualTo(DEFAULT_YR);
//        assertThat(testBidPrjMonth.getMnth()).isEqualTo(UPDATED_MNTH);
//        assertThat(testBidPrjMonth.getSyr()).isEqualTo(DEFAULT_SYR);
//        assertThat(testBidPrjMonth.getItem()).isEqualTo(DEFAULT_ITEM);
//        assertThat(testBidPrjMonth.getPtrate()).isEqualTo(DEFAULT_PTRATE);
//        assertThat(testBidPrjMonth.getPexp()).isEqualTo(UPDATED_PEXP);
//        assertThat(testBidPrjMonth.getRate()).isEqualTo(DEFAULT_RATE);
//        assertThat(testBidPrjMonth.getYpexp()).isEqualTo(DEFAULT_YPEXP);
//        assertThat(testBidPrjMonth.getYaexp()).isEqualTo(DEFAULT_YAEXP);
//        assertThat(testBidPrjMonth.getArat()).isEqualTo(UPDATED_ARAT);
//        assertThat(testBidPrjMonth.getAexp()).isEqualTo(UPDATED_AEXP);
//        assertThat(testBidPrjMonth.getAtrate()).isEqualTo(DEFAULT_ATRATE);
//        assertThat(testBidPrjMonth.getRemark()).isEqualTo(UPDATED_REMARK);
//        assertThat(testBidPrjMonth.getSumt()).isEqualTo(UPDATED_SUMT);
//        assertThat(testBidPrjMonth.getStus()).isEqualTo(UPDATED_STUS);
//        assertThat(testBidPrjMonth.getMdsts()).isEqualTo(DEFAULT_MDSTS);
//        assertThat(testBidPrjMonth.getSumdat()).isEqualTo(DEFAULT_SUMDAT);
//        assertThat(testBidPrjMonth.getNopayamt()).isEqualTo(UPDATED_NOPAYAMT);
//        assertThat(testBidPrjMonth.getNopayresn()).isEqualTo(UPDATED_NOPAYRESN);
//        assertThat(testBidPrjMonth.getResumt()).isEqualTo(DEFAULT_RESUMT);
//        assertThat(testBidPrjMonth.getTrdt()).isEqualTo(UPDATED_TRDT);
//        assertThat(testBidPrjMonth.getNopaydetel()).isEqualTo(DEFAULT_NOPAYDETEL);
//        assertThat(testBidPrjMonth.getMeetCnt()).isEqualByComparingTo(DEFAULT_MEET_CNT);
//        assertThat(testBidPrjMonth.getMeterChk()).isEqualByComparingTo(DEFAULT_METER_CHK);
//        assertThat(testBidPrjMonth.getMeterChkF()).isEqualByComparingTo(UPDATED_METER_CHK_F);
//        assertThat(testBidPrjMonth.getWorkChk()).isEqualByComparingTo(UPDATED_WORK_CHK);
//        assertThat(testBidPrjMonth.getWorkChkF()).isEqualByComparingTo(DEFAULT_WORK_CHK_F);
//        assertThat(testBidPrjMonth.getReplMeth()).isEqualTo(UPDATED_REPL_METH);
//        assertThat(testBidPrjMonth.getTeckind3()).isEqualByComparingTo(UPDATED_TECKIND_3);
//        assertThat(testBidPrjMonth.getEnviCnt()).isEqualByComparingTo(DEFAULT_ENVI_CNT);
//        assertThat(testBidPrjMonth.getEnviFee()).isEqualTo(UPDATED_ENVI_FEE);
//        assertThat(testBidPrjMonth.getWsafeCnt()).isEqualByComparingTo(UPDATED_WSAFE_CNT);
//        assertThat(testBidPrjMonth.getWsafeFee()).isEqualTo(DEFAULT_WSAFE_FEE);
//        assertThat(testBidPrjMonth.getSorc1()).isEqualTo(DEFAULT_SORC_1);
//        assertThat(testBidPrjMonth.getSorc1b()).isEqualTo(DEFAULT_SORC_1_B);
//        assertThat(testBidPrjMonth.getSorc1c()).isEqualTo(DEFAULT_SORC_1_C);
//        assertThat(testBidPrjMonth.getSorc1d()).isEqualTo(UPDATED_SORC_1_D);
//        assertThat(testBidPrjMonth.getSorc2()).isEqualTo(UPDATED_SORC_2);
//        assertThat(testBidPrjMonth.getSorc3()).isEqualTo(DEFAULT_SORC_3);
//        assertThat(testBidPrjMonth.getSorc4()).isEqualTo(UPDATED_SORC_4);
//        assertThat(testBidPrjMonth.getSorc5()).isEqualTo(DEFAULT_SORC_5);
//        assertThat(testBidPrjMonth.getSpcw01()).isEqualTo(DEFAULT_SPCW_01);
//        assertThat(testBidPrjMonth.getSpcw02()).isEqualTo(UPDATED_SPCW_02);
//        assertThat(testBidPrjMonth.getSpcw03()).isEqualTo(DEFAULT_SPCW_03);
//        assertThat(testBidPrjMonth.getSpcw04()).isEqualTo(DEFAULT_SPCW_04);
//        assertThat(testBidPrjMonth.getSpcw05()).isEqualTo(UPDATED_SPCW_05);
//        assertThat(testBidPrjMonth.getSpcw06()).isEqualTo(DEFAULT_SPCW_06);
//        assertThat(testBidPrjMonth.getSpcw07()).isEqualTo(UPDATED_SPCW_07);
//        assertThat(testBidPrjMonth.getSpcw08()).isEqualTo(DEFAULT_SPCW_08);
//        assertThat(testBidPrjMonth.getSpcw09()).isEqualTo(UPDATED_SPCW_09);
//        assertThat(testBidPrjMonth.getSpcw10()).isEqualTo(DEFAULT_SPCW_10);
//        assertThat(testBidPrjMonth.getSpcw11()).isEqualTo(DEFAULT_SPCW_11);
//        assertThat(testBidPrjMonth.getSpcw12()).isEqualTo(DEFAULT_SPCW_12);
//        assertThat(testBidPrjMonth.getEqup01()).isEqualTo(UPDATED_EQUP_01);
//        assertThat(testBidPrjMonth.getEqup02()).isEqualTo(UPDATED_EQUP_02);
//        assertThat(testBidPrjMonth.getEqup03()).isEqualTo(UPDATED_EQUP_03);
//        assertThat(testBidPrjMonth.getEqup04()).isEqualTo(UPDATED_EQUP_04);
//        assertThat(testBidPrjMonth.getEqup05()).isEqualTo(DEFAULT_EQUP_05);
//        assertThat(testBidPrjMonth.getEqup06()).isEqualTo(DEFAULT_EQUP_06);
//        assertThat(testBidPrjMonth.getEqup07()).isEqualTo(DEFAULT_EQUP_07);
//        assertThat(testBidPrjMonth.getEqup08()).isEqualTo(DEFAULT_EQUP_08);
//        assertThat(testBidPrjMonth.getEqup09()).isEqualTo(UPDATED_EQUP_09);
//        assertThat(testBidPrjMonth.getEqup10()).isEqualTo(DEFAULT_EQUP_10);
//        assertThat(testBidPrjMonth.getEqup11()).isEqualTo(UPDATED_EQUP_11);
//        assertThat(testBidPrjMonth.getCosend()).isEqualByComparingTo(UPDATED_COSEND);
//        assertThat(testBidPrjMonth.getAtrate()).isEqualTo(UPDATED_A_TRATE);
//        assertThat(testBidPrjMonth.getWdayeds()).isEqualTo(DEFAULT_WDAYEDS);
//        assertThat(testBidPrjMonth.getSorc3b()).isEqualTo(UPDATED_SORC_3_B);
//        assertThat(testBidPrjMonth.getSorc3c()).isEqualTo(UPDATED_SORC_3_C);
//        assertThat(testBidPrjMonth.getSorc3d()).isEqualTo(DEFAULT_SORC_3_D);
//        assertThat(testBidPrjMonth.getSorc3e()).isEqualTo(UPDATED_SORC_3_E);
//        assertThat(testBidPrjMonth.getSorc3a()).isEqualTo(DEFAULT_SORC_3_A);
//        assertThat(testBidPrjMonth.getSorc6()).isEqualTo(UPDATED_SORC_6);
//        assertThat(testBidPrjMonth.getSorc3b2()).isEqualTo(DEFAULT_SORC_3_B_2);
//        assertThat(testBidPrjMonth.getSorc3b3()).isEqualTo(UPDATED_SORC_3_B_3);
//        assertThat(testBidPrjMonth.getSorc3b4()).isEqualTo(UPDATED_SORC_3_B_4);
//        assertThat(testBidPrjMonth.getSorc3b5()).isEqualTo(DEFAULT_SORC_3_B_5);
//        assertThat(testBidPrjMonth.getSorc3c1()).isEqualTo(DEFAULT_SORC_3_C_1);
//        assertThat(testBidPrjMonth.getSorc3d1()).isEqualTo(UPDATED_SORC_3_D_1);
//        assertThat(testBidPrjMonth.getSorc3d3()).isEqualTo(DEFAULT_SORC_3_D_3);
//        assertThat(testBidPrjMonth.getSorc3d4()).isEqualTo(DEFAULT_SORC_3_D_4);
//        assertThat(testBidPrjMonth.getTeckind4()).isEqualByComparingTo(DEFAULT_TECKIND_4);
//        assertThat(testBidPrjMonth.getSorc7()).isEqualTo(UPDATED_SORC_7);
//        assertThat(testBidPrjMonth.getSorc7use()).isEqualTo(DEFAULT_SORC_7_USE);
//        assertThat(testBidPrjMonth.getSorc7oth()).isEqualTo(DEFAULT_SORC_7_OTH);
//        assertThat(testBidPrjMonth.getSorc7tac()).isEqualTo(DEFAULT_SORC_7_TAC);
//        assertThat(testBidPrjMonth.getSorc7self()).isEqualTo(DEFAULT_SORC_7_SELF);
//        assertThat(testBidPrjMonth.getSorc8()).isEqualTo(DEFAULT_SORC_8);
//        assertThat(testBidPrjMonth.getPayArr()).isEqualTo(DEFAULT_PAY_ARR);
//        assertThat(testBidPrjMonth.getSorc7use2()).isEqualTo(UPDATED_SORC_7_USE_2);
//        assertThat(testBidPrjMonth.getSorc7use3()).isEqualTo(UPDATED_SORC_7_USE_3);
//        assertThat(testBidPrjMonth.getSfrWkr()).isEqualTo(DEFAULT_SFR_WKR);
//        assertThat(testBidPrjMonth.getSorc9()).isEqualTo(UPDATED_SORC_9);
//        assertThat(testBidPrjMonth.getDsts()).isEqualTo(DEFAULT_DSTS);
//        assertThat(testBidPrjMonth.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
//        assertThat(testBidPrjMonth.getConfOut2()).isEqualByComparingTo(UPDATED_CONF_OUT_2);
//        assertThat(testBidPrjMonth.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
//        assertThat(testBidPrjMonth.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
//        assertThat(testBidPrjMonth.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//        assertThat(testBidPrjMonth.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjMonthWithPatch() throws Exception {
        // Initialize the database
//        bidPrjMonthRepository.saveAndFlush(bidPrjMonth);
//
//        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();
//
//        // Update the bidPrjMonth using partial update
//        BidPrjMonth partialUpdatedBidPrjMonth = new BidPrjMonth();
//        partialUpdatedBidPrjMonth.setId(bidPrjMonth.getId());
//
//        partialUpdatedBidPrjMonth
//            .wkut(UPDATED_WKUT)
//            .prjno(UPDATED_PRJNO)
//            .yr(UPDATED_YR)
//            .mnth(UPDATED_MNTH)
//            .syr(UPDATED_SYR)
//            .item(UPDATED_ITEM)
//            .ptrate(UPDATED_PTRATE)
//            .pexp(UPDATED_PEXP)
//            .rate(UPDATED_RATE)
//            .ypexp(UPDATED_YPEXP)
//            .yaexp(UPDATED_YAEXP)
//            .arat(UPDATED_ARAT)
//            .aexp(UPDATED_AEXP)
//            .atrate(UPDATED_ATRATE)
//            .remark(UPDATED_REMARK)
//            .sumt(UPDATED_SUMT)
//            .stus(UPDATED_STUS)
//            .mdsts(UPDATED_MDSTS)
//            .sumdat(UPDATED_SUMDAT)
//            .nopayamt(UPDATED_NOPAYAMT)
//            .nopayresn(UPDATED_NOPAYRESN)
//            .resumt(UPDATED_RESUMT)
//            .trdt(UPDATED_TRDT)
//            .nopaydetel(UPDATED_NOPAYDETEL)
//            .meetCnt(UPDATED_MEET_CNT)
//            .meterChk(UPDATED_METER_CHK)
//            .meterChkF(UPDATED_METER_CHK_F)
//            .workChk(UPDATED_WORK_CHK)
//            .workChkF(UPDATED_WORK_CHK_F)
//            .replMeth(UPDATED_REPL_METH)
//            .teckind3(UPDATED_TECKIND_3)
//            .enviCnt(UPDATED_ENVI_CNT)
//            .enviFee(UPDATED_ENVI_FEE)
//            .wsafeCnt(UPDATED_WSAFE_CNT)
//            .wsafeFee(UPDATED_WSAFE_FEE)
//            .sorc1(UPDATED_SORC_1)
//            .sorc1b(UPDATED_SORC_1_B)
//            .sorc1c(UPDATED_SORC_1_C)
//            .sorc1d(UPDATED_SORC_1_D)
//            .sorc2(UPDATED_SORC_2)
//            .sorc3(UPDATED_SORC_3)
//            .sorc4(UPDATED_SORC_4)
//            .sorc5(UPDATED_SORC_5)
//            .spcw01(UPDATED_SPCW_01)
//            .spcw02(UPDATED_SPCW_02)
//            .spcw03(UPDATED_SPCW_03)
//            .spcw04(UPDATED_SPCW_04)
//            .spcw05(UPDATED_SPCW_05)
//            .spcw06(UPDATED_SPCW_06)
//            .spcw07(UPDATED_SPCW_07)
//            .spcw08(UPDATED_SPCW_08)
//            .spcw09(UPDATED_SPCW_09)
//            .spcw10(UPDATED_SPCW_10)
//            .spcw11(UPDATED_SPCW_11)
//            .spcw12(UPDATED_SPCW_12)
//            .equp01(UPDATED_EQUP_01)
//            .equp02(UPDATED_EQUP_02)
//            .equp03(UPDATED_EQUP_03)
//            .equp04(UPDATED_EQUP_04)
//            .equp05(UPDATED_EQUP_05)
//            .equp06(UPDATED_EQUP_06)
//            .equp07(UPDATED_EQUP_07)
//            .equp08(UPDATED_EQUP_08)
//            .equp09(UPDATED_EQUP_09)
//            .equp10(UPDATED_EQUP_10)
//            .equp11(UPDATED_EQUP_11)
//            .cosend(UPDATED_COSEND)
//            .aTrate(UPDATED_A_TRATE)
//            .wdayeds(UPDATED_WDAYEDS)
//            .sorc3b(UPDATED_SORC_3_B)
//            .sorc3c(UPDATED_SORC_3_C)
//            .sorc3d(UPDATED_SORC_3_D)
//            .sorc3e(UPDATED_SORC_3_E)
//            .sorc3a(UPDATED_SORC_3_A)
//            .sorc6(UPDATED_SORC_6)
//            .sorc3b2(UPDATED_SORC_3_B_2)
//            .sorc3b3(UPDATED_SORC_3_B_3)
//            .sorc3b4(UPDATED_SORC_3_B_4)
//            .sorc3b5(UPDATED_SORC_3_B_5)
//            .sorc3c1(UPDATED_SORC_3_C_1)
//            .sorc3d1(UPDATED_SORC_3_D_1)
//            .sorc3d3(UPDATED_SORC_3_D_3)
//            .sorc3d4(UPDATED_SORC_3_D_4)
//            .teckind4(UPDATED_TECKIND_4)
//            .sorc7(UPDATED_SORC_7)
//            .sorc7use(UPDATED_SORC_7_USE)
//            .sorc7oth(UPDATED_SORC_7_OTH)
//            .sorc7tac(UPDATED_SORC_7_TAC)
//            .sorc7self(UPDATED_SORC_7_SELF)
//            .sorc8(UPDATED_SORC_8)
//            .payArr(UPDATED_PAY_ARR)
//            .sorc7use2(UPDATED_SORC_7_USE_2)
//            .sorc7use3(UPDATED_SORC_7_USE_3)
//            .sfrWkr(UPDATED_SFR_WKR)
//            .sorc9(UPDATED_SORC_9)
//            .dsts(UPDATED_DSTS)
//            .confOut(UPDATED_CONF_OUT)
//            .confOut2(UPDATED_CONF_OUT_2)
//            .createDate(UPDATED_CREATE_DATE)
//            .createUser(UPDATED_CREATE_USER)
//            .updateDate(UPDATED_UPDATE_DATE)
//            .updateUser(UPDATED_UPDATE_USER);
//
//        restBidPrjMonthMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjMonth.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjMonth))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
//        BidPrjMonth testBidPrjMonth = bidPrjMonthList.get(bidPrjMonthList.size() - 1);
//        assertThat(testBidPrjMonth.getWkut()).isEqualTo(UPDATED_WKUT);
//        assertThat(testBidPrjMonth.getPrjno()).isEqualTo(UPDATED_PRJNO);
//        assertThat(testBidPrjMonth.getYr()).isEqualTo(UPDATED_YR);
//        assertThat(testBidPrjMonth.getMnth()).isEqualTo(UPDATED_MNTH);
//        assertThat(testBidPrjMonth.getSyr()).isEqualTo(UPDATED_SYR);
//        assertThat(testBidPrjMonth.getItem()).isEqualTo(UPDATED_ITEM);
//        assertThat(testBidPrjMonth.getPtrate()).isEqualTo(UPDATED_PTRATE);
//        assertThat(testBidPrjMonth.getPexp()).isEqualTo(UPDATED_PEXP);
//        assertThat(testBidPrjMonth.getRate()).isEqualTo(UPDATED_RATE);
//        assertThat(testBidPrjMonth.getYpexp()).isEqualTo(UPDATED_YPEXP);
//        assertThat(testBidPrjMonth.getYaexp()).isEqualTo(UPDATED_YAEXP);
//        assertThat(testBidPrjMonth.getArat()).isEqualTo(UPDATED_ARAT);
//        assertThat(testBidPrjMonth.getAexp()).isEqualTo(UPDATED_AEXP);
//        assertThat(testBidPrjMonth.getAtrate()).isEqualTo(UPDATED_ATRATE);
//        assertThat(testBidPrjMonth.getRemark()).isEqualTo(UPDATED_REMARK);
//        assertThat(testBidPrjMonth.getSumt()).isEqualTo(UPDATED_SUMT);
//        assertThat(testBidPrjMonth.getStus()).isEqualTo(UPDATED_STUS);
//        assertThat(testBidPrjMonth.getMdsts()).isEqualTo(UPDATED_MDSTS);
//        assertThat(testBidPrjMonth.getSumdat()).isEqualTo(UPDATED_SUMDAT);
//        assertThat(testBidPrjMonth.getNopayamt()).isEqualTo(UPDATED_NOPAYAMT);
//        assertThat(testBidPrjMonth.getNopayresn()).isEqualTo(UPDATED_NOPAYRESN);
//        assertThat(testBidPrjMonth.getResumt()).isEqualTo(UPDATED_RESUMT);
//        assertThat(testBidPrjMonth.getTrdt()).isEqualTo(UPDATED_TRDT);
//        assertThat(testBidPrjMonth.getNopaydetel()).isEqualTo(UPDATED_NOPAYDETEL);
//        assertThat(testBidPrjMonth.getMeetCnt()).isEqualByComparingTo(UPDATED_MEET_CNT);
//        assertThat(testBidPrjMonth.getMeterChk()).isEqualByComparingTo(UPDATED_METER_CHK);
//        assertThat(testBidPrjMonth.getMeterChkF()).isEqualByComparingTo(UPDATED_METER_CHK_F);
//        assertThat(testBidPrjMonth.getWorkChk()).isEqualByComparingTo(UPDATED_WORK_CHK);
//        assertThat(testBidPrjMonth.getWorkChkF()).isEqualByComparingTo(UPDATED_WORK_CHK_F);
//        assertThat(testBidPrjMonth.getReplMeth()).isEqualTo(UPDATED_REPL_METH);
//        assertThat(testBidPrjMonth.getTeckind3()).isEqualByComparingTo(UPDATED_TECKIND_3);
//        assertThat(testBidPrjMonth.getEnviCnt()).isEqualByComparingTo(UPDATED_ENVI_CNT);
//        assertThat(testBidPrjMonth.getEnviFee()).isEqualTo(UPDATED_ENVI_FEE);
//        assertThat(testBidPrjMonth.getWsafeCnt()).isEqualByComparingTo(UPDATED_WSAFE_CNT);
//        assertThat(testBidPrjMonth.getWsafeFee()).isEqualTo(UPDATED_WSAFE_FEE);
//        assertThat(testBidPrjMonth.getSorc1()).isEqualTo(UPDATED_SORC_1);
//        assertThat(testBidPrjMonth.getSorc1b()).isEqualTo(UPDATED_SORC_1_B);
//        assertThat(testBidPrjMonth.getSorc1c()).isEqualTo(UPDATED_SORC_1_C);
//        assertThat(testBidPrjMonth.getSorc1d()).isEqualTo(UPDATED_SORC_1_D);
//        assertThat(testBidPrjMonth.getSorc2()).isEqualTo(UPDATED_SORC_2);
//        assertThat(testBidPrjMonth.getSorc3()).isEqualTo(UPDATED_SORC_3);
//        assertThat(testBidPrjMonth.getSorc4()).isEqualTo(UPDATED_SORC_4);
//        assertThat(testBidPrjMonth.getSorc5()).isEqualTo(UPDATED_SORC_5);
//        assertThat(testBidPrjMonth.getSpcw01()).isEqualTo(UPDATED_SPCW_01);
//        assertThat(testBidPrjMonth.getSpcw02()).isEqualTo(UPDATED_SPCW_02);
//        assertThat(testBidPrjMonth.getSpcw03()).isEqualTo(UPDATED_SPCW_03);
//        assertThat(testBidPrjMonth.getSpcw04()).isEqualTo(UPDATED_SPCW_04);
//        assertThat(testBidPrjMonth.getSpcw05()).isEqualTo(UPDATED_SPCW_05);
//        assertThat(testBidPrjMonth.getSpcw06()).isEqualTo(UPDATED_SPCW_06);
//        assertThat(testBidPrjMonth.getSpcw07()).isEqualTo(UPDATED_SPCW_07);
//        assertThat(testBidPrjMonth.getSpcw08()).isEqualTo(UPDATED_SPCW_08);
//        assertThat(testBidPrjMonth.getSpcw09()).isEqualTo(UPDATED_SPCW_09);
//        assertThat(testBidPrjMonth.getSpcw10()).isEqualTo(UPDATED_SPCW_10);
//        assertThat(testBidPrjMonth.getSpcw11()).isEqualTo(UPDATED_SPCW_11);
//        assertThat(testBidPrjMonth.getSpcw12()).isEqualTo(UPDATED_SPCW_12);
//        assertThat(testBidPrjMonth.getEqup01()).isEqualTo(UPDATED_EQUP_01);
//        assertThat(testBidPrjMonth.getEqup02()).isEqualTo(UPDATED_EQUP_02);
//        assertThat(testBidPrjMonth.getEqup03()).isEqualTo(UPDATED_EQUP_03);
//        assertThat(testBidPrjMonth.getEqup04()).isEqualTo(UPDATED_EQUP_04);
//        assertThat(testBidPrjMonth.getEqup05()).isEqualTo(UPDATED_EQUP_05);
//        assertThat(testBidPrjMonth.getEqup06()).isEqualTo(UPDATED_EQUP_06);
//        assertThat(testBidPrjMonth.getEqup07()).isEqualTo(UPDATED_EQUP_07);
//        assertThat(testBidPrjMonth.getEqup08()).isEqualTo(UPDATED_EQUP_08);
//        assertThat(testBidPrjMonth.getEqup09()).isEqualTo(UPDATED_EQUP_09);
//        assertThat(testBidPrjMonth.getEqup10()).isEqualTo(UPDATED_EQUP_10);
//        assertThat(testBidPrjMonth.getEqup11()).isEqualTo(UPDATED_EQUP_11);
//        assertThat(testBidPrjMonth.getCosend()).isEqualByComparingTo(UPDATED_COSEND);
//        assertThat(testBidPrjMonth.getAtrate()).isEqualTo(UPDATED_A_TRATE);
//        assertThat(testBidPrjMonth.getWdayeds()).isEqualTo(UPDATED_WDAYEDS);
//        assertThat(testBidPrjMonth.getSorc3b()).isEqualTo(UPDATED_SORC_3_B);
//        assertThat(testBidPrjMonth.getSorc3c()).isEqualTo(UPDATED_SORC_3_C);
//        assertThat(testBidPrjMonth.getSorc3d()).isEqualTo(UPDATED_SORC_3_D);
//        assertThat(testBidPrjMonth.getSorc3e()).isEqualTo(UPDATED_SORC_3_E);
//        assertThat(testBidPrjMonth.getSorc3a()).isEqualTo(UPDATED_SORC_3_A);
//        assertThat(testBidPrjMonth.getSorc6()).isEqualTo(UPDATED_SORC_6);
//        assertThat(testBidPrjMonth.getSorc3b2()).isEqualTo(UPDATED_SORC_3_B_2);
//        assertThat(testBidPrjMonth.getSorc3b3()).isEqualTo(UPDATED_SORC_3_B_3);
//        assertThat(testBidPrjMonth.getSorc3b4()).isEqualTo(UPDATED_SORC_3_B_4);
//        assertThat(testBidPrjMonth.getSorc3b5()).isEqualTo(UPDATED_SORC_3_B_5);
//        assertThat(testBidPrjMonth.getSorc3c1()).isEqualTo(UPDATED_SORC_3_C_1);
//        assertThat(testBidPrjMonth.getSorc3d1()).isEqualTo(UPDATED_SORC_3_D_1);
//        assertThat(testBidPrjMonth.getSorc3d3()).isEqualTo(UPDATED_SORC_3_D_3);
//        assertThat(testBidPrjMonth.getSorc3d4()).isEqualTo(UPDATED_SORC_3_D_4);
//        assertThat(testBidPrjMonth.getTeckind4()).isEqualByComparingTo(UPDATED_TECKIND_4);
//        assertThat(testBidPrjMonth.getSorc7()).isEqualTo(UPDATED_SORC_7);
//        assertThat(testBidPrjMonth.getSorc7use()).isEqualTo(UPDATED_SORC_7_USE);
//        assertThat(testBidPrjMonth.getSorc7oth()).isEqualTo(UPDATED_SORC_7_OTH);
//        assertThat(testBidPrjMonth.getSorc7tac()).isEqualTo(UPDATED_SORC_7_TAC);
//        assertThat(testBidPrjMonth.getSorc7self()).isEqualTo(UPDATED_SORC_7_SELF);
//        assertThat(testBidPrjMonth.getSorc8()).isEqualTo(UPDATED_SORC_8);
//        assertThat(testBidPrjMonth.getPayArr()).isEqualTo(UPDATED_PAY_ARR);
//        assertThat(testBidPrjMonth.getSorc7use2()).isEqualTo(UPDATED_SORC_7_USE_2);
//        assertThat(testBidPrjMonth.getSorc7use3()).isEqualTo(UPDATED_SORC_7_USE_3);
//        assertThat(testBidPrjMonth.getSfrWkr()).isEqualTo(UPDATED_SFR_WKR);
//        assertThat(testBidPrjMonth.getSorc9()).isEqualTo(UPDATED_SORC_9);
//        assertThat(testBidPrjMonth.getDsts()).isEqualTo(UPDATED_DSTS);
//        assertThat(testBidPrjMonth.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
//        assertThat(testBidPrjMonth.getConfOut2()).isEqualByComparingTo(UPDATED_CONF_OUT_2);
//        assertThat(testBidPrjMonth.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
//        assertThat(testBidPrjMonth.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
//        assertThat(testBidPrjMonth.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//        assertThat(testBidPrjMonth.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjMonth() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();
//        bidPrjMonth.setId(count.incrementAndGet());
//
//        // Create the BidPrjMonth
//        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjMonthMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjMonthDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjMonth() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();
//        bidPrjMonth.setId(count.incrementAndGet());
//
//        // Create the BidPrjMonth
//        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjMonthMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjMonth() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMonthRepository.findAll().size();
//        bidPrjMonth.setId(count.incrementAndGet());
//
//        // Create the BidPrjMonth
//        BidPrjMonthDTO bidPrjMonthDTO = bidPrjMonthMapper.toDto(bidPrjMonth);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjMonthMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjMonthDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjMonth in the database
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjMonth() throws Exception {
//        // Initialize the database
//        bidPrjMonthRepository.saveAndFlush(bidPrjMonth);
//
//        int databaseSizeBeforeDelete = bidPrjMonthRepository.findAll().size();
//
//        // Delete the bidPrjMonth
//        restBidPrjMonthMockMvc
//            .perform(delete(ENTITY_API_URL_ID, bidPrjMonth.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<BidPrjMonth> bidPrjMonthList = bidPrjMonthRepository.findAll();
//        assertThat(bidPrjMonthList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
