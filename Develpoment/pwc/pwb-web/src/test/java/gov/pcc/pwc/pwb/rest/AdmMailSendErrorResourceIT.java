package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmMailSendError;
import gov.pcc.pwc.repository.AdmMailSendErrorRepository;
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
 * Integration tests for the {@link AdmMailSendErrorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmMailSendErrorResourceIT {

    private static final Instant DEFAULT_REAL_SEND_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REAL_SEND_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-mail-send-errors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmMailSendErrorRepository admMailSendErrorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmMailSendErrorMockMvc;

    private AdmMailSendError admMailSendError;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmMailSendError createEntity(EntityManager em) {
        AdmMailSendError admMailSendError = new AdmMailSendError()
            .realSendTime(DEFAULT_REAL_SEND_TIME)
            .sender(DEFAULT_SENDER)
            .receiver(DEFAULT_RECEIVER)
            .subject(DEFAULT_SUBJECT)
            .content(DEFAULT_CONTENT)
            .errorMessage(DEFAULT_ERROR_MESSAGE)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admMailSendError;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmMailSendError createUpdatedEntity(EntityManager em) {
        AdmMailSendError admMailSendError = new AdmMailSendError()
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .subject(UPDATED_SUBJECT)
            .content(UPDATED_CONTENT)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admMailSendError;
    }

    @BeforeEach
    public void initTest() {
        admMailSendError = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmMailSendError() throws Exception {
        int databaseSizeBeforeCreate = admMailSendErrorRepository.findAll().size();
        // Create the AdmMailSendError
        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isCreated());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeCreate + 1);
        AdmMailSendError testAdmMailSendError = admMailSendErrorList.get(admMailSendErrorList.size() - 1);
        assertThat(testAdmMailSendError.getRealSendTime()).isEqualTo(DEFAULT_REAL_SEND_TIME);
        assertThat(testAdmMailSendError.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmMailSendError.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmMailSendError.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testAdmMailSendError.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testAdmMailSendError.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testAdmMailSendError.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmMailSendError.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmMailSendError.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmMailSendError.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmMailSendErrorWithExistingId() throws Exception {
        // Create the AdmMailSendError with an existing ID
        admMailSendError.setId(1L);

        int databaseSizeBeforeCreate = admMailSendErrorRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRealSendTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendErrorRepository.findAll().size();
        // set the field null
        admMailSendError.setRealSendTime(null);

        // Create the AdmMailSendError, which fails.

        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendErrorRepository.findAll().size();
        // set the field null
        admMailSendError.setSender(null);

        // Create the AdmMailSendError, which fails.

        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReceiverIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendErrorRepository.findAll().size();
        // set the field null
        admMailSendError.setReceiver(null);

        // Create the AdmMailSendError, which fails.

        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubjectIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendErrorRepository.findAll().size();
        // set the field null
        admMailSendError.setSubject(null);

        // Create the AdmMailSendError, which fails.

        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendErrorRepository.findAll().size();
        // set the field null
        admMailSendError.setContent(null);

        // Create the AdmMailSendError, which fails.

        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkErrorMessageIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendErrorRepository.findAll().size();
        // set the field null
        admMailSendError.setErrorMessage(null);

        // Create the AdmMailSendError, which fails.

        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendErrorRepository.findAll().size();
        // set the field null
        admMailSendError.setCreateUser(null);

        // Create the AdmMailSendError, which fails.

        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admMailSendErrorRepository.findAll().size();
        // set the field null
        admMailSendError.setCreateTime(null);

        // Create the AdmMailSendError, which fails.

        restAdmMailSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmMailSendErrors() throws Exception {
        // Initialize the database
        admMailSendErrorRepository.saveAndFlush(admMailSendError);

        // Get all the admMailSendErrorList
        restAdmMailSendErrorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admMailSendError.getId().intValue())))
            .andExpect(jsonPath("$.[*].realSendTime").value(hasItem(DEFAULT_REAL_SEND_TIME.toString())))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].errorMessage").value(hasItem(DEFAULT_ERROR_MESSAGE)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmMailSendError() throws Exception {
        // Initialize the database
        admMailSendErrorRepository.saveAndFlush(admMailSendError);

        // Get the admMailSendError
        restAdmMailSendErrorMockMvc
            .perform(get(ENTITY_API_URL_ID, admMailSendError.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admMailSendError.getId().intValue()))
            .andExpect(jsonPath("$.realSendTime").value(DEFAULT_REAL_SEND_TIME.toString()))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.errorMessage").value(DEFAULT_ERROR_MESSAGE))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmMailSendError() throws Exception {
        // Get the admMailSendError
        restAdmMailSendErrorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmMailSendError() throws Exception {
        // Initialize the database
        admMailSendErrorRepository.saveAndFlush(admMailSendError);

        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();

        // Update the admMailSendError
        AdmMailSendError updatedAdmMailSendError = admMailSendErrorRepository.findById(admMailSendError.getId()).get();
        // Disconnect from session so that the updates on updatedAdmMailSendError are not directly saved in db
        em.detach(updatedAdmMailSendError);
        updatedAdmMailSendError
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .subject(UPDATED_SUBJECT)
            .content(UPDATED_CONTENT)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmMailSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmMailSendError.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmMailSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmMailSendError testAdmMailSendError = admMailSendErrorList.get(admMailSendErrorList.size() - 1);
        assertThat(testAdmMailSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmMailSendError.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmMailSendError.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmMailSendError.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testAdmMailSendError.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testAdmMailSendError.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testAdmMailSendError.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmMailSendError.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmMailSendError.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmMailSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmMailSendError() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();
        admMailSendError.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmMailSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admMailSendError.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmMailSendError() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();
        admMailSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmMailSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmMailSendError() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();
        admMailSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmMailSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmMailSendErrorWithPatch() throws Exception {
        // Initialize the database
        admMailSendErrorRepository.saveAndFlush(admMailSendError);

        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();

        // Update the admMailSendError using partial update
        AdmMailSendError partialUpdatedAdmMailSendError = new AdmMailSendError();
        partialUpdatedAdmMailSendError.setId(admMailSendError.getId());

        partialUpdatedAdmMailSendError
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .subject(UPDATED_SUBJECT)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmMailSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmMailSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmMailSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmMailSendError testAdmMailSendError = admMailSendErrorList.get(admMailSendErrorList.size() - 1);
        assertThat(testAdmMailSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmMailSendError.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmMailSendError.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmMailSendError.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testAdmMailSendError.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testAdmMailSendError.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testAdmMailSendError.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmMailSendError.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmMailSendError.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmMailSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmMailSendErrorWithPatch() throws Exception {
        // Initialize the database
        admMailSendErrorRepository.saveAndFlush(admMailSendError);

        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();

        // Update the admMailSendError using partial update
        AdmMailSendError partialUpdatedAdmMailSendError = new AdmMailSendError();
        partialUpdatedAdmMailSendError.setId(admMailSendError.getId());

        partialUpdatedAdmMailSendError
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .subject(UPDATED_SUBJECT)
            .content(UPDATED_CONTENT)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmMailSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmMailSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmMailSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmMailSendError testAdmMailSendError = admMailSendErrorList.get(admMailSendErrorList.size() - 1);
        assertThat(testAdmMailSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmMailSendError.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmMailSendError.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmMailSendError.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testAdmMailSendError.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testAdmMailSendError.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testAdmMailSendError.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmMailSendError.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmMailSendError.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmMailSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmMailSendError() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();
        admMailSendError.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmMailSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admMailSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmMailSendError() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();
        admMailSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmMailSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmMailSendError() throws Exception {
        int databaseSizeBeforeUpdate = admMailSendErrorRepository.findAll().size();
        admMailSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmMailSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admMailSendError))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmMailSendError in the database
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmMailSendError() throws Exception {
        // Initialize the database
        admMailSendErrorRepository.saveAndFlush(admMailSendError);

        int databaseSizeBeforeDelete = admMailSendErrorRepository.findAll().size();

        // Delete the admMailSendError
        restAdmMailSendErrorMockMvc
            .perform(delete(ENTITY_API_URL_ID, admMailSendError.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmMailSendError> admMailSendErrorList = admMailSendErrorRepository.findAll();
        assertThat(admMailSendErrorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
