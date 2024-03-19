package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmFunction;
import gov.pcc.pwc.repository.AdmFunctionRepository;
import gov.pcc.pwc.pwb.rest.AdmFunctionResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static gov.pcc.pwc.pwb.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdmFunctionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmFunctionResourceIT {

    private static final String DEFAULT_FUNCTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_DESCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_DESCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_CATEGORY = "A";
    private static final String UPDATED_FUNCTION_CATEGORY = "B";

    private static final String DEFAULT_SYS_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SYS_CATEGORY = "BBBBBBBBBB";

    private static final int DEFAULT_SORT_NO = 0;
    private static final int UPDATED_SORT_NO = 1;

    private static final String DEFAULT_MASTER_FUNCTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_MASTER_FUNCTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-functions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmFunctionRepository admFunctionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmFunctionMockMvc;

    private AdmFunction admFunction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFunction createEntity(EntityManager em) {
        AdmFunction admFunction = new AdmFunction();
        admFunction.setFunctionId(DEFAULT_FUNCTION_ID);
        admFunction.setFunctionName(DEFAULT_FUNCTION_NAME);
        admFunction.setFunctionDescript(DEFAULT_FUNCTION_DESCRIPT);
        admFunction.setFunctionPath(DEFAULT_FUNCTION_PATH);
        admFunction.setFunctionCategory(DEFAULT_FUNCTION_CATEGORY);
        admFunction.setSysCategory(DEFAULT_SYS_CATEGORY);
        admFunction.setSortNo(DEFAULT_SORT_NO);
        admFunction.setMasterFunctionId(DEFAULT_MASTER_FUNCTION_ID);
        admFunction.setStatus(DEFAULT_STATUS);
        admFunction.setUpdateUser(DEFAULT_UPDATE_USER);
        admFunction.setUpdateTime(DEFAULT_UPDATE_TIME);
        return admFunction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFunction createUpdatedEntity(EntityManager em) {
        AdmFunction admFunction = new AdmFunction();
        admFunction.setFunctionId(UPDATED_FUNCTION_ID);
        admFunction.setFunctionName(UPDATED_FUNCTION_NAME);
        admFunction.setFunctionDescript(UPDATED_FUNCTION_DESCRIPT);
        admFunction.setFunctionPath(UPDATED_FUNCTION_PATH);
        admFunction.setFunctionCategory(UPDATED_FUNCTION_CATEGORY);
        admFunction.setSysCategory(UPDATED_SYS_CATEGORY);
        admFunction.setSortNo(UPDATED_SORT_NO);
        admFunction.setMasterFunctionId(UPDATED_MASTER_FUNCTION_ID);
        admFunction.setStatus(UPDATED_STATUS);
        admFunction.setUpdateUser(UPDATED_UPDATE_USER);
        admFunction.setUpdateTime(UPDATED_UPDATE_TIME);
        return admFunction;
    }

    @BeforeEach
    public void initTest() {
        admFunction = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmFunction() throws Exception {
        int databaseSizeBeforeCreate = admFunctionRepository.findAll().size();
        // Create the AdmFunction
        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isCreated());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeCreate + 1);
        AdmFunction testAdmFunction = admFunctionList.get(admFunctionList.size() - 1);
        assertThat(testAdmFunction.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testAdmFunction.getFunctionName()).isEqualTo(DEFAULT_FUNCTION_NAME);
        assertThat(testAdmFunction.getFunctionDescript()).isEqualTo(DEFAULT_FUNCTION_DESCRIPT);
        assertThat(testAdmFunction.getFunctionPath()).isEqualTo(DEFAULT_FUNCTION_PATH);
        assertThat(testAdmFunction.getFunctionCategory()).isEqualTo(DEFAULT_FUNCTION_CATEGORY);
        assertThat(testAdmFunction.getSysCategory()).isEqualTo(DEFAULT_SYS_CATEGORY);
        assertThat(testAdmFunction.getSortNo()).isEqualByComparingTo(DEFAULT_SORT_NO);
        assertThat(testAdmFunction.getMasterFunctionId()).isEqualTo(DEFAULT_MASTER_FUNCTION_ID);
        assertThat(testAdmFunction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmFunction.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmFunction.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmFunctionWithExistingId() throws Exception {
        // Create the AdmFunction with an existing ID
        admFunction.setId(1L);

        int databaseSizeBeforeCreate = admFunctionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFunctionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setFunctionId(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setFunctionName(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionDescriptIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setFunctionDescript(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionPathIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setFunctionPath(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setFunctionCategory(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSysCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setSysCategory(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }


    @Test
    @Transactional
    void checkMasterFunctionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setMasterFunctionId(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setStatus(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setUpdateUser(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionRepository.findAll().size();
        // set the field null
        admFunction.setUpdateTime(null);

        // Create the AdmFunction, which fails.

        restAdmFunctionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isBadRequest());

        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmFunctions() throws Exception {
        // Initialize the database
        admFunctionRepository.saveAndFlush(admFunction);

        // Get all the admFunctionList
        restAdmFunctionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admFunction.getId().intValue())))
            .andExpect(jsonPath("$.[*].functionId").value(hasItem(DEFAULT_FUNCTION_ID)))
            .andExpect(jsonPath("$.[*].functionName").value(hasItem(DEFAULT_FUNCTION_NAME)))
            .andExpect(jsonPath("$.[*].functionDescript").value(hasItem(DEFAULT_FUNCTION_DESCRIPT)))
            .andExpect(jsonPath("$.[*].functionPath").value(hasItem(DEFAULT_FUNCTION_PATH)))
            .andExpect(jsonPath("$.[*].functionCategory").value(hasItem(DEFAULT_FUNCTION_CATEGORY)))
            .andExpect(jsonPath("$.[*].sysCategory").value(hasItem(DEFAULT_SYS_CATEGORY)))
            .andExpect(jsonPath("$.[*].sortNo").value(hasItem(hasItem(DEFAULT_SORT_NO))))
            .andExpect(jsonPath("$.[*].masterFunctionId").value(hasItem(DEFAULT_MASTER_FUNCTION_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmFunction() throws Exception {
        // Initialize the database
        admFunctionRepository.saveAndFlush(admFunction);

        // Get the admFunction
        restAdmFunctionMockMvc
            .perform(get(ENTITY_API_URL_ID, admFunction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admFunction.getId().intValue()))
            .andExpect(jsonPath("$.functionId").value(DEFAULT_FUNCTION_ID))
            .andExpect(jsonPath("$.functionName").value(DEFAULT_FUNCTION_NAME))
            .andExpect(jsonPath("$.functionDescript").value(DEFAULT_FUNCTION_DESCRIPT))
            .andExpect(jsonPath("$.functionPath").value(DEFAULT_FUNCTION_PATH))
            .andExpect(jsonPath("$.functionCategory").value(DEFAULT_FUNCTION_CATEGORY))
            .andExpect(jsonPath("$.sysCategory").value(DEFAULT_SYS_CATEGORY))
            .andExpect(jsonPath("$.sortNo").value(hasItem(DEFAULT_SORT_NO)))
            .andExpect(jsonPath("$.masterFunctionId").value(DEFAULT_MASTER_FUNCTION_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmFunction() throws Exception {
        // Get the admFunction
        restAdmFunctionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmFunction() throws Exception {
        // Initialize the database
        admFunctionRepository.saveAndFlush(admFunction);

        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();

        // Update the admFunction
        AdmFunction updatedAdmFunction = admFunctionRepository.findById(admFunction.getId()).get();
        // Disconnect from session so that the updates on updatedAdmFunction are not directly saved in db
        em.detach(updatedAdmFunction);
        updatedAdmFunction.setFunctionId(UPDATED_FUNCTION_ID);
        updatedAdmFunction.setFunctionName(UPDATED_FUNCTION_NAME);
        updatedAdmFunction.setFunctionDescript(UPDATED_FUNCTION_DESCRIPT);
        updatedAdmFunction.setFunctionPath(UPDATED_FUNCTION_PATH);
        updatedAdmFunction.setFunctionCategory(UPDATED_FUNCTION_CATEGORY);
        updatedAdmFunction.setSysCategory(UPDATED_SYS_CATEGORY);
        updatedAdmFunction.setSortNo(UPDATED_SORT_NO);
        updatedAdmFunction.setMasterFunctionId(UPDATED_MASTER_FUNCTION_ID);
        updatedAdmFunction.setStatus(UPDATED_STATUS);
        updatedAdmFunction.setUpdateUser(UPDATED_UPDATE_USER);
        updatedAdmFunction.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmFunctionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmFunction.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmFunction))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
        AdmFunction testAdmFunction = admFunctionList.get(admFunctionList.size() - 1);
        assertThat(testAdmFunction.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmFunction.getFunctionName()).isEqualTo(UPDATED_FUNCTION_NAME);
        assertThat(testAdmFunction.getFunctionDescript()).isEqualTo(UPDATED_FUNCTION_DESCRIPT);
        assertThat(testAdmFunction.getFunctionPath()).isEqualTo(UPDATED_FUNCTION_PATH);
        assertThat(testAdmFunction.getFunctionCategory()).isEqualTo(UPDATED_FUNCTION_CATEGORY);
        assertThat(testAdmFunction.getSysCategory()).isEqualTo(UPDATED_SYS_CATEGORY);
        assertThat(testAdmFunction.getSortNo()).isEqualTo(UPDATED_SORT_NO);
        assertThat(testAdmFunction.getMasterFunctionId()).isEqualTo(UPDATED_MASTER_FUNCTION_ID);
        assertThat(testAdmFunction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmFunction.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmFunction.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmFunction() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();
        admFunction.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFunctionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admFunction.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunction))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmFunction() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();
        admFunction.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunction))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmFunction() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();
        admFunction.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunction)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmFunctionWithPatch() throws Exception {
        // Initialize the database
        admFunctionRepository.saveAndFlush(admFunction);

        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();

        // Update the admFunction using partial update
        AdmFunction partialUpdatedAdmFunction = new AdmFunction();
        partialUpdatedAdmFunction.setId(admFunction.getId());

        partialUpdatedAdmFunction.setFunctionName(UPDATED_FUNCTION_NAME);
        partialUpdatedAdmFunction.setFunctionDescript(UPDATED_FUNCTION_DESCRIPT);
        partialUpdatedAdmFunction.setFunctionPath(UPDATED_FUNCTION_PATH);
        partialUpdatedAdmFunction.setFunctionCategory(UPDATED_FUNCTION_CATEGORY);
        partialUpdatedAdmFunction.setSortNo(UPDATED_SORT_NO);
        partialUpdatedAdmFunction.setUpdateUser(UPDATED_UPDATE_USER);
        partialUpdatedAdmFunction.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmFunctionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFunction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFunction))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
        AdmFunction testAdmFunction = admFunctionList.get(admFunctionList.size() - 1);
        assertThat(testAdmFunction.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testAdmFunction.getFunctionName()).isEqualTo(UPDATED_FUNCTION_NAME);
        assertThat(testAdmFunction.getFunctionDescript()).isEqualTo(UPDATED_FUNCTION_DESCRIPT);
        assertThat(testAdmFunction.getFunctionPath()).isEqualTo(UPDATED_FUNCTION_PATH);
        assertThat(testAdmFunction.getFunctionCategory()).isEqualTo(UPDATED_FUNCTION_CATEGORY);
        assertThat(testAdmFunction.getSysCategory()).isEqualTo(DEFAULT_SYS_CATEGORY);
        assertThat(testAdmFunction.getSortNo()).isEqualByComparingTo(UPDATED_SORT_NO);
        assertThat(testAdmFunction.getMasterFunctionId()).isEqualTo(DEFAULT_MASTER_FUNCTION_ID);
        assertThat(testAdmFunction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmFunction.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmFunction.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmFunctionWithPatch() throws Exception {
        // Initialize the database
        admFunctionRepository.saveAndFlush(admFunction);

        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();

        // Update the admFunction using partial update
        AdmFunction partialUpdatedAdmFunction = new AdmFunction();
        partialUpdatedAdmFunction.setId(admFunction.getId());

        partialUpdatedAdmFunction.setFunctionId(UPDATED_FUNCTION_ID);
        partialUpdatedAdmFunction.setFunctionName(UPDATED_FUNCTION_NAME);
        partialUpdatedAdmFunction.setFunctionDescript(UPDATED_FUNCTION_DESCRIPT);
        partialUpdatedAdmFunction.setFunctionPath(UPDATED_FUNCTION_PATH);
        partialUpdatedAdmFunction.setFunctionCategory(UPDATED_FUNCTION_CATEGORY);
        partialUpdatedAdmFunction.setSysCategory(UPDATED_SYS_CATEGORY);
        partialUpdatedAdmFunction.setSortNo(UPDATED_SORT_NO);
        partialUpdatedAdmFunction.setMasterFunctionId(UPDATED_MASTER_FUNCTION_ID);
        partialUpdatedAdmFunction.setStatus(UPDATED_STATUS);
        partialUpdatedAdmFunction.setUpdateUser(UPDATED_UPDATE_USER);
        partialUpdatedAdmFunction.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmFunctionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFunction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFunction))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
        AdmFunction testAdmFunction = admFunctionList.get(admFunctionList.size() - 1);
        assertThat(testAdmFunction.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmFunction.getFunctionName()).isEqualTo(UPDATED_FUNCTION_NAME);
        assertThat(testAdmFunction.getFunctionDescript()).isEqualTo(UPDATED_FUNCTION_DESCRIPT);
        assertThat(testAdmFunction.getFunctionPath()).isEqualTo(UPDATED_FUNCTION_PATH);
        assertThat(testAdmFunction.getFunctionCategory()).isEqualTo(UPDATED_FUNCTION_CATEGORY);
        assertThat(testAdmFunction.getSysCategory()).isEqualTo(UPDATED_SYS_CATEGORY);
        assertThat(testAdmFunction.getSortNo()).isEqualByComparingTo(UPDATED_SORT_NO);
        assertThat(testAdmFunction.getMasterFunctionId()).isEqualTo(UPDATED_MASTER_FUNCTION_ID);
        assertThat(testAdmFunction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmFunction.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmFunction.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmFunction() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();
        admFunction.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFunctionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admFunction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunction))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmFunction() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();
        admFunction.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunction))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmFunction() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionRepository.findAll().size();
        admFunction.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admFunction))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFunction in the database
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmFunction() throws Exception {
        // Initialize the database
        admFunctionRepository.saveAndFlush(admFunction);

        int databaseSizeBeforeDelete = admFunctionRepository.findAll().size();

        // Delete the admFunction
        restAdmFunctionMockMvc
            .perform(delete(ENTITY_API_URL_ID, admFunction.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmFunction> admFunctionList = admFunctionRepository.findAll();
        assertThat(admFunctionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
