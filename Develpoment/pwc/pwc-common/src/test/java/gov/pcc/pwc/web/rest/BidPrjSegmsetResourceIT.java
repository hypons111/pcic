package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjSegmset;
import gov.pcc.pwc.repository.BidPrjSegmsetRepository;
import gov.pcc.pwc.service.dto.BidPrjSegmsetDTO;
import gov.pcc.pwc.service.mapper.BidPrjSegmsetMapper;
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
 * Integration tests for the {@link BidPrjSegmsetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjSegmsetResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AGREE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AGREE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AGREE_NUM = "AAAAAAAAAA";
    private static final String UPDATED_AGREE_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_AGREE_RESON = "A";
    private static final String UPDATED_AGREE_RESON = "B";

    private static final BigDecimal DEFAULT_AGREE_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_AGREE_VALUE = new BigDecimal(2);

    private static final LocalDate DEFAULT_TEAR_DOWN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TEAR_DOWN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_TOTAL_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL_VALUE = new BigDecimal(2);

    private static final String DEFAULT_VALUE_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_VALUE_REMARK = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/bid-prj-segmsets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjSegmsetRepository bidPrjSegmsetRepository;

    @Autowired
    private BidPrjSegmsetMapper bidPrjSegmsetMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjSegmsetMockMvc;

    private BidPrjSegmset bidPrjSegmset;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjSegmset createEntity(EntityManager em) {
        BidPrjSegmset bidPrjSegmset = new BidPrjSegmset()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .agreeDate(DEFAULT_AGREE_DATE)
            .agreeNum(DEFAULT_AGREE_NUM)
            .agreeReson(DEFAULT_AGREE_RESON)
            .agreeValue(DEFAULT_AGREE_VALUE)
            .tearDownDate(DEFAULT_TEAR_DOWN_DATE)
            .totalValue(DEFAULT_TOTAL_VALUE)
            .valueRemark(DEFAULT_VALUE_REMARK)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .uIp(DEFAULT_U_IP);
        return bidPrjSegmset;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjSegmset createUpdatedEntity(EntityManager em) {
        BidPrjSegmset bidPrjSegmset = new BidPrjSegmset()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .agreeDate(UPDATED_AGREE_DATE)
            .agreeNum(UPDATED_AGREE_NUM)
            .agreeReson(UPDATED_AGREE_RESON)
            .agreeValue(UPDATED_AGREE_VALUE)
            .tearDownDate(UPDATED_TEAR_DOWN_DATE)
            .totalValue(UPDATED_TOTAL_VALUE)
            .valueRemark(UPDATED_VALUE_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP);
        return bidPrjSegmset;
    }

    @BeforeEach
    public void initTest() {
        bidPrjSegmset = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjSegmset() throws Exception {
        int databaseSizeBeforeCreate = bidPrjSegmsetRepository.findAll().size();
        // Create the BidPrjSegmset
        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);
        restBidPrjSegmsetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjSegmset in the database
        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjSegmset testBidPrjSegmset = bidPrjSegmsetList.get(bidPrjSegmsetList.size() - 1);
        assertThat(testBidPrjSegmset.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjSegmset.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjSegmset.getAgreeDate()).isEqualTo(DEFAULT_AGREE_DATE);
        assertThat(testBidPrjSegmset.getAgreeNum()).isEqualTo(DEFAULT_AGREE_NUM);
        assertThat(testBidPrjSegmset.getAgreeReson()).isEqualTo(DEFAULT_AGREE_RESON);
        assertThat(testBidPrjSegmset.getAgreeValue()).isEqualByComparingTo(DEFAULT_AGREE_VALUE);
        assertThat(testBidPrjSegmset.getTearDownDate()).isEqualTo(DEFAULT_TEAR_DOWN_DATE);
        assertThat(testBidPrjSegmset.getTotalValue()).isEqualByComparingTo(DEFAULT_TOTAL_VALUE);
        assertThat(testBidPrjSegmset.getValueRemark()).isEqualTo(DEFAULT_VALUE_REMARK);
        assertThat(testBidPrjSegmset.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjSegmset.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjSegmset.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjSegmset.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjSegmset.getuIp()).isEqualTo(DEFAULT_U_IP);
    }

    @Test
    @Transactional
    void createBidPrjSegmsetWithExistingId() throws Exception {
        // Create the BidPrjSegmset with an existing ID
//        bidPrjSegmset.setId(1L);
//        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);
//
//        int databaseSizeBeforeCreate = bidPrjSegmsetRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjSegmsetMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSegmset in the database
//        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
//        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjSegmsetRepository.findAll().size();
        // set the field null
        bidPrjSegmset.setWkut(null);

        // Create the BidPrjSegmset, which fails.
        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);

        restBidPrjSegmsetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjSegmsetRepository.findAll().size();
        // set the field null
        bidPrjSegmset.setPrjno(null);

        // Create the BidPrjSegmset, which fails.
        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);

        restBidPrjSegmsetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAgreeDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjSegmsetRepository.findAll().size();
        // set the field null
        bidPrjSegmset.setAgreeDate(null);

        // Create the BidPrjSegmset, which fails.
        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);

        restBidPrjSegmsetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjSegmsetRepository.findAll().size();
        // set the field null
        bidPrjSegmset.setCreateDate(null);

        // Create the BidPrjSegmset, which fails.
        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);

        restBidPrjSegmsetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjSegmsetRepository.findAll().size();
        // set the field null
        bidPrjSegmset.setCreateUser(null);

        // Create the BidPrjSegmset, which fails.
        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);

        restBidPrjSegmsetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBidPrjSegmsets() throws Exception {
        // Initialize the database
        bidPrjSegmsetRepository.saveAndFlush(bidPrjSegmset);

        // Get all the bidPrjSegmsetList
        restBidPrjSegmsetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjSegmset.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].agreeDate").value(hasItem(DEFAULT_AGREE_DATE.toString())))
            .andExpect(jsonPath("$.[*].agreeNum").value(hasItem(DEFAULT_AGREE_NUM)))
            .andExpect(jsonPath("$.[*].agreeReson").value(hasItem(DEFAULT_AGREE_RESON)))
            .andExpect(jsonPath("$.[*].agreeValue").value(hasItem(sameNumber(DEFAULT_AGREE_VALUE))))
            .andExpect(jsonPath("$.[*].tearDownDate").value(hasItem(DEFAULT_TEAR_DOWN_DATE.toString())))
            .andExpect(jsonPath("$.[*].totalValue").value(hasItem(sameNumber(DEFAULT_TOTAL_VALUE))))
            .andExpect(jsonPath("$.[*].valueRemark").value(hasItem(DEFAULT_VALUE_REMARK)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)));
    }

    @Test
    @Transactional
    void getBidPrjSegmset() throws Exception {
        // Initialize the database
        bidPrjSegmsetRepository.saveAndFlush(bidPrjSegmset);

        // Get the bidPrjSegmset
        restBidPrjSegmsetMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjSegmset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjSegmset.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.agreeDate").value(DEFAULT_AGREE_DATE.toString()))
            .andExpect(jsonPath("$.agreeNum").value(DEFAULT_AGREE_NUM))
            .andExpect(jsonPath("$.agreeReson").value(DEFAULT_AGREE_RESON))
            .andExpect(jsonPath("$.agreeValue").value(sameNumber(DEFAULT_AGREE_VALUE)))
            .andExpect(jsonPath("$.tearDownDate").value(DEFAULT_TEAR_DOWN_DATE.toString()))
            .andExpect(jsonPath("$.totalValue").value(sameNumber(DEFAULT_TOTAL_VALUE)))
            .andExpect(jsonPath("$.valueRemark").value(DEFAULT_VALUE_REMARK))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjSegmset() throws Exception {
        // Get the bidPrjSegmset
        restBidPrjSegmsetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjSegmset() throws Exception {
        // Initialize the database
        bidPrjSegmsetRepository.saveAndFlush(bidPrjSegmset);

        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();

        // Update the bidPrjSegmset
        BidPrjSegmset updatedBidPrjSegmset = bidPrjSegmsetRepository.findById(bidPrjSegmset.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjSegmset are not directly saved in db
        em.detach(updatedBidPrjSegmset);
        updatedBidPrjSegmset
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .agreeDate(UPDATED_AGREE_DATE)
            .agreeNum(UPDATED_AGREE_NUM)
            .agreeReson(UPDATED_AGREE_RESON)
            .agreeValue(UPDATED_AGREE_VALUE)
            .tearDownDate(UPDATED_TEAR_DOWN_DATE)
            .totalValue(UPDATED_TOTAL_VALUE)
            .valueRemark(UPDATED_VALUE_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP);
        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(updatedBidPrjSegmset);

        restBidPrjSegmsetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjSegmsetDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSegmset in the database
        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSegmset testBidPrjSegmset = bidPrjSegmsetList.get(bidPrjSegmsetList.size() - 1);
        assertThat(testBidPrjSegmset.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSegmset.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSegmset.getAgreeDate()).isEqualTo(UPDATED_AGREE_DATE);
        assertThat(testBidPrjSegmset.getAgreeNum()).isEqualTo(UPDATED_AGREE_NUM);
        assertThat(testBidPrjSegmset.getAgreeReson()).isEqualTo(UPDATED_AGREE_RESON);
        assertThat(testBidPrjSegmset.getAgreeValue()).isEqualByComparingTo(UPDATED_AGREE_VALUE);
        assertThat(testBidPrjSegmset.getTearDownDate()).isEqualTo(UPDATED_TEAR_DOWN_DATE);
        assertThat(testBidPrjSegmset.getTotalValue()).isEqualByComparingTo(UPDATED_TOTAL_VALUE);
        assertThat(testBidPrjSegmset.getValueRemark()).isEqualTo(UPDATED_VALUE_REMARK);
        assertThat(testBidPrjSegmset.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSegmset.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSegmset.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSegmset.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSegmset.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjSegmset() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();
//        bidPrjSegmset.setId(count.incrementAndGet());
//
//        // Create the BidPrjSegmset
//        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjSegmsetMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjSegmsetDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSegmset in the database
//        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
//        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjSegmset() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();
//        bidPrjSegmset.setId(count.incrementAndGet());
//
//        // Create the BidPrjSegmset
//        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSegmsetMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSegmset in the database
//        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
//        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjSegmset() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();
//        bidPrjSegmset.setId(count.incrementAndGet());
//
//        // Create the BidPrjSegmset
//        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSegmsetMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjSegmset in the database
//        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
//        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjSegmsetWithPatch() throws Exception {
        // Initialize the database
        bidPrjSegmsetRepository.saveAndFlush(bidPrjSegmset);

        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();

        // Update the bidPrjSegmset using partial update
        BidPrjSegmset partialUpdatedBidPrjSegmset = new BidPrjSegmset();
        partialUpdatedBidPrjSegmset.setId(bidPrjSegmset.getId());

        partialUpdatedBidPrjSegmset
            .agreeDate(UPDATED_AGREE_DATE)
            .agreeNum(UPDATED_AGREE_NUM)
            .agreeValue(UPDATED_AGREE_VALUE)
            .totalValue(UPDATED_TOTAL_VALUE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .uIp(UPDATED_U_IP);

        restBidPrjSegmsetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjSegmset.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjSegmset))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSegmset in the database
        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSegmset testBidPrjSegmset = bidPrjSegmsetList.get(bidPrjSegmsetList.size() - 1);
        assertThat(testBidPrjSegmset.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjSegmset.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjSegmset.getAgreeDate()).isEqualTo(UPDATED_AGREE_DATE);
        assertThat(testBidPrjSegmset.getAgreeNum()).isEqualTo(UPDATED_AGREE_NUM);
        assertThat(testBidPrjSegmset.getAgreeReson()).isEqualTo(DEFAULT_AGREE_RESON);
        assertThat(testBidPrjSegmset.getAgreeValue()).isEqualByComparingTo(UPDATED_AGREE_VALUE);
        assertThat(testBidPrjSegmset.getTearDownDate()).isEqualTo(DEFAULT_TEAR_DOWN_DATE);
        assertThat(testBidPrjSegmset.getTotalValue()).isEqualByComparingTo(UPDATED_TOTAL_VALUE);
        assertThat(testBidPrjSegmset.getValueRemark()).isEqualTo(DEFAULT_VALUE_REMARK);
        assertThat(testBidPrjSegmset.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjSegmset.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSegmset.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSegmset.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjSegmset.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjSegmsetWithPatch() throws Exception {
        // Initialize the database
        bidPrjSegmsetRepository.saveAndFlush(bidPrjSegmset);

        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();

        // Update the bidPrjSegmset using partial update
        BidPrjSegmset partialUpdatedBidPrjSegmset = new BidPrjSegmset();
        partialUpdatedBidPrjSegmset.setId(bidPrjSegmset.getId());

        partialUpdatedBidPrjSegmset
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .agreeDate(UPDATED_AGREE_DATE)
            .agreeNum(UPDATED_AGREE_NUM)
            .agreeReson(UPDATED_AGREE_RESON)
            .agreeValue(UPDATED_AGREE_VALUE)
            .tearDownDate(UPDATED_TEAR_DOWN_DATE)
            .totalValue(UPDATED_TOTAL_VALUE)
            .valueRemark(UPDATED_VALUE_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .uIp(UPDATED_U_IP);

        restBidPrjSegmsetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjSegmset.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjSegmset))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSegmset in the database
        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
        BidPrjSegmset testBidPrjSegmset = bidPrjSegmsetList.get(bidPrjSegmsetList.size() - 1);
        assertThat(testBidPrjSegmset.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSegmset.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSegmset.getAgreeDate()).isEqualTo(UPDATED_AGREE_DATE);
        assertThat(testBidPrjSegmset.getAgreeNum()).isEqualTo(UPDATED_AGREE_NUM);
        assertThat(testBidPrjSegmset.getAgreeReson()).isEqualTo(UPDATED_AGREE_RESON);
        assertThat(testBidPrjSegmset.getAgreeValue()).isEqualByComparingTo(UPDATED_AGREE_VALUE);
        assertThat(testBidPrjSegmset.getTearDownDate()).isEqualTo(UPDATED_TEAR_DOWN_DATE);
        assertThat(testBidPrjSegmset.getTotalValue()).isEqualByComparingTo(UPDATED_TOTAL_VALUE);
        assertThat(testBidPrjSegmset.getValueRemark()).isEqualTo(UPDATED_VALUE_REMARK);
        assertThat(testBidPrjSegmset.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSegmset.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSegmset.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSegmset.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSegmset.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjSegmset() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();
//        bidPrjSegmset.setId(count.incrementAndGet());
//
//        // Create the BidPrjSegmset
//        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjSegmsetMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjSegmsetDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSegmset in the database
//        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
//        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjSegmset() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();
//        bidPrjSegmset.setId(count.incrementAndGet());
//
//        // Create the BidPrjSegmset
//        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSegmsetMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSegmset in the database
//        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
//        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjSegmset() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSegmsetRepository.findAll().size();
//        bidPrjSegmset.setId(count.incrementAndGet());
//
//        // Create the BidPrjSegmset
//        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetMapper.toDto(bidPrjSegmset);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSegmsetMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSegmsetDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjSegmset in the database
//        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
//        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjSegmset() throws Exception {
        // Initialize the database
        bidPrjSegmsetRepository.saveAndFlush(bidPrjSegmset);

        int databaseSizeBeforeDelete = bidPrjSegmsetRepository.findAll().size();

        // Delete the bidPrjSegmset
        restBidPrjSegmsetMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjSegmset.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjSegmset> bidPrjSegmsetList = bidPrjSegmsetRepository.findAll();
        assertThat(bidPrjSegmsetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
