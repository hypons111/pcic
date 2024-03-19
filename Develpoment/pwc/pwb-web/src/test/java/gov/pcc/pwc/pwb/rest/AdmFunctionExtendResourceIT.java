package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmFunctionExtend;
import gov.pcc.pwc.repository.AdmFunctionExtendRepository;
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
 * Integration tests for the {@link AdmFunctionExtendResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmFunctionExtendResourceIT {

    private static final String DEFAULT_FUNCTION_EXTEND_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_EXTEND_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_EXTEND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_EXTEND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_EXTEND_DESCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_EXTEND_DESCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-function-extends";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmFunctionExtendRepository admFunctionExtendRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmFunctionExtendMockMvc;

    private AdmFunctionExtend admFunctionExtend;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFunctionExtend createEntity(EntityManager em) {
        AdmFunctionExtend admFunctionExtend = new AdmFunctionExtend();
        admFunctionExtend.setFunctionExtendId(DEFAULT_FUNCTION_EXTEND_ID);
        admFunctionExtend.setFunctionExtendName(DEFAULT_FUNCTION_EXTEND_NAME);
        admFunctionExtend.setFunctionExtendDescript(DEFAULT_FUNCTION_EXTEND_DESCRIPT);
        admFunctionExtend.setUpdateUser(DEFAULT_UPDATE_USER);
        admFunctionExtend.setUpdateTime(DEFAULT_UPDATE_TIME);
        return admFunctionExtend;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmFunctionExtend createUpdatedEntity(EntityManager em) {
        AdmFunctionExtend admFunctionExtend = new AdmFunctionExtend();
        admFunctionExtend.setFunctionExtendId(UPDATED_FUNCTION_EXTEND_ID);
        admFunctionExtend.setFunctionExtendName(UPDATED_FUNCTION_EXTEND_NAME);
        admFunctionExtend.setFunctionExtendDescript(UPDATED_FUNCTION_EXTEND_DESCRIPT);
        admFunctionExtend.setUpdateUser(UPDATED_UPDATE_USER);
        admFunctionExtend.setUpdateTime(UPDATED_UPDATE_TIME);
        return admFunctionExtend;
    }

    @BeforeEach
    public void initTest() {
        admFunctionExtend = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmFunctionExtend() throws Exception {
        int databaseSizeBeforeCreate = admFunctionExtendRepository.findAll().size();
        // Create the AdmFunctionExtend
        restAdmFunctionExtendMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isCreated());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeCreate + 1);
        AdmFunctionExtend testAdmFunctionExtend = admFunctionExtendList.get(admFunctionExtendList.size() - 1);
        assertThat(testAdmFunctionExtend.getFunctionExtendId()).isEqualTo(DEFAULT_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionExtend.getFunctionExtendName()).isEqualTo(DEFAULT_FUNCTION_EXTEND_NAME);
        assertThat(testAdmFunctionExtend.getFunctionExtendDescript()).isEqualTo(DEFAULT_FUNCTION_EXTEND_DESCRIPT);
        assertThat(testAdmFunctionExtend.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmFunctionExtend.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmFunctionExtendWithExistingId() throws Exception {
        // Create the AdmFunctionExtend with an existing ID
        admFunctionExtend.setId(1L);

        int databaseSizeBeforeCreate = admFunctionExtendRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmFunctionExtendMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFunctionExtendIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionExtendRepository.findAll().size();
        // set the field null
        admFunctionExtend.setFunctionExtendId(null);

        // Create the AdmFunctionExtend, which fails.

        restAdmFunctionExtendMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionExtendNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionExtendRepository.findAll().size();
        // set the field null
        admFunctionExtend.setFunctionExtendName(null);

        // Create the AdmFunctionExtend, which fails.

        restAdmFunctionExtendMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFunctionExtendDescriptIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionExtendRepository.findAll().size();
        // set the field null
        admFunctionExtend.setFunctionExtendDescript(null);

        // Create the AdmFunctionExtend, which fails.

        restAdmFunctionExtendMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionExtendRepository.findAll().size();
        // set the field null
        admFunctionExtend.setUpdateUser(null);

        // Create the AdmFunctionExtend, which fails.

        restAdmFunctionExtendMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admFunctionExtendRepository.findAll().size();
        // set the field null
        admFunctionExtend.setUpdateTime(null);

        // Create the AdmFunctionExtend, which fails.

        restAdmFunctionExtendMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmFunctionExtends() throws Exception {
        // Initialize the database
        admFunctionExtendRepository.saveAndFlush(admFunctionExtend);

        // Get all the admFunctionExtendList
        restAdmFunctionExtendMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admFunctionExtend.getId().intValue())))
            .andExpect(jsonPath("$.[*].functionExtendId").value(hasItem(DEFAULT_FUNCTION_EXTEND_ID)))
            .andExpect(jsonPath("$.[*].functionExtendName").value(hasItem(DEFAULT_FUNCTION_EXTEND_NAME)))
            .andExpect(jsonPath("$.[*].functionExtendDescript").value(hasItem(DEFAULT_FUNCTION_EXTEND_DESCRIPT)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmFunctionExtend() throws Exception {
        // Initialize the database
        admFunctionExtendRepository.saveAndFlush(admFunctionExtend);

        // Get the admFunctionExtend
        restAdmFunctionExtendMockMvc
            .perform(get(ENTITY_API_URL_ID, admFunctionExtend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admFunctionExtend.getId().intValue()))
            .andExpect(jsonPath("$.functionExtendId").value(DEFAULT_FUNCTION_EXTEND_ID))
            .andExpect(jsonPath("$.functionExtendName").value(DEFAULT_FUNCTION_EXTEND_NAME))
            .andExpect(jsonPath("$.functionExtendDescript").value(DEFAULT_FUNCTION_EXTEND_DESCRIPT))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmFunctionExtend() throws Exception {
        // Get the admFunctionExtend
        restAdmFunctionExtendMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmFunctionExtend() throws Exception {
        // Initialize the database
        admFunctionExtendRepository.saveAndFlush(admFunctionExtend);

        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();

        // Update the admFunctionExtend
        AdmFunctionExtend updatedAdmFunctionExtend = admFunctionExtendRepository.findById(admFunctionExtend.getId()).get();
        // Disconnect from session so that the updates on updatedAdmFunctionExtend are not directly saved in db
        em.detach(updatedAdmFunctionExtend);
        updatedAdmFunctionExtend.setFunctionExtendId(UPDATED_FUNCTION_EXTEND_ID);
        updatedAdmFunctionExtend.setFunctionExtendName(UPDATED_FUNCTION_EXTEND_NAME);
        updatedAdmFunctionExtend.setFunctionExtendDescript(UPDATED_FUNCTION_EXTEND_DESCRIPT);
        updatedAdmFunctionExtend.setUpdateUser(UPDATED_UPDATE_USER);
        updatedAdmFunctionExtend.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmFunctionExtendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmFunctionExtend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmFunctionExtend))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionExtend testAdmFunctionExtend = admFunctionExtendList.get(admFunctionExtendList.size() - 1);
        assertThat(testAdmFunctionExtend.getFunctionExtendId()).isEqualTo(UPDATED_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionExtend.getFunctionExtendName()).isEqualTo(UPDATED_FUNCTION_EXTEND_NAME);
        assertThat(testAdmFunctionExtend.getFunctionExtendDescript()).isEqualTo(UPDATED_FUNCTION_EXTEND_DESCRIPT);
        assertThat(testAdmFunctionExtend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmFunctionExtend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmFunctionExtend() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();
        admFunctionExtend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFunctionExtendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admFunctionExtend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmFunctionExtend() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();
        admFunctionExtend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionExtendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmFunctionExtend() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();
        admFunctionExtend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionExtendMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmFunctionExtendWithPatch() throws Exception {
        // Initialize the database
        admFunctionExtendRepository.saveAndFlush(admFunctionExtend);

        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();

        // Update the admFunctionExtend using partial update
        AdmFunctionExtend partialUpdatedAdmFunctionExtend = new AdmFunctionExtend();
        partialUpdatedAdmFunctionExtend.setId(admFunctionExtend.getId());

        partialUpdatedAdmFunctionExtend.setFunctionExtendName(UPDATED_FUNCTION_EXTEND_NAME);
        partialUpdatedAdmFunctionExtend.setFunctionExtendDescript(UPDATED_FUNCTION_EXTEND_DESCRIPT);
        partialUpdatedAdmFunctionExtend.setUpdateTime(UPDATED_UPDATE_TIME);;

        restAdmFunctionExtendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFunctionExtend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFunctionExtend))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionExtend testAdmFunctionExtend = admFunctionExtendList.get(admFunctionExtendList.size() - 1);
        assertThat(testAdmFunctionExtend.getFunctionExtendId()).isEqualTo(DEFAULT_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionExtend.getFunctionExtendName()).isEqualTo(UPDATED_FUNCTION_EXTEND_NAME);
        assertThat(testAdmFunctionExtend.getFunctionExtendDescript()).isEqualTo(UPDATED_FUNCTION_EXTEND_DESCRIPT);
        assertThat(testAdmFunctionExtend.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmFunctionExtend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmFunctionExtendWithPatch() throws Exception {
        // Initialize the database
        admFunctionExtendRepository.saveAndFlush(admFunctionExtend);

        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();

        // Update the admFunctionExtend using partial update
        AdmFunctionExtend partialUpdatedAdmFunctionExtend = new AdmFunctionExtend();
        partialUpdatedAdmFunctionExtend.setId(admFunctionExtend.getId());

        partialUpdatedAdmFunctionExtend.setFunctionExtendId(UPDATED_FUNCTION_EXTEND_ID);
        partialUpdatedAdmFunctionExtend.setFunctionExtendName(UPDATED_FUNCTION_EXTEND_NAME);
        partialUpdatedAdmFunctionExtend.setFunctionExtendDescript(UPDATED_FUNCTION_EXTEND_DESCRIPT);
        partialUpdatedAdmFunctionExtend.setUpdateUser(UPDATED_UPDATE_USER);
        partialUpdatedAdmFunctionExtend.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmFunctionExtendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmFunctionExtend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmFunctionExtend))
            )
            .andExpect(status().isOk());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
        AdmFunctionExtend testAdmFunctionExtend = admFunctionExtendList.get(admFunctionExtendList.size() - 1);
        assertThat(testAdmFunctionExtend.getFunctionExtendId()).isEqualTo(UPDATED_FUNCTION_EXTEND_ID);
        assertThat(testAdmFunctionExtend.getFunctionExtendName()).isEqualTo(UPDATED_FUNCTION_EXTEND_NAME);
        assertThat(testAdmFunctionExtend.getFunctionExtendDescript()).isEqualTo(UPDATED_FUNCTION_EXTEND_DESCRIPT);
        assertThat(testAdmFunctionExtend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmFunctionExtend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmFunctionExtend() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();
        admFunctionExtend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmFunctionExtendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admFunctionExtend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmFunctionExtend() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();
        admFunctionExtend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionExtendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmFunctionExtend() throws Exception {
        int databaseSizeBeforeUpdate = admFunctionExtendRepository.findAll().size();
        admFunctionExtend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmFunctionExtendMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admFunctionExtend))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmFunctionExtend in the database
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmFunctionExtend() throws Exception {
        // Initialize the database
        admFunctionExtendRepository.saveAndFlush(admFunctionExtend);

        int databaseSizeBeforeDelete = admFunctionExtendRepository.findAll().size();

        // Delete the admFunctionExtend
        restAdmFunctionExtendMockMvc
            .perform(delete(ENTITY_API_URL_ID, admFunctionExtend.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmFunctionExtend> admFunctionExtendList = admFunctionExtendRepository.findAll();
        assertThat(admFunctionExtendList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
