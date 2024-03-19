package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwbRoleApply;
import gov.pcc.pwc.repository.PwbRoleApplyRepository;
import gov.pcc.pwc.service.dto.PwbRoleApplyDTO;
import gov.pcc.pwc.service.mapper.PwbRoleApplyMapper;
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
 * Integration tests for the {@link PwbRoleApplyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwbRoleApplyResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPLY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPLY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_APPROVE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final String DEFAULT_MODULE_TYPE = "AAA";
    private static final String UPDATED_MODULE_TYPE = "BBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pwb-role-applies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwbRoleApplyRepository pwbRoleApplyRepository;

    @Autowired
    private PwbRoleApplyMapper pwbRoleApplyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwbRoleApplyMockMvc;

    private PwbRoleApply pwbRoleApply;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbRoleApply createEntity(EntityManager em) {
        PwbRoleApply pwbRoleApply = new PwbRoleApply()
            .userId(DEFAULT_USER_ID)
            .applyTime(DEFAULT_APPLY_TIME)
            .approveTime(DEFAULT_APPROVE_TIME)
            .status(DEFAULT_STATUS)
            .moduleType(DEFAULT_MODULE_TYPE)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return pwbRoleApply;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbRoleApply createUpdatedEntity(EntityManager em) {
        PwbRoleApply pwbRoleApply = new PwbRoleApply()
            .userId(UPDATED_USER_ID)
            .applyTime(UPDATED_APPLY_TIME)
            .approveTime(UPDATED_APPROVE_TIME)
            .status(UPDATED_STATUS)
            .moduleType(UPDATED_MODULE_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return pwbRoleApply;
    }

    @BeforeEach
    public void initTest() {
        pwbRoleApply = createEntity(em);
    }

    @Test
    @Transactional
    void createPwbRoleApply() throws Exception {
        int databaseSizeBeforeCreate = pwbRoleApplyRepository.findAll().size();
        // Create the PwbRoleApply
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);
        restPwbRoleApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeCreate + 1);
        PwbRoleApply testPwbRoleApply = pwbRoleApplyList.get(pwbRoleApplyList.size() - 1);
        assertThat(testPwbRoleApply.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testPwbRoleApply.getApplyTime()).isEqualTo(DEFAULT_APPLY_TIME);
        assertThat(testPwbRoleApply.getApproveTime()).isEqualTo(DEFAULT_APPROVE_TIME);
        assertThat(testPwbRoleApply.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPwbRoleApply.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testPwbRoleApply.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbRoleApply.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwbRoleApply.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testPwbRoleApply.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createPwbRoleApplyWithExistingId() throws Exception {
        // Create the PwbRoleApply with an existing ID
        pwbRoleApply.setId(1L);
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        int databaseSizeBeforeCreate = pwbRoleApplyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwbRoleApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApplyRepository.findAll().size();
        // set the field null
        pwbRoleApply.setUserId(null);

        // Create the PwbRoleApply, which fails.
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        restPwbRoleApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModuleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApplyRepository.findAll().size();
        // set the field null
        pwbRoleApply.setModuleType(null);

        // Create the PwbRoleApply, which fails.
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        restPwbRoleApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApplyRepository.findAll().size();
        // set the field null
        pwbRoleApply.setUpdateUser(null);

        // Create the PwbRoleApply, which fails.
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        restPwbRoleApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApplyRepository.findAll().size();
        // set the field null
        pwbRoleApply.setUpdateTime(null);

        // Create the PwbRoleApply, which fails.
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        restPwbRoleApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApplyRepository.findAll().size();
        // set the field null
        pwbRoleApply.setCreateUser(null);

        // Create the PwbRoleApply, which fails.
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        restPwbRoleApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApplyRepository.findAll().size();
        // set the field null
        pwbRoleApply.setCreateTime(null);

        // Create the PwbRoleApply, which fails.
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        restPwbRoleApplyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPwbRoleApplies() throws Exception {
        // Initialize the database
        pwbRoleApplyRepository.saveAndFlush(pwbRoleApply);

        // Get all the pwbRoleApplyList
        restPwbRoleApplyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pwbRoleApply.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].applyTime").value(hasItem(DEFAULT_APPLY_TIME.toString())))
            .andExpect(jsonPath("$.[*].approveTime").value(hasItem(DEFAULT_APPROVE_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].moduleType").value(hasItem(DEFAULT_MODULE_TYPE)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getPwbRoleApply() throws Exception {
        // Initialize the database
        pwbRoleApplyRepository.saveAndFlush(pwbRoleApply);

        // Get the pwbRoleApply
        restPwbRoleApplyMockMvc
            .perform(get(ENTITY_API_URL_ID, pwbRoleApply.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pwbRoleApply.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.applyTime").value(DEFAULT_APPLY_TIME.toString()))
            .andExpect(jsonPath("$.approveTime").value(DEFAULT_APPROVE_TIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.moduleType").value(DEFAULT_MODULE_TYPE))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPwbRoleApply() throws Exception {
        // Get the pwbRoleApply
        restPwbRoleApplyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwbRoleApply() throws Exception {
        // Initialize the database
        pwbRoleApplyRepository.saveAndFlush(pwbRoleApply);

        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();

        // Update the pwbRoleApply
        PwbRoleApply updatedPwbRoleApply = pwbRoleApplyRepository.findById(pwbRoleApply.getId()).get();
        // Disconnect from session so that the updates on updatedPwbRoleApply are not directly saved in db
        em.detach(updatedPwbRoleApply);
        updatedPwbRoleApply
            .userId(UPDATED_USER_ID)
            .applyTime(UPDATED_APPLY_TIME)
            .approveTime(UPDATED_APPROVE_TIME)
            .status(UPDATED_STATUS)
            .moduleType(UPDATED_MODULE_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(updatedPwbRoleApply);

        restPwbRoleApplyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbRoleApplyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleApply testPwbRoleApply = pwbRoleApplyList.get(pwbRoleApplyList.size() - 1);
        assertThat(testPwbRoleApply.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbRoleApply.getApplyTime()).isEqualTo(UPDATED_APPLY_TIME);
        assertThat(testPwbRoleApply.getApproveTime()).isEqualTo(UPDATED_APPROVE_TIME);
        assertThat(testPwbRoleApply.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPwbRoleApply.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbRoleApply.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbRoleApply.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbRoleApply.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleApply.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingPwbRoleApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();
        pwbRoleApply.setId(count.incrementAndGet());

        // Create the PwbRoleApply
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbRoleApplyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbRoleApplyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwbRoleApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();
        pwbRoleApply.setId(count.incrementAndGet());

        // Create the PwbRoleApply
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleApplyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwbRoleApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();
        pwbRoleApply.setId(count.incrementAndGet());

        // Create the PwbRoleApply
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleApplyMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwbRoleApplyWithPatch() throws Exception {
        // Initialize the database
        pwbRoleApplyRepository.saveAndFlush(pwbRoleApply);

        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();

        // Update the pwbRoleApply using partial update
        PwbRoleApply partialUpdatedPwbRoleApply = new PwbRoleApply();
        partialUpdatedPwbRoleApply.setId(pwbRoleApply.getId());

        partialUpdatedPwbRoleApply
            .userId(UPDATED_USER_ID)
            .applyTime(UPDATED_APPLY_TIME)
            .status(UPDATED_STATUS)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwbRoleApplyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbRoleApply.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbRoleApply))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleApply testPwbRoleApply = pwbRoleApplyList.get(pwbRoleApplyList.size() - 1);
        assertThat(testPwbRoleApply.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbRoleApply.getApplyTime()).isEqualTo(UPDATED_APPLY_TIME);
        assertThat(testPwbRoleApply.getApproveTime()).isEqualTo(DEFAULT_APPROVE_TIME);
        assertThat(testPwbRoleApply.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPwbRoleApply.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testPwbRoleApply.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbRoleApply.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbRoleApply.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleApply.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdatePwbRoleApplyWithPatch() throws Exception {
        // Initialize the database
        pwbRoleApplyRepository.saveAndFlush(pwbRoleApply);

        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();

        // Update the pwbRoleApply using partial update
        PwbRoleApply partialUpdatedPwbRoleApply = new PwbRoleApply();
        partialUpdatedPwbRoleApply.setId(pwbRoleApply.getId());

        partialUpdatedPwbRoleApply
            .userId(UPDATED_USER_ID)
            .applyTime(UPDATED_APPLY_TIME)
            .approveTime(UPDATED_APPROVE_TIME)
            .status(UPDATED_STATUS)
            .moduleType(UPDATED_MODULE_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restPwbRoleApplyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbRoleApply.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbRoleApply))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleApply testPwbRoleApply = pwbRoleApplyList.get(pwbRoleApplyList.size() - 1);
        assertThat(testPwbRoleApply.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbRoleApply.getApplyTime()).isEqualTo(UPDATED_APPLY_TIME);
        assertThat(testPwbRoleApply.getApproveTime()).isEqualTo(UPDATED_APPROVE_TIME);
        assertThat(testPwbRoleApply.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPwbRoleApply.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbRoleApply.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbRoleApply.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbRoleApply.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleApply.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingPwbRoleApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();
        pwbRoleApply.setId(count.incrementAndGet());

        // Create the PwbRoleApply
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbRoleApplyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwbRoleApplyDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwbRoleApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();
        pwbRoleApply.setId(count.incrementAndGet());

        // Create the PwbRoleApply
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleApplyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwbRoleApply() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApplyRepository.findAll().size();
        pwbRoleApply.setId(count.incrementAndGet());

        // Create the PwbRoleApply
        PwbRoleApplyDTO pwbRoleApplyDTO = pwbRoleApplyMapper.toDto(pwbRoleApply);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleApplyMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApplyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbRoleApply in the database
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwbRoleApply() throws Exception {
        // Initialize the database
        pwbRoleApplyRepository.saveAndFlush(pwbRoleApply);

        int databaseSizeBeforeDelete = pwbRoleApplyRepository.findAll().size();

        // Delete the pwbRoleApply
        restPwbRoleApplyMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwbRoleApply.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwbRoleApply> pwbRoleApplyList = pwbRoleApplyRepository.findAll();
        assertThat(pwbRoleApplyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
