package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjStc;
import gov.pcc.pwc.repository.BidPrjStcRepository;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;
import gov.pcc.pwc.service.mapper.BidPrjStcMapper;
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
 * Integration tests for the {@link BidPrjStcResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjStcResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHECK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECK_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ADM_FILE_ID = 1L;
    private static final Long UPDATED_ADM_FILE_ID = 2L;

    private static final String DEFAULT_YND_A_01 = "AAAAAAA";
    private static final String UPDATED_YND_A_01 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_01 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_01 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_01 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_01 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_02 = "AAAAAAA";
    private static final String UPDATED_YND_A_02 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_02 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_02 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_02 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_02 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_03 = "AAAAAAA";
    private static final String UPDATED_YND_A_03 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_03 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_03 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_03 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_03 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_04 = "AAAAAAA";
    private static final String UPDATED_YND_A_04 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_04 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_04 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_04 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_04 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_05 = "AAAAAAA";
    private static final String UPDATED_YND_A_05 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_05 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_05 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_05 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_05 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_06 = "AAAAAAA";
    private static final String UPDATED_YND_A_06 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_06 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_06 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_06 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_06 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_07 = "AAAAAAA";
    private static final String UPDATED_YND_A_07 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_07 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_07 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_07 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_07 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_08 = "AAAAAAA";
    private static final String UPDATED_YND_A_08 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_08 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_08 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_08 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_08 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_09 = "AAAAAAA";
    private static final String UPDATED_YND_A_09 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_09 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_09 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_09 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_09 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_10 = "AAAAAAA";
    private static final String UPDATED_YND_A_10 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_10 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_10 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_10 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_10 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_11 = "AAAAAAA";
    private static final String UPDATED_YND_A_11 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_11 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_11 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_11 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_11 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_12 = "AAAAAAA";
    private static final String UPDATED_YND_A_12 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_12 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_12 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_12 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_12 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_13 = "AAAAAAA";
    private static final String UPDATED_YND_A_13 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_13 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_13 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_13 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_13 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_14 = "AAAAAAA";
    private static final String UPDATED_YND_A_14 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_14 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_14 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_14 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_14 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_A_15 = "AAAAAAA";
    private static final String UPDATED_YND_A_15 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_15 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_15 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_15 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_15 = LocalDate.now(ZoneId.systemDefault());

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

    private static final String DEFAULT_YND_A_16 = "AAAAAAA";
    private static final String UPDATED_YND_A_16 = "BBBBBBB";

    private static final String DEFAULT_RESON_A_16 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_A_16 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_A_16 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_A_16 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_B_01 = "AAAAAAA";
    private static final String UPDATED_YND_B_01 = "BBBBBBB";

    private static final String DEFAULT_RESON_B_01 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_B_01 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_B_01 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_B_01 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_B_02 = "AAAAAAA";
    private static final String UPDATED_YND_B_02 = "BBBBBBB";

    private static final String DEFAULT_RESON_B_02 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_B_02 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_B_02 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_B_02 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_B_03 = "AAAAAAA";
    private static final String UPDATED_YND_B_03 = "BBBBBBB";

    private static final String DEFAULT_RESON_B_03 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_B_03 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_B_03 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_B_03 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_B_04 = "AAAAAAA";
    private static final String UPDATED_YND_B_04 = "BBBBBBB";

    private static final String DEFAULT_RESON_B_04 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_B_04 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_B_04 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_B_04 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_B_05 = "AAAAAAA";
    private static final String UPDATED_YND_B_05 = "BBBBBBB";

    private static final String DEFAULT_RESON_B_05 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_B_05 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_B_05 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_B_05 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_B_06 = "AAAAAAA";
    private static final String UPDATED_YND_B_06 = "BBBBBBB";

    private static final String DEFAULT_RESON_B_06 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_B_06 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_B_06 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_B_06 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_YND_B_07 = "AAAAAAA";
    private static final String UPDATED_YND_B_07 = "BBBBBBB";

    private static final String DEFAULT_RESON_B_07 = "AAAAAAAAAA";
    private static final String UPDATED_RESON_B_07 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_B_07 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_B_07 = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/bid-prj-stcs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjStcRepository bidPrjStcRepository;

    @Autowired
    private BidPrjStcMapper bidPrjStcMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjStcMockMvc;

    private BidPrjStc bidPrjStc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjStc createEntity(EntityManager em) {
        BidPrjStc bidPrjStc = new BidPrjStc()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .checkDate(DEFAULT_CHECK_DATE)
            .admFileId(DEFAULT_ADM_FILE_ID)
            .yndA01(DEFAULT_YND_A_01)
            .resonA01(DEFAULT_RESON_A_01)
            .dateA01(DEFAULT_DATE_A_01)
            .yndA02(DEFAULT_YND_A_02)
            .resonA02(DEFAULT_RESON_A_02)
            .dateA02(DEFAULT_DATE_A_02)
            .yndA03(DEFAULT_YND_A_03)
            .resonA03(DEFAULT_RESON_A_03)
            .dateA03(DEFAULT_DATE_A_03)
            .yndA04(DEFAULT_YND_A_04)
            .resonA04(DEFAULT_RESON_A_04)
            .dateA04(DEFAULT_DATE_A_04)
            .yndA05(DEFAULT_YND_A_05)
            .resonA05(DEFAULT_RESON_A_05)
            .dateA05(DEFAULT_DATE_A_05)
            .yndA06(DEFAULT_YND_A_06)
            .resonA06(DEFAULT_RESON_A_06)
            .dateA06(DEFAULT_DATE_A_06)
            .yndA07(DEFAULT_YND_A_07)
            .resonA07(DEFAULT_RESON_A_07)
            .dateA07(DEFAULT_DATE_A_07)
            .yndA08(DEFAULT_YND_A_08)
            .resonA08(DEFAULT_RESON_A_08)
            .dateA08(DEFAULT_DATE_A_08)
            .yndA09(DEFAULT_YND_A_09)
            .resonA09(DEFAULT_RESON_A_09)
            .dateA09(DEFAULT_DATE_A_09)
            .yndA10(DEFAULT_YND_A_10)
            .resonA10(DEFAULT_RESON_A_10)
            .dateA10(DEFAULT_DATE_A_10)
            .yndA11(DEFAULT_YND_A_11)
            .resonA11(DEFAULT_RESON_A_11)
            .dateA11(DEFAULT_DATE_A_11)
            .yndA12(DEFAULT_YND_A_12)
            .resonA12(DEFAULT_RESON_A_12)
            .dateA12(DEFAULT_DATE_A_12)
            .yndA13(DEFAULT_YND_A_13)
            .resonA13(DEFAULT_RESON_A_13)
            .dateA13(DEFAULT_DATE_A_13)
            .yndA14(DEFAULT_YND_A_14)
            .resonA14(DEFAULT_RESON_A_14)
            .dateA14(DEFAULT_DATE_A_14)
            .yndA15(DEFAULT_YND_A_15)
            .resonA15(DEFAULT_RESON_A_15)
            .dateA15(DEFAULT_DATE_A_15)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .dsts(DEFAULT_DSTS)
            .yndA16(DEFAULT_YND_A_16)
            .resonA16(DEFAULT_RESON_A_16)
            .dateA16(DEFAULT_DATE_A_16)
            .yndB01(DEFAULT_YND_B_01)
            .resonB01(DEFAULT_RESON_B_01)
            .dateB01(DEFAULT_DATE_B_01)
            .yndB02(DEFAULT_YND_B_02)
            .resonB02(DEFAULT_RESON_B_02)
            .dateB02(DEFAULT_DATE_B_02)
            .yndB03(DEFAULT_YND_B_03)
            .resonB03(DEFAULT_RESON_B_03)
            .dateB03(DEFAULT_DATE_B_03)
            .yndB04(DEFAULT_YND_B_04)
            .resonB04(DEFAULT_RESON_B_04)
            .dateB04(DEFAULT_DATE_B_04)
            .yndB05(DEFAULT_YND_B_05)
            .resonB05(DEFAULT_RESON_B_05)
            .dateB05(DEFAULT_DATE_B_05)
            .yndB06(DEFAULT_YND_B_06)
            .resonB06(DEFAULT_RESON_B_06)
            .dateB06(DEFAULT_DATE_B_06)
            .yndB07(DEFAULT_YND_B_07)
            .resonB07(DEFAULT_RESON_B_07)
            .dateB07(DEFAULT_DATE_B_07);
        return bidPrjStc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjStc createUpdatedEntity(EntityManager em) {
        BidPrjStc bidPrjStc = new BidPrjStc()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .checkDate(UPDATED_CHECK_DATE)
            .admFileId(UPDATED_ADM_FILE_ID)
            .yndA01(UPDATED_YND_A_01)
            .resonA01(UPDATED_RESON_A_01)
            .dateA01(UPDATED_DATE_A_01)
            .yndA02(UPDATED_YND_A_02)
            .resonA02(UPDATED_RESON_A_02)
            .dateA02(UPDATED_DATE_A_02)
            .yndA03(UPDATED_YND_A_03)
            .resonA03(UPDATED_RESON_A_03)
            .dateA03(UPDATED_DATE_A_03)
            .yndA04(UPDATED_YND_A_04)
            .resonA04(UPDATED_RESON_A_04)
            .dateA04(UPDATED_DATE_A_04)
            .yndA05(UPDATED_YND_A_05)
            .resonA05(UPDATED_RESON_A_05)
            .dateA05(UPDATED_DATE_A_05)
            .yndA06(UPDATED_YND_A_06)
            .resonA06(UPDATED_RESON_A_06)
            .dateA06(UPDATED_DATE_A_06)
            .yndA07(UPDATED_YND_A_07)
            .resonA07(UPDATED_RESON_A_07)
            .dateA07(UPDATED_DATE_A_07)
            .yndA08(UPDATED_YND_A_08)
            .resonA08(UPDATED_RESON_A_08)
            .dateA08(UPDATED_DATE_A_08)
            .yndA09(UPDATED_YND_A_09)
            .resonA09(UPDATED_RESON_A_09)
            .dateA09(UPDATED_DATE_A_09)
            .yndA10(UPDATED_YND_A_10)
            .resonA10(UPDATED_RESON_A_10)
            .dateA10(UPDATED_DATE_A_10)
            .yndA11(UPDATED_YND_A_11)
            .resonA11(UPDATED_RESON_A_11)
            .dateA11(UPDATED_DATE_A_11)
            .yndA12(UPDATED_YND_A_12)
            .resonA12(UPDATED_RESON_A_12)
            .dateA12(UPDATED_DATE_A_12)
            .yndA13(UPDATED_YND_A_13)
            .resonA13(UPDATED_RESON_A_13)
            .dateA13(UPDATED_DATE_A_13)
            .yndA14(UPDATED_YND_A_14)
            .resonA14(UPDATED_RESON_A_14)
            .dateA14(UPDATED_DATE_A_14)
            .yndA15(UPDATED_YND_A_15)
            .resonA15(UPDATED_RESON_A_15)
            .dateA15(UPDATED_DATE_A_15)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .yndA16(UPDATED_YND_A_16)
            .resonA16(UPDATED_RESON_A_16)
            .dateA16(UPDATED_DATE_A_16)
            .yndB01(UPDATED_YND_B_01)
            .resonB01(UPDATED_RESON_B_01)
            .dateB01(UPDATED_DATE_B_01)
            .yndB02(UPDATED_YND_B_02)
            .resonB02(UPDATED_RESON_B_02)
            .dateB02(UPDATED_DATE_B_02)
            .yndB03(UPDATED_YND_B_03)
            .resonB03(UPDATED_RESON_B_03)
            .dateB03(UPDATED_DATE_B_03)
            .yndB04(UPDATED_YND_B_04)
            .resonB04(UPDATED_RESON_B_04)
            .dateB04(UPDATED_DATE_B_04)
            .yndB05(UPDATED_YND_B_05)
            .resonB05(UPDATED_RESON_B_05)
            .dateB05(UPDATED_DATE_B_05)
            .yndB06(UPDATED_YND_B_06)
            .resonB06(UPDATED_RESON_B_06)
            .dateB06(UPDATED_DATE_B_06)
            .yndB07(UPDATED_YND_B_07)
            .resonB07(UPDATED_RESON_B_07)
            .dateB07(UPDATED_DATE_B_07);
        return bidPrjStc;
    }

    @BeforeEach
    public void initTest() {
        bidPrjStc = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjStc() throws Exception {
        int databaseSizeBeforeCreate = bidPrjStcRepository.findAll().size();
        // Create the BidPrjStc
        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(bidPrjStc);
        restBidPrjStcMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO)))
            .andExpect(status().isCreated());

        // Validate the BidPrjStc in the database
        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjStc testBidPrjStc = bidPrjStcList.get(bidPrjStcList.size() - 1);
        assertThat(testBidPrjStc.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjStc.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjStc.getCheckDate()).isEqualTo(DEFAULT_CHECK_DATE);
        assertThat(testBidPrjStc.getAdmFileId()).isEqualTo(DEFAULT_ADM_FILE_ID);
        assertThat(testBidPrjStc.getYndA01()).isEqualTo(DEFAULT_YND_A_01);
        assertThat(testBidPrjStc.getResonA01()).isEqualTo(DEFAULT_RESON_A_01);
        assertThat(testBidPrjStc.getDateA01()).isEqualTo(DEFAULT_DATE_A_01);
        assertThat(testBidPrjStc.getYndA02()).isEqualTo(DEFAULT_YND_A_02);
        assertThat(testBidPrjStc.getResonA02()).isEqualTo(DEFAULT_RESON_A_02);
        assertThat(testBidPrjStc.getDateA02()).isEqualTo(DEFAULT_DATE_A_02);
        assertThat(testBidPrjStc.getYndA03()).isEqualTo(DEFAULT_YND_A_03);
        assertThat(testBidPrjStc.getResonA03()).isEqualTo(DEFAULT_RESON_A_03);
        assertThat(testBidPrjStc.getDateA03()).isEqualTo(DEFAULT_DATE_A_03);
        assertThat(testBidPrjStc.getYndA04()).isEqualTo(DEFAULT_YND_A_04);
        assertThat(testBidPrjStc.getResonA04()).isEqualTo(DEFAULT_RESON_A_04);
        assertThat(testBidPrjStc.getDateA04()).isEqualTo(DEFAULT_DATE_A_04);
        assertThat(testBidPrjStc.getYndA05()).isEqualTo(DEFAULT_YND_A_05);
        assertThat(testBidPrjStc.getResonA05()).isEqualTo(DEFAULT_RESON_A_05);
        assertThat(testBidPrjStc.getDateA05()).isEqualTo(DEFAULT_DATE_A_05);
        assertThat(testBidPrjStc.getYndA06()).isEqualTo(DEFAULT_YND_A_06);
        assertThat(testBidPrjStc.getResonA06()).isEqualTo(DEFAULT_RESON_A_06);
        assertThat(testBidPrjStc.getDateA06()).isEqualTo(DEFAULT_DATE_A_06);
        assertThat(testBidPrjStc.getYndA07()).isEqualTo(DEFAULT_YND_A_07);
        assertThat(testBidPrjStc.getResonA07()).isEqualTo(DEFAULT_RESON_A_07);
        assertThat(testBidPrjStc.getDateA07()).isEqualTo(DEFAULT_DATE_A_07);
        assertThat(testBidPrjStc.getYndA08()).isEqualTo(DEFAULT_YND_A_08);
        assertThat(testBidPrjStc.getResonA08()).isEqualTo(DEFAULT_RESON_A_08);
        assertThat(testBidPrjStc.getDateA08()).isEqualTo(DEFAULT_DATE_A_08);
        assertThat(testBidPrjStc.getYndA09()).isEqualTo(DEFAULT_YND_A_09);
        assertThat(testBidPrjStc.getResonA09()).isEqualTo(DEFAULT_RESON_A_09);
        assertThat(testBidPrjStc.getDateA09()).isEqualTo(DEFAULT_DATE_A_09);
        assertThat(testBidPrjStc.getYndA10()).isEqualTo(DEFAULT_YND_A_10);
        assertThat(testBidPrjStc.getResonA10()).isEqualTo(DEFAULT_RESON_A_10);
        assertThat(testBidPrjStc.getDateA10()).isEqualTo(DEFAULT_DATE_A_10);
        assertThat(testBidPrjStc.getYndA11()).isEqualTo(DEFAULT_YND_A_11);
        assertThat(testBidPrjStc.getResonA11()).isEqualTo(DEFAULT_RESON_A_11);
        assertThat(testBidPrjStc.getDateA11()).isEqualTo(DEFAULT_DATE_A_11);
        assertThat(testBidPrjStc.getYndA12()).isEqualTo(DEFAULT_YND_A_12);
        assertThat(testBidPrjStc.getResonA12()).isEqualTo(DEFAULT_RESON_A_12);
        assertThat(testBidPrjStc.getDateA12()).isEqualTo(DEFAULT_DATE_A_12);
        assertThat(testBidPrjStc.getYndA13()).isEqualTo(DEFAULT_YND_A_13);
        assertThat(testBidPrjStc.getResonA13()).isEqualTo(DEFAULT_RESON_A_13);
        assertThat(testBidPrjStc.getDateA13()).isEqualTo(DEFAULT_DATE_A_13);
        assertThat(testBidPrjStc.getYndA14()).isEqualTo(DEFAULT_YND_A_14);
        assertThat(testBidPrjStc.getResonA14()).isEqualTo(DEFAULT_RESON_A_14);
        assertThat(testBidPrjStc.getDateA14()).isEqualTo(DEFAULT_DATE_A_14);
        assertThat(testBidPrjStc.getYndA15()).isEqualTo(DEFAULT_YND_A_15);
        assertThat(testBidPrjStc.getResonA15()).isEqualTo(DEFAULT_RESON_A_15);
        assertThat(testBidPrjStc.getDateA15()).isEqualTo(DEFAULT_DATE_A_15);
        assertThat(testBidPrjStc.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjStc.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjStc.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjStc.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjStc.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjStc.getYndA16()).isEqualTo(DEFAULT_YND_A_16);
        assertThat(testBidPrjStc.getResonA16()).isEqualTo(DEFAULT_RESON_A_16);
        assertThat(testBidPrjStc.getDateA16()).isEqualTo(DEFAULT_DATE_A_16);
        assertThat(testBidPrjStc.getYndB01()).isEqualTo(DEFAULT_YND_B_01);
        assertThat(testBidPrjStc.getResonB01()).isEqualTo(DEFAULT_RESON_B_01);
        assertThat(testBidPrjStc.getDateB01()).isEqualTo(DEFAULT_DATE_B_01);
        assertThat(testBidPrjStc.getYndB02()).isEqualTo(DEFAULT_YND_B_02);
        assertThat(testBidPrjStc.getResonB02()).isEqualTo(DEFAULT_RESON_B_02);
        assertThat(testBidPrjStc.getDateB02()).isEqualTo(DEFAULT_DATE_B_02);
        assertThat(testBidPrjStc.getYndB03()).isEqualTo(DEFAULT_YND_B_03);
        assertThat(testBidPrjStc.getResonB03()).isEqualTo(DEFAULT_RESON_B_03);
        assertThat(testBidPrjStc.getDateB03()).isEqualTo(DEFAULT_DATE_B_03);
        assertThat(testBidPrjStc.getYndB04()).isEqualTo(DEFAULT_YND_B_04);
        assertThat(testBidPrjStc.getResonB04()).isEqualTo(DEFAULT_RESON_B_04);
        assertThat(testBidPrjStc.getDateB04()).isEqualTo(DEFAULT_DATE_B_04);
        assertThat(testBidPrjStc.getYndB05()).isEqualTo(DEFAULT_YND_B_05);
        assertThat(testBidPrjStc.getResonB05()).isEqualTo(DEFAULT_RESON_B_05);
        assertThat(testBidPrjStc.getDateB05()).isEqualTo(DEFAULT_DATE_B_05);
        assertThat(testBidPrjStc.getYndB06()).isEqualTo(DEFAULT_YND_B_06);
        assertThat(testBidPrjStc.getResonB06()).isEqualTo(DEFAULT_RESON_B_06);
        assertThat(testBidPrjStc.getDateB06()).isEqualTo(DEFAULT_DATE_B_06);
        assertThat(testBidPrjStc.getYndB07()).isEqualTo(DEFAULT_YND_B_07);
        assertThat(testBidPrjStc.getResonB07()).isEqualTo(DEFAULT_RESON_B_07);
        assertThat(testBidPrjStc.getDateB07()).isEqualTo(DEFAULT_DATE_B_07);
    }

    @Test
    @Transactional
    void createBidPrjStcWithExistingId() throws Exception {
        // Create the BidPrjStc with an existing ID
//        bidPrjStc.setId(1L);
//        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(bidPrjStc);
//
//        int databaseSizeBeforeCreate = bidPrjStcRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjStcMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStc in the database
//        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
//        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidPrjStcs() throws Exception {
        // Initialize the database
        bidPrjStcRepository.saveAndFlush(bidPrjStc);

        // Get all the bidPrjStcList
        restBidPrjStcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjStc.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].checkDate").value(hasItem(DEFAULT_CHECK_DATE.toString())))
            .andExpect(jsonPath("$.[*].admFileId").value(hasItem(DEFAULT_ADM_FILE_ID.intValue())))
            .andExpect(jsonPath("$.[*].yndA01").value(hasItem(DEFAULT_YND_A_01)))
            .andExpect(jsonPath("$.[*].resonA01").value(hasItem(DEFAULT_RESON_A_01)))
            .andExpect(jsonPath("$.[*].dateA01").value(hasItem(DEFAULT_DATE_A_01.toString())))
            .andExpect(jsonPath("$.[*].yndA02").value(hasItem(DEFAULT_YND_A_02)))
            .andExpect(jsonPath("$.[*].resonA02").value(hasItem(DEFAULT_RESON_A_02)))
            .andExpect(jsonPath("$.[*].dateA02").value(hasItem(DEFAULT_DATE_A_02.toString())))
            .andExpect(jsonPath("$.[*].yndA03").value(hasItem(DEFAULT_YND_A_03)))
            .andExpect(jsonPath("$.[*].resonA03").value(hasItem(DEFAULT_RESON_A_03)))
            .andExpect(jsonPath("$.[*].dateA03").value(hasItem(DEFAULT_DATE_A_03.toString())))
            .andExpect(jsonPath("$.[*].yndA04").value(hasItem(DEFAULT_YND_A_04)))
            .andExpect(jsonPath("$.[*].resonA04").value(hasItem(DEFAULT_RESON_A_04)))
            .andExpect(jsonPath("$.[*].dateA04").value(hasItem(DEFAULT_DATE_A_04.toString())))
            .andExpect(jsonPath("$.[*].yndA05").value(hasItem(DEFAULT_YND_A_05)))
            .andExpect(jsonPath("$.[*].resonA05").value(hasItem(DEFAULT_RESON_A_05)))
            .andExpect(jsonPath("$.[*].dateA05").value(hasItem(DEFAULT_DATE_A_05.toString())))
            .andExpect(jsonPath("$.[*].yndA06").value(hasItem(DEFAULT_YND_A_06)))
            .andExpect(jsonPath("$.[*].resonA06").value(hasItem(DEFAULT_RESON_A_06)))
            .andExpect(jsonPath("$.[*].dateA06").value(hasItem(DEFAULT_DATE_A_06.toString())))
            .andExpect(jsonPath("$.[*].yndA07").value(hasItem(DEFAULT_YND_A_07)))
            .andExpect(jsonPath("$.[*].resonA07").value(hasItem(DEFAULT_RESON_A_07)))
            .andExpect(jsonPath("$.[*].dateA07").value(hasItem(DEFAULT_DATE_A_07.toString())))
            .andExpect(jsonPath("$.[*].yndA08").value(hasItem(DEFAULT_YND_A_08)))
            .andExpect(jsonPath("$.[*].resonA08").value(hasItem(DEFAULT_RESON_A_08)))
            .andExpect(jsonPath("$.[*].dateA08").value(hasItem(DEFAULT_DATE_A_08.toString())))
            .andExpect(jsonPath("$.[*].yndA09").value(hasItem(DEFAULT_YND_A_09)))
            .andExpect(jsonPath("$.[*].resonA09").value(hasItem(DEFAULT_RESON_A_09)))
            .andExpect(jsonPath("$.[*].dateA09").value(hasItem(DEFAULT_DATE_A_09.toString())))
            .andExpect(jsonPath("$.[*].yndA10").value(hasItem(DEFAULT_YND_A_10)))
            .andExpect(jsonPath("$.[*].resonA10").value(hasItem(DEFAULT_RESON_A_10)))
            .andExpect(jsonPath("$.[*].dateA10").value(hasItem(DEFAULT_DATE_A_10.toString())))
            .andExpect(jsonPath("$.[*].yndA11").value(hasItem(DEFAULT_YND_A_11)))
            .andExpect(jsonPath("$.[*].resonA11").value(hasItem(DEFAULT_RESON_A_11)))
            .andExpect(jsonPath("$.[*].dateA11").value(hasItem(DEFAULT_DATE_A_11.toString())))
            .andExpect(jsonPath("$.[*].yndA12").value(hasItem(DEFAULT_YND_A_12)))
            .andExpect(jsonPath("$.[*].resonA12").value(hasItem(DEFAULT_RESON_A_12)))
            .andExpect(jsonPath("$.[*].dateA12").value(hasItem(DEFAULT_DATE_A_12.toString())))
            .andExpect(jsonPath("$.[*].yndA13").value(hasItem(DEFAULT_YND_A_13)))
            .andExpect(jsonPath("$.[*].resonA13").value(hasItem(DEFAULT_RESON_A_13)))
            .andExpect(jsonPath("$.[*].dateA13").value(hasItem(DEFAULT_DATE_A_13.toString())))
            .andExpect(jsonPath("$.[*].yndA14").value(hasItem(DEFAULT_YND_A_14)))
            .andExpect(jsonPath("$.[*].resonA14").value(hasItem(DEFAULT_RESON_A_14)))
            .andExpect(jsonPath("$.[*].dateA14").value(hasItem(DEFAULT_DATE_A_14.toString())))
            .andExpect(jsonPath("$.[*].yndA15").value(hasItem(DEFAULT_YND_A_15)))
            .andExpect(jsonPath("$.[*].resonA15").value(hasItem(DEFAULT_RESON_A_15)))
            .andExpect(jsonPath("$.[*].dateA15").value(hasItem(DEFAULT_DATE_A_15.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].dsts").value(hasItem(DEFAULT_DSTS)))
            .andExpect(jsonPath("$.[*].yndA16").value(hasItem(DEFAULT_YND_A_16)))
            .andExpect(jsonPath("$.[*].resonA16").value(hasItem(DEFAULT_RESON_A_16)))
            .andExpect(jsonPath("$.[*].dateA16").value(hasItem(DEFAULT_DATE_A_16.toString())))
            .andExpect(jsonPath("$.[*].yndB01").value(hasItem(DEFAULT_YND_B_01)))
            .andExpect(jsonPath("$.[*].resonB01").value(hasItem(DEFAULT_RESON_B_01)))
            .andExpect(jsonPath("$.[*].dateB01").value(hasItem(DEFAULT_DATE_B_01.toString())))
            .andExpect(jsonPath("$.[*].yndB02").value(hasItem(DEFAULT_YND_B_02)))
            .andExpect(jsonPath("$.[*].resonB02").value(hasItem(DEFAULT_RESON_B_02)))
            .andExpect(jsonPath("$.[*].dateB02").value(hasItem(DEFAULT_DATE_B_02.toString())))
            .andExpect(jsonPath("$.[*].yndB03").value(hasItem(DEFAULT_YND_B_03)))
            .andExpect(jsonPath("$.[*].resonB03").value(hasItem(DEFAULT_RESON_B_03)))
            .andExpect(jsonPath("$.[*].dateB03").value(hasItem(DEFAULT_DATE_B_03.toString())))
            .andExpect(jsonPath("$.[*].yndB04").value(hasItem(DEFAULT_YND_B_04)))
            .andExpect(jsonPath("$.[*].resonB04").value(hasItem(DEFAULT_RESON_B_04)))
            .andExpect(jsonPath("$.[*].dateB04").value(hasItem(DEFAULT_DATE_B_04.toString())))
            .andExpect(jsonPath("$.[*].yndB05").value(hasItem(DEFAULT_YND_B_05)))
            .andExpect(jsonPath("$.[*].resonB05").value(hasItem(DEFAULT_RESON_B_05)))
            .andExpect(jsonPath("$.[*].dateB05").value(hasItem(DEFAULT_DATE_B_05.toString())))
            .andExpect(jsonPath("$.[*].yndB06").value(hasItem(DEFAULT_YND_B_06)))
            .andExpect(jsonPath("$.[*].resonB06").value(hasItem(DEFAULT_RESON_B_06)))
            .andExpect(jsonPath("$.[*].dateB06").value(hasItem(DEFAULT_DATE_B_06.toString())))
            .andExpect(jsonPath("$.[*].yndB07").value(hasItem(DEFAULT_YND_B_07)))
            .andExpect(jsonPath("$.[*].resonB07").value(hasItem(DEFAULT_RESON_B_07)))
            .andExpect(jsonPath("$.[*].dateB07").value(hasItem(DEFAULT_DATE_B_07.toString())));
    }

    @Test
    @Transactional
    void getBidPrjStc() throws Exception {
        // Initialize the database
        bidPrjStcRepository.saveAndFlush(bidPrjStc);

        // Get the bidPrjStc
        restBidPrjStcMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjStc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjStc.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.checkDate").value(DEFAULT_CHECK_DATE.toString()))
            .andExpect(jsonPath("$.admFileId").value(DEFAULT_ADM_FILE_ID.intValue()))
            .andExpect(jsonPath("$.yndA01").value(DEFAULT_YND_A_01))
            .andExpect(jsonPath("$.resonA01").value(DEFAULT_RESON_A_01))
            .andExpect(jsonPath("$.dateA01").value(DEFAULT_DATE_A_01.toString()))
            .andExpect(jsonPath("$.yndA02").value(DEFAULT_YND_A_02))
            .andExpect(jsonPath("$.resonA02").value(DEFAULT_RESON_A_02))
            .andExpect(jsonPath("$.dateA02").value(DEFAULT_DATE_A_02.toString()))
            .andExpect(jsonPath("$.yndA03").value(DEFAULT_YND_A_03))
            .andExpect(jsonPath("$.resonA03").value(DEFAULT_RESON_A_03))
            .andExpect(jsonPath("$.dateA03").value(DEFAULT_DATE_A_03.toString()))
            .andExpect(jsonPath("$.yndA04").value(DEFAULT_YND_A_04))
            .andExpect(jsonPath("$.resonA04").value(DEFAULT_RESON_A_04))
            .andExpect(jsonPath("$.dateA04").value(DEFAULT_DATE_A_04.toString()))
            .andExpect(jsonPath("$.yndA05").value(DEFAULT_YND_A_05))
            .andExpect(jsonPath("$.resonA05").value(DEFAULT_RESON_A_05))
            .andExpect(jsonPath("$.dateA05").value(DEFAULT_DATE_A_05.toString()))
            .andExpect(jsonPath("$.yndA06").value(DEFAULT_YND_A_06))
            .andExpect(jsonPath("$.resonA06").value(DEFAULT_RESON_A_06))
            .andExpect(jsonPath("$.dateA06").value(DEFAULT_DATE_A_06.toString()))
            .andExpect(jsonPath("$.yndA07").value(DEFAULT_YND_A_07))
            .andExpect(jsonPath("$.resonA07").value(DEFAULT_RESON_A_07))
            .andExpect(jsonPath("$.dateA07").value(DEFAULT_DATE_A_07.toString()))
            .andExpect(jsonPath("$.yndA08").value(DEFAULT_YND_A_08))
            .andExpect(jsonPath("$.resonA08").value(DEFAULT_RESON_A_08))
            .andExpect(jsonPath("$.dateA08").value(DEFAULT_DATE_A_08.toString()))
            .andExpect(jsonPath("$.yndA09").value(DEFAULT_YND_A_09))
            .andExpect(jsonPath("$.resonA09").value(DEFAULT_RESON_A_09))
            .andExpect(jsonPath("$.dateA09").value(DEFAULT_DATE_A_09.toString()))
            .andExpect(jsonPath("$.yndA10").value(DEFAULT_YND_A_10))
            .andExpect(jsonPath("$.resonA10").value(DEFAULT_RESON_A_10))
            .andExpect(jsonPath("$.dateA10").value(DEFAULT_DATE_A_10.toString()))
            .andExpect(jsonPath("$.yndA11").value(DEFAULT_YND_A_11))
            .andExpect(jsonPath("$.resonA11").value(DEFAULT_RESON_A_11))
            .andExpect(jsonPath("$.dateA11").value(DEFAULT_DATE_A_11.toString()))
            .andExpect(jsonPath("$.yndA12").value(DEFAULT_YND_A_12))
            .andExpect(jsonPath("$.resonA12").value(DEFAULT_RESON_A_12))
            .andExpect(jsonPath("$.dateA12").value(DEFAULT_DATE_A_12.toString()))
            .andExpect(jsonPath("$.yndA13").value(DEFAULT_YND_A_13))
            .andExpect(jsonPath("$.resonA13").value(DEFAULT_RESON_A_13))
            .andExpect(jsonPath("$.dateA13").value(DEFAULT_DATE_A_13.toString()))
            .andExpect(jsonPath("$.yndA14").value(DEFAULT_YND_A_14))
            .andExpect(jsonPath("$.resonA14").value(DEFAULT_RESON_A_14))
            .andExpect(jsonPath("$.dateA14").value(DEFAULT_DATE_A_14.toString()))
            .andExpect(jsonPath("$.yndA15").value(DEFAULT_YND_A_15))
            .andExpect(jsonPath("$.resonA15").value(DEFAULT_RESON_A_15))
            .andExpect(jsonPath("$.dateA15").value(DEFAULT_DATE_A_15.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.dsts").value(DEFAULT_DSTS))
            .andExpect(jsonPath("$.yndA16").value(DEFAULT_YND_A_16))
            .andExpect(jsonPath("$.resonA16").value(DEFAULT_RESON_A_16))
            .andExpect(jsonPath("$.dateA16").value(DEFAULT_DATE_A_16.toString()))
            .andExpect(jsonPath("$.yndB01").value(DEFAULT_YND_B_01))
            .andExpect(jsonPath("$.resonB01").value(DEFAULT_RESON_B_01))
            .andExpect(jsonPath("$.dateB01").value(DEFAULT_DATE_B_01.toString()))
            .andExpect(jsonPath("$.yndB02").value(DEFAULT_YND_B_02))
            .andExpect(jsonPath("$.resonB02").value(DEFAULT_RESON_B_02))
            .andExpect(jsonPath("$.dateB02").value(DEFAULT_DATE_B_02.toString()))
            .andExpect(jsonPath("$.yndB03").value(DEFAULT_YND_B_03))
            .andExpect(jsonPath("$.resonB03").value(DEFAULT_RESON_B_03))
            .andExpect(jsonPath("$.dateB03").value(DEFAULT_DATE_B_03.toString()))
            .andExpect(jsonPath("$.yndB04").value(DEFAULT_YND_B_04))
            .andExpect(jsonPath("$.resonB04").value(DEFAULT_RESON_B_04))
            .andExpect(jsonPath("$.dateB04").value(DEFAULT_DATE_B_04.toString()))
            .andExpect(jsonPath("$.yndB05").value(DEFAULT_YND_B_05))
            .andExpect(jsonPath("$.resonB05").value(DEFAULT_RESON_B_05))
            .andExpect(jsonPath("$.dateB05").value(DEFAULT_DATE_B_05.toString()))
            .andExpect(jsonPath("$.yndB06").value(DEFAULT_YND_B_06))
            .andExpect(jsonPath("$.resonB06").value(DEFAULT_RESON_B_06))
            .andExpect(jsonPath("$.dateB06").value(DEFAULT_DATE_B_06.toString()))
            .andExpect(jsonPath("$.yndB07").value(DEFAULT_YND_B_07))
            .andExpect(jsonPath("$.resonB07").value(DEFAULT_RESON_B_07))
            .andExpect(jsonPath("$.dateB07").value(DEFAULT_DATE_B_07.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjStc() throws Exception {
        // Get the bidPrjStc
        restBidPrjStcMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjStc() throws Exception {
        // Initialize the database
        bidPrjStcRepository.saveAndFlush(bidPrjStc);

        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();

        // Update the bidPrjStc
        BidPrjStc updatedBidPrjStc = bidPrjStcRepository.findById(bidPrjStc.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjStc are not directly saved in db
        em.detach(updatedBidPrjStc);
        updatedBidPrjStc
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .checkDate(UPDATED_CHECK_DATE)
            .admFileId(UPDATED_ADM_FILE_ID)
            .yndA01(UPDATED_YND_A_01)
            .resonA01(UPDATED_RESON_A_01)
            .dateA01(UPDATED_DATE_A_01)
            .yndA02(UPDATED_YND_A_02)
            .resonA02(UPDATED_RESON_A_02)
            .dateA02(UPDATED_DATE_A_02)
            .yndA03(UPDATED_YND_A_03)
            .resonA03(UPDATED_RESON_A_03)
            .dateA03(UPDATED_DATE_A_03)
            .yndA04(UPDATED_YND_A_04)
            .resonA04(UPDATED_RESON_A_04)
            .dateA04(UPDATED_DATE_A_04)
            .yndA05(UPDATED_YND_A_05)
            .resonA05(UPDATED_RESON_A_05)
            .dateA05(UPDATED_DATE_A_05)
            .yndA06(UPDATED_YND_A_06)
            .resonA06(UPDATED_RESON_A_06)
            .dateA06(UPDATED_DATE_A_06)
            .yndA07(UPDATED_YND_A_07)
            .resonA07(UPDATED_RESON_A_07)
            .dateA07(UPDATED_DATE_A_07)
            .yndA08(UPDATED_YND_A_08)
            .resonA08(UPDATED_RESON_A_08)
            .dateA08(UPDATED_DATE_A_08)
            .yndA09(UPDATED_YND_A_09)
            .resonA09(UPDATED_RESON_A_09)
            .dateA09(UPDATED_DATE_A_09)
            .yndA10(UPDATED_YND_A_10)
            .resonA10(UPDATED_RESON_A_10)
            .dateA10(UPDATED_DATE_A_10)
            .yndA11(UPDATED_YND_A_11)
            .resonA11(UPDATED_RESON_A_11)
            .dateA11(UPDATED_DATE_A_11)
            .yndA12(UPDATED_YND_A_12)
            .resonA12(UPDATED_RESON_A_12)
            .dateA12(UPDATED_DATE_A_12)
            .yndA13(UPDATED_YND_A_13)
            .resonA13(UPDATED_RESON_A_13)
            .dateA13(UPDATED_DATE_A_13)
            .yndA14(UPDATED_YND_A_14)
            .resonA14(UPDATED_RESON_A_14)
            .dateA14(UPDATED_DATE_A_14)
            .yndA15(UPDATED_YND_A_15)
            .resonA15(UPDATED_RESON_A_15)
            .dateA15(UPDATED_DATE_A_15)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .yndA16(UPDATED_YND_A_16)
            .resonA16(UPDATED_RESON_A_16)
            .dateA16(UPDATED_DATE_A_16)
            .yndB01(UPDATED_YND_B_01)
            .resonB01(UPDATED_RESON_B_01)
            .dateB01(UPDATED_DATE_B_01)
            .yndB02(UPDATED_YND_B_02)
            .resonB02(UPDATED_RESON_B_02)
            .dateB02(UPDATED_DATE_B_02)
            .yndB03(UPDATED_YND_B_03)
            .resonB03(UPDATED_RESON_B_03)
            .dateB03(UPDATED_DATE_B_03)
            .yndB04(UPDATED_YND_B_04)
            .resonB04(UPDATED_RESON_B_04)
            .dateB04(UPDATED_DATE_B_04)
            .yndB05(UPDATED_YND_B_05)
            .resonB05(UPDATED_RESON_B_05)
            .dateB05(UPDATED_DATE_B_05)
            .yndB06(UPDATED_YND_B_06)
            .resonB06(UPDATED_RESON_B_06)
            .dateB06(UPDATED_DATE_B_06)
            .yndB07(UPDATED_YND_B_07)
            .resonB07(UPDATED_RESON_B_07)
            .dateB07(UPDATED_DATE_B_07);
        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(updatedBidPrjStc);

        restBidPrjStcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjStcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjStc in the database
        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjStc testBidPrjStc = bidPrjStcList.get(bidPrjStcList.size() - 1);
        assertThat(testBidPrjStc.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjStc.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjStc.getCheckDate()).isEqualTo(UPDATED_CHECK_DATE);
        assertThat(testBidPrjStc.getAdmFileId()).isEqualTo(UPDATED_ADM_FILE_ID);
        assertThat(testBidPrjStc.getYndA01()).isEqualTo(UPDATED_YND_A_01);
        assertThat(testBidPrjStc.getResonA01()).isEqualTo(UPDATED_RESON_A_01);
        assertThat(testBidPrjStc.getDateA01()).isEqualTo(UPDATED_DATE_A_01);
        assertThat(testBidPrjStc.getYndA02()).isEqualTo(UPDATED_YND_A_02);
        assertThat(testBidPrjStc.getResonA02()).isEqualTo(UPDATED_RESON_A_02);
        assertThat(testBidPrjStc.getDateA02()).isEqualTo(UPDATED_DATE_A_02);
        assertThat(testBidPrjStc.getYndA03()).isEqualTo(UPDATED_YND_A_03);
        assertThat(testBidPrjStc.getResonA03()).isEqualTo(UPDATED_RESON_A_03);
        assertThat(testBidPrjStc.getDateA03()).isEqualTo(UPDATED_DATE_A_03);
        assertThat(testBidPrjStc.getYndA04()).isEqualTo(UPDATED_YND_A_04);
        assertThat(testBidPrjStc.getResonA04()).isEqualTo(UPDATED_RESON_A_04);
        assertThat(testBidPrjStc.getDateA04()).isEqualTo(UPDATED_DATE_A_04);
        assertThat(testBidPrjStc.getYndA05()).isEqualTo(UPDATED_YND_A_05);
        assertThat(testBidPrjStc.getResonA05()).isEqualTo(UPDATED_RESON_A_05);
        assertThat(testBidPrjStc.getDateA05()).isEqualTo(UPDATED_DATE_A_05);
        assertThat(testBidPrjStc.getYndA06()).isEqualTo(UPDATED_YND_A_06);
        assertThat(testBidPrjStc.getResonA06()).isEqualTo(UPDATED_RESON_A_06);
        assertThat(testBidPrjStc.getDateA06()).isEqualTo(UPDATED_DATE_A_06);
        assertThat(testBidPrjStc.getYndA07()).isEqualTo(UPDATED_YND_A_07);
        assertThat(testBidPrjStc.getResonA07()).isEqualTo(UPDATED_RESON_A_07);
        assertThat(testBidPrjStc.getDateA07()).isEqualTo(UPDATED_DATE_A_07);
        assertThat(testBidPrjStc.getYndA08()).isEqualTo(UPDATED_YND_A_08);
        assertThat(testBidPrjStc.getResonA08()).isEqualTo(UPDATED_RESON_A_08);
        assertThat(testBidPrjStc.getDateA08()).isEqualTo(UPDATED_DATE_A_08);
        assertThat(testBidPrjStc.getYndA09()).isEqualTo(UPDATED_YND_A_09);
        assertThat(testBidPrjStc.getResonA09()).isEqualTo(UPDATED_RESON_A_09);
        assertThat(testBidPrjStc.getDateA09()).isEqualTo(UPDATED_DATE_A_09);
        assertThat(testBidPrjStc.getYndA10()).isEqualTo(UPDATED_YND_A_10);
        assertThat(testBidPrjStc.getResonA10()).isEqualTo(UPDATED_RESON_A_10);
        assertThat(testBidPrjStc.getDateA10()).isEqualTo(UPDATED_DATE_A_10);
        assertThat(testBidPrjStc.getYndA11()).isEqualTo(UPDATED_YND_A_11);
        assertThat(testBidPrjStc.getResonA11()).isEqualTo(UPDATED_RESON_A_11);
        assertThat(testBidPrjStc.getDateA11()).isEqualTo(UPDATED_DATE_A_11);
        assertThat(testBidPrjStc.getYndA12()).isEqualTo(UPDATED_YND_A_12);
        assertThat(testBidPrjStc.getResonA12()).isEqualTo(UPDATED_RESON_A_12);
        assertThat(testBidPrjStc.getDateA12()).isEqualTo(UPDATED_DATE_A_12);
        assertThat(testBidPrjStc.getYndA13()).isEqualTo(UPDATED_YND_A_13);
        assertThat(testBidPrjStc.getResonA13()).isEqualTo(UPDATED_RESON_A_13);
        assertThat(testBidPrjStc.getDateA13()).isEqualTo(UPDATED_DATE_A_13);
        assertThat(testBidPrjStc.getYndA14()).isEqualTo(UPDATED_YND_A_14);
        assertThat(testBidPrjStc.getResonA14()).isEqualTo(UPDATED_RESON_A_14);
        assertThat(testBidPrjStc.getDateA14()).isEqualTo(UPDATED_DATE_A_14);
        assertThat(testBidPrjStc.getYndA15()).isEqualTo(UPDATED_YND_A_15);
        assertThat(testBidPrjStc.getResonA15()).isEqualTo(UPDATED_RESON_A_15);
        assertThat(testBidPrjStc.getDateA15()).isEqualTo(UPDATED_DATE_A_15);
        assertThat(testBidPrjStc.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjStc.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjStc.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjStc.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjStc.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjStc.getYndA16()).isEqualTo(UPDATED_YND_A_16);
        assertThat(testBidPrjStc.getResonA16()).isEqualTo(UPDATED_RESON_A_16);
        assertThat(testBidPrjStc.getDateA16()).isEqualTo(UPDATED_DATE_A_16);
        assertThat(testBidPrjStc.getYndB01()).isEqualTo(UPDATED_YND_B_01);
        assertThat(testBidPrjStc.getResonB01()).isEqualTo(UPDATED_RESON_B_01);
        assertThat(testBidPrjStc.getDateB01()).isEqualTo(UPDATED_DATE_B_01);
        assertThat(testBidPrjStc.getYndB02()).isEqualTo(UPDATED_YND_B_02);
        assertThat(testBidPrjStc.getResonB02()).isEqualTo(UPDATED_RESON_B_02);
        assertThat(testBidPrjStc.getDateB02()).isEqualTo(UPDATED_DATE_B_02);
        assertThat(testBidPrjStc.getYndB03()).isEqualTo(UPDATED_YND_B_03);
        assertThat(testBidPrjStc.getResonB03()).isEqualTo(UPDATED_RESON_B_03);
        assertThat(testBidPrjStc.getDateB03()).isEqualTo(UPDATED_DATE_B_03);
        assertThat(testBidPrjStc.getYndB04()).isEqualTo(UPDATED_YND_B_04);
        assertThat(testBidPrjStc.getResonB04()).isEqualTo(UPDATED_RESON_B_04);
        assertThat(testBidPrjStc.getDateB04()).isEqualTo(UPDATED_DATE_B_04);
        assertThat(testBidPrjStc.getYndB05()).isEqualTo(UPDATED_YND_B_05);
        assertThat(testBidPrjStc.getResonB05()).isEqualTo(UPDATED_RESON_B_05);
        assertThat(testBidPrjStc.getDateB05()).isEqualTo(UPDATED_DATE_B_05);
        assertThat(testBidPrjStc.getYndB06()).isEqualTo(UPDATED_YND_B_06);
        assertThat(testBidPrjStc.getResonB06()).isEqualTo(UPDATED_RESON_B_06);
        assertThat(testBidPrjStc.getDateB06()).isEqualTo(UPDATED_DATE_B_06);
        assertThat(testBidPrjStc.getYndB07()).isEqualTo(UPDATED_YND_B_07);
        assertThat(testBidPrjStc.getResonB07()).isEqualTo(UPDATED_RESON_B_07);
        assertThat(testBidPrjStc.getDateB07()).isEqualTo(UPDATED_DATE_B_07);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjStc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();
//        bidPrjStc.setId(count.incrementAndGet());
//
//        // Create the BidPrjStc
//        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(bidPrjStc);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjStcMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjStcDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStc in the database
//        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
//        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjStc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();
//        bidPrjStc.setId(count.incrementAndGet());
//
//        // Create the BidPrjStc
//        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(bidPrjStc);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjStcMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStc in the database
//        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
//        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjStc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();
//        bidPrjStc.setId(count.incrementAndGet());
//
//        // Create the BidPrjStc
//        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(bidPrjStc);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjStcMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjStc in the database
//        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
//        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjStcWithPatch() throws Exception {
        // Initialize the database
        bidPrjStcRepository.saveAndFlush(bidPrjStc);

        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();

        // Update the bidPrjStc using partial update
        BidPrjStc partialUpdatedBidPrjStc = new BidPrjStc();
        partialUpdatedBidPrjStc.setId(bidPrjStc.getId());

        partialUpdatedBidPrjStc
            .prjno(UPDATED_PRJNO)
            .admFileId(UPDATED_ADM_FILE_ID)
            .yndA01(UPDATED_YND_A_01)
            .resonA01(UPDATED_RESON_A_01)
            .dateA01(UPDATED_DATE_A_01)
            .yndA02(UPDATED_YND_A_02)
            .resonA02(UPDATED_RESON_A_02)
            .dateA03(UPDATED_DATE_A_03)
            .yndA04(UPDATED_YND_A_04)
            .yndA05(UPDATED_YND_A_05)
            .resonA05(UPDATED_RESON_A_05)
            .dateA05(UPDATED_DATE_A_05)
            .yndA07(UPDATED_YND_A_07)
            .resonA07(UPDATED_RESON_A_07)
            .dateA07(UPDATED_DATE_A_07)
            .yndA08(UPDATED_YND_A_08)
            .resonA08(UPDATED_RESON_A_08)
            .yndA09(UPDATED_YND_A_09)
            .dateA09(UPDATED_DATE_A_09)
            .resonA10(UPDATED_RESON_A_10)
            .dateA10(UPDATED_DATE_A_10)
            .resonA11(UPDATED_RESON_A_11)
            .yndA12(UPDATED_YND_A_12)
            .resonA12(UPDATED_RESON_A_12)
            .dateA12(UPDATED_DATE_A_12)
            .yndA13(UPDATED_YND_A_13)
            .dateA13(UPDATED_DATE_A_13)
            .resonA14(UPDATED_RESON_A_14)
            .yndA15(UPDATED_YND_A_15)
            .resonA15(UPDATED_RESON_A_15)
            .dateA15(UPDATED_DATE_A_15)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .yndA16(UPDATED_YND_A_16)
            .dateA16(UPDATED_DATE_A_16)
            .resonB01(UPDATED_RESON_B_01)
            .resonB02(UPDATED_RESON_B_02)
            .dateB02(UPDATED_DATE_B_02)
            .resonB04(UPDATED_RESON_B_04)
            .yndB06(UPDATED_YND_B_06)
            .resonB06(UPDATED_RESON_B_06)
            .dateB06(UPDATED_DATE_B_06)
            .resonB07(UPDATED_RESON_B_07);

        restBidPrjStcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjStc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjStc))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjStc in the database
        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjStc testBidPrjStc = bidPrjStcList.get(bidPrjStcList.size() - 1);
        assertThat(testBidPrjStc.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjStc.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjStc.getCheckDate()).isEqualTo(DEFAULT_CHECK_DATE);
        assertThat(testBidPrjStc.getAdmFileId()).isEqualTo(UPDATED_ADM_FILE_ID);
        assertThat(testBidPrjStc.getYndA01()).isEqualTo(UPDATED_YND_A_01);
        assertThat(testBidPrjStc.getResonA01()).isEqualTo(UPDATED_RESON_A_01);
        assertThat(testBidPrjStc.getDateA01()).isEqualTo(UPDATED_DATE_A_01);
        assertThat(testBidPrjStc.getYndA02()).isEqualTo(UPDATED_YND_A_02);
        assertThat(testBidPrjStc.getResonA02()).isEqualTo(UPDATED_RESON_A_02);
        assertThat(testBidPrjStc.getDateA02()).isEqualTo(DEFAULT_DATE_A_02);
        assertThat(testBidPrjStc.getYndA03()).isEqualTo(DEFAULT_YND_A_03);
        assertThat(testBidPrjStc.getResonA03()).isEqualTo(DEFAULT_RESON_A_03);
        assertThat(testBidPrjStc.getDateA03()).isEqualTo(UPDATED_DATE_A_03);
        assertThat(testBidPrjStc.getYndA04()).isEqualTo(UPDATED_YND_A_04);
        assertThat(testBidPrjStc.getResonA04()).isEqualTo(DEFAULT_RESON_A_04);
        assertThat(testBidPrjStc.getDateA04()).isEqualTo(DEFAULT_DATE_A_04);
        assertThat(testBidPrjStc.getYndA05()).isEqualTo(UPDATED_YND_A_05);
        assertThat(testBidPrjStc.getResonA05()).isEqualTo(UPDATED_RESON_A_05);
        assertThat(testBidPrjStc.getDateA05()).isEqualTo(UPDATED_DATE_A_05);
        assertThat(testBidPrjStc.getYndA06()).isEqualTo(DEFAULT_YND_A_06);
        assertThat(testBidPrjStc.getResonA06()).isEqualTo(DEFAULT_RESON_A_06);
        assertThat(testBidPrjStc.getDateA06()).isEqualTo(DEFAULT_DATE_A_06);
        assertThat(testBidPrjStc.getYndA07()).isEqualTo(UPDATED_YND_A_07);
        assertThat(testBidPrjStc.getResonA07()).isEqualTo(UPDATED_RESON_A_07);
        assertThat(testBidPrjStc.getDateA07()).isEqualTo(UPDATED_DATE_A_07);
        assertThat(testBidPrjStc.getYndA08()).isEqualTo(UPDATED_YND_A_08);
        assertThat(testBidPrjStc.getResonA08()).isEqualTo(UPDATED_RESON_A_08);
        assertThat(testBidPrjStc.getDateA08()).isEqualTo(DEFAULT_DATE_A_08);
        assertThat(testBidPrjStc.getYndA09()).isEqualTo(UPDATED_YND_A_09);
        assertThat(testBidPrjStc.getResonA09()).isEqualTo(DEFAULT_RESON_A_09);
        assertThat(testBidPrjStc.getDateA09()).isEqualTo(UPDATED_DATE_A_09);
        assertThat(testBidPrjStc.getYndA10()).isEqualTo(DEFAULT_YND_A_10);
        assertThat(testBidPrjStc.getResonA10()).isEqualTo(UPDATED_RESON_A_10);
        assertThat(testBidPrjStc.getDateA10()).isEqualTo(UPDATED_DATE_A_10);
        assertThat(testBidPrjStc.getYndA11()).isEqualTo(DEFAULT_YND_A_11);
        assertThat(testBidPrjStc.getResonA11()).isEqualTo(UPDATED_RESON_A_11);
        assertThat(testBidPrjStc.getDateA11()).isEqualTo(DEFAULT_DATE_A_11);
        assertThat(testBidPrjStc.getYndA12()).isEqualTo(UPDATED_YND_A_12);
        assertThat(testBidPrjStc.getResonA12()).isEqualTo(UPDATED_RESON_A_12);
        assertThat(testBidPrjStc.getDateA12()).isEqualTo(UPDATED_DATE_A_12);
        assertThat(testBidPrjStc.getYndA13()).isEqualTo(UPDATED_YND_A_13);
        assertThat(testBidPrjStc.getResonA13()).isEqualTo(DEFAULT_RESON_A_13);
        assertThat(testBidPrjStc.getDateA13()).isEqualTo(UPDATED_DATE_A_13);
        assertThat(testBidPrjStc.getYndA14()).isEqualTo(DEFAULT_YND_A_14);
        assertThat(testBidPrjStc.getResonA14()).isEqualTo(UPDATED_RESON_A_14);
        assertThat(testBidPrjStc.getDateA14()).isEqualTo(DEFAULT_DATE_A_14);
        assertThat(testBidPrjStc.getYndA15()).isEqualTo(UPDATED_YND_A_15);
        assertThat(testBidPrjStc.getResonA15()).isEqualTo(UPDATED_RESON_A_15);
        assertThat(testBidPrjStc.getDateA15()).isEqualTo(UPDATED_DATE_A_15);
        assertThat(testBidPrjStc.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjStc.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjStc.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjStc.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjStc.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjStc.getYndA16()).isEqualTo(UPDATED_YND_A_16);
        assertThat(testBidPrjStc.getResonA16()).isEqualTo(DEFAULT_RESON_A_16);
        assertThat(testBidPrjStc.getDateA16()).isEqualTo(UPDATED_DATE_A_16);
        assertThat(testBidPrjStc.getYndB01()).isEqualTo(DEFAULT_YND_B_01);
        assertThat(testBidPrjStc.getResonB01()).isEqualTo(UPDATED_RESON_B_01);
        assertThat(testBidPrjStc.getDateB01()).isEqualTo(DEFAULT_DATE_B_01);
        assertThat(testBidPrjStc.getYndB02()).isEqualTo(DEFAULT_YND_B_02);
        assertThat(testBidPrjStc.getResonB02()).isEqualTo(UPDATED_RESON_B_02);
        assertThat(testBidPrjStc.getDateB02()).isEqualTo(UPDATED_DATE_B_02);
        assertThat(testBidPrjStc.getYndB03()).isEqualTo(DEFAULT_YND_B_03);
        assertThat(testBidPrjStc.getResonB03()).isEqualTo(DEFAULT_RESON_B_03);
        assertThat(testBidPrjStc.getDateB03()).isEqualTo(DEFAULT_DATE_B_03);
        assertThat(testBidPrjStc.getYndB04()).isEqualTo(DEFAULT_YND_B_04);
        assertThat(testBidPrjStc.getResonB04()).isEqualTo(UPDATED_RESON_B_04);
        assertThat(testBidPrjStc.getDateB04()).isEqualTo(DEFAULT_DATE_B_04);
        assertThat(testBidPrjStc.getYndB05()).isEqualTo(DEFAULT_YND_B_05);
        assertThat(testBidPrjStc.getResonB05()).isEqualTo(DEFAULT_RESON_B_05);
        assertThat(testBidPrjStc.getDateB05()).isEqualTo(DEFAULT_DATE_B_05);
        assertThat(testBidPrjStc.getYndB06()).isEqualTo(UPDATED_YND_B_06);
        assertThat(testBidPrjStc.getResonB06()).isEqualTo(UPDATED_RESON_B_06);
        assertThat(testBidPrjStc.getDateB06()).isEqualTo(UPDATED_DATE_B_06);
        assertThat(testBidPrjStc.getYndB07()).isEqualTo(DEFAULT_YND_B_07);
        assertThat(testBidPrjStc.getResonB07()).isEqualTo(UPDATED_RESON_B_07);
        assertThat(testBidPrjStc.getDateB07()).isEqualTo(DEFAULT_DATE_B_07);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjStcWithPatch() throws Exception {
        // Initialize the database
        bidPrjStcRepository.saveAndFlush(bidPrjStc);

        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();

        // Update the bidPrjStc using partial update
        BidPrjStc partialUpdatedBidPrjStc = new BidPrjStc();
        partialUpdatedBidPrjStc.setId(bidPrjStc.getId());

        partialUpdatedBidPrjStc
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .checkDate(UPDATED_CHECK_DATE)
            .admFileId(UPDATED_ADM_FILE_ID)
            .yndA01(UPDATED_YND_A_01)
            .resonA01(UPDATED_RESON_A_01)
            .dateA01(UPDATED_DATE_A_01)
            .yndA02(UPDATED_YND_A_02)
            .resonA02(UPDATED_RESON_A_02)
            .dateA02(UPDATED_DATE_A_02)
            .yndA03(UPDATED_YND_A_03)
            .resonA03(UPDATED_RESON_A_03)
            .dateA03(UPDATED_DATE_A_03)
            .yndA04(UPDATED_YND_A_04)
            .resonA04(UPDATED_RESON_A_04)
            .dateA04(UPDATED_DATE_A_04)
            .yndA05(UPDATED_YND_A_05)
            .resonA05(UPDATED_RESON_A_05)
            .dateA05(UPDATED_DATE_A_05)
            .yndA06(UPDATED_YND_A_06)
            .resonA06(UPDATED_RESON_A_06)
            .dateA06(UPDATED_DATE_A_06)
            .yndA07(UPDATED_YND_A_07)
            .resonA07(UPDATED_RESON_A_07)
            .dateA07(UPDATED_DATE_A_07)
            .yndA08(UPDATED_YND_A_08)
            .resonA08(UPDATED_RESON_A_08)
            .dateA08(UPDATED_DATE_A_08)
            .yndA09(UPDATED_YND_A_09)
            .resonA09(UPDATED_RESON_A_09)
            .dateA09(UPDATED_DATE_A_09)
            .yndA10(UPDATED_YND_A_10)
            .resonA10(UPDATED_RESON_A_10)
            .dateA10(UPDATED_DATE_A_10)
            .yndA11(UPDATED_YND_A_11)
            .resonA11(UPDATED_RESON_A_11)
            .dateA11(UPDATED_DATE_A_11)
            .yndA12(UPDATED_YND_A_12)
            .resonA12(UPDATED_RESON_A_12)
            .dateA12(UPDATED_DATE_A_12)
            .yndA13(UPDATED_YND_A_13)
            .resonA13(UPDATED_RESON_A_13)
            .dateA13(UPDATED_DATE_A_13)
            .yndA14(UPDATED_YND_A_14)
            .resonA14(UPDATED_RESON_A_14)
            .dateA14(UPDATED_DATE_A_14)
            .yndA15(UPDATED_YND_A_15)
            .resonA15(UPDATED_RESON_A_15)
            .dateA15(UPDATED_DATE_A_15)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .yndA16(UPDATED_YND_A_16)
            .resonA16(UPDATED_RESON_A_16)
            .dateA16(UPDATED_DATE_A_16)
            .yndB01(UPDATED_YND_B_01)
            .resonB01(UPDATED_RESON_B_01)
            .dateB01(UPDATED_DATE_B_01)
            .yndB02(UPDATED_YND_B_02)
            .resonB02(UPDATED_RESON_B_02)
            .dateB02(UPDATED_DATE_B_02)
            .yndB03(UPDATED_YND_B_03)
            .resonB03(UPDATED_RESON_B_03)
            .dateB03(UPDATED_DATE_B_03)
            .yndB04(UPDATED_YND_B_04)
            .resonB04(UPDATED_RESON_B_04)
            .dateB04(UPDATED_DATE_B_04)
            .yndB05(UPDATED_YND_B_05)
            .resonB05(UPDATED_RESON_B_05)
            .dateB05(UPDATED_DATE_B_05)
            .yndB06(UPDATED_YND_B_06)
            .resonB06(UPDATED_RESON_B_06)
            .dateB06(UPDATED_DATE_B_06)
            .yndB07(UPDATED_YND_B_07)
            .resonB07(UPDATED_RESON_B_07)
            .dateB07(UPDATED_DATE_B_07);

        restBidPrjStcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjStc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjStc))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjStc in the database
        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjStc testBidPrjStc = bidPrjStcList.get(bidPrjStcList.size() - 1);
        assertThat(testBidPrjStc.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjStc.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjStc.getCheckDate()).isEqualTo(UPDATED_CHECK_DATE);
        assertThat(testBidPrjStc.getAdmFileId()).isEqualTo(UPDATED_ADM_FILE_ID);
        assertThat(testBidPrjStc.getYndA01()).isEqualTo(UPDATED_YND_A_01);
        assertThat(testBidPrjStc.getResonA01()).isEqualTo(UPDATED_RESON_A_01);
        assertThat(testBidPrjStc.getDateA01()).isEqualTo(UPDATED_DATE_A_01);
        assertThat(testBidPrjStc.getYndA02()).isEqualTo(UPDATED_YND_A_02);
        assertThat(testBidPrjStc.getResonA02()).isEqualTo(UPDATED_RESON_A_02);
        assertThat(testBidPrjStc.getDateA02()).isEqualTo(UPDATED_DATE_A_02);
        assertThat(testBidPrjStc.getYndA03()).isEqualTo(UPDATED_YND_A_03);
        assertThat(testBidPrjStc.getResonA03()).isEqualTo(UPDATED_RESON_A_03);
        assertThat(testBidPrjStc.getDateA03()).isEqualTo(UPDATED_DATE_A_03);
        assertThat(testBidPrjStc.getYndA04()).isEqualTo(UPDATED_YND_A_04);
        assertThat(testBidPrjStc.getResonA04()).isEqualTo(UPDATED_RESON_A_04);
        assertThat(testBidPrjStc.getDateA04()).isEqualTo(UPDATED_DATE_A_04);
        assertThat(testBidPrjStc.getYndA05()).isEqualTo(UPDATED_YND_A_05);
        assertThat(testBidPrjStc.getResonA05()).isEqualTo(UPDATED_RESON_A_05);
        assertThat(testBidPrjStc.getDateA05()).isEqualTo(UPDATED_DATE_A_05);
        assertThat(testBidPrjStc.getYndA06()).isEqualTo(UPDATED_YND_A_06);
        assertThat(testBidPrjStc.getResonA06()).isEqualTo(UPDATED_RESON_A_06);
        assertThat(testBidPrjStc.getDateA06()).isEqualTo(UPDATED_DATE_A_06);
        assertThat(testBidPrjStc.getYndA07()).isEqualTo(UPDATED_YND_A_07);
        assertThat(testBidPrjStc.getResonA07()).isEqualTo(UPDATED_RESON_A_07);
        assertThat(testBidPrjStc.getDateA07()).isEqualTo(UPDATED_DATE_A_07);
        assertThat(testBidPrjStc.getYndA08()).isEqualTo(UPDATED_YND_A_08);
        assertThat(testBidPrjStc.getResonA08()).isEqualTo(UPDATED_RESON_A_08);
        assertThat(testBidPrjStc.getDateA08()).isEqualTo(UPDATED_DATE_A_08);
        assertThat(testBidPrjStc.getYndA09()).isEqualTo(UPDATED_YND_A_09);
        assertThat(testBidPrjStc.getResonA09()).isEqualTo(UPDATED_RESON_A_09);
        assertThat(testBidPrjStc.getDateA09()).isEqualTo(UPDATED_DATE_A_09);
        assertThat(testBidPrjStc.getYndA10()).isEqualTo(UPDATED_YND_A_10);
        assertThat(testBidPrjStc.getResonA10()).isEqualTo(UPDATED_RESON_A_10);
        assertThat(testBidPrjStc.getDateA10()).isEqualTo(UPDATED_DATE_A_10);
        assertThat(testBidPrjStc.getYndA11()).isEqualTo(UPDATED_YND_A_11);
        assertThat(testBidPrjStc.getResonA11()).isEqualTo(UPDATED_RESON_A_11);
        assertThat(testBidPrjStc.getDateA11()).isEqualTo(UPDATED_DATE_A_11);
        assertThat(testBidPrjStc.getYndA12()).isEqualTo(UPDATED_YND_A_12);
        assertThat(testBidPrjStc.getResonA12()).isEqualTo(UPDATED_RESON_A_12);
        assertThat(testBidPrjStc.getDateA12()).isEqualTo(UPDATED_DATE_A_12);
        assertThat(testBidPrjStc.getYndA13()).isEqualTo(UPDATED_YND_A_13);
        assertThat(testBidPrjStc.getResonA13()).isEqualTo(UPDATED_RESON_A_13);
        assertThat(testBidPrjStc.getDateA13()).isEqualTo(UPDATED_DATE_A_13);
        assertThat(testBidPrjStc.getYndA14()).isEqualTo(UPDATED_YND_A_14);
        assertThat(testBidPrjStc.getResonA14()).isEqualTo(UPDATED_RESON_A_14);
        assertThat(testBidPrjStc.getDateA14()).isEqualTo(UPDATED_DATE_A_14);
        assertThat(testBidPrjStc.getYndA15()).isEqualTo(UPDATED_YND_A_15);
        assertThat(testBidPrjStc.getResonA15()).isEqualTo(UPDATED_RESON_A_15);
        assertThat(testBidPrjStc.getDateA15()).isEqualTo(UPDATED_DATE_A_15);
        assertThat(testBidPrjStc.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjStc.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjStc.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjStc.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjStc.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjStc.getYndA16()).isEqualTo(UPDATED_YND_A_16);
        assertThat(testBidPrjStc.getResonA16()).isEqualTo(UPDATED_RESON_A_16);
        assertThat(testBidPrjStc.getDateA16()).isEqualTo(UPDATED_DATE_A_16);
        assertThat(testBidPrjStc.getYndB01()).isEqualTo(UPDATED_YND_B_01);
        assertThat(testBidPrjStc.getResonB01()).isEqualTo(UPDATED_RESON_B_01);
        assertThat(testBidPrjStc.getDateB01()).isEqualTo(UPDATED_DATE_B_01);
        assertThat(testBidPrjStc.getYndB02()).isEqualTo(UPDATED_YND_B_02);
        assertThat(testBidPrjStc.getResonB02()).isEqualTo(UPDATED_RESON_B_02);
        assertThat(testBidPrjStc.getDateB02()).isEqualTo(UPDATED_DATE_B_02);
        assertThat(testBidPrjStc.getYndB03()).isEqualTo(UPDATED_YND_B_03);
        assertThat(testBidPrjStc.getResonB03()).isEqualTo(UPDATED_RESON_B_03);
        assertThat(testBidPrjStc.getDateB03()).isEqualTo(UPDATED_DATE_B_03);
        assertThat(testBidPrjStc.getYndB04()).isEqualTo(UPDATED_YND_B_04);
        assertThat(testBidPrjStc.getResonB04()).isEqualTo(UPDATED_RESON_B_04);
        assertThat(testBidPrjStc.getDateB04()).isEqualTo(UPDATED_DATE_B_04);
        assertThat(testBidPrjStc.getYndB05()).isEqualTo(UPDATED_YND_B_05);
        assertThat(testBidPrjStc.getResonB05()).isEqualTo(UPDATED_RESON_B_05);
        assertThat(testBidPrjStc.getDateB05()).isEqualTo(UPDATED_DATE_B_05);
        assertThat(testBidPrjStc.getYndB06()).isEqualTo(UPDATED_YND_B_06);
        assertThat(testBidPrjStc.getResonB06()).isEqualTo(UPDATED_RESON_B_06);
        assertThat(testBidPrjStc.getDateB06()).isEqualTo(UPDATED_DATE_B_06);
        assertThat(testBidPrjStc.getYndB07()).isEqualTo(UPDATED_YND_B_07);
        assertThat(testBidPrjStc.getResonB07()).isEqualTo(UPDATED_RESON_B_07);
        assertThat(testBidPrjStc.getDateB07()).isEqualTo(UPDATED_DATE_B_07);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjStc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();
//        bidPrjStc.setId(count.incrementAndGet());
//
//        // Create the BidPrjStc
//        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(bidPrjStc);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjStcMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjStcDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStc in the database
//        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
//        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjStc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();
//        bidPrjStc.setId(count.incrementAndGet());
//
//        // Create the BidPrjStc
//        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(bidPrjStc);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjStcMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStc in the database
//        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
//        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjStc() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStcRepository.findAll().size();
//        bidPrjStc.setId(count.incrementAndGet());
//
//        // Create the BidPrjStc
//        BidPrjStcDTO bidPrjStcDTO = bidPrjStcMapper.toDto(bidPrjStc);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjStcMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjStcDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjStc in the database
//        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
//        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjStc() throws Exception {
        // Initialize the database
        bidPrjStcRepository.saveAndFlush(bidPrjStc);

        int databaseSizeBeforeDelete = bidPrjStcRepository.findAll().size();

        // Delete the bidPrjStc
        restBidPrjStcMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjStc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjStc> bidPrjStcList = bidPrjStcRepository.findAll();
        assertThat(bidPrjStcList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
