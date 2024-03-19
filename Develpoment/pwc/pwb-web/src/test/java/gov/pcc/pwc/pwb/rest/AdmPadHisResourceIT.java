package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmPadHis;
import gov.pcc.pwc.repository.AdmPadHisRepository;
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
 * Integration tests for the {@link AdmPadHisResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmPadHisResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_PAD_HASH = "AAAAAAAAAA";
    private static final String UPDATED_USER_PAD_HASH = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-pad-his";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmPadHisRepository admPadHisRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmPadHisMockMvc;

    private AdmPadHis admPadHis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmPadHis createEntity(EntityManager em) {
        AdmPadHis admPadHis = new AdmPadHis().userId(DEFAULT_USER_ID).userPadHash(DEFAULT_USER_PAD_HASH).createTime(DEFAULT_CREATE_TIME);
        return admPadHis;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmPadHis createUpdatedEntity(EntityManager em) {
        AdmPadHis admPadHis = new AdmPadHis().userId(UPDATED_USER_ID).userPadHash(UPDATED_USER_PAD_HASH).createTime(UPDATED_CREATE_TIME);
        return admPadHis;
    }

    @BeforeEach
    public void initTest() {
        admPadHis = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmPadHis() throws Exception {
        int databaseSizeBeforeCreate = admPadHisRepository.findAll().size();
        // Create the AdmPadHis
        restAdmPadHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admPadHis)))
            .andExpect(status().isCreated());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeCreate + 1);
        AdmPadHis testAdmPadHis = admPadHisList.get(admPadHisList.size() - 1);
        assertThat(testAdmPadHis.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testAdmPadHis.getUserPadHash()).isEqualTo(DEFAULT_USER_PAD_HASH);
        assertThat(testAdmPadHis.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createAdmPadHisWithExistingId() throws Exception {
        // Create the AdmPadHis with an existing ID
        admPadHis.setId(1L);

        int databaseSizeBeforeCreate = admPadHisRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmPadHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admPadHis)))
            .andExpect(status().isBadRequest());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admPadHisRepository.findAll().size();
        // set the field null
        admPadHis.setUserId(null);

        // Create the AdmPadHis, which fails.

        restAdmPadHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admPadHis)))
            .andExpect(status().isBadRequest());

        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUserPadHashIsRequired() throws Exception {
        int databaseSizeBeforeTest = admPadHisRepository.findAll().size();
        // set the field null
        admPadHis.setUserPadHash(null);

        // Create the AdmPadHis, which fails.

        restAdmPadHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admPadHis)))
            .andExpect(status().isBadRequest());

        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admPadHisRepository.findAll().size();
        // set the field null
        admPadHis.setCreateTime(null);

        // Create the AdmPadHis, which fails.

        restAdmPadHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admPadHis)))
            .andExpect(status().isBadRequest());

        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmPadHis() throws Exception {
        // Initialize the database
        admPadHisRepository.saveAndFlush(admPadHis);

        // Get all the admPadHisList
        restAdmPadHisMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admPadHis.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userPadHash").value(hasItem(DEFAULT_USER_PAD_HASH)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmPadHis() throws Exception {
        // Initialize the database
        admPadHisRepository.saveAndFlush(admPadHis);

        // Get the admPadHis
        restAdmPadHisMockMvc
            .perform(get(ENTITY_API_URL_ID, admPadHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admPadHis.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userPadHash").value(DEFAULT_USER_PAD_HASH))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmPadHis() throws Exception {
        // Get the admPadHis
        restAdmPadHisMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmPadHis() throws Exception {
        // Initialize the database
        admPadHisRepository.saveAndFlush(admPadHis);

        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();

        // Update the admPadHis
        AdmPadHis updatedAdmPadHis = admPadHisRepository.findById(admPadHis.getId()).get();
        // Disconnect from session so that the updates on updatedAdmPadHis are not directly saved in db
        em.detach(updatedAdmPadHis);
        updatedAdmPadHis.userId(UPDATED_USER_ID).userPadHash(UPDATED_USER_PAD_HASH).createTime(UPDATED_CREATE_TIME);

        restAdmPadHisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmPadHis.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmPadHis))
            )
            .andExpect(status().isOk());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
        AdmPadHis testAdmPadHis = admPadHisList.get(admPadHisList.size() - 1);
        assertThat(testAdmPadHis.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAdmPadHis.getUserPadHash()).isEqualTo(UPDATED_USER_PAD_HASH);
        assertThat(testAdmPadHis.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmPadHis() throws Exception {
        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();
        admPadHis.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmPadHisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admPadHis.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admPadHis))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmPadHis() throws Exception {
        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();
        admPadHis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmPadHisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admPadHis))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmPadHis() throws Exception {
        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();
        admPadHis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmPadHisMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admPadHis)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmPadHisWithPatch() throws Exception {
        // Initialize the database
        admPadHisRepository.saveAndFlush(admPadHis);

        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();

        // Update the admPadHis using partial update
        AdmPadHis partialUpdatedAdmPadHis = new AdmPadHis();
        partialUpdatedAdmPadHis.setId(admPadHis.getId());

        partialUpdatedAdmPadHis.userPadHash(UPDATED_USER_PAD_HASH);

        restAdmPadHisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmPadHis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmPadHis))
            )
            .andExpect(status().isOk());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
        AdmPadHis testAdmPadHis = admPadHisList.get(admPadHisList.size() - 1);
        assertThat(testAdmPadHis.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testAdmPadHis.getUserPadHash()).isEqualTo(UPDATED_USER_PAD_HASH);
        assertThat(testAdmPadHis.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmPadHisWithPatch() throws Exception {
        // Initialize the database
        admPadHisRepository.saveAndFlush(admPadHis);

        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();

        // Update the admPadHis using partial update
        AdmPadHis partialUpdatedAdmPadHis = new AdmPadHis();
        partialUpdatedAdmPadHis.setId(admPadHis.getId());

        partialUpdatedAdmPadHis.userId(UPDATED_USER_ID).userPadHash(UPDATED_USER_PAD_HASH).createTime(UPDATED_CREATE_TIME);

        restAdmPadHisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmPadHis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmPadHis))
            )
            .andExpect(status().isOk());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
        AdmPadHis testAdmPadHis = admPadHisList.get(admPadHisList.size() - 1);
        assertThat(testAdmPadHis.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAdmPadHis.getUserPadHash()).isEqualTo(UPDATED_USER_PAD_HASH);
        assertThat(testAdmPadHis.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmPadHis() throws Exception {
        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();
        admPadHis.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmPadHisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admPadHis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admPadHis))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmPadHis() throws Exception {
        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();
        admPadHis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmPadHisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admPadHis))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmPadHis() throws Exception {
        int databaseSizeBeforeUpdate = admPadHisRepository.findAll().size();
        admPadHis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmPadHisMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admPadHis))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmPadHis in the database
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmPadHis() throws Exception {
        // Initialize the database
        admPadHisRepository.saveAndFlush(admPadHis);

        int databaseSizeBeforeDelete = admPadHisRepository.findAll().size();

        // Delete the admPadHis
        restAdmPadHisMockMvc
            .perform(delete(ENTITY_API_URL_ID, admPadHis.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmPadHis> admPadHisList = admPadHisRepository.findAll();
        assertThat(admPadHisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
