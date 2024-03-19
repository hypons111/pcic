package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidProject;
import gov.pcc.pwc.repository.BidProjectRepository;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.mapper.BidProjectMapper;
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
 * Integration tests for the {@link BidProjectResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidProjectResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IS_ADD_92 = "AA";
    private static final String UPDATED_IS_ADD_92 = "BB";

    private static final String DEFAULT_PNT_CTRL = "A";
    private static final String UPDATED_PNT_CTRL = "B";

    private static final String DEFAULT_TYPE_CODE = "AA";
    private static final String UPDATED_TYPE_CODE = "BB";

    private static final String DEFAULT_IS_UNION = "A";
    private static final String UPDATED_IS_UNION = "B";

    private static final String DEFAULT_PLACE_CODE = "AAAA";
    private static final String UPDATED_PLACE_CODE = "BBBB";

    private static final String DEFAULT_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_BOS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BOS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_IS_BG = "A";
    private static final String UPDATED_IS_BG = "B";

    private static final String DEFAULT_PLN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PLN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PLN_NO = "AAAAAAAAAA";
    private static final String UPDATED_PLN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PLN_UNICODE = "AAAAAAAAAA";
    private static final String UPDATED_PLN_UNICODE = "BBBBBBBBBB";

    private static final String DEFAULT_BOS_2_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BOS_2_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BOS_2_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BOS_2_RATE = new BigDecimal(2);

    private static final String DEFAULT_BOS_2_B_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BOS_2_B_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BOS_2_B_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BOS_2_B_RATE = new BigDecimal(2);

    private static final String DEFAULT_BOS_2_C_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BOS_2_C_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BOS_2_C_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BOS_2_C_RATE = new BigDecimal(2);

    private static final String DEFAULT_BOS_2_D_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BOS_2_D_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BOS_2_D_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BOS_2_D_RATE = new BigDecimal(2);

    private static final String DEFAULT_LNK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LNK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CCNT_2 = "AAAAAAAAAA";
    private static final String UPDATED_CCNT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MAN = "AAAAAAAAAA";
    private static final String UPDATED_MAN = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_E_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_E_MAIL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_WRATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_WRATE = new BigDecimal(2);

    private static final String DEFAULT_CONTAIN = "AAAAAAAAAA";
    private static final String UPDATED_CONTAIN = "BBBBBBBBBB";

    private static final String DEFAULT_PFUNC = "AAAAAAAAAA";
    private static final String UPDATED_PFUNC = "BBBBBBBBBB";

    private static final String DEFAULT_BENF = "AAAAAAAAAA";
    private static final String UPDATED_BENF = "BBBBBBBBBB";

    private static final String DEFAULT_PSTAND = "AAAAAAAAAA";
    private static final String UPDATED_PSTAND = "BBBBBBBBBB";

    private static final String DEFAULT_PQTY = "AAAAAAAAAA";
    private static final String UPDATED_PQTY = "BBBBBBBBBB";

    private static final String DEFAULT_UNTND = "AA";
    private static final String UPDATED_UNTND = "BB";

    private static final String DEFAULT_IS_SPCW = "A";
    private static final String UPDATED_IS_SPCW = "B";

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

    private static final BigDecimal DEFAULT_SPCW_01_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_01_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_01_NM = 1;
    private static final Integer UPDATED_SPCW_01_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_02_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_02_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_02_NM = 1;
    private static final Integer UPDATED_SPCW_02_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_03_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_03_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_03_NM = 1;
    private static final Integer UPDATED_SPCW_03_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_04_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_04_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_04_NM = 1;
    private static final Integer UPDATED_SPCW_04_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_05_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_05_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_05_NM = 1;
    private static final Integer UPDATED_SPCW_05_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_06_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_06_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_06_NM = 1;
    private static final Integer UPDATED_SPCW_06_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_07_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_07_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_07_NM = 1;
    private static final Integer UPDATED_SPCW_07_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_08_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_08_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_08_NM = 1;
    private static final Integer UPDATED_SPCW_08_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_09_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_09_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_09_NM = 1;
    private static final Integer UPDATED_SPCW_09_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_10_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_10_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_10_NM = 1;
    private static final Integer UPDATED_SPCW_10_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_11_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_11_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_11_NM = 1;
    private static final Integer UPDATED_SPCW_11_NM = 2;

    private static final BigDecimal DEFAULT_SPCW_12_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPCW_12_AMT = new BigDecimal(2);

    private static final Integer DEFAULT_SPCW_12_NM = 1;
    private static final Integer UPDATED_SPCW_12_NM = 2;

    private static final Integer DEFAULT_JOB_01_TYPE = 1;
    private static final Integer UPDATED_JOB_01_TYPE = 2;

    private static final Integer DEFAULT_JOB_02_TYPE = 1;
    private static final Integer UPDATED_JOB_02_TYPE = 2;

    private static final Integer DEFAULT_JOB_03_TYPE = 1;
    private static final Integer UPDATED_JOB_03_TYPE = 2;

    private static final Integer DEFAULT_JOB_04_TYPE = 1;
    private static final Integer UPDATED_JOB_04_TYPE = 2;

    private static final Integer DEFAULT_JOB_05_TYPE = 1;
    private static final Integer UPDATED_JOB_05_TYPE = 2;

    private static final Integer DEFAULT_JOB_06_TYPE = 1;
    private static final Integer UPDATED_JOB_06_TYPE = 2;

    private static final Integer DEFAULT_JOB_07_TYPE = 1;
    private static final Integer UPDATED_JOB_07_TYPE = 2;

    private static final Integer DEFAULT_JOB_08_TYPE = 1;
    private static final Integer UPDATED_JOB_08_TYPE = 2;

    private static final Integer DEFAULT_JOB_09_TYPE = 1;
    private static final Integer UPDATED_JOB_09_TYPE = 2;

    private static final Integer DEFAULT_JOB_10_TYPE = 1;
    private static final Integer UPDATED_JOB_10_TYPE = 2;

    private static final Integer DEFAULT_JOB_11_TYPE = 1;
    private static final Integer UPDATED_JOB_11_TYPE = 2;

    private static final Integer DEFAULT_JOB_12_TYPE = 1;
    private static final Integer UPDATED_JOB_12_TYPE = 2;

    private static final Integer DEFAULT_JOB_13_TYPE = 1;
    private static final Integer UPDATED_JOB_13_TYPE = 2;

    private static final Integer DEFAULT_JOB_14_TYPE = 1;
    private static final Integer UPDATED_JOB_14_TYPE = 2;

    private static final String DEFAULT_SEMSET = "A";
    private static final String UPDATED_SEMSET = "B";

    private static final String DEFAULT_IS_RCB = "A";
    private static final String UPDATED_IS_RCB = "B";

    private static final BigDecimal DEFAULT_SORC_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_1_B = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_1_B = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_1_C = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_1_C = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_6 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_6 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_E = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_E = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_1_D = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_1_D = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_A = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_A = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_5 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_5 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_B = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_B = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_C = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_C = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_3_D = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_3_D = new BigDecimal(2);

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

    private static final BigDecimal DEFAULT_SORC_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7_USE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_USE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_8 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_8 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7_USE_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_USE_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SORC_7_USE_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_SORC_7_USE_3 = new BigDecimal(2);

    private static final String DEFAULT_OPEN_YN = "A";
    private static final String UPDATED_OPEN_YN = "B";

    private static final String DEFAULT_W_KIND = "A";
    private static final String UPDATED_W_KIND = "B";

    private static final Integer DEFAULT_W_DAYS = 1;
    private static final Integer UPDATED_W_DAYS = 2;

    private static final String DEFAULT_W_REMK = "AAAAAAAAAA";
    private static final String UPDATED_W_REMK = "BBBBBBBBBB";

    private static final String DEFAULT_B_CODE = "AAAAAAAAAA";
    private static final String UPDATED_B_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TOT_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOT_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BDGT_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_BDGT_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_IGTCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_IGTCT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OTHCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OTHCT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RSVCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_RSVCT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_POLL = new BigDecimal(1);
    private static final BigDecimal UPDATED_POLL = new BigDecimal(2);

    private static final String DEFAULT_POLL_NO = "AAAAAAAAAA";
    private static final String UPDATED_POLL_NO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_OTH_MON = new BigDecimal(1);
    private static final BigDecimal UPDATED_OTH_MON = new BigDecimal(2);

    private static final String DEFAULT_GY_RESN = "AAAAAAAAAA";
    private static final String UPDATED_GY_RESN = "BBBBBBBBBB";

    private static final String DEFAULT_DRUT = "AAAAAAAAAA";
    private static final String UPDATED_DRUT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DRCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DRCT = new BigDecimal(2);

    private static final String DEFAULT_DSRM = "AAAAAAAAAA";
    private static final String UPDATED_DSRM = "BBBBBBBBBB";

    private static final String DEFAULT_SCRM = "AAAAAAAAAA";
    private static final String UPDATED_SCRM = "BBBBBBBBBB";

    private static final String DEFAULT_SCRR = "A";
    private static final String UPDATED_SCRR = "B";

    private static final Integer DEFAULT_SCNUM = 1;
    private static final Integer UPDATED_SCNUM = 2;

    private static final String DEFAULT_PCMUT = "AAAAAAAAAA";
    private static final String UPDATED_PCMUT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CCNUM = 1;
    private static final Integer UPDATED_CCNUM = 2;

    private static final LocalDate DEFAULT_ISS_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ISS_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ISS_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ISS_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_ISS_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_ISS_COST = new BigDecimal(2);

    private static final String DEFAULT_ISS_NO = "AAAAAAAAAA";
    private static final String UPDATED_ISS_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ISS_RMK = "AAAAAAAAAA";
    private static final String UPDATED_ISS_RMK = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PDS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PDS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ADS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_GETL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_GETL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_AGETL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AGETL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_IS_SPAY = "A";
    private static final String UPDATED_IS_SPAY = "B";

    private static final String DEFAULT_IPE = "AA";
    private static final String UPDATED_IPE = "BB";

    private static final String DEFAULT_IPE_Y = "AAAAAAAAAA";
    private static final String UPDATED_IPE_Y = "BBBBBBBBBB";

    private static final String DEFAULT_IPE_N = "AAAAAAAAAA";
    private static final String UPDATED_IPE_N = "BBBBBBBBBB";

    private static final String DEFAULT_IS_NLIC = "A";
    private static final String UPDATED_IS_NLIC = "B";

    private static final String DEFAULT_IS_DLIC = "A";
    private static final String UPDATED_IS_DLIC = "B";

    private static final String DEFAULT_LIC_NO = "AAAAAAAAAA";
    private static final String UPDATED_LIC_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LIC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LIC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ALIC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ALIC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_OP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_AOP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AOP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PCK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PCK_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACK_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PSC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PSC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ASC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ASC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PCT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PCT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CTMETH = "A";
    private static final String UPDATED_CTMETH = "B";

    private static final String DEFAULT_ACTMTH = "AA";
    private static final String UPDATED_ACTMTH = "BB";

    private static final String DEFAULT_SPEC_PURCH = "A";
    private static final String UPDATED_SPEC_PURCH = "B";

    private static final String DEFAULT_IS_INTL = "A";
    private static final String UPDATED_IS_INTL = "B";

    private static final String DEFAULT_TCTMTH = "A";
    private static final String UPDATED_TCTMTH = "B";

    private static final BigDecimal DEFAULT_BDGT_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_BDGT_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BDGT_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_BDGT_3 = new BigDecimal(2);

    private static final String DEFAULT_MCTMTH = "AA";
    private static final String UPDATED_MCTMTH = "BB";

    private static final String DEFAULT_CCNT_3 = "AA";
    private static final String UPDATED_CCNT_3 = "BB";

    private static final String DEFAULT_IS_PER = "AA";
    private static final String UPDATED_IS_PER = "BB";

    private static final BigDecimal DEFAULT_CT_SUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_CT_SUM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CT_SUM_F = new BigDecimal(1);
    private static final BigDecimal UPDATED_CT_SUM_F = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CT_SUM_U = new BigDecimal(1);
    private static final BigDecimal UPDATED_CT_SUM_U = new BigDecimal(2);

    private static final String DEFAULT_CTMO = "AAAAAAAAAA";
    private static final String UPDATED_CTMO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CT_SUM_D = new BigDecimal(1);
    private static final BigDecimal UPDATED_CT_SUM_D = new BigDecimal(2);

    private static final String DEFAULT_CNTID = "AAAAAAAAAA";
    private static final String UPDATED_CNTID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PAYS = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAYS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PMS_SUM_T = new BigDecimal(1);
    private static final BigDecimal UPDATED_PMS_SUM_T = new BigDecimal(2);

    private static final String DEFAULT_PMS_TYPE = "AA";
    private static final String UPDATED_PMS_TYPE = "BB";

    private static final String DEFAULT_PAYMTH = "A";
    private static final String UPDATED_PAYMTH = "B";

    private static final String DEFAULT_FOREIGN_YN = "A";
    private static final String UPDATED_FOREIGN_YN = "B";

    private static final LocalDate DEFAULT_CS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_AS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ASU_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ASU_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CE_DATE_F = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CE_DATE_F = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_AE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_FCOST_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_FCOST_2 = new BigDecimal(2);

    private static final LocalDate DEFAULT_FCS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FCS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_AFCS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AFCS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_FCOST = new BigDecimal(1);
    private static final BigDecimal UPDATED_FCOST = new BigDecimal(2);

    private static final String DEFAULT_IS_CKP = "A";
    private static final String UPDATED_IS_CKP = "B";

    private static final String DEFAULT_IS_CKP_2 = "A";
    private static final String UPDATED_IS_CKP_2 = "B";

    private static final String DEFAULT_IS_CKP_3 = "A";
    private static final String UPDATED_IS_CKP_3 = "B";

    private static final BigDecimal DEFAULT_CKP_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_CKP_MONEY = new BigDecimal(2);

    private static final String DEFAULT_CKP_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_CKP_UNIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CKP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CKP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_CKP_CNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CKP_CNT = new BigDecimal(2);

    private static final String DEFAULT_CKP_NUM = "AAAAAAAAAA";
    private static final String UPDATED_CKP_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_CKA_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_CKA_UNIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CKA_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CKA_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_CKA_CNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CKA_CNT = new BigDecimal(2);

    private static final String DEFAULT_CKA_NUM = "AAAAAAAAAA";
    private static final String UPDATED_CKA_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_CKD_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_CKD_UNIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CKD_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CKD_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_CKD_CNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CKD_CNT = new BigDecimal(2);

    private static final String DEFAULT_CKD_NUM = "AAAAAAAAAA";
    private static final String UPDATED_CKD_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_WORK_MEMO = "BBBBBBBBBB";

    private static final String DEFAULT_HOT_LINE = "AAAAAAAAAA";
    private static final String UPDATED_HOT_LINE = "BBBBBBBBBB";

    private static final String DEFAULT_LNK_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_LNK_CODE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_LNK_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_LNK_CODE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_LNK_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_LNK_NO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CCNT_1 = "AAAAAAAAAA";
    private static final String UPDATED_CCNT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_CODE_2 = "AAAA";
    private static final String UPDATED_TYPE_CODE_2 = "BBBB";

    private static final String DEFAULT_DEPT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AOP_CNT = "AAAAA";
    private static final String UPDATED_AOP_CNT = "BBBBB";

    private static final String DEFAULT_UNIQUE_KEY = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUE_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_IS_BIDSTR = "A";
    private static final String UPDATED_IS_BIDSTR = "B";

    private static final String DEFAULT_IS_ENGINEER = "A";
    private static final String UPDATED_IS_ENGINEER = "B";

    private static final String DEFAULT_NAME_0 = "AAAAAAAAAA";
    private static final String UPDATED_NAME_0 = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CT_SUM_0 = new BigDecimal(1);
    private static final BigDecimal UPDATED_CT_SUM_0 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BDGT_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_BDGT_10 = new BigDecimal(2);

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BATCH = "AAAAAAA";
    private static final String UPDATED_BATCH = "BBBBBBB";

    private static final Integer DEFAULT_NOCNT_DAY = 1;
    private static final Integer UPDATED_NOCNT_DAY = 2;

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final LocalDate DEFAULT_AOK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AOK_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_FCOST_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_FCOST_3 = new BigDecimal(2);

    private static final Integer DEFAULT_FIN_WAYS = 1;
    private static final Integer UPDATED_FIN_WAYS = 2;

    private static final Integer DEFAULT_AWORK_DAY = 1;
    private static final Integer UPDATED_AWORK_DAY = 2;

    private static final Integer DEFAULT_FAST_DAY = 1;
    private static final Integer UPDATED_FAST_DAY = 2;

    private static final Integer DEFAULT_OVER_DAY = 1;
    private static final Integer UPDATED_OVER_DAY = 2;

    private static final Integer DEFAULT_OVER_DAY_Y = 1;
    private static final Integer UPDATED_OVER_DAY_Y = 2;

    private static final BigDecimal DEFAULT_OVER_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OVER_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OTHER_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OTHER_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PUNSH_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PUNSH_FEE = new BigDecimal(2);

    private static final String DEFAULT_TM_2_X = "AAAAAAAAAA";
    private static final String UPDATED_TM_2_X = "BBBBBBBBBB";

    private static final String DEFAULT_TM_2_Y = "AAAAAAAAAA";
    private static final String UPDATED_TM_2_Y = "BBBBBBBBBB";

    private static final String DEFAULT_TM_2_X_2 = "AAAAAAAAAA";
    private static final String UPDATED_TM_2_X_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TM_2_Y_2 = "AAAAAAAAAA";
    private static final String UPDATED_TM_2_Y_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TM_2_X_3 = "AAAAAAAAAA";
    private static final String UPDATED_TM_2_X_3 = "BBBBBBBBBB";

    private static final String DEFAULT_TM_2_Y_3 = "AAAAAAAAAA";
    private static final String UPDATED_TM_2_Y_3 = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_DSTS = "A";
    private static final String UPDATED_DSTS = "B";

    private static final BigDecimal DEFAULT_CONF_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT = new BigDecimal(2);

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_CAN = "A";
    private static final String UPDATED_CAN = "B";

    private static final String DEFAULT_SRUT = "AAAAAAAAAA";
    private static final String UPDATED_SRUT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ANOCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ANOCT = new BigDecimal(2);

    private static final String DEFAULT_CEPD_NO = "AAAAAAA";
    private static final String UPDATED_CEPD_NO = "BBBBBBB";

    private static final String DEFAULT_SUB_NO = "AA";
    private static final String UPDATED_SUB_NO = "BB";

    private static final String DEFAULT_SCNT = "AAAAAAAAAA";
    private static final String UPDATED_SCNT = "BBBBBBBBBB";

    private static final String DEFAULT_DSDM = "AAAAAAAAAA";
    private static final String UPDATED_DSDM = "BBBBBBBBBB";

    private static final String DEFAULT_PRESON = "AAAAAAAAAA";
    private static final String UPDATED_PRESON = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PSIGN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PSIGN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ASIGN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ASIGN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TR_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TR_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_CONF_IN = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_IN = new BigDecimal(2);

    private static final String DEFAULT_U_KEY = "AAAAAAAAAA";
    private static final String UPDATED_U_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_TPCOTH = "AAAAAAAAAA";
    private static final String UPDATED_TPCOTH = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PCMCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PCMCT = new BigDecimal(2);

    private static final String DEFAULT_WKUT_OLD = "AAAAAAAAAA";
    private static final String UPDATED_WKUT_OLD = "BBBBBBBBBB";

    private static final String DEFAULT_PCC_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_PCC_MEMO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BGB_SUMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BGB_SUMT = new BigDecimal(2);

    private static final LocalDate DEFAULT_BGB_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BGB_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MAIN_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_M_DESC = "AAAAAAAAAA";
    private static final String UPDATED_M_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_M_RMK = "AAAAAAAAAA";
    private static final String UPDATED_M_RMK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PMR_SUMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PMR_SUMT = new BigDecimal(2);

    private static final LocalDate DEFAULT_PMR_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PMR_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PMR_RESM = "AA";
    private static final String UPDATED_PMR_RESM = "BB";

    private static final String ENTITY_API_URL = "/api/bid-projects";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidProjectRepository bidProjectRepository;

    @Autowired
    private BidProjectMapper bidProjectMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidProjectMockMvc;

    private BidProject bidProject;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidProject createEntity(EntityManager em) {
        BidProject bidProject = new BidProject()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .name(DEFAULT_NAME)
            .isAdd92(DEFAULT_IS_ADD_92)
            .pntCtrl(DEFAULT_PNT_CTRL)
            .typeCode(DEFAULT_TYPE_CODE)
            .isUnion(DEFAULT_IS_UNION)
            .placeCode(DEFAULT_PLACE_CODE)
            .place(DEFAULT_PLACE)
            .bosCode(DEFAULT_BOS_CODE)
            .depCode(DEFAULT_DEP_CODE)
            .isBg(DEFAULT_IS_BG)
            .plnCode(DEFAULT_PLN_CODE)
            .plnNo(DEFAULT_PLN_NO)
            .plnUnicode(DEFAULT_PLN_UNICODE)
            .bos2Code(DEFAULT_BOS_2_CODE)
            .bos2Rate(DEFAULT_BOS_2_RATE)
            .bos2bCode(DEFAULT_BOS_2_B_CODE)
            .bos2bRate(DEFAULT_BOS_2_B_RATE)
            .bos2cCode(DEFAULT_BOS_2_C_CODE)
            .bos2cRate(DEFAULT_BOS_2_C_RATE)
            .bos2dCode(DEFAULT_BOS_2_D_CODE)
            .bos2dRate(DEFAULT_BOS_2_D_RATE)
            .lnkCode(DEFAULT_LNK_CODE)
            .ccnt2(DEFAULT_CCNT_2)
            .man(DEFAULT_MAN)
            .tel(DEFAULT_TEL)
            .eMail(DEFAULT_E_MAIL)
            .wrate(DEFAULT_WRATE)
            .contain(DEFAULT_CONTAIN)
            .pfunc(DEFAULT_PFUNC)
            .benf(DEFAULT_BENF)
            .pstand(DEFAULT_PSTAND)
            .pqty(DEFAULT_PQTY)
            .untnd(DEFAULT_UNTND)
            .isSpcw(DEFAULT_IS_SPCW)
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
            .spcw01Amt(DEFAULT_SPCW_01_AMT)
            .spcw01Nm(DEFAULT_SPCW_01_NM)
            .spcw02Amt(DEFAULT_SPCW_02_AMT)
            .spcw02Nm(DEFAULT_SPCW_02_NM)
            .spcw03Amt(DEFAULT_SPCW_03_AMT)
            .spcw03Nm(DEFAULT_SPCW_03_NM)
            .spcw04Amt(DEFAULT_SPCW_04_AMT)
            .spcw04Nm(DEFAULT_SPCW_04_NM)
            .spcw05Amt(DEFAULT_SPCW_05_AMT)
            .spcw05Nm(DEFAULT_SPCW_05_NM)
            .spcw06Amt(DEFAULT_SPCW_06_AMT)
            .spcw06Nm(DEFAULT_SPCW_06_NM)
            .spcw07Amt(DEFAULT_SPCW_07_AMT)
            .spcw07Nm(DEFAULT_SPCW_07_NM)
            .spcw08Amt(DEFAULT_SPCW_08_AMT)
            .spcw08Nm(DEFAULT_SPCW_08_NM)
            .spcw09Amt(DEFAULT_SPCW_09_AMT)
            .spcw09Nm(DEFAULT_SPCW_09_NM)
            .spcw10Amt(DEFAULT_SPCW_10_AMT)
            .spcw10Nm(DEFAULT_SPCW_10_NM)
            .spcw11Amt(DEFAULT_SPCW_11_AMT)
            .spcw11Nm(DEFAULT_SPCW_11_NM)
            .spcw12Amt(DEFAULT_SPCW_12_AMT)
            .spcw12Nm(DEFAULT_SPCW_12_NM)
            .job01Type(DEFAULT_JOB_01_TYPE)
            .job02Type(DEFAULT_JOB_02_TYPE)
            .job03Type(DEFAULT_JOB_03_TYPE)
            .job04Type(DEFAULT_JOB_04_TYPE)
            .job05Type(DEFAULT_JOB_05_TYPE)
            .job06Type(DEFAULT_JOB_06_TYPE)
            .job07Type(DEFAULT_JOB_07_TYPE)
            .job08Type(DEFAULT_JOB_08_TYPE)
            .job09Type(DEFAULT_JOB_09_TYPE)
            .job10Type(DEFAULT_JOB_10_TYPE)
            .job11Type(DEFAULT_JOB_11_TYPE)
            .job12Type(DEFAULT_JOB_12_TYPE)
            .job13Type(DEFAULT_JOB_13_TYPE)
            .job14Type(DEFAULT_JOB_14_TYPE)
            .semset(DEFAULT_SEMSET)
            .isRcb(DEFAULT_IS_RCB)
            .sorc1(DEFAULT_SORC_1)
            .sorc1b(DEFAULT_SORC_1_B)
            .sorc1c(DEFAULT_SORC_1_C)
            .sorc6(DEFAULT_SORC_6)
            .sorc3e(DEFAULT_SORC_3_E)
            .sorc1d(DEFAULT_SORC_1_D)
            .sorc2(DEFAULT_SORC_2)
            .sorc3(DEFAULT_SORC_3)
            .sorc3a(DEFAULT_SORC_3_A)
            .sorc4(DEFAULT_SORC_4)
            .sorc5(DEFAULT_SORC_5)
            .sorc3b(DEFAULT_SORC_3_B)
            .sorc3c(DEFAULT_SORC_3_C)
            .sorc3d(DEFAULT_SORC_3_D)
            .sorc3b2(DEFAULT_SORC_3_B_2)
            .sorc3b3(DEFAULT_SORC_3_B_3)
            .sorc3b4(DEFAULT_SORC_3_B_4)
            .sorc3b5(DEFAULT_SORC_3_B_5)
            .sorc3c1(DEFAULT_SORC_3_C_1)
            .sorc3d1(DEFAULT_SORC_3_D_1)
            .sorc3d3(DEFAULT_SORC_3_D_3)
            .sorc3d4(DEFAULT_SORC_3_D_4)
            .sorc7(DEFAULT_SORC_7)
            .sorc7Use(DEFAULT_SORC_7_USE)
            .sorc8(DEFAULT_SORC_8)
            .sorc7Use2(DEFAULT_SORC_7_USE_2)
            .sorc7Use3(DEFAULT_SORC_7_USE_3)
            .openYn(DEFAULT_OPEN_YN)
            .wKind(DEFAULT_W_KIND)
            .wDays(DEFAULT_W_DAYS)
            .wRemk(DEFAULT_W_REMK)
            .bCode(DEFAULT_B_CODE)
            .totAmt(DEFAULT_TOT_AMT)
            .bdgt1(DEFAULT_BDGT_1)
            .igtct(DEFAULT_IGTCT)
            .othct(DEFAULT_OTHCT)
            .rsvct(DEFAULT_RSVCT)
            .poll(DEFAULT_POLL)
            .pollNo(DEFAULT_POLL_NO)
            .othMon(DEFAULT_OTH_MON)
            .gyResn(DEFAULT_GY_RESN)
            .drut(DEFAULT_DRUT)
            .drct(DEFAULT_DRCT)
            .dsrm(DEFAULT_DSRM)
            .scrm(DEFAULT_SCRM)
            .scrr(DEFAULT_SCRR)
            .scnum(DEFAULT_SCNUM)
            .pcmut(DEFAULT_PCMUT)
            .ccnum(DEFAULT_CCNUM)
            .issStartDate(DEFAULT_ISS_START_DATE)
            .issEndDate(DEFAULT_ISS_END_DATE)
            .issCost(DEFAULT_ISS_COST)
            .issNo(DEFAULT_ISS_NO)
            .issRmk(DEFAULT_ISS_RMK)
            .pdsDate(DEFAULT_PDS_DATE)
            .dsDate(DEFAULT_DS_DATE)
            .adsDate(DEFAULT_ADS_DATE)
            .getlDate(DEFAULT_GETL_DATE)
            .agetlDate(DEFAULT_AGETL_DATE)
            .isSpay(DEFAULT_IS_SPAY)
            .ipe(DEFAULT_IPE)
            .ipeY(DEFAULT_IPE_Y)
            .ipeN(DEFAULT_IPE_N)
            .isNlic(DEFAULT_IS_NLIC)
            .isDlic(DEFAULT_IS_DLIC)
            .licNo(DEFAULT_LIC_NO)
            .licDate(DEFAULT_LIC_DATE)
            .alicDate(DEFAULT_ALIC_DATE)
            .opDate(DEFAULT_OP_DATE)
            .aopDate(DEFAULT_AOP_DATE)
            .pckDate(DEFAULT_PCK_DATE)
            .ackDate(DEFAULT_ACK_DATE)
            .pscDate(DEFAULT_PSC_DATE)
            .ascDate(DEFAULT_ASC_DATE)
            .pctDate(DEFAULT_PCT_DATE)
            .actDate(DEFAULT_ACT_DATE)
            .ctmeth(DEFAULT_CTMETH)
            .actmth(DEFAULT_ACTMTH)
            .specPurch(DEFAULT_SPEC_PURCH)
            .isIntl(DEFAULT_IS_INTL)
            .tctmth(DEFAULT_TCTMTH)
            .bdgt2(DEFAULT_BDGT_2)
            .bdgt3(DEFAULT_BDGT_3)
            .mctmth(DEFAULT_MCTMTH)
            .ccnt3(DEFAULT_CCNT_3)
            .isPer(DEFAULT_IS_PER)
            .ctSum(DEFAULT_CT_SUM)
            .ctSumF(DEFAULT_CT_SUM_F)
            .ctSumU(DEFAULT_CT_SUM_U)
            .ctmo(DEFAULT_CTMO)
            .ctSumD(DEFAULT_CT_SUM_D)
            .cntid(DEFAULT_CNTID)
            .pays(DEFAULT_PAYS)
            .pmsSumT(DEFAULT_PMS_SUM_T)
            .pmsType(DEFAULT_PMS_TYPE)
            .paymth(DEFAULT_PAYMTH)
            .foreignYn(DEFAULT_FOREIGN_YN)
            .csDate(DEFAULT_CS_DATE)
            .asDate(DEFAULT_AS_DATE)
            .asuDate(DEFAULT_ASU_DATE)
            .ceDate(DEFAULT_CE_DATE)
            .ceDateF(DEFAULT_CE_DATE_F)
            .aeDate(DEFAULT_AE_DATE)
            .fcost2(DEFAULT_FCOST_2)
            .fcsDate(DEFAULT_FCS_DATE)
            .afcsDate(DEFAULT_AFCS_DATE)
            .fcost(DEFAULT_FCOST)
            .isCkp(DEFAULT_IS_CKP)
            .isCkp2(DEFAULT_IS_CKP_2)
            .isCkp3(DEFAULT_IS_CKP_3)
            .ckpMoney(DEFAULT_CKP_MONEY)
            .ckpUnit(DEFAULT_CKP_UNIT)
            .ckpDate(DEFAULT_CKP_DATE)
            .ckpCnt(DEFAULT_CKP_CNT)
            .ckpNum(DEFAULT_CKP_NUM)
            .ckaUnit(DEFAULT_CKA_UNIT)
            .ckaDate(DEFAULT_CKA_DATE)
            .ckaCnt(DEFAULT_CKA_CNT)
            .ckaNum(DEFAULT_CKA_NUM)
            .ckdUnit(DEFAULT_CKD_UNIT)
            .ckdDate(DEFAULT_CKD_DATE)
            .ckdCnt(DEFAULT_CKD_CNT)
            .ckdNum(DEFAULT_CKD_NUM)
            .workMemo(DEFAULT_WORK_MEMO)
            .hotLine(DEFAULT_HOT_LINE)
            .lnkCode2(DEFAULT_LNK_CODE_2)
            .lnkCode3(DEFAULT_LNK_CODE_3)
            .lnkNo2(DEFAULT_LNK_NO_2)
            .ccnt1(DEFAULT_CCNT_1)
            .typeCode2(DEFAULT_TYPE_CODE_2)
            .deptName(DEFAULT_DEPT_NAME)
            .aopCnt(DEFAULT_AOP_CNT)
            .uniqueKey(DEFAULT_UNIQUE_KEY)
            .isBidstr(DEFAULT_IS_BIDSTR)
            .isEngineer(DEFAULT_IS_ENGINEER)
            .name0(DEFAULT_NAME_0)
            .ctSum0(DEFAULT_CT_SUM_0)
            .bdgt10(DEFAULT_BDGT_10)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .batch(DEFAULT_BATCH)
            .nocntDay(DEFAULT_NOCNT_DAY)
            .status(DEFAULT_STATUS)
            .aokDate(DEFAULT_AOK_DATE)
            .fcost3(DEFAULT_FCOST_3)
            .finWays(DEFAULT_FIN_WAYS)
            .aworkDay(DEFAULT_AWORK_DAY)
            .fastDay(DEFAULT_FAST_DAY)
            .overDay(DEFAULT_OVER_DAY)
            .overDayY(DEFAULT_OVER_DAY_Y)
            .overFee(DEFAULT_OVER_FEE)
            .otherFee(DEFAULT_OTHER_FEE)
            .punshFee(DEFAULT_PUNSH_FEE)
            .tm2x(DEFAULT_TM_2_X)
            .tm2y(DEFAULT_TM_2_Y)
            .tm2x2(DEFAULT_TM_2_X_2)
            .tm2y2(DEFAULT_TM_2_Y_2)
            .tm2x3(DEFAULT_TM_2_X_3)
            .tm2y3(DEFAULT_TM_2_Y_3)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .dsts(DEFAULT_DSTS)
            .confOut(DEFAULT_CONF_OUT)
            .uIp(DEFAULT_U_IP)
            .can(DEFAULT_CAN)
            .srut(DEFAULT_SRUT)
            .anoct(DEFAULT_ANOCT)
            .cepdNo(DEFAULT_CEPD_NO)
            .subNo(DEFAULT_SUB_NO)
            .scnt(DEFAULT_SCNT)
            .dsdm(DEFAULT_DSDM)
            .preson(DEFAULT_PRESON)
            .psignDate(DEFAULT_PSIGN_DATE)
            .asignDate(DEFAULT_ASIGN_DATE)
            .trDate(DEFAULT_TR_DATE)
            .confIn(DEFAULT_CONF_IN)
            .uKey(DEFAULT_U_KEY)
            .tpcoth(DEFAULT_TPCOTH)
            .pcmct(DEFAULT_PCMCT)
            .wkutOld(DEFAULT_WKUT_OLD)
            .pccMemo(DEFAULT_PCC_MEMO)
            .bgbSumt(DEFAULT_BGB_SUMT)
            .bgbDate(DEFAULT_BGB_DATE)
            .mainItem(DEFAULT_MAIN_ITEM)
            .mDesc(DEFAULT_M_DESC)
            .mRmk(DEFAULT_M_RMK)
            .pmrSumt(DEFAULT_PMR_SUMT)
            .pmrDate(DEFAULT_PMR_DATE)
            .pmrResm(DEFAULT_PMR_RESM);
        return bidProject;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidProject createUpdatedEntity(EntityManager em) {
        BidProject bidProject = new BidProject()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .name(UPDATED_NAME)
            .isAdd92(UPDATED_IS_ADD_92)
            .pntCtrl(UPDATED_PNT_CTRL)
            .typeCode(UPDATED_TYPE_CODE)
            .isUnion(UPDATED_IS_UNION)
            .placeCode(UPDATED_PLACE_CODE)
            .place(UPDATED_PLACE)
            .bosCode(UPDATED_BOS_CODE)
            .depCode(UPDATED_DEP_CODE)
            .isBg(UPDATED_IS_BG)
            .plnCode(UPDATED_PLN_CODE)
            .plnNo(UPDATED_PLN_NO)
            .plnUnicode(UPDATED_PLN_UNICODE)
            .bos2Code(UPDATED_BOS_2_CODE)
            .bos2Rate(UPDATED_BOS_2_RATE)
            .bos2bCode(UPDATED_BOS_2_B_CODE)
            .bos2bRate(UPDATED_BOS_2_B_RATE)
            .bos2cCode(UPDATED_BOS_2_C_CODE)
            .bos2cRate(UPDATED_BOS_2_C_RATE)
            .bos2dCode(UPDATED_BOS_2_D_CODE)
            .bos2dRate(UPDATED_BOS_2_D_RATE)
            .lnkCode(UPDATED_LNK_CODE)
            .ccnt2(UPDATED_CCNT_2)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL)
            .eMail(UPDATED_E_MAIL)
            .wrate(UPDATED_WRATE)
            .contain(UPDATED_CONTAIN)
            .pfunc(UPDATED_PFUNC)
            .benf(UPDATED_BENF)
            .pstand(UPDATED_PSTAND)
            .pqty(UPDATED_PQTY)
            .untnd(UPDATED_UNTND)
            .isSpcw(UPDATED_IS_SPCW)
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
            .spcw01Amt(UPDATED_SPCW_01_AMT)
            .spcw01Nm(UPDATED_SPCW_01_NM)
            .spcw02Amt(UPDATED_SPCW_02_AMT)
            .spcw02Nm(UPDATED_SPCW_02_NM)
            .spcw03Amt(UPDATED_SPCW_03_AMT)
            .spcw03Nm(UPDATED_SPCW_03_NM)
            .spcw04Amt(UPDATED_SPCW_04_AMT)
            .spcw04Nm(UPDATED_SPCW_04_NM)
            .spcw05Amt(UPDATED_SPCW_05_AMT)
            .spcw05Nm(UPDATED_SPCW_05_NM)
            .spcw06Amt(UPDATED_SPCW_06_AMT)
            .spcw06Nm(UPDATED_SPCW_06_NM)
            .spcw07Amt(UPDATED_SPCW_07_AMT)
            .spcw07Nm(UPDATED_SPCW_07_NM)
            .spcw08Amt(UPDATED_SPCW_08_AMT)
            .spcw08Nm(UPDATED_SPCW_08_NM)
            .spcw09Amt(UPDATED_SPCW_09_AMT)
            .spcw09Nm(UPDATED_SPCW_09_NM)
            .spcw10Amt(UPDATED_SPCW_10_AMT)
            .spcw10Nm(UPDATED_SPCW_10_NM)
            .spcw11Amt(UPDATED_SPCW_11_AMT)
            .spcw11Nm(UPDATED_SPCW_11_NM)
            .spcw12Amt(UPDATED_SPCW_12_AMT)
            .spcw12Nm(UPDATED_SPCW_12_NM)
            .job01Type(UPDATED_JOB_01_TYPE)
            .job02Type(UPDATED_JOB_02_TYPE)
            .job03Type(UPDATED_JOB_03_TYPE)
            .job04Type(UPDATED_JOB_04_TYPE)
            .job05Type(UPDATED_JOB_05_TYPE)
            .job06Type(UPDATED_JOB_06_TYPE)
            .job07Type(UPDATED_JOB_07_TYPE)
            .job08Type(UPDATED_JOB_08_TYPE)
            .job09Type(UPDATED_JOB_09_TYPE)
            .job10Type(UPDATED_JOB_10_TYPE)
            .job11Type(UPDATED_JOB_11_TYPE)
            .job12Type(UPDATED_JOB_12_TYPE)
            .job13Type(UPDATED_JOB_13_TYPE)
            .job14Type(UPDATED_JOB_14_TYPE)
            .semset(UPDATED_SEMSET)
            .isRcb(UPDATED_IS_RCB)
            .sorc1(UPDATED_SORC_1)
            .sorc1b(UPDATED_SORC_1_B)
            .sorc1c(UPDATED_SORC_1_C)
            .sorc6(UPDATED_SORC_6)
            .sorc3e(UPDATED_SORC_3_E)
            .sorc1d(UPDATED_SORC_1_D)
            .sorc2(UPDATED_SORC_2)
            .sorc3(UPDATED_SORC_3)
            .sorc3a(UPDATED_SORC_3_A)
            .sorc4(UPDATED_SORC_4)
            .sorc5(UPDATED_SORC_5)
            .sorc3b(UPDATED_SORC_3_B)
            .sorc3c(UPDATED_SORC_3_C)
            .sorc3d(UPDATED_SORC_3_D)
            .sorc3b2(UPDATED_SORC_3_B_2)
            .sorc3b3(UPDATED_SORC_3_B_3)
            .sorc3b4(UPDATED_SORC_3_B_4)
            .sorc3b5(UPDATED_SORC_3_B_5)
            .sorc3c1(UPDATED_SORC_3_C_1)
            .sorc3d1(UPDATED_SORC_3_D_1)
            .sorc3d3(UPDATED_SORC_3_D_3)
            .sorc3d4(UPDATED_SORC_3_D_4)
            .sorc7(UPDATED_SORC_7)
            .sorc7Use(UPDATED_SORC_7_USE)
            .sorc8(UPDATED_SORC_8)
            .sorc7Use2(UPDATED_SORC_7_USE_2)
            .sorc7Use3(UPDATED_SORC_7_USE_3)
            .openYn(UPDATED_OPEN_YN)
            .wKind(UPDATED_W_KIND)
            .wDays(UPDATED_W_DAYS)
            .wRemk(UPDATED_W_REMK)
            .bCode(UPDATED_B_CODE)
            .totAmt(UPDATED_TOT_AMT)
            .bdgt1(UPDATED_BDGT_1)
            .igtct(UPDATED_IGTCT)
            .othct(UPDATED_OTHCT)
            .rsvct(UPDATED_RSVCT)
            .poll(UPDATED_POLL)
            .pollNo(UPDATED_POLL_NO)
            .othMon(UPDATED_OTH_MON)
            .gyResn(UPDATED_GY_RESN)
            .drut(UPDATED_DRUT)
            .drct(UPDATED_DRCT)
            .dsrm(UPDATED_DSRM)
            .scrm(UPDATED_SCRM)
            .scrr(UPDATED_SCRR)
            .scnum(UPDATED_SCNUM)
            .pcmut(UPDATED_PCMUT)
            .ccnum(UPDATED_CCNUM)
            .issStartDate(UPDATED_ISS_START_DATE)
            .issEndDate(UPDATED_ISS_END_DATE)
            .issCost(UPDATED_ISS_COST)
            .issNo(UPDATED_ISS_NO)
            .issRmk(UPDATED_ISS_RMK)
            .pdsDate(UPDATED_PDS_DATE)
            .dsDate(UPDATED_DS_DATE)
            .adsDate(UPDATED_ADS_DATE)
            .getlDate(UPDATED_GETL_DATE)
            .agetlDate(UPDATED_AGETL_DATE)
            .isSpay(UPDATED_IS_SPAY)
            .ipe(UPDATED_IPE)
            .ipeY(UPDATED_IPE_Y)
            .ipeN(UPDATED_IPE_N)
            .isNlic(UPDATED_IS_NLIC)
            .isDlic(UPDATED_IS_DLIC)
            .licNo(UPDATED_LIC_NO)
            .licDate(UPDATED_LIC_DATE)
            .alicDate(UPDATED_ALIC_DATE)
            .opDate(UPDATED_OP_DATE)
            .aopDate(UPDATED_AOP_DATE)
            .pckDate(UPDATED_PCK_DATE)
            .ackDate(UPDATED_ACK_DATE)
            .pscDate(UPDATED_PSC_DATE)
            .ascDate(UPDATED_ASC_DATE)
            .pctDate(UPDATED_PCT_DATE)
            .actDate(UPDATED_ACT_DATE)
            .ctmeth(UPDATED_CTMETH)
            .actmth(UPDATED_ACTMTH)
            .specPurch(UPDATED_SPEC_PURCH)
            .isIntl(UPDATED_IS_INTL)
            .tctmth(UPDATED_TCTMTH)
            .bdgt2(UPDATED_BDGT_2)
            .bdgt3(UPDATED_BDGT_3)
            .mctmth(UPDATED_MCTMTH)
            .ccnt3(UPDATED_CCNT_3)
            .isPer(UPDATED_IS_PER)
            .ctSum(UPDATED_CT_SUM)
            .ctSumF(UPDATED_CT_SUM_F)
            .ctSumU(UPDATED_CT_SUM_U)
            .ctmo(UPDATED_CTMO)
            .ctSumD(UPDATED_CT_SUM_D)
            .cntid(UPDATED_CNTID)
            .pays(UPDATED_PAYS)
            .pmsSumT(UPDATED_PMS_SUM_T)
            .pmsType(UPDATED_PMS_TYPE)
            .paymth(UPDATED_PAYMTH)
            .foreignYn(UPDATED_FOREIGN_YN)
            .csDate(UPDATED_CS_DATE)
            .asDate(UPDATED_AS_DATE)
            .asuDate(UPDATED_ASU_DATE)
            .ceDate(UPDATED_CE_DATE)
            .ceDateF(UPDATED_CE_DATE_F)
            .aeDate(UPDATED_AE_DATE)
            .fcost2(UPDATED_FCOST_2)
            .fcsDate(UPDATED_FCS_DATE)
            .afcsDate(UPDATED_AFCS_DATE)
            .fcost(UPDATED_FCOST)
            .isCkp(UPDATED_IS_CKP)
            .isCkp2(UPDATED_IS_CKP_2)
            .isCkp3(UPDATED_IS_CKP_3)
            .ckpMoney(UPDATED_CKP_MONEY)
            .ckpUnit(UPDATED_CKP_UNIT)
            .ckpDate(UPDATED_CKP_DATE)
            .ckpCnt(UPDATED_CKP_CNT)
            .ckpNum(UPDATED_CKP_NUM)
            .ckaUnit(UPDATED_CKA_UNIT)
            .ckaDate(UPDATED_CKA_DATE)
            .ckaCnt(UPDATED_CKA_CNT)
            .ckaNum(UPDATED_CKA_NUM)
            .ckdUnit(UPDATED_CKD_UNIT)
            .ckdDate(UPDATED_CKD_DATE)
            .ckdCnt(UPDATED_CKD_CNT)
            .ckdNum(UPDATED_CKD_NUM)
            .workMemo(UPDATED_WORK_MEMO)
            .hotLine(UPDATED_HOT_LINE)
            .lnkCode2(UPDATED_LNK_CODE_2)
            .lnkCode3(UPDATED_LNK_CODE_3)
            .lnkNo2(UPDATED_LNK_NO_2)
            .ccnt1(UPDATED_CCNT_1)
            .typeCode2(UPDATED_TYPE_CODE_2)
            .deptName(UPDATED_DEPT_NAME)
            .aopCnt(UPDATED_AOP_CNT)
            .uniqueKey(UPDATED_UNIQUE_KEY)
            .isBidstr(UPDATED_IS_BIDSTR)
            .isEngineer(UPDATED_IS_ENGINEER)
            .name0(UPDATED_NAME_0)
            .ctSum0(UPDATED_CT_SUM_0)
            .bdgt10(UPDATED_BDGT_10)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .batch(UPDATED_BATCH)
            .nocntDay(UPDATED_NOCNT_DAY)
            .status(UPDATED_STATUS)
            .aokDate(UPDATED_AOK_DATE)
            .fcost3(UPDATED_FCOST_3)
            .finWays(UPDATED_FIN_WAYS)
            .aworkDay(UPDATED_AWORK_DAY)
            .fastDay(UPDATED_FAST_DAY)
            .overDay(UPDATED_OVER_DAY)
            .overDayY(UPDATED_OVER_DAY_Y)
            .overFee(UPDATED_OVER_FEE)
            .otherFee(UPDATED_OTHER_FEE)
            .punshFee(UPDATED_PUNSH_FEE)
            .tm2x(UPDATED_TM_2_X)
            .tm2y(UPDATED_TM_2_Y)
            .tm2x2(UPDATED_TM_2_X_2)
            .tm2y2(UPDATED_TM_2_Y_2)
            .tm2x3(UPDATED_TM_2_X_3)
            .tm2y3(UPDATED_TM_2_Y_3)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP)
            .can(UPDATED_CAN)
            .srut(UPDATED_SRUT)
            .anoct(UPDATED_ANOCT)
            .cepdNo(UPDATED_CEPD_NO)
            .subNo(UPDATED_SUB_NO)
            .scnt(UPDATED_SCNT)
            .dsdm(UPDATED_DSDM)
            .preson(UPDATED_PRESON)
            .psignDate(UPDATED_PSIGN_DATE)
            .asignDate(UPDATED_ASIGN_DATE)
            .trDate(UPDATED_TR_DATE)
            .confIn(UPDATED_CONF_IN)
            .uKey(UPDATED_U_KEY)
            .tpcoth(UPDATED_TPCOTH)
            .pcmct(UPDATED_PCMCT)
            .wkutOld(UPDATED_WKUT_OLD)
            .pccMemo(UPDATED_PCC_MEMO)
            .bgbSumt(UPDATED_BGB_SUMT)
            .bgbDate(UPDATED_BGB_DATE)
            .mainItem(UPDATED_MAIN_ITEM)
            .mDesc(UPDATED_M_DESC)
            .mRmk(UPDATED_M_RMK)
            .pmrSumt(UPDATED_PMR_SUMT)
            .pmrDate(UPDATED_PMR_DATE)
            .pmrResm(UPDATED_PMR_RESM);
        return bidProject;
    }

    @BeforeEach
    public void initTest() {
        bidProject = createEntity(em);
    }

    @Test
    @Transactional
    void createBidProject() throws Exception {
        int databaseSizeBeforeCreate = bidProjectRepository.findAll().size();
        // Create the BidProject
        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);
        restBidProjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectDTO)))
            .andExpect(status().isCreated());

        // Validate the BidProject in the database
        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeCreate + 1);
        BidProject testBidProject = bidProjectList.get(bidProjectList.size() - 1);
        assertThat(testBidProject.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidProject.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidProject.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBidProject.getIsAdd92()).isEqualTo(DEFAULT_IS_ADD_92);
        assertThat(testBidProject.getPntCtrl()).isEqualTo(DEFAULT_PNT_CTRL);
        assertThat(testBidProject.getTypeCode()).isEqualTo(DEFAULT_TYPE_CODE);
        assertThat(testBidProject.getIsUnion()).isEqualTo(DEFAULT_IS_UNION);
        assertThat(testBidProject.getPlaceCode()).isEqualTo(DEFAULT_PLACE_CODE);
        assertThat(testBidProject.getPlace()).isEqualTo(DEFAULT_PLACE);
        assertThat(testBidProject.getBosCode()).isEqualTo(DEFAULT_BOS_CODE);
        assertThat(testBidProject.getDepCode()).isEqualTo(DEFAULT_DEP_CODE);
        assertThat(testBidProject.getIsBg()).isEqualTo(DEFAULT_IS_BG);
        assertThat(testBidProject.getPlnCode()).isEqualTo(DEFAULT_PLN_CODE);
        assertThat(testBidProject.getPlnNo()).isEqualTo(DEFAULT_PLN_NO);
        assertThat(testBidProject.getPlnUnicode()).isEqualTo(DEFAULT_PLN_UNICODE);
        assertThat(testBidProject.getBos2Code()).isEqualTo(DEFAULT_BOS_2_CODE);
        assertThat(testBidProject.getBos2Rate()).isEqualByComparingTo(DEFAULT_BOS_2_RATE);
        assertThat(testBidProject.getBos2bCode()).isEqualTo(DEFAULT_BOS_2_B_CODE);
        assertThat(testBidProject.getBos2bRate()).isEqualByComparingTo(DEFAULT_BOS_2_B_RATE);
        assertThat(testBidProject.getBos2cCode()).isEqualTo(DEFAULT_BOS_2_C_CODE);
        assertThat(testBidProject.getBos2cRate()).isEqualByComparingTo(DEFAULT_BOS_2_C_RATE);
        assertThat(testBidProject.getBos2dCode()).isEqualTo(DEFAULT_BOS_2_D_CODE);
        assertThat(testBidProject.getBos2dRate()).isEqualByComparingTo(DEFAULT_BOS_2_D_RATE);
        assertThat(testBidProject.getLnkCode()).isEqualTo(DEFAULT_LNK_CODE);
        assertThat(testBidProject.getCcnt2()).isEqualTo(DEFAULT_CCNT_2);
        assertThat(testBidProject.getMan()).isEqualTo(DEFAULT_MAN);
        assertThat(testBidProject.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testBidProject.geteMail()).isEqualTo(DEFAULT_E_MAIL);
        assertThat(testBidProject.getWrate()).isEqualByComparingTo(DEFAULT_WRATE);
        assertThat(testBidProject.getContain()).isEqualTo(DEFAULT_CONTAIN);
        assertThat(testBidProject.getPfunc()).isEqualTo(DEFAULT_PFUNC);
        assertThat(testBidProject.getBenf()).isEqualTo(DEFAULT_BENF);
        assertThat(testBidProject.getPstand()).isEqualTo(DEFAULT_PSTAND);
        assertThat(testBidProject.getPqty()).isEqualTo(DEFAULT_PQTY);
        assertThat(testBidProject.getUntnd()).isEqualTo(DEFAULT_UNTND);
        assertThat(testBidProject.getIsSpcw()).isEqualTo(DEFAULT_IS_SPCW);
        assertThat(testBidProject.getSpcw01()).isEqualTo(DEFAULT_SPCW_01);
        assertThat(testBidProject.getSpcw02()).isEqualTo(DEFAULT_SPCW_02);
        assertThat(testBidProject.getSpcw03()).isEqualTo(DEFAULT_SPCW_03);
        assertThat(testBidProject.getSpcw04()).isEqualTo(DEFAULT_SPCW_04);
        assertThat(testBidProject.getSpcw05()).isEqualTo(DEFAULT_SPCW_05);
        assertThat(testBidProject.getSpcw06()).isEqualTo(DEFAULT_SPCW_06);
        assertThat(testBidProject.getSpcw07()).isEqualTo(DEFAULT_SPCW_07);
        assertThat(testBidProject.getSpcw08()).isEqualTo(DEFAULT_SPCW_08);
        assertThat(testBidProject.getSpcw09()).isEqualTo(DEFAULT_SPCW_09);
        assertThat(testBidProject.getSpcw10()).isEqualTo(DEFAULT_SPCW_10);
        assertThat(testBidProject.getSpcw11()).isEqualTo(DEFAULT_SPCW_11);
        assertThat(testBidProject.getSpcw12()).isEqualTo(DEFAULT_SPCW_12);
        assertThat(testBidProject.getSpcw01Amt()).isEqualByComparingTo(DEFAULT_SPCW_01_AMT);
        assertThat(testBidProject.getSpcw01Nm()).isEqualTo(DEFAULT_SPCW_01_NM);
        assertThat(testBidProject.getSpcw02Amt()).isEqualByComparingTo(DEFAULT_SPCW_02_AMT);
        assertThat(testBidProject.getSpcw02Nm()).isEqualTo(DEFAULT_SPCW_02_NM);
        assertThat(testBidProject.getSpcw03Amt()).isEqualByComparingTo(DEFAULT_SPCW_03_AMT);
        assertThat(testBidProject.getSpcw03Nm()).isEqualTo(DEFAULT_SPCW_03_NM);
        assertThat(testBidProject.getSpcw04Amt()).isEqualByComparingTo(DEFAULT_SPCW_04_AMT);
        assertThat(testBidProject.getSpcw04Nm()).isEqualTo(DEFAULT_SPCW_04_NM);
        assertThat(testBidProject.getSpcw05Amt()).isEqualByComparingTo(DEFAULT_SPCW_05_AMT);
        assertThat(testBidProject.getSpcw05Nm()).isEqualTo(DEFAULT_SPCW_05_NM);
        assertThat(testBidProject.getSpcw06Amt()).isEqualByComparingTo(DEFAULT_SPCW_06_AMT);
        assertThat(testBidProject.getSpcw06Nm()).isEqualTo(DEFAULT_SPCW_06_NM);
        assertThat(testBidProject.getSpcw07Amt()).isEqualByComparingTo(DEFAULT_SPCW_07_AMT);
        assertThat(testBidProject.getSpcw07Nm()).isEqualTo(DEFAULT_SPCW_07_NM);
        assertThat(testBidProject.getSpcw08Amt()).isEqualByComparingTo(DEFAULT_SPCW_08_AMT);
        assertThat(testBidProject.getSpcw08Nm()).isEqualTo(DEFAULT_SPCW_08_NM);
        assertThat(testBidProject.getSpcw09Amt()).isEqualByComparingTo(DEFAULT_SPCW_09_AMT);
        assertThat(testBidProject.getSpcw09Nm()).isEqualTo(DEFAULT_SPCW_09_NM);
        assertThat(testBidProject.getSpcw10Amt()).isEqualByComparingTo(DEFAULT_SPCW_10_AMT);
        assertThat(testBidProject.getSpcw10Nm()).isEqualTo(DEFAULT_SPCW_10_NM);
        assertThat(testBidProject.getSpcw11Amt()).isEqualByComparingTo(DEFAULT_SPCW_11_AMT);
        assertThat(testBidProject.getSpcw11Nm()).isEqualTo(DEFAULT_SPCW_11_NM);
        assertThat(testBidProject.getSpcw12Amt()).isEqualByComparingTo(DEFAULT_SPCW_12_AMT);
        assertThat(testBidProject.getSpcw12Nm()).isEqualTo(DEFAULT_SPCW_12_NM);
        assertThat(testBidProject.getJob01Type()).isEqualTo(DEFAULT_JOB_01_TYPE);
        assertThat(testBidProject.getJob02Type()).isEqualTo(DEFAULT_JOB_02_TYPE);
        assertThat(testBidProject.getJob03Type()).isEqualTo(DEFAULT_JOB_03_TYPE);
        assertThat(testBidProject.getJob04Type()).isEqualTo(DEFAULT_JOB_04_TYPE);
        assertThat(testBidProject.getJob05Type()).isEqualTo(DEFAULT_JOB_05_TYPE);
        assertThat(testBidProject.getJob06Type()).isEqualTo(DEFAULT_JOB_06_TYPE);
        assertThat(testBidProject.getJob07Type()).isEqualTo(DEFAULT_JOB_07_TYPE);
        assertThat(testBidProject.getJob08Type()).isEqualTo(DEFAULT_JOB_08_TYPE);
        assertThat(testBidProject.getJob09Type()).isEqualTo(DEFAULT_JOB_09_TYPE);
        assertThat(testBidProject.getJob10Type()).isEqualTo(DEFAULT_JOB_10_TYPE);
        assertThat(testBidProject.getJob11Type()).isEqualTo(DEFAULT_JOB_11_TYPE);
        assertThat(testBidProject.getJob12Type()).isEqualTo(DEFAULT_JOB_12_TYPE);
        assertThat(testBidProject.getJob13Type()).isEqualTo(DEFAULT_JOB_13_TYPE);
        assertThat(testBidProject.getJob14Type()).isEqualTo(DEFAULT_JOB_14_TYPE);
        assertThat(testBidProject.getSemset()).isEqualTo(DEFAULT_SEMSET);
        assertThat(testBidProject.getIsRcb()).isEqualTo(DEFAULT_IS_RCB);
        assertThat(testBidProject.getSorc1()).isEqualByComparingTo(DEFAULT_SORC_1);
        assertThat(testBidProject.getSorc1b()).isEqualByComparingTo(DEFAULT_SORC_1_B);
        assertThat(testBidProject.getSorc1c()).isEqualByComparingTo(DEFAULT_SORC_1_C);
        assertThat(testBidProject.getSorc6()).isEqualByComparingTo(DEFAULT_SORC_6);
        assertThat(testBidProject.getSorc3e()).isEqualByComparingTo(DEFAULT_SORC_3_E);
        assertThat(testBidProject.getSorc1d()).isEqualByComparingTo(DEFAULT_SORC_1_D);
        assertThat(testBidProject.getSorc2()).isEqualByComparingTo(DEFAULT_SORC_2);
        assertThat(testBidProject.getSorc3()).isEqualByComparingTo(DEFAULT_SORC_3);
        assertThat(testBidProject.getSorc3a()).isEqualByComparingTo(DEFAULT_SORC_3_A);
        assertThat(testBidProject.getSorc4()).isEqualByComparingTo(DEFAULT_SORC_4);
        assertThat(testBidProject.getSorc5()).isEqualByComparingTo(DEFAULT_SORC_5);
        assertThat(testBidProject.getSorc3b()).isEqualByComparingTo(DEFAULT_SORC_3_B);
        assertThat(testBidProject.getSorc3c()).isEqualByComparingTo(DEFAULT_SORC_3_C);
        assertThat(testBidProject.getSorc3d()).isEqualByComparingTo(DEFAULT_SORC_3_D);
        assertThat(testBidProject.getSorc3b2()).isEqualByComparingTo(DEFAULT_SORC_3_B_2);
        assertThat(testBidProject.getSorc3b3()).isEqualByComparingTo(DEFAULT_SORC_3_B_3);
        assertThat(testBidProject.getSorc3b4()).isEqualByComparingTo(DEFAULT_SORC_3_B_4);
        assertThat(testBidProject.getSorc3b5()).isEqualByComparingTo(DEFAULT_SORC_3_B_5);
        assertThat(testBidProject.getSorc3c1()).isEqualByComparingTo(DEFAULT_SORC_3_C_1);
        assertThat(testBidProject.getSorc3d1()).isEqualByComparingTo(DEFAULT_SORC_3_D_1);
        assertThat(testBidProject.getSorc3d3()).isEqualByComparingTo(DEFAULT_SORC_3_D_3);
        assertThat(testBidProject.getSorc3d4()).isEqualByComparingTo(DEFAULT_SORC_3_D_4);
        assertThat(testBidProject.getSorc7()).isEqualByComparingTo(DEFAULT_SORC_7);
        assertThat(testBidProject.getSorc7Use()).isEqualByComparingTo(DEFAULT_SORC_7_USE);
        assertThat(testBidProject.getSorc8()).isEqualByComparingTo(DEFAULT_SORC_8);
        assertThat(testBidProject.getSorc7Use2()).isEqualByComparingTo(DEFAULT_SORC_7_USE_2);
        assertThat(testBidProject.getSorc7Use3()).isEqualByComparingTo(DEFAULT_SORC_7_USE_3);
        assertThat(testBidProject.getOpenYn()).isEqualTo(DEFAULT_OPEN_YN);
        assertThat(testBidProject.getwKind()).isEqualTo(DEFAULT_W_KIND);
        assertThat(testBidProject.getwDays()).isEqualTo(DEFAULT_W_DAYS);
        assertThat(testBidProject.getwRemk()).isEqualTo(DEFAULT_W_REMK);
        assertThat(testBidProject.getbCode()).isEqualTo(DEFAULT_B_CODE);
        assertThat(testBidProject.getTotAmt()).isEqualByComparingTo(DEFAULT_TOT_AMT);
        assertThat(testBidProject.getBdgt1()).isEqualByComparingTo(DEFAULT_BDGT_1);
        assertThat(testBidProject.getIgtct()).isEqualByComparingTo(DEFAULT_IGTCT);
        assertThat(testBidProject.getOthct()).isEqualByComparingTo(DEFAULT_OTHCT);
        assertThat(testBidProject.getRsvct()).isEqualByComparingTo(DEFAULT_RSVCT);
        assertThat(testBidProject.getPoll()).isEqualByComparingTo(DEFAULT_POLL);
        assertThat(testBidProject.getPollNo()).isEqualTo(DEFAULT_POLL_NO);
        assertThat(testBidProject.getOthMon()).isEqualByComparingTo(DEFAULT_OTH_MON);
        assertThat(testBidProject.getGyResn()).isEqualTo(DEFAULT_GY_RESN);
        assertThat(testBidProject.getDrut()).isEqualTo(DEFAULT_DRUT);
        assertThat(testBidProject.getDrct()).isEqualByComparingTo(DEFAULT_DRCT);
        assertThat(testBidProject.getDsrm()).isEqualTo(DEFAULT_DSRM);
        assertThat(testBidProject.getScrm()).isEqualTo(DEFAULT_SCRM);
        assertThat(testBidProject.getScrr()).isEqualTo(DEFAULT_SCRR);
        assertThat(testBidProject.getScnum()).isEqualTo(DEFAULT_SCNUM);
        assertThat(testBidProject.getPcmut()).isEqualTo(DEFAULT_PCMUT);
        assertThat(testBidProject.getCcnum()).isEqualTo(DEFAULT_CCNUM);
        assertThat(testBidProject.getIssStartDate()).isEqualTo(DEFAULT_ISS_START_DATE);
        assertThat(testBidProject.getIssEndDate()).isEqualTo(DEFAULT_ISS_END_DATE);
        assertThat(testBidProject.getIssCost()).isEqualByComparingTo(DEFAULT_ISS_COST);
        assertThat(testBidProject.getIssNo()).isEqualTo(DEFAULT_ISS_NO);
        assertThat(testBidProject.getIssRmk()).isEqualTo(DEFAULT_ISS_RMK);
        assertThat(testBidProject.getPdsDate()).isEqualTo(DEFAULT_PDS_DATE);
        assertThat(testBidProject.getDsDate()).isEqualTo(DEFAULT_DS_DATE);
        assertThat(testBidProject.getAdsDate()).isEqualTo(DEFAULT_ADS_DATE);
        assertThat(testBidProject.getGetlDate()).isEqualTo(DEFAULT_GETL_DATE);
        assertThat(testBidProject.getAgetlDate()).isEqualTo(DEFAULT_AGETL_DATE);
        assertThat(testBidProject.getIsSpay()).isEqualTo(DEFAULT_IS_SPAY);
        assertThat(testBidProject.getIpe()).isEqualTo(DEFAULT_IPE);
        assertThat(testBidProject.getIpeY()).isEqualTo(DEFAULT_IPE_Y);
        assertThat(testBidProject.getIpeN()).isEqualTo(DEFAULT_IPE_N);
        assertThat(testBidProject.getIsNlic()).isEqualTo(DEFAULT_IS_NLIC);
        assertThat(testBidProject.getIsDlic()).isEqualTo(DEFAULT_IS_DLIC);
        assertThat(testBidProject.getLicNo()).isEqualTo(DEFAULT_LIC_NO);
        assertThat(testBidProject.getLicDate()).isEqualTo(DEFAULT_LIC_DATE);
        assertThat(testBidProject.getAlicDate()).isEqualTo(DEFAULT_ALIC_DATE);
        assertThat(testBidProject.getOpDate()).isEqualTo(DEFAULT_OP_DATE);
        assertThat(testBidProject.getAopDate()).isEqualTo(DEFAULT_AOP_DATE);
        assertThat(testBidProject.getPckDate()).isEqualTo(DEFAULT_PCK_DATE);
        assertThat(testBidProject.getAckDate()).isEqualTo(DEFAULT_ACK_DATE);
        assertThat(testBidProject.getPscDate()).isEqualTo(DEFAULT_PSC_DATE);
        assertThat(testBidProject.getAscDate()).isEqualTo(DEFAULT_ASC_DATE);
        assertThat(testBidProject.getPctDate()).isEqualTo(DEFAULT_PCT_DATE);
        assertThat(testBidProject.getActDate()).isEqualTo(DEFAULT_ACT_DATE);
        assertThat(testBidProject.getCtmeth()).isEqualTo(DEFAULT_CTMETH);
        assertThat(testBidProject.getActmth()).isEqualTo(DEFAULT_ACTMTH);
        assertThat(testBidProject.getSpecPurch()).isEqualTo(DEFAULT_SPEC_PURCH);
        assertThat(testBidProject.getIsIntl()).isEqualTo(DEFAULT_IS_INTL);
        assertThat(testBidProject.getTctmth()).isEqualTo(DEFAULT_TCTMTH);
        assertThat(testBidProject.getBdgt2()).isEqualByComparingTo(DEFAULT_BDGT_2);
        assertThat(testBidProject.getBdgt3()).isEqualByComparingTo(DEFAULT_BDGT_3);
        assertThat(testBidProject.getMctmth()).isEqualTo(DEFAULT_MCTMTH);
        assertThat(testBidProject.getCcnt3()).isEqualTo(DEFAULT_CCNT_3);
        assertThat(testBidProject.getIsPer()).isEqualTo(DEFAULT_IS_PER);
        assertThat(testBidProject.getCtSum()).isEqualByComparingTo(DEFAULT_CT_SUM);
        assertThat(testBidProject.getCtSumF()).isEqualByComparingTo(DEFAULT_CT_SUM_F);
        assertThat(testBidProject.getCtSumU()).isEqualByComparingTo(DEFAULT_CT_SUM_U);
        assertThat(testBidProject.getCtmo()).isEqualTo(DEFAULT_CTMO);
        assertThat(testBidProject.getCtSumD()).isEqualByComparingTo(DEFAULT_CT_SUM_D);
        assertThat(testBidProject.getCntid()).isEqualTo(DEFAULT_CNTID);
        assertThat(testBidProject.getPays()).isEqualByComparingTo(DEFAULT_PAYS);
        assertThat(testBidProject.getPmsSumT()).isEqualByComparingTo(DEFAULT_PMS_SUM_T);
        assertThat(testBidProject.getPmsType()).isEqualTo(DEFAULT_PMS_TYPE);
        assertThat(testBidProject.getPaymth()).isEqualTo(DEFAULT_PAYMTH);
        assertThat(testBidProject.getForeignYn()).isEqualTo(DEFAULT_FOREIGN_YN);
        assertThat(testBidProject.getCsDate()).isEqualTo(DEFAULT_CS_DATE);
        assertThat(testBidProject.getAsDate()).isEqualTo(DEFAULT_AS_DATE);
        assertThat(testBidProject.getAsuDate()).isEqualTo(DEFAULT_ASU_DATE);
        assertThat(testBidProject.getCeDate()).isEqualTo(DEFAULT_CE_DATE);
        assertThat(testBidProject.getCeDateF()).isEqualTo(DEFAULT_CE_DATE_F);
        assertThat(testBidProject.getAeDate()).isEqualTo(DEFAULT_AE_DATE);
        assertThat(testBidProject.getFcost2()).isEqualByComparingTo(DEFAULT_FCOST_2);
        assertThat(testBidProject.getFcsDate()).isEqualTo(DEFAULT_FCS_DATE);
        assertThat(testBidProject.getAfcsDate()).isEqualTo(DEFAULT_AFCS_DATE);
        assertThat(testBidProject.getFcost()).isEqualByComparingTo(DEFAULT_FCOST);
        assertThat(testBidProject.getIsCkp()).isEqualTo(DEFAULT_IS_CKP);
        assertThat(testBidProject.getIsCkp2()).isEqualTo(DEFAULT_IS_CKP_2);
        assertThat(testBidProject.getIsCkp3()).isEqualTo(DEFAULT_IS_CKP_3);
        assertThat(testBidProject.getCkpMoney()).isEqualByComparingTo(DEFAULT_CKP_MONEY);
        assertThat(testBidProject.getCkpUnit()).isEqualTo(DEFAULT_CKP_UNIT);
        assertThat(testBidProject.getCkpDate()).isEqualTo(DEFAULT_CKP_DATE);
        assertThat(testBidProject.getCkpCnt()).isEqualByComparingTo(DEFAULT_CKP_CNT);
        assertThat(testBidProject.getCkpNum()).isEqualTo(DEFAULT_CKP_NUM);
        assertThat(testBidProject.getCkaUnit()).isEqualTo(DEFAULT_CKA_UNIT);
        assertThat(testBidProject.getCkaDate()).isEqualTo(DEFAULT_CKA_DATE);
        assertThat(testBidProject.getCkaCnt()).isEqualByComparingTo(DEFAULT_CKA_CNT);
        assertThat(testBidProject.getCkaNum()).isEqualTo(DEFAULT_CKA_NUM);
        assertThat(testBidProject.getCkdUnit()).isEqualTo(DEFAULT_CKD_UNIT);
        assertThat(testBidProject.getCkdDate()).isEqualTo(DEFAULT_CKD_DATE);
        assertThat(testBidProject.getCkdCnt()).isEqualByComparingTo(DEFAULT_CKD_CNT);
        assertThat(testBidProject.getCkdNum()).isEqualTo(DEFAULT_CKD_NUM);
        assertThat(testBidProject.getWorkMemo()).isEqualTo(DEFAULT_WORK_MEMO);
        assertThat(testBidProject.getHotLine()).isEqualTo(DEFAULT_HOT_LINE);
        assertThat(testBidProject.getLnkCode2()).isEqualTo(DEFAULT_LNK_CODE_2);
        assertThat(testBidProject.getLnkCode3()).isEqualTo(DEFAULT_LNK_CODE_3);
        assertThat(testBidProject.getLnkNo2()).isEqualTo(DEFAULT_LNK_NO_2);
        assertThat(testBidProject.getCcnt1()).isEqualTo(DEFAULT_CCNT_1);
        assertThat(testBidProject.getTypeCode2()).isEqualTo(DEFAULT_TYPE_CODE_2);
        assertThat(testBidProject.getDeptName()).isEqualTo(DEFAULT_DEPT_NAME);
        assertThat(testBidProject.getAopCnt()).isEqualTo(DEFAULT_AOP_CNT);
        assertThat(testBidProject.getUniqueKey()).isEqualTo(DEFAULT_UNIQUE_KEY);
        assertThat(testBidProject.getIsBidstr()).isEqualTo(DEFAULT_IS_BIDSTR);
        assertThat(testBidProject.getIsEngineer()).isEqualTo(DEFAULT_IS_ENGINEER);
        assertThat(testBidProject.getName0()).isEqualTo(DEFAULT_NAME_0);
        assertThat(testBidProject.getCtSum0()).isEqualByComparingTo(DEFAULT_CT_SUM_0);
        assertThat(testBidProject.getBdgt1_0()).isEqualByComparingTo(DEFAULT_BDGT_10);
        assertThat(testBidProject.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBidProject.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testBidProject.getBatch()).isEqualTo(DEFAULT_BATCH);
        assertThat(testBidProject.getNocntDay()).isEqualTo(DEFAULT_NOCNT_DAY);
        assertThat(testBidProject.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBidProject.getAokDate()).isEqualTo(DEFAULT_AOK_DATE);
        assertThat(testBidProject.getFcost3()).isEqualByComparingTo(DEFAULT_FCOST_3);
        assertThat(testBidProject.getFinWays()).isEqualTo(DEFAULT_FIN_WAYS);
        assertThat(testBidProject.getAworkDay()).isEqualTo(DEFAULT_AWORK_DAY);
        assertThat(testBidProject.getFastDay()).isEqualTo(DEFAULT_FAST_DAY);
        assertThat(testBidProject.getOverDay()).isEqualTo(DEFAULT_OVER_DAY);
        assertThat(testBidProject.getOverDayY()).isEqualTo(DEFAULT_OVER_DAY_Y);
        assertThat(testBidProject.getOverFee()).isEqualByComparingTo(DEFAULT_OVER_FEE);
        assertThat(testBidProject.getOtherFee()).isEqualByComparingTo(DEFAULT_OTHER_FEE);
        assertThat(testBidProject.getPunshFee()).isEqualByComparingTo(DEFAULT_PUNSH_FEE);
        assertThat(testBidProject.getTm2x()).isEqualTo(DEFAULT_TM_2_X);
        assertThat(testBidProject.getTm2y()).isEqualTo(DEFAULT_TM_2_Y);
        assertThat(testBidProject.getTm2x2()).isEqualTo(DEFAULT_TM_2_X_2);
        assertThat(testBidProject.getTm2y2()).isEqualTo(DEFAULT_TM_2_Y_2);
        assertThat(testBidProject.getTm2x3()).isEqualTo(DEFAULT_TM_2_X_3);
        assertThat(testBidProject.getTm2y3()).isEqualTo(DEFAULT_TM_2_Y_3);
        assertThat(testBidProject.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidProject.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidProject.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidProject.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidProject.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidProject.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidProject.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidProject.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testBidProject.getSrut()).isEqualTo(DEFAULT_SRUT);
        assertThat(testBidProject.getAnoct()).isEqualByComparingTo(DEFAULT_ANOCT);
        assertThat(testBidProject.getCepdNo()).isEqualTo(DEFAULT_CEPD_NO);
        assertThat(testBidProject.getSubNo()).isEqualTo(DEFAULT_SUB_NO);
        assertThat(testBidProject.getScnt()).isEqualTo(DEFAULT_SCNT);
        assertThat(testBidProject.getDsdm()).isEqualTo(DEFAULT_DSDM);
        assertThat(testBidProject.getPreson()).isEqualTo(DEFAULT_PRESON);
        assertThat(testBidProject.getPsignDate()).isEqualTo(DEFAULT_PSIGN_DATE);
        assertThat(testBidProject.getAsignDate()).isEqualTo(DEFAULT_ASIGN_DATE);
        assertThat(testBidProject.getTrDate()).isEqualTo(DEFAULT_TR_DATE);
        assertThat(testBidProject.getConfIn()).isEqualByComparingTo(DEFAULT_CONF_IN);
        assertThat(testBidProject.getuKey()).isEqualTo(DEFAULT_U_KEY);
        assertThat(testBidProject.getTpcoth()).isEqualTo(DEFAULT_TPCOTH);
        assertThat(testBidProject.getPcmct()).isEqualByComparingTo(DEFAULT_PCMCT);
        assertThat(testBidProject.getWkutOld()).isEqualTo(DEFAULT_WKUT_OLD);
        assertThat(testBidProject.getPccMemo()).isEqualTo(DEFAULT_PCC_MEMO);
        assertThat(testBidProject.getBgbSumt()).isEqualByComparingTo(DEFAULT_BGB_SUMT);
        assertThat(testBidProject.getBgbDate()).isEqualTo(DEFAULT_BGB_DATE);
        assertThat(testBidProject.getMainItem()).isEqualTo(DEFAULT_MAIN_ITEM);
        assertThat(testBidProject.getmDesc()).isEqualTo(DEFAULT_M_DESC);
        assertThat(testBidProject.getmRmk()).isEqualTo(DEFAULT_M_RMK);
        assertThat(testBidProject.getPmrSumt()).isEqualByComparingTo(DEFAULT_PMR_SUMT);
        assertThat(testBidProject.getPmrDate()).isEqualTo(DEFAULT_PMR_DATE);
        assertThat(testBidProject.getPmrResm()).isEqualTo(DEFAULT_PMR_RESM);
    }

