package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidGovPrecheck;
import gov.pcc.pwc.domain.BidGovPrecheckPrimaryKey;
import gov.pcc.pwc.repository.BidGovPrecheckRepository;
import gov.pcc.pwc.service.dto.BidGovPrecheckDTO;
import gov.pcc.pwc.service.mapper.BidGovPrecheckMapper;
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
 * Integration tests for the {@link BidGovPrecheckResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidGovPrecheckResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHECK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECK_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CHECK_DATE_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECK_DATE_END = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CHECK_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_SUBUNIT = "AA";
    private static final String UPDATED_CHECK_SUBUNIT = "BB";

    private static final String DEFAULT_CHECKER_1 = "AAAAAAAAAA";
    private static final String UPDATED_CHECKER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_LEADER = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_LEADER = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_MEMBER = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_MEMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_STATUS = "A";
    private static final String UPDATED_DATA_STATUS = "B";

    private static final String DEFAULT_METHOD = "A";
    private static final String UPDATED_METHOD = "B";

    private static final String DEFAULT_CHECK_PURP = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_PURP = "BBBBBBBBBB";

    private static final String DEFAULT_CHECKER_TEL = "AAAAAAAAAA";
    private static final String UPDATED_CHECKER_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_CAN = "A";
    private static final String UPDATED_CAN = "B";

    private static final String DEFAULT_COLUMN_1 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_1 = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-gov-prechecks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidGovPrecheckRepository bidGovPrecheckRepository;

    @Autowired
    private BidGovPrecheckMapper bidGovPrecheckMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidGovPrecheckMockMvc;

    private BidGovPrecheck bidGovPrecheck;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidGovPrecheck createEntity(EntityManager em) {
        BidGovPrecheck bidGovPrecheck = new BidGovPrecheck()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .checkDate(DEFAULT_CHECK_DATE)
            .checkDateEnd(DEFAULT_CHECK_DATE_END)
            .checkUnit(DEFAULT_CHECK_UNIT)
            .checkSubunit(DEFAULT_CHECK_SUBUNIT)
            .checker1(DEFAULT_CHECKER_1)
            .checkLeader(DEFAULT_CHECK_LEADER)
            .checkMember(DEFAULT_CHECK_MEMBER)
            .dataStatus(DEFAULT_DATA_STATUS)
            .method(DEFAULT_METHOD)
            .checkPurp(DEFAULT_CHECK_PURP)
            .checkerTel(DEFAULT_CHECKER_TEL)
            .can(DEFAULT_CAN)
            .column1(DEFAULT_COLUMN_1)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER);
        return bidGovPrecheck;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidGovPrecheck createUpdatedEntity(EntityManager em) {
        BidGovPrecheck bidGovPrecheck = new BidGovPrecheck()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .checkDate(UPDATED_CHECK_DATE)
            .checkDateEnd(UPDATED_CHECK_DATE_END)
            .checkUnit(UPDATED_CHECK_UNIT)
            .checkSubunit(UPDATED_CHECK_SUBUNIT)
            .checker1(UPDATED_CHECKER_1)
            .checkLeader(UPDATED_CHECK_LEADER)
            .checkMember(UPDATED_CHECK_MEMBER)
            .dataStatus(UPDATED_DATA_STATUS)
            .method(UPDATED_METHOD)
            .checkPurp(UPDATED_CHECK_PURP)
            .checkerTel(UPDATED_CHECKER_TEL)
            .can(UPDATED_CAN)
            .column1(UPDATED_COLUMN_1)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        return bidGovPrecheck;
    }

    @BeforeEach
    public void initTest() {
        bidGovPrecheck = createEntity(em);
    }

    @Test
    @Transactional
    void createBidGovPrecheck() throws Exception {
        int databaseSizeBeforeCreate = bidGovPrecheckRepository.findAll().size();
        // Create the BidGovPrecheck
        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);
        restBidGovPrecheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidGovPrecheck in the database
        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeCreate + 1);
        BidGovPrecheck testBidGovPrecheck = bidGovPrecheckList.get(bidGovPrecheckList.size() - 1);
        assertThat(testBidGovPrecheck.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidGovPrecheck.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidGovPrecheck.getCheckDate()).isEqualTo(DEFAULT_CHECK_DATE);
        assertThat(testBidGovPrecheck.getCheckDateEnd()).isEqualTo(DEFAULT_CHECK_DATE_END);
        assertThat(testBidGovPrecheck.getCheckUnit()).isEqualTo(DEFAULT_CHECK_UNIT);
        assertThat(testBidGovPrecheck.getCheckSubunit()).isEqualTo(DEFAULT_CHECK_SUBUNIT);
        assertThat(testBidGovPrecheck.getChecker1()).isEqualTo(DEFAULT_CHECKER_1);
        assertThat(testBidGovPrecheck.getCheckLeader()).isEqualTo(DEFAULT_CHECK_LEADER);
        assertThat(testBidGovPrecheck.getCheckMember()).isEqualTo(DEFAULT_CHECK_MEMBER);
        assertThat(testBidGovPrecheck.getDataStatus()).isEqualTo(DEFAULT_DATA_STATUS);
        assertThat(testBidGovPrecheck.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testBidGovPrecheck.getCheckPurp()).isEqualTo(DEFAULT_CHECK_PURP);
        assertThat(testBidGovPrecheck.getCheckerTel()).isEqualTo(DEFAULT_CHECKER_TEL);
        assertThat(testBidGovPrecheck.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testBidGovPrecheck.getColumn1()).isEqualTo(DEFAULT_COLUMN_1);
        assertThat(testBidGovPrecheck.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidGovPrecheck.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidGovPrecheck.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidGovPrecheck.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    void createBidGovPrecheckWithExistingId() throws Exception {
        // Create the BidGovPrecheck with an existing ID
        bidGovPrecheck.setId(new BidGovPrecheckPrimaryKey("123","456", LocalDate.now()));
        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);

        int databaseSizeBeforeCreate = bidGovPrecheckRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidGovPrecheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidGovPrecheck in the database
        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidGovPrecheckRepository.findAll().size();
        // set the field null
        bidGovPrecheck.setWkut(null);

        // Create the BidGovPrecheck, which fails.
        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);

        restBidGovPrecheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidGovPrecheckRepository.findAll().size();
        // set the field null
        bidGovPrecheck.setPrjno(null);

        // Create the BidGovPrecheck, which fails.
        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);

        restBidGovPrecheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCheckDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidGovPrecheckRepository.findAll().size();
        // set the field null
        bidGovPrecheck.setCheckDate(null);

        // Create the BidGovPrecheck, which fails.
        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);

        restBidGovPrecheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidGovPrecheckRepository.findAll().size();
        // set the field null
        bidGovPrecheck.setCreateDate(null);

        // Create the BidGovPrecheck, which fails.
        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);

        restBidGovPrecheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidGovPrecheckRepository.findAll().size();
        // set the field null
        bidGovPrecheck.setCreateUser(null);

        // Create the BidGovPrecheck, which fails.
        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);

        restBidGovPrecheckMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    void getAllBidGovPrechecks() throws Exception {
//        // Initialize the database
//        bidGovPrecheckRepository.saveAndFlush(bidGovPrecheck);
//
//        // Get all the bidGovPrecheckList
//        restBidGovPrecheckMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidGovPrecheck.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].checkDate").value(hasItem(DEFAULT_CHECK_DATE.toString())))
//            .andExpect(jsonPath("$.[*].checkDateEnd").value(hasItem(DEFAULT_CHECK_DATE_END.toString())))
//            .andExpect(jsonPath("$.[*].checkUnit").value(hasItem(DEFAULT_CHECK_UNIT)))
//            .andExpect(jsonPath("$.[*].checkSubunit").value(hasItem(DEFAULT_CHECK_SUBUNIT)))
//            .andExpect(jsonPath("$.[*].checker1").value(hasItem(DEFAULT_CHECKER_1)))
//            .andExpect(jsonPath("$.[*].checkLeader").value(hasItem(DEFAULT_CHECK_LEADER)))
//            .andExpect(jsonPath("$.[*].checkMember").value(hasItem(DEFAULT_CHECK_MEMBER)))
//            .andExpect(jsonPath("$.[*].dataStatus").value(hasItem(DEFAULT_DATA_STATUS)))
//            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD)))
//            .andExpect(jsonPath("$.[*].checkPurp").value(hasItem(DEFAULT_CHECK_PURP)))
//            .andExpect(jsonPath("$.[*].checkerTel").value(hasItem(DEFAULT_CHECKER_TEL)))
//            .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN)))
//            .andExpect(jsonPath("$.[*].column1").value(hasItem(DEFAULT_COLUMN_1)))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)));
//    }

//    @Test
//    @Transactional
//    void getBidGovPrecheck() throws Exception {
//        // Initialize the database
//        bidGovPrecheckRepository.saveAndFlush(bidGovPrecheck);
//
//        // Get the bidGovPrecheck
//        restBidGovPrecheckMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidGovPrecheck.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidGovPrecheck.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.checkDate").value(DEFAULT_CHECK_DATE.toString()))
//            .andExpect(jsonPath("$.checkDateEnd").value(DEFAULT_CHECK_DATE_END.toString()))
//            .andExpect(jsonPath("$.checkUnit").value(DEFAULT_CHECK_UNIT))
//            .andExpect(jsonPath("$.checkSubunit").value(DEFAULT_CHECK_SUBUNIT))
//            .andExpect(jsonPath("$.checker1").value(DEFAULT_CHECKER_1))
//            .andExpect(jsonPath("$.checkLeader").value(DEFAULT_CHECK_LEADER))
//            .andExpect(jsonPath("$.checkMember").value(DEFAULT_CHECK_MEMBER))
//            .andExpect(jsonPath("$.dataStatus").value(DEFAULT_DATA_STATUS))
//            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD))
//            .andExpect(jsonPath("$.checkPurp").value(DEFAULT_CHECK_PURP))
//            .andExpect(jsonPath("$.checkerTel").value(DEFAULT_CHECKER_TEL))
//            .andExpect(jsonPath("$.can").value(DEFAULT_CAN))
//            .andExpect(jsonPath("$.column1").value(DEFAULT_COLUMN_1))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER));
//    }

    @Test
    @Transactional
    void getNonExistingBidGovPrecheck() throws Exception {
        // Get the bidGovPrecheck
        restBidGovPrecheckMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidGovPrecheck() throws Exception {
        // Initialize the database
        bidGovPrecheckRepository.saveAndFlush(bidGovPrecheck);

        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();

        // Update the bidGovPrecheck
        BidGovPrecheck updatedBidGovPrecheck = bidGovPrecheckRepository.findById(bidGovPrecheck.getId()).get();
        // Disconnect from session so that the updates on updatedBidGovPrecheck are not directly saved in db
        em.detach(updatedBidGovPrecheck);
        updatedBidGovPrecheck
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .checkDate(UPDATED_CHECK_DATE)
            .checkDateEnd(UPDATED_CHECK_DATE_END)
            .checkUnit(UPDATED_CHECK_UNIT)
            .checkSubunit(UPDATED_CHECK_SUBUNIT)
            .checker1(UPDATED_CHECKER_1)
            .checkLeader(UPDATED_CHECK_LEADER)
            .checkMember(UPDATED_CHECK_MEMBER)
            .dataStatus(UPDATED_DATA_STATUS)
            .method(UPDATED_METHOD)
            .checkPurp(UPDATED_CHECK_PURP)
            .checkerTel(UPDATED_CHECKER_TEL)
            .can(UPDATED_CAN)
            .column1(UPDATED_COLUMN_1)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(updatedBidGovPrecheck);

        restBidGovPrecheckMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidGovPrecheckDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidGovPrecheck in the database
        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
        BidGovPrecheck testBidGovPrecheck = bidGovPrecheckList.get(bidGovPrecheckList.size() - 1);
        assertThat(testBidGovPrecheck.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidGovPrecheck.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidGovPrecheck.getCheckDate()).isEqualTo(UPDATED_CHECK_DATE);
        assertThat(testBidGovPrecheck.getCheckDateEnd()).isEqualTo(UPDATED_CHECK_DATE_END);
        assertThat(testBidGovPrecheck.getCheckUnit()).isEqualTo(UPDATED_CHECK_UNIT);
        assertThat(testBidGovPrecheck.getCheckSubunit()).isEqualTo(UPDATED_CHECK_SUBUNIT);
        assertThat(testBidGovPrecheck.getChecker1()).isEqualTo(UPDATED_CHECKER_1);
        assertThat(testBidGovPrecheck.getCheckLeader()).isEqualTo(UPDATED_CHECK_LEADER);
        assertThat(testBidGovPrecheck.getCheckMember()).isEqualTo(UPDATED_CHECK_MEMBER);
        assertThat(testBidGovPrecheck.getDataStatus()).isEqualTo(UPDATED_DATA_STATUS);
        assertThat(testBidGovPrecheck.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testBidGovPrecheck.getCheckPurp()).isEqualTo(UPDATED_CHECK_PURP);
        assertThat(testBidGovPrecheck.getCheckerTel()).isEqualTo(UPDATED_CHECKER_TEL);
        assertThat(testBidGovPrecheck.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testBidGovPrecheck.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
        assertThat(testBidGovPrecheck.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidGovPrecheck.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidGovPrecheck.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidGovPrecheck.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

//    @Test
//    @Transactional
//    void putNonExistingBidGovPrecheck() throws Exception {
//        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();
//        bidGovPrecheck.setId(count.incrementAndGet());
//
//        // Create the BidGovPrecheck
//        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidGovPrecheckMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidGovPrecheckDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidGovPrecheck in the database
//        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
//        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithIdMismatchBidGovPrecheck() throws Exception {
//        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();
//        bidGovPrecheck.setId(count.incrementAndGet());
//
//        // Create the BidGovPrecheck
//        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidGovPrecheckMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidGovPrecheck in the database
//        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
//        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithMissingIdPathParamBidGovPrecheck() throws Exception {
//        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();
//        bidGovPrecheck.setId(count.incrementAndGet());
//
//        // Create the BidGovPrecheck
//        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidGovPrecheckMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidGovPrecheck in the database
//        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
//        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void partialUpdateBidGovPrecheckWithPatch() throws Exception {
//        // Initialize the database
//        bidGovPrecheckRepository.saveAndFlush(bidGovPrecheck);
//
//        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();
//
//        // Update the bidGovPrecheck using partial update
//        BidGovPrecheck partialUpdatedBidGovPrecheck = new BidGovPrecheck();
//        partialUpdatedBidGovPrecheck.setId(bidGovPrecheck.getId());
//
//        partialUpdatedBidGovPrecheck
//            .prjno(UPDATED_PRJNO)
//            .checkDateEnd(UPDATED_CHECK_DATE_END)
//            .checkLeader(UPDATED_CHECK_LEADER)
//            .method(UPDATED_METHOD)
//            .checkPurp(UPDATED_CHECK_PURP)
//            .checkerTel(UPDATED_CHECKER_TEL)
//            .column1(UPDATED_COLUMN_1)
//            .createDate(UPDATED_CREATE_DATE)
//            .updateDate(UPDATED_UPDATE_DATE)
//            .updateUser(UPDATED_UPDATE_USER);
//
//        restBidGovPrecheckMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedBidGovPrecheck.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidGovPrecheck))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the BidGovPrecheck in the database
//        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
//        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
//        BidGovPrecheck testBidGovPrecheck = bidGovPrecheckList.get(bidGovPrecheckList.size() - 1);
//        assertThat(testBidGovPrecheck.getWkut()).isEqualTo(DEFAULT_WKUT);
//        assertThat(testBidGovPrecheck.getPrjno()).isEqualTo(UPDATED_PRJNO);
//        assertThat(testBidGovPrecheck.getCheckDate()).isEqualTo(DEFAULT_CHECK_DATE);
//        assertThat(testBidGovPrecheck.getCheckDateEnd()).isEqualTo(UPDATED_CHECK_DATE_END);
//        assertThat(testBidGovPrecheck.getCheckUnit()).isEqualTo(DEFAULT_CHECK_UNIT);
//        assertThat(testBidGovPrecheck.getCheckSubunit()).isEqualTo(DEFAULT_CHECK_SUBUNIT);
//        assertThat(testBidGovPrecheck.getChecker1()).isEqualTo(DEFAULT_CHECKER_1);
//        assertThat(testBidGovPrecheck.getCheckLeader()).isEqualTo(UPDATED_CHECK_LEADER);
//        assertThat(testBidGovPrecheck.getCheckMember()).isEqualTo(DEFAULT_CHECK_MEMBER);
//        assertThat(testBidGovPrecheck.getDataStatus()).isEqualTo(DEFAULT_DATA_STATUS);
//        assertThat(testBidGovPrecheck.getMethod()).isEqualTo(UPDATED_METHOD);
//        assertThat(testBidGovPrecheck.getCheckPurp()).isEqualTo(UPDATED_CHECK_PURP);
//        assertThat(testBidGovPrecheck.getCheckerTel()).isEqualTo(UPDATED_CHECKER_TEL);
//        assertThat(testBidGovPrecheck.getCan()).isEqualTo(DEFAULT_CAN);
//        assertThat(testBidGovPrecheck.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
//        assertThat(testBidGovPrecheck.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
//        assertThat(testBidGovPrecheck.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
//        assertThat(testBidGovPrecheck.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//        assertThat(testBidGovPrecheck.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//    }

    @Test
    @Transactional
    void fullUpdateBidGovPrecheckWithPatch() throws Exception {
        // Initialize the database
        bidGovPrecheckRepository.saveAndFlush(bidGovPrecheck);

        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();

        // Update the bidGovPrecheck using partial update
        BidGovPrecheck partialUpdatedBidGovPrecheck = new BidGovPrecheck();
        partialUpdatedBidGovPrecheck.setId(bidGovPrecheck.getId());

        partialUpdatedBidGovPrecheck
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .checkDate(UPDATED_CHECK_DATE)
            .checkDateEnd(UPDATED_CHECK_DATE_END)
            .checkUnit(UPDATED_CHECK_UNIT)
            .checkSubunit(UPDATED_CHECK_SUBUNIT)
            .checker1(UPDATED_CHECKER_1)
            .checkLeader(UPDATED_CHECK_LEADER)
            .checkMember(UPDATED_CHECK_MEMBER)
            .dataStatus(UPDATED_DATA_STATUS)
            .method(UPDATED_METHOD)
            .checkPurp(UPDATED_CHECK_PURP)
            .checkerTel(UPDATED_CHECKER_TEL)
            .can(UPDATED_CAN)
            .column1(UPDATED_COLUMN_1)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);

        restBidGovPrecheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidGovPrecheck.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidGovPrecheck))
            )
            .andExpect(status().isOk());

        // Validate the BidGovPrecheck in the database
        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
        BidGovPrecheck testBidGovPrecheck = bidGovPrecheckList.get(bidGovPrecheckList.size() - 1);
        assertThat(testBidGovPrecheck.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidGovPrecheck.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidGovPrecheck.getCheckDate()).isEqualTo(UPDATED_CHECK_DATE);
        assertThat(testBidGovPrecheck.getCheckDateEnd()).isEqualTo(UPDATED_CHECK_DATE_END);
        assertThat(testBidGovPrecheck.getCheckUnit()).isEqualTo(UPDATED_CHECK_UNIT);
        assertThat(testBidGovPrecheck.getCheckSubunit()).isEqualTo(UPDATED_CHECK_SUBUNIT);
        assertThat(testBidGovPrecheck.getChecker1()).isEqualTo(UPDATED_CHECKER_1);
        assertThat(testBidGovPrecheck.getCheckLeader()).isEqualTo(UPDATED_CHECK_LEADER);
        assertThat(testBidGovPrecheck.getCheckMember()).isEqualTo(UPDATED_CHECK_MEMBER);
        assertThat(testBidGovPrecheck.getDataStatus()).isEqualTo(UPDATED_DATA_STATUS);
        assertThat(testBidGovPrecheck.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testBidGovPrecheck.getCheckPurp()).isEqualTo(UPDATED_CHECK_PURP);
        assertThat(testBidGovPrecheck.getCheckerTel()).isEqualTo(UPDATED_CHECKER_TEL);
        assertThat(testBidGovPrecheck.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testBidGovPrecheck.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
        assertThat(testBidGovPrecheck.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidGovPrecheck.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidGovPrecheck.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidGovPrecheck.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

//    @Test
//    @Transactional
//    void patchNonExistingBidGovPrecheck() throws Exception {
//        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();
//        bidGovPrecheck.setId(count.incrementAndGet());
//
//        // Create the BidGovPrecheck
//        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidGovPrecheckMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidGovPrecheckDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidGovPrecheck in the database
//        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
//        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithIdMismatchBidGovPrecheck() throws Exception {
//        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();
//        bidGovPrecheck.setId(count.incrementAndGet());
//
//        // Create the BidGovPrecheck
//        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidGovPrecheckMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidGovPrecheck in the database
//        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
//        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamBidGovPrecheck() throws Exception {
//        int databaseSizeBeforeUpdate = bidGovPrecheckRepository.findAll().size();
//        bidGovPrecheck.setId(count.incrementAndGet());
//
//        // Create the BidGovPrecheck
//        BidGovPrecheckDTO bidGovPrecheckDTO = bidGovPrecheckMapper.toDto(bidGovPrecheck);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidGovPrecheckMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidGovPrecheckDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidGovPrecheck in the database
//        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
//        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void deleteBidGovPrecheck() throws Exception {
        // Initialize the database
        bidGovPrecheckRepository.saveAndFlush(bidGovPrecheck);

        int databaseSizeBeforeDelete = bidGovPrecheckRepository.findAll().size();

        // Delete the bidGovPrecheck
        restBidGovPrecheckMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidGovPrecheck.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidGovPrecheck> bidGovPrecheckList = bidGovPrecheckRepository.findAll();
        assertThat(bidGovPrecheckList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
