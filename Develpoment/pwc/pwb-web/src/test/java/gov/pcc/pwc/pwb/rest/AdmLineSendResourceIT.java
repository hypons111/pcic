package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmLineSend;
import gov.pcc.pwc.repository.AdmLineSendRepository;
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
 * Integration tests for the {@link AdmLineSendResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmLineSendResourceIT {

    private static final String DEFAULT_LINE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LINE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Instant DEFAULT_EXPECT_SEND_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPECT_SEND_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_REAL_SEND_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REAL_SEND_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final String DEFAULT_PROCESS_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_PROCESS_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-line-sends";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmLineSendRepository admLineSendRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmLineSendMockMvc;

    private AdmLineSend admLineSend;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmLineSend createEntity(EntityManager em) {
        AdmLineSend admLineSend = new AdmLineSend()
            .lineType(DEFAULT_LINE_TYPE)
            .sourceId(DEFAULT_SOURCE_ID)
            .sender(DEFAULT_SENDER)
            .receiver(DEFAULT_RECEIVER)
            .message(DEFAULT_MESSAGE)
            .expectSendTime(DEFAULT_EXPECT_SEND_TIME)
            .realSendTime(DEFAULT_REAL_SEND_TIME)
            .status(DEFAULT_STATUS)
            .processMessage(DEFAULT_PROCESS_MESSAGE)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admLineSend;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmLineSend createUpdatedEntity(EntityManager em) {
        AdmLineSend admLineSend = new AdmLineSend()
            .lineType(UPDATED_LINE_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admLineSend;
    }

    @BeforeEach
    public void initTest() {
        admLineSend = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmLineSend() throws Exception {
        int databaseSizeBeforeCreate = admLineSendRepository.findAll().size();
        // Create the AdmLineSend
        restAdmLineSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSend)))
            .andExpect(status().isCreated());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeCreate + 1);
        AdmLineSend testAdmLineSend = admLineSendList.get(admLineSendList.size() - 1);
        assertThat(testAdmLineSend.getLineType()).isEqualTo(DEFAULT_LINE_TYPE);
        assertThat(testAdmLineSend.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testAdmLineSend.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmLineSend.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmLineSend.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testAdmLineSend.getExpectSendTime()).isEqualTo(DEFAULT_EXPECT_SEND_TIME);
        assertThat(testAdmLineSend.getRealSendTime()).isEqualTo(DEFAULT_REAL_SEND_TIME);
        assertThat(testAdmLineSend.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmLineSend.getProcessMessage()).isEqualTo(DEFAULT_PROCESS_MESSAGE);
        assertThat(testAdmLineSend.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmLineSend.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmLineSend.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmLineSend.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmLineSendWithExistingId() throws Exception {
        // Create the AdmLineSend with an existing ID
        admLineSend.setId(1L);

        int databaseSizeBeforeCreate = admLineSendRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmLineSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSend)))
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLineTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendRepository.findAll().size();
        // set the field null
        admLineSend.setLineType(null);

        // Create the AdmLineSend, which fails.

        restAdmLineSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSend)))
            .andExpect(status().isBadRequest());

        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSourceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendRepository.findAll().size();
        // set the field null
        admLineSend.setSourceId(null);

        // Create the AdmLineSend, which fails.

        restAdmLineSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSend)))
            .andExpect(status().isBadRequest());

        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendRepository.findAll().size();
        // set the field null
        admLineSend.setStatus(null);

        // Create the AdmLineSend, which fails.

        restAdmLineSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSend)))
            .andExpect(status().isBadRequest());

        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendRepository.findAll().size();
        // set the field null
        admLineSend.setCreateUser(null);

        // Create the AdmLineSend, which fails.

        restAdmLineSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSend)))
            .andExpect(status().isBadRequest());

        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendRepository.findAll().size();
        // set the field null
        admLineSend.setCreateTime(null);

        // Create the AdmLineSend, which fails.

        restAdmLineSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSend)))
            .andExpect(status().isBadRequest());

        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmLineSends() throws Exception {
        // Initialize the database
        admLineSendRepository.saveAndFlush(admLineSend);

        // Get all the admLineSendList
        restAdmLineSendMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admLineSend.getId().intValue())))
            .andExpect(jsonPath("$.[*].lineType").value(hasItem(DEFAULT_LINE_TYPE)))
            .andExpect(jsonPath("$.[*].sourceId").value(hasItem(DEFAULT_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].expectSendTime").value(hasItem(DEFAULT_EXPECT_SEND_TIME.toString())))
            .andExpect(jsonPath("$.[*].realSendTime").value(hasItem(DEFAULT_REAL_SEND_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].processMessage").value(hasItem(DEFAULT_PROCESS_MESSAGE)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmLineSend() throws Exception {
        // Initialize the database
        admLineSendRepository.saveAndFlush(admLineSend);

        // Get the admLineSend
        restAdmLineSendMockMvc
            .perform(get(ENTITY_API_URL_ID, admLineSend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admLineSend.getId().intValue()))
            .andExpect(jsonPath("$.lineType").value(DEFAULT_LINE_TYPE))
            .andExpect(jsonPath("$.sourceId").value(DEFAULT_SOURCE_ID))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.expectSendTime").value(DEFAULT_EXPECT_SEND_TIME.toString()))
            .andExpect(jsonPath("$.realSendTime").value(DEFAULT_REAL_SEND_TIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.processMessage").value(DEFAULT_PROCESS_MESSAGE))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmLineSend() throws Exception {
        // Get the admLineSend
        restAdmLineSendMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmLineSend() throws Exception {
        // Initialize the database
        admLineSendRepository.saveAndFlush(admLineSend);

        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();

        // Update the admLineSend
        AdmLineSend updatedAdmLineSend = admLineSendRepository.findById(admLineSend.getId()).get();
        // Disconnect from session so that the updates on updatedAdmLineSend are not directly saved in db
        em.detach(updatedAdmLineSend);
        updatedAdmLineSend
            .lineType(UPDATED_LINE_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmLineSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmLineSend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmLineSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
        AdmLineSend testAdmLineSend = admLineSendList.get(admLineSendList.size() - 1);
        assertThat(testAdmLineSend.getLineType()).isEqualTo(UPDATED_LINE_TYPE);
        assertThat(testAdmLineSend.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmLineSend.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmLineSend.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmLineSend.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmLineSend.getExpectSendTime()).isEqualTo(UPDATED_EXPECT_SEND_TIME);
        assertThat(testAdmLineSend.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmLineSend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmLineSend.getProcessMessage()).isEqualTo(UPDATED_PROCESS_MESSAGE);
        assertThat(testAdmLineSend.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmLineSend.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmLineSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmLineSend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmLineSend() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();
        admLineSend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmLineSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admLineSend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admLineSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmLineSend() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();
        admLineSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLineSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admLineSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmLineSend() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();
        admLineSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLineSendMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSend)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmLineSendWithPatch() throws Exception {
        // Initialize the database
        admLineSendRepository.saveAndFlush(admLineSend);

        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();

        // Update the admLineSend using partial update
        AdmLineSend partialUpdatedAdmLineSend = new AdmLineSend();
        partialUpdatedAdmLineSend.setId(admLineSend.getId());

        partialUpdatedAdmLineSend
            .lineType(UPDATED_LINE_TYPE)
            .message(UPDATED_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);

        restAdmLineSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmLineSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmLineSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
        AdmLineSend testAdmLineSend = admLineSendList.get(admLineSendList.size() - 1);
        assertThat(testAdmLineSend.getLineType()).isEqualTo(UPDATED_LINE_TYPE);
        assertThat(testAdmLineSend.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testAdmLineSend.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmLineSend.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmLineSend.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmLineSend.getExpectSendTime()).isEqualTo(DEFAULT_EXPECT_SEND_TIME);
        assertThat(testAdmLineSend.getRealSendTime()).isEqualTo(DEFAULT_REAL_SEND_TIME);
        assertThat(testAdmLineSend.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmLineSend.getProcessMessage()).isEqualTo(DEFAULT_PROCESS_MESSAGE);
        assertThat(testAdmLineSend.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmLineSend.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmLineSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmLineSend.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmLineSendWithPatch() throws Exception {
        // Initialize the database
        admLineSendRepository.saveAndFlush(admLineSend);

        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();

        // Update the admLineSend using partial update
        AdmLineSend partialUpdatedAdmLineSend = new AdmLineSend();
        partialUpdatedAdmLineSend.setId(admLineSend.getId());

        partialUpdatedAdmLineSend
            .lineType(UPDATED_LINE_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmLineSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmLineSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmLineSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
        AdmLineSend testAdmLineSend = admLineSendList.get(admLineSendList.size() - 1);
        assertThat(testAdmLineSend.getLineType()).isEqualTo(UPDATED_LINE_TYPE);
        assertThat(testAdmLineSend.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmLineSend.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmLineSend.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmLineSend.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmLineSend.getExpectSendTime()).isEqualTo(UPDATED_EXPECT_SEND_TIME);
        assertThat(testAdmLineSend.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmLineSend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmLineSend.getProcessMessage()).isEqualTo(UPDATED_PROCESS_MESSAGE);
        assertThat(testAdmLineSend.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmLineSend.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmLineSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmLineSend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmLineSend() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();
        admLineSend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmLineSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admLineSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admLineSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmLineSend() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();
        admLineSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLineSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admLineSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmLineSend() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendRepository.findAll().size();
        admLineSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLineSendMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admLineSend))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmLineSend in the database
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmLineSend() throws Exception {
        // Initialize the database
        admLineSendRepository.saveAndFlush(admLineSend);

        int databaseSizeBeforeDelete = admLineSendRepository.findAll().size();

        // Delete the admLineSend
        restAdmLineSendMockMvc
            .perform(delete(ENTITY_API_URL_ID, admLineSend.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmLineSend> admLineSendList = admLineSendRepository.findAll();
        assertThat(admLineSendList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
