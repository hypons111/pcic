package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.repository.AdmFileRepository;
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
 * Integration tests for the {@link AdmFileResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmFileResourceIT {

    private static final String DEFAULT_MODULE_TYPE = "AAA";
    private static final String UPDATED_MODULE_TYPE = "BBB";

    private static final String DEFAULT_BUSINESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME_ORI = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME_ORI = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_FILE_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_S_3_URL = "AAAAAAAAAA";
    private static final String UPDATED_S_3_URL = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-files";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmFileRepository admFileRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmFileMockMvc;

    private AdmFile admFile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFile createEntity(EntityManager em) {
        AdmFile admFile = new AdmFile()
            .moduleType(DEFAULT_MODULE_TYPE)
            .businessType(DEFAULT_BUSINESS_TYPE)
            .sourceId(DEFAULT_SOURCE_ID)
            .fileType(DEFAULT_FILE_TYPE)
            .fileNameOri(DEFAULT_FILE_NAME_ORI)
            .filePathName(DEFAULT_FILE_NAME)
            .fileRemark(DEFAULT_FILE_REMARK)
            .s3Url(DEFAULT_S_3_URL)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admFile;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFile createUpdatedEntity(EntityManager em) {
        AdmFile admFile = new AdmFile()
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .fileType(UPDATED_FILE_TYPE)
            .fileNameOri(UPDATED_FILE_NAME_ORI)
            .filePathName(UPDATED_FILE_NAME)
            .fileRemark(UPDATED_FILE_REMARK)
            .s3Url(UPDATED_S_3_URL)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admFile;
    }

    @BeforeEach
    public void initTest() {
        admFile = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmFile() throws Exception {
        int databaseSizeBeforeCreate = admFileRepository.findAll().size();
        // Create the AdmFile
        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isCreated());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeCreate + 1);
        AdmFile testAdmFile = admFileList.get(admFileList.size() - 1);
        assertThat(testAdmFile.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testAdmFile.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testAdmFile.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testAdmFile.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testAdmFile.getFileNameOri()).isEqualTo(DEFAULT_FILE_NAME_ORI);
        assertThat(testAdmFile.getFilePathName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testAdmFile.getFileRemark()).isEqualTo(DEFAULT_FILE_REMARK);
        assertThat(testAdmFile.getS3Url()).isEqualTo(DEFAULT_S_3_URL);
        assertThat(testAdmFile.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmFile.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmFile.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmFile.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmFileWithExistingId() throws Exception {
        // Create the AdmFile with an existing ID
        admFile.setId(1L);

        int databaseSizeBeforeCreate = admFileRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkModuleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setModuleType(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBusinessTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setBusinessType(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSourceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setSourceId(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFileTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setFileType(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFileNameOriIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setFileNameOri(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFileNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setFilePathName(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checks3UrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setS3Url(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setCreateUser(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFileRepository.findAll().size();
        // set the field null
        admFile.setCreateTime(null);

        // Create the AdmFile, which fails.

        restAdmFileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isBadRequest());

        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmFiles() throws Exception {
        // Initialize the database
        admFileRepository.saveAndFlush(admFile);

        // Get all the admFileList
        restAdmFileMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].moduleType").value(hasItem(DEFAULT_MODULE_TYPE)))
            .andExpect(jsonPath("$.[*].businessType").value(hasItem(DEFAULT_BUSINESS_TYPE)))
            .andExpect(jsonPath("$.[*].sourceId").value(hasItem(DEFAULT_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE)))
            .andExpect(jsonPath("$.[*].fileNameOri").value(hasItem(DEFAULT_FILE_NAME_ORI)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].fileRemark").value(hasItem(DEFAULT_FILE_REMARK)))
            .andExpect(jsonPath("$.[*].s3Url").value(hasItem(DEFAULT_S_3_URL)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmFile() throws Exception {
        // Initialize the database
        admFileRepository.saveAndFlush(admFile);

        // Get the admFile
        restAdmFileMockMvc
            .perform(get(ENTITY_API_URL_ID, admFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admFile.getId().intValue()))
            .andExpect(jsonPath("$.moduleType").value(DEFAULT_MODULE_TYPE))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE))
            .andExpect(jsonPath("$.sourceId").value(DEFAULT_SOURCE_ID))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE))
            .andExpect(jsonPath("$.fileNameOri").value(DEFAULT_FILE_NAME_ORI))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.fileRemark").value(DEFAULT_FILE_REMARK))
            .andExpect(jsonPath("$.s3Url").value(DEFAULT_S_3_URL))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmFile() throws Exception {
        // Get the admFile
        restAdmFileMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmFile() throws Exception {
        // Initialize the database
        admFileRepository.saveAndFlush(admFile);

        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();

        // Update the admFile
        AdmFile updatedAdmFile = admFileRepository.findById(admFile.getId()).get();
        // Disconnect from session so that the updates on updatedAdmFile are not directly saved in db
        em.detach(updatedAdmFile);
        updatedAdmFile
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .fileType(UPDATED_FILE_TYPE)
            .fileNameOri(UPDATED_FILE_NAME_ORI)
            .filePathName(UPDATED_FILE_NAME)
            .fileRemark(UPDATED_FILE_REMARK)
            .s3Url(UPDATED_S_3_URL)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmFileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmFile.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmFile))
            )
            .andExpect(status().isOk());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
        AdmFile testAdmFile = admFileList.get(admFileList.size() - 1);
        assertThat(testAdmFile.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testAdmFile.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testAdmFile.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmFile.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testAdmFile.getFileNameOri()).isEqualTo(UPDATED_FILE_NAME_ORI);
        assertThat(testAdmFile.getFilePathName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testAdmFile.getFileRemark()).isEqualTo(UPDATED_FILE_REMARK);
        assertThat(testAdmFile.getS3Url()).isEqualTo(UPDATED_S_3_URL);
        assertThat(testAdmFile.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmFile.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmFile.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmFile.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmFile() throws Exception {
        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();
        admFile.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admFile.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmFile() throws Exception {
        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();
        admFile.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmFile() throws Exception {
        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();
        admFile.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFileMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmFileWithPatch() throws Exception {
        // Initialize the database
        admFileRepository.saveAndFlush(admFile);

        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();

        // Update the admFile using partial update
        AdmFile partialUpdatedAdmFile = new AdmFile();
        partialUpdatedAdmFile.setId(admFile.getId());

        partialUpdatedAdmFile
            .sourceId(UPDATED_SOURCE_ID)
            .fileNameOri(UPDATED_FILE_NAME_ORI)
            .filePathName(UPDATED_FILE_NAME)
            .s3Url(UPDATED_S_3_URL)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmFileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFile.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFile))
            )
            .andExpect(status().isOk());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
        AdmFile testAdmFile = admFileList.get(admFileList.size() - 1);
        assertThat(testAdmFile.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testAdmFile.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testAdmFile.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmFile.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testAdmFile.getFileNameOri()).isEqualTo(UPDATED_FILE_NAME_ORI);
        assertThat(testAdmFile.getFilePathName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testAdmFile.getFileRemark()).isEqualTo(DEFAULT_FILE_REMARK);
        assertThat(testAdmFile.getS3Url()).isEqualTo(UPDATED_S_3_URL);
        assertThat(testAdmFile.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmFile.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmFile.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmFile.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmFileWithPatch() throws Exception {
        // Initialize the database
        admFileRepository.saveAndFlush(admFile);

        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();

        // Update the admFile using partial update
        AdmFile partialUpdatedAdmFile = new AdmFile();
        partialUpdatedAdmFile.setId(admFile.getId());

        partialUpdatedAdmFile
            .moduleType(UPDATED_MODULE_TYPE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .fileType(UPDATED_FILE_TYPE)
            .fileNameOri(UPDATED_FILE_NAME_ORI)
            .filePathName(UPDATED_FILE_NAME)
            .fileRemark(UPDATED_FILE_REMARK)
            .s3Url(UPDATED_S_3_URL)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmFileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFile.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFile))
            )
            .andExpect(status().isOk());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
        AdmFile testAdmFile = admFileList.get(admFileList.size() - 1);
        assertThat(testAdmFile.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
        assertThat(testAdmFile.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testAdmFile.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmFile.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testAdmFile.getFileNameOri()).isEqualTo(UPDATED_FILE_NAME_ORI);
        assertThat(testAdmFile.getFilePathName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testAdmFile.getFileRemark()).isEqualTo(UPDATED_FILE_REMARK);
        assertThat(testAdmFile.getS3Url()).isEqualTo(UPDATED_S_3_URL);
        assertThat(testAdmFile.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmFile.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmFile.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmFile.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmFile() throws Exception {
        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();
        admFile.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admFile.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmFile() throws Exception {
        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();
        admFile.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFile))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmFile() throws Exception {
        int databaseSizeBeforeUpdate = admFileRepository.findAll().size();
        admFile.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFileMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admFile)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFile in the database
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmFile() throws Exception {
        // Initialize the database
        admFileRepository.saveAndFlush(admFile);

        int databaseSizeBeforeDelete = admFileRepository.findAll().size();

        // Delete the admFile
        restAdmFileMockMvc
            .perform(delete(ENTITY_API_URL_ID, admFile.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmFile> admFileList = admFileRepository.findAll();
        assertThat(admFileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
