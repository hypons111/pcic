package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.ProOrgSuggestion;
import gov.pcc.pwc.repository.ProOrgSuggestionRepository;
import gov.pcc.pwc.service.dto.ProOrgSuggestionDTO;
import gov.pcc.pwc.service.mapper.ProOrgSuggestionMapper;
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
 * Integration tests for the {@link ProOrgSuggestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProOrgSuggestionResourceIT {

    private static final String DEFAULT_PRO_INNOVATIVE_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRO_INNOVATIVE_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "A";
    private static final String UPDATED_TYPE = "B";

    private static final String DEFAULT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_ADDR = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_OUTLINE = "AAAAAAAAAA";
    private static final String UPDATED_OUTLINE = "BBBBBBBBBB";

    private static final String DEFAULT_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_MEMO = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final String DEFAULT_COMM_REASON = "AAAAAAAAAA";
    private static final String UPDATED_COMM_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_WRITE_CONDITION = "A";
    private static final String UPDATED_WRITE_CONDITION = "B";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pro-org-suggestions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proOrgSuggestionId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProOrgSuggestionRepository proOrgSuggestionRepository;

    @Autowired
    private ProOrgSuggestionMapper proOrgSuggestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProOrgSuggestionMockMvc;

    private ProOrgSuggestion proOrgSuggestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProOrgSuggestion createEntity(EntityManager em) {
        ProOrgSuggestion proOrgSuggestion = new ProOrgSuggestion()
            .proInnovativeProductId(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID)
            .userId(DEFAULT_USER_ID)
            .type(DEFAULT_TYPE)
            .addr(DEFAULT_ADDR)
            .time(DEFAULT_TIME)
            .outline(DEFAULT_OUTLINE)
            .memo(DEFAULT_MEMO)
            .other(DEFAULT_OTHER)
            .status(DEFAULT_STATUS)
            .commReason(DEFAULT_COMM_REASON)
            .writeCondition(DEFAULT_WRITE_CONDITION)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return proOrgSuggestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProOrgSuggestion createUpdatedEntity(EntityManager em) {
        ProOrgSuggestion proOrgSuggestion = new ProOrgSuggestion()
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .userId(UPDATED_USER_ID)
            .type(UPDATED_TYPE)
            .addr(UPDATED_ADDR)
            .time(UPDATED_TIME)
            .outline(UPDATED_OUTLINE)
            .memo(UPDATED_MEMO)
            .other(UPDATED_OTHER)
            .status(UPDATED_STATUS)
            .commReason(UPDATED_COMM_REASON)
            .writeCondition(UPDATED_WRITE_CONDITION)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return proOrgSuggestion;
    }

    @BeforeEach
    public void initTest() {
        proOrgSuggestion = createEntity(em);
    }

    @Test
    @Transactional
    void createProOrgSuggestion() throws Exception {
        int databaseSizeBeforeCreate = proOrgSuggestionRepository.findAll().size();
        // Create the ProOrgSuggestion
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(proOrgSuggestion);
        restProOrgSuggestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeCreate + 1);
        ProOrgSuggestion testProOrgSuggestion = proOrgSuggestionList.get(proOrgSuggestionList.size() - 1);
        assertThat(testProOrgSuggestion.getProInnovativeProductId()).isEqualTo(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProOrgSuggestion.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testProOrgSuggestion.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProOrgSuggestion.getAddr()).isEqualTo(DEFAULT_ADDR);
        assertThat(testProOrgSuggestion.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testProOrgSuggestion.getOutline()).isEqualTo(DEFAULT_OUTLINE);
        assertThat(testProOrgSuggestion.getMemo()).isEqualTo(DEFAULT_MEMO);
        assertThat(testProOrgSuggestion.getOther()).isEqualTo(DEFAULT_OTHER);
        assertThat(testProOrgSuggestion.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProOrgSuggestion.getCommReason()).isEqualTo(DEFAULT_COMM_REASON);
        assertThat(testProOrgSuggestion.getWriteCondition()).isEqualTo(DEFAULT_WRITE_CONDITION);
        assertThat(testProOrgSuggestion.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProOrgSuggestion.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProOrgSuggestion.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProOrgSuggestion.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createProOrgSuggestionWithExistingId() throws Exception {
        // Create the ProOrgSuggestion with an existing ID
        proOrgSuggestion.setProOrgSuggestionId(1L);
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(proOrgSuggestion);

        int databaseSizeBeforeCreate = proOrgSuggestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProOrgSuggestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProOrgSuggestions() throws Exception {
        // Initialize the database
        proOrgSuggestionRepository.saveAndFlush(proOrgSuggestion);

        // Get all the proOrgSuggestionList
        restProOrgSuggestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proOrgSuggestionId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proOrgSuggestionId").value(hasItem(proOrgSuggestion.getProOrgSuggestionId().intValue())))
            .andExpect(jsonPath("$.[*].proInnovativeProductId").value(hasItem(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].addr").value(hasItem(DEFAULT_ADDR)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].outline").value(hasItem(DEFAULT_OUTLINE)))
            .andExpect(jsonPath("$.[*].memo").value(hasItem(DEFAULT_MEMO)))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].commReason").value(hasItem(DEFAULT_COMM_REASON)))
            .andExpect(jsonPath("$.[*].writeCondition").value(hasItem(DEFAULT_WRITE_CONDITION)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getProOrgSuggestion() throws Exception {
        // Initialize the database
        proOrgSuggestionRepository.saveAndFlush(proOrgSuggestion);

        // Get the proOrgSuggestion
        restProOrgSuggestionMockMvc
            .perform(get(ENTITY_API_URL_ID, proOrgSuggestion.getProOrgSuggestionId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proOrgSuggestionId").value(proOrgSuggestion.getProOrgSuggestionId().intValue()))
            .andExpect(jsonPath("$.proInnovativeProductId").value(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.addr").value(DEFAULT_ADDR))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.outline").value(DEFAULT_OUTLINE))
            .andExpect(jsonPath("$.memo").value(DEFAULT_MEMO))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.commReason").value(DEFAULT_COMM_REASON))
            .andExpect(jsonPath("$.writeCondition").value(DEFAULT_WRITE_CONDITION))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProOrgSuggestion() throws Exception {
        // Get the proOrgSuggestion
        restProOrgSuggestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProOrgSuggestion() throws Exception {
        // Initialize the database
        proOrgSuggestionRepository.saveAndFlush(proOrgSuggestion);

        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();

        // Update the proOrgSuggestion
        ProOrgSuggestion updatedProOrgSuggestion = proOrgSuggestionRepository.findById(proOrgSuggestion.getProOrgSuggestionId()).get();
        // Disconnect from session so that the updates on updatedProOrgSuggestion are not directly saved in db
        em.detach(updatedProOrgSuggestion);
        updatedProOrgSuggestion
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .userId(UPDATED_USER_ID)
            .type(UPDATED_TYPE)
            .addr(UPDATED_ADDR)
            .time(UPDATED_TIME)
            .outline(UPDATED_OUTLINE)
            .memo(UPDATED_MEMO)
            .other(UPDATED_OTHER)
            .status(UPDATED_STATUS)
            .commReason(UPDATED_COMM_REASON)
            .writeCondition(UPDATED_WRITE_CONDITION)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(updatedProOrgSuggestion);

        restProOrgSuggestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proOrgSuggestionDTO.getProOrgSuggestionId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
        ProOrgSuggestion testProOrgSuggestion = proOrgSuggestionList.get(proOrgSuggestionList.size() - 1);
        assertThat(testProOrgSuggestion.getProInnovativeProductId()).isEqualTo(UPDATED_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProOrgSuggestion.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProOrgSuggestion.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProOrgSuggestion.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testProOrgSuggestion.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testProOrgSuggestion.getOutline()).isEqualTo(UPDATED_OUTLINE);
        assertThat(testProOrgSuggestion.getMemo()).isEqualTo(UPDATED_MEMO);
        assertThat(testProOrgSuggestion.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testProOrgSuggestion.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProOrgSuggestion.getCommReason()).isEqualTo(UPDATED_COMM_REASON);
        assertThat(testProOrgSuggestion.getWriteCondition()).isEqualTo(UPDATED_WRITE_CONDITION);
        assertThat(testProOrgSuggestion.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProOrgSuggestion.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProOrgSuggestion.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProOrgSuggestion.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingProOrgSuggestion() throws Exception {
        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();
        proOrgSuggestion.setProOrgSuggestionId(count.incrementAndGet());

        // Create the ProOrgSuggestion
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(proOrgSuggestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProOrgSuggestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proOrgSuggestionDTO.getProOrgSuggestionId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProOrgSuggestion() throws Exception {
        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();
        proOrgSuggestion.setProOrgSuggestionId(count.incrementAndGet());

        // Create the ProOrgSuggestion
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(proOrgSuggestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProOrgSuggestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProOrgSuggestion() throws Exception {
        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();
        proOrgSuggestion.setProOrgSuggestionId(count.incrementAndGet());

        // Create the ProOrgSuggestion
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(proOrgSuggestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProOrgSuggestionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProOrgSuggestionWithPatch() throws Exception {
        // Initialize the database
        proOrgSuggestionRepository.saveAndFlush(proOrgSuggestion);

        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();

        // Update the proOrgSuggestion using partial update
        ProOrgSuggestion partialUpdatedProOrgSuggestion = new ProOrgSuggestion();
        partialUpdatedProOrgSuggestion.setProOrgSuggestionId(proOrgSuggestion.getProOrgSuggestionId());

        partialUpdatedProOrgSuggestion
            .userId(UPDATED_USER_ID)
            .addr(UPDATED_ADDR)
            .other(UPDATED_OTHER)
            .commReason(UPDATED_COMM_REASON)
            .writeCondition(UPDATED_WRITE_CONDITION)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restProOrgSuggestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProOrgSuggestion.getProOrgSuggestionId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProOrgSuggestion))
            )
            .andExpect(status().isOk());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
        ProOrgSuggestion testProOrgSuggestion = proOrgSuggestionList.get(proOrgSuggestionList.size() - 1);
        assertThat(testProOrgSuggestion.getProInnovativeProductId()).isEqualTo(DEFAULT_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProOrgSuggestion.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProOrgSuggestion.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProOrgSuggestion.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testProOrgSuggestion.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testProOrgSuggestion.getOutline()).isEqualTo(DEFAULT_OUTLINE);
        assertThat(testProOrgSuggestion.getMemo()).isEqualTo(DEFAULT_MEMO);
        assertThat(testProOrgSuggestion.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testProOrgSuggestion.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProOrgSuggestion.getCommReason()).isEqualTo(UPDATED_COMM_REASON);
        assertThat(testProOrgSuggestion.getWriteCondition()).isEqualTo(UPDATED_WRITE_CONDITION);
        assertThat(testProOrgSuggestion.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProOrgSuggestion.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProOrgSuggestion.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProOrgSuggestion.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateProOrgSuggestionWithPatch() throws Exception {
        // Initialize the database
        proOrgSuggestionRepository.saveAndFlush(proOrgSuggestion);

        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();

        // Update the proOrgSuggestion using partial update
        ProOrgSuggestion partialUpdatedProOrgSuggestion = new ProOrgSuggestion();
        partialUpdatedProOrgSuggestion.setProOrgSuggestionId(proOrgSuggestion.getProOrgSuggestionId());

        partialUpdatedProOrgSuggestion
            .proInnovativeProductId(UPDATED_PRO_INNOVATIVE_PRODUCT_ID)
            .userId(UPDATED_USER_ID)
            .type(UPDATED_TYPE)
            .addr(UPDATED_ADDR)
            .time(UPDATED_TIME)
            .outline(UPDATED_OUTLINE)
            .memo(UPDATED_MEMO)
            .other(UPDATED_OTHER)
            .status(UPDATED_STATUS)
            .commReason(UPDATED_COMM_REASON)
            .writeCondition(UPDATED_WRITE_CONDITION)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restProOrgSuggestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProOrgSuggestion.getProOrgSuggestionId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProOrgSuggestion))
            )
            .andExpect(status().isOk());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
        ProOrgSuggestion testProOrgSuggestion = proOrgSuggestionList.get(proOrgSuggestionList.size() - 1);
        assertThat(testProOrgSuggestion.getProInnovativeProductId()).isEqualTo(UPDATED_PRO_INNOVATIVE_PRODUCT_ID);
        assertThat(testProOrgSuggestion.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProOrgSuggestion.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProOrgSuggestion.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testProOrgSuggestion.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testProOrgSuggestion.getOutline()).isEqualTo(UPDATED_OUTLINE);
        assertThat(testProOrgSuggestion.getMemo()).isEqualTo(UPDATED_MEMO);
        assertThat(testProOrgSuggestion.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testProOrgSuggestion.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProOrgSuggestion.getCommReason()).isEqualTo(UPDATED_COMM_REASON);
        assertThat(testProOrgSuggestion.getWriteCondition()).isEqualTo(UPDATED_WRITE_CONDITION);
        assertThat(testProOrgSuggestion.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProOrgSuggestion.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProOrgSuggestion.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProOrgSuggestion.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingProOrgSuggestion() throws Exception {
        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();
        proOrgSuggestion.setProOrgSuggestionId(count.incrementAndGet());

        // Create the ProOrgSuggestion
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(proOrgSuggestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProOrgSuggestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, proOrgSuggestionDTO.getProOrgSuggestionId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProOrgSuggestion() throws Exception {
        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();
        proOrgSuggestion.setProOrgSuggestionId(count.incrementAndGet());

        // Create the ProOrgSuggestion
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(proOrgSuggestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProOrgSuggestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProOrgSuggestion() throws Exception {
        int databaseSizeBeforeUpdate = proOrgSuggestionRepository.findAll().size();
        proOrgSuggestion.setProOrgSuggestionId(count.incrementAndGet());

        // Create the ProOrgSuggestion
        ProOrgSuggestionDTO proOrgSuggestionDTO = proOrgSuggestionMapper.toDto(proOrgSuggestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProOrgSuggestionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proOrgSuggestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProOrgSuggestion in the database
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProOrgSuggestion() throws Exception {
        // Initialize the database
        proOrgSuggestionRepository.saveAndFlush(proOrgSuggestion);

        int databaseSizeBeforeDelete = proOrgSuggestionRepository.findAll().size();

        // Delete the proOrgSuggestion
        restProOrgSuggestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, proOrgSuggestion.getProOrgSuggestionId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProOrgSuggestion> proOrgSuggestionList = proOrgSuggestionRepository.findAll();
        assertThat(proOrgSuggestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
