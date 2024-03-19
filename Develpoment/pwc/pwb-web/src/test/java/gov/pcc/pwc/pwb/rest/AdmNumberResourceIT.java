package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmNumber;
import gov.pcc.pwc.repository.AdmNumberRepository;
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
 * Integration tests for the {@link AdmNumberResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmNumberResourceIT {

    private static final String DEFAULT_CODE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_YEAR = "AAA";
    private static final String UPDATED_CODE_YEAR = "BBB";

    private static final String DEFAULT_CODE_MONTH = "AA";
    private static final String UPDATED_CODE_MONTH = "BB";

    private static final String DEFAULT_CODE_DATE = "AA";
    private static final String UPDATED_CODE_DATE = "BB";

    private static final String DEFAULT_NUMBER = "AAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-numbers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmNumberRepository admNumberRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmNumberMockMvc;

    private AdmNumber admNumber;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmNumber createEntity(EntityManager em) {
        AdmNumber admNumber = new AdmNumber()
            .codeType(DEFAULT_CODE_TYPE)
            .codeYear(DEFAULT_CODE_YEAR)
            .codeMonth(DEFAULT_CODE_MONTH)
            .codeDate(DEFAULT_CODE_DATE)
            .number(DEFAULT_NUMBER)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admNumber;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmNumber createUpdatedEntity(EntityManager em) {
        AdmNumber admNumber = new AdmNumber()
            .codeType(UPDATED_CODE_TYPE)
            .codeYear(UPDATED_CODE_YEAR)
            .codeMonth(UPDATED_CODE_MONTH)
            .codeDate(UPDATED_CODE_DATE)
            .number(UPDATED_NUMBER)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admNumber;
    }

    @BeforeEach
    public void initTest() {
        admNumber = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmNumber() throws Exception {
        int databaseSizeBeforeCreate = admNumberRepository.findAll().size();
        // Create the AdmNumber
        restAdmNumberMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admNumber)))
            .andExpect(status().isCreated());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeCreate + 1);
        AdmNumber testAdmNumber = admNumberList.get(admNumberList.size() - 1);
        assertThat(testAdmNumber.getCodeType()).isEqualTo(DEFAULT_CODE_TYPE);
        assertThat(testAdmNumber.getCodeYear()).isEqualTo(DEFAULT_CODE_YEAR);
        assertThat(testAdmNumber.getCodeMonth()).isEqualTo(DEFAULT_CODE_MONTH);
        assertThat(testAdmNumber.getCodeDate()).isEqualTo(DEFAULT_CODE_DATE);
        assertThat(testAdmNumber.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testAdmNumber.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmNumber.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmNumber.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmNumber.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmNumberWithExistingId() throws Exception {
        // Create the AdmNumber with an existing ID
        admNumber.setId(1L);

        int databaseSizeBeforeCreate = admNumberRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmNumberMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admNumber)))
            .andExpect(status().isBadRequest());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = admNumberRepository.findAll().size();
        // set the field null
        admNumber.setNumber(null);

        // Create the AdmNumber, which fails.

        restAdmNumberMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admNumber)))
            .andExpect(status().isBadRequest());

        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admNumberRepository.findAll().size();
        // set the field null
        admNumber.setCreateUser(null);

        // Create the AdmNumber, which fails.

        restAdmNumberMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admNumber)))
            .andExpect(status().isBadRequest());

        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admNumberRepository.findAll().size();
        // set the field null
        admNumber.setCreateTime(null);

        // Create the AdmNumber, which fails.

        restAdmNumberMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admNumber)))
            .andExpect(status().isBadRequest());

        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmNumbers() throws Exception {
        // Initialize the database
        admNumberRepository.saveAndFlush(admNumber);

        // Get all the admNumberList
        restAdmNumberMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admNumber.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeType").value(hasItem(DEFAULT_CODE_TYPE)))
            .andExpect(jsonPath("$.[*].codeYear").value(hasItem(DEFAULT_CODE_YEAR)))
            .andExpect(jsonPath("$.[*].codeMonth").value(hasItem(DEFAULT_CODE_MONTH)))
            .andExpect(jsonPath("$.[*].codeDate").value(hasItem(DEFAULT_CODE_DATE)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmNumber() throws Exception {
        // Initialize the database
        admNumberRepository.saveAndFlush(admNumber);

        // Get the admNumber
        restAdmNumberMockMvc
            .perform(get(ENTITY_API_URL_ID, admNumber.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admNumber.getId().intValue()))
            .andExpect(jsonPath("$.codeType").value(DEFAULT_CODE_TYPE))
            .andExpect(jsonPath("$.codeYear").value(DEFAULT_CODE_YEAR))
            .andExpect(jsonPath("$.codeMonth").value(DEFAULT_CODE_MONTH))
            .andExpect(jsonPath("$.codeDate").value(DEFAULT_CODE_DATE))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmNumber() throws Exception {
        // Get the admNumber
        restAdmNumberMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmNumber() throws Exception {
        // Initialize the database
        admNumberRepository.saveAndFlush(admNumber);

        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();

        // Update the admNumber
        AdmNumber updatedAdmNumber = admNumberRepository.findById(admNumber.getId()).get();
        // Disconnect from session so that the updates on updatedAdmNumber are not directly saved in db
        em.detach(updatedAdmNumber);
        updatedAdmNumber
            .codeType(UPDATED_CODE_TYPE)
            .codeYear(UPDATED_CODE_YEAR)
            .codeMonth(UPDATED_CODE_MONTH)
            .codeDate(UPDATED_CODE_DATE)
            .number(UPDATED_NUMBER)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmNumber.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmNumber))
            )
            .andExpect(status().isOk());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
        AdmNumber testAdmNumber = admNumberList.get(admNumberList.size() - 1);
        assertThat(testAdmNumber.getCodeType()).isEqualTo(UPDATED_CODE_TYPE);
        assertThat(testAdmNumber.getCodeYear()).isEqualTo(UPDATED_CODE_YEAR);
        assertThat(testAdmNumber.getCodeMonth()).isEqualTo(UPDATED_CODE_MONTH);
        assertThat(testAdmNumber.getCodeDate()).isEqualTo(UPDATED_CODE_DATE);
        assertThat(testAdmNumber.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testAdmNumber.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmNumber.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmNumber.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmNumber.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmNumber() throws Exception {
        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();
        admNumber.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admNumber.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admNumber))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmNumber() throws Exception {
        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();
        admNumber.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmNumberMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admNumber))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmNumber() throws Exception {
        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();
        admNumber.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmNumberMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admNumber)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmNumberWithPatch() throws Exception {
        // Initialize the database
        admNumberRepository.saveAndFlush(admNumber);

        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();

        // Update the admNumber using partial update
        AdmNumber partialUpdatedAdmNumber = new AdmNumber();
        partialUpdatedAdmNumber.setId(admNumber.getId());

        partialUpdatedAdmNumber
            .codeYear(UPDATED_CODE_YEAR)
            .number(UPDATED_NUMBER)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmNumber.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmNumber))
            )
            .andExpect(status().isOk());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
        AdmNumber testAdmNumber = admNumberList.get(admNumberList.size() - 1);
        assertThat(testAdmNumber.getCodeType()).isEqualTo(DEFAULT_CODE_TYPE);
        assertThat(testAdmNumber.getCodeYear()).isEqualTo(UPDATED_CODE_YEAR);
        assertThat(testAdmNumber.getCodeMonth()).isEqualTo(DEFAULT_CODE_MONTH);
        assertThat(testAdmNumber.getCodeDate()).isEqualTo(DEFAULT_CODE_DATE);
        assertThat(testAdmNumber.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testAdmNumber.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmNumber.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmNumber.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmNumber.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmNumberWithPatch() throws Exception {
        // Initialize the database
        admNumberRepository.saveAndFlush(admNumber);

        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();

        // Update the admNumber using partial update
        AdmNumber partialUpdatedAdmNumber = new AdmNumber();
        partialUpdatedAdmNumber.setId(admNumber.getId());

        partialUpdatedAdmNumber
            .codeType(UPDATED_CODE_TYPE)
            .codeYear(UPDATED_CODE_YEAR)
            .codeMonth(UPDATED_CODE_MONTH)
            .codeDate(UPDATED_CODE_DATE)
            .number(UPDATED_NUMBER)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmNumber.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmNumber))
            )
            .andExpect(status().isOk());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
        AdmNumber testAdmNumber = admNumberList.get(admNumberList.size() - 1);
        assertThat(testAdmNumber.getCodeType()).isEqualTo(UPDATED_CODE_TYPE);
        assertThat(testAdmNumber.getCodeYear()).isEqualTo(UPDATED_CODE_YEAR);
        assertThat(testAdmNumber.getCodeMonth()).isEqualTo(UPDATED_CODE_MONTH);
        assertThat(testAdmNumber.getCodeDate()).isEqualTo(UPDATED_CODE_DATE);
        assertThat(testAdmNumber.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testAdmNumber.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testAdmNumber.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmNumber.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmNumber.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmNumber() throws Exception {
        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();
        admNumber.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admNumber.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admNumber))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmNumber() throws Exception {
        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();
        admNumber.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmNumberMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admNumber))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmNumber() throws Exception {
        int databaseSizeBeforeUpdate = admNumberRepository.findAll().size();
        admNumber.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmNumberMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admNumber))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmNumber in the database
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmNumber() throws Exception {
        // Initialize the database
        admNumberRepository.saveAndFlush(admNumber);

        int databaseSizeBeforeDelete = admNumberRepository.findAll().size();

        // Delete the admNumber
        restAdmNumberMockMvc
            .perform(delete(ENTITY_API_URL_ID, admNumber.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmNumber> admNumberList = admNumberRepository.findAll();
        assertThat(admNumberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
