package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjModify;
import gov.pcc.pwc.domain.BidPrjModifyPrimaryKey;
import gov.pcc.pwc.repository.BidPrjModifyRepository;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;
import gov.pcc.pwc.service.mapper.BidPrjModifyMapper;

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
 * Integration tests for the {@link BidPrjModifyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjModifyResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MDY_PRESS = "A";
    private static final String UPDATED_MDY_PRESS = "B";

    private static final String DEFAULT_OK_NO = "AAAAAAAAAA";
    private static final String UPDATED_OK_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MDY_RESON = "AA";
    private static final String UPDATED_MDY_RESON = "BB";

    private static final String DEFAULT_MDY_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_MDY_REMARK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CHG_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CHG_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OLD_ADD_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OLD_ADD_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OLD_SUBTRACT_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OLD_SUBTRACT_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OLD_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OLD_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ADD_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ADD_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SUBTRACT_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUBTRACT_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NEW_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NEW_AMT = new BigDecimal(2);

    private static final LocalDate DEFAULT_OLD_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OLD_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NEW_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEW_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DLYD_1 = 1;
    private static final Integer UPDATED_DLYD_1 = 2;

    private static final Integer DEFAULT_DLYD_2 = 1;
    private static final Integer UPDATED_DLYD_2 = 2;

    private static final Integer DEFAULT_DLYD_3 = 1;
    private static final Integer UPDATED_DLYD_3 = 2;

    private static final Integer DEFAULT_DLYD_4 = 1;
    private static final Integer UPDATED_DLYD_4 = 2;

    private static final Integer DEFAULT_DLYD_5 = 1;
    private static final Integer UPDATED_DLYD_5 = 2;

    private static final Integer DEFAULT_N_DAYS = 1;
    private static final Integer UPDATED_N_DAYS = 2;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_DSTS = "AAAAAAAAAA";
    private static final String UPDATED_DSTS = "BBBBBBBBBB";

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_MERGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MERGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MERGE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MERGE_NO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MERGECT_SUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_MERGECT_SUM = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK_1 = "AAAAAAAAAA";
    private static final String UPDATED_REMARK_1 = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK_2 = "AAAAAAAAAA";
    private static final String UPDATED_REMARK_2 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-modifies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjModifyRepository bidPrjModifyRepository;

    @Autowired
    private BidPrjModifyMapper bidPrjModifyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjModifyMockMvc;

    private BidPrjModify bidPrjModify;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjModify createEntity(EntityManager em) {
        BidPrjModify bidPrjModify = new BidPrjModify()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .chgDate(DEFAULT_CHG_DATE)
            .mdyPress(DEFAULT_MDY_PRESS)
            .okNo(DEFAULT_OK_NO)
            .mdyReson(DEFAULT_MDY_RESON)
            .mdyRemark(DEFAULT_MDY_REMARK)
            .chgRate(DEFAULT_CHG_RATE)
            .oldAddAmt(DEFAULT_OLD_ADD_AMT)
            .oldSubtractAmt(DEFAULT_OLD_SUBTRACT_AMT)
            .oldAmt(DEFAULT_OLD_AMT)
            .addAmt(DEFAULT_ADD_AMT)
            .subtractAmt(DEFAULT_SUBTRACT_AMT)
            .newAmt(DEFAULT_NEW_AMT)
            .oldDate(DEFAULT_OLD_DATE)
            .newDate(DEFAULT_NEW_DATE)
            .dlyd1(DEFAULT_DLYD_1)
            .dlyd2(DEFAULT_DLYD_2)
            .dlyd3(DEFAULT_DLYD_3)
            .dlyd4(DEFAULT_DLYD_4)
            .dlyd5(DEFAULT_DLYD_5)
            .nDays(DEFAULT_N_DAYS)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .dsts(DEFAULT_DSTS)
            .uIp(DEFAULT_U_IP)
            .mergeName(DEFAULT_MERGE_NAME)
            .mergeNo(DEFAULT_MERGE_NO)
            .mergectSum(DEFAULT_MERGECT_SUM)
            .remark(DEFAULT_REMARK)
            .remark1(DEFAULT_REMARK_1)
            .remark2(DEFAULT_REMARK_2);
        return bidPrjModify;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjModify createUpdatedEntity(EntityManager em) {
        BidPrjModify bidPrjModify = new BidPrjModify()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .chgDate(UPDATED_CHG_DATE)
            .mdyPress(UPDATED_MDY_PRESS)
            .okNo(UPDATED_OK_NO)
            .mdyReson(UPDATED_MDY_RESON)
            .mdyRemark(UPDATED_MDY_REMARK)
            .chgRate(UPDATED_CHG_RATE)
            .oldAddAmt(UPDATED_OLD_ADD_AMT)
            .oldSubtractAmt(UPDATED_OLD_SUBTRACT_AMT)
            .oldAmt(UPDATED_OLD_AMT)
            .addAmt(UPDATED_ADD_AMT)
            .subtractAmt(UPDATED_SUBTRACT_AMT)
            .newAmt(UPDATED_NEW_AMT)
            .oldDate(UPDATED_OLD_DATE)
            .newDate(UPDATED_NEW_DATE)
            .dlyd1(UPDATED_DLYD_1)
            .dlyd2(UPDATED_DLYD_2)
            .dlyd3(UPDATED_DLYD_3)
            .dlyd4(UPDATED_DLYD_4)
            .dlyd5(UPDATED_DLYD_5)
            .nDays(UPDATED_N_DAYS)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .uIp(UPDATED_U_IP)
            .mergeName(UPDATED_MERGE_NAME)
            .mergeNo(UPDATED_MERGE_NO)
            .mergectSum(UPDATED_MERGECT_SUM)
            .remark(UPDATED_REMARK)
            .remark1(UPDATED_REMARK_1)
            .remark2(UPDATED_REMARK_2);
        return bidPrjModify;
    }

    @BeforeEach
    public void initTest() {
        bidPrjModify = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjModify() throws Exception {
        int databaseSizeBeforeCreate = bidPrjModifyRepository.findAll().size();
        // Create the BidPrjModify
        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);
        restBidPrjModifyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjModify in the database
        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjModify testBidPrjModify = bidPrjModifyList.get(bidPrjModifyList.size() - 1);
        assertThat(testBidPrjModify.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjModify.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjModify.getChgDate()).isEqualTo(DEFAULT_CHG_DATE);
        assertThat(testBidPrjModify.getMdyPress()).isEqualTo(DEFAULT_MDY_PRESS);
        assertThat(testBidPrjModify.getOkNo()).isEqualTo(DEFAULT_OK_NO);
        assertThat(testBidPrjModify.getMdyReson()).isEqualTo(DEFAULT_MDY_RESON);
        assertThat(testBidPrjModify.getMdyRemark()).isEqualTo(DEFAULT_MDY_REMARK);
        assertThat(testBidPrjModify.getChgRate()).isEqualByComparingTo(DEFAULT_CHG_RATE);
        assertThat(testBidPrjModify.getOldAddAmt()).isEqualByComparingTo(DEFAULT_OLD_ADD_AMT);
        assertThat(testBidPrjModify.getOldSubtractAmt()).isEqualByComparingTo(DEFAULT_OLD_SUBTRACT_AMT);
        assertThat(testBidPrjModify.getOldAmt()).isEqualByComparingTo(DEFAULT_OLD_AMT);
        assertThat(testBidPrjModify.getAddAmt()).isEqualByComparingTo(DEFAULT_ADD_AMT);
        assertThat(testBidPrjModify.getSubtractAmt()).isEqualByComparingTo(DEFAULT_SUBTRACT_AMT);
        assertThat(testBidPrjModify.getNewAmt()).isEqualByComparingTo(DEFAULT_NEW_AMT);
        assertThat(testBidPrjModify.getOldDate()).isEqualTo(DEFAULT_OLD_DATE);
        assertThat(testBidPrjModify.getNewDate()).isEqualTo(DEFAULT_NEW_DATE);
        assertThat(testBidPrjModify.getDlyd1()).isEqualTo(DEFAULT_DLYD_1);
        assertThat(testBidPrjModify.getDlyd2()).isEqualTo(DEFAULT_DLYD_2);
        assertThat(testBidPrjModify.getDlyd3()).isEqualTo(DEFAULT_DLYD_3);
        assertThat(testBidPrjModify.getDlyd4()).isEqualTo(DEFAULT_DLYD_4);
        assertThat(testBidPrjModify.getDlyd5()).isEqualTo(DEFAULT_DLYD_5);
        assertThat(testBidPrjModify.getnDays()).isEqualTo(DEFAULT_N_DAYS);
        assertThat(testBidPrjModify.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjModify.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjModify.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjModify.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjModify.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjModify.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjModify.getMergeName()).isEqualTo(DEFAULT_MERGE_NAME);
        assertThat(testBidPrjModify.getMergeNo()).isEqualTo(DEFAULT_MERGE_NO);
        assertThat(testBidPrjModify.getMergectSum()).isEqualByComparingTo(DEFAULT_MERGECT_SUM);
        assertThat(testBidPrjModify.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBidPrjModify.getRemark1()).isEqualTo(DEFAULT_REMARK_1);
        assertThat(testBidPrjModify.getRemark2()).isEqualTo(DEFAULT_REMARK_2);
    }

//    @Test
//    @Transactional
//    void createBidPrjModifyWithExistingId() throws Exception {
//        // Create the BidPrjModify with an existing ID
//        bidPrjModify.setId(1L);
//        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);
//
//        int databaseSizeBeforeCreate = bidPrjModifyRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjModifyMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjModify in the database
//        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
//        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeCreate);
//    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjModifyRepository.findAll().size();
        // set the field null
        bidPrjModify.setWkut(null);

        // Create the BidPrjModify, which fails.
        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);

        restBidPrjModifyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjModifyRepository.findAll().size();
        // set the field null
        bidPrjModify.setPrjno(null);

        // Create the BidPrjModify, which fails.
        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);

        restBidPrjModifyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkChgDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjModifyRepository.findAll().size();
        // set the field null
        bidPrjModify.setChgDate(null);

        // Create the BidPrjModify, which fails.
        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);

        restBidPrjModifyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    void getAllBidPrjModifies() throws Exception {
//        // Initialize the database
//        bidPrjModifyRepository.saveAndFlush(bidPrjModify);
//
//        // Get all the bidPrjModifyList
//        restBidPrjModifyMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjModify.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].chgDate").value(hasItem(DEFAULT_CHG_DATE.toString())))
//            .andExpect(jsonPath("$.[*].mdyPress").value(hasItem(DEFAULT_MDY_PRESS)))
//            .andExpect(jsonPath("$.[*].okNo").value(hasItem(DEFAULT_OK_NO)))
//            .andExpect(jsonPath("$.[*].mdyReson").value(hasItem(DEFAULT_MDY_RESON)))
//            .andExpect(jsonPath("$.[*].mdyRemark").value(hasItem(DEFAULT_MDY_REMARK)))
//            .andExpect(jsonPath("$.[*].chgRate").value(hasItem(sameNumber(DEFAULT_CHG_RATE))))
//            .andExpect(jsonPath("$.[*].oldAddAmt").value(hasItem(sameNumber(DEFAULT_OLD_ADD_AMT))))
//            .andExpect(jsonPath("$.[*].oldSubtractAmt").value(hasItem(sameNumber(DEFAULT_OLD_SUBTRACT_AMT))))
//            .andExpect(jsonPath("$.[*].oldAmt").value(hasItem(sameNumber(DEFAULT_OLD_AMT))))
//            .andExpect(jsonPath("$.[*].addAmt").value(hasItem(sameNumber(DEFAULT_ADD_AMT))))
//            .andExpect(jsonPath("$.[*].subtractAmt").value(hasItem(sameNumber(DEFAULT_SUBTRACT_AMT))))
//            .andExpect(jsonPath("$.[*].newAmt").value(hasItem(sameNumber(DEFAULT_NEW_AMT))))
//            .andExpect(jsonPath("$.[*].oldDate").value(hasItem(DEFAULT_OLD_DATE.toString())))
//            .andExpect(jsonPath("$.[*].newDate").value(hasItem(DEFAULT_NEW_DATE.toString())))
//            .andExpect(jsonPath("$.[*].dlyd1").value(hasItem(DEFAULT_DLYD_1)))
//            .andExpect(jsonPath("$.[*].dlyd2").value(hasItem(DEFAULT_DLYD_2)))
//            .andExpect(jsonPath("$.[*].dlyd3").value(hasItem(DEFAULT_DLYD_3)))
//            .andExpect(jsonPath("$.[*].dlyd4").value(hasItem(DEFAULT_DLYD_4)))
//            .andExpect(jsonPath("$.[*].dlyd5").value(hasItem(DEFAULT_DLYD_5)))
//            .andExpect(jsonPath("$.[*].nDays").value(hasItem(DEFAULT_N_DAYS)))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].dsts").value(hasItem(DEFAULT_DSTS)))
//            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
//            .andExpect(jsonPath("$.[*].mergeName").value(hasItem(DEFAULT_MERGE_NAME)))
//            .andExpect(jsonPath("$.[*].mergeNo").value(hasItem(DEFAULT_MERGE_NO)))
//            .andExpect(jsonPath("$.[*].mergectSum").value(hasItem(sameNumber(DEFAULT_MERGECT_SUM))))
//            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
//            .andExpect(jsonPath("$.[*].remark1").value(hasItem(DEFAULT_REMARK_1)))
//            .andExpect(jsonPath("$.[*].remark2").value(hasItem(DEFAULT_REMARK_2)));
//    }

//    @Test
//    @Transactional
//    void getBidPrjModify() throws Exception {
//        // Initialize the database
//        bidPrjModifyRepository.saveAndFlush(bidPrjModify);
//
//        // Get the bidPrjModify
//        restBidPrjModifyMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidPrjModify.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjModify.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.chgDate").value(DEFAULT_CHG_DATE.toString()))
//            .andExpect(jsonPath("$.mdyPress").value(DEFAULT_MDY_PRESS))
//            .andExpect(jsonPath("$.okNo").value(DEFAULT_OK_NO))
//            .andExpect(jsonPath("$.mdyReson").value(DEFAULT_MDY_RESON))
//            .andExpect(jsonPath("$.mdyRemark").value(DEFAULT_MDY_REMARK))
//            .andExpect(jsonPath("$.chgRate").value(sameNumber(DEFAULT_CHG_RATE)))
//            .andExpect(jsonPath("$.oldAddAmt").value(sameNumber(DEFAULT_OLD_ADD_AMT)))
//            .andExpect(jsonPath("$.oldSubtractAmt").value(sameNumber(DEFAULT_OLD_SUBTRACT_AMT)))
//            .andExpect(jsonPath("$.oldAmt").value(sameNumber(DEFAULT_OLD_AMT)))
//            .andExpect(jsonPath("$.addAmt").value(sameNumber(DEFAULT_ADD_AMT)))
//            .andExpect(jsonPath("$.subtractAmt").value(sameNumber(DEFAULT_SUBTRACT_AMT)))
//            .andExpect(jsonPath("$.newAmt").value(sameNumber(DEFAULT_NEW_AMT)))
//            .andExpect(jsonPath("$.oldDate").value(DEFAULT_OLD_DATE.toString()))
//            .andExpect(jsonPath("$.newDate").value(DEFAULT_NEW_DATE.toString()))
//            .andExpect(jsonPath("$.dlyd1").value(DEFAULT_DLYD_1))
//            .andExpect(jsonPath("$.dlyd2").value(DEFAULT_DLYD_2))
//            .andExpect(jsonPath("$.dlyd3").value(DEFAULT_DLYD_3))
//            .andExpect(jsonPath("$.dlyd4").value(DEFAULT_DLYD_4))
//            .andExpect(jsonPath("$.dlyd5").value(DEFAULT_DLYD_5))
//            .andExpect(jsonPath("$.nDays").value(DEFAULT_N_DAYS))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.dsts").value(DEFAULT_DSTS))
//            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
//            .andExpect(jsonPath("$.mergeName").value(DEFAULT_MERGE_NAME))
//            .andExpect(jsonPath("$.mergeNo").value(DEFAULT_MERGE_NO))
//            .andExpect(jsonPath("$.mergectSum").value(sameNumber(DEFAULT_MERGECT_SUM)))
//            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
//            .andExpect(jsonPath("$.remark1").value(DEFAULT_REMARK_1))
//            .andExpect(jsonPath("$.remark2").value(DEFAULT_REMARK_2));
//    }

    @Test
    @Transactional
    void getNonExistingBidPrjModify() throws Exception {
        // Get the bidPrjModify
        restBidPrjModifyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjModify() throws Exception {
        // Initialize the database
        bidPrjModifyRepository.saveAndFlush(bidPrjModify);

        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();

        // Update the bidPrjModify
        BidPrjModify updatedBidPrjModify = bidPrjModifyRepository.findById(bidPrjModify.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjModify are not directly saved in db
        em.detach(updatedBidPrjModify);
        updatedBidPrjModify
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .chgDate(UPDATED_CHG_DATE)
            .mdyPress(UPDATED_MDY_PRESS)
            .okNo(UPDATED_OK_NO)
            .mdyReson(UPDATED_MDY_RESON)
            .mdyRemark(UPDATED_MDY_REMARK)
            .chgRate(UPDATED_CHG_RATE)
            .oldAddAmt(UPDATED_OLD_ADD_AMT)
            .oldSubtractAmt(UPDATED_OLD_SUBTRACT_AMT)
            .oldAmt(UPDATED_OLD_AMT)
            .addAmt(UPDATED_ADD_AMT)
            .subtractAmt(UPDATED_SUBTRACT_AMT)
            .newAmt(UPDATED_NEW_AMT)
            .oldDate(UPDATED_OLD_DATE)
            .newDate(UPDATED_NEW_DATE)
            .dlyd1(UPDATED_DLYD_1)
            .dlyd2(UPDATED_DLYD_2)
            .dlyd3(UPDATED_DLYD_3)
            .dlyd4(UPDATED_DLYD_4)
            .dlyd5(UPDATED_DLYD_5)
            .nDays(UPDATED_N_DAYS)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .uIp(UPDATED_U_IP)
            .mergeName(UPDATED_MERGE_NAME)
            .mergeNo(UPDATED_MERGE_NO)
            .mergectSum(UPDATED_MERGECT_SUM)
            .remark(UPDATED_REMARK)
            .remark1(UPDATED_REMARK_1)
            .remark2(UPDATED_REMARK_2);
        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(updatedBidPrjModify);

        restBidPrjModifyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjModifyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjModify in the database
        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
        BidPrjModify testBidPrjModify = bidPrjModifyList.get(bidPrjModifyList.size() - 1);
        assertThat(testBidPrjModify.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjModify.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjModify.getChgDate()).isEqualTo(UPDATED_CHG_DATE);
        assertThat(testBidPrjModify.getMdyPress()).isEqualTo(UPDATED_MDY_PRESS);
        assertThat(testBidPrjModify.getOkNo()).isEqualTo(UPDATED_OK_NO);
        assertThat(testBidPrjModify.getMdyReson()).isEqualTo(UPDATED_MDY_RESON);
        assertThat(testBidPrjModify.getMdyRemark()).isEqualTo(UPDATED_MDY_REMARK);
        assertThat(testBidPrjModify.getChgRate()).isEqualByComparingTo(UPDATED_CHG_RATE);
        assertThat(testBidPrjModify.getOldAddAmt()).isEqualByComparingTo(UPDATED_OLD_ADD_AMT);
        assertThat(testBidPrjModify.getOldSubtractAmt()).isEqualByComparingTo(UPDATED_OLD_SUBTRACT_AMT);
        assertThat(testBidPrjModify.getOldAmt()).isEqualByComparingTo(UPDATED_OLD_AMT);
        assertThat(testBidPrjModify.getAddAmt()).isEqualByComparingTo(UPDATED_ADD_AMT);
        assertThat(testBidPrjModify.getSubtractAmt()).isEqualByComparingTo(UPDATED_SUBTRACT_AMT);
        assertThat(testBidPrjModify.getNewAmt()).isEqualByComparingTo(UPDATED_NEW_AMT);
        assertThat(testBidPrjModify.getOldDate()).isEqualTo(UPDATED_OLD_DATE);
        assertThat(testBidPrjModify.getNewDate()).isEqualTo(UPDATED_NEW_DATE);
        assertThat(testBidPrjModify.getDlyd1()).isEqualTo(UPDATED_DLYD_1);
        assertThat(testBidPrjModify.getDlyd2()).isEqualTo(UPDATED_DLYD_2);
        assertThat(testBidPrjModify.getDlyd3()).isEqualTo(UPDATED_DLYD_3);
        assertThat(testBidPrjModify.getDlyd4()).isEqualTo(UPDATED_DLYD_4);
        assertThat(testBidPrjModify.getDlyd5()).isEqualTo(UPDATED_DLYD_5);
        assertThat(testBidPrjModify.getnDays()).isEqualTo(UPDATED_N_DAYS);
        assertThat(testBidPrjModify.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjModify.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjModify.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjModify.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjModify.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjModify.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjModify.getMergeName()).isEqualTo(UPDATED_MERGE_NAME);
        assertThat(testBidPrjModify.getMergeNo()).isEqualTo(UPDATED_MERGE_NO);
        assertThat(testBidPrjModify.getMergectSum()).isEqualByComparingTo(UPDATED_MERGECT_SUM);
        assertThat(testBidPrjModify.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjModify.getRemark1()).isEqualTo(UPDATED_REMARK_1);
        assertThat(testBidPrjModify.getRemark2()).isEqualTo(UPDATED_REMARK_2);
    }

//    @Test
//    @Transactional
//    void putNonExistingBidPrjModify() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();
//        bidPrjModify.setId(count.incrementAndGet());
//
//        // Create the BidPrjModify
//        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjModifyMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjModifyDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjModify in the database
//        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
//        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithIdMismatchBidPrjModify() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();
//        bidPrjModify.setId(count.incrementAndGet());
//
//        // Create the BidPrjModify
//        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjModifyMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjModify in the database
//        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
//        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithMissingIdPathParamBidPrjModify() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();
//        bidPrjModify.setId(count.incrementAndGet());
//
//        // Create the BidPrjModify
//        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjModifyMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjModify in the database
//        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
//        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void partialUpdateBidPrjModifyWithPatch() throws Exception {
        // Initialize the database
        bidPrjModifyRepository.saveAndFlush(bidPrjModify);

        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();

        // Update the bidPrjModify using partial update
        BidPrjModify partialUpdatedBidPrjModify = new BidPrjModify();
        partialUpdatedBidPrjModify.setId(bidPrjModify.getId());

        partialUpdatedBidPrjModify
            .okNo(UPDATED_OK_NO)
            .mdyReson(UPDATED_MDY_RESON)
            .oldAddAmt(UPDATED_OLD_ADD_AMT)
            .oldSubtractAmt(UPDATED_OLD_SUBTRACT_AMT)
            .oldAmt(UPDATED_OLD_AMT)
            .subtractAmt(UPDATED_SUBTRACT_AMT)
            .newAmt(UPDATED_NEW_AMT)
            .newDate(UPDATED_NEW_DATE)
            .dlyd2(UPDATED_DLYD_2)
            .dlyd5(UPDATED_DLYD_5)
            .createDate(UPDATED_CREATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .mergeName(UPDATED_MERGE_NAME)
            .mergeNo(UPDATED_MERGE_NO)
            .remark(UPDATED_REMARK);

        restBidPrjModifyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjModify.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjModify))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjModify in the database
        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
        BidPrjModify testBidPrjModify = bidPrjModifyList.get(bidPrjModifyList.size() - 1);
        assertThat(testBidPrjModify.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjModify.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjModify.getChgDate()).isEqualTo(DEFAULT_CHG_DATE);
        assertThat(testBidPrjModify.getMdyPress()).isEqualTo(DEFAULT_MDY_PRESS);
        assertThat(testBidPrjModify.getOkNo()).isEqualTo(UPDATED_OK_NO);
        assertThat(testBidPrjModify.getMdyReson()).isEqualTo(UPDATED_MDY_RESON);
        assertThat(testBidPrjModify.getMdyRemark()).isEqualTo(DEFAULT_MDY_REMARK);
        assertThat(testBidPrjModify.getChgRate()).isEqualByComparingTo(DEFAULT_CHG_RATE);
        assertThat(testBidPrjModify.getOldAddAmt()).isEqualByComparingTo(UPDATED_OLD_ADD_AMT);
        assertThat(testBidPrjModify.getOldSubtractAmt()).isEqualByComparingTo(UPDATED_OLD_SUBTRACT_AMT);
        assertThat(testBidPrjModify.getOldAmt()).isEqualByComparingTo(UPDATED_OLD_AMT);
        assertThat(testBidPrjModify.getAddAmt()).isEqualByComparingTo(DEFAULT_ADD_AMT);
        assertThat(testBidPrjModify.getSubtractAmt()).isEqualByComparingTo(UPDATED_SUBTRACT_AMT);
        assertThat(testBidPrjModify.getNewAmt()).isEqualByComparingTo(UPDATED_NEW_AMT);
        assertThat(testBidPrjModify.getOldDate()).isEqualTo(DEFAULT_OLD_DATE);
        assertThat(testBidPrjModify.getNewDate()).isEqualTo(UPDATED_NEW_DATE);
        assertThat(testBidPrjModify.getDlyd1()).isEqualTo(DEFAULT_DLYD_1);
        assertThat(testBidPrjModify.getDlyd2()).isEqualTo(UPDATED_DLYD_2);
        assertThat(testBidPrjModify.getDlyd3()).isEqualTo(DEFAULT_DLYD_3);
        assertThat(testBidPrjModify.getDlyd4()).isEqualTo(DEFAULT_DLYD_4);
        assertThat(testBidPrjModify.getDlyd5()).isEqualTo(UPDATED_DLYD_5);
        assertThat(testBidPrjModify.getnDays()).isEqualTo(DEFAULT_N_DAYS);
        assertThat(testBidPrjModify.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjModify.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjModify.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjModify.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjModify.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjModify.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjModify.getMergeName()).isEqualTo(UPDATED_MERGE_NAME);
        assertThat(testBidPrjModify.getMergeNo()).isEqualTo(UPDATED_MERGE_NO);
        assertThat(testBidPrjModify.getMergectSum()).isEqualByComparingTo(DEFAULT_MERGECT_SUM);
        assertThat(testBidPrjModify.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjModify.getRemark1()).isEqualTo(DEFAULT_REMARK_1);
        assertThat(testBidPrjModify.getRemark2()).isEqualTo(DEFAULT_REMARK_2);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjModifyWithPatch() throws Exception {
        // Initialize the database
        bidPrjModifyRepository.saveAndFlush(bidPrjModify);

        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();

        // Update the bidPrjModify using partial update
        BidPrjModify partialUpdatedBidPrjModify = new BidPrjModify();
        partialUpdatedBidPrjModify.setId(bidPrjModify.getId());

        partialUpdatedBidPrjModify
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .chgDate(UPDATED_CHG_DATE)
            .mdyPress(UPDATED_MDY_PRESS)
            .okNo(UPDATED_OK_NO)
            .mdyReson(UPDATED_MDY_RESON)
            .mdyRemark(UPDATED_MDY_REMARK)
            .chgRate(UPDATED_CHG_RATE)
            .oldAddAmt(UPDATED_OLD_ADD_AMT)
            .oldSubtractAmt(UPDATED_OLD_SUBTRACT_AMT)
            .oldAmt(UPDATED_OLD_AMT)
            .addAmt(UPDATED_ADD_AMT)
            .subtractAmt(UPDATED_SUBTRACT_AMT)
            .newAmt(UPDATED_NEW_AMT)
            .oldDate(UPDATED_OLD_DATE)
            .newDate(UPDATED_NEW_DATE)
            .dlyd1(UPDATED_DLYD_1)
            .dlyd2(UPDATED_DLYD_2)
            .dlyd3(UPDATED_DLYD_3)
            .dlyd4(UPDATED_DLYD_4)
            .dlyd5(UPDATED_DLYD_5)
            .nDays(UPDATED_N_DAYS)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .uIp(UPDATED_U_IP)
            .mergeName(UPDATED_MERGE_NAME)
            .mergeNo(UPDATED_MERGE_NO)
            .mergectSum(UPDATED_MERGECT_SUM)
            .remark(UPDATED_REMARK)
            .remark1(UPDATED_REMARK_1)
            .remark2(UPDATED_REMARK_2);

        restBidPrjModifyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjModify.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjModify))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjModify in the database
        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
        BidPrjModify testBidPrjModify = bidPrjModifyList.get(bidPrjModifyList.size() - 1);
        assertThat(testBidPrjModify.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjModify.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjModify.getChgDate()).isEqualTo(UPDATED_CHG_DATE);
        assertThat(testBidPrjModify.getMdyPress()).isEqualTo(UPDATED_MDY_PRESS);
        assertThat(testBidPrjModify.getOkNo()).isEqualTo(UPDATED_OK_NO);
        assertThat(testBidPrjModify.getMdyReson()).isEqualTo(UPDATED_MDY_RESON);
        assertThat(testBidPrjModify.getMdyRemark()).isEqualTo(UPDATED_MDY_REMARK);
        assertThat(testBidPrjModify.getChgRate()).isEqualByComparingTo(UPDATED_CHG_RATE);
        assertThat(testBidPrjModify.getOldAddAmt()).isEqualByComparingTo(UPDATED_OLD_ADD_AMT);
        assertThat(testBidPrjModify.getOldSubtractAmt()).isEqualByComparingTo(UPDATED_OLD_SUBTRACT_AMT);
        assertThat(testBidPrjModify.getOldAmt()).isEqualByComparingTo(UPDATED_OLD_AMT);
        assertThat(testBidPrjModify.getAddAmt()).isEqualByComparingTo(UPDATED_ADD_AMT);
        assertThat(testBidPrjModify.getSubtractAmt()).isEqualByComparingTo(UPDATED_SUBTRACT_AMT);
        assertThat(testBidPrjModify.getNewAmt()).isEqualByComparingTo(UPDATED_NEW_AMT);
        assertThat(testBidPrjModify.getOldDate()).isEqualTo(UPDATED_OLD_DATE);
        assertThat(testBidPrjModify.getNewDate()).isEqualTo(UPDATED_NEW_DATE);
        assertThat(testBidPrjModify.getDlyd1()).isEqualTo(UPDATED_DLYD_1);
        assertThat(testBidPrjModify.getDlyd2()).isEqualTo(UPDATED_DLYD_2);
        assertThat(testBidPrjModify.getDlyd3()).isEqualTo(UPDATED_DLYD_3);
        assertThat(testBidPrjModify.getDlyd4()).isEqualTo(UPDATED_DLYD_4);
        assertThat(testBidPrjModify.getDlyd5()).isEqualTo(UPDATED_DLYD_5);
        assertThat(testBidPrjModify.getnDays()).isEqualTo(UPDATED_N_DAYS);
        assertThat(testBidPrjModify.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjModify.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjModify.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjModify.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjModify.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjModify.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjModify.getMergeName()).isEqualTo(UPDATED_MERGE_NAME);
        assertThat(testBidPrjModify.getMergeNo()).isEqualTo(UPDATED_MERGE_NO);
        assertThat(testBidPrjModify.getMergectSum()).isEqualByComparingTo(UPDATED_MERGECT_SUM);
        assertThat(testBidPrjModify.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjModify.getRemark1()).isEqualTo(UPDATED_REMARK_1);
        assertThat(testBidPrjModify.getRemark2()).isEqualTo(UPDATED_REMARK_2);
    }

//    @Test
//    @Transactional
//    void patchNonExistingBidPrjModify() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();
//        bidPrjModify.setId(count.incrementAndGet());
//
//        // Create the BidPrjModify
//        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjModifyMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjModifyDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjModify in the database
//        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
//        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithIdMismatchBidPrjModify() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();
//        bidPrjModify.setId(count.incrementAndGet());
//
//        // Create the BidPrjModify
//        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjModifyMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjModify in the database
//        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
//        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamBidPrjModify() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjModifyRepository.findAll().size();
//        bidPrjModify.setId(count.incrementAndGet());
//
//        // Create the BidPrjModify
//        BidPrjModifyDTO bidPrjModifyDTO = bidPrjModifyMapper.toDto(bidPrjModify);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjModifyMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjModifyDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjModify in the database
//        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
//        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void deleteBidPrjModify() throws Exception {
        // Initialize the database
        bidPrjModifyRepository.saveAndFlush(bidPrjModify);

        int databaseSizeBeforeDelete = bidPrjModifyRepository.findAll().size();

        // Delete the bidPrjModify
        restBidPrjModifyMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjModify.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjModify> bidPrjModifyList = bidPrjModifyRepository.findAll();
        assertThat(bidPrjModifyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
