package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.repository.AdmMailSendRepository;
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
 * Integration tests for the {@link AdmMailSendResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmMailSendResourceIT {

    private static final String DEFAULT_MAIL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/adm-mail-sends";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmMailSendRepository admMailSendRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmMailSendMockMvc;

    private AdmMailSend admMailSend;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmMailSend createEntity(EntityManager em) {
        AdmMailSend admMailSend = new AdmMailSend()
            .mailType(DEFAULT_MAIL_TYPE)
            .sourceId(DEFAULT_SOURCE_ID)
            .sender(DEFAULT_SENDER)
            .receiver(DEFAULT_RECEIVER)
            .subject(DEFAULT_SUBJECT)
            .content(DEFAULT_CONTENT)
            .expectSendTime(DEFAULT_EXPECT_SEND_TIME)
            .realSendTime(DEFAULT_REAL_SEND_TIME)
            .status(DEFAULT_STATUS)
            .processMessage(DEFAULT_PROCESS_MESSAGE)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admMailSend;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmMailSend createUpdatedEntity(EntityManager em) {
        AdmMailSend admMailSend = new AdmMailSend()
            .mailType(UPDATED_MAIL_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .subject(UPDATED_SUBJECT)
            .content(UPDATED_CONTENT)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admMailSend;
    }

    @BeforeEach
    public void initTest() {
        admMailSend = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmMailSend() throws Exception {
        int databaseSizeBeforeCreate = admMailSendRepository.findAll().size();
        // Create the AdmMailSend
        restAdmMailSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSend)))
            .andExpect(status().isCreated());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeCreate + 1);
        AdmMailSend testAdmMailSend = admMailSendList.get(admMailSendList.size() - 1);
        assertThat(testAdmMailSend.getMailType()).isEqualTo(DEFAULT_MAIL_TYPE);
        assertThat(testAdmMailSend.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testAdmMailSend.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmMailSend.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmMailSend.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testAdmMailSend.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testAdmMailSend.getExpectSendTime()).isEqualTo(DEFAULT_EXPECT_SEND_TIME);
        assertThat(testAdmMailSend.getRealSendTime()).isEqualTo(DEFAULT_REAL_SEND_TIME);
        assertThat(testAdmMailSend.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmMailSend.getProcessMessage()).isEqualTo(DEFAULT_PROCESS_MESSAGE);
        assertThat(testAdmMailSend.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmMailSend.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmMailSend.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmMailSend.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmMailSendWithExistingId() throws Exception {
        // Create the AdmMailSend with an existing ID
        admMailSend.setId(1L);

        int databaseSizeBeforeCreate = admMailSendRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmMailSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSend)))
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMailTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendRepository.findAll().size();
        // set the field null
        admMailSend.setMailType(null);

        // Create the AdmMailSend, which fails.

        restAdmMailSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSend)))
            .andExpect(status().isBadRequest());

        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSourceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendRepository.findAll().size();
        // set the field null
        admMailSend.setSourceId(null);

        // Create the AdmMailSend, which fails.

        restAdmMailSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSend)))
            .andExpect(status().isBadRequest());

        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendRepository.findAll().size();
        // set the field null
        admMailSend.setStatus(null);

        // Create the AdmMailSend, which fails.

        restAdmMailSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSend)))
            .andExpect(status().isBadRequest());

        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendRepository.findAll().size();
        // set the field null
        admMailSend.setCreateUser(null);

        // Create the AdmMailSend, which fails.

        restAdmMailSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSend)))
            .andExpect(status().isBadRequest());

        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendRepository.findAll().size();
        // set the field null
        admMailSend.setCreateTime(null);

        // Create the AdmMailSend, which fails.

        restAdmMailSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSend)))
            .andExpect(status().isBadRequest());

        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmMailSends() throws Exception {
        // Initialize the database
        admMailSendRepository.saveAndFlush(admMailSend);

        // Get all the admMailSendList
        restAdmMailSendMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admMailSend.getId().intValue())))
            .andExpect(jsonPath("$.[*].mailType").value(hasItem(DEFAULT_MAIL_TYPE)))
            .andExpect(jsonPath("$.[*].sourceId").value(hasItem(DEFAULT_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
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
    void getAdmMailSend() throws Exception {
        // Initialize the database
        admMailSendRepository.saveAndFlush(admMailSend);

        // Get the admMailSend
        restAdmMailSendMockMvc
            .perform(get(ENTITY_API_URL_ID, admMailSend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admMailSend.getId().intValue()))
            .andExpect(jsonPath("$.mailType").value(DEFAULT_MAIL_TYPE))
            .andExpect(jsonPath("$.sourceId").value(DEFAULT_SOURCE_ID))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
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
    void getNonExistingAdmMailSend() throws Exception {
        // Get the admMailSend
        restAdmMailSendMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmMailSend() throws Exception {
        // Initialize the database
        admMailSendRepository.saveAndFlush(admMailSend);

        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();

        // Update the admMailSend
        AdmMailSend updatedAdmMailSend = admMailSendRepository.findById(admMailSend.getId()).get();
        // Disconnect from session so that the updates on updatedAdmMailSend are not directly saved in db
        em.detach(updatedAdmMailSend);
        updatedAdmMailSend
            .mailType(UPDATED_MAIL_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .subject(UPDATED_SUBJECT)
            .content(UPDATED_CONTENT)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmMailSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmMailSend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmMailSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
        AdmMailSend testAdmMailSend = admMailSendList.get(admMailSendList.size() - 1);
        assertThat(testAdmMailSend.getMailType()).isEqualTo(UPDATED_MAIL_TYPE);
        assertThat(testAdmMailSend.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmMailSend.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmMailSend.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmMailSend.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testAdmMailSend.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testAdmMailSend.getExpectSendTime()).isEqualTo(UPDATED_EXPECT_SEND_TIME);
        assertThat(testAdmMailSend.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmMailSend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmMailSend.getProcessMessage()).isEqualTo(UPDATED_PROCESS_MESSAGE);
        assertThat(testAdmMailSend.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmMailSend.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmMailSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmMailSend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmMailSend() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();
        admMailSend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmMailSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admMailSend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admMailSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmMailSend() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();
        admMailSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmMailSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admMailSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmMailSend() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();
        admMailSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmMailSendMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSend)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmMailSendWithPatch() throws Exception {
        // Initialize the database
        admMailSendRepository.saveAndFlush(admMailSend);

        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();

        // Update the admMailSend using partial update
        AdmMailSend partialUpdatedAdmMailSend = new AdmMailSend();
        partialUpdatedAdmMailSend.setId(admMailSend.getId());

        partialUpdatedAdmMailSend
            .mailType(UPDATED_MAIL_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .subject(UPDATED_SUBJECT)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmMailSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmMailSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmMailSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
        AdmMailSend testAdmMailSend = admMailSendList.get(admMailSendList.size() - 1);
        assertThat(testAdmMailSend.getMailType()).isEqualTo(UPDATED_MAIL_TYPE);
        assertThat(testAdmMailSend.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmMailSend.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmMailSend.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmMailSend.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testAdmMailSend.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testAdmMailSend.getExpectSendTime()).isEqualTo(DEFAULT_EXPECT_SEND_TIME);
        assertThat(testAdmMailSend.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmMailSend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmMailSend.getProcessMessage()).isEqualTo(DEFAULT_PROCESS_MESSAGE);
        assertThat(testAdmMailSend.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmMailSend.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmMailSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmMailSend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmMailSendWithPatch() throws Exception {
        // Initialize the database
        admMailSendRepository.saveAndFlush(admMailSend);

        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();

        // Update the admMailSend using partial update
        AdmMailSend partialUpdatedAdmMailSend = new AdmMailSend();
        partialUpdatedAdmMailSend.setId(admMailSend.getId());

        partialUpdatedAdmMailSend
            .mailType(UPDATED_MAIL_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .subject(UPDATED_SUBJECT)
            .content(UPDATED_CONTENT)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmMailSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmMailSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmMailSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
        AdmMailSend testAdmMailSend = admMailSendList.get(admMailSendList.size() - 1);
        assertThat(testAdmMailSend.getMailType()).isEqualTo(UPDATED_MAIL_TYPE);
        assertThat(testAdmMailSend.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmMailSend.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmMailSend.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmMailSend.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testAdmMailSend.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testAdmMailSend.getExpectSendTime()).isEqualTo(UPDATED_EXPECT_SEND_TIME);
        assertThat(testAdmMailSend.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmMailSend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmMailSend.getProcessMessage()).isEqualTo(UPDATED_PROCESS_MESSAGE);
        assertThat(testAdmMailSend.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmMailSend.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmMailSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmMailSend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmMailSend() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();
        admMailSend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmMailSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admMailSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admMailSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmMailSend() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();
        admMailSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmMailSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admMailSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmMailSend() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendRepository.findAll().size();
        admMailSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmMailSendMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admMailSend))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmMailSend in the database
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmMailSend() throws Exception {
        // Initialize the database
        admMailSendRepository.saveAndFlush(admMailSend);

        int databaseSizeBeforeDelete = admMailSendRepository.findAll().size();

        // Delete the admMailSend
        restAdmMailSendMockMvc
            .perform(delete(ENTITY_API_URL_ID, admMailSend.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmMailSend> admMailSendList = admMailSendRepository.findAll();
        assertThat(admMailSendList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
