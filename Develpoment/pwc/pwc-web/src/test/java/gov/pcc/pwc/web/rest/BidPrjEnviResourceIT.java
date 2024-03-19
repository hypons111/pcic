package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.repository.BidPrjEnviRepository;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import gov.pcc.pwc.service.mapper.BidPrjEnviMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
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
 * Integration tests for the {@link BidPrjEnviResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjEnviResourceIT {

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final Instant DEFAULT_SDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PRM_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CT_UNIT = "AA";
    private static final String UPDATED_CT_UNIT = "BB";

    private static final String DEFAULT_CCUT = "AAAAAAAAAA";
    private static final String UPDATED_CCUT = "BBBBBBBBBB";

    private static final String DEFAULT_STYPE = "AA";
    private static final String UPDATED_STYPE = "BB";

    private static final Instant DEFAULT_VIOLATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VIOLATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PRM_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_PRM_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_VIOLATE_FACT = "AAAAAAAAAA";
    private static final String UPDATED_VIOLATE_FACT = "BBBBBBBBBB";

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

    private static final Float DEFAULT_WDAYED = 1F;
    private static final Float UPDATED_WDAYED = 2F;

    private static final String ENTITY_API_URL = "/api/bid-prj-envis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{wkut}";

    @Autowired
    private BidPrjEnviRepository bidPrjEnviRepository;

    @Autowired
    private BidPrjEnviMapper bidPrjEnviMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjEnviMockMvc;

    private BidPrjEnvi bidPrjEnvi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjEnvi createEntity(EntityManager em) {
        BidPrjEnvi bidPrjEnvi = new BidPrjEnvi()
            .prjno(DEFAULT_PRJNO)
            .sdate(LocalDate.from(DEFAULT_SDATE))
            .prmNo(DEFAULT_PRM_NO)
            .ctUnit(DEFAULT_CT_UNIT)
            .ccut(DEFAULT_CCUT)
            .stype(DEFAULT_STYPE)
            .violateDate(LocalDate.from(DEFAULT_VIOLATE_DATE))
            .prmTitle(DEFAULT_PRM_TITLE)
            .violateFact(DEFAULT_VIOLATE_FACT)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .uIp(DEFAULT_U_IP)
            .wdayed(DEFAULT_WDAYED);
        return bidPrjEnvi;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjEnvi createUpdatedEntity(EntityManager em) {
        BidPrjEnvi bidPrjEnvi = new BidPrjEnvi()
            .prjno(UPDATED_PRJNO)
            .sdate(LocalDate.from(UPDATED_SDATE))
            .prmNo(UPDATED_PRM_NO)
            .ctUnit(UPDATED_CT_UNIT)
            .ccut(UPDATED_CCUT)
            .stype(UPDATED_STYPE)
            .violateDate(LocalDate.from(UPDATED_VIOLATE_DATE))
            .prmTitle(UPDATED_PRM_TITLE)
            .violateFact(UPDATED_VIOLATE_FACT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .wdayed(UPDATED_WDAYED);
        return bidPrjEnvi;
    }

    @BeforeEach
    public void initTest() {
        bidPrjEnvi = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjEnvi() throws Exception {
        int databaseSizeBeforeCreate = bidPrjEnviRepository.findAll().size();
        // Create the BidPrjEnvi
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);
        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isCreated());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjEnvi testBidPrjEnvi = bidPrjEnviList.get(bidPrjEnviList.size() - 1);
        assertThat(testBidPrjEnvi.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjEnvi.getSdate()).isEqualTo(DEFAULT_SDATE);
        assertThat(testBidPrjEnvi.getPrmNo()).isEqualTo(DEFAULT_PRM_NO);
        assertThat(testBidPrjEnvi.getCtUnit()).isEqualTo(DEFAULT_CT_UNIT);
        assertThat(testBidPrjEnvi.getCcut()).isEqualTo(DEFAULT_CCUT);
        assertThat(testBidPrjEnvi.getStype()).isEqualTo(DEFAULT_STYPE);
        assertThat(testBidPrjEnvi.getViolateDate()).isEqualTo(DEFAULT_VIOLATE_DATE);
        assertThat(testBidPrjEnvi.getPrmTitle()).isEqualTo(DEFAULT_PRM_TITLE);
        assertThat(testBidPrjEnvi.getViolateFact()).isEqualTo(DEFAULT_VIOLATE_FACT);
        assertThat(testBidPrjEnvi.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjEnvi.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjEnvi.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjEnvi.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjEnvi.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjEnvi.getWdayed()).isEqualTo(DEFAULT_WDAYED);
    }

    @Test
    @Transactional
    void createBidPrjEnviWithExistingId() throws Exception {
        // Create the BidPrjEnvi with an existing ID
        bidPrjEnvi.setWkut("existing_id");
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        int databaseSizeBeforeCreate = bidPrjEnviRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setPrjno(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSdateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setSdate(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrmNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setPrmNo(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCtUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setCtUnit(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkViolateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setViolateDate(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrmTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setPrmTitle(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkViolateFactIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setViolateFact(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDAteIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setCreateDate(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setCreateUser(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkWdayedIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEnviRepository.findAll().size();
        // set the field null
        bidPrjEnvi.setWdayed(null);

        // Create the BidPrjEnvi, which fails.
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        restBidPrjEnviMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isBadRequest());

        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBidPrjEnvis() throws Exception {
        // Initialize the database
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());
        bidPrjEnviRepository.saveAndFlush(bidPrjEnvi);

        // Get all the bidPrjEnviList
        restBidPrjEnviMockMvc
            .perform(get(ENTITY_API_URL + "?sort=wkut,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(bidPrjEnvi.getWkut())))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].sdate").value(hasItem(DEFAULT_SDATE.toString())))
            .andExpect(jsonPath("$.[*].prmNo").value(hasItem(DEFAULT_PRM_NO)))
            .andExpect(jsonPath("$.[*].ctUnit").value(hasItem(DEFAULT_CT_UNIT)))
            .andExpect(jsonPath("$.[*].ccut").value(hasItem(DEFAULT_CCUT)))
            .andExpect(jsonPath("$.[*].stype").value(hasItem(DEFAULT_STYPE)))
            .andExpect(jsonPath("$.[*].violateDate").value(hasItem(DEFAULT_VIOLATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].prmTitle").value(hasItem(DEFAULT_PRM_TITLE)))
            .andExpect(jsonPath("$.[*].violateFact").value(hasItem(DEFAULT_VIOLATE_FACT)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
            .andExpect(jsonPath("$.[*].wdayed").value(hasItem(DEFAULT_WDAYED.doubleValue())));
    }

    @Test
    @Transactional
    void getBidPrjEnvi() throws Exception {
        // Initialize the database
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());
        bidPrjEnviRepository.saveAndFlush(bidPrjEnvi);

        // Get the bidPrjEnvi
        restBidPrjEnviMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjEnvi.getWkut()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.wkut").value(bidPrjEnvi.getWkut()))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.sdate").value(DEFAULT_SDATE.toString()))
            .andExpect(jsonPath("$.prmNo").value(DEFAULT_PRM_NO))
            .andExpect(jsonPath("$.ctUnit").value(DEFAULT_CT_UNIT))
            .andExpect(jsonPath("$.ccut").value(DEFAULT_CCUT))
            .andExpect(jsonPath("$.stype").value(DEFAULT_STYPE))
            .andExpect(jsonPath("$.violateDate").value(DEFAULT_VIOLATE_DATE.toString()))
            .andExpect(jsonPath("$.prmTitle").value(DEFAULT_PRM_TITLE))
            .andExpect(jsonPath("$.violateFact").value(DEFAULT_VIOLATE_FACT))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
            .andExpect(jsonPath("$.wdayed").value(DEFAULT_WDAYED.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjEnvi() throws Exception {
        // Get the bidPrjEnvi
        restBidPrjEnviMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

//    @Test
//    @Transactional
//    void putNewBidPrjEnvi() throws Exception {
//        // Initialize the database
//        bidPrjEnvi.setWkut(UUID.randomUUID().toString());
//        bidPrjEnviRepository.saveAndFlush(bidPrjEnvi);
//
//        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();
//
//        // Update the bidPrjEnvi
//        BidPrjEnvi updatedBidPrjEnvi = bidPrjEnviRepository.findById(bidPrjEnvi.getWkut()).get();
//        // Disconnect from session so that the updates on updatedBidPrjEnvi are not directly saved in db
//        em.detach(updatedBidPrjEnvi);
//        updatedBidPrjEnvi
//            .prjno(UPDATED_PRJNO)
//            .sdate(UPDATED_SDATE)
//            .prmNo(UPDATED_PRM_NO)
//            .ctUnit(UPDATED_CT_UNIT)
//            .ccut(UPDATED_CCUT)
//            .stype(UPDATED_STYPE)
//            .violateDate(UPDATED_VIOLATE_DATE)
//            .prmTitle(UPDATED_PRM_TITLE)
//            .violateFact(UPDATED_VIOLATE_FACT)
//            .createDAte(UPDATED_CREATE_D_ATE)
//            .createUser(UPDATED_CREATE_USER)
//            .updateDate(UPDATED_UPDATE_DATE)
//            .updateUser(UPDATED_UPDATE_USER)
//            .uIp(UPDATED_U_IP)
//            .wdayed(UPDATED_WDAYED);
//        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(updatedBidPrjEnvi);
//
//        restBidPrjEnviMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjEnviDTO.getWkut())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the BidPrjEnvi in the database
//        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
//        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
//        BidPrjEnvi testBidPrjEnvi = bidPrjEnviList.get(bidPrjEnviList.size() - 1);
//        assertThat(testBidPrjEnvi.getPrjno()).isEqualTo(UPDATED_PRJNO);
//        assertThat(testBidPrjEnvi.getSdate()).isEqualTo(UPDATED_SDATE);
//        assertThat(testBidPrjEnvi.getPrmNo()).isEqualTo(UPDATED_PRM_NO);
//        assertThat(testBidPrjEnvi.getCtUnit()).isEqualTo(UPDATED_CT_UNIT);
//        assertThat(testBidPrjEnvi.getCcut()).isEqualTo(UPDATED_CCUT);
//        assertThat(testBidPrjEnvi.getStype()).isEqualTo(UPDATED_STYPE);
//        assertThat(testBidPrjEnvi.getViolateDate()).isEqualTo(UPDATED_VIOLATE_DATE);
//        assertThat(testBidPrjEnvi.getPrmTitle()).isEqualTo(UPDATED_PRM_TITLE);
//        assertThat(testBidPrjEnvi.getViolateFact()).isEqualTo(UPDATED_VIOLATE_FACT);
//        assertThat(testBidPrjEnvi.getCreateDAte()).isEqualTo(UPDATED_CREATE_D_ATE);
//        assertThat(testBidPrjEnvi.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
//        assertThat(testBidPrjEnvi.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//        assertThat(testBidPrjEnvi.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//        assertThat(testBidPrjEnvi.getuIp()).isEqualTo(UPDATED_U_IP);
//        assertThat(testBidPrjEnvi.getWdayed()).isEqualTo(UPDATED_WDAYED);
//    }

    @Test
    @Transactional
    void putNonExistingBidPrjEnvi() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());

        // Create the BidPrjEnvi
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBidPrjEnviMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjEnviDTO.getWkut())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjEnvi() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());

        // Create the BidPrjEnvi
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjEnviMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjEnvi() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());

        // Create the BidPrjEnvi
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjEnviMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjEnviWithPatch() throws Exception {
        // Initialize the database
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());
        bidPrjEnviRepository.saveAndFlush(bidPrjEnvi);

        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();

        // Update the bidPrjEnvi using partial update
        BidPrjEnvi partialUpdatedBidPrjEnvi = new BidPrjEnvi();
        partialUpdatedBidPrjEnvi.setWkut(bidPrjEnvi.getWkut());

        partialUpdatedBidPrjEnvi
            .prmNo(UPDATED_PRM_NO)
            .ccut(UPDATED_CCUT)
            .prmTitle(UPDATED_PRM_TITLE)
            .createDate(UPDATED_CREATE_DATE)
            .wdayed(UPDATED_WDAYED);

        restBidPrjEnviMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjEnvi.getWkut())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjEnvi))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
        BidPrjEnvi testBidPrjEnvi = bidPrjEnviList.get(bidPrjEnviList.size() - 1);
        assertThat(testBidPrjEnvi.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjEnvi.getSdate()).isEqualTo(DEFAULT_SDATE);
        assertThat(testBidPrjEnvi.getPrmNo()).isEqualTo(UPDATED_PRM_NO);
        assertThat(testBidPrjEnvi.getCtUnit()).isEqualTo(DEFAULT_CT_UNIT);
        assertThat(testBidPrjEnvi.getCcut()).isEqualTo(UPDATED_CCUT);
        assertThat(testBidPrjEnvi.getStype()).isEqualTo(DEFAULT_STYPE);
        assertThat(testBidPrjEnvi.getViolateDate()).isEqualTo(DEFAULT_VIOLATE_DATE);
        assertThat(testBidPrjEnvi.getPrmTitle()).isEqualTo(UPDATED_PRM_TITLE);
        assertThat(testBidPrjEnvi.getViolateFact()).isEqualTo(DEFAULT_VIOLATE_FACT);
        assertThat(testBidPrjEnvi.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjEnvi.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjEnvi.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjEnvi.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjEnvi.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjEnvi.getWdayed()).isEqualTo(UPDATED_WDAYED);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjEnviWithPatch() throws Exception {
        // Initialize the database
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());
        bidPrjEnviRepository.saveAndFlush(bidPrjEnvi);

        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();

        // Update the bidPrjEnvi using partial update
        BidPrjEnvi partialUpdatedBidPrjEnvi = new BidPrjEnvi();
        partialUpdatedBidPrjEnvi.setWkut(bidPrjEnvi.getWkut());

        partialUpdatedBidPrjEnvi
            .prjno(UPDATED_PRJNO)
            .sdate(LocalDate.from(UPDATED_SDATE))
            .prmNo(UPDATED_PRM_NO)
            .ctUnit(UPDATED_CT_UNIT)
            .ccut(UPDATED_CCUT)
            .stype(UPDATED_STYPE)
            .violateDate(LocalDate.from(UPDATED_VIOLATE_DATE))
            .prmTitle(UPDATED_PRM_TITLE)
            .violateFact(UPDATED_VIOLATE_FACT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP)
            .wdayed(UPDATED_WDAYED);

        restBidPrjEnviMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjEnvi.getWkut())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjEnvi))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
        BidPrjEnvi testBidPrjEnvi = bidPrjEnviList.get(bidPrjEnviList.size() - 1);
        assertThat(testBidPrjEnvi.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjEnvi.getSdate()).isEqualTo(UPDATED_SDATE);
        assertThat(testBidPrjEnvi.getPrmNo()).isEqualTo(UPDATED_PRM_NO);
        assertThat(testBidPrjEnvi.getCtUnit()).isEqualTo(UPDATED_CT_UNIT);
        assertThat(testBidPrjEnvi.getCcut()).isEqualTo(UPDATED_CCUT);
        assertThat(testBidPrjEnvi.getStype()).isEqualTo(UPDATED_STYPE);
        assertThat(testBidPrjEnvi.getViolateDate()).isEqualTo(UPDATED_VIOLATE_DATE);
        assertThat(testBidPrjEnvi.getPrmTitle()).isEqualTo(UPDATED_PRM_TITLE);
        assertThat(testBidPrjEnvi.getViolateFact()).isEqualTo(UPDATED_VIOLATE_FACT);
        assertThat(testBidPrjEnvi.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjEnvi.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjEnvi.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjEnvi.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjEnvi.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjEnvi.getWdayed()).isEqualTo(UPDATED_WDAYED);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjEnvi() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());

        // Create the BidPrjEnvi
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBidPrjEnviMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bidPrjEnviDTO.getWkut())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjEnvi() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());

        // Create the BidPrjEnvi
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjEnviMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjEnvi() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjEnviRepository.findAll().size();
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());

        // Create the BidPrjEnvi
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviMapper.toDto(bidPrjEnvi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjEnviMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjEnviDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BidPrjEnvi in the database
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjEnvi() throws Exception {
        // Initialize the database
        bidPrjEnvi.setWkut(UUID.randomUUID().toString());
        bidPrjEnviRepository.saveAndFlush(bidPrjEnvi);

        int databaseSizeBeforeDelete = bidPrjEnviRepository.findAll().size();

        // Delete the bidPrjEnvi
        restBidPrjEnviMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjEnvi.getWkut()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjEnvi> bidPrjEnviList = bidPrjEnviRepository.findAll();
        assertThat(bidPrjEnviList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
