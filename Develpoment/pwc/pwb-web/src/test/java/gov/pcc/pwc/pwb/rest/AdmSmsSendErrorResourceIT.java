package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmSmsSendError;
import gov.pcc.pwc.repository.AdmSmsSendErrorRepository;
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
 * Integration tests for the {@link AdmSmsSendErrorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmSmsSendErrorResourceIT {

    private static final Instant DEFAULT_REAL_SEND_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REAL_SEND_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/adm-sms-send-errors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmSmsSendErrorRepository admSmsSendErrorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmSmsSendErrorMockMvc;

    private AdmSmsSendError admSmsSendError;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmSmsSendError createEntity(EntityManager em) {
        AdmSmsSendError admSmsSendError = new AdmSmsSendError()
            .realSendTime(DEFAULT_REAL_SEND_TIME)
            .sender(DEFAULT_SENDER)
            .receiver(DEFAULT_RECEIVER)
            .message(DEFAULT_MESSAGE)
            .errorMessage(DEFAULT_ERROR_MESSAGE)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admSmsSendError;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmSmsSendError createUpdatedEntity(EntityManager em) {
        AdmSmsSendError admSmsSendError = new AdmSmsSendError()
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admSmsSendError;
    }

    @BeforeEach
    public void initTest() {
        admSmsSendError = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmSmsSendError() throws Exception {
        int databaseSizeBeforeCreate = admSmsSendErrorRepository.findAll().size();
        // Create the AdmSmsSendError
        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isCreated());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeCreate + 1);
        AdmSmsSendError testAdmSmsSendError = admSmsSendErrorList.get(admSmsSendErrorList.size() - 1);
        assertThat(testAdmSmsSendError.getRealSendTime()).isEqualTo(DEFAULT_REAL_SEND_TIME);
        assertThat(testAdmSmsSendError.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmSmsSendError.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmSmsSendError.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testAdmSmsSendError.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testAdmSmsSendError.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmSmsSendError.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmSmsSendError.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmSmsSendError.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmSmsSendErrorWithExistingId() throws Exception {
        // Create the AdmSmsSendError with an existing ID
        admSmsSendError.setId(1L);

        int databaseSizeBeforeCreate = admSmsSendErrorRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRealSendTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendErrorRepository.findAll().size();
        // set the field null
        admSmsSendError.setRealSendTime(null);

        // Create the AdmSmsSendError, which fails.

        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendErrorRepository.findAll().size();
        // set the field null
        admSmsSendError.setSender(null);

        // Create the AdmSmsSendError, which fails.

        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReceiverIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendErrorRepository.findAll().size();
        // set the field null
        admSmsSendError.setReceiver(null);

        // Create the AdmSmsSendError, which fails.

        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMessageIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendErrorRepository.findAll().size();
        // set the field null
        admSmsSendError.setMessage(null);

        // Create the AdmSmsSendError, which fails.

        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkErrorMessageIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendErrorRepository.findAll().size();
        // set the field null
        admSmsSendError.setErrorMessage(null);

        // Create the AdmSmsSendError, which fails.

        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendErrorRepository.findAll().size();
        // set the field null
        admSmsSendError.setCreateUser(null);

        // Create the AdmSmsSendError, which fails.

        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSmsSendErrorRepository.findAll().size();
        // set the field null
        admSmsSendError.setCreateTime(null);

        // Create the AdmSmsSendError, which fails.

        restAdmSmsSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmSmsSendErrors() throws Exception {
        // Initialize the database
        admSmsSendErrorRepository.saveAndFlush(admSmsSendError);

        // Get all the admSmsSendErrorList
        restAdmSmsSendErrorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admSmsSendError.getId().intValue())))
            .andExpect(jsonPath("$.[*].realSendTime").value(hasItem(DEFAULT_REAL_SEND_TIME.toString())))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].errorMessage").value(hasItem(DEFAULT_ERROR_MESSAGE)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmSmsSendError() throws Exception {
        // Initialize the database
        admSmsSendErrorRepository.saveAndFlush(admSmsSendError);

        // Get the admSmsSendError
        restAdmSmsSendErrorMockMvc
            .perform(get(ENTITY_API_URL_ID, admSmsSendError.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admSmsSendError.getId().intValue()))
            .andExpect(jsonPath("$.realSendTime").value(DEFAULT_REAL_SEND_TIME.toString()))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.errorMessage").value(DEFAULT_ERROR_MESSAGE))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmSmsSendError() throws Exception {
        // Get the admSmsSendError
        restAdmSmsSendErrorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmSmsSendError() throws Exception {
        // Initialize the database
        admSmsSendErrorRepository.saveAndFlush(admSmsSendError);

        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();

        // Update the admSmsSendError
        AdmSmsSendError updatedAdmSmsSendError = admSmsSendErrorRepository.findById(admSmsSendError.getId()).get();
        // Disconnect from session so that the updates on updatedAdmSmsSendError are not directly saved in db
        em.detach(updatedAdmSmsSendError);
        updatedAdmSmsSendError
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmSmsSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmSmsSendError.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmSmsSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmSmsSendError testAdmSmsSendError = admSmsSendErrorList.get(admSmsSendErrorList.size() - 1);
        assertThat(testAdmSmsSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmSmsSendError.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmSmsSendError.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmSmsSendError.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmSmsSendError.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testAdmSmsSendError.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmSmsSendError.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmSmsSendError.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmSmsSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmSmsSendError() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();
        admSmsSendError.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmSmsSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admSmsSendError.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmSmsSendError() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();
        admSmsSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmSmsSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmSmsSendError() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();
        admSmsSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmSmsSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmSmsSendErrorWithPatch() throws Exception {
        // Initialize the database
        admSmsSendErrorRepository.saveAndFlush(admSmsSendError);

        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();

        // Update the admSmsSendError using partial update
        AdmSmsSendError partialUpdatedAdmSmsSendError = new AdmSmsSendError();
        partialUpdatedAdmSmsSendError.setId(admSmsSendError.getId());

        partialUpdatedAdmSmsSendError
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .message(UPDATED_MESSAGE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmSmsSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmSmsSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmSmsSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmSmsSendError testAdmSmsSendError = admSmsSendErrorList.get(admSmsSendErrorList.size() - 1);
        assertThat(testAdmSmsSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmSmsSendError.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmSmsSendError.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmSmsSendError.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmSmsSendError.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testAdmSmsSendError.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmSmsSendError.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmSmsSendError.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmSmsSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmSmsSendErrorWithPatch() throws Exception {
        // Initialize the database
        admSmsSendErrorRepository.saveAndFlush(admSmsSendError);

        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();

        // Update the admSmsSendError using partial update
        AdmSmsSendError partialUpdatedAdmSmsSendError = new AdmSmsSendError();
        partialUpdatedAdmSmsSendError.setId(admSmsSendError.getId());

        partialUpdatedAdmSmsSendError
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmSmsSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmSmsSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmSmsSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmSmsSendError testAdmSmsSendError = admSmsSendErrorList.get(admSmsSendErrorList.size() - 1);
        assertThat(testAdmSmsSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmSmsSendError.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmSmsSendError.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmSmsSendError.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmSmsSendError.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testAdmSmsSendError.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmSmsSendError.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmSmsSendError.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmSmsSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmSmsSendError() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();
        admSmsSendError.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmSmsSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admSmsSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmSmsSendError() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();
        admSmsSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmSmsSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmSmsSendError() throws Exception {
        int databaseSizeBeforeUpdate = admSmsSendErrorRepository.findAll().size();
        admSmsSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmSmsSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admSmsSendError))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmSmsSendError in the database
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmSmsSendError() throws Exception {
        // Initialize the database
        admSmsSendErrorRepository.saveAndFlush(admSmsSendError);

        int databaseSizeBeforeDelete = admSmsSendErrorRepository.findAll().size();

        // Delete the admSmsSendError
        restAdmSmsSendErrorMockMvc
            .perform(delete(ENTITY_API_URL_ID, admSmsSendError.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmSmsSendError> admSmsSendErrorList = admSmsSendErrorRepository.findAll();
        assertThat(admSmsSendErrorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
