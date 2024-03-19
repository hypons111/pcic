package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwbRoleApprove;
import gov.pcc.pwc.repository.PwbRoleApproveRepository;
import gov.pcc.pwc.service.dto.PwbRoleApproveDTO;
import gov.pcc.pwc.service.mapper.PwbRoleApproveMapper;
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
 * Integration tests for the {@link PwbRoleApproveResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwbRoleApproveResourceIT {

    private static final String DEFAULT_ROLE_TYPE = "AAA";
    private static final String UPDATED_ROLE_TYPE = "BBB";

    private static final String DEFAULT_MODULE_TYPE = "AAA";
    private static final String UPDATED_MODULE_TYPE = "BBB";

    private static final String DEFAULT_BUSINESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pwb-role-approves";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwbRoleApproveRepository pwbRoleApproveRepository;

    @Autowired
    private PwbRoleApproveMapper pwbRoleApproveMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwbRoleApproveMockMvc;

    private PwbRoleApprove pwbRoleApprove;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbRoleApprove createEntity(EntityManager em) {
        PwbRoleApprove pwbRoleApprove = new PwbRoleApprove()
            .roleType(DEFAULT_ROLE_TYPE)
            .moduleType(DEFAULT_MODULE_TYPE)
            .businessType(DEFAULT_BUSINESS_TYPE)
            .userId(DEFAULT_USER_ID)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return pwbRoleApprove;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbRoleApprove createUpdatedEntity(EntityManager em) {
        PwbRoleApprove pwbRoleApprove = new PwbRoleApprove()
            .roleType(UPDATED_ROLE_TYPE)
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .userId(UPDATED_USER_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return pwbRoleApprove;
    }

    @BeforeEach
    public void initTest() {
        pwbRoleApprove = createEntity(em);
    }

    @Test
    @Transactional
    void createPwbRoleApprove() throws Exception {
        int databaseSizeBeforeCreate = pwbRoleApproveRepository.findAll().size();
        // Create the PwbRoleApprove
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);
        restPwbRoleApproveMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeCreate + 1);
        PwbRoleApprove testPwbRoleApprove = pwbRoleApproveList.get(pwbRoleApproveList.size() - 1);
        assertThat(testPwbRoleApprove.getRoleType()).isEqualTo(DEFAULT_ROLE_TYPE);
        assertThat(testPwbRoleApprove.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testPwbRoleApprove.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testPwbRoleApprove.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testPwbRoleApprove.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbRoleApprove.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwbRoleApprove.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testPwbRoleApprove.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createPwbRoleApproveWithExistingId() throws Exception {
        // Create the PwbRoleApprove with an existing ID
        pwbRoleApprove.setId(1L);
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        int databaseSizeBeforeCreate = pwbRoleApproveRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwbRoleApproveMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRoleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApproveRepository.findAll().size();
        // set the field null
        pwbRoleApprove.setRoleType(null);

        // Create the PwbRoleApprove, which fails.
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        restPwbRoleApproveMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModuleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApproveRepository.findAll().size();
        // set the field null
        pwbRoleApprove.setModuleType(null);

        // Create the PwbRoleApprove, which fails.
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        restPwbRoleApproveMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApproveRepository.findAll().size();
        // set the field null
        pwbRoleApprove.setUpdateUser(null);

        // Create the PwbRoleApprove, which fails.
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        restPwbRoleApproveMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApproveRepository.findAll().size();
        // set the field null
        pwbRoleApprove.setUpdateTime(null);

        // Create the PwbRoleApprove, which fails.
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        restPwbRoleApproveMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApproveRepository.findAll().size();
        // set the field null
        pwbRoleApprove.setCreateUser(null);

        // Create the PwbRoleApprove, which fails.
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        restPwbRoleApproveMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleApproveRepository.findAll().size();
        // set the field null
        pwbRoleApprove.setCreateTime(null);

        // Create the PwbRoleApprove, which fails.
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        restPwbRoleApproveMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPwbRoleApproves() throws Exception {
        // Initialize the database
        pwbRoleApproveRepository.saveAndFlush(pwbRoleApprove);

        // Get all the pwbRoleApproveList
        restPwbRoleApproveMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pwbRoleApprove.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleType").value(hasItem(DEFAULT_ROLE_TYPE)))
            .andExpect(jsonPath("$.[*].moduleType").value(hasItem(DEFAULT_MODULE_TYPE)))
            .andExpect(jsonPath("$.[*].businessType").value(hasItem(DEFAULT_BUSINESS_TYPE)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getPwbRoleApprove() throws Exception {
        // Initialize the database
        pwbRoleApproveRepository.saveAndFlush(pwbRoleApprove);

        // Get the pwbRoleApprove
        restPwbRoleApproveMockMvc
            .perform(get(ENTITY_API_URL_ID, pwbRoleApprove.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pwbRoleApprove.getId().intValue()))
            .andExpect(jsonPath("$.roleType").value(DEFAULT_ROLE_TYPE))
            .andExpect(jsonPath("$.moduleType").value(DEFAULT_MODULE_TYPE))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPwbRoleApprove() throws Exception {
        // Get the pwbRoleApprove
        restPwbRoleApproveMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwbRoleApprove() throws Exception {
        // Initialize the database
        pwbRoleApproveRepository.saveAndFlush(pwbRoleApprove);

        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();

        // Update the pwbRoleApprove
        PwbRoleApprove updatedPwbRoleApprove = pwbRoleApproveRepository.findById(pwbRoleApprove.getId()).get();
        // Disconnect from session so that the updates on updatedPwbRoleApprove are not directly saved in db
        em.detach(updatedPwbRoleApprove);
        updatedPwbRoleApprove
            .roleType(UPDATED_ROLE_TYPE)
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .userId(UPDATED_USER_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(updatedPwbRoleApprove);

        restPwbRoleApproveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbRoleApproveDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleApprove testPwbRoleApprove = pwbRoleApproveList.get(pwbRoleApproveList.size() - 1);
        assertThat(testPwbRoleApprove.getRoleType()).isEqualTo(UPDATED_ROLE_TYPE);
        assertThat(testPwbRoleApprove.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbRoleApprove.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testPwbRoleApprove.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbRoleApprove.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbRoleApprove.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbRoleApprove.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleApprove.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingPwbRoleApprove() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();
        pwbRoleApprove.setId(count.incrementAndGet());

        // Create the PwbRoleApprove
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbRoleApproveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbRoleApproveDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwbRoleApprove() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();
        pwbRoleApprove.setId(count.incrementAndGet());

        // Create the PwbRoleApprove
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleApproveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwbRoleApprove() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();
        pwbRoleApprove.setId(count.incrementAndGet());

        // Create the PwbRoleApprove
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleApproveMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwbRoleApproveWithPatch() throws Exception {
        // Initialize the database
        pwbRoleApproveRepository.saveAndFlush(pwbRoleApprove);

        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();

        // Update the pwbRoleApprove using partial update
        PwbRoleApprove partialUpdatedPwbRoleApprove = new PwbRoleApprove();
        partialUpdatedPwbRoleApprove.setId(pwbRoleApprove.getId());

        partialUpdatedPwbRoleApprove
            .roleType(UPDATED_ROLE_TYPE)
            .moduleType(UPDATED_MODULE_TYPE)
            .userId(UPDATED_USER_ID)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwbRoleApproveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbRoleApprove.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbRoleApprove))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleApprove testPwbRoleApprove = pwbRoleApproveList.get(pwbRoleApproveList.size() - 1);
        assertThat(testPwbRoleApprove.getRoleType()).isEqualTo(UPDATED_ROLE_TYPE);
        assertThat(testPwbRoleApprove.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbRoleApprove.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testPwbRoleApprove.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbRoleApprove.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbRoleApprove.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbRoleApprove.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleApprove.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdatePwbRoleApproveWithPatch() throws Exception {
        // Initialize the database
        pwbRoleApproveRepository.saveAndFlush(pwbRoleApprove);

        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();

        // Update the pwbRoleApprove using partial update
        PwbRoleApprove partialUpdatedPwbRoleApprove = new PwbRoleApprove();
        partialUpdatedPwbRoleApprove.setId(pwbRoleApprove.getId());

        partialUpdatedPwbRoleApprove
            .roleType(UPDATED_ROLE_TYPE)
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .userId(UPDATED_USER_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restPwbRoleApproveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbRoleApprove.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbRoleApprove))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleApprove testPwbRoleApprove = pwbRoleApproveList.get(pwbRoleApproveList.size() - 1);
        assertThat(testPwbRoleApprove.getRoleType()).isEqualTo(UPDATED_ROLE_TYPE);
        assertThat(testPwbRoleApprove.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbRoleApprove.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testPwbRoleApprove.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbRoleApprove.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbRoleApprove.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbRoleApprove.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleApprove.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingPwbRoleApprove() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();
        pwbRoleApprove.setId(count.incrementAndGet());

        // Create the PwbRoleApprove
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbRoleApproveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwbRoleApproveDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwbRoleApprove() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();
        pwbRoleApprove.setId(count.incrementAndGet());

        // Create the PwbRoleApprove
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleApproveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwbRoleApprove() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleApproveRepository.findAll().size();
        pwbRoleApprove.setId(count.incrementAndGet());

        // Create the PwbRoleApprove
        PwbRoleApproveDTO pwbRoleApproveDTO = pwbRoleApproveMapper.toDto(pwbRoleApprove);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleApproveMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleApproveDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbRoleApprove in the database
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwbRoleApprove() throws Exception {
        // Initialize the database
        pwbRoleApproveRepository.saveAndFlush(pwbRoleApprove);

        int databaseSizeBeforeDelete = pwbRoleApproveRepository.findAll().size();

        // Delete the pwbRoleApprove
        restPwbRoleApproveMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwbRoleApprove.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwbRoleApprove> pwbRoleApproveList = pwbRoleApproveRepository.findAll();
        assertThat(pwbRoleApproveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
