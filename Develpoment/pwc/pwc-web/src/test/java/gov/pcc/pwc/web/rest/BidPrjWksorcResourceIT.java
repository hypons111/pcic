package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjWksorc;
import gov.pcc.pwc.repository.BidPrjWksorcRepository;
import gov.pcc.pwc.service.dto.BidPrjWksorcDTO;
import gov.pcc.pwc.service.mapper.BidPrjWksorcMapper;
import java.math.BigDecimal;
import java.time.Instant;
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
 * Integration tests for the {@link BidPrjWksorcResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjWksorcResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_SYR = "AAA";
    private static final String UPDATED_SYR = "BBB";

    private static final String DEFAULT_MONTH = "AA";
    private static final String UPDATED_MONTH = "BB";

    private static final BigDecimal DEFAULT_PERSON_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERSON_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_03 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_03 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_04 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_04 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_05 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_05 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_06 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_06 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_07 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_07 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_08 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_08 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_09 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_09 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_10 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_11 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_11 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_12 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_12 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERSON_A_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_A_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERSON_A_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_A_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_03 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_03 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_04 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_04 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_05 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_05 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_06 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_06 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_07 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_07 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_08 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_08 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_09 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_09 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_10 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_11 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_11 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_12 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_12 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERSON_B_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_B_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERSON_B_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_B_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_03 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_03 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_04 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_04 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_05 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_05 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_06 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_06 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_07 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_07 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_08 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_08 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_09 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_09 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_10 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_11 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_11 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_12 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_12 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERSON_C_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_C_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERSON_C_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_C_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_03 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_03 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_04 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_04 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_05 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_05 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_06 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_06 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_07 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_07 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_08 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_08 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_09 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_09 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_10 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_11 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_11 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_12 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_12 = new BigDecimal(2);

    private static final Integer DEFAULT_PERSON_01_SALARY = 1;
    private static final Integer UPDATED_PERSON_01_SALARY = 2;

    private static final Integer DEFAULT_PERSON_02_SALARY = 1;
    private static final Integer UPDATED_PERSON_02_SALARY = 2;

    private static final Integer DEFAULT_WORKER_01_SALARY = 1;
    private static final Integer UPDATED_WORKER_01_SALARY = 2;

    private static final Integer DEFAULT_WORKER_02_SALARY = 1;
    private static final Integer UPDATED_WORKER_02_SALARY = 2;

    private static final Integer DEFAULT_WORKER_03_SALARY = 1;
    private static final Integer UPDATED_WORKER_03_SALARY = 2;

    private static final Integer DEFAULT_WORKER_04_SALARY = 1;
    private static final Integer UPDATED_WORKER_04_SALARY = 2;

    private static final Integer DEFAULT_WORKER_05_SALARY = 1;
    private static final Integer UPDATED_WORKER_05_SALARY = 2;

    private static final Integer DEFAULT_WORKER_06_SALARY = 1;
    private static final Integer UPDATED_WORKER_06_SALARY = 2;

    private static final Integer DEFAULT_WORKER_07_SALARY = 1;
    private static final Integer UPDATED_WORKER_07_SALARY = 2;

    private static final Integer DEFAULT_WORKER_08_SALARY = 1;
    private static final Integer UPDATED_WORKER_08_SALARY = 2;

    private static final Integer DEFAULT_WORKER_09_SALARY = 1;
    private static final Integer UPDATED_WORKER_09_SALARY = 2;

    private static final Integer DEFAULT_WORKER_10_SALARY = 1;
    private static final Integer UPDATED_WORKER_10_SALARY = 2;

    private static final Integer DEFAULT_WORKER_11_SALARY = 1;
    private static final Integer UPDATED_WORKER_11_SALARY = 2;

    private static final Integer DEFAULT_WORKER_12_SALARY = 1;
    private static final Integer UPDATED_WORKER_12_SALARY = 2;

    private static final BigDecimal DEFAULT_APERSON_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_APERSON_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_APERSON_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_APERSON_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_03 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_03 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_04 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_04 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_05 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_05 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_06 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_06 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_07 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_07 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_08 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_08 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_09 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_09 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_10 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_11 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_11 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_12 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_12 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_01 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_01 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_02 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_02 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_03 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_03 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_04 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_04 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_05 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_05 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_06 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_06 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_07 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_07 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_08 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_08 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_09 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_09 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_10 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_11 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_11 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_12 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_12 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_13 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_13 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AJOBTYPE_14 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AJOBTYPE_14 = new BigDecimal(2);

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CONF_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONF_IN = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_IN = new BigDecimal(2);

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_WORKER_13 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_13 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_14 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_14 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_15 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_15 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_16 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_16 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_13 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_13 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_14 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_14 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_15 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_15 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_A_16 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_A_16 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_13 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_13 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_14 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_14 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_15 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_15 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_B_16 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_B_16 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_13 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_13 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_14 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_14 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_15 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_15 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WORKER_C_16 = new BigDecimal(1);
    private static final BigDecimal UPDATED_WORKER_C_16 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_13 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_13 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_14 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_14 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_15 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_15 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AWORKER_16 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AWORKER_16 = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/bid-prj-wksorcs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjWksorcRepository bidPrjWksorcRepository;

    @Autowired
    private BidPrjWksorcMapper bidPrjWksorcMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjWksorcMockMvc;

    private BidPrjWksorc bidPrjWksorc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjWksorc createEntity(EntityManager em) {
        BidPrjWksorc bidPrjWksorc = new BidPrjWksorc()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .syr(DEFAULT_SYR)
            .month(DEFAULT_MONTH)
            .person01(DEFAULT_PERSON_01)
            .person02(DEFAULT_PERSON_02)
            .worker01(DEFAULT_WORKER_01)
            .worker02(DEFAULT_WORKER_02)
            .worker03(DEFAULT_WORKER_03)
            .worker04(DEFAULT_WORKER_04)
            .worker05(DEFAULT_WORKER_05)
            .worker06(DEFAULT_WORKER_06)
            .worker07(DEFAULT_WORKER_07)
            .worker08(DEFAULT_WORKER_08)
            .worker09(DEFAULT_WORKER_09)
            .worker10(DEFAULT_WORKER_10)
            .worker11(DEFAULT_WORKER_11)
            .worker12(DEFAULT_WORKER_12)
            .personA01(DEFAULT_PERSON_A_01)
            .personA02(DEFAULT_PERSON_A_02)
            .workerA01(DEFAULT_WORKER_A_01)
            .workerA02(DEFAULT_WORKER_A_02)
            .workerA03(DEFAULT_WORKER_A_03)
            .workerA04(DEFAULT_WORKER_A_04)
            .workerA05(DEFAULT_WORKER_A_05)
            .workerA06(DEFAULT_WORKER_A_06)
            .workerA07(DEFAULT_WORKER_A_07)
            .workerA08(DEFAULT_WORKER_A_08)
            .workerA09(DEFAULT_WORKER_A_09)
            .workerA10(DEFAULT_WORKER_A_10)
            .workerA11(DEFAULT_WORKER_A_11)
            .workerA12(DEFAULT_WORKER_A_12)
            .personB01(DEFAULT_PERSON_B_01)
            .personB02(DEFAULT_PERSON_B_02)
            .workerB01(DEFAULT_WORKER_B_01)
            .workerB02(DEFAULT_WORKER_B_02)
            .workerB03(DEFAULT_WORKER_B_03)
            .workerB04(DEFAULT_WORKER_B_04)
            .workerB05(DEFAULT_WORKER_B_05)
            .workerB06(DEFAULT_WORKER_B_06)
            .workerB07(DEFAULT_WORKER_B_07)
            .workerB08(DEFAULT_WORKER_B_08)
            .workerB09(DEFAULT_WORKER_B_09)
            .workerB10(DEFAULT_WORKER_B_10)
            .workerB11(DEFAULT_WORKER_B_11)
            .workerB12(DEFAULT_WORKER_B_12)
            .personC01(DEFAULT_PERSON_C_01)
            .personC02(DEFAULT_PERSON_C_02)
            .workerC01(DEFAULT_WORKER_C_01)
            .workerC02(DEFAULT_WORKER_C_02)
            .workerC03(DEFAULT_WORKER_C_03)
            .workerC04(DEFAULT_WORKER_C_04)
            .workerC05(DEFAULT_WORKER_C_05)
            .workerC06(DEFAULT_WORKER_C_06)
            .workerC07(DEFAULT_WORKER_C_07)
            .workerC08(DEFAULT_WORKER_C_08)
            .workerC09(DEFAULT_WORKER_C_09)
            .workerC10(DEFAULT_WORKER_C_10)
            .workerC11(DEFAULT_WORKER_C_11)
            .workerC12(DEFAULT_WORKER_C_12)
            .person01Salary(DEFAULT_PERSON_01_SALARY)
            .person02Salary(DEFAULT_PERSON_02_SALARY)
            .worker01Salary(DEFAULT_WORKER_01_SALARY)
            .worker02Salary(DEFAULT_WORKER_02_SALARY)
            .worker03Salary(DEFAULT_WORKER_03_SALARY)
            .worker04Salary(DEFAULT_WORKER_04_SALARY)
            .worker05Salary(DEFAULT_WORKER_05_SALARY)
            .worker06Salary(DEFAULT_WORKER_06_SALARY)
            .worker07Salary(DEFAULT_WORKER_07_SALARY)
            .worker08Salary(DEFAULT_WORKER_08_SALARY)
            .worker09Salary(DEFAULT_WORKER_09_SALARY)
            .worker10Salary(DEFAULT_WORKER_10_SALARY)
            .worker11Salary(DEFAULT_WORKER_11_SALARY)
            .worker12Salary(DEFAULT_WORKER_12_SALARY)
            .aperson01(DEFAULT_APERSON_01)
            .aperson02(DEFAULT_APERSON_02)
            .aworker01(DEFAULT_AWORKER_01)
            .aworker02(DEFAULT_AWORKER_02)
            .aworker03(DEFAULT_AWORKER_03)
            .aworker04(DEFAULT_AWORKER_04)
            .aworker05(DEFAULT_AWORKER_05)
            .aworker06(DEFAULT_AWORKER_06)
            .aworker07(DEFAULT_AWORKER_07)
            .aworker08(DEFAULT_AWORKER_08)
            .aworker09(DEFAULT_AWORKER_09)
            .aworker10(DEFAULT_AWORKER_10)
            .aworker11(DEFAULT_AWORKER_11)
            .aworker12(DEFAULT_AWORKER_12)
            .ajobtype01(DEFAULT_AJOBTYPE_01)
            .ajobtype02(DEFAULT_AJOBTYPE_02)
            .ajobtype03(DEFAULT_AJOBTYPE_03)
            .ajobtype04(DEFAULT_AJOBTYPE_04)
            .ajobtype05(DEFAULT_AJOBTYPE_05)
            .ajobtype06(DEFAULT_AJOBTYPE_06)
            .ajobtype07(DEFAULT_AJOBTYPE_07)
            .ajobtype08(DEFAULT_AJOBTYPE_08)
            .ajobtype09(DEFAULT_AJOBTYPE_09)
            .ajobtype10(DEFAULT_AJOBTYPE_10)
            .ajobtype11(DEFAULT_AJOBTYPE_11)
            .ajobtype12(DEFAULT_AJOBTYPE_12)
            .ajobtype13(DEFAULT_AJOBTYPE_13)
            .ajobtype14(DEFAULT_AJOBTYPE_14)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .confOut(DEFAULT_CONF_OUT)
            .confIn(DEFAULT_CONF_IN)
            .uIp(DEFAULT_U_IP)
            .worker13(DEFAULT_WORKER_13)
            .worker14(DEFAULT_WORKER_14)
            .worker15(DEFAULT_WORKER_15)
            .worker16(DEFAULT_WORKER_16)
            .workerA13(DEFAULT_WORKER_A_13)
            .workerA14(DEFAULT_WORKER_A_14)
            .workerA15(DEFAULT_WORKER_A_15)
            .workerA16(DEFAULT_WORKER_A_16)
            .workerB13(DEFAULT_WORKER_B_13)
            .workerB14(DEFAULT_WORKER_B_14)
            .workerB15(DEFAULT_WORKER_B_15)
            .workerB16(DEFAULT_WORKER_B_16)
            .workerC13(DEFAULT_WORKER_C_13)
            .workerC14(DEFAULT_WORKER_C_14)
            .workerC15(DEFAULT_WORKER_C_15)
            .workerC16(DEFAULT_WORKER_C_16)
            .aworker13(DEFAULT_AWORKER_13)
            .aworker14(DEFAULT_AWORKER_14)
            .aworker15(DEFAULT_AWORKER_15)
            .aworker16(DEFAULT_AWORKER_16);
        return bidPrjWksorc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjWksorc createUpdatedEntity(EntityManager em) {
        BidPrjWksorc bidPrjWksorc = new BidPrjWksorc()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .syr(UPDATED_SYR)
            .month(UPDATED_MONTH)
            .person01(UPDATED_PERSON_01)
            .person02(UPDATED_PERSON_02)
            .worker01(UPDATED_WORKER_01)
            .worker02(UPDATED_WORKER_02)
            .worker03(UPDATED_WORKER_03)
            .worker04(UPDATED_WORKER_04)
            .worker05(UPDATED_WORKER_05)
            .worker06(UPDATED_WORKER_06)
            .worker07(UPDATED_WORKER_07)
            .worker08(UPDATED_WORKER_08)
            .worker09(UPDATED_WORKER_09)
            .worker10(UPDATED_WORKER_10)
            .worker11(UPDATED_WORKER_11)
            .worker12(UPDATED_WORKER_12)
            .personA01(UPDATED_PERSON_A_01)
            .personA02(UPDATED_PERSON_A_02)
            .workerA01(UPDATED_WORKER_A_01)
            .workerA02(UPDATED_WORKER_A_02)
            .workerA03(UPDATED_WORKER_A_03)
            .workerA04(UPDATED_WORKER_A_04)
            .workerA05(UPDATED_WORKER_A_05)
            .workerA06(UPDATED_WORKER_A_06)
            .workerA07(UPDATED_WORKER_A_07)
            .workerA08(UPDATED_WORKER_A_08)
            .workerA09(UPDATED_WORKER_A_09)
            .workerA10(UPDATED_WORKER_A_10)
            .workerA11(UPDATED_WORKER_A_11)
            .workerA12(UPDATED_WORKER_A_12)
            .personB01(UPDATED_PERSON_B_01)
            .personB02(UPDATED_PERSON_B_02)
            .workerB01(UPDATED_WORKER_B_01)
            .workerB02(UPDATED_WORKER_B_02)
            .workerB03(UPDATED_WORKER_B_03)
            .workerB04(UPDATED_WORKER_B_04)
            .workerB05(UPDATED_WORKER_B_05)
            .workerB06(UPDATED_WORKER_B_06)
            .workerB07(UPDATED_WORKER_B_07)
            .workerB08(UPDATED_WORKER_B_08)
            .workerB09(UPDATED_WORKER_B_09)
            .workerB10(UPDATED_WORKER_B_10)
            .workerB11(UPDATED_WORKER_B_11)
            .workerB12(UPDATED_WORKER_B_12)
            .personC01(UPDATED_PERSON_C_01)
            .personC02(UPDATED_PERSON_C_02)
            .workerC01(UPDATED_WORKER_C_01)
            .workerC02(UPDATED_WORKER_C_02)
            .workerC03(UPDATED_WORKER_C_03)
            .workerC04(UPDATED_WORKER_C_04)
            .workerC05(UPDATED_WORKER_C_05)
            .workerC06(UPDATED_WORKER_C_06)
            .workerC07(UPDATED_WORKER_C_07)
            .workerC08(UPDATED_WORKER_C_08)
            .workerC09(UPDATED_WORKER_C_09)
            .workerC10(UPDATED_WORKER_C_10)
            .workerC11(UPDATED_WORKER_C_11)
            .workerC12(UPDATED_WORKER_C_12)
            .person01Salary(UPDATED_PERSON_01_SALARY)
            .person02Salary(UPDATED_PERSON_02_SALARY)
            .worker01Salary(UPDATED_WORKER_01_SALARY)
            .worker02Salary(UPDATED_WORKER_02_SALARY)
            .worker03Salary(UPDATED_WORKER_03_SALARY)
            .worker04Salary(UPDATED_WORKER_04_SALARY)
            .worker05Salary(UPDATED_WORKER_05_SALARY)
            .worker06Salary(UPDATED_WORKER_06_SALARY)
            .worker07Salary(UPDATED_WORKER_07_SALARY)
            .worker08Salary(UPDATED_WORKER_08_SALARY)
            .worker09Salary(UPDATED_WORKER_09_SALARY)
            .worker10Salary(UPDATED_WORKER_10_SALARY)
            .worker11Salary(UPDATED_WORKER_11_SALARY)
            .worker12Salary(UPDATED_WORKER_12_SALARY)
            .aperson01(UPDATED_APERSON_01)
            .aperson02(UPDATED_APERSON_02)
            .aworker01(UPDATED_AWORKER_01)
            .aworker02(UPDATED_AWORKER_02)
            .aworker03(UPDATED_AWORKER_03)
            .aworker04(UPDATED_AWORKER_04)
            .aworker05(UPDATED_AWORKER_05)
            .aworker06(UPDATED_AWORKER_06)
            .aworker07(UPDATED_AWORKER_07)
            .aworker08(UPDATED_AWORKER_08)
            .aworker09(UPDATED_AWORKER_09)
            .aworker10(UPDATED_AWORKER_10)
            .aworker11(UPDATED_AWORKER_11)
            .aworker12(UPDATED_AWORKER_12)
            .ajobtype01(UPDATED_AJOBTYPE_01)
            .ajobtype02(UPDATED_AJOBTYPE_02)
            .ajobtype03(UPDATED_AJOBTYPE_03)
            .ajobtype04(UPDATED_AJOBTYPE_04)
            .ajobtype05(UPDATED_AJOBTYPE_05)
            .ajobtype06(UPDATED_AJOBTYPE_06)
            .ajobtype07(UPDATED_AJOBTYPE_07)
            .ajobtype08(UPDATED_AJOBTYPE_08)
            .ajobtype09(UPDATED_AJOBTYPE_09)
            .ajobtype10(UPDATED_AJOBTYPE_10)
            .ajobtype11(UPDATED_AJOBTYPE_11)
            .ajobtype12(UPDATED_AJOBTYPE_12)
            .ajobtype13(UPDATED_AJOBTYPE_13)
            .ajobtype14(UPDATED_AJOBTYPE_14)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .confOut(UPDATED_CONF_OUT)
            .confIn(UPDATED_CONF_IN)
            .uIp(UPDATED_U_IP)
            .worker13(UPDATED_WORKER_13)
            .worker14(UPDATED_WORKER_14)
            .worker15(UPDATED_WORKER_15)
            .worker16(UPDATED_WORKER_16)
            .workerA13(UPDATED_WORKER_A_13)
            .workerA14(UPDATED_WORKER_A_14)
            .workerA15(UPDATED_WORKER_A_15)
            .workerA16(UPDATED_WORKER_A_16)
            .workerB13(UPDATED_WORKER_B_13)
            .workerB14(UPDATED_WORKER_B_14)
            .workerB15(UPDATED_WORKER_B_15)
            .workerB16(UPDATED_WORKER_B_16)
            .workerC13(UPDATED_WORKER_C_13)
            .workerC14(UPDATED_WORKER_C_14)
            .workerC15(UPDATED_WORKER_C_15)
            .workerC16(UPDATED_WORKER_C_16)
            .aworker13(UPDATED_AWORKER_13)
            .aworker14(UPDATED_AWORKER_14)
            .aworker15(UPDATED_AWORKER_15)
            .aworker16(UPDATED_AWORKER_16);
        return bidPrjWksorc;
    }

    @BeforeEach
    public void initTest() {
        bidPrjWksorc = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjWksorc() throws Exception {
        int databaseSizeBeforeCreate = bidPrjWksorcRepository.findAll().size();
        // Create the BidPrjWksorc
        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);
        restBidPrjWksorcMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjWksorc in the database
        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjWksorc testBidPrjWksorc = bidPrjWksorcList.get(bidPrjWksorcList.size() - 1);
        assertThat(testBidPrjWksorc.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjWksorc.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjWksorc.getSyr()).isEqualTo(DEFAULT_SYR);
        assertThat(testBidPrjWksorc.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBidPrjWksorc.getPerson01()).isEqualByComparingTo(DEFAULT_PERSON_01);
        assertThat(testBidPrjWksorc.getPerson02()).isEqualByComparingTo(DEFAULT_PERSON_02);
        assertThat(testBidPrjWksorc.getWorker01()).isEqualByComparingTo(DEFAULT_WORKER_01);
        assertThat(testBidPrjWksorc.getWorker02()).isEqualByComparingTo(DEFAULT_WORKER_02);
        assertThat(testBidPrjWksorc.getWorker03()).isEqualByComparingTo(DEFAULT_WORKER_03);
        assertThat(testBidPrjWksorc.getWorker04()).isEqualByComparingTo(DEFAULT_WORKER_04);
        assertThat(testBidPrjWksorc.getWorker05()).isEqualByComparingTo(DEFAULT_WORKER_05);
        assertThat(testBidPrjWksorc.getWorker06()).isEqualByComparingTo(DEFAULT_WORKER_06);
        assertThat(testBidPrjWksorc.getWorker07()).isEqualByComparingTo(DEFAULT_WORKER_07);
        assertThat(testBidPrjWksorc.getWorker08()).isEqualByComparingTo(DEFAULT_WORKER_08);
        assertThat(testBidPrjWksorc.getWorker09()).isEqualByComparingTo(DEFAULT_WORKER_09);
        assertThat(testBidPrjWksorc.getWorker10()).isEqualByComparingTo(DEFAULT_WORKER_10);
        assertThat(testBidPrjWksorc.getWorker11()).isEqualByComparingTo(DEFAULT_WORKER_11);
        assertThat(testBidPrjWksorc.getWorker12()).isEqualByComparingTo(DEFAULT_WORKER_12);
        assertThat(testBidPrjWksorc.getPersonA01()).isEqualByComparingTo(DEFAULT_PERSON_A_01);
        assertThat(testBidPrjWksorc.getPersonA02()).isEqualByComparingTo(DEFAULT_PERSON_A_02);
        assertThat(testBidPrjWksorc.getWorkerA01()).isEqualByComparingTo(DEFAULT_WORKER_A_01);
        assertThat(testBidPrjWksorc.getWorkerA02()).isEqualByComparingTo(DEFAULT_WORKER_A_02);
        assertThat(testBidPrjWksorc.getWorkerA03()).isEqualByComparingTo(DEFAULT_WORKER_A_03);
        assertThat(testBidPrjWksorc.getWorkerA04()).isEqualByComparingTo(DEFAULT_WORKER_A_04);
        assertThat(testBidPrjWksorc.getWorkerA05()).isEqualByComparingTo(DEFAULT_WORKER_A_05);
        assertThat(testBidPrjWksorc.getWorkerA06()).isEqualByComparingTo(DEFAULT_WORKER_A_06);
        assertThat(testBidPrjWksorc.getWorkerA07()).isEqualByComparingTo(DEFAULT_WORKER_A_07);
        assertThat(testBidPrjWksorc.getWorkerA08()).isEqualByComparingTo(DEFAULT_WORKER_A_08);
        assertThat(testBidPrjWksorc.getWorkerA09()).isEqualByComparingTo(DEFAULT_WORKER_A_09);
        assertThat(testBidPrjWksorc.getWorkerA10()).isEqualByComparingTo(DEFAULT_WORKER_A_10);
        assertThat(testBidPrjWksorc.getWorkerA11()).isEqualByComparingTo(DEFAULT_WORKER_A_11);
        assertThat(testBidPrjWksorc.getWorkerA12()).isEqualByComparingTo(DEFAULT_WORKER_A_12);
        assertThat(testBidPrjWksorc.getPersonB01()).isEqualByComparingTo(DEFAULT_PERSON_B_01);
        assertThat(testBidPrjWksorc.getPersonB02()).isEqualByComparingTo(DEFAULT_PERSON_B_02);
        assertThat(testBidPrjWksorc.getWorkerB01()).isEqualByComparingTo(DEFAULT_WORKER_B_01);
        assertThat(testBidPrjWksorc.getWorkerB02()).isEqualByComparingTo(DEFAULT_WORKER_B_02);
        assertThat(testBidPrjWksorc.getWorkerB03()).isEqualByComparingTo(DEFAULT_WORKER_B_03);
        assertThat(testBidPrjWksorc.getWorkerB04()).isEqualByComparingTo(DEFAULT_WORKER_B_04);
        assertThat(testBidPrjWksorc.getWorkerB05()).isEqualByComparingTo(DEFAULT_WORKER_B_05);
        assertThat(testBidPrjWksorc.getWorkerB06()).isEqualByComparingTo(DEFAULT_WORKER_B_06);
        assertThat(testBidPrjWksorc.getWorkerB07()).isEqualByComparingTo(DEFAULT_WORKER_B_07);
        assertThat(testBidPrjWksorc.getWorkerB08()).isEqualByComparingTo(DEFAULT_WORKER_B_08);
        assertThat(testBidPrjWksorc.getWorkerB09()).isEqualByComparingTo(DEFAULT_WORKER_B_09);
        assertThat(testBidPrjWksorc.getWorkerB10()).isEqualByComparingTo(DEFAULT_WORKER_B_10);
        assertThat(testBidPrjWksorc.getWorkerB11()).isEqualByComparingTo(DEFAULT_WORKER_B_11);
        assertThat(testBidPrjWksorc.getWorkerB12()).isEqualByComparingTo(DEFAULT_WORKER_B_12);
        assertThat(testBidPrjWksorc.getPersonC01()).isEqualByComparingTo(DEFAULT_PERSON_C_01);
        assertThat(testBidPrjWksorc.getPersonC02()).isEqualByComparingTo(DEFAULT_PERSON_C_02);
        assertThat(testBidPrjWksorc.getWorkerC01()).isEqualByComparingTo(DEFAULT_WORKER_C_01);
        assertThat(testBidPrjWksorc.getWorkerC02()).isEqualByComparingTo(DEFAULT_WORKER_C_02);
        assertThat(testBidPrjWksorc.getWorkerC03()).isEqualByComparingTo(DEFAULT_WORKER_C_03);
        assertThat(testBidPrjWksorc.getWorkerC04()).isEqualByComparingTo(DEFAULT_WORKER_C_04);
        assertThat(testBidPrjWksorc.getWorkerC05()).isEqualByComparingTo(DEFAULT_WORKER_C_05);
        assertThat(testBidPrjWksorc.getWorkerC06()).isEqualByComparingTo(DEFAULT_WORKER_C_06);
        assertThat(testBidPrjWksorc.getWorkerC07()).isEqualByComparingTo(DEFAULT_WORKER_C_07);
        assertThat(testBidPrjWksorc.getWorkerC08()).isEqualByComparingTo(DEFAULT_WORKER_C_08);
        assertThat(testBidPrjWksorc.getWorkerC09()).isEqualByComparingTo(DEFAULT_WORKER_C_09);
        assertThat(testBidPrjWksorc.getWorkerC10()).isEqualByComparingTo(DEFAULT_WORKER_C_10);
        assertThat(testBidPrjWksorc.getWorkerC11()).isEqualByComparingTo(DEFAULT_WORKER_C_11);
        assertThat(testBidPrjWksorc.getWorkerC12()).isEqualByComparingTo(DEFAULT_WORKER_C_12);
        assertThat(testBidPrjWksorc.getPerson01Salary()).isEqualTo(DEFAULT_PERSON_01_SALARY);
        assertThat(testBidPrjWksorc.getPerson02Salary()).isEqualTo(DEFAULT_PERSON_02_SALARY);
        assertThat(testBidPrjWksorc.getWorker01Salary()).isEqualTo(DEFAULT_WORKER_01_SALARY);
        assertThat(testBidPrjWksorc.getWorker02Salary()).isEqualTo(DEFAULT_WORKER_02_SALARY);
        assertThat(testBidPrjWksorc.getWorker03Salary()).isEqualTo(DEFAULT_WORKER_03_SALARY);
        assertThat(testBidPrjWksorc.getWorker04Salary()).isEqualTo(DEFAULT_WORKER_04_SALARY);
        assertThat(testBidPrjWksorc.getWorker05Salary()).isEqualTo(DEFAULT_WORKER_05_SALARY);
        assertThat(testBidPrjWksorc.getWorker06Salary()).isEqualTo(DEFAULT_WORKER_06_SALARY);
        assertThat(testBidPrjWksorc.getWorker07Salary()).isEqualTo(DEFAULT_WORKER_07_SALARY);
        assertThat(testBidPrjWksorc.getWorker08Salary()).isEqualTo(DEFAULT_WORKER_08_SALARY);
        assertThat(testBidPrjWksorc.getWorker09Salary()).isEqualTo(DEFAULT_WORKER_09_SALARY);
        assertThat(testBidPrjWksorc.getWorker10Salary()).isEqualTo(DEFAULT_WORKER_10_SALARY);
        assertThat(testBidPrjWksorc.getWorker11Salary()).isEqualTo(DEFAULT_WORKER_11_SALARY);
        assertThat(testBidPrjWksorc.getWorker12Salary()).isEqualTo(DEFAULT_WORKER_12_SALARY);
        assertThat(testBidPrjWksorc.getAperson01()).isEqualByComparingTo(DEFAULT_APERSON_01);
        assertThat(testBidPrjWksorc.getAperson02()).isEqualByComparingTo(DEFAULT_APERSON_02);
        assertThat(testBidPrjWksorc.getAworker01()).isEqualByComparingTo(DEFAULT_AWORKER_01);
        assertThat(testBidPrjWksorc.getAworker02()).isEqualByComparingTo(DEFAULT_AWORKER_02);
        assertThat(testBidPrjWksorc.getAworker03()).isEqualByComparingTo(DEFAULT_AWORKER_03);
        assertThat(testBidPrjWksorc.getAworker04()).isEqualByComparingTo(DEFAULT_AWORKER_04);
        assertThat(testBidPrjWksorc.getAworker05()).isEqualByComparingTo(DEFAULT_AWORKER_05);
        assertThat(testBidPrjWksorc.getAworker06()).isEqualByComparingTo(DEFAULT_AWORKER_06);
        assertThat(testBidPrjWksorc.getAworker07()).isEqualByComparingTo(DEFAULT_AWORKER_07);
        assertThat(testBidPrjWksorc.getAworker08()).isEqualByComparingTo(DEFAULT_AWORKER_08);
        assertThat(testBidPrjWksorc.getAworker09()).isEqualByComparingTo(DEFAULT_AWORKER_09);
        assertThat(testBidPrjWksorc.getAworker10()).isEqualByComparingTo(DEFAULT_AWORKER_10);
        assertThat(testBidPrjWksorc.getAworker11()).isEqualByComparingTo(DEFAULT_AWORKER_11);
        assertThat(testBidPrjWksorc.getAworker12()).isEqualByComparingTo(DEFAULT_AWORKER_12);
        assertThat(testBidPrjWksorc.getAjobtype01()).isEqualByComparingTo(DEFAULT_AJOBTYPE_01);
        assertThat(testBidPrjWksorc.getAjobtype02()).isEqualByComparingTo(DEFAULT_AJOBTYPE_02);
        assertThat(testBidPrjWksorc.getAjobtype03()).isEqualByComparingTo(DEFAULT_AJOBTYPE_03);
        assertThat(testBidPrjWksorc.getAjobtype04()).isEqualByComparingTo(DEFAULT_AJOBTYPE_04);
        assertThat(testBidPrjWksorc.getAjobtype05()).isEqualByComparingTo(DEFAULT_AJOBTYPE_05);
        assertThat(testBidPrjWksorc.getAjobtype06()).isEqualByComparingTo(DEFAULT_AJOBTYPE_06);
        assertThat(testBidPrjWksorc.getAjobtype07()).isEqualByComparingTo(DEFAULT_AJOBTYPE_07);
        assertThat(testBidPrjWksorc.getAjobtype08()).isEqualByComparingTo(DEFAULT_AJOBTYPE_08);
        assertThat(testBidPrjWksorc.getAjobtype09()).isEqualByComparingTo(DEFAULT_AJOBTYPE_09);
        assertThat(testBidPrjWksorc.getAjobtype10()).isEqualByComparingTo(DEFAULT_AJOBTYPE_10);
        assertThat(testBidPrjWksorc.getAjobtype11()).isEqualByComparingTo(DEFAULT_AJOBTYPE_11);
        assertThat(testBidPrjWksorc.getAjobtype12()).isEqualByComparingTo(DEFAULT_AJOBTYPE_12);
        assertThat(testBidPrjWksorc.getAjobtype13()).isEqualByComparingTo(DEFAULT_AJOBTYPE_13);
        assertThat(testBidPrjWksorc.getAjobtype14()).isEqualByComparingTo(DEFAULT_AJOBTYPE_14);
        assertThat(testBidPrjWksorc.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjWksorc.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjWksorc.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjWksorc.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjWksorc.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjWksorc.getConfIn()).isEqualByComparingTo(DEFAULT_CONF_IN);
        assertThat(testBidPrjWksorc.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjWksorc.getWorker13()).isEqualByComparingTo(DEFAULT_WORKER_13);
        assertThat(testBidPrjWksorc.getWorker14()).isEqualByComparingTo(DEFAULT_WORKER_14);
        assertThat(testBidPrjWksorc.getWorker15()).isEqualByComparingTo(DEFAULT_WORKER_15);
        assertThat(testBidPrjWksorc.getWorker16()).isEqualByComparingTo(DEFAULT_WORKER_16);
        assertThat(testBidPrjWksorc.getWorkerA13()).isEqualByComparingTo(DEFAULT_WORKER_A_13);
        assertThat(testBidPrjWksorc.getWorkerA14()).isEqualByComparingTo(DEFAULT_WORKER_A_14);
        assertThat(testBidPrjWksorc.getWorkerA15()).isEqualByComparingTo(DEFAULT_WORKER_A_15);
        assertThat(testBidPrjWksorc.getWorkerA16()).isEqualByComparingTo(DEFAULT_WORKER_A_16);
        assertThat(testBidPrjWksorc.getWorkerB13()).isEqualByComparingTo(DEFAULT_WORKER_B_13);
        assertThat(testBidPrjWksorc.getWorkerB14()).isEqualByComparingTo(DEFAULT_WORKER_B_14);
        assertThat(testBidPrjWksorc.getWorkerB15()).isEqualByComparingTo(DEFAULT_WORKER_B_15);
        assertThat(testBidPrjWksorc.getWorkerB16()).isEqualByComparingTo(DEFAULT_WORKER_B_16);
        assertThat(testBidPrjWksorc.getWorkerC13()).isEqualByComparingTo(DEFAULT_WORKER_C_13);
        assertThat(testBidPrjWksorc.getWorkerC14()).isEqualByComparingTo(DEFAULT_WORKER_C_14);
        assertThat(testBidPrjWksorc.getWorkerC15()).isEqualByComparingTo(DEFAULT_WORKER_C_15);
        assertThat(testBidPrjWksorc.getWorkerC16()).isEqualByComparingTo(DEFAULT_WORKER_C_16);
        assertThat(testBidPrjWksorc.getAworker13()).isEqualByComparingTo(DEFAULT_AWORKER_13);
        assertThat(testBidPrjWksorc.getAworker14()).isEqualByComparingTo(DEFAULT_AWORKER_14);
        assertThat(testBidPrjWksorc.getAworker15()).isEqualByComparingTo(DEFAULT_AWORKER_15);
        assertThat(testBidPrjWksorc.getAworker16()).isEqualByComparingTo(DEFAULT_AWORKER_16);
    }

    @Test
    @Transactional
    void createBidPrjWksorcWithExistingId() throws Exception {
//        // Create the BidPrjWksorc with an existing ID
//        bidPrjWksorc.setId(1L);
//        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);
//
//        int databaseSizeBeforeCreate = bidPrjWksorcRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjWksorcMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjWksorc in the database
//        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
//        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjWksorcRepository.findAll().size();
        // set the field null
        bidPrjWksorc.setWkut(null);

        // Create the BidPrjWksorc, which fails.
        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);

        restBidPrjWksorcMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjWksorcRepository.findAll().size();
        // set the field null
        bidPrjWksorc.setPrjno(null);

        // Create the BidPrjWksorc, which fails.
        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);

        restBidPrjWksorcMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSyrIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjWksorcRepository.findAll().size();
        // set the field null
        bidPrjWksorc.setSyr(null);

        // Create the BidPrjWksorc, which fails.
        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);

        restBidPrjWksorcMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMonthIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjWksorcRepository.findAll().size();
        // set the field null
        bidPrjWksorc.setMonth(null);

        // Create the BidPrjWksorc, which fails.
        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);

        restBidPrjWksorcMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjWksorcRepository.findAll().size();
        // set the field null
        bidPrjWksorc.setCreateDate(null);

        // Create the BidPrjWksorc, which fails.
        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);

        restBidPrjWksorcMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjWksorcRepository.findAll().size();
        // set the field null
        bidPrjWksorc.setCreateUser(null);

        // Create the BidPrjWksorc, which fails.
        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);

        restBidPrjWksorcMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBidPrjWksorcs() throws Exception {
        // Initialize the database
        bidPrjWksorcRepository.saveAndFlush(bidPrjWksorc);

        // Get all the bidPrjWksorcList
        restBidPrjWksorcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjWksorc.getId().intValue())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].syr").value(hasItem(DEFAULT_SYR)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].person01").value(hasItem(sameNumber(DEFAULT_PERSON_01))))
            .andExpect(jsonPath("$.[*].person02").value(hasItem(sameNumber(DEFAULT_PERSON_02))))
            .andExpect(jsonPath("$.[*].worker01").value(hasItem(sameNumber(DEFAULT_WORKER_01))))
            .andExpect(jsonPath("$.[*].worker02").value(hasItem(sameNumber(DEFAULT_WORKER_02))))
            .andExpect(jsonPath("$.[*].worker03").value(hasItem(sameNumber(DEFAULT_WORKER_03))))
            .andExpect(jsonPath("$.[*].worker04").value(hasItem(sameNumber(DEFAULT_WORKER_04))))
            .andExpect(jsonPath("$.[*].worker05").value(hasItem(sameNumber(DEFAULT_WORKER_05))))
            .andExpect(jsonPath("$.[*].worker06").value(hasItem(sameNumber(DEFAULT_WORKER_06))))
            .andExpect(jsonPath("$.[*].worker07").value(hasItem(sameNumber(DEFAULT_WORKER_07))))
            .andExpect(jsonPath("$.[*].worker08").value(hasItem(sameNumber(DEFAULT_WORKER_08))))
            .andExpect(jsonPath("$.[*].worker09").value(hasItem(sameNumber(DEFAULT_WORKER_09))))
            .andExpect(jsonPath("$.[*].worker10").value(hasItem(sameNumber(DEFAULT_WORKER_10))))
            .andExpect(jsonPath("$.[*].worker11").value(hasItem(sameNumber(DEFAULT_WORKER_11))))
            .andExpect(jsonPath("$.[*].worker12").value(hasItem(sameNumber(DEFAULT_WORKER_12))))
            .andExpect(jsonPath("$.[*].personA01").value(hasItem(sameNumber(DEFAULT_PERSON_A_01))))
            .andExpect(jsonPath("$.[*].personA02").value(hasItem(sameNumber(DEFAULT_PERSON_A_02))))
            .andExpect(jsonPath("$.[*].workerA01").value(hasItem(sameNumber(DEFAULT_WORKER_A_01))))
            .andExpect(jsonPath("$.[*].workerA02").value(hasItem(sameNumber(DEFAULT_WORKER_A_02))))
            .andExpect(jsonPath("$.[*].workerA03").value(hasItem(sameNumber(DEFAULT_WORKER_A_03))))
            .andExpect(jsonPath("$.[*].workerA04").value(hasItem(sameNumber(DEFAULT_WORKER_A_04))))
            .andExpect(jsonPath("$.[*].workerA05").value(hasItem(sameNumber(DEFAULT_WORKER_A_05))))
            .andExpect(jsonPath("$.[*].workerA06").value(hasItem(sameNumber(DEFAULT_WORKER_A_06))))
            .andExpect(jsonPath("$.[*].workerA07").value(hasItem(sameNumber(DEFAULT_WORKER_A_07))))
            .andExpect(jsonPath("$.[*].workerA08").value(hasItem(sameNumber(DEFAULT_WORKER_A_08))))
            .andExpect(jsonPath("$.[*].workerA09").value(hasItem(sameNumber(DEFAULT_WORKER_A_09))))
            .andExpect(jsonPath("$.[*].workerA10").value(hasItem(sameNumber(DEFAULT_WORKER_A_10))))
            .andExpect(jsonPath("$.[*].workerA11").value(hasItem(sameNumber(DEFAULT_WORKER_A_11))))
            .andExpect(jsonPath("$.[*].workerA12").value(hasItem(sameNumber(DEFAULT_WORKER_A_12))))
            .andExpect(jsonPath("$.[*].personB01").value(hasItem(sameNumber(DEFAULT_PERSON_B_01))))
            .andExpect(jsonPath("$.[*].personB02").value(hasItem(sameNumber(DEFAULT_PERSON_B_02))))
            .andExpect(jsonPath("$.[*].workerB01").value(hasItem(sameNumber(DEFAULT_WORKER_B_01))))
            .andExpect(jsonPath("$.[*].workerB02").value(hasItem(sameNumber(DEFAULT_WORKER_B_02))))
            .andExpect(jsonPath("$.[*].workerB03").value(hasItem(sameNumber(DEFAULT_WORKER_B_03))))
            .andExpect(jsonPath("$.[*].workerB04").value(hasItem(sameNumber(DEFAULT_WORKER_B_04))))
            .andExpect(jsonPath("$.[*].workerB05").value(hasItem(sameNumber(DEFAULT_WORKER_B_05))))
            .andExpect(jsonPath("$.[*].workerB06").value(hasItem(sameNumber(DEFAULT_WORKER_B_06))))
            .andExpect(jsonPath("$.[*].workerB07").value(hasItem(sameNumber(DEFAULT_WORKER_B_07))))
            .andExpect(jsonPath("$.[*].workerB08").value(hasItem(sameNumber(DEFAULT_WORKER_B_08))))
            .andExpect(jsonPath("$.[*].workerB09").value(hasItem(sameNumber(DEFAULT_WORKER_B_09))))
            .andExpect(jsonPath("$.[*].workerB10").value(hasItem(sameNumber(DEFAULT_WORKER_B_10))))
            .andExpect(jsonPath("$.[*].workerB11").value(hasItem(sameNumber(DEFAULT_WORKER_B_11))))
            .andExpect(jsonPath("$.[*].workerB12").value(hasItem(sameNumber(DEFAULT_WORKER_B_12))))
            .andExpect(jsonPath("$.[*].personC01").value(hasItem(sameNumber(DEFAULT_PERSON_C_01))))
            .andExpect(jsonPath("$.[*].personC02").value(hasItem(sameNumber(DEFAULT_PERSON_C_02))))
            .andExpect(jsonPath("$.[*].workerC01").value(hasItem(sameNumber(DEFAULT_WORKER_C_01))))
            .andExpect(jsonPath("$.[*].workerC02").value(hasItem(sameNumber(DEFAULT_WORKER_C_02))))
            .andExpect(jsonPath("$.[*].workerC03").value(hasItem(sameNumber(DEFAULT_WORKER_C_03))))
            .andExpect(jsonPath("$.[*].workerC04").value(hasItem(sameNumber(DEFAULT_WORKER_C_04))))
            .andExpect(jsonPath("$.[*].workerC05").value(hasItem(sameNumber(DEFAULT_WORKER_C_05))))
            .andExpect(jsonPath("$.[*].workerC06").value(hasItem(sameNumber(DEFAULT_WORKER_C_06))))
            .andExpect(jsonPath("$.[*].workerC07").value(hasItem(sameNumber(DEFAULT_WORKER_C_07))))
            .andExpect(jsonPath("$.[*].workerC08").value(hasItem(sameNumber(DEFAULT_WORKER_C_08))))
            .andExpect(jsonPath("$.[*].workerC09").value(hasItem(sameNumber(DEFAULT_WORKER_C_09))))
            .andExpect(jsonPath("$.[*].workerC10").value(hasItem(sameNumber(DEFAULT_WORKER_C_10))))
            .andExpect(jsonPath("$.[*].workerC11").value(hasItem(sameNumber(DEFAULT_WORKER_C_11))))
            .andExpect(jsonPath("$.[*].workerC12").value(hasItem(sameNumber(DEFAULT_WORKER_C_12))))
            .andExpect(jsonPath("$.[*].person01Salary").value(hasItem(DEFAULT_PERSON_01_SALARY)))
            .andExpect(jsonPath("$.[*].person02Salary").value(hasItem(DEFAULT_PERSON_02_SALARY)))
            .andExpect(jsonPath("$.[*].worker01Salary").value(hasItem(DEFAULT_WORKER_01_SALARY)))
            .andExpect(jsonPath("$.[*].worker02Salary").value(hasItem(DEFAULT_WORKER_02_SALARY)))
            .andExpect(jsonPath("$.[*].worker03Salary").value(hasItem(DEFAULT_WORKER_03_SALARY)))
            .andExpect(jsonPath("$.[*].worker04Salary").value(hasItem(DEFAULT_WORKER_04_SALARY)))
            .andExpect(jsonPath("$.[*].worker05Salary").value(hasItem(DEFAULT_WORKER_05_SALARY)))
            .andExpect(jsonPath("$.[*].worker06Salary").value(hasItem(DEFAULT_WORKER_06_SALARY)))
            .andExpect(jsonPath("$.[*].worker07Salary").value(hasItem(DEFAULT_WORKER_07_SALARY)))
            .andExpect(jsonPath("$.[*].worker08Salary").value(hasItem(DEFAULT_WORKER_08_SALARY)))
            .andExpect(jsonPath("$.[*].worker09Salary").value(hasItem(DEFAULT_WORKER_09_SALARY)))
            .andExpect(jsonPath("$.[*].worker10Salary").value(hasItem(DEFAULT_WORKER_10_SALARY)))
            .andExpect(jsonPath("$.[*].worker11Salary").value(hasItem(DEFAULT_WORKER_11_SALARY)))
            .andExpect(jsonPath("$.[*].worker12Salary").value(hasItem(DEFAULT_WORKER_12_SALARY)))
            .andExpect(jsonPath("$.[*].aperson01").value(hasItem(sameNumber(DEFAULT_APERSON_01))))
            .andExpect(jsonPath("$.[*].aperson02").value(hasItem(sameNumber(DEFAULT_APERSON_02))))
            .andExpect(jsonPath("$.[*].aworker01").value(hasItem(sameNumber(DEFAULT_AWORKER_01))))
            .andExpect(jsonPath("$.[*].aworker02").value(hasItem(sameNumber(DEFAULT_AWORKER_02))))
            .andExpect(jsonPath("$.[*].aworker03").value(hasItem(sameNumber(DEFAULT_AWORKER_03))))
            .andExpect(jsonPath("$.[*].aworker04").value(hasItem(sameNumber(DEFAULT_AWORKER_04))))
            .andExpect(jsonPath("$.[*].aworker05").value(hasItem(sameNumber(DEFAULT_AWORKER_05))))
            .andExpect(jsonPath("$.[*].aworker06").value(hasItem(sameNumber(DEFAULT_AWORKER_06))))
            .andExpect(jsonPath("$.[*].aworker07").value(hasItem(sameNumber(DEFAULT_AWORKER_07))))
            .andExpect(jsonPath("$.[*].aworker08").value(hasItem(sameNumber(DEFAULT_AWORKER_08))))
            .andExpect(jsonPath("$.[*].aworker09").value(hasItem(sameNumber(DEFAULT_AWORKER_09))))
            .andExpect(jsonPath("$.[*].aworker10").value(hasItem(sameNumber(DEFAULT_AWORKER_10))))
            .andExpect(jsonPath("$.[*].aworker11").value(hasItem(sameNumber(DEFAULT_AWORKER_11))))
            .andExpect(jsonPath("$.[*].aworker12").value(hasItem(sameNumber(DEFAULT_AWORKER_12))))
            .andExpect(jsonPath("$.[*].ajobtype01").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_01))))
            .andExpect(jsonPath("$.[*].ajobtype02").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_02))))
            .andExpect(jsonPath("$.[*].ajobtype03").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_03))))
            .andExpect(jsonPath("$.[*].ajobtype04").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_04))))
            .andExpect(jsonPath("$.[*].ajobtype05").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_05))))
            .andExpect(jsonPath("$.[*].ajobtype06").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_06))))
            .andExpect(jsonPath("$.[*].ajobtype07").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_07))))
            .andExpect(jsonPath("$.[*].ajobtype08").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_08))))
            .andExpect(jsonPath("$.[*].ajobtype09").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_09))))
            .andExpect(jsonPath("$.[*].ajobtype10").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_10))))
            .andExpect(jsonPath("$.[*].ajobtype11").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_11))))
            .andExpect(jsonPath("$.[*].ajobtype12").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_12))))
            .andExpect(jsonPath("$.[*].ajobtype13").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_13))))
            .andExpect(jsonPath("$.[*].ajobtype14").value(hasItem(sameNumber(DEFAULT_AJOBTYPE_14))))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].confOut").value(hasItem(sameNumber(DEFAULT_CONF_OUT))))
            .andExpect(jsonPath("$.[*].confIn").value(hasItem(sameNumber(DEFAULT_CONF_IN))))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
            .andExpect(jsonPath("$.[*].worker13").value(hasItem(sameNumber(DEFAULT_WORKER_13))))
            .andExpect(jsonPath("$.[*].worker14").value(hasItem(sameNumber(DEFAULT_WORKER_14))))
            .andExpect(jsonPath("$.[*].worker15").value(hasItem(sameNumber(DEFAULT_WORKER_15))))
            .andExpect(jsonPath("$.[*].worker16").value(hasItem(sameNumber(DEFAULT_WORKER_16))))
            .andExpect(jsonPath("$.[*].workerA13").value(hasItem(sameNumber(DEFAULT_WORKER_A_13))))
            .andExpect(jsonPath("$.[*].workerA14").value(hasItem(sameNumber(DEFAULT_WORKER_A_14))))
            .andExpect(jsonPath("$.[*].workerA15").value(hasItem(sameNumber(DEFAULT_WORKER_A_15))))
            .andExpect(jsonPath("$.[*].workerA16").value(hasItem(sameNumber(DEFAULT_WORKER_A_16))))
            .andExpect(jsonPath("$.[*].workerB13").value(hasItem(sameNumber(DEFAULT_WORKER_B_13))))
            .andExpect(jsonPath("$.[*].workerB14").value(hasItem(sameNumber(DEFAULT_WORKER_B_14))))
            .andExpect(jsonPath("$.[*].workerB15").value(hasItem(sameNumber(DEFAULT_WORKER_B_15))))
            .andExpect(jsonPath("$.[*].workerB16").value(hasItem(sameNumber(DEFAULT_WORKER_B_16))))
            .andExpect(jsonPath("$.[*].workerC13").value(hasItem(sameNumber(DEFAULT_WORKER_C_13))))
            .andExpect(jsonPath("$.[*].workerC14").value(hasItem(sameNumber(DEFAULT_WORKER_C_14))))
            .andExpect(jsonPath("$.[*].workerC15").value(hasItem(sameNumber(DEFAULT_WORKER_C_15))))
            .andExpect(jsonPath("$.[*].workerC16").value(hasItem(sameNumber(DEFAULT_WORKER_C_16))))
            .andExpect(jsonPath("$.[*].aworker13").value(hasItem(sameNumber(DEFAULT_AWORKER_13))))
            .andExpect(jsonPath("$.[*].aworker14").value(hasItem(sameNumber(DEFAULT_AWORKER_14))))
            .andExpect(jsonPath("$.[*].aworker15").value(hasItem(sameNumber(DEFAULT_AWORKER_15))))
            .andExpect(jsonPath("$.[*].aworker16").value(hasItem(sameNumber(DEFAULT_AWORKER_16))));
    }

    @Test
    @Transactional
    void getBidPrjWksorc() throws Exception {
        // Initialize the database
        bidPrjWksorcRepository.saveAndFlush(bidPrjWksorc);

        // Get the bidPrjWksorc
        restBidPrjWksorcMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjWksorc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjWksorc.getId().intValue()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.syr").value(DEFAULT_SYR))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
            .andExpect(jsonPath("$.person01").value(sameNumber(DEFAULT_PERSON_01)))
            .andExpect(jsonPath("$.person02").value(sameNumber(DEFAULT_PERSON_02)))
            .andExpect(jsonPath("$.worker01").value(sameNumber(DEFAULT_WORKER_01)))
            .andExpect(jsonPath("$.worker02").value(sameNumber(DEFAULT_WORKER_02)))
            .andExpect(jsonPath("$.worker03").value(sameNumber(DEFAULT_WORKER_03)))
            .andExpect(jsonPath("$.worker04").value(sameNumber(DEFAULT_WORKER_04)))
            .andExpect(jsonPath("$.worker05").value(sameNumber(DEFAULT_WORKER_05)))
            .andExpect(jsonPath("$.worker06").value(sameNumber(DEFAULT_WORKER_06)))
            .andExpect(jsonPath("$.worker07").value(sameNumber(DEFAULT_WORKER_07)))
            .andExpect(jsonPath("$.worker08").value(sameNumber(DEFAULT_WORKER_08)))
            .andExpect(jsonPath("$.worker09").value(sameNumber(DEFAULT_WORKER_09)))
            .andExpect(jsonPath("$.worker10").value(sameNumber(DEFAULT_WORKER_10)))
            .andExpect(jsonPath("$.worker11").value(sameNumber(DEFAULT_WORKER_11)))
            .andExpect(jsonPath("$.worker12").value(sameNumber(DEFAULT_WORKER_12)))
            .andExpect(jsonPath("$.personA01").value(sameNumber(DEFAULT_PERSON_A_01)))
            .andExpect(jsonPath("$.personA02").value(sameNumber(DEFAULT_PERSON_A_02)))
            .andExpect(jsonPath("$.workerA01").value(sameNumber(DEFAULT_WORKER_A_01)))
            .andExpect(jsonPath("$.workerA02").value(sameNumber(DEFAULT_WORKER_A_02)))
            .andExpect(jsonPath("$.workerA03").value(sameNumber(DEFAULT_WORKER_A_03)))
            .andExpect(jsonPath("$.workerA04").value(sameNumber(DEFAULT_WORKER_A_04)))
            .andExpect(jsonPath("$.workerA05").value(sameNumber(DEFAULT_WORKER_A_05)))
            .andExpect(jsonPath("$.workerA06").value(sameNumber(DEFAULT_WORKER_A_06)))
            .andExpect(jsonPath("$.workerA07").value(sameNumber(DEFAULT_WORKER_A_07)))
            .andExpect(jsonPath("$.workerA08").value(sameNumber(DEFAULT_WORKER_A_08)))
            .andExpect(jsonPath("$.workerA09").value(sameNumber(DEFAULT_WORKER_A_09)))
            .andExpect(jsonPath("$.workerA10").value(sameNumber(DEFAULT_WORKER_A_10)))
            .andExpect(jsonPath("$.workerA11").value(sameNumber(DEFAULT_WORKER_A_11)))
            .andExpect(jsonPath("$.workerA12").value(sameNumber(DEFAULT_WORKER_A_12)))
            .andExpect(jsonPath("$.personB01").value(sameNumber(DEFAULT_PERSON_B_01)))
            .andExpect(jsonPath("$.personB02").value(sameNumber(DEFAULT_PERSON_B_02)))
            .andExpect(jsonPath("$.workerB01").value(sameNumber(DEFAULT_WORKER_B_01)))
            .andExpect(jsonPath("$.workerB02").value(sameNumber(DEFAULT_WORKER_B_02)))
            .andExpect(jsonPath("$.workerB03").value(sameNumber(DEFAULT_WORKER_B_03)))
            .andExpect(jsonPath("$.workerB04").value(sameNumber(DEFAULT_WORKER_B_04)))
            .andExpect(jsonPath("$.workerB05").value(sameNumber(DEFAULT_WORKER_B_05)))
            .andExpect(jsonPath("$.workerB06").value(sameNumber(DEFAULT_WORKER_B_06)))
            .andExpect(jsonPath("$.workerB07").value(sameNumber(DEFAULT_WORKER_B_07)))
            .andExpect(jsonPath("$.workerB08").value(sameNumber(DEFAULT_WORKER_B_08)))
            .andExpect(jsonPath("$.workerB09").value(sameNumber(DEFAULT_WORKER_B_09)))
            .andExpect(jsonPath("$.workerB10").value(sameNumber(DEFAULT_WORKER_B_10)))
            .andExpect(jsonPath("$.workerB11").value(sameNumber(DEFAULT_WORKER_B_11)))
            .andExpect(jsonPath("$.workerB12").value(sameNumber(DEFAULT_WORKER_B_12)))
            .andExpect(jsonPath("$.personC01").value(sameNumber(DEFAULT_PERSON_C_01)))
            .andExpect(jsonPath("$.personC02").value(sameNumber(DEFAULT_PERSON_C_02)))
            .andExpect(jsonPath("$.workerC01").value(sameNumber(DEFAULT_WORKER_C_01)))
            .andExpect(jsonPath("$.workerC02").value(sameNumber(DEFAULT_WORKER_C_02)))
            .andExpect(jsonPath("$.workerC03").value(sameNumber(DEFAULT_WORKER_C_03)))
            .andExpect(jsonPath("$.workerC04").value(sameNumber(DEFAULT_WORKER_C_04)))
            .andExpect(jsonPath("$.workerC05").value(sameNumber(DEFAULT_WORKER_C_05)))
            .andExpect(jsonPath("$.workerC06").value(sameNumber(DEFAULT_WORKER_C_06)))
            .andExpect(jsonPath("$.workerC07").value(sameNumber(DEFAULT_WORKER_C_07)))
            .andExpect(jsonPath("$.workerC08").value(sameNumber(DEFAULT_WORKER_C_08)))
            .andExpect(jsonPath("$.workerC09").value(sameNumber(DEFAULT_WORKER_C_09)))
            .andExpect(jsonPath("$.workerC10").value(sameNumber(DEFAULT_WORKER_C_10)))
            .andExpect(jsonPath("$.workerC11").value(sameNumber(DEFAULT_WORKER_C_11)))
            .andExpect(jsonPath("$.workerC12").value(sameNumber(DEFAULT_WORKER_C_12)))
            .andExpect(jsonPath("$.person01Salary").value(DEFAULT_PERSON_01_SALARY))
            .andExpect(jsonPath("$.person02Salary").value(DEFAULT_PERSON_02_SALARY))
            .andExpect(jsonPath("$.worker01Salary").value(DEFAULT_WORKER_01_SALARY))
            .andExpect(jsonPath("$.worker02Salary").value(DEFAULT_WORKER_02_SALARY))
            .andExpect(jsonPath("$.worker03Salary").value(DEFAULT_WORKER_03_SALARY))
            .andExpect(jsonPath("$.worker04Salary").value(DEFAULT_WORKER_04_SALARY))
            .andExpect(jsonPath("$.worker05Salary").value(DEFAULT_WORKER_05_SALARY))
            .andExpect(jsonPath("$.worker06Salary").value(DEFAULT_WORKER_06_SALARY))
            .andExpect(jsonPath("$.worker07Salary").value(DEFAULT_WORKER_07_SALARY))
            .andExpect(jsonPath("$.worker08Salary").value(DEFAULT_WORKER_08_SALARY))
            .andExpect(jsonPath("$.worker09Salary").value(DEFAULT_WORKER_09_SALARY))
            .andExpect(jsonPath("$.worker10Salary").value(DEFAULT_WORKER_10_SALARY))
            .andExpect(jsonPath("$.worker11Salary").value(DEFAULT_WORKER_11_SALARY))
            .andExpect(jsonPath("$.worker12Salary").value(DEFAULT_WORKER_12_SALARY))
            .andExpect(jsonPath("$.aperson01").value(sameNumber(DEFAULT_APERSON_01)))
            .andExpect(jsonPath("$.aperson02").value(sameNumber(DEFAULT_APERSON_02)))
            .andExpect(jsonPath("$.aworker01").value(sameNumber(DEFAULT_AWORKER_01)))
            .andExpect(jsonPath("$.aworker02").value(sameNumber(DEFAULT_AWORKER_02)))
            .andExpect(jsonPath("$.aworker03").value(sameNumber(DEFAULT_AWORKER_03)))
            .andExpect(jsonPath("$.aworker04").value(sameNumber(DEFAULT_AWORKER_04)))
            .andExpect(jsonPath("$.aworker05").value(sameNumber(DEFAULT_AWORKER_05)))
            .andExpect(jsonPath("$.aworker06").value(sameNumber(DEFAULT_AWORKER_06)))
            .andExpect(jsonPath("$.aworker07").value(sameNumber(DEFAULT_AWORKER_07)))
            .andExpect(jsonPath("$.aworker08").value(sameNumber(DEFAULT_AWORKER_08)))
            .andExpect(jsonPath("$.aworker09").value(sameNumber(DEFAULT_AWORKER_09)))
            .andExpect(jsonPath("$.aworker10").value(sameNumber(DEFAULT_AWORKER_10)))
            .andExpect(jsonPath("$.aworker11").value(sameNumber(DEFAULT_AWORKER_11)))
            .andExpect(jsonPath("$.aworker12").value(sameNumber(DEFAULT_AWORKER_12)))
            .andExpect(jsonPath("$.ajobtype01").value(sameNumber(DEFAULT_AJOBTYPE_01)))
            .andExpect(jsonPath("$.ajobtype02").value(sameNumber(DEFAULT_AJOBTYPE_02)))
            .andExpect(jsonPath("$.ajobtype03").value(sameNumber(DEFAULT_AJOBTYPE_03)))
            .andExpect(jsonPath("$.ajobtype04").value(sameNumber(DEFAULT_AJOBTYPE_04)))
            .andExpect(jsonPath("$.ajobtype05").value(sameNumber(DEFAULT_AJOBTYPE_05)))
            .andExpect(jsonPath("$.ajobtype06").value(sameNumber(DEFAULT_AJOBTYPE_06)))
            .andExpect(jsonPath("$.ajobtype07").value(sameNumber(DEFAULT_AJOBTYPE_07)))
            .andExpect(jsonPath("$.ajobtype08").value(sameNumber(DEFAULT_AJOBTYPE_08)))
            .andExpect(jsonPath("$.ajobtype09").value(sameNumber(DEFAULT_AJOBTYPE_09)))
            .andExpect(jsonPath("$.ajobtype10").value(sameNumber(DEFAULT_AJOBTYPE_10)))
            .andExpect(jsonPath("$.ajobtype11").value(sameNumber(DEFAULT_AJOBTYPE_11)))
            .andExpect(jsonPath("$.ajobtype12").value(sameNumber(DEFAULT_AJOBTYPE_12)))
            .andExpect(jsonPath("$.ajobtype13").value(sameNumber(DEFAULT_AJOBTYPE_13)))
            .andExpect(jsonPath("$.ajobtype14").value(sameNumber(DEFAULT_AJOBTYPE_14)))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.confOut").value(sameNumber(DEFAULT_CONF_OUT)))
            .andExpect(jsonPath("$.confIn").value(sameNumber(DEFAULT_CONF_IN)))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
            .andExpect(jsonPath("$.worker13").value(sameNumber(DEFAULT_WORKER_13)))
            .andExpect(jsonPath("$.worker14").value(sameNumber(DEFAULT_WORKER_14)))
            .andExpect(jsonPath("$.worker15").value(sameNumber(DEFAULT_WORKER_15)))
            .andExpect(jsonPath("$.worker16").value(sameNumber(DEFAULT_WORKER_16)))
            .andExpect(jsonPath("$.workerA13").value(sameNumber(DEFAULT_WORKER_A_13)))
            .andExpect(jsonPath("$.workerA14").value(sameNumber(DEFAULT_WORKER_A_14)))
            .andExpect(jsonPath("$.workerA15").value(sameNumber(DEFAULT_WORKER_A_15)))
            .andExpect(jsonPath("$.workerA16").value(sameNumber(DEFAULT_WORKER_A_16)))
            .andExpect(jsonPath("$.workerB13").value(sameNumber(DEFAULT_WORKER_B_13)))
            .andExpect(jsonPath("$.workerB14").value(sameNumber(DEFAULT_WORKER_B_14)))
            .andExpect(jsonPath("$.workerB15").value(sameNumber(DEFAULT_WORKER_B_15)))
            .andExpect(jsonPath("$.workerB16").value(sameNumber(DEFAULT_WORKER_B_16)))
            .andExpect(jsonPath("$.workerC13").value(sameNumber(DEFAULT_WORKER_C_13)))
            .andExpect(jsonPath("$.workerC14").value(sameNumber(DEFAULT_WORKER_C_14)))
            .andExpect(jsonPath("$.workerC15").value(sameNumber(DEFAULT_WORKER_C_15)))
            .andExpect(jsonPath("$.workerC16").value(sameNumber(DEFAULT_WORKER_C_16)))
            .andExpect(jsonPath("$.aworker13").value(sameNumber(DEFAULT_AWORKER_13)))
            .andExpect(jsonPath("$.aworker14").value(sameNumber(DEFAULT_AWORKER_14)))
            .andExpect(jsonPath("$.aworker15").value(sameNumber(DEFAULT_AWORKER_15)))
            .andExpect(jsonPath("$.aworker16").value(sameNumber(DEFAULT_AWORKER_16)));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjWksorc() throws Exception {
        // Get the bidPrjWksorc
        restBidPrjWksorcMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjWksorc() throws Exception {
        // Initialize the database
        bidPrjWksorcRepository.saveAndFlush(bidPrjWksorc);

        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();

        // Update the bidPrjWksorc
        BidPrjWksorc updatedBidPrjWksorc = bidPrjWksorcRepository.findById(bidPrjWksorc.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjWksorc are not directly saved in db
        em.detach(updatedBidPrjWksorc);
        updatedBidPrjWksorc
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .syr(UPDATED_SYR)
            .month(UPDATED_MONTH)
            .person01(UPDATED_PERSON_01)
            .person02(UPDATED_PERSON_02)
            .worker01(UPDATED_WORKER_01)
            .worker02(UPDATED_WORKER_02)
            .worker03(UPDATED_WORKER_03)
            .worker04(UPDATED_WORKER_04)
            .worker05(UPDATED_WORKER_05)
            .worker06(UPDATED_WORKER_06)
            .worker07(UPDATED_WORKER_07)
            .worker08(UPDATED_WORKER_08)
            .worker09(UPDATED_WORKER_09)
            .worker10(UPDATED_WORKER_10)
            .worker11(UPDATED_WORKER_11)
            .worker12(UPDATED_WORKER_12)
            .personA01(UPDATED_PERSON_A_01)
            .personA02(UPDATED_PERSON_A_02)
            .workerA01(UPDATED_WORKER_A_01)
            .workerA02(UPDATED_WORKER_A_02)
            .workerA03(UPDATED_WORKER_A_03)
            .workerA04(UPDATED_WORKER_A_04)
            .workerA05(UPDATED_WORKER_A_05)
            .workerA06(UPDATED_WORKER_A_06)
            .workerA07(UPDATED_WORKER_A_07)
            .workerA08(UPDATED_WORKER_A_08)
            .workerA09(UPDATED_WORKER_A_09)
            .workerA10(UPDATED_WORKER_A_10)
            .workerA11(UPDATED_WORKER_A_11)
            .workerA12(UPDATED_WORKER_A_12)
            .personB01(UPDATED_PERSON_B_01)
            .personB02(UPDATED_PERSON_B_02)
            .workerB01(UPDATED_WORKER_B_01)
            .workerB02(UPDATED_WORKER_B_02)
            .workerB03(UPDATED_WORKER_B_03)
            .workerB04(UPDATED_WORKER_B_04)
            .workerB05(UPDATED_WORKER_B_05)
            .workerB06(UPDATED_WORKER_B_06)
            .workerB07(UPDATED_WORKER_B_07)
            .workerB08(UPDATED_WORKER_B_08)
            .workerB09(UPDATED_WORKER_B_09)
            .workerB10(UPDATED_WORKER_B_10)
            .workerB11(UPDATED_WORKER_B_11)
            .workerB12(UPDATED_WORKER_B_12)
            .personC01(UPDATED_PERSON_C_01)
            .personC02(UPDATED_PERSON_C_02)
            .workerC01(UPDATED_WORKER_C_01)
            .workerC02(UPDATED_WORKER_C_02)
            .workerC03(UPDATED_WORKER_C_03)
            .workerC04(UPDATED_WORKER_C_04)
            .workerC05(UPDATED_WORKER_C_05)
            .workerC06(UPDATED_WORKER_C_06)
            .workerC07(UPDATED_WORKER_C_07)
            .workerC08(UPDATED_WORKER_C_08)
            .workerC09(UPDATED_WORKER_C_09)
            .workerC10(UPDATED_WORKER_C_10)
            .workerC11(UPDATED_WORKER_C_11)
            .workerC12(UPDATED_WORKER_C_12)
            .person01Salary(UPDATED_PERSON_01_SALARY)
            .person02Salary(UPDATED_PERSON_02_SALARY)
            .worker01Salary(UPDATED_WORKER_01_SALARY)
            .worker02Salary(UPDATED_WORKER_02_SALARY)
            .worker03Salary(UPDATED_WORKER_03_SALARY)
            .worker04Salary(UPDATED_WORKER_04_SALARY)
            .worker05Salary(UPDATED_WORKER_05_SALARY)
            .worker06Salary(UPDATED_WORKER_06_SALARY)
            .worker07Salary(UPDATED_WORKER_07_SALARY)
            .worker08Salary(UPDATED_WORKER_08_SALARY)
            .worker09Salary(UPDATED_WORKER_09_SALARY)
            .worker10Salary(UPDATED_WORKER_10_SALARY)
            .worker11Salary(UPDATED_WORKER_11_SALARY)
            .worker12Salary(UPDATED_WORKER_12_SALARY)
            .aperson01(UPDATED_APERSON_01)
            .aperson02(UPDATED_APERSON_02)
            .aworker01(UPDATED_AWORKER_01)
            .aworker02(UPDATED_AWORKER_02)
            .aworker03(UPDATED_AWORKER_03)
            .aworker04(UPDATED_AWORKER_04)
            .aworker05(UPDATED_AWORKER_05)
            .aworker06(UPDATED_AWORKER_06)
            .aworker07(UPDATED_AWORKER_07)
            .aworker08(UPDATED_AWORKER_08)
            .aworker09(UPDATED_AWORKER_09)
            .aworker10(UPDATED_AWORKER_10)
            .aworker11(UPDATED_AWORKER_11)
            .aworker12(UPDATED_AWORKER_12)
            .ajobtype01(UPDATED_AJOBTYPE_01)
            .ajobtype02(UPDATED_AJOBTYPE_02)
            .ajobtype03(UPDATED_AJOBTYPE_03)
            .ajobtype04(UPDATED_AJOBTYPE_04)
            .ajobtype05(UPDATED_AJOBTYPE_05)
            .ajobtype06(UPDATED_AJOBTYPE_06)
            .ajobtype07(UPDATED_AJOBTYPE_07)
            .ajobtype08(UPDATED_AJOBTYPE_08)
            .ajobtype09(UPDATED_AJOBTYPE_09)
            .ajobtype10(UPDATED_AJOBTYPE_10)
            .ajobtype11(UPDATED_AJOBTYPE_11)
            .ajobtype12(UPDATED_AJOBTYPE_12)
            .ajobtype13(UPDATED_AJOBTYPE_13)
            .ajobtype14(UPDATED_AJOBTYPE_14)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .confOut(UPDATED_CONF_OUT)
            .confIn(UPDATED_CONF_IN)
            .uIp(UPDATED_U_IP)
            .worker13(UPDATED_WORKER_13)
            .worker14(UPDATED_WORKER_14)
            .worker15(UPDATED_WORKER_15)
            .worker16(UPDATED_WORKER_16)
            .workerA13(UPDATED_WORKER_A_13)
            .workerA14(UPDATED_WORKER_A_14)
            .workerA15(UPDATED_WORKER_A_15)
            .workerA16(UPDATED_WORKER_A_16)
            .workerB13(UPDATED_WORKER_B_13)
            .workerB14(UPDATED_WORKER_B_14)
            .workerB15(UPDATED_WORKER_B_15)
            .workerB16(UPDATED_WORKER_B_16)
            .workerC13(UPDATED_WORKER_C_13)
            .workerC14(UPDATED_WORKER_C_14)
            .workerC15(UPDATED_WORKER_C_15)
            .workerC16(UPDATED_WORKER_C_16)
            .aworker13(UPDATED_AWORKER_13)
            .aworker14(UPDATED_AWORKER_14)
            .aworker15(UPDATED_AWORKER_15)
            .aworker16(UPDATED_AWORKER_16);
        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(updatedBidPrjWksorc);

        restBidPrjWksorcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjWksorcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjWksorc in the database
        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjWksorc testBidPrjWksorc = bidPrjWksorcList.get(bidPrjWksorcList.size() - 1);
        assertThat(testBidPrjWksorc.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjWksorc.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjWksorc.getSyr()).isEqualTo(UPDATED_SYR);
        assertThat(testBidPrjWksorc.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjWksorc.getPerson01()).isEqualByComparingTo(UPDATED_PERSON_01);
        assertThat(testBidPrjWksorc.getPerson02()).isEqualByComparingTo(UPDATED_PERSON_02);
        assertThat(testBidPrjWksorc.getWorker01()).isEqualByComparingTo(UPDATED_WORKER_01);
        assertThat(testBidPrjWksorc.getWorker02()).isEqualByComparingTo(UPDATED_WORKER_02);
        assertThat(testBidPrjWksorc.getWorker03()).isEqualByComparingTo(UPDATED_WORKER_03);
        assertThat(testBidPrjWksorc.getWorker04()).isEqualByComparingTo(UPDATED_WORKER_04);
        assertThat(testBidPrjWksorc.getWorker05()).isEqualByComparingTo(UPDATED_WORKER_05);
        assertThat(testBidPrjWksorc.getWorker06()).isEqualByComparingTo(UPDATED_WORKER_06);
        assertThat(testBidPrjWksorc.getWorker07()).isEqualByComparingTo(UPDATED_WORKER_07);
        assertThat(testBidPrjWksorc.getWorker08()).isEqualByComparingTo(UPDATED_WORKER_08);
        assertThat(testBidPrjWksorc.getWorker09()).isEqualByComparingTo(UPDATED_WORKER_09);
        assertThat(testBidPrjWksorc.getWorker10()).isEqualByComparingTo(UPDATED_WORKER_10);
        assertThat(testBidPrjWksorc.getWorker11()).isEqualByComparingTo(UPDATED_WORKER_11);
        assertThat(testBidPrjWksorc.getWorker12()).isEqualByComparingTo(UPDATED_WORKER_12);
        assertThat(testBidPrjWksorc.getPersonA01()).isEqualByComparingTo(UPDATED_PERSON_A_01);
        assertThat(testBidPrjWksorc.getPersonA02()).isEqualByComparingTo(UPDATED_PERSON_A_02);
        assertThat(testBidPrjWksorc.getWorkerA01()).isEqualByComparingTo(UPDATED_WORKER_A_01);
        assertThat(testBidPrjWksorc.getWorkerA02()).isEqualByComparingTo(UPDATED_WORKER_A_02);
        assertThat(testBidPrjWksorc.getWorkerA03()).isEqualByComparingTo(UPDATED_WORKER_A_03);
        assertThat(testBidPrjWksorc.getWorkerA04()).isEqualByComparingTo(UPDATED_WORKER_A_04);
        assertThat(testBidPrjWksorc.getWorkerA05()).isEqualByComparingTo(UPDATED_WORKER_A_05);
        assertThat(testBidPrjWksorc.getWorkerA06()).isEqualByComparingTo(UPDATED_WORKER_A_06);
        assertThat(testBidPrjWksorc.getWorkerA07()).isEqualByComparingTo(UPDATED_WORKER_A_07);
        assertThat(testBidPrjWksorc.getWorkerA08()).isEqualByComparingTo(UPDATED_WORKER_A_08);
        assertThat(testBidPrjWksorc.getWorkerA09()).isEqualByComparingTo(UPDATED_WORKER_A_09);
        assertThat(testBidPrjWksorc.getWorkerA10()).isEqualByComparingTo(UPDATED_WORKER_A_10);
        assertThat(testBidPrjWksorc.getWorkerA11()).isEqualByComparingTo(UPDATED_WORKER_A_11);
        assertThat(testBidPrjWksorc.getWorkerA12()).isEqualByComparingTo(UPDATED_WORKER_A_12);
        assertThat(testBidPrjWksorc.getPersonB01()).isEqualByComparingTo(UPDATED_PERSON_B_01);
        assertThat(testBidPrjWksorc.getPersonB02()).isEqualByComparingTo(UPDATED_PERSON_B_02);
        assertThat(testBidPrjWksorc.getWorkerB01()).isEqualByComparingTo(UPDATED_WORKER_B_01);
        assertThat(testBidPrjWksorc.getWorkerB02()).isEqualByComparingTo(UPDATED_WORKER_B_02);
        assertThat(testBidPrjWksorc.getWorkerB03()).isEqualByComparingTo(UPDATED_WORKER_B_03);
        assertThat(testBidPrjWksorc.getWorkerB04()).isEqualByComparingTo(UPDATED_WORKER_B_04);
        assertThat(testBidPrjWksorc.getWorkerB05()).isEqualByComparingTo(UPDATED_WORKER_B_05);
        assertThat(testBidPrjWksorc.getWorkerB06()).isEqualByComparingTo(UPDATED_WORKER_B_06);
        assertThat(testBidPrjWksorc.getWorkerB07()).isEqualByComparingTo(UPDATED_WORKER_B_07);
        assertThat(testBidPrjWksorc.getWorkerB08()).isEqualByComparingTo(UPDATED_WORKER_B_08);
        assertThat(testBidPrjWksorc.getWorkerB09()).isEqualByComparingTo(UPDATED_WORKER_B_09);
        assertThat(testBidPrjWksorc.getWorkerB10()).isEqualByComparingTo(UPDATED_WORKER_B_10);
        assertThat(testBidPrjWksorc.getWorkerB11()).isEqualByComparingTo(UPDATED_WORKER_B_11);
        assertThat(testBidPrjWksorc.getWorkerB12()).isEqualByComparingTo(UPDATED_WORKER_B_12);
        assertThat(testBidPrjWksorc.getPersonC01()).isEqualByComparingTo(UPDATED_PERSON_C_01);
        assertThat(testBidPrjWksorc.getPersonC02()).isEqualByComparingTo(UPDATED_PERSON_C_02);
        assertThat(testBidPrjWksorc.getWorkerC01()).isEqualByComparingTo(UPDATED_WORKER_C_01);
        assertThat(testBidPrjWksorc.getWorkerC02()).isEqualByComparingTo(UPDATED_WORKER_C_02);
        assertThat(testBidPrjWksorc.getWorkerC03()).isEqualByComparingTo(UPDATED_WORKER_C_03);
        assertThat(testBidPrjWksorc.getWorkerC04()).isEqualByComparingTo(UPDATED_WORKER_C_04);
        assertThat(testBidPrjWksorc.getWorkerC05()).isEqualByComparingTo(UPDATED_WORKER_C_05);
        assertThat(testBidPrjWksorc.getWorkerC06()).isEqualByComparingTo(UPDATED_WORKER_C_06);
        assertThat(testBidPrjWksorc.getWorkerC07()).isEqualByComparingTo(UPDATED_WORKER_C_07);
        assertThat(testBidPrjWksorc.getWorkerC08()).isEqualByComparingTo(UPDATED_WORKER_C_08);
        assertThat(testBidPrjWksorc.getWorkerC09()).isEqualByComparingTo(UPDATED_WORKER_C_09);
        assertThat(testBidPrjWksorc.getWorkerC10()).isEqualByComparingTo(UPDATED_WORKER_C_10);
        assertThat(testBidPrjWksorc.getWorkerC11()).isEqualByComparingTo(UPDATED_WORKER_C_11);
        assertThat(testBidPrjWksorc.getWorkerC12()).isEqualByComparingTo(UPDATED_WORKER_C_12);
        assertThat(testBidPrjWksorc.getPerson01Salary()).isEqualTo(UPDATED_PERSON_01_SALARY);
        assertThat(testBidPrjWksorc.getPerson02Salary()).isEqualTo(UPDATED_PERSON_02_SALARY);
        assertThat(testBidPrjWksorc.getWorker01Salary()).isEqualTo(UPDATED_WORKER_01_SALARY);
        assertThat(testBidPrjWksorc.getWorker02Salary()).isEqualTo(UPDATED_WORKER_02_SALARY);
        assertThat(testBidPrjWksorc.getWorker03Salary()).isEqualTo(UPDATED_WORKER_03_SALARY);
        assertThat(testBidPrjWksorc.getWorker04Salary()).isEqualTo(UPDATED_WORKER_04_SALARY);
        assertThat(testBidPrjWksorc.getWorker05Salary()).isEqualTo(UPDATED_WORKER_05_SALARY);
        assertThat(testBidPrjWksorc.getWorker06Salary()).isEqualTo(UPDATED_WORKER_06_SALARY);
        assertThat(testBidPrjWksorc.getWorker07Salary()).isEqualTo(UPDATED_WORKER_07_SALARY);
        assertThat(testBidPrjWksorc.getWorker08Salary()).isEqualTo(UPDATED_WORKER_08_SALARY);
        assertThat(testBidPrjWksorc.getWorker09Salary()).isEqualTo(UPDATED_WORKER_09_SALARY);
        assertThat(testBidPrjWksorc.getWorker10Salary()).isEqualTo(UPDATED_WORKER_10_SALARY);
        assertThat(testBidPrjWksorc.getWorker11Salary()).isEqualTo(UPDATED_WORKER_11_SALARY);
        assertThat(testBidPrjWksorc.getWorker12Salary()).isEqualTo(UPDATED_WORKER_12_SALARY);
        assertThat(testBidPrjWksorc.getAperson01()).isEqualByComparingTo(UPDATED_APERSON_01);
        assertThat(testBidPrjWksorc.getAperson02()).isEqualByComparingTo(UPDATED_APERSON_02);
        assertThat(testBidPrjWksorc.getAworker01()).isEqualByComparingTo(UPDATED_AWORKER_01);
        assertThat(testBidPrjWksorc.getAworker02()).isEqualByComparingTo(UPDATED_AWORKER_02);
        assertThat(testBidPrjWksorc.getAworker03()).isEqualByComparingTo(UPDATED_AWORKER_03);
        assertThat(testBidPrjWksorc.getAworker04()).isEqualByComparingTo(UPDATED_AWORKER_04);
        assertThat(testBidPrjWksorc.getAworker05()).isEqualByComparingTo(UPDATED_AWORKER_05);
        assertThat(testBidPrjWksorc.getAworker06()).isEqualByComparingTo(UPDATED_AWORKER_06);
        assertThat(testBidPrjWksorc.getAworker07()).isEqualByComparingTo(UPDATED_AWORKER_07);
        assertThat(testBidPrjWksorc.getAworker08()).isEqualByComparingTo(UPDATED_AWORKER_08);
        assertThat(testBidPrjWksorc.getAworker09()).isEqualByComparingTo(UPDATED_AWORKER_09);
        assertThat(testBidPrjWksorc.getAworker10()).isEqualByComparingTo(UPDATED_AWORKER_10);
        assertThat(testBidPrjWksorc.getAworker11()).isEqualByComparingTo(UPDATED_AWORKER_11);
        assertThat(testBidPrjWksorc.getAworker12()).isEqualByComparingTo(UPDATED_AWORKER_12);
        assertThat(testBidPrjWksorc.getAjobtype01()).isEqualByComparingTo(UPDATED_AJOBTYPE_01);
        assertThat(testBidPrjWksorc.getAjobtype02()).isEqualByComparingTo(UPDATED_AJOBTYPE_02);
        assertThat(testBidPrjWksorc.getAjobtype03()).isEqualByComparingTo(UPDATED_AJOBTYPE_03);
        assertThat(testBidPrjWksorc.getAjobtype04()).isEqualByComparingTo(UPDATED_AJOBTYPE_04);
        assertThat(testBidPrjWksorc.getAjobtype05()).isEqualByComparingTo(UPDATED_AJOBTYPE_05);
        assertThat(testBidPrjWksorc.getAjobtype06()).isEqualByComparingTo(UPDATED_AJOBTYPE_06);
        assertThat(testBidPrjWksorc.getAjobtype07()).isEqualByComparingTo(UPDATED_AJOBTYPE_07);
        assertThat(testBidPrjWksorc.getAjobtype08()).isEqualByComparingTo(UPDATED_AJOBTYPE_08);
        assertThat(testBidPrjWksorc.getAjobtype09()).isEqualByComparingTo(UPDATED_AJOBTYPE_09);
        assertThat(testBidPrjWksorc.getAjobtype10()).isEqualByComparingTo(UPDATED_AJOBTYPE_10);
        assertThat(testBidPrjWksorc.getAjobtype11()).isEqualByComparingTo(UPDATED_AJOBTYPE_11);
        assertThat(testBidPrjWksorc.getAjobtype12()).isEqualByComparingTo(UPDATED_AJOBTYPE_12);
        assertThat(testBidPrjWksorc.getAjobtype13()).isEqualByComparingTo(UPDATED_AJOBTYPE_13);
        assertThat(testBidPrjWksorc.getAjobtype14()).isEqualByComparingTo(UPDATED_AJOBTYPE_14);
        assertThat(testBidPrjWksorc.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjWksorc.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjWksorc.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjWksorc.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjWksorc.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjWksorc.getConfIn()).isEqualByComparingTo(UPDATED_CONF_IN);
        assertThat(testBidPrjWksorc.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjWksorc.getWorker13()).isEqualByComparingTo(UPDATED_WORKER_13);
        assertThat(testBidPrjWksorc.getWorker14()).isEqualByComparingTo(UPDATED_WORKER_14);
        assertThat(testBidPrjWksorc.getWorker15()).isEqualByComparingTo(UPDATED_WORKER_15);
        assertThat(testBidPrjWksorc.getWorker16()).isEqualByComparingTo(UPDATED_WORKER_16);
        assertThat(testBidPrjWksorc.getWorkerA13()).isEqualByComparingTo(UPDATED_WORKER_A_13);
        assertThat(testBidPrjWksorc.getWorkerA14()).isEqualByComparingTo(UPDATED_WORKER_A_14);
        assertThat(testBidPrjWksorc.getWorkerA15()).isEqualByComparingTo(UPDATED_WORKER_A_15);
        assertThat(testBidPrjWksorc.getWorkerA16()).isEqualByComparingTo(UPDATED_WORKER_A_16);
        assertThat(testBidPrjWksorc.getWorkerB13()).isEqualByComparingTo(UPDATED_WORKER_B_13);
        assertThat(testBidPrjWksorc.getWorkerB14()).isEqualByComparingTo(UPDATED_WORKER_B_14);
        assertThat(testBidPrjWksorc.getWorkerB15()).isEqualByComparingTo(UPDATED_WORKER_B_15);
        assertThat(testBidPrjWksorc.getWorkerB16()).isEqualByComparingTo(UPDATED_WORKER_B_16);
        assertThat(testBidPrjWksorc.getWorkerC13()).isEqualByComparingTo(UPDATED_WORKER_C_13);
        assertThat(testBidPrjWksorc.getWorkerC14()).isEqualByComparingTo(UPDATED_WORKER_C_14);
        assertThat(testBidPrjWksorc.getWorkerC15()).isEqualByComparingTo(UPDATED_WORKER_C_15);
        assertThat(testBidPrjWksorc.getWorkerC16()).isEqualByComparingTo(UPDATED_WORKER_C_16);
        assertThat(testBidPrjWksorc.getAworker13()).isEqualByComparingTo(UPDATED_AWORKER_13);
        assertThat(testBidPrjWksorc.getAworker14()).isEqualByComparingTo(UPDATED_AWORKER_14);
        assertThat(testBidPrjWksorc.getAworker15()).isEqualByComparingTo(UPDATED_AWORKER_15);
        assertThat(testBidPrjWksorc.getAworker16()).isEqualByComparingTo(UPDATED_AWORKER_16);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjWksorc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();
//        bidPrjWksorc.setId(count.incrementAndGet());
//
//        // Create the BidPrjWksorc
//        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjWksorcMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjWksorcDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjWksorc in the database
//        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
//        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjWksorc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();
//        bidPrjWksorc.setId(count.incrementAndGet());
//
//        // Create the BidPrjWksorc
//        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjWksorcMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjWksorc in the database
//        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
//        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjWksorc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();
//        bidPrjWksorc.setId(count.incrementAndGet());
//
//        // Create the BidPrjWksorc
//        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjWksorcMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjWksorc in the database
//        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
//        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjWksorcWithPatch() throws Exception {
        // Initialize the database
        bidPrjWksorcRepository.saveAndFlush(bidPrjWksorc);

        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();

        // Update the bidPrjWksorc using partial update
        BidPrjWksorc partialUpdatedBidPrjWksorc = new BidPrjWksorc();
        partialUpdatedBidPrjWksorc.setId(bidPrjWksorc.getId());

        partialUpdatedBidPrjWksorc
            .syr(UPDATED_SYR)
            .month(UPDATED_MONTH)
            .worker02(UPDATED_WORKER_02)
            .worker03(UPDATED_WORKER_03)
            .worker05(UPDATED_WORKER_05)
            .worker07(UPDATED_WORKER_07)
            .worker11(UPDATED_WORKER_11)
            .workerA01(UPDATED_WORKER_A_01)
            .workerA05(UPDATED_WORKER_A_05)
            .workerA08(UPDATED_WORKER_A_08)
            .personB01(UPDATED_PERSON_B_01)
            .workerB05(UPDATED_WORKER_B_05)
            .workerB07(UPDATED_WORKER_B_07)
            .workerB08(UPDATED_WORKER_B_08)
            .workerB09(UPDATED_WORKER_B_09)
            .workerB10(UPDATED_WORKER_B_10)
            .workerB11(UPDATED_WORKER_B_11)
            .personC01(UPDATED_PERSON_C_01)
            .personC02(UPDATED_PERSON_C_02)
            .workerC01(UPDATED_WORKER_C_01)
            .workerC02(UPDATED_WORKER_C_02)
            .workerC03(UPDATED_WORKER_C_03)
            .workerC04(UPDATED_WORKER_C_04)
            .workerC05(UPDATED_WORKER_C_05)
            .workerC06(UPDATED_WORKER_C_06)
            .workerC07(UPDATED_WORKER_C_07)
            .workerC09(UPDATED_WORKER_C_09)
            .worker02Salary(UPDATED_WORKER_02_SALARY)
            .worker05Salary(UPDATED_WORKER_05_SALARY)
            .worker09Salary(UPDATED_WORKER_09_SALARY)
            .worker10Salary(UPDATED_WORKER_10_SALARY)
            .aperson01(UPDATED_APERSON_01)
            .aperson02(UPDATED_APERSON_02)
            .aworker03(UPDATED_AWORKER_03)
            .aworker05(UPDATED_AWORKER_05)
            .aworker07(UPDATED_AWORKER_07)
            .aworker09(UPDATED_AWORKER_09)
            .aworker10(UPDATED_AWORKER_10)
            .ajobtype01(UPDATED_AJOBTYPE_01)
            .ajobtype03(UPDATED_AJOBTYPE_03)
            .ajobtype04(UPDATED_AJOBTYPE_04)
            .ajobtype05(UPDATED_AJOBTYPE_05)
            .ajobtype06(UPDATED_AJOBTYPE_06)
            .ajobtype08(UPDATED_AJOBTYPE_08)
            .ajobtype09(UPDATED_AJOBTYPE_09)
            .ajobtype12(UPDATED_AJOBTYPE_12)
            .ajobtype13(UPDATED_AJOBTYPE_13)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .confOut(UPDATED_CONF_OUT)
            .confIn(UPDATED_CONF_IN)
            .uIp(UPDATED_U_IP)
            .worker14(UPDATED_WORKER_14)
            .worker15(UPDATED_WORKER_15)
            .workerA14(UPDATED_WORKER_A_14)
            .workerA15(UPDATED_WORKER_A_15)
            .workerA16(UPDATED_WORKER_A_16)
            .workerC13(UPDATED_WORKER_C_13)
            .workerC15(UPDATED_WORKER_C_15)
            .workerC16(UPDATED_WORKER_C_16)
            .aworker13(UPDATED_AWORKER_13)
            .aworker14(UPDATED_AWORKER_14)
            .aworker15(UPDATED_AWORKER_15);

        restBidPrjWksorcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjWksorc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjWksorc))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjWksorc in the database
        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjWksorc testBidPrjWksorc = bidPrjWksorcList.get(bidPrjWksorcList.size() - 1);
        assertThat(testBidPrjWksorc.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjWksorc.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjWksorc.getSyr()).isEqualTo(UPDATED_SYR);
        assertThat(testBidPrjWksorc.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjWksorc.getPerson01()).isEqualByComparingTo(DEFAULT_PERSON_01);
        assertThat(testBidPrjWksorc.getPerson02()).isEqualByComparingTo(DEFAULT_PERSON_02);
        assertThat(testBidPrjWksorc.getWorker01()).isEqualByComparingTo(DEFAULT_WORKER_01);
        assertThat(testBidPrjWksorc.getWorker02()).isEqualByComparingTo(UPDATED_WORKER_02);
        assertThat(testBidPrjWksorc.getWorker03()).isEqualByComparingTo(UPDATED_WORKER_03);
        assertThat(testBidPrjWksorc.getWorker04()).isEqualByComparingTo(DEFAULT_WORKER_04);
        assertThat(testBidPrjWksorc.getWorker05()).isEqualByComparingTo(UPDATED_WORKER_05);
        assertThat(testBidPrjWksorc.getWorker06()).isEqualByComparingTo(DEFAULT_WORKER_06);
        assertThat(testBidPrjWksorc.getWorker07()).isEqualByComparingTo(UPDATED_WORKER_07);
        assertThat(testBidPrjWksorc.getWorker08()).isEqualByComparingTo(DEFAULT_WORKER_08);
        assertThat(testBidPrjWksorc.getWorker09()).isEqualByComparingTo(DEFAULT_WORKER_09);
        assertThat(testBidPrjWksorc.getWorker10()).isEqualByComparingTo(DEFAULT_WORKER_10);
        assertThat(testBidPrjWksorc.getWorker11()).isEqualByComparingTo(UPDATED_WORKER_11);
        assertThat(testBidPrjWksorc.getWorker12()).isEqualByComparingTo(DEFAULT_WORKER_12);
        assertThat(testBidPrjWksorc.getPersonA01()).isEqualByComparingTo(DEFAULT_PERSON_A_01);
        assertThat(testBidPrjWksorc.getPersonA02()).isEqualByComparingTo(DEFAULT_PERSON_A_02);
        assertThat(testBidPrjWksorc.getWorkerA01()).isEqualByComparingTo(UPDATED_WORKER_A_01);
        assertThat(testBidPrjWksorc.getWorkerA02()).isEqualByComparingTo(DEFAULT_WORKER_A_02);
        assertThat(testBidPrjWksorc.getWorkerA03()).isEqualByComparingTo(DEFAULT_WORKER_A_03);
        assertThat(testBidPrjWksorc.getWorkerA04()).isEqualByComparingTo(DEFAULT_WORKER_A_04);
        assertThat(testBidPrjWksorc.getWorkerA05()).isEqualByComparingTo(UPDATED_WORKER_A_05);
        assertThat(testBidPrjWksorc.getWorkerA06()).isEqualByComparingTo(DEFAULT_WORKER_A_06);
        assertThat(testBidPrjWksorc.getWorkerA07()).isEqualByComparingTo(DEFAULT_WORKER_A_07);
        assertThat(testBidPrjWksorc.getWorkerA08()).isEqualByComparingTo(UPDATED_WORKER_A_08);
        assertThat(testBidPrjWksorc.getWorkerA09()).isEqualByComparingTo(DEFAULT_WORKER_A_09);
        assertThat(testBidPrjWksorc.getWorkerA10()).isEqualByComparingTo(DEFAULT_WORKER_A_10);
        assertThat(testBidPrjWksorc.getWorkerA11()).isEqualByComparingTo(DEFAULT_WORKER_A_11);
        assertThat(testBidPrjWksorc.getWorkerA12()).isEqualByComparingTo(DEFAULT_WORKER_A_12);
        assertThat(testBidPrjWksorc.getPersonB01()).isEqualByComparingTo(UPDATED_PERSON_B_01);
        assertThat(testBidPrjWksorc.getPersonB02()).isEqualByComparingTo(DEFAULT_PERSON_B_02);
        assertThat(testBidPrjWksorc.getWorkerB01()).isEqualByComparingTo(DEFAULT_WORKER_B_01);
        assertThat(testBidPrjWksorc.getWorkerB02()).isEqualByComparingTo(DEFAULT_WORKER_B_02);
        assertThat(testBidPrjWksorc.getWorkerB03()).isEqualByComparingTo(DEFAULT_WORKER_B_03);
        assertThat(testBidPrjWksorc.getWorkerB04()).isEqualByComparingTo(DEFAULT_WORKER_B_04);
        assertThat(testBidPrjWksorc.getWorkerB05()).isEqualByComparingTo(UPDATED_WORKER_B_05);
        assertThat(testBidPrjWksorc.getWorkerB06()).isEqualByComparingTo(DEFAULT_WORKER_B_06);
        assertThat(testBidPrjWksorc.getWorkerB07()).isEqualByComparingTo(UPDATED_WORKER_B_07);
        assertThat(testBidPrjWksorc.getWorkerB08()).isEqualByComparingTo(UPDATED_WORKER_B_08);
        assertThat(testBidPrjWksorc.getWorkerB09()).isEqualByComparingTo(UPDATED_WORKER_B_09);
        assertThat(testBidPrjWksorc.getWorkerB10()).isEqualByComparingTo(UPDATED_WORKER_B_10);
        assertThat(testBidPrjWksorc.getWorkerB11()).isEqualByComparingTo(UPDATED_WORKER_B_11);
        assertThat(testBidPrjWksorc.getWorkerB12()).isEqualByComparingTo(DEFAULT_WORKER_B_12);
        assertThat(testBidPrjWksorc.getPersonC01()).isEqualByComparingTo(UPDATED_PERSON_C_01);
        assertThat(testBidPrjWksorc.getPersonC02()).isEqualByComparingTo(UPDATED_PERSON_C_02);
        assertThat(testBidPrjWksorc.getWorkerC01()).isEqualByComparingTo(UPDATED_WORKER_C_01);
        assertThat(testBidPrjWksorc.getWorkerC02()).isEqualByComparingTo(UPDATED_WORKER_C_02);
        assertThat(testBidPrjWksorc.getWorkerC03()).isEqualByComparingTo(UPDATED_WORKER_C_03);
        assertThat(testBidPrjWksorc.getWorkerC04()).isEqualByComparingTo(UPDATED_WORKER_C_04);
        assertThat(testBidPrjWksorc.getWorkerC05()).isEqualByComparingTo(UPDATED_WORKER_C_05);
        assertThat(testBidPrjWksorc.getWorkerC06()).isEqualByComparingTo(UPDATED_WORKER_C_06);
        assertThat(testBidPrjWksorc.getWorkerC07()).isEqualByComparingTo(UPDATED_WORKER_C_07);
        assertThat(testBidPrjWksorc.getWorkerC08()).isEqualByComparingTo(DEFAULT_WORKER_C_08);
        assertThat(testBidPrjWksorc.getWorkerC09()).isEqualByComparingTo(UPDATED_WORKER_C_09);
        assertThat(testBidPrjWksorc.getWorkerC10()).isEqualByComparingTo(DEFAULT_WORKER_C_10);
        assertThat(testBidPrjWksorc.getWorkerC11()).isEqualByComparingTo(DEFAULT_WORKER_C_11);
        assertThat(testBidPrjWksorc.getWorkerC12()).isEqualByComparingTo(DEFAULT_WORKER_C_12);
        assertThat(testBidPrjWksorc.getPerson01Salary()).isEqualTo(DEFAULT_PERSON_01_SALARY);
        assertThat(testBidPrjWksorc.getPerson02Salary()).isEqualTo(DEFAULT_PERSON_02_SALARY);
        assertThat(testBidPrjWksorc.getWorker01Salary()).isEqualTo(DEFAULT_WORKER_01_SALARY);
        assertThat(testBidPrjWksorc.getWorker02Salary()).isEqualTo(UPDATED_WORKER_02_SALARY);
        assertThat(testBidPrjWksorc.getWorker03Salary()).isEqualTo(DEFAULT_WORKER_03_SALARY);
        assertThat(testBidPrjWksorc.getWorker04Salary()).isEqualTo(DEFAULT_WORKER_04_SALARY);
        assertThat(testBidPrjWksorc.getWorker05Salary()).isEqualTo(UPDATED_WORKER_05_SALARY);
        assertThat(testBidPrjWksorc.getWorker06Salary()).isEqualTo(DEFAULT_WORKER_06_SALARY);
        assertThat(testBidPrjWksorc.getWorker07Salary()).isEqualTo(DEFAULT_WORKER_07_SALARY);
        assertThat(testBidPrjWksorc.getWorker08Salary()).isEqualTo(DEFAULT_WORKER_08_SALARY);
        assertThat(testBidPrjWksorc.getWorker09Salary()).isEqualTo(UPDATED_WORKER_09_SALARY);
        assertThat(testBidPrjWksorc.getWorker10Salary()).isEqualTo(UPDATED_WORKER_10_SALARY);
        assertThat(testBidPrjWksorc.getWorker11Salary()).isEqualTo(DEFAULT_WORKER_11_SALARY);
        assertThat(testBidPrjWksorc.getWorker12Salary()).isEqualTo(DEFAULT_WORKER_12_SALARY);
        assertThat(testBidPrjWksorc.getAperson01()).isEqualByComparingTo(UPDATED_APERSON_01);
        assertThat(testBidPrjWksorc.getAperson02()).isEqualByComparingTo(UPDATED_APERSON_02);
        assertThat(testBidPrjWksorc.getAworker01()).isEqualByComparingTo(DEFAULT_AWORKER_01);
        assertThat(testBidPrjWksorc.getAworker02()).isEqualByComparingTo(DEFAULT_AWORKER_02);
        assertThat(testBidPrjWksorc.getAworker03()).isEqualByComparingTo(UPDATED_AWORKER_03);
        assertThat(testBidPrjWksorc.getAworker04()).isEqualByComparingTo(DEFAULT_AWORKER_04);
        assertThat(testBidPrjWksorc.getAworker05()).isEqualByComparingTo(UPDATED_AWORKER_05);
        assertThat(testBidPrjWksorc.getAworker06()).isEqualByComparingTo(DEFAULT_AWORKER_06);
        assertThat(testBidPrjWksorc.getAworker07()).isEqualByComparingTo(UPDATED_AWORKER_07);
        assertThat(testBidPrjWksorc.getAworker08()).isEqualByComparingTo(DEFAULT_AWORKER_08);
        assertThat(testBidPrjWksorc.getAworker09()).isEqualByComparingTo(UPDATED_AWORKER_09);
        assertThat(testBidPrjWksorc.getAworker10()).isEqualByComparingTo(UPDATED_AWORKER_10);
        assertThat(testBidPrjWksorc.getAworker11()).isEqualByComparingTo(DEFAULT_AWORKER_11);
        assertThat(testBidPrjWksorc.getAworker12()).isEqualByComparingTo(DEFAULT_AWORKER_12);
        assertThat(testBidPrjWksorc.getAjobtype01()).isEqualByComparingTo(UPDATED_AJOBTYPE_01);
        assertThat(testBidPrjWksorc.getAjobtype02()).isEqualByComparingTo(DEFAULT_AJOBTYPE_02);
        assertThat(testBidPrjWksorc.getAjobtype03()).isEqualByComparingTo(UPDATED_AJOBTYPE_03);
        assertThat(testBidPrjWksorc.getAjobtype04()).isEqualByComparingTo(UPDATED_AJOBTYPE_04);
        assertThat(testBidPrjWksorc.getAjobtype05()).isEqualByComparingTo(UPDATED_AJOBTYPE_05);
        assertThat(testBidPrjWksorc.getAjobtype06()).isEqualByComparingTo(UPDATED_AJOBTYPE_06);
        assertThat(testBidPrjWksorc.getAjobtype07()).isEqualByComparingTo(DEFAULT_AJOBTYPE_07);
        assertThat(testBidPrjWksorc.getAjobtype08()).isEqualByComparingTo(UPDATED_AJOBTYPE_08);
        assertThat(testBidPrjWksorc.getAjobtype09()).isEqualByComparingTo(UPDATED_AJOBTYPE_09);
        assertThat(testBidPrjWksorc.getAjobtype10()).isEqualByComparingTo(DEFAULT_AJOBTYPE_10);
        assertThat(testBidPrjWksorc.getAjobtype11()).isEqualByComparingTo(DEFAULT_AJOBTYPE_11);
        assertThat(testBidPrjWksorc.getAjobtype12()).isEqualByComparingTo(UPDATED_AJOBTYPE_12);
        assertThat(testBidPrjWksorc.getAjobtype13()).isEqualByComparingTo(UPDATED_AJOBTYPE_13);
        assertThat(testBidPrjWksorc.getAjobtype14()).isEqualByComparingTo(DEFAULT_AJOBTYPE_14);
        assertThat(testBidPrjWksorc.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjWksorc.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjWksorc.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjWksorc.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjWksorc.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjWksorc.getConfIn()).isEqualByComparingTo(UPDATED_CONF_IN);
        assertThat(testBidPrjWksorc.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjWksorc.getWorker13()).isEqualByComparingTo(DEFAULT_WORKER_13);
        assertThat(testBidPrjWksorc.getWorker14()).isEqualByComparingTo(UPDATED_WORKER_14);
        assertThat(testBidPrjWksorc.getWorker15()).isEqualByComparingTo(UPDATED_WORKER_15);
        assertThat(testBidPrjWksorc.getWorker16()).isEqualByComparingTo(DEFAULT_WORKER_16);
        assertThat(testBidPrjWksorc.getWorkerA13()).isEqualByComparingTo(DEFAULT_WORKER_A_13);
        assertThat(testBidPrjWksorc.getWorkerA14()).isEqualByComparingTo(UPDATED_WORKER_A_14);
        assertThat(testBidPrjWksorc.getWorkerA15()).isEqualByComparingTo(UPDATED_WORKER_A_15);
        assertThat(testBidPrjWksorc.getWorkerA16()).isEqualByComparingTo(UPDATED_WORKER_A_16);
        assertThat(testBidPrjWksorc.getWorkerB13()).isEqualByComparingTo(DEFAULT_WORKER_B_13);
        assertThat(testBidPrjWksorc.getWorkerB14()).isEqualByComparingTo(DEFAULT_WORKER_B_14);
        assertThat(testBidPrjWksorc.getWorkerB15()).isEqualByComparingTo(DEFAULT_WORKER_B_15);
        assertThat(testBidPrjWksorc.getWorkerB16()).isEqualByComparingTo(DEFAULT_WORKER_B_16);
        assertThat(testBidPrjWksorc.getWorkerC13()).isEqualByComparingTo(UPDATED_WORKER_C_13);
        assertThat(testBidPrjWksorc.getWorkerC14()).isEqualByComparingTo(DEFAULT_WORKER_C_14);
        assertThat(testBidPrjWksorc.getWorkerC15()).isEqualByComparingTo(UPDATED_WORKER_C_15);
        assertThat(testBidPrjWksorc.getWorkerC16()).isEqualByComparingTo(UPDATED_WORKER_C_16);
        assertThat(testBidPrjWksorc.getAworker13()).isEqualByComparingTo(UPDATED_AWORKER_13);
        assertThat(testBidPrjWksorc.getAworker14()).isEqualByComparingTo(UPDATED_AWORKER_14);
        assertThat(testBidPrjWksorc.getAworker15()).isEqualByComparingTo(UPDATED_AWORKER_15);
        assertThat(testBidPrjWksorc.getAworker16()).isEqualByComparingTo(DEFAULT_AWORKER_16);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjWksorcWithPatch() throws Exception {
        // Initialize the database
        bidPrjWksorcRepository.saveAndFlush(bidPrjWksorc);

        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();

        // Update the bidPrjWksorc using partial update
        BidPrjWksorc partialUpdatedBidPrjWksorc = new BidPrjWksorc();
        partialUpdatedBidPrjWksorc.setId(bidPrjWksorc.getId());

        partialUpdatedBidPrjWksorc
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .syr(UPDATED_SYR)
            .month(UPDATED_MONTH)
            .person01(UPDATED_PERSON_01)
            .person02(UPDATED_PERSON_02)
            .worker01(UPDATED_WORKER_01)
            .worker02(UPDATED_WORKER_02)
            .worker03(UPDATED_WORKER_03)
            .worker04(UPDATED_WORKER_04)
            .worker05(UPDATED_WORKER_05)
            .worker06(UPDATED_WORKER_06)
            .worker07(UPDATED_WORKER_07)
            .worker08(UPDATED_WORKER_08)
            .worker09(UPDATED_WORKER_09)
            .worker10(UPDATED_WORKER_10)
            .worker11(UPDATED_WORKER_11)
            .worker12(UPDATED_WORKER_12)
            .personA01(UPDATED_PERSON_A_01)
            .personA02(UPDATED_PERSON_A_02)
            .workerA01(UPDATED_WORKER_A_01)
            .workerA02(UPDATED_WORKER_A_02)
            .workerA03(UPDATED_WORKER_A_03)
            .workerA04(UPDATED_WORKER_A_04)
            .workerA05(UPDATED_WORKER_A_05)
            .workerA06(UPDATED_WORKER_A_06)
            .workerA07(UPDATED_WORKER_A_07)
            .workerA08(UPDATED_WORKER_A_08)
            .workerA09(UPDATED_WORKER_A_09)
            .workerA10(UPDATED_WORKER_A_10)
            .workerA11(UPDATED_WORKER_A_11)
            .workerA12(UPDATED_WORKER_A_12)
            .personB01(UPDATED_PERSON_B_01)
            .personB02(UPDATED_PERSON_B_02)
            .workerB01(UPDATED_WORKER_B_01)
            .workerB02(UPDATED_WORKER_B_02)
            .workerB03(UPDATED_WORKER_B_03)
            .workerB04(UPDATED_WORKER_B_04)
            .workerB05(UPDATED_WORKER_B_05)
            .workerB06(UPDATED_WORKER_B_06)
            .workerB07(UPDATED_WORKER_B_07)
            .workerB08(UPDATED_WORKER_B_08)
            .workerB09(UPDATED_WORKER_B_09)
            .workerB10(UPDATED_WORKER_B_10)
            .workerB11(UPDATED_WORKER_B_11)
            .workerB12(UPDATED_WORKER_B_12)
            .personC01(UPDATED_PERSON_C_01)
            .personC02(UPDATED_PERSON_C_02)
            .workerC01(UPDATED_WORKER_C_01)
            .workerC02(UPDATED_WORKER_C_02)
            .workerC03(UPDATED_WORKER_C_03)
            .workerC04(UPDATED_WORKER_C_04)
            .workerC05(UPDATED_WORKER_C_05)
            .workerC06(UPDATED_WORKER_C_06)
            .workerC07(UPDATED_WORKER_C_07)
            .workerC08(UPDATED_WORKER_C_08)
            .workerC09(UPDATED_WORKER_C_09)
            .workerC10(UPDATED_WORKER_C_10)
            .workerC11(UPDATED_WORKER_C_11)
            .workerC12(UPDATED_WORKER_C_12)
            .person01Salary(UPDATED_PERSON_01_SALARY)
            .person02Salary(UPDATED_PERSON_02_SALARY)
            .worker01Salary(UPDATED_WORKER_01_SALARY)
            .worker02Salary(UPDATED_WORKER_02_SALARY)
            .worker03Salary(UPDATED_WORKER_03_SALARY)
            .worker04Salary(UPDATED_WORKER_04_SALARY)
            .worker05Salary(UPDATED_WORKER_05_SALARY)
            .worker06Salary(UPDATED_WORKER_06_SALARY)
            .worker07Salary(UPDATED_WORKER_07_SALARY)
            .worker08Salary(UPDATED_WORKER_08_SALARY)
            .worker09Salary(UPDATED_WORKER_09_SALARY)
            .worker10Salary(UPDATED_WORKER_10_SALARY)
            .worker11Salary(UPDATED_WORKER_11_SALARY)
            .worker12Salary(UPDATED_WORKER_12_SALARY)
            .aperson01(UPDATED_APERSON_01)
            .aperson02(UPDATED_APERSON_02)
            .aworker01(UPDATED_AWORKER_01)
            .aworker02(UPDATED_AWORKER_02)
            .aworker03(UPDATED_AWORKER_03)
            .aworker04(UPDATED_AWORKER_04)
            .aworker05(UPDATED_AWORKER_05)
            .aworker06(UPDATED_AWORKER_06)
            .aworker07(UPDATED_AWORKER_07)
            .aworker08(UPDATED_AWORKER_08)
            .aworker09(UPDATED_AWORKER_09)
            .aworker10(UPDATED_AWORKER_10)
            .aworker11(UPDATED_AWORKER_11)
            .aworker12(UPDATED_AWORKER_12)
            .ajobtype01(UPDATED_AJOBTYPE_01)
            .ajobtype02(UPDATED_AJOBTYPE_02)
            .ajobtype03(UPDATED_AJOBTYPE_03)
            .ajobtype04(UPDATED_AJOBTYPE_04)
            .ajobtype05(UPDATED_AJOBTYPE_05)
            .ajobtype06(UPDATED_AJOBTYPE_06)
            .ajobtype07(UPDATED_AJOBTYPE_07)
            .ajobtype08(UPDATED_AJOBTYPE_08)
            .ajobtype09(UPDATED_AJOBTYPE_09)
            .ajobtype10(UPDATED_AJOBTYPE_10)
            .ajobtype11(UPDATED_AJOBTYPE_11)
            .ajobtype12(UPDATED_AJOBTYPE_12)
            .ajobtype13(UPDATED_AJOBTYPE_13)
            .ajobtype14(UPDATED_AJOBTYPE_14)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .confOut(UPDATED_CONF_OUT)
            .confIn(UPDATED_CONF_IN)
            .uIp(UPDATED_U_IP)
            .worker13(UPDATED_WORKER_13)
            .worker14(UPDATED_WORKER_14)
            .worker15(UPDATED_WORKER_15)
            .worker16(UPDATED_WORKER_16)
            .workerA13(UPDATED_WORKER_A_13)
            .workerA14(UPDATED_WORKER_A_14)
            .workerA15(UPDATED_WORKER_A_15)
            .workerA16(UPDATED_WORKER_A_16)
            .workerB13(UPDATED_WORKER_B_13)
            .workerB14(UPDATED_WORKER_B_14)
            .workerB15(UPDATED_WORKER_B_15)
            .workerB16(UPDATED_WORKER_B_16)
            .workerC13(UPDATED_WORKER_C_13)
            .workerC14(UPDATED_WORKER_C_14)
            .workerC15(UPDATED_WORKER_C_15)
            .workerC16(UPDATED_WORKER_C_16)
            .aworker13(UPDATED_AWORKER_13)
            .aworker14(UPDATED_AWORKER_14)
            .aworker15(UPDATED_AWORKER_15)
            .aworker16(UPDATED_AWORKER_16);

        restBidPrjWksorcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjWksorc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjWksorc))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjWksorc in the database
        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjWksorc testBidPrjWksorc = bidPrjWksorcList.get(bidPrjWksorcList.size() - 1);
        assertThat(testBidPrjWksorc.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjWksorc.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjWksorc.getSyr()).isEqualTo(UPDATED_SYR);
        assertThat(testBidPrjWksorc.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjWksorc.getPerson01()).isEqualByComparingTo(UPDATED_PERSON_01);
        assertThat(testBidPrjWksorc.getPerson02()).isEqualByComparingTo(UPDATED_PERSON_02);
        assertThat(testBidPrjWksorc.getWorker01()).isEqualByComparingTo(UPDATED_WORKER_01);
        assertThat(testBidPrjWksorc.getWorker02()).isEqualByComparingTo(UPDATED_WORKER_02);
        assertThat(testBidPrjWksorc.getWorker03()).isEqualByComparingTo(UPDATED_WORKER_03);
        assertThat(testBidPrjWksorc.getWorker04()).isEqualByComparingTo(UPDATED_WORKER_04);
        assertThat(testBidPrjWksorc.getWorker05()).isEqualByComparingTo(UPDATED_WORKER_05);
        assertThat(testBidPrjWksorc.getWorker06()).isEqualByComparingTo(UPDATED_WORKER_06);
        assertThat(testBidPrjWksorc.getWorker07()).isEqualByComparingTo(UPDATED_WORKER_07);
        assertThat(testBidPrjWksorc.getWorker08()).isEqualByComparingTo(UPDATED_WORKER_08);
        assertThat(testBidPrjWksorc.getWorker09()).isEqualByComparingTo(UPDATED_WORKER_09);
        assertThat(testBidPrjWksorc.getWorker10()).isEqualByComparingTo(UPDATED_WORKER_10);
        assertThat(testBidPrjWksorc.getWorker11()).isEqualByComparingTo(UPDATED_WORKER_11);
        assertThat(testBidPrjWksorc.getWorker12()).isEqualByComparingTo(UPDATED_WORKER_12);
        assertThat(testBidPrjWksorc.getPersonA01()).isEqualByComparingTo(UPDATED_PERSON_A_01);
        assertThat(testBidPrjWksorc.getPersonA02()).isEqualByComparingTo(UPDATED_PERSON_A_02);
        assertThat(testBidPrjWksorc.getWorkerA01()).isEqualByComparingTo(UPDATED_WORKER_A_01);
        assertThat(testBidPrjWksorc.getWorkerA02()).isEqualByComparingTo(UPDATED_WORKER_A_02);
        assertThat(testBidPrjWksorc.getWorkerA03()).isEqualByComparingTo(UPDATED_WORKER_A_03);
        assertThat(testBidPrjWksorc.getWorkerA04()).isEqualByComparingTo(UPDATED_WORKER_A_04);
        assertThat(testBidPrjWksorc.getWorkerA05()).isEqualByComparingTo(UPDATED_WORKER_A_05);
        assertThat(testBidPrjWksorc.getWorkerA06()).isEqualByComparingTo(UPDATED_WORKER_A_06);
        assertThat(testBidPrjWksorc.getWorkerA07()).isEqualByComparingTo(UPDATED_WORKER_A_07);
        assertThat(testBidPrjWksorc.getWorkerA08()).isEqualByComparingTo(UPDATED_WORKER_A_08);
        assertThat(testBidPrjWksorc.getWorkerA09()).isEqualByComparingTo(UPDATED_WORKER_A_09);
        assertThat(testBidPrjWksorc.getWorkerA10()).isEqualByComparingTo(UPDATED_WORKER_A_10);
        assertThat(testBidPrjWksorc.getWorkerA11()).isEqualByComparingTo(UPDATED_WORKER_A_11);
        assertThat(testBidPrjWksorc.getWorkerA12()).isEqualByComparingTo(UPDATED_WORKER_A_12);
        assertThat(testBidPrjWksorc.getPersonB01()).isEqualByComparingTo(UPDATED_PERSON_B_01);
        assertThat(testBidPrjWksorc.getPersonB02()).isEqualByComparingTo(UPDATED_PERSON_B_02);
        assertThat(testBidPrjWksorc.getWorkerB01()).isEqualByComparingTo(UPDATED_WORKER_B_01);
        assertThat(testBidPrjWksorc.getWorkerB02()).isEqualByComparingTo(UPDATED_WORKER_B_02);
        assertThat(testBidPrjWksorc.getWorkerB03()).isEqualByComparingTo(UPDATED_WORKER_B_03);
        assertThat(testBidPrjWksorc.getWorkerB04()).isEqualByComparingTo(UPDATED_WORKER_B_04);
        assertThat(testBidPrjWksorc.getWorkerB05()).isEqualByComparingTo(UPDATED_WORKER_B_05);
        assertThat(testBidPrjWksorc.getWorkerB06()).isEqualByComparingTo(UPDATED_WORKER_B_06);
        assertThat(testBidPrjWksorc.getWorkerB07()).isEqualByComparingTo(UPDATED_WORKER_B_07);
        assertThat(testBidPrjWksorc.getWorkerB08()).isEqualByComparingTo(UPDATED_WORKER_B_08);
        assertThat(testBidPrjWksorc.getWorkerB09()).isEqualByComparingTo(UPDATED_WORKER_B_09);
        assertThat(testBidPrjWksorc.getWorkerB10()).isEqualByComparingTo(UPDATED_WORKER_B_10);
        assertThat(testBidPrjWksorc.getWorkerB11()).isEqualByComparingTo(UPDATED_WORKER_B_11);
        assertThat(testBidPrjWksorc.getWorkerB12()).isEqualByComparingTo(UPDATED_WORKER_B_12);
        assertThat(testBidPrjWksorc.getPersonC01()).isEqualByComparingTo(UPDATED_PERSON_C_01);
        assertThat(testBidPrjWksorc.getPersonC02()).isEqualByComparingTo(UPDATED_PERSON_C_02);
        assertThat(testBidPrjWksorc.getWorkerC01()).isEqualByComparingTo(UPDATED_WORKER_C_01);
        assertThat(testBidPrjWksorc.getWorkerC02()).isEqualByComparingTo(UPDATED_WORKER_C_02);
        assertThat(testBidPrjWksorc.getWorkerC03()).isEqualByComparingTo(UPDATED_WORKER_C_03);
        assertThat(testBidPrjWksorc.getWorkerC04()).isEqualByComparingTo(UPDATED_WORKER_C_04);
        assertThat(testBidPrjWksorc.getWorkerC05()).isEqualByComparingTo(UPDATED_WORKER_C_05);
        assertThat(testBidPrjWksorc.getWorkerC06()).isEqualByComparingTo(UPDATED_WORKER_C_06);
        assertThat(testBidPrjWksorc.getWorkerC07()).isEqualByComparingTo(UPDATED_WORKER_C_07);
        assertThat(testBidPrjWksorc.getWorkerC08()).isEqualByComparingTo(UPDATED_WORKER_C_08);
        assertThat(testBidPrjWksorc.getWorkerC09()).isEqualByComparingTo(UPDATED_WORKER_C_09);
        assertThat(testBidPrjWksorc.getWorkerC10()).isEqualByComparingTo(UPDATED_WORKER_C_10);
        assertThat(testBidPrjWksorc.getWorkerC11()).isEqualByComparingTo(UPDATED_WORKER_C_11);
        assertThat(testBidPrjWksorc.getWorkerC12()).isEqualByComparingTo(UPDATED_WORKER_C_12);
        assertThat(testBidPrjWksorc.getPerson01Salary()).isEqualTo(UPDATED_PERSON_01_SALARY);
        assertThat(testBidPrjWksorc.getPerson02Salary()).isEqualTo(UPDATED_PERSON_02_SALARY);
        assertThat(testBidPrjWksorc.getWorker01Salary()).isEqualTo(UPDATED_WORKER_01_SALARY);
        assertThat(testBidPrjWksorc.getWorker02Salary()).isEqualTo(UPDATED_WORKER_02_SALARY);
        assertThat(testBidPrjWksorc.getWorker03Salary()).isEqualTo(UPDATED_WORKER_03_SALARY);
        assertThat(testBidPrjWksorc.getWorker04Salary()).isEqualTo(UPDATED_WORKER_04_SALARY);
        assertThat(testBidPrjWksorc.getWorker05Salary()).isEqualTo(UPDATED_WORKER_05_SALARY);
        assertThat(testBidPrjWksorc.getWorker06Salary()).isEqualTo(UPDATED_WORKER_06_SALARY);
        assertThat(testBidPrjWksorc.getWorker07Salary()).isEqualTo(UPDATED_WORKER_07_SALARY);
        assertThat(testBidPrjWksorc.getWorker08Salary()).isEqualTo(UPDATED_WORKER_08_SALARY);
        assertThat(testBidPrjWksorc.getWorker09Salary()).isEqualTo(UPDATED_WORKER_09_SALARY);
        assertThat(testBidPrjWksorc.getWorker10Salary()).isEqualTo(UPDATED_WORKER_10_SALARY);
        assertThat(testBidPrjWksorc.getWorker11Salary()).isEqualTo(UPDATED_WORKER_11_SALARY);
        assertThat(testBidPrjWksorc.getWorker12Salary()).isEqualTo(UPDATED_WORKER_12_SALARY);
        assertThat(testBidPrjWksorc.getAperson01()).isEqualByComparingTo(UPDATED_APERSON_01);
        assertThat(testBidPrjWksorc.getAperson02()).isEqualByComparingTo(UPDATED_APERSON_02);
        assertThat(testBidPrjWksorc.getAworker01()).isEqualByComparingTo(UPDATED_AWORKER_01);
        assertThat(testBidPrjWksorc.getAworker02()).isEqualByComparingTo(UPDATED_AWORKER_02);
        assertThat(testBidPrjWksorc.getAworker03()).isEqualByComparingTo(UPDATED_AWORKER_03);
        assertThat(testBidPrjWksorc.getAworker04()).isEqualByComparingTo(UPDATED_AWORKER_04);
        assertThat(testBidPrjWksorc.getAworker05()).isEqualByComparingTo(UPDATED_AWORKER_05);
        assertThat(testBidPrjWksorc.getAworker06()).isEqualByComparingTo(UPDATED_AWORKER_06);
        assertThat(testBidPrjWksorc.getAworker07()).isEqualByComparingTo(UPDATED_AWORKER_07);
        assertThat(testBidPrjWksorc.getAworker08()).isEqualByComparingTo(UPDATED_AWORKER_08);
        assertThat(testBidPrjWksorc.getAworker09()).isEqualByComparingTo(UPDATED_AWORKER_09);
        assertThat(testBidPrjWksorc.getAworker10()).isEqualByComparingTo(UPDATED_AWORKER_10);
        assertThat(testBidPrjWksorc.getAworker11()).isEqualByComparingTo(UPDATED_AWORKER_11);
        assertThat(testBidPrjWksorc.getAworker12()).isEqualByComparingTo(UPDATED_AWORKER_12);
        assertThat(testBidPrjWksorc.getAjobtype01()).isEqualByComparingTo(UPDATED_AJOBTYPE_01);
        assertThat(testBidPrjWksorc.getAjobtype02()).isEqualByComparingTo(UPDATED_AJOBTYPE_02);
        assertThat(testBidPrjWksorc.getAjobtype03()).isEqualByComparingTo(UPDATED_AJOBTYPE_03);
        assertThat(testBidPrjWksorc.getAjobtype04()).isEqualByComparingTo(UPDATED_AJOBTYPE_04);
        assertThat(testBidPrjWksorc.getAjobtype05()).isEqualByComparingTo(UPDATED_AJOBTYPE_05);
        assertThat(testBidPrjWksorc.getAjobtype06()).isEqualByComparingTo(UPDATED_AJOBTYPE_06);
        assertThat(testBidPrjWksorc.getAjobtype07()).isEqualByComparingTo(UPDATED_AJOBTYPE_07);
        assertThat(testBidPrjWksorc.getAjobtype08()).isEqualByComparingTo(UPDATED_AJOBTYPE_08);
        assertThat(testBidPrjWksorc.getAjobtype09()).isEqualByComparingTo(UPDATED_AJOBTYPE_09);
        assertThat(testBidPrjWksorc.getAjobtype10()).isEqualByComparingTo(UPDATED_AJOBTYPE_10);
        assertThat(testBidPrjWksorc.getAjobtype11()).isEqualByComparingTo(UPDATED_AJOBTYPE_11);
        assertThat(testBidPrjWksorc.getAjobtype12()).isEqualByComparingTo(UPDATED_AJOBTYPE_12);
        assertThat(testBidPrjWksorc.getAjobtype13()).isEqualByComparingTo(UPDATED_AJOBTYPE_13);
        assertThat(testBidPrjWksorc.getAjobtype14()).isEqualByComparingTo(UPDATED_AJOBTYPE_14);
        assertThat(testBidPrjWksorc.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjWksorc.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjWksorc.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjWksorc.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjWksorc.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjWksorc.getConfIn()).isEqualByComparingTo(UPDATED_CONF_IN);
        assertThat(testBidPrjWksorc.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjWksorc.getWorker13()).isEqualByComparingTo(UPDATED_WORKER_13);
        assertThat(testBidPrjWksorc.getWorker14()).isEqualByComparingTo(UPDATED_WORKER_14);
        assertThat(testBidPrjWksorc.getWorker15()).isEqualByComparingTo(UPDATED_WORKER_15);
        assertThat(testBidPrjWksorc.getWorker16()).isEqualByComparingTo(UPDATED_WORKER_16);
        assertThat(testBidPrjWksorc.getWorkerA13()).isEqualByComparingTo(UPDATED_WORKER_A_13);
        assertThat(testBidPrjWksorc.getWorkerA14()).isEqualByComparingTo(UPDATED_WORKER_A_14);
        assertThat(testBidPrjWksorc.getWorkerA15()).isEqualByComparingTo(UPDATED_WORKER_A_15);
        assertThat(testBidPrjWksorc.getWorkerA16()).isEqualByComparingTo(UPDATED_WORKER_A_16);
        assertThat(testBidPrjWksorc.getWorkerB13()).isEqualByComparingTo(UPDATED_WORKER_B_13);
        assertThat(testBidPrjWksorc.getWorkerB14()).isEqualByComparingTo(UPDATED_WORKER_B_14);
        assertThat(testBidPrjWksorc.getWorkerB15()).isEqualByComparingTo(UPDATED_WORKER_B_15);
        assertThat(testBidPrjWksorc.getWorkerB16()).isEqualByComparingTo(UPDATED_WORKER_B_16);
        assertThat(testBidPrjWksorc.getWorkerC13()).isEqualByComparingTo(UPDATED_WORKER_C_13);
        assertThat(testBidPrjWksorc.getWorkerC14()).isEqualByComparingTo(UPDATED_WORKER_C_14);
        assertThat(testBidPrjWksorc.getWorkerC15()).isEqualByComparingTo(UPDATED_WORKER_C_15);
        assertThat(testBidPrjWksorc.getWorkerC16()).isEqualByComparingTo(UPDATED_WORKER_C_16);
        assertThat(testBidPrjWksorc.getAworker13()).isEqualByComparingTo(UPDATED_AWORKER_13);
        assertThat(testBidPrjWksorc.getAworker14()).isEqualByComparingTo(UPDATED_AWORKER_14);
        assertThat(testBidPrjWksorc.getAworker15()).isEqualByComparingTo(UPDATED_AWORKER_15);
        assertThat(testBidPrjWksorc.getAworker16()).isEqualByComparingTo(UPDATED_AWORKER_16);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjWksorc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();
//        bidPrjWksorc.setId(count.incrementAndGet());
//
//        // Create the BidPrjWksorc
//        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjWksorcMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjWksorcDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjWksorc in the database
//        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
//        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjWksorc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();
//        bidPrjWksorc.setId(count.incrementAndGet());
//
//        // Create the BidPrjWksorc
//        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjWksorcMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjWksorc in the database
//        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
//        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjWksorc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjWksorcRepository.findAll().size();
//        bidPrjWksorc.setId(count.incrementAndGet());
//
//        // Create the BidPrjWksorc
//        BidPrjWksorcDTO bidPrjWksorcDTO = bidPrjWksorcMapper.toDto(bidPrjWksorc);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjWksorcMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjWksorcDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjWksorc in the database
//        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
//        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjWksorc() throws Exception {
        // Initialize the database
        bidPrjWksorcRepository.saveAndFlush(bidPrjWksorc);

        int databaseSizeBeforeDelete = bidPrjWksorcRepository.findAll().size();

        // Delete the bidPrjWksorc
        restBidPrjWksorcMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjWksorc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjWksorc> bidPrjWksorcList = bidPrjWksorcRepository.findAll();
        assertThat(bidPrjWksorcList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
