package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.RecUserProjectCode;
import gov.pcc.pwc.repository.RecUserProjectCodeRepository;
import gov.pcc.pwc.service.dto.RecUserProjectCodeDTO;
import gov.pcc.pwc.service.mapper.RecUserProjectCodeMapper;
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
 * Integration tests for the {@link RecUserProjectCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RecUserProjectCodeResourceIT {

    private static final String DEFAULT_PROJECT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/rec-user-project-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{userId}";

    @Autowired
    private RecUserProjectCodeRepository recUserProjectCodeRepository;

    @Autowired
    private RecUserProjectCodeMapper recUserProjectCodeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRecUserProjectCodeMockMvc;

    private RecUserProjectCode recUserProjectCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecUserProjectCode createEntity(EntityManager em) {
        RecUserProjectCode recUserProjectCode = new RecUserProjectCode()
            .projectCode(DEFAULT_PROJECT_CODE)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return recUserProjectCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecUserProjectCode createUpdatedEntity(EntityManager em) {
        RecUserProjectCode recUserProjectCode = new RecUserProjectCode()
            .projectCode(UPDATED_PROJECT_CODE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return recUserProjectCode;
    }

    @BeforeEach
    public void initTest() {
        recUserProjectCode = createEntity(em);
    }

    @Test
    @Transactional
    void createRecUserProjectCode() throws Exception {
        int databaseSizeBeforeCreate = recUserProjectCodeRepository.findAll().size();
        // Create the RecUserProjectCode
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);
        restRecUserProjectCodeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeCreate + 1);
        RecUserProjectCode testRecUserProjectCode = recUserProjectCodeList.get(recUserProjectCodeList.size() - 1);
        assertThat(testRecUserProjectCode.getProjectCode()).isEqualTo(DEFAULT_PROJECT_CODE);
        assertThat(testRecUserProjectCode.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testRecUserProjectCode.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testRecUserProjectCode.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testRecUserProjectCode.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createRecUserProjectCodeWithExistingId() throws Exception {
        // Create the RecUserProjectCode with an existing ID
        recUserProjectCode.setUserId("existing_id");
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        int databaseSizeBeforeCreate = recUserProjectCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecUserProjectCodeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkProjectCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = recUserProjectCodeRepository.findAll().size();
        // set the field null
        recUserProjectCode.setProjectCode(null);

        // Create the RecUserProjectCode, which fails.
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        restRecUserProjectCodeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = recUserProjectCodeRepository.findAll().size();
        // set the field null
        recUserProjectCode.setCreateUser(null);

        // Create the RecUserProjectCode, which fails.
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        restRecUserProjectCodeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = recUserProjectCodeRepository.findAll().size();
        // set the field null
        recUserProjectCode.setCreateTime(null);

        // Create the RecUserProjectCode, which fails.
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        restRecUserProjectCodeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = recUserProjectCodeRepository.findAll().size();
        // set the field null
        recUserProjectCode.setUpdateUser(null);

        // Create the RecUserProjectCode, which fails.
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        restRecUserProjectCodeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = recUserProjectCodeRepository.findAll().size();
        // set the field null
        recUserProjectCode.setUpdateTime(null);

        // Create the RecUserProjectCode, which fails.
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        restRecUserProjectCodeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRecUserProjectCodes() throws Exception {
        // Initialize the database
        recUserProjectCode.setUserId(UUID.randomUUID().toString());
        recUserProjectCodeRepository.saveAndFlush(recUserProjectCode);

        // Get all the recUserProjectCodeList
        restRecUserProjectCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=userId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(recUserProjectCode.getUserId())))
            .andExpect(jsonPath("$.[*].projectCode").value(hasItem(DEFAULT_PROJECT_CODE)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getRecUserProjectCode() throws Exception {
        // Initialize the database
        recUserProjectCode.setUserId(UUID.randomUUID().toString());
        recUserProjectCodeRepository.saveAndFlush(recUserProjectCode);

        // Get the recUserProjectCode
        restRecUserProjectCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, recUserProjectCode.getUserId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.userId").value(recUserProjectCode.getUserId()))
            .andExpect(jsonPath("$.projectCode").value(DEFAULT_PROJECT_CODE))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRecUserProjectCode() throws Exception {
        // Get the recUserProjectCode
        restRecUserProjectCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRecUserProjectCode() throws Exception {
        // Initialize the database
        recUserProjectCode.setUserId(UUID.randomUUID().toString());
        recUserProjectCodeRepository.saveAndFlush(recUserProjectCode);

        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();

        // Update the recUserProjectCode
        RecUserProjectCode updatedRecUserProjectCode = recUserProjectCodeRepository.findById(recUserProjectCode.getUserId()).get();
        // Disconnect from session so that the updates on updatedRecUserProjectCode are not directly saved in db
        em.detach(updatedRecUserProjectCode);
        updatedRecUserProjectCode
            .projectCode(UPDATED_PROJECT_CODE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(updatedRecUserProjectCode);

        restRecUserProjectCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, recUserProjectCodeDTO.getUserId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isOk());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
        RecUserProjectCode testRecUserProjectCode = recUserProjectCodeList.get(recUserProjectCodeList.size() - 1);
        assertThat(testRecUserProjectCode.getProjectCode()).isEqualTo(UPDATED_PROJECT_CODE);
        assertThat(testRecUserProjectCode.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testRecUserProjectCode.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRecUserProjectCode.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testRecUserProjectCode.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingRecUserProjectCode() throws Exception {
        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();
        recUserProjectCode.setUserId(UUID.randomUUID().toString());

        // Create the RecUserProjectCode
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecUserProjectCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, recUserProjectCodeDTO.getUserId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRecUserProjectCode() throws Exception {
        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();
        recUserProjectCode.setUserId(UUID.randomUUID().toString());

        // Create the RecUserProjectCode
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecUserProjectCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRecUserProjectCode() throws Exception {
        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();
        recUserProjectCode.setUserId(UUID.randomUUID().toString());

        // Create the RecUserProjectCode
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecUserProjectCodeMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRecUserProjectCodeWithPatch() throws Exception {
        // Initialize the database
        recUserProjectCode.setUserId(UUID.randomUUID().toString());
        recUserProjectCodeRepository.saveAndFlush(recUserProjectCode);

        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();

        // Update the recUserProjectCode using partial update
        RecUserProjectCode partialUpdatedRecUserProjectCode = new RecUserProjectCode();
        partialUpdatedRecUserProjectCode.setUserId(recUserProjectCode.getUserId());

        partialUpdatedRecUserProjectCode
            .projectCode(UPDATED_PROJECT_CODE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restRecUserProjectCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRecUserProjectCode.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRecUserProjectCode))
            )
            .andExpect(status().isOk());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
        RecUserProjectCode testRecUserProjectCode = recUserProjectCodeList.get(recUserProjectCodeList.size() - 1);
        assertThat(testRecUserProjectCode.getProjectCode()).isEqualTo(UPDATED_PROJECT_CODE);
        assertThat(testRecUserProjectCode.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testRecUserProjectCode.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRecUserProjectCode.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testRecUserProjectCode.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateRecUserProjectCodeWithPatch() throws Exception {
        // Initialize the database
        recUserProjectCode.setUserId(UUID.randomUUID().toString());
        recUserProjectCodeRepository.saveAndFlush(recUserProjectCode);

        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();

        // Update the recUserProjectCode using partial update
        RecUserProjectCode partialUpdatedRecUserProjectCode = new RecUserProjectCode();
        partialUpdatedRecUserProjectCode.setUserId(recUserProjectCode.getUserId());

        partialUpdatedRecUserProjectCode
            .projectCode(UPDATED_PROJECT_CODE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restRecUserProjectCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRecUserProjectCode.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRecUserProjectCode))
            )
            .andExpect(status().isOk());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
        RecUserProjectCode testRecUserProjectCode = recUserProjectCodeList.get(recUserProjectCodeList.size() - 1);
        assertThat(testRecUserProjectCode.getProjectCode()).isEqualTo(UPDATED_PROJECT_CODE);
        assertThat(testRecUserProjectCode.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testRecUserProjectCode.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRecUserProjectCode.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testRecUserProjectCode.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingRecUserProjectCode() throws Exception {
        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();
        recUserProjectCode.setUserId(UUID.randomUUID().toString());

        // Create the RecUserProjectCode
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecUserProjectCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, recUserProjectCodeDTO.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRecUserProjectCode() throws Exception {
        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();
        recUserProjectCode.setUserId(UUID.randomUUID().toString());

        // Create the RecUserProjectCode
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecUserProjectCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRecUserProjectCode() throws Exception {
        int databaseSizeBeforeUpdate = recUserProjectCodeRepository.findAll().size();
        recUserProjectCode.setUserId(UUID.randomUUID().toString());

        // Create the RecUserProjectCode
        RecUserProjectCodeDTO recUserProjectCodeDTO = recUserProjectCodeMapper.toDto(recUserProjectCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecUserProjectCodeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(recUserProjectCodeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RecUserProjectCode in the database
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRecUserProjectCode() throws Exception {
        // Initialize the database
        recUserProjectCode.setUserId(UUID.randomUUID().toString());
        recUserProjectCodeRepository.saveAndFlush(recUserProjectCode);

        int databaseSizeBeforeDelete = recUserProjectCodeRepository.findAll().size();

        // Delete the recUserProjectCode
        restRecUserProjectCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, recUserProjectCode.getUserId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RecUserProjectCode> recUserProjectCodeList = recUserProjectCodeRepository.findAll();
        assertThat(recUserProjectCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
