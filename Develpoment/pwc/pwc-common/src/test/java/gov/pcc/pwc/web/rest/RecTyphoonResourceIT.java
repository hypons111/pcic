package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.RecTyphoon;
import gov.pcc.pwc.repository.RecTyphoonRepository;
import gov.pcc.pwc.service.dto.RecTyphoonDTO;
import gov.pcc.pwc.service.mapper.RecTyphoonMapper;
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
 * Integration tests for the {@link RecTyphoonResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RecTyphoonResourceIT {

    private static final String DEFAULT_TYPHOON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPHOON_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPLY_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPLY_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_APPLY_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPLY_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_LOCAL_ONLY = false;
    private static final Boolean UPDATED_IS_LOCAL_ONLY = true;

    private static final Instant DEFAULT_FINISH_DATE_1000 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FINISH_DATE_1000 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FINISH_DATE_5000 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FINISH_DATE_5000 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_BID_PROJECT_IMPORT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BID_PROJECT_IMPORT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CENTER_APPROVED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CENTER_APPROVED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CENTER_APPROVED_PROGRESS = "AAAAAAAAAA";
    private static final String UPDATED_CENTER_APPROVED_PROGRESS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_EDIT_SUBMIT_DATA = false;
    private static final Boolean UPDATED_IS_EDIT_SUBMIT_DATA = true;

    private static final Boolean DEFAULT_IS_DENY_BUDGET_CHANGE = false;
    private static final Boolean UPDATED_IS_DENY_BUDGET_CHANGE = true;

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/rec-typhoons";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{typhoonNo}";

    @Autowired
    private RecTyphoonRepository recTyphoonRepository;

    @Autowired
    private RecTyphoonMapper recTyphoonMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRecTyphoonMockMvc;

    private RecTyphoon recTyphoon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecTyphoon createEntity(EntityManager em) {
        RecTyphoon recTyphoon = new RecTyphoon()
            .typhoonName(DEFAULT_TYPHOON_NAME)
            .applyStartTime(DEFAULT_APPLY_START_TIME)
            .applyEndTime(DEFAULT_APPLY_END_TIME)
            .isLocalOnly(DEFAULT_IS_LOCAL_ONLY)
            .finishDate1000(DEFAULT_FINISH_DATE_1000)
            .finishDate5000(DEFAULT_FINISH_DATE_5000)
            .bidProjectImportDate(DEFAULT_BID_PROJECT_IMPORT_DATE)
            .centerApprovedDate(DEFAULT_CENTER_APPROVED_DATE)
            .centerApprovedProgress(DEFAULT_CENTER_APPROVED_PROGRESS)
            .isEditSubmitData(DEFAULT_IS_EDIT_SUBMIT_DATA)
            .isDenyBudgetChange(DEFAULT_IS_DENY_BUDGET_CHANGE)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return recTyphoon;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecTyphoon createUpdatedEntity(EntityManager em) {
        RecTyphoon recTyphoon = new RecTyphoon()
            .typhoonName(UPDATED_TYPHOON_NAME)
            .applyStartTime(UPDATED_APPLY_START_TIME)
            .applyEndTime(UPDATED_APPLY_END_TIME)
            .isLocalOnly(UPDATED_IS_LOCAL_ONLY)
            .finishDate1000(UPDATED_FINISH_DATE_1000)
            .finishDate5000(UPDATED_FINISH_DATE_5000)
            .bidProjectImportDate(UPDATED_BID_PROJECT_IMPORT_DATE)
            .centerApprovedDate(UPDATED_CENTER_APPROVED_DATE)
            .centerApprovedProgress(UPDATED_CENTER_APPROVED_PROGRESS)
            .isEditSubmitData(UPDATED_IS_EDIT_SUBMIT_DATA)
            .isDenyBudgetChange(UPDATED_IS_DENY_BUDGET_CHANGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return recTyphoon;
    }

    @BeforeEach
    public void initTest() {
        recTyphoon = createEntity(em);
    }

    @Test
    @Transactional
    void createRecTyphoon() throws Exception {
        int databaseSizeBeforeCreate = recTyphoonRepository.findAll().size();
        // Create the RecTyphoon
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);
        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isCreated());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeCreate + 1);
        RecTyphoon testRecTyphoon = recTyphoonList.get(recTyphoonList.size() - 1);
        assertThat(testRecTyphoon.getTyphoonName()).isEqualTo(DEFAULT_TYPHOON_NAME);
        assertThat(testRecTyphoon.getApplyStartTime()).isEqualTo(DEFAULT_APPLY_START_TIME);
        assertThat(testRecTyphoon.getApplyEndTime()).isEqualTo(DEFAULT_APPLY_END_TIME);
        assertThat(testRecTyphoon.getIsLocalOnly()).isEqualTo(DEFAULT_IS_LOCAL_ONLY);
        assertThat(testRecTyphoon.getFinishDate1000()).isEqualTo(DEFAULT_FINISH_DATE_1000);
        assertThat(testRecTyphoon.getFinishDate5000()).isEqualTo(DEFAULT_FINISH_DATE_5000);
        assertThat(testRecTyphoon.getBidProjectImportDate()).isEqualTo(DEFAULT_BID_PROJECT_IMPORT_DATE);
        assertThat(testRecTyphoon.getCenterApprovedDate()).isEqualTo(DEFAULT_CENTER_APPROVED_DATE);
        assertThat(testRecTyphoon.getCenterApprovedProgress()).isEqualTo(DEFAULT_CENTER_APPROVED_PROGRESS);
        assertThat(testRecTyphoon.getIsEditSubmitData()).isEqualTo(DEFAULT_IS_EDIT_SUBMIT_DATA);
        assertThat(testRecTyphoon.getIsDenyBudgetChange()).isEqualTo(DEFAULT_IS_DENY_BUDGET_CHANGE);
        assertThat(testRecTyphoon.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testRecTyphoon.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testRecTyphoon.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testRecTyphoon.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createRecTyphoonWithExistingId() throws Exception {
        // Create the RecTyphoon with an existing ID
        recTyphoon.setTyphoonNo("existing_id");
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        int databaseSizeBeforeCreate = recTyphoonRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkApplyStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setApplyStartTime(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsLocalOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setIsLocalOnly(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFinishDate1000IsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setFinishDate1000(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFinishDate5000IsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setFinishDate5000(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsEditSubmitDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setIsEditSubmitData(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsDenyBudgetChangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setIsDenyBudgetChange(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setCreateUser(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setCreateTime(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setUpdateUser(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = recTyphoonRepository.findAll().size();
        // set the field null
        recTyphoon.setUpdateTime(null);

        // Create the RecTyphoon, which fails.
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        restRecTyphoonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isBadRequest());

        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRecTyphoons() throws Exception {
        // Initialize the database
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());
        recTyphoonRepository.saveAndFlush(recTyphoon);

        // Get all the recTyphoonList
        restRecTyphoonMockMvc
            .perform(get(ENTITY_API_URL + "?sort=typhoonNo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].typhoonNo").value(hasItem(recTyphoon.getTyphoonNo())))
            .andExpect(jsonPath("$.[*].typhoonName").value(hasItem(DEFAULT_TYPHOON_NAME)))
            .andExpect(jsonPath("$.[*].applyStartTime").value(hasItem(DEFAULT_APPLY_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].applyEndTime").value(hasItem(DEFAULT_APPLY_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].isLocalOnly").value(hasItem(DEFAULT_IS_LOCAL_ONLY.booleanValue())))
            .andExpect(jsonPath("$.[*].finishDate1000").value(hasItem(DEFAULT_FINISH_DATE_1000.toString())))
            .andExpect(jsonPath("$.[*].finishDate5000").value(hasItem(DEFAULT_FINISH_DATE_5000.toString())))
            .andExpect(jsonPath("$.[*].bidProjectImportDate").value(hasItem(DEFAULT_BID_PROJECT_IMPORT_DATE.toString())))
            .andExpect(jsonPath("$.[*].centerApprovedDate").value(hasItem(DEFAULT_CENTER_APPROVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].centerApprovedProgress").value(hasItem(DEFAULT_CENTER_APPROVED_PROGRESS)))
            .andExpect(jsonPath("$.[*].isEditSubmitData").value(hasItem(DEFAULT_IS_EDIT_SUBMIT_DATA.booleanValue())))
            .andExpect(jsonPath("$.[*].isDenyBudgetChange").value(hasItem(DEFAULT_IS_DENY_BUDGET_CHANGE.booleanValue())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getRecTyphoon() throws Exception {
        // Initialize the database
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());
        recTyphoonRepository.saveAndFlush(recTyphoon);

        // Get the recTyphoon
        restRecTyphoonMockMvc
            .perform(get(ENTITY_API_URL_ID, recTyphoon.getTyphoonNo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.typhoonNo").value(recTyphoon.getTyphoonNo()))
            .andExpect(jsonPath("$.typhoonName").value(DEFAULT_TYPHOON_NAME))
            .andExpect(jsonPath("$.applyStartTime").value(DEFAULT_APPLY_START_TIME.toString()))
            .andExpect(jsonPath("$.applyEndTime").value(DEFAULT_APPLY_END_TIME.toString()))
            .andExpect(jsonPath("$.isLocalOnly").value(DEFAULT_IS_LOCAL_ONLY.booleanValue()))
            .andExpect(jsonPath("$.finishDate1000").value(DEFAULT_FINISH_DATE_1000.toString()))
            .andExpect(jsonPath("$.finishDate5000").value(DEFAULT_FINISH_DATE_5000.toString()))
            .andExpect(jsonPath("$.bidProjectImportDate").value(DEFAULT_BID_PROJECT_IMPORT_DATE.toString()))
            .andExpect(jsonPath("$.centerApprovedDate").value(DEFAULT_CENTER_APPROVED_DATE.toString()))
            .andExpect(jsonPath("$.centerApprovedProgress").value(DEFAULT_CENTER_APPROVED_PROGRESS))
            .andExpect(jsonPath("$.isEditSubmitData").value(DEFAULT_IS_EDIT_SUBMIT_DATA.booleanValue()))
            .andExpect(jsonPath("$.isDenyBudgetChange").value(DEFAULT_IS_DENY_BUDGET_CHANGE.booleanValue()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRecTyphoon() throws Exception {
        // Get the recTyphoon
        restRecTyphoonMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRecTyphoon() throws Exception {
        // Initialize the database
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());
        recTyphoonRepository.saveAndFlush(recTyphoon);

        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();

        // Update the recTyphoon
        RecTyphoon updatedRecTyphoon = recTyphoonRepository.findById(recTyphoon.getTyphoonNo()).get();
        // Disconnect from session so that the updates on updatedRecTyphoon are not directly saved in db
        em.detach(updatedRecTyphoon);
        updatedRecTyphoon
            .typhoonName(UPDATED_TYPHOON_NAME)
            .applyStartTime(UPDATED_APPLY_START_TIME)
            .applyEndTime(UPDATED_APPLY_END_TIME)
            .isLocalOnly(UPDATED_IS_LOCAL_ONLY)
            .finishDate1000(UPDATED_FINISH_DATE_1000)
            .finishDate5000(UPDATED_FINISH_DATE_5000)
            .bidProjectImportDate(UPDATED_BID_PROJECT_IMPORT_DATE)
            .centerApprovedDate(UPDATED_CENTER_APPROVED_DATE)
            .centerApprovedProgress(UPDATED_CENTER_APPROVED_PROGRESS)
            .isEditSubmitData(UPDATED_IS_EDIT_SUBMIT_DATA)
            .isDenyBudgetChange(UPDATED_IS_DENY_BUDGET_CHANGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(updatedRecTyphoon);

        restRecTyphoonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, recTyphoonDTO.getTyphoonNo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO))
            )
            .andExpect(status().isOk());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
        RecTyphoon testRecTyphoon = recTyphoonList.get(recTyphoonList.size() - 1);
        assertThat(testRecTyphoon.getTyphoonName()).isEqualTo(UPDATED_TYPHOON_NAME);
        assertThat(testRecTyphoon.getApplyStartTime()).isEqualTo(UPDATED_APPLY_START_TIME);
        assertThat(testRecTyphoon.getApplyEndTime()).isEqualTo(UPDATED_APPLY_END_TIME);
        assertThat(testRecTyphoon.getIsLocalOnly()).isEqualTo(UPDATED_IS_LOCAL_ONLY);
        assertThat(testRecTyphoon.getFinishDate1000()).isEqualTo(UPDATED_FINISH_DATE_1000);
        assertThat(testRecTyphoon.getFinishDate5000()).isEqualTo(UPDATED_FINISH_DATE_5000);
        assertThat(testRecTyphoon.getBidProjectImportDate()).isEqualTo(UPDATED_BID_PROJECT_IMPORT_DATE);
        assertThat(testRecTyphoon.getCenterApprovedDate()).isEqualTo(UPDATED_CENTER_APPROVED_DATE);
        assertThat(testRecTyphoon.getCenterApprovedProgress()).isEqualTo(UPDATED_CENTER_APPROVED_PROGRESS);
        assertThat(testRecTyphoon.getIsEditSubmitData()).isEqualTo(UPDATED_IS_EDIT_SUBMIT_DATA);
        assertThat(testRecTyphoon.getIsDenyBudgetChange()).isEqualTo(UPDATED_IS_DENY_BUDGET_CHANGE);
        assertThat(testRecTyphoon.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testRecTyphoon.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRecTyphoon.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testRecTyphoon.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingRecTyphoon() throws Exception {
        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());

        // Create the RecTyphoon
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecTyphoonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, recTyphoonDTO.getTyphoonNo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRecTyphoon() throws Exception {
        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());

        // Create the RecTyphoon
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecTyphoonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRecTyphoon() throws Exception {
        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());

        // Create the RecTyphoon
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecTyphoonMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRecTyphoonWithPatch() throws Exception {
        // Initialize the database
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());
        recTyphoonRepository.saveAndFlush(recTyphoon);

        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();

        // Update the recTyphoon using partial update
        RecTyphoon partialUpdatedRecTyphoon = new RecTyphoon();
        partialUpdatedRecTyphoon.setTyphoonNo(recTyphoon.getTyphoonNo());

        partialUpdatedRecTyphoon
            .finishDate1000(UPDATED_FINISH_DATE_1000)
            .bidProjectImportDate(UPDATED_BID_PROJECT_IMPORT_DATE)
            .isEditSubmitData(UPDATED_IS_EDIT_SUBMIT_DATA)
            .isDenyBudgetChange(UPDATED_IS_DENY_BUDGET_CHANGE)
            .createTime(UPDATED_CREATE_TIME);

        restRecTyphoonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRecTyphoon.getTyphoonNo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRecTyphoon))
            )
            .andExpect(status().isOk());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
        RecTyphoon testRecTyphoon = recTyphoonList.get(recTyphoonList.size() - 1);
        assertThat(testRecTyphoon.getTyphoonName()).isEqualTo(DEFAULT_TYPHOON_NAME);
        assertThat(testRecTyphoon.getApplyStartTime()).isEqualTo(DEFAULT_APPLY_START_TIME);
        assertThat(testRecTyphoon.getApplyEndTime()).isEqualTo(DEFAULT_APPLY_END_TIME);
        assertThat(testRecTyphoon.getIsLocalOnly()).isEqualTo(DEFAULT_IS_LOCAL_ONLY);
        assertThat(testRecTyphoon.getFinishDate1000()).isEqualTo(UPDATED_FINISH_DATE_1000);
        assertThat(testRecTyphoon.getFinishDate5000()).isEqualTo(DEFAULT_FINISH_DATE_5000);
        assertThat(testRecTyphoon.getBidProjectImportDate()).isEqualTo(UPDATED_BID_PROJECT_IMPORT_DATE);
        assertThat(testRecTyphoon.getCenterApprovedDate()).isEqualTo(DEFAULT_CENTER_APPROVED_DATE);
        assertThat(testRecTyphoon.getCenterApprovedProgress()).isEqualTo(DEFAULT_CENTER_APPROVED_PROGRESS);
        assertThat(testRecTyphoon.getIsEditSubmitData()).isEqualTo(UPDATED_IS_EDIT_SUBMIT_DATA);
        assertThat(testRecTyphoon.getIsDenyBudgetChange()).isEqualTo(UPDATED_IS_DENY_BUDGET_CHANGE);
        assertThat(testRecTyphoon.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testRecTyphoon.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRecTyphoon.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testRecTyphoon.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateRecTyphoonWithPatch() throws Exception {
        // Initialize the database
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());
        recTyphoonRepository.saveAndFlush(recTyphoon);

        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();

        // Update the recTyphoon using partial update
        RecTyphoon partialUpdatedRecTyphoon = new RecTyphoon();
        partialUpdatedRecTyphoon.setTyphoonNo(recTyphoon.getTyphoonNo());

        partialUpdatedRecTyphoon
            .typhoonName(UPDATED_TYPHOON_NAME)
            .applyStartTime(UPDATED_APPLY_START_TIME)
            .applyEndTime(UPDATED_APPLY_END_TIME)
            .isLocalOnly(UPDATED_IS_LOCAL_ONLY)
            .finishDate1000(UPDATED_FINISH_DATE_1000)
            .finishDate5000(UPDATED_FINISH_DATE_5000)
            .bidProjectImportDate(UPDATED_BID_PROJECT_IMPORT_DATE)
            .centerApprovedDate(UPDATED_CENTER_APPROVED_DATE)
            .centerApprovedProgress(UPDATED_CENTER_APPROVED_PROGRESS)
            .isEditSubmitData(UPDATED_IS_EDIT_SUBMIT_DATA)
            .isDenyBudgetChange(UPDATED_IS_DENY_BUDGET_CHANGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restRecTyphoonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRecTyphoon.getTyphoonNo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRecTyphoon))
            )
            .andExpect(status().isOk());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
        RecTyphoon testRecTyphoon = recTyphoonList.get(recTyphoonList.size() - 1);
        assertThat(testRecTyphoon.getTyphoonName()).isEqualTo(UPDATED_TYPHOON_NAME);
        assertThat(testRecTyphoon.getApplyStartTime()).isEqualTo(UPDATED_APPLY_START_TIME);
        assertThat(testRecTyphoon.getApplyEndTime()).isEqualTo(UPDATED_APPLY_END_TIME);
        assertThat(testRecTyphoon.getIsLocalOnly()).isEqualTo(UPDATED_IS_LOCAL_ONLY);
        assertThat(testRecTyphoon.getFinishDate1000()).isEqualTo(UPDATED_FINISH_DATE_1000);
        assertThat(testRecTyphoon.getFinishDate5000()).isEqualTo(UPDATED_FINISH_DATE_5000);
        assertThat(testRecTyphoon.getBidProjectImportDate()).isEqualTo(UPDATED_BID_PROJECT_IMPORT_DATE);
        assertThat(testRecTyphoon.getCenterApprovedDate()).isEqualTo(UPDATED_CENTER_APPROVED_DATE);
        assertThat(testRecTyphoon.getCenterApprovedProgress()).isEqualTo(UPDATED_CENTER_APPROVED_PROGRESS);
        assertThat(testRecTyphoon.getIsEditSubmitData()).isEqualTo(UPDATED_IS_EDIT_SUBMIT_DATA);
        assertThat(testRecTyphoon.getIsDenyBudgetChange()).isEqualTo(UPDATED_IS_DENY_BUDGET_CHANGE);
        assertThat(testRecTyphoon.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testRecTyphoon.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRecTyphoon.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testRecTyphoon.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingRecTyphoon() throws Exception {
        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());

        // Create the RecTyphoon
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecTyphoonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, recTyphoonDTO.getTyphoonNo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRecTyphoon() throws Exception {
        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());

        // Create the RecTyphoon
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecTyphoonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRecTyphoon() throws Exception {
        int databaseSizeBeforeUpdate = recTyphoonRepository.findAll().size();
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());

        // Create the RecTyphoon
        RecTyphoonDTO recTyphoonDTO = recTyphoonMapper.toDto(recTyphoon);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecTyphoonMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(recTyphoonDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RecTyphoon in the database
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRecTyphoon() throws Exception {
        // Initialize the database
        recTyphoon.setTyphoonNo(UUID.randomUUID().toString());
        recTyphoonRepository.saveAndFlush(recTyphoon);

        int databaseSizeBeforeDelete = recTyphoonRepository.findAll().size();

        // Delete the recTyphoon
        restRecTyphoonMockMvc
            .perform(delete(ENTITY_API_URL_ID, recTyphoon.getTyphoonNo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RecTyphoon> recTyphoonList = recTyphoonRepository.findAll();
        assertThat(recTyphoonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
