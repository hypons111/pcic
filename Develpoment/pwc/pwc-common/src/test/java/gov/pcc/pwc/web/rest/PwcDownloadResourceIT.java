package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwcDownload;
import gov.pcc.pwc.repository.PwcDownloadRepository;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;
import gov.pcc.pwc.service.mapper.PwcDownloadMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PwcDownloadResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwcDownloadResourceIT {

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_KIND = "AAAAAAAAAA";
    private static final String UPDATED_KIND = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RELEASE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RELEASE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DOWN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOWN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RELEASE_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_RELEASE_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_INFORMATION_TEL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_INFORMATION_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_INFORMATION_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_INFORMATION_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ENABLE = "N";
    private static final String UPDATED_ENABLE = "NB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pwc-downloads";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwcDownloadRepository pwcDownloadRepository;

    @Autowired
    private PwcDownloadMapper pwcDownloadMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwcDownloadMockMvc;

    private PwcDownload pwcDownload;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcDownload createEntity(EntityManager em) {
        PwcDownload pwcDownload = new PwcDownload()
            .subject(DEFAULT_SUBJECT)
            .text(DEFAULT_TEXT)
            .kind(DEFAULT_KIND)
            .location(DEFAULT_LOCATION)
            .releaseDate(DEFAULT_RELEASE_DATE)
            .downDate(DEFAULT_DOWN_DATE)
            .releaseUnit(DEFAULT_RELEASE_UNIT)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .contactInformationTel(DEFAULT_CONTACT_INFORMATION_TEL)
            .contactInformationEmail(DEFAULT_CONTACT_INFORMATION_EMAIL)
            .enable(DEFAULT_ENABLE)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER);
        return pwcDownload;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcDownload createUpdatedEntity(EntityManager em) {
        PwcDownload pwcDownload = new PwcDownload()
            .subject(UPDATED_SUBJECT)
            .text(UPDATED_TEXT)
            .kind(UPDATED_KIND)
            .location(UPDATED_LOCATION)
            .releaseDate(UPDATED_RELEASE_DATE)
            .downDate(UPDATED_DOWN_DATE)
            .releaseUnit(UPDATED_RELEASE_UNIT)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .contactInformationTel(UPDATED_CONTACT_INFORMATION_TEL)
            .contactInformationEmail(UPDATED_CONTACT_INFORMATION_EMAIL)
            .enable(UPDATED_ENABLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        return pwcDownload;
    }

    @BeforeEach
    public void initTest() {
        pwcDownload = createEntity(em);
    }

    @Test
    @Transactional
    void createPwcDownload() throws Exception {
        int databaseSizeBeforeCreate = pwcDownloadRepository.findAll().size();
        // Create the PwcDownload
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);
        restPwcDownloadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeCreate + 1);
        PwcDownload testPwcDownload = pwcDownloadList.get(pwcDownloadList.size() - 1);
        assertThat(testPwcDownload.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testPwcDownload.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testPwcDownload.getKind()).isEqualTo(DEFAULT_KIND);
        assertThat(testPwcDownload.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testPwcDownload.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testPwcDownload.getDownDate()).isEqualTo(DEFAULT_DOWN_DATE);
        assertThat(testPwcDownload.getReleaseUnit()).isEqualTo(DEFAULT_RELEASE_UNIT);
        assertThat(testPwcDownload.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testPwcDownload.getContactInformationTel()).isEqualTo(DEFAULT_CONTACT_INFORMATION_TEL);
        assertThat(testPwcDownload.getContactInformationEmail()).isEqualTo(DEFAULT_CONTACT_INFORMATION_EMAIL);
        assertThat(testPwcDownload.getEnable()).isEqualTo(DEFAULT_ENABLE);
        assertThat(testPwcDownload.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcDownload.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcDownload.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcDownload.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void createPwcDownloadWithExistingId() throws Exception {
        // Create the PwcDownload with an existing ID
        pwcDownload.setId(1L);
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        int databaseSizeBeforeCreate = pwcDownloadRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwcDownloadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSubjectIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwcDownloadRepository.findAll().size();
        // set the field null
        pwcDownload.setSubject(null);

        // Create the PwcDownload, which fails.
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        restPwcDownloadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwcDownloadRepository.findAll().size();
        // set the field null
        pwcDownload.setText(null);

        // Create the PwcDownload, which fails.
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        restPwcDownloadMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPwcDownloads() throws Exception {
        // Initialize the database
        pwcDownloadRepository.saveAndFlush(pwcDownload);

        // Get all the pwcDownloadList
        restPwcDownloadMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pwcDownload.getId().intValue())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].kind").value(hasItem(DEFAULT_KIND)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].releaseDate").value(hasItem(DEFAULT_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].downDate").value(hasItem(DEFAULT_DOWN_DATE.toString())))
            .andExpect(jsonPath("$.[*].releaseUnit").value(hasItem(DEFAULT_RELEASE_UNIT)))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].contactInformationTel").value(hasItem(DEFAULT_CONTACT_INFORMATION_TEL)))
            .andExpect(jsonPath("$.[*].contactInformationEmail").value(hasItem(DEFAULT_CONTACT_INFORMATION_EMAIL)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)));
    }

    @Test
    @Transactional
    void getPwcDownload() throws Exception {
        // Initialize the database
        pwcDownloadRepository.saveAndFlush(pwcDownload);

        // Get the pwcDownload
        restPwcDownloadMockMvc
            .perform(get(ENTITY_API_URL_ID, pwcDownload.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pwcDownload.getId().intValue()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.kind").value(DEFAULT_KIND))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.releaseDate").value(DEFAULT_RELEASE_DATE.toString()))
            .andExpect(jsonPath("$.downDate").value(DEFAULT_DOWN_DATE.toString()))
            .andExpect(jsonPath("$.releaseUnit").value(DEFAULT_RELEASE_UNIT))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON))
            .andExpect(jsonPath("$.contactInformationTel").value(hasItem(DEFAULT_CONTACT_INFORMATION_TEL)))
            .andExpect(jsonPath("$.contactInformationEmail").value(hasItem(DEFAULT_CONTACT_INFORMATION_EMAIL)))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingPwcDownload() throws Exception {
        // Get the pwcDownload
        restPwcDownloadMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwcDownload() throws Exception {
        // Initialize the database
        pwcDownloadRepository.saveAndFlush(pwcDownload);

        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();

        // Update the pwcDownload
        PwcDownload updatedPwcDownload = pwcDownloadRepository.findById(pwcDownload.getId()).get();
        // Disconnect from session so that the updates on updatedPwcDownload are not directly saved in db
        em.detach(updatedPwcDownload);
        updatedPwcDownload
            .subject(UPDATED_SUBJECT)
            .text(UPDATED_TEXT)
            .kind(UPDATED_KIND)
            .location(UPDATED_LOCATION)
            .releaseDate(UPDATED_RELEASE_DATE)
            .downDate(UPDATED_DOWN_DATE)
            .releaseUnit(UPDATED_RELEASE_UNIT)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .contactInformationTel(UPDATED_CONTACT_INFORMATION_TEL)
            .contactInformationEmail(UPDATED_CONTACT_INFORMATION_EMAIL)
            .enable(UPDATED_ENABLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(updatedPwcDownload);

        restPwcDownloadMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcDownloadDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
        PwcDownload testPwcDownload = pwcDownloadList.get(pwcDownloadList.size() - 1);
        assertThat(testPwcDownload.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testPwcDownload.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcDownload.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testPwcDownload.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testPwcDownload.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testPwcDownload.getDownDate()).isEqualTo(UPDATED_DOWN_DATE);
        assertThat(testPwcDownload.getReleaseUnit()).isEqualTo(UPDATED_RELEASE_UNIT);
        assertThat(testPwcDownload.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testPwcDownload.getContactInformationTel()).isEqualTo(UPDATED_CONTACT_INFORMATION_TEL);
        assertThat(testPwcDownload.getContactInformationEmail()).isEqualTo(UPDATED_CONTACT_INFORMATION_EMAIL);
        assertThat(testPwcDownload.getEnable()).isEqualTo(UPDATED_ENABLE);
        assertThat(testPwcDownload.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownload.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownload.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownload.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingPwcDownload() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();
        pwcDownload.setId(count.incrementAndGet());

        // Create the PwcDownload
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcDownloadMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcDownloadDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwcDownload() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();
        pwcDownload.setId(count.incrementAndGet());

        // Create the PwcDownload
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwcDownload() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();
        pwcDownload.setId(count.incrementAndGet());

        // Create the PwcDownload
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwcDownloadWithPatch() throws Exception {
        // Initialize the database
        pwcDownloadRepository.saveAndFlush(pwcDownload);

        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();

        // Update the pwcDownload using partial update
        PwcDownload partialUpdatedPwcDownload = new PwcDownload();
        partialUpdatedPwcDownload.setId(pwcDownload.getId());

        partialUpdatedPwcDownload
            .subject(UPDATED_SUBJECT)
            .text(UPDATED_TEXT)
            .kind(UPDATED_KIND)
            .downDate(UPDATED_DOWN_DATE)
            .releaseUnit(UPDATED_RELEASE_UNIT)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .updateTime(UPDATED_UPDATE_TIME)
            .createTime(UPDATED_CREATE_TIME);

        restPwcDownloadMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcDownload.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcDownload))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
        PwcDownload testPwcDownload = pwcDownloadList.get(pwcDownloadList.size() - 1);
        assertThat(testPwcDownload.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testPwcDownload.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcDownload.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testPwcDownload.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testPwcDownload.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testPwcDownload.getDownDate()).isEqualTo(UPDATED_DOWN_DATE);
        assertThat(testPwcDownload.getReleaseUnit()).isEqualTo(UPDATED_RELEASE_UNIT);
        assertThat(testPwcDownload.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testPwcDownload.getContactInformationTel()).isEqualTo(DEFAULT_CONTACT_INFORMATION_TEL);
        assertThat(testPwcDownload.getContactInformationEmail()).isEqualTo(DEFAULT_CONTACT_INFORMATION_EMAIL);
        assertThat(testPwcDownload.getEnable()).isEqualTo(DEFAULT_ENABLE);
        assertThat(testPwcDownload.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownload.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcDownload.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownload.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void fullUpdatePwcDownloadWithPatch() throws Exception {
        // Initialize the database
        pwcDownloadRepository.saveAndFlush(pwcDownload);

        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();

        // Update the pwcDownload using partial update
        PwcDownload partialUpdatedPwcDownload = new PwcDownload();
        partialUpdatedPwcDownload.setId(pwcDownload.getId());

        partialUpdatedPwcDownload
            .subject(UPDATED_SUBJECT)
            .text(UPDATED_TEXT)
            .kind(UPDATED_KIND)
            .location(UPDATED_LOCATION)
            .releaseDate(UPDATED_RELEASE_DATE)
            .downDate(UPDATED_DOWN_DATE)
            .releaseUnit(UPDATED_RELEASE_UNIT)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .contactInformationTel(UPDATED_CONTACT_INFORMATION_TEL)
            .contactInformationEmail(UPDATED_CONTACT_INFORMATION_EMAIL)
            .enable(UPDATED_ENABLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwcDownloadMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcDownload.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcDownload))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
        PwcDownload testPwcDownload = pwcDownloadList.get(pwcDownloadList.size() - 1);
        assertThat(testPwcDownload.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testPwcDownload.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcDownload.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testPwcDownload.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testPwcDownload.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testPwcDownload.getDownDate()).isEqualTo(UPDATED_DOWN_DATE);
        assertThat(testPwcDownload.getReleaseUnit()).isEqualTo(UPDATED_RELEASE_UNIT);
        assertThat(testPwcDownload.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testPwcDownload.getContactInformationTel()).isEqualTo(UPDATED_CONTACT_INFORMATION_TEL);
        assertThat(testPwcDownload.getContactInformationEmail()).isEqualTo(UPDATED_CONTACT_INFORMATION_EMAIL);
        assertThat(testPwcDownload.getEnable()).isEqualTo(UPDATED_ENABLE);
        assertThat(testPwcDownload.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownload.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownload.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownload.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingPwcDownload() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();
        pwcDownload.setId(count.incrementAndGet());

        // Create the PwcDownload
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcDownloadMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwcDownloadDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwcDownload() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();
        pwcDownload.setId(count.incrementAndGet());

        // Create the PwcDownload
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwcDownload() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRepository.findAll().size();
        pwcDownload.setId(count.incrementAndGet());

        // Create the PwcDownload
        PwcDownloadDTO pwcDownloadDTO = pwcDownloadMapper.toDto(pwcDownload);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(pwcDownloadDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcDownload in the database
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwcDownload() throws Exception {
        // Initialize the database
        pwcDownloadRepository.saveAndFlush(pwcDownload);

        int databaseSizeBeforeDelete = pwcDownloadRepository.findAll().size();

        // Delete the pwcDownload
        restPwcDownloadMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwcDownload.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwcDownload> pwcDownloadList = pwcDownloadRepository.findAll();
        assertThat(pwcDownloadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
