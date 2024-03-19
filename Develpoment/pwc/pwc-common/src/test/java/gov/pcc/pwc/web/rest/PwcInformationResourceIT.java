package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwcInformation;
import gov.pcc.pwc.repository.PwcInformationRepository;
import gov.pcc.pwc.service.dto.PwcInformationDTO;
import gov.pcc.pwc.service.mapper.PwcInformationMapper;
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
 * Integration tests for the {@link PwcInformationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwcInformationResourceIT {

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_KIND = "AAAAAAAAAA";
    private static final String UPDATED_KIND = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTES = "1";
    private static final String UPDATED_ATTRIBUTES = "1B";

    private static final String DEFAULT_ROLE = "1";
    private static final String UPDATED_ROLE = "1B";

    private static final String DEFAULT_APPENDIX_LINK = "AAAAAAAAAA";
    private static final String UPDATED_APPENDIX_LINK = "BBBBBBBBBB";

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

    private static final String DEFAULT_ENABLE = "Y";
    private static final String UPDATED_ENABLE = "N";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pwc-informations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwcInformationRepository pwcInformationRepository;

    @Autowired
    private PwcInformationMapper pwcInformationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwcInformationMockMvc;

    private PwcInformation pwcInformation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcInformation createEntity(EntityManager em) {
        PwcInformation pwcInformation = new PwcInformation()
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
        return pwcInformation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcInformation createUpdatedEntity(EntityManager em) {
        PwcInformation pwcInformation = new PwcInformation()
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
        return pwcInformation;
    }

    @BeforeEach
    public void initTest() {
        pwcInformation = createEntity(em);
    }

    @Test
    @Transactional
    void createPwcInformation() throws Exception {
        int databaseSizeBeforeCreate = pwcInformationRepository.findAll().size();
        // Create the PwcInformation
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);
        restPwcInformationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeCreate + 1);
        PwcInformation testPwcInformation = pwcInformationList.get(pwcInformationList.size() - 1);
        assertThat(testPwcInformation.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testPwcInformation.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testPwcInformation.getKind()).isEqualTo(DEFAULT_KIND);
        assertThat(testPwcInformation.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testPwcInformation.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testPwcInformation.getDownDate()).isEqualTo(DEFAULT_DOWN_DATE);
        assertThat(testPwcInformation.getReleaseUnit()).isEqualTo(DEFAULT_RELEASE_UNIT);
        assertThat(testPwcInformation.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testPwcInformation.getContactInformationTel()).isEqualTo(DEFAULT_CONTACT_INFORMATION_TEL);
        assertThat(testPwcInformation.getContactInformationEmail()).isEqualTo(DEFAULT_CONTACT_INFORMATION_EMAIL);
        assertThat(testPwcInformation.getEnable()).isEqualTo(DEFAULT_ENABLE);
        assertThat(testPwcInformation.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcInformation.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcInformation.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcInformation.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void createPwcInformationWithExistingId() throws Exception {
        // Create the PwcInformation with an existing ID
        pwcInformation.setId(1L);
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        int databaseSizeBeforeCreate = pwcInformationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwcInformationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSubjectIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwcInformationRepository.findAll().size();
        // set the field null
        pwcInformation.setSubject(null);

        // Create the PwcInformation, which fails.
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        restPwcInformationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwcInformationRepository.findAll().size();
        // set the field null
        pwcInformation.setText(null);

        // Create the PwcInformation, which fails.
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        restPwcInformationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPwcInformations() throws Exception {
        // Initialize the database
        pwcInformationRepository.saveAndFlush(pwcInformation);

        // Get all the pwcInformationList
        restPwcInformationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pwcInformation.getId().intValue())))
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
    void getPwcInformation() throws Exception {
        // Initialize the database
        pwcInformationRepository.saveAndFlush(pwcInformation);

        // Get the pwcInformation
        restPwcInformationMockMvc
            .perform(get(ENTITY_API_URL_ID, pwcInformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pwcInformation.getId().intValue()))
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
    void getNonExistingPwcInformation() throws Exception {
        // Get the pwcInformation
        restPwcInformationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwcInformation() throws Exception {
        // Initialize the database
        pwcInformationRepository.saveAndFlush(pwcInformation);

        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();

        // Update the pwcInformation
        PwcInformation updatedPwcInformation = pwcInformationRepository.findById(pwcInformation.getId()).get();
        // Disconnect from session so that the updates on updatedPwcInformation are not directly saved in db
        em.detach(updatedPwcInformation);
        updatedPwcInformation
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
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(updatedPwcInformation);

        restPwcInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcInformationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
        PwcInformation testPwcInformation = pwcInformationList.get(pwcInformationList.size() - 1);
        assertThat(testPwcInformation.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testPwcInformation.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcInformation.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testPwcInformation.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testPwcInformation.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testPwcInformation.getDownDate()).isEqualTo(UPDATED_DOWN_DATE);
        assertThat(testPwcInformation.getReleaseUnit()).isEqualTo(UPDATED_RELEASE_UNIT);
        assertThat(testPwcInformation.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testPwcInformation.getContactInformationTel()).isEqualTo(UPDATED_CONTACT_INFORMATION_TEL);
        assertThat(testPwcInformation.getContactInformationEmail()).isEqualTo(UPDATED_CONTACT_INFORMATION_EMAIL);
        assertThat(testPwcInformation.getEnable()).isEqualTo(UPDATED_ENABLE);
        assertThat(testPwcInformation.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformation.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformation.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformation.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingPwcInformation() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();
        pwcInformation.setId(count.incrementAndGet());

        // Create the PwcInformation
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcInformationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwcInformation() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();
        pwcInformation.setId(count.incrementAndGet());

        // Create the PwcInformation
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwcInformation() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();
        pwcInformation.setId(count.incrementAndGet());

        // Create the PwcInformation
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwcInformationWithPatch() throws Exception {
        // Initialize the database
        pwcInformationRepository.saveAndFlush(pwcInformation);

        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();

        // Update the pwcInformation using partial update
        PwcInformation partialUpdatedPwcInformation = new PwcInformation();
        partialUpdatedPwcInformation.setId(pwcInformation.getId());

        partialUpdatedPwcInformation
            .text(UPDATED_TEXT)
            .kind(UPDATED_KIND)
            .releaseDate(UPDATED_RELEASE_DATE)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .enable(UPDATED_ENABLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);

        restPwcInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcInformation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcInformation))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
        PwcInformation testPwcInformation = pwcInformationList.get(pwcInformationList.size() - 1);
        assertThat(testPwcInformation.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testPwcInformation.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcInformation.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testPwcInformation.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testPwcInformation.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testPwcInformation.getDownDate()).isEqualTo(DEFAULT_DOWN_DATE);
        assertThat(testPwcInformation.getReleaseUnit()).isEqualTo(DEFAULT_RELEASE_UNIT);
        assertThat(testPwcInformation.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testPwcInformation.getContactInformationTel()).isEqualTo(DEFAULT_CONTACT_INFORMATION_TEL);
        assertThat(testPwcInformation.getContactInformationEmail()).isEqualTo(DEFAULT_CONTACT_INFORMATION_EMAIL);
        assertThat(testPwcInformation.getEnable()).isEqualTo(UPDATED_ENABLE);
        assertThat(testPwcInformation.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformation.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformation.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcInformation.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void fullUpdatePwcInformationWithPatch() throws Exception {
        // Initialize the database
        pwcInformationRepository.saveAndFlush(pwcInformation);

        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();

        // Update the pwcInformation using partial update
        PwcInformation partialUpdatedPwcInformation = new PwcInformation();
        partialUpdatedPwcInformation.setId(pwcInformation.getId());

        partialUpdatedPwcInformation
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

        restPwcInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcInformation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcInformation))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
        PwcInformation testPwcInformation = pwcInformationList.get(pwcInformationList.size() - 1);
        assertThat(testPwcInformation.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testPwcInformation.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcInformation.getKind()).isEqualTo(UPDATED_KIND);
        assertThat(testPwcInformation.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testPwcInformation.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testPwcInformation.getDownDate()).isEqualTo(UPDATED_DOWN_DATE);
        assertThat(testPwcInformation.getReleaseUnit()).isEqualTo(UPDATED_RELEASE_UNIT);
        assertThat(testPwcInformation.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testPwcInformation.getContactInformationTel()).isEqualTo(UPDATED_CONTACT_INFORMATION_TEL);
        assertThat(testPwcInformation.getContactInformationEmail()).isEqualTo(UPDATED_CONTACT_INFORMATION_EMAIL);
        assertThat(testPwcInformation.getEnable()).isEqualTo(UPDATED_ENABLE);
        assertThat(testPwcInformation.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformation.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformation.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformation.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingPwcInformation() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();
        pwcInformation.setId(count.incrementAndGet());

        // Create the PwcInformation
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwcInformationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwcInformation() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();
        pwcInformation.setId(count.incrementAndGet());

        // Create the PwcInformation
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwcInformation() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRepository.findAll().size();
        pwcInformation.setId(count.incrementAndGet());

        // Create the PwcInformation
        PwcInformationDTO pwcInformationDTO = pwcInformationMapper.toDto(pwcInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcInformation in the database
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwcInformation() throws Exception {
        // Initialize the database
        pwcInformationRepository.saveAndFlush(pwcInformation);

        int databaseSizeBeforeDelete = pwcInformationRepository.findAll().size();

        // Delete the pwcInformation
        restPwcInformationMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwcInformation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwcInformation> pwcInformationList = pwcInformationRepository.findAll();
        assertThat(pwcInformationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
