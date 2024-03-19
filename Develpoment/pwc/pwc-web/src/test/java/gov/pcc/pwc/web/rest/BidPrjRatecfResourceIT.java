package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjRatecf;
import gov.pcc.pwc.repository.BidPrjRatecfRepository;
import gov.pcc.pwc.service.dto.BidPrjRatecfDTO;
import gov.pcc.pwc.service.mapper.BidPrjRatecfMapper;
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
 * Integration tests for the {@link BidPrjRatecfResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjRatecfResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_SYR = "AAA";
    private static final String UPDATED_SYR = "BBB";

    private static final String DEFAULT_MONTH = "AA";
    private static final String UPDATED_MONTH = "BB";

    private static final String DEFAULT_CHKUNIT = "AAAAAAAAAA";
    private static final String UPDATED_CHKUNIT = "BBBBBBBBBB";

    private static final String DEFAULT_MFT = "AAA";
    private static final String UPDATED_MFT = "BBB";

    private static final BigDecimal DEFAULT_PTRATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PTRATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PTRATE_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_PTRATE_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ATRATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ATRATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ATRATE_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_ATRATE_1 = new BigDecimal(2);

    private static final String DEFAULT_MFAS = "AAAAAAAAAA";
    private static final String UPDATED_MFAS = "BBBBBBBBBB";

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

    private static final String DEFAULT_MFTNO = "AAAAAAAAAA";
    private static final String UPDATED_MFTNO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-ratecfs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjRatecfRepository bidPrjRatecfRepository;

    @Autowired
    private BidPrjRatecfMapper bidPrjRatecfMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjRatecfMockMvc;

    private BidPrjRatecf bidPrjRatecf;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjRatecf createEntity(EntityManager em) {
        BidPrjRatecf bidPrjRatecf = new BidPrjRatecf()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .syr(DEFAULT_SYR)
            .month(DEFAULT_MONTH)
            .chkunit(DEFAULT_CHKUNIT)
            .mft(DEFAULT_MFT)
            .ptrate(DEFAULT_PTRATE)
            .ptrate1(DEFAULT_PTRATE_1)
            .atrate(DEFAULT_ATRATE)
            .atrate1(DEFAULT_ATRATE_1)
            .mfas(DEFAULT_MFAS)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .uIp(DEFAULT_U_IP)
            .mftno(DEFAULT_MFTNO);
        return bidPrjRatecf;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjRatecf createUpdatedEntity(EntityManager em) {
        BidPrjRatecf bidPrjRatecf = new BidPrjRatecf()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .syr(UPDATED_SYR)
            .month(UPDATED_MONTH)
            .chkunit(UPDATED_CHKUNIT)
            .mft(UPDATED_MFT)
            .ptrate(UPDATED_PTRATE)
            .ptrate1(UPDATED_PTRATE_1)
            .atrate(UPDATED_ATRATE)
            .atrate1(UPDATED_ATRATE_1)
            .mfas(UPDATED_MFAS)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .mftno(UPDATED_MFTNO);
        return bidPrjRatecf;
    }

    @BeforeEach
    public void initTest() {
        bidPrjRatecf = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjRatecf() throws Exception {
        int databaseSizeBeforeCreate = bidPrjRatecfRepository.findAll().size();
        // Create the BidPrjRatecf
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);
        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjRatecf in the database
        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjRatecf testBidPrjRatecf = bidPrjRatecfList.get(bidPrjRatecfList.size() - 1);
        assertThat(testBidPrjRatecf.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjRatecf.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjRatecf.getSyr()).isEqualTo(DEFAULT_SYR);
        assertThat(testBidPrjRatecf.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBidPrjRatecf.getChkunit()).isEqualTo(DEFAULT_CHKUNIT);
        assertThat(testBidPrjRatecf.getMft()).isEqualTo(DEFAULT_MFT);
        assertThat(testBidPrjRatecf.getPtrate()).isEqualByComparingTo(DEFAULT_PTRATE);
        assertThat(testBidPrjRatecf.getPtrate1()).isEqualByComparingTo(DEFAULT_PTRATE_1);
        assertThat(testBidPrjRatecf.getAtrate()).isEqualByComparingTo(DEFAULT_ATRATE);
        assertThat(testBidPrjRatecf.getAtrate1()).isEqualByComparingTo(DEFAULT_ATRATE_1);
        assertThat(testBidPrjRatecf.getMfas()).isEqualTo(DEFAULT_MFAS);
        assertThat(testBidPrjRatecf.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjRatecf.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjRatecf.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjRatecf.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjRatecf.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjRatecf.getMftno()).isEqualTo(DEFAULT_MFTNO);
    }

    @Test
    @Transactional
    void createBidPrjRatecfWithExistingId() throws Exception {
        // Create the BidPrjRatecf with an existing ID
//        bidPrjRatecf.setId(1L);
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        int databaseSizeBeforeCreate = bidPrjRatecfRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjRatecf in the database
        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjRatecfRepository.findAll().size();
        // set the field null
        bidPrjRatecf.setWkut(null);

        // Create the BidPrjRatecf, which fails.
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjRatecfRepository.findAll().size();
        // set the field null
        bidPrjRatecf.setPrjno(null);

        // Create the BidPrjRatecf, which fails.
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSyrIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjRatecfRepository.findAll().size();
        // set the field null
        bidPrjRatecf.setSyr(null);

        // Create the BidPrjRatecf, which fails.
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMonthIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjRatecfRepository.findAll().size();
        // set the field null
        bidPrjRatecf.setMonth(null);

        // Create the BidPrjRatecf, which fails.
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkChkunitIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjRatecfRepository.findAll().size();
        // set the field null
        bidPrjRatecf.setChkunit(null);

        // Create the BidPrjRatecf, which fails.
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMftIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjRatecfRepository.findAll().size();
        // set the field null
        bidPrjRatecf.setMft(null);

        // Create the BidPrjRatecf, which fails.
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjRatecfRepository.findAll().size();
        // set the field null
        bidPrjRatecf.setCreateDate(null);

        // Create the BidPrjRatecf, which fails.
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjRatecfRepository.findAll().size();
        // set the field null
        bidPrjRatecf.setCreateUser(null);

        // Create the BidPrjRatecf, which fails.
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBidPrjRatecfs() throws Exception {
        // Initialize the database
        bidPrjRatecfRepository.saveAndFlush(bidPrjRatecf);

        // Get all the bidPrjRatecfList
        restBidPrjRatecfMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjRatecf.getId().intValue())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].syr").value(hasItem(DEFAULT_SYR)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].chkunit").value(hasItem(DEFAULT_CHKUNIT)))
            .andExpect(jsonPath("$.[*].mft").value(hasItem(DEFAULT_MFT)))
            .andExpect(jsonPath("$.[*].ptrate").value(hasItem(sameNumber(DEFAULT_PTRATE))))
            .andExpect(jsonPath("$.[*].ptrate1").value(hasItem(sameNumber(DEFAULT_PTRATE_1))))
            .andExpect(jsonPath("$.[*].atrate").value(hasItem(sameNumber(DEFAULT_ATRATE))))
            .andExpect(jsonPath("$.[*].atrate1").value(hasItem(sameNumber(DEFAULT_ATRATE_1))))
            .andExpect(jsonPath("$.[*].mfas").value(hasItem(DEFAULT_MFAS)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
            .andExpect(jsonPath("$.[*].mftno").value(hasItem(DEFAULT_MFTNO)));
    }

    @Test
    @Transactional
    void getBidPrjRatecf() throws Exception {
        // Initialize the database
        bidPrjRatecfRepository.saveAndFlush(bidPrjRatecf);

        // Get the bidPrjRatecf
        restBidPrjRatecfMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjRatecf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjRatecf.getId().intValue()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.syr").value(DEFAULT_SYR))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
            .andExpect(jsonPath("$.chkunit").value(DEFAULT_CHKUNIT))
            .andExpect(jsonPath("$.mft").value(DEFAULT_MFT))
            .andExpect(jsonPath("$.ptrate").value(sameNumber(DEFAULT_PTRATE)))
            .andExpect(jsonPath("$.ptrate1").value(sameNumber(DEFAULT_PTRATE_1)))
            .andExpect(jsonPath("$.atrate").value(sameNumber(DEFAULT_ATRATE)))
            .andExpect(jsonPath("$.atrate1").value(sameNumber(DEFAULT_ATRATE_1)))
            .andExpect(jsonPath("$.mfas").value(DEFAULT_MFAS))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
            .andExpect(jsonPath("$.mftno").value(DEFAULT_MFTNO));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjRatecf() throws Exception {
        // Get the bidPrjRatecf
        restBidPrjRatecfMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjRatecf() throws Exception {
        // Initialize the database
        bidPrjRatecfRepository.saveAndFlush(bidPrjRatecf);

        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();

        // Update the bidPrjRatecf
        BidPrjRatecf updatedBidPrjRatecf = bidPrjRatecfRepository.findById(bidPrjRatecf.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjRatecf are not directly saved in db
        em.detach(updatedBidPrjRatecf);
        updatedBidPrjRatecf
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .syr(UPDATED_SYR)
            .month(UPDATED_MONTH)
            .chkunit(UPDATED_CHKUNIT)
            .mft(UPDATED_MFT)
            .ptrate(UPDATED_PTRATE)
            .ptrate1(UPDATED_PTRATE_1)
            .atrate(UPDATED_ATRATE)
            .atrate1(UPDATED_ATRATE_1)
            .mfas(UPDATED_MFAS)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .mftno(UPDATED_MFTNO);
        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(updatedBidPrjRatecf);

        restBidPrjRatecfMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjRatecfDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjRatecf in the database
        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
        BidPrjRatecf testBidPrjRatecf = bidPrjRatecfList.get(bidPrjRatecfList.size() - 1);
        assertThat(testBidPrjRatecf.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjRatecf.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjRatecf.getSyr()).isEqualTo(UPDATED_SYR);
        assertThat(testBidPrjRatecf.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjRatecf.getChkunit()).isEqualTo(UPDATED_CHKUNIT);
        assertThat(testBidPrjRatecf.getMft()).isEqualTo(UPDATED_MFT);
        assertThat(testBidPrjRatecf.getPtrate()).isEqualByComparingTo(UPDATED_PTRATE);
        assertThat(testBidPrjRatecf.getPtrate1()).isEqualByComparingTo(UPDATED_PTRATE_1);
        assertThat(testBidPrjRatecf.getAtrate()).isEqualByComparingTo(UPDATED_ATRATE);
        assertThat(testBidPrjRatecf.getAtrate1()).isEqualByComparingTo(UPDATED_ATRATE_1);
        assertThat(testBidPrjRatecf.getMfas()).isEqualTo(UPDATED_MFAS);
        assertThat(testBidPrjRatecf.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjRatecf.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjRatecf.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjRatecf.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjRatecf.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjRatecf.getMftno()).isEqualTo(UPDATED_MFTNO);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjRatecf() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();
//        bidPrjRatecf.setId(count.incrementAndGet());
//
//        // Create the BidPrjRatecf
//        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjRatecfMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjRatecfDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjRatecf in the database
//        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
//        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjRatecf() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();
//        bidPrjRatecf.setId(count.incrementAndGet());
//
//        // Create the BidPrjRatecf
//        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjRatecfMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjRatecf in the database
//        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
//        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjRatecf() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();
//        bidPrjRatecf.setId(count.incrementAndGet());
//
//        // Create the BidPrjRatecf
//        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjRatecfMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjRatecf in the database
//        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
//        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjRatecfWithPatch() throws Exception {
        // Initialize the database
        bidPrjRatecfRepository.saveAndFlush(bidPrjRatecf);

        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();

        // Update the bidPrjRatecf using partial update
        BidPrjRatecf partialUpdatedBidPrjRatecf = new BidPrjRatecf();
        partialUpdatedBidPrjRatecf.setId(bidPrjRatecf.getId());

        partialUpdatedBidPrjRatecf.wkut(UPDATED_WKUT).mft(UPDATED_MFT).createUser(UPDATED_CREATE_USER).updateDate(UPDATED_UPDATE_DATE);

        restBidPrjRatecfMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjRatecf.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjRatecf))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjRatecf in the database
        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
        BidPrjRatecf testBidPrjRatecf = bidPrjRatecfList.get(bidPrjRatecfList.size() - 1);
        assertThat(testBidPrjRatecf.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjRatecf.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjRatecf.getSyr()).isEqualTo(DEFAULT_SYR);
        assertThat(testBidPrjRatecf.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBidPrjRatecf.getChkunit()).isEqualTo(DEFAULT_CHKUNIT);
        assertThat(testBidPrjRatecf.getMft()).isEqualTo(UPDATED_MFT);
        assertThat(testBidPrjRatecf.getPtrate()).isEqualByComparingTo(DEFAULT_PTRATE);
        assertThat(testBidPrjRatecf.getPtrate1()).isEqualByComparingTo(DEFAULT_PTRATE_1);
        assertThat(testBidPrjRatecf.getAtrate()).isEqualByComparingTo(DEFAULT_ATRATE);
        assertThat(testBidPrjRatecf.getAtrate1()).isEqualByComparingTo(DEFAULT_ATRATE_1);
        assertThat(testBidPrjRatecf.getMfas()).isEqualTo(DEFAULT_MFAS);
        assertThat(testBidPrjRatecf.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjRatecf.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjRatecf.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjRatecf.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjRatecf.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjRatecf.getMftno()).isEqualTo(DEFAULT_MFTNO);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjRatecfWithPatch() throws Exception {
        // Initialize the database
        bidPrjRatecfRepository.saveAndFlush(bidPrjRatecf);

        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();

        // Update the bidPrjRatecf using partial update
        BidPrjRatecf partialUpdatedBidPrjRatecf = new BidPrjRatecf();
        partialUpdatedBidPrjRatecf.setId(bidPrjRatecf.getId());

        partialUpdatedBidPrjRatecf
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .syr(UPDATED_SYR)
            .month(UPDATED_MONTH)
            .chkunit(UPDATED_CHKUNIT)
            .mft(UPDATED_MFT)
            .ptrate(UPDATED_PTRATE)
            .ptrate1(UPDATED_PTRATE_1)
            .atrate(UPDATED_ATRATE)
            .atrate1(UPDATED_ATRATE_1)
            .mfas(UPDATED_MFAS)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .mftno(UPDATED_MFTNO);

        restBidPrjRatecfMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjRatecf.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjRatecf))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjRatecf in the database
        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
        BidPrjRatecf testBidPrjRatecf = bidPrjRatecfList.get(bidPrjRatecfList.size() - 1);
        assertThat(testBidPrjRatecf.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjRatecf.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjRatecf.getSyr()).isEqualTo(UPDATED_SYR);
        assertThat(testBidPrjRatecf.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjRatecf.getChkunit()).isEqualTo(UPDATED_CHKUNIT);
        assertThat(testBidPrjRatecf.getMft()).isEqualTo(UPDATED_MFT);
        assertThat(testBidPrjRatecf.getPtrate()).isEqualByComparingTo(UPDATED_PTRATE);
        assertThat(testBidPrjRatecf.getPtrate1()).isEqualByComparingTo(UPDATED_PTRATE_1);
        assertThat(testBidPrjRatecf.getAtrate()).isEqualByComparingTo(UPDATED_ATRATE);
        assertThat(testBidPrjRatecf.getAtrate1()).isEqualByComparingTo(UPDATED_ATRATE_1);
        assertThat(testBidPrjRatecf.getMfas()).isEqualTo(UPDATED_MFAS);
        assertThat(testBidPrjRatecf.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjRatecf.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjRatecf.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjRatecf.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjRatecf.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjRatecf.getMftno()).isEqualTo(UPDATED_MFTNO);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjRatecf() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();
//        bidPrjRatecf.setId(count.incrementAndGet());
//
//        // Create the BidPrjRatecf
//        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjRatecfMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjRatecfDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjRatecf in the database
//        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
//        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjRatecf() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();
//        bidPrjRatecf.setId(count.incrementAndGet());
//
//        // Create the BidPrjRatecf
//        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjRatecfMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjRatecf in the database
//        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
//        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjRatecf() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjRatecfRepository.findAll().size();
//        bidPrjRatecf.setId(count.incrementAndGet());
//
//        // Create the BidPrjRatecf
//        BidPrjRatecfDTO bidPrjRatecfDTO = bidPrjRatecfMapper.toDto(bidPrjRatecf);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjRatecfMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjRatecfDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjRatecf in the database
//        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
//        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjRatecf() throws Exception {
        // Initialize the database
        bidPrjRatecfRepository.saveAndFlush(bidPrjRatecf);

        int databaseSizeBeforeDelete = bidPrjRatecfRepository.findAll().size();

        // Delete the bidPrjRatecf
        restBidPrjRatecfMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjRatecf.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjRatecf> bidPrjRatecfList = bidPrjRatecfRepository.findAll();
        assertThat(bidPrjRatecfList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
