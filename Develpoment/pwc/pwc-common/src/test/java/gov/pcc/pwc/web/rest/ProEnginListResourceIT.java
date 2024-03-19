package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.ProEnginList;
import gov.pcc.pwc.repository.ProEnginListRepository;
import gov.pcc.pwc.service.dto.ProEnginListDTO;
import gov.pcc.pwc.service.mapper.ProEnginListMapper;
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
 * Integration tests for the {@link ProEnginListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProEnginListResourceIT {

    private static final String DEFAULT_PRO_INNOVATIVE_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRO_INNOVATIVE_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ENGIN_TYPE = "AA";
    private static final String UPDATED_ENGIN_TYPE = "BB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pro-engin-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proEnginListId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProEnginListRepository proEnginListRepository;

    @Autowired
    private ProEnginListMapper proEnginListMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProEnginListMockMvc;

    private ProEnginList proEnginList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProEnginList createEntity(EntityManager em) {
        ProEnginList proEnginList = new ProEnginList()
            .proInnovativeProductId(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID)
            .enginType(DEFAULT_ENGIN_TYPE)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return proEnginList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProEnginList createUpdatedEntity(EntityManager em) {
        ProEnginList proEnginList = new ProEnginList()
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .enginType(UPDATED_ENGIN_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return proEnginList;
    }

    @BeforeEach
    public void initTest() {
        proEnginList = createEntity(em);
    }

    @Test
    @Transactional
    void createProEnginList() throws Exception {
        int databaseSizeBeforeCreate = proEnginListRepository.findAll().size();
        // Create the ProEnginList
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(proEnginList);
        restProEnginListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeCreate + 1);
        ProEnginList testProEnginList = proEnginListList.get(proEnginListList.size() - 1);
        assertThat(testProEnginList.getProInnovativeProductId()).isEqualTo(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProEnginList.getEnginType()).isEqualTo(DEFAULT_ENGIN_TYPE);
        assertThat(testProEnginList.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProEnginList.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProEnginList.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProEnginList.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createProEnginListWithExistingId() throws Exception {
        // Create the ProEnginList with an existing ID
        proEnginList.setProEnginListId(1L);
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(proEnginList);

        int databaseSizeBeforeCreate = proEnginListRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProEnginListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProEnginLists() throws Exception {
        // Initialize the database
        proEnginListRepository.saveAndFlush(proEnginList);

        // Get all the proEnginListList
        restProEnginListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proEnginListId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proEnginListId").value(hasItem(proEnginList.getProEnginListId().intValue())))
            .andExpect(jsonPath("$.[*].proInnovativeProductId").value(hasItem(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].enginType").value(hasItem(DEFAULT_ENGIN_TYPE)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getProEnginList() throws Exception {
        // Initialize the database
        proEnginListRepository.saveAndFlush(proEnginList);

        // Get the proEnginList
        restProEnginListMockMvc
            .perform(get(ENTITY_API_URL_ID, proEnginList.getProEnginListId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proEnginListId").value(proEnginList.getProEnginListId().intValue()))
            .andExpect(jsonPath("$.proInnovativeProductId").value(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID))
            .andExpect(jsonPath("$.enginType").value(DEFAULT_ENGIN_TYPE))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProEnginList() throws Exception {
        // Get the proEnginList
        restProEnginListMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProEnginList() throws Exception {
        // Initialize the database
        proEnginListRepository.saveAndFlush(proEnginList);

        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();

        // Update the proEnginList
        ProEnginList updatedProEnginList = proEnginListRepository.findById(proEnginList.getProEnginListId()).get();
        // Disconnect from session so that the updates on updatedProEnginList are not directly saved in db
        em.detach(updatedProEnginList);
        updatedProEnginList
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .enginType(UPDATED_ENGIN_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(updatedProEnginList);

        restProEnginListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proEnginListDTO.getProEnginListId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
        ProEnginList testProEnginList = proEnginListList.get(proEnginListList.size() - 1);
        assertThat(testProEnginList.getProInnovativeProductId()).isEqualTo(UPDATED_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProEnginList.getEnginType()).isEqualTo(UPDATED_ENGIN_TYPE);
        assertThat(testProEnginList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProEnginList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProEnginList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProEnginList.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingProEnginList() throws Exception {
        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();
        proEnginList.setProEnginListId(count.incrementAndGet());

        // Create the ProEnginList
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(proEnginList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProEnginListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proEnginListDTO.getProEnginListId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProEnginList() throws Exception {
        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();
        proEnginList.setProEnginListId(count.incrementAndGet());

        // Create the ProEnginList
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(proEnginList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProEnginListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProEnginList() throws Exception {
        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();
        proEnginList.setProEnginListId(count.incrementAndGet());

        // Create the ProEnginList
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(proEnginList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProEnginListMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProEnginListWithPatch() throws Exception {
        // Initialize the database
        proEnginListRepository.saveAndFlush(proEnginList);

        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();

        // Update the proEnginList using partial update
        ProEnginList partialUpdatedProEnginList = new ProEnginList();
        partialUpdatedProEnginList.setProEnginListId(proEnginList.getProEnginListId());

        partialUpdatedProEnginList
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restProEnginListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProEnginList.getProEnginListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProEnginList))
            )
            .andExpect(status().isOk());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
        ProEnginList testProEnginList = proEnginListList.get(proEnginListList.size() - 1);
        assertThat(testProEnginList.getProInnovativeProductId()).isEqualTo(UPDATED_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProEnginList.getEnginType()).isEqualTo(DEFAULT_ENGIN_TYPE);
        assertThat(testProEnginList.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProEnginList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProEnginList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProEnginList.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateProEnginListWithPatch() throws Exception {
        // Initialize the database
        proEnginListRepository.saveAndFlush(proEnginList);

        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();

        // Update the proEnginList using partial update
        ProEnginList partialUpdatedProEnginList = new ProEnginList();
        partialUpdatedProEnginList.setProEnginListId(proEnginList.getProEnginListId());

        partialUpdatedProEnginList
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .enginType(UPDATED_ENGIN_TYPE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restProEnginListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProEnginList.getProEnginListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProEnginList))
            )
            .andExpect(status().isOk());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
        ProEnginList testProEnginList = proEnginListList.get(proEnginListList.size() - 1);
        assertThat(testProEnginList.getProInnovativeProductId()).isEqualTo(UPDATED_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProEnginList.getEnginType()).isEqualTo(UPDATED_ENGIN_TYPE);
        assertThat(testProEnginList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProEnginList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProEnginList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProEnginList.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingProEnginList() throws Exception {
        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();
        proEnginList.setProEnginListId(count.incrementAndGet());

        // Create the ProEnginList
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(proEnginList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProEnginListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, proEnginListDTO.getProEnginListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProEnginList() throws Exception {
        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();
        proEnginList.setProEnginListId(count.incrementAndGet());

        // Create the ProEnginList
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(proEnginList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProEnginListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProEnginList() throws Exception {
        int databaseSizeBeforeUpdate = proEnginListRepository.findAll().size();
        proEnginList.setProEnginListId(count.incrementAndGet());

        // Create the ProEnginList
        ProEnginListDTO proEnginListDTO = proEnginListMapper.toDto(proEnginList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProEnginListMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proEnginListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProEnginList in the database
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProEnginList() throws Exception {
        // Initialize the database
        proEnginListRepository.saveAndFlush(proEnginList);

        int databaseSizeBeforeDelete = proEnginListRepository.findAll().size();

        // Delete the proEnginList
        restProEnginListMockMvc
            .perform(delete(ENTITY_API_URL_ID, proEnginList.getProEnginListId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProEnginList> proEnginListList = proEnginListRepository.findAll();
        assertThat(proEnginListList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
