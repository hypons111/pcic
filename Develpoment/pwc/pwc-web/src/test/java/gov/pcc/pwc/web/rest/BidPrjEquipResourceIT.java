package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjEquip;
import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;
import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;
import gov.pcc.pwc.repository.BidPrjEquipRepository;
import gov.pcc.pwc.service.dto.BidPrjEquipDTO;
import gov.pcc.pwc.service.mapper.BidPrjEquipMapper;
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
 * Integration tests for the {@link BidPrjEquipResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjEquipResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_YEAR = "AAA";
    private static final String UPDATED_YEAR = "BBB";

    private static final String DEFAULT_MONTH = "AA";
    private static final String UPDATED_MONTH = "BB";

    private static final String DEFAULT_E_CODE = "AAAAA";
    private static final String UPDATED_E_CODE = "BBBBB";

    private static final String DEFAULT_E_CNT = "AAAAAAAAAA";
    private static final String UPDATED_E_CNT = "BBBBBBBBBB";

    private static final String DEFAULT_E_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_E_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String DEFAULT_SUP_CCUT = "AAAAAAAAAA";
    private static final String UPDATED_SUP_CCUT = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-equips";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjEquipRepository bidPrjEquipRepository;

    @Autowired
    private BidPrjEquipMapper bidPrjEquipMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjEquipMockMvc;

    private BidPrjEquip bidPrjEquip;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjEquip createEntity(EntityManager em) {
        BidPrjEquip bidPrjEquip = new BidPrjEquip()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .year(DEFAULT_YEAR)
            .month(DEFAULT_MONTH)
            .eCode(DEFAULT_E_CODE)
            .eCnt(DEFAULT_E_CNT)
            .eRemark(DEFAULT_E_REMARK)
            .uIp(DEFAULT_U_IP)
            .supCcut(DEFAULT_SUP_CCUT)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER);
        return bidPrjEquip;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjEquip createUpdatedEntity(EntityManager em) {
        BidPrjEquip bidPrjEquip = new BidPrjEquip()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .eCode(UPDATED_E_CODE)
            .eCnt(UPDATED_E_CNT)
            .eRemark(UPDATED_E_REMARK)
            .uIp(UPDATED_U_IP)
            .supCcut(UPDATED_SUP_CCUT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        return bidPrjEquip;
    }

    @BeforeEach
    public void initTest() {
        bidPrjEquip = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjEquip() throws Exception {
        int databaseSizeBeforeCreate = bidPrjEquipRepository.findAll().size();
        // Create the BidPrjEquip
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);
        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjEquip in the database
        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjEquip testBidPrjEquip = bidPrjEquipList.get(bidPrjEquipList.size() - 1);
        assertThat(testBidPrjEquip.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjEquip.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjEquip.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testBidPrjEquip.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBidPrjEquip.geteCode()).isEqualTo(DEFAULT_E_CODE);
        assertThat(testBidPrjEquip.geteCnt()).isEqualTo(DEFAULT_E_CNT);
        assertThat(testBidPrjEquip.geteRemark()).isEqualTo(DEFAULT_E_REMARK);
        assertThat(testBidPrjEquip.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjEquip.getSupCcut()).isEqualTo(DEFAULT_SUP_CCUT);
        assertThat(testBidPrjEquip.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjEquip.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjEquip.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjEquip.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    void createBidPrjEquipWithExistingId() throws Exception {
        // Create the BidPrjEquip with an existing ID
        bidPrjEquip.setId(new BidPrjEquipPrimaryKey("12345678","12345678","111","01","01"));
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        int databaseSizeBeforeCreate = bidPrjEquipRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjEquip in the database
        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEquipRepository.findAll().size();
        // set the field null
        bidPrjEquip.setWkut(null);

        // Create the BidPrjEquip, which fails.
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEquipRepository.findAll().size();
        // set the field null
        bidPrjEquip.setPrjno(null);

        // Create the BidPrjEquip, which fails.
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEquipRepository.findAll().size();
        // set the field null
        bidPrjEquip.setYear(null);

        // Create the BidPrjEquip, which fails.
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMonthIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEquipRepository.findAll().size();
        // set the field null
        bidPrjEquip.setMonth(null);

        // Create the BidPrjEquip, which fails.
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEquipRepository.findAll().size();
        // set the field null
        bidPrjEquip.setCreateDate(null);

        // Create the BidPrjEquip, which fails.
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEquipRepository.findAll().size();
        // set the field null
        bidPrjEquip.setCreateUser(null);

        // Create the BidPrjEquip, which fails.
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEquipRepository.findAll().size();
        // set the field null
        bidPrjEquip.setUpdateDate(null);

        // Create the BidPrjEquip, which fails.
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjEquipRepository.findAll().size();
        // set the field null
        bidPrjEquip.setUpdateUser(null);

        // Create the BidPrjEquip, which fails.
        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);

        restBidPrjEquipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeTest);
    }

//    @Test
//    @Transactional
//    void getAllBidPrjEquips() throws Exception {
//        // Initialize the database
//        bidPrjEquipRepository.saveAndFlush(bidPrjEquip);
//
//        // Get all the bidPrjEquipList
//        restBidPrjEquipMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjEquip.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
//            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
//            .andExpect(jsonPath("$.[*].eCode").value(hasItem(DEFAULT_E_CODE)))
//            .andExpect(jsonPath("$.[*].eCnt").value(hasItem(DEFAULT_E_CNT)))
//            .andExpect(jsonPath("$.[*].eRemark").value(hasItem(DEFAULT_E_REMARK)))
//            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)))
//            .andExpect(jsonPath("$.[*].supCcut").value(hasItem(DEFAULT_SUP_CCUT)))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)));
//    }

//    @Test
//    @Transactional
//    void getBidPrjEquip() throws Exception {
//        // Initialize the database
//        bidPrjEquipRepository.saveAndFlush(bidPrjEquip);
//
//        // Get the bidPrjEquip
//        restBidPrjEquipMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidPrjEquip.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjEquip.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
//            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
//            .andExpect(jsonPath("$.eCode").value(DEFAULT_E_CODE))
//            .andExpect(jsonPath("$.eCnt").value(DEFAULT_E_CNT))
//            .andExpect(jsonPath("$.eRemark").value(DEFAULT_E_REMARK))
//            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP))
//            .andExpect(jsonPath("$.supCcut").value(DEFAULT_SUP_CCUT))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER));
//    }

    @Test
    @Transactional
    void getNonExistingBidPrjEquip() throws Exception {
        // Get the bidPrjEquip
        restBidPrjEquipMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

//    @Test
//    @Transactional
//    void putNewBidPrjEquip() throws Exception {
//        // Initialize the database
//        bidPrjEquipRepository.saveAndFlush(bidPrjEquip);
//
//        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();
//
//        // Update the bidPrjEquip
//        BidPrjEquip updatedBidPrjEquip = bidPrjEquipRepository.findById(bidPrjEquip.getId()).get();
//        // Disconnect from session so that the updates on updatedBidPrjEquip are not directly saved in db
//        em.detach(updatedBidPrjEquip);
//        updatedBidPrjEquip
//            .wkut(UPDATED_WKUT)
//            .prjno(UPDATED_PRJNO)
//            .year(UPDATED_YEAR)
//            .month(UPDATED_MONTH)
//            .eCode(UPDATED_E_CODE)
//            .eCnt(UPDATED_E_CNT)
//            .eRemark(UPDATED_E_REMARK)
//            .uIp(UPDATED_U_IP)
//            .supCcut(UPDATED_SUP_CCUT)
//            .createDate(UPDATED_CREATE_DATE)
//            .createUser(UPDATED_CREATE_USER)
//            .updateDate(UPDATED_UPDATE_DATE)
//            .updateUser(UPDATED_UPDATE_USER);
//        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(updatedBidPrjEquip);
//
//        restBidPrjEquipMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjEquipDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the BidPrjEquip in the database
//        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
//        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
//        BidPrjEquip testBidPrjEquip = bidPrjEquipList.get(bidPrjEquipList.size() - 1);
//        assertThat(testBidPrjEquip.getWkut()).isEqualTo(UPDATED_WKUT);
//        assertThat(testBidPrjEquip.getPrjno()).isEqualTo(UPDATED_PRJNO);
//        assertThat(testBidPrjEquip.getYear()).isEqualTo(UPDATED_YEAR);
//        assertThat(testBidPrjEquip.getMonth()).isEqualTo(UPDATED_MONTH);
//        assertThat(testBidPrjEquip.geteCode()).isEqualTo(UPDATED_E_CODE);
//        assertThat(testBidPrjEquip.geteCnt()).isEqualTo(UPDATED_E_CNT);
//        assertThat(testBidPrjEquip.geteRemark()).isEqualTo(UPDATED_E_REMARK);
//        assertThat(testBidPrjEquip.getuIp()).isEqualTo(UPDATED_U_IP);
//        assertThat(testBidPrjEquip.getSupCcut()).isEqualTo(UPDATED_SUP_CCUT);
//        assertThat(testBidPrjEquip.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
//        assertThat(testBidPrjEquip.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
//        assertThat(testBidPrjEquip.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//        assertThat(testBidPrjEquip.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//    }

//    @Test
//    @Transactional
//    void putNonExistingBidPrjEquip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();
//        bidPrjEquip.setId(count.incrementAndGet());
//
//        // Create the BidPrjEquip
//        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjEquipMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjEquipDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjEquip in the database
//        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
//        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithIdMismatchBidPrjEquip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();
//        bidPrjEquip.setId(count.incrementAndGet());
//
//        // Create the BidPrjEquip
//        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjEquipMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjEquip in the database
//        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
//        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void putWithMissingIdPathParamBidPrjEquip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();
//        bidPrjEquip.setId(count.incrementAndGet());
//
//        // Create the BidPrjEquip
//        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjEquipMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjEquip in the database
//        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
//        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void partialUpdateBidPrjEquipWithPatch() throws Exception {
        // Initialize the database
        bidPrjEquipRepository.saveAndFlush(bidPrjEquip);

        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();

        // Update the bidPrjEquip using partial update
        BidPrjEquip partialUpdatedBidPrjEquip = new BidPrjEquip();
        partialUpdatedBidPrjEquip.setId(bidPrjEquip.getId());

        partialUpdatedBidPrjEquip
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YEAR)
            .eCode(UPDATED_E_CODE)
            .eCnt(UPDATED_E_CNT)
            .eRemark(UPDATED_E_REMARK)
            .supCcut(UPDATED_SUP_CCUT)
            .updateDate(UPDATED_UPDATE_DATE);

        restBidPrjEquipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjEquip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjEquip))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjEquip in the database
        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
        BidPrjEquip testBidPrjEquip = bidPrjEquipList.get(bidPrjEquipList.size() - 1);
        assertThat(testBidPrjEquip.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjEquip.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjEquip.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testBidPrjEquip.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBidPrjEquip.geteCode()).isEqualTo(UPDATED_E_CODE);
        assertThat(testBidPrjEquip.geteCnt()).isEqualTo(UPDATED_E_CNT);
        assertThat(testBidPrjEquip.geteRemark()).isEqualTo(UPDATED_E_REMARK);
        assertThat(testBidPrjEquip.getuIp()).isEqualTo(DEFAULT_U_IP);
        assertThat(testBidPrjEquip.getSupCcut()).isEqualTo(UPDATED_SUP_CCUT);
        assertThat(testBidPrjEquip.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjEquip.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjEquip.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjEquip.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjEquipWithPatch() throws Exception {
        // Initialize the database
        bidPrjEquipRepository.saveAndFlush(bidPrjEquip);

        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();

        // Update the bidPrjEquip using partial update
        BidPrjEquip partialUpdatedBidPrjEquip = new BidPrjEquip();
        partialUpdatedBidPrjEquip.setId(bidPrjEquip.getId());

        partialUpdatedBidPrjEquip
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .eCode(UPDATED_E_CODE)
            .eCnt(UPDATED_E_CNT)
            .eRemark(UPDATED_E_REMARK)
            .uIp(UPDATED_U_IP)
            .supCcut(UPDATED_SUP_CCUT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);

        restBidPrjEquipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjEquip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjEquip))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjEquip in the database
        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
        BidPrjEquip testBidPrjEquip = bidPrjEquipList.get(bidPrjEquipList.size() - 1);
        assertThat(testBidPrjEquip.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjEquip.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjEquip.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testBidPrjEquip.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjEquip.geteCode()).isEqualTo(UPDATED_E_CODE);
        assertThat(testBidPrjEquip.geteCnt()).isEqualTo(UPDATED_E_CNT);
        assertThat(testBidPrjEquip.geteRemark()).isEqualTo(UPDATED_E_REMARK);
        assertThat(testBidPrjEquip.getuIp()).isEqualTo(UPDATED_U_IP);
        assertThat(testBidPrjEquip.getSupCcut()).isEqualTo(UPDATED_SUP_CCUT);
        assertThat(testBidPrjEquip.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjEquip.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjEquip.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjEquip.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

//    @Test
//    @Transactional
//    void patchNonExistingBidPrjEquip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();
//        bidPrjEquip.setId(count.incrementAndGet());
//
//        // Create the BidPrjEquip
//        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjEquipMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjEquipDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjEquip in the database
//        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
//        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithIdMismatchBidPrjEquip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();
//        bidPrjEquip.setId(count.incrementAndGet());
//
//        // Create the BidPrjEquip
//        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjEquipMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjEquip in the database
//        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
//        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
//    }

//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamBidPrjEquip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjEquipRepository.findAll().size();
//        bidPrjEquip.setId(count.incrementAndGet());
//
//        // Create the BidPrjEquip
//        BidPrjEquipDTO bidPrjEquipDTO = bidPrjEquipMapper.toDto(bidPrjEquip);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjEquipMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjEquipDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjEquip in the database
//        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
//        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeUpdate);
//    }

    @Test
    @Transactional
    void deleteBidPrjEquip() throws Exception {
        // Initialize the database
        bidPrjEquipRepository.saveAndFlush(bidPrjEquip);

        int databaseSizeBeforeDelete = bidPrjEquipRepository.findAll().size();

        // Delete the bidPrjEquip
        restBidPrjEquipMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjEquip.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjEquip> bidPrjEquipList = bidPrjEquipRepository.findAll();
        assertThat(bidPrjEquipList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
