package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjDelete;
import gov.pcc.pwc.repository.BidPrjDeleteRepository;
import gov.pcc.pwc.service.dto.BidPrjDeleteDTO;
import gov.pcc.pwc.service.mapper.BidPrjDeleteMapper;
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
 * Integration tests for the {@link BidPrjDeleteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjDeleteResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PLN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PLN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PLN_NO = "AAAAAAAAAA";
    private static final String UPDATED_PLN_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_CODE = "AA";
    private static final String UPDATED_TYPE_CODE = "BB";

    private static final BigDecimal DEFAULT_TOT_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOT_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BDGT_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_BDGT_1 = new BigDecimal(2);

    private static final LocalDate DEFAULT_OP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_AOP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AOP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PCT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PCT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_CT_SUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_CT_SUM = new BigDecimal(2);

    private static final String DEFAULT_CNTID = "AAAAAAAAAA";
    private static final String UPDATED_CNTID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PAYS = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAYS = new BigDecimal(2);

    private static final String DEFAULT_CTMETH = "A";
    private static final String UPDATED_CTMETH = "B";

    private static final String DEFAULT_ACTMTH = "AA";
    private static final String UPDATED_ACTMTH = "BB";

    private static final String DEFAULT_W_KIND = "A";
    private static final String UPDATED_W_KIND = "B";

    private static final Integer DEFAULT_W_DAYS = 1;
    private static final Integer UPDATED_W_DAYS = 2;

    private static final String DEFAULT_PLACE_CODE = "AAAA";
    private static final String UPDATED_PLACE_CODE = "BBBB";

    private static final String DEFAULT_MAN = "AAAAAAAAAA";
    private static final String UPDATED_MAN = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

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

    private static final String DEFAULT_LNK_NO_2 = "AAAAAAAAAA";
    private static final String UPDATED_LNK_NO_2 = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FCOST_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_FCOST_2 = new BigDecimal(2);

    private static final String DEFAULT_LNK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LNK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LNK_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_LNK_CODE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_LNK_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_LNK_CODE_3 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DEL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DEL_RESN = "AAAAAAAAAA";
    private static final String UPDATED_DEL_RESN = "BBBBBBBBBB";

    private static final String DEFAULT_UNIQUEKEY = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUEKEY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-deletes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjDeleteRepository bidPrjDeleteRepository;

    @Autowired
    private BidPrjDeleteMapper bidPrjDeleteMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjDeleteMockMvc;

    private BidPrjDelete bidPrjDelete;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjDelete createEntity(EntityManager em) {
        BidPrjDelete bidPrjDelete = new BidPrjDelete()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .name(DEFAULT_NAME)
            .plnCode(DEFAULT_PLN_CODE)
            .plnNo(DEFAULT_PLN_NO)
            .typeCode(DEFAULT_TYPE_CODE)
            .totAmt(DEFAULT_TOT_AMT)
            .bdgt1(DEFAULT_BDGT_1)
            .opDate(DEFAULT_OP_DATE)
            .aopDate(DEFAULT_AOP_DATE)
            .pctDate(DEFAULT_PCT_DATE)
            .actDate(DEFAULT_ACT_DATE)
            .ctSum(DEFAULT_CT_SUM)
            .cntid(DEFAULT_CNTID)
            .pays(DEFAULT_PAYS)
            .ctmeth(DEFAULT_CTMETH)
            .actmth(DEFAULT_ACTMTH)
            .wKind(DEFAULT_W_KIND)
            .wDays(DEFAULT_W_DAYS)
            .placeCode(DEFAULT_PLACE_CODE)
            .man(DEFAULT_MAN)
            .tel(DEFAULT_TEL)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .uIp(DEFAULT_U_IP)
            .lnkNo2(DEFAULT_LNK_NO_2)
            .fcost2(DEFAULT_FCOST_2)
            .lnkCode(DEFAULT_LNK_CODE)
            .lnkCode2(DEFAULT_LNK_CODE_2)
            .lnkCode3(DEFAULT_LNK_CODE_3)
            .delDate(DEFAULT_DEL_DATE)
            .delResn(DEFAULT_DEL_RESN)
            .uniquekey(DEFAULT_UNIQUEKEY);
        return bidPrjDelete;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjDelete createUpdatedEntity(EntityManager em) {
        BidPrjDelete bidPrjDelete = new BidPrjDelete()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .name(UPDATED_NAME)
            .plnCode(UPDATED_PLN_CODE)
            .plnNo(UPDATED_PLN_NO)
            .typeCode(UPDATED_TYPE_CODE)
            .totAmt(UPDATED_TOT_AMT)
            .bdgt1(UPDATED_BDGT_1)
            .opDate(UPDATED_OP_DATE)
            .aopDate(UPDATED_AOP_DATE)
            .pctDate(UPDATED_PCT_DATE)
            .actDate(UPDATED_ACT_DATE)
            .ctSum(UPDATED_CT_SUM)
            .cntid(UPDATED_CNTID)
            .pays(UPDATED_PAYS)
            .ctmeth(UPDATED_CTMETH)
            .actmth(UPDATED_ACTMTH)
            .wKind(UPDATED_W_KIND)
            .wDays(UPDATED_W_DAYS)
            .placeCode(UPDATED_PLACE_CODE)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .lnkNo2(UPDATED_LNK_NO_2)
            .fcost2(UPDATED_FCOST_2)
            .lnkCode(UPDATED_LNK_CODE)
            .lnkCode2(UPDATED_LNK_CODE_2)
            .lnkCode3(UPDATED_LNK_CODE_3)
            .delDate(UPDATED_DEL_DATE)
            .delResn(UPDATED_DEL_RESN)
            .uniquekey(UPDATED_UNIQUEKEY);
        return bidPrjDelete;
    }

    @BeforeEach
    public void initTest() {
        bidPrjDelete = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjDelete() throws Exception {
        int databaseSizeBeforeCreate = bidPrjDeleteRepository.findAll().size();
        // Create the BidPrjDelete
        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);
        restBidPrjDeleteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjDelete in the database
        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjDelete testBidPrjDelete = bidPrjDeleteList.get(bidPrjDeleteList.size() - 1);
        assertThat(testBidPrjDelete.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjDelete.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjDelete.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBidPrjDelete.getPlnCode()).isEqualTo(DEFAULT_PLN_CODE);
        assertThat(testBidPrjDelete.getPlnNo()).isEqualTo(DEFAULT_PLN_NO);
        assertThat(testBidPrjDelete.getTypeCode()).isEqualTo(DEFAULT_TYPE_CODE);
        assertThat(testBidPrjDelete.getTotAmt()).isEqualByComparingTo(DEFAULT_TOT_AMT);
        assertThat(testBidPrjDelete.getBdgt1()).isEqualByComparingTo(DEFAULT_BDGT_1);
        assertThat(testBidPrjDelete.getOpDate()).isEqualTo(DEFAULT_OP_DATE);
        assertThat(testBidPrjDelete.getAopDate()).isEqualTo(DEFAULT_AOP_DATE);
        assertThat(testBidPrjDelete.getPctDate()).isEqualTo(DEFAULT_PCT_DATE);
        assertThat(testBidPrjDelete.getActDate()).isEqualTo(DEFAULT_ACT_DATE);
        assertThat(testBidPrjDelete.getCtSum()).isEqualByComparingTo(DEFAULT_CT_SUM);
        assertThat(testBidPrjDelete.getCntid()).isEqualTo(DEFAULT_CNTID);
        assertThat(testBidPrjDelete.getPays()).isEqualByComparingTo(DEFAULT_PAYS);
        assertThat(testBidPrjDelete.getCtmeth()).isEqualTo(DEFAULT_CTMETH);
        assertThat(testBidPrjDelete.getActmth()).isEqualTo(DEFAULT_ACTMTH);
        assertThat(testBidPrjDelete.getwKind()).isEqualTo(DEFAULT_W_KIND);
        assertThat(testBidPrjDelete.getwDays()).isEqualTo(DEFAULT_W_DAYS);
        assertThat(testBidPrjDelete.getPlaceCode()).isEqualTo(DEFAULT_PLACE_CODE);
        assertThat(testBidPrjDelete.getMan()).isEqualTo(DEFAULT_MAN);
        assertThat(testBidPrjDelete.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testBidPrjDelete.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjDelete.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjDelete.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjDelete.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjDelete.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjDelete.getLnkNo2()).isEqualTo(DEFAULT_LNK_NO_2);
        assertThat(testBidPrjDelete.getFcost2()).isEqualByComparingTo(DEFAULT_FCOST_2);
        assertThat(testBidPrjDelete.getLnkCode()).isEqualTo(DEFAULT_LNK_CODE);
        assertThat(testBidPrjDelete.getLnkCode2()).isEqualTo(DEFAULT_LNK_CODE_2);
        assertThat(testBidPrjDelete.getLnkCode3()).isEqualTo(DEFAULT_LNK_CODE_3);
        assertThat(testBidPrjDelete.getDelDate()).isEqualTo(DEFAULT_DEL_DATE);
        assertThat(testBidPrjDelete.getDelResn()).isEqualTo(DEFAULT_DEL_RESN);
        assertThat(testBidPrjDelete.getUniquekey()).isEqualTo(DEFAULT_UNIQUEKEY);
    }

//    @Test
//    @Transactional
//    void createBidPrjDeleteWithExistingId() throws Exception {
//        // Create the BidPrjDelete with an existing ID
//        bidPrjDelete.setId(1L);
//        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);
//
//        int databaseSizeBeforeCreate = bidPrjDeleteRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjDeleteMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDelete in the database
//        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
//        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeCreate);
//    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjDeleteRepository.findAll().size();
        // set the field null
        bidPrjDelete.setWkut(null);

        // Create the BidPrjDelete, which fails.
        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);

        restBidPrjDeleteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjDeleteRepository.findAll().size();
        // set the field null
        bidPrjDelete.setPrjno(null);

        // Create the BidPrjDelete, which fails.
        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);

        restBidPrjDeleteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    void getAllBidPrjDeletes() throws Exception {
//        // Initialize the database
//        bidPrjDeleteRepository.saveAndFlush(bidPrjDelete);
//
//        // Get all the bidPrjDeleteList
//        restBidPrjDeleteMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjDelete.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
//            .andExpect(jsonPath("$.[*].plnCode").value(hasItem(DEFAULT_PLN_CODE)))
//            .andExpect(jsonPath("$.[*].plnNo").value(hasItem(DEFAULT_PLN_NO)))
//            .andExpect(jsonPath("$.[*].typeCode").value(hasItem(DEFAULT_TYPE_CODE)))
//            .andExpect(jsonPath("$.[*].totAmt").value(hasItem(sameNumber(DEFAULT_TOT_AMT))))
//            .andExpect(jsonPath("$.[*].bdgt1").value(hasItem(sameNumber(DEFAULT_BDGT_1))))
//            .andExpect(jsonPath("$.[*].opDate").value(hasItem(DEFAULT_OP_DATE.toString())))
//            .andExpect(jsonPath("$.[*].aopDate").value(hasItem(DEFAULT_AOP_DATE.toString())))
//            .andExpect(jsonPath("$.[*].pctDate").value(hasItem(DEFAULT_PCT_DATE.toString())))
//            .andExpect(jsonPath("$.[*].actDate").value(hasItem(DEFAULT_ACT_DATE.toString())))
//            .andExpect(jsonPath("$.[*].ctSum").value(hasItem(sameNumber(DEFAULT_CT_SUM))))
//            .andExpect(jsonPath("$.[*].cntid").value(hasItem(DEFAULT_CNTID)))
//            .andExpect(jsonPath("$.[*].pays").value(hasItem(sameNumber(DEFAULT_PAYS))))
//            .andExpect(jsonPath("$.[*].ctmeth").value(hasItem(DEFAULT_CTMETH)))
//            .andExpect(jsonPath("$.[*].actmth").value(hasItem(DEFAULT_ACTMTH)))
//            .andExpect(jsonPath("$.[*].wKind").value(hasItem(DEFAULT_W_KIND)))
//            .andExpect(jsonPath("$.[*].wDays").value(hasItem(DEFAULT_W_DAYS)))
//            .andExpect(jsonPath("$.[*].placeCode").value(hasItem(DEFAULT_PLACE_CODE)))
//            .andExpect(jsonPath("$.[*].man").value(hasItem(DEFAULT_MAN)))
//            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
//            .andExpect(jsonPath("$.[*].lnkNo2").value(hasItem(DEFAULT_LNK_NO_2)))
//            .andExpect(jsonPath("$.[*].fcost2").value(hasItem(sameNumber(DEFAULT_FCOST_2))))
//            .andExpect(jsonPath("$.[*].lnkCode").value(hasItem(DEFAULT_LNK_CODE)))
//            .andExpect(jsonPath("$.[*].lnkCode2").value(hasItem(DEFAULT_LNK_CODE_2)))
//            .andExpect(jsonPath("$.[*].lnkCode3").value(hasItem(DEFAULT_LNK_CODE_3)))
//            .andExpect(jsonPath("$.[*].delDate").value(hasItem(DEFAULT_DEL_DATE.toString())))
//            .andExpect(jsonPath("$.[*].delResn").value(hasItem(DEFAULT_DEL_RESN)))
//            .andExpect(jsonPath("$.[*].uniquekey").value(hasItem(DEFAULT_UNIQUEKEY)));
//    }

//    @Test
//    @Transactional
//    void getBidPrjDelete() throws Exception {
//        // Initialize the database
//        bidPrjDeleteRepository.saveAndFlush(bidPrjDelete);
//
//        // Get the bidPrjDelete
//        restBidPrjDeleteMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidPrjDelete.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjDelete.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
//            .andExpect(jsonPath("$.plnCode").value(DEFAULT_PLN_CODE))
//            .andExpect(jsonPath("$.plnNo").value(DEFAULT_PLN_NO))
//            .andExpect(jsonPath("$.typeCode").value(DEFAULT_TYPE_CODE))
//            .andExpect(jsonPath("$.totAmt").value(sameNumber(DEFAULT_TOT_AMT)))
//            .andExpect(jsonPath("$.bdgt1").value(sameNumber(DEFAULT_BDGT_1)))
//            .andExpect(jsonPath("$.opDate").value(DEFAULT_OP_DATE.toString()))
//            .andExpect(jsonPath("$.aopDate").value(DEFAULT_AOP_DATE.toString()))
//            .andExpect(jsonPath("$.pctDate").value(DEFAULT_PCT_DATE.toString()))
//            .andExpect(jsonPath("$.actDate").value(DEFAULT_ACT_DATE.toString()))
//            .andExpect(jsonPath("$.ctSum").value(sameNumber(DEFAULT_CT_SUM)))
//            .andExpect(jsonPath("$.cntid").value(DEFAULT_CNTID))
//            .andExpect(jsonPath("$.pays").value(sameNumber(DEFAULT_PAYS)))
//            .andExpect(jsonPath("$.ctmeth").value(DEFAULT_CTMETH))
//            .andExpect(jsonPath("$.actmth").value(DEFAULT_ACTMTH))
//            .andExpect(jsonPath("$.wKind").value(DEFAULT_W_KIND))
//            .andExpect(jsonPath("$.wDays").value(DEFAULT_W_DAYS))
//            .andExpect(jsonPath("$.placeCode").value(DEFAULT_PLACE_CODE))
//            .andExpect(jsonPath("$.man").value(DEFAULT_MAN))
//            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
//            .andExpect(jsonPath("$.lnkNo2").value(DEFAULT_LNK_NO_2))
//            .andExpect(jsonPath("$.fcost2").value(sameNumber(DEFAULT_FCOST_2)))
//            .andExpect(jsonPath("$.lnkCode").value(DEFAULT_LNK_CODE))
//            .andExpect(jsonPath("$.lnkCode2").value(DEFAULT_LNK_CODE_2))
//            .andExpect(jsonPath("$.lnkCode3").value(DEFAULT_LNK_CODE_3))
//            .andExpect(jsonPath("$.delDate").value(DEFAULT_DEL_DATE.toString()))
//            .andExpect(jsonPath("$.delResn").value(DEFAULT_DEL_RESN))
//            .andExpect(jsonPath("$.uniquekey").value(DEFAULT_UNIQUEKEY));
//    }

    @Test
    @Transactional
    void getNonExistingBidPrjDelete() throws Exception {
        // Get the bidPrjDelete
        restBidPrjDeleteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjDelete() throws Exception {
        // Initialize the database
        bidPrjDeleteRepository.saveAndFlush(bidPrjDelete);

        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();

        // Update the bidPrjDelete
        BidPrjDelete updatedBidPrjDelete = bidPrjDeleteRepository.findById(bidPrjDelete.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjDelete are not directly saved in db
        em.detach(updatedBidPrjDelete);
        updatedBidPrjDelete
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .name(UPDATED_NAME)
            .plnCode(UPDATED_PLN_CODE)
            .plnNo(UPDATED_PLN_NO)
            .typeCode(UPDATED_TYPE_CODE)
            .totAmt(UPDATED_TOT_AMT)
            .bdgt1(UPDATED_BDGT_1)
            .opDate(UPDATED_OP_DATE)
            .aopDate(UPDATED_AOP_DATE)
            .pctDate(UPDATED_PCT_DATE)
            .actDate(UPDATED_ACT_DATE)
            .ctSum(UPDATED_CT_SUM)
            .cntid(UPDATED_CNTID)
            .pays(UPDATED_PAYS)
            .ctmeth(UPDATED_CTMETH)
            .actmth(UPDATED_ACTMTH)
            .wKind(UPDATED_W_KIND)
            .wDays(UPDATED_W_DAYS)
            .placeCode(UPDATED_PLACE_CODE)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .lnkNo2(UPDATED_LNK_NO_2)
            .fcost2(UPDATED_FCOST_2)
            .lnkCode(UPDATED_LNK_CODE)
            .lnkCode2(UPDATED_LNK_CODE_2)
            .lnkCode3(UPDATED_LNK_CODE_3)
            .delDate(UPDATED_DEL_DATE)
            .delResn(UPDATED_DEL_RESN)
            .uniquekey(UPDATED_UNIQUEKEY);
        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(updatedBidPrjDelete);

        restBidPrjDeleteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjDeleteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjDelete in the database
        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
        BidPrjDelete testBidPrjDelete = bidPrjDeleteList.get(bidPrjDeleteList.size() - 1);
        assertThat(testBidPrjDelete.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjDelete.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjDelete.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBidPrjDelete.getPlnCode()).isEqualTo(UPDATED_PLN_CODE);
        assertThat(testBidPrjDelete.getPlnNo()).isEqualTo(UPDATED_PLN_NO);
        assertThat(testBidPrjDelete.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testBidPrjDelete.getTotAmt()).isEqualByComparingTo(UPDATED_TOT_AMT);
        assertThat(testBidPrjDelete.getBdgt1()).isEqualByComparingTo(UPDATED_BDGT_1);
        assertThat(testBidPrjDelete.getOpDate()).isEqualTo(UPDATED_OP_DATE);
        assertThat(testBidPrjDelete.getAopDate()).isEqualTo(UPDATED_AOP_DATE);
        assertThat(testBidPrjDelete.getPctDate()).isEqualTo(UPDATED_PCT_DATE);
        assertThat(testBidPrjDelete.getActDate()).isEqualTo(UPDATED_ACT_DATE);
        assertThat(testBidPrjDelete.getCtSum()).isEqualByComparingTo(UPDATED_CT_SUM);
        assertThat(testBidPrjDelete.getCntid()).isEqualTo(UPDATED_CNTID);
        assertThat(testBidPrjDelete.getPays()).isEqualByComparingTo(UPDATED_PAYS);
        assertThat(testBidPrjDelete.getCtmeth()).isEqualTo(UPDATED_CTMETH);
        assertThat(testBidPrjDelete.getActmth()).isEqualTo(UPDATED_ACTMTH);
        assertThat(testBidPrjDelete.getwKind()).isEqualTo(UPDATED_W_KIND);
        assertThat(testBidPrjDelete.getwDays()).isEqualTo(UPDATED_W_DAYS);
        assertThat(testBidPrjDelete.getPlaceCode()).isEqualTo(UPDATED_PLACE_CODE);
        assertThat(testBidPrjDelete.getMan()).isEqualTo(UPDATED_MAN);
        assertThat(testBidPrjDelete.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testBidPrjDelete.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjDelete.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjDelete.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjDelete.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjDelete.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjDelete.getLnkNo2()).isEqualTo(UPDATED_LNK_NO_2);
        assertThat(testBidPrjDelete.getFcost2()).isEqualByComparingTo(UPDATED_FCOST_2);
        assertThat(testBidPrjDelete.getLnkCode()).isEqualTo(UPDATED_LNK_CODE);
        assertThat(testBidPrjDelete.getLnkCode2()).isEqualTo(UPDATED_LNK_CODE_2);
        assertThat(testBidPrjDelete.getLnkCode3()).isEqualTo(UPDATED_LNK_CODE_3);
        assertThat(testBidPrjDelete.getDelDate()).isEqualTo(UPDATED_DEL_DATE);
        assertThat(testBidPrjDelete.getDelResn()).isEqualTo(UPDATED_DEL_RESN);
        assertThat(testBidPrjDelete.getUniquekey()).isEqualTo(UPDATED_UNIQUEKEY);
    }

//    @Test
//    @Transactional
//    void putNonExistingBidPrjDelete() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();
//        bidPrjDelete.setId(count.incrementAndGet());
//
//        // Create the BidPrjDelete
//        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjDeleteMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjDeleteDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDelete in the database
//        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
//        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithIdMismatchBidPrjDelete() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();
//        bidPrjDelete.setId(count.incrementAndGet());
//
//        // Create the BidPrjDelete
//        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjDeleteMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDelete in the database
//        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
//        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithMissingIdPathParamBidPrjDelete() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();
//        bidPrjDelete.setId(count.incrementAndGet());
//
//        // Create the BidPrjDelete
//        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjDeleteMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjDelete in the database
//        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
//        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void partialUpdateBidPrjDeleteWithPatch() throws Exception {
        // Initialize the database
        bidPrjDeleteRepository.saveAndFlush(bidPrjDelete);

        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();

        // Update the bidPrjDelete using partial update
        BidPrjDelete partialUpdatedBidPrjDelete = new BidPrjDelete();
        partialUpdatedBidPrjDelete.setId(bidPrjDelete.getId());

        partialUpdatedBidPrjDelete
            .plnCode(UPDATED_PLN_CODE)
            .plnNo(UPDATED_PLN_NO)
            .totAmt(UPDATED_TOT_AMT)
            .bdgt1(UPDATED_BDGT_1)
            .opDate(UPDATED_OP_DATE)
            .aopDate(UPDATED_AOP_DATE)
            .pctDate(UPDATED_PCT_DATE)
            .ctSum(UPDATED_CT_SUM)
            .pays(UPDATED_PAYS)
            .ctmeth(UPDATED_CTMETH)
            .wKind(UPDATED_W_KIND)
            .wDays(UPDATED_W_DAYS)
            .placeCode(UPDATED_PLACE_CODE)
            .tel(UPDATED_TEL)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .fcost2(UPDATED_FCOST_2)
            .lnkCode(UPDATED_LNK_CODE)
            .delResn(UPDATED_DEL_RESN);

        restBidPrjDeleteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjDelete.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjDelete))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjDelete in the database
        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
        BidPrjDelete testBidPrjDelete = bidPrjDeleteList.get(bidPrjDeleteList.size() - 1);
        assertThat(testBidPrjDelete.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjDelete.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjDelete.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBidPrjDelete.getPlnCode()).isEqualTo(UPDATED_PLN_CODE);
        assertThat(testBidPrjDelete.getPlnNo()).isEqualTo(UPDATED_PLN_NO);
        assertThat(testBidPrjDelete.getTypeCode()).isEqualTo(DEFAULT_TYPE_CODE);
        assertThat(testBidPrjDelete.getTotAmt()).isEqualByComparingTo(UPDATED_TOT_AMT);
        assertThat(testBidPrjDelete.getBdgt1()).isEqualByComparingTo(UPDATED_BDGT_1);
        assertThat(testBidPrjDelete.getOpDate()).isEqualTo(UPDATED_OP_DATE);
        assertThat(testBidPrjDelete.getAopDate()).isEqualTo(UPDATED_AOP_DATE);
        assertThat(testBidPrjDelete.getPctDate()).isEqualTo(UPDATED_PCT_DATE);
        assertThat(testBidPrjDelete.getActDate()).isEqualTo(DEFAULT_ACT_DATE);
        assertThat(testBidPrjDelete.getCtSum()).isEqualByComparingTo(UPDATED_CT_SUM);
        assertThat(testBidPrjDelete.getCntid()).isEqualTo(DEFAULT_CNTID);
        assertThat(testBidPrjDelete.getPays()).isEqualByComparingTo(UPDATED_PAYS);
        assertThat(testBidPrjDelete.getCtmeth()).isEqualTo(UPDATED_CTMETH);
        assertThat(testBidPrjDelete.getActmth()).isEqualTo(DEFAULT_ACTMTH);
        assertThat(testBidPrjDelete.getwKind()).isEqualTo(UPDATED_W_KIND);
        assertThat(testBidPrjDelete.getwDays()).isEqualTo(UPDATED_W_DAYS);
        assertThat(testBidPrjDelete.getPlaceCode()).isEqualTo(UPDATED_PLACE_CODE);
        assertThat(testBidPrjDelete.getMan()).isEqualTo(DEFAULT_MAN);
        assertThat(testBidPrjDelete.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testBidPrjDelete.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjDelete.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjDelete.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjDelete.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjDelete.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjDelete.getLnkNo2()).isEqualTo(DEFAULT_LNK_NO_2);
        assertThat(testBidPrjDelete.getFcost2()).isEqualByComparingTo(UPDATED_FCOST_2);
        assertThat(testBidPrjDelete.getLnkCode()).isEqualTo(UPDATED_LNK_CODE);
        assertThat(testBidPrjDelete.getLnkCode2()).isEqualTo(DEFAULT_LNK_CODE_2);
        assertThat(testBidPrjDelete.getLnkCode3()).isEqualTo(DEFAULT_LNK_CODE_3);
        assertThat(testBidPrjDelete.getDelDate()).isEqualTo(DEFAULT_DEL_DATE);
        assertThat(testBidPrjDelete.getDelResn()).isEqualTo(UPDATED_DEL_RESN);
        assertThat(testBidPrjDelete.getUniquekey()).isEqualTo(DEFAULT_UNIQUEKEY);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjDeleteWithPatch() throws Exception {
        // Initialize the database
        bidPrjDeleteRepository.saveAndFlush(bidPrjDelete);

        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();

        // Update the bidPrjDelete using partial update
        BidPrjDelete partialUpdatedBidPrjDelete = new BidPrjDelete();
        partialUpdatedBidPrjDelete.setId(bidPrjDelete.getId());

        partialUpdatedBidPrjDelete
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .name(UPDATED_NAME)
            .plnCode(UPDATED_PLN_CODE)
            .plnNo(UPDATED_PLN_NO)
            .typeCode(UPDATED_TYPE_CODE)
            .totAmt(UPDATED_TOT_AMT)
            .bdgt1(UPDATED_BDGT_1)
            .opDate(UPDATED_OP_DATE)
            .aopDate(UPDATED_AOP_DATE)
            .pctDate(UPDATED_PCT_DATE)
            .actDate(UPDATED_ACT_DATE)
            .ctSum(UPDATED_CT_SUM)
            .cntid(UPDATED_CNTID)
            .pays(UPDATED_PAYS)
            .ctmeth(UPDATED_CTMETH)
            .actmth(UPDATED_ACTMTH)
            .wKind(UPDATED_W_KIND)
            .wDays(UPDATED_W_DAYS)
            .placeCode(UPDATED_PLACE_CODE)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .lnkNo2(UPDATED_LNK_NO_2)
            .fcost2(UPDATED_FCOST_2)
            .lnkCode(UPDATED_LNK_CODE)
            .lnkCode2(UPDATED_LNK_CODE_2)
            .lnkCode3(UPDATED_LNK_CODE_3)
            .delDate(UPDATED_DEL_DATE)
            .delResn(UPDATED_DEL_RESN)
            .uniquekey(UPDATED_UNIQUEKEY);

        restBidPrjDeleteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjDelete.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjDelete))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjDelete in the database
        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
        BidPrjDelete testBidPrjDelete = bidPrjDeleteList.get(bidPrjDeleteList.size() - 1);
        assertThat(testBidPrjDelete.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjDelete.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjDelete.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBidPrjDelete.getPlnCode()).isEqualTo(UPDATED_PLN_CODE);
        assertThat(testBidPrjDelete.getPlnNo()).isEqualTo(UPDATED_PLN_NO);
        assertThat(testBidPrjDelete.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testBidPrjDelete.getTotAmt()).isEqualByComparingTo(UPDATED_TOT_AMT);
        assertThat(testBidPrjDelete.getBdgt1()).isEqualByComparingTo(UPDATED_BDGT_1);
        assertThat(testBidPrjDelete.getOpDate()).isEqualTo(UPDATED_OP_DATE);
        assertThat(testBidPrjDelete.getAopDate()).isEqualTo(UPDATED_AOP_DATE);
        assertThat(testBidPrjDelete.getPctDate()).isEqualTo(UPDATED_PCT_DATE);
        assertThat(testBidPrjDelete.getActDate()).isEqualTo(UPDATED_ACT_DATE);
        assertThat(testBidPrjDelete.getCtSum()).isEqualByComparingTo(UPDATED_CT_SUM);
        assertThat(testBidPrjDelete.getCntid()).isEqualTo(UPDATED_CNTID);
        assertThat(testBidPrjDelete.getPays()).isEqualByComparingTo(UPDATED_PAYS);
        assertThat(testBidPrjDelete.getCtmeth()).isEqualTo(UPDATED_CTMETH);
        assertThat(testBidPrjDelete.getActmth()).isEqualTo(UPDATED_ACTMTH);
        assertThat(testBidPrjDelete.getwKind()).isEqualTo(UPDATED_W_KIND);
        assertThat(testBidPrjDelete.getwDays()).isEqualTo(UPDATED_W_DAYS);
        assertThat(testBidPrjDelete.getPlaceCode()).isEqualTo(UPDATED_PLACE_CODE);
        assertThat(testBidPrjDelete.getMan()).isEqualTo(UPDATED_MAN);
        assertThat(testBidPrjDelete.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testBidPrjDelete.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjDelete.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjDelete.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjDelete.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjDelete.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjDelete.getLnkNo2()).isEqualTo(UPDATED_LNK_NO_2);
        assertThat(testBidPrjDelete.getFcost2()).isEqualByComparingTo(UPDATED_FCOST_2);
        assertThat(testBidPrjDelete.getLnkCode()).isEqualTo(UPDATED_LNK_CODE);
        assertThat(testBidPrjDelete.getLnkCode2()).isEqualTo(UPDATED_LNK_CODE_2);
        assertThat(testBidPrjDelete.getLnkCode3()).isEqualTo(UPDATED_LNK_CODE_3);
        assertThat(testBidPrjDelete.getDelDate()).isEqualTo(UPDATED_DEL_DATE);
        assertThat(testBidPrjDelete.getDelResn()).isEqualTo(UPDATED_DEL_RESN);
        assertThat(testBidPrjDelete.getUniquekey()).isEqualTo(UPDATED_UNIQUEKEY);
    }

