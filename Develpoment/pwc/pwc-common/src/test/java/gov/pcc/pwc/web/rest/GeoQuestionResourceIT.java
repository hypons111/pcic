package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.GeoQuestion;
import gov.pcc.pwc.repository.GeoQuestionRepository;
import gov.pcc.pwc.service.dto.GeoQuestionDTO;
import gov.pcc.pwc.service.mapper.GeoQuestionMapper;
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
 * Integration tests for the {@link GeoQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GeoQuestionResourceIT {

    private static final String DEFAULT_GEO_QUESTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_GEO_QUESTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION = "BBBBBBBBBB";

    private static final String DEFAULT_REPLY = "AAAAAAAAAA";
    private static final String UPDATED_REPLY = "BBBBBBBBBB";

    private static final Instant DEFAULT_TRANSFER_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSFER_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TRANSFER_FROM = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_IS_DEL = "AAAAAAAAAA";
    private static final String UPDATED_IS_DEL = "BBBBBBBBBB";

    private static final Instant DEFAULT_DEL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DEL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DEL_ACC_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEL_ACC_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/geo-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GeoQuestionRepository geoQuestionRepository;

    @Autowired
    private GeoQuestionMapper geoQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoQuestionMockMvc;

    private GeoQuestion geoQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoQuestion createEntity(EntityManager em) {
        GeoQuestion geoQuestion = new GeoQuestion()
            .geoQuestionId(DEFAULT_GEO_QUESTION_ID)
            .roleId(DEFAULT_ROLE_ID)
            .question(DEFAULT_QUESTION)
            .reply(DEFAULT_REPLY)
            .transferTime(DEFAULT_TRANSFER_TIME)
            .transferFrom(DEFAULT_TRANSFER_FROM)
            .isDel(DEFAULT_IS_DEL)
            .delDate(DEFAULT_DEL_DATE)
            .delAccId(DEFAULT_DEL_ACC_ID)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return geoQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoQuestion createUpdatedEntity(EntityManager em) {
        GeoQuestion geoQuestion = new GeoQuestion()
            .geoQuestionId(UPDATED_GEO_QUESTION_ID)
            .roleId(UPDATED_ROLE_ID)
            .question(UPDATED_QUESTION)
            .reply(UPDATED_REPLY)
            .transferTime(UPDATED_TRANSFER_TIME)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .isDel(UPDATED_IS_DEL)
            .delDate(UPDATED_DEL_DATE)
            .delAccId(UPDATED_DEL_ACC_ID)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return geoQuestion;
    }

    @BeforeEach
    public void initTest() {
        geoQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void createGeoQuestion() throws Exception {
        int databaseSizeBeforeCreate = geoQuestionRepository.findAll().size();
        // Create the GeoQuestion
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(geoQuestion);
        restGeoQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        GeoQuestion testGeoQuestion = geoQuestionList.get(geoQuestionList.size() - 1);
        assertThat(testGeoQuestion.getGeoQuestionId()).isEqualTo(DEFAULT_GEO_QUESTION_ID);
        assertThat(testGeoQuestion.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testGeoQuestion.getQuestion()).isEqualTo(DEFAULT_QUESTION);
        assertThat(testGeoQuestion.getReply()).isEqualTo(DEFAULT_REPLY);
        assertThat(testGeoQuestion.getTransferTime()).isEqualTo(DEFAULT_TRANSFER_TIME);
        assertThat(testGeoQuestion.getTransferFrom()).isEqualTo(DEFAULT_TRANSFER_FROM);
        assertThat(testGeoQuestion.getIsDel()).isEqualTo(DEFAULT_IS_DEL);
        assertThat(testGeoQuestion.getDelDate()).isEqualTo(DEFAULT_DEL_DATE);
        assertThat(testGeoQuestion.getDelAccId()).isEqualTo(DEFAULT_DEL_ACC_ID);
        assertThat(testGeoQuestion.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testGeoQuestion.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testGeoQuestion.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testGeoQuestion.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createGeoQuestionWithExistingId() throws Exception {
        // Create the GeoQuestion with an existing ID
        geoQuestion.setId(1L);
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(geoQuestion);

        int databaseSizeBeforeCreate = geoQuestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGeoQuestions() throws Exception {
        // Initialize the database
        geoQuestionRepository.saveAndFlush(geoQuestion);

        // Get all the geoQuestionList
        restGeoQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].geoQuestionId").value(hasItem(DEFAULT_GEO_QUESTION_ID)))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION)))
            .andExpect(jsonPath("$.[*].reply").value(hasItem(DEFAULT_REPLY)))
            .andExpect(jsonPath("$.[*].transferTime").value(hasItem(DEFAULT_TRANSFER_TIME.toString())))
            .andExpect(jsonPath("$.[*].transferFrom").value(hasItem(DEFAULT_TRANSFER_FROM)))
            .andExpect(jsonPath("$.[*].isDel").value(hasItem(DEFAULT_IS_DEL)))
            .andExpect(jsonPath("$.[*].delDate").value(hasItem(DEFAULT_DEL_DATE.toString())))
            .andExpect(jsonPath("$.[*].delAccId").value(hasItem(DEFAULT_DEL_ACC_ID)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getGeoQuestion() throws Exception {
        // Initialize the database
        geoQuestionRepository.saveAndFlush(geoQuestion);

        // Get the geoQuestion
        restGeoQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, geoQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoQuestion.getId().intValue()))
            .andExpect(jsonPath("$.geoQuestionId").value(DEFAULT_GEO_QUESTION_ID))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.question").value(DEFAULT_QUESTION))
            .andExpect(jsonPath("$.reply").value(DEFAULT_REPLY))
            .andExpect(jsonPath("$.transferTime").value(DEFAULT_TRANSFER_TIME.toString()))
            .andExpect(jsonPath("$.transferFrom").value(DEFAULT_TRANSFER_FROM))
            .andExpect(jsonPath("$.isDel").value(DEFAULT_IS_DEL))
            .andExpect(jsonPath("$.delDate").value(DEFAULT_DEL_DATE.toString()))
            .andExpect(jsonPath("$.delAccId").value(DEFAULT_DEL_ACC_ID))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingGeoQuestion() throws Exception {
        // Get the geoQuestion
        restGeoQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGeoQuestion() throws Exception {
        // Initialize the database
        geoQuestionRepository.saveAndFlush(geoQuestion);

        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();

        // Update the geoQuestion
        GeoQuestion updatedGeoQuestion = geoQuestionRepository.findById(geoQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedGeoQuestion are not directly saved in db
        em.detach(updatedGeoQuestion);
        updatedGeoQuestion
            .geoQuestionId(UPDATED_GEO_QUESTION_ID)
            .roleId(UPDATED_ROLE_ID)
            .question(UPDATED_QUESTION)
            .reply(UPDATED_REPLY)
            .transferTime(UPDATED_TRANSFER_TIME)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .isDel(UPDATED_IS_DEL)
            .delDate(UPDATED_DEL_DATE)
            .delAccId(UPDATED_DEL_ACC_ID)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(updatedGeoQuestion);

        restGeoQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, geoQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
        GeoQuestion testGeoQuestion = geoQuestionList.get(geoQuestionList.size() - 1);
        assertThat(testGeoQuestion.getGeoQuestionId()).isEqualTo(UPDATED_GEO_QUESTION_ID);
        assertThat(testGeoQuestion.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testGeoQuestion.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testGeoQuestion.getReply()).isEqualTo(UPDATED_REPLY);
        assertThat(testGeoQuestion.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
        assertThat(testGeoQuestion.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testGeoQuestion.getIsDel()).isEqualTo(UPDATED_IS_DEL);
        assertThat(testGeoQuestion.getDelDate()).isEqualTo(UPDATED_DEL_DATE);
        assertThat(testGeoQuestion.getDelAccId()).isEqualTo(UPDATED_DEL_ACC_ID);
        assertThat(testGeoQuestion.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testGeoQuestion.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testGeoQuestion.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testGeoQuestion.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingGeoQuestion() throws Exception {
        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();
        geoQuestion.setId(count.incrementAndGet());

        // Create the GeoQuestion
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(geoQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, geoQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGeoQuestion() throws Exception {
        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();
        geoQuestion.setId(count.incrementAndGet());

        // Create the GeoQuestion
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(geoQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGeoQuestion() throws Exception {
        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();
        geoQuestion.setId(count.incrementAndGet());

        // Create the GeoQuestion
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(geoQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoQuestionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGeoQuestionWithPatch() throws Exception {
        // Initialize the database
        geoQuestionRepository.saveAndFlush(geoQuestion);

        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();

        // Update the geoQuestion using partial update
        GeoQuestion partialUpdatedGeoQuestion = new GeoQuestion();
        partialUpdatedGeoQuestion.setId(geoQuestion.getId());

        partialUpdatedGeoQuestion
            .geoQuestionId(UPDATED_GEO_QUESTION_ID)
            .roleId(UPDATED_ROLE_ID)
            .question(UPDATED_QUESTION)
            .reply(UPDATED_REPLY)
            .transferTime(UPDATED_TRANSFER_TIME)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .isDel(UPDATED_IS_DEL)
            .delAccId(UPDATED_DEL_ACC_ID)
            .updateUser(UPDATED_UPDATE_USER);

        restGeoQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGeoQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGeoQuestion))
            )
            .andExpect(status().isOk());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
        GeoQuestion testGeoQuestion = geoQuestionList.get(geoQuestionList.size() - 1);
        assertThat(testGeoQuestion.getGeoQuestionId()).isEqualTo(UPDATED_GEO_QUESTION_ID);
        assertThat(testGeoQuestion.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testGeoQuestion.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testGeoQuestion.getReply()).isEqualTo(UPDATED_REPLY);
        assertThat(testGeoQuestion.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
        assertThat(testGeoQuestion.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testGeoQuestion.getIsDel()).isEqualTo(UPDATED_IS_DEL);
        assertThat(testGeoQuestion.getDelDate()).isEqualTo(DEFAULT_DEL_DATE);
        assertThat(testGeoQuestion.getDelAccId()).isEqualTo(UPDATED_DEL_ACC_ID);
        assertThat(testGeoQuestion.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testGeoQuestion.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testGeoQuestion.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testGeoQuestion.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateGeoQuestionWithPatch() throws Exception {
        // Initialize the database
        geoQuestionRepository.saveAndFlush(geoQuestion);

        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();

        // Update the geoQuestion using partial update
        GeoQuestion partialUpdatedGeoQuestion = new GeoQuestion();
        partialUpdatedGeoQuestion.setId(geoQuestion.getId());

        partialUpdatedGeoQuestion
            .geoQuestionId(UPDATED_GEO_QUESTION_ID)
            .roleId(UPDATED_ROLE_ID)
            .question(UPDATED_QUESTION)
            .reply(UPDATED_REPLY)
            .transferTime(UPDATED_TRANSFER_TIME)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .isDel(UPDATED_IS_DEL)
            .delDate(UPDATED_DEL_DATE)
            .delAccId(UPDATED_DEL_ACC_ID)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restGeoQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGeoQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGeoQuestion))
            )
            .andExpect(status().isOk());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
        GeoQuestion testGeoQuestion = geoQuestionList.get(geoQuestionList.size() - 1);
        assertThat(testGeoQuestion.getGeoQuestionId()).isEqualTo(UPDATED_GEO_QUESTION_ID);
        assertThat(testGeoQuestion.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testGeoQuestion.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testGeoQuestion.getReply()).isEqualTo(UPDATED_REPLY);
        assertThat(testGeoQuestion.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
        assertThat(testGeoQuestion.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testGeoQuestion.getIsDel()).isEqualTo(UPDATED_IS_DEL);
        assertThat(testGeoQuestion.getDelDate()).isEqualTo(UPDATED_DEL_DATE);
        assertThat(testGeoQuestion.getDelAccId()).isEqualTo(UPDATED_DEL_ACC_ID);
        assertThat(testGeoQuestion.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testGeoQuestion.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testGeoQuestion.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testGeoQuestion.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingGeoQuestion() throws Exception {
        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();
        geoQuestion.setId(count.incrementAndGet());

        // Create the GeoQuestion
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(geoQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, geoQuestionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGeoQuestion() throws Exception {
        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();
        geoQuestion.setId(count.incrementAndGet());

        // Create the GeoQuestion
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(geoQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGeoQuestion() throws Exception {
        int databaseSizeBeforeUpdate = geoQuestionRepository.findAll().size();
        geoQuestion.setId(count.incrementAndGet());

        // Create the GeoQuestion
        GeoQuestionDTO geoQuestionDTO = geoQuestionMapper.toDto(geoQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(geoQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GeoQuestion in the database
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGeoQuestion() throws Exception {
        // Initialize the database
        geoQuestionRepository.saveAndFlush(geoQuestion);

        int databaseSizeBeforeDelete = geoQuestionRepository.findAll().size();

        // Delete the geoQuestion
        restGeoQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, geoQuestion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoQuestion> geoQuestionList = geoQuestionRepository.findAll();
        assertThat(geoQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
