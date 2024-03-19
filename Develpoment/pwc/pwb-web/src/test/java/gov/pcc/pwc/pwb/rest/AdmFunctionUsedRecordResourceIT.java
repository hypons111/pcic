package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmFunctionUsedRecord;
import gov.pcc.pwc.repository.AdmFunctionUsedRecordRepository;
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
 * Integration tests for the {@link AdmFunctionUsedRecordResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmFunctionUsedRecordResourceIT {

    private static final String DEFAULT_SYS_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SYS_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_EXTEND_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_EXTEND_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_CONTENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER_SYS = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER_SYS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/adm-function-used-records";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmFunctionUsedRecordRepository admFunctionUsedRecordRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmFunctionUsedRecordMockMvc;

    private AdmFunctionUsedRecord admFunctionUsedRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFunctionUsedRecord createEntity(EntityManager em) {
        AdmFunctionUsedRecord admFunctionUsedRecord = new AdmFunctionUsedRecord()
            .sysCategory(DEFAULT_SYS_CATEGORY)
            .functionId(DEFAULT_FUNCTION_ID)
            .functionExtendId(DEFAULT_FUNCTION_EXTEND_ID)
            .recordContent(DEFAULT_RECORD_CONTENT)
            .createTime(DEFAULT_CREATE_TIME)
            .createUserSys(DEFAULT_CREATE_USER_SYS);
        return admFunctionUsedRecord;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFunctionUsedRecord createUpdatedEntity(EntityManager em) {
        AdmFunctionUsedRecord admFunctionUsedRecord = new AdmFunctionUsedRecord()
            .sysCategory(UPDATED_SYS_CATEGORY)
            .functionId(UPDATED_FUNCTION_ID)
            .functionExtendId(UPDATED_FUNCTION_EXTEND_ID)
            .recordContent(UPDATED_RECORD_CONTENT)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);
        return admFunctionUsedRecord;
    }

    @BeforeEach
    public void initTest() {
        admFunctionUsedRecord = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmFunctionUsedRecord() throws Exception {
        int databaseSizeBeforeCreate = admFunctionUsedRecordRepository.findAll().size();
        // Create the AdmFunctionUsedRecord
        restAdmFunctionUsedRecordMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isCreated());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeCreate + 1);
        AdmFunctionUsedRecord testAdmFunctionUsedRecord = admFunctionUsedRecordList.get(admFunctionUsedRecordList.size() - 1);
        assertThat(testAdmFunctionUsedRecord.getSysCategory()).isEqualTo(DEFAULT_SYS_CATEGORY);
        assertThat(testAdmFunctionUsedRecord.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testAdmFunctionUsedRecord.getFunctionExtendId()).isEqualTo(DEFAULT_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionUsedRecord.getRecordContent()).isEqualTo(DEFAULT_RECORD_CONTENT);
        assertThat(testAdmFunctionUsedRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmFunctionUsedRecord.getCreateUserSys()).isEqualTo(DEFAULT_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void createAdmFunctionUsedRecordWithExistingId() throws Exception {
        // Create the AdmFunctionUsedRecord with an existing ID
        admFunctionUsedRecord.setId(1L);

        int databaseSizeBeforeCreate = admFunctionUsedRecordRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmFunctionUsedRecordMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSysCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionUsedRecordRepository.findAll().size();
        // set the field null
        admFunctionUsedRecord.setSysCategory(null);

        // Create the AdmFunctionUsedRecord, which fails.

        restAdmFunctionUsedRecordMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionUsedRecordRepository.findAll().size();
        // set the field null
        admFunctionUsedRecord.setFunctionId(null);

        // Create the AdmFunctionUsedRecord, which fails.

        restAdmFunctionUsedRecordMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionExtendIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionUsedRecordRepository.findAll().size();
        // set the field null
        admFunctionUsedRecord.setFunctionExtendId(null);

        // Create the AdmFunctionUsedRecord, which fails.

        restAdmFunctionUsedRecordMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRecordContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionUsedRecordRepository.findAll().size();
        // set the field null
        admFunctionUsedRecord.setRecordContent(null);

        // Create the AdmFunctionUsedRecord, which fails.

        restAdmFunctionUsedRecordMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionUsedRecordRepository.findAll().size();
        // set the field null
        admFunctionUsedRecord.setCreateTime(null);

        // Create the AdmFunctionUsedRecord, which fails.

        restAdmFunctionUsedRecordMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserSysIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionUsedRecordRepository.findAll().size();
        // set the field null
        admFunctionUsedRecord.setCreateUserSys(null);

        // Create the AdmFunctionUsedRecord, which fails.

        restAdmFunctionUsedRecordMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmFunctionUsedRecords() throws Exception {
        // Initialize the database
        admFunctionUsedRecordRepository.saveAndFlush(admFunctionUsedRecord);

        // Get all the admFunctionUsedRecordList
        restAdmFunctionUsedRecordMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admFunctionUsedRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysCategory").value(hasItem(DEFAULT_SYS_CATEGORY)))
            .andExpect(jsonPath("$.[*].functionId").value(hasItem(DEFAULT_FUNCTION_ID)))
            .andExpect(jsonPath("$.[*].functionExtendId").value(hasItem(DEFAULT_FUNCTION_EXTEND_ID)))
            .andExpect(jsonPath("$.[*].recordContent").value(hasItem(DEFAULT_RECORD_CONTENT)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUserSys").value(hasItem(DEFAULT_CREATE_USER_SYS)));
    }

    @Test
    @Transactional
    void getAdmFunctionUsedRecord() throws Exception {
        // Initialize the database
        admFunctionUsedRecordRepository.saveAndFlush(admFunctionUsedRecord);

        // Get the admFunctionUsedRecord
        restAdmFunctionUsedRecordMockMvc
            .perform(get(ENTITY_API_URL_ID, admFunctionUsedRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admFunctionUsedRecord.getId().intValue()))
            .andExpect(jsonPath("$.sysCategory").value(DEFAULT_SYS_CATEGORY))
            .andExpect(jsonPath("$.functionId").value(DEFAULT_FUNCTION_ID))
            .andExpect(jsonPath("$.functionExtendId").value(DEFAULT_FUNCTION_EXTEND_ID))
            .andExpect(jsonPath("$.recordContent").value(DEFAULT_RECORD_CONTENT))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUserSys").value(DEFAULT_CREATE_USER_SYS));
    }

    @Test
    @Transactional
    void getNonExistingAdmFunctionUsedRecord() throws Exception {
        // Get the admFunctionUsedRecord
        restAdmFunctionUsedRecordMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmFunctionUsedRecord() throws Exception {
        // Initialize the database
        admFunctionUsedRecordRepository.saveAndFlush(admFunctionUsedRecord);

        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();

        // Update the admFunctionUsedRecord
        AdmFunctionUsedRecord updatedAdmFunctionUsedRecord = admFunctionUsedRecordRepository.findById(admFunctionUsedRecord.getId()).get();
        // Disconnect from session so that the updates on updatedAdmFunctionUsedRecord are not directly saved in db
        em.detach(updatedAdmFunctionUsedRecord);
        updatedAdmFunctionUsedRecord
            .sysCategory(UPDATED_SYS_CATEGORY)
            .functionId(UPDATED_FUNCTION_ID)
            .functionExtendId(UPDATED_FUNCTION_EXTEND_ID)
            .recordContent(UPDATED_RECORD_CONTENT)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);

        restAdmFunctionUsedRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmFunctionUsedRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmFunctionUsedRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionUsedRecord testAdmFunctionUsedRecord = admFunctionUsedRecordList.get(admFunctionUsedRecordList.size() - 1);
        assertThat(testAdmFunctionUsedRecord.getSysCategory()).isEqualTo(UPDATED_SYS_CATEGORY);
        assertThat(testAdmFunctionUsedRecord.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmFunctionUsedRecord.getFunctionExtendId()).isEqualTo(UPDATED_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionUsedRecord.getRecordContent()).isEqualTo(UPDATED_RECORD_CONTENT);
        assertThat(testAdmFunctionUsedRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmFunctionUsedRecord.getCreateUserSys()).isEqualTo(UPDATED_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void putNonExistingAdmFunctionUsedRecord() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();
        admFunctionUsedRecord.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFunctionUsedRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admFunctionUsedRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmFunctionUsedRecord() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();
        admFunctionUsedRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionUsedRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmFunctionUsedRecord() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();
        admFunctionUsedRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionUsedRecordMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmFunctionUsedRecordWithPatch() throws Exception {
        // Initialize the database
        admFunctionUsedRecordRepository.saveAndFlush(admFunctionUsedRecord);

        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();

        // Update the admFunctionUsedRecord using partial update
        AdmFunctionUsedRecord partialUpdatedAdmFunctionUsedRecord = new AdmFunctionUsedRecord();
        partialUpdatedAdmFunctionUsedRecord.setId(admFunctionUsedRecord.getId());

        partialUpdatedAdmFunctionUsedRecord
            .functionId(UPDATED_FUNCTION_ID)
            .recordContent(UPDATED_RECORD_CONTENT)
            .createUserSys(UPDATED_CREATE_USER_SYS);

        restAdmFunctionUsedRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFunctionUsedRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFunctionUsedRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionUsedRecord testAdmFunctionUsedRecord = admFunctionUsedRecordList.get(admFunctionUsedRecordList.size() - 1);
        assertThat(testAdmFunctionUsedRecord.getSysCategory()).isEqualTo(DEFAULT_SYS_CATEGORY);
        assertThat(testAdmFunctionUsedRecord.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmFunctionUsedRecord.getFunctionExtendId()).isEqualTo(DEFAULT_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionUsedRecord.getRecordContent()).isEqualTo(UPDATED_RECORD_CONTENT);
        assertThat(testAdmFunctionUsedRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmFunctionUsedRecord.getCreateUserSys()).isEqualTo(UPDATED_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void fullUpdateAdmFunctionUsedRecordWithPatch() throws Exception {
        // Initialize the database
        admFunctionUsedRecordRepository.saveAndFlush(admFunctionUsedRecord);

        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();

        // Update the admFunctionUsedRecord using partial update
        AdmFunctionUsedRecord partialUpdatedAdmFunctionUsedRecord = new AdmFunctionUsedRecord();
        partialUpdatedAdmFunctionUsedRecord.setId(admFunctionUsedRecord.getId());

        partialUpdatedAdmFunctionUsedRecord
            .sysCategory(UPDATED_SYS_CATEGORY)
            .functionId(UPDATED_FUNCTION_ID)
            .functionExtendId(UPDATED_FUNCTION_EXTEND_ID)
            .recordContent(UPDATED_RECORD_CONTENT)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);

        restAdmFunctionUsedRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFunctionUsedRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFunctionUsedRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionUsedRecord testAdmFunctionUsedRecord = admFunctionUsedRecordList.get(admFunctionUsedRecordList.size() - 1);
        assertThat(testAdmFunctionUsedRecord.getSysCategory()).isEqualTo(UPDATED_SYS_CATEGORY);
        assertThat(testAdmFunctionUsedRecord.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmFunctionUsedRecord.getFunctionExtendId()).isEqualTo(UPDATED_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionUsedRecord.getRecordContent()).isEqualTo(UPDATED_RECORD_CONTENT);
        assertThat(testAdmFunctionUsedRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmFunctionUsedRecord.getCreateUserSys()).isEqualTo(UPDATED_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void patchNonExistingAdmFunctionUsedRecord() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();
        admFunctionUsedRecord.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFunctionUsedRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admFunctionUsedRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmFunctionUsedRecord() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();
        admFunctionUsedRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionUsedRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmFunctionUsedRecord() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionUsedRecordRepository.findAll().size();
        admFunctionUsedRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionUsedRecordMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionUsedRecord))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFunctionUsedRecord in the database
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmFunctionUsedRecord() throws Exception {
        // Initialize the database
        admFunctionUsedRecordRepository.saveAndFlush(admFunctionUsedRecord);

        int databaseSizeBeforeDelete = admFunctionUsedRecordRepository.findAll().size();

        // Delete the admFunctionUsedRecord
        restAdmFunctionUsedRecordMockMvc
            .perform(delete(ENTITY_API_URL_ID, admFunctionUsedRecord.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmFunctionUsedRecord> admFunctionUsedRecordList = admFunctionUsedRecordRepository.findAll();
        assertThat(admFunctionUsedRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