//    @Test
//    @Transactional
//    void createBidProjectWithExistingId() throws Exception {
//        // Create the BidProject with an existing ID
//        bidProject.setId(1L);
//        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);
//
//        int databaseSizeBeforeCreate = bidProjectRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidProjectMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidProject in the database
//        List<BidProject> bidProjectList = bidProjectRepository.findAll();
//        assertThat(bidProjectList).hasSize(databaseSizeBeforeCreate);
//    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectRepository.findAll().size();
        // set the field null
        bidProject.setWkut(null);

        // Create the BidProject, which fails.
        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);

        restBidProjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectDTO)))
            .andExpect(status().isBadRequest());

        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectRepository.findAll().size();
        // set the field null
        bidProject.setPrjno(null);

        // Create the BidProject, which fails.
        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);

        restBidProjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectDTO)))
            .andExpect(status().isBadRequest());

        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectRepository.findAll().size();
        // set the field null
        bidProject.setName(null);

        // Create the BidProject, which fails.
        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);

        restBidProjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectDTO)))
            .andExpect(status().isBadRequest());

        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectRepository.findAll().size();
        // set the field null
        bidProject.setCreateDate(null);

        // Create the BidProject, which fails.
        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);

        restBidProjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectDTO)))
            .andExpect(status().isBadRequest());

        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectRepository.findAll().size();
        // set the field null
        bidProject.setCreateUser(null);

        // Create the BidProject, which fails.
        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);

        restBidProjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectDTO)))
            .andExpect(status().isBadRequest());

        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    void getAllBidProjects() throws Exception {
//        // Initialize the database
//        bidProjectRepository.saveAndFlush(bidProject);
//
//        // Get all the bidProjectList
//        restBidProjectMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidProject.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
//            .andExpect(jsonPath("$.[*].isAdd92").value(hasItem(DEFAULT_IS_ADD_92)))
//            .andExpect(jsonPath("$.[*].pntCtrl").value(hasItem(DEFAULT_PNT_CTRL)))
//            .andExpect(jsonPath("$.[*].typeCode").value(hasItem(DEFAULT_TYPE_CODE)))
//            .andExpect(jsonPath("$.[*].isUnion").value(hasItem(DEFAULT_IS_UNION)))
//            .andExpect(jsonPath("$.[*].placeCode").value(hasItem(DEFAULT_PLACE_CODE)))
//            .andExpect(jsonPath("$.[*].place").value(hasItem(DEFAULT_PLACE)))
//            .andExpect(jsonPath("$.[*].bosCode").value(hasItem(DEFAULT_BOS_CODE)))
//            .andExpect(jsonPath("$.[*].depCode").value(hasItem(DEFAULT_DEP_CODE)))
//            .andExpect(jsonPath("$.[*].isBg").value(hasItem(DEFAULT_IS_BG)))
//            .andExpect(jsonPath("$.[*].plnCode").value(hasItem(DEFAULT_PLN_CODE)))
//            .andExpect(jsonPath("$.[*].plnNo").value(hasItem(DEFAULT_PLN_NO)))
//            .andExpect(jsonPath("$.[*].plnUnicode").value(hasItem(DEFAULT_PLN_UNICODE)))
//            .andExpect(jsonPath("$.[*].bos2Code").value(hasItem(DEFAULT_BOS_2_CODE)))
//            .andExpect(jsonPath("$.[*].bos2Rate").value(hasItem(sameNumber(DEFAULT_BOS_2_RATE))))
//            .andExpect(jsonPath("$.[*].bos2bCode").value(hasItem(DEFAULT_BOS_2_B_CODE)))
//            .andExpect(jsonPath("$.[*].bos2bRate").value(hasItem(sameNumber(DEFAULT_BOS_2_B_RATE))))
//            .andExpect(jsonPath("$.[*].bos2cCode").value(hasItem(DEFAULT_BOS_2_C_CODE)))
//            .andExpect(jsonPath("$.[*].bos2cRate").value(hasItem(sameNumber(DEFAULT_BOS_2_C_RATE))))
//            .andExpect(jsonPath("$.[*].bos2dCode").value(hasItem(DEFAULT_BOS_2_D_CODE)))
//            .andExpect(jsonPath("$.[*].bos2dRate").value(hasItem(sameNumber(DEFAULT_BOS_2_D_RATE))))
//            .andExpect(jsonPath("$.[*].lnkCode").value(hasItem(DEFAULT_LNK_CODE)))
//            .andExpect(jsonPath("$.[*].ccnt2").value(hasItem(DEFAULT_CCNT_2)))
//            .andExpect(jsonPath("$.[*].man").value(hasItem(DEFAULT_MAN)))
//            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
//            .andExpect(jsonPath("$.[*].eMail").value(hasItem(DEFAULT_E_MAIL)))
//            .andExpect(jsonPath("$.[*].wrate").value(hasItem(sameNumber(DEFAULT_WRATE))))
//            .andExpect(jsonPath("$.[*].contain").value(hasItem(DEFAULT_CONTAIN)))
//            .andExpect(jsonPath("$.[*].pfunc").value(hasItem(DEFAULT_PFUNC)))
//            .andExpect(jsonPath("$.[*].benf").value(hasItem(DEFAULT_BENF)))
//            .andExpect(jsonPath("$.[*].pstand").value(hasItem(DEFAULT_PSTAND)))
//            .andExpect(jsonPath("$.[*].pqty").value(hasItem(DEFAULT_PQTY)))
//            .andExpect(jsonPath("$.[*].untnd").value(hasItem(DEFAULT_UNTND)))
//            .andExpect(jsonPath("$.[*].isSpcw").value(hasItem(DEFAULT_IS_SPCW)))
//            .andExpect(jsonPath("$.[*].spcw01").value(hasItem(DEFAULT_SPCW_01)))
//            .andExpect(jsonPath("$.[*].spcw02").value(hasItem(DEFAULT_SPCW_02)))
//            .andExpect(jsonPath("$.[*].spcw03").value(hasItem(DEFAULT_SPCW_03)))
//            .andExpect(jsonPath("$.[*].spcw04").value(hasItem(DEFAULT_SPCW_04)))
//            .andExpect(jsonPath("$.[*].spcw05").value(hasItem(DEFAULT_SPCW_05)))
//            .andExpect(jsonPath("$.[*].spcw06").value(hasItem(DEFAULT_SPCW_06)))
//            .andExpect(jsonPath("$.[*].spcw07").value(hasItem(DEFAULT_SPCW_07)))
//            .andExpect(jsonPath("$.[*].spcw08").value(hasItem(DEFAULT_SPCW_08)))
//            .andExpect(jsonPath("$.[*].spcw09").value(hasItem(DEFAULT_SPCW_09)))
//            .andExpect(jsonPath("$.[*].spcw10").value(hasItem(DEFAULT_SPCW_10)))
//            .andExpect(jsonPath("$.[*].spcw11").value(hasItem(DEFAULT_SPCW_11)))
//            .andExpect(jsonPath("$.[*].spcw12").value(hasItem(DEFAULT_SPCW_12)))
//            .andExpect(jsonPath("$.[*].spcw01Amt").value(hasItem(sameNumber(DEFAULT_SPCW_01_AMT))))
//            .andExpect(jsonPath("$.[*].spcw01Nm").value(hasItem(DEFAULT_SPCW_01_NM)))
//            .andExpect(jsonPath("$.[*].spcw02Amt").value(hasItem(sameNumber(DEFAULT_SPCW_02_AMT))))
//            .andExpect(jsonPath("$.[*].spcw02Nm").value(hasItem(DEFAULT_SPCW_02_NM)))
//            .andExpect(jsonPath("$.[*].spcw03Amt").value(hasItem(sameNumber(DEFAULT_SPCW_03_AMT))))
//            .andExpect(jsonPath("$.[*].spcw03Nm").value(hasItem(DEFAULT_SPCW_03_NM)))
//            .andExpect(jsonPath("$.[*].spcw04Amt").value(hasItem(sameNumber(DEFAULT_SPCW_04_AMT))))
//            .andExpect(jsonPath("$.[*].spcw04Nm").value(hasItem(DEFAULT_SPCW_04_NM)))
//            .andExpect(jsonPath("$.[*].spcw05Amt").value(hasItem(sameNumber(DEFAULT_SPCW_05_AMT))))
//            .andExpect(jsonPath("$.[*].spcw05Nm").value(hasItem(DEFAULT_SPCW_05_NM)))
//            .andExpect(jsonPath("$.[*].spcw06Amt").value(hasItem(sameNumber(DEFAULT_SPCW_06_AMT))))
//            .andExpect(jsonPath("$.[*].spcw06Nm").value(hasItem(DEFAULT_SPCW_06_NM)))
//            .andExpect(jsonPath("$.[*].spcw07Amt").value(hasItem(sameNumber(DEFAULT_SPCW_07_AMT))))
//            .andExpect(jsonPath("$.[*].spcw07Nm").value(hasItem(DEFAULT_SPCW_07_NM)))
//            .andExpect(jsonPath("$.[*].spcw08Amt").value(hasItem(sameNumber(DEFAULT_SPCW_08_AMT))))
//            .andExpect(jsonPath("$.[*].spcw08Nm").value(hasItem(DEFAULT_SPCW_08_NM)))
//            .andExpect(jsonPath("$.[*].spcw09Amt").value(hasItem(sameNumber(DEFAULT_SPCW_09_AMT))))
//            .andExpect(jsonPath("$.[*].spcw09Nm").value(hasItem(DEFAULT_SPCW_09_NM)))
//            .andExpect(jsonPath("$.[*].spcw10Amt").value(hasItem(sameNumber(DEFAULT_SPCW_10_AMT))))
//            .andExpect(jsonPath("$.[*].spcw10Nm").value(hasItem(DEFAULT_SPCW_10_NM)))
//            .andExpect(jsonPath("$.[*].spcw11Amt").value(hasItem(sameNumber(DEFAULT_SPCW_11_AMT))))
//            .andExpect(jsonPath("$.[*].spcw11Nm").value(hasItem(DEFAULT_SPCW_11_NM)))
//            .andExpect(jsonPath("$.[*].spcw12Amt").value(hasItem(sameNumber(DEFAULT_SPCW_12_AMT))))
//            .andExpect(jsonPath("$.[*].spcw12Nm").value(hasItem(DEFAULT_SPCW_12_NM)))
//            .andExpect(jsonPath("$.[*].job01Type").value(hasItem(DEFAULT_JOB_01_TYPE)))
//            .andExpect(jsonPath("$.[*].job02Type").value(hasItem(DEFAULT_JOB_02_TYPE)))
//            .andExpect(jsonPath("$.[*].job03Type").value(hasItem(DEFAULT_JOB_03_TYPE)))
//            .andExpect(jsonPath("$.[*].job04Type").value(hasItem(DEFAULT_JOB_04_TYPE)))
//            .andExpect(jsonPath("$.[*].job05Type").value(hasItem(DEFAULT_JOB_05_TYPE)))
//            .andExpect(jsonPath("$.[*].job06Type").value(hasItem(DEFAULT_JOB_06_TYPE)))
//            .andExpect(jsonPath("$.[*].job07Type").value(hasItem(DEFAULT_JOB_07_TYPE)))
//            .andExpect(jsonPath("$.[*].job08Type").value(hasItem(DEFAULT_JOB_08_TYPE)))
//            .andExpect(jsonPath("$.[*].job09Type").value(hasItem(DEFAULT_JOB_09_TYPE)))
//            .andExpect(jsonPath("$.[*].job10Type").value(hasItem(DEFAULT_JOB_10_TYPE)))
//            .andExpect(jsonPath("$.[*].job11Type").value(hasItem(DEFAULT_JOB_11_TYPE)))
//            .andExpect(jsonPath("$.[*].job12Type").value(hasItem(DEFAULT_JOB_12_TYPE)))
//            .andExpect(jsonPath("$.[*].job13Type").value(hasItem(DEFAULT_JOB_13_TYPE)))
//            .andExpect(jsonPath("$.[*].job14Type").value(hasItem(DEFAULT_JOB_14_TYPE)))
//            .andExpect(jsonPath("$.[*].semset").value(hasItem(DEFAULT_SEMSET)))
//            .andExpect(jsonPath("$.[*].isRcb").value(hasItem(DEFAULT_IS_RCB)))
//            .andExpect(jsonPath("$.[*].sorc1").value(hasItem(sameNumber(DEFAULT_SORC_1))))
//            .andExpect(jsonPath("$.[*].sorc1b").value(hasItem(sameNumber(DEFAULT_SORC_1_B))))
//            .andExpect(jsonPath("$.[*].sorc1c").value(hasItem(sameNumber(DEFAULT_SORC_1_C))))
//            .andExpect(jsonPath("$.[*].sorc6").value(hasItem(sameNumber(DEFAULT_SORC_6))))
//            .andExpect(jsonPath("$.[*].sorc3e").value(hasItem(sameNumber(DEFAULT_SORC_3_E))))
//            .andExpect(jsonPath("$.[*].sorc1d").value(hasItem(sameNumber(DEFAULT_SORC_1_D))))
//            .andExpect(jsonPath("$.[*].sorc2").value(hasItem(sameNumber(DEFAULT_SORC_2))))
//            .andExpect(jsonPath("$.[*].sorc3").value(hasItem(sameNumber(DEFAULT_SORC_3))))
//            .andExpect(jsonPath("$.[*].sorc3a").value(hasItem(sameNumber(DEFAULT_SORC_3_A))))
//            .andExpect(jsonPath("$.[*].sorc4").value(hasItem(sameNumber(DEFAULT_SORC_4))))
//            .andExpect(jsonPath("$.[*].sorc5").value(hasItem(sameNumber(DEFAULT_SORC_5))))
//            .andExpect(jsonPath("$.[*].sorc3b").value(hasItem(sameNumber(DEFAULT_SORC_3_B))))
//            .andExpect(jsonPath("$.[*].sorc3c").value(hasItem(sameNumber(DEFAULT_SORC_3_C))))
//            .andExpect(jsonPath("$.[*].sorc3d").value(hasItem(sameNumber(DEFAULT_SORC_3_D))))
//            .andExpect(jsonPath("$.[*].sorc3b2").value(hasItem(sameNumber(DEFAULT_SORC_3_B_2))))
//            .andExpect(jsonPath("$.[*].sorc3b3").value(hasItem(sameNumber(DEFAULT_SORC_3_B_3))))
//            .andExpect(jsonPath("$.[*].sorc3b4").value(hasItem(sameNumber(DEFAULT_SORC_3_B_4))))
//            .andExpect(jsonPath("$.[*].sorc3b5").value(hasItem(sameNumber(DEFAULT_SORC_3_B_5))))
//            .andExpect(jsonPath("$.[*].sorc3c1").value(hasItem(sameNumber(DEFAULT_SORC_3_C_1))))
//            .andExpect(jsonPath("$.[*].sorc3d1").value(hasItem(sameNumber(DEFAULT_SORC_3_D_1))))
//            .andExpect(jsonPath("$.[*].sorc3d3").value(hasItem(sameNumber(DEFAULT_SORC_3_D_3))))
//            .andExpect(jsonPath("$.[*].sorc3d4").value(hasItem(sameNumber(DEFAULT_SORC_3_D_4))))
//            .andExpect(jsonPath("$.[*].sorc7").value(hasItem(sameNumber(DEFAULT_SORC_7))))
//            .andExpect(jsonPath("$.[*].sorc7Use").value(hasItem(sameNumber(DEFAULT_SORC_7_USE))))
//            .andExpect(jsonPath("$.[*].sorc8").value(hasItem(sameNumber(DEFAULT_SORC_8))))
//            .andExpect(jsonPath("$.[*].sorc7Use2").value(hasItem(sameNumber(DEFAULT_SORC_7_USE_2))))
//            .andExpect(jsonPath("$.[*].sorc7Use3").value(hasItem(sameNumber(DEFAULT_SORC_7_USE_3))))
//            .andExpect(jsonPath("$.[*].openYn").value(hasItem(DEFAULT_OPEN_YN)))
//            .andExpect(jsonPath("$.[*].wKind").value(hasItem(DEFAULT_W_KIND)))
//            .andExpect(jsonPath("$.[*].wDays").value(hasItem(DEFAULT_W_DAYS)))
//            .andExpect(jsonPath("$.[*].wRemk").value(hasItem(DEFAULT_W_REMK)))
//            .andExpect(jsonPath("$.[*].bCode").value(hasItem(DEFAULT_B_CODE)))
//            .andExpect(jsonPath("$.[*].totAmt").value(hasItem(sameNumber(DEFAULT_TOT_AMT))))
//            .andExpect(jsonPath("$.[*].bdgt1").value(hasItem(sameNumber(DEFAULT_BDGT_1))))
//            .andExpect(jsonPath("$.[*].igtct").value(hasItem(sameNumber(DEFAULT_IGTCT))))
//            .andExpect(jsonPath("$.[*].othct").value(hasItem(sameNumber(DEFAULT_OTHCT))))
//            .andExpect(jsonPath("$.[*].rsvct").value(hasItem(sameNumber(DEFAULT_RSVCT))))
//            .andExpect(jsonPath("$.[*].poll").value(hasItem(sameNumber(DEFAULT_POLL))))
//            .andExpect(jsonPath("$.[*].pollNo").value(hasItem(DEFAULT_POLL_NO)))
//            .andExpect(jsonPath("$.[*].othMon").value(hasItem(sameNumber(DEFAULT_OTH_MON))))
//            .andExpect(jsonPath("$.[*].gyResn").value(hasItem(DEFAULT_GY_RESN)))
//            .andExpect(jsonPath("$.[*].drut").value(hasItem(DEFAULT_DRUT)))
//            .andExpect(jsonPath("$.[*].drct").value(hasItem(sameNumber(DEFAULT_DRCT))))
//            .andExpect(jsonPath("$.[*].dsrm").value(hasItem(DEFAULT_DSRM)))
//            .andExpect(jsonPath("$.[*].scrm").value(hasItem(DEFAULT_SCRM)))
//            .andExpect(jsonPath("$.[*].scrr").value(hasItem(DEFAULT_SCRR)))
//            .andExpect(jsonPath("$.[*].scnum").value(hasItem(DEFAULT_SCNUM)))
//            .andExpect(jsonPath("$.[*].pcmut").value(hasItem(DEFAULT_PCMUT)))
//            .andExpect(jsonPath("$.[*].ccnum").value(hasItem(DEFAULT_CCNUM)))
//            .andExpect(jsonPath("$.[*].issStartDate").value(hasItem(DEFAULT_ISS_START_DATE.toString())))
//            .andExpect(jsonPath("$.[*].issEndDate").value(hasItem(DEFAULT_ISS_END_DATE.toString())))
//            .andExpect(jsonPath("$.[*].issCost").value(hasItem(sameNumber(DEFAULT_ISS_COST))))
//            .andExpect(jsonPath("$.[*].issNo").value(hasItem(DEFAULT_ISS_NO)))
//            .andExpect(jsonPath("$.[*].issRmk").value(hasItem(DEFAULT_ISS_RMK)))
//            .andExpect(jsonPath("$.[*].pdsDate").value(hasItem(DEFAULT_PDS_DATE.toString())))
//            .andExpect(jsonPath("$.[*].dsDate").value(hasItem(DEFAULT_DS_DATE.toString())))
//            .andExpect(jsonPath("$.[*].adsDate").value(hasItem(DEFAULT_ADS_DATE.toString())))
//            .andExpect(jsonPath("$.[*].getlDate").value(hasItem(DEFAULT_GETL_DATE.toString())))
//            .andExpect(jsonPath("$.[*].agetlDate").value(hasItem(DEFAULT_AGETL_DATE.toString())))
//            .andExpect(jsonPath("$.[*].isSpay").value(hasItem(DEFAULT_IS_SPAY)))
//            .andExpect(jsonPath("$.[*].ipe").value(hasItem(DEFAULT_IPE)))
//            .andExpect(jsonPath("$.[*].ipeY").value(hasItem(DEFAULT_IPE_Y)))
//            .andExpect(jsonPath("$.[*].ipeN").value(hasItem(DEFAULT_IPE_N)))
//            .andExpect(jsonPath("$.[*].isNlic").value(hasItem(DEFAULT_IS_NLIC)))
//            .andExpect(jsonPath("$.[*].isDlic").value(hasItem(DEFAULT_IS_DLIC)))
//            .andExpect(jsonPath("$.[*].licNo").value(hasItem(DEFAULT_LIC_NO)))
//            .andExpect(jsonPath("$.[*].licDate").value(hasItem(DEFAULT_LIC_DATE.toString())))
//            .andExpect(jsonPath("$.[*].alicDate").value(hasItem(DEFAULT_ALIC_DATE.toString())))
//            .andExpect(jsonPath("$.[*].opDate").value(hasItem(DEFAULT_OP_DATE.toString())))
//            .andExpect(jsonPath("$.[*].aopDate").value(hasItem(DEFAULT_AOP_DATE.toString())))
//            .andExpect(jsonPath("$.[*].pckDate").value(hasItem(DEFAULT_PCK_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ackDate").value(hasItem(DEFAULT_ACK_DATE.toString())))
//            .andExpect(jsonPath("$.[*].pscDate").value(hasItem(DEFAULT_PSC_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ascDate").value(hasItem(DEFAULT_ASC_DATE.toString())))
//            .andExpect(jsonPath("$.[*].pctDate").value(hasItem(DEFAULT_PCT_DATE.toString())))
//            .andExpect(jsonPath("$.[*].actDate").value(hasItem(DEFAULT_ACT_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ctmeth").value(hasItem(DEFAULT_CTMETH)))
//            .andExpect(jsonPath("$.[*].actmth").value(hasItem(DEFAULT_ACTMTH)))
//            .andExpect(jsonPath("$.[*].specPurch").value(hasItem(DEFAULT_SPEC_PURCH)))
//            .andExpect(jsonPath("$.[*].isIntl").value(hasItem(DEFAULT_IS_INTL)))
//            .andExpect(jsonPath("$.[*].tctmth").value(hasItem(DEFAULT_TCTMTH)))
//            .andExpect(jsonPath("$.[*].bdgt2").value(hasItem(sameNumber(DEFAULT_BDGT_2))))
//            .andExpect(jsonPath("$.[*].bdgt3").value(hasItem(sameNumber(DEFAULT_BDGT_3))))
//            .andExpect(jsonPath("$.[*].mctmth").value(hasItem(DEFAULT_MCTMTH)))
//            .andExpect(jsonPath("$.[*].ccnt3").value(hasItem(DEFAULT_CCNT_3)))
//            .andExpect(jsonPath("$.[*].isPer").value(hasItem(DEFAULT_IS_PER)))
//            .andExpect(jsonPath("$.[*].ctSum").value(hasItem(sameNumber(DEFAULT_CT_SUM))))
//            .andExpect(jsonPath("$.[*].ctSumF").value(hasItem(sameNumber(DEFAULT_CT_SUM_F))))
//            .andExpect(jsonPath("$.[*].ctSumU").value(hasItem(sameNumber(DEFAULT_CT_SUM_U))))
//            .andExpect(jsonPath("$.[*].ctmo").value(hasItem(DEFAULT_CTMO)))
//            .andExpect(jsonPath("$.[*].ctSumD").value(hasItem(sameNumber(DEFAULT_CT_SUM_D))))
//            .andExpect(jsonPath("$.[*].cntid").value(hasItem(DEFAULT_CNTID)))
//            .andExpect(jsonPath("$.[*].pays").value(hasItem(sameNumber(DEFAULT_PAYS))))
//            .andExpect(jsonPath("$.[*].pmsSumT").value(hasItem(sameNumber(DEFAULT_PMS_SUM_T))))
//            .andExpect(jsonPath("$.[*].pmsType").value(hasItem(DEFAULT_PMS_TYPE)))
//            .andExpect(jsonPath("$.[*].paymth").value(hasItem(DEFAULT_PAYMTH)))
//            .andExpect(jsonPath("$.[*].foreignYn").value(hasItem(DEFAULT_FOREIGN_YN)))
//            .andExpect(jsonPath("$.[*].csDate").value(hasItem(DEFAULT_CS_DATE.toString())))
//            .andExpect(jsonPath("$.[*].asDate").value(hasItem(DEFAULT_AS_DATE.toString())))
//            .andExpect(jsonPath("$.[*].asuDate").value(hasItem(DEFAULT_ASU_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ceDate").value(hasItem(DEFAULT_CE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ceDateF").value(hasItem(DEFAULT_CE_DATE_F.toString())))
//            .andExpect(jsonPath("$.[*].aeDate").value(hasItem(DEFAULT_AE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].fcost2").value(hasItem(sameNumber(DEFAULT_FCOST_2))))
//            .andExpect(jsonPath("$.[*].fcsDate").value(hasItem(DEFAULT_FCS_DATE.toString())))
//            .andExpect(jsonPath("$.[*].afcsDate").value(hasItem(DEFAULT_AFCS_DATE.toString())))
//            .andExpect(jsonPath("$.[*].fcost").value(hasItem(sameNumber(DEFAULT_FCOST))))
//            .andExpect(jsonPath("$.[*].isCkp").value(hasItem(DEFAULT_IS_CKP)))
//            .andExpect(jsonPath("$.[*].isCkp2").value(hasItem(DEFAULT_IS_CKP_2)))
//            .andExpect(jsonPath("$.[*].isCkp3").value(hasItem(DEFAULT_IS_CKP_3)))
//            .andExpect(jsonPath("$.[*].ckpMoney").value(hasItem(sameNumber(DEFAULT_CKP_MONEY))))
//            .andExpect(jsonPath("$.[*].ckpUnit").value(hasItem(DEFAULT_CKP_UNIT)))
//            .andExpect(jsonPath("$.[*].ckpDate").value(hasItem(DEFAULT_CKP_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ckpCnt").value(hasItem(sameNumber(DEFAULT_CKP_CNT))))
//            .andExpect(jsonPath("$.[*].ckpNum").value(hasItem(DEFAULT_CKP_NUM)))
//            .andExpect(jsonPath("$.[*].ckaUnit").value(hasItem(DEFAULT_CKA_UNIT)))
//            .andExpect(jsonPath("$.[*].ckaDate").value(hasItem(DEFAULT_CKA_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ckaCnt").value(hasItem(sameNumber(DEFAULT_CKA_CNT))))
//            .andExpect(jsonPath("$.[*].ckaNum").value(hasItem(DEFAULT_CKA_NUM)))
//            .andExpect(jsonPath("$.[*].ckdUnit").value(hasItem(DEFAULT_CKD_UNIT)))
//            .andExpect(jsonPath("$.[*].ckdDate").value(hasItem(DEFAULT_CKD_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ckdCnt").value(hasItem(sameNumber(DEFAULT_CKD_CNT))))
//            .andExpect(jsonPath("$.[*].ckdNum").value(hasItem(DEFAULT_CKD_NUM)))
//            .andExpect(jsonPath("$.[*].workMemo").value(hasItem(DEFAULT_WORK_MEMO)))
//            .andExpect(jsonPath("$.[*].hotLine").value(hasItem(DEFAULT_HOT_LINE)))
//            .andExpect(jsonPath("$.[*].lnkCode2").value(hasItem(DEFAULT_LNK_CODE_2)))
//            .andExpect(jsonPath("$.[*].lnkCode3").value(hasItem(DEFAULT_LNK_CODE_3)))
//            .andExpect(jsonPath("$.[*].lnkNo2").value(hasItem(DEFAULT_LNK_NO_2)))
//            .andExpect(jsonPath("$.[*].ccnt1").value(hasItem(DEFAULT_CCNT_1)))
//            .andExpect(jsonPath("$.[*].typeCode2").value(hasItem(DEFAULT_TYPE_CODE_2)))
//            .andExpect(jsonPath("$.[*].deptName").value(hasItem(DEFAULT_DEPT_NAME)))
//            .andExpect(jsonPath("$.[*].aopCnt").value(hasItem(DEFAULT_AOP_CNT)))
//            .andExpect(jsonPath("$.[*].uniqueKey").value(hasItem(DEFAULT_UNIQUE_KEY)))
//            .andExpect(jsonPath("$.[*].isBidstr").value(hasItem(DEFAULT_IS_BIDSTR)))
//            .andExpect(jsonPath("$.[*].isEngineer").value(hasItem(DEFAULT_IS_ENGINEER)))
//            .andExpect(jsonPath("$.[*].name0").value(hasItem(DEFAULT_NAME_0)))
//            .andExpect(jsonPath("$.[*].ctSum0").value(hasItem(sameNumber(DEFAULT_CT_SUM_0))))
//            .andExpect(jsonPath("$.[*].bdgt10").value(hasItem(sameNumber(DEFAULT_BDGT_10))))
//            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
//            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
//            .andExpect(jsonPath("$.[*].batch").value(hasItem(DEFAULT_BATCH)))
//            .andExpect(jsonPath("$.[*].nocntDay").value(hasItem(DEFAULT_NOCNT_DAY)))
//            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
//            .andExpect(jsonPath("$.[*].aokDate").value(hasItem(DEFAULT_AOK_DATE.toString())))
//            .andExpect(jsonPath("$.[*].fcost3").value(hasItem(sameNumber(DEFAULT_FCOST_3))))
//            .andExpect(jsonPath("$.[*].finWays").value(hasItem(DEFAULT_FIN_WAYS)))
//            .andExpect(jsonPath("$.[*].aworkDay").value(hasItem(DEFAULT_AWORK_DAY)))
//            .andExpect(jsonPath("$.[*].fastDay").value(hasItem(DEFAULT_FAST_DAY)))
//            .andExpect(jsonPath("$.[*].overDay").value(hasItem(DEFAULT_OVER_DAY)))
//            .andExpect(jsonPath("$.[*].overDayY").value(hasItem(DEFAULT_OVER_DAY_Y)))
//            .andExpect(jsonPath("$.[*].overFee").value(hasItem(sameNumber(DEFAULT_OVER_FEE))))
//            .andExpect(jsonPath("$.[*].otherFee").value(hasItem(sameNumber(DEFAULT_OTHER_FEE))))
//            .andExpect(jsonPath("$.[*].punshFee").value(hasItem(sameNumber(DEFAULT_PUNSH_FEE))))
//            .andExpect(jsonPath("$.[*].tm2x").value(hasItem(DEFAULT_TM_2_X)))
//            .andExpect(jsonPath("$.[*].tm2y").value(hasItem(DEFAULT_TM_2_Y)))
//            .andExpect(jsonPath("$.[*].tm2x2").value(hasItem(DEFAULT_TM_2_X_2)))
//            .andExpect(jsonPath("$.[*].tm2y2").value(hasItem(DEFAULT_TM_2_Y_2)))
//            .andExpect(jsonPath("$.[*].tm2x3").value(hasItem(DEFAULT_TM_2_X_3)))
//            .andExpect(jsonPath("$.[*].tm2y3").value(hasItem(DEFAULT_TM_2_Y_3)))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].dsts").value(hasItem(DEFAULT_DSTS)))
//            .andExpect(jsonPath("$.[*].confOut").value(hasItem(sameNumber(DEFAULT_CONF_OUT))))
//            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
//            .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN)))
//            .andExpect(jsonPath("$.[*].srut").value(hasItem(DEFAULT_SRUT)))
//            .andExpect(jsonPath("$.[*].anoct").value(hasItem(sameNumber(DEFAULT_ANOCT))))
//            .andExpect(jsonPath("$.[*].cepdNo").value(hasItem(DEFAULT_CEPD_NO)))
//            .andExpect(jsonPath("$.[*].subNo").value(hasItem(DEFAULT_SUB_NO)))
//            .andExpect(jsonPath("$.[*].scnt").value(hasItem(DEFAULT_SCNT)))
//            .andExpect(jsonPath("$.[*].dsdm").value(hasItem(DEFAULT_DSDM)))
//            .andExpect(jsonPath("$.[*].preson").value(hasItem(DEFAULT_PRESON)))
//            .andExpect(jsonPath("$.[*].psignDate").value(hasItem(DEFAULT_PSIGN_DATE.toString())))
//            .andExpect(jsonPath("$.[*].asignDate").value(hasItem(DEFAULT_ASIGN_DATE.toString())))
//            .andExpect(jsonPath("$.[*].trDate").value(hasItem(DEFAULT_TR_DATE.toString())))
//            .andExpect(jsonPath("$.[*].confIn").value(hasItem(sameNumber(DEFAULT_CONF_IN))))
//            .andExpect(jsonPath("$.[*].uKey").value(hasItem(DEFAULT_U_KEY)))
//            .andExpect(jsonPath("$.[*].tpcoth").value(hasItem(DEFAULT_TPCOTH)))
//            .andExpect(jsonPath("$.[*].pcmct").value(hasItem(sameNumber(DEFAULT_PCMCT))))
//            .andExpect(jsonPath("$.[*].wkutOld").value(hasItem(DEFAULT_WKUT_OLD)))
//            .andExpect(jsonPath("$.[*].pccMemo").value(hasItem(DEFAULT_PCC_MEMO)))
//            .andExpect(jsonPath("$.[*].bgbSumt").value(hasItem(sameNumber(DEFAULT_BGB_SUMT))))
//            .andExpect(jsonPath("$.[*].bgbDate").value(hasItem(DEFAULT_BGB_DATE)))
//            .andExpect(jsonPath("$.[*].mainItem").value(hasItem(DEFAULT_MAIN_ITEM)))
//            .andExpect(jsonPath("$.[*].mDesc").value(hasItem(DEFAULT_M_DESC)))
//            .andExpect(jsonPath("$.[*].mRmk").value(hasItem(DEFAULT_M_RMK)))
//            .andExpect(jsonPath("$.[*].pmrSumt").value(hasItem(sameNumber(DEFAULT_PMR_SUMT))))
//            .andExpect(jsonPath("$.[*].pmrDate").value(hasItem(DEFAULT_PMR_DATE.toString())))
//            .andExpect(jsonPath("$.[*].pmrResm").value(hasItem(DEFAULT_PMR_RESM)));
//    }

//    @Test
//    @Transactional
//    void getBidProject() throws Exception {
//        // Initialize the database
//        bidProjectRepository.saveAndFlush(bidProject);
//
//        // Get the bidProject
//        restBidProjectMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidProject.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidProject.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
//            .andExpect(jsonPath("$.isAdd92").value(DEFAULT_IS_ADD_92))
//            .andExpect(jsonPath("$.pntCtrl").value(DEFAULT_PNT_CTRL))
//            .andExpect(jsonPath("$.typeCode").value(DEFAULT_TYPE_CODE))
//            .andExpect(jsonPath("$.isUnion").value(DEFAULT_IS_UNION))
//            .andExpect(jsonPath("$.placeCode").value(DEFAULT_PLACE_CODE))
//            .andExpect(jsonPath("$.place").value(DEFAULT_PLACE))
//            .andExpect(jsonPath("$.bosCode").value(DEFAULT_BOS_CODE))
//            .andExpect(jsonPath("$.depCode").value(DEFAULT_DEP_CODE))
//            .andExpect(jsonPath("$.isBg").value(DEFAULT_IS_BG))
//            .andExpect(jsonPath("$.plnCode").value(DEFAULT_PLN_CODE))
//            .andExpect(jsonPath("$.plnNo").value(DEFAULT_PLN_NO))
//            .andExpect(jsonPath("$.plnUnicode").value(DEFAULT_PLN_UNICODE))
//            .andExpect(jsonPath("$.bos2Code").value(DEFAULT_BOS_2_CODE))
//            .andExpect(jsonPath("$.bos2Rate").value(sameNumber(DEFAULT_BOS_2_RATE)))
//            .andExpect(jsonPath("$.bos2bCode").value(DEFAULT_BOS_2_B_CODE))
//            .andExpect(jsonPath("$.bos2bRate").value(sameNumber(DEFAULT_BOS_2_B_RATE)))
//            .andExpect(jsonPath("$.bos2cCode").value(DEFAULT_BOS_2_C_CODE))
//            .andExpect(jsonPath("$.bos2cRate").value(sameNumber(DEFAULT_BOS_2_C_RATE)))
//            .andExpect(jsonPath("$.bos2dCode").value(DEFAULT_BOS_2_D_CODE))
//            .andExpect(jsonPath("$.bos2dRate").value(sameNumber(DEFAULT_BOS_2_D_RATE)))
//            .andExpect(jsonPath("$.lnkCode").value(DEFAULT_LNK_CODE))
//            .andExpect(jsonPath("$.ccnt2").value(DEFAULT_CCNT_2))
//            .andExpect(jsonPath("$.man").value(DEFAULT_MAN))
//            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
//            .andExpect(jsonPath("$.eMail").value(DEFAULT_E_MAIL))
//            .andExpect(jsonPath("$.wrate").value(sameNumber(DEFAULT_WRATE)))
//            .andExpect(jsonPath("$.contain").value(DEFAULT_CONTAIN))
//            .andExpect(jsonPath("$.pfunc").value(DEFAULT_PFUNC))
//            .andExpect(jsonPath("$.benf").value(DEFAULT_BENF))
//            .andExpect(jsonPath("$.pstand").value(DEFAULT_PSTAND))
//            .andExpect(jsonPath("$.pqty").value(DEFAULT_PQTY))
//            .andExpect(jsonPath("$.untnd").value(DEFAULT_UNTND))
//            .andExpect(jsonPath("$.isSpcw").value(DEFAULT_IS_SPCW))
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
//            .andExpect(jsonPath("$.spcw01Amt").value(sameNumber(DEFAULT_SPCW_01_AMT)))
//            .andExpect(jsonPath("$.spcw01Nm").value(DEFAULT_SPCW_01_NM))
//            .andExpect(jsonPath("$.spcw02Amt").value(sameNumber(DEFAULT_SPCW_02_AMT)))
//            .andExpect(jsonPath("$.spcw02Nm").value(DEFAULT_SPCW_02_NM))
//            .andExpect(jsonPath("$.spcw03Amt").value(sameNumber(DEFAULT_SPCW_03_AMT)))
//            .andExpect(jsonPath("$.spcw03Nm").value(DEFAULT_SPCW_03_NM))
//            .andExpect(jsonPath("$.spcw04Amt").value(sameNumber(DEFAULT_SPCW_04_AMT)))
//            .andExpect(jsonPath("$.spcw04Nm").value(DEFAULT_SPCW_04_NM))
//            .andExpect(jsonPath("$.spcw05Amt").value(sameNumber(DEFAULT_SPCW_05_AMT)))
//            .andExpect(jsonPath("$.spcw05Nm").value(DEFAULT_SPCW_05_NM))
//            .andExpect(jsonPath("$.spcw06Amt").value(sameNumber(DEFAULT_SPCW_06_AMT)))
//            .andExpect(jsonPath("$.spcw06Nm").value(DEFAULT_SPCW_06_NM))
//            .andExpect(jsonPath("$.spcw07Amt").value(sameNumber(DEFAULT_SPCW_07_AMT)))
//            .andExpect(jsonPath("$.spcw07Nm").value(DEFAULT_SPCW_07_NM))
//            .andExpect(jsonPath("$.spcw08Amt").value(sameNumber(DEFAULT_SPCW_08_AMT)))
//            .andExpect(jsonPath("$.spcw08Nm").value(DEFAULT_SPCW_08_NM))
//            .andExpect(jsonPath("$.spcw09Amt").value(sameNumber(DEFAULT_SPCW_09_AMT)))
//            .andExpect(jsonPath("$.spcw09Nm").value(DEFAULT_SPCW_09_NM))
//            .andExpect(jsonPath("$.spcw10Amt").value(sameNumber(DEFAULT_SPCW_10_AMT)))
//            .andExpect(jsonPath("$.spcw10Nm").value(DEFAULT_SPCW_10_NM))
//            .andExpect(jsonPath("$.spcw11Amt").value(sameNumber(DEFAULT_SPCW_11_AMT)))
//            .andExpect(jsonPath("$.spcw11Nm").value(DEFAULT_SPCW_11_NM))
//            .andExpect(jsonPath("$.spcw12Amt").value(sameNumber(DEFAULT_SPCW_12_AMT)))
//            .andExpect(jsonPath("$.spcw12Nm").value(DEFAULT_SPCW_12_NM))
//            .andExpect(jsonPath("$.job01Type").value(DEFAULT_JOB_01_TYPE))
//            .andExpect(jsonPath("$.job02Type").value(DEFAULT_JOB_02_TYPE))
//            .andExpect(jsonPath("$.job03Type").value(DEFAULT_JOB_03_TYPE))
//            .andExpect(jsonPath("$.job04Type").value(DEFAULT_JOB_04_TYPE))
//            .andExpect(jsonPath("$.job05Type").value(DEFAULT_JOB_05_TYPE))
//            .andExpect(jsonPath("$.job06Type").value(DEFAULT_JOB_06_TYPE))
//            .andExpect(jsonPath("$.job07Type").value(DEFAULT_JOB_07_TYPE))
//            .andExpect(jsonPath("$.job08Type").value(DEFAULT_JOB_08_TYPE))
//            .andExpect(jsonPath("$.job09Type").value(DEFAULT_JOB_09_TYPE))
//            .andExpect(jsonPath("$.job10Type").value(DEFAULT_JOB_10_TYPE))
//            .andExpect(jsonPath("$.job11Type").value(DEFAULT_JOB_11_TYPE))
//            .andExpect(jsonPath("$.job12Type").value(DEFAULT_JOB_12_TYPE))
//            .andExpect(jsonPath("$.job13Type").value(DEFAULT_JOB_13_TYPE))
//            .andExpect(jsonPath("$.job14Type").value(DEFAULT_JOB_14_TYPE))
//            .andExpect(jsonPath("$.semset").value(DEFAULT_SEMSET))
//            .andExpect(jsonPath("$.isRcb").value(DEFAULT_IS_RCB))
//            .andExpect(jsonPath("$.sorc1").value(sameNumber(DEFAULT_SORC_1)))
//            .andExpect(jsonPath("$.sorc1b").value(sameNumber(DEFAULT_SORC_1_B)))
//            .andExpect(jsonPath("$.sorc1c").value(sameNumber(DEFAULT_SORC_1_C)))
//            .andExpect(jsonPath("$.sorc6").value(sameNumber(DEFAULT_SORC_6)))
//            .andExpect(jsonPath("$.sorc3e").value(sameNumber(DEFAULT_SORC_3_E)))
//            .andExpect(jsonPath("$.sorc1d").value(sameNumber(DEFAULT_SORC_1_D)))
//            .andExpect(jsonPath("$.sorc2").value(sameNumber(DEFAULT_SORC_2)))
//            .andExpect(jsonPath("$.sorc3").value(sameNumber(DEFAULT_SORC_3)))
//            .andExpect(jsonPath("$.sorc3a").value(sameNumber(DEFAULT_SORC_3_A)))
//            .andExpect(jsonPath("$.sorc4").value(sameNumber(DEFAULT_SORC_4)))
//            .andExpect(jsonPath("$.sorc5").value(sameNumber(DEFAULT_SORC_5)))
//            .andExpect(jsonPath("$.sorc3b").value(sameNumber(DEFAULT_SORC_3_B)))
//            .andExpect(jsonPath("$.sorc3c").value(sameNumber(DEFAULT_SORC_3_C)))
//            .andExpect(jsonPath("$.sorc3d").value(sameNumber(DEFAULT_SORC_3_D)))
//            .andExpect(jsonPath("$.sorc3b2").value(sameNumber(DEFAULT_SORC_3_B_2)))
//            .andExpect(jsonPath("$.sorc3b3").value(sameNumber(DEFAULT_SORC_3_B_3)))
//            .andExpect(jsonPath("$.sorc3b4").value(sameNumber(DEFAULT_SORC_3_B_4)))
//            .andExpect(jsonPath("$.sorc3b5").value(sameNumber(DEFAULT_SORC_3_B_5)))
//            .andExpect(jsonPath("$.sorc3c1").value(sameNumber(DEFAULT_SORC_3_C_1)))
//            .andExpect(jsonPath("$.sorc3d1").value(sameNumber(DEFAULT_SORC_3_D_1)))
//            .andExpect(jsonPath("$.sorc3d3").value(sameNumber(DEFAULT_SORC_3_D_3)))
//            .andExpect(jsonPath("$.sorc3d4").value(sameNumber(DEFAULT_SORC_3_D_4)))
//            .andExpect(jsonPath("$.sorc7").value(sameNumber(DEFAULT_SORC_7)))
//            .andExpect(jsonPath("$.sorc7Use").value(sameNumber(DEFAULT_SORC_7_USE)))
//            .andExpect(jsonPath("$.sorc8").value(sameNumber(DEFAULT_SORC_8)))
//            .andExpect(jsonPath("$.sorc7Use2").value(sameNumber(DEFAULT_SORC_7_USE_2)))
//            .andExpect(jsonPath("$.sorc7Use3").value(sameNumber(DEFAULT_SORC_7_USE_3)))
//            .andExpect(jsonPath("$.openYn").value(DEFAULT_OPEN_YN))
//            .andExpect(jsonPath("$.wKind").value(DEFAULT_W_KIND))
//            .andExpect(jsonPath("$.wDays").value(DEFAULT_W_DAYS))
//            .andExpect(jsonPath("$.wRemk").value(DEFAULT_W_REMK))
//            .andExpect(jsonPath("$.bCode").value(DEFAULT_B_CODE))
//            .andExpect(jsonPath("$.totAmt").value(sameNumber(DEFAULT_TOT_AMT)))
//            .andExpect(jsonPath("$.bdgt1").value(sameNumber(DEFAULT_BDGT_1)))
//            .andExpect(jsonPath("$.igtct").value(sameNumber(DEFAULT_IGTCT)))
//            .andExpect(jsonPath("$.othct").value(sameNumber(DEFAULT_OTHCT)))
//            .andExpect(jsonPath("$.rsvct").value(sameNumber(DEFAULT_RSVCT)))
//            .andExpect(jsonPath("$.poll").value(sameNumber(DEFAULT_POLL)))
//            .andExpect(jsonPath("$.pollNo").value(DEFAULT_POLL_NO))
//            .andExpect(jsonPath("$.othMon").value(sameNumber(DEFAULT_OTH_MON)))
//            .andExpect(jsonPath("$.gyResn").value(DEFAULT_GY_RESN))
//            .andExpect(jsonPath("$.drut").value(DEFAULT_DRUT))
//            .andExpect(jsonPath("$.drct").value(sameNumber(DEFAULT_DRCT)))
//            .andExpect(jsonPath("$.dsrm").value(DEFAULT_DSRM))
//            .andExpect(jsonPath("$.scrm").value(DEFAULT_SCRM))
//            .andExpect(jsonPath("$.scrr").value(DEFAULT_SCRR))
//            .andExpect(jsonPath("$.scnum").value(DEFAULT_SCNUM))
//            .andExpect(jsonPath("$.pcmut").value(DEFAULT_PCMUT))
//            .andExpect(jsonPath("$.ccnum").value(DEFAULT_CCNUM))
//            .andExpect(jsonPath("$.issStartDate").value(DEFAULT_ISS_START_DATE.toString()))
//            .andExpect(jsonPath("$.issEndDate").value(DEFAULT_ISS_END_DATE.toString()))
//            .andExpect(jsonPath("$.issCost").value(sameNumber(DEFAULT_ISS_COST)))
//            .andExpect(jsonPath("$.issNo").value(DEFAULT_ISS_NO))
//            .andExpect(jsonPath("$.issRmk").value(DEFAULT_ISS_RMK))
//            .andExpect(jsonPath("$.pdsDate").value(DEFAULT_PDS_DATE.toString()))
//            .andExpect(jsonPath("$.dsDate").value(DEFAULT_DS_DATE.toString()))
//            .andExpect(jsonPath("$.adsDate").value(DEFAULT_ADS_DATE.toString()))
//            .andExpect(jsonPath("$.getlDate").value(DEFAULT_GETL_DATE.toString()))
//            .andExpect(jsonPath("$.agetlDate").value(DEFAULT_AGETL_DATE.toString()))
//            .andExpect(jsonPath("$.isSpay").value(DEFAULT_IS_SPAY))
//            .andExpect(jsonPath("$.ipe").value(DEFAULT_IPE))
//            .andExpect(jsonPath("$.ipeY").value(DEFAULT_IPE_Y))
//            .andExpect(jsonPath("$.ipeN").value(DEFAULT_IPE_N))
//            .andExpect(jsonPath("$.isNlic").value(DEFAULT_IS_NLIC))
//            .andExpect(jsonPath("$.isDlic").value(DEFAULT_IS_DLIC))
//            .andExpect(jsonPath("$.licNo").value(DEFAULT_LIC_NO))
//            .andExpect(jsonPath("$.licDate").value(DEFAULT_LIC_DATE.toString()))
//            .andExpect(jsonPath("$.alicDate").value(DEFAULT_ALIC_DATE.toString()))
//            .andExpect(jsonPath("$.opDate").value(DEFAULT_OP_DATE.toString()))
//            .andExpect(jsonPath("$.aopDate").value(DEFAULT_AOP_DATE.toString()))
//            .andExpect(jsonPath("$.pckDate").value(DEFAULT_PCK_DATE.toString()))
//            .andExpect(jsonPath("$.ackDate").value(DEFAULT_ACK_DATE.toString()))
//            .andExpect(jsonPath("$.pscDate").value(DEFAULT_PSC_DATE.toString()))
//            .andExpect(jsonPath("$.ascDate").value(DEFAULT_ASC_DATE.toString()))
//            .andExpect(jsonPath("$.pctDate").value(DEFAULT_PCT_DATE.toString()))
//            .andExpect(jsonPath("$.actDate").value(DEFAULT_ACT_DATE.toString()))
//            .andExpect(jsonPath("$.ctmeth").value(DEFAULT_CTMETH))
//            .andExpect(jsonPath("$.actmth").value(DEFAULT_ACTMTH))
//            .andExpect(jsonPath("$.specPurch").value(DEFAULT_SPEC_PURCH))
//            .andExpect(jsonPath("$.isIntl").value(DEFAULT_IS_INTL))
//            .andExpect(jsonPath("$.tctmth").value(DEFAULT_TCTMTH))
//            .andExpect(jsonPath("$.bdgt2").value(sameNumber(DEFAULT_BDGT_2)))
//            .andExpect(jsonPath("$.bdgt3").value(sameNumber(DEFAULT_BDGT_3)))
//            .andExpect(jsonPath("$.mctmth").value(DEFAULT_MCTMTH))
//            .andExpect(jsonPath("$.ccnt3").value(DEFAULT_CCNT_3))
//            .andExpect(jsonPath("$.isPer").value(DEFAULT_IS_PER))
//            .andExpect(jsonPath("$.ctSum").value(sameNumber(DEFAULT_CT_SUM)))
//            .andExpect(jsonPath("$.ctSumF").value(sameNumber(DEFAULT_CT_SUM_F)))
//            .andExpect(jsonPath("$.ctSumU").value(sameNumber(DEFAULT_CT_SUM_U)))
//            .andExpect(jsonPath("$.ctmo").value(DEFAULT_CTMO))
//            .andExpect(jsonPath("$.ctSumD").value(sameNumber(DEFAULT_CT_SUM_D)))
//            .andExpect(jsonPath("$.cntid").value(DEFAULT_CNTID))
//            .andExpect(jsonPath("$.pays").value(sameNumber(DEFAULT_PAYS)))
//            .andExpect(jsonPath("$.pmsSumT").value(sameNumber(DEFAULT_PMS_SUM_T)))
//            .andExpect(jsonPath("$.pmsType").value(DEFAULT_PMS_TYPE))
//            .andExpect(jsonPath("$.paymth").value(DEFAULT_PAYMTH))
//            .andExpect(jsonPath("$.foreignYn").value(DEFAULT_FOREIGN_YN))
//            .andExpect(jsonPath("$.csDate").value(DEFAULT_CS_DATE.toString()))
//            .andExpect(jsonPath("$.asDate").value(DEFAULT_AS_DATE.toString()))
//            .andExpect(jsonPath("$.asuDate").value(DEFAULT_ASU_DATE.toString()))
//            .andExpect(jsonPath("$.ceDate").value(DEFAULT_CE_DATE.toString()))
//            .andExpect(jsonPath("$.ceDateF").value(DEFAULT_CE_DATE_F.toString()))
//            .andExpect(jsonPath("$.aeDate").value(DEFAULT_AE_DATE.toString()))
//            .andExpect(jsonPath("$.fcost2").value(sameNumber(DEFAULT_FCOST_2)))
//            .andExpect(jsonPath("$.fcsDate").value(DEFAULT_FCS_DATE.toString()))
//            .andExpect(jsonPath("$.afcsDate").value(DEFAULT_AFCS_DATE.toString()))
//            .andExpect(jsonPath("$.fcost").value(sameNumber(DEFAULT_FCOST)))
//            .andExpect(jsonPath("$.isCkp").value(DEFAULT_IS_CKP))
//            .andExpect(jsonPath("$.isCkp2").value(DEFAULT_IS_CKP_2))
//            .andExpect(jsonPath("$.isCkp3").value(DEFAULT_IS_CKP_3))
//            .andExpect(jsonPath("$.ckpMoney").value(sameNumber(DEFAULT_CKP_MONEY)))
//            .andExpect(jsonPath("$.ckpUnit").value(DEFAULT_CKP_UNIT))
//            .andExpect(jsonPath("$.ckpDate").value(DEFAULT_CKP_DATE.toString()))
//            .andExpect(jsonPath("$.ckpCnt").value(sameNumber(DEFAULT_CKP_CNT)))
//            .andExpect(jsonPath("$.ckpNum").value(DEFAULT_CKP_NUM))
//            .andExpect(jsonPath("$.ckaUnit").value(DEFAULT_CKA_UNIT))
//            .andExpect(jsonPath("$.ckaDate").value(DEFAULT_CKA_DATE.toString()))
//            .andExpect(jsonPath("$.ckaCnt").value(sameNumber(DEFAULT_CKA_CNT)))
//            .andExpect(jsonPath("$.ckaNum").value(DEFAULT_CKA_NUM))
//            .andExpect(jsonPath("$.ckdUnit").value(DEFAULT_CKD_UNIT))
//            .andExpect(jsonPath("$.ckdDate").value(DEFAULT_CKD_DATE.toString()))
//            .andExpect(jsonPath("$.ckdCnt").value(sameNumber(DEFAULT_CKD_CNT)))
//            .andExpect(jsonPath("$.ckdNum").value(DEFAULT_CKD_NUM))
//            .andExpect(jsonPath("$.workMemo").value(DEFAULT_WORK_MEMO))
//            .andExpect(jsonPath("$.hotLine").value(DEFAULT_HOT_LINE))
//            .andExpect(jsonPath("$.lnkCode2").value(DEFAULT_LNK_CODE_2))
//            .andExpect(jsonPath("$.lnkCode3").value(DEFAULT_LNK_CODE_3))
//            .andExpect(jsonPath("$.lnkNo2").value(DEFAULT_LNK_NO_2))
//            .andExpect(jsonPath("$.ccnt1").value(DEFAULT_CCNT_1))
//            .andExpect(jsonPath("$.typeCode2").value(DEFAULT_TYPE_CODE_2))
//            .andExpect(jsonPath("$.deptName").value(DEFAULT_DEPT_NAME))
//            .andExpect(jsonPath("$.aopCnt").value(DEFAULT_AOP_CNT))
//            .andExpect(jsonPath("$.uniqueKey").value(DEFAULT_UNIQUE_KEY))
//            .andExpect(jsonPath("$.isBidstr").value(DEFAULT_IS_BIDSTR))
//            .andExpect(jsonPath("$.isEngineer").value(DEFAULT_IS_ENGINEER))
//            .andExpect(jsonPath("$.name0").value(DEFAULT_NAME_0))
//            .andExpect(jsonPath("$.ctSum0").value(sameNumber(DEFAULT_CT_SUM_0)))
//            .andExpect(jsonPath("$.bdgt10").value(sameNumber(DEFAULT_BDGT_10)))
//            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
//            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
//            .andExpect(jsonPath("$.batch").value(DEFAULT_BATCH))
//            .andExpect(jsonPath("$.nocntDay").value(DEFAULT_NOCNT_DAY))
//            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
//            .andExpect(jsonPath("$.aokDate").value(DEFAULT_AOK_DATE.toString()))
//            .andExpect(jsonPath("$.fcost3").value(sameNumber(DEFAULT_FCOST_3)))
//            .andExpect(jsonPath("$.finWays").value(DEFAULT_FIN_WAYS))
//            .andExpect(jsonPath("$.aworkDay").value(DEFAULT_AWORK_DAY))
//            .andExpect(jsonPath("$.fastDay").value(DEFAULT_FAST_DAY))
//            .andExpect(jsonPath("$.overDay").value(DEFAULT_OVER_DAY))
//            .andExpect(jsonPath("$.overDayY").value(DEFAULT_OVER_DAY_Y))
//            .andExpect(jsonPath("$.overFee").value(sameNumber(DEFAULT_OVER_FEE)))
//            .andExpect(jsonPath("$.otherFee").value(sameNumber(DEFAULT_OTHER_FEE)))
//            .andExpect(jsonPath("$.punshFee").value(sameNumber(DEFAULT_PUNSH_FEE)))
//            .andExpect(jsonPath("$.tm2x").value(DEFAULT_TM_2_X))
//            .andExpect(jsonPath("$.tm2y").value(DEFAULT_TM_2_Y))
//            .andExpect(jsonPath("$.tm2x2").value(DEFAULT_TM_2_X_2))
//            .andExpect(jsonPath("$.tm2y2").value(DEFAULT_TM_2_Y_2))
//            .andExpect(jsonPath("$.tm2x3").value(DEFAULT_TM_2_X_3))
//            .andExpect(jsonPath("$.tm2y3").value(DEFAULT_TM_2_Y_3))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.dsts").value(DEFAULT_DSTS))
//            .andExpect(jsonPath("$.confOut").value(sameNumber(DEFAULT_CONF_OUT)))
//            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
//            .andExpect(jsonPath("$.can").value(DEFAULT_CAN))
//            .andExpect(jsonPath("$.srut").value(DEFAULT_SRUT))
//            .andExpect(jsonPath("$.anoct").value(sameNumber(DEFAULT_ANOCT)))
//            .andExpect(jsonPath("$.cepdNo").value(DEFAULT_CEPD_NO))
//            .andExpect(jsonPath("$.subNo").value(DEFAULT_SUB_NO))
//            .andExpect(jsonPath("$.scnt").value(DEFAULT_SCNT))
//            .andExpect(jsonPath("$.dsdm").value(DEFAULT_DSDM))
//            .andExpect(jsonPath("$.preson").value(DEFAULT_PRESON))
//            .andExpect(jsonPath("$.psignDate").value(DEFAULT_PSIGN_DATE.toString()))
//            .andExpect(jsonPath("$.asignDate").value(DEFAULT_ASIGN_DATE.toString()))
//            .andExpect(jsonPath("$.trDate").value(DEFAULT_TR_DATE.toString()))
//            .andExpect(jsonPath("$.confIn").value(sameNumber(DEFAULT_CONF_IN)))
//            .andExpect(jsonPath("$.uKey").value(DEFAULT_U_KEY))
//            .andExpect(jsonPath("$.tpcoth").value(DEFAULT_TPCOTH))
//            .andExpect(jsonPath("$.pcmct").value(sameNumber(DEFAULT_PCMCT)))
//            .andExpect(jsonPath("$.wkutOld").value(DEFAULT_WKUT_OLD))
//            .andExpect(jsonPath("$.pccMemo").value(DEFAULT_PCC_MEMO))
//            .andExpect(jsonPath("$.bgbSumt").value(sameNumber(DEFAULT_BGB_SUMT)))
//            .andExpect(jsonPath("$.bgbDate").value(DEFAULT_BGB_DATE))
//            .andExpect(jsonPath("$.mainItem").value(DEFAULT_MAIN_ITEM))
//            .andExpect(jsonPath("$.mDesc").value(DEFAULT_M_DESC))
//            .andExpect(jsonPath("$.mRmk").value(DEFAULT_M_RMK))
//            .andExpect(jsonPath("$.pmrSumt").value(sameNumber(DEFAULT_PMR_SUMT)))
//            .andExpect(jsonPath("$.pmrDate").value(DEFAULT_PMR_DATE.toString()))
//            .andExpect(jsonPath("$.pmrResm").value(DEFAULT_PMR_RESM));
//    }

    @Test
    @Transactional
    void getNonExistingBidProject() throws Exception {
        // Get the bidProject
        restBidProjectMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidProject() throws Exception {
        // Initialize the database
        bidProjectRepository.saveAndFlush(bidProject);

        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();

        // Update the bidProject
        BidProject updatedBidProject = bidProjectRepository.findById(bidProject.getId()).get();
        // Disconnect from session so that the updates on updatedBidProject are not directly saved in db
        em.detach(updatedBidProject);
        updatedBidProject
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .name(UPDATED_NAME)
            .isAdd92(UPDATED_IS_ADD_92)
            .pntCtrl(UPDATED_PNT_CTRL)
            .typeCode(UPDATED_TYPE_CODE)
            .isUnion(UPDATED_IS_UNION)
            .placeCode(UPDATED_PLACE_CODE)
            .place(UPDATED_PLACE)
            .bosCode(UPDATED_BOS_CODE)
            .depCode(UPDATED_DEP_CODE)
            .isBg(UPDATED_IS_BG)
            .plnCode(UPDATED_PLN_CODE)
            .plnNo(UPDATED_PLN_NO)
            .plnUnicode(UPDATED_PLN_UNICODE)
            .bos2Code(UPDATED_BOS_2_CODE)
            .bos2Rate(UPDATED_BOS_2_RATE)
            .bos2bCode(UPDATED_BOS_2_B_CODE)
            .bos2bRate(UPDATED_BOS_2_B_RATE)
            .bos2cCode(UPDATED_BOS_2_C_CODE)
            .bos2cRate(UPDATED_BOS_2_C_RATE)
            .bos2dCode(UPDATED_BOS_2_D_CODE)
            .bos2dRate(UPDATED_BOS_2_D_RATE)
            .lnkCode(UPDATED_LNK_CODE)
            .ccnt2(UPDATED_CCNT_2)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL)
            .eMail(UPDATED_E_MAIL)
            .wrate(UPDATED_WRATE)
            .contain(UPDATED_CONTAIN)
            .pfunc(UPDATED_PFUNC)
            .benf(UPDATED_BENF)
            .pstand(UPDATED_PSTAND)
            .pqty(UPDATED_PQTY)
            .untnd(UPDATED_UNTND)
            .isSpcw(UPDATED_IS_SPCW)
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
            .spcw01Amt(UPDATED_SPCW_01_AMT)
            .spcw01Nm(UPDATED_SPCW_01_NM)
            .spcw02Amt(UPDATED_SPCW_02_AMT)
            .spcw02Nm(UPDATED_SPCW_02_NM)
            .spcw03Amt(UPDATED_SPCW_03_AMT)
            .spcw03Nm(UPDATED_SPCW_03_NM)
            .spcw04Amt(UPDATED_SPCW_04_AMT)
            .spcw04Nm(UPDATED_SPCW_04_NM)
            .spcw05Amt(UPDATED_SPCW_05_AMT)
            .spcw05Nm(UPDATED_SPCW_05_NM)
            .spcw06Amt(UPDATED_SPCW_06_AMT)
            .spcw06Nm(UPDATED_SPCW_06_NM)
            .spcw07Amt(UPDATED_SPCW_07_AMT)
            .spcw07Nm(UPDATED_SPCW_07_NM)
            .spcw08Amt(UPDATED_SPCW_08_AMT)
            .spcw08Nm(UPDATED_SPCW_08_NM)
            .spcw09Amt(UPDATED_SPCW_09_AMT)
            .spcw09Nm(UPDATED_SPCW_09_NM)
            .spcw10Amt(UPDATED_SPCW_10_AMT)
            .spcw10Nm(UPDATED_SPCW_10_NM)
            .spcw11Amt(UPDATED_SPCW_11_AMT)
            .spcw11Nm(UPDATED_SPCW_11_NM)
            .spcw12Amt(UPDATED_SPCW_12_AMT)
            .spcw12Nm(UPDATED_SPCW_12_NM)
            .job01Type(UPDATED_JOB_01_TYPE)
            .job02Type(UPDATED_JOB_02_TYPE)
            .job03Type(UPDATED_JOB_03_TYPE)
            .job04Type(UPDATED_JOB_04_TYPE)
            .job05Type(UPDATED_JOB_05_TYPE)
            .job06Type(UPDATED_JOB_06_TYPE)
            .job07Type(UPDATED_JOB_07_TYPE)
            .job08Type(UPDATED_JOB_08_TYPE)
            .job09Type(UPDATED_JOB_09_TYPE)
            .job10Type(UPDATED_JOB_10_TYPE)
            .job11Type(UPDATED_JOB_11_TYPE)
            .job12Type(UPDATED_JOB_12_TYPE)
            .job13Type(UPDATED_JOB_13_TYPE)
            .job14Type(UPDATED_JOB_14_TYPE)
            .semset(UPDATED_SEMSET)
            .isRcb(UPDATED_IS_RCB)
            .sorc1(UPDATED_SORC_1)
            .sorc1b(UPDATED_SORC_1_B)
            .sorc1c(UPDATED_SORC_1_C)
            .sorc6(UPDATED_SORC_6)
            .sorc3e(UPDATED_SORC_3_E)
            .sorc1d(UPDATED_SORC_1_D)
            .sorc2(UPDATED_SORC_2)
            .sorc3(UPDATED_SORC_3)
            .sorc3a(UPDATED_SORC_3_A)
            .sorc4(UPDATED_SORC_4)
            .sorc5(UPDATED_SORC_5)
            .sorc3b(UPDATED_SORC_3_B)
            .sorc3c(UPDATED_SORC_3_C)
            .sorc3d(UPDATED_SORC_3_D)
            .sorc3b2(UPDATED_SORC_3_B_2)
            .sorc3b3(UPDATED_SORC_3_B_3)
            .sorc3b4(UPDATED_SORC_3_B_4)
            .sorc3b5(UPDATED_SORC_3_B_5)
            .sorc3c1(UPDATED_SORC_3_C_1)
            .sorc3d1(UPDATED_SORC_3_D_1)
            .sorc3d3(UPDATED_SORC_3_D_3)
            .sorc3d4(UPDATED_SORC_3_D_4)
            .sorc7(UPDATED_SORC_7)
            .sorc7Use(UPDATED_SORC_7_USE)
            .sorc8(UPDATED_SORC_8)
            .sorc7Use2(UPDATED_SORC_7_USE_2)
            .sorc7Use3(UPDATED_SORC_7_USE_3)
            .openYn(UPDATED_OPEN_YN)
            .wKind(UPDATED_W_KIND)
            .wDays(UPDATED_W_DAYS)
            .wRemk(UPDATED_W_REMK)
            .bCode(UPDATED_B_CODE)
            .totAmt(UPDATED_TOT_AMT)
            .bdgt1(UPDATED_BDGT_1)
            .igtct(UPDATED_IGTCT)
            .othct(UPDATED_OTHCT)
            .rsvct(UPDATED_RSVCT)
            .poll(UPDATED_POLL)
            .pollNo(UPDATED_POLL_NO)
            .othMon(UPDATED_OTH_MON)
            .gyResn(UPDATED_GY_RESN)
            .drut(UPDATED_DRUT)
            .drct(UPDATED_DRCT)
            .dsrm(UPDATED_DSRM)
            .scrm(UPDATED_SCRM)
            .scrr(UPDATED_SCRR)
            .scnum(UPDATED_SCNUM)
            .pcmut(UPDATED_PCMUT)
            .ccnum(UPDATED_CCNUM)
            .issStartDate(UPDATED_ISS_START_DATE)
            .issEndDate(UPDATED_ISS_END_DATE)
            .issCost(UPDATED_ISS_COST)
            .issNo(UPDATED_ISS_NO)
            .issRmk(UPDATED_ISS_RMK)
            .pdsDate(UPDATED_PDS_DATE)
            .dsDate(UPDATED_DS_DATE)
            .adsDate(UPDATED_ADS_DATE)
            .getlDate(UPDATED_GETL_DATE)
            .agetlDate(UPDATED_AGETL_DATE)
            .isSpay(UPDATED_IS_SPAY)
            .ipe(UPDATED_IPE)
            .ipeY(UPDATED_IPE_Y)
            .ipeN(UPDATED_IPE_N)
            .isNlic(UPDATED_IS_NLIC)
            .isDlic(UPDATED_IS_DLIC)
            .licNo(UPDATED_LIC_NO)
            .licDate(UPDATED_LIC_DATE)
            .alicDate(UPDATED_ALIC_DATE)
            .opDate(UPDATED_OP_DATE)
            .aopDate(UPDATED_AOP_DATE)
            .pckDate(UPDATED_PCK_DATE)
            .ackDate(UPDATED_ACK_DATE)
            .pscDate(UPDATED_PSC_DATE)
            .ascDate(UPDATED_ASC_DATE)
            .pctDate(UPDATED_PCT_DATE)
            .actDate(UPDATED_ACT_DATE)
            .ctmeth(UPDATED_CTMETH)
            .actmth(UPDATED_ACTMTH)
            .specPurch(UPDATED_SPEC_PURCH)
            .isIntl(UPDATED_IS_INTL)
            .tctmth(UPDATED_TCTMTH)
            .bdgt2(UPDATED_BDGT_2)
            .bdgt3(UPDATED_BDGT_3)
            .mctmth(UPDATED_MCTMTH)
            .ccnt3(UPDATED_CCNT_3)
            .isPer(UPDATED_IS_PER)
            .ctSum(UPDATED_CT_SUM)
            .ctSumF(UPDATED_CT_SUM_F)
            .ctSumU(UPDATED_CT_SUM_U)
            .ctmo(UPDATED_CTMO)
            .ctSumD(UPDATED_CT_SUM_D)
            .cntid(UPDATED_CNTID)
            .pays(UPDATED_PAYS)
            .pmsSumT(UPDATED_PMS_SUM_T)
            .pmsType(UPDATED_PMS_TYPE)
            .paymth(UPDATED_PAYMTH)
            .foreignYn(UPDATED_FOREIGN_YN)
            .csDate(UPDATED_CS_DATE)
            .asDate(UPDATED_AS_DATE)
            .asuDate(UPDATED_ASU_DATE)
            .ceDate(UPDATED_CE_DATE)
            .ceDateF(UPDATED_CE_DATE_F)
            .aeDate(UPDATED_AE_DATE)
            .fcost2(UPDATED_FCOST_2)
            .fcsDate(UPDATED_FCS_DATE)
            .afcsDate(UPDATED_AFCS_DATE)
            .fcost(UPDATED_FCOST)
            .isCkp(UPDATED_IS_CKP)
            .isCkp2(UPDATED_IS_CKP_2)
            .isCkp3(UPDATED_IS_CKP_3)
            .ckpMoney(UPDATED_CKP_MONEY)
            .ckpUnit(UPDATED_CKP_UNIT)
            .ckpDate(UPDATED_CKP_DATE)
            .ckpCnt(UPDATED_CKP_CNT)
            .ckpNum(UPDATED_CKP_NUM)
            .ckaUnit(UPDATED_CKA_UNIT)
            .ckaDate(UPDATED_CKA_DATE)
            .ckaCnt(UPDATED_CKA_CNT)
            .ckaNum(UPDATED_CKA_NUM)
            .ckdUnit(UPDATED_CKD_UNIT)
            .ckdDate(UPDATED_CKD_DATE)
            .ckdCnt(UPDATED_CKD_CNT)
            .ckdNum(UPDATED_CKD_NUM)
            .workMemo(UPDATED_WORK_MEMO)
            .hotLine(UPDATED_HOT_LINE)
            .lnkCode2(UPDATED_LNK_CODE_2)
            .lnkCode3(UPDATED_LNK_CODE_3)
            .lnkNo2(UPDATED_LNK_NO_2)
            .ccnt1(UPDATED_CCNT_1)
            .typeCode2(UPDATED_TYPE_CODE_2)
            .deptName(UPDATED_DEPT_NAME)
            .aopCnt(UPDATED_AOP_CNT)
            .uniqueKey(UPDATED_UNIQUE_KEY)
            .isBidstr(UPDATED_IS_BIDSTR)
            .isEngineer(UPDATED_IS_ENGINEER)
            .name0(UPDATED_NAME_0)
            .ctSum0(UPDATED_CT_SUM_0)
            .bdgt10(UPDATED_BDGT_10)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .batch(UPDATED_BATCH)
            .nocntDay(UPDATED_NOCNT_DAY)
            .status(UPDATED_STATUS)
            .aokDate(UPDATED_AOK_DATE)
            .fcost3(UPDATED_FCOST_3)
            .finWays(UPDATED_FIN_WAYS)
            .aworkDay(UPDATED_AWORK_DAY)
            .fastDay(UPDATED_FAST_DAY)
            .overDay(UPDATED_OVER_DAY)
            .overDayY(UPDATED_OVER_DAY_Y)
            .overFee(UPDATED_OVER_FEE)
            .otherFee(UPDATED_OTHER_FEE)
            .punshFee(UPDATED_PUNSH_FEE)
            .tm2x(UPDATED_TM_2_X)
            .tm2y(UPDATED_TM_2_Y)
            .tm2x2(UPDATED_TM_2_X_2)
            .tm2y2(UPDATED_TM_2_Y_2)
            .tm2x3(UPDATED_TM_2_X_3)
            .tm2y3(UPDATED_TM_2_Y_3)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP)
            .can(UPDATED_CAN)
            .srut(UPDATED_SRUT)
            .anoct(UPDATED_ANOCT)
            .cepdNo(UPDATED_CEPD_NO)
            .subNo(UPDATED_SUB_NO)
            .scnt(UPDATED_SCNT)
            .dsdm(UPDATED_DSDM)
            .preson(UPDATED_PRESON)
            .psignDate(UPDATED_PSIGN_DATE)
            .asignDate(UPDATED_ASIGN_DATE)
            .trDate(UPDATED_TR_DATE)
            .confIn(UPDATED_CONF_IN)
            .uKey(UPDATED_U_KEY)
            .tpcoth(UPDATED_TPCOTH)
            .pcmct(UPDATED_PCMCT)
            .wkutOld(UPDATED_WKUT_OLD)
            .pccMemo(UPDATED_PCC_MEMO)
            .bgbSumt(UPDATED_BGB_SUMT)
            .bgbDate(UPDATED_BGB_DATE)
            .mainItem(UPDATED_MAIN_ITEM)
            .mDesc(UPDATED_M_DESC)
            .mRmk(UPDATED_M_RMK)
            .pmrSumt(UPDATED_PMR_SUMT)
            .pmrDate(UPDATED_PMR_DATE)
            .pmrResm(UPDATED_PMR_RESM);
        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(updatedBidProject);

        restBidProjectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidProjectDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidProjectDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidProject in the database
        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
        BidProject testBidProject = bidProjectList.get(bidProjectList.size() - 1);
        assertThat(testBidProject.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidProject.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidProject.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBidProject.getIsAdd92()).isEqualTo(UPDATED_IS_ADD_92);
        assertThat(testBidProject.getPntCtrl()).isEqualTo(UPDATED_PNT_CTRL);
        assertThat(testBidProject.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testBidProject.getIsUnion()).isEqualTo(UPDATED_IS_UNION);
        assertThat(testBidProject.getPlaceCode()).isEqualTo(UPDATED_PLACE_CODE);
        assertThat(testBidProject.getPlace()).isEqualTo(UPDATED_PLACE);
        assertThat(testBidProject.getBosCode()).isEqualTo(UPDATED_BOS_CODE);
        assertThat(testBidProject.getDepCode()).isEqualTo(UPDATED_DEP_CODE);
        assertThat(testBidProject.getIsBg()).isEqualTo(UPDATED_IS_BG);
        assertThat(testBidProject.getPlnCode()).isEqualTo(UPDATED_PLN_CODE);
        assertThat(testBidProject.getPlnNo()).isEqualTo(UPDATED_PLN_NO);
        assertThat(testBidProject.getPlnUnicode()).isEqualTo(UPDATED_PLN_UNICODE);
        assertThat(testBidProject.getBos2Code()).isEqualTo(UPDATED_BOS_2_CODE);
        assertThat(testBidProject.getBos2Rate()).isEqualByComparingTo(UPDATED_BOS_2_RATE);
        assertThat(testBidProject.getBos2bCode()).isEqualTo(UPDATED_BOS_2_B_CODE);
        assertThat(testBidProject.getBos2bRate()).isEqualByComparingTo(UPDATED_BOS_2_B_RATE);
        assertThat(testBidProject.getBos2cCode()).isEqualTo(UPDATED_BOS_2_C_CODE);
        assertThat(testBidProject.getBos2cRate()).isEqualByComparingTo(UPDATED_BOS_2_C_RATE);
        assertThat(testBidProject.getBos2dCode()).isEqualTo(UPDATED_BOS_2_D_CODE);
        assertThat(testBidProject.getBos2dRate()).isEqualByComparingTo(UPDATED_BOS_2_D_RATE);
        assertThat(testBidProject.getLnkCode()).isEqualTo(UPDATED_LNK_CODE);
        assertThat(testBidProject.getCcnt2()).isEqualTo(UPDATED_CCNT_2);
        assertThat(testBidProject.getMan()).isEqualTo(UPDATED_MAN);
        assertThat(testBidProject.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testBidProject.geteMail()).isEqualTo(UPDATED_E_MAIL);
        assertThat(testBidProject.getWrate()).isEqualByComparingTo(UPDATED_WRATE);
        assertThat(testBidProject.getContain()).isEqualTo(UPDATED_CONTAIN);
        assertThat(testBidProject.getPfunc()).isEqualTo(UPDATED_PFUNC);
        assertThat(testBidProject.getBenf()).isEqualTo(UPDATED_BENF);
        assertThat(testBidProject.getPstand()).isEqualTo(UPDATED_PSTAND);
        assertThat(testBidProject.getPqty()).isEqualTo(UPDATED_PQTY);
        assertThat(testBidProject.getUntnd()).isEqualTo(UPDATED_UNTND);
        assertThat(testBidProject.getIsSpcw()).isEqualTo(UPDATED_IS_SPCW);
        assertThat(testBidProject.getSpcw01()).isEqualTo(UPDATED_SPCW_01);
        assertThat(testBidProject.getSpcw02()).isEqualTo(UPDATED_SPCW_02);
        assertThat(testBidProject.getSpcw03()).isEqualTo(UPDATED_SPCW_03);
        assertThat(testBidProject.getSpcw04()).isEqualTo(UPDATED_SPCW_04);
        assertThat(testBidProject.getSpcw05()).isEqualTo(UPDATED_SPCW_05);
        assertThat(testBidProject.getSpcw06()).isEqualTo(UPDATED_SPCW_06);
        assertThat(testBidProject.getSpcw07()).isEqualTo(UPDATED_SPCW_07);
        assertThat(testBidProject.getSpcw08()).isEqualTo(UPDATED_SPCW_08);
        assertThat(testBidProject.getSpcw09()).isEqualTo(UPDATED_SPCW_09);
        assertThat(testBidProject.getSpcw10()).isEqualTo(UPDATED_SPCW_10);
        assertThat(testBidProject.getSpcw11()).isEqualTo(UPDATED_SPCW_11);
        assertThat(testBidProject.getSpcw12()).isEqualTo(UPDATED_SPCW_12);
        assertThat(testBidProject.getSpcw01Amt()).isEqualByComparingTo(UPDATED_SPCW_01_AMT);
        assertThat(testBidProject.getSpcw01Nm()).isEqualTo(UPDATED_SPCW_01_NM);
        assertThat(testBidProject.getSpcw02Amt()).isEqualByComparingTo(UPDATED_SPCW_02_AMT);
        assertThat(testBidProject.getSpcw02Nm()).isEqualTo(UPDATED_SPCW_02_NM);
        assertThat(testBidProject.getSpcw03Amt()).isEqualByComparingTo(UPDATED_SPCW_03_AMT);
        assertThat(testBidProject.getSpcw03Nm()).isEqualTo(UPDATED_SPCW_03_NM);
        assertThat(testBidProject.getSpcw04Amt()).isEqualByComparingTo(UPDATED_SPCW_04_AMT);
        assertThat(testBidProject.getSpcw04Nm()).isEqualTo(UPDATED_SPCW_04_NM);
        assertThat(testBidProject.getSpcw05Amt()).isEqualByComparingTo(UPDATED_SPCW_05_AMT);
        assertThat(testBidProject.getSpcw05Nm()).isEqualTo(UPDATED_SPCW_05_NM);
        assertThat(testBidProject.getSpcw06Amt()).isEqualByComparingTo(UPDATED_SPCW_06_AMT);
        assertThat(testBidProject.getSpcw06Nm()).isEqualTo(UPDATED_SPCW_06_NM);
        assertThat(testBidProject.getSpcw07Amt()).isEqualByComparingTo(UPDATED_SPCW_07_AMT);
        assertThat(testBidProject.getSpcw07Nm()).isEqualTo(UPDATED_SPCW_07_NM);
        assertThat(testBidProject.getSpcw08Amt()).isEqualByComparingTo(UPDATED_SPCW_08_AMT);
        assertThat(testBidProject.getSpcw08Nm()).isEqualTo(UPDATED_SPCW_08_NM);
        assertThat(testBidProject.getSpcw09Amt()).isEqualByComparingTo(UPDATED_SPCW_09_AMT);
        assertThat(testBidProject.getSpcw09Nm()).isEqualTo(UPDATED_SPCW_09_NM);
        assertThat(testBidProject.getSpcw10Amt()).isEqualByComparingTo(UPDATED_SPCW_10_AMT);
        assertThat(testBidProject.getSpcw10Nm()).isEqualTo(UPDATED_SPCW_10_NM);
        assertThat(testBidProject.getSpcw11Amt()).isEqualByComparingTo(UPDATED_SPCW_11_AMT);
        assertThat(testBidProject.getSpcw11Nm()).isEqualTo(UPDATED_SPCW_11_NM);
        assertThat(testBidProject.getSpcw12Amt()).isEqualByComparingTo(UPDATED_SPCW_12_AMT);
        assertThat(testBidProject.getSpcw12Nm()).isEqualTo(UPDATED_SPCW_12_NM);
        assertThat(testBidProject.getJob01Type()).isEqualTo(UPDATED_JOB_01_TYPE);
        assertThat(testBidProject.getJob02Type()).isEqualTo(UPDATED_JOB_02_TYPE);
        assertThat(testBidProject.getJob03Type()).isEqualTo(UPDATED_JOB_03_TYPE);
        assertThat(testBidProject.getJob04Type()).isEqualTo(UPDATED_JOB_04_TYPE);
        assertThat(testBidProject.getJob05Type()).isEqualTo(UPDATED_JOB_05_TYPE);
        assertThat(testBidProject.getJob06Type()).isEqualTo(UPDATED_JOB_06_TYPE);
        assertThat(testBidProject.getJob07Type()).isEqualTo(UPDATED_JOB_07_TYPE);
        assertThat(testBidProject.getJob08Type()).isEqualTo(UPDATED_JOB_08_TYPE);
        assertThat(testBidProject.getJob09Type()).isEqualTo(UPDATED_JOB_09_TYPE);
        assertThat(testBidProject.getJob10Type()).isEqualTo(UPDATED_JOB_10_TYPE);
        assertThat(testBidProject.getJob11Type()).isEqualTo(UPDATED_JOB_11_TYPE);
        assertThat(testBidProject.getJob12Type()).isEqualTo(UPDATED_JOB_12_TYPE);
        assertThat(testBidProject.getJob13Type()).isEqualTo(UPDATED_JOB_13_TYPE);
        assertThat(testBidProject.getJob14Type()).isEqualTo(UPDATED_JOB_14_TYPE);
        assertThat(testBidProject.getSemset()).isEqualTo(UPDATED_SEMSET);
        assertThat(testBidProject.getIsRcb()).isEqualTo(UPDATED_IS_RCB);
        assertThat(testBidProject.getSorc1()).isEqualByComparingTo(UPDATED_SORC_1);
        assertThat(testBidProject.getSorc1b()).isEqualByComparingTo(UPDATED_SORC_1_B);
        assertThat(testBidProject.getSorc1c()).isEqualByComparingTo(UPDATED_SORC_1_C);
        assertThat(testBidProject.getSorc6()).isEqualByComparingTo(UPDATED_SORC_6);
        assertThat(testBidProject.getSorc3e()).isEqualByComparingTo(UPDATED_SORC_3_E);
        assertThat(testBidProject.getSorc1d()).isEqualByComparingTo(UPDATED_SORC_1_D);
        assertThat(testBidProject.getSorc2()).isEqualByComparingTo(UPDATED_SORC_2);
        assertThat(testBidProject.getSorc3()).isEqualByComparingTo(UPDATED_SORC_3);
        assertThat(testBidProject.getSorc3a()).isEqualByComparingTo(UPDATED_SORC_3_A);
        assertThat(testBidProject.getSorc4()).isEqualByComparingTo(UPDATED_SORC_4);
        assertThat(testBidProject.getSorc5()).isEqualByComparingTo(UPDATED_SORC_5);
        assertThat(testBidProject.getSorc3b()).isEqualByComparingTo(UPDATED_SORC_3_B);
        assertThat(testBidProject.getSorc3c()).isEqualByComparingTo(UPDATED_SORC_3_C);
        assertThat(testBidProject.getSorc3d()).isEqualByComparingTo(UPDATED_SORC_3_D);
        assertThat(testBidProject.getSorc3b2()).isEqualByComparingTo(UPDATED_SORC_3_B_2);
        assertThat(testBidProject.getSorc3b3()).isEqualByComparingTo(UPDATED_SORC_3_B_3);
        assertThat(testBidProject.getSorc3b4()).isEqualByComparingTo(UPDATED_SORC_3_B_4);
        assertThat(testBidProject.getSorc3b5()).isEqualByComparingTo(UPDATED_SORC_3_B_5);
        assertThat(testBidProject.getSorc3c1()).isEqualByComparingTo(UPDATED_SORC_3_C_1);
        assertThat(testBidProject.getSorc3d1()).isEqualByComparingTo(UPDATED_SORC_3_D_1);
        assertThat(testBidProject.getSorc3d3()).isEqualByComparingTo(UPDATED_SORC_3_D_3);
        assertThat(testBidProject.getSorc3d4()).isEqualByComparingTo(UPDATED_SORC_3_D_4);
        assertThat(testBidProject.getSorc7()).isEqualByComparingTo(UPDATED_SORC_7);
        assertThat(testBidProject.getSorc7Use()).isEqualByComparingTo(UPDATED_SORC_7_USE);
        assertThat(testBidProject.getSorc8()).isEqualByComparingTo(UPDATED_SORC_8);
        assertThat(testBidProject.getSorc7Use2()).isEqualByComparingTo(UPDATED_SORC_7_USE_2);
        assertThat(testBidProject.getSorc7Use3()).isEqualByComparingTo(UPDATED_SORC_7_USE_3);
        assertThat(testBidProject.getOpenYn()).isEqualTo(UPDATED_OPEN_YN);
        assertThat(testBidProject.getwKind()).isEqualTo(UPDATED_W_KIND);
        assertThat(testBidProject.getwDays()).isEqualTo(UPDATED_W_DAYS);
        assertThat(testBidProject.getwRemk()).isEqualTo(UPDATED_W_REMK);
        assertThat(testBidProject.getbCode()).isEqualTo(UPDATED_B_CODE);
        assertThat(testBidProject.getTotAmt()).isEqualByComparingTo(UPDATED_TOT_AMT);
        assertThat(testBidProject.getBdgt1()).isEqualByComparingTo(UPDATED_BDGT_1);
        assertThat(testBidProject.getIgtct()).isEqualByComparingTo(UPDATED_IGTCT);
        assertThat(testBidProject.getOthct()).isEqualByComparingTo(UPDATED_OTHCT);
        assertThat(testBidProject.getRsvct()).isEqualByComparingTo(UPDATED_RSVCT);
        assertThat(testBidProject.getPoll()).isEqualByComparingTo(UPDATED_POLL);
        assertThat(testBidProject.getPollNo()).isEqualTo(UPDATED_POLL_NO);
        assertThat(testBidProject.getOthMon()).isEqualByComparingTo(UPDATED_OTH_MON);
        assertThat(testBidProject.getGyResn()).isEqualTo(UPDATED_GY_RESN);
        assertThat(testBidProject.getDrut()).isEqualTo(UPDATED_DRUT);
        assertThat(testBidProject.getDrct()).isEqualByComparingTo(UPDATED_DRCT);
        assertThat(testBidProject.getDsrm()).isEqualTo(UPDATED_DSRM);
        assertThat(testBidProject.getScrm()).isEqualTo(UPDATED_SCRM);
        assertThat(testBidProject.getScrr()).isEqualTo(UPDATED_SCRR);
        assertThat(testBidProject.getScnum()).isEqualTo(UPDATED_SCNUM);
        assertThat(testBidProject.getPcmut()).isEqualTo(UPDATED_PCMUT);
        assertThat(testBidProject.getCcnum()).isEqualTo(UPDATED_CCNUM);
        assertThat(testBidProject.getIssStartDate()).isEqualTo(UPDATED_ISS_START_DATE);
        assertThat(testBidProject.getIssEndDate()).isEqualTo(UPDATED_ISS_END_DATE);
        assertThat(testBidProject.getIssCost()).isEqualByComparingTo(UPDATED_ISS_COST);
        assertThat(testBidProject.getIssNo()).isEqualTo(UPDATED_ISS_NO);
        assertThat(testBidProject.getIssRmk()).isEqualTo(UPDATED_ISS_RMK);
        assertThat(testBidProject.getPdsDate()).isEqualTo(UPDATED_PDS_DATE);
        assertThat(testBidProject.getDsDate()).isEqualTo(UPDATED_DS_DATE);
        assertThat(testBidProject.getAdsDate()).isEqualTo(UPDATED_ADS_DATE);
        assertThat(testBidProject.getGetlDate()).isEqualTo(UPDATED_GETL_DATE);
        assertThat(testBidProject.getAgetlDate()).isEqualTo(UPDATED_AGETL_DATE);
        assertThat(testBidProject.getIsSpay()).isEqualTo(UPDATED_IS_SPAY);
        assertThat(testBidProject.getIpe()).isEqualTo(UPDATED_IPE);
        assertThat(testBidProject.getIpeY()).isEqualTo(UPDATED_IPE_Y);
        assertThat(testBidProject.getIpeN()).isEqualTo(UPDATED_IPE_N);
        assertThat(testBidProject.getIsNlic()).isEqualTo(UPDATED_IS_NLIC);
        assertThat(testBidProject.getIsDlic()).isEqualTo(UPDATED_IS_DLIC);
        assertThat(testBidProject.getLicNo()).isEqualTo(UPDATED_LIC_NO);
        assertThat(testBidProject.getLicDate()).isEqualTo(UPDATED_LIC_DATE);
        assertThat(testBidProject.getAlicDate()).isEqualTo(UPDATED_ALIC_DATE);
        assertThat(testBidProject.getOpDate()).isEqualTo(UPDATED_OP_DATE);
        assertThat(testBidProject.getAopDate()).isEqualTo(UPDATED_AOP_DATE);
        assertThat(testBidProject.getPckDate()).isEqualTo(UPDATED_PCK_DATE);
        assertThat(testBidProject.getAckDate()).isEqualTo(UPDATED_ACK_DATE);
        assertThat(testBidProject.getPscDate()).isEqualTo(UPDATED_PSC_DATE);
        assertThat(testBidProject.getAscDate()).isEqualTo(UPDATED_ASC_DATE);
        assertThat(testBidProject.getPctDate()).isEqualTo(UPDATED_PCT_DATE);
        assertThat(testBidProject.getActDate()).isEqualTo(UPDATED_ACT_DATE);
        assertThat(testBidProject.getCtmeth()).isEqualTo(UPDATED_CTMETH);
        assertThat(testBidProject.getActmth()).isEqualTo(UPDATED_ACTMTH);
        assertThat(testBidProject.getSpecPurch()).isEqualTo(UPDATED_SPEC_PURCH);
        assertThat(testBidProject.getIsIntl()).isEqualTo(UPDATED_IS_INTL);
        assertThat(testBidProject.getTctmth()).isEqualTo(UPDATED_TCTMTH);
        assertThat(testBidProject.getBdgt2()).isEqualByComparingTo(UPDATED_BDGT_2);
        assertThat(testBidProject.getBdgt3()).isEqualByComparingTo(UPDATED_BDGT_3);
        assertThat(testBidProject.getMctmth()).isEqualTo(UPDATED_MCTMTH);
        assertThat(testBidProject.getCcnt3()).isEqualTo(UPDATED_CCNT_3);
        assertThat(testBidProject.getIsPer()).isEqualTo(UPDATED_IS_PER);
        assertThat(testBidProject.getCtSum()).isEqualByComparingTo(UPDATED_CT_SUM);
        assertThat(testBidProject.getCtSumF()).isEqualByComparingTo(UPDATED_CT_SUM_F);
        assertThat(testBidProject.getCtSumU()).isEqualByComparingTo(UPDATED_CT_SUM_U);
        assertThat(testBidProject.getCtmo()).isEqualTo(UPDATED_CTMO);
        assertThat(testBidProject.getCtSumD()).isEqualByComparingTo(UPDATED_CT_SUM_D);
        assertThat(testBidProject.getCntid()).isEqualTo(UPDATED_CNTID);
        assertThat(testBidProject.getPays()).isEqualByComparingTo(UPDATED_PAYS);
        assertThat(testBidProject.getPmsSumT()).isEqualByComparingTo(UPDATED_PMS_SUM_T);
        assertThat(testBidProject.getPmsType()).isEqualTo(UPDATED_PMS_TYPE);
        assertThat(testBidProject.getPaymth()).isEqualTo(UPDATED_PAYMTH);
        assertThat(testBidProject.getForeignYn()).isEqualTo(UPDATED_FOREIGN_YN);
        assertThat(testBidProject.getCsDate()).isEqualTo(UPDATED_CS_DATE);
        assertThat(testBidProject.getAsDate()).isEqualTo(UPDATED_AS_DATE);
        assertThat(testBidProject.getAsuDate()).isEqualTo(UPDATED_ASU_DATE);
        assertThat(testBidProject.getCeDate()).isEqualTo(UPDATED_CE_DATE);
        assertThat(testBidProject.getCeDateF()).isEqualTo(UPDATED_CE_DATE_F);
        assertThat(testBidProject.getAeDate()).isEqualTo(UPDATED_AE_DATE);
        assertThat(testBidProject.getFcost2()).isEqualByComparingTo(UPDATED_FCOST_2);
        assertThat(testBidProject.getFcsDate()).isEqualTo(UPDATED_FCS_DATE);
        assertThat(testBidProject.getAfcsDate()).isEqualTo(UPDATED_AFCS_DATE);
        assertThat(testBidProject.getFcost()).isEqualByComparingTo(UPDATED_FCOST);
        assertThat(testBidProject.getIsCkp()).isEqualTo(UPDATED_IS_CKP);
        assertThat(testBidProject.getIsCkp2()).isEqualTo(UPDATED_IS_CKP_2);
        assertThat(testBidProject.getIsCkp3()).isEqualTo(UPDATED_IS_CKP_3);
        assertThat(testBidProject.getCkpMoney()).isEqualByComparingTo(UPDATED_CKP_MONEY);
        assertThat(testBidProject.getCkpUnit()).isEqualTo(UPDATED_CKP_UNIT);
        assertThat(testBidProject.getCkpDate()).isEqualTo(UPDATED_CKP_DATE);
        assertThat(testBidProject.getCkpCnt()).isEqualByComparingTo(UPDATED_CKP_CNT);
        assertThat(testBidProject.getCkpNum()).isEqualTo(UPDATED_CKP_NUM);
        assertThat(testBidProject.getCkaUnit()).isEqualTo(UPDATED_CKA_UNIT);
        assertThat(testBidProject.getCkaDate()).isEqualTo(UPDATED_CKA_DATE);
        assertThat(testBidProject.getCkaCnt()).isEqualByComparingTo(UPDATED_CKA_CNT);
        assertThat(testBidProject.getCkaNum()).isEqualTo(UPDATED_CKA_NUM);
        assertThat(testBidProject.getCkdUnit()).isEqualTo(UPDATED_CKD_UNIT);
        assertThat(testBidProject.getCkdDate()).isEqualTo(UPDATED_CKD_DATE);
        assertThat(testBidProject.getCkdCnt()).isEqualByComparingTo(UPDATED_CKD_CNT);
        assertThat(testBidProject.getCkdNum()).isEqualTo(UPDATED_CKD_NUM);
        assertThat(testBidProject.getWorkMemo()).isEqualTo(UPDATED_WORK_MEMO);
        assertThat(testBidProject.getHotLine()).isEqualTo(UPDATED_HOT_LINE);
        assertThat(testBidProject.getLnkCode2()).isEqualTo(UPDATED_LNK_CODE_2);
        assertThat(testBidProject.getLnkCode3()).isEqualTo(UPDATED_LNK_CODE_3);
        assertThat(testBidProject.getLnkNo2()).isEqualTo(UPDATED_LNK_NO_2);
        assertThat(testBidProject.getCcnt1()).isEqualTo(UPDATED_CCNT_1);
        assertThat(testBidProject.getTypeCode2()).isEqualTo(UPDATED_TYPE_CODE_2);
        assertThat(testBidProject.getDeptName()).isEqualTo(UPDATED_DEPT_NAME);
        assertThat(testBidProject.getAopCnt()).isEqualTo(UPDATED_AOP_CNT);
        assertThat(testBidProject.getUniqueKey()).isEqualTo(UPDATED_UNIQUE_KEY);
        assertThat(testBidProject.getIsBidstr()).isEqualTo(UPDATED_IS_BIDSTR);
        assertThat(testBidProject.getIsEngineer()).isEqualTo(UPDATED_IS_ENGINEER);
        assertThat(testBidProject.getName0()).isEqualTo(UPDATED_NAME_0);
        assertThat(testBidProject.getCtSum0()).isEqualByComparingTo(UPDATED_CT_SUM_0);
        assertThat(testBidProject.getBdgt1_0()).isEqualByComparingTo(UPDATED_BDGT_10);
        assertThat(testBidProject.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBidProject.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBidProject.getBatch()).isEqualTo(UPDATED_BATCH);
        assertThat(testBidProject.getNocntDay()).isEqualTo(UPDATED_NOCNT_DAY);
        assertThat(testBidProject.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBidProject.getAokDate()).isEqualTo(UPDATED_AOK_DATE);
        assertThat(testBidProject.getFcost3()).isEqualByComparingTo(UPDATED_FCOST_3);
        assertThat(testBidProject.getFinWays()).isEqualTo(UPDATED_FIN_WAYS);
        assertThat(testBidProject.getAworkDay()).isEqualTo(UPDATED_AWORK_DAY);
        assertThat(testBidProject.getFastDay()).isEqualTo(UPDATED_FAST_DAY);
        assertThat(testBidProject.getOverDay()).isEqualTo(UPDATED_OVER_DAY);
        assertThat(testBidProject.getOverDayY()).isEqualTo(UPDATED_OVER_DAY_Y);
        assertThat(testBidProject.getOverFee()).isEqualByComparingTo(UPDATED_OVER_FEE);
        assertThat(testBidProject.getOtherFee()).isEqualByComparingTo(UPDATED_OTHER_FEE);
        assertThat(testBidProject.getPunshFee()).isEqualByComparingTo(UPDATED_PUNSH_FEE);
        assertThat(testBidProject.getTm2x()).isEqualTo(UPDATED_TM_2_X);
        assertThat(testBidProject.getTm2y()).isEqualTo(UPDATED_TM_2_Y);
        assertThat(testBidProject.getTm2x2()).isEqualTo(UPDATED_TM_2_X_2);
        assertThat(testBidProject.getTm2y2()).isEqualTo(UPDATED_TM_2_Y_2);
        assertThat(testBidProject.getTm2x3()).isEqualTo(UPDATED_TM_2_X_3);
        assertThat(testBidProject.getTm2y3()).isEqualTo(UPDATED_TM_2_Y_3);
        assertThat(testBidProject.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidProject.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidProject.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidProject.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidProject.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidProject.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidProject.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidProject.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testBidProject.getSrut()).isEqualTo(UPDATED_SRUT);
        assertThat(testBidProject.getAnoct()).isEqualByComparingTo(UPDATED_ANOCT);
        assertThat(testBidProject.getCepdNo()).isEqualTo(UPDATED_CEPD_NO);
        assertThat(testBidProject.getSubNo()).isEqualTo(UPDATED_SUB_NO);
        assertThat(testBidProject.getScnt()).isEqualTo(UPDATED_SCNT);
        assertThat(testBidProject.getDsdm()).isEqualTo(UPDATED_DSDM);
        assertThat(testBidProject.getPreson()).isEqualTo(UPDATED_PRESON);
        assertThat(testBidProject.getPsignDate()).isEqualTo(UPDATED_PSIGN_DATE);
        assertThat(testBidProject.getAsignDate()).isEqualTo(UPDATED_ASIGN_DATE);
        assertThat(testBidProject.getTrDate()).isEqualTo(UPDATED_TR_DATE);
        assertThat(testBidProject.getConfIn()).isEqualByComparingTo(UPDATED_CONF_IN);
        assertThat(testBidProject.getuKey()).isEqualTo(UPDATED_U_KEY);
        assertThat(testBidProject.getTpcoth()).isEqualTo(UPDATED_TPCOTH);
        assertThat(testBidProject.getPcmct()).isEqualByComparingTo(UPDATED_PCMCT);
        assertThat(testBidProject.getWkutOld()).isEqualTo(UPDATED_WKUT_OLD);
        assertThat(testBidProject.getPccMemo()).isEqualTo(UPDATED_PCC_MEMO);
        assertThat(testBidProject.getBgbSumt()).isEqualByComparingTo(UPDATED_BGB_SUMT);
        assertThat(testBidProject.getBgbDate()).isEqualTo(UPDATED_BGB_DATE);
        assertThat(testBidProject.getMainItem()).isEqualTo(UPDATED_MAIN_ITEM);
        assertThat(testBidProject.getmDesc()).isEqualTo(UPDATED_M_DESC);
        assertThat(testBidProject.getmRmk()).isEqualTo(UPDATED_M_RMK);
        assertThat(testBidProject.getPmrSumt()).isEqualByComparingTo(UPDATED_PMR_SUMT);
        assertThat(testBidProject.getPmrDate()).isEqualTo(UPDATED_PMR_DATE);
        assertThat(testBidProject.getPmrResm()).isEqualTo(UPDATED_PMR_RESM);
    }

