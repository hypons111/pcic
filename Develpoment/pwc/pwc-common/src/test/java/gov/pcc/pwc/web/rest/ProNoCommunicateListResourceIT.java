package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.ProNoCommunicateList;
import gov.pcc.pwc.repository.ProNoCommunicateListRepository;
import gov.pcc.pwc.service.dto.ProNoCommunicateListDTO;
import gov.pcc.pwc.service.mapper.ProNoCommunicateListMapper;
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
 * Integration tests for the {@link ProNoCommunicateListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProNoCommunicateListResourceIT {

    private static final Long DEFAULT_PRO_ORG_SUGGESTION_ID = 1L;
    private static final Long UPDATED_PRO_ORG_SUGGESTION_ID = 2L;

    private static final String DEFAULT_NO_COMMUNICATE_TYPE = "A";
    private static final String UPDATED_NO_COMMUNICATE_TYPE = "B";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pro-no-communicate-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proNoCommunicateListId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProNoCommunicateListRepository proNoCommunicateListRepository;

    @Autowired
    private ProNoCommunicateListMapper proNoCommunicateListMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProNoCommunicateListMockMvc;

    private ProNoCommunicateList proNoCommunicateList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProNoCommunicateList createEntity(EntityManager em) {
        ProNoCommunicateList proNoCommunicateList = new ProNoCommunicateList()
            .proOrgSuggestionId(DEFAULT_PRO_ORG_SUGGESTION_ID)
            .noCommunicateType(DEFAULT_NO_COMMUNICATE_TYPE)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return proNoCommunicateList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProNoCommunicateList createUpdatedEntity(EntityManager em) {
        ProNoCommunicateList proNoCommunicateList = new ProNoCommunicateList()
            .proOrgSuggestionId(UPDATED_PRO_ORG_SUGGESTION_ID)
            .noCommunicateType(UPDATED_NO_COMMUNICATE_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return proNoCommunicateList;
    }

    @BeforeEach
    public void initTest() {
        proNoCommunicateList = createEntity(em);
    }

    @Test
    @Transactional
    void createProNoCommunicateList() throws Exception {
        int databaseSizeBeforeCreate = proNoCommunicateListRepository.findAll().size();
        // Create the ProNoCommunicateList
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);
        restProNoCommunicateListMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeCreate + 1);
        ProNoCommunicateList testProNoCommunicateList = proNoCommunicateListList.get(proNoCommunicateListList.size() - 1);
        assertThat(testProNoCommunicateList.getProOrgSuggestionId()).isEqualTo(DEFAULT_PRO_ORG_SUGGESTION_ID);
        assertThat(testProNoCommunicateList.getNoCommunicateType()).isEqualTo(DEFAULT_NO_COMMUNICATE_TYPE);
        assertThat(testProNoCommunicateList.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProNoCommunicateList.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProNoCommunicateList.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProNoCommunicateList.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createProNoCommunicateListWithExistingId() throws Exception {
        // Create the ProNoCommunicateList with an existing ID
        proNoCommunicateList.setProNoCommunicateListId(1L);
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);

        int databaseSizeBeforeCreate = proNoCommunicateListRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProNoCommunicateListMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkProOrgSuggestionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNoCommunicateListRepository.findAll().size();
        // set the field null
        proNoCommunicateList.setProOrgSuggestionId(null);

        // Create the ProNoCommunicateList, which fails.
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);

        restProNoCommunicateListMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isBadRequest());

        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllProNoCommunicateLists() throws Exception {
        // Initialize the database
        proNoCommunicateListRepository.saveAndFlush(proNoCommunicateList);

        // Get all the proNoCommunicateListList
        restProNoCommunicateListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proNoCommunicateListId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proNoCommunicateListId").value(hasItem(proNoCommunicateList.getProNoCommunicateListId().intValue())))
            .andExpect(jsonPath("$.[*].proOrgSuggestionId").value(hasItem(DEFAULT_PRO_ORG_SUGGESTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].noCommunicateType").value(hasItem(DEFAULT_NO_COMMUNICATE_TYPE)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getProNoCommunicateList() throws Exception {
        // Initialize the database
        proNoCommunicateListRepository.saveAndFlush(proNoCommunicateList);

        // Get the proNoCommunicateList
        restProNoCommunicateListMockMvc
            .perform(get(ENTITY_API_URL_ID, proNoCommunicateList.getProNoCommunicateListId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proNoCommunicateListId").value(proNoCommunicateList.getProNoCommunicateListId().intValue()))
            .andExpect(jsonPath("$.proOrgSuggestionId").value(DEFAULT_PRO_ORG_SUGGESTION_ID.intValue()))
            .andExpect(jsonPath("$.noCommunicateType").value(DEFAULT_NO_COMMUNICATE_TYPE))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProNoCommunicateList() throws Exception {
        // Get the proNoCommunicateList
        restProNoCommunicateListMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProNoCommunicateList() throws Exception {
        // Initialize the database
        proNoCommunicateListRepository.saveAndFlush(proNoCommunicateList);

        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();

        // Update the proNoCommunicateList
        ProNoCommunicateList updatedProNoCommunicateList = proNoCommunicateListRepository
            .findById(proNoCommunicateList.getProNoCommunicateListId())
            .get();
        // Disconnect from session so that the updates on updatedProNoCommunicateList are not directly saved in db
        em.detach(updatedProNoCommunicateList);
        updatedProNoCommunicateList
            .proOrgSuggestionId(UPDATED_PRO_ORG_SUGGESTION_ID)
            .noCommunicateType(UPDATED_NO_COMMUNICATE_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(updatedProNoCommunicateList);

        restProNoCommunicateListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proNoCommunicateListDTO.getProNoCommunicateListId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
        ProNoCommunicateList testProNoCommunicateList = proNoCommunicateListList.get(proNoCommunicateListList.size() - 1);
        assertThat(testProNoCommunicateList.getProOrgSuggestionId()).isEqualTo(UPDATED_PRO_ORG_SUGGESTION_ID);
        assertThat(testProNoCommunicateList.getNoCommunicateType()).isEqualTo(UPDATED_NO_COMMUNICATE_TYPE);
        assertThat(testProNoCommunicateList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProNoCommunicateList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProNoCommunicateList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProNoCommunicateList.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingProNoCommunicateList() throws Exception {
        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();
        proNoCommunicateList.setProNoCommunicateListId(count.incrementAndGet());

        // Create the ProNoCommunicateList
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProNoCommunicateListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proNoCommunicateListDTO.getProNoCommunicateListId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProNoCommunicateList() throws Exception {
        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();
        proNoCommunicateList.setProNoCommunicateListId(count.incrementAndGet());

        // Create the ProNoCommunicateList
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProNoCommunicateListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProNoCommunicateList() throws Exception {
        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();
        proNoCommunicateList.setProNoCommunicateListId(count.incrementAndGet());

        // Create the ProNoCommunicateList
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProNoCommunicateListMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProNoCommunicateListWithPatch() throws Exception {
        // Initialize the database
        proNoCommunicateListRepository.saveAndFlush(proNoCommunicateList);

        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();

        // Update the proNoCommunicateList using partial update
        ProNoCommunicateList partialUpdatedProNoCommunicateList = new ProNoCommunicateList();
        partialUpdatedProNoCommunicateList.setProNoCommunicateListId(proNoCommunicateList.getProNoCommunicateListId());

        partialUpdatedProNoCommunicateList.proOrgSuggestionId(UPDATED_PRO_ORG_SUGGESTION_ID).updateUser(UPDATED_UPDATE_USER);

        restProNoCommunicateListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProNoCommunicateList.getProNoCommunicateListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProNoCommunicateList))
            )
            .andExpect(status().isOk());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
        ProNoCommunicateList testProNoCommunicateList = proNoCommunicateListList.get(proNoCommunicateListList.size() - 1);
        assertThat(testProNoCommunicateList.getProOrgSuggestionId()).isEqualTo(UPDATED_PRO_ORG_SUGGESTION_ID);
        assertThat(testProNoCommunicateList.getNoCommunicateType()).isEqualTo(DEFAULT_NO_COMMUNICATE_TYPE);
        assertThat(testProNoCommunicateList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProNoCommunicateList.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProNoCommunicateList.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProNoCommunicateList.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateProNoCommunicateListWithPatch() throws Exception {
        // Initialize the database
        proNoCommunicateListRepository.saveAndFlush(proNoCommunicateList);

        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();

        // Update the proNoCommunicateList using partial update
        ProNoCommunicateList partialUpdatedProNoCommunicateList = new ProNoCommunicateList();
        partialUpdatedProNoCommunicateList.setProNoCommunicateListId(proNoCommunicateList.getProNoCommunicateListId());

        partialUpdatedProNoCommunicateList
            .proOrgSuggestionId(UPDATED_PRO_ORG_SUGGESTION_ID)
            .noCommunicateType(UPDATED_NO_COMMUNICATE_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restProNoCommunicateListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProNoCommunicateList.getProNoCommunicateListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProNoCommunicateList))
            )
            .andExpect(status().isOk());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
        ProNoCommunicateList testProNoCommunicateList = proNoCommunicateListList.get(proNoCommunicateListList.size() - 1);
        assertThat(testProNoCommunicateList.getProOrgSuggestionId()).isEqualTo(UPDATED_PRO_ORG_SUGGESTION_ID);
        assertThat(testProNoCommunicateList.getNoCommunicateType()).isEqualTo(UPDATED_NO_COMMUNICATE_TYPE);
        assertThat(testProNoCommunicateList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProNoCommunicateList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProNoCommunicateList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProNoCommunicateList.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingProNoCommunicateList() throws Exception {
        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();
        proNoCommunicateList.setProNoCommunicateListId(count.incrementAndGet());

        // Create the ProNoCommunicateList
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProNoCommunicateListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, proNoCommunicateListDTO.getProNoCommunicateListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProNoCommunicateList() throws Exception {
        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();
        proNoCommunicateList.setProNoCommunicateListId(count.incrementAndGet());

        // Create the ProNoCommunicateList
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProNoCommunicateListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProNoCommunicateList() throws Exception {
        int databaseSizeBeforeUpdate = proNoCommunicateListRepository.findAll().size();
        proNoCommunicateList.setProNoCommunicateListId(count.incrementAndGet());

        // Create the ProNoCommunicateList
        ProNoCommunicateListDTO proNoCommunicateListDTO = proNoCommunicateListMapper.toDto(proNoCommunicateList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProNoCommunicateListMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proNoCommunicateListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProNoCommunicateList in the database
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProNoCommunicateList() throws Exception {
        // Initialize the database
        proNoCommunicateListRepository.saveAndFlush(proNoCommunicateList);

        int databaseSizeBeforeDelete = proNoCommunicateListRepository.findAll().size();

        // Delete the proNoCommunicateList
        restProNoCommunicateListMockMvc
            .perform(delete(ENTITY_API_URL_ID, proNoCommunicateList.getProNoCommunicateListId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProNoCommunicateList> proNoCommunicateListList = proNoCommunicateListRepository.findAll();
        assertThat(proNoCommunicateListList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
