package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwbUnitApply;
import gov.pcc.pwc.repository.PwbUnitApplyRepository;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import gov.pcc.pwc.service.mapper.PwbUnitApplyMapper;
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
 * Integration tests for the {@link PwbUnitApplyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwbUnitApplyResourceIT {

    private static final String DEFAULT_UNIT_ID = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPLY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPLY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_APPROVE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final String DEFAULT_MODULE_TYPE = "AAA";
    private static final String UPDATED_MODULE_TYPE = "BBB";

    private static final String DEFAULT_BUSINESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVE_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_APPROVE_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pwb-unit-applies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwbUnitApplyRepository pwbUnitApplyRepository;

    @Autowired
    private PwbUnitApplyMapper pwbUnitApplyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwbUnitApplyMockMvc;

    private PwbUnitApply pwbUnitApply;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbUnitApply createEntity(EntityManager em) {
        PwbUnitApply pwbUnitApply = new PwbUnitApply()
            .unitId(DEFAULT_UNIT_ID)
            .applyTime(DEFAULT_APPLY_TIME)
            .approveTime(DEFAULT_APPROVE_TIME)
            .status(DEFAULT_STATUS)
            .moduleType(DEFAULT_MODULE_TYPE)
            .businessType(DEFAULT_BUSINESS_TYPE)
            .approveUnit(DEFAULT_APPROVE_UNIT)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return pwbUnitApply;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbUnitApply createUpdatedEntity(EntityManager em) {
        PwbUnitApply pwbUnitApply = new PwbUnitApply()
            .unitId(UPDATED_UNIT_ID)
            .applyTime(UPDATED_APPLY_TIME)
            .approveTime(UPDATED_APPROVE_TIME)
            .status(UPDATED_STATUS)
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .approveUnit(UPDATED_APPROVE_UNIT)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return pwbUnitApply;
    }

    @BeforeEach
    public void initTest() {
        pwbUnitApply = createEntity(em);
    }

    @Test
    @Transactional
    void createPwbUnitApply() throws Exception {
        int databaseSizeBeforeCreate = pwbUnitApplyRepository.findAll().size();
        // Create the PwbUnitApply
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);
        restPwbUnitApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeCreate + 1);
        PwbUnitApply testPwbUnitApply = pwbUnitApplyList.get(pwbUnitApplyList.size() - 1);
        assertThat(testPwbUnitApply.getUnitId()).isEqualTo(DEFAULT_UNIT_ID);
        assertThat(testPwbUnitApply.getApplyTime()).isEqualTo(DEFAULT_APPLY_TIME);
        assertThat(testPwbUnitApply.getApproveTime()).isEqualTo(DEFAULT_APPROVE_TIME);
        assertThat(testPwbUnitApply.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPwbUnitApply.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testPwbUnitApply.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testPwbUnitApply.getApproveUnit()).isEqualTo(DEFAULT_APPROVE_UNIT);
        assertThat(testPwbUnitApply.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbUnitApply.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwbUnitApply.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testPwbUnitApply.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createPwbUnitApplyWithExistingId() throws Exception {
        // Create the PwbUnitApply with an existing ID
        pwbUnitApply.setId(1L);
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        int databaseSizeBeforeCreate = pwbUnitApplyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwbUnitApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUnitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitApplyRepository.findAll().size();
        // set the field null
        pwbUnitApply.setUnitId(null);

        // Create the PwbUnitApply, which fails.
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        restPwbUnitApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModuleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitApplyRepository.findAll().size();
        // set the field null
        pwbUnitApply.setModuleType(null);

        // Create the PwbUnitApply, which fails.
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        restPwbUnitApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitApplyRepository.findAll().size();
        // set the field null
        pwbUnitApply.setUpdateUser(null);

        // Create the PwbUnitApply, which fails.
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        restPwbUnitApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitApplyRepository.findAll().size();
        // set the field null
        pwbUnitApply.setUpdateTime(null);

        // Create the PwbUnitApply, which fails.
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        restPwbUnitApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitApplyRepository.findAll().size();
        // set the field null
        pwbUnitApply.setCreateUser(null);

        // Create the PwbUnitApply, which fails.
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        restPwbUnitApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitApplyRepository.findAll().size();
        // set the field null
        pwbUnitApply.setCreateTime(null);

        // Create the PwbUnitApply, which fails.
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        restPwbUnitApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPwbUnitApplies() throws Exception {
        // Initialize the database
        pwbUnitApplyRepository.saveAndFlush(pwbUnitApply);

        // Get all the pwbUnitApplyList
        restPwbUnitApplyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pwbUnitApply.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitId").value(hasItem(DEFAULT_UNIT_ID)))
            .andExpect(jsonPath("$.[*].applyTime").value(hasItem(DEFAULT_APPLY_TIME.toString())))
            .andExpect(jsonPath("$.[*].approveTime").value(hasItem(DEFAULT_APPROVE_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].moduleType").value(hasItem(DEFAULT_MODULE_TYPE)))
            .andExpect(jsonPath("$.[*].businessType").value(hasItem(DEFAULT_BUSINESS_TYPE)))
            .andExpect(jsonPath("$.[*].approveUnit").value(hasItem(DEFAULT_APPROVE_UNIT)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getPwbUnitApply() throws Exception {
        // Initialize the database
        pwbUnitApplyRepository.saveAndFlush(pwbUnitApply);

        // Get the pwbUnitApply
        restPwbUnitApplyMockMvc
            .perform(get(ENTITY_API_URL_ID, pwbUnitApply.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pwbUnitApply.getId().intValue()))
            .andExpect(jsonPath("$.unitId").value(DEFAULT_UNIT_ID))
            .andExpect(jsonPath("$.applyTime").value(DEFAULT_APPLY_TIME.toString()))
            .andExpect(jsonPath("$.approveTime").value(DEFAULT_APPROVE_TIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.moduleType").value(DEFAULT_MODULE_TYPE))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE))
            .andExpect(jsonPath("$.approveUnit").value(DEFAULT_APPROVE_UNIT))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPwbUnitApply() throws Exception {
        // Get the pwbUnitApply
        restPwbUnitApplyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwbUnitApply() throws Exception {
        // Initialize the database
        pwbUnitApplyRepository.saveAndFlush(pwbUnitApply);

        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();

        // Update the pwbUnitApply
        PwbUnitApply updatedPwbUnitApply = pwbUnitApplyRepository.findById(pwbUnitApply.getId()).get();
        // Disconnect from session so that the updates on updatedPwbUnitApply are not directly saved in db
        em.detach(updatedPwbUnitApply);
        updatedPwbUnitApply
            .unitId(UPDATED_UNIT_ID)
            .applyTime(UPDATED_APPLY_TIME)
            .approveTime(UPDATED_APPROVE_TIME)
            .status(UPDATED_STATUS)
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .approveUnit(UPDATED_APPROVE_UNIT)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(updatedPwbUnitApply);

        restPwbUnitApplyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbUnitApplyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
        PwbUnitApply testPwbUnitApply = pwbUnitApplyList.get(pwbUnitApplyList.size() - 1);
        assertThat(testPwbUnitApply.getUnitId()).isEqualTo(UPDATED_UNIT_ID);
        assertThat(testPwbUnitApply.getApplyTime()).isEqualTo(UPDATED_APPLY_TIME);
        assertThat(testPwbUnitApply.getApproveTime()).isEqualTo(UPDATED_APPROVE_TIME);
        assertThat(testPwbUnitApply.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPwbUnitApply.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbUnitApply.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testPwbUnitApply.getApproveUnit()).isEqualTo(UPDATED_APPROVE_UNIT);
        assertThat(testPwbUnitApply.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbUnitApply.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbUnitApply.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbUnitApply.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingPwbUnitApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();
        pwbUnitApply.setId(count.incrementAndGet());

        // Create the PwbUnitApply
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbUnitApplyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbUnitApplyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwbUnitApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();
        pwbUnitApply.setId(count.incrementAndGet());

        // Create the PwbUnitApply
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUnitApplyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwbUnitApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();
        pwbUnitApply.setId(count.incrementAndGet());

        // Create the PwbUnitApply
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUnitApplyMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwbUnitApplyWithPatch() throws Exception {
        // Initialize the database
        pwbUnitApplyRepository.saveAndFlush(pwbUnitApply);

        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();

        // Update the pwbUnitApply using partial update
        PwbUnitApply partialUpdatedPwbUnitApply = new PwbUnitApply();
        partialUpdatedPwbUnitApply.setId(pwbUnitApply.getId());

        partialUpdatedPwbUnitApply.unitId(UPDATED_UNIT_ID).status(UPDATED_STATUS).approveUnit(UPDATED_APPROVE_UNIT);

        restPwbUnitApplyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbUnitApply.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbUnitApply))
            )
            .andExpect(status().isOk());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
        PwbUnitApply testPwbUnitApply = pwbUnitApplyList.get(pwbUnitApplyList.size() - 1);
        assertThat(testPwbUnitApply.getUnitId()).isEqualTo(UPDATED_UNIT_ID);
        assertThat(testPwbUnitApply.getApplyTime()).isEqualTo(DEFAULT_APPLY_TIME);
        assertThat(testPwbUnitApply.getApproveTime()).isEqualTo(DEFAULT_APPROVE_TIME);
        assertThat(testPwbUnitApply.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPwbUnitApply.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testPwbUnitApply.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testPwbUnitApply.getApproveUnit()).isEqualTo(UPDATED_APPROVE_UNIT);
        assertThat(testPwbUnitApply.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbUnitApply.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwbUnitApply.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testPwbUnitApply.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdatePwbUnitApplyWithPatch() throws Exception {
        // Initialize the database
        pwbUnitApplyRepository.saveAndFlush(pwbUnitApply);

        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();

        // Update the pwbUnitApply using partial update
        PwbUnitApply partialUpdatedPwbUnitApply = new PwbUnitApply();
        partialUpdatedPwbUnitApply.setId(pwbUnitApply.getId());

        partialUpdatedPwbUnitApply
            .unitId(UPDATED_UNIT_ID)
            .applyTime(UPDATED_APPLY_TIME)
            .approveTime(UPDATED_APPROVE_TIME)
            .status(UPDATED_STATUS)
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .approveUnit(UPDATED_APPROVE_UNIT)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restPwbUnitApplyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbUnitApply.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbUnitApply))
            )
            .andExpect(status().isOk());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
        PwbUnitApply testPwbUnitApply = pwbUnitApplyList.get(pwbUnitApplyList.size() - 1);
        assertThat(testPwbUnitApply.getUnitId()).isEqualTo(UPDATED_UNIT_ID);
        assertThat(testPwbUnitApply.getApplyTime()).isEqualTo(UPDATED_APPLY_TIME);
        assertThat(testPwbUnitApply.getApproveTime()).isEqualTo(UPDATED_APPROVE_TIME);
        assertThat(testPwbUnitApply.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPwbUnitApply.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbUnitApply.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testPwbUnitApply.getApproveUnit()).isEqualTo(UPDATED_APPROVE_UNIT);
        assertThat(testPwbUnitApply.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbUnitApply.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbUnitApply.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbUnitApply.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingPwbUnitApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();
        pwbUnitApply.setId(count.incrementAndGet());

        // Create the PwbUnitApply
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbUnitApplyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwbUnitApplyDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwbUnitApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();
        pwbUnitApply.setId(count.incrementAndGet());

        // Create the PwbUnitApply
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUnitApplyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwbUnitApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitApplyRepository.findAll().size();
        pwbUnitApply.setId(count.incrementAndGet());

        // Create the PwbUnitApply
        PwbUnitApplyDTO pwbUnitApplyDTO = pwbUnitApplyMapper.toDto(pwbUnitApply);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUnitApplyMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitApplyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbUnitApply in the database
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwbUnitApply() throws Exception {
        // Initialize the database
        pwbUnitApplyRepository.saveAndFlush(pwbUnitApply);

        int databaseSizeBeforeDelete = pwbUnitApplyRepository.findAll().size();

        // Delete the pwbUnitApply
        restPwbUnitApplyMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwbUnitApply.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwbUnitApply> pwbUnitApplyList = pwbUnitApplyRepository.findAll();
        assertThat(pwbUnitApplyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