//    @Test
//    @Transactional
//    void putNonExistingBidProject() throws Exception {
//        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();
//        bidProject.setId(count.incrementAndGet());
//
//        // Create the BidProject
//        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidProjectMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidProjectDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidProjectDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidProject in the database
//        List<BidProject> bidProjectList = bidProjectRepository.findAll();
//        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithIdMismatchBidProject() throws Exception {
//        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();
//        bidProject.setId(count.incrementAndGet());
//
//        // Create the BidProject
//        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidProjectMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidProjectDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidProject in the database
//        List<BidProject> bidProjectList = bidProjectRepository.findAll();
//        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithMissingIdPathParamBidProject() throws Exception {
//        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();
//        bidProject.setId(count.incrementAndGet());
//
//        // Create the BidProject
//        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidProjectMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidProject in the database
//        List<BidProject> bidProjectList = bidProjectRepository.findAll();
//        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void partialUpdateBidProjectWithPatch() throws Exception {
//        // Initialize the database
//        bidProjectRepository.saveAndFlush(bidProject);
//
//        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();
//
//        // Update the bidProject using partial update
//        BidProject partialUpdatedBidProject = new BidProject();
//        partialUpdatedBidProject.setId(bidProject.getId());
//
//        partialUpdatedBidProject
//            .prjno(UPDATED_PRJNO)
//            .name(UPDATED_NAME)
//            .typeCode(UPDATED_TYPE_CODE)
//            .place(UPDATED_PLACE)
//            .bosCode(UPDATED_BOS_CODE)
//            .isBg(UPDATED_IS_BG)
//            .plnCode(UPDATED_PLN_CODE)
//            .plnNo(UPDATED_PLN_NO)
//            .plnUnicode(UPDATED_PLN_UNICODE)
//            .bos2Rate(UPDATED_BOS_2_RATE)
//            .bos2bCode(UPDATED_BOS_2_B_CODE)
//            .bos2bRate(UPDATED_BOS_2_B_RATE)
//            .bos2dCode(UPDATED_BOS_2_D_CODE)
//            .lnkCode(UPDATED_LNK_CODE)
//            .ccnt2(UPDATED_CCNT_2)
//            .pfunc(UPDATED_PFUNC)
//            .pstand(UPDATED_PSTAND)
//            .pqty(UPDATED_PQTY)
//            .isSpcw(UPDATED_IS_SPCW)
//            .spcw06(UPDATED_SPCW_06)
//            .spcw09(UPDATED_SPCW_09)
//            .spcw10(UPDATED_SPCW_10)
//            .spcw11(UPDATED_SPCW_11)
//            .spcw01Amt(UPDATED_SPCW_01_AMT)
//            .spcw01Nm(UPDATED_SPCW_01_NM)
//            .spcw02Amt(UPDATED_SPCW_02_AMT)
//            .spcw03Nm(UPDATED_SPCW_03_NM)
//            .spcw04Amt(UPDATED_SPCW_04_AMT)
//            .spcw05Amt(UPDATED_SPCW_05_AMT)
//            .spcw06Nm(UPDATED_SPCW_06_NM)
//            .spcw07Amt(UPDATED_SPCW_07_AMT)
//            .spcw07Nm(UPDATED_SPCW_07_NM)
//            .spcw08Nm(UPDATED_SPCW_08_NM)
//            .spcw09Nm(UPDATED_SPCW_09_NM)
//            .spcw12Amt(UPDATED_SPCW_12_AMT)
//            .job03Type(UPDATED_JOB_03_TYPE)
//            .job04Type(UPDATED_JOB_04_TYPE)
//            .job07Type(UPDATED_JOB_07_TYPE)
//            .job09Type(UPDATED_JOB_09_TYPE)
//            .job10Type(UPDATED_JOB_10_TYPE)
//            .job14Type(UPDATED_JOB_14_TYPE)
//            .isRcb(UPDATED_IS_RCB)
//            .sorc6(UPDATED_SORC_6)
//            .sorc3e(UPDATED_SORC_3_E)
//            .sorc3a(UPDATED_SORC_3_A)
//            .sorc4(UPDATED_SORC_4)
//            .sorc3b(UPDATED_SORC_3_B)
//            .sorc3c(UPDATED_SORC_3_C)
//            .sorc3b2(UPDATED_SORC_3_B_2)
//            .sorc3b4(UPDATED_SORC_3_B_4)
//            .sorc3b5(UPDATED_SORC_3_B_5)
//            .sorc3c1(UPDATED_SORC_3_C_1)
//            .sorc3d1(UPDATED_SORC_3_D_1)
//            .sorc3d3(UPDATED_SORC_3_D_3)
//            .sorc7Use(UPDATED_SORC_7_USE)
//            .sorc7Use2(UPDATED_SORC_7_USE_2)
//            .sorc7Use3(UPDATED_SORC_7_USE_3)
//            .openYn(UPDATED_OPEN_YN)
//            .wDays(UPDATED_W_DAYS)
//            .wRemk(UPDATED_W_REMK)
//            .totAmt(UPDATED_TOT_AMT)
//            .igtct(UPDATED_IGTCT)
//            .othct(UPDATED_OTHCT)
//            .rsvct(UPDATED_RSVCT)
//            .othMon(UPDATED_OTH_MON)
//            .drut(UPDATED_DRUT)
//            .drct(UPDATED_DRCT)
//            .scrr(UPDATED_SCRR)
//            .pcmut(UPDATED_PCMUT)
//            .issEndDate(UPDATED_ISS_END_DATE)
//            .issCost(UPDATED_ISS_COST)
//            .issRmk(UPDATED_ISS_RMK)
//            .adsDate(UPDATED_ADS_DATE)
//            .getlDate(UPDATED_GETL_DATE)
//            .isSpay(UPDATED_IS_SPAY)
//            .ipe(UPDATED_IPE)
//            .ipeY(UPDATED_IPE_Y)
//            .isDlic(UPDATED_IS_DLIC)
//            .licNo(UPDATED_LIC_NO)
//            .licDate(UPDATED_LIC_DATE)
//            .actDate(UPDATED_ACT_DATE)
//            .ctmeth(UPDATED_CTMETH)
//            .bdgt2(UPDATED_BDGT_2)
//            .isPer(UPDATED_IS_PER)
//            .ctSum(UPDATED_CT_SUM)
//            .ctSumF(UPDATED_CT_SUM_F)
//            .ctmo(UPDATED_CTMO)
//            .pmsSumT(UPDATED_PMS_SUM_T)
//            .pmsType(UPDATED_PMS_TYPE)
//            .asDate(UPDATED_AS_DATE)
//            .asuDate(UPDATED_ASU_DATE)
//            .ceDate(UPDATED_CE_DATE)
//            .aeDate(UPDATED_AE_DATE)
//            .fcsDate(UPDATED_FCS_DATE)
//            .afcsDate(UPDATED_AFCS_DATE)
//            .isCkp(UPDATED_IS_CKP)
//            .ckpNum(UPDATED_CKP_NUM)
//            .ckaUnit(UPDATED_CKA_UNIT)
//            .ckaDate(UPDATED_CKA_DATE)
//            .ckdUnit(UPDATED_CKD_UNIT)
//            .ckdNum(UPDATED_CKD_NUM)
//            .workMemo(UPDATED_WORK_MEMO)
//            .hotLine(UPDATED_HOT_LINE)
//            .ccnt1(UPDATED_CCNT_1)
//            .aopCnt(UPDATED_AOP_CNT)
//            .isBidstr(UPDATED_IS_BIDSTR)
//            .ctSum0(UPDATED_CT_SUM_0)
//            .bdgt10(UPDATED_BDGT_10)
//            .startDate(UPDATED_START_DATE)
//            .nocntDay(UPDATED_NOCNT_DAY)
//            .status(UPDATED_STATUS)
//            .aokDate(UPDATED_AOK_DATE)
//            .aworkDay(UPDATED_AWORK_DAY)
//            .fastDay(UPDATED_FAST_DAY)
//            .overDay(UPDATED_OVER_DAY)
//            .overDayY(UPDATED_OVER_DAY_Y)
//            .otherFee(UPDATED_OTHER_FEE)
//            .tm2y2(UPDATED_TM_2_Y_2)
//            .tm2x3(UPDATED_TM_2_X_3)
//            .tm2y3(UPDATED_TM_2_Y_3)
//            .updateUser(UPDATED_UPDATE_USER)
//            .uIp(UPDATED_U_IP)
//            .can(UPDATED_CAN)
//            .srut(UPDATED_SRUT)
//            .scnt(UPDATED_SCNT)
//            .psignDate(UPDATED_PSIGN_DATE)
//            .asignDate(UPDATED_ASIGN_DATE)
//            .uKey(UPDATED_U_KEY)
//            .tpcoth(UPDATED_TPCOTH)
//            .pcmct(UPDATED_PCMCT)
//            .bgbSumt(UPDATED_BGB_SUMT)
//            .bgbDate(UPDATED_BGB_DATE)
//            .mDesc(UPDATED_M_DESC)
//            .mRmk(UPDATED_M_RMK)
//            .pmrResm(UPDATED_PMR_RESM);
//
//        restBidProjectMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedBidProject.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidProject))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the BidProject in the database
//        List<BidProject> bidProjectList = bidProjectRepository.findAll();
//        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
//        BidProject testBidProject = bidProjectList.get(bidProjectList.size() - 1);
//        assertThat(testBidProject.getWkut()).isEqualTo(DEFAULT_WKUT);
//        assertThat(testBidProject.getPrjno()).isEqualTo(UPDATED_PRJNO);
//        assertThat(testBidProject.getName()).isEqualTo(UPDATED_NAME);
//        assertThat(testBidProject.getIsAdd92()).isEqualTo(DEFAULT_IS_ADD_92);
//        assertThat(testBidProject.getPntCtrl()).isEqualTo(DEFAULT_PNT_CTRL);
//        assertThat(testBidProject.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
//        assertThat(testBidProject.getIsUnion()).isEqualTo(DEFAULT_IS_UNION);
//        assertThat(testBidProject.getPlaceCode()).isEqualTo(DEFAULT_PLACE_CODE);
//        assertThat(testBidProject.getPlace()).isEqualTo(UPDATED_PLACE);
//        assertThat(testBidProject.getBosCode()).isEqualTo(UPDATED_BOS_CODE);
//        assertThat(testBidProject.getDepCode()).isEqualTo(DEFAULT_DEP_CODE);
//        assertThat(testBidProject.getIsBg()).isEqualTo(UPDATED_IS_BG);
//        assertThat(testBidProject.getPlnCode()).isEqualTo(UPDATED_PLN_CODE);
//        assertThat(testBidProject.getPlnNo()).isEqualTo(UPDATED_PLN_NO);
//        assertThat(testBidProject.getPlnUnicode()).isEqualTo(UPDATED_PLN_UNICODE);
//        assertThat(testBidProject.getBos2Code()).isEqualTo(DEFAULT_BOS_2_CODE);
//        assertThat(testBidProject.getBos2Rate()).isEqualByComparingTo(UPDATED_BOS_2_RATE);
//        assertThat(testBidProject.getBos2bCode()).isEqualTo(UPDATED_BOS_2_B_CODE);
//        assertThat(testBidProject.getBos2bRate()).isEqualByComparingTo(UPDATED_BOS_2_B_RATE);
//        assertThat(testBidProject.getBos2cCode()).isEqualTo(DEFAULT_BOS_2_C_CODE);
//        assertThat(testBidProject.getBos2cRate()).isEqualByComparingTo(DEFAULT_BOS_2_C_RATE);
//        assertThat(testBidProject.getBos2dCode()).isEqualTo(UPDATED_BOS_2_D_CODE);
//        assertThat(testBidProject.getBos2dRate()).isEqualByComparingTo(DEFAULT_BOS_2_D_RATE);
//        assertThat(testBidProject.getLnkCode()).isEqualTo(UPDATED_LNK_CODE);
//        assertThat(testBidProject.getCcnt2()).isEqualTo(UPDATED_CCNT_2);
//        assertThat(testBidProject.getMan()).isEqualTo(DEFAULT_MAN);
//        assertThat(testBidProject.getTel()).isEqualTo(DEFAULT_TEL);
//        assertThat(testBidProject.geteMail()).isEqualTo(DEFAULT_E_MAIL);
//        assertThat(testBidProject.getWrate()).isEqualByComparingTo(DEFAULT_WRATE);
//        assertThat(testBidProject.getContain()).isEqualTo(DEFAULT_CONTAIN);
//        assertThat(testBidProject.getPfunc()).isEqualTo(UPDATED_PFUNC);
//        assertThat(testBidProject.getBenf()).isEqualTo(DEFAULT_BENF);
//        assertThat(testBidProject.getPstand()).isEqualTo(UPDATED_PSTAND);
//        assertThat(testBidProject.getPqty()).isEqualTo(UPDATED_PQTY);
//        assertThat(testBidProject.getUntnd()).isEqualTo(DEFAULT_UNTND);
//        assertThat(testBidProject.getIsSpcw()).isEqualTo(UPDATED_IS_SPCW);
//        assertThat(testBidProject.getSpcw01()).isEqualTo(DEFAULT_SPCW_01);
//        assertThat(testBidProject.getSpcw02()).isEqualTo(DEFAULT_SPCW_02);
//        assertThat(testBidProject.getSpcw03()).isEqualTo(DEFAULT_SPCW_03);
//        assertThat(testBidProject.getSpcw04()).isEqualTo(DEFAULT_SPCW_04);
//        assertThat(testBidProject.getSpcw05()).isEqualTo(DEFAULT_SPCW_05);
//        assertThat(testBidProject.getSpcw06()).isEqualTo(UPDATED_SPCW_06);
//        assertThat(testBidProject.getSpcw07()).isEqualTo(DEFAULT_SPCW_07);
//        assertThat(testBidProject.getSpcw08()).isEqualTo(DEFAULT_SPCW_08);
//        assertThat(testBidProject.getSpcw09()).isEqualTo(UPDATED_SPCW_09);
//        assertThat(testBidProject.getSpcw10()).isEqualTo(UPDATED_SPCW_10);
//        assertThat(testBidProject.getSpcw11()).isEqualTo(UPDATED_SPCW_11);
//        assertThat(testBidProject.getSpcw12()).isEqualTo(DEFAULT_SPCW_12);
//        assertThat(testBidProject.getSpcw01Amt()).isEqualByComparingTo(UPDATED_SPCW_01_AMT);
//        assertThat(testBidProject.getSpcw01Nm()).isEqualTo(UPDATED_SPCW_01_NM);
//        assertThat(testBidProject.getSpcw02Amt()).isEqualByComparingTo(UPDATED_SPCW_02_AMT);
//        assertThat(testBidProject.getSpcw02Nm()).isEqualTo(DEFAULT_SPCW_02_NM);
//        assertThat(testBidProject.getSpcw03Amt()).isEqualByComparingTo(DEFAULT_SPCW_03_AMT);
//        assertThat(testBidProject.getSpcw03Nm()).isEqualTo(UPDATED_SPCW_03_NM);
//        assertThat(testBidProject.getSpcw04Amt()).isEqualByComparingTo(UPDATED_SPCW_04_AMT);
//        assertThat(testBidProject.getSpcw04Nm()).isEqualTo(DEFAULT_SPCW_04_NM);
//        assertThat(testBidProject.getSpcw05Amt()).isEqualByComparingTo(UPDATED_SPCW_05_AMT);
//        assertThat(testBidProject.getSpcw05Nm()).isEqualTo(DEFAULT_SPCW_05_NM);
//        assertThat(testBidProject.getSpcw06Amt()).isEqualByComparingTo(DEFAULT_SPCW_06_AMT);
//        assertThat(testBidProject.getSpcw06Nm()).isEqualTo(UPDATED_SPCW_06_NM);
//        assertThat(testBidProject.getSpcw07Amt()).isEqualByComparingTo(UPDATED_SPCW_07_AMT);
//        assertThat(testBidProject.getSpcw07Nm()).isEqualTo(UPDATED_SPCW_07_NM);
//        assertThat(testBidProject.getSpcw08Amt()).isEqualByComparingTo(DEFAULT_SPCW_08_AMT);
//        assertThat(testBidProject.getSpcw08Nm()).isEqualTo(UPDATED_SPCW_08_NM);
//        assertThat(testBidProject.getSpcw09Amt()).isEqualByComparingTo(DEFAULT_SPCW_09_AMT);
//        assertThat(testBidProject.getSpcw09Nm()).isEqualTo(UPDATED_SPCW_09_NM);
//        assertThat(testBidProject.getSpcw10Amt()).isEqualByComparingTo(DEFAULT_SPCW_10_AMT);
//        assertThat(testBidProject.getSpcw10Nm()).isEqualTo(DEFAULT_SPCW_10_NM);
//        assertThat(testBidProject.getSpcw11Amt()).isEqualByComparingTo(DEFAULT_SPCW_11_AMT);
//        assertThat(testBidProject.getSpcw11Nm()).isEqualTo(DEFAULT_SPCW_11_NM);
//        assertThat(testBidProject.getSpcw12Amt()).isEqualByComparingTo(UPDATED_SPCW_12_AMT);
//        assertThat(testBidProject.getSpcw12Nm()).isEqualTo(DEFAULT_SPCW_12_NM);
//        assertThat(testBidProject.getJob01Type()).isEqualTo(DEFAULT_JOB_01_TYPE);
//        assertThat(testBidProject.getJob02Type()).isEqualTo(DEFAULT_JOB_02_TYPE);
//        assertThat(testBidProject.getJob03Type()).isEqualTo(UPDATED_JOB_03_TYPE);
//        assertThat(testBidProject.getJob04Type()).isEqualTo(UPDATED_JOB_04_TYPE);
//        assertThat(testBidProject.getJob05Type()).isEqualTo(DEFAULT_JOB_05_TYPE);
//        assertThat(testBidProject.getJob06Type()).isEqualTo(DEFAULT_JOB_06_TYPE);
//        assertThat(testBidProject.getJob07Type()).isEqualTo(UPDATED_JOB_07_TYPE);
//        assertThat(testBidProject.getJob08Type()).isEqualTo(DEFAULT_JOB_08_TYPE);
//        assertThat(testBidProject.getJob09Type()).isEqualTo(UPDATED_JOB_09_TYPE);
//        assertThat(testBidProject.getJob10Type()).isEqualTo(UPDATED_JOB_10_TYPE);
//        assertThat(testBidProject.getJob11Type()).isEqualTo(DEFAULT_JOB_11_TYPE);
//        assertThat(testBidProject.getJob12Type()).isEqualTo(DEFAULT_JOB_12_TYPE);
//        assertThat(testBidProject.getJob13Type()).isEqualTo(DEFAULT_JOB_13_TYPE);
//        assertThat(testBidProject.getJob14Type()).isEqualTo(UPDATED_JOB_14_TYPE);
//        assertThat(testBidProject.getSemset()).isEqualTo(DEFAULT_SEMSET);
//        assertThat(testBidProject.getIsRcb()).isEqualTo(UPDATED_IS_RCB);
//        assertThat(testBidProject.getSorc1()).isEqualByComparingTo(DEFAULT_SORC_1);
//        assertThat(testBidProject.getSorc1b()).isEqualByComparingTo(DEFAULT_SORC_1_B);
//        assertThat(testBidProject.getSorc1c()).isEqualByComparingTo(DEFAULT_SORC_1_C);
//        assertThat(testBidProject.getSorc6()).isEqualByComparingTo(UPDATED_SORC_6);
//        assertThat(testBidProject.getSorc3e()).isEqualByComparingTo(UPDATED_SORC_3_E);
//        assertThat(testBidProject.getSorc1d()).isEqualByComparingTo(DEFAULT_SORC_1_D);
//        assertThat(testBidProject.getSorc2()).isEqualByComparingTo(DEFAULT_SORC_2);
//        assertThat(testBidProject.getSorc3()).isEqualByComparingTo(DEFAULT_SORC_3);
//        assertThat(testBidProject.getSorc3a()).isEqualByComparingTo(UPDATED_SORC_3_A);
//        assertThat(testBidProject.getSorc4()).isEqualByComparingTo(UPDATED_SORC_4);
//        assertThat(testBidProject.getSorc5()).isEqualByComparingTo(DEFAULT_SORC_5);
//        assertThat(testBidProject.getSorc3b()).isEqualByComparingTo(UPDATED_SORC_3_B);
//        assertThat(testBidProject.getSorc3c()).isEqualByComparingTo(UPDATED_SORC_3_C);
//        assertThat(testBidProject.getSorc3d()).isEqualByComparingTo(DEFAULT_SORC_3_D);
//        assertThat(testBidProject.getSorc3b2()).isEqualByComparingTo(UPDATED_SORC_3_B_2);
//        assertThat(testBidProject.getSorc3b3()).isEqualByComparingTo(DEFAULT_SORC_3_B_3);
//        assertThat(testBidProject.getSorc3b4()).isEqualByComparingTo(UPDATED_SORC_3_B_4);
//        assertThat(testBidProject.getSorc3b5()).isEqualByComparingTo(UPDATED_SORC_3_B_5);
//        assertThat(testBidProject.getSorc3c1()).isEqualByComparingTo(UPDATED_SORC_3_C_1);
//        assertThat(testBidProject.getSorc3d1()).isEqualByComparingTo(UPDATED_SORC_3_D_1);
//        assertThat(testBidProject.getSorc3d3()).isEqualByComparingTo(UPDATED_SORC_3_D_3);
//        assertThat(testBidProject.getSorc3d4()).isEqualByComparingTo(DEFAULT_SORC_3_D_4);
//        assertThat(testBidProject.getSorc7()).isEqualByComparingTo(DEFAULT_SORC_7);
//        assertThat(testBidProject.getSorc7Use()).isEqualByComparingTo(UPDATED_SORC_7_USE);
//        assertThat(testBidProject.getSorc8()).isEqualByComparingTo(DEFAULT_SORC_8);
//        assertThat(testBidProject.getSorc7Use2()).isEqualByComparingTo(UPDATED_SORC_7_USE_2);
//        assertThat(testBidProject.getSorc7Use3()).isEqualByComparingTo(UPDATED_SORC_7_USE_3);
//        assertThat(testBidProject.getOpenYn()).isEqualTo(UPDATED_OPEN_YN);
//        assertThat(testBidProject.getwKind()).isEqualTo(DEFAULT_W_KIND);
//        assertThat(testBidProject.getwDays()).isEqualTo(UPDATED_W_DAYS);
//        assertThat(testBidProject.getwRemk()).isEqualTo(UPDATED_W_REMK);
//        assertThat(testBidProject.getbCode()).isEqualTo(DEFAULT_B_CODE);
//        assertThat(testBidProject.getTotAmt()).isEqualByComparingTo(UPDATED_TOT_AMT);
//        assertThat(testBidProject.getBdgt1()).isEqualByComparingTo(DEFAULT_BDGT_1);
//        assertThat(testBidProject.getIgtct()).isEqualByComparingTo(UPDATED_IGTCT);
//        assertThat(testBidProject.getOthct()).isEqualByComparingTo(UPDATED_OTHCT);
//        assertThat(testBidProject.getRsvct()).isEqualByComparingTo(UPDATED_RSVCT);
//        assertThat(testBidProject.getPoll()).isEqualByComparingTo(DEFAULT_POLL);
//        assertThat(testBidProject.getPollNo()).isEqualTo(DEFAULT_POLL_NO);
//        assertThat(testBidProject.getOthMon()).isEqualByComparingTo(UPDATED_OTH_MON);
//        assertThat(testBidProject.getGyResn()).isEqualTo(DEFAULT_GY_RESN);
//        assertThat(testBidProject.getDrut()).isEqualTo(UPDATED_DRUT);
//        assertThat(testBidProject.getDrct()).isEqualByComparingTo(UPDATED_DRCT);
//        assertThat(testBidProject.getDsrm()).isEqualTo(DEFAULT_DSRM);
//        assertThat(testBidProject.getScrm()).isEqualTo(DEFAULT_SCRM);
//        assertThat(testBidProject.getScrr()).isEqualTo(UPDATED_SCRR);
//        assertThat(testBidProject.getScnum()).isEqualTo(DEFAULT_SCNUM);
//        assertThat(testBidProject.getPcmut()).isEqualTo(UPDATED_PCMUT);
//        assertThat(testBidProject.getCcnum()).isEqualTo(DEFAULT_CCNUM);
//        assertThat(testBidProject.getIssStartDate()).isEqualTo(DEFAULT_ISS_START_DATE);
//        assertThat(testBidProject.getIssEndDate()).isEqualTo(UPDATED_ISS_END_DATE);
//        assertThat(testBidProject.getIssCost()).isEqualByComparingTo(UPDATED_ISS_COST);
//        assertThat(testBidProject.getIssNo()).isEqualTo(DEFAULT_ISS_NO);
//        assertThat(testBidProject.getIssRmk()).isEqualTo(UPDATED_ISS_RMK);
//        assertThat(testBidProject.getPdsDate()).isEqualTo(DEFAULT_PDS_DATE);
//        assertThat(testBidProject.getDsDate()).isEqualTo(DEFAULT_DS_DATE);
//        assertThat(testBidProject.getAdsDate()).isEqualTo(UPDATED_ADS_DATE);
//        assertThat(testBidProject.getGetlDate()).isEqualTo(UPDATED_GETL_DATE);
//        assertThat(testBidProject.getAgetlDate()).isEqualTo(DEFAULT_AGETL_DATE);
//        assertThat(testBidProject.getIsSpay()).isEqualTo(UPDATED_IS_SPAY);
//        assertThat(testBidProject.getIpe()).isEqualTo(UPDATED_IPE);
//        assertThat(testBidProject.getIpeY()).isEqualTo(UPDATED_IPE_Y);
//        assertThat(testBidProject.getIpeN()).isEqualTo(DEFAULT_IPE_N);
//        assertThat(testBidProject.getIsNlic()).isEqualTo(DEFAULT_IS_NLIC);
//        assertThat(testBidProject.getIsDlic()).isEqualTo(UPDATED_IS_DLIC);
//        assertThat(testBidProject.getLicNo()).isEqualTo(UPDATED_LIC_NO);
//        assertThat(testBidProject.getLicDate()).isEqualTo(UPDATED_LIC_DATE);
//        assertThat(testBidProject.getAlicDate()).isEqualTo(DEFAULT_ALIC_DATE);
//        assertThat(testBidProject.getOpDate()).isEqualTo(DEFAULT_OP_DATE);
//        assertThat(testBidProject.getAopDate()).isEqualTo(DEFAULT_AOP_DATE);
//        assertThat(testBidProject.getPckDate()).isEqualTo(DEFAULT_PCK_DATE);
//        assertThat(testBidProject.getAckDate()).isEqualTo(DEFAULT_ACK_DATE);
//        assertThat(testBidProject.getPscDate()).isEqualTo(DEFAULT_PSC_DATE);
//        assertThat(testBidProject.getAscDate()).isEqualTo(DEFAULT_ASC_DATE);
//        assertThat(testBidProject.getPctDate()).isEqualTo(DEFAULT_PCT_DATE);
//        assertThat(testBidProject.getActDate()).isEqualTo(UPDATED_ACT_DATE);
//        assertThat(testBidProject.getCtmeth()).isEqualTo(UPDATED_CTMETH);
//        assertThat(testBidProject.getActmth()).isEqualTo(DEFAULT_ACTMTH);
//        assertThat(testBidProject.getSpecPurch()).isEqualTo(DEFAULT_SPEC_PURCH);
//        assertThat(testBidProject.getIsIntl()).isEqualTo(DEFAULT_IS_INTL);
//        assertThat(testBidProject.getTctmth()).isEqualTo(DEFAULT_TCTMTH);
//        assertThat(testBidProject.getBdgt2()).isEqualByComparingTo(UPDATED_BDGT_2);
//        assertThat(testBidProject.getBdgt3()).isEqualByComparingTo(DEFAULT_BDGT_3);
//        assertThat(testBidProject.getMctmth()).isEqualTo(DEFAULT_MCTMTH);
//        assertThat(testBidProject.getCcnt3()).isEqualTo(DEFAULT_CCNT_3);
//        assertThat(testBidProject.getIsPer()).isEqualTo(UPDATED_IS_PER);
//        assertThat(testBidProject.getCtSum()).isEqualByComparingTo(UPDATED_CT_SUM);
//        assertThat(testBidProject.getCtSumF()).isEqualByComparingTo(UPDATED_CT_SUM_F);
//        assertThat(testBidProject.getCtSumU()).isEqualByComparingTo(DEFAULT_CT_SUM_U);
//        assertThat(testBidProject.getCtmo()).isEqualTo(UPDATED_CTMO);
//        assertThat(testBidProject.getCtSumD()).isEqualByComparingTo(DEFAULT_CT_SUM_D);
//        assertThat(testBidProject.getCntid()).isEqualTo(DEFAULT_CNTID);
//        assertThat(testBidProject.getPays()).isEqualByComparingTo(DEFAULT_PAYS);
//        assertThat(testBidProject.getPmsSumT()).isEqualByComparingTo(UPDATED_PMS_SUM_T);
//        assertThat(testBidProject.getPmsType()).isEqualTo(UPDATED_PMS_TYPE);
//        assertThat(testBidProject.getPaymth()).isEqualTo(DEFAULT_PAYMTH);
//        assertThat(testBidProject.getForeignYn()).isEqualTo(DEFAULT_FOREIGN_YN);
//        assertThat(testBidProject.getCsDate()).isEqualTo(DEFAULT_CS_DATE);
//        assertThat(testBidProject.getAsDate()).isEqualTo(UPDATED_AS_DATE);
//        assertThat(testBidProject.getAsuDate()).isEqualTo(UPDATED_ASU_DATE);
//        assertThat(testBidProject.getCeDate()).isEqualTo(UPDATED_CE_DATE);
//        assertThat(testBidProject.getCeDateF()).isEqualTo(DEFAULT_CE_DATE_F);
//        assertThat(testBidProject.getAeDate()).isEqualTo(UPDATED_AE_DATE);
//        assertThat(testBidProject.getFcost2()).isEqualByComparingTo(DEFAULT_FCOST_2);
//        assertThat(testBidProject.getFcsDate()).isEqualTo(UPDATED_FCS_DATE);
//        assertThat(testBidProject.getAfcsDate()).isEqualTo(UPDATED_AFCS_DATE);
//        assertThat(testBidProject.getFcost()).isEqualByComparingTo(DEFAULT_FCOST);
//        assertThat(testBidProject.getIsCkp()).isEqualTo(UPDATED_IS_CKP);
//        assertThat(testBidProject.getIsCkp2()).isEqualTo(DEFAULT_IS_CKP_2);
//        assertThat(testBidProject.getIsCkp3()).isEqualTo(DEFAULT_IS_CKP_3);
//        assertThat(testBidProject.getCkpMoney()).isEqualByComparingTo(DEFAULT_CKP_MONEY);
//        assertThat(testBidProject.getCkpUnit()).isEqualTo(DEFAULT_CKP_UNIT);
//        assertThat(testBidProject.getCkpDate()).isEqualTo(DEFAULT_CKP_DATE);
//        assertThat(testBidProject.getCkpCnt()).isEqualByComparingTo(DEFAULT_CKP_CNT);
//        assertThat(testBidProject.getCkpNum()).isEqualTo(UPDATED_CKP_NUM);
//        assertThat(testBidProject.getCkaUnit()).isEqualTo(UPDATED_CKA_UNIT);
//        assertThat(testBidProject.getCkaDate()).isEqualTo(UPDATED_CKA_DATE);
//        assertThat(testBidProject.getCkaCnt()).isEqualByComparingTo(DEFAULT_CKA_CNT);
//        assertThat(testBidProject.getCkaNum()).isEqualTo(DEFAULT_CKA_NUM);
//        assertThat(testBidProject.getCkdUnit()).isEqualTo(UPDATED_CKD_UNIT);
//        assertThat(testBidProject.getCkdDate()).isEqualTo(DEFAULT_CKD_DATE);
//        assertThat(testBidProject.getCkdCnt()).isEqualByComparingTo(DEFAULT_CKD_CNT);
//        assertThat(testBidProject.getCkdNum()).isEqualTo(UPDATED_CKD_NUM);
//        assertThat(testBidProject.getWorkMemo()).isEqualTo(UPDATED_WORK_MEMO);
//        assertThat(testBidProject.getHotLine()).isEqualTo(UPDATED_HOT_LINE);
//        assertThat(testBidProject.getLnkCode2()).isEqualTo(DEFAULT_LNK_CODE_2);
//        assertThat(testBidProject.getLnkCode3()).isEqualTo(DEFAULT_LNK_CODE_3);
//        assertThat(testBidProject.getLnkNo2()).isEqualTo(DEFAULT_LNK_NO_2);
//        assertThat(testBidProject.getCcnt1()).isEqualTo(UPDATED_CCNT_1);
//        assertThat(testBidProject.getTypeCode2()).isEqualTo(DEFAULT_TYPE_CODE_2);
//        assertThat(testBidProject.getDeptName()).isEqualTo(DEFAULT_DEPT_NAME);
//        assertThat(testBidProject.getAopCnt()).isEqualTo(UPDATED_AOP_CNT);
//        assertThat(testBidProject.getUniqueKey()).isEqualTo(DEFAULT_UNIQUE_KEY);
//        assertThat(testBidProject.getIsBidstr()).isEqualTo(UPDATED_IS_BIDSTR);
//        assertThat(testBidProject.getIsEngineer()).isEqualTo(DEFAULT_IS_ENGINEER);
//        assertThat(testBidProject.getName0()).isEqualTo(DEFAULT_NAME_0);
//        assertThat(testBidProject.getCtSum0()).isEqualByComparingTo(UPDATED_CT_SUM_0);
//        assertThat(testBidProject.getBdgt10()).isEqualByComparingTo(UPDATED_BDGT_10);
//        assertThat(testBidProject.getStartDate()).isEqualTo(UPDATED_START_DATE);
//        assertThat(testBidProject.getEndDate()).isEqualTo(DEFAULT_END_DATE);
//        assertThat(testBidProject.getBatch()).isEqualTo(DEFAULT_BATCH);
//        assertThat(testBidProject.getNocntDay()).isEqualTo(UPDATED_NOCNT_DAY);
//        assertThat(testBidProject.getStatus()).isEqualTo(UPDATED_STATUS);
//        assertThat(testBidProject.getAokDate()).isEqualTo(UPDATED_AOK_DATE);
//        assertThat(testBidProject.getFcost3()).isEqualByComparingTo(DEFAULT_FCOST_3);
//        assertThat(testBidProject.getFinWays()).isEqualTo(DEFAULT_FIN_WAYS);
//        assertThat(testBidProject.getAworkDay()).isEqualTo(UPDATED_AWORK_DAY);
//        assertThat(testBidProject.getFastDay()).isEqualTo(UPDATED_FAST_DAY);
//        assertThat(testBidProject.getOverDay()).isEqualTo(UPDATED_OVER_DAY);
//        assertThat(testBidProject.getOverDayY()).isEqualTo(UPDATED_OVER_DAY_Y);
//        assertThat(testBidProject.getOverFee()).isEqualByComparingTo(DEFAULT_OVER_FEE);
//        assertThat(testBidProject.getOtherFee()).isEqualByComparingTo(UPDATED_OTHER_FEE);
//        assertThat(testBidProject.getPunshFee()).isEqualByComparingTo(DEFAULT_PUNSH_FEE);
//        assertThat(testBidProject.getTm2x()).isEqualTo(DEFAULT_TM_2_X);
//        assertThat(testBidProject.getTm2y()).isEqualTo(DEFAULT_TM_2_Y);
//        assertThat(testBidProject.getTm2x2()).isEqualTo(DEFAULT_TM_2_X_2);
//        assertThat(testBidProject.getTm2y2()).isEqualTo(UPDATED_TM_2_Y_2);
//        assertThat(testBidProject.getTm2x3()).isEqualTo(UPDATED_TM_2_X_3);
//        assertThat(testBidProject.getTm2y3()).isEqualTo(UPDATED_TM_2_Y_3);
//        assertThat(testBidProject.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
//        assertThat(testBidProject.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
//        assertThat(testBidProject.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
//        assertThat(testBidProject.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//        assertThat(testBidProject.getDsts()).isEqualTo(DEFAULT_DSTS);
//        assertThat(testBidProject.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
//        assertThat(testBidProject.getuIp()).isEqualTo(UPDATED_U_IP);
//        assertThat(testBidProject.getCan()).isEqualTo(UPDATED_CAN);
//        assertThat(testBidProject.getSrut()).isEqualTo(UPDATED_SRUT);
//        assertThat(testBidProject.getAnoct()).isEqualByComparingTo(DEFAULT_ANOCT);
//        assertThat(testBidProject.getCepdNo()).isEqualTo(DEFAULT_CEPD_NO);
//        assertThat(testBidProject.getSubNo()).isEqualTo(DEFAULT_SUB_NO);
//        assertThat(testBidProject.getScnt()).isEqualTo(UPDATED_SCNT);
//        assertThat(testBidProject.getDsdm()).isEqualTo(DEFAULT_DSDM);
//        assertThat(testBidProject.getPreson()).isEqualTo(DEFAULT_PRESON);
//        assertThat(testBidProject.getPsignDate()).isEqualTo(UPDATED_PSIGN_DATE);
//        assertThat(testBidProject.getAsignDate()).isEqualTo(UPDATED_ASIGN_DATE);
//        assertThat(testBidProject.getTrDate()).isEqualTo(DEFAULT_TR_DATE);
//        assertThat(testBidProject.getConfIn()).isEqualByComparingTo(DEFAULT_CONF_IN);
//        assertThat(testBidProject.getuKey()).isEqualTo(UPDATED_U_KEY);
//        assertThat(testBidProject.getTpcoth()).isEqualTo(UPDATED_TPCOTH);
//        assertThat(testBidProject.getPcmct()).isEqualByComparingTo(UPDATED_PCMCT);
//        assertThat(testBidProject.getWkutOld()).isEqualTo(DEFAULT_WKUT_OLD);
//        assertThat(testBidProject.getPccMemo()).isEqualTo(DEFAULT_PCC_MEMO);
//        assertThat(testBidProject.getBgbSumt()).isEqualByComparingTo(UPDATED_BGB_SUMT);
//        assertThat(testBidProject.getBgbDate()).isEqualTo(UPDATED_BGB_DATE);
//        assertThat(testBidProject.getMainItem()).isEqualTo(DEFAULT_MAIN_ITEM);
//        assertThat(testBidProject.getmDesc()).isEqualTo(UPDATED_M_DESC);
//        assertThat(testBidProject.getmRmk()).isEqualTo(UPDATED_M_RMK);
//        assertThat(testBidProject.getPmrSumt()).isEqualByComparingTo(DEFAULT_PMR_SUMT);
//        assertThat(testBidProject.getPmrDate()).isEqualTo(DEFAULT_PMR_DATE);
//        assertThat(testBidProject.getPmrResm()).isEqualTo(UPDATED_PMR_RESM);
//    }

    @Test
    @Transactional
    void fullUpdateBidProjectWithPatch() throws Exception {
        // Initialize the database
        bidProjectRepository.saveAndFlush(bidProject);

        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();

        // Update the bidProject using partial update
        BidProject partialUpdatedBidProject = new BidProject();
        partialUpdatedBidProject.setId(bidProject.getId());

        partialUpdatedBidProject
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .name(UPDATED_NAME)
            .isAdd92(UPDATED_IS_ADD_92)
            .pntCtrl(UPDATED_PNT_CTRL)
            .typeCode(UPDATED_TYPE_CODE)
            .isUnion(UPDATED_IS_UNION)
            .placeCode(UPDATED_PLACE_CODE)
            .place(UPDATED_PLACE)
            .bosCode(UPDATED_BOS_CODE)
            .depCode(UPDATED_DEP_CODE)
            .isBg(UPDATED_IS_BG)
            .plnCode(UPDATED_PLN_CODE)
            .plnNo(UPDATED_PLN_NO)
            .plnUnicode(UPDATED_PLN_UNICODE)
            .bos2Code(UPDATED_BOS_2_CODE)
            .bos2Rate(UPDATED_BOS_2_RATE)
            .bos2bCode(UPDATED_BOS_2_B_CODE)
            .bos2bRate(UPDATED_BOS_2_B_RATE)
            .bos2cCode(UPDATED_BOS_2_C_CODE)
            .bos2cRate(UPDATED_BOS_2_C_RATE)
            .bos2dCode(UPDATED_BOS_2_D_CODE)
            .bos2dRate(UPDATED_BOS_2_D_RATE)
            .lnkCode(UPDATED_LNK_CODE)
            .ccnt2(UPDATED_CCNT_2)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL)
            .eMail(UPDATED_E_MAIL)
            .wrate(UPDATED_WRATE)
            .contain(UPDATED_CONTAIN)
            .pfunc(UPDATED_PFUNC)
            .benf(UPDATED_BENF)
            .pstand(UPDATED_PSTAND)
            .pqty(UPDATED_PQTY)
            .untnd(UPDATED_UNTND)
            .isSpcw(UPDATED_IS_SPCW)
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
            .spcw01Amt(UPDATED_SPCW_01_AMT)
            .spcw01Nm(UPDATED_SPCW_01_NM)
            .spcw02Amt(UPDATED_SPCW_02_AMT)
            .spcw02Nm(UPDATED_SPCW_02_NM)
            .spcw03Amt(UPDATED_SPCW_03_AMT)
            .spcw03Nm(UPDATED_SPCW_03_NM)
            .spcw04Amt(UPDATED_SPCW_04_AMT)
            .spcw04Nm(UPDATED_SPCW_04_NM)
            .spcw05Amt(UPDATED_SPCW_05_AMT)
            .spcw05Nm(UPDATED_SPCW_05_NM)
            .spcw06Amt(UPDATED_SPCW_06_AMT)
            .spcw06Nm(UPDATED_SPCW_06_NM)
            .spcw07Amt(UPDATED_SPCW_07_AMT)
            .spcw07Nm(UPDATED_SPCW_07_NM)
            .spcw08Amt(UPDATED_SPCW_08_AMT)
            .spcw08Nm(UPDATED_SPCW_08_NM)
            .spcw09Amt(UPDATED_SPCW_09_AMT)
            .spcw09Nm(UPDATED_SPCW_09_NM)
            .spcw10Amt(UPDATED_SPCW_10_AMT)
            .spcw10Nm(UPDATED_SPCW_10_NM)
            .spcw11Amt(UPDATED_SPCW_11_AMT)
            .spcw11Nm(UPDATED_SPCW_11_NM)
            .spcw12Amt(UPDATED_SPCW_12_AMT)
            .spcw12Nm(UPDATED_SPCW_12_NM)
            .job01Type(UPDATED_JOB_01_TYPE)
            .job02Type(UPDATED_JOB_02_TYPE)
            .job03Type(UPDATED_JOB_03_TYPE)
            .job04Type(UPDATED_JOB_04_TYPE)
            .job05Type(UPDATED_JOB_05_TYPE)
            .job06Type(UPDATED_JOB_06_TYPE)
            .job07Type(UPDATED_JOB_07_TYPE)
            .job08Type(UPDATED_JOB_08_TYPE)
            .job09Type(UPDATED_JOB_09_TYPE)
            .job10Type(UPDATED_JOB_10_TYPE)
            .job11Type(UPDATED_JOB_11_TYPE)
            .job12Type(UPDATED_JOB_12_TYPE)
            .job13Type(UPDATED_JOB_13_TYPE)
            .job14Type(UPDATED_JOB_14_TYPE)
            .semset(UPDATED_SEMSET)
            .isRcb(UPDATED_IS_RCB)
            .sorc1(UPDATED_SORC_1)
            .sorc1b(UPDATED_SORC_1_B)
            .sorc1c(UPDATED_SORC_1_C)
            .sorc6(UPDATED_SORC_6)
            .sorc3e(UPDATED_SORC_3_E)
            .sorc1d(UPDATED_SORC_1_D)
            .sorc2(UPDATED_SORC_2)
            .sorc3(UPDATED_SORC_3)
            .sorc3a(UPDATED_SORC_3_A)
            .sorc4(UPDATED_SORC_4)
            .sorc5(UPDATED_SORC_5)
            .sorc3b(UPDATED_SORC_3_B)
            .sorc3c(UPDATED_SORC_3_C)
            .sorc3d(UPDATED_SORC_3_D)
            .sorc3b2(UPDATED_SORC_3_B_2)
            .sorc3b3(UPDATED_SORC_3_B_3)
            .sorc3b4(UPDATED_SORC_3_B_4)
            .sorc3b5(UPDATED_SORC_3_B_5)
            .sorc3c1(UPDATED_SORC_3_C_1)
            .sorc3d1(UPDATED_SORC_3_D_1)
            .sorc3d3(UPDATED_SORC_3_D_3)
            .sorc3d4(UPDATED_SORC_3_D_4)
            .sorc7(UPDATED_SORC_7)
            .sorc7Use(UPDATED_SORC_7_USE)
            .sorc8(UPDATED_SORC_8)
            .sorc7Use2(UPDATED_SORC_7_USE_2)
            .sorc7Use3(UPDATED_SORC_7_USE_3)
            .openYn(UPDATED_OPEN_YN)
            .wKind(UPDATED_W_KIND)
            .wDays(UPDATED_W_DAYS)
            .wRemk(UPDATED_W_REMK)
            .bCode(UPDATED_B_CODE)
            .totAmt(UPDATED_TOT_AMT)
            .bdgt1(UPDATED_BDGT_1)
            .igtct(UPDATED_IGTCT)
            .othct(UPDATED_OTHCT)
            .rsvct(UPDATED_RSVCT)
            .poll(UPDATED_POLL)
            .pollNo(UPDATED_POLL_NO)
            .othMon(UPDATED_OTH_MON)
            .gyResn(UPDATED_GY_RESN)
            .drut(UPDATED_DRUT)
            .drct(UPDATED_DRCT)
            .dsrm(UPDATED_DSRM)
            .scrm(UPDATED_SCRM)
            .scrr(UPDATED_SCRR)
            .scnum(UPDATED_SCNUM)
            .pcmut(UPDATED_PCMUT)
            .ccnum(UPDATED_CCNUM)
            .issStartDate(UPDATED_ISS_START_DATE)
            .issEndDate(UPDATED_ISS_END_DATE)
            .issCost(UPDATED_ISS_COST)
            .issNo(UPDATED_ISS_NO)
            .issRmk(UPDATED_ISS_RMK)
            .pdsDate(UPDATED_PDS_DATE)
            .dsDate(UPDATED_DS_DATE)
            .adsDate(UPDATED_ADS_DATE)
            .getlDate(UPDATED_GETL_DATE)
            .agetlDate(UPDATED_AGETL_DATE)
            .isSpay(UPDATED_IS_SPAY)
            .ipe(UPDATED_IPE)
            .ipeY(UPDATED_IPE_Y)
            .ipeN(UPDATED_IPE_N)
            .isNlic(UPDATED_IS_NLIC)
            .isDlic(UPDATED_IS_DLIC)
            .licNo(UPDATED_LIC_NO)
            .licDate(UPDATED_LIC_DATE)
            .alicDate(UPDATED_ALIC_DATE)
            .opDate(UPDATED_OP_DATE)
            .aopDate(UPDATED_AOP_DATE)
            .pckDate(UPDATED_PCK_DATE)
            .ackDate(UPDATED_ACK_DATE)
            .pscDate(UPDATED_PSC_DATE)
            .ascDate(UPDATED_ASC_DATE)
            .pctDate(UPDATED_PCT_DATE)
            .actDate(UPDATED_ACT_DATE)
            .ctmeth(UPDATED_CTMETH)
            .actmth(UPDATED_ACTMTH)
            .specPurch(UPDATED_SPEC_PURCH)
            .isIntl(UPDATED_IS_INTL)
            .tctmth(UPDATED_TCTMTH)
            .bdgt2(UPDATED_BDGT_2)
            .bdgt3(UPDATED_BDGT_3)
            .mctmth(UPDATED_MCTMTH)
            .ccnt3(UPDATED_CCNT_3)
            .isPer(UPDATED_IS_PER)
            .ctSum(UPDATED_CT_SUM)
            .ctSumF(UPDATED_CT_SUM_F)
            .ctSumU(UPDATED_CT_SUM_U)
            .ctmo(UPDATED_CTMO)
            .ctSumD(UPDATED_CT_SUM_D)
            .cntid(UPDATED_CNTID)
            .pays(UPDATED_PAYS)
            .pmsSumT(UPDATED_PMS_SUM_T)
            .pmsType(UPDATED_PMS_TYPE)
            .paymth(UPDATED_PAYMTH)
            .foreignYn(UPDATED_FOREIGN_YN)
            .csDate(UPDATED_CS_DATE)
            .asDate(UPDATED_AS_DATE)
            .asuDate(UPDATED_ASU_DATE)
            .ceDate(UPDATED_CE_DATE)
            .ceDateF(UPDATED_CE_DATE_F)
            .aeDate(UPDATED_AE_DATE)
            .fcost2(UPDATED_FCOST_2)
            .fcsDate(UPDATED_FCS_DATE)
            .afcsDate(UPDATED_AFCS_DATE)
            .fcost(UPDATED_FCOST)
            .isCkp(UPDATED_IS_CKP)
            .isCkp2(UPDATED_IS_CKP_2)
            .isCkp3(UPDATED_IS_CKP_3)
            .ckpMoney(UPDATED_CKP_MONEY)
            .ckpUnit(UPDATED_CKP_UNIT)
            .ckpDate(UPDATED_CKP_DATE)
            .ckpCnt(UPDATED_CKP_CNT)
            .ckpNum(UPDATED_CKP_NUM)
            .ckaUnit(UPDATED_CKA_UNIT)
            .ckaDate(UPDATED_CKA_DATE)
            .ckaCnt(UPDATED_CKA_CNT)
            .ckaNum(UPDATED_CKA_NUM)
            .ckdUnit(UPDATED_CKD_UNIT)
            .ckdDate(UPDATED_CKD_DATE)
            .ckdCnt(UPDATED_CKD_CNT)
            .ckdNum(UPDATED_CKD_NUM)
            .workMemo(UPDATED_WORK_MEMO)
            .hotLine(UPDATED_HOT_LINE)
            .lnkCode2(UPDATED_LNK_CODE_2)
            .lnkCode3(UPDATED_LNK_CODE_3)
            .lnkNo2(UPDATED_LNK_NO_2)
            .ccnt1(UPDATED_CCNT_1)
            .typeCode2(UPDATED_TYPE_CODE_2)
            .deptName(UPDATED_DEPT_NAME)
            .aopCnt(UPDATED_AOP_CNT)
            .uniqueKey(UPDATED_UNIQUE_KEY)
            .isBidstr(UPDATED_IS_BIDSTR)
            .isEngineer(UPDATED_IS_ENGINEER)
            .name0(UPDATED_NAME_0)
            .ctSum0(UPDATED_CT_SUM_0)
            .bdgt10(UPDATED_BDGT_10)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .batch(UPDATED_BATCH)
            .nocntDay(UPDATED_NOCNT_DAY)
            .status(UPDATED_STATUS)
            .aokDate(UPDATED_AOK_DATE)
            .fcost3(UPDATED_FCOST_3)
            .finWays(UPDATED_FIN_WAYS)
            .aworkDay(UPDATED_AWORK_DAY)
            .fastDay(UPDATED_FAST_DAY)
            .overDay(UPDATED_OVER_DAY)
            .overDayY(UPDATED_OVER_DAY_Y)
            .overFee(UPDATED_OVER_FEE)
            .otherFee(UPDATED_OTHER_FEE)
            .punshFee(UPDATED_PUNSH_FEE)
            .tm2x(UPDATED_TM_2_X)
            .tm2y(UPDATED_TM_2_Y)
            .tm2x2(UPDATED_TM_2_X_2)
            .tm2y2(UPDATED_TM_2_Y_2)
            .tm2x3(UPDATED_TM_2_X_3)
            .tm2y3(UPDATED_TM_2_Y_3)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP)
            .can(UPDATED_CAN)
            .srut(UPDATED_SRUT)
            .anoct(UPDATED_ANOCT)
            .cepdNo(UPDATED_CEPD_NO)
            .subNo(UPDATED_SUB_NO)
            .scnt(UPDATED_SCNT)
            .dsdm(UPDATED_DSDM)
            .preson(UPDATED_PRESON)
            .psignDate(UPDATED_PSIGN_DATE)
            .asignDate(UPDATED_ASIGN_DATE)
            .trDate(UPDATED_TR_DATE)
            .confIn(UPDATED_CONF_IN)
            .uKey(UPDATED_U_KEY)
            .tpcoth(UPDATED_TPCOTH)
            .pcmct(UPDATED_PCMCT)
            .wkutOld(UPDATED_WKUT_OLD)
            .pccMemo(UPDATED_PCC_MEMO)
            .bgbSumt(UPDATED_BGB_SUMT)
            .bgbDate(UPDATED_BGB_DATE)
            .mainItem(UPDATED_MAIN_ITEM)
            .mDesc(UPDATED_M_DESC)
            .mRmk(UPDATED_M_RMK)
            .pmrSumt(UPDATED_PMR_SUMT)
            .pmrDate(UPDATED_PMR_DATE)
            .pmrResm(UPDATED_PMR_RESM);

        restBidProjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidProject.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidProject))
            )
            .andExpect(status().isOk());

        // Validate the BidProject in the database
        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
        BidProject testBidProject = bidProjectList.get(bidProjectList.size() - 1);
        assertThat(testBidProject.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidProject.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidProject.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBidProject.getIsAdd92()).isEqualTo(UPDATED_IS_ADD_92);
        assertThat(testBidProject.getPntCtrl()).isEqualTo(UPDATED_PNT_CTRL);
        assertThat(testBidProject.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testBidProject.getIsUnion()).isEqualTo(UPDATED_IS_UNION);
        assertThat(testBidProject.getPlaceCode()).isEqualTo(UPDATED_PLACE_CODE);
        assertThat(testBidProject.getPlace()).isEqualTo(UPDATED_PLACE);
        assertThat(testBidProject.getBosCode()).isEqualTo(UPDATED_BOS_CODE);
        assertThat(testBidProject.getDepCode()).isEqualTo(UPDATED_DEP_CODE);
        assertThat(testBidProject.getIsBg()).isEqualTo(UPDATED_IS_BG);
        assertThat(testBidProject.getPlnCode()).isEqualTo(UPDATED_PLN_CODE);
        assertThat(testBidProject.getPlnNo()).isEqualTo(UPDATED_PLN_NO);
        assertThat(testBidProject.getPlnUnicode()).isEqualTo(UPDATED_PLN_UNICODE);
        assertThat(testBidProject.getBos2Code()).isEqualTo(UPDATED_BOS_2_CODE);
        assertThat(testBidProject.getBos2Rate()).isEqualByComparingTo(UPDATED_BOS_2_RATE);
        assertThat(testBidProject.getBos2bCode()).isEqualTo(UPDATED_BOS_2_B_CODE);
        assertThat(testBidProject.getBos2bRate()).isEqualByComparingTo(UPDATED_BOS_2_B_RATE);
        assertThat(testBidProject.getBos2cCode()).isEqualTo(UPDATED_BOS_2_C_CODE);
        assertThat(testBidProject.getBos2cRate()).isEqualByComparingTo(UPDATED_BOS_2_C_RATE);
        assertThat(testBidProject.getBos2dCode()).isEqualTo(UPDATED_BOS_2_D_CODE);
        assertThat(testBidProject.getBos2dRate()).isEqualByComparingTo(UPDATED_BOS_2_D_RATE);
        assertThat(testBidProject.getLnkCode()).isEqualTo(UPDATED_LNK_CODE);
        assertThat(testBidProject.getCcnt2()).isEqualTo(UPDATED_CCNT_2);
        assertThat(testBidProject.getMan()).isEqualTo(UPDATED_MAN);
        assertThat(testBidProject.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testBidProject.geteMail()).isEqualTo(UPDATED_E_MAIL);
        assertThat(testBidProject.getWrate()).isEqualByComparingTo(UPDATED_WRATE);
        assertThat(testBidProject.getContain()).isEqualTo(UPDATED_CONTAIN);
        assertThat(testBidProject.getPfunc()).isEqualTo(UPDATED_PFUNC);
        assertThat(testBidProject.getBenf()).isEqualTo(UPDATED_BENF);
        assertThat(testBidProject.getPstand()).isEqualTo(UPDATED_PSTAND);
        assertThat(testBidProject.getPqty()).isEqualTo(UPDATED_PQTY);
        assertThat(testBidProject.getUntnd()).isEqualTo(UPDATED_UNTND);
        assertThat(testBidProject.getIsSpcw()).isEqualTo(UPDATED_IS_SPCW);
        assertThat(testBidProject.getSpcw01()).isEqualTo(UPDATED_SPCW_01);
        assertThat(testBidProject.getSpcw02()).isEqualTo(UPDATED_SPCW_02);
        assertThat(testBidProject.getSpcw03()).isEqualTo(UPDATED_SPCW_03);
        assertThat(testBidProject.getSpcw04()).isEqualTo(UPDATED_SPCW_04);
        assertThat(testBidProject.getSpcw05()).isEqualTo(UPDATED_SPCW_05);
        assertThat(testBidProject.getSpcw06()).isEqualTo(UPDATED_SPCW_06);
        assertThat(testBidProject.getSpcw07()).isEqualTo(UPDATED_SPCW_07);
        assertThat(testBidProject.getSpcw08()).isEqualTo(UPDATED_SPCW_08);
        assertThat(testBidProject.getSpcw09()).isEqualTo(UPDATED_SPCW_09);
        assertThat(testBidProject.getSpcw10()).isEqualTo(UPDATED_SPCW_10);
        assertThat(testBidProject.getSpcw11()).isEqualTo(UPDATED_SPCW_11);
        assertThat(testBidProject.getSpcw12()).isEqualTo(UPDATED_SPCW_12);
        assertThat(testBidProject.getSpcw01Amt()).isEqualByComparingTo(UPDATED_SPCW_01_AMT);
        assertThat(testBidProject.getSpcw01Nm()).isEqualTo(UPDATED_SPCW_01_NM);
        assertThat(testBidProject.getSpcw02Amt()).isEqualByComparingTo(UPDATED_SPCW_02_AMT);
        assertThat(testBidProject.getSpcw02Nm()).isEqualTo(UPDATED_SPCW_02_NM);
        assertThat(testBidProject.getSpcw03Amt()).isEqualByComparingTo(UPDATED_SPCW_03_AMT);
        assertThat(testBidProject.getSpcw03Nm()).isEqualTo(UPDATED_SPCW_03_NM);
        assertThat(testBidProject.getSpcw04Amt()).isEqualByComparingTo(UPDATED_SPCW_04_AMT);
        assertThat(testBidProject.getSpcw04Nm()).isEqualTo(UPDATED_SPCW_04_NM);
        assertThat(testBidProject.getSpcw05Amt()).isEqualByComparingTo(UPDATED_SPCW_05_AMT);
        assertThat(testBidProject.getSpcw05Nm()).isEqualTo(UPDATED_SPCW_05_NM);
        assertThat(testBidProject.getSpcw06Amt()).isEqualByComparingTo(UPDATED_SPCW_06_AMT);
        assertThat(testBidProject.getSpcw06Nm()).isEqualTo(UPDATED_SPCW_06_NM);
        assertThat(testBidProject.getSpcw07Amt()).isEqualByComparingTo(UPDATED_SPCW_07_AMT);
        assertThat(testBidProject.getSpcw07Nm()).isEqualTo(UPDATED_SPCW_07_NM);
        assertThat(testBidProject.getSpcw08Amt()).isEqualByComparingTo(UPDATED_SPCW_08_AMT);
        assertThat(testBidProject.getSpcw08Nm()).isEqualTo(UPDATED_SPCW_08_NM);
        assertThat(testBidProject.getSpcw09Amt()).isEqualByComparingTo(UPDATED_SPCW_09_AMT);
        assertThat(testBidProject.getSpcw09Nm()).isEqualTo(UPDATED_SPCW_09_NM);
        assertThat(testBidProject.getSpcw10Amt()).isEqualByComparingTo(UPDATED_SPCW_10_AMT);
        assertThat(testBidProject.getSpcw10Nm()).isEqualTo(UPDATED_SPCW_10_NM);
        assertThat(testBidProject.getSpcw11Amt()).isEqualByComparingTo(UPDATED_SPCW_11_AMT);
        assertThat(testBidProject.getSpcw11Nm()).isEqualTo(UPDATED_SPCW_11_NM);
        assertThat(testBidProject.getSpcw12Amt()).isEqualByComparingTo(UPDATED_SPCW_12_AMT);
        assertThat(testBidProject.getSpcw12Nm()).isEqualTo(UPDATED_SPCW_12_NM);
        assertThat(testBidProject.getJob01Type()).isEqualTo(UPDATED_JOB_01_TYPE);
        assertThat(testBidProject.getJob02Type()).isEqualTo(UPDATED_JOB_02_TYPE);
        assertThat(testBidProject.getJob03Type()).isEqualTo(UPDATED_JOB_03_TYPE);
        assertThat(testBidProject.getJob04Type()).isEqualTo(UPDATED_JOB_04_TYPE);
        assertThat(testBidProject.getJob05Type()).isEqualTo(UPDATED_JOB_05_TYPE);
        assertThat(testBidProject.getJob06Type()).isEqualTo(UPDATED_JOB_06_TYPE);
        assertThat(testBidProject.getJob07Type()).isEqualTo(UPDATED_JOB_07_TYPE);
        assertThat(testBidProject.getJob08Type()).isEqualTo(UPDATED_JOB_08_TYPE);
        assertThat(testBidProject.getJob09Type()).isEqualTo(UPDATED_JOB_09_TYPE);
        assertThat(testBidProject.getJob10Type()).isEqualTo(UPDATED_JOB_10_TYPE);
        assertThat(testBidProject.getJob11Type()).isEqualTo(UPDATED_JOB_11_TYPE);
        assertThat(testBidProject.getJob12Type()).isEqualTo(UPDATED_JOB_12_TYPE);
        assertThat(testBidProject.getJob13Type()).isEqualTo(UPDATED_JOB_13_TYPE);
        assertThat(testBidProject.getJob14Type()).isEqualTo(UPDATED_JOB_14_TYPE);
        assertThat(testBidProject.getSemset()).isEqualTo(UPDATED_SEMSET);
        assertThat(testBidProject.getIsRcb()).isEqualTo(UPDATED_IS_RCB);
        assertThat(testBidProject.getSorc1()).isEqualByComparingTo(UPDATED_SORC_1);
        assertThat(testBidProject.getSorc1b()).isEqualByComparingTo(UPDATED_SORC_1_B);
        assertThat(testBidProject.getSorc1c()).isEqualByComparingTo(UPDATED_SORC_1_C);
        assertThat(testBidProject.getSorc6()).isEqualByComparingTo(UPDATED_SORC_6);
        assertThat(testBidProject.getSorc3e()).isEqualByComparingTo(UPDATED_SORC_3_E);
        assertThat(testBidProject.getSorc1d()).isEqualByComparingTo(UPDATED_SORC_1_D);
        assertThat(testBidProject.getSorc2()).isEqualByComparingTo(UPDATED_SORC_2);
        assertThat(testBidProject.getSorc3()).isEqualByComparingTo(UPDATED_SORC_3);
        assertThat(testBidProject.getSorc3a()).isEqualByComparingTo(UPDATED_SORC_3_A);
        assertThat(testBidProject.getSorc4()).isEqualByComparingTo(UPDATED_SORC_4);
        assertThat(testBidProject.getSorc5()).isEqualByComparingTo(UPDATED_SORC_5);
        assertThat(testBidProject.getSorc3b()).isEqualByComparingTo(UPDATED_SORC_3_B);
        assertThat(testBidProject.getSorc3c()).isEqualByComparingTo(UPDATED_SORC_3_C);
        assertThat(testBidProject.getSorc3d()).isEqualByComparingTo(UPDATED_SORC_3_D);
        assertThat(testBidProject.getSorc3b2()).isEqualByComparingTo(UPDATED_SORC_3_B_2);
        assertThat(testBidProject.getSorc3b3()).isEqualByComparingTo(UPDATED_SORC_3_B_3);
        assertThat(testBidProject.getSorc3b4()).isEqualByComparingTo(UPDATED_SORC_3_B_4);
        assertThat(testBidProject.getSorc3b5()).isEqualByComparingTo(UPDATED_SORC_3_B_5);
        assertThat(testBidProject.getSorc3c1()).isEqualByComparingTo(UPDATED_SORC_3_C_1);
        assertThat(testBidProject.getSorc3d1()).isEqualByComparingTo(UPDATED_SORC_3_D_1);
        assertThat(testBidProject.getSorc3d3()).isEqualByComparingTo(UPDATED_SORC_3_D_3);
        assertThat(testBidProject.getSorc3d4()).isEqualByComparingTo(UPDATED_SORC_3_D_4);
        assertThat(testBidProject.getSorc7()).isEqualByComparingTo(UPDATED_SORC_7);
        assertThat(testBidProject.getSorc7Use()).isEqualByComparingTo(UPDATED_SORC_7_USE);
        assertThat(testBidProject.getSorc8()).isEqualByComparingTo(UPDATED_SORC_8);
        assertThat(testBidProject.getSorc7Use2()).isEqualByComparingTo(UPDATED_SORC_7_USE_2);
        assertThat(testBidProject.getSorc7Use3()).isEqualByComparingTo(UPDATED_SORC_7_USE_3);
        assertThat(testBidProject.getOpenYn()).isEqualTo(UPDATED_OPEN_YN);
        assertThat(testBidProject.getwKind()).isEqualTo(UPDATED_W_KIND);
        assertThat(testBidProject.getwDays()).isEqualTo(UPDATED_W_DAYS);
        assertThat(testBidProject.getwRemk()).isEqualTo(UPDATED_W_REMK);
        assertThat(testBidProject.getbCode()).isEqualTo(UPDATED_B_CODE);
        assertThat(testBidProject.getTotAmt()).isEqualByComparingTo(UPDATED_TOT_AMT);
        assertThat(testBidProject.getBdgt1()).isEqualByComparingTo(UPDATED_BDGT_1);
        assertThat(testBidProject.getIgtct()).isEqualByComparingTo(UPDATED_IGTCT);
        assertThat(testBidProject.getOthct()).isEqualByComparingTo(UPDATED_OTHCT);
        assertThat(testBidProject.getRsvct()).isEqualByComparingTo(UPDATED_RSVCT);
        assertThat(testBidProject.getPoll()).isEqualByComparingTo(UPDATED_POLL);
        assertThat(testBidProject.getPollNo()).isEqualTo(UPDATED_POLL_NO);
        assertThat(testBidProject.getOthMon()).isEqualByComparingTo(UPDATED_OTH_MON);
        assertThat(testBidProject.getGyResn()).isEqualTo(UPDATED_GY_RESN);
        assertThat(testBidProject.getDrut()).isEqualTo(UPDATED_DRUT);
        assertThat(testBidProject.getDrct()).isEqualByComparingTo(UPDATED_DRCT);
        assertThat(testBidProject.getDsrm()).isEqualTo(UPDATED_DSRM);
        assertThat(testBidProject.getScrm()).isEqualTo(UPDATED_SCRM);
        assertThat(testBidProject.getScrr()).isEqualTo(UPDATED_SCRR);
        assertThat(testBidProject.getScnum()).isEqualTo(UPDATED_SCNUM);
        assertThat(testBidProject.getPcmut()).isEqualTo(UPDATED_PCMUT);
        assertThat(testBidProject.getCcnum()).isEqualTo(UPDATED_CCNUM);
        assertThat(testBidProject.getIssStartDate()).isEqualTo(UPDATED_ISS_START_DATE);
        assertThat(testBidProject.getIssEndDate()).isEqualTo(UPDATED_ISS_END_DATE);
        assertThat(testBidProject.getIssCost()).isEqualByComparingTo(UPDATED_ISS_COST);
        assertThat(testBidProject.getIssNo()).isEqualTo(UPDATED_ISS_NO);
        assertThat(testBidProject.getIssRmk()).isEqualTo(UPDATED_ISS_RMK);
        assertThat(testBidProject.getPdsDate()).isEqualTo(UPDATED_PDS_DATE);
        assertThat(testBidProject.getDsDate()).isEqualTo(UPDATED_DS_DATE);
        assertThat(testBidProject.getAdsDate()).isEqualTo(UPDATED_ADS_DATE);
        assertThat(testBidProject.getGetlDate()).isEqualTo(UPDATED_GETL_DATE);
        assertThat(testBidProject.getAgetlDate()).isEqualTo(UPDATED_AGETL_DATE);
        assertThat(testBidProject.getIsSpay()).isEqualTo(UPDATED_IS_SPAY);
        assertThat(testBidProject.getIpe()).isEqualTo(UPDATED_IPE);
        assertThat(testBidProject.getIpeY()).isEqualTo(UPDATED_IPE_Y);
        assertThat(testBidProject.getIpeN()).isEqualTo(UPDATED_IPE_N);
        assertThat(testBidProject.getIsNlic()).isEqualTo(UPDATED_IS_NLIC);
        assertThat(testBidProject.getIsDlic()).isEqualTo(UPDATED_IS_DLIC);
        assertThat(testBidProject.getLicNo()).isEqualTo(UPDATED_LIC_NO);
        assertThat(testBidProject.getLicDate()).isEqualTo(UPDATED_LIC_DATE);
        assertThat(testBidProject.getAlicDate()).isEqualTo(UPDATED_ALIC_DATE);
        assertThat(testBidProject.getOpDate()).isEqualTo(UPDATED_OP_DATE);
        assertThat(testBidProject.getAopDate()).isEqualTo(UPDATED_AOP_DATE);
        assertThat(testBidProject.getPckDate()).isEqualTo(UPDATED_PCK_DATE);
        assertThat(testBidProject.getAckDate()).isEqualTo(UPDATED_ACK_DATE);
        assertThat(testBidProject.getPscDate()).isEqualTo(UPDATED_PSC_DATE);
        assertThat(testBidProject.getAscDate()).isEqualTo(UPDATED_ASC_DATE);
        assertThat(testBidProject.getPctDate()).isEqualTo(UPDATED_PCT_DATE);
        assertThat(testBidProject.getActDate()).isEqualTo(UPDATED_ACT_DATE);
        assertThat(testBidProject.getCtmeth()).isEqualTo(UPDATED_CTMETH);
        assertThat(testBidProject.getActmth()).isEqualTo(UPDATED_ACTMTH);
        assertThat(testBidProject.getSpecPurch()).isEqualTo(UPDATED_SPEC_PURCH);
        assertThat(testBidProject.getIsIntl()).isEqualTo(UPDATED_IS_INTL);
        assertThat(testBidProject.getTctmth()).isEqualTo(UPDATED_TCTMTH);
        assertThat(testBidProject.getBdgt2()).isEqualByComparingTo(UPDATED_BDGT_2);
        assertThat(testBidProject.getBdgt3()).isEqualByComparingTo(UPDATED_BDGT_3);
        assertThat(testBidProject.getMctmth()).isEqualTo(UPDATED_MCTMTH);
        assertThat(testBidProject.getCcnt3()).isEqualTo(UPDATED_CCNT_3);
        assertThat(testBidProject.getIsPer()).isEqualTo(UPDATED_IS_PER);
        assertThat(testBidProject.getCtSum()).isEqualByComparingTo(UPDATED_CT_SUM);
        assertThat(testBidProject.getCtSumF()).isEqualByComparingTo(UPDATED_CT_SUM_F);
        assertThat(testBidProject.getCtSumU()).isEqualByComparingTo(UPDATED_CT_SUM_U);
        assertThat(testBidProject.getCtmo()).isEqualTo(UPDATED_CTMO);
        assertThat(testBidProject.getCtSumD()).isEqualByComparingTo(UPDATED_CT_SUM_D);
        assertThat(testBidProject.getCntid()).isEqualTo(UPDATED_CNTID);
        assertThat(testBidProject.getPays()).isEqualByComparingTo(UPDATED_PAYS);
        assertThat(testBidProject.getPmsSumT()).isEqualByComparingTo(UPDATED_PMS_SUM_T);
        assertThat(testBidProject.getPmsType()).isEqualTo(UPDATED_PMS_TYPE);
        assertThat(testBidProject.getPaymth()).isEqualTo(UPDATED_PAYMTH);
        assertThat(testBidProject.getForeignYn()).isEqualTo(UPDATED_FOREIGN_YN);
        assertThat(testBidProject.getCsDate()).isEqualTo(UPDATED_CS_DATE);
        assertThat(testBidProject.getAsDate()).isEqualTo(UPDATED_AS_DATE);
        assertThat(testBidProject.getAsuDate()).isEqualTo(UPDATED_ASU_DATE);
        assertThat(testBidProject.getCeDate()).isEqualTo(UPDATED_CE_DATE);
        assertThat(testBidProject.getCeDateF()).isEqualTo(UPDATED_CE_DATE_F);
        assertThat(testBidProject.getAeDate()).isEqualTo(UPDATED_AE_DATE);
        assertThat(testBidProject.getFcost2()).isEqualByComparingTo(UPDATED_FCOST_2);
        assertThat(testBidProject.getFcsDate()).isEqualTo(UPDATED_FCS_DATE);
        assertThat(testBidProject.getAfcsDate()).isEqualTo(UPDATED_AFCS_DATE);
        assertThat(testBidProject.getFcost()).isEqualByComparingTo(UPDATED_FCOST);
        assertThat(testBidProject.getIsCkp()).isEqualTo(UPDATED_IS_CKP);
        assertThat(testBidProject.getIsCkp2()).isEqualTo(UPDATED_IS_CKP_2);
        assertThat(testBidProject.getIsCkp3()).isEqualTo(UPDATED_IS_CKP_3);
        assertThat(testBidProject.getCkpMoney()).isEqualByComparingTo(UPDATED_CKP_MONEY);
        assertThat(testBidProject.getCkpUnit()).isEqualTo(UPDATED_CKP_UNIT);
        assertThat(testBidProject.getCkpDate()).isEqualTo(UPDATED_CKP_DATE);
        assertThat(testBidProject.getCkpCnt()).isEqualByComparingTo(UPDATED_CKP_CNT);
        assertThat(testBidProject.getCkpNum()).isEqualTo(UPDATED_CKP_NUM);
        assertThat(testBidProject.getCkaUnit()).isEqualTo(UPDATED_CKA_UNIT);
        assertThat(testBidProject.getCkaDate()).isEqualTo(UPDATED_CKA_DATE);
        assertThat(testBidProject.getCkaCnt()).isEqualByComparingTo(UPDATED_CKA_CNT);
        assertThat(testBidProject.getCkaNum()).isEqualTo(UPDATED_CKA_NUM);
        assertThat(testBidProject.getCkdUnit()).isEqualTo(UPDATED_CKD_UNIT);
        assertThat(testBidProject.getCkdDate()).isEqualTo(UPDATED_CKD_DATE);
        assertThat(testBidProject.getCkdCnt()).isEqualByComparingTo(UPDATED_CKD_CNT);
        assertThat(testBidProject.getCkdNum()).isEqualTo(UPDATED_CKD_NUM);
        assertThat(testBidProject.getWorkMemo()).isEqualTo(UPDATED_WORK_MEMO);
        assertThat(testBidProject.getHotLine()).isEqualTo(UPDATED_HOT_LINE);
        assertThat(testBidProject.getLnkCode2()).isEqualTo(UPDATED_LNK_CODE_2);
        assertThat(testBidProject.getLnkCode3()).isEqualTo(UPDATED_LNK_CODE_3);
        assertThat(testBidProject.getLnkNo2()).isEqualTo(UPDATED_LNK_NO_2);
        assertThat(testBidProject.getCcnt1()).isEqualTo(UPDATED_CCNT_1);
        assertThat(testBidProject.getTypeCode2()).isEqualTo(UPDATED_TYPE_CODE_2);
        assertThat(testBidProject.getDeptName()).isEqualTo(UPDATED_DEPT_NAME);
        assertThat(testBidProject.getAopCnt()).isEqualTo(UPDATED_AOP_CNT);
        assertThat(testBidProject.getUniqueKey()).isEqualTo(UPDATED_UNIQUE_KEY);
        assertThat(testBidProject.getIsBidstr()).isEqualTo(UPDATED_IS_BIDSTR);
        assertThat(testBidProject.getIsEngineer()).isEqualTo(UPDATED_IS_ENGINEER);
        assertThat(testBidProject.getName0()).isEqualTo(UPDATED_NAME_0);
        assertThat(testBidProject.getCtSum0()).isEqualByComparingTo(UPDATED_CT_SUM_0);
        assertThat(testBidProject.getBdgt1_0()).isEqualByComparingTo(UPDATED_BDGT_10);
        assertThat(testBidProject.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBidProject.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBidProject.getBatch()).isEqualTo(UPDATED_BATCH);
        assertThat(testBidProject.getNocntDay()).isEqualTo(UPDATED_NOCNT_DAY);
        assertThat(testBidProject.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBidProject.getAokDate()).isEqualTo(UPDATED_AOK_DATE);
        assertThat(testBidProject.getFcost3()).isEqualByComparingTo(UPDATED_FCOST_3);
        assertThat(testBidProject.getFinWays()).isEqualTo(UPDATED_FIN_WAYS);
        assertThat(testBidProject.getAworkDay()).isEqualTo(UPDATED_AWORK_DAY);
        assertThat(testBidProject.getFastDay()).isEqualTo(UPDATED_FAST_DAY);
        assertThat(testBidProject.getOverDay()).isEqualTo(UPDATED_OVER_DAY);
        assertThat(testBidProject.getOverDayY()).isEqualTo(UPDATED_OVER_DAY_Y);
        assertThat(testBidProject.getOverFee()).isEqualByComparingTo(UPDATED_OVER_FEE);
        assertThat(testBidProject.getOtherFee()).isEqualByComparingTo(UPDATED_OTHER_FEE);
        assertThat(testBidProject.getPunshFee()).isEqualByComparingTo(UPDATED_PUNSH_FEE);
        assertThat(testBidProject.getTm2x()).isEqualTo(UPDATED_TM_2_X);
        assertThat(testBidProject.getTm2y()).isEqualTo(UPDATED_TM_2_Y);
        assertThat(testBidProject.getTm2x2()).isEqualTo(UPDATED_TM_2_X_2);
        assertThat(testBidProject.getTm2y2()).isEqualTo(UPDATED_TM_2_Y_2);
        assertThat(testBidProject.getTm2x3()).isEqualTo(UPDATED_TM_2_X_3);
        assertThat(testBidProject.getTm2y3()).isEqualTo(UPDATED_TM_2_Y_3);
        assertThat(testBidProject.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidProject.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidProject.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidProject.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidProject.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidProject.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidProject.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidProject.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testBidProject.getSrut()).isEqualTo(UPDATED_SRUT);
        assertThat(testBidProject.getAnoct()).isEqualByComparingTo(UPDATED_ANOCT);
        assertThat(testBidProject.getCepdNo()).isEqualTo(UPDATED_CEPD_NO);
        assertThat(testBidProject.getSubNo()).isEqualTo(UPDATED_SUB_NO);
        assertThat(testBidProject.getScnt()).isEqualTo(UPDATED_SCNT);
        assertThat(testBidProject.getDsdm()).isEqualTo(UPDATED_DSDM);
        assertThat(testBidProject.getPreson()).isEqualTo(UPDATED_PRESON);
        assertThat(testBidProject.getPsignDate()).isEqualTo(UPDATED_PSIGN_DATE);
        assertThat(testBidProject.getAsignDate()).isEqualTo(UPDATED_ASIGN_DATE);
        assertThat(testBidProject.getTrDate()).isEqualTo(UPDATED_TR_DATE);
        assertThat(testBidProject.getConfIn()).isEqualByComparingTo(UPDATED_CONF_IN);
        assertThat(testBidProject.getuKey()).isEqualTo(UPDATED_U_KEY);
        assertThat(testBidProject.getTpcoth()).isEqualTo(UPDATED_TPCOTH);
        assertThat(testBidProject.getPcmct()).isEqualByComparingTo(UPDATED_PCMCT);
        assertThat(testBidProject.getWkutOld()).isEqualTo(UPDATED_WKUT_OLD);
        assertThat(testBidProject.getPccMemo()).isEqualTo(UPDATED_PCC_MEMO);
        assertThat(testBidProject.getBgbSumt()).isEqualByComparingTo(UPDATED_BGB_SUMT);
        assertThat(testBidProject.getBgbDate()).isEqualTo(UPDATED_BGB_DATE);
        assertThat(testBidProject.getMainItem()).isEqualTo(UPDATED_MAIN_ITEM);
        assertThat(testBidProject.getmDesc()).isEqualTo(UPDATED_M_DESC);
        assertThat(testBidProject.getmRmk()).isEqualTo(UPDATED_M_RMK);
        assertThat(testBidProject.getPmrSumt()).isEqualByComparingTo(UPDATED_PMR_SUMT);
        assertThat(testBidProject.getPmrDate()).isEqualTo(UPDATED_PMR_DATE);
        assertThat(testBidProject.getPmrResm()).isEqualTo(UPDATED_PMR_RESM);
    }

