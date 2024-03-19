package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmBatchService;
import gov.pcc.pwc.pwb.rest.AdmBatchServiceResource;
import gov.pcc.pwc.repository.AdmBatchServiceRepository;
import java.time.Instant;
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
 * Integration tests for the {@link AdmBatchServiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmBatchServiceResourceIT {

    private static final String DEFAULT_BATCH_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_SERVICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_SERVICE_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_SERVICE_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_IS_REGULAR = "A";
    private static final String UPDATED_IS_REGULAR = "B";

    private static final String DEFAULT_BATCH_SERVICE_PROG = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_SERVICE_PROG = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_SERVICE_PROG_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_SERVICE_PROG_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_SERVICE_STATUS = "A";
    private static final String UPDATED_BATCH_SERVICE_STATUS = "B";

    private static final String DEFAULT_BATCH_EXE_STATUS = "A";
    private static final String UPDATED_BATCH_EXE_STATUS = "B";

    private static final String DEFAULT_BATCH_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_FREQUENCY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-batch-services";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{batchServiceNo}";

    @Autowired
    private AdmBatchServiceRepository admBatchServiceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmBatchServiceMockMvc;

    private AdmBatchService admBatchService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmBatchService createEntity(EntityManager em) {
        AdmBatchService admBatchService = new AdmBatchService()
            .batchServiceName(DEFAULT_BATCH_SERVICE_NAME)
            .batchServiceCategory(DEFAULT_BATCH_SERVICE_CATEGORY)
            .isRegular(DEFAULT_IS_REGULAR)
            .batchServiceProg(DEFAULT_BATCH_SERVICE_PROG)
            .batchServiceProgMethod(DEFAULT_BATCH_SERVICE_PROG_METHOD)
            .batchServiceStatus(DEFAULT_BATCH_SERVICE_STATUS)
            .batchExeStatus(DEFAULT_BATCH_EXE_STATUS)
            .batchFrequency(DEFAULT_BATCH_FREQUENCY)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admBatchService;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmBatchService createUpdatedEntity(EntityManager em) {
        AdmBatchService admBatchService = new AdmBatchService()
            .batchServiceName(UPDATED_BATCH_SERVICE_NAME)
            .batchServiceCategory(UPDATED_BATCH_SERVICE_CATEGORY)
            .isRegular(UPDATED_IS_REGULAR)
            .batchServiceProg(UPDATED_BATCH_SERVICE_PROG)
            .batchServiceProgMethod(UPDATED_BATCH_SERVICE_PROG_METHOD)
            .batchServiceStatus(UPDATED_BATCH_SERVICE_STATUS)
            .batchExeStatus(UPDATED_BATCH_EXE_STATUS)
            .batchFrequency(UPDATED_BATCH_FREQUENCY)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admBatchService;
    }

    @BeforeEach
    public void initTest() {
        admBatchService = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmBatchService() throws Exception {
        int databaseSizeBeforeCreate = admBatchServiceRepository.findAll().size();
        // Create the AdmBatchService
        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isCreated());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeCreate + 1);
        AdmBatchService testAdmBatchService = admBatchServiceList.get(admBatchServiceList.size() - 1);
        assertThat(testAdmBatchService.getBatchServiceName()).isEqualTo(DEFAULT_BATCH_SERVICE_NAME);
        assertThat(testAdmBatchService.getBatchServiceCategory()).isEqualTo(DEFAULT_BATCH_SERVICE_CATEGORY);
        assertThat(testAdmBatchService.getIsRegular()).isEqualTo(DEFAULT_IS_REGULAR);
        assertThat(testAdmBatchService.getBatchServiceProg()).isEqualTo(DEFAULT_BATCH_SERVICE_PROG);
        assertThat(testAdmBatchService.getBatchServiceProgMethod()).isEqualTo(DEFAULT_BATCH_SERVICE_PROG_METHOD);
        assertThat(testAdmBatchService.getBatchServiceStatus()).isEqualTo(DEFAULT_BATCH_SERVICE_STATUS);
        assertThat(testAdmBatchService.getBatchExeStatus()).isEqualTo(DEFAULT_BATCH_EXE_STATUS);
        assertThat(testAdmBatchService.getBatchFrequency()).isEqualTo(DEFAULT_BATCH_FREQUENCY);
        assertThat(testAdmBatchService.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmBatchService.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmBatchServiceWithExistingId() throws Exception {
        // Create the AdmBatchService with an existing ID
        admBatchService.setBatchServiceNo("existing_id");

        int databaseSizeBeforeCreate = admBatchServiceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBatchServiceNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setBatchServiceName(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBatchServiceCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setBatchServiceCategory(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsRegularIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setIsRegular(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBatchServiceProgIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setBatchServiceProg(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBatchServiceStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setBatchServiceStatus(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBatchExeStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setBatchExeStatus(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBatchFrequencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setBatchFrequency(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setUpdateUser(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchServiceRepository.findAll().size();
        // set the field null
        admBatchService.setUpdateTime(null);

        // Create the AdmBatchService, which fails.

        restAdmBatchServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmBatchServices() throws Exception {
        // Initialize the database
        admBatchServiceRepository.saveAndFlush(admBatchService);

        // Get all the admBatchServiceList
        restAdmBatchServiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=batchServiceNo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].batchServiceNo").value(hasItem(admBatchService.getBatchServiceNo())))
            .andExpect(jsonPath("$.[*].batchServiceName").value(hasItem(DEFAULT_BATCH_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].batchServiceCategory").value(hasItem(DEFAULT_BATCH_SERVICE_CATEGORY)))
            .andExpect(jsonPath("$.[*].isRegular").value(hasItem(DEFAULT_IS_REGULAR)))
            .andExpect(jsonPath("$.[*].batchServiceProg").value(hasItem(DEFAULT_BATCH_SERVICE_PROG)))
            .andExpect(jsonPath("$.[*].batchServiceProgMethod").value(hasItem(DEFAULT_BATCH_SERVICE_PROG_METHOD)))
            .andExpect(jsonPath("$.[*].batchServiceStatus").value(hasItem(DEFAULT_BATCH_SERVICE_STATUS)))
            .andExpect(jsonPath("$.[*].batchExeStatus").value(hasItem(DEFAULT_BATCH_EXE_STATUS)))
            .andExpect(jsonPath("$.[*].batchFrequency").value(hasItem(DEFAULT_BATCH_FREQUENCY)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmBatchService() throws Exception {
        // Initialize the database
        admBatchServiceRepository.saveAndFlush(admBatchService);

        // Get the admBatchService
        restAdmBatchServiceMockMvc
            .perform(get(ENTITY_API_URL_ID, admBatchService.getBatchServiceNo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.batchServiceNo").value(admBatchService.getBatchServiceNo()))
            .andExpect(jsonPath("$.batchServiceName").value(DEFAULT_BATCH_SERVICE_NAME))
            .andExpect(jsonPath("$.batchServiceCategory").value(DEFAULT_BATCH_SERVICE_CATEGORY))
            .andExpect(jsonPath("$.isRegular").value(DEFAULT_IS_REGULAR))
            .andExpect(jsonPath("$.batchServiceProg").value(DEFAULT_BATCH_SERVICE_PROG))
            .andExpect(jsonPath("$.batchServiceProgMethod").value(DEFAULT_BATCH_SERVICE_PROG_METHOD))
            .andExpect(jsonPath("$.batchServiceStatus").value(DEFAULT_BATCH_SERVICE_STATUS))
            .andExpect(jsonPath("$.batchExeStatus").value(DEFAULT_BATCH_EXE_STATUS))
            .andExpect(jsonPath("$.batchFrequency").value(DEFAULT_BATCH_FREQUENCY))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmBatchService() throws Exception {
        // Get the admBatchService
        restAdmBatchServiceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmBatchService() throws Exception {
        // Initialize the database
        admBatchServiceRepository.saveAndFlush(admBatchService);

        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();

        // Update the admBatchService
        AdmBatchService updatedAdmBatchService = admBatchServiceRepository.findById(admBatchService.getBatchServiceNo()).get();
        // Disconnect from session so that the updates on updatedAdmBatchService are not directly saved in db
        em.detach(updatedAdmBatchService);
        updatedAdmBatchService
            .batchServiceName(UPDATED_BATCH_SERVICE_NAME)
            .batchServiceCategory(UPDATED_BATCH_SERVICE_CATEGORY)
            .isRegular(UPDATED_IS_REGULAR)
            .batchServiceProg(UPDATED_BATCH_SERVICE_PROG)
            .batchServiceProgMethod(UPDATED_BATCH_SERVICE_PROG_METHOD)
            .batchServiceStatus(UPDATED_BATCH_SERVICE_STATUS)
            .batchExeStatus(UPDATED_BATCH_EXE_STATUS)
            .batchFrequency(UPDATED_BATCH_FREQUENCY)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmBatchServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmBatchService.getBatchServiceNo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmBatchService))
            )
            .andExpect(status().isOk());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
        AdmBatchService testAdmBatchService = admBatchServiceList.get(admBatchServiceList.size() - 1);
        assertThat(testAdmBatchService.getBatchServiceName()).isEqualTo(UPDATED_BATCH_SERVICE_NAME);
        assertThat(testAdmBatchService.getBatchServiceCategory()).isEqualTo(UPDATED_BATCH_SERVICE_CATEGORY);
        assertThat(testAdmBatchService.getIsRegular()).isEqualTo(UPDATED_IS_REGULAR);
        assertThat(testAdmBatchService.getBatchServiceProg()).isEqualTo(UPDATED_BATCH_SERVICE_PROG);
        assertThat(testAdmBatchService.getBatchServiceProgMethod()).isEqualTo(UPDATED_BATCH_SERVICE_PROG_METHOD);
        assertThat(testAdmBatchService.getBatchServiceStatus()).isEqualTo(UPDATED_BATCH_SERVICE_STATUS);
        assertThat(testAdmBatchService.getBatchExeStatus()).isEqualTo(UPDATED_BATCH_EXE_STATUS);
        assertThat(testAdmBatchService.getBatchFrequency()).isEqualTo(UPDATED_BATCH_FREQUENCY);
        assertThat(testAdmBatchService.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmBatchService.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmBatchService() throws Exception {
        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();
        admBatchService.setBatchServiceNo(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmBatchServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admBatchService.getBatchServiceNo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmBatchService() throws Exception {
        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();
        admBatchService.setBatchServiceNo(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmBatchServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmBatchService() throws Exception {
        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();
        admBatchService.setBatchServiceNo(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmBatchServiceMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmBatchServiceWithPatch() throws Exception {
        // Initialize the database
        admBatchServiceRepository.saveAndFlush(admBatchService);

        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();

        // Update the admBatchService using partial update
        AdmBatchService partialUpdatedAdmBatchService = new AdmBatchService();
        partialUpdatedAdmBatchService.setBatchServiceNo(admBatchService.getBatchServiceNo());

        partialUpdatedAdmBatchService
            .batchServiceName(UPDATED_BATCH_SERVICE_NAME)
            .isRegular(UPDATED_IS_REGULAR)
            .batchServiceProg(UPDATED_BATCH_SERVICE_PROG)
            .batchServiceProgMethod(UPDATED_BATCH_SERVICE_PROG_METHOD)
            .batchFrequency(UPDATED_BATCH_FREQUENCY);

        restAdmBatchServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmBatchService.getBatchServiceNo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmBatchService))
            )
            .andExpect(status().isOk());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
        AdmBatchService testAdmBatchService = admBatchServiceList.get(admBatchServiceList.size() - 1);
        assertThat(testAdmBatchService.getBatchServiceName()).isEqualTo(UPDATED_BATCH_SERVICE_NAME);
        assertThat(testAdmBatchService.getBatchServiceCategory()).isEqualTo(DEFAULT_BATCH_SERVICE_CATEGORY);
        assertThat(testAdmBatchService.getIsRegular()).isEqualTo(UPDATED_IS_REGULAR);
        assertThat(testAdmBatchService.getBatchServiceProg()).isEqualTo(UPDATED_BATCH_SERVICE_PROG);
        assertThat(testAdmBatchService.getBatchServiceProgMethod()).isEqualTo(UPDATED_BATCH_SERVICE_PROG_METHOD);
        assertThat(testAdmBatchService.getBatchServiceStatus()).isEqualTo(DEFAULT_BATCH_SERVICE_STATUS);
        assertThat(testAdmBatchService.getBatchExeStatus()).isEqualTo(DEFAULT_BATCH_EXE_STATUS);
        assertThat(testAdmBatchService.getBatchFrequency()).isEqualTo(UPDATED_BATCH_FREQUENCY);
        assertThat(testAdmBatchService.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmBatchService.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmBatchServiceWithPatch() throws Exception {
        // Initialize the database
        admBatchServiceRepository.saveAndFlush(admBatchService);

        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();

        // Update the admBatchService using partial update
        AdmBatchService partialUpdatedAdmBatchService = new AdmBatchService();
        partialUpdatedAdmBatchService.setBatchServiceNo(admBatchService.getBatchServiceNo());

        partialUpdatedAdmBatchService
            .batchServiceName(UPDATED_BATCH_SERVICE_NAME)
            .batchServiceCategory(UPDATED_BATCH_SERVICE_CATEGORY)
            .isRegular(UPDATED_IS_REGULAR)
            .batchServiceProg(UPDATED_BATCH_SERVICE_PROG)
            .batchServiceProgMethod(UPDATED_BATCH_SERVICE_PROG_METHOD)
            .batchServiceStatus(UPDATED_BATCH_SERVICE_STATUS)
            .batchExeStatus(UPDATED_BATCH_EXE_STATUS)
            .batchFrequency(UPDATED_BATCH_FREQUENCY)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmBatchServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmBatchService.getBatchServiceNo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmBatchService))
            )
            .andExpect(status().isOk());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
        AdmBatchService testAdmBatchService = admBatchServiceList.get(admBatchServiceList.size() - 1);
        assertThat(testAdmBatchService.getBatchServiceName()).isEqualTo(UPDATED_BATCH_SERVICE_NAME);
        assertThat(testAdmBatchService.getBatchServiceCategory()).isEqualTo(UPDATED_BATCH_SERVICE_CATEGORY);
        assertThat(testAdmBatchService.getIsRegular()).isEqualTo(UPDATED_IS_REGULAR);
        assertThat(testAdmBatchService.getBatchServiceProg()).isEqualTo(UPDATED_BATCH_SERVICE_PROG);
        assertThat(testAdmBatchService.getBatchServiceProgMethod()).isEqualTo(UPDATED_BATCH_SERVICE_PROG_METHOD);
        assertThat(testAdmBatchService.getBatchServiceStatus()).isEqualTo(UPDATED_BATCH_SERVICE_STATUS);
        assertThat(testAdmBatchService.getBatchExeStatus()).isEqualTo(UPDATED_BATCH_EXE_STATUS);
        assertThat(testAdmBatchService.getBatchFrequency()).isEqualTo(UPDATED_BATCH_FREQUENCY);
        assertThat(testAdmBatchService.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmBatchService.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmBatchService() throws Exception {
        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();
        admBatchService.setBatchServiceNo(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmBatchServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admBatchService.getBatchServiceNo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmBatchService() throws Exception {
        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();
        admBatchService.setBatchServiceNo(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmBatchServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmBatchService() throws Exception {
        int databaseSizeBeforeUpdate = admBatchServiceRepository.findAll().size();
        admBatchService.setBatchServiceNo(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmBatchServiceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admBatchService))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmBatchService in the database
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmBatchService() throws Exception {
        // Initialize the database
        admBatchServiceRepository.saveAndFlush(admBatchService);

        int databaseSizeBeforeDelete = admBatchServiceRepository.findAll().size();

        // Delete the admBatchService
        restAdmBatchServiceMockMvc
            .perform(delete(ENTITY_API_URL_ID, admBatchService.getBatchServiceNo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmBatchService> admBatchServiceList = admBatchServiceRepository.findAll();
        assertThat(admBatchServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
