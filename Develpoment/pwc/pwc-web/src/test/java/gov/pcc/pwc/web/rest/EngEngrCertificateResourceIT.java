package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.EngEngrCertificate;
import gov.pcc.pwc.domain.EngEngrCertificatePK;
import gov.pcc.pwc.repository.EngEngrCertificateRepository;
import gov.pcc.pwc.service.dto.EngEngrCertificateDTO;
import gov.pcc.pwc.service.mapper.EngEngrCertificateMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link EngEngrCertificateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EngEngrCertificateResourceIT {

    private static final BigDecimal DEFAULT_ENG_ENGR_CERTIFICATE_VERSION = new BigDecimal(1);
    private static final BigDecimal UPDATED_ENG_ENGR_CERTIFICATE_VERSION = new BigDecimal(2);

    private static final String DEFAULT_ENG_ENGR_CERTIFICATE_NO = "AAAAAAAAA";
    private static final String UPDATED_ENG_ENGR_CERTIFICATE_NO = "BBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final String DEFAULT_REVIEW_STAFF = "AAAAAAAAAA";
    private static final String UPDATED_REVIEW_STAFF = "BBBBBBBBBB";

    private static final Instant DEFAULT_REVIEW_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REVIEW_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REVIEW_DESC = "AAAAAAAAAA";
    private static final String UPDATED_REVIEW_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_APPLY_ITEMS_LIST = "AAA";
    private static final String UPDATED_APPLY_ITEMS_LIST = "BBB";

    private static final String DEFAULT_APPLY_TYPE = "A";
    private static final String UPDATED_APPLY_TYPE = "B";

    private static final LocalDate DEFAULT_APPLY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPLY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ENGR_ID = "AAAAAAAAAA";
    private static final String UPDATED_ENGR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_IDNO = "AAAAAAAAAA";
    private static final String UPDATED_IDNO = "BBBBBBBBBB";

    private static final String DEFAULT_CH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EN_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SEX = "0";
    private static final String UPDATED_SEX = "1";

    private static final String DEFAULT_CONTACT_TEL_OFFICE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_TEL_OFFICE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_TEL_HOME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_TEL_HOME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_TEL = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PERMANENT_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_PERMANENT_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_PERMANENT_ADDR_CITY = "AAAAA";
    private static final String UPDATED_PERMANENT_ADDR_CITY = "BBBBB";

    private static final String DEFAULT_PERMANENT_ADDR_TOWN = "AAA";
    private static final String UPDATED_PERMANENT_ADDR_TOWN = "BBB";

    private static final String DEFAULT_PERMANENT_ADDR_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_PERMANENT_ADDR_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_ADDR_CITY = "AAAAA";
    private static final String UPDATED_CONTACT_ADDR_CITY = "BBBBB";

    private static final String DEFAULT_CONTACT_ADDR_TOWN = "AAA";
    private static final String UPDATED_CONTACT_ADDR_TOWN = "BBB";

    private static final String DEFAULT_CONTACT_ADDR_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_ADDR_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTITY_TYPE = "A";
    private static final String UPDATED_IDENTITY_TYPE = "B";

    private static final String DEFAULT_ADDR_FOREIGN = "AAAAAAAAAA";
    private static final String UPDATED_ADDR_FOREIGN = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAA";
    private static final String UPDATED_SUBJECT = "BBB";

    private static final String DEFAULT_ENGR_CERTIFICATE_DOC_NO = "AAAAAAAAAA";
    private static final String UPDATED_ENGR_CERTIFICATE_DOC_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ENGR_CERTIFICATE_DOC_NO_OLD = "AAAAAAAAAA";
    private static final String UPDATED_ENGR_CERTIFICATE_DOC_NO_OLD = "BBBBBBBBBB";

    private static final String DEFAULT_EXAM_TYPE = "A";
    private static final String UPDATED_EXAM_TYPE = "B";

    private static final String DEFAULT_EXAM_NO = "AAAAAAAAAA";
    private static final String UPDATED_EXAM_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EXAM_NO_OLD = "AAAAAAAAAA";
    private static final String UPDATED_EXAM_NO_OLD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RECEIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECEIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RECEIVE_NO = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PUBLIC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PUBLIC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RECEIVE_TYPE = "A";
    private static final String UPDATED_RECEIVE_TYPE = "B";

    private static final String DEFAULT_RECEIVE_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVE_ADDR_CITY = "AAAAA";
    private static final String UPDATED_RECEIVE_ADDR_CITY = "BBBBB";

    private static final String DEFAULT_RECEIVE_ADDR_TOWN = "AAA";
    private static final String UPDATED_RECEIVE_ADDR_TOWN = "BBB";

    private static final String DEFAULT_RECEIVE_ADDR_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_ADDR_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTOR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_FROM = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_FROM = "BBBBBBBBBB";

    private static final Instant DEFAULT_TRANSFER_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSFER_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/eng-engr-certificates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{engEngrCertificateId}";

    @Autowired
    private EngEngrCertificateRepository engEngrCertificateRepository;

    @Autowired
    private EngEngrCertificateMapper engEngrCertificateMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEngEngrCertificateMockMvc;

    private EngEngrCertificate engEngrCertificate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EngEngrCertificate createEntity(EntityManager em) {
        EngEngrCertificate engEngrCertificate = new EngEngrCertificate()
            .engEngrCertificateVersion(DEFAULT_ENG_ENGR_CERTIFICATE_VERSION)
            .engEngrCertificateNo(DEFAULT_ENG_ENGR_CERTIFICATE_NO)
            .status(DEFAULT_STATUS)
            .reviewStaff(DEFAULT_REVIEW_STAFF)
            .reviewTime(DEFAULT_REVIEW_TIME)
            .reviewDesc(DEFAULT_REVIEW_DESC)
            .applyItemsList(DEFAULT_APPLY_ITEMS_LIST)
            .applyType(DEFAULT_APPLY_TYPE)
            .applyDate(DEFAULT_APPLY_DATE)
            .engrId(DEFAULT_ENGR_ID)
            .idno(DEFAULT_IDNO)
            .chName(DEFAULT_CH_NAME)
            .enName(DEFAULT_EN_NAME)
            .birthDate(DEFAULT_BIRTH_DATE)
            .sex(DEFAULT_SEX)
            .contactTelOffice(DEFAULT_CONTACT_TEL_OFFICE)
            .contactTelHome(DEFAULT_CONTACT_TEL_HOME)
            .mobileTel(DEFAULT_MOBILE_TEL)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL)
            .permanentAddrZip(DEFAULT_PERMANENT_ADDR_ZIP)
            .permanentAddrCity(DEFAULT_PERMANENT_ADDR_CITY)
            .permanentAddrTown(DEFAULT_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(DEFAULT_PERMANENT_ADDR_OTHER)
            .contactAddrZip(DEFAULT_CONTACT_ADDR_ZIP)
            .contactAddrCity(DEFAULT_CONTACT_ADDR_CITY)
            .contactAddrTown(DEFAULT_CONTACT_ADDR_TOWN)
            .contactAddrOther(DEFAULT_CONTACT_ADDR_OTHER)
            .identityType(DEFAULT_IDENTITY_TYPE)
            .addrForeign(DEFAULT_ADDR_FOREIGN)
            .subject(DEFAULT_SUBJECT)
            .engrCertificateDocNo(DEFAULT_ENGR_CERTIFICATE_DOC_NO)
            .engrCertificateDocNoOld(DEFAULT_ENGR_CERTIFICATE_DOC_NO_OLD)
            .examType(DEFAULT_EXAM_TYPE)
            .examNo(DEFAULT_EXAM_NO)
            .examNoOld(DEFAULT_EXAM_NO_OLD)
            .receiveDate(DEFAULT_RECEIVE_DATE)
            .receiveNo(DEFAULT_RECEIVE_NO)
            .publicDate(DEFAULT_PUBLIC_DATE)
            .receiveType(DEFAULT_RECEIVE_TYPE)
            .receiveAddrZip(DEFAULT_RECEIVE_ADDR_ZIP)
            .receiveAddrCity(DEFAULT_RECEIVE_ADDR_CITY)
            .receiveAddrTown(DEFAULT_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(DEFAULT_RECEIVE_ADDR_OTHER)
            .contractorId(DEFAULT_CONTRACTOR_ID)
            .remark(DEFAULT_REMARK)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .transferFrom(DEFAULT_TRANSFER_FROM)
            .transferTime(DEFAULT_TRANSFER_TIME);
        return engEngrCertificate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EngEngrCertificate createUpdatedEntity(EntityManager em) {
        EngEngrCertificate engEngrCertificate = new EngEngrCertificate()
            .engEngrCertificateVersion(UPDATED_ENG_ENGR_CERTIFICATE_VERSION)
            .engEngrCertificateNo(UPDATED_ENG_ENGR_CERTIFICATE_NO)
            .status(UPDATED_STATUS)
            .reviewStaff(UPDATED_REVIEW_STAFF)
            .reviewTime(UPDATED_REVIEW_TIME)
            .reviewDesc(UPDATED_REVIEW_DESC)
            .applyItemsList(UPDATED_APPLY_ITEMS_LIST)
            .applyType(UPDATED_APPLY_TYPE)
            .applyDate(UPDATED_APPLY_DATE)
            .engrId(UPDATED_ENGR_ID)
            .idno(UPDATED_IDNO)
            .chName(UPDATED_CH_NAME)
            .enName(UPDATED_EN_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .contactTelOffice(UPDATED_CONTACT_TEL_OFFICE)
            .contactTelHome(UPDATED_CONTACT_TEL_HOME)
            .mobileTel(UPDATED_MOBILE_TEL)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .permanentAddrZip(UPDATED_PERMANENT_ADDR_ZIP)
            .permanentAddrCity(UPDATED_PERMANENT_ADDR_CITY)
            .permanentAddrTown(UPDATED_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(UPDATED_PERMANENT_ADDR_OTHER)
            .contactAddrZip(UPDATED_CONTACT_ADDR_ZIP)
            .contactAddrCity(UPDATED_CONTACT_ADDR_CITY)
            .contactAddrTown(UPDATED_CONTACT_ADDR_TOWN)
            .contactAddrOther(UPDATED_CONTACT_ADDR_OTHER)
            .identityType(UPDATED_IDENTITY_TYPE)
            .addrForeign(UPDATED_ADDR_FOREIGN)
            .subject(UPDATED_SUBJECT)
            .engrCertificateDocNo(UPDATED_ENGR_CERTIFICATE_DOC_NO)
            .engrCertificateDocNoOld(UPDATED_ENGR_CERTIFICATE_DOC_NO_OLD)
            .examType(UPDATED_EXAM_TYPE)
            .examNo(UPDATED_EXAM_NO)
            .examNoOld(UPDATED_EXAM_NO_OLD)
            .receiveDate(UPDATED_RECEIVE_DATE)
            .receiveNo(UPDATED_RECEIVE_NO)
            .publicDate(UPDATED_PUBLIC_DATE)
            .receiveType(UPDATED_RECEIVE_TYPE)
            .receiveAddrZip(UPDATED_RECEIVE_ADDR_ZIP)
            .receiveAddrCity(UPDATED_RECEIVE_ADDR_CITY)
            .receiveAddrTown(UPDATED_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(UPDATED_RECEIVE_ADDR_OTHER)
            .contractorId(UPDATED_CONTRACTOR_ID)
            .remark(UPDATED_REMARK)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);
        return engEngrCertificate;
    }

    @BeforeEach
    public void initTest() {
        engEngrCertificate = createEntity(em);
    }

    @Test
    @Transactional
    void createEngEngrCertificate() throws Exception {
        int databaseSizeBeforeCreate = engEngrCertificateRepository.findAll().size();
        // Create the EngEngrCertificate
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);
        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeCreate + 1);
        EngEngrCertificate testEngEngrCertificate = engEngrCertificateList.get(engEngrCertificateList.size() - 1);
        assertThat(testEngEngrCertificate.getEngEngrCertificateVersion()).isEqualByComparingTo(DEFAULT_ENG_ENGR_CERTIFICATE_VERSION);
        assertThat(testEngEngrCertificate.getEngEngrCertificateNo()).isEqualTo(DEFAULT_ENG_ENGR_CERTIFICATE_NO);
        assertThat(testEngEngrCertificate.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEngEngrCertificate.getReviewStaff()).isEqualTo(DEFAULT_REVIEW_STAFF);
        assertThat(testEngEngrCertificate.getReviewTime()).isEqualTo(DEFAULT_REVIEW_TIME);
        assertThat(testEngEngrCertificate.getReviewDesc()).isEqualTo(DEFAULT_REVIEW_DESC);
        assertThat(testEngEngrCertificate.getApplyItemsList()).isEqualTo(DEFAULT_APPLY_ITEMS_LIST);
        assertThat(testEngEngrCertificate.getApplyType()).isEqualTo(DEFAULT_APPLY_TYPE);
        assertThat(testEngEngrCertificate.getApplyDate()).isEqualTo(DEFAULT_APPLY_DATE);
        assertThat(testEngEngrCertificate.getEngrId()).isEqualTo(DEFAULT_ENGR_ID);
        assertThat(testEngEngrCertificate.getIdno()).isEqualTo(DEFAULT_IDNO);
        assertThat(testEngEngrCertificate.getChName()).isEqualTo(DEFAULT_CH_NAME);
        assertThat(testEngEngrCertificate.getEnName()).isEqualTo(DEFAULT_EN_NAME);
        assertThat(testEngEngrCertificate.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testEngEngrCertificate.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testEngEngrCertificate.getContactTelOffice()).isEqualTo(DEFAULT_CONTACT_TEL_OFFICE);
        assertThat(testEngEngrCertificate.getContactTelHome()).isEqualTo(DEFAULT_CONTACT_TEL_HOME);
        assertThat(testEngEngrCertificate.getMobileTel()).isEqualTo(DEFAULT_MOBILE_TEL);
        assertThat(testEngEngrCertificate.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testEngEngrCertificate.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEngEngrCertificate.getPermanentAddrZip()).isEqualTo(DEFAULT_PERMANENT_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getPermanentAddrCity()).isEqualTo(DEFAULT_PERMANENT_ADDR_CITY);
        assertThat(testEngEngrCertificate.getPermanentAddrTown()).isEqualTo(DEFAULT_PERMANENT_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getPermanentAddrOther()).isEqualTo(DEFAULT_PERMANENT_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getContactAddrZip()).isEqualTo(DEFAULT_CONTACT_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getContactAddrCity()).isEqualTo(DEFAULT_CONTACT_ADDR_CITY);
        assertThat(testEngEngrCertificate.getContactAddrTown()).isEqualTo(DEFAULT_CONTACT_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getContactAddrOther()).isEqualTo(DEFAULT_CONTACT_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getIdentityType()).isEqualTo(DEFAULT_IDENTITY_TYPE);
        assertThat(testEngEngrCertificate.getAddrForeign()).isEqualTo(DEFAULT_ADDR_FOREIGN);
        assertThat(testEngEngrCertificate.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testEngEngrCertificate.getEngrCertificateDocNo()).isEqualTo(DEFAULT_ENGR_CERTIFICATE_DOC_NO);
        assertThat(testEngEngrCertificate.getEngrCertificateDocNoOld()).isEqualTo(DEFAULT_ENGR_CERTIFICATE_DOC_NO_OLD);
        assertThat(testEngEngrCertificate.getExamType()).isEqualTo(DEFAULT_EXAM_TYPE);
        assertThat(testEngEngrCertificate.getExamNo()).isEqualTo(DEFAULT_EXAM_NO);
        assertThat(testEngEngrCertificate.getExamNoOld()).isEqualTo(DEFAULT_EXAM_NO_OLD);
        assertThat(testEngEngrCertificate.getReceiveDate()).isEqualTo(DEFAULT_RECEIVE_DATE);
        assertThat(testEngEngrCertificate.getReceiveNo()).isEqualTo(DEFAULT_RECEIVE_NO);
        assertThat(testEngEngrCertificate.getPublicDate()).isEqualTo(DEFAULT_PUBLIC_DATE);
        assertThat(testEngEngrCertificate.getReceiveType()).isEqualTo(DEFAULT_RECEIVE_TYPE);
        assertThat(testEngEngrCertificate.getReceiveAddrZip()).isEqualTo(DEFAULT_RECEIVE_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getReceiveAddrCity()).isEqualTo(DEFAULT_RECEIVE_ADDR_CITY);
        assertThat(testEngEngrCertificate.getReceiveAddrTown()).isEqualTo(DEFAULT_RECEIVE_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getReceiveAddrOther()).isEqualTo(DEFAULT_RECEIVE_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getContractorId()).isEqualTo(DEFAULT_CONTRACTOR_ID);
        assertThat(testEngEngrCertificate.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testEngEngrCertificate.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testEngEngrCertificate.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testEngEngrCertificate.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEngEngrCertificate.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testEngEngrCertificate.getTransferFrom()).isEqualTo(DEFAULT_TRANSFER_FROM);
        assertThat(testEngEngrCertificate.getTransferTime()).isEqualTo(DEFAULT_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void createEngEngrCertificateWithExistingId() throws Exception {
        // Create the EngEngrCertificate with an existing ID
        engEngrCertificate.setEngEngrCertificateId("existing_id");
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        int databaseSizeBeforeCreate = engEngrCertificateRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEngEngrCertificateVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrCertificateRepository.findAll().size();
        // set the field null
        engEngrCertificate.setEngEngrCertificateVersion(null);

        // Create the EngEngrCertificate, which fails.
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIdentityTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrCertificateRepository.findAll().size();
        // set the field null
        engEngrCertificate.setIdentityType(null);

        // Create the EngEngrCertificate, which fails.
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubjectIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrCertificateRepository.findAll().size();
        // set the field null
        engEngrCertificate.setSubject(null);

        // Create the EngEngrCertificate, which fails.
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExamTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrCertificateRepository.findAll().size();
        // set the field null
        engEngrCertificate.setExamType(null);

        // Create the EngEngrCertificate, which fails.
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrCertificateRepository.findAll().size();
        // set the field null
        engEngrCertificate.setUpdateTime(null);

        // Create the EngEngrCertificate, which fails.
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrCertificateRepository.findAll().size();
        // set the field null
        engEngrCertificate.setUpdateUser(null);

        // Create the EngEngrCertificate, which fails.
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrCertificateRepository.findAll().size();
        // set the field null
        engEngrCertificate.setCreateTime(null);

        // Create the EngEngrCertificate, which fails.
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrCertificateRepository.findAll().size();
        // set the field null
        engEngrCertificate.setCreateUser(null);

        // Create the EngEngrCertificate, which fails.
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllEngEngrCertificates() throws Exception {
        // Initialize the database
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());
        engEngrCertificateRepository.saveAndFlush(engEngrCertificate);

        // Get all the engEngrCertificateList
        restEngEngrCertificateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=engEngrCertificateId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].engEngrCertificateId").value(hasItem(engEngrCertificate.getEngEngrCertificateId())))
            .andExpect(jsonPath("$.[*].engEngrCertificateVersion").value(hasItem(sameNumber(DEFAULT_ENG_ENGR_CERTIFICATE_VERSION))))
            .andExpect(jsonPath("$.[*].engEngrCertificateNo").value(hasItem(DEFAULT_ENG_ENGR_CERTIFICATE_NO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].reviewStaff").value(hasItem(DEFAULT_REVIEW_STAFF)))
            .andExpect(jsonPath("$.[*].reviewTime").value(hasItem(DEFAULT_REVIEW_TIME.toString())))
            .andExpect(jsonPath("$.[*].reviewDesc").value(hasItem(DEFAULT_REVIEW_DESC)))
            .andExpect(jsonPath("$.[*].applyItemsList").value(hasItem(DEFAULT_APPLY_ITEMS_LIST)))
            .andExpect(jsonPath("$.[*].applyType").value(hasItem(DEFAULT_APPLY_TYPE)))
            .andExpect(jsonPath("$.[*].applyDate").value(hasItem(DEFAULT_APPLY_DATE.toString())))
            .andExpect(jsonPath("$.[*].engrId").value(hasItem(DEFAULT_ENGR_ID)))
            .andExpect(jsonPath("$.[*].idno").value(hasItem(DEFAULT_IDNO)))
            .andExpect(jsonPath("$.[*].chName").value(hasItem(DEFAULT_CH_NAME)))
            .andExpect(jsonPath("$.[*].enName").value(hasItem(DEFAULT_EN_NAME)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].contactTelOffice").value(hasItem(DEFAULT_CONTACT_TEL_OFFICE)))
            .andExpect(jsonPath("$.[*].contactTelHome").value(hasItem(DEFAULT_CONTACT_TEL_HOME)))
            .andExpect(jsonPath("$.[*].mobileTel").value(hasItem(DEFAULT_MOBILE_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].permanentAddrZip").value(hasItem(DEFAULT_PERMANENT_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].permanentAddrCity").value(hasItem(DEFAULT_PERMANENT_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].permanentAddrTown").value(hasItem(DEFAULT_PERMANENT_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].permanentAddrOther").value(hasItem(DEFAULT_PERMANENT_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].contactAddrZip").value(hasItem(DEFAULT_CONTACT_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].contactAddrCity").value(hasItem(DEFAULT_CONTACT_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].contactAddrTown").value(hasItem(DEFAULT_CONTACT_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].contactAddrOther").value(hasItem(DEFAULT_CONTACT_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].identityType").value(hasItem(DEFAULT_IDENTITY_TYPE)))
            .andExpect(jsonPath("$.[*].addrForeign").value(hasItem(DEFAULT_ADDR_FOREIGN)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].engrCertificateDocNo").value(hasItem(DEFAULT_ENGR_CERTIFICATE_DOC_NO)))
            .andExpect(jsonPath("$.[*].engrCertificateDocNoOld").value(hasItem(DEFAULT_ENGR_CERTIFICATE_DOC_NO_OLD)))
            .andExpect(jsonPath("$.[*].examType").value(hasItem(DEFAULT_EXAM_TYPE)))
            .andExpect(jsonPath("$.[*].examNo").value(hasItem(DEFAULT_EXAM_NO)))
            .andExpect(jsonPath("$.[*].examNoOld").value(hasItem(DEFAULT_EXAM_NO_OLD)))
            .andExpect(jsonPath("$.[*].receiveDate").value(hasItem(DEFAULT_RECEIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].receiveNo").value(hasItem(DEFAULT_RECEIVE_NO)))
            .andExpect(jsonPath("$.[*].publicDate").value(hasItem(DEFAULT_PUBLIC_DATE.toString())))
            .andExpect(jsonPath("$.[*].receiveType").value(hasItem(DEFAULT_RECEIVE_TYPE)))
            .andExpect(jsonPath("$.[*].receiveAddrZip").value(hasItem(DEFAULT_RECEIVE_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].receiveAddrCity").value(hasItem(DEFAULT_RECEIVE_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].receiveAddrTown").value(hasItem(DEFAULT_RECEIVE_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].receiveAddrOther").value(hasItem(DEFAULT_RECEIVE_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].contractorId").value(hasItem(DEFAULT_CONTRACTOR_ID)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].transferFrom").value(hasItem(DEFAULT_TRANSFER_FROM)))
            .andExpect(jsonPath("$.[*].transferTime").value(hasItem(DEFAULT_TRANSFER_TIME.toString())));
    }

    @Test
    @Transactional
    void getEngEngrCertificate() throws Exception {
        // Initialize the database
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());
        engEngrCertificateRepository.saveAndFlush(engEngrCertificate);

        // Get the engEngrCertificate
        restEngEngrCertificateMockMvc
            .perform(get(ENTITY_API_URL_ID, engEngrCertificate.getEngEngrCertificateId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.engEngrCertificateId").value(engEngrCertificate.getEngEngrCertificateId()))
            .andExpect(jsonPath("$.engEngrCertificateVersion").value(sameNumber(DEFAULT_ENG_ENGR_CERTIFICATE_VERSION)))
            .andExpect(jsonPath("$.engEngrCertificateNo").value(DEFAULT_ENG_ENGR_CERTIFICATE_NO))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.reviewStaff").value(DEFAULT_REVIEW_STAFF))
            .andExpect(jsonPath("$.reviewTime").value(DEFAULT_REVIEW_TIME.toString()))
            .andExpect(jsonPath("$.reviewDesc").value(DEFAULT_REVIEW_DESC))
            .andExpect(jsonPath("$.applyItemsList").value(DEFAULT_APPLY_ITEMS_LIST))
            .andExpect(jsonPath("$.applyType").value(DEFAULT_APPLY_TYPE))
            .andExpect(jsonPath("$.applyDate").value(DEFAULT_APPLY_DATE.toString()))
            .andExpect(jsonPath("$.engrId").value(DEFAULT_ENGR_ID))
            .andExpect(jsonPath("$.idno").value(DEFAULT_IDNO))
            .andExpect(jsonPath("$.chName").value(DEFAULT_CH_NAME))
            .andExpect(jsonPath("$.enName").value(DEFAULT_EN_NAME))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX))
            .andExpect(jsonPath("$.contactTelOffice").value(DEFAULT_CONTACT_TEL_OFFICE))
            .andExpect(jsonPath("$.contactTelHome").value(DEFAULT_CONTACT_TEL_HOME))
            .andExpect(jsonPath("$.mobileTel").value(DEFAULT_MOBILE_TEL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.permanentAddrZip").value(DEFAULT_PERMANENT_ADDR_ZIP))
            .andExpect(jsonPath("$.permanentAddrCity").value(DEFAULT_PERMANENT_ADDR_CITY))
            .andExpect(jsonPath("$.permanentAddrTown").value(DEFAULT_PERMANENT_ADDR_TOWN))
            .andExpect(jsonPath("$.permanentAddrOther").value(DEFAULT_PERMANENT_ADDR_OTHER))
            .andExpect(jsonPath("$.contactAddrZip").value(DEFAULT_CONTACT_ADDR_ZIP))
            .andExpect(jsonPath("$.contactAddrCity").value(DEFAULT_CONTACT_ADDR_CITY))
            .andExpect(jsonPath("$.contactAddrTown").value(DEFAULT_CONTACT_ADDR_TOWN))
            .andExpect(jsonPath("$.contactAddrOther").value(DEFAULT_CONTACT_ADDR_OTHER))
            .andExpect(jsonPath("$.identityType").value(DEFAULT_IDENTITY_TYPE))
            .andExpect(jsonPath("$.addrForeign").value(DEFAULT_ADDR_FOREIGN))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.engrCertificateDocNo").value(DEFAULT_ENGR_CERTIFICATE_DOC_NO))
            .andExpect(jsonPath("$.engrCertificateDocNoOld").value(DEFAULT_ENGR_CERTIFICATE_DOC_NO_OLD))
            .andExpect(jsonPath("$.examType").value(DEFAULT_EXAM_TYPE))
            .andExpect(jsonPath("$.examNo").value(DEFAULT_EXAM_NO))
            .andExpect(jsonPath("$.examNoOld").value(DEFAULT_EXAM_NO_OLD))
            .andExpect(jsonPath("$.receiveDate").value(DEFAULT_RECEIVE_DATE.toString()))
            .andExpect(jsonPath("$.receiveNo").value(DEFAULT_RECEIVE_NO))
            .andExpect(jsonPath("$.publicDate").value(DEFAULT_PUBLIC_DATE.toString()))
            .andExpect(jsonPath("$.receiveType").value(DEFAULT_RECEIVE_TYPE))
            .andExpect(jsonPath("$.receiveAddrZip").value(DEFAULT_RECEIVE_ADDR_ZIP))
            .andExpect(jsonPath("$.receiveAddrCity").value(DEFAULT_RECEIVE_ADDR_CITY))
            .andExpect(jsonPath("$.receiveAddrTown").value(DEFAULT_RECEIVE_ADDR_TOWN))
            .andExpect(jsonPath("$.receiveAddrOther").value(DEFAULT_RECEIVE_ADDR_OTHER))
            .andExpect(jsonPath("$.contractorId").value(DEFAULT_CONTRACTOR_ID))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.transferFrom").value(DEFAULT_TRANSFER_FROM))
            .andExpect(jsonPath("$.transferTime").value(DEFAULT_TRANSFER_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEngEngrCertificate() throws Exception {
        // Get the engEngrCertificate
        restEngEngrCertificateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEngEngrCertificate() throws Exception {
        // Initialize the database
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());
        engEngrCertificateRepository.saveAndFlush(engEngrCertificate);

        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();

        // Update the engEngrCertificate
        EngEngrCertificate updatedEngEngrCertificate = engEngrCertificateRepository
            .findById(new EngEngrCertificatePK(engEngrCertificate.getEngEngrCertificateId(), engEngrCertificate.getEngEngrCertificateVersion()))
            .get();
        // Disconnect from session so that the updates on updatedEngEngrCertificate are not directly saved in db
        em.detach(updatedEngEngrCertificate);
        updatedEngEngrCertificate
            .engEngrCertificateVersion(UPDATED_ENG_ENGR_CERTIFICATE_VERSION)
            .engEngrCertificateNo(UPDATED_ENG_ENGR_CERTIFICATE_NO)
            .status(UPDATED_STATUS)
            .reviewStaff(UPDATED_REVIEW_STAFF)
            .reviewTime(UPDATED_REVIEW_TIME)
            .reviewDesc(UPDATED_REVIEW_DESC)
            .applyItemsList(UPDATED_APPLY_ITEMS_LIST)
            .applyType(UPDATED_APPLY_TYPE)
            .applyDate(UPDATED_APPLY_DATE)
            .engrId(UPDATED_ENGR_ID)
            .idno(UPDATED_IDNO)
            .chName(UPDATED_CH_NAME)
            .enName(UPDATED_EN_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .contactTelOffice(UPDATED_CONTACT_TEL_OFFICE)
            .contactTelHome(UPDATED_CONTACT_TEL_HOME)
            .mobileTel(UPDATED_MOBILE_TEL)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .permanentAddrZip(UPDATED_PERMANENT_ADDR_ZIP)
            .permanentAddrCity(UPDATED_PERMANENT_ADDR_CITY)
            .permanentAddrTown(UPDATED_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(UPDATED_PERMANENT_ADDR_OTHER)
            .contactAddrZip(UPDATED_CONTACT_ADDR_ZIP)
            .contactAddrCity(UPDATED_CONTACT_ADDR_CITY)
            .contactAddrTown(UPDATED_CONTACT_ADDR_TOWN)
            .contactAddrOther(UPDATED_CONTACT_ADDR_OTHER)
            .identityType(UPDATED_IDENTITY_TYPE)
            .addrForeign(UPDATED_ADDR_FOREIGN)
            .subject(UPDATED_SUBJECT)
            .engrCertificateDocNo(UPDATED_ENGR_CERTIFICATE_DOC_NO)
            .engrCertificateDocNoOld(UPDATED_ENGR_CERTIFICATE_DOC_NO_OLD)
            .examType(UPDATED_EXAM_TYPE)
            .examNo(UPDATED_EXAM_NO)
            .examNoOld(UPDATED_EXAM_NO_OLD)
            .receiveDate(UPDATED_RECEIVE_DATE)
            .receiveNo(UPDATED_RECEIVE_NO)
            .publicDate(UPDATED_PUBLIC_DATE)
            .receiveType(UPDATED_RECEIVE_TYPE)
            .receiveAddrZip(UPDATED_RECEIVE_ADDR_ZIP)
            .receiveAddrCity(UPDATED_RECEIVE_ADDR_CITY)
            .receiveAddrTown(UPDATED_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(UPDATED_RECEIVE_ADDR_OTHER)
            .contractorId(UPDATED_CONTRACTOR_ID)
            .remark(UPDATED_REMARK)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(updatedEngEngrCertificate);

        restEngEngrCertificateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, engEngrCertificateDTO.getEngEngrCertificateId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isOk());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
        EngEngrCertificate testEngEngrCertificate = engEngrCertificateList.get(engEngrCertificateList.size() - 1);
        assertThat(testEngEngrCertificate.getEngEngrCertificateVersion()).isEqualByComparingTo(UPDATED_ENG_ENGR_CERTIFICATE_VERSION);
        assertThat(testEngEngrCertificate.getEngEngrCertificateNo()).isEqualTo(UPDATED_ENG_ENGR_CERTIFICATE_NO);
        assertThat(testEngEngrCertificate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEngEngrCertificate.getReviewStaff()).isEqualTo(UPDATED_REVIEW_STAFF);
        assertThat(testEngEngrCertificate.getReviewTime()).isEqualTo(UPDATED_REVIEW_TIME);
        assertThat(testEngEngrCertificate.getReviewDesc()).isEqualTo(UPDATED_REVIEW_DESC);
        assertThat(testEngEngrCertificate.getApplyItemsList()).isEqualTo(UPDATED_APPLY_ITEMS_LIST);
        assertThat(testEngEngrCertificate.getApplyType()).isEqualTo(UPDATED_APPLY_TYPE);
        assertThat(testEngEngrCertificate.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testEngEngrCertificate.getEngrId()).isEqualTo(UPDATED_ENGR_ID);
        assertThat(testEngEngrCertificate.getIdno()).isEqualTo(UPDATED_IDNO);
        assertThat(testEngEngrCertificate.getChName()).isEqualTo(UPDATED_CH_NAME);
        assertThat(testEngEngrCertificate.getEnName()).isEqualTo(UPDATED_EN_NAME);
        assertThat(testEngEngrCertificate.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testEngEngrCertificate.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testEngEngrCertificate.getContactTelOffice()).isEqualTo(UPDATED_CONTACT_TEL_OFFICE);
        assertThat(testEngEngrCertificate.getContactTelHome()).isEqualTo(UPDATED_CONTACT_TEL_HOME);
        assertThat(testEngEngrCertificate.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testEngEngrCertificate.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testEngEngrCertificate.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEngEngrCertificate.getPermanentAddrZip()).isEqualTo(UPDATED_PERMANENT_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getPermanentAddrCity()).isEqualTo(UPDATED_PERMANENT_ADDR_CITY);
        assertThat(testEngEngrCertificate.getPermanentAddrTown()).isEqualTo(UPDATED_PERMANENT_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getPermanentAddrOther()).isEqualTo(UPDATED_PERMANENT_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getContactAddrZip()).isEqualTo(UPDATED_CONTACT_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getContactAddrCity()).isEqualTo(UPDATED_CONTACT_ADDR_CITY);
        assertThat(testEngEngrCertificate.getContactAddrTown()).isEqualTo(UPDATED_CONTACT_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getContactAddrOther()).isEqualTo(UPDATED_CONTACT_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getIdentityType()).isEqualTo(UPDATED_IDENTITY_TYPE);
        assertThat(testEngEngrCertificate.getAddrForeign()).isEqualTo(UPDATED_ADDR_FOREIGN);
        assertThat(testEngEngrCertificate.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testEngEngrCertificate.getEngrCertificateDocNo()).isEqualTo(UPDATED_ENGR_CERTIFICATE_DOC_NO);
        assertThat(testEngEngrCertificate.getEngrCertificateDocNoOld()).isEqualTo(UPDATED_ENGR_CERTIFICATE_DOC_NO_OLD);
        assertThat(testEngEngrCertificate.getExamType()).isEqualTo(UPDATED_EXAM_TYPE);
        assertThat(testEngEngrCertificate.getExamNo()).isEqualTo(UPDATED_EXAM_NO);
        assertThat(testEngEngrCertificate.getExamNoOld()).isEqualTo(UPDATED_EXAM_NO_OLD);
        assertThat(testEngEngrCertificate.getReceiveDate()).isEqualTo(UPDATED_RECEIVE_DATE);
        assertThat(testEngEngrCertificate.getReceiveNo()).isEqualTo(UPDATED_RECEIVE_NO);
        assertThat(testEngEngrCertificate.getPublicDate()).isEqualTo(UPDATED_PUBLIC_DATE);
        assertThat(testEngEngrCertificate.getReceiveType()).isEqualTo(UPDATED_RECEIVE_TYPE);
        assertThat(testEngEngrCertificate.getReceiveAddrZip()).isEqualTo(UPDATED_RECEIVE_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getReceiveAddrCity()).isEqualTo(UPDATED_RECEIVE_ADDR_CITY);
        assertThat(testEngEngrCertificate.getReceiveAddrTown()).isEqualTo(UPDATED_RECEIVE_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getReceiveAddrOther()).isEqualTo(UPDATED_RECEIVE_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getContractorId()).isEqualTo(UPDATED_CONTRACTOR_ID);
        assertThat(testEngEngrCertificate.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testEngEngrCertificate.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testEngEngrCertificate.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testEngEngrCertificate.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEngEngrCertificate.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testEngEngrCertificate.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testEngEngrCertificate.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void putNonExistingEngEngrCertificate() throws Exception {
        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());

        // Create the EngEngrCertificate
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEngEngrCertificateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, engEngrCertificateDTO.getEngEngrCertificateId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEngEngrCertificate() throws Exception {
        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());

        // Create the EngEngrCertificate
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEngEngrCertificateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEngEngrCertificate() throws Exception {
        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());

        // Create the EngEngrCertificate
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEngEngrCertificateMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEngEngrCertificateWithPatch() throws Exception {
        // Initialize the database
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());
        engEngrCertificateRepository.saveAndFlush(engEngrCertificate);

        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();

        // Update the engEngrCertificate using partial update
        EngEngrCertificate partialUpdatedEngEngrCertificate = new EngEngrCertificate();
        partialUpdatedEngEngrCertificate.setEngEngrCertificateId(engEngrCertificate.getEngEngrCertificateId());

        partialUpdatedEngEngrCertificate
            .engEngrCertificateVersion(UPDATED_ENG_ENGR_CERTIFICATE_VERSION)
            .status(UPDATED_STATUS)
            .reviewTime(UPDATED_REVIEW_TIME)
            .reviewDesc(UPDATED_REVIEW_DESC)
            .applyType(UPDATED_APPLY_TYPE)
            .applyDate(UPDATED_APPLY_DATE)
            .engrId(UPDATED_ENGR_ID)
            .birthDate(UPDATED_BIRTH_DATE)
//            .sex(UPDATED_SEX)
            .contactTelOffice(UPDATED_CONTACT_TEL_OFFICE)
            .contactTelHome(UPDATED_CONTACT_TEL_HOME)
            .email(UPDATED_EMAIL)
            .permanentAddrZip(UPDATED_PERMANENT_ADDR_ZIP)
            .permanentAddrOther(UPDATED_PERMANENT_ADDR_OTHER)
            .contactAddrZip(UPDATED_CONTACT_ADDR_ZIP)
            .addrForeign(UPDATED_ADDR_FOREIGN)
            .engrCertificateDocNoOld(UPDATED_ENGR_CERTIFICATE_DOC_NO_OLD)
            .examType(UPDATED_EXAM_TYPE)
            .examNo(UPDATED_EXAM_NO)
            .receiveNo(UPDATED_RECEIVE_NO)
            .publicDate(UPDATED_PUBLIC_DATE)
            .receiveType(UPDATED_RECEIVE_TYPE)
            .receiveAddrTown(UPDATED_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(UPDATED_RECEIVE_ADDR_OTHER)
            .contractorId(UPDATED_CONTRACTOR_ID)
            .remark(UPDATED_REMARK)
            .transferFrom(UPDATED_TRANSFER_FROM);

        restEngEngrCertificateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEngEngrCertificate.getEngEngrCertificateId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEngEngrCertificate))
            )
            .andExpect(status().isOk());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
        EngEngrCertificate testEngEngrCertificate = engEngrCertificateList.get(engEngrCertificateList.size() - 1);
        assertThat(testEngEngrCertificate.getEngEngrCertificateVersion()).isEqualByComparingTo(UPDATED_ENG_ENGR_CERTIFICATE_VERSION);
        assertThat(testEngEngrCertificate.getEngEngrCertificateNo()).isEqualTo(DEFAULT_ENG_ENGR_CERTIFICATE_NO);
        assertThat(testEngEngrCertificate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEngEngrCertificate.getReviewStaff()).isEqualTo(DEFAULT_REVIEW_STAFF);
        assertThat(testEngEngrCertificate.getReviewTime()).isEqualTo(UPDATED_REVIEW_TIME);
        assertThat(testEngEngrCertificate.getReviewDesc()).isEqualTo(UPDATED_REVIEW_DESC);
        assertThat(testEngEngrCertificate.getApplyItemsList()).isEqualTo(DEFAULT_APPLY_ITEMS_LIST);
        assertThat(testEngEngrCertificate.getApplyType()).isEqualTo(UPDATED_APPLY_TYPE);
        assertThat(testEngEngrCertificate.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testEngEngrCertificate.getEngrId()).isEqualTo(UPDATED_ENGR_ID);
        assertThat(testEngEngrCertificate.getIdno()).isEqualTo(DEFAULT_IDNO);
        assertThat(testEngEngrCertificate.getChName()).isEqualTo(DEFAULT_CH_NAME);
        assertThat(testEngEngrCertificate.getEnName()).isEqualTo(DEFAULT_EN_NAME);
        assertThat(testEngEngrCertificate.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testEngEngrCertificate.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testEngEngrCertificate.getContactTelOffice()).isEqualTo(UPDATED_CONTACT_TEL_OFFICE);
        assertThat(testEngEngrCertificate.getContactTelHome()).isEqualTo(UPDATED_CONTACT_TEL_HOME);
        assertThat(testEngEngrCertificate.getMobileTel()).isEqualTo(DEFAULT_MOBILE_TEL);
        assertThat(testEngEngrCertificate.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testEngEngrCertificate.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEngEngrCertificate.getPermanentAddrZip()).isEqualTo(UPDATED_PERMANENT_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getPermanentAddrCity()).isEqualTo(DEFAULT_PERMANENT_ADDR_CITY);
        assertThat(testEngEngrCertificate.getPermanentAddrTown()).isEqualTo(DEFAULT_PERMANENT_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getPermanentAddrOther()).isEqualTo(UPDATED_PERMANENT_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getContactAddrZip()).isEqualTo(UPDATED_CONTACT_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getContactAddrCity()).isEqualTo(DEFAULT_CONTACT_ADDR_CITY);
        assertThat(testEngEngrCertificate.getContactAddrTown()).isEqualTo(DEFAULT_CONTACT_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getContactAddrOther()).isEqualTo(DEFAULT_CONTACT_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getIdentityType()).isEqualTo(DEFAULT_IDENTITY_TYPE);
        assertThat(testEngEngrCertificate.getAddrForeign()).isEqualTo(UPDATED_ADDR_FOREIGN);
        assertThat(testEngEngrCertificate.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testEngEngrCertificate.getEngrCertificateDocNo()).isEqualTo(DEFAULT_ENGR_CERTIFICATE_DOC_NO);
        assertThat(testEngEngrCertificate.getEngrCertificateDocNoOld()).isEqualTo(UPDATED_ENGR_CERTIFICATE_DOC_NO_OLD);
        assertThat(testEngEngrCertificate.getExamType()).isEqualTo(UPDATED_EXAM_TYPE);
        assertThat(testEngEngrCertificate.getExamNo()).isEqualTo(UPDATED_EXAM_NO);
        assertThat(testEngEngrCertificate.getExamNoOld()).isEqualTo(DEFAULT_EXAM_NO_OLD);
        assertThat(testEngEngrCertificate.getReceiveDate()).isEqualTo(DEFAULT_RECEIVE_DATE);
        assertThat(testEngEngrCertificate.getReceiveNo()).isEqualTo(UPDATED_RECEIVE_NO);
        assertThat(testEngEngrCertificate.getPublicDate()).isEqualTo(UPDATED_PUBLIC_DATE);
        assertThat(testEngEngrCertificate.getReceiveType()).isEqualTo(UPDATED_RECEIVE_TYPE);
        assertThat(testEngEngrCertificate.getReceiveAddrZip()).isEqualTo(DEFAULT_RECEIVE_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getReceiveAddrCity()).isEqualTo(DEFAULT_RECEIVE_ADDR_CITY);
        assertThat(testEngEngrCertificate.getReceiveAddrTown()).isEqualTo(UPDATED_RECEIVE_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getReceiveAddrOther()).isEqualTo(UPDATED_RECEIVE_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getContractorId()).isEqualTo(UPDATED_CONTRACTOR_ID);
        assertThat(testEngEngrCertificate.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testEngEngrCertificate.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testEngEngrCertificate.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testEngEngrCertificate.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEngEngrCertificate.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testEngEngrCertificate.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testEngEngrCertificate.getTransferTime()).isEqualTo(DEFAULT_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void fullUpdateEngEngrCertificateWithPatch() throws Exception {
        // Initialize the database
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());
        engEngrCertificateRepository.saveAndFlush(engEngrCertificate);

        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();

        // Update the engEngrCertificate using partial update
        EngEngrCertificate partialUpdatedEngEngrCertificate = new EngEngrCertificate();
        partialUpdatedEngEngrCertificate.setEngEngrCertificateId(engEngrCertificate.getEngEngrCertificateId());

        partialUpdatedEngEngrCertificate
            .engEngrCertificateVersion(UPDATED_ENG_ENGR_CERTIFICATE_VERSION)
            .engEngrCertificateNo(UPDATED_ENG_ENGR_CERTIFICATE_NO)
            .status(UPDATED_STATUS)
            .reviewStaff(UPDATED_REVIEW_STAFF)
            .reviewTime(UPDATED_REVIEW_TIME)
            .reviewDesc(UPDATED_REVIEW_DESC)
            .applyItemsList(UPDATED_APPLY_ITEMS_LIST)
            .applyType(UPDATED_APPLY_TYPE)
            .applyDate(UPDATED_APPLY_DATE)
            .engrId(UPDATED_ENGR_ID)
            .idno(UPDATED_IDNO)
            .chName(UPDATED_CH_NAME)
            .enName(UPDATED_EN_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .contactTelOffice(UPDATED_CONTACT_TEL_OFFICE)
            .contactTelHome(UPDATED_CONTACT_TEL_HOME)
            .mobileTel(UPDATED_MOBILE_TEL)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .permanentAddrZip(UPDATED_PERMANENT_ADDR_ZIP)
            .permanentAddrCity(UPDATED_PERMANENT_ADDR_CITY)
            .permanentAddrTown(UPDATED_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(UPDATED_PERMANENT_ADDR_OTHER)
            .contactAddrZip(UPDATED_CONTACT_ADDR_ZIP)
            .contactAddrCity(UPDATED_CONTACT_ADDR_CITY)
            .contactAddrTown(UPDATED_CONTACT_ADDR_TOWN)
            .contactAddrOther(UPDATED_CONTACT_ADDR_OTHER)
            .identityType(UPDATED_IDENTITY_TYPE)
            .addrForeign(UPDATED_ADDR_FOREIGN)
            .subject(UPDATED_SUBJECT)
            .engrCertificateDocNo(UPDATED_ENGR_CERTIFICATE_DOC_NO)
            .engrCertificateDocNoOld(UPDATED_ENGR_CERTIFICATE_DOC_NO_OLD)
            .examType(UPDATED_EXAM_TYPE)
            .examNo(UPDATED_EXAM_NO)
            .examNoOld(UPDATED_EXAM_NO_OLD)
            .receiveDate(UPDATED_RECEIVE_DATE)
            .receiveNo(UPDATED_RECEIVE_NO)
            .publicDate(UPDATED_PUBLIC_DATE)
            .receiveType(UPDATED_RECEIVE_TYPE)
            .receiveAddrZip(UPDATED_RECEIVE_ADDR_ZIP)
            .receiveAddrCity(UPDATED_RECEIVE_ADDR_CITY)
            .receiveAddrTown(UPDATED_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(UPDATED_RECEIVE_ADDR_OTHER)
            .contractorId(UPDATED_CONTRACTOR_ID)
            .remark(UPDATED_REMARK)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);

        restEngEngrCertificateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEngEngrCertificate.getEngEngrCertificateId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEngEngrCertificate))
            )
            .andExpect(status().isOk());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
        EngEngrCertificate testEngEngrCertificate = engEngrCertificateList.get(engEngrCertificateList.size() - 1);
        assertThat(testEngEngrCertificate.getEngEngrCertificateVersion()).isEqualByComparingTo(UPDATED_ENG_ENGR_CERTIFICATE_VERSION);
        assertThat(testEngEngrCertificate.getEngEngrCertificateNo()).isEqualTo(UPDATED_ENG_ENGR_CERTIFICATE_NO);
        assertThat(testEngEngrCertificate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEngEngrCertificate.getReviewStaff()).isEqualTo(UPDATED_REVIEW_STAFF);
        assertThat(testEngEngrCertificate.getReviewTime()).isEqualTo(UPDATED_REVIEW_TIME);
        assertThat(testEngEngrCertificate.getReviewDesc()).isEqualTo(UPDATED_REVIEW_DESC);
        assertThat(testEngEngrCertificate.getApplyItemsList()).isEqualTo(UPDATED_APPLY_ITEMS_LIST);
        assertThat(testEngEngrCertificate.getApplyType()).isEqualTo(UPDATED_APPLY_TYPE);
        assertThat(testEngEngrCertificate.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testEngEngrCertificate.getEngrId()).isEqualTo(UPDATED_ENGR_ID);
        assertThat(testEngEngrCertificate.getIdno()).isEqualTo(UPDATED_IDNO);
        assertThat(testEngEngrCertificate.getChName()).isEqualTo(UPDATED_CH_NAME);
        assertThat(testEngEngrCertificate.getEnName()).isEqualTo(UPDATED_EN_NAME);
        assertThat(testEngEngrCertificate.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testEngEngrCertificate.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testEngEngrCertificate.getContactTelOffice()).isEqualTo(UPDATED_CONTACT_TEL_OFFICE);
        assertThat(testEngEngrCertificate.getContactTelHome()).isEqualTo(UPDATED_CONTACT_TEL_HOME);
        assertThat(testEngEngrCertificate.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testEngEngrCertificate.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testEngEngrCertificate.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEngEngrCertificate.getPermanentAddrZip()).isEqualTo(UPDATED_PERMANENT_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getPermanentAddrCity()).isEqualTo(UPDATED_PERMANENT_ADDR_CITY);
        assertThat(testEngEngrCertificate.getPermanentAddrTown()).isEqualTo(UPDATED_PERMANENT_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getPermanentAddrOther()).isEqualTo(UPDATED_PERMANENT_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getContactAddrZip()).isEqualTo(UPDATED_CONTACT_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getContactAddrCity()).isEqualTo(UPDATED_CONTACT_ADDR_CITY);
        assertThat(testEngEngrCertificate.getContactAddrTown()).isEqualTo(UPDATED_CONTACT_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getContactAddrOther()).isEqualTo(UPDATED_CONTACT_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getIdentityType()).isEqualTo(UPDATED_IDENTITY_TYPE);
        assertThat(testEngEngrCertificate.getAddrForeign()).isEqualTo(UPDATED_ADDR_FOREIGN);
        assertThat(testEngEngrCertificate.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testEngEngrCertificate.getEngrCertificateDocNo()).isEqualTo(UPDATED_ENGR_CERTIFICATE_DOC_NO);
        assertThat(testEngEngrCertificate.getEngrCertificateDocNoOld()).isEqualTo(UPDATED_ENGR_CERTIFICATE_DOC_NO_OLD);
        assertThat(testEngEngrCertificate.getExamType()).isEqualTo(UPDATED_EXAM_TYPE);
        assertThat(testEngEngrCertificate.getExamNo()).isEqualTo(UPDATED_EXAM_NO);
        assertThat(testEngEngrCertificate.getExamNoOld()).isEqualTo(UPDATED_EXAM_NO_OLD);
        assertThat(testEngEngrCertificate.getReceiveDate()).isEqualTo(UPDATED_RECEIVE_DATE);
        assertThat(testEngEngrCertificate.getReceiveNo()).isEqualTo(UPDATED_RECEIVE_NO);
        assertThat(testEngEngrCertificate.getPublicDate()).isEqualTo(UPDATED_PUBLIC_DATE);
        assertThat(testEngEngrCertificate.getReceiveType()).isEqualTo(UPDATED_RECEIVE_TYPE);
        assertThat(testEngEngrCertificate.getReceiveAddrZip()).isEqualTo(UPDATED_RECEIVE_ADDR_ZIP);
        assertThat(testEngEngrCertificate.getReceiveAddrCity()).isEqualTo(UPDATED_RECEIVE_ADDR_CITY);
        assertThat(testEngEngrCertificate.getReceiveAddrTown()).isEqualTo(UPDATED_RECEIVE_ADDR_TOWN);
        assertThat(testEngEngrCertificate.getReceiveAddrOther()).isEqualTo(UPDATED_RECEIVE_ADDR_OTHER);
        assertThat(testEngEngrCertificate.getContractorId()).isEqualTo(UPDATED_CONTRACTOR_ID);
        assertThat(testEngEngrCertificate.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testEngEngrCertificate.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testEngEngrCertificate.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testEngEngrCertificate.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEngEngrCertificate.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testEngEngrCertificate.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testEngEngrCertificate.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingEngEngrCertificate() throws Exception {
        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());

        // Create the EngEngrCertificate
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEngEngrCertificateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, engEngrCertificateDTO.getEngEngrCertificateId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEngEngrCertificate() throws Exception {
        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());

        // Create the EngEngrCertificate
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEngEngrCertificateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEngEngrCertificate() throws Exception {
        int databaseSizeBeforeUpdate = engEngrCertificateRepository.findAll().size();
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());

        // Create the EngEngrCertificate
        EngEngrCertificateDTO engEngrCertificateDTO = engEngrCertificateMapper.toDto(engEngrCertificate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEngEngrCertificateMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(engEngrCertificateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EngEngrCertificate in the database
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEngEngrCertificate() throws Exception {
        // Initialize the database
        engEngrCertificate.setEngEngrCertificateId(UUID.randomUUID().toString());
        engEngrCertificateRepository.saveAndFlush(engEngrCertificate);

        int databaseSizeBeforeDelete = engEngrCertificateRepository.findAll().size();

        // Delete the engEngrCertificate
        restEngEngrCertificateMockMvc
            .perform(delete(ENTITY_API_URL_ID, engEngrCertificate.getEngEngrCertificateId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EngEngrCertificate> engEngrCertificateList = engEngrCertificateRepository.findAll();
        assertThat(engEngrCertificateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
