package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.ProRecommendList;
import gov.pcc.pwc.repository.ProRecommendListRepository;
import gov.pcc.pwc.service.dto.ProRecommendListDTO;
import gov.pcc.pwc.service.mapper.ProRecommendListMapper;
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
 * Integration tests for the {@link ProRecommendListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProRecommendListResourceIT {

    private static final String DEFAULT_PRO_INNOVATIVE_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRO_INNOVATIVE_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMMEND_TYPE = "AA";
    private static final String UPDATED_RECOMMEND_TYPE = "BB";

    private static final String DEFAULT_RECOMMEND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMEND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMMEND_TEL = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMEND_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pro-recommend-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proRecommendListId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProRecommendListRepository proRecommendListRepository;

    @Autowired
    private ProRecommendListMapper proRecommendListMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProRecommendListMockMvc;

    private ProRecommendList proRecommendList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProRecommendList createEntity(EntityManager em) {
        ProRecommendList proRecommendList = new ProRecommendList()
            .proInnovativeProductId(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID)
            .recommendType(DEFAULT_RECOMMEND_TYPE)
            .recommendName(DEFAULT_RECOMMEND_NAME)
            .recommendTel(DEFAULT_RECOMMEND_TEL)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return proRecommendList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProRecommendList createUpdatedEntity(EntityManager em) {
        ProRecommendList proRecommendList = new ProRecommendList()
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .recommendType(UPDATED_RECOMMEND_TYPE)
            .recommendName(UPDATED_RECOMMEND_NAME)
            .recommendTel(UPDATED_RECOMMEND_TEL)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return proRecommendList;
    }

    @BeforeEach
    public void initTest() {
        proRecommendList = createEntity(em);
    }

    @Test
    @Transactional
    void createProRecommendList() throws Exception {
        int databaseSizeBeforeCreate = proRecommendListRepository.findAll().size();
        // Create the ProRecommendList
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(proRecommendList);
        restProRecommendListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeCreate + 1);
        ProRecommendList testProRecommendList = proRecommendListList.get(proRecommendListList.size() - 1);
        assertThat(testProRecommendList.getProInnovativeProductId()).isEqualTo(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProRecommendList.getRecommendType()).isEqualTo(DEFAULT_RECOMMEND_TYPE);
        assertThat(testProRecommendList.getRecommendName()).isEqualTo(DEFAULT_RECOMMEND_NAME);
        assertThat(testProRecommendList.getRecommendTel()).isEqualTo(DEFAULT_RECOMMEND_TEL);
        assertThat(testProRecommendList.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProRecommendList.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProRecommendList.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProRecommendList.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createProRecommendListWithExistingId() throws Exception {
        // Create the ProRecommendList with an existing ID
        proRecommendList.setProRecommendListId(1L);
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(proRecommendList);

        int databaseSizeBeforeCreate = proRecommendListRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProRecommendListMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProRecommendLists() throws Exception {
        // Initialize the database
        proRecommendListRepository.saveAndFlush(proRecommendList);

        // Get all the proRecommendListList
        restProRecommendListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proRecommendListId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proRecommendListId").value(hasItem(proRecommendList.getProRecommendListId().intValue())))
            .andExpect(jsonPath("$.[*].proInnovativeProductId").value(hasItem(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].recommendType").value(hasItem(DEFAULT_RECOMMEND_TYPE)))
            .andExpect(jsonPath("$.[*].recommendName").value(hasItem(DEFAULT_RECOMMEND_NAME)))
            .andExpect(jsonPath("$.[*].recommendTel").value(hasItem(DEFAULT_RECOMMEND_TEL)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getProRecommendList() throws Exception {
        // Initialize the database
        proRecommendListRepository.saveAndFlush(proRecommendList);

        // Get the proRecommendList
        restProRecommendListMockMvc
            .perform(get(ENTITY_API_URL_ID, proRecommendList.getProRecommendListId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proRecommendListId").value(proRecommendList.getProRecommendListId().intValue()))
            .andExpect(jsonPath("$.proInnovativeProductId").value(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID))
            .andExpect(jsonPath("$.recommendType").value(DEFAULT_RECOMMEND_TYPE))
            .andExpect(jsonPath("$.recommendName").value(DEFAULT_RECOMMEND_NAME))
            .andExpect(jsonPath("$.recommendTel").value(DEFAULT_RECOMMEND_TEL))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProRecommendList() throws Exception {
        // Get the proRecommendList
        restProRecommendListMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProRecommendList() throws Exception {
        // Initialize the database
        proRecommendListRepository.saveAndFlush(proRecommendList);

        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();

        // Update the proRecommendList
        ProRecommendList updatedProRecommendList = proRecommendListRepository.findById(proRecommendList.getProRecommendListId()).get();
        // Disconnect from session so that the updates on updatedProRecommendList are not directly saved in db
        em.detach(updatedProRecommendList);
        updatedProRecommendList
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .recommendType(UPDATED_RECOMMEND_TYPE)
            .recommendName(UPDATED_RECOMMEND_NAME)
            .recommendTel(UPDATED_RECOMMEND_TEL)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(updatedProRecommendList);

        restProRecommendListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proRecommendListDTO.getProRecommendListId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
        ProRecommendList testProRecommendList = proRecommendListList.get(proRecommendListList.size() - 1);
        assertThat(testProRecommendList.getProInnovativeProductId()).isEqualTo(UPDATED_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProRecommendList.getRecommendType()).isEqualTo(UPDATED_RECOMMEND_TYPE);
        assertThat(testProRecommendList.getRecommendName()).isEqualTo(UPDATED_RECOMMEND_NAME);
        assertThat(testProRecommendList.getRecommendTel()).isEqualTo(UPDATED_RECOMMEND_TEL);
        assertThat(testProRecommendList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProRecommendList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProRecommendList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProRecommendList.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingProRecommendList() throws Exception {
        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();
        proRecommendList.setProRecommendListId(count.incrementAndGet());

        // Create the ProRecommendList
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(proRecommendList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProRecommendListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proRecommendListDTO.getProRecommendListId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProRecommendList() throws Exception {
        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();
        proRecommendList.setProRecommendListId(count.incrementAndGet());

        // Create the ProRecommendList
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(proRecommendList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProRecommendListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProRecommendList() throws Exception {
        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();
        proRecommendList.setProRecommendListId(count.incrementAndGet());

        // Create the ProRecommendList
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(proRecommendList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProRecommendListMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProRecommendListWithPatch() throws Exception {
        // Initialize the database
        proRecommendListRepository.saveAndFlush(proRecommendList);

        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();

        // Update the proRecommendList using partial update
        ProRecommendList partialUpdatedProRecommendList = new ProRecommendList();
        partialUpdatedProRecommendList.setProRecommendListId(proRecommendList.getProRecommendListId());

        partialUpdatedProRecommendList
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .recommendType(UPDATED_RECOMMEND_TYPE)
            .updateTime(UPDATED_UPDATE_TIME);

        restProRecommendListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProRecommendList.getProRecommendListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProRecommendList))
            )
            .andExpect(status().isOk());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
        ProRecommendList testProRecommendList = proRecommendListList.get(proRecommendListList.size() - 1);
        assertThat(testProRecommendList.getProInnovativeProductId()).isEqualTo(UPDATED_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProRecommendList.getRecommendType()).isEqualTo(UPDATED_RECOMMEND_TYPE);
        assertThat(testProRecommendList.getRecommendName()).isEqualTo(DEFAULT_RECOMMEND_NAME);
        assertThat(testProRecommendList.getRecommendTel()).isEqualTo(DEFAULT_RECOMMEND_TEL);
        assertThat(testProRecommendList.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProRecommendList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProRecommendList.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProRecommendList.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateProRecommendListWithPatch() throws Exception {
        // Initialize the database
        proRecommendListRepository.saveAndFlush(proRecommendList);

        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();

        // Update the proRecommendList using partial update
        ProRecommendList partialUpdatedProRecommendList = new ProRecommendList();
        partialUpdatedProRecommendList.setProRecommendListId(proRecommendList.getProRecommendListId());

        partialUpdatedProRecommendList
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .recommendType(UPDATED_RECOMMEND_TYPE)
            .recommendName(UPDATED_RECOMMEND_NAME)
            .recommendTel(UPDATED_RECOMMEND_TEL)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restProRecommendListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProRecommendList.getProRecommendListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProRecommendList))
            )
            .andExpect(status().isOk());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
        ProRecommendList testProRecommendList = proRecommendListList.get(proRecommendListList.size() - 1);
        assertThat(testProRecommendList.getProInnovativeProductId()).isEqualTo(UPDATED_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProRecommendList.getRecommendType()).isEqualTo(UPDATED_RECOMMEND_TYPE);
        assertThat(testProRecommendList.getRecommendName()).isEqualTo(UPDATED_RECOMMEND_NAME);
        assertThat(testProRecommendList.getRecommendTel()).isEqualTo(UPDATED_RECOMMEND_TEL);
        assertThat(testProRecommendList.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProRecommendList.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProRecommendList.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProRecommendList.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingProRecommendList() throws Exception {
        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();
        proRecommendList.setProRecommendListId(count.incrementAndGet());

        // Create the ProRecommendList
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(proRecommendList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProRecommendListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, proRecommendListDTO.getProRecommendListId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProRecommendList() throws Exception {
        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();
        proRecommendList.setProRecommendListId(count.incrementAndGet());

        // Create the ProRecommendList
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(proRecommendList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProRecommendListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProRecommendList() throws Exception {
        int databaseSizeBeforeUpdate = proRecommendListRepository.findAll().size();
        proRecommendList.setProRecommendListId(count.incrementAndGet());

        // Create the ProRecommendList
        ProRecommendListDTO proRecommendListDTO = proRecommendListMapper.toDto(proRecommendList);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProRecommendListMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proRecommendListDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProRecommendList in the database
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProRecommendList() throws Exception {
        // Initialize the database
        proRecommendListRepository.saveAndFlush(proRecommendList);

        int databaseSizeBeforeDelete = proRecommendListRepository.findAll().size();

        // Delete the proRecommendList
        restProRecommendListMockMvc
            .perform(delete(ENTITY_API_URL_ID, proRecommendList.getProRecommendListId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProRecommendList> proRecommendListList = proRecommendListRepository.findAll();
        assertThat(proRecommendListList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
