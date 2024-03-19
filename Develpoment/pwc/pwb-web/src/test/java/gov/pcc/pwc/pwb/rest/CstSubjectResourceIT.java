package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.CstSubject;
import gov.pcc.pwc.repository.CstSubjectRepository;
import gov.pcc.pwc.service.dto.CstSubjectDTO;
import gov.pcc.pwc.service.mapper.CstSubjectMapper;
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
 * Integration tests for the {@link CstSubjectResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CstSubjectResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cst-subjects";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CstSubjectRepository cstSubjectRepository;

    @Autowired
    private CstSubjectMapper cstSubjectMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCstSubjectMockMvc;

    private CstSubject cstSubject;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CstSubject createEntity(EntityManager em) {
        CstSubject cstSubject = new CstSubject().name(DEFAULT_NAME);
        return cstSubject;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CstSubject createUpdatedEntity(EntityManager em) {
        CstSubject cstSubject = new CstSubject().name(UPDATED_NAME);
        return cstSubject;
    }

    @BeforeEach
    public void initTest() {
        cstSubject = createEntity(em);
    }

    @Test
    @Transactional
    void createCstSubject() throws Exception {
        int databaseSizeBeforeCreate = cstSubjectRepository.findAll().size();
        // Create the CstSubject
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(cstSubject);
        restCstSubjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO)))
            .andExpect(status().isCreated());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeCreate + 1);
        CstSubject testCstSubject = cstSubjectList.get(cstSubjectList.size() - 1);
        assertThat(testCstSubject.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createCstSubjectWithExistingId() throws Exception {
        // Create the CstSubject with an existing ID
        cstSubject.setId(1L);
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(cstSubject);

        int databaseSizeBeforeCreate = cstSubjectRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCstSubjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCstSubjects() throws Exception {
        // Initialize the database
        cstSubjectRepository.saveAndFlush(cstSubject);

        // Get all the cstSubjectList
        restCstSubjectMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cstSubject.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getCstSubject() throws Exception {
        // Initialize the database
        cstSubjectRepository.saveAndFlush(cstSubject);

        // Get the cstSubject
        restCstSubjectMockMvc
            .perform(get(ENTITY_API_URL_ID, cstSubject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cstSubject.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingCstSubject() throws Exception {
        // Get the cstSubject
        restCstSubjectMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCstSubject() throws Exception {
        // Initialize the database
        cstSubjectRepository.saveAndFlush(cstSubject);

        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();

        // Update the cstSubject
        CstSubject updatedCstSubject = cstSubjectRepository.findById(cstSubject.getId()).get();
        // Disconnect from session so that the updates on updatedCstSubject are not directly saved in db
        em.detach(updatedCstSubject);
        updatedCstSubject.name(UPDATED_NAME);
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(updatedCstSubject);

        restCstSubjectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cstSubjectDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO))
            )
            .andExpect(status().isOk());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
        CstSubject testCstSubject = cstSubjectList.get(cstSubjectList.size() - 1);
        assertThat(testCstSubject.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingCstSubject() throws Exception {
        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();
        cstSubject.setId(count.incrementAndGet());

        // Create the CstSubject
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(cstSubject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCstSubjectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cstSubjectDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCstSubject() throws Exception {
        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();
        cstSubject.setId(count.incrementAndGet());

        // Create the CstSubject
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(cstSubject);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCstSubjectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCstSubject() throws Exception {
        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();
        cstSubject.setId(count.incrementAndGet());

        // Create the CstSubject
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(cstSubject);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCstSubjectMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCstSubjectWithPatch() throws Exception {
        // Initialize the database
        cstSubjectRepository.saveAndFlush(cstSubject);

        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();

        // Update the cstSubject using partial update
        CstSubject partialUpdatedCstSubject = new CstSubject();
        partialUpdatedCstSubject.setId(cstSubject.getId());

        partialUpdatedCstSubject.name(UPDATED_NAME);

        restCstSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCstSubject.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCstSubject))
            )
            .andExpect(status().isOk());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
        CstSubject testCstSubject = cstSubjectList.get(cstSubjectList.size() - 1);
        assertThat(testCstSubject.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void fullUpdateCstSubjectWithPatch() throws Exception {
        // Initialize the database
        cstSubjectRepository.saveAndFlush(cstSubject);

        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();

        // Update the cstSubject using partial update
        CstSubject partialUpdatedCstSubject = new CstSubject();
        partialUpdatedCstSubject.setId(cstSubject.getId());

        partialUpdatedCstSubject.name(UPDATED_NAME);

        restCstSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCstSubject.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCstSubject))
            )
            .andExpect(status().isOk());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
        CstSubject testCstSubject = cstSubjectList.get(cstSubjectList.size() - 1);
        assertThat(testCstSubject.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingCstSubject() throws Exception {
        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();
        cstSubject.setId(count.incrementAndGet());

        // Create the CstSubject
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(cstSubject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCstSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cstSubjectDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCstSubject() throws Exception {
        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();
        cstSubject.setId(count.incrementAndGet());

        // Create the CstSubject
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(cstSubject);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCstSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCstSubject() throws Exception {
        int databaseSizeBeforeUpdate = cstSubjectRepository.findAll().size();
        cstSubject.setId(count.incrementAndGet());

        // Create the CstSubject
        CstSubjectDTO cstSubjectDTO = cstSubjectMapper.toDto(cstSubject);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCstSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(cstSubjectDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CstSubject in the database
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCstSubject() throws Exception {
        // Initialize the database
        cstSubjectRepository.saveAndFlush(cstSubject);

        int databaseSizeBeforeDelete = cstSubjectRepository.findAll().size();

        // Delete the cstSubject
        restCstSubjectMockMvc
            .perform(delete(ENTITY_API_URL_ID, cstSubject.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CstSubject> cstSubjectList = cstSubjectRepository.findAll();
        assertThat(cstSubjectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