//    @Test
//    @Transactional
//    void patchNonExistingBidProject() throws Exception {
//        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();
//        bidProject.setId(count.incrementAndGet());
//
//        // Create the BidProject
//        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidProjectMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidProjectDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidProjectDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidProject in the database
//        List<BidProject> bidProjectList = bidProjectRepository.findAll();
//        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithIdMismatchBidProject() throws Exception {
//        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();
//        bidProject.setId(count.incrementAndGet());
//
//        // Create the BidProject
//        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidProjectMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidProjectDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidProject in the database
//        List<BidProject> bidProjectList = bidProjectRepository.findAll();
//        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamBidProject() throws Exception {
//        int databaseSizeBeforeUpdate = bidProjectRepository.findAll().size();
//        bidProject.setId(count.incrementAndGet());
//
//        // Create the BidProject
//        BidProjectDTO bidProjectDTO = bidProjectMapper.toDto(bidProject);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidProjectMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidProjectDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidProject in the database
//        List<BidProject> bidProjectList = bidProjectRepository.findAll();
//        assertThat(bidProjectList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void deleteBidProject() throws Exception {
        // Initialize the database
        bidProjectRepository.saveAndFlush(bidProject);

        int databaseSizeBeforeDelete = bidProjectRepository.findAll().size();

        // Delete the bidProject
        restBidProjectMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidProject.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidProject> bidProjectList = bidProjectRepository.findAll();
        assertThat(bidProjectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
