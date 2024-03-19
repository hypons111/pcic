package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjStopwk;
import gov.pcc.pwc.repository.BidPrjStopwkRepository;
import gov.pcc.pwc.service.dto.BidPrjStopwkDTO;
import gov.pcc.pwc.service.mapper.BidPrjStopwkMapper;
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
 * Integration tests for the {@link BidPrjStopwkResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjStopwkResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESPONS = "A";
    private static final String UPDATED_RESPONS = "B";

    private static final String DEFAULT_DUTY = "A";
    private static final String UPDATED_DUTY = "B";

    private static final String DEFAULT_PRM_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_NEGO_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_NEGO_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_STOP_RESON = "AA";
    private static final String UPDATED_STOP_RESON = "BB";

    private static final String DEFAULT_NEGO_LEVEL = "AA";
    private static final String UPDATED_NEGO_LEVEL = "BB";

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

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_E_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_E_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CONF_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT = new BigDecimal(2);

    private static final Integer DEFAULT_NO_CNT_DAY = 1;
    private static final Integer UPDATED_NO_CNT_DAY = 2;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final LocalDate DEFAULT_PE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/bid-prj-stopwks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjStopwkRepository bidPrjStopwkRepository;

    @Autowired
    private BidPrjStopwkMapper bidPrjStopwkMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjStopwkMockMvc;

    private BidPrjStopwk bidPrjStopwk;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjStopwk createEntity(EntityManager em) {
        BidPrjStopwk bidPrjStopwk = new BidPrjStopwk()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .startDate(DEFAULT_START_DATE)
            .respons(DEFAULT_RESPONS)
            .duty(DEFAULT_DUTY)
            .prmNo(DEFAULT_PRM_NO)
            .negoUnit(DEFAULT_NEGO_UNIT)
            .stopReson(DEFAULT_STOP_RESON)
            .negoLevel(DEFAULT_NEGO_LEVEL)
            .column1(DEFAULT_COLUMN_1)
            .column2(DEFAULT_COLUMN_2)
            .column3(DEFAULT_COLUMN_3)
            .column4(DEFAULT_COLUMN_4)
            .column5(DEFAULT_COLUMN_5)
            .remark(DEFAULT_REMARK)
            .eRemark(DEFAULT_E_REMARK)
            .uIp(DEFAULT_U_IP)
            .createUser(DEFAULT_CREATE_USER)
            .updateUser(DEFAULT_UPDATE_USER)
            .confOut(DEFAULT_CONF_OUT)
            .noCntDay(DEFAULT_NO_CNT_DAY)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE)
            .peDate(DEFAULT_PE_DATE)
            .endDate(DEFAULT_END_DATE);
        return bidPrjStopwk;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjStopwk createUpdatedEntity(EntityManager em) {
        BidPrjStopwk bidPrjStopwk = new BidPrjStopwk()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .startDate(UPDATED_START_DATE)
            .respons(UPDATED_RESPONS)
            .duty(UPDATED_DUTY)
            .prmNo(UPDATED_PRM_NO)
            .negoUnit(UPDATED_NEGO_UNIT)
            .stopReson(UPDATED_STOP_RESON)
            .negoLevel(UPDATED_NEGO_LEVEL)
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column4(UPDATED_COLUMN_4)
            .column5(UPDATED_COLUMN_5)
            .remark(UPDATED_REMARK)
            .eRemark(UPDATED_E_REMARK)
            .uIp(UPDATED_U_IP)
            .createUser(UPDATED_CREATE_USER)
            .updateUser(UPDATED_UPDATE_USER)
            .confOut(UPDATED_CONF_OUT)
            .noCntDay(UPDATED_NO_CNT_DAY)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .peDate(UPDATED_PE_DATE)
            .endDate(UPDATED_END_DATE);
        return bidPrjStopwk;
    }

    @BeforeEach
    public void initTest() {
        bidPrjStopwk = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjStopwk() throws Exception {
        int databaseSizeBeforeCreate = bidPrjStopwkRepository.findAll().size();
        // Create the BidPrjStopwk
        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);
        restBidPrjStopwkMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjStopwk in the database
        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjStopwk testBidPrjStopwk = bidPrjStopwkList.get(bidPrjStopwkList.size() - 1);
        assertThat(testBidPrjStopwk.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjStopwk.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjStopwk.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBidPrjStopwk.getRespons()).isEqualTo(DEFAULT_RESPONS);
        assertThat(testBidPrjStopwk.getDuty()).isEqualTo(DEFAULT_DUTY);
        assertThat(testBidPrjStopwk.getPrmNo()).isEqualTo(DEFAULT_PRM_NO);
        assertThat(testBidPrjStopwk.getNegoUnit()).isEqualTo(DEFAULT_NEGO_UNIT);
        assertThat(testBidPrjStopwk.getStopReson()).isEqualTo(DEFAULT_STOP_RESON);
        assertThat(testBidPrjStopwk.getNegoLevel()).isEqualTo(DEFAULT_NEGO_LEVEL);
        assertThat(testBidPrjStopwk.getColumn1()).isEqualTo(DEFAULT_COLUMN_1);
        assertThat(testBidPrjStopwk.getColumn2()).isEqualTo(DEFAULT_COLUMN_2);
        assertThat(testBidPrjStopwk.getColumn3()).isEqualTo(DEFAULT_COLUMN_3);
        assertThat(testBidPrjStopwk.getColumn4()).isEqualTo(DEFAULT_COLUMN_4);
        assertThat(testBidPrjStopwk.getColumn5()).isEqualTo(DEFAULT_COLUMN_5);
        assertThat(testBidPrjStopwk.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBidPrjStopwk.geteRemark()).isEqualTo(DEFAULT_E_REMARK);
        assertThat(testBidPrjStopwk.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjStopwk.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjStopwk.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjStopwk.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjStopwk.getNoCntDay()).isEqualTo(DEFAULT_NO_CNT_DAY);
        assertThat(testBidPrjStopwk.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjStopwk.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjStopwk.getPeDate()).isEqualTo(DEFAULT_PE_DATE);
        assertThat(testBidPrjStopwk.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

//    @Test
//    @Transactional
//    void createBidPrjStopwkWithExistingId() throws Exception {
//        // Create the BidPrjStopwk with an existing ID
//        bidPrjStopwk.setId(1L);
//        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);
//
//        int databaseSizeBeforeCreate = bidPrjStopwkRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjStopwkMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStopwk in the database
//        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
//        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeCreate);
//    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjStopwkRepository.findAll().size();
        // set the field null
        bidPrjStopwk.setWkut(null);

        // Create the BidPrjStopwk, which fails.
        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);

        restBidPrjStopwkMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjStopwkRepository.findAll().size();
        // set the field null
        bidPrjStopwk.setPrjno(null);

        // Create the BidPrjStopwk, which fails.
        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);

        restBidPrjStopwkMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjStopwkRepository.findAll().size();
        // set the field null
        bidPrjStopwk.setStartDate(null);

        // Create the BidPrjStopwk, which fails.
        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);

        restBidPrjStopwkMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjStopwkRepository.findAll().size();
        // set the field null
        bidPrjStopwk.setCreateUser(null);

        // Create the BidPrjStopwk, which fails.
        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);

        restBidPrjStopwkMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjStopwkRepository.findAll().size();
        // set the field null
        bidPrjStopwk.setCreateDate(null);

        // Create the BidPrjStopwk, which fails.
        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);

        restBidPrjStopwkMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    void getAllBidPrjStopwks() throws Exception {
//        // Initialize the database
//        bidPrjStopwkRepository.saveAndFlush(bidPrjStopwk);
//
//        // Get all the bidPrjStopwkList
//        restBidPrjStopwkMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjStopwk.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
//            .andExpect(jsonPath("$.[*].respons").value(hasItem(DEFAULT_RESPONS)))
//            .andExpect(jsonPath("$.[*].duty").value(hasItem(DEFAULT_DUTY)))
//            .andExpect(jsonPath("$.[*].prmNo").value(hasItem(DEFAULT_PRM_NO)))
//            .andExpect(jsonPath("$.[*].negoUnit").value(hasItem(DEFAULT_NEGO_UNIT)))
//            .andExpect(jsonPath("$.[*].stopReson").value(hasItem(DEFAULT_STOP_RESON)))
//            .andExpect(jsonPath("$.[*].negoLevel").value(hasItem(DEFAULT_NEGO_LEVEL)))
//            .andExpect(jsonPath("$.[*].column1").value(hasItem(DEFAULT_COLUMN_1)))
//            .andExpect(jsonPath("$.[*].column2").value(hasItem(DEFAULT_COLUMN_2)))
//            .andExpect(jsonPath("$.[*].column3").value(hasItem(DEFAULT_COLUMN_3)))
//            .andExpect(jsonPath("$.[*].column4").value(hasItem(DEFAULT_COLUMN_4)))
//            .andExpect(jsonPath("$.[*].column5").value(hasItem(DEFAULT_COLUMN_5)))
//            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
//            .andExpect(jsonPath("$.[*].eRemark").value(hasItem(DEFAULT_E_REMARK)))
//            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].confOut").value(hasItem(sameNumber(DEFAULT_CONF_OUT))))
//            .andExpect(jsonPath("$.[*].noCntDay").value(hasItem(DEFAULT_NO_CNT_DAY)))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].peDate").value(hasItem(DEFAULT_PE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
//    }

//    @Test
//    @Transactional
//    void getBidPrjStopwk() throws Exception {
//        // Initialize the database
//        bidPrjStopwkRepository.saveAndFlush(bidPrjStopwk);
//
//        // Get the bidPrjStopwk
//        restBidPrjStopwkMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidPrjStopwk.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjStopwk.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
//            .andExpect(jsonPath("$.respons").value(DEFAULT_RESPONS))
//            .andExpect(jsonPath("$.duty").value(DEFAULT_DUTY))
//            .andExpect(jsonPath("$.prmNo").value(DEFAULT_PRM_NO))
//            .andExpect(jsonPath("$.negoUnit").value(DEFAULT_NEGO_UNIT))
//            .andExpect(jsonPath("$.stopReson").value(DEFAULT_STOP_RESON))
//            .andExpect(jsonPath("$.negoLevel").value(DEFAULT_NEGO_LEVEL))
//            .andExpect(jsonPath("$.column1").value(DEFAULT_COLUMN_1))
//            .andExpect(jsonPath("$.column2").value(DEFAULT_COLUMN_2))
//            .andExpect(jsonPath("$.column3").value(DEFAULT_COLUMN_3))
//            .andExpect(jsonPath("$.column4").value(DEFAULT_COLUMN_4))
//            .andExpect(jsonPath("$.column5").value(DEFAULT_COLUMN_5))
//            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
//            .andExpect(jsonPath("$.eRemark").value(DEFAULT_E_REMARK))
//            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.confOut").value(sameNumber(DEFAULT_CONF_OUT)))
//            .andExpect(jsonPath("$.noCntDay").value(DEFAULT_NO_CNT_DAY))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.peDate").value(DEFAULT_PE_DATE.toString()))
//            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
//    }

    @Test
    @Transactional
    void getNonExistingBidPrjStopwk() throws Exception {
        // Get the bidPrjStopwk
        restBidPrjStopwkMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjStopwk() throws Exception {
        // Initialize the database
        bidPrjStopwkRepository.saveAndFlush(bidPrjStopwk);

        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();

        // Update the bidPrjStopwk
        BidPrjStopwk updatedBidPrjStopwk = bidPrjStopwkRepository.findById(bidPrjStopwk.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjStopwk are not directly saved in db
        em.detach(updatedBidPrjStopwk);
        updatedBidPrjStopwk
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .startDate(UPDATED_START_DATE)
            .respons(UPDATED_RESPONS)
            .duty(UPDATED_DUTY)
            .prmNo(UPDATED_PRM_NO)
            .negoUnit(UPDATED_NEGO_UNIT)
            .stopReson(UPDATED_STOP_RESON)
            .negoLevel(UPDATED_NEGO_LEVEL)
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column4(UPDATED_COLUMN_4)
            .column5(UPDATED_COLUMN_5)
            .remark(UPDATED_REMARK)
            .eRemark(UPDATED_E_REMARK)
            .uIp(UPDATED_U_IP)
            .createUser(UPDATED_CREATE_USER)
            .updateUser(UPDATED_UPDATE_USER)
            .confOut(UPDATED_CONF_OUT)
            .noCntDay(UPDATED_NO_CNT_DAY)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .peDate(UPDATED_PE_DATE)
            .endDate(UPDATED_END_DATE);
        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(updatedBidPrjStopwk);

        restBidPrjStopwkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjStopwkDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjStopwk in the database
        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
        BidPrjStopwk testBidPrjStopwk = bidPrjStopwkList.get(bidPrjStopwkList.size() - 1);
        assertThat(testBidPrjStopwk.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjStopwk.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjStopwk.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBidPrjStopwk.getRespons()).isEqualTo(UPDATED_RESPONS);
        assertThat(testBidPrjStopwk.getDuty()).isEqualTo(UPDATED_DUTY);
        assertThat(testBidPrjStopwk.getPrmNo()).isEqualTo(UPDATED_PRM_NO);
        assertThat(testBidPrjStopwk.getNegoUnit()).isEqualTo(UPDATED_NEGO_UNIT);
        assertThat(testBidPrjStopwk.getStopReson()).isEqualTo(UPDATED_STOP_RESON);
        assertThat(testBidPrjStopwk.getNegoLevel()).isEqualTo(UPDATED_NEGO_LEVEL);
        assertThat(testBidPrjStopwk.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
        assertThat(testBidPrjStopwk.getColumn2()).isEqualTo(UPDATED_COLUMN_2);
        assertThat(testBidPrjStopwk.getColumn3()).isEqualTo(UPDATED_COLUMN_3);
        assertThat(testBidPrjStopwk.getColumn4()).isEqualTo(UPDATED_COLUMN_4);
        assertThat(testBidPrjStopwk.getColumn5()).isEqualTo(UPDATED_COLUMN_5);
        assertThat(testBidPrjStopwk.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjStopwk.geteRemark()).isEqualTo(UPDATED_E_REMARK);
        assertThat(testBidPrjStopwk.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjStopwk.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjStopwk.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjStopwk.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjStopwk.getNoCntDay()).isEqualTo(UPDATED_NO_CNT_DAY);
        assertThat(testBidPrjStopwk.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjStopwk.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjStopwk.getPeDate()).isEqualTo(UPDATED_PE_DATE);
        assertThat(testBidPrjStopwk.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

//    @Test
//    @Transactional
//    void putNonExistingBidPrjStopwk() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();
//        bidPrjStopwk.setId(count.incrementAndGet());
//
//        // Create the BidPrjStopwk
//        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjStopwkMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjStopwkDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStopwk in the database
//        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
//        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithIdMismatchBidPrjStopwk() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();
//        bidPrjStopwk.setId(count.incrementAndGet());
//
//        // Create the BidPrjStopwk
//        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjStopwkMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStopwk in the database
//        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
//        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithMissingIdPathParamBidPrjStopwk() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();
//        bidPrjStopwk.setId(count.incrementAndGet());
//
//        // Create the BidPrjStopwk
//        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjStopwkMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjStopwk in the database
//        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
//        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void partialUpdateBidPrjStopwkWithPatch() throws Exception {
        // Initialize the database
        bidPrjStopwkRepository.saveAndFlush(bidPrjStopwk);

        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();

        // Update the bidPrjStopwk using partial update
        BidPrjStopwk partialUpdatedBidPrjStopwk = new BidPrjStopwk();
        partialUpdatedBidPrjStopwk.setId(bidPrjStopwk.getId());

        partialUpdatedBidPrjStopwk
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .duty(UPDATED_DUTY)
            .prmNo(UPDATED_PRM_NO)
            .negoUnit(UPDATED_NEGO_UNIT)
            .stopReson(UPDATED_STOP_RESON)
            .column2(UPDATED_COLUMN_2)
            .column5(UPDATED_COLUMN_5)
            .eRemark(UPDATED_E_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .peDate(UPDATED_PE_DATE)
            .endDate(UPDATED_END_DATE);

        restBidPrjStopwkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjStopwk.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjStopwk))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjStopwk in the database
        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
        BidPrjStopwk testBidPrjStopwk = bidPrjStopwkList.get(bidPrjStopwkList.size() - 1);
        assertThat(testBidPrjStopwk.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjStopwk.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjStopwk.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBidPrjStopwk.getRespons()).isEqualTo(DEFAULT_RESPONS);
        assertThat(testBidPrjStopwk.getDuty()).isEqualTo(UPDATED_DUTY);
        assertThat(testBidPrjStopwk.getPrmNo()).isEqualTo(UPDATED_PRM_NO);
        assertThat(testBidPrjStopwk.getNegoUnit()).isEqualTo(UPDATED_NEGO_UNIT);
        assertThat(testBidPrjStopwk.getStopReson()).isEqualTo(UPDATED_STOP_RESON);
        assertThat(testBidPrjStopwk.getNegoLevel()).isEqualTo(DEFAULT_NEGO_LEVEL);
        assertThat(testBidPrjStopwk.getColumn1()).isEqualTo(DEFAULT_COLUMN_1);
        assertThat(testBidPrjStopwk.getColumn2()).isEqualTo(UPDATED_COLUMN_2);
        assertThat(testBidPrjStopwk.getColumn3()).isEqualTo(DEFAULT_COLUMN_3);
        assertThat(testBidPrjStopwk.getColumn4()).isEqualTo(DEFAULT_COLUMN_4);
        assertThat(testBidPrjStopwk.getColumn5()).isEqualTo(UPDATED_COLUMN_5);
        assertThat(testBidPrjStopwk.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBidPrjStopwk.geteRemark()).isEqualTo(UPDATED_E_REMARK);
        assertThat(testBidPrjStopwk.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjStopwk.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjStopwk.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjStopwk.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjStopwk.getNoCntDay()).isEqualTo(DEFAULT_NO_CNT_DAY);
        assertThat(testBidPrjStopwk.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjStopwk.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjStopwk.getPeDate()).isEqualTo(UPDATED_PE_DATE);
        assertThat(testBidPrjStopwk.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjStopwkWithPatch() throws Exception {
        // Initialize the database
        bidPrjStopwkRepository.saveAndFlush(bidPrjStopwk);

        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();

        // Update the bidPrjStopwk using partial update
        BidPrjStopwk partialUpdatedBidPrjStopwk = new BidPrjStopwk();
        partialUpdatedBidPrjStopwk.setId(bidPrjStopwk.getId());

        partialUpdatedBidPrjStopwk
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .startDate(UPDATED_START_DATE)
            .respons(UPDATED_RESPONS)
            .duty(UPDATED_DUTY)
            .prmNo(UPDATED_PRM_NO)
            .negoUnit(UPDATED_NEGO_UNIT)
            .stopReson(UPDATED_STOP_RESON)
            .negoLevel(UPDATED_NEGO_LEVEL)
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column4(UPDATED_COLUMN_4)
            .column5(UPDATED_COLUMN_5)
            .remark(UPDATED_REMARK)
            .eRemark(UPDATED_E_REMARK)
            .uIp(UPDATED_U_IP)
            .createUser(UPDATED_CREATE_USER)
            .updateUser(UPDATED_UPDATE_USER)
            .confOut(UPDATED_CONF_OUT)
            .noCntDay(UPDATED_NO_CNT_DAY)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .peDate(UPDATED_PE_DATE)
            .endDate(UPDATED_END_DATE);

        restBidPrjStopwkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjStopwk.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjStopwk))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjStopwk in the database
        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
        BidPrjStopwk testBidPrjStopwk = bidPrjStopwkList.get(bidPrjStopwkList.size() - 1);
        assertThat(testBidPrjStopwk.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjStopwk.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjStopwk.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBidPrjStopwk.getRespons()).isEqualTo(UPDATED_RESPONS);
        assertThat(testBidPrjStopwk.getDuty()).isEqualTo(UPDATED_DUTY);
        assertThat(testBidPrjStopwk.getPrmNo()).isEqualTo(UPDATED_PRM_NO);
        assertThat(testBidPrjStopwk.getNegoUnit()).isEqualTo(UPDATED_NEGO_UNIT);
        assertThat(testBidPrjStopwk.getStopReson()).isEqualTo(UPDATED_STOP_RESON);
        assertThat(testBidPrjStopwk.getNegoLevel()).isEqualTo(UPDATED_NEGO_LEVEL);
        assertThat(testBidPrjStopwk.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
        assertThat(testBidPrjStopwk.getColumn2()).isEqualTo(UPDATED_COLUMN_2);
        assertThat(testBidPrjStopwk.getColumn3()).isEqualTo(UPDATED_COLUMN_3);
        assertThat(testBidPrjStopwk.getColumn4()).isEqualTo(UPDATED_COLUMN_4);
        assertThat(testBidPrjStopwk.getColumn5()).isEqualTo(UPDATED_COLUMN_5);
        assertThat(testBidPrjStopwk.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjStopwk.geteRemark()).isEqualTo(UPDATED_E_REMARK);
        assertThat(testBidPrjStopwk.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjStopwk.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjStopwk.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjStopwk.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjStopwk.getNoCntDay()).isEqualTo(UPDATED_NO_CNT_DAY);
        assertThat(testBidPrjStopwk.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjStopwk.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjStopwk.getPeDate()).isEqualTo(UPDATED_PE_DATE);
        assertThat(testBidPrjStopwk.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

//    @Test
//    @Transactional
//    void patchNonExistingBidPrjStopwk() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();
//        bidPrjStopwk.setId(count.incrementAndGet());
//
//        // Create the BidPrjStopwk
//        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjStopwkMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjStopwkDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStopwk in the database
//        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
//        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithIdMismatchBidPrjStopwk() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();
//        bidPrjStopwk.setId(count.incrementAndGet());
//
//        // Create the BidPrjStopwk
//        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjStopwkMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjStopwk in the database
//        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
//        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamBidPrjStopwk() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjStopwkRepository.findAll().size();
//        bidPrjStopwk.setId(count.incrementAndGet());
//
//        // Create the BidPrjStopwk
//        BidPrjStopwkDTO bidPrjStopwkDTO = bidPrjStopwkMapper.toDto(bidPrjStopwk);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjStopwkMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjStopwkDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjStopwk in the database
//        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
//        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void deleteBidPrjStopwk() throws Exception {
        // Initialize the database
        bidPrjStopwkRepository.saveAndFlush(bidPrjStopwk);

        int databaseSizeBeforeDelete = bidPrjStopwkRepository.findAll().size();

        // Delete the bidPrjStopwk
        restBidPrjStopwkMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjStopwk.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjStopwk> bidPrjStopwkList = bidPrjStopwkRepository.findAll();
        assertThat(bidPrjStopwkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
