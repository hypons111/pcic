package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmImportRecord;
import gov.pcc.pwc.repository.AdmImportRecordRepository;
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
import java.time.LocalDate;
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
 * Integration tests for the {@link AdmImportRecordResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmImportRecordResourceIT {

    private static final LocalDate DEFAULT_PROCESS_DATE = LocalDate.of(2022,6,19);
    private static final LocalDate UPDATED_PROCESS_DATE = LocalDate.of(2022,6,20);

    private static final Instant DEFAULT_PROCESS_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROCESS_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PROCESS_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROCESS_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PROCESS_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROCESS_FILE_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ORIGINAL_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORIGINAL_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PROCESSED_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROCESSED_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_UNPROCESSED_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNPROCESSED_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INSERT_SUCCESS_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INSERT_SUCCESS_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INSERT_FAIL_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INSERT_FAIL_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_UPDATE_SUCCESS_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_UPDATE_SUCCESS_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_UPDATE_FAIL_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_UPDATE_FAIL_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DELETE_SUCCESS_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DELETE_SUCCESS_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DELETE_FAIL_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DELETE_FAIL_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CASE_SUCCESS_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CASE_SUCCESS_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CASE_FAIL_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CASE_FAIL_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INTERFACING_SUCCESS_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTERFACING_SUCCESS_DATA_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INTERFACING_FAIL_DATA_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTERFACING_FAIL_DATA_COUNT = new BigDecimal(2);

    private static final String DEFAULT_FAILED_DATA_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_FAILED_DATA_MEMO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/adm-import-records";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmImportRecordRepository admImportRecordRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmImportRecordMockMvc;

    private AdmImportRecord admImportRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmImportRecord createEntity(EntityManager em) {
        AdmImportRecord admImportRecord = new AdmImportRecord()
            .processDate(DEFAULT_PROCESS_DATE)
            .processStartTime(DEFAULT_PROCESS_START_TIME)
            .processEndTime(DEFAULT_PROCESS_END_TIME)
            .processFileName(DEFAULT_PROCESS_FILE_NAME)
            .originalDataCount(DEFAULT_ORIGINAL_DATA_COUNT)
            .processedDataCount(DEFAULT_PROCESSED_DATA_COUNT)
            .unprocessedDataCount(DEFAULT_UNPROCESSED_DATA_COUNT)
            .insertSuccessDataCount(DEFAULT_INSERT_SUCCESS_DATA_COUNT)
            .insertFailDataCount(DEFAULT_INSERT_FAIL_DATA_COUNT)
            .updateSuccessDataCount(DEFAULT_UPDATE_SUCCESS_DATA_COUNT)
            .updateFailDataCount(DEFAULT_UPDATE_FAIL_DATA_COUNT)
            .deleteSuccessDataCount(DEFAULT_DELETE_SUCCESS_DATA_COUNT)
            .deleteFailDataCount(DEFAULT_DELETE_FAIL_DATA_COUNT)
            .caseSuccessDataCount(DEFAULT_CASE_SUCCESS_DATA_COUNT)
            .caseFailDataCount(DEFAULT_CASE_FAIL_DATA_COUNT)
            .interfacingSuccessDataCount(DEFAULT_INTERFACING_SUCCESS_DATA_COUNT)
            .interfacingFailDataCount(DEFAULT_INTERFACING_FAIL_DATA_COUNT)
            .failedDataMemo(DEFAULT_FAILED_DATA_MEMO);
        return admImportRecord;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmImportRecord createUpdatedEntity(EntityManager em) {
        AdmImportRecord admImportRecord = new AdmImportRecord()
            .processDate(UPDATED_PROCESS_DATE)
            .processStartTime(UPDATED_PROCESS_START_TIME)
            .processEndTime(UPDATED_PROCESS_END_TIME)
            .processFileName(UPDATED_PROCESS_FILE_NAME)
            .originalDataCount(UPDATED_ORIGINAL_DATA_COUNT)
            .processedDataCount(UPDATED_PROCESSED_DATA_COUNT)
            .unprocessedDataCount(UPDATED_UNPROCESSED_DATA_COUNT)
            .insertSuccessDataCount(UPDATED_INSERT_SUCCESS_DATA_COUNT)
            .insertFailDataCount(UPDATED_INSERT_FAIL_DATA_COUNT)
            .updateSuccessDataCount(UPDATED_UPDATE_SUCCESS_DATA_COUNT)
            .updateFailDataCount(UPDATED_UPDATE_FAIL_DATA_COUNT)
            .deleteSuccessDataCount(UPDATED_DELETE_SUCCESS_DATA_COUNT)
            .deleteFailDataCount(UPDATED_DELETE_FAIL_DATA_COUNT)
            .caseSuccessDataCount(UPDATED_CASE_SUCCESS_DATA_COUNT)
            .caseFailDataCount(UPDATED_CASE_FAIL_DATA_COUNT)
            .interfacingSuccessDataCount(UPDATED_INTERFACING_SUCCESS_DATA_COUNT)
            .interfacingFailDataCount(UPDATED_INTERFACING_FAIL_DATA_COUNT)
            .failedDataMemo(UPDATED_FAILED_DATA_MEMO);
        return admImportRecord;
    }

    @BeforeEach
    public void initTest() {
        admImportRecord = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmImportRecord() throws Exception {
        int databaseSizeBeforeCreate = admImportRecordRepository.findAll().size();
        // Create the AdmImportRecord
        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isCreated());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeCreate + 1);
        AdmImportRecord testAdmImportRecord = admImportRecordList.get(admImportRecordList.size() - 1);
        assertThat(testAdmImportRecord.getProcessDate()).isEqualTo(DEFAULT_PROCESS_DATE);
        assertThat(testAdmImportRecord.getProcessStartTime()).isEqualTo(DEFAULT_PROCESS_START_TIME);
        assertThat(testAdmImportRecord.getProcessEndTime()).isEqualTo(DEFAULT_PROCESS_END_TIME);
        assertThat(testAdmImportRecord.getProcessFileName()).isEqualTo(DEFAULT_PROCESS_FILE_NAME);
        assertThat(testAdmImportRecord.getOriginalDataCount()).isEqualByComparingTo(DEFAULT_ORIGINAL_DATA_COUNT);
        assertThat(testAdmImportRecord.getProcessedDataCount()).isEqualByComparingTo(DEFAULT_PROCESSED_DATA_COUNT);
        assertThat(testAdmImportRecord.getUnprocessedDataCount()).isEqualByComparingTo(DEFAULT_UNPROCESSED_DATA_COUNT);
        assertThat(testAdmImportRecord.getInsertSuccessDataCount()).isEqualByComparingTo(DEFAULT_INSERT_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getInsertFailDataCount()).isEqualByComparingTo(DEFAULT_INSERT_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getUpdateSuccessDataCount()).isEqualByComparingTo(DEFAULT_UPDATE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getUpdateFailDataCount()).isEqualByComparingTo(DEFAULT_UPDATE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getDeleteSuccessDataCount()).isEqualByComparingTo(DEFAULT_DELETE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getDeleteFailDataCount()).isEqualByComparingTo(DEFAULT_DELETE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getCaseSuccessDataCount()).isEqualByComparingTo(DEFAULT_CASE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getCaseFailDataCount()).isEqualByComparingTo(DEFAULT_CASE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getInterfacingSuccessDataCount()).isEqualByComparingTo(DEFAULT_INTERFACING_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getInterfacingFailDataCount()).isEqualByComparingTo(DEFAULT_INTERFACING_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getFailedDataMemo()).isEqualTo(DEFAULT_FAILED_DATA_MEMO);
    }

    @Test
    @Transactional
    void createAdmImportRecordWithExistingId() throws Exception {
        // Create the AdmImportRecord with an existing ID
        admImportRecord.setId(1L);

        int databaseSizeBeforeCreate = admImportRecordRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkProcessDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setProcessDate(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcessStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setProcessStartTime(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcessEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setProcessEndTime(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcessFileNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setProcessFileName(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOriginalDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setOriginalDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcessedDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setProcessedDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUnprocessedDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setUnprocessedDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInsertSuccessDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setInsertSuccessDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInsertFailDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setInsertFailDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateSuccessDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setUpdateSuccessDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateFailDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setUpdateFailDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDeleteSuccessDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setDeleteSuccessDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDeleteFailDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setDeleteFailDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCaseSuccessDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setCaseSuccessDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCaseFailDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setCaseFailDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterfacingSuccessDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setInterfacingSuccessDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInterfacingFailDataCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = admImportRecordRepository.findAll().size();
        // set the field null
        admImportRecord.setInterfacingFailDataCount(null);

        // Create the AdmImportRecord, which fails.

        restAdmImportRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmImportRecords() throws Exception {
        // Initialize the database
        admImportRecordRepository.saveAndFlush(admImportRecord);

        // Get all the admImportRecordList
        restAdmImportRecordMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admImportRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].processDate").value(hasItem(DEFAULT_PROCESS_DATE.toString())))
            .andExpect(jsonPath("$.[*].processStartTime").value(hasItem(DEFAULT_PROCESS_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].processEndTime").value(hasItem(DEFAULT_PROCESS_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].processFileName").value(hasItem(DEFAULT_PROCESS_FILE_NAME)))
            .andExpect(jsonPath("$.[*].originalDataCount").value(hasItem(sameNumber(DEFAULT_ORIGINAL_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].processedDataCount").value(hasItem(sameNumber(DEFAULT_PROCESSED_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].unprocessedDataCount").value(hasItem(sameNumber(DEFAULT_UNPROCESSED_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].insertSuccessDataCount").value(hasItem(sameNumber(DEFAULT_INSERT_SUCCESS_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].insertFailDataCount").value(hasItem(sameNumber(DEFAULT_INSERT_FAIL_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].updateSuccessDataCount").value(hasItem(sameNumber(DEFAULT_UPDATE_SUCCESS_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].updateFailDataCount").value(hasItem(sameNumber(DEFAULT_UPDATE_FAIL_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].deleteSuccessDataCount").value(hasItem(sameNumber(DEFAULT_DELETE_SUCCESS_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].deleteFailDataCount").value(hasItem(sameNumber(DEFAULT_DELETE_FAIL_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].caseSuccessDataCount").value(hasItem(sameNumber(DEFAULT_CASE_SUCCESS_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].caseFailDataCount").value(hasItem(sameNumber(DEFAULT_CASE_FAIL_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].interfacingSuccessDataCount").value(hasItem(sameNumber(DEFAULT_INTERFACING_SUCCESS_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].interfacingFailDataCount").value(hasItem(sameNumber(DEFAULT_INTERFACING_FAIL_DATA_COUNT))))
            .andExpect(jsonPath("$.[*].failedDataMemo").value(hasItem(DEFAULT_FAILED_DATA_MEMO)));
    }

    @Test
    @Transactional
    void getAdmImportRecord() throws Exception {
        // Initialize the database
        admImportRecordRepository.saveAndFlush(admImportRecord);

        // Get the admImportRecord
        restAdmImportRecordMockMvc
            .perform(get(ENTITY_API_URL_ID, admImportRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admImportRecord.getId().intValue()))
            .andExpect(jsonPath("$.processDate").value(DEFAULT_PROCESS_DATE.toString()))
            .andExpect(jsonPath("$.processStartTime").value(DEFAULT_PROCESS_START_TIME.toString()))
            .andExpect(jsonPath("$.processEndTime").value(DEFAULT_PROCESS_END_TIME.toString()))
            .andExpect(jsonPath("$.processFileName").value(DEFAULT_PROCESS_FILE_NAME))
            .andExpect(jsonPath("$.originalDataCount").value(sameNumber(DEFAULT_ORIGINAL_DATA_COUNT)))
            .andExpect(jsonPath("$.processedDataCount").value(sameNumber(DEFAULT_PROCESSED_DATA_COUNT)))
            .andExpect(jsonPath("$.unprocessedDataCount").value(sameNumber(DEFAULT_UNPROCESSED_DATA_COUNT)))
            .andExpect(jsonPath("$.insertSuccessDataCount").value(sameNumber(DEFAULT_INSERT_SUCCESS_DATA_COUNT)))
            .andExpect(jsonPath("$.insertFailDataCount").value(sameNumber(DEFAULT_INSERT_FAIL_DATA_COUNT)))
            .andExpect(jsonPath("$.updateSuccessDataCount").value(sameNumber(DEFAULT_UPDATE_SUCCESS_DATA_COUNT)))
            .andExpect(jsonPath("$.updateFailDataCount").value(sameNumber(DEFAULT_UPDATE_FAIL_DATA_COUNT)))
            .andExpect(jsonPath("$.deleteSuccessDataCount").value(sameNumber(DEFAULT_DELETE_SUCCESS_DATA_COUNT)))
            .andExpect(jsonPath("$.deleteFailDataCount").value(sameNumber(DEFAULT_DELETE_FAIL_DATA_COUNT)))
            .andExpect(jsonPath("$.caseSuccessDataCount").value(sameNumber(DEFAULT_CASE_SUCCESS_DATA_COUNT)))
            .andExpect(jsonPath("$.caseFailDataCount").value(sameNumber(DEFAULT_CASE_FAIL_DATA_COUNT)))
            .andExpect(jsonPath("$.interfacingSuccessDataCount").value(sameNumber(DEFAULT_INTERFACING_SUCCESS_DATA_COUNT)))
            .andExpect(jsonPath("$.interfacingFailDataCount").value(sameNumber(DEFAULT_INTERFACING_FAIL_DATA_COUNT)))
            .andExpect(jsonPath("$.failedDataMemo").value(DEFAULT_FAILED_DATA_MEMO));
    }

    @Test
    @Transactional
    void getNonExistingAdmImportRecord() throws Exception {
        // Get the admImportRecord
        restAdmImportRecordMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmImportRecord() throws Exception {
        // Initialize the database
        admImportRecordRepository.saveAndFlush(admImportRecord);

        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();

        // Update the admImportRecord
        AdmImportRecord updatedAdmImportRecord = admImportRecordRepository.findById(admImportRecord.getId()).get();
        // Disconnect from session so that the updates on updatedAdmImportRecord are not directly saved in db
        em.detach(updatedAdmImportRecord);
        updatedAdmImportRecord
            .processDate(UPDATED_PROCESS_DATE)
            .processStartTime(UPDATED_PROCESS_START_TIME)
            .processEndTime(UPDATED_PROCESS_END_TIME)
            .processFileName(UPDATED_PROCESS_FILE_NAME)
            .originalDataCount(UPDATED_ORIGINAL_DATA_COUNT)
            .processedDataCount(UPDATED_PROCESSED_DATA_COUNT)
            .unprocessedDataCount(UPDATED_UNPROCESSED_DATA_COUNT)
            .insertSuccessDataCount(UPDATED_INSERT_SUCCESS_DATA_COUNT)
            .insertFailDataCount(UPDATED_INSERT_FAIL_DATA_COUNT)
            .updateSuccessDataCount(UPDATED_UPDATE_SUCCESS_DATA_COUNT)
            .updateFailDataCount(UPDATED_UPDATE_FAIL_DATA_COUNT)
            .deleteSuccessDataCount(UPDATED_DELETE_SUCCESS_DATA_COUNT)
            .deleteFailDataCount(UPDATED_DELETE_FAIL_DATA_COUNT)
            .caseSuccessDataCount(UPDATED_CASE_SUCCESS_DATA_COUNT)
            .caseFailDataCount(UPDATED_CASE_FAIL_DATA_COUNT)
            .interfacingSuccessDataCount(UPDATED_INTERFACING_SUCCESS_DATA_COUNT)
            .interfacingFailDataCount(UPDATED_INTERFACING_FAIL_DATA_COUNT)
            .failedDataMemo(UPDATED_FAILED_DATA_MEMO);

        restAdmImportRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmImportRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmImportRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmImportRecord testAdmImportRecord = admImportRecordList.get(admImportRecordList.size() - 1);
        assertThat(testAdmImportRecord.getProcessDate()).isEqualTo(UPDATED_PROCESS_DATE);
        assertThat(testAdmImportRecord.getProcessStartTime()).isEqualTo(UPDATED_PROCESS_START_TIME);
        assertThat(testAdmImportRecord.getProcessEndTime()).isEqualTo(UPDATED_PROCESS_END_TIME);
        assertThat(testAdmImportRecord.getProcessFileName()).isEqualTo(UPDATED_PROCESS_FILE_NAME);
        assertThat(testAdmImportRecord.getOriginalDataCount()).isEqualTo(UPDATED_ORIGINAL_DATA_COUNT);
        assertThat(testAdmImportRecord.getProcessedDataCount()).isEqualTo(UPDATED_PROCESSED_DATA_COUNT);
        assertThat(testAdmImportRecord.getUnprocessedDataCount()).isEqualTo(UPDATED_UNPROCESSED_DATA_COUNT);
        assertThat(testAdmImportRecord.getInsertSuccessDataCount()).isEqualTo(UPDATED_INSERT_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getInsertFailDataCount()).isEqualTo(UPDATED_INSERT_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getUpdateSuccessDataCount()).isEqualTo(UPDATED_UPDATE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getUpdateFailDataCount()).isEqualTo(UPDATED_UPDATE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getDeleteSuccessDataCount()).isEqualTo(UPDATED_DELETE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getDeleteFailDataCount()).isEqualTo(UPDATED_DELETE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getCaseSuccessDataCount()).isEqualTo(UPDATED_CASE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getCaseFailDataCount()).isEqualTo(UPDATED_CASE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getInterfacingSuccessDataCount()).isEqualTo(UPDATED_INTERFACING_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getInterfacingFailDataCount()).isEqualTo(UPDATED_INTERFACING_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getFailedDataMemo()).isEqualTo(UPDATED_FAILED_DATA_MEMO);
    }

    @Test
    @Transactional
    void putNonExistingAdmImportRecord() throws Exception {
        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();
        admImportRecord.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmImportRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admImportRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmImportRecord() throws Exception {
        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();
        admImportRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmImportRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmImportRecord() throws Exception {
        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();
        admImportRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmImportRecordMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmImportRecordWithPatch() throws Exception {
        // Initialize the database
        admImportRecordRepository.saveAndFlush(admImportRecord);

        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();

        // Update the admImportRecord using partial update
        AdmImportRecord partialUpdatedAdmImportRecord = new AdmImportRecord();
        partialUpdatedAdmImportRecord.setId(admImportRecord.getId());

        partialUpdatedAdmImportRecord
            .processDate(UPDATED_PROCESS_DATE)
            .processStartTime(UPDATED_PROCESS_START_TIME)
            .unprocessedDataCount(UPDATED_UNPROCESSED_DATA_COUNT)
            .insertSuccessDataCount(UPDATED_INSERT_SUCCESS_DATA_COUNT)
            .insertFailDataCount(UPDATED_INSERT_FAIL_DATA_COUNT)
            .updateSuccessDataCount(UPDATED_UPDATE_SUCCESS_DATA_COUNT)
            .updateFailDataCount(UPDATED_UPDATE_FAIL_DATA_COUNT)
            .deleteSuccessDataCount(UPDATED_DELETE_SUCCESS_DATA_COUNT)
            .caseFailDataCount(UPDATED_CASE_FAIL_DATA_COUNT);

        restAdmImportRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmImportRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmImportRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmImportRecord testAdmImportRecord = admImportRecordList.get(admImportRecordList.size() - 1);
        assertThat(testAdmImportRecord.getProcessDate()).isEqualTo(UPDATED_PROCESS_DATE);
        assertThat(testAdmImportRecord.getProcessStartTime()).isEqualTo(UPDATED_PROCESS_START_TIME);
        assertThat(testAdmImportRecord.getProcessEndTime()).isEqualTo(DEFAULT_PROCESS_END_TIME);
        assertThat(testAdmImportRecord.getProcessFileName()).isEqualTo(DEFAULT_PROCESS_FILE_NAME);
        assertThat(testAdmImportRecord.getOriginalDataCount()).isEqualByComparingTo(DEFAULT_ORIGINAL_DATA_COUNT);
        assertThat(testAdmImportRecord.getProcessedDataCount()).isEqualByComparingTo(DEFAULT_PROCESSED_DATA_COUNT);
        assertThat(testAdmImportRecord.getUnprocessedDataCount()).isEqualByComparingTo(UPDATED_UNPROCESSED_DATA_COUNT);
        assertThat(testAdmImportRecord.getInsertSuccessDataCount()).isEqualByComparingTo(UPDATED_INSERT_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getInsertFailDataCount()).isEqualByComparingTo(UPDATED_INSERT_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getUpdateSuccessDataCount()).isEqualByComparingTo(UPDATED_UPDATE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getUpdateFailDataCount()).isEqualByComparingTo(UPDATED_UPDATE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getDeleteSuccessDataCount()).isEqualByComparingTo(UPDATED_DELETE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getDeleteFailDataCount()).isEqualByComparingTo(DEFAULT_DELETE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getCaseSuccessDataCount()).isEqualByComparingTo(DEFAULT_CASE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getCaseFailDataCount()).isEqualByComparingTo(UPDATED_CASE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getInterfacingSuccessDataCount()).isEqualByComparingTo(DEFAULT_INTERFACING_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getInterfacingFailDataCount()).isEqualByComparingTo(DEFAULT_INTERFACING_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getFailedDataMemo()).isEqualTo(DEFAULT_FAILED_DATA_MEMO);
    }

    @Test
    @Transactional
    void fullUpdateAdmImportRecordWithPatch() throws Exception {
        // Initialize the database
        admImportRecordRepository.saveAndFlush(admImportRecord);

        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();

        // Update the admImportRecord using partial update
        AdmImportRecord partialUpdatedAdmImportRecord = new AdmImportRecord();
        partialUpdatedAdmImportRecord.setId(admImportRecord.getId());

        partialUpdatedAdmImportRecord
            .processDate(UPDATED_PROCESS_DATE)
            .processStartTime(UPDATED_PROCESS_START_TIME)
            .processEndTime(UPDATED_PROCESS_END_TIME)
            .processFileName(UPDATED_PROCESS_FILE_NAME)
            .originalDataCount(UPDATED_ORIGINAL_DATA_COUNT)
            .processedDataCount(UPDATED_PROCESSED_DATA_COUNT)
            .unprocessedDataCount(UPDATED_UNPROCESSED_DATA_COUNT)
            .insertSuccessDataCount(UPDATED_INSERT_SUCCESS_DATA_COUNT)
            .insertFailDataCount(UPDATED_INSERT_FAIL_DATA_COUNT)
            .updateSuccessDataCount(UPDATED_UPDATE_SUCCESS_DATA_COUNT)
            .updateFailDataCount(UPDATED_UPDATE_FAIL_DATA_COUNT)
            .deleteSuccessDataCount(UPDATED_DELETE_SUCCESS_DATA_COUNT)
            .deleteFailDataCount(UPDATED_DELETE_FAIL_DATA_COUNT)
            .caseSuccessDataCount(UPDATED_CASE_SUCCESS_DATA_COUNT)
            .caseFailDataCount(UPDATED_CASE_FAIL_DATA_COUNT)
            .interfacingSuccessDataCount(UPDATED_INTERFACING_SUCCESS_DATA_COUNT)
            .interfacingFailDataCount(UPDATED_INTERFACING_FAIL_DATA_COUNT)
            .failedDataMemo(UPDATED_FAILED_DATA_MEMO);

        restAdmImportRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmImportRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmImportRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmImportRecord testAdmImportRecord = admImportRecordList.get(admImportRecordList.size() - 1);
        assertThat(testAdmImportRecord.getProcessDate()).isEqualTo(UPDATED_PROCESS_DATE);
        assertThat(testAdmImportRecord.getProcessStartTime()).isEqualTo(UPDATED_PROCESS_START_TIME);
        assertThat(testAdmImportRecord.getProcessEndTime()).isEqualTo(UPDATED_PROCESS_END_TIME);
        assertThat(testAdmImportRecord.getProcessFileName()).isEqualTo(UPDATED_PROCESS_FILE_NAME);
        assertThat(testAdmImportRecord.getOriginalDataCount()).isEqualByComparingTo(UPDATED_ORIGINAL_DATA_COUNT);
        assertThat(testAdmImportRecord.getProcessedDataCount()).isEqualByComparingTo(UPDATED_PROCESSED_DATA_COUNT);
        assertThat(testAdmImportRecord.getUnprocessedDataCount()).isEqualByComparingTo(UPDATED_UNPROCESSED_DATA_COUNT);
        assertThat(testAdmImportRecord.getInsertSuccessDataCount()).isEqualByComparingTo(UPDATED_INSERT_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getInsertFailDataCount()).isEqualByComparingTo(UPDATED_INSERT_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getUpdateSuccessDataCount()).isEqualByComparingTo(UPDATED_UPDATE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getUpdateFailDataCount()).isEqualByComparingTo(UPDATED_UPDATE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getDeleteSuccessDataCount()).isEqualByComparingTo(UPDATED_DELETE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getDeleteFailDataCount()).isEqualByComparingTo(UPDATED_DELETE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getCaseSuccessDataCount()).isEqualByComparingTo(UPDATED_CASE_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getCaseFailDataCount()).isEqualByComparingTo(UPDATED_CASE_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getInterfacingSuccessDataCount()).isEqualByComparingTo(UPDATED_INTERFACING_SUCCESS_DATA_COUNT);
        assertThat(testAdmImportRecord.getInterfacingFailDataCount()).isEqualByComparingTo(UPDATED_INTERFACING_FAIL_DATA_COUNT);
        assertThat(testAdmImportRecord.getFailedDataMemo()).isEqualTo(UPDATED_FAILED_DATA_MEMO);
    }

    @Test
    @Transactional
    void patchNonExistingAdmImportRecord() throws Exception {
        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();
        admImportRecord.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmImportRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admImportRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmImportRecord() throws Exception {
        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();
        admImportRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmImportRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmImportRecord() throws Exception {
        int databaseSizeBeforeUpdate = admImportRecordRepository.findAll().size();
        admImportRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmImportRecordMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admImportRecord))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmImportRecord in the database
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmImportRecord() throws Exception {
        // Initialize the database
        admImportRecordRepository.saveAndFlush(admImportRecord);

        int databaseSizeBeforeDelete = admImportRecordRepository.findAll().size();

        // Delete the admImportRecord
        restAdmImportRecordMockMvc
            .perform(delete(ENTITY_API_URL_ID, admImportRecord.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmImportRecord> admImportRecordList = admImportRecordRepository.findAll();
        assertThat(admImportRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
