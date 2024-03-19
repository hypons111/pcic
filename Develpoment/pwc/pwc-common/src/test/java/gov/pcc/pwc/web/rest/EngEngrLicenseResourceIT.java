package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.EngEngrLicense;
import gov.pcc.pwc.repository.EngEngrLicenseRepository;
import gov.pcc.pwc.service.dto.EngEngrLicenseDTO;
import gov.pcc.pwc.service.mapper.EngEngrLicenseMapper;
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
 * Integration tests for the {@link EngEngrLicenseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EngEngrLicenseResourceIT {

    private static final BigDecimal DEFAULT_ENG_ENGR_LICENSE_VERSION = new BigDecimal(1);
    private static final BigDecimal UPDATED_ENG_ENGR_LICENSE_VERSION = new BigDecimal(2);

    private static final String DEFAULT_ENG_ENGR_LICENSE_NO = "AAAAAAAAAA";
    private static final String UPDATED_ENG_ENGR_LICENSE_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RECEIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECEIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ENGR_LICENSE_DOC_NO = "AAAAAAAAAA";
    private static final String UPDATED_ENGR_LICENSE_DOC_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ENGR_ID = "AAAAAAAAAA";
    private static final String UPDATED_ENGR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTITY_TYPE = "AAA";
    private static final String UPDATED_IDENTITY_TYPE = "BBB";

    private static final String DEFAULT_IDNO = "AAAAAAAAAA";
    private static final String UPDATED_IDNO = "BBBBBBBBBB";

    private static final String DEFAULT_CH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CH_NAME_ROME = "AAAAAAAAAA";
    private static final String UPDATED_CH_NAME_ROME = "BBBBBBBBBB";

    private static final String DEFAULT_EN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EN_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SEX = "A";
    private static final String UPDATED_SEX = "B";

    private static final String DEFAULT_MOBILE_TEL = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_TEL = "BBBBBBBBBB";

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

    private static final String DEFAULT_EXEC_TYPE = "AAA";
    private static final String UPDATED_EXEC_TYPE = "BBB";

    private static final String DEFAULT_COMP_ID = "AAAAAAAAAA";
    private static final String UPDATED_COMP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_TYPE = "AAA";
    private static final String UPDATED_COMP_TYPE = "BBB";

    private static final String DEFAULT_COMP_IDNO = "AAAAAAAAAA";
    private static final String UPDATED_COMP_IDNO = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_CH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMP_CH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_EN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMP_EN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_TEL_1 = "AAAAAAAAAA";
    private static final String UPDATED_COMP_TEL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_TEL_2 = "AAAAAAAAAA";
    private static final String UPDATED_COMP_TEL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_FAX = "AAAAAAAAAA";
    private static final String UPDATED_COMP_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_COMP_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_ADDR_CITY = "AAAAA";
    private static final String UPDATED_COMP_ADDR_CITY = "BBBBB";

    private static final String DEFAULT_COMP_ADDR_TOWN = "AAA";
    private static final String UPDATED_COMP_ADDR_TOWN = "BBB";

    private static final String DEFAULT_COMP_ADDR_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_COMP_ADDR_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_ADDR_EN = "AAAAAAAAAA";
    private static final String UPDATED_COMP_ADDR_EN = "BBBBBBBBBB";

    private static final String DEFAULT_APPLY_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_APPLY_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_APPLY_TYPE = "AAA";
    private static final String UPDATED_APPLY_TYPE = "BBB";

    private static final String DEFAULT_RECEIVE_NO = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PUBLIC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PUBLIC_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EFFECT_DATE_START = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECT_DATE_START = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EFFECT_DATE_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECT_DATE_END = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVE_TYPE = "AAA";
    private static final String UPDATED_RECEIVE_TYPE = "BBB";

    private static final String DEFAULT_RECEIVE_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVE_ADDR_CITY = "AAAAA";
    private static final String UPDATED_RECEIVE_ADDR_CITY = "BBBBB";

    private static final String DEFAULT_RECEIVE_ADDR_TOWN = "AAA";
    private static final String UPDATED_RECEIVE_ADDR_TOWN = "BBB";

    private static final String DEFAULT_RECEIVE_ADDR_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_ADDR_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATE_ID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_ID_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT_LIST = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_IS_MORE_7_YEAR = "A";
    private static final String UPDATED_IS_MORE_7_YEAR = "B";

    private static final String DEFAULT_IS_XCA_CERT = "A";
    private static final String UPDATED_IS_XCA_CERT = "B";

    private static final String DEFAULT_CHANGE_ITEMS = "AAAAAAAAAA";
    private static final String UPDATED_CHANGE_ITEMS = "BBBBBBBBBB";

    private static final String DEFAULT_CHANGE_ITEMS_DESC = "AAAAAAAAAA";
    private static final String UPDATED_CHANGE_ITEMS_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTOR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAA";
    private static final String UPDATED_STATUS = "BBB";

    private static final String DEFAULT_REVIEW_STAFF = "AAAAAAAAAA";
    private static final String UPDATED_REVIEW_STAFF = "BBBBBBBBBB";

    private static final Instant DEFAULT_REVIEW_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REVIEW_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REVIEW_DESC = "AAAAAAAAAA";
    private static final String UPDATED_REVIEW_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TRANSFER_FROM = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_FROM = "BBBBBBBBBB";

    private static final Instant DEFAULT_TRANSFER_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSFER_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/eng-engr-licenses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{engEngrLicenseId}";

    @Autowired
    private EngEngrLicenseRepository engEngrLicenseRepository;

    @Autowired
    private EngEngrLicenseMapper engEngrLicenseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEngEngrLicenseMockMvc;

    private EngEngrLicense engEngrLicense;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EngEngrLicense createEntity(EntityManager em) {
        EngEngrLicense engEngrLicense = new EngEngrLicense()
            .engEngrLicenseVersion(DEFAULT_ENG_ENGR_LICENSE_VERSION)
            .engEngrLicenseNo(DEFAULT_ENG_ENGR_LICENSE_NO)
            .receiveDate(DEFAULT_RECEIVE_DATE)
            .engrLicenseDocNo(DEFAULT_ENGR_LICENSE_DOC_NO)
            .engrId(DEFAULT_ENGR_ID)
            .identityType(DEFAULT_IDENTITY_TYPE)
            .idno(DEFAULT_IDNO)
            .chName(DEFAULT_CH_NAME)
            .chNameRome(DEFAULT_CH_NAME_ROME)
            .enName(DEFAULT_EN_NAME)
            .birthDate(DEFAULT_BIRTH_DATE)
            .sex(DEFAULT_SEX)
            .mobileTel(DEFAULT_MOBILE_TEL)
            .email(DEFAULT_EMAIL)
            .permanentAddrZip(DEFAULT_PERMANENT_ADDR_ZIP)
            .permanentAddrCity(DEFAULT_PERMANENT_ADDR_CITY)
            .permanentAddrTown(DEFAULT_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(DEFAULT_PERMANENT_ADDR_OTHER)
            .contactAddrZip(DEFAULT_CONTACT_ADDR_ZIP)
            .contactAddrCity(DEFAULT_CONTACT_ADDR_CITY)
            .contactAddrTown(DEFAULT_CONTACT_ADDR_TOWN)
            .contactAddrOther(DEFAULT_CONTACT_ADDR_OTHER)
            .execType(DEFAULT_EXEC_TYPE)
            .compId(DEFAULT_COMP_ID)
            .compType(DEFAULT_COMP_TYPE)
            .compIdno(DEFAULT_COMP_IDNO)
            .compChName(DEFAULT_COMP_CH_NAME)
            .compEnName(DEFAULT_COMP_EN_NAME)
            .compTel1(DEFAULT_COMP_TEL_1)
            .compTel2(DEFAULT_COMP_TEL_2)
            .compFax(DEFAULT_COMP_FAX)
            .compAddrZip(DEFAULT_COMP_ADDR_ZIP)
            .compAddrCity(DEFAULT_COMP_ADDR_CITY)
            .compAddrTown(DEFAULT_COMP_ADDR_TOWN)
            .compAddrOther(DEFAULT_COMP_ADDR_OTHER)
            .compAddrEn(DEFAULT_COMP_ADDR_EN)
            .applyItem(DEFAULT_APPLY_ITEM)
            .applyType(DEFAULT_APPLY_TYPE)
            .receiveNo(DEFAULT_RECEIVE_NO)
            .publicDate(DEFAULT_PUBLIC_DATE)
            .effectDateStart(DEFAULT_EFFECT_DATE_START)
            .effectDateEnd(DEFAULT_EFFECT_DATE_END)
            .remark(DEFAULT_REMARK)
            .receiveType(DEFAULT_RECEIVE_TYPE)
            .receiveAddrZip(DEFAULT_RECEIVE_ADDR_ZIP)
            .receiveAddrCity(DEFAULT_RECEIVE_ADDR_CITY)
            .receiveAddrTown(DEFAULT_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(DEFAULT_RECEIVE_ADDR_OTHER)
            .certificateIdList(DEFAULT_CERTIFICATE_ID_LIST)
            .subjectList(DEFAULT_SUBJECT_LIST)
            .isMore7year(DEFAULT_IS_MORE_7_YEAR)
            .isXcaCert(DEFAULT_IS_XCA_CERT)
            .changeItems(DEFAULT_CHANGE_ITEMS)
            .changeItemsDesc(DEFAULT_CHANGE_ITEMS_DESC)
            .contractorId(DEFAULT_CONTRACTOR_ID)
            .status(DEFAULT_STATUS)
            .reviewStaff(DEFAULT_REVIEW_STAFF)
            .reviewTime(DEFAULT_REVIEW_TIME)
            .reviewDesc(DEFAULT_REVIEW_DESC)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .transferFrom(DEFAULT_TRANSFER_FROM)
            .transferTime(DEFAULT_TRANSFER_TIME);
        return engEngrLicense;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EngEngrLicense createUpdatedEntity(EntityManager em) {
        EngEngrLicense engEngrLicense = new EngEngrLicense()
            .engEngrLicenseVersion(UPDATED_ENG_ENGR_LICENSE_VERSION)
            .engEngrLicenseNo(UPDATED_ENG_ENGR_LICENSE_NO)
            .receiveDate(UPDATED_RECEIVE_DATE)
            .engrLicenseDocNo(UPDATED_ENGR_LICENSE_DOC_NO)
            .engrId(UPDATED_ENGR_ID)
            .identityType(UPDATED_IDENTITY_TYPE)
            .idno(UPDATED_IDNO)
            .chName(UPDATED_CH_NAME)
            .chNameRome(UPDATED_CH_NAME_ROME)
            .enName(UPDATED_EN_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .mobileTel(UPDATED_MOBILE_TEL)
            .email(UPDATED_EMAIL)
            .permanentAddrZip(UPDATED_PERMANENT_ADDR_ZIP)
            .permanentAddrCity(UPDATED_PERMANENT_ADDR_CITY)
            .permanentAddrTown(UPDATED_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(UPDATED_PERMANENT_ADDR_OTHER)
            .contactAddrZip(UPDATED_CONTACT_ADDR_ZIP)
            .contactAddrCity(UPDATED_CONTACT_ADDR_CITY)
            .contactAddrTown(UPDATED_CONTACT_ADDR_TOWN)
            .contactAddrOther(UPDATED_CONTACT_ADDR_OTHER)
            .execType(UPDATED_EXEC_TYPE)
            .compId(UPDATED_COMP_ID)
            .compType(UPDATED_COMP_TYPE)
            .compIdno(UPDATED_COMP_IDNO)
            .compChName(UPDATED_COMP_CH_NAME)
            .compEnName(UPDATED_COMP_EN_NAME)
            .compTel1(UPDATED_COMP_TEL_1)
            .compTel2(UPDATED_COMP_TEL_2)
            .compFax(UPDATED_COMP_FAX)
            .compAddrZip(UPDATED_COMP_ADDR_ZIP)
            .compAddrCity(UPDATED_COMP_ADDR_CITY)
            .compAddrTown(UPDATED_COMP_ADDR_TOWN)
            .compAddrOther(UPDATED_COMP_ADDR_OTHER)
            .compAddrEn(UPDATED_COMP_ADDR_EN)
            .applyItem(UPDATED_APPLY_ITEM)
            .applyType(UPDATED_APPLY_TYPE)
            .receiveNo(UPDATED_RECEIVE_NO)
            .publicDate(UPDATED_PUBLIC_DATE)
            .effectDateStart(UPDATED_EFFECT_DATE_START)
            .effectDateEnd(UPDATED_EFFECT_DATE_END)
            .remark(UPDATED_REMARK)
            .receiveType(UPDATED_RECEIVE_TYPE)
            .receiveAddrZip(UPDATED_RECEIVE_ADDR_ZIP)
            .receiveAddrCity(UPDATED_RECEIVE_ADDR_CITY)
            .receiveAddrTown(UPDATED_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(UPDATED_RECEIVE_ADDR_OTHER)
            .certificateIdList(UPDATED_CERTIFICATE_ID_LIST)
            .subjectList(UPDATED_SUBJECT_LIST)
            .isMore7year(UPDATED_IS_MORE_7_YEAR)
            .isXcaCert(UPDATED_IS_XCA_CERT)
            .changeItems(UPDATED_CHANGE_ITEMS)
            .changeItemsDesc(UPDATED_CHANGE_ITEMS_DESC)
            .contractorId(UPDATED_CONTRACTOR_ID)
            .status(UPDATED_STATUS)
            .reviewStaff(UPDATED_REVIEW_STAFF)
            .reviewTime(UPDATED_REVIEW_TIME)
            .reviewDesc(UPDATED_REVIEW_DESC)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);
        return engEngrLicense;
    }

    @BeforeEach
    public void initTest() {
        engEngrLicense = createEntity(em);
    }

    @Test
    @Transactional
    void createEngEngrLicense() throws Exception {
        int databaseSizeBeforeCreate = engEngrLicenseRepository.findAll().size();
        // Create the EngEngrLicense
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);
        restEngEngrLicenseMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeCreate + 1);
        EngEngrLicense testEngEngrLicense = engEngrLicenseList.get(engEngrLicenseList.size() - 1);
        assertThat(testEngEngrLicense.getEngEngrLicenseVersion()).isEqualByComparingTo(DEFAULT_ENG_ENGR_LICENSE_VERSION);
        assertThat(testEngEngrLicense.getEngEngrLicenseNo()).isEqualTo(DEFAULT_ENG_ENGR_LICENSE_NO);
        assertThat(testEngEngrLicense.getReceiveDate()).isEqualTo(DEFAULT_RECEIVE_DATE);
        assertThat(testEngEngrLicense.getEngrLicenseDocNo()).isEqualTo(DEFAULT_ENGR_LICENSE_DOC_NO);
        assertThat(testEngEngrLicense.getEngrId()).isEqualTo(DEFAULT_ENGR_ID);
        assertThat(testEngEngrLicense.getIdentityType()).isEqualTo(DEFAULT_IDENTITY_TYPE);
        assertThat(testEngEngrLicense.getIdno()).isEqualTo(DEFAULT_IDNO);
        assertThat(testEngEngrLicense.getChName()).isEqualTo(DEFAULT_CH_NAME);
        assertThat(testEngEngrLicense.getChNameRome()).isEqualTo(DEFAULT_CH_NAME_ROME);
        assertThat(testEngEngrLicense.getEnName()).isEqualTo(DEFAULT_EN_NAME);
        assertThat(testEngEngrLicense.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testEngEngrLicense.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testEngEngrLicense.getMobileTel()).isEqualTo(DEFAULT_MOBILE_TEL);
        assertThat(testEngEngrLicense.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEngEngrLicense.getPermanentAddrZip()).isEqualTo(DEFAULT_PERMANENT_ADDR_ZIP);
        assertThat(testEngEngrLicense.getPermanentAddrCity()).isEqualTo(DEFAULT_PERMANENT_ADDR_CITY);
        assertThat(testEngEngrLicense.getPermanentAddrTown()).isEqualTo(DEFAULT_PERMANENT_ADDR_TOWN);
        assertThat(testEngEngrLicense.getPermanentAddrOther()).isEqualTo(DEFAULT_PERMANENT_ADDR_OTHER);
        assertThat(testEngEngrLicense.getContactAddrZip()).isEqualTo(DEFAULT_CONTACT_ADDR_ZIP);
        assertThat(testEngEngrLicense.getContactAddrCity()).isEqualTo(DEFAULT_CONTACT_ADDR_CITY);
        assertThat(testEngEngrLicense.getContactAddrTown()).isEqualTo(DEFAULT_CONTACT_ADDR_TOWN);
        assertThat(testEngEngrLicense.getContactAddrOther()).isEqualTo(DEFAULT_CONTACT_ADDR_OTHER);
        assertThat(testEngEngrLicense.getExecType()).isEqualTo(DEFAULT_EXEC_TYPE);
        assertThat(testEngEngrLicense.getCompId()).isEqualTo(DEFAULT_COMP_ID);
        assertThat(testEngEngrLicense.getCompType()).isEqualTo(DEFAULT_COMP_TYPE);
        assertThat(testEngEngrLicense.getCompIdno()).isEqualTo(DEFAULT_COMP_IDNO);
        assertThat(testEngEngrLicense.getCompChName()).isEqualTo(DEFAULT_COMP_CH_NAME);
        assertThat(testEngEngrLicense.getCompEnName()).isEqualTo(DEFAULT_COMP_EN_NAME);
        assertThat(testEngEngrLicense.getCompTel1()).isEqualTo(DEFAULT_COMP_TEL_1);
        assertThat(testEngEngrLicense.getCompTel2()).isEqualTo(DEFAULT_COMP_TEL_2);
        assertThat(testEngEngrLicense.getCompFax()).isEqualTo(DEFAULT_COMP_FAX);
        assertThat(testEngEngrLicense.getCompAddrZip()).isEqualTo(DEFAULT_COMP_ADDR_ZIP);
        assertThat(testEngEngrLicense.getCompAddrCity()).isEqualTo(DEFAULT_COMP_ADDR_CITY);
        assertThat(testEngEngrLicense.getCompAddrTown()).isEqualTo(DEFAULT_COMP_ADDR_TOWN);
        assertThat(testEngEngrLicense.getCompAddrOther()).isEqualTo(DEFAULT_COMP_ADDR_OTHER);
        assertThat(testEngEngrLicense.getCompAddrEn()).isEqualTo(DEFAULT_COMP_ADDR_EN);
        assertThat(testEngEngrLicense.getApplyItem()).isEqualTo(DEFAULT_APPLY_ITEM);
        assertThat(testEngEngrLicense.getApplyType()).isEqualTo(DEFAULT_APPLY_TYPE);
        assertThat(testEngEngrLicense.getReceiveNo()).isEqualTo(DEFAULT_RECEIVE_NO);
        assertThat(testEngEngrLicense.getPublicDate()).isEqualTo(DEFAULT_PUBLIC_DATE);
        assertThat(testEngEngrLicense.getEffectDateStart()).isEqualTo(DEFAULT_EFFECT_DATE_START);
        assertThat(testEngEngrLicense.getEffectDateEnd()).isEqualTo(DEFAULT_EFFECT_DATE_END);
        assertThat(testEngEngrLicense.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testEngEngrLicense.getReceiveType()).isEqualTo(DEFAULT_RECEIVE_TYPE);
        assertThat(testEngEngrLicense.getReceiveAddrZip()).isEqualTo(DEFAULT_RECEIVE_ADDR_ZIP);
        assertThat(testEngEngrLicense.getReceiveAddrCity()).isEqualTo(DEFAULT_RECEIVE_ADDR_CITY);
        assertThat(testEngEngrLicense.getReceiveAddrTown()).isEqualTo(DEFAULT_RECEIVE_ADDR_TOWN);
        assertThat(testEngEngrLicense.getReceiveAddrOther()).isEqualTo(DEFAULT_RECEIVE_ADDR_OTHER);
        assertThat(testEngEngrLicense.getCertificateIdList()).isEqualTo(DEFAULT_CERTIFICATE_ID_LIST);
        assertThat(testEngEngrLicense.getSubjectList()).isEqualTo(DEFAULT_SUBJECT_LIST);
        assertThat(testEngEngrLicense.getIsMore7year()).isEqualTo(DEFAULT_IS_MORE_7_YEAR);
        assertThat(testEngEngrLicense.getIsXcaCert()).isEqualTo(DEFAULT_IS_XCA_CERT);
        assertThat(testEngEngrLicense.getChangeItems()).isEqualTo(DEFAULT_CHANGE_ITEMS);
        assertThat(testEngEngrLicense.getChangeItemsDesc()).isEqualTo(DEFAULT_CHANGE_ITEMS_DESC);
        assertThat(testEngEngrLicense.getContractorId()).isEqualTo(DEFAULT_CONTRACTOR_ID);
        assertThat(testEngEngrLicense.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEngEngrLicense.getReviewStaff()).isEqualTo(DEFAULT_REVIEW_STAFF);
        assertThat(testEngEngrLicense.getReviewTime()).isEqualTo(DEFAULT_REVIEW_TIME);
        assertThat(testEngEngrLicense.getReviewDesc()).isEqualTo(DEFAULT_REVIEW_DESC);
        assertThat(testEngEngrLicense.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testEngEngrLicense.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEngEngrLicense.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testEngEngrLicense.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testEngEngrLicense.getTransferFrom()).isEqualTo(DEFAULT_TRANSFER_FROM);
        assertThat(testEngEngrLicense.getTransferTime()).isEqualTo(DEFAULT_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void createEngEngrLicenseWithExistingId() throws Exception {
        // Create the EngEngrLicense with an existing ID
        engEngrLicense.setEngEngrLicenseId("existing_id");
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);

        int databaseSizeBeforeCreate = engEngrLicenseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEngEngrLicenseMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEngEngrLicenseVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = engEngrLicenseRepository.findAll().size();
        // set the field null
        engEngrLicense.setEngEngrLicenseVersion(null);

        // Create the EngEngrLicense, which fails.
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);

        restEngEngrLicenseMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isBadRequest());

        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllEngEngrLicenses() throws Exception {
        // Initialize the database
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());
        engEngrLicenseRepository.saveAndFlush(engEngrLicense);

        // Get all the engEngrLicenseList
        restEngEngrLicenseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=engEngrLicenseId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].engEngrLicenseId").value(hasItem(engEngrLicense.getEngEngrLicenseId())))
            .andExpect(jsonPath("$.[*].engEngrLicenseVersion").value(hasItem(sameNumber(DEFAULT_ENG_ENGR_LICENSE_VERSION))))
            .andExpect(jsonPath("$.[*].engEngrLicenseNo").value(hasItem(DEFAULT_ENG_ENGR_LICENSE_NO)))
            .andExpect(jsonPath("$.[*].receiveDate").value(hasItem(DEFAULT_RECEIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].engrLicenseDocNo").value(hasItem(DEFAULT_ENGR_LICENSE_DOC_NO)))
            .andExpect(jsonPath("$.[*].engrId").value(hasItem(DEFAULT_ENGR_ID)))
            .andExpect(jsonPath("$.[*].identityType").value(hasItem(DEFAULT_IDENTITY_TYPE)))
            .andExpect(jsonPath("$.[*].idno").value(hasItem(DEFAULT_IDNO)))
            .andExpect(jsonPath("$.[*].chName").value(hasItem(DEFAULT_CH_NAME)))
            .andExpect(jsonPath("$.[*].chNameRome").value(hasItem(DEFAULT_CH_NAME_ROME)))
            .andExpect(jsonPath("$.[*].enName").value(hasItem(DEFAULT_EN_NAME)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].mobileTel").value(hasItem(DEFAULT_MOBILE_TEL)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].permanentAddrZip").value(hasItem(DEFAULT_PERMANENT_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].permanentAddrCity").value(hasItem(DEFAULT_PERMANENT_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].permanentAddrTown").value(hasItem(DEFAULT_PERMANENT_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].permanentAddrOther").value(hasItem(DEFAULT_PERMANENT_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].contactAddrZip").value(hasItem(DEFAULT_CONTACT_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].contactAddrCity").value(hasItem(DEFAULT_CONTACT_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].contactAddrTown").value(hasItem(DEFAULT_CONTACT_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].contactAddrOther").value(hasItem(DEFAULT_CONTACT_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].execType").value(hasItem(DEFAULT_EXEC_TYPE)))
            .andExpect(jsonPath("$.[*].compId").value(hasItem(DEFAULT_COMP_ID)))
            .andExpect(jsonPath("$.[*].compType").value(hasItem(DEFAULT_COMP_TYPE)))
            .andExpect(jsonPath("$.[*].compIdno").value(hasItem(DEFAULT_COMP_IDNO)))
            .andExpect(jsonPath("$.[*].compChName").value(hasItem(DEFAULT_COMP_CH_NAME)))
            .andExpect(jsonPath("$.[*].compEnName").value(hasItem(DEFAULT_COMP_EN_NAME)))
            .andExpect(jsonPath("$.[*].compTel1").value(hasItem(DEFAULT_COMP_TEL_1)))
            .andExpect(jsonPath("$.[*].compTel2").value(hasItem(DEFAULT_COMP_TEL_2)))
            .andExpect(jsonPath("$.[*].compFax").value(hasItem(DEFAULT_COMP_FAX)))
            .andExpect(jsonPath("$.[*].compAddrZip").value(hasItem(DEFAULT_COMP_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].compAddrCity").value(hasItem(DEFAULT_COMP_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].compAddrTown").value(hasItem(DEFAULT_COMP_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].compAddrOther").value(hasItem(DEFAULT_COMP_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].compAddrEn").value(hasItem(DEFAULT_COMP_ADDR_EN)))
            .andExpect(jsonPath("$.[*].applyItem").value(hasItem(DEFAULT_APPLY_ITEM)))
            .andExpect(jsonPath("$.[*].applyType").value(hasItem(DEFAULT_APPLY_TYPE)))
            .andExpect(jsonPath("$.[*].receiveNo").value(hasItem(DEFAULT_RECEIVE_NO)))
            .andExpect(jsonPath("$.[*].publicDate").value(hasItem(DEFAULT_PUBLIC_DATE.toString())))
            .andExpect(jsonPath("$.[*].effectDateStart").value(hasItem(DEFAULT_EFFECT_DATE_START.toString())))
            .andExpect(jsonPath("$.[*].effectDateEnd").value(hasItem(DEFAULT_EFFECT_DATE_END.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].receiveType").value(hasItem(DEFAULT_RECEIVE_TYPE)))
            .andExpect(jsonPath("$.[*].receiveAddrZip").value(hasItem(DEFAULT_RECEIVE_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].receiveAddrCity").value(hasItem(DEFAULT_RECEIVE_ADDR_CITY)))
            .andExpect(jsonPath("$.[*].receiveAddrTown").value(hasItem(DEFAULT_RECEIVE_ADDR_TOWN)))
            .andExpect(jsonPath("$.[*].receiveAddrOther").value(hasItem(DEFAULT_RECEIVE_ADDR_OTHER)))
            .andExpect(jsonPath("$.[*].certificateIdList").value(hasItem(DEFAULT_CERTIFICATE_ID_LIST)))
            .andExpect(jsonPath("$.[*].subjectList").value(hasItem(DEFAULT_SUBJECT_LIST)))
            .andExpect(jsonPath("$.[*].isMore7year").value(hasItem(DEFAULT_IS_MORE_7_YEAR)))
            .andExpect(jsonPath("$.[*].isXcaCert").value(hasItem(DEFAULT_IS_XCA_CERT)))
            .andExpect(jsonPath("$.[*].changeItems").value(hasItem(DEFAULT_CHANGE_ITEMS)))
            .andExpect(jsonPath("$.[*].changeItemsDesc").value(hasItem(DEFAULT_CHANGE_ITEMS_DESC)))
            .andExpect(jsonPath("$.[*].contractorId").value(hasItem(DEFAULT_CONTRACTOR_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].reviewStaff").value(hasItem(DEFAULT_REVIEW_STAFF)))
            .andExpect(jsonPath("$.[*].reviewTime").value(hasItem(DEFAULT_REVIEW_TIME.toString())))
            .andExpect(jsonPath("$.[*].reviewDesc").value(hasItem(DEFAULT_REVIEW_DESC)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].transferFrom").value(hasItem(DEFAULT_TRANSFER_FROM)))
            .andExpect(jsonPath("$.[*].transferTime").value(hasItem(DEFAULT_TRANSFER_TIME.toString())));
    }

    @Test
    @Transactional
    void getEngEngrLicense() throws Exception {
        // Initialize the database
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());
        engEngrLicenseRepository.saveAndFlush(engEngrLicense);

        // Get the engEngrLicense
        restEngEngrLicenseMockMvc
            .perform(get(ENTITY_API_URL_ID, engEngrLicense.getEngEngrLicenseId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.engEngrLicenseId").value(engEngrLicense.getEngEngrLicenseId()))
            .andExpect(jsonPath("$.engEngrLicenseVersion").value(sameNumber(DEFAULT_ENG_ENGR_LICENSE_VERSION)))
            .andExpect(jsonPath("$.engEngrLicenseNo").value(DEFAULT_ENG_ENGR_LICENSE_NO))
            .andExpect(jsonPath("$.receiveDate").value(DEFAULT_RECEIVE_DATE.toString()))
            .andExpect(jsonPath("$.engrLicenseDocNo").value(DEFAULT_ENGR_LICENSE_DOC_NO))
            .andExpect(jsonPath("$.engrId").value(DEFAULT_ENGR_ID))
            .andExpect(jsonPath("$.identityType").value(DEFAULT_IDENTITY_TYPE))
            .andExpect(jsonPath("$.idno").value(DEFAULT_IDNO))
            .andExpect(jsonPath("$.chName").value(DEFAULT_CH_NAME))
            .andExpect(jsonPath("$.chNameRome").value(DEFAULT_CH_NAME_ROME))
            .andExpect(jsonPath("$.enName").value(DEFAULT_EN_NAME))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX))
            .andExpect(jsonPath("$.mobileTel").value(DEFAULT_MOBILE_TEL))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.permanentAddrZip").value(DEFAULT_PERMANENT_ADDR_ZIP))
            .andExpect(jsonPath("$.permanentAddrCity").value(DEFAULT_PERMANENT_ADDR_CITY))
            .andExpect(jsonPath("$.permanentAddrTown").value(DEFAULT_PERMANENT_ADDR_TOWN))
            .andExpect(jsonPath("$.permanentAddrOther").value(DEFAULT_PERMANENT_ADDR_OTHER))
            .andExpect(jsonPath("$.contactAddrZip").value(DEFAULT_CONTACT_ADDR_ZIP))
            .andExpect(jsonPath("$.contactAddrCity").value(DEFAULT_CONTACT_ADDR_CITY))
            .andExpect(jsonPath("$.contactAddrTown").value(DEFAULT_CONTACT_ADDR_TOWN))
            .andExpect(jsonPath("$.contactAddrOther").value(DEFAULT_CONTACT_ADDR_OTHER))
            .andExpect(jsonPath("$.execType").value(DEFAULT_EXEC_TYPE))
            .andExpect(jsonPath("$.compId").value(DEFAULT_COMP_ID))
            .andExpect(jsonPath("$.compType").value(DEFAULT_COMP_TYPE))
            .andExpect(jsonPath("$.compIdno").value(DEFAULT_COMP_IDNO))
            .andExpect(jsonPath("$.compChName").value(DEFAULT_COMP_CH_NAME))
            .andExpect(jsonPath("$.compEnName").value(DEFAULT_COMP_EN_NAME))
            .andExpect(jsonPath("$.compTel1").value(DEFAULT_COMP_TEL_1))
            .andExpect(jsonPath("$.compTel2").value(DEFAULT_COMP_TEL_2))
            .andExpect(jsonPath("$.compFax").value(DEFAULT_COMP_FAX))
            .andExpect(jsonPath("$.compAddrZip").value(DEFAULT_COMP_ADDR_ZIP))
            .andExpect(jsonPath("$.compAddrCity").value(DEFAULT_COMP_ADDR_CITY))
            .andExpect(jsonPath("$.compAddrTown").value(DEFAULT_COMP_ADDR_TOWN))
            .andExpect(jsonPath("$.compAddrOther").value(DEFAULT_COMP_ADDR_OTHER))
            .andExpect(jsonPath("$.compAddrEn").value(DEFAULT_COMP_ADDR_EN))
            .andExpect(jsonPath("$.applyItem").value(DEFAULT_APPLY_ITEM))
            .andExpect(jsonPath("$.applyType").value(DEFAULT_APPLY_TYPE))
            .andExpect(jsonPath("$.receiveNo").value(DEFAULT_RECEIVE_NO))
            .andExpect(jsonPath("$.publicDate").value(DEFAULT_PUBLIC_DATE.toString()))
            .andExpect(jsonPath("$.effectDateStart").value(DEFAULT_EFFECT_DATE_START.toString()))
            .andExpect(jsonPath("$.effectDateEnd").value(DEFAULT_EFFECT_DATE_END.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.receiveType").value(DEFAULT_RECEIVE_TYPE))
            .andExpect(jsonPath("$.receiveAddrZip").value(DEFAULT_RECEIVE_ADDR_ZIP))
            .andExpect(jsonPath("$.receiveAddrCity").value(DEFAULT_RECEIVE_ADDR_CITY))
            .andExpect(jsonPath("$.receiveAddrTown").value(DEFAULT_RECEIVE_ADDR_TOWN))
            .andExpect(jsonPath("$.receiveAddrOther").value(DEFAULT_RECEIVE_ADDR_OTHER))
            .andExpect(jsonPath("$.certificateIdList").value(DEFAULT_CERTIFICATE_ID_LIST))
            .andExpect(jsonPath("$.subjectList").value(DEFAULT_SUBJECT_LIST))
            .andExpect(jsonPath("$.isMore7year").value(DEFAULT_IS_MORE_7_YEAR))
            .andExpect(jsonPath("$.isXcaCert").value(DEFAULT_IS_XCA_CERT))
            .andExpect(jsonPath("$.changeItems").value(DEFAULT_CHANGE_ITEMS))
            .andExpect(jsonPath("$.changeItemsDesc").value(DEFAULT_CHANGE_ITEMS_DESC))
            .andExpect(jsonPath("$.contractorId").value(DEFAULT_CONTRACTOR_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.reviewStaff").value(DEFAULT_REVIEW_STAFF))
            .andExpect(jsonPath("$.reviewTime").value(DEFAULT_REVIEW_TIME.toString()))
            .andExpect(jsonPath("$.reviewDesc").value(DEFAULT_REVIEW_DESC))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.transferFrom").value(DEFAULT_TRANSFER_FROM))
            .andExpect(jsonPath("$.transferTime").value(DEFAULT_TRANSFER_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEngEngrLicense() throws Exception {
        // Get the engEngrLicense
        restEngEngrLicenseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEngEngrLicense() throws Exception {
        // Initialize the database
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());
        engEngrLicenseRepository.saveAndFlush(engEngrLicense);

        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();

        // Update the engEngrLicense
        EngEngrLicense updatedEngEngrLicense = engEngrLicenseRepository.findById(engEngrLicense.getEngEngrLicenseId()).get();
        // Disconnect from session so that the updates on updatedEngEngrLicense are not directly saved in db
        em.detach(updatedEngEngrLicense);
        updatedEngEngrLicense
            .engEngrLicenseVersion(UPDATED_ENG_ENGR_LICENSE_VERSION)
            .engEngrLicenseNo(UPDATED_ENG_ENGR_LICENSE_NO)
            .receiveDate(UPDATED_RECEIVE_DATE)
            .engrLicenseDocNo(UPDATED_ENGR_LICENSE_DOC_NO)
            .engrId(UPDATED_ENGR_ID)
            .identityType(UPDATED_IDENTITY_TYPE)
            .idno(UPDATED_IDNO)
            .chName(UPDATED_CH_NAME)
            .chNameRome(UPDATED_CH_NAME_ROME)
            .enName(UPDATED_EN_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .mobileTel(UPDATED_MOBILE_TEL)
            .email(UPDATED_EMAIL)
            .permanentAddrZip(UPDATED_PERMANENT_ADDR_ZIP)
            .permanentAddrCity(UPDATED_PERMANENT_ADDR_CITY)
            .permanentAddrTown(UPDATED_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(UPDATED_PERMANENT_ADDR_OTHER)
            .contactAddrZip(UPDATED_CONTACT_ADDR_ZIP)
            .contactAddrCity(UPDATED_CONTACT_ADDR_CITY)
            .contactAddrTown(UPDATED_CONTACT_ADDR_TOWN)
            .contactAddrOther(UPDATED_CONTACT_ADDR_OTHER)
            .execType(UPDATED_EXEC_TYPE)
            .compId(UPDATED_COMP_ID)
            .compType(UPDATED_COMP_TYPE)
            .compIdno(UPDATED_COMP_IDNO)
            .compChName(UPDATED_COMP_CH_NAME)
            .compEnName(UPDATED_COMP_EN_NAME)
            .compTel1(UPDATED_COMP_TEL_1)
            .compTel2(UPDATED_COMP_TEL_2)
            .compFax(UPDATED_COMP_FAX)
            .compAddrZip(UPDATED_COMP_ADDR_ZIP)
            .compAddrCity(UPDATED_COMP_ADDR_CITY)
            .compAddrTown(UPDATED_COMP_ADDR_TOWN)
            .compAddrOther(UPDATED_COMP_ADDR_OTHER)
            .compAddrEn(UPDATED_COMP_ADDR_EN)
            .applyItem(UPDATED_APPLY_ITEM)
            .applyType(UPDATED_APPLY_TYPE)
            .receiveNo(UPDATED_RECEIVE_NO)
            .publicDate(UPDATED_PUBLIC_DATE)
            .effectDateStart(UPDATED_EFFECT_DATE_START)
            .effectDateEnd(UPDATED_EFFECT_DATE_END)
            .remark(UPDATED_REMARK)
            .receiveType(UPDATED_RECEIVE_TYPE)
            .receiveAddrZip(UPDATED_RECEIVE_ADDR_ZIP)
            .receiveAddrCity(UPDATED_RECEIVE_ADDR_CITY)
            .receiveAddrTown(UPDATED_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(UPDATED_RECEIVE_ADDR_OTHER)
            .certificateIdList(UPDATED_CERTIFICATE_ID_LIST)
            .subjectList(UPDATED_SUBJECT_LIST)
            .isMore7year(UPDATED_IS_MORE_7_YEAR)
            .isXcaCert(UPDATED_IS_XCA_CERT)
            .changeItems(UPDATED_CHANGE_ITEMS)
            .changeItemsDesc(UPDATED_CHANGE_ITEMS_DESC)
            .contractorId(UPDATED_CONTRACTOR_ID)
            .status(UPDATED_STATUS)
            .reviewStaff(UPDATED_REVIEW_STAFF)
            .reviewTime(UPDATED_REVIEW_TIME)
            .reviewDesc(UPDATED_REVIEW_DESC)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(updatedEngEngrLicense);

        restEngEngrLicenseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, engEngrLicenseDTO.getEngEngrLicenseId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isOk());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
        EngEngrLicense testEngEngrLicense = engEngrLicenseList.get(engEngrLicenseList.size() - 1);
        assertThat(testEngEngrLicense.getEngEngrLicenseVersion()).isEqualByComparingTo(UPDATED_ENG_ENGR_LICENSE_VERSION);
        assertThat(testEngEngrLicense.getEngEngrLicenseNo()).isEqualTo(UPDATED_ENG_ENGR_LICENSE_NO);
        assertThat(testEngEngrLicense.getReceiveDate()).isEqualTo(UPDATED_RECEIVE_DATE);
        assertThat(testEngEngrLicense.getEngrLicenseDocNo()).isEqualTo(UPDATED_ENGR_LICENSE_DOC_NO);
        assertThat(testEngEngrLicense.getEngrId()).isEqualTo(UPDATED_ENGR_ID);
        assertThat(testEngEngrLicense.getIdentityType()).isEqualTo(UPDATED_IDENTITY_TYPE);
        assertThat(testEngEngrLicense.getIdno()).isEqualTo(UPDATED_IDNO);
        assertThat(testEngEngrLicense.getChName()).isEqualTo(UPDATED_CH_NAME);
        assertThat(testEngEngrLicense.getChNameRome()).isEqualTo(UPDATED_CH_NAME_ROME);
        assertThat(testEngEngrLicense.getEnName()).isEqualTo(UPDATED_EN_NAME);
        assertThat(testEngEngrLicense.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testEngEngrLicense.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testEngEngrLicense.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testEngEngrLicense.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEngEngrLicense.getPermanentAddrZip()).isEqualTo(UPDATED_PERMANENT_ADDR_ZIP);
        assertThat(testEngEngrLicense.getPermanentAddrCity()).isEqualTo(UPDATED_PERMANENT_ADDR_CITY);
        assertThat(testEngEngrLicense.getPermanentAddrTown()).isEqualTo(UPDATED_PERMANENT_ADDR_TOWN);
        assertThat(testEngEngrLicense.getPermanentAddrOther()).isEqualTo(UPDATED_PERMANENT_ADDR_OTHER);
        assertThat(testEngEngrLicense.getContactAddrZip()).isEqualTo(UPDATED_CONTACT_ADDR_ZIP);
        assertThat(testEngEngrLicense.getContactAddrCity()).isEqualTo(UPDATED_CONTACT_ADDR_CITY);
        assertThat(testEngEngrLicense.getContactAddrTown()).isEqualTo(UPDATED_CONTACT_ADDR_TOWN);
        assertThat(testEngEngrLicense.getContactAddrOther()).isEqualTo(UPDATED_CONTACT_ADDR_OTHER);
        assertThat(testEngEngrLicense.getExecType()).isEqualTo(UPDATED_EXEC_TYPE);
        assertThat(testEngEngrLicense.getCompId()).isEqualTo(UPDATED_COMP_ID);
        assertThat(testEngEngrLicense.getCompType()).isEqualTo(UPDATED_COMP_TYPE);
        assertThat(testEngEngrLicense.getCompIdno()).isEqualTo(UPDATED_COMP_IDNO);
        assertThat(testEngEngrLicense.getCompChName()).isEqualTo(UPDATED_COMP_CH_NAME);
        assertThat(testEngEngrLicense.getCompEnName()).isEqualTo(UPDATED_COMP_EN_NAME);
        assertThat(testEngEngrLicense.getCompTel1()).isEqualTo(UPDATED_COMP_TEL_1);
        assertThat(testEngEngrLicense.getCompTel2()).isEqualTo(UPDATED_COMP_TEL_2);
        assertThat(testEngEngrLicense.getCompFax()).isEqualTo(UPDATED_COMP_FAX);
        assertThat(testEngEngrLicense.getCompAddrZip()).isEqualTo(UPDATED_COMP_ADDR_ZIP);
        assertThat(testEngEngrLicense.getCompAddrCity()).isEqualTo(UPDATED_COMP_ADDR_CITY);
        assertThat(testEngEngrLicense.getCompAddrTown()).isEqualTo(UPDATED_COMP_ADDR_TOWN);
        assertThat(testEngEngrLicense.getCompAddrOther()).isEqualTo(UPDATED_COMP_ADDR_OTHER);
        assertThat(testEngEngrLicense.getCompAddrEn()).isEqualTo(UPDATED_COMP_ADDR_EN);
        assertThat(testEngEngrLicense.getApplyItem()).isEqualTo(UPDATED_APPLY_ITEM);
        assertThat(testEngEngrLicense.getApplyType()).isEqualTo(UPDATED_APPLY_TYPE);
        assertThat(testEngEngrLicense.getReceiveNo()).isEqualTo(UPDATED_RECEIVE_NO);
        assertThat(testEngEngrLicense.getPublicDate()).isEqualTo(UPDATED_PUBLIC_DATE);
        assertThat(testEngEngrLicense.getEffectDateStart()).isEqualTo(UPDATED_EFFECT_DATE_START);
        assertThat(testEngEngrLicense.getEffectDateEnd()).isEqualTo(UPDATED_EFFECT_DATE_END);
        assertThat(testEngEngrLicense.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testEngEngrLicense.getReceiveType()).isEqualTo(UPDATED_RECEIVE_TYPE);
        assertThat(testEngEngrLicense.getReceiveAddrZip()).isEqualTo(UPDATED_RECEIVE_ADDR_ZIP);
        assertThat(testEngEngrLicense.getReceiveAddrCity()).isEqualTo(UPDATED_RECEIVE_ADDR_CITY);
        assertThat(testEngEngrLicense.getReceiveAddrTown()).isEqualTo(UPDATED_RECEIVE_ADDR_TOWN);
        assertThat(testEngEngrLicense.getReceiveAddrOther()).isEqualTo(UPDATED_RECEIVE_ADDR_OTHER);
        assertThat(testEngEngrLicense.getCertificateIdList()).isEqualTo(UPDATED_CERTIFICATE_ID_LIST);
        assertThat(testEngEngrLicense.getSubjectList()).isEqualTo(UPDATED_SUBJECT_LIST);
        assertThat(testEngEngrLicense.getIsMore7year()).isEqualTo(UPDATED_IS_MORE_7_YEAR);
        assertThat(testEngEngrLicense.getIsXcaCert()).isEqualTo(UPDATED_IS_XCA_CERT);
        assertThat(testEngEngrLicense.getChangeItems()).isEqualTo(UPDATED_CHANGE_ITEMS);
        assertThat(testEngEngrLicense.getChangeItemsDesc()).isEqualTo(UPDATED_CHANGE_ITEMS_DESC);
        assertThat(testEngEngrLicense.getContractorId()).isEqualTo(UPDATED_CONTRACTOR_ID);
        assertThat(testEngEngrLicense.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEngEngrLicense.getReviewStaff()).isEqualTo(UPDATED_REVIEW_STAFF);
        assertThat(testEngEngrLicense.getReviewTime()).isEqualTo(UPDATED_REVIEW_TIME);
        assertThat(testEngEngrLicense.getReviewDesc()).isEqualTo(UPDATED_REVIEW_DESC);
        assertThat(testEngEngrLicense.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testEngEngrLicense.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEngEngrLicense.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testEngEngrLicense.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testEngEngrLicense.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testEngEngrLicense.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void putNonExistingEngEngrLicense() throws Exception {
        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());

        // Create the EngEngrLicense
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEngEngrLicenseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, engEngrLicenseDTO.getEngEngrLicenseId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEngEngrLicense() throws Exception {
        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());

        // Create the EngEngrLicense
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEngEngrLicenseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEngEngrLicense() throws Exception {
        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());

        // Create the EngEngrLicense
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEngEngrLicenseMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEngEngrLicenseWithPatch() throws Exception {
        // Initialize the database
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());
        engEngrLicenseRepository.saveAndFlush(engEngrLicense);

        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();

        // Update the engEngrLicense using partial update
        EngEngrLicense partialUpdatedEngEngrLicense = new EngEngrLicense();
        partialUpdatedEngEngrLicense.setEngEngrLicenseId(engEngrLicense.getEngEngrLicenseId());

        partialUpdatedEngEngrLicense
            .engEngrLicenseNo(UPDATED_ENG_ENGR_LICENSE_NO)
            .engrLicenseDocNo(UPDATED_ENGR_LICENSE_DOC_NO)
            .idno(UPDATED_IDNO)
            .chName(UPDATED_CH_NAME)
            .enName(UPDATED_EN_NAME)
            .sex(UPDATED_SEX)
            .mobileTel(UPDATED_MOBILE_TEL)
            .permanentAddrZip(UPDATED_PERMANENT_ADDR_ZIP)
            .permanentAddrTown(UPDATED_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(UPDATED_PERMANENT_ADDR_OTHER)
            .contactAddrZip(UPDATED_CONTACT_ADDR_ZIP)
            .compId(UPDATED_COMP_ID)
            .compChName(UPDATED_COMP_CH_NAME)
            .compTel1(UPDATED_COMP_TEL_1)
            .compTel2(UPDATED_COMP_TEL_2)
            .compAddrCity(UPDATED_COMP_ADDR_CITY)
            .compAddrTown(UPDATED_COMP_ADDR_TOWN)
            .compAddrEn(UPDATED_COMP_ADDR_EN)
            .applyItem(UPDATED_APPLY_ITEM)
            .publicDate(UPDATED_PUBLIC_DATE)
            .effectDateStart(UPDATED_EFFECT_DATE_START)
            .effectDateEnd(UPDATED_EFFECT_DATE_END)
            .receiveAddrTown(UPDATED_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(UPDATED_RECEIVE_ADDR_OTHER)
            .subjectList(UPDATED_SUBJECT_LIST)
            .changeItemsDesc(UPDATED_CHANGE_ITEMS_DESC)
            .contractorId(UPDATED_CONTRACTOR_ID)
            .reviewStaff(UPDATED_REVIEW_STAFF)
            .createTime(UPDATED_CREATE_TIME);

        restEngEngrLicenseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEngEngrLicense.getEngEngrLicenseId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEngEngrLicense))
            )
            .andExpect(status().isOk());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
        EngEngrLicense testEngEngrLicense = engEngrLicenseList.get(engEngrLicenseList.size() - 1);
        assertThat(testEngEngrLicense.getEngEngrLicenseVersion()).isEqualByComparingTo(DEFAULT_ENG_ENGR_LICENSE_VERSION);
        assertThat(testEngEngrLicense.getEngEngrLicenseNo()).isEqualTo(UPDATED_ENG_ENGR_LICENSE_NO);
        assertThat(testEngEngrLicense.getReceiveDate()).isEqualTo(DEFAULT_RECEIVE_DATE);
        assertThat(testEngEngrLicense.getEngrLicenseDocNo()).isEqualTo(UPDATED_ENGR_LICENSE_DOC_NO);
        assertThat(testEngEngrLicense.getEngrId()).isEqualTo(DEFAULT_ENGR_ID);
        assertThat(testEngEngrLicense.getIdentityType()).isEqualTo(DEFAULT_IDENTITY_TYPE);
        assertThat(testEngEngrLicense.getIdno()).isEqualTo(UPDATED_IDNO);
        assertThat(testEngEngrLicense.getChName()).isEqualTo(UPDATED_CH_NAME);
        assertThat(testEngEngrLicense.getChNameRome()).isEqualTo(DEFAULT_CH_NAME_ROME);
        assertThat(testEngEngrLicense.getEnName()).isEqualTo(UPDATED_EN_NAME);
        assertThat(testEngEngrLicense.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testEngEngrLicense.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testEngEngrLicense.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testEngEngrLicense.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEngEngrLicense.getPermanentAddrZip()).isEqualTo(UPDATED_PERMANENT_ADDR_ZIP);
        assertThat(testEngEngrLicense.getPermanentAddrCity()).isEqualTo(DEFAULT_PERMANENT_ADDR_CITY);
        assertThat(testEngEngrLicense.getPermanentAddrTown()).isEqualTo(UPDATED_PERMANENT_ADDR_TOWN);
        assertThat(testEngEngrLicense.getPermanentAddrOther()).isEqualTo(UPDATED_PERMANENT_ADDR_OTHER);
        assertThat(testEngEngrLicense.getContactAddrZip()).isEqualTo(UPDATED_CONTACT_ADDR_ZIP);
        assertThat(testEngEngrLicense.getContactAddrCity()).isEqualTo(DEFAULT_CONTACT_ADDR_CITY);
        assertThat(testEngEngrLicense.getContactAddrTown()).isEqualTo(DEFAULT_CONTACT_ADDR_TOWN);
        assertThat(testEngEngrLicense.getContactAddrOther()).isEqualTo(DEFAULT_CONTACT_ADDR_OTHER);
        assertThat(testEngEngrLicense.getExecType()).isEqualTo(DEFAULT_EXEC_TYPE);
        assertThat(testEngEngrLicense.getCompId()).isEqualTo(UPDATED_COMP_ID);
        assertThat(testEngEngrLicense.getCompType()).isEqualTo(DEFAULT_COMP_TYPE);
        assertThat(testEngEngrLicense.getCompIdno()).isEqualTo(DEFAULT_COMP_IDNO);
        assertThat(testEngEngrLicense.getCompChName()).isEqualTo(UPDATED_COMP_CH_NAME);
        assertThat(testEngEngrLicense.getCompEnName()).isEqualTo(DEFAULT_COMP_EN_NAME);
        assertThat(testEngEngrLicense.getCompTel1()).isEqualTo(UPDATED_COMP_TEL_1);
        assertThat(testEngEngrLicense.getCompTel2()).isEqualTo(UPDATED_COMP_TEL_2);
        assertThat(testEngEngrLicense.getCompFax()).isEqualTo(DEFAULT_COMP_FAX);
        assertThat(testEngEngrLicense.getCompAddrZip()).isEqualTo(DEFAULT_COMP_ADDR_ZIP);
        assertThat(testEngEngrLicense.getCompAddrCity()).isEqualTo(UPDATED_COMP_ADDR_CITY);
        assertThat(testEngEngrLicense.getCompAddrTown()).isEqualTo(UPDATED_COMP_ADDR_TOWN);
        assertThat(testEngEngrLicense.getCompAddrOther()).isEqualTo(DEFAULT_COMP_ADDR_OTHER);
        assertThat(testEngEngrLicense.getCompAddrEn()).isEqualTo(UPDATED_COMP_ADDR_EN);
        assertThat(testEngEngrLicense.getApplyItem()).isEqualTo(UPDATED_APPLY_ITEM);
        assertThat(testEngEngrLicense.getApplyType()).isEqualTo(DEFAULT_APPLY_TYPE);
        assertThat(testEngEngrLicense.getReceiveNo()).isEqualTo(DEFAULT_RECEIVE_NO);
        assertThat(testEngEngrLicense.getPublicDate()).isEqualTo(UPDATED_PUBLIC_DATE);
        assertThat(testEngEngrLicense.getEffectDateStart()).isEqualTo(UPDATED_EFFECT_DATE_START);
        assertThat(testEngEngrLicense.getEffectDateEnd()).isEqualTo(UPDATED_EFFECT_DATE_END);
        assertThat(testEngEngrLicense.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testEngEngrLicense.getReceiveType()).isEqualTo(DEFAULT_RECEIVE_TYPE);
        assertThat(testEngEngrLicense.getReceiveAddrZip()).isEqualTo(DEFAULT_RECEIVE_ADDR_ZIP);
        assertThat(testEngEngrLicense.getReceiveAddrCity()).isEqualTo(DEFAULT_RECEIVE_ADDR_CITY);
        assertThat(testEngEngrLicense.getReceiveAddrTown()).isEqualTo(UPDATED_RECEIVE_ADDR_TOWN);
        assertThat(testEngEngrLicense.getReceiveAddrOther()).isEqualTo(UPDATED_RECEIVE_ADDR_OTHER);
        assertThat(testEngEngrLicense.getCertificateIdList()).isEqualTo(DEFAULT_CERTIFICATE_ID_LIST);
        assertThat(testEngEngrLicense.getSubjectList()).isEqualTo(UPDATED_SUBJECT_LIST);
        assertThat(testEngEngrLicense.getIsMore7year()).isEqualTo(DEFAULT_IS_MORE_7_YEAR);
        assertThat(testEngEngrLicense.getIsXcaCert()).isEqualTo(DEFAULT_IS_XCA_CERT);
        assertThat(testEngEngrLicense.getChangeItems()).isEqualTo(DEFAULT_CHANGE_ITEMS);
        assertThat(testEngEngrLicense.getChangeItemsDesc()).isEqualTo(UPDATED_CHANGE_ITEMS_DESC);
        assertThat(testEngEngrLicense.getContractorId()).isEqualTo(UPDATED_CONTRACTOR_ID);
        assertThat(testEngEngrLicense.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEngEngrLicense.getReviewStaff()).isEqualTo(UPDATED_REVIEW_STAFF);
        assertThat(testEngEngrLicense.getReviewTime()).isEqualTo(DEFAULT_REVIEW_TIME);
        assertThat(testEngEngrLicense.getReviewDesc()).isEqualTo(DEFAULT_REVIEW_DESC);
        assertThat(testEngEngrLicense.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testEngEngrLicense.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEngEngrLicense.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testEngEngrLicense.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testEngEngrLicense.getTransferFrom()).isEqualTo(DEFAULT_TRANSFER_FROM);
        assertThat(testEngEngrLicense.getTransferTime()).isEqualTo(DEFAULT_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void fullUpdateEngEngrLicenseWithPatch() throws Exception {
        // Initialize the database
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());
        engEngrLicenseRepository.saveAndFlush(engEngrLicense);

        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();

        // Update the engEngrLicense using partial update
        EngEngrLicense partialUpdatedEngEngrLicense = new EngEngrLicense();
        partialUpdatedEngEngrLicense.setEngEngrLicenseId(engEngrLicense.getEngEngrLicenseId());

        partialUpdatedEngEngrLicense
            .engEngrLicenseVersion(UPDATED_ENG_ENGR_LICENSE_VERSION)
            .engEngrLicenseNo(UPDATED_ENG_ENGR_LICENSE_NO)
            .receiveDate(UPDATED_RECEIVE_DATE)
            .engrLicenseDocNo(UPDATED_ENGR_LICENSE_DOC_NO)
            .engrId(UPDATED_ENGR_ID)
            .identityType(UPDATED_IDENTITY_TYPE)
            .idno(UPDATED_IDNO)
            .chName(UPDATED_CH_NAME)
            .chNameRome(UPDATED_CH_NAME_ROME)
            .enName(UPDATED_EN_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .mobileTel(UPDATED_MOBILE_TEL)
            .email(UPDATED_EMAIL)
            .permanentAddrZip(UPDATED_PERMANENT_ADDR_ZIP)
            .permanentAddrCity(UPDATED_PERMANENT_ADDR_CITY)
            .permanentAddrTown(UPDATED_PERMANENT_ADDR_TOWN)
            .permanentAddrOther(UPDATED_PERMANENT_ADDR_OTHER)
            .contactAddrZip(UPDATED_CONTACT_ADDR_ZIP)
            .contactAddrCity(UPDATED_CONTACT_ADDR_CITY)
            .contactAddrTown(UPDATED_CONTACT_ADDR_TOWN)
            .contactAddrOther(UPDATED_CONTACT_ADDR_OTHER)
            .execType(UPDATED_EXEC_TYPE)
            .compId(UPDATED_COMP_ID)
            .compType(UPDATED_COMP_TYPE)
            .compIdno(UPDATED_COMP_IDNO)
            .compChName(UPDATED_COMP_CH_NAME)
            .compEnName(UPDATED_COMP_EN_NAME)
            .compTel1(UPDATED_COMP_TEL_1)
            .compTel2(UPDATED_COMP_TEL_2)
            .compFax(UPDATED_COMP_FAX)
            .compAddrZip(UPDATED_COMP_ADDR_ZIP)
            .compAddrCity(UPDATED_COMP_ADDR_CITY)
            .compAddrTown(UPDATED_COMP_ADDR_TOWN)
            .compAddrOther(UPDATED_COMP_ADDR_OTHER)
            .compAddrEn(UPDATED_COMP_ADDR_EN)
            .applyItem(UPDATED_APPLY_ITEM)
            .applyType(UPDATED_APPLY_TYPE)
            .receiveNo(UPDATED_RECEIVE_NO)
            .publicDate(UPDATED_PUBLIC_DATE)
            .effectDateStart(UPDATED_EFFECT_DATE_START)
            .effectDateEnd(UPDATED_EFFECT_DATE_END)
            .remark(UPDATED_REMARK)
            .receiveType(UPDATED_RECEIVE_TYPE)
            .receiveAddrZip(UPDATED_RECEIVE_ADDR_ZIP)
            .receiveAddrCity(UPDATED_RECEIVE_ADDR_CITY)
            .receiveAddrTown(UPDATED_RECEIVE_ADDR_TOWN)
            .receiveAddrOther(UPDATED_RECEIVE_ADDR_OTHER)
            .certificateIdList(UPDATED_CERTIFICATE_ID_LIST)
            .subjectList(UPDATED_SUBJECT_LIST)
            .isMore7year(UPDATED_IS_MORE_7_YEAR)
            .isXcaCert(UPDATED_IS_XCA_CERT)
            .changeItems(UPDATED_CHANGE_ITEMS)
            .changeItemsDesc(UPDATED_CHANGE_ITEMS_DESC)
            .contractorId(UPDATED_CONTRACTOR_ID)
            .status(UPDATED_STATUS)
            .reviewStaff(UPDATED_REVIEW_STAFF)
            .reviewTime(UPDATED_REVIEW_TIME)
            .reviewDesc(UPDATED_REVIEW_DESC)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);

        restEngEngrLicenseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEngEngrLicense.getEngEngrLicenseId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEngEngrLicense))
            )
            .andExpect(status().isOk());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
        EngEngrLicense testEngEngrLicense = engEngrLicenseList.get(engEngrLicenseList.size() - 1);
        assertThat(testEngEngrLicense.getEngEngrLicenseVersion()).isEqualByComparingTo(UPDATED_ENG_ENGR_LICENSE_VERSION);
        assertThat(testEngEngrLicense.getEngEngrLicenseNo()).isEqualTo(UPDATED_ENG_ENGR_LICENSE_NO);
        assertThat(testEngEngrLicense.getReceiveDate()).isEqualTo(UPDATED_RECEIVE_DATE);
        assertThat(testEngEngrLicense.getEngrLicenseDocNo()).isEqualTo(UPDATED_ENGR_LICENSE_DOC_NO);
        assertThat(testEngEngrLicense.getEngrId()).isEqualTo(UPDATED_ENGR_ID);
        assertThat(testEngEngrLicense.getIdentityType()).isEqualTo(UPDATED_IDENTITY_TYPE);
        assertThat(testEngEngrLicense.getIdno()).isEqualTo(UPDATED_IDNO);
        assertThat(testEngEngrLicense.getChName()).isEqualTo(UPDATED_CH_NAME);
        assertThat(testEngEngrLicense.getChNameRome()).isEqualTo(UPDATED_CH_NAME_ROME);
        assertThat(testEngEngrLicense.getEnName()).isEqualTo(UPDATED_EN_NAME);
        assertThat(testEngEngrLicense.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testEngEngrLicense.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testEngEngrLicense.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testEngEngrLicense.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEngEngrLicense.getPermanentAddrZip()).isEqualTo(UPDATED_PERMANENT_ADDR_ZIP);
        assertThat(testEngEngrLicense.getPermanentAddrCity()).isEqualTo(UPDATED_PERMANENT_ADDR_CITY);
        assertThat(testEngEngrLicense.getPermanentAddrTown()).isEqualTo(UPDATED_PERMANENT_ADDR_TOWN);
        assertThat(testEngEngrLicense.getPermanentAddrOther()).isEqualTo(UPDATED_PERMANENT_ADDR_OTHER);
        assertThat(testEngEngrLicense.getContactAddrZip()).isEqualTo(UPDATED_CONTACT_ADDR_ZIP);
        assertThat(testEngEngrLicense.getContactAddrCity()).isEqualTo(UPDATED_CONTACT_ADDR_CITY);
        assertThat(testEngEngrLicense.getContactAddrTown()).isEqualTo(UPDATED_CONTACT_ADDR_TOWN);
        assertThat(testEngEngrLicense.getContactAddrOther()).isEqualTo(UPDATED_CONTACT_ADDR_OTHER);
        assertThat(testEngEngrLicense.getExecType()).isEqualTo(UPDATED_EXEC_TYPE);
        assertThat(testEngEngrLicense.getCompId()).isEqualTo(UPDATED_COMP_ID);
        assertThat(testEngEngrLicense.getCompType()).isEqualTo(UPDATED_COMP_TYPE);
        assertThat(testEngEngrLicense.getCompIdno()).isEqualTo(UPDATED_COMP_IDNO);
        assertThat(testEngEngrLicense.getCompChName()).isEqualTo(UPDATED_COMP_CH_NAME);
        assertThat(testEngEngrLicense.getCompEnName()).isEqualTo(UPDATED_COMP_EN_NAME);
        assertThat(testEngEngrLicense.getCompTel1()).isEqualTo(UPDATED_COMP_TEL_1);
        assertThat(testEngEngrLicense.getCompTel2()).isEqualTo(UPDATED_COMP_TEL_2);
        assertThat(testEngEngrLicense.getCompFax()).isEqualTo(UPDATED_COMP_FAX);
        assertThat(testEngEngrLicense.getCompAddrZip()).isEqualTo(UPDATED_COMP_ADDR_ZIP);
        assertThat(testEngEngrLicense.getCompAddrCity()).isEqualTo(UPDATED_COMP_ADDR_CITY);
        assertThat(testEngEngrLicense.getCompAddrTown()).isEqualTo(UPDATED_COMP_ADDR_TOWN);
        assertThat(testEngEngrLicense.getCompAddrOther()).isEqualTo(UPDATED_COMP_ADDR_OTHER);
        assertThat(testEngEngrLicense.getCompAddrEn()).isEqualTo(UPDATED_COMP_ADDR_EN);
        assertThat(testEngEngrLicense.getApplyItem()).isEqualTo(UPDATED_APPLY_ITEM);
        assertThat(testEngEngrLicense.getApplyType()).isEqualTo(UPDATED_APPLY_TYPE);
        assertThat(testEngEngrLicense.getReceiveNo()).isEqualTo(UPDATED_RECEIVE_NO);
        assertThat(testEngEngrLicense.getPublicDate()).isEqualTo(UPDATED_PUBLIC_DATE);
        assertThat(testEngEngrLicense.getEffectDateStart()).isEqualTo(UPDATED_EFFECT_DATE_START);
        assertThat(testEngEngrLicense.getEffectDateEnd()).isEqualTo(UPDATED_EFFECT_DATE_END);
        assertThat(testEngEngrLicense.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testEngEngrLicense.getReceiveType()).isEqualTo(UPDATED_RECEIVE_TYPE);
        assertThat(testEngEngrLicense.getReceiveAddrZip()).isEqualTo(UPDATED_RECEIVE_ADDR_ZIP);
        assertThat(testEngEngrLicense.getReceiveAddrCity()).isEqualTo(UPDATED_RECEIVE_ADDR_CITY);
        assertThat(testEngEngrLicense.getReceiveAddrTown()).isEqualTo(UPDATED_RECEIVE_ADDR_TOWN);
        assertThat(testEngEngrLicense.getReceiveAddrOther()).isEqualTo(UPDATED_RECEIVE_ADDR_OTHER);
        assertThat(testEngEngrLicense.getCertificateIdList()).isEqualTo(UPDATED_CERTIFICATE_ID_LIST);
        assertThat(testEngEngrLicense.getSubjectList()).isEqualTo(UPDATED_SUBJECT_LIST);
        assertThat(testEngEngrLicense.getIsMore7year()).isEqualTo(UPDATED_IS_MORE_7_YEAR);
        assertThat(testEngEngrLicense.getIsXcaCert()).isEqualTo(UPDATED_IS_XCA_CERT);
        assertThat(testEngEngrLicense.getChangeItems()).isEqualTo(UPDATED_CHANGE_ITEMS);
        assertThat(testEngEngrLicense.getChangeItemsDesc()).isEqualTo(UPDATED_CHANGE_ITEMS_DESC);
        assertThat(testEngEngrLicense.getContractorId()).isEqualTo(UPDATED_CONTRACTOR_ID);
        assertThat(testEngEngrLicense.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEngEngrLicense.getReviewStaff()).isEqualTo(UPDATED_REVIEW_STAFF);
        assertThat(testEngEngrLicense.getReviewTime()).isEqualTo(UPDATED_REVIEW_TIME);
        assertThat(testEngEngrLicense.getReviewDesc()).isEqualTo(UPDATED_REVIEW_DESC);
        assertThat(testEngEngrLicense.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testEngEngrLicense.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEngEngrLicense.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testEngEngrLicense.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testEngEngrLicense.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testEngEngrLicense.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingEngEngrLicense() throws Exception {
        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());

        // Create the EngEngrLicense
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEngEngrLicenseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, engEngrLicenseDTO.getEngEngrLicenseId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEngEngrLicense() throws Exception {
        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());

        // Create the EngEngrLicense
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEngEngrLicenseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEngEngrLicense() throws Exception {
        int databaseSizeBeforeUpdate = engEngrLicenseRepository.findAll().size();
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());

        // Create the EngEngrLicense
        EngEngrLicenseDTO engEngrLicenseDTO = engEngrLicenseMapper.toDto(engEngrLicense);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEngEngrLicenseMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(engEngrLicenseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EngEngrLicense in the database
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEngEngrLicense() throws Exception {
        // Initialize the database
        engEngrLicense.setEngEngrLicenseId(UUID.randomUUID().toString());
        engEngrLicenseRepository.saveAndFlush(engEngrLicense);

        int databaseSizeBeforeDelete = engEngrLicenseRepository.findAll().size();

        // Delete the engEngrLicense
        restEngEngrLicenseMockMvc
            .perform(delete(ENTITY_API_URL_ID, engEngrLicense.getEngEngrLicenseId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EngEngrLicense> engEngrLicenseList = engEngrLicenseRepository.findAll();
        assertThat(engEngrLicenseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
