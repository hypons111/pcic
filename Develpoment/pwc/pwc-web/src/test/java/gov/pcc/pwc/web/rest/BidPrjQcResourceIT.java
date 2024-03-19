package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjQc;
import gov.pcc.pwc.repository.BidPrjQcRepository;
import gov.pcc.pwc.service.dto.BidPrjQcDTO;
import gov.pcc.pwc.service.mapper.BidPrjQcMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link BidPrjQcResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjQcResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJ_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRJ_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DSTS = "AA";
    private static final String UPDATED_DSTS = "BB";

    private static final String DEFAULT_JOB_TYPE = "A";
    private static final String UPDATED_JOB_TYPE = "B";

    private static final String DEFAULT_MAJOR = "AAAAAAAAAA";
    private static final String UPDATED_MAJOR = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_TEL = "AAAAAAAAAA";
    private static final String UPDATED_SITE_TEL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DUTY = "A";
    private static final String UPDATED_DUTY = "B";

    private static final String DEFAULT_RE_ENTRY = "A";
    private static final String UPDATED_RE_ENTRY = "B";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_MAN = "AAAAAAAAAA";
    private static final String UPDATED_MAN = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-qcs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private BidPrjQcRepository bidPrjQcRepository;

    @Autowired
    private BidPrjQcMapper bidPrjQcMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjQcMockMvc;

    private BidPrjQc bidPrjQc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjQc createEntity(EntityManager em) {
        BidPrjQc bidPrjQc = new BidPrjQc()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJ_NO)
            .dsts(DEFAULT_DSTS)
            .jobType(DEFAULT_JOB_TYPE)
            .major(DEFAULT_MAJOR)
            .company(DEFAULT_COMPANY)
            .siteTel(DEFAULT_SITE_TEL)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .duty(DEFAULT_DUTY)
            .reEntry(DEFAULT_RE_ENTRY)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .remark(DEFAULT_REMARK)
            .man(DEFAULT_MAN)
            .tel(DEFAULT_TEL);
        return bidPrjQc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjQc createUpdatedEntity(EntityManager em) {
        BidPrjQc bidPrjQc = new BidPrjQc()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJ_NO)
            .dsts(UPDATED_DSTS)
            .jobType(UPDATED_JOB_TYPE)
            .major(UPDATED_MAJOR)
            .company(UPDATED_COMPANY)
            .siteTel(UPDATED_SITE_TEL)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .duty(UPDATED_DUTY)
            .reEntry(UPDATED_RE_ENTRY)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .remark(UPDATED_REMARK)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL);
        return bidPrjQc;
    }

    @BeforeEach
    public void initTest() {
        bidPrjQc = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjQc() throws Exception {
        int databaseSizeBeforeCreate = bidPrjQcRepository.findAll().size();
        // Create the BidPrjQc
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(bidPrjQc);
        restBidPrjQcMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO)))
            .andExpect(status().isCreated());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjQc testBidPrjQc = bidPrjQcList.get(bidPrjQcList.size() - 1);
        assertThat(testBidPrjQc.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjQc.getPrjno()).isEqualTo(DEFAULT_PRJ_NO);
        assertThat(testBidPrjQc.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjQc.getJobType()).isEqualTo(DEFAULT_JOB_TYPE);
        assertThat(testBidPrjQc.getMajor()).isEqualTo(DEFAULT_MAJOR);
        assertThat(testBidPrjQc.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testBidPrjQc.getSiteTel()).isEqualTo(DEFAULT_SITE_TEL);
        assertThat(testBidPrjQc.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBidPrjQc.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testBidPrjQc.getDuty()).isEqualTo(DEFAULT_DUTY);
        assertThat(testBidPrjQc.getReEntry()).isEqualTo(DEFAULT_RE_ENTRY);
        assertThat(testBidPrjQc.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjQc.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjQc.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjQc.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjQc.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBidPrjQc.getMan()).isEqualTo(DEFAULT_MAN);
        assertThat(testBidPrjQc.getTel()).isEqualTo(DEFAULT_TEL);
    }

    @Test
    @Transactional
    void createBidPrjQcWithExistingId() throws Exception {
        // Create the BidPrjQc with an existing ID
        bidPrjQc.setId("existing_id");
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(bidPrjQc);

        int databaseSizeBeforeCreate = bidPrjQcRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidPrjQcMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidPrjQcs() throws Exception {
        // Initialize the database
        bidPrjQc.setId(UUID.randomUUID().toString());
        bidPrjQcRepository.saveAndFlush(bidPrjQc);

        // Get all the bidPrjQcList
        restBidPrjQcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjQc.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjNo").value(hasItem(DEFAULT_PRJ_NO)))
            .andExpect(jsonPath("$.[*].dsts").value(hasItem(DEFAULT_DSTS)))
            .andExpect(jsonPath("$.[*].jobType").value(hasItem(DEFAULT_JOB_TYPE)))
            .andExpect(jsonPath("$.[*].major").value(hasItem(DEFAULT_MAJOR)))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].siteTel").value(hasItem(DEFAULT_SITE_TEL)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].duty").value(hasItem(DEFAULT_DUTY)))
            .andExpect(jsonPath("$.[*].reEntry").value(hasItem(DEFAULT_RE_ENTRY)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].man").value(hasItem(DEFAULT_MAN)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)));
    }

    @Test
    @Transactional
    void getBidPrjQc() throws Exception {
        // Initialize the database
        bidPrjQc.setId(UUID.randomUUID().toString());
        bidPrjQcRepository.saveAndFlush(bidPrjQc);

        // Get the bidPrjQc
        restBidPrjQcMockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjQc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjQc.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjNo").value(DEFAULT_PRJ_NO))
            .andExpect(jsonPath("$.dsts").value(DEFAULT_DSTS))
            .andExpect(jsonPath("$.jobType").value(DEFAULT_JOB_TYPE))
            .andExpect(jsonPath("$.major").value(DEFAULT_MAJOR))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.siteTel").value(DEFAULT_SITE_TEL))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.duty").value(DEFAULT_DUTY))
            .andExpect(jsonPath("$.reEntry").value(DEFAULT_RE_ENTRY))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.man").value(DEFAULT_MAN))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjQc() throws Exception {
        // Get the bidPrjQc
        restBidPrjQcMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjQc() throws Exception {
        // Initialize the database
        bidPrjQc.setId(UUID.randomUUID().toString());
        bidPrjQcRepository.saveAndFlush(bidPrjQc);

        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();

        // Update the bidPrjQc
        BidPrjQc updatedBidPrjQc = bidPrjQcRepository.findById(bidPrjQc.getPrjQcKey()).get();
        // Disconnect from session so that the updates on updatedBidPrjQc are not directly saved in db
        em.detach(updatedBidPrjQc);
        updatedBidPrjQc
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJ_NO)
            .dsts(UPDATED_DSTS)
            .jobType(UPDATED_JOB_TYPE)
            .major(UPDATED_MAJOR)
            .company(UPDATED_COMPANY)
            .siteTel(UPDATED_SITE_TEL)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .duty(UPDATED_DUTY)
            .reEntry(UPDATED_RE_ENTRY)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .remark(UPDATED_REMARK)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL);
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(updatedBidPrjQc);

        restBidPrjQcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjQcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjQc testBidPrjQc = bidPrjQcList.get(bidPrjQcList.size() - 1);
        assertThat(testBidPrjQc.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjQc.getPrjno()).isEqualTo(UPDATED_PRJ_NO);
        assertThat(testBidPrjQc.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjQc.getJobType()).isEqualTo(UPDATED_JOB_TYPE);
        assertThat(testBidPrjQc.getMajor()).isEqualTo(UPDATED_MAJOR);
        assertThat(testBidPrjQc.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testBidPrjQc.getSiteTel()).isEqualTo(UPDATED_SITE_TEL);
        assertThat(testBidPrjQc.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBidPrjQc.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBidPrjQc.getDuty()).isEqualTo(UPDATED_DUTY);
        assertThat(testBidPrjQc.getReEntry()).isEqualTo(UPDATED_RE_ENTRY);
        assertThat(testBidPrjQc.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjQc.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjQc.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjQc.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjQc.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjQc.getMan()).isEqualTo(UPDATED_MAN);
        assertThat(testBidPrjQc.getTel()).isEqualTo(UPDATED_TEL);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjQc() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();
        bidPrjQc.setId(UUID.randomUUID().toString());

        // Create the BidPrjQc
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(bidPrjQc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBidPrjQcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjQcDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjQc() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();
        bidPrjQc.setId(UUID.randomUUID().toString());

        // Create the BidPrjQc
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(bidPrjQc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjQcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjQc() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();
        bidPrjQc.setId(UUID.randomUUID().toString());

        // Create the BidPrjQc
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(bidPrjQc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjQcMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjQcWithPatch() throws Exception {
        // Initialize the database
        bidPrjQc.setId(UUID.randomUUID().toString());
        bidPrjQcRepository.saveAndFlush(bidPrjQc);

        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();

        // Update the bidPrjQc using partial update
        BidPrjQc partialUpdatedBidPrjQc = new BidPrjQc();
        partialUpdatedBidPrjQc.setId(bidPrjQc.getId());

        partialUpdatedBidPrjQc
            .prjno(UPDATED_PRJ_NO)
            .dsts(UPDATED_DSTS)
            .siteTel(UPDATED_SITE_TEL)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .reEntry(UPDATED_RE_ENTRY)
            .createDate(UPDATED_CREATE_DATE)
            .remark(UPDATED_REMARK)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL);

        restBidPrjQcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjQc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjQc))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjQc testBidPrjQc = bidPrjQcList.get(bidPrjQcList.size() - 1);
        assertThat(testBidPrjQc.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjQc.getPrjno()).isEqualTo(UPDATED_PRJ_NO);
        assertThat(testBidPrjQc.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjQc.getJobType()).isEqualTo(DEFAULT_JOB_TYPE);
        assertThat(testBidPrjQc.getMajor()).isEqualTo(DEFAULT_MAJOR);
        assertThat(testBidPrjQc.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testBidPrjQc.getSiteTel()).isEqualTo(UPDATED_SITE_TEL);
        assertThat(testBidPrjQc.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBidPrjQc.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBidPrjQc.getDuty()).isEqualTo(DEFAULT_DUTY);
        assertThat(testBidPrjQc.getReEntry()).isEqualTo(UPDATED_RE_ENTRY);
        assertThat(testBidPrjQc.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjQc.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjQc.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjQc.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjQc.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjQc.getMan()).isEqualTo(UPDATED_MAN);
        assertThat(testBidPrjQc.getTel()).isEqualTo(UPDATED_TEL);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjQcWithPatch() throws Exception {
        // Initialize the database
        bidPrjQc.setId(UUID.randomUUID().toString());
        bidPrjQcRepository.saveAndFlush(bidPrjQc);

        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();

        // Update the bidPrjQc using partial update
        BidPrjQc partialUpdatedBidPrjQc = new BidPrjQc();
        partialUpdatedBidPrjQc.setId(bidPrjQc.getId());

        partialUpdatedBidPrjQc
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJ_NO)
            .dsts(UPDATED_DSTS)
            .jobType(UPDATED_JOB_TYPE)
            .major(UPDATED_MAJOR)
            .company(UPDATED_COMPANY)
            .siteTel(UPDATED_SITE_TEL)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .duty(UPDATED_DUTY)
            .reEntry(UPDATED_RE_ENTRY)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .remark(UPDATED_REMARK)
            .man(UPDATED_MAN)
            .tel(UPDATED_TEL);

        restBidPrjQcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjQc.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjQc))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
        BidPrjQc testBidPrjQc = bidPrjQcList.get(bidPrjQcList.size() - 1);
        assertThat(testBidPrjQc.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjQc.getPrjno()).isEqualTo(UPDATED_PRJ_NO);
        assertThat(testBidPrjQc.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjQc.getJobType()).isEqualTo(UPDATED_JOB_TYPE);
        assertThat(testBidPrjQc.getMajor()).isEqualTo(UPDATED_MAJOR);
        assertThat(testBidPrjQc.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testBidPrjQc.getSiteTel()).isEqualTo(UPDATED_SITE_TEL);
        assertThat(testBidPrjQc.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBidPrjQc.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBidPrjQc.getDuty()).isEqualTo(UPDATED_DUTY);
        assertThat(testBidPrjQc.getReEntry()).isEqualTo(UPDATED_RE_ENTRY);
        assertThat(testBidPrjQc.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjQc.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjQc.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjQc.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjQc.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBidPrjQc.getMan()).isEqualTo(UPDATED_MAN);
        assertThat(testBidPrjQc.getTel()).isEqualTo(UPDATED_TEL);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjQc() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();
        bidPrjQc.setId(UUID.randomUUID().toString());

        // Create the BidPrjQc
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(bidPrjQc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBidPrjQcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bidPrjQcDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjQc() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();
        bidPrjQc.setId(UUID.randomUUID().toString());

        // Create the BidPrjQc
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(bidPrjQc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjQcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjQc() throws Exception {
        int databaseSizeBeforeUpdate = bidPrjQcRepository.findAll().size();
        bidPrjQc.setId(UUID.randomUUID().toString());

        // Create the BidPrjQc
        BidPrjQcDTO bidPrjQcDTO = bidPrjQcMapper.toDto(bidPrjQc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBidPrjQcMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjQcDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BidPrjQc in the database
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjQc() throws Exception {
        // Initialize the database
        bidPrjQc.setId(UUID.randomUUID().toString());
        bidPrjQcRepository.saveAndFlush(bidPrjQc);

        int databaseSizeBeforeDelete = bidPrjQcRepository.findAll().size();

        // Delete the bidPrjQc
        restBidPrjQcMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjQc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjQc> bidPrjQcList = bidPrjQcRepository.findAll();
        assertThat(bidPrjQcList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
