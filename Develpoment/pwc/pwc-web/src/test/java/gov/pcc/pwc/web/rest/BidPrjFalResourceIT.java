package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjFal;
import gov.pcc.pwc.domain.BidPrjFalPrimaryKey;
import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;
import gov.pcc.pwc.repository.BidPrjFalRepository;
import gov.pcc.pwc.service.dto.BidPrjFalDTO;
import gov.pcc.pwc.service.mapper.BidPrjFalMapper;
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
 * Integration tests for the {@link BidPrjFalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjFalResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_YEAR = "AAA";
    private static final String UPDATED_YEAR = "BBB";

    private static final String DEFAULT_MONTH = "AA";
    private static final String UPDATED_MONTH = "BB";

    private static final String DEFAULT_SYR = "AAA";
    private static final String UPDATED_SYR = "BBB";

    private static final String DEFAULT_MFT = "AAA";
    private static final String UPDATED_MFT = "BBB";

    private static final String DEFAULT_MFAS = "AAAAAAAAAA";
    private static final String UPDATED_MFAS = "BBBBBBBBBB";

    private static final String DEFAULT_MFRT = "AAAAAAAAAA";
    private static final String UPDATED_MFRT = "BBBBBBBBBB";

    private static final String DEFAULT_OK_DATE = "AAAAA";
    private static final String UPDATED_OK_DATE = "BBBBB";

    private static final String DEFAULT_D_STATUS = "A";
    private static final String UPDATED_D_STATUS = "B";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MFRU = "AAAAAAAAAA";
    private static final String UPDATED_MFRU = "BBBBBBBBBB";

    private static final String DEFAULT_MFRS = "A";
    private static final String UPDATED_MFRS = "B";

    private static final Float DEFAULT_FAL_RATE = 1F;
    private static final Float UPDATED_FAL_RATE = 2F;

    private static final String DEFAULT_RESPONS = "A";
    private static final String UPDATED_RESPONS = "B";

    private static final String DEFAULT_METHER = "AA";
    private static final String UPDATED_METHER = "BB";

    private static final String DEFAULT_COLUMN_1 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_1 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_2 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_2 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_3 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_3 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_4 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_4 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_5 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_5 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_6 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_6 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_7 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_7 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_8 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_8 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_9 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_9 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-fals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjFalRepository bidPrjFalRepository;

    @Autowired
    private BidPrjFalMapper bidPrjFalMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjFalMockMvc;

    private BidPrjFal bidPrjFal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjFal createEntity(EntityManager em) {
        BidPrjFal bidPrjFal = new BidPrjFal()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .year(DEFAULT_YEAR)
            .month(DEFAULT_MONTH)
            .syr(DEFAULT_SYR)
            .mft(DEFAULT_MFT)
            .mfas(DEFAULT_MFAS)
            .mfrt(DEFAULT_MFRT)
//            .okDate(DEFAULT_OK_DATE)
            .dStatus(DEFAULT_D_STATUS)
            .createUser(DEFAULT_CREATE_USER)
            .createDate(DEFAULT_CREATE_DATE)
            .uIp(DEFAULT_U_IP)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .mfru(DEFAULT_MFRU)
            .mfrs(DEFAULT_MFRS)
            .falRate(DEFAULT_FAL_RATE)
            .respons(DEFAULT_RESPONS)
            .mether(DEFAULT_METHER)
            .column1(DEFAULT_COLUMN_1)
            .column2(DEFAULT_COLUMN_2)
            .column3(DEFAULT_COLUMN_3)
            .column4(DEFAULT_COLUMN_4)
            .column5(DEFAULT_COLUMN_5)
            .column6(DEFAULT_COLUMN_6)
            .column7(DEFAULT_COLUMN_7)
            .column8(DEFAULT_COLUMN_8)
            .column9(DEFAULT_COLUMN_9);
        return bidPrjFal;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjFal createUpdatedEntity(EntityManager em) {
        BidPrjFal bidPrjFal = new BidPrjFal()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .syr(UPDATED_SYR)
            .mft(UPDATED_MFT)
            .mfas(UPDATED_MFAS)
            .mfrt(UPDATED_MFRT)
//            .okDate(UPDATED_OK_DATE)
            .dStatus(UPDATED_D_STATUS)
            .createUser(UPDATED_CREATE_USER)
            .createDate(UPDATED_CREATE_DATE)
            .uIp(UPDATED_U_IP)
            .updateUser(UPDATED_UPDATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .mfru(UPDATED_MFRU)
            .mfrs(UPDATED_MFRS)
            .falRate(UPDATED_FAL_RATE)
            .respons(UPDATED_RESPONS)
            .mether(UPDATED_METHER)
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column4(UPDATED_COLUMN_4)
            .column5(UPDATED_COLUMN_5)
            .column6(UPDATED_COLUMN_6)
            .column7(UPDATED_COLUMN_7)
            .column8(UPDATED_COLUMN_8)
            .column9(UPDATED_COLUMN_9);
        return bidPrjFal;
    }

    @BeforeEach
    public void initTest() {
        bidPrjFal = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjFal() throws Exception {
        int databaseSizeBeforeCreate = bidPrjFalRepository.findAll().size();
        // Create the BidPrjFal
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);
        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isCreated());

        // Validate the BidPrjFal in the database
        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjFal testBidPrjFal = bidPrjFalList.get(bidPrjFalList.size() - 1);
        assertThat(testBidPrjFal.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjFal.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjFal.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testBidPrjFal.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBidPrjFal.getSyr()).isEqualTo(DEFAULT_SYR);
        assertThat(testBidPrjFal.getMft()).isEqualTo(DEFAULT_MFT);
        assertThat(testBidPrjFal.getMfas()).isEqualTo(DEFAULT_MFAS);
        assertThat(testBidPrjFal.getMfrt()).isEqualTo(DEFAULT_MFRT);
        assertThat(testBidPrjFal.getOkDate()).isEqualTo(DEFAULT_OK_DATE);
        assertThat(testBidPrjFal.getdStatus()).isEqualTo(DEFAULT_D_STATUS);
        assertThat(testBidPrjFal.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjFal.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjFal.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjFal.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjFal.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjFal.getMfru()).isEqualTo(DEFAULT_MFRU);
        assertThat(testBidPrjFal.getMfrs()).isEqualTo(DEFAULT_MFRS);
        assertThat(testBidPrjFal.getFalRate()).isEqualTo(DEFAULT_FAL_RATE);
        assertThat(testBidPrjFal.getRespons()).isEqualTo(DEFAULT_RESPONS);
        assertThat(testBidPrjFal.getMether()).isEqualTo(DEFAULT_METHER);
        assertThat(testBidPrjFal.getColumn1()).isEqualTo(DEFAULT_COLUMN_1);
        assertThat(testBidPrjFal.getColumn2()).isEqualTo(DEFAULT_COLUMN_2);
        assertThat(testBidPrjFal.getColumn3()).isEqualTo(DEFAULT_COLUMN_3);
        assertThat(testBidPrjFal.getColumn4()).isEqualTo(DEFAULT_COLUMN_4);
        assertThat(testBidPrjFal.getColumn5()).isEqualTo(DEFAULT_COLUMN_5);
        assertThat(testBidPrjFal.getColumn6()).isEqualTo(DEFAULT_COLUMN_6);
        assertThat(testBidPrjFal.getColumn7()).isEqualTo(DEFAULT_COLUMN_7);
        assertThat(testBidPrjFal.getColumn8()).isEqualTo(DEFAULT_COLUMN_8);
        assertThat(testBidPrjFal.getColumn9()).isEqualTo(DEFAULT_COLUMN_9);
    }

    @Test
    @Transactional
    void createBidPrjFalWithExistingId() throws Exception {
        // Create the BidPrjFal with an existing ID
        bidPrjFal.setId(new BidPrjFalPrimaryKey("12345678","12345678","111","01","01"));
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        int databaseSizeBeforeCreate = bidPrjFalRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BidPrjFal in the database
        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFalRepository.findAll().size();
        // set the field null
        bidPrjFal.setWkut(null);

        // Create the BidPrjFal, which fails.
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFalRepository.findAll().size();
        // set the field null
        bidPrjFal.setPrjno(null);

        // Create the BidPrjFal, which fails.
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFalRepository.findAll().size();
        // set the field null
        bidPrjFal.setYear(null);

        // Create the BidPrjFal, which fails.
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMonthIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFalRepository.findAll().size();
        // set the field null
        bidPrjFal.setMonth(null);

        // Create the BidPrjFal, which fails.
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSyrIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFalRepository.findAll().size();
        // set the field null
        bidPrjFal.setSyr(null);

        // Create the BidPrjFal, which fails.
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMftIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFalRepository.findAll().size();
        // set the field null
        bidPrjFal.setMft(null);

        // Create the BidPrjFal, which fails.
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFalRepository.findAll().size();
        // set the field null
        bidPrjFal.setCreateUser(null);

        // Create the BidPrjFal, which fails.
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjFalRepository.findAll().size();
        // set the field null
        bidPrjFal.setCreateDate(null);

        // Create the BidPrjFal, which fails.
        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);

        restBidPrjFalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    void getAllBidPrjFals() throws Exception {
//        // Initialize the database
//        bidPrjFalRepository.saveAndFlush(bidPrjFal);
//
//        // Get all the bidPrjFalList
//        restBidPrjFalMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjFal.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
//            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
//            .andExpect(jsonPath("$.[*].syr").value(hasItem(DEFAULT_SYR)))
//            .andExpect(jsonPath("$.[*].mft").value(hasItem(DEFAULT_MFT)))
//            .andExpect(jsonPath("$.[*].mfas").value(hasItem(DEFAULT_MFAS)))
//            .andExpect(jsonPath("$.[*].mfrt").value(hasItem(DEFAULT_MFRT)))
//            .andExpect(jsonPath("$.[*].okDate").value(hasItem(DEFAULT_OK_DATE)))
//            .andExpect(jsonPath("$.[*].dStatus").value(hasItem(DEFAULT_D_STATUS)))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].mfru").value(hasItem(DEFAULT_MFRU)))
//            .andExpect(jsonPath("$.[*].mfrs").value(hasItem(DEFAULT_MFRS)))
//            .andExpect(jsonPath("$.[*].falRate").value(hasItem(DEFAULT_FAL_RATE.doubleValue())))
//            .andExpect(jsonPath("$.[*].respons").value(hasItem(DEFAULT_RESPONS)))
//            .andExpect(jsonPath("$.[*].mether").value(hasItem(DEFAULT_METHER)))
//            .andExpect(jsonPath("$.[*].column1").value(hasItem(DEFAULT_COLUMN_1)))
//            .andExpect(jsonPath("$.[*].column2").value(hasItem(DEFAULT_COLUMN_2)))
//            .andExpect(jsonPath("$.[*].column3").value(hasItem(DEFAULT_COLUMN_3)))
//            .andExpect(jsonPath("$.[*].column4").value(hasItem(DEFAULT_COLUMN_4)))
//            .andExpect(jsonPath("$.[*].column5").value(hasItem(DEFAULT_COLUMN_5)))
//            .andExpect(jsonPath("$.[*].column6").value(hasItem(DEFAULT_COLUMN_6)))
//            .andExpect(jsonPath("$.[*].column7").value(hasItem(DEFAULT_COLUMN_7)))
//            .andExpect(jsonPath("$.[*].column8").value(hasItem(DEFAULT_COLUMN_8)))
//            .andExpect(jsonPath("$.[*].column9").value(hasItem(DEFAULT_COLUMN_9)));
//    }

//    @Test
//    @Transactional
//    void getBidPrjFal() throws Exception {
//        // Initialize the database
//        bidPrjFalRepository.saveAndFlush(bidPrjFal);
//
//        // Get the bidPrjFal
//        restBidPrjFalMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidPrjFal.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjFal.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
//            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
//            .andExpect(jsonPath("$.syr").value(DEFAULT_SYR))
//            .andExpect(jsonPath("$.mft").value(DEFAULT_MFT))
//            .andExpect(jsonPath("$.mfas").value(DEFAULT_MFAS))
//            .andExpect(jsonPath("$.mfrt").value(DEFAULT_MFRT))
//            .andExpect(jsonPath("$.okDate").value(DEFAULT_OK_DATE))
//            .andExpect(jsonPath("$.dStatus").value(DEFAULT_D_STATUS))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.mfru").value(DEFAULT_MFRU))
//            .andExpect(jsonPath("$.mfrs").value(DEFAULT_MFRS))
//            .andExpect(jsonPath("$.falRate").value(DEFAULT_FAL_RATE.doubleValue()))
//            .andExpect(jsonPath("$.respons").value(DEFAULT_RESPONS))
//            .andExpect(jsonPath("$.mether").value(DEFAULT_METHER))
//            .andExpect(jsonPath("$.column1").value(DEFAULT_COLUMN_1))
//            .andExpect(jsonPath("$.column2").value(DEFAULT_COLUMN_2))
//            .andExpect(jsonPath("$.column3").value(DEFAULT_COLUMN_3))
//            .andExpect(jsonPath("$.column4").value(DEFAULT_COLUMN_4))
//            .andExpect(jsonPath("$.column5").value(DEFAULT_COLUMN_5))
//            .andExpect(jsonPath("$.column6").value(DEFAULT_COLUMN_6))
//            .andExpect(jsonPath("$.column7").value(DEFAULT_COLUMN_7))
//            .andExpect(jsonPath("$.column8").value(DEFAULT_COLUMN_8))
//            .andExpect(jsonPath("$.column9").value(DEFAULT_COLUMN_9));
//    }

    @Test
    @Transactional
    void getNonExistingBidPrjFal() throws Exception {
        // Get the bidPrjFal
        restBidPrjFalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

//    @Test
//    @Transactional
//    void putNewBidPrjFal() throws Exception {
//        // Initialize the database
//        bidPrjFalRepository.saveAndFlush(bidPrjFal);
//
//        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();
//
//        // Update the bidPrjFal
//        BidPrjFal updatedBidPrjFal = bidPrjFalRepository.findById(bidPrjFal.getId()).get();
//        // Disconnect from session so that the updates on updatedBidPrjFal are not directly saved in db
//        em.detach(updatedBidPrjFal);
//        updatedBidPrjFal
//            .wkut(UPDATED_WKUT)
//            .prjno(UPDATED_PRJNO)
//            .year(UPDATED_YEAR)
//            .month(UPDATED_MONTH)
//            .syr(UPDATED_SYR)
//            .mft(UPDATED_MFT)
//            .mfas(UPDATED_MFAS)
//            .mfrt(UPDATED_MFRT)
//            .okDate(UPDATED_OK_DATE)
//            .dStatus(UPDATED_D_STATUS)
//            .createUser(UPDATED_CREATE_USER)
//            .createDate(UPDATED_CREATE_DATE)
//            .uIp(UPDATED_U_IP)
//            .updateUser(UPDATED_UPDATE_USER)
//            .updateDate(UPDATED_UPDATE_DATE)
//            .mfru(UPDATED_MFRU)
//            .mfrs(UPDATED_MFRS)
//            .falRate(UPDATED_FAL_RATE)
//            .respons(UPDATED_RESPONS)
//            .mether(UPDATED_METHER)
//            .column1(UPDATED_COLUMN_1)
//            .column2(UPDATED_COLUMN_2)
//            .column3(UPDATED_COLUMN_3)
//            .column4(UPDATED_COLUMN_4)
//            .column5(UPDATED_COLUMN_5)
//            .column6(UPDATED_COLUMN_6)
//            .column7(UPDATED_COLUMN_7)
//            .column8(UPDATED_COLUMN_8)
//            .column9(UPDATED_COLUMN_9);
//        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(updatedBidPrjFal);
//
//        restBidPrjFalMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjFalDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the BidPrjFal in the database
//        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
//        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
//        BidPrjFal testBidPrjFal = bidPrjFalList.get(bidPrjFalList.size() - 1);
//        assertThat(testBidPrjFal.getWkut()).isEqualTo(UPDATED_WKUT);
//        assertThat(testBidPrjFal.getPrjno()).isEqualTo(UPDATED_PRJNO);
//        assertThat(testBidPrjFal.getYear()).isEqualTo(UPDATED_YEAR);
//        assertThat(testBidPrjFal.getMonth()).isEqualTo(UPDATED_MONTH);
//        assertThat(testBidPrjFal.getSyr()).isEqualTo(UPDATED_SYR);
//        assertThat(testBidPrjFal.getMft()).isEqualTo(UPDATED_MFT);
//        assertThat(testBidPrjFal.getMfas()).isEqualTo(UPDATED_MFAS);
//        assertThat(testBidPrjFal.getMfrt()).isEqualTo(UPDATED_MFRT);
//        assertThat(testBidPrjFal.getOkDate()).isEqualTo(UPDATED_OK_DATE);
//        assertThat(testBidPrjFal.getdStatus()).isEqualTo(UPDATED_D_STATUS);
//        assertThat(testBidPrjFal.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
//        assertThat(testBidPrjFal.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
//        assertThat(testBidPrjFal.getuIp()).isEqualTo(UPDATED_U_IP);
//        assertThat(testBidPrjFal.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//        assertThat(testBidPrjFal.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//        assertThat(testBidPrjFal.getMfru()).isEqualTo(UPDATED_MFRU);
//        assertThat(testBidPrjFal.getMfrs()).isEqualTo(UPDATED_MFRS);
//        assertThat(testBidPrjFal.getFalRate()).isEqualTo(UPDATED_FAL_RATE);
//        assertThat(testBidPrjFal.getRespons()).isEqualTo(UPDATED_RESPONS);
//        assertThat(testBidPrjFal.getMether()).isEqualTo(UPDATED_METHER);
//        assertThat(testBidPrjFal.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
//        assertThat(testBidPrjFal.getColumn2()).isEqualTo(UPDATED_COLUMN_2);
//        assertThat(testBidPrjFal.getColumn3()).isEqualTo(UPDATED_COLUMN_3);
//        assertThat(testBidPrjFal.getColumn4()).isEqualTo(UPDATED_COLUMN_4);
//        assertThat(testBidPrjFal.getColumn5()).isEqualTo(UPDATED_COLUMN_5);
//        assertThat(testBidPrjFal.getColumn6()).isEqualTo(UPDATED_COLUMN_6);
//        assertThat(testBidPrjFal.getColumn7()).isEqualTo(UPDATED_COLUMN_7);
//        assertThat(testBidPrjFal.getColumn8()).isEqualTo(UPDATED_COLUMN_8);
//        assertThat(testBidPrjFal.getColumn9()).isEqualTo(UPDATED_COLUMN_9);
//    }

//    @Test
//    @Transactional
//    void putNonExistingBidPrjFal() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();
//        bidPrjFal.setId(count.incrementAndGet());
//
//        // Create the BidPrjFal
//        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjFalMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjFalDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjFal in the database
//        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
//        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithIdMismatchBidPrjFal() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();
//        bidPrjFal.setId(count.incrementAndGet());
//
//        // Create the BidPrjFal
//        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjFalMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjFal in the database
//        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
//        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithMissingIdPathParamBidPrjFal() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();
//        bidPrjFal.setId(count.incrementAndGet());
//
//        // Create the BidPrjFal
//        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjFalMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjFal in the database
//        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
//        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void partialUpdateBidPrjFalWithPatch() throws Exception {
        // Initialize the database
        bidPrjFalRepository.saveAndFlush(bidPrjFal);

        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();

        // Update the bidPrjFal using partial update
        BidPrjFal partialUpdatedBidPrjFal = new BidPrjFal();
        partialUpdatedBidPrjFal.setId(bidPrjFal.getId());

        partialUpdatedBidPrjFal
            .mft(UPDATED_MFT)
            .mfas(UPDATED_MFAS)
//            .okDate(UPDATED_OK_DATE)
            .createUser(UPDATED_CREATE_USER)
            .createDate(UPDATED_CREATE_DATE)
            .uIp(UPDATED_U_IP)
            .mfrs(UPDATED_MFRS)
            .falRate(UPDATED_FAL_RATE)
            .respons(UPDATED_RESPONS)
            .mether(UPDATED_METHER)
            .column3(UPDATED_COLUMN_3)
            .column6(UPDATED_COLUMN_6);

        restBidPrjFalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjFal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjFal))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjFal in the database
        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
        BidPrjFal testBidPrjFal = bidPrjFalList.get(bidPrjFalList.size() - 1);
        assertThat(testBidPrjFal.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjFal.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjFal.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testBidPrjFal.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBidPrjFal.getSyr()).isEqualTo(DEFAULT_SYR);
        assertThat(testBidPrjFal.getMft()).isEqualTo(UPDATED_MFT);
        assertThat(testBidPrjFal.getMfas()).isEqualTo(UPDATED_MFAS);
        assertThat(testBidPrjFal.getMfrt()).isEqualTo(DEFAULT_MFRT);
        assertThat(testBidPrjFal.getOkDate()).isEqualTo(UPDATED_OK_DATE);
        assertThat(testBidPrjFal.getdStatus()).isEqualTo(DEFAULT_D_STATUS);
        assertThat(testBidPrjFal.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjFal.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjFal.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjFal.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjFal.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjFal.getMfru()).isEqualTo(DEFAULT_MFRU);
        assertThat(testBidPrjFal.getMfrs()).isEqualTo(UPDATED_MFRS);
        assertThat(testBidPrjFal.getFalRate()).isEqualTo(UPDATED_FAL_RATE);
        assertThat(testBidPrjFal.getRespons()).isEqualTo(UPDATED_RESPONS);
        assertThat(testBidPrjFal.getMether()).isEqualTo(UPDATED_METHER);
        assertThat(testBidPrjFal.getColumn1()).isEqualTo(DEFAULT_COLUMN_1);
        assertThat(testBidPrjFal.getColumn2()).isEqualTo(DEFAULT_COLUMN_2);
        assertThat(testBidPrjFal.getColumn3()).isEqualTo(UPDATED_COLUMN_3);
        assertThat(testBidPrjFal.getColumn4()).isEqualTo(DEFAULT_COLUMN_4);
        assertThat(testBidPrjFal.getColumn5()).isEqualTo(DEFAULT_COLUMN_5);
        assertThat(testBidPrjFal.getColumn6()).isEqualTo(UPDATED_COLUMN_6);
        assertThat(testBidPrjFal.getColumn7()).isEqualTo(DEFAULT_COLUMN_7);
        assertThat(testBidPrjFal.getColumn8()).isEqualTo(DEFAULT_COLUMN_8);
        assertThat(testBidPrjFal.getColumn9()).isEqualTo(DEFAULT_COLUMN_9);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjFalWithPatch() throws Exception {
        // Initialize the database
        bidPrjFalRepository.saveAndFlush(bidPrjFal);

        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();

        // Update the bidPrjFal using partial update
        BidPrjFal partialUpdatedBidPrjFal = new BidPrjFal();
        partialUpdatedBidPrjFal.setId(bidPrjFal.getId());

        partialUpdatedBidPrjFal
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .syr(UPDATED_SYR)
            .mft(UPDATED_MFT)
            .mfas(UPDATED_MFAS)
            .mfrt(UPDATED_MFRT)
//            .okDate(UPDATED_OK_DATE)
            .dStatus(UPDATED_D_STATUS)
            .createUser(UPDATED_CREATE_USER)
            .createDate(UPDATED_CREATE_DATE)
            .uIp(UPDATED_U_IP)
            .updateUser(UPDATED_UPDATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .mfru(UPDATED_MFRU)
            .mfrs(UPDATED_MFRS)
            .falRate(UPDATED_FAL_RATE)
            .respons(UPDATED_RESPONS)
            .mether(UPDATED_METHER)
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column4(UPDATED_COLUMN_4)
            .column5(UPDATED_COLUMN_5)
            .column6(UPDATED_COLUMN_6)
            .column7(UPDATED_COLUMN_7)
            .column8(UPDATED_COLUMN_8)
            .column9(UPDATED_COLUMN_9);

        restBidPrjFalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjFal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjFal))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjFal in the database
        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
        BidPrjFal testBidPrjFal = bidPrjFalList.get(bidPrjFalList.size() - 1);
        assertThat(testBidPrjFal.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjFal.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjFal.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testBidPrjFal.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjFal.getSyr()).isEqualTo(UPDATED_SYR);
        assertThat(testBidPrjFal.getMft()).isEqualTo(UPDATED_MFT);
        assertThat(testBidPrjFal.getMfas()).isEqualTo(UPDATED_MFAS);
        assertThat(testBidPrjFal.getMfrt()).isEqualTo(UPDATED_MFRT);
        assertThat(testBidPrjFal.getOkDate()).isEqualTo(UPDATED_OK_DATE);
        assertThat(testBidPrjFal.getdStatus()).isEqualTo(UPDATED_D_STATUS);
        assertThat(testBidPrjFal.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjFal.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjFal.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjFal.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjFal.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjFal.getMfru()).isEqualTo(UPDATED_MFRU);
        assertThat(testBidPrjFal.getMfrs()).isEqualTo(UPDATED_MFRS);
        assertThat(testBidPrjFal.getFalRate()).isEqualTo(UPDATED_FAL_RATE);
        assertThat(testBidPrjFal.getRespons()).isEqualTo(UPDATED_RESPONS);
        assertThat(testBidPrjFal.getMether()).isEqualTo(UPDATED_METHER);
        assertThat(testBidPrjFal.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
        assertThat(testBidPrjFal.getColumn2()).isEqualTo(UPDATED_COLUMN_2);
        assertThat(testBidPrjFal.getColumn3()).isEqualTo(UPDATED_COLUMN_3);
        assertThat(testBidPrjFal.getColumn4()).isEqualTo(UPDATED_COLUMN_4);
        assertThat(testBidPrjFal.getColumn5()).isEqualTo(UPDATED_COLUMN_5);
        assertThat(testBidPrjFal.getColumn6()).isEqualTo(UPDATED_COLUMN_6);
        assertThat(testBidPrjFal.getColumn7()).isEqualTo(UPDATED_COLUMN_7);
        assertThat(testBidPrjFal.getColumn8()).isEqualTo(UPDATED_COLUMN_8);
        assertThat(testBidPrjFal.getColumn9()).isEqualTo(UPDATED_COLUMN_9);
    }

//    @Test
//    @Transactional
//    void patchNonExistingBidPrjFal() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();
//        bidPrjFal.setId(count.incrementAndGet());
//
//        // Create the BidPrjFal
//        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjFalMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjFalDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjFal in the database
//        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
//        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithIdMismatchBidPrjFal() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();
//        bidPrjFal.setId(count.incrementAndGet());
//
//        // Create the BidPrjFal
//        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjFalMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjFal in the database
//        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
//        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamBidPrjFal() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjFalRepository.findAll().size();
//        bidPrjFal.setId(count.incrementAndGet());
//
//        // Create the BidPrjFal
//        BidPrjFalDTO bidPrjFalDTO = bidPrjFalMapper.toDto(bidPrjFal);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjFalMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjFalDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjFal in the database
//        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
//        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void deleteBidPrjFal() throws Exception {
        // Initialize the database
        bidPrjFalRepository.saveAndFlush(bidPrjFal);

        int databaseSizeBeforeDelete = bidPrjFalRepository.findAll().size();

        // Delete the bidPrjFal
        restBidPrjFalMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjFal.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjFal> bidPrjFalList = bidPrjFalRepository.findAll();
        assertThat(bidPrjFalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
