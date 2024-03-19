package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmFunctionClick;
import gov.pcc.pwc.repository.AdmFunctionClickRepository;
import gov.pcc.pwc.pwb.rest.AdmFunctionClickResource;
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
 * Integration tests for the {@link AdmFunctionClickResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmFunctionClickResourceIT {

    private static final String DEFAULT_FUNCTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_EXTEND_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_EXTEND_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-function-clicks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmFunctionClickRepository admFunctionClickRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmFunctionClickMockMvc;

    private AdmFunctionClick admFunctionClick;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFunctionClick createEntity(EntityManager em) {
        AdmFunctionClick admFunctionClick = new AdmFunctionClick()
            .functionId(DEFAULT_FUNCTION_ID)
            .functionExtendId(DEFAULT_FUNCTION_EXTEND_ID)
            .createTime(DEFAULT_CREATE_TIME);
        return admFunctionClick;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFunctionClick createUpdatedEntity(EntityManager em) {
        AdmFunctionClick admFunctionClick = new AdmFunctionClick()
            .functionId(UPDATED_FUNCTION_ID)
            .functionExtendId(UPDATED_FUNCTION_EXTEND_ID)
            .createTime(UPDATED_CREATE_TIME);
        return admFunctionClick;
    }

    @BeforeEach
    public void initTest() {
        admFunctionClick = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmFunctionClick() throws Exception {
        int databaseSizeBeforeCreate = admFunctionClickRepository.findAll().size();
        // Create the AdmFunctionClick
        restAdmFunctionClickMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isCreated());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeCreate + 1);
        AdmFunctionClick testAdmFunctionClick = admFunctionClickList.get(admFunctionClickList.size() - 1);
        assertThat(testAdmFunctionClick.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testAdmFunctionClick.getFunctionExtendId()).isEqualTo(DEFAULT_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionClick.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createAdmFunctionClickWithExistingId() throws Exception {
        // Create the AdmFunctionClick with an existing ID
        admFunctionClick.setId(1L);

        int databaseSizeBeforeCreate = admFunctionClickRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmFunctionClickMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFunctionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionClickRepository.findAll().size();
        // set the field null
        admFunctionClick.setFunctionId(null);

        // Create the AdmFunctionClick, which fails.

        restAdmFunctionClickMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionExtendIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionClickRepository.findAll().size();
        // set the field null
        admFunctionClick.setFunctionExtendId(null);

        // Create the AdmFunctionClick, which fails.

        restAdmFunctionClickMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionClickRepository.findAll().size();
        // set the field null
        admFunctionClick.setCreateTime(null);

        // Create the AdmFunctionClick, which fails.

        restAdmFunctionClickMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmFunctionClicks() throws Exception {
        // Initialize the database
        admFunctionClickRepository.saveAndFlush(admFunctionClick);

        // Get all the admFunctionClickList
        restAdmFunctionClickMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admFunctionClick.getId().intValue())))
            .andExpect(jsonPath("$.[*].functionId").value(hasItem(DEFAULT_FUNCTION_ID)))
            .andExpect(jsonPath("$.[*].functionExtendId").value(hasItem(DEFAULT_FUNCTION_EXTEND_ID)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmFunctionClick() throws Exception {
        // Initialize the database
        admFunctionClickRepository.saveAndFlush(admFunctionClick);

        // Get the admFunctionClick
        restAdmFunctionClickMockMvc
            .perform(get(ENTITY_API_URL_ID, admFunctionClick.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admFunctionClick.getId().intValue()))
            .andExpect(jsonPath("$.functionId").value(DEFAULT_FUNCTION_ID))
            .andExpect(jsonPath("$.functionExtendId").value(DEFAULT_FUNCTION_EXTEND_ID))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmFunctionClick() throws Exception {
        // Get the admFunctionClick
        restAdmFunctionClickMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmFunctionClick() throws Exception {
        // Initialize the database
        admFunctionClickRepository.saveAndFlush(admFunctionClick);

        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();

        // Update the admFunctionClick
        AdmFunctionClick updatedAdmFunctionClick = admFunctionClickRepository.findById(admFunctionClick.getId()).get();
        // Disconnect from session so that the updates on updatedAdmFunctionClick are not directly saved in db
        em.detach(updatedAdmFunctionClick);
        updatedAdmFunctionClick
            .functionId(UPDATED_FUNCTION_ID)
            .functionExtendId(UPDATED_FUNCTION_EXTEND_ID)
            .createTime(UPDATED_CREATE_TIME);

        restAdmFunctionClickMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmFunctionClick.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmFunctionClick))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionClick testAdmFunctionClick = admFunctionClickList.get(admFunctionClickList.size() - 1);
        assertThat(testAdmFunctionClick.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmFunctionClick.getFunctionExtendId()).isEqualTo(UPDATED_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionClick.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmFunctionClick() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();
        admFunctionClick.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFunctionClickMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admFunctionClick.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmFunctionClick() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();
        admFunctionClick.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionClickMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmFunctionClick() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();
        admFunctionClick.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionClickMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmFunctionClickWithPatch() throws Exception {
        // Initialize the database
        admFunctionClickRepository.saveAndFlush(admFunctionClick);

        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();

        // Update the admFunctionClick using partial update
        AdmFunctionClick partialUpdatedAdmFunctionClick = new AdmFunctionClick();
        partialUpdatedAdmFunctionClick.setId(admFunctionClick.getId());

        restAdmFunctionClickMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFunctionClick.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFunctionClick))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionClick testAdmFunctionClick = admFunctionClickList.get(admFunctionClickList.size() - 1);
        assertThat(testAdmFunctionClick.getFunctionId()).isEqualTo(DEFAULT_FUNCTION_ID);
        assertThat(testAdmFunctionClick.getFunctionExtendId()).isEqualTo(DEFAULT_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionClick.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmFunctionClickWithPatch() throws Exception {
        // Initialize the database
        admFunctionClickRepository.saveAndFlush(admFunctionClick);

        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();

        // Update the admFunctionClick using partial update
        AdmFunctionClick partialUpdatedAdmFunctionClick = new AdmFunctionClick();
        partialUpdatedAdmFunctionClick.setId(admFunctionClick.getId());

        partialUpdatedAdmFunctionClick
            .functionId(UPDATED_FUNCTION_ID)
            .functionExtendId(UPDATED_FUNCTION_EXTEND_ID)
            .createTime(UPDATED_CREATE_TIME);

        restAdmFunctionClickMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFunctionClick.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFunctionClick))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionClick testAdmFunctionClick = admFunctionClickList.get(admFunctionClickList.size() - 1);
        assertThat(testAdmFunctionClick.getFunctionId()).isEqualTo(UPDATED_FUNCTION_ID);
        assertThat(testAdmFunctionClick.getFunctionExtendId()).isEqualTo(UPDATED_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionClick.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmFunctionClick() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();
        admFunctionClick.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFunctionClickMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admFunctionClick.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmFunctionClick() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();
        admFunctionClick.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionClickMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmFunctionClick() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionClickRepository.findAll().size();
        admFunctionClick.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionClickMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionClick))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFunctionClick in the database
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmFunctionClick() throws Exception {
        // Initialize the database
        admFunctionClickRepository.saveAndFlush(admFunctionClick);

        int databaseSizeBeforeDelete = admFunctionClickRepository.findAll().size();

        // Delete the admFunctionClick
        restAdmFunctionClickMockMvc
            .perform(delete(ENTITY_API_URL_ID, admFunctionClick.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmFunctionClick> admFunctionClickList = admFunctionClickRepository.findAll();
        assertThat(admFunctionClickList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
