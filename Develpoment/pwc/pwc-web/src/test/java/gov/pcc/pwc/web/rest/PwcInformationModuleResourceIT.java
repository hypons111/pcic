package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwcInformationModule;
import gov.pcc.pwc.repository.PwcInformationModuleRepository;
import gov.pcc.pwc.service.dto.PwcInformationModuleDTO;
import gov.pcc.pwc.service.mapper.PwcInformationModuleMapper;
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
 * Integration tests for the {@link PwcInformationModuleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwcInformationModuleResourceIT {

    private static final Long DEFAULT_PRO_INFORMATION_ID = 1L;
    private static final Long UPDATED_PRO_INFORMATION_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/pwc-information-modules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proInformationModuleId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwcInformationModuleRepository pwcInformationModuleRepository;

    @Autowired
    private PwcInformationModuleMapper pwcInformationModuleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwcInformationModuleMockMvc;

    private PwcInformationModule pwcInformationModule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcInformationModule createEntity(EntityManager em) {
        PwcInformationModule pwcInformationModule = new PwcInformationModule()
            .proInformationId(DEFAULT_PRO_INFORMATION_ID)
            .module(DEFAULT_MODULE)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER);
        return pwcInformationModule;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcInformationModule createUpdatedEntity(EntityManager em) {
        PwcInformationModule pwcInformationModule = new PwcInformationModule()
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .module(UPDATED_MODULE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        return pwcInformationModule;
    }

    @BeforeEach
    public void initTest() {
        pwcInformationModule = createEntity(em);
    }

    @Test
    @Transactional
    void createPwcInformationModule() throws Exception {
        int databaseSizeBeforeCreate = pwcInformationModuleRepository.findAll().size();
        // Create the PwcInformationModule
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(pwcInformationModule);
        restPwcInformationModuleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeCreate + 1);
        PwcInformationModule testPwcInformationModule = pwcInformationModuleList.get(pwcInformationModuleList.size() - 1);
        assertThat(testPwcInformationModule.getProInformationId()).isEqualTo(DEFAULT_PRO_INFORMATION_ID);
        assertThat(testPwcInformationModule.getModule()).isEqualTo(DEFAULT_MODULE);
        assertThat(testPwcInformationModule.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcInformationModule.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcInformationModule.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcInformationModule.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void createPwcInformationModuleWithExistingId() throws Exception {
        // Create the PwcInformationModule with an existing ID
        pwcInformationModule.setProInformationModuleId(1L);
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(pwcInformationModule);

        int databaseSizeBeforeCreate = pwcInformationModuleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwcInformationModuleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPwcInformationModules() throws Exception {
        // Initialize the database
        pwcInformationModuleRepository.saveAndFlush(pwcInformationModule);

        // Get all the pwcInformationModuleList
        restPwcInformationModuleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proInformationModuleId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proInformationModuleId").value(hasItem(pwcInformationModule.getProInformationModuleId().intValue())))
            .andExpect(jsonPath("$.[*].proInformationId").value(hasItem(DEFAULT_PRO_INFORMATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].module").value(hasItem(DEFAULT_MODULE)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)));
    }

    @Test
    @Transactional
    void getPwcInformationModule() throws Exception {
        // Initialize the database
        pwcInformationModuleRepository.saveAndFlush(pwcInformationModule);

        // Get the pwcInformationModule
        restPwcInformationModuleMockMvc
            .perform(get(ENTITY_API_URL_ID, pwcInformationModule.getProInformationModuleId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proInformationModuleId").value(pwcInformationModule.getProInformationModuleId().intValue()))
            .andExpect(jsonPath("$.proInformationId").value(DEFAULT_PRO_INFORMATION_ID.intValue()))
            .andExpect(jsonPath("$.module").value(DEFAULT_MODULE))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingPwcInformationModule() throws Exception {
        // Get the pwcInformationModule
        restPwcInformationModuleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwcInformationModule() throws Exception {
        // Initialize the database
        pwcInformationModuleRepository.saveAndFlush(pwcInformationModule);

        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();

        // Update the pwcInformationModule
        PwcInformationModule updatedPwcInformationModule = pwcInformationModuleRepository
            .findById(pwcInformationModule.getProInformationModuleId())
            .get();
        // Disconnect from session so that the updates on updatedPwcInformationModule are not directly saved in db
        em.detach(updatedPwcInformationModule);
        updatedPwcInformationModule
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .module(UPDATED_MODULE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(updatedPwcInformationModule);

        restPwcInformationModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcInformationModuleDTO.getProInformationModuleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationModule testPwcInformationModule = pwcInformationModuleList.get(pwcInformationModuleList.size() - 1);
        assertThat(testPwcInformationModule.getProInformationId()).isEqualTo(UPDATED_PRO_INFORMATION_ID);
        assertThat(testPwcInformationModule.getModule()).isEqualTo(UPDATED_MODULE);
        assertThat(testPwcInformationModule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformationModule.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformationModule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformationModule.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingPwcInformationModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();
        pwcInformationModule.setProInformationModuleId(count.incrementAndGet());

        // Create the PwcInformationModule
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(pwcInformationModule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcInformationModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcInformationModuleDTO.getProInformationModuleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwcInformationModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();
        pwcInformationModule.setProInformationModuleId(count.incrementAndGet());

        // Create the PwcInformationModule
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(pwcInformationModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationModuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwcInformationModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();
        pwcInformationModule.setProInformationModuleId(count.incrementAndGet());

        // Create the PwcInformationModule
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(pwcInformationModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationModuleMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwcInformationModuleWithPatch() throws Exception {
        // Initialize the database
        pwcInformationModuleRepository.saveAndFlush(pwcInformationModule);

        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();

        // Update the pwcInformationModule using partial update
        PwcInformationModule partialUpdatedPwcInformationModule = new PwcInformationModule();
        partialUpdatedPwcInformationModule.setProInformationModuleId(pwcInformationModule.getProInformationModuleId());

        partialUpdatedPwcInformationModule.updateUser(UPDATED_UPDATE_USER).createTime(UPDATED_CREATE_TIME);

        restPwcInformationModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcInformationModule.getProInformationModuleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcInformationModule))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationModule testPwcInformationModule = pwcInformationModuleList.get(pwcInformationModuleList.size() - 1);
        assertThat(testPwcInformationModule.getProInformationId()).isEqualTo(DEFAULT_PRO_INFORMATION_ID);
        assertThat(testPwcInformationModule.getModule()).isEqualTo(DEFAULT_MODULE);
        assertThat(testPwcInformationModule.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcInformationModule.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformationModule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformationModule.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void fullUpdatePwcInformationModuleWithPatch() throws Exception {
        // Initialize the database
        pwcInformationModuleRepository.saveAndFlush(pwcInformationModule);

        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();

        // Update the pwcInformationModule using partial update
        PwcInformationModule partialUpdatedPwcInformationModule = new PwcInformationModule();
        partialUpdatedPwcInformationModule.setProInformationModuleId(pwcInformationModule.getProInformationModuleId());

        partialUpdatedPwcInformationModule
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .module(UPDATED_MODULE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwcInformationModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcInformationModule.getProInformationModuleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcInformationModule))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationModule testPwcInformationModule = pwcInformationModuleList.get(pwcInformationModuleList.size() - 1);
        assertThat(testPwcInformationModule.getProInformationId()).isEqualTo(UPDATED_PRO_INFORMATION_ID);
        assertThat(testPwcInformationModule.getModule()).isEqualTo(UPDATED_MODULE);
        assertThat(testPwcInformationModule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformationModule.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformationModule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformationModule.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingPwcInformationModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();
        pwcInformationModule.setProInformationModuleId(count.incrementAndGet());

        // Create the PwcInformationModule
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(pwcInformationModule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcInformationModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwcInformationModuleDTO.getProInformationModuleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwcInformationModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();
        pwcInformationModule.setProInformationModuleId(count.incrementAndGet());

        // Create the PwcInformationModule
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(pwcInformationModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationModuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwcInformationModule() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationModuleRepository.findAll().size();
        pwcInformationModule.setProInformationModuleId(count.incrementAndGet());

        // Create the PwcInformationModule
        PwcInformationModuleDTO pwcInformationModuleDTO = pwcInformationModuleMapper.toDto(pwcInformationModule);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationModuleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationModuleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcInformationModule in the database
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwcInformationModule() throws Exception {
        // Initialize the database
        pwcInformationModuleRepository.saveAndFlush(pwcInformationModule);

        int databaseSizeBeforeDelete = pwcInformationModuleRepository.findAll().size();

        // Delete the pwcInformationModule
        restPwcInformationModuleMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwcInformationModule.getProInformationModuleId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwcInformationModule> pwcInformationModuleList = pwcInformationModuleRepository.findAll();
        assertThat(pwcInformationModuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