//    @Test
//    @Transactional
//    void patchNonExistingBidPrjDelete() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();
//        bidPrjDelete.setId(count.incrementAndGet());
//
//        // Create the BidPrjDelete
//        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjDeleteMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjDeleteDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDelete in the database
//        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
//        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithIdMismatchBidPrjDelete() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();
//        bidPrjDelete.setId(count.incrementAndGet());
//
//        // Create the BidPrjDelete
//        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjDeleteMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjDelete in the database
//        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
//        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamBidPrjDelete() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjDeleteRepository.findAll().size();
//        bidPrjDelete.setId(count.incrementAndGet());
//
//        // Create the BidPrjDelete
//        BidPrjDeleteDTO bidPrjDeleteDTO = bidPrjDeleteMapper.toDto(bidPrjDelete);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjDeleteMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjDeleteDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjDelete in the database
//        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
//        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void deleteBidPrjDelete() throws Exception {
        // Initialize the database
        bidPrjDeleteRepository.saveAndFlush(bidPrjDelete);

        int databaseSizeBeforeDelete = bidPrjDeleteRepository.findAll().size();

        // Delete the bidPrjDelete
        restBidPrjDeleteMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjDelete.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjDelete> bidPrjDeleteList = bidPrjDeleteRepository.findAll();
        assertThat(bidPrjDeleteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
