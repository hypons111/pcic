package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmCertRecord;
import gov.pcc.pwc.repository.AdmCertRecordRepository;
import gov.pcc.pwc.pwb.rest.AdmCertRecordResource;
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
 * Integration tests for the {@link AdmCertRecordResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmCertRecordResourceIT {

    private static final String DEFAULT_FUNCTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CERT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CERT_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_CERT_SN = "AAAAAAAAAA";
    private static final String UPDATED_CERT_SN = "BBBBBBBBBB";

    private static final String DEFAULT_SIGN_OPS = "AAAAAAAAAA";
    private static final String UPDATED_SIGN_OPS = "BBBBBBBBBB";

    private static final String DEFAULT_ENC_OPS = "AAAAAAAAAA";
    private static final String UPDATED_ENC_OPS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER_SYS = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER_SYS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/adm-cert-records";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmCertRecordRepository admCertRecordRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmCertRecordMockMvc;

    private AdmCertRecord admCertRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmCertRecord createEntity(EntityManager em) {
        AdmCertRecord admCertRecord = new AdmCertRecord()
            .functionId(DEFAULT_FUNCTION_ID)
            .certCategory(DEFAULT_CERT_CATEGORY)
            .certSn(DEFAULT_CERT_SN)
            .signOps(DEFAULT_SIGN_OPS)
            .encOps(DEFAULT_ENC_OPS)
            .createTime(DEFAULT_CREATE_TIME)
            .createUserSys(DEFAULT_CREATE_USER_SYS);
        return admCertRecord;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmCertRecord createUpdatedEntity(EntityManager em) {
        AdmCertRecord admCertRecord = new AdmCertRecord()
            .functionId(UPDATED_FUNCTION_ID)
            .certCategory(UPDATED_CERT_CATEGORY)
            .certSn(UPDATED_CERT_SN)
            .signOps(UPDATED_SIGN_OPS)
            .encOps(UPDATED_ENC_OPS)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);
        return admCertRecord;
    }

    @BeforeEach
    public void initTest() {
        admCertRecord = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmCertRecord() throws Exception {
        int databaseSizeBeforeCreate = admCertRecordRepository.findAll().size();
        // Create the AdmCertRecord
        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isCreated());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeCreate + 1);
        AdmCertRecord testAdmCertRecord = admCertRecordList.get(admCertRecordList.size() - 1);
        assertThat(testAdmCertRecord.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testAdmCertRecord.getCertCategory()).isEqualTo(DEFAULT_CERT_CATEGORY);
        assertThat(testAdmCertRecord.getCertSn()).isEqualTo(DEFAULT_CERT_SN);
        assertThat(testAdmCertRecord.getSignOps()).isEqualTo(DEFAULT_SIGN_OPS);
        assertThat(testAdmCertRecord.getEncOps()).isEqualTo(DEFAULT_ENC_OPS);
        assertThat(testAdmCertRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmCertRecord.getCreateUserSys()).isEqualTo(DEFAULT_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void createAdmCertRecordWithExistingId() throws Exception {
        // Create the AdmCertRecord with an existing ID
        admCertRecord.setId(1L);

        int databaseSizeBeforeCreate = admCertRecordRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isBadRequest());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFunctionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCertRecordRepository.findAll().size();
        // set the field null
        admCertRecord.setFunctionId(null);

        // Create the AdmCertRecord, which fails.

        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isBadRequest());

        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCertCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCertRecordRepository.findAll().size();
        // set the field null
        admCertRecord.setCertCategory(null);

        // Create the AdmCertRecord, which fails.

        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isBadRequest());

        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCertSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCertRecordRepository.findAll().size();
        // set the field null
        admCertRecord.setCertSn(null);

        // Create the AdmCertRecord, which fails.

        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isBadRequest());

        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSignOpsIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCertRecordRepository.findAll().size();
        // set the field null
        admCertRecord.setSignOps(null);

        // Create the AdmCertRecord, which fails.

        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isBadRequest());

        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEncOpsIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCertRecordRepository.findAll().size();
        // set the field null
        admCertRecord.setEncOps(null);

        // Create the AdmCertRecord, which fails.

        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isBadRequest());

        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCertRecordRepository.findAll().size();
        // set the field null
        admCertRecord.setCreateTime(null);

        // Create the AdmCertRecord, which fails.

        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isBadRequest());

        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserSysIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCertRecordRepository.findAll().size();
        // set the field null
        admCertRecord.setCreateUserSys(null);

        // Create the AdmCertRecord, which fails.

        restAdmCertRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isBadRequest());

        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmCertRecords() throws Exception {
        // Initialize the database
        admCertRecordRepository.saveAndFlush(admCertRecord);

        // Get all the admCertRecordList
        restAdmCertRecordMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admCertRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].functionId").value(hasItem(DEFAULT_FUNCTION_ID)))
            .andExpect(jsonPath("$.[*].certCategory").value(hasItem(DEFAULT_CERT_CATEGORY)))
            .andExpect(jsonPath("$.[*].certSn").value(hasItem(DEFAULT_CERT_SN)))
            .andExpect(jsonPath("$.[*].signOps").value(hasItem(DEFAULT_SIGN_OPS)))
            .andExpect(jsonPath("$.[*].encOps").value(hasItem(DEFAULT_ENC_OPS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUserSys").value(hasItem(DEFAULT_CREATE_USER_SYS)));
    }

    @Test
    @Transactional
    void getAdmCertRecord() throws Exception {
        // Initialize the database
        admCertRecordRepository.saveAndFlush(admCertRecord);

        // Get the admCertRecord
        restAdmCertRecordMockMvc
            .perform(get(ENTITY_API_URL_ID, admCertRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admCertRecord.getId().intValue()))
            .andExpect(jsonPath("$.functionId").value(DEFAULT_FUNCTION_ID))
            .andExpect(jsonPath("$.certCategory").value(DEFAULT_CERT_CATEGORY))
            .andExpect(jsonPath("$.certSn").value(DEFAULT_CERT_SN))
            .andExpect(jsonPath("$.signOps").value(DEFAULT_SIGN_OPS))
            .andExpect(jsonPath("$.encOps").value(DEFAULT_ENC_OPS))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUserSys").value(DEFAULT_CREATE_USER_SYS));
    }

    @Test
    @Transactional
    void getNonExistingAdmCertRecord() throws Exception {
        // Get the admCertRecord
        restAdmCertRecordMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmCertRecord() throws Exception {
        // Initialize the database
        admCertRecordRepository.saveAndFlush(admCertRecord);

        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();

        // Update the admCertRecord
        AdmCertRecord updatedAdmCertRecord = admCertRecordRepository.findById(admCertRecord.getId()).get();
        // Disconnect from session so that the updates on updatedAdmCertRecord are not directly saved in db
        em.detach(updatedAdmCertRecord);
        updatedAdmCertRecord
            .functionId(UPDATED_FUNCTION_ID)
            .certCategory(UPDATED_CERT_CATEGORY)
            .certSn(UPDATED_CERT_SN)
            .signOps(UPDATED_SIGN_OPS)
            .encOps(UPDATED_ENC_OPS)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);

        restAdmCertRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmCertRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmCertRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmCertRecord testAdmCertRecord = admCertRecordList.get(admCertRecordList.size() - 1);
        assertThat(testAdmCertRecord.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmCertRecord.getCertCategory()).isEqualTo(UPDATED_CERT_CATEGORY);
        assertThat(testAdmCertRecord.getCertSn()).isEqualTo(UPDATED_CERT_SN);
        assertThat(testAdmCertRecord.getSignOps()).isEqualTo(UPDATED_SIGN_OPS);
        assertThat(testAdmCertRecord.getEncOps()).isEqualTo(UPDATED_ENC_OPS);
        assertThat(testAdmCertRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmCertRecord.getCreateUserSys()).isEqualTo(UPDATED_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void putNonExistingAdmCertRecord() throws Exception {
        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();
        admCertRecord.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmCertRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admCertRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admCertRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmCertRecord() throws Exception {
        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();
        admCertRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmCertRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admCertRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmCertRecord() throws Exception {
        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();
        admCertRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmCertRecordMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCertRecord)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmCertRecordWithPatch() throws Exception {
        // Initialize the database
        admCertRecordRepository.saveAndFlush(admCertRecord);

        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();

        // Update the admCertRecord using partial update
        AdmCertRecord partialUpdatedAdmCertRecord = new AdmCertRecord();
        partialUpdatedAdmCertRecord.setId(admCertRecord.getId());

        partialUpdatedAdmCertRecord.signOps(UPDATED_SIGN_OPS).encOps(UPDATED_ENC_OPS);

        restAdmCertRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmCertRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmCertRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmCertRecord testAdmCertRecord = admCertRecordList.get(admCertRecordList.size() - 1);
        assertThat(testAdmCertRecord.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testAdmCertRecord.getCertCategory()).isEqualTo(DEFAULT_CERT_CATEGORY);
        assertThat(testAdmCertRecord.getCertSn()).isEqualTo(DEFAULT_CERT_SN);
        assertThat(testAdmCertRecord.getSignOps()).isEqualTo(UPDATED_SIGN_OPS);
        assertThat(testAdmCertRecord.getEncOps()).isEqualTo(UPDATED_ENC_OPS);
        assertThat(testAdmCertRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmCertRecord.getCreateUserSys()).isEqualTo(DEFAULT_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void fullUpdateAdmCertRecordWithPatch() throws Exception {
        // Initialize the database
        admCertRecordRepository.saveAndFlush(admCertRecord);

        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();

        // Update the admCertRecord using partial update
        AdmCertRecord partialUpdatedAdmCertRecord = new AdmCertRecord();
        partialUpdatedAdmCertRecord.setId(admCertRecord.getId());

        partialUpdatedAdmCertRecord
            .functionId(UPDATED_FUNCTION_ID)
            .certCategory(UPDATED_CERT_CATEGORY)
            .certSn(UPDATED_CERT_SN)
            .signOps(UPDATED_SIGN_OPS)
            .encOps(UPDATED_ENC_OPS)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);

        restAdmCertRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmCertRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmCertRecord))
            )
            .andExpect(status().isOk());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
        AdmCertRecord testAdmCertRecord = admCertRecordList.get(admCertRecordList.size() - 1);
        assertThat(testAdmCertRecord.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmCertRecord.getCertCategory()).isEqualTo(UPDATED_CERT_CATEGORY);
        assertThat(testAdmCertRecord.getCertSn()).isEqualTo(UPDATED_CERT_SN);
        assertThat(testAdmCertRecord.getSignOps()).isEqualTo(UPDATED_SIGN_OPS);
        assertThat(testAdmCertRecord.getEncOps()).isEqualTo(UPDATED_ENC_OPS);
        assertThat(testAdmCertRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmCertRecord.getCreateUserSys()).isEqualTo(UPDATED_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void patchNonExistingAdmCertRecord() throws Exception {
        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();
        admCertRecord.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmCertRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admCertRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admCertRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmCertRecord() throws Exception {
        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();
        admCertRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmCertRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admCertRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmCertRecord() throws Exception {
        int databaseSizeBeforeUpdate = admCertRecordRepository.findAll().size();
        admCertRecord.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmCertRecordMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admCertRecord))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmCertRecord in the database
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmCertRecord() throws Exception {
        // Initialize the database
        admCertRecordRepository.saveAndFlush(admCertRecord);

        int databaseSizeBeforeDelete = admCertRecordRepository.findAll().size();

        // Delete the admCertRecord
        restAdmCertRecordMockMvc
            .perform(delete(ENTITY_API_URL_ID, admCertRecord.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmCertRecord> admCertRecordList = admCertRecordRepository.findAll();
        assertThat(admCertRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
