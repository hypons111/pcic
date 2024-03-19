package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmLineSendError;
import gov.pcc.pwc.repository.AdmLineSendErrorRepository;
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
 * Integration tests for the {@link AdmLineSendErrorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmLineSendErrorResourceIT {

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

    private static final String ENTITY_API_URL = "/api/adm-line-send-errors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmLineSendErrorRepository admLineSendErrorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmLineSendErrorMockMvc;

    private AdmLineSendError admLineSendError;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmLineSendError createEntity(EntityManager em) {
        AdmLineSendError admLineSendError = new AdmLineSendError()
            .realSendTime(DEFAULT_REAL_SEND_TIME)
            .sender(DEFAULT_SENDER)
            .receiver(DEFAULT_RECEIVER)
            .message(DEFAULT_MESSAGE)
            .errorMessage(DEFAULT_ERROR_MESSAGE)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admLineSendError;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmLineSendError createUpdatedEntity(EntityManager em) {
        AdmLineSendError admLineSendError = new AdmLineSendError()
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admLineSendError;
    }

    @BeforeEach
    public void initTest() {
        admLineSendError = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmLineSendError() throws Exception {
        int databaseSizeBeforeCreate = admLineSendErrorRepository.findAll().size();
        // Create the AdmLineSendError
        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isCreated());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeCreate + 1);
        AdmLineSendError testAdmLineSendError = admLineSendErrorList.get(admLineSendErrorList.size() - 1);
        assertThat(testAdmLineSendError.getRealSendTime()).isEqualTo(DEFAULT_REAL_SEND_TIME);
        assertThat(testAdmLineSendError.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmLineSendError.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmLineSendError.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testAdmLineSendError.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testAdmLineSendError.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmLineSendError.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmLineSendError.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmLineSendError.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmLineSendErrorWithExistingId() throws Exception {
        // Create the AdmLineSendError with an existing ID
        admLineSendError.setId(1L);

        int databaseSizeBeforeCreate = admLineSendErrorRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRealSendTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendErrorRepository.findAll().size();
        // set the field null
        admLineSendError.setRealSendTime(null);

        // Create the AdmLineSendError, which fails.

        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendErrorRepository.findAll().size();
        // set the field null
        admLineSendError.setSender(null);

        // Create the AdmLineSendError, which fails.

        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReceiverIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendErrorRepository.findAll().size();
        // set the field null
        admLineSendError.setReceiver(null);

        // Create the AdmLineSendError, which fails.

        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMessageIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendErrorRepository.findAll().size();
        // set the field null
        admLineSendError.setMessage(null);

        // Create the AdmLineSendError, which fails.

        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkErrorMessageIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendErrorRepository.findAll().size();
        // set the field null
        admLineSendError.setErrorMessage(null);

        // Create the AdmLineSendError, which fails.

        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendErrorRepository.findAll().size();
        // set the field null
        admLineSendError.setCreateUser(null);

        // Create the AdmLineSendError, which fails.

        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLineSendErrorRepository.findAll().size();
        // set the field null
        admLineSendError.setCreateTime(null);

        // Create the AdmLineSendError, which fails.

        restAdmLineSendErrorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmLineSendErrors() throws Exception {
        // Initialize the database
        admLineSendErrorRepository.saveAndFlush(admLineSendError);

        // Get all the admLineSendErrorList
        restAdmLineSendErrorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admLineSendError.getId().intValue())))
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
    void getAdmLineSendError() throws Exception {
        // Initialize the database
        admLineSendErrorRepository.saveAndFlush(admLineSendError);

        // Get the admLineSendError
        restAdmLineSendErrorMockMvc
            .perform(get(ENTITY_API_URL_ID, admLineSendError.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admLineSendError.getId().intValue()))
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
    void getNonExistingAdmLineSendError() throws Exception {
        // Get the admLineSendError
        restAdmLineSendErrorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmLineSendError() throws Exception {
        // Initialize the database
        admLineSendErrorRepository.saveAndFlush(admLineSendError);

        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();

        // Update the admLineSendError
        AdmLineSendError updatedAdmLineSendError = admLineSendErrorRepository.findById(admLineSendError.getId()).get();
        // Disconnect from session so that the updates on updatedAdmLineSendError are not directly saved in db
        em.detach(updatedAdmLineSendError);
        updatedAdmLineSendError
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmLineSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmLineSendError.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmLineSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmLineSendError testAdmLineSendError = admLineSendErrorList.get(admLineSendErrorList.size() - 1);
        assertThat(testAdmLineSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmLineSendError.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmLineSendError.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmLineSendError.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmLineSendError.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testAdmLineSendError.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmLineSendError.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmLineSendError.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmLineSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmLineSendError() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();
        admLineSendError.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmLineSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admLineSendError.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmLineSendError() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();
        admLineSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLineSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmLineSendError() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();
        admLineSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLineSendErrorMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmLineSendErrorWithPatch() throws Exception {
        // Initialize the database
        admLineSendErrorRepository.saveAndFlush(admLineSendError);

        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();

        // Update the admLineSendError using partial update
        AdmLineSendError partialUpdatedAdmLineSendError = new AdmLineSendError();
        partialUpdatedAdmLineSendError.setId(admLineSendError.getId());

        partialUpdatedAdmLineSendError.realSendTime(UPDATED_REAL_SEND_TIME).updateTime(UPDATED_UPDATE_TIME);

        restAdmLineSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmLineSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmLineSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmLineSendError testAdmLineSendError = admLineSendErrorList.get(admLineSendErrorList.size() - 1);
        assertThat(testAdmLineSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmLineSendError.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testAdmLineSendError.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testAdmLineSendError.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testAdmLineSendError.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testAdmLineSendError.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmLineSendError.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmLineSendError.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmLineSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmLineSendErrorWithPatch() throws Exception {
        // Initialize the database
        admLineSendErrorRepository.saveAndFlush(admLineSendError);

        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();

        // Update the admLineSendError using partial update
        AdmLineSendError partialUpdatedAdmLineSendError = new AdmLineSendError();
        partialUpdatedAdmLineSendError.setId(admLineSendError.getId());

        partialUpdatedAdmLineSendError
            .realSendTime(UPDATED_REAL_SEND_TIME)
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .message(UPDATED_MESSAGE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmLineSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmLineSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmLineSendError))
            )
            .andExpect(status().isOk());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
        AdmLineSendError testAdmLineSendError = admLineSendErrorList.get(admLineSendErrorList.size() - 1);
        assertThat(testAdmLineSendError.getRealSendTime()).isEqualTo(UPDATED_REAL_SEND_TIME);
        assertThat(testAdmLineSendError.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testAdmLineSendError.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testAdmLineSendError.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAdmLineSendError.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testAdmLineSendError.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmLineSendError.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmLineSendError.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmLineSendError.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmLineSendError() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();
        admLineSendError.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmLineSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admLineSendError.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmLineSendError() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();
        admLineSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLineSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmLineSendError() throws Exception {
        int databaseSizeBeforeUpdate = admLineSendErrorRepository.findAll().size();
        admLineSendError.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLineSendErrorMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admLineSendError))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmLineSendError in the database
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmLineSendError() throws Exception {
        // Initialize the database
        admLineSendErrorRepository.saveAndFlush(admLineSendError);

        int databaseSizeBeforeDelete = admLineSendErrorRepository.findAll().size();

        // Delete the admLineSendError
        restAdmLineSendErrorMockMvc
            .perform(delete(ENTITY_API_URL_ID, admLineSendError.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmLineSendError> admLineSendErrorList = admLineSendErrorRepository.findAll();
        assertThat(admLineSendErrorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
