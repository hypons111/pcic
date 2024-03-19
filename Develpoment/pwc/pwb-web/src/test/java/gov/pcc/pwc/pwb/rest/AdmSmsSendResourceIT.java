package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmSmsSend;
import gov.pcc.pwc.repository.AdmSmsSendRepository;
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
 * Integration tests for the {@link AdmSmsSendResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmSmsSendResourceIT {

    private static final String DEFAULT_SMS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SMS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/adm-sms-sends";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmSmsSendRepository admSmsSendRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmSmsSendMockMvc;

    private AdmSmsSend admSmsSend;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmSmsSend createEntity(EntityManager em) {
        AdmSmsSend admSmsSend = new AdmSmsSend()
            .smsType(DEFAULT_SMS_TYPE)
            .sourceId(DEFAULT_SOURCE_ID)
            .sender(DEFAULT_SENDER)
            .receiver(DEFAULT_RECEIVER)
            .supplier(DEFAULT_SUPPLIER)
            .message(DEFAULT_MESSAGE)
            .expectSendTime(DEFAULT_EXPECT_SEND_TIME)
            .realSendTime(DEFAULT_REAL_SEND_TIME)
            .status(DEFAULT_STATUS)
            .processMessage(DEFAULT_PROCESS_MESSAGE)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admSmsSend;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmSmsSend createUpdatedEntity(EntityManager em) {
        AdmSmsSend admSmsSend = new AdmSmsSend()
            .smsType(UPDATED_SMS_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .supplier(UPDATED_SUPPLIER)
            .message(UPDATED_MESSAGE)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admSmsSend;
    }

    @BeforeEach
    public void initTest() {
        admSmsSend = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmSmsSend() throws Exception {
        int databaseSizeBeforeCreate = admSmsSendRepository.findAll().size();
        // Create the AdmSmsSend
        restAdmSmsSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSend)))
            .andExpect(status().isCreated());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeCreate + 1);
        AdmSmsSend testAdmSmsSend = admSmsSendList.get(admSmsSendList.size() - 1);
        assertThat(testAdmSmsSend.getSmsType()).isEqualTo(DEFAULT_SMS_TYPE);
        assertThat(testAdmSmsSend.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testAdmSmsSend.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmSmsSend.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmSmsSend.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testAdmSmsSend.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testAdmSmsSend.getExpectSendTime()).isEqualTo(DEFAULT_EXPECT_SEND_TIME);
        assertThat(testAdmSmsSend.getRealSendTime()).isEqualTo(DEFAULT_REAL_SEND_TIME);
        assertThat(testAdmSmsSend.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmSmsSend.getProcessMessage()).isEqualTo(DEFAULT_PROCESS_MESSAGE);
        assertThat(testAdmSmsSend.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmSmsSend.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmSmsSend.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmSmsSend.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmSmsSendWithExistingId() throws Exception {
        // Create the AdmSmsSend with an existing ID
        admSmsSend.setId(1L);

        int databaseSizeBeforeCreate = admSmsSendRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmSmsSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSend)))
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSmsTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendRepository.findAll().size();
        // set the field null
        admSmsSend.setSmsType(null);

        // Create the AdmSmsSend, which fails.

        restAdmSmsSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSend)))
            .andExpect(status().isBadRequest());

        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSourceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendRepository.findAll().size();
        // set the field null
        admSmsSend.setSourceId(null);

        // Create the AdmSmsSend, which fails.

        restAdmSmsSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSend)))
            .andExpect(status().isBadRequest());

        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendRepository.findAll().size();
        // set the field null
        admSmsSend.setStatus(null);

        // Create the AdmSmsSend, which fails.

        restAdmSmsSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSend)))
            .andExpect(status().isBadRequest());

        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendRepository.findAll().size();
        // set the field null
        admSmsSend.setCreateUser(null);

        // Create the AdmSmsSend, which fails.

        restAdmSmsSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSend)))
            .andExpect(status().isBadRequest());

        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendRepository.findAll().size();
        // set the field null
        admSmsSend.setCreateTime(null);

        // Create the AdmSmsSend, which fails.

        restAdmSmsSendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSend)))
            .andExpect(status().isBadRequest());

        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmSmsSends() throws Exception {
        // Initialize the database
        admSmsSendRepository.saveAndFlush(admSmsSend);

        // Get all the admSmsSendList
        restAdmSmsSendMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admSmsSend.getId().intValue())))
            .andExpect(jsonPath("$.[*].smsType").value(hasItem(DEFAULT_SMS_TYPE)))
            .andExpect(jsonPath("$.[*].sourceId").value(hasItem(DEFAULT_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
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
    void getAdmSmsSend() throws Exception {
        // Initialize the database
        admSmsSendRepository.saveAndFlush(admSmsSend);

        // Get the admSmsSend
        restAdmSmsSendMockMvc
            .perform(get(ENTITY_API_URL_ID, admSmsSend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admSmsSend.getId().intValue()))
            .andExpect(jsonPath("$.smsType").value(DEFAULT_SMS_TYPE))
            .andExpect(jsonPath("$.sourceId").value(DEFAULT_SOURCE_ID))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
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
    void getNonExistingAdmSmsSend() throws Exception {
        // Get the admSmsSend
        restAdmSmsSendMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmSmsSend() throws Exception {
        // Initialize the database
        admSmsSendRepository.saveAndFlush(admSmsSend);

        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();

        // Update the admSmsSend
        AdmSmsSend updatedAdmSmsSend = admSmsSendRepository.findById(admSmsSend.getId()).get();
        // Disconnect from session so that the updates on updatedAdmSmsSend are not directly saved in db
        em.detach(updatedAdmSmsSend);
        updatedAdmSmsSend
            .smsType(UPDATED_SMS_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .supplier(UPDATED_SUPPLIER)
            .message(UPDATED_MESSAGE)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmSmsSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmSmsSend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmSmsSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
        AdmSmsSend testAdmSmsSend = admSmsSendList.get(admSmsSendList.size() - 1);
        assertThat(testAdmSmsSend.getSmsType()).isEqualTo(UPDATED_SMS_TYPE);
        assertThat(testAdmSmsSend.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmSmsSend.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmSmsSend.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmSmsSend.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testAdmSmsSend.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmSmsSend.getExpectSendTime()).isEqualTo(UPDATED_EXPECT_SEND_TIME);
        assertThat(testAdmSmsSend.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmSmsSend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmSmsSend.getProcessMessage()).isEqualTo(UPDATED_PROCESS_MESSAGE);
        assertThat(testAdmSmsSend.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmSmsSend.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmSmsSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmSmsSend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmSmsSend() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();
        admSmsSend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmSmsSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admSmsSend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmSmsSend() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();
        admSmsSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmSmsSendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmSmsSend() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();
        admSmsSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmSmsSendMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSend)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmSmsSendWithPatch() throws Exception {
        // Initialize the database
        admSmsSendRepository.saveAndFlush(admSmsSend);

        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();

        // Update the admSmsSend using partial update
        AdmSmsSend partialUpdatedAdmSmsSend = new AdmSmsSend();
        partialUpdatedAdmSmsSend.setId(admSmsSend.getId());

        partialUpdatedAdmSmsSend
            .sourceId(UPDATED_SOURCE_ID)
            .supplier(UPDATED_SUPPLIER)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmSmsSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmSmsSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmSmsSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
        AdmSmsSend testAdmSmsSend = admSmsSendList.get(admSmsSendList.size() - 1);
        assertThat(testAdmSmsSend.getSmsType()).isEqualTo(DEFAULT_SMS_TYPE);
        assertThat(testAdmSmsSend.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmSmsSend.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmSmsSend.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmSmsSend.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testAdmSmsSend.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testAdmSmsSend.getExpectSendTime()).isEqualTo(DEFAULT_EXPECT_SEND_TIME);
        assertThat(testAdmSmsSend.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmSmsSend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmSmsSend.getProcessMessage()).isEqualTo(UPDATED_PROCESS_MESSAGE);
        assertThat(testAdmSmsSend.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmSmsSend.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmSmsSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmSmsSend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmSmsSendWithPatch() throws Exception {
        // Initialize the database
        admSmsSendRepository.saveAndFlush(admSmsSend);

        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();

        // Update the admSmsSend using partial update
        AdmSmsSend partialUpdatedAdmSmsSend = new AdmSmsSend();
        partialUpdatedAdmSmsSend.setId(admSmsSend.getId());

        partialUpdatedAdmSmsSend
            .smsType(UPDATED_SMS_TYPE)
            .sourceId(UPDATED_SOURCE_ID)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .supplier(UPDATED_SUPPLIER)
            .message(UPDATED_MESSAGE)
            .expectSendTime(UPDATED_EXPECT_SEND_TIME)
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .status(UPDATED_STATUS)
            .processMessage(UPDATED_PROCESS_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmSmsSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmSmsSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmSmsSend))
            )
            .andExpect(status().isOk());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
        AdmSmsSend testAdmSmsSend = admSmsSendList.get(admSmsSendList.size() - 1);
        assertThat(testAdmSmsSend.getSmsType()).isEqualTo(UPDATED_SMS_TYPE);
        assertThat(testAdmSmsSend.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testAdmSmsSend.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmSmsSend.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmSmsSend.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testAdmSmsSend.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmSmsSend.getExpectSendTime()).isEqualTo(UPDATED_EXPECT_SEND_TIME);
        assertThat(testAdmSmsSend.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmSmsSend.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmSmsSend.getProcessMessage()).isEqualTo(UPDATED_PROCESS_MESSAGE);
        assertThat(testAdmSmsSend.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmSmsSend.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmSmsSend.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmSmsSend.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmSmsSend() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();
        admSmsSend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmSmsSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admSmsSend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmSmsSend() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();
        admSmsSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmSmsSendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSend))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmSmsSend() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendRepository.findAll().size();
        admSmsSend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmSmsSendMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admSmsSend))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmSmsSend in the database
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmSmsSend() throws Exception {
        // Initialize the database
        admSmsSendRepository.saveAndFlush(admSmsSend);

        int databaseSizeBeforeDelete = admSmsSendRepository.findAll().size();

        // Delete the admSmsSend
        restAdmSmsSendMockMvc
            .perform(delete(ENTITY_API_URL_ID, admSmsSend.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmSmsSend> admSmsSendList = admSmsSendRepository.findAll();
        assertThat(admSmsSendList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
