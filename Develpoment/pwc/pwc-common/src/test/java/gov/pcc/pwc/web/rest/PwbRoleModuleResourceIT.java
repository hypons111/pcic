package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;

import gov.pcc.pwc.domain.PwbRoleModule;
import gov.pcc.pwc.repository.PwbRoleModuleRepository;
import gov.pcc.pwc.service.dto.PwbRoleModuleDTO;
import gov.pcc.pwc.service.mapper.PwbRoleModuleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PwbRoleModuleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwbRoleModuleResourceIT {

    private static final String DEFAULT_ROLE_TYPE = "A";
    private static final String UPDATED_ROLE_TYPE = "B";

    private static final String DEFAULT_MODULE_TYPE = "AAA";
    private static final String UPDATED_MODULE_TYPE = "BBB";

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_BUSINESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVE_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_APPROVE_LEVEL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pwb-role-modules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwbRoleModuleRepository pwbRoleModuleRepository;

    @Autowired
    private PwbRoleModuleMapper pwbRoleModuleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwbRoleModuleMockMvc;

    private PwbRoleModule pwbRoleModule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbRoleModule createEntity(EntityManager em) {
        PwbRoleModule pwbRoleModule = new PwbRoleModule()
            .roleType(DEFAULT_ROLE_TYPE)
            .moduleType(DEFAULT_MODULE_TYPE)
            .roleId(DEFAULT_ROLE_ID)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .businessType(DEFAULT_BUSINESS_TYPE)
            .approveLevel(DEFAULT_APPROVE_LEVEL);
        return pwbRoleModule;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbRoleModule createUpdatedEntity(EntityManager em) {
        PwbRoleModule pwbRoleModule = new PwbRoleModule()
            .roleType(UPDATED_ROLE_TYPE)
            .moduleType(UPDATED_MODULE_TYPE)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .businessType(UPDATED_BUSINESS_TYPE)
            .approveLevel(UPDATED_APPROVE_LEVEL);
        return pwbRoleModule;
    }

    @BeforeEach
    public void initTest() {
        pwbRoleModule = createEntity(em);
    }

    @Test
    @Transactional
    void createPwbRoleModule() throws Exception {
        int databaseSizeBeforeCreate = pwbRoleModuleRepository.findAll().size();
        // Create the PwbRoleModule
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);
        restPwbRoleModuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeCreate + 1);
        PwbRoleModule testPwbRoleModule = pwbRoleModuleList.get(pwbRoleModuleList.size() - 1);
        assertThat(testPwbRoleModule.getRoleType()).isEqualTo(DEFAULT_ROLE_TYPE);
        assertThat(testPwbRoleModule.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testPwbRoleModule.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testPwbRoleModule.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbRoleModule.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwbRoleModule.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testPwbRoleModule.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwbRoleModule.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testPwbRoleModule.getApproveLevel()).isEqualTo(DEFAULT_APPROVE_LEVEL);
    }

    @Test
    @Transactional
    void createPwbRoleModuleWithExistingId() throws Exception {
        // Create the PwbRoleModule with an existing ID
        pwbRoleModule.setId(1L);
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        int databaseSizeBeforeCreate = pwbRoleModuleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwbRoleModuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkModuleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleModuleRepository.findAll().size();
        // set the field null
        pwbRoleModule.setModuleType(null);

        // Create the PwbRoleModule, which fails.
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        restPwbRoleModuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRoleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleModuleRepository.findAll().size();
        // set the field null
        pwbRoleModule.setRoleId(null);

        // Create the PwbRoleModule, which fails.
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        restPwbRoleModuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleModuleRepository.findAll().size();
        // set the field null
        pwbRoleModule.setUpdateUser(null);

        // Create the PwbRoleModule, which fails.
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        restPwbRoleModuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleModuleRepository.findAll().size();
        // set the field null
        pwbRoleModule.setUpdateTime(null);

        // Create the PwbRoleModule, which fails.
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        restPwbRoleModuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleModuleRepository.findAll().size();
        // set the field null
        pwbRoleModule.setCreateUser(null);

        // Create the PwbRoleModule, which fails.
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        restPwbRoleModuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbRoleModuleRepository.findAll().size();
        // set the field null
        pwbRoleModule.setCreateTime(null);

        // Create the PwbRoleModule, which fails.
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        restPwbRoleModuleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPwbRoleModules() throws Exception {
        // Initialize the database
        pwbRoleModuleRepository.saveAndFlush(pwbRoleModule);

        // Get all the pwbRoleModuleList
        restPwbRoleModuleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pwbRoleModule.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleType").value(hasItem(DEFAULT_ROLE_TYPE)))
            .andExpect(jsonPath("$.[*].moduleType").value(hasItem(DEFAULT_MODULE_TYPE)))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].businessType").value(hasItem(DEFAULT_BUSINESS_TYPE)))
            .andExpect(jsonPath("$.[*].approveLevel").value(hasItem(DEFAULT_APPROVE_LEVEL)));
    }

    @Test
    @Transactional
    void getPwbRoleModule() throws Exception {
        // Initialize the database
        pwbRoleModuleRepository.saveAndFlush(pwbRoleModule);

        // Get the pwbRoleModule
        restPwbRoleModuleMockMvc
            .perform(get(ENTITY_API_URL_ID, pwbRoleModule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pwbRoleModule.getId().intValue()))
            .andExpect(jsonPath("$.roleType").value(DEFAULT_ROLE_TYPE))
            .andExpect(jsonPath("$.moduleType").value(DEFAULT_MODULE_TYPE))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE))
            .andExpect(jsonPath("$.approveLevel").value(DEFAULT_APPROVE_LEVEL));
    }

    @Test
    @Transactional
    void getNonExistingPwbRoleModule() throws Exception {
        // Get the pwbRoleModule
        restPwbRoleModuleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwbRoleModule() throws Exception {
        // Initialize the database
        pwbRoleModuleRepository.saveAndFlush(pwbRoleModule);

        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();

        // Update the pwbRoleModule
        PwbRoleModule updatedPwbRoleModule = pwbRoleModuleRepository.findById(pwbRoleModule.getId()).get();
        // Disconnect from session so that the updates on updatedPwbRoleModule are not directly saved in db
        em.detach(updatedPwbRoleModule);
        updatedPwbRoleModule
            .roleType(UPDATED_ROLE_TYPE)
            .moduleType(UPDATED_MODULE_TYPE)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .businessType(UPDATED_BUSINESS_TYPE)
            .approveLevel(UPDATED_APPROVE_LEVEL);
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(updatedPwbRoleModule);

        restPwbRoleModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbRoleModuleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleModule testPwbRoleModule = pwbRoleModuleList.get(pwbRoleModuleList.size() - 1);
        assertThat(testPwbRoleModule.getRoleType()).isEqualTo(UPDATED_ROLE_TYPE);
        assertThat(testPwbRoleModule.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbRoleModule.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testPwbRoleModule.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbRoleModule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbRoleModule.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleModule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwbRoleModule.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testPwbRoleModule.getApproveLevel()).isEqualTo(UPDATED_APPROVE_LEVEL);
    }

    @Test
    @Transactional
    void putNonExistingPwbRoleModule() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();
        pwbRoleModule.setId(count.incrementAndGet());

        // Create the PwbRoleModule
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbRoleModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbRoleModuleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwbRoleModule() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();
        pwbRoleModule.setId(count.incrementAndGet());

        // Create the PwbRoleModule
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwbRoleModule() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();
        pwbRoleModule.setId(count.incrementAndGet());

        // Create the PwbRoleModule
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleModuleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwbRoleModuleWithPatch() throws Exception {
        // Initialize the database
        pwbRoleModuleRepository.saveAndFlush(pwbRoleModule);

        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();

        // Update the pwbRoleModule using partial update
        PwbRoleModule partialUpdatedPwbRoleModule = new PwbRoleModule();
        partialUpdatedPwbRoleModule.setId(pwbRoleModule.getId());

        partialUpdatedPwbRoleModule
            .roleType(UPDATED_ROLE_TYPE)
            .roleId(UPDATED_ROLE_ID)
            .createUser(UPDATED_CREATE_USER)
            .approveLevel(UPDATED_APPROVE_LEVEL);

        restPwbRoleModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbRoleModule.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbRoleModule))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleModule testPwbRoleModule = pwbRoleModuleList.get(pwbRoleModuleList.size() - 1);
        assertThat(testPwbRoleModule.getRoleType()).isEqualTo(UPDATED_ROLE_TYPE);
        assertThat(testPwbRoleModule.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testPwbRoleModule.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testPwbRoleModule.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbRoleModule.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwbRoleModule.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleModule.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwbRoleModule.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testPwbRoleModule.getApproveLevel()).isEqualTo(UPDATED_APPROVE_LEVEL);
    }

    @Test
    @Transactional
    void fullUpdatePwbRoleModuleWithPatch() throws Exception {
        // Initialize the database
        pwbRoleModuleRepository.saveAndFlush(pwbRoleModule);

        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();

        // Update the pwbRoleModule using partial update
        PwbRoleModule partialUpdatedPwbRoleModule = new PwbRoleModule();
        partialUpdatedPwbRoleModule.setId(pwbRoleModule.getId());

        partialUpdatedPwbRoleModule
            .roleType(UPDATED_ROLE_TYPE)
            .moduleType(UPDATED_MODULE_TYPE)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .businessType(UPDATED_BUSINESS_TYPE)
            .approveLevel(UPDATED_APPROVE_LEVEL);

        restPwbRoleModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbRoleModule.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbRoleModule))
            )
            .andExpect(status().isOk());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
        PwbRoleModule testPwbRoleModule = pwbRoleModuleList.get(pwbRoleModuleList.size() - 1);
        assertThat(testPwbRoleModule.getRoleType()).isEqualTo(UPDATED_ROLE_TYPE);
        assertThat(testPwbRoleModule.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testPwbRoleModule.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testPwbRoleModule.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbRoleModule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbRoleModule.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbRoleModule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwbRoleModule.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testPwbRoleModule.getApproveLevel()).isEqualTo(UPDATED_APPROVE_LEVEL);
    }

    @Test
    @Transactional
    void patchNonExistingPwbRoleModule() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();
        pwbRoleModule.setId(count.incrementAndGet());

        // Create the PwbRoleModule
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbRoleModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwbRoleModuleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwbRoleModule() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();
        pwbRoleModule.setId(count.incrementAndGet());

        // Create the PwbRoleModule
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwbRoleModule() throws Exception {
        int databaseSizeBeforeUpdate = pwbRoleModuleRepository.findAll().size();
        pwbRoleModule.setId(count.incrementAndGet());

        // Create the PwbRoleModule
        PwbRoleModuleDTO pwbRoleModuleDTO = pwbRoleModuleMapper.toDto(pwbRoleModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbRoleModuleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbRoleModuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbRoleModule in the database
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwbRoleModule() throws Exception {
        // Initialize the database
        pwbRoleModuleRepository.saveAndFlush(pwbRoleModule);

        int databaseSizeBeforeDelete = pwbRoleModuleRepository.findAll().size();

        // Delete the pwbRoleModule
        restPwbRoleModuleMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwbRoleModule.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwbRoleModule> pwbRoleModuleList = pwbRoleModuleRepository.findAll();
        assertThat(pwbRoleModuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
