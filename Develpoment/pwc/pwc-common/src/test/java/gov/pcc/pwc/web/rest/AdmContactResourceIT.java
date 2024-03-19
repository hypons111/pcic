package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmContact;
import gov.pcc.pwc.repository.AdmContactRepository;
import gov.pcc.pwc.service.dto.AdmContactDTO;
import gov.pcc.pwc.service.mapper.AdmContactMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
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
 * Integration tests for the {@link AdmContactResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmContactResourceIT {

    private static final String DEFAULT_CH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_BIRTHDAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTHDAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_IDNO = "AAAAAAAAAA";
    private static final String UPDATED_IDNO = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "A";
    private static final String UPDATED_SEX = "B";

    private static final String DEFAULT_WORK_TEL = "AAAAAAAAAA";
    private static final String UPDATED_WORK_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_TEL = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_HOME_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_ADDR_CITY = "AAAAAAAAAA";
    private static final String UPDATED_HOME_ADDR_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_ADDR_TOWN = "AAAAAAAAAA";
    private static final String UPDATED_HOME_ADDR_TOWN = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_ADDR_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_HOME_ADDR_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_WORK_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_ADDR_CITY = "AAAAAAAAAA";
    private static final String UPDATED_WORK_ADDR_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_ADDR_TOWN = "AAAAAAAAAA";
    private static final String UPDATED_WORK_ADDR_TOWN = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_ADDR_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_WORK_ADDR_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_LINE_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_LINE_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CH_NAME_ROME = "AAAAAAAAAA";
    private static final String UPDATED_CH_NAME_ROME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/adm-contacts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{userId}";

    @Autowired
    private AdmContactRepository admContactRepository;

    @Autowired
    private AdmContactMapper admContactMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmContactMockMvc;

    private AdmContact admContact;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmContact createEntity(EntityManager em) {
        AdmContact admContact = new AdmContact()
            .chName(DEFAULT_CH_NAME)
            .title(DEFAULT_TITLE)
            .birthday(DEFAULT_BIRTHDAY)
            .idno(DEFAULT_IDNO)
            .sex(DEFAULT_SEX)
            .workTel(DEFAULT_WORK_TEL)
            .mobileTel(DEFAULT_MOBILE_TEL)
            .email(DEFAULT_EMAIL)
            .homeAddrZip(DEFAULT_HOME_ADDR_ZIP)
            .homeAddrCity(DEFAULT_HOME_ADDR_CITY)
            .homeAddrTown(DEFAULT_HOME_ADDR_TOWN)
            .homeAddrOther(DEFAULT_HOME_ADDR_OTHER)
            .workAddrZip(DEFAULT_WORK_ADDR_ZIP)
            .workAddrCity(DEFAULT_WORK_ADDR_CITY)
            .workAddrTown(DEFAULT_WORK_ADDR_TOWN)
            .workAddrOther(DEFAULT_WORK_ADDR_OTHER)
            .lineToken(DEFAULT_LINE_TOKEN)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .chNameRome(DEFAULT_CH_NAME_ROME);
        return admContact;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmContact createUpdatedEntity(EntityManager em) {
        AdmContact admContact = new AdmContact()
            .chName(UPDATED_CH_NAME)
            .title(UPDATED_TITLE)
            .birthday(UPDATED_BIRTHDAY)
            .idno(UPDATED_IDNO)
            .sex(UPDATED_SEX)
            .workTel(UPDATED_WORK_TEL)
            .mobileTel(UPDATED_MOBILE_TEL)
            .email(UPDATED_EMAIL)
            .homeAddrZip(UPDATED_HOME_ADDR_ZIP)
            .homeAddrCity(UPDATED_HOME_ADDR_CITY)
            .homeAddrTown(UPDATED_HOME_ADDR_TOWN)
            .homeAddrOther(UPDATED_HOME_ADDR_OTHER)
            .workAddrZip(UPDATED_WORK_ADDR_ZIP)
            .workAddrCity(UPDATED_WORK_ADDR_CITY)
            .workAddrTown(UPDATED_WORK_ADDR_TOWN)
            .workAddrOther(UPDATED_WORK_ADDR_OTHER)
            .lineToken(UPDATED_LINE_TOKEN)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .chNameRome(UPDATED_CH_NAME_ROME);
        return admContact;
    }

    @BeforeEach
    public void initTest() {
        admContact = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmContact() throws Exception {
        int databaseSizeBeforeCreate = admContactRepository.findAll().size();
        // Create the AdmContact
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);
        restAdmContactMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admContactDTO)))
            .andExpect(status().isCreated());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeCreate + 1);
        AdmContact testAdmContact = admContactList.get(admContactList.size() - 1);
        assertThat(testAdmContact.getChName()).isEqualTo(DEFAULT_CH_NAME);
        assertThat(testAdmContact.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testAdmContact.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testAdmContact.getIdno()).isEqualTo(DEFAULT_IDNO);
        assertThat(testAdmContact.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testAdmContact.getWorkTel()).isEqualTo(DEFAULT_WORK_TEL);
        assertThat(testAdmContact.getMobileTel()).isEqualTo(DEFAULT_MOBILE_TEL);
        assertThat(testAdmContact.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAdmContact.getHomeAddrZip()).isEqualTo(DEFAULT_HOME_ADDR_ZIP);
        assertThat(testAdmContact.getHomeAddrCity()).isEqualTo(DEFAULT_HOME_ADDR_CITY);
        assertThat(testAdmContact.getHomeAddrTown()).isEqualTo(DEFAULT_HOME_ADDR_TOWN);
        assertThat(testAdmContact.getHomeAddrOther()).isEqualTo(DEFAULT_HOME_ADDR_OTHER);
        assertThat(testAdmContact.getWorkAddrZip()).isEqualTo(DEFAULT_WORK_ADDR_ZIP);
        assertThat(testAdmContact.getWorkAddrCity()).isEqualTo(DEFAULT_WORK_ADDR_CITY);
        assertThat(testAdmContact.getWorkAddrTown()).isEqualTo(DEFAULT_WORK_ADDR_TOWN);
        assertThat(testAdmContact.getWorkAddrOther()).isEqualTo(DEFAULT_WORK_ADDR_OTHER);
        assertThat(testAdmContact.getLineToken()).isEqualTo(DEFAULT_LINE_TOKEN);
        assertThat(testAdmContact.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmContact.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testAdmContact.getChNameRome()).isEqualTo(DEFAULT_CH_NAME_ROME);
    }

    @Test
    @Transactional
    void createAdmContactWithExistingId() throws Exception {
        // Create the AdmContact with an existing ID
        admContact.setUserId("existing_id");
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        int databaseSizeBeforeCreate = admContactRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmContactMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = admContactRepository.findAll().size();
        // set the field null
        admContact.setEmail(null);

        // Create the AdmContact, which fails.
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        restAdmContactMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admContactDTO)))
            .andExpect(status().isBadRequest());

        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admContactRepository.findAll().size();
        // set the field null
        admContact.setUpdateUser(null);

        // Create the AdmContact, which fails.
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        restAdmContactMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admContactDTO)))
            .andExpect(status().isBadRequest());

        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admContactRepository.findAll().size();
        // set the field null
        admContact.setUpdateTime(null);

        // Create the AdmContact, which fails.
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        restAdmContactMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admContactDTO)))
            .andExpect(status().isBadRequest());

        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmContacts() throws Exception {
        // Initialize the database
        admContact.setUserId(UUID.randomUUID().toString());
        admContactRepository.saveAndFlush(admContact);

        // Get all the admContactList
        restAdmContactMockMvc
            .perform(get(ENTITY_API_URL + "?sort=userId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(admContact.getUserId())))
            .andExpect(jsonPath("$.[*].chName").value(hasItem(DEFAULT_CH_NAME)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].idno").value(hasItem(DEFAULT_IDNO)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].workTel").value(hasItem(DEFAULT_WORK_TEL)))
            .andExpect(jsonPath("$.[*].mobileTel").value(hasItem(DEFAULT_MOBILE_TEL)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].homeAddrZip").value(hasItem(DEFAULT_HOME_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].homeAddrCity").value(hasItem(DEFAULT_HOME_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].homeAddrTown").value(hasItem(DEFAULT_HOME_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].homeAddrOther").value(hasItem(DEFAULT_HOME_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].workAddrZip").value(hasItem(DEFAULT_WORK_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].workAddrCity").value(hasItem(DEFAULT_WORK_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].workAddrTown").value(hasItem(DEFAULT_WORK_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].workAddrOther").value(hasItem(DEFAULT_WORK_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].lineToken").value(hasItem(DEFAULT_LINE_TOKEN)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].chNameRome").value(hasItem(DEFAULT_CH_NAME_ROME)));
    }

    @Test
    @Transactional
    void getAdmContact() throws Exception {
        // Initialize the database
        admContact.setUserId(UUID.randomUUID().toString());
        admContactRepository.saveAndFlush(admContact);

        // Get the admContact
        restAdmContactMockMvc
            .perform(get(ENTITY_API_URL_ID, admContact.getUserId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.userId").value(admContact.getUserId()))
            .andExpect(jsonPath("$.chName").value(DEFAULT_CH_NAME))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()))
            .andExpect(jsonPath("$.idno").value(DEFAULT_IDNO))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX))
            .andExpect(jsonPath("$.workTel").value(DEFAULT_WORK_TEL))
            .andExpect(jsonPath("$.mobileTel").value(DEFAULT_MOBILE_TEL))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.homeAddrZip").value(DEFAULT_HOME_ADDR_ZIP))
            .andExpect(jsonPath("$.homeAddrCity").value(DEFAULT_HOME_ADDR_CITY))
            .andExpect(jsonPath("$.homeAddrTown").value(DEFAULT_HOME_ADDR_TOWN))
            .andExpect(jsonPath("$.homeAddrOther").value(DEFAULT_HOME_ADDR_OTHER))
            .andExpect(jsonPath("$.workAddrZip").value(DEFAULT_WORK_ADDR_ZIP))
            .andExpect(jsonPath("$.workAddrCity").value(DEFAULT_WORK_ADDR_CITY))
            .andExpect(jsonPath("$.workAddrTown").value(DEFAULT_WORK_ADDR_TOWN))
            .andExpect(jsonPath("$.workAddrOther").value(DEFAULT_WORK_ADDR_OTHER))
            .andExpect(jsonPath("$.lineToken").value(DEFAULT_LINE_TOKEN))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.chNameRome").value(DEFAULT_CH_NAME_ROME));
    }

    @Test
    @Transactional
    void getNonExistingAdmContact() throws Exception {
        // Get the admContact
        restAdmContactMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmContact() throws Exception {
        // Initialize the database
        admContact.setUserId(UUID.randomUUID().toString());
        admContactRepository.saveAndFlush(admContact);

        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();

        // Update the admContact
        AdmContact updatedAdmContact = admContactRepository.findById(admContact.getUserId()).get();
        // Disconnect from session so that the updates on updatedAdmContact are not directly saved in db
        em.detach(updatedAdmContact);
        updatedAdmContact
            .chName(UPDATED_CH_NAME)
            .title(UPDATED_TITLE)
            .birthday(UPDATED_BIRTHDAY)
            .idno(UPDATED_IDNO)
            .sex(UPDATED_SEX)
            .workTel(UPDATED_WORK_TEL)
            .mobileTel(UPDATED_MOBILE_TEL)
            .email(UPDATED_EMAIL)
            .homeAddrZip(UPDATED_HOME_ADDR_ZIP)
            .homeAddrCity(UPDATED_HOME_ADDR_CITY)
            .homeAddrTown(UPDATED_HOME_ADDR_TOWN)
            .homeAddrOther(UPDATED_HOME_ADDR_OTHER)
            .workAddrZip(UPDATED_WORK_ADDR_ZIP)
            .workAddrCity(UPDATED_WORK_ADDR_CITY)
            .workAddrTown(UPDATED_WORK_ADDR_TOWN)
            .workAddrOther(UPDATED_WORK_ADDR_OTHER)
            .lineToken(UPDATED_LINE_TOKEN)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .chNameRome(UPDATED_CH_NAME_ROME);
        AdmContactDTO admContactDTO = admContactMapper.toDto(updatedAdmContact);

        restAdmContactMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admContactDTO.getUserId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admContactDTO))
            )
            .andExpect(status().isOk());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
        AdmContact testAdmContact = admContactList.get(admContactList.size() - 1);
        assertThat(testAdmContact.getChName()).isEqualTo(UPDATED_CH_NAME);
        assertThat(testAdmContact.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAdmContact.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testAdmContact.getIdno()).isEqualTo(UPDATED_IDNO);
        assertThat(testAdmContact.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testAdmContact.getWorkTel()).isEqualTo(UPDATED_WORK_TEL);
        assertThat(testAdmContact.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testAdmContact.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAdmContact.getHomeAddrZip()).isEqualTo(UPDATED_HOME_ADDR_ZIP);
        assertThat(testAdmContact.getHomeAddrCity()).isEqualTo(UPDATED_HOME_ADDR_CITY);
        assertThat(testAdmContact.getHomeAddrTown()).isEqualTo(UPDATED_HOME_ADDR_TOWN);
        assertThat(testAdmContact.getHomeAddrOther()).isEqualTo(UPDATED_HOME_ADDR_OTHER);
        assertThat(testAdmContact.getWorkAddrZip()).isEqualTo(UPDATED_WORK_ADDR_ZIP);
        assertThat(testAdmContact.getWorkAddrCity()).isEqualTo(UPDATED_WORK_ADDR_CITY);
        assertThat(testAdmContact.getWorkAddrTown()).isEqualTo(UPDATED_WORK_ADDR_TOWN);
        assertThat(testAdmContact.getWorkAddrOther()).isEqualTo(UPDATED_WORK_ADDR_OTHER);
        assertThat(testAdmContact.getLineToken()).isEqualTo(UPDATED_LINE_TOKEN);
        assertThat(testAdmContact.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmContact.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testAdmContact.getChNameRome()).isEqualTo(UPDATED_CH_NAME_ROME);
    }

    @Test
    @Transactional
    void putNonExistingAdmContact() throws Exception {
        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();
        admContact.setUserId(UUID.randomUUID().toString());

        // Create the AdmContact
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmContactMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admContactDTO.getUserId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admContactDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmContact() throws Exception {
        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();
        admContact.setUserId(UUID.randomUUID().toString());

        // Create the AdmContact
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmContactMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admContactDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmContact() throws Exception {
        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();
        admContact.setUserId(UUID.randomUUID().toString());

        // Create the AdmContact
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmContactMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admContactDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmContactWithPatch() throws Exception {
        // Initialize the database
        admContact.setUserId(UUID.randomUUID().toString());
        admContactRepository.saveAndFlush(admContact);

        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();

        // Update the admContact using partial update
        AdmContact partialUpdatedAdmContact = new AdmContact();
        partialUpdatedAdmContact.setUserId(admContact.getUserId());

        partialUpdatedAdmContact
            .chName(UPDATED_CH_NAME)
            .title(UPDATED_TITLE)
            .sex(UPDATED_SEX)
            .mobileTel(UPDATED_MOBILE_TEL)
            .email(UPDATED_EMAIL)
            .homeAddrCity(UPDATED_HOME_ADDR_CITY)
            .homeAddrTown(UPDATED_HOME_ADDR_TOWN)
            .workAddrZip(UPDATED_WORK_ADDR_ZIP)
            .workAddrCity(UPDATED_WORK_ADDR_CITY)
            .workAddrTown(UPDATED_WORK_ADDR_TOWN)
            .workAddrOther(UPDATED_WORK_ADDR_OTHER)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .chNameRome(UPDATED_CH_NAME_ROME);

        restAdmContactMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmContact.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmContact))
            )
            .andExpect(status().isOk());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
        AdmContact testAdmContact = admContactList.get(admContactList.size() - 1);
        assertThat(testAdmContact.getChName()).isEqualTo(UPDATED_CH_NAME);
        assertThat(testAdmContact.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAdmContact.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testAdmContact.getIdno()).isEqualTo(DEFAULT_IDNO);
        assertThat(testAdmContact.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testAdmContact.getWorkTel()).isEqualTo(DEFAULT_WORK_TEL);
        assertThat(testAdmContact.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testAdmContact.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAdmContact.getHomeAddrZip()).isEqualTo(DEFAULT_HOME_ADDR_ZIP);
        assertThat(testAdmContact.getHomeAddrCity()).isEqualTo(UPDATED_HOME_ADDR_CITY);
        assertThat(testAdmContact.getHomeAddrTown()).isEqualTo(UPDATED_HOME_ADDR_TOWN);
        assertThat(testAdmContact.getHomeAddrOther()).isEqualTo(DEFAULT_HOME_ADDR_OTHER);
        assertThat(testAdmContact.getWorkAddrZip()).isEqualTo(UPDATED_WORK_ADDR_ZIP);
        assertThat(testAdmContact.getWorkAddrCity()).isEqualTo(UPDATED_WORK_ADDR_CITY);
        assertThat(testAdmContact.getWorkAddrTown()).isEqualTo(UPDATED_WORK_ADDR_TOWN);
        assertThat(testAdmContact.getWorkAddrOther()).isEqualTo(UPDATED_WORK_ADDR_OTHER);
        assertThat(testAdmContact.getLineToken()).isEqualTo(DEFAULT_LINE_TOKEN);
        assertThat(testAdmContact.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmContact.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testAdmContact.getChNameRome()).isEqualTo(UPDATED_CH_NAME_ROME);
    }

    @Test
    @Transactional
    void fullUpdateAdmContactWithPatch() throws Exception {
        // Initialize the database
        admContact.setUserId(UUID.randomUUID().toString());
        admContactRepository.saveAndFlush(admContact);

        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();

        // Update the admContact using partial update
        AdmContact partialUpdatedAdmContact = new AdmContact();
        partialUpdatedAdmContact.setUserId(admContact.getUserId());

        partialUpdatedAdmContact
            .chName(UPDATED_CH_NAME)
            .title(UPDATED_TITLE)
            .birthday(UPDATED_BIRTHDAY)
            .idno(UPDATED_IDNO)
            .sex(UPDATED_SEX)
            .workTel(UPDATED_WORK_TEL)
            .mobileTel(UPDATED_MOBILE_TEL)
            .email(UPDATED_EMAIL)
            .homeAddrZip(UPDATED_HOME_ADDR_ZIP)
            .homeAddrCity(UPDATED_HOME_ADDR_CITY)
            .homeAddrTown(UPDATED_HOME_ADDR_TOWN)
            .homeAddrOther(UPDATED_HOME_ADDR_OTHER)
            .workAddrZip(UPDATED_WORK_ADDR_ZIP)
            .workAddrCity(UPDATED_WORK_ADDR_CITY)
            .workAddrTown(UPDATED_WORK_ADDR_TOWN)
            .workAddrOther(UPDATED_WORK_ADDR_OTHER)
            .lineToken(UPDATED_LINE_TOKEN)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .chNameRome(UPDATED_CH_NAME_ROME);

        restAdmContactMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmContact.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmContact))
            )
            .andExpect(status().isOk());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
        AdmContact testAdmContact = admContactList.get(admContactList.size() - 1);
        assertThat(testAdmContact.getChName()).isEqualTo(UPDATED_CH_NAME);
        assertThat(testAdmContact.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAdmContact.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testAdmContact.getIdno()).isEqualTo(UPDATED_IDNO);
        assertThat(testAdmContact.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testAdmContact.getWorkTel()).isEqualTo(UPDATED_WORK_TEL);
        assertThat(testAdmContact.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testAdmContact.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAdmContact.getHomeAddrZip()).isEqualTo(UPDATED_HOME_ADDR_ZIP);
        assertThat(testAdmContact.getHomeAddrCity()).isEqualTo(UPDATED_HOME_ADDR_CITY);
        assertThat(testAdmContact.getHomeAddrTown()).isEqualTo(UPDATED_HOME_ADDR_TOWN);
        assertThat(testAdmContact.getHomeAddrOther()).isEqualTo(UPDATED_HOME_ADDR_OTHER);
        assertThat(testAdmContact.getWorkAddrZip()).isEqualTo(UPDATED_WORK_ADDR_ZIP);
        assertThat(testAdmContact.getWorkAddrCity()).isEqualTo(UPDATED_WORK_ADDR_CITY);
        assertThat(testAdmContact.getWorkAddrTown()).isEqualTo(UPDATED_WORK_ADDR_TOWN);
        assertThat(testAdmContact.getWorkAddrOther()).isEqualTo(UPDATED_WORK_ADDR_OTHER);
        assertThat(testAdmContact.getLineToken()).isEqualTo(UPDATED_LINE_TOKEN);
        assertThat(testAdmContact.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmContact.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testAdmContact.getChNameRome()).isEqualTo(UPDATED_CH_NAME_ROME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmContact() throws Exception {
        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();
        admContact.setUserId(UUID.randomUUID().toString());

        // Create the AdmContact
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmContactMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admContactDTO.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admContactDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmContact() throws Exception {
        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();
        admContact.setUserId(UUID.randomUUID().toString());

        // Create the AdmContact
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmContactMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admContactDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmContact() throws Exception {
        int databaseSizeBeforeUpdate = admContactRepository.findAll().size();
        admContact.setUserId(UUID.randomUUID().toString());

        // Create the AdmContact
        AdmContactDTO admContactDTO = admContactMapper.toDto(admContact);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmContactMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admContactDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmContact in the database
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmContact() throws Exception {
        // Initialize the database
        admContact.setUserId(UUID.randomUUID().toString());
        admContactRepository.saveAndFlush(admContact);

        int databaseSizeBeforeDelete = admContactRepository.findAll().size();

        // Delete the admContact
        restAdmContactMockMvc
            .perform(delete(ENTITY_API_URL_ID, admContact.getUserId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmContact> admContactList = admContactRepository.findAll();
        assertThat(admContactList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
