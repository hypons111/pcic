package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.ProOsPersonList;
import gov.pcc.pwc.repository.ProOsPersonListRepository;
import gov.pcc.pwc.service.dto.ProOsPersonListDTO;
import gov.pcc.pwc.service.mapper.ProOsPersonListMapper;
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
 * Integration tests for the {@link ProOsPersonListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProOsPersonListResourceIT {

    private static final Long DEFAULT_PRO_ORG_SUGGESTION_ID = 1L;
    private static final Long UPDATED_PRO_ORG_SUGGESTION_ID = 2L;

    private static final String DEFAULT_PERSON_TYPE = "A";
    private static final String UPDATED_PERSON_TYPE = "B";

    private static final String DEFAULT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PERSON_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pro-os-person-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proOsPersonListId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProOsPersonListRepository proOsPersonListRepository;

    @Autowired
    private ProOsPersonListMapper proOsPersonListMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProOsPersonListMockMvc;

    private ProOsPersonList proOsPersonList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProOsPersonList createEntity(EntityManager em) {
        ProOsPersonList proOsPersonList = new ProOsPersonList()
            .proOrgSuggestionId(DEFAULT_PRO_ORG_SUGGESTION_ID)
            .personType(DEFAULT_PERSON_TYPE)
            .personName(DEFAULT_PERSON_NAME)
            .personTitle(DEFAULT_PERSON_TITLE)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return proOsPersonList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProOsPersonList createUpdatedEntity(EntityManager em) {
        ProOsPersonList proOsPersonList = new ProOsPersonList()
            .proOrgSuggestionId(UPDATED_PRO_ORG_SUGGESTION_ID)
            .personType(UPDATED_PERSON_TYPE)
            .personName(UPDATED_PERSON_NAME)
            .personTitle(UPDATED_PERSON_TITLE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return proOsPersonList;
    }

    @BeforeEach
    public void initTest() {
        proOsPersonList = createEntity(em);
    }

    @Test
    @Transactional
    void createProOsPersonList() throws Exception {
        int databaseSizeBeforeCreate = proOsPersonListRepository.findAll().size();
        // Create the ProOsPersonList
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);
        restProOsPersonListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeCreate + 1);
        ProOsPersonList testProOsPersonList = proOsPersonListList.get(proOsPersonListList.size() - 1);
        assertThat(testProOsPersonList.getProOrgSuggestionId()).isEqualTo(DEFAULT_PRO_ORG_SUGGESTION_ID);
        assertThat(testProOsPersonList.getPersonType()).isEqualTo(DEFAULT_PERSON_TYPE);
        assertThat(testProOsPersonList.getPersonName()).isEqualTo(DEFAULT_PERSON_NAME);
        assertThat(testProOsPersonList.getPersonTitle()).isEqualTo(DEFAULT_PERSON_TITLE);
        assertThat(testProOsPersonList.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProOsPersonList.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProOsPersonList.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProOsPersonList.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createProOsPersonListWithExistingId() throws Exception {
        // Create the ProOsPersonList with an existing ID
        proOsPersonList.setProOsPersonListId(1L);
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);

        int databaseSizeBeforeCreate = proOsPersonListRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProOsPersonListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkProOrgSuggestionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = proOsPersonListRepository.findAll().size();
        // set the field null
        proOsPersonList.setProOrgSuggestionId(null);

        // Create the ProOsPersonList, which fails.
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);

        restProOsPersonListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isBadRequest());

        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllProOsPersonLists() throws Exception {
        // Initialize the database
        proOsPersonListRepository.saveAndFlush(proOsPersonList);

        // Get all the proOsPersonListList
        restProOsPersonListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proOsPersonListId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proOsPersonListId").value(hasItem(proOsPersonList.getProOsPersonListId().intValue())))
            .andExpect(jsonPath("$.[*].proOrgSuggestionId").value(hasItem(DEFAULT_PRO_ORG_SUGGESTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].personType").value(hasItem(DEFAULT_PERSON_TYPE)))
            .andExpect(jsonPath("$.[*].personName").value(hasItem(DEFAULT_PERSON_NAME)))
            .andExpect(jsonPath("$.[*].personTitle").value(hasItem(DEFAULT_PERSON_TITLE)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getProOsPersonList() throws Exception {
        // Initialize the database
        proOsPersonListRepository.saveAndFlush(proOsPersonList);

        // Get the proOsPersonList
        restProOsPersonListMockMvc
            .perform(get(ENTITY_API_URL_ID, proOsPersonList.getProOsPersonListId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proOsPersonListId").value(proOsPersonList.getProOsPersonListId().intValue()))
            .andExpect(jsonPath("$.proOrgSuggestionId").value(DEFAULT_PRO_ORG_SUGGESTION_ID.intValue()))
            .andExpect(jsonPath("$.personType").value(DEFAULT_PERSON_TYPE))
            .andExpect(jsonPath("$.personName").value(DEFAULT_PERSON_NAME))
            .andExpect(jsonPath("$.personTitle").value(DEFAULT_PERSON_TITLE))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProOsPersonList() throws Exception {
        // Get the proOsPersonList
        restProOsPersonListMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProOsPersonList() throws Exception {
        // Initialize the database
        proOsPersonListRepository.saveAndFlush(proOsPersonList);

        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();

        // Update the proOsPersonList
        ProOsPersonList updatedProOsPersonList = proOsPersonListRepository.findById(proOsPersonList.getProOsPersonListId()).get();
        // Disconnect from session so that the updates on updatedProOsPersonList are not directly saved in db
        em.detach(updatedProOsPersonList);
        updatedProOsPersonList
            .proOrgSuggestionId(UPDATED_PRO_ORG_SUGGESTION_ID)
            .personType(UPDATED_PERSON_TYPE)
            .personName(UPDATED_PERSON_NAME)
            .personTitle(UPDATED_PERSON_TITLE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(updatedProOsPersonList);

        restProOsPersonListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proOsPersonListDTO.getProOsPersonListId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
        ProOsPersonList testProOsPersonList = proOsPersonListList.get(proOsPersonListList.size() - 1);
        assertThat(testProOsPersonList.getProOrgSuggestionId()).isEqualTo(UPDATED_PRO_ORG_SUGGESTION_ID);
        assertThat(testProOsPersonList.getPersonType()).isEqualTo(UPDATED_PERSON_TYPE);
        assertThat(testProOsPersonList.getPersonName()).isEqualTo(UPDATED_PERSON_NAME);
        assertThat(testProOsPersonList.getPersonTitle()).isEqualTo(UPDATED_PERSON_TITLE);
        assertThat(testProOsPersonList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProOsPersonList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProOsPersonList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProOsPersonList.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingProOsPersonList() throws Exception {
        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();
        proOsPersonList.setProOsPersonListId(count.incrementAndGet());

        // Create the ProOsPersonList
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProOsPersonListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proOsPersonListDTO.getProOsPersonListId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProOsPersonList() throws Exception {
        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();
        proOsPersonList.setProOsPersonListId(count.incrementAndGet());

        // Create the ProOsPersonList
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProOsPersonListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProOsPersonList() throws Exception {
        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();
        proOsPersonList.setProOsPersonListId(count.incrementAndGet());

        // Create the ProOsPersonList
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProOsPersonListMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProOsPersonListWithPatch() throws Exception {
        // Initialize the database
        proOsPersonListRepository.saveAndFlush(proOsPersonList);

        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();

        // Update the proOsPersonList using partial update
        ProOsPersonList partialUpdatedProOsPersonList = new ProOsPersonList();
        partialUpdatedProOsPersonList.setProOsPersonListId(proOsPersonList.getProOsPersonListId());

        partialUpdatedProOsPersonList
            .proOrgSuggestionId(UPDATED_PRO_ORG_SUGGESTION_ID)
            .personName(UPDATED_PERSON_NAME)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restProOsPersonListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProOsPersonList.getProOsPersonListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProOsPersonList))
            )
            .andExpect(status().isOk());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
        ProOsPersonList testProOsPersonList = proOsPersonListList.get(proOsPersonListList.size() - 1);
        assertThat(testProOsPersonList.getProOrgSuggestionId()).isEqualTo(UPDATED_PRO_ORG_SUGGESTION_ID);
        assertThat(testProOsPersonList.getPersonType()).isEqualTo(DEFAULT_PERSON_TYPE);
        assertThat(testProOsPersonList.getPersonName()).isEqualTo(UPDATED_PERSON_NAME);
        assertThat(testProOsPersonList.getPersonTitle()).isEqualTo(DEFAULT_PERSON_TITLE);
        assertThat(testProOsPersonList.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProOsPersonList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProOsPersonList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProOsPersonList.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateProOsPersonListWithPatch() throws Exception {
        // Initialize the database
        proOsPersonListRepository.saveAndFlush(proOsPersonList);

        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();

        // Update the proOsPersonList using partial update
        ProOsPersonList partialUpdatedProOsPersonList = new ProOsPersonList();
        partialUpdatedProOsPersonList.setProOsPersonListId(proOsPersonList.getProOsPersonListId());

        partialUpdatedProOsPersonList
            .proOrgSuggestionId(UPDATED_PRO_ORG_SUGGESTION_ID)
            .personType(UPDATED_PERSON_TYPE)
            .personName(UPDATED_PERSON_NAME)
            .personTitle(UPDATED_PERSON_TITLE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restProOsPersonListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProOsPersonList.getProOsPersonListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProOsPersonList))
            )
            .andExpect(status().isOk());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
        ProOsPersonList testProOsPersonList = proOsPersonListList.get(proOsPersonListList.size() - 1);
        assertThat(testProOsPersonList.getProOrgSuggestionId()).isEqualTo(UPDATED_PRO_ORG_SUGGESTION_ID);
        assertThat(testProOsPersonList.getPersonType()).isEqualTo(UPDATED_PERSON_TYPE);
        assertThat(testProOsPersonList.getPersonName()).isEqualTo(UPDATED_PERSON_NAME);
        assertThat(testProOsPersonList.getPersonTitle()).isEqualTo(UPDATED_PERSON_TITLE);
        assertThat(testProOsPersonList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProOsPersonList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProOsPersonList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProOsPersonList.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingProOsPersonList() throws Exception {
        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();
        proOsPersonList.setProOsPersonListId(count.incrementAndGet());

        // Create the ProOsPersonList
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProOsPersonListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, proOsPersonListDTO.getProOsPersonListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProOsPersonList() throws Exception {
        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();
        proOsPersonList.setProOsPersonListId(count.incrementAndGet());

        // Create the ProOsPersonList
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProOsPersonListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProOsPersonList() throws Exception {
        int databaseSizeBeforeUpdate = proOsPersonListRepository.findAll().size();
        proOsPersonList.setProOsPersonListId(count.incrementAndGet());

        // Create the ProOsPersonList
        ProOsPersonListDTO proOsPersonListDTO = proOsPersonListMapper.toDto(proOsPersonList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProOsPersonListMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proOsPersonListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProOsPersonList in the database
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProOsPersonList() throws Exception {
        // Initialize the database
        proOsPersonListRepository.saveAndFlush(proOsPersonList);

        int databaseSizeBeforeDelete = proOsPersonListRepository.findAll().size();

        // Delete the proOsPersonList
        restProOsPersonListMockMvc
            .perform(delete(ENTITY_API_URL_ID, proOsPersonList.getProOsPersonListId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProOsPersonList> proOsPersonListList = proOsPersonListRepository.findAll();
        assertThat(proOsPersonListList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
