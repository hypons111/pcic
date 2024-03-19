package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmBatchRecord;
import gov.pcc.pwc.repository.AdmBatchRecordRepository;
import gov.pcc.pwc.pwb.rest.AdmBatchRecordResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdmBatchRecordResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmBatchRecordResourceIT {

    private static final String DEFAULT_BATCH_SERVICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_SERVICE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_SERVICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_SERVICE_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_SERVICE_CATEGORY = "BBBBBBBBBB";

    private static final Instant DEFAULT_TRIGGER_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRIGGER_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FINISH_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FINISH_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RESULT = "A";
    private static final String UPDATED_RESULT = "B";

    private static final String ENTITY_API_URL = "/api/adm-batch-records";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmBatchRecordRepository admBatchRecordRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmBatchRecordMockMvc;

    private AdmBatchRecord admBatchRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmBatchRecord createEntity(EntityManager em) {
        AdmBatchRecord admBatchRecord = new AdmBatchRecord()
            .batchServiceNo(DEFAULT_BATCH_SERVICE_NO)
            .batchServiceName(DEFAULT_BATCH_SERVICE_NAME)
            .batchServiceCategory(DEFAULT_BATCH_SERVICE_CATEGORY)
            .triggerTime(DEFAULT_TRIGGER_TIME)
            .finishTime(DEFAULT_FINISH_TIME)
            .result(DEFAULT_RESULT);
        return admBatchRecord;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmBatchRecord createUpdatedEntity(EntityManager em) {
        AdmBatchRecord admBatchRecord = new AdmBatchRecord()
            .batchServiceNo(UPDATED_BATCH_SERVICE_NO)
            .batchServiceName(UPDATED_BATCH_SERVICE_NAME)
            .batchServiceCategory(UPDATED_BATCH_SERVICE_CATEGORY)
            .triggerTime(UPDATED_TRIGGER_TIME)
            .finishTime(UPDATED_FINISH_TIME)
            .result(UPDATED_RESULT);
        return admBatchRecord;
    }

    @BeforeEach
    public void initTest() {
        admBatchRecord = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmBatchRecord() throws Exception {
        int databaseSizeBeforeCreate = admBatchRecordRepository.findAll().size();
        // Create the AdmBatchRecord
        restAdmBatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isCreated());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeCreate + 1);
        AdmBatchRecord testAdmBatchRecord = admBatchRecordList.get(admBatchRecordList.size() - 1);
        assertThat(testAdmBatchRecord.getBatchServiceNo()).isEqualTo(DEFAULT_BATCH_SERVICE_NO);
        assertThat(testAdmBatchRecord.getBatchServiceName()).isEqualTo(DEFAULT_BATCH_SERVICE_NAME);
        assertThat(testAdmBatchRecord.getBatchServiceCategory()).isEqualTo(DEFAULT_BATCH_SERVICE_CATEGORY);
        assertThat(testAdmBatchRecord.getTriggerTime()).isEqualTo(DEFAULT_TRIGGER_TIME);
        assertThat(testAdmBatchRecord.getFinishTime()).isEqualTo(DEFAULT_FINISH_TIME);
        assertThat(testAdmBatchRecord.getResult()).isEqualTo(DEFAULT_RESULT);
    }

    @Test
    @Transactional
    void createAdmBatchRecordWithExistingId() throws Exception {
        // Create the AdmBatchRecord with an existing ID
        admBatchRecord.setId(1L);

        int databaseSizeBeforeCreate = admBatchRecordRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmBatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBatchServiceNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchRecordRepository.findAll().size();
        // set the field null
        admBatchRecord.setBatchServiceNo(null);

        // Create the AdmBatchRecord, which fails.

        restAdmBatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBatchServiceNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchRecordRepository.findAll().size();
        // set the field null
        admBatchRecord.setBatchServiceName(null);

        // Create the AdmBatchRecord, which fails.

        restAdmBatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBatchServiceCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchRecordRepository.findAll().size();
        // set the field null
        admBatchRecord.setBatchServiceCategory(null);

        // Create the AdmBatchRecord, which fails.

        restAdmBatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTriggerTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchRecordRepository.findAll().size();
        // set the field null
        admBatchRecord.setTriggerTime(null);

        // Create the AdmBatchRecord, which fails.

        restAdmBatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFinishTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchRecordRepository.findAll().size();
        // set the field null
        admBatchRecord.setFinishTime(null);

        // Create the AdmBatchRecord, which fails.

        restAdmBatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = admBatchRecordRepository.findAll().size();
        // set the field null
        admBatchRecord.setResult(null);

        // Create the AdmBatchRecord, which fails.

        restAdmBatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmBatchRecords() throws Exception {
        // Initialize the database
        admBatchRecordRepository.saveAndFlush(admBatchRecord);

        // Get all the admBatchRecordList
        restAdmBatchRecordMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admBatchRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].batchServiceNo").value(hasItem(DEFAULT_BATCH_SERVICE_NO)))
            .andExpect(jsonPath("$.[*].batchServiceName").value(hasItem(DEFAULT_BATCH_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].batchServiceCategory").value(hasItem(DEFAULT_BATCH_SERVICE_CATEGORY)))
            .andExpect(jsonPath("$.[*].triggerTime").value(hasItem(DEFAULT_TRIGGER_TIME.toString())))
            .andExpect(jsonPath("$.[*].finishTime").value(hasItem(DEFAULT_FINISH_TIME.toString())))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)));
    }

    @Test
    @Transactional
    void getAdmBatchRecord() throws Exception {
        // Initialize the database
        admBatchRecordRepository.saveAndFlush(admBatchRecord);

        // Get the admBatchRecord
        restAdmBatchRecordMockMvc
            .perform(get(ENTITY_API_URL_ID, admBatchRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admBatchRecord.getId().intValue()))
            .andExpect(jsonPath("$.batchServiceNo").value(DEFAULT_BATCH_SERVICE_NO))
            .andExpect(jsonPath("$.batchServiceName").value(DEFAULT_BATCH_SERVICE_NAME))
            .andExpect(jsonPath("$.batchServiceCategory").value(DEFAULT_BATCH_SERVICE_CATEGORY))
            .andExpect(jsonPath("$.triggerTime").value(DEFAULT_TRIGGER_TIME.toString()))
            .andExpect(jsonPath("$.finishTime").value(DEFAULT_FINISH_TIME.toString()))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT));
    }

    @Test
    @Transactional
    void getNonExistingAdmBatchRecord() throws Exception {
        // Get the admBatchRecord
        restAdmBatchRecordMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmBatchRecord() throws Exception {
        // Initialize the database
        admBatchRecordRepository.saveAndFlush(admBatchRecord);

        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();

        // Update the admBatchRecord
        AdmBatchRecord updatedAdmBatchRecord = admBatchRecordRepository.findById(admBatchRecord.getId()).get();
        // Disconnect from session so that the updates on updatedAdmBatchRecord are not directly saved in db
        em.detach(updatedAdmBatchRecord);
        updatedAdmBatchRecord
            .batchServiceNo(UPDATED_BATCH_SERVICE_NO)
            .batchServiceName(UPDATED_BATCH_SERVICE_NAME)
            .batchServiceCategory(UPDATED_BATCH_SERVICE_CATEGORY)
            .triggerTime(UPDATED_TRIGGER_TIME)
            .finishTime(UPDATED_FINISH_TIME)
            .result(UPDATED_RESULT);

        restAdmBatchRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmBatchRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmBatchRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmBatchRecord testAdmBatchRecord = admBatchRecordList.get(admBatchRecordList.size() - 1);
        assertThat(testAdmBatchRecord.getBatchServiceNo()).isEqualTo(UPDATED_BATCH_SERVICE_NO);
        assertThat(testAdmBatchRecord.getBatchServiceName()).isEqualTo(UPDATED_BATCH_SERVICE_NAME);
        assertThat(testAdmBatchRecord.getBatchServiceCategory()).isEqualTo(UPDATED_BATCH_SERVICE_CATEGORY);
        assertThat(testAdmBatchRecord.getTriggerTime()).isEqualTo(UPDATED_TRIGGER_TIME);
        assertThat(testAdmBatchRecord.getFinishTime()).isEqualTo(UPDATED_FINISH_TIME);
        assertThat(testAdmBatchRecord.getResult()).isEqualTo(UPDATED_RESULT);
    }

    @Test
    @Transactional
    void putNonExistingAdmBatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();
        admBatchRecord.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmBatchRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admBatchRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmBatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();
        admBatchRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmBatchRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmBatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();
        admBatchRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmBatchRecordMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admBatchRecord)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmBatchRecordWithPatch() throws Exception {
        // Initialize the database
        admBatchRecordRepository.saveAndFlush(admBatchRecord);

        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();

        // Update the admBatchRecord using partial update
        AdmBatchRecord partialUpdatedAdmBatchRecord = new AdmBatchRecord();
        partialUpdatedAdmBatchRecord.setId(admBatchRecord.getId());

        partialUpdatedAdmBatchRecord
            .batchServiceNo(UPDATED_BATCH_SERVICE_NO)
            .batchServiceCategory(UPDATED_BATCH_SERVICE_CATEGORY)
            .triggerTime(UPDATED_TRIGGER_TIME)
            .finishTime(UPDATED_FINISH_TIME)
            .result(UPDATED_RESULT);

        restAdmBatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmBatchRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmBatchRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmBatchRecord testAdmBatchRecord = admBatchRecordList.get(admBatchRecordList.size() - 1);
        assertThat(testAdmBatchRecord.getBatchServiceNo()).isEqualTo(UPDATED_BATCH_SERVICE_NO);
        assertThat(testAdmBatchRecord.getBatchServiceName()).isEqualTo(DEFAULT_BATCH_SERVICE_NAME);
        assertThat(testAdmBatchRecord.getBatchServiceCategory()).isEqualTo(UPDATED_BATCH_SERVICE_CATEGORY);
        assertThat(testAdmBatchRecord.getTriggerTime()).isEqualTo(UPDATED_TRIGGER_TIME);
        assertThat(testAdmBatchRecord.getFinishTime()).isEqualTo(UPDATED_FINISH_TIME);
        assertThat(testAdmBatchRecord.getResult()).isEqualTo(UPDATED_RESULT);
    }

    @Test
    @Transactional
    void fullUpdateAdmBatchRecordWithPatch() throws Exception {
        // Initialize the database
        admBatchRecordRepository.saveAndFlush(admBatchRecord);

        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();

        // Update the admBatchRecord using partial update
        AdmBatchRecord partialUpdatedAdmBatchRecord = new AdmBatchRecord();
        partialUpdatedAdmBatchRecord.setId(admBatchRecord.getId());

        partialUpdatedAdmBatchRecord
            .batchServiceNo(UPDATED_BATCH_SERVICE_NO)
            .batchServiceName(UPDATED_BATCH_SERVICE_NAME)
            .batchServiceCategory(UPDATED_BATCH_SERVICE_CATEGORY)
            .triggerTime(UPDATED_TRIGGER_TIME)
            .finishTime(UPDATED_FINISH_TIME)
            .result(UPDATED_RESULT);

        restAdmBatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmBatchRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmBatchRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmBatchRecord testAdmBatchRecord = admBatchRecordList.get(admBatchRecordList.size() - 1);
        assertThat(testAdmBatchRecord.getBatchServiceNo()).isEqualTo(UPDATED_BATCH_SERVICE_NO);
        assertThat(testAdmBatchRecord.getBatchServiceName()).isEqualTo(UPDATED_BATCH_SERVICE_NAME);
        assertThat(testAdmBatchRecord.getBatchServiceCategory()).isEqualTo(UPDATED_BATCH_SERVICE_CATEGORY);
        assertThat(testAdmBatchRecord.getTriggerTime()).isEqualTo(UPDATED_TRIGGER_TIME);
        assertThat(testAdmBatchRecord.getFinishTime()).isEqualTo(UPDATED_FINISH_TIME);
        assertThat(testAdmBatchRecord.getResult()).isEqualTo(UPDATED_RESULT);
    }

    @Test
    @Transactional
    void patchNonExistingAdmBatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();
        admBatchRecord.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmBatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admBatchRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmBatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();
        admBatchRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmBatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmBatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = admBatchRecordRepository.findAll().size();
        admBatchRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmBatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admBatchRecord))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmBatchRecord in the database
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmBatchRecord() throws Exception {
        // Initialize the database
        admBatchRecordRepository.saveAndFlush(admBatchRecord);

        int databaseSizeBeforeDelete = admBatchRecordRepository.findAll().size();

        // Delete the admBatchRecord
        restAdmBatchRecordMockMvc
            .perform(delete(ENTITY_API_URL_ID, admBatchRecord.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmBatchRecord> admBatchRecordList = admBatchRecordRepository.findAll();
        assertThat(admBatchRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
