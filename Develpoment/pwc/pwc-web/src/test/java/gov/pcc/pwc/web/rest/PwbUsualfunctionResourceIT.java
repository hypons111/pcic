package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwbUsualfunction;
import gov.pcc.pwc.repository.PwbUsualfunctionRepository;
import gov.pcc.pwc.service.dto.PwbUsualfunctionDTO;
import gov.pcc.pwc.service.mapper.PwbUsualfunctionMapper;
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
 * Integration tests for the {@link PwbUsualfunctionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwbUsualfunctionResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pwb-usualfunctions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwbUsualfunctionRepository pwbUsualfunctionRepository;

    @Autowired
    private PwbUsualfunctionMapper pwbUsualfunctionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwbUsualfunctionMockMvc;

    private PwbUsualfunction pwbUsualfunction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbUsualfunction createEntity(EntityManager em) {
        PwbUsualfunction pwbUsualfunction = new PwbUsualfunction()
            .userId(DEFAULT_USER_ID)
            .functionId(DEFAULT_FUNCTION_ID)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return pwbUsualfunction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbUsualfunction createUpdatedEntity(EntityManager em) {
        PwbUsualfunction pwbUsualfunction = new PwbUsualfunction()
            .userId(UPDATED_USER_ID)
            .functionId(UPDATED_FUNCTION_ID)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return pwbUsualfunction;
    }

    @BeforeEach
    public void initTest() {
        pwbUsualfunction = createEntity(em);
    }

    @Test
    @Transactional
    void createPwbUsualfunction() throws Exception {
        int databaseSizeBeforeCreate = pwbUsualfunctionRepository.findAll().size();
        // Create the PwbUsualfunction
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);
        restPwbUsualfunctionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeCreate + 1);
        PwbUsualfunction testPwbUsualfunction = pwbUsualfunctionList.get(pwbUsualfunctionList.size() - 1);
        assertThat(testPwbUsualfunction.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testPwbUsualfunction.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testPwbUsualfunction.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testPwbUsualfunction.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createPwbUsualfunctionWithExistingId() throws Exception {
        // Create the PwbUsualfunction with an existing ID
        pwbUsualfunction.setId(1L);
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        int databaseSizeBeforeCreate = pwbUsualfunctionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwbUsualfunctionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUsualfunctionRepository.findAll().size();
        // set the field null
        pwbUsualfunction.setUserId(null);

        // Create the PwbUsualfunction, which fails.
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        restPwbUsualfunctionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUsualfunctionRepository.findAll().size();
        // set the field null
        pwbUsualfunction.setFunctionId(null);

        // Create the PwbUsualfunction, which fails.
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        restPwbUsualfunctionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUsualfunctionRepository.findAll().size();
        // set the field null
        pwbUsualfunction.setCreateUser(null);

        // Create the PwbUsualfunction, which fails.
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        restPwbUsualfunctionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUsualfunctionRepository.findAll().size();
        // set the field null
        pwbUsualfunction.setCreateTime(null);

        // Create the PwbUsualfunction, which fails.
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        restPwbUsualfunctionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPwbUsualfunctions() throws Exception {
        // Initialize the database
        pwbUsualfunctionRepository.saveAndFlush(pwbUsualfunction);

        // Get all the pwbUsualfunctionList
        restPwbUsualfunctionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pwbUsualfunction.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].functionId").value(hasItem(DEFAULT_FUNCTION_ID)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getPwbUsualfunction() throws Exception {
        // Initialize the database
        pwbUsualfunctionRepository.saveAndFlush(pwbUsualfunction);

        // Get the pwbUsualfunction
        restPwbUsualfunctionMockMvc
            .perform(get(ENTITY_API_URL_ID, pwbUsualfunction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pwbUsualfunction.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.functionId").value(DEFAULT_FUNCTION_ID))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPwbUsualfunction() throws Exception {
        // Get the pwbUsualfunction
        restPwbUsualfunctionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwbUsualfunction() throws Exception {
        // Initialize the database
        pwbUsualfunctionRepository.saveAndFlush(pwbUsualfunction);

        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();

        // Update the pwbUsualfunction
        PwbUsualfunction updatedPwbUsualfunction = pwbUsualfunctionRepository.findById(pwbUsualfunction.getId()).get();
        // Disconnect from session so that the updates on updatedPwbUsualfunction are not directly saved in db
        em.detach(updatedPwbUsualfunction);
        updatedPwbUsualfunction
            .userId(UPDATED_USER_ID)
            .functionId(UPDATED_FUNCTION_ID)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(updatedPwbUsualfunction);

        restPwbUsualfunctionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbUsualfunctionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
        PwbUsualfunction testPwbUsualfunction = pwbUsualfunctionList.get(pwbUsualfunctionList.size() - 1);
        assertThat(testPwbUsualfunction.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbUsualfunction.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testPwbUsualfunction.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbUsualfunction.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingPwbUsualfunction() throws Exception {
        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();
        pwbUsualfunction.setId(count.incrementAndGet());

        // Create the PwbUsualfunction
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbUsualfunctionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbUsualfunctionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwbUsualfunction() throws Exception {
        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();
        pwbUsualfunction.setId(count.incrementAndGet());

        // Create the PwbUsualfunction
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUsualfunctionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwbUsualfunction() throws Exception {
        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();
        pwbUsualfunction.setId(count.incrementAndGet());

        // Create the PwbUsualfunction
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUsualfunctionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwbUsualfunctionWithPatch() throws Exception {
        // Initialize the database
        pwbUsualfunctionRepository.saveAndFlush(pwbUsualfunction);

        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();

        // Update the pwbUsualfunction using partial update
        PwbUsualfunction partialUpdatedPwbUsualfunction = new PwbUsualfunction();
        partialUpdatedPwbUsualfunction.setId(pwbUsualfunction.getId());

        partialUpdatedPwbUsualfunction.userId(UPDATED_USER_ID).createUser(UPDATED_CREATE_USER);

        restPwbUsualfunctionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbUsualfunction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbUsualfunction))
            )
            .andExpect(status().isOk());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
        PwbUsualfunction testPwbUsualfunction = pwbUsualfunctionList.get(pwbUsualfunctionList.size() - 1);
        assertThat(testPwbUsualfunction.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbUsualfunction.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testPwbUsualfunction.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbUsualfunction.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdatePwbUsualfunctionWithPatch() throws Exception {
        // Initialize the database
        pwbUsualfunctionRepository.saveAndFlush(pwbUsualfunction);

        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();

        // Update the pwbUsualfunction using partial update
        PwbUsualfunction partialUpdatedPwbUsualfunction = new PwbUsualfunction();
        partialUpdatedPwbUsualfunction.setId(pwbUsualfunction.getId());

        partialUpdatedPwbUsualfunction
            .userId(UPDATED_USER_ID)
            .functionId(UPDATED_FUNCTION_ID)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restPwbUsualfunctionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbUsualfunction.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbUsualfunction))
            )
            .andExpect(status().isOk());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
        PwbUsualfunction testPwbUsualfunction = pwbUsualfunctionList.get(pwbUsualfunctionList.size() - 1);
        assertThat(testPwbUsualfunction.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testPwbUsualfunction.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testPwbUsualfunction.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbUsualfunction.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingPwbUsualfunction() throws Exception {
        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();
        pwbUsualfunction.setId(count.incrementAndGet());

        // Create the PwbUsualfunction
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbUsualfunctionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwbUsualfunctionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwbUsualfunction() throws Exception {
        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();
        pwbUsualfunction.setId(count.incrementAndGet());

        // Create the PwbUsualfunction
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUsualfunctionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwbUsualfunction() throws Exception {
        int databaseSizeBeforeUpdate = pwbUsualfunctionRepository.findAll().size();
        pwbUsualfunction.setId(count.incrementAndGet());

        // Create the PwbUsualfunction
        PwbUsualfunctionDTO pwbUsualfunctionDTO = pwbUsualfunctionMapper.toDto(pwbUsualfunction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUsualfunctionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbUsualfunctionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbUsualfunction in the database
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwbUsualfunction() throws Exception {
        // Initialize the database
        pwbUsualfunctionRepository.saveAndFlush(pwbUsualfunction);

        int databaseSizeBeforeDelete = pwbUsualfunctionRepository.findAll().size();

        // Delete the pwbUsualfunction
        restPwbUsualfunctionMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwbUsualfunction.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwbUsualfunction> pwbUsualfunctionList = pwbUsualfunctionRepository.findAll();
        assertThat(pwbUsualfunctionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
