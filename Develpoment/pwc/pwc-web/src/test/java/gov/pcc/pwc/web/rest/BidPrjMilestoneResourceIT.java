package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjMilestone;
import gov.pcc.pwc.repository.BidPrjMilestoneRepository;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
import gov.pcc.pwc.service.mapper.BidPrjMilestoneMapper;
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
 * Integration tests for the {@link BidPrjMilestoneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjMilestoneResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPLY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPLY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MILESTONE_NO = "AAAA";
    private static final String UPDATED_MILESTONE_NO = "BBBB";

    private static final String DEFAULT_MILESTONE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MILESTONE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MILESTONE_TYPE = "AA";
    private static final String UPDATED_MILESTONE_TYPE = "BB";

    private static final String DEFAULT_MILESTONE_STATUS = "A";
    private static final String UPDATED_MILESTONE_STATUS = "B";

    private static final LocalDate DEFAULT_ESTIMATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ESTIMATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-milestones";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjMilestoneRepository bidPrjMilestoneRepository;

    @Autowired
    private BidPrjMilestoneMapper bidPrjMilestoneMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjMilestoneMockMvc;

    private BidPrjMilestone bidPrjMilestone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjMilestone createEntity(EntityManager em) {
        BidPrjMilestone bidPrjMilestone = new BidPrjMilestone()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .applyDate(DEFAULT_APPLY_DATE)
            .milestoneNo(DEFAULT_MILESTONE_NO)
            .milestoneName(DEFAULT_MILESTONE_NAME)
            .milestoneType(DEFAULT_MILESTONE_TYPE)
            .milestoneStatus(DEFAULT_MILESTONE_STATUS)
            .estimateDate(DEFAULT_ESTIMATE_DATE)
            .actualDate(DEFAULT_ACTUAL_DATE)
            .remark(DEFAULT_REMARK)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER);
        return bidPrjMilestone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjMilestone createUpdatedEntity(EntityManager em) {
        BidPrjMilestone bidPrjMilestone = new BidPrjMilestone()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .applyDate(UPDATED_APPLY_DATE)
            .milestoneNo(UPDATED_MILESTONE_NO)
            .milestoneName(UPDATED_MILESTONE_NAME)
            .milestoneType(UPDATED_MILESTONE_TYPE)
            .milestoneStatus(UPDATED_MILESTONE_STATUS)
            .estimateDate(UPDATED_ESTIMATE_DATE)
            .actualDate(UPDATED_ACTUAL_DATE)
            .remark(UPDATED_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        return bidPrjMilestone;
    }

    @BeforeEach
    public void initTest() {
        bidPrjMilestone = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjMilestone() throws Exception {
        int databaseSizeBeforeCreate = bidPrjMilestoneRepository.findAll().size();
        // Create the BidPrjMilestone
        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(bidPrjMilestone);
        restBidPrjMilestoneMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjMilestone in the database
        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjMilestone testBidPrjMilestone = bidPrjMilestoneList.get(bidPrjMilestoneList.size() - 1);
        assertThat(testBidPrjMilestone.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjMilestone.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjMilestone.getApplyDate()).isEqualTo(DEFAULT_APPLY_DATE);
        assertThat(testBidPrjMilestone.getMilestoneNo()).isEqualTo(DEFAULT_MILESTONE_NO);
        assertThat(testBidPrjMilestone.getMilestoneName()).isEqualTo(DEFAULT_MILESTONE_NAME);
        assertThat(testBidPrjMilestone.getMilestoneType()).isEqualTo(DEFAULT_MILESTONE_TYPE);
        assertThat(testBidPrjMilestone.getMilestoneStatus()).isEqualTo(DEFAULT_MILESTONE_STATUS);
        assertThat(testBidPrjMilestone.getEstimateDate()).isEqualTo(DEFAULT_ESTIMATE_DATE);
        assertThat(testBidPrjMilestone.getActualDate()).isEqualTo(DEFAULT_ACTUAL_DATE);
        assertThat(testBidPrjMilestone.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBidPrjMilestone.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjMilestone.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjMilestone.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjMilestone.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    void createBidPrjMilestoneWithExistingId() throws Exception {
        // Create the BidPrjMilestone with an existing ID
//        bidPrjMilestone.setId(1L);
//        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(bidPrjMilestone);
//
//        int databaseSizeBeforeCreate = bidPrjMilestoneRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjMilestoneMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMilestone in the database
//        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
//        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidPrjMilestones() throws Exception {
        // Initialize the database
        bidPrjMilestoneRepository.saveAndFlush(bidPrjMilestone);

        // Get all the bidPrjMilestoneList
        restBidPrjMilestoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjMilestone.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].applyDate").value(hasItem(DEFAULT_APPLY_DATE.toString())))
            .andExpect(jsonPath("$.[*].milestoneNo").value(hasItem(DEFAULT_MILESTONE_NO)))
            .andExpect(jsonPath("$.[*].milestoneName").value(hasItem(DEFAULT_MILESTONE_NAME)))
            .andExpect(jsonPath("$.[*].milestoneType").value(hasItem(DEFAULT_MILESTONE_TYPE)))
            .andExpect(jsonPath("$.[*].milestoneStatus").value(hasItem(DEFAULT_MILESTONE_STATUS)))
            .andExpect(jsonPath("$.[*].estimateDate").value(hasItem(DEFAULT_ESTIMATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].actualDate").value(hasItem(DEFAULT_ACTUAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)));
    }

    @Test
    @Transactional
    void getBidPrjMilestone() throws Exception {
        // Initialize the database
        bidPrjMilestoneRepository.saveAndFlush(bidPrjMilestone);

        // Get the bidPrjMilestone
        restBidPrjMilestoneMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjMilestone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjMilestone.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.applyDate").value(DEFAULT_APPLY_DATE.toString()))
            .andExpect(jsonPath("$.milestoneNo").value(DEFAULT_MILESTONE_NO))
            .andExpect(jsonPath("$.milestoneName").value(DEFAULT_MILESTONE_NAME))
            .andExpect(jsonPath("$.milestoneType").value(DEFAULT_MILESTONE_TYPE))
            .andExpect(jsonPath("$.milestoneStatus").value(DEFAULT_MILESTONE_STATUS))
            .andExpect(jsonPath("$.estimateDate").value(DEFAULT_ESTIMATE_DATE.toString()))
            .andExpect(jsonPath("$.actualDate").value(DEFAULT_ACTUAL_DATE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjMilestone() throws Exception {
        // Get the bidPrjMilestone
        restBidPrjMilestoneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjMilestone() throws Exception {
        // Initialize the database
        bidPrjMilestoneRepository.saveAndFlush(bidPrjMilestone);

        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();

        // Update the bidPrjMilestone
        BidPrjMilestone updatedBidPrjMilestone = bidPrjMilestoneRepository.findById(bidPrjMilestone.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjMilestone are not directly saved in db
        em.detach(updatedBidPrjMilestone);
        updatedBidPrjMilestone
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .applyDate(UPDATED_APPLY_DATE)
            .milestoneNo(UPDATED_MILESTONE_NO)
            .milestoneName(UPDATED_MILESTONE_NAME)
            .milestoneType(UPDATED_MILESTONE_TYPE)
            .milestoneStatus(UPDATED_MILESTONE_STATUS)
            .estimateDate(UPDATED_ESTIMATE_DATE)
            .actualDate(UPDATED_ACTUAL_DATE)
            .remark(UPDATED_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(updatedBidPrjMilestone);

        restBidPrjMilestoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjMilestoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjMilestone in the database
        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
        BidPrjMilestone testBidPrjMilestone = bidPrjMilestoneList.get(bidPrjMilestoneList.size() - 1);
        assertThat(testBidPrjMilestone.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjMilestone.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjMilestone.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testBidPrjMilestone.getMilestoneNo()).isEqualTo(UPDATED_MILESTONE_NO);
        assertThat(testBidPrjMilestone.getMilestoneName()).isEqualTo(UPDATED_MILESTONE_NAME);
        assertThat(testBidPrjMilestone.getMilestoneType()).isEqualTo(UPDATED_MILESTONE_TYPE);
        assertThat(testBidPrjMilestone.getMilestoneStatus()).isEqualTo(UPDATED_MILESTONE_STATUS);
        assertThat(testBidPrjMilestone.getEstimateDate()).isEqualTo(UPDATED_ESTIMATE_DATE);
        assertThat(testBidPrjMilestone.getActualDate()).isEqualTo(UPDATED_ACTUAL_DATE);
        assertThat(testBidPrjMilestone.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjMilestone.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjMilestone.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjMilestone.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjMilestone.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjMilestone() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();
//        bidPrjMilestone.setId(count.incrementAndGet());
//
//        // Create the BidPrjMilestone
//        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(bidPrjMilestone);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjMilestoneMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjMilestoneDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMilestone in the database
//        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
//        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjMilestone() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();
//        bidPrjMilestone.setId(count.incrementAndGet());
//
//        // Create the BidPrjMilestone
//        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(bidPrjMilestone);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjMilestoneMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMilestone in the database
//        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
//        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjMilestone() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();
//        bidPrjMilestone.setId(count.incrementAndGet());
//
//        // Create the BidPrjMilestone
//        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(bidPrjMilestone);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjMilestoneMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjMilestone in the database
//        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
//        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjMilestoneWithPatch() throws Exception {
        // Initialize the database
        bidPrjMilestoneRepository.saveAndFlush(bidPrjMilestone);

        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();

        // Update the bidPrjMilestone using partial update
        BidPrjMilestone partialUpdatedBidPrjMilestone = new BidPrjMilestone();
        partialUpdatedBidPrjMilestone.setId(bidPrjMilestone.getId());

        partialUpdatedBidPrjMilestone
            .wkut(UPDATED_WKUT)
            .applyDate(UPDATED_APPLY_DATE)
            .milestoneNo(UPDATED_MILESTONE_NO)
            .milestoneName(UPDATED_MILESTONE_NAME)
            .milestoneType(UPDATED_MILESTONE_TYPE)
            .actualDate(UPDATED_ACTUAL_DATE)
            .remark(UPDATED_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);

        restBidPrjMilestoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjMilestone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjMilestone))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjMilestone in the database
        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
        BidPrjMilestone testBidPrjMilestone = bidPrjMilestoneList.get(bidPrjMilestoneList.size() - 1);
        assertThat(testBidPrjMilestone.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjMilestone.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjMilestone.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testBidPrjMilestone.getMilestoneNo()).isEqualTo(UPDATED_MILESTONE_NO);
        assertThat(testBidPrjMilestone.getMilestoneName()).isEqualTo(UPDATED_MILESTONE_NAME);
        assertThat(testBidPrjMilestone.getMilestoneType()).isEqualTo(UPDATED_MILESTONE_TYPE);
        assertThat(testBidPrjMilestone.getMilestoneStatus()).isEqualTo(DEFAULT_MILESTONE_STATUS);
        assertThat(testBidPrjMilestone.getEstimateDate()).isEqualTo(DEFAULT_ESTIMATE_DATE);
        assertThat(testBidPrjMilestone.getActualDate()).isEqualTo(UPDATED_ACTUAL_DATE);
        assertThat(testBidPrjMilestone.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjMilestone.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjMilestone.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjMilestone.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjMilestone.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjMilestoneWithPatch() throws Exception {
        // Initialize the database
        bidPrjMilestoneRepository.saveAndFlush(bidPrjMilestone);

        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();

        // Update the bidPrjMilestone using partial update
        BidPrjMilestone partialUpdatedBidPrjMilestone = new BidPrjMilestone();
        partialUpdatedBidPrjMilestone.setId(bidPrjMilestone.getId());

        partialUpdatedBidPrjMilestone
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .applyDate(UPDATED_APPLY_DATE)
            .milestoneNo(UPDATED_MILESTONE_NO)
            .milestoneName(UPDATED_MILESTONE_NAME)
            .milestoneType(UPDATED_MILESTONE_TYPE)
            .milestoneStatus(UPDATED_MILESTONE_STATUS)
            .estimateDate(UPDATED_ESTIMATE_DATE)
            .actualDate(UPDATED_ACTUAL_DATE)
            .remark(UPDATED_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);

        restBidPrjMilestoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjMilestone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjMilestone))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjMilestone in the database
        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
        BidPrjMilestone testBidPrjMilestone = bidPrjMilestoneList.get(bidPrjMilestoneList.size() - 1);
        assertThat(testBidPrjMilestone.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjMilestone.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjMilestone.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testBidPrjMilestone.getMilestoneNo()).isEqualTo(UPDATED_MILESTONE_NO);
        assertThat(testBidPrjMilestone.getMilestoneName()).isEqualTo(UPDATED_MILESTONE_NAME);
        assertThat(testBidPrjMilestone.getMilestoneType()).isEqualTo(UPDATED_MILESTONE_TYPE);
        assertThat(testBidPrjMilestone.getMilestoneStatus()).isEqualTo(UPDATED_MILESTONE_STATUS);
        assertThat(testBidPrjMilestone.getEstimateDate()).isEqualTo(UPDATED_ESTIMATE_DATE);
        assertThat(testBidPrjMilestone.getActualDate()).isEqualTo(UPDATED_ACTUAL_DATE);
        assertThat(testBidPrjMilestone.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjMilestone.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjMilestone.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjMilestone.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjMilestone.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjMilestone() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();
//        bidPrjMilestone.setId(count.incrementAndGet());
//
//        // Create the BidPrjMilestone
//        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(bidPrjMilestone);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjMilestoneMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjMilestoneDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMilestone in the database
//        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
//        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjMilestone() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();
//        bidPrjMilestone.setId(count.incrementAndGet());
//
//        // Create the BidPrjMilestone
//        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(bidPrjMilestone);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjMilestoneMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjMilestone in the database
//        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
//        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjMilestone() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjMilestoneRepository.findAll().size();
//        bidPrjMilestone.setId(count.incrementAndGet());
//
//        // Create the BidPrjMilestone
//        BidPrjMilestoneDTO bidPrjMilestoneDTO = bidPrjMilestoneMapper.toDto(bidPrjMilestone);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjMilestoneMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjMilestoneDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjMilestone in the database
//        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
//        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjMilestone() throws Exception {
        // Initialize the database
        bidPrjMilestoneRepository.saveAndFlush(bidPrjMilestone);

        int databaseSizeBeforeDelete = bidPrjMilestoneRepository.findAll().size();

        // Delete the bidPrjMilestone
        restBidPrjMilestoneMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjMilestone.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjMilestone> bidPrjMilestoneList = bidPrjMilestoneRepository.findAll();
        assertThat(bidPrjMilestoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
