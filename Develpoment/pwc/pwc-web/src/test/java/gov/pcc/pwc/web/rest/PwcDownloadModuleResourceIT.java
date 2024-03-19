package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwcDownloadModule;
import gov.pcc.pwc.repository.PwcDownloadModuleRepository;
import gov.pcc.pwc.service.dto.PwcDownloadModuleDTO;
import gov.pcc.pwc.service.mapper.PwcDownloadModuleMapper;
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
 * Integration tests for the {@link PwcDownloadModuleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwcDownloadModuleResourceIT {

    private static final Long DEFAULT_PRO_Download_ID = 1L;
    private static final Long UPDATED_PRO_Download_ID = 2L;

    private static final String DEFAULT_MODULE = "AAAAAAAAAA";
    private static final String UPDATED_MODULE = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pwc-download-modules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proDownloadModuleId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwcDownloadModuleRepository pwcDownloadModuleRepository;

    @Autowired
    private PwcDownloadModuleMapper pwcDownloadModuleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwcDownloadModuleMockMvc;

    private PwcDownloadModule pwcDownloadModule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcDownloadModule createEntity(EntityManager em) {
        PwcDownloadModule pwcDownloadModule = new PwcDownloadModule()
            .proDownloadId(DEFAULT_PRO_Download_ID)
            .module(DEFAULT_MODULE)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER);
        return pwcDownloadModule;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcDownloadModule createUpdatedEntity(EntityManager em) {
        PwcDownloadModule pwcDownloadModule = new PwcDownloadModule()
            .proDownloadId(UPDATED_PRO_Download_ID)
            .module(UPDATED_MODULE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        return pwcDownloadModule;
    }

    @BeforeEach
    public void initTest() {
        pwcDownloadModule = createEntity(em);
    }

    @Test
    @Transactional
    void createPwcDownloadModule() throws Exception {
        int databaseSizeBeforeCreate = pwcDownloadModuleRepository.findAll().size();
        // Create the PwcDownloadModule
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(pwcDownloadModule);
        restPwcDownloadModuleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeCreate + 1);
        PwcDownloadModule testPwcDownloadModule = pwcDownloadModuleList.get(pwcDownloadModuleList.size() - 1);
        assertThat(testPwcDownloadModule.getProDownloadId()).isEqualTo(DEFAULT_PRO_Download_ID);
        assertThat(testPwcDownloadModule.getModule()).isEqualTo(DEFAULT_MODULE);
        assertThat(testPwcDownloadModule.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcDownloadModule.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcDownloadModule.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcDownloadModule.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void createPwcDownloadModuleWithExistingId() throws Exception {
        // Create the PwcDownloadModule with an existing ID
        pwcDownloadModule.setProDownloadModuleId(1L);
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(pwcDownloadModule);

        int databaseSizeBeforeCreate = pwcDownloadModuleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwcDownloadModuleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPwcDownloadModules() throws Exception {
        // Initialize the database
        pwcDownloadModuleRepository.saveAndFlush(pwcDownloadModule);

        // Get all the pwcDownloadModuleList
        restPwcDownloadModuleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proDownloadModuleId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proDownloadModuleId").value(hasItem(pwcDownloadModule.getProDownloadModuleId().intValue())))
            .andExpect(jsonPath("$.[*].proDownloadId").value(hasItem(DEFAULT_PRO_Download_ID.intValue())))
            .andExpect(jsonPath("$.[*].module").value(hasItem(DEFAULT_MODULE)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)));
    }

    @Test
    @Transactional
    void getPwcDownloadModule() throws Exception {
        // Initialize the database
        pwcDownloadModuleRepository.saveAndFlush(pwcDownloadModule);

        // Get the pwcDownloadModule
        restPwcDownloadModuleMockMvc
            .perform(get(ENTITY_API_URL_ID, pwcDownloadModule.getProDownloadModuleId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proDownloadModuleId").value(pwcDownloadModule.getProDownloadModuleId().intValue()))
            .andExpect(jsonPath("$.proDownloadId").value(DEFAULT_PRO_Download_ID.intValue()))
            .andExpect(jsonPath("$.module").value(DEFAULT_MODULE))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingPwcDownloadModule() throws Exception {
        // Get the pwcDownloadModule
        restPwcDownloadModuleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwcDownloadModule() throws Exception {
        // Initialize the database
        pwcDownloadModuleRepository.saveAndFlush(pwcDownloadModule);

        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();

        // Update the pwcDownloadModule
        PwcDownloadModule updatedPwcDownloadModule = pwcDownloadModuleRepository.findById(pwcDownloadModule.getProDownloadModuleId()).get();
        // Disconnect from session so that the updates on updatedPwcDownloadModule are not directly saved in db
        em.detach(updatedPwcDownloadModule);
        updatedPwcDownloadModule
            .proDownloadId(UPDATED_PRO_Download_ID)
            .module(UPDATED_MODULE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(updatedPwcDownloadModule);

        restPwcDownloadModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcDownloadModuleDTO.getProDownloadModuleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadModule testPwcDownloadModule = pwcDownloadModuleList.get(pwcDownloadModuleList.size() - 1);
        assertThat(testPwcDownloadModule.getProDownloadId()).isEqualTo(UPDATED_PRO_Download_ID);
        assertThat(testPwcDownloadModule.getModule()).isEqualTo(UPDATED_MODULE);
        assertThat(testPwcDownloadModule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownloadModule.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownloadModule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownloadModule.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingPwcDownloadModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();
        pwcDownloadModule.setProDownloadModuleId(count.incrementAndGet());

        // Create the PwcDownloadModule
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(pwcDownloadModule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcDownloadModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcDownloadModuleDTO.getProDownloadModuleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwcDownloadModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();
        pwcDownloadModule.setProDownloadModuleId(count.incrementAndGet());

        // Create the PwcDownloadModule
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(pwcDownloadModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwcDownloadModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();
        pwcDownloadModule.setProDownloadModuleId(count.incrementAndGet());

        // Create the PwcDownloadModule
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(pwcDownloadModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadModuleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwcDownloadModuleWithPatch() throws Exception {
        // Initialize the database
        pwcDownloadModuleRepository.saveAndFlush(pwcDownloadModule);

        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();

        // Update the pwcDownloadModule using partial update
        PwcDownloadModule partialUpdatedPwcDownloadModule = new PwcDownloadModule();
        partialUpdatedPwcDownloadModule.setProDownloadModuleId(pwcDownloadModule.getProDownloadModuleId());

        partialUpdatedPwcDownloadModule.module(UPDATED_MODULE);

        restPwcDownloadModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcDownloadModule.getProDownloadModuleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcDownloadModule))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadModule testPwcDownloadModule = pwcDownloadModuleList.get(pwcDownloadModuleList.size() - 1);
        assertThat(testPwcDownloadModule.getProDownloadId()).isEqualTo(DEFAULT_PRO_Download_ID);
        assertThat(testPwcDownloadModule.getModule()).isEqualTo(UPDATED_MODULE);
        assertThat(testPwcDownloadModule.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcDownloadModule.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcDownloadModule.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcDownloadModule.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void fullUpdatePwcDownloadModuleWithPatch() throws Exception {
        // Initialize the database
        pwcDownloadModuleRepository.saveAndFlush(pwcDownloadModule);

        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();

        // Update the pwcDownloadModule using partial update
        PwcDownloadModule partialUpdatedPwcDownloadModule = new PwcDownloadModule();
        partialUpdatedPwcDownloadModule.setProDownloadModuleId(pwcDownloadModule.getProDownloadModuleId());

        partialUpdatedPwcDownloadModule
            .proDownloadId(UPDATED_PRO_Download_ID)
            .module(UPDATED_MODULE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwcDownloadModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcDownloadModule.getProDownloadModuleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcDownloadModule))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadModule testPwcDownloadModule = pwcDownloadModuleList.get(pwcDownloadModuleList.size() - 1);
        assertThat(testPwcDownloadModule.getProDownloadId()).isEqualTo(UPDATED_PRO_Download_ID);
        assertThat(testPwcDownloadModule.getModule()).isEqualTo(UPDATED_MODULE);
        assertThat(testPwcDownloadModule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownloadModule.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownloadModule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownloadModule.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingPwcDownloadModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();
        pwcDownloadModule.setProDownloadModuleId(count.incrementAndGet());

        // Create the PwcDownloadModule
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(pwcDownloadModule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcDownloadModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwcDownloadModuleDTO.getProDownloadModuleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwcDownloadModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();
        pwcDownloadModule.setProDownloadModuleId(count.incrementAndGet());

        // Create the PwcDownloadModule
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(pwcDownloadModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwcDownloadModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadModuleRepository.findAll().size();
        pwcDownloadModule.setProDownloadModuleId(count.incrementAndGet());

        // Create the PwcDownloadModule
        PwcDownloadModuleDTO pwcDownloadModuleDTO = pwcDownloadModuleMapper.toDto(pwcDownloadModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadModuleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadModuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcDownloadModule in the database
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwcDownloadModule() throws Exception {
        // Initialize the database
        pwcDownloadModuleRepository.saveAndFlush(pwcDownloadModule);

        int databaseSizeBeforeDelete = pwcDownloadModuleRepository.findAll().size();

        // Delete the pwcDownloadModule
        restPwcDownloadModuleMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwcDownloadModule.getProDownloadModuleId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwcDownloadModule> pwcDownloadModuleList = pwcDownloadModuleRepository.findAll();
        assertThat(pwcDownloadModuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
