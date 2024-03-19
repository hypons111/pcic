package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.GeoJob;
import gov.pcc.pwc.repository.GeoJobRepository;
import gov.pcc.pwc.service.dto.GeoJobDTO;
import gov.pcc.pwc.service.mapper.GeoJobMapper;
import java.math.BigDecimal;
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
 * Integration tests for the {@link GeoJobResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GeoJobResourceIT {

    private static final Long DEFAULT_GEO_JOB_ID = 1L;
    private static final Long UPDATED_GEO_JOB_ID = 2L;

    private static final String DEFAULT_SALARY_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_SALARY_RANGE = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PLACE_LIST = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PLACE_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PLACE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PLACE_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_COMMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_PUBLISH_DATE_START = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLISH_DATE_START = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PUBLISH_DATE_END = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLISH_DATE_END = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PUBLISH_STATUS = "A";
    private static final String UPDATED_PUBLISH_STATUS = "B";

    private static final String DEFAULT_JOB_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_IS_APPROVE = "A";
    private static final String UPDATED_IS_APPROVE = "B";

    private static final String DEFAULT_JOB_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_1_LIST = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_1_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_2_LIST = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_2_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_3_LIST = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_3_LIST = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PERSON_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERSON_COUNT = new BigDecimal(2);

    private static final String DEFAULT_REJECT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REJECT_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_IS_PUBLIC = "A";
    private static final String UPDATED_IS_PUBLIC = "B";

    private static final String DEFAULT_MOBILE_TEL = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TRANSFER_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSFER_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TRANSFER_FROM = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_KIND = "A";
    private static final String UPDATED_JOB_KIND = "B";

    private static final String ENTITY_API_URL = "/api/geo-jobs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GeoJobRepository geoJobRepository;

    @Autowired
    private GeoJobMapper geoJobMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoJobMockMvc;

    private GeoJob geoJob;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoJob createEntity(EntityManager em) {
        GeoJob geoJob = new GeoJob()
            .geoJobId(DEFAULT_GEO_JOB_ID)
            .salaryRange(DEFAULT_SALARY_RANGE)
            .workPlaceList(DEFAULT_WORK_PLACE_LIST)
            .workPlaceDesc(DEFAULT_WORK_PLACE_DESC)
            .otherComment(DEFAULT_OTHER_COMMENT)
            .publishDateStart(DEFAULT_PUBLISH_DATE_START)
            .publishDateEnd(DEFAULT_PUBLISH_DATE_END)
            .publishStatus(DEFAULT_PUBLISH_STATUS)
            .jobTitle(DEFAULT_JOB_TITLE)
            .isApprove(DEFAULT_IS_APPROVE)
            .jobType(DEFAULT_JOB_TYPE)
            .type1List(DEFAULT_TYPE_1_LIST)
            .type2List(DEFAULT_TYPE_2_LIST)
            .type3List(DEFAULT_TYPE_3_LIST)
            .personCount(DEFAULT_PERSON_COUNT)
            .rejectReason(DEFAULT_REJECT_REASON)
            .isPublic(DEFAULT_IS_PUBLIC)
            .mobileTel(DEFAULT_MOBILE_TEL)
            .contactTel(DEFAULT_CONTACT_TEL)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .transferDate(DEFAULT_TRANSFER_DATE)
            .transferFrom(DEFAULT_TRANSFER_FROM)
            .jobKind(DEFAULT_JOB_KIND);
        return geoJob;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoJob createUpdatedEntity(EntityManager em) {
        GeoJob geoJob = new GeoJob()
            .geoJobId(UPDATED_GEO_JOB_ID)
            .salaryRange(UPDATED_SALARY_RANGE)
            .workPlaceList(UPDATED_WORK_PLACE_LIST)
            .workPlaceDesc(UPDATED_WORK_PLACE_DESC)
            .otherComment(UPDATED_OTHER_COMMENT)
            .publishDateStart(UPDATED_PUBLISH_DATE_START)
            .publishDateEnd(UPDATED_PUBLISH_DATE_END)
            .publishStatus(UPDATED_PUBLISH_STATUS)
            .jobTitle(UPDATED_JOB_TITLE)
            .isApprove(UPDATED_IS_APPROVE)
            .jobType(UPDATED_JOB_TYPE)
            .type1List(UPDATED_TYPE_1_LIST)
            .type2List(UPDATED_TYPE_2_LIST)
            .type3List(UPDATED_TYPE_3_LIST)
            .personCount(UPDATED_PERSON_COUNT)
            .rejectReason(UPDATED_REJECT_REASON)
            .isPublic(UPDATED_IS_PUBLIC)
            .mobileTel(UPDATED_MOBILE_TEL)
            .contactTel(UPDATED_CONTACT_TEL)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .transferDate(UPDATED_TRANSFER_DATE)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .jobKind(UPDATED_JOB_KIND);
        return geoJob;
    }

    @BeforeEach
    public void initTest() {
        geoJob = createEntity(em);
    }

    @Test
    @Transactional
    void createGeoJob() throws Exception {
        int databaseSizeBeforeCreate = geoJobRepository.findAll().size();
        // Create the GeoJob
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(geoJob);
        restGeoJobMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoJobDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeCreate + 1);
        GeoJob testGeoJob = geoJobList.get(geoJobList.size() - 1);
        assertThat(testGeoJob.getGeoJobId()).isEqualTo(DEFAULT_GEO_JOB_ID);
        assertThat(testGeoJob.getSalaryRange()).isEqualTo(DEFAULT_SALARY_RANGE);
        assertThat(testGeoJob.getWorkPlaceList()).isEqualTo(DEFAULT_WORK_PLACE_LIST);
        assertThat(testGeoJob.getWorkPlaceDesc()).isEqualTo(DEFAULT_WORK_PLACE_DESC);
        assertThat(testGeoJob.getOtherComment()).isEqualTo(DEFAULT_OTHER_COMMENT);
        assertThat(testGeoJob.getPublishDateStart()).isEqualTo(DEFAULT_PUBLISH_DATE_START);
        assertThat(testGeoJob.getPublishDateEnd()).isEqualTo(DEFAULT_PUBLISH_DATE_END);
        assertThat(testGeoJob.getPublishStatus()).isEqualTo(DEFAULT_PUBLISH_STATUS);
        assertThat(testGeoJob.getJobTitle()).isEqualTo(DEFAULT_JOB_TITLE);
        assertThat(testGeoJob.getIsApprove()).isEqualTo(DEFAULT_IS_APPROVE);
        assertThat(testGeoJob.getJobType()).isEqualTo(DEFAULT_JOB_TYPE);
        assertThat(testGeoJob.getType1List()).isEqualTo(DEFAULT_TYPE_1_LIST);
        assertThat(testGeoJob.getType2List()).isEqualTo(DEFAULT_TYPE_2_LIST);
        assertThat(testGeoJob.getType3List()).isEqualTo(DEFAULT_TYPE_3_LIST);
        assertThat(testGeoJob.getPersonCount()).isEqualByComparingTo(DEFAULT_PERSON_COUNT);
        assertThat(testGeoJob.getRejectReason()).isEqualTo(DEFAULT_REJECT_REASON);
        assertThat(testGeoJob.getIsPublic()).isEqualTo(DEFAULT_IS_PUBLIC);
        assertThat(testGeoJob.getMobileTel()).isEqualTo(DEFAULT_MOBILE_TEL);
        assertThat(testGeoJob.getContactTel()).isEqualTo(DEFAULT_CONTACT_TEL);
        assertThat(testGeoJob.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testGeoJob.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testGeoJob.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testGeoJob.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testGeoJob.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testGeoJob.getTransferDate()).isEqualTo(DEFAULT_TRANSFER_DATE);
        assertThat(testGeoJob.getTransferFrom()).isEqualTo(DEFAULT_TRANSFER_FROM);
        assertThat(testGeoJob.getJobKind()).isEqualTo(DEFAULT_JOB_KIND);
    }

    @Test
    @Transactional
    void createGeoJobWithExistingId() throws Exception {
        // Create the GeoJob with an existing ID
        geoJob.setId(1L);
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(geoJob);

        int databaseSizeBeforeCreate = geoJobRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoJobMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoJobDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGeoJobs() throws Exception {
        // Initialize the database
        geoJobRepository.saveAndFlush(geoJob);

        // Get all the geoJobList
        restGeoJobMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoJob.getId().intValue())))
            .andExpect(jsonPath("$.[*].geoJobId").value(hasItem(DEFAULT_GEO_JOB_ID.intValue())))
            .andExpect(jsonPath("$.[*].salaryRange").value(hasItem(DEFAULT_SALARY_RANGE)))
            .andExpect(jsonPath("$.[*].workPlaceList").value(hasItem(DEFAULT_WORK_PLACE_LIST)))
            .andExpect(jsonPath("$.[*].workPlaceDesc").value(hasItem(DEFAULT_WORK_PLACE_DESC)))
            .andExpect(jsonPath("$.[*].otherComment").value(hasItem(DEFAULT_OTHER_COMMENT)))
            .andExpect(jsonPath("$.[*].publishDateStart").value(hasItem(DEFAULT_PUBLISH_DATE_START.toString())))
            .andExpect(jsonPath("$.[*].publishDateEnd").value(hasItem(DEFAULT_PUBLISH_DATE_END.toString())))
            .andExpect(jsonPath("$.[*].publishStatus").value(hasItem(DEFAULT_PUBLISH_STATUS)))
            .andExpect(jsonPath("$.[*].jobTitle").value(hasItem(DEFAULT_JOB_TITLE)))
            .andExpect(jsonPath("$.[*].isApprove").value(hasItem(DEFAULT_IS_APPROVE)))
            .andExpect(jsonPath("$.[*].jobType").value(hasItem(DEFAULT_JOB_TYPE)))
            .andExpect(jsonPath("$.[*].type1List").value(hasItem(DEFAULT_TYPE_1_LIST)))
            .andExpect(jsonPath("$.[*].type2List").value(hasItem(DEFAULT_TYPE_2_LIST)))
            .andExpect(jsonPath("$.[*].type3List").value(hasItem(DEFAULT_TYPE_3_LIST)))
            .andExpect(jsonPath("$.[*].personCount").value(hasItem(sameNumber(DEFAULT_PERSON_COUNT))))
            .andExpect(jsonPath("$.[*].rejectReason").value(hasItem(DEFAULT_REJECT_REASON)))
            .andExpect(jsonPath("$.[*].isPublic").value(hasItem(DEFAULT_IS_PUBLIC)))
            .andExpect(jsonPath("$.[*].mobileTel").value(hasItem(DEFAULT_MOBILE_TEL)))
            .andExpect(jsonPath("$.[*].contactTel").value(hasItem(DEFAULT_CONTACT_TEL)))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].transferDate").value(hasItem(DEFAULT_TRANSFER_DATE.toString())))
            .andExpect(jsonPath("$.[*].transferFrom").value(hasItem(DEFAULT_TRANSFER_FROM)))
            .andExpect(jsonPath("$.[*].jobKind").value(hasItem(DEFAULT_JOB_KIND)));
    }

    @Test
    @Transactional
    void getGeoJob() throws Exception {
        // Initialize the database
        geoJobRepository.saveAndFlush(geoJob);

        // Get the geoJob
        restGeoJobMockMvc
            .perform(get(ENTITY_API_URL_ID, geoJob.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoJob.getId().intValue()))
            .andExpect(jsonPath("$.geoJobId").value(DEFAULT_GEO_JOB_ID.intValue()))
            .andExpect(jsonPath("$.salaryRange").value(DEFAULT_SALARY_RANGE))
            .andExpect(jsonPath("$.workPlaceList").value(DEFAULT_WORK_PLACE_LIST))
            .andExpect(jsonPath("$.workPlaceDesc").value(DEFAULT_WORK_PLACE_DESC))
            .andExpect(jsonPath("$.otherComment").value(DEFAULT_OTHER_COMMENT))
            .andExpect(jsonPath("$.publishDateStart").value(DEFAULT_PUBLISH_DATE_START.toString()))
            .andExpect(jsonPath("$.publishDateEnd").value(DEFAULT_PUBLISH_DATE_END.toString()))
            .andExpect(jsonPath("$.publishStatus").value(DEFAULT_PUBLISH_STATUS))
            .andExpect(jsonPath("$.jobTitle").value(DEFAULT_JOB_TITLE))
            .andExpect(jsonPath("$.isApprove").value(DEFAULT_IS_APPROVE))
            .andExpect(jsonPath("$.jobType").value(DEFAULT_JOB_TYPE))
            .andExpect(jsonPath("$.type1List").value(DEFAULT_TYPE_1_LIST))
            .andExpect(jsonPath("$.type2List").value(DEFAULT_TYPE_2_LIST))
            .andExpect(jsonPath("$.type3List").value(DEFAULT_TYPE_3_LIST))
            .andExpect(jsonPath("$.personCount").value(sameNumber(DEFAULT_PERSON_COUNT)))
            .andExpect(jsonPath("$.rejectReason").value(DEFAULT_REJECT_REASON))
            .andExpect(jsonPath("$.isPublic").value(DEFAULT_IS_PUBLIC))
            .andExpect(jsonPath("$.mobileTel").value(DEFAULT_MOBILE_TEL))
            .andExpect(jsonPath("$.contactTel").value(DEFAULT_CONTACT_TEL))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.transferDate").value(DEFAULT_TRANSFER_DATE.toString()))
            .andExpect(jsonPath("$.transferFrom").value(DEFAULT_TRANSFER_FROM))
            .andExpect(jsonPath("$.jobKind").value(DEFAULT_JOB_KIND));
    }

    @Test
    @Transactional
    void getNonExistingGeoJob() throws Exception {
        // Get the geoJob
        restGeoJobMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGeoJob() throws Exception {
        // Initialize the database
        geoJobRepository.saveAndFlush(geoJob);

        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();

        // Update the geoJob
        GeoJob updatedGeoJob = geoJobRepository.findById(geoJob.getId()).get();
        // Disconnect from session so that the updates on updatedGeoJob are not directly saved in db
        em.detach(updatedGeoJob);
        updatedGeoJob
            .geoJobId(UPDATED_GEO_JOB_ID)
            .salaryRange(UPDATED_SALARY_RANGE)
            .workPlaceList(UPDATED_WORK_PLACE_LIST)
            .workPlaceDesc(UPDATED_WORK_PLACE_DESC)
            .otherComment(UPDATED_OTHER_COMMENT)
            .publishDateStart(UPDATED_PUBLISH_DATE_START)
            .publishDateEnd(UPDATED_PUBLISH_DATE_END)
            .publishStatus(UPDATED_PUBLISH_STATUS)
            .jobTitle(UPDATED_JOB_TITLE)
            .isApprove(UPDATED_IS_APPROVE)
            .jobType(UPDATED_JOB_TYPE)
            .type1List(UPDATED_TYPE_1_LIST)
            .type2List(UPDATED_TYPE_2_LIST)
            .type3List(UPDATED_TYPE_3_LIST)
            .personCount(UPDATED_PERSON_COUNT)
            .rejectReason(UPDATED_REJECT_REASON)
            .isPublic(UPDATED_IS_PUBLIC)
            .mobileTel(UPDATED_MOBILE_TEL)
            .contactTel(UPDATED_CONTACT_TEL)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .transferDate(UPDATED_TRANSFER_DATE)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .jobKind(UPDATED_JOB_KIND);
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(updatedGeoJob);

        restGeoJobMockMvc
            .perform(
                put(ENTITY_API_URL_ID, geoJobDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoJobDTO))
            )
            .andExpect(status().isOk());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
        GeoJob testGeoJob = geoJobList.get(geoJobList.size() - 1);
        assertThat(testGeoJob.getGeoJobId()).isEqualTo(UPDATED_GEO_JOB_ID);
        assertThat(testGeoJob.getSalaryRange()).isEqualTo(UPDATED_SALARY_RANGE);
        assertThat(testGeoJob.getWorkPlaceList()).isEqualTo(UPDATED_WORK_PLACE_LIST);
        assertThat(testGeoJob.getWorkPlaceDesc()).isEqualTo(UPDATED_WORK_PLACE_DESC);
        assertThat(testGeoJob.getOtherComment()).isEqualTo(UPDATED_OTHER_COMMENT);
        assertThat(testGeoJob.getPublishDateStart()).isEqualTo(UPDATED_PUBLISH_DATE_START);
        assertThat(testGeoJob.getPublishDateEnd()).isEqualTo(UPDATED_PUBLISH_DATE_END);
        assertThat(testGeoJob.getPublishStatus()).isEqualTo(UPDATED_PUBLISH_STATUS);
        assertThat(testGeoJob.getJobTitle()).isEqualTo(UPDATED_JOB_TITLE);
        assertThat(testGeoJob.getIsApprove()).isEqualTo(UPDATED_IS_APPROVE);
        assertThat(testGeoJob.getJobType()).isEqualTo(UPDATED_JOB_TYPE);
        assertThat(testGeoJob.getType1List()).isEqualTo(UPDATED_TYPE_1_LIST);
        assertThat(testGeoJob.getType2List()).isEqualTo(UPDATED_TYPE_2_LIST);
        assertThat(testGeoJob.getType3List()).isEqualTo(UPDATED_TYPE_3_LIST);
        assertThat(testGeoJob.getPersonCount()).isEqualByComparingTo(UPDATED_PERSON_COUNT);
        assertThat(testGeoJob.getRejectReason()).isEqualTo(UPDATED_REJECT_REASON);
        assertThat(testGeoJob.getIsPublic()).isEqualTo(UPDATED_IS_PUBLIC);
        assertThat(testGeoJob.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testGeoJob.getContactTel()).isEqualTo(UPDATED_CONTACT_TEL);
        assertThat(testGeoJob.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testGeoJob.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testGeoJob.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testGeoJob.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testGeoJob.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testGeoJob.getTransferDate()).isEqualTo(UPDATED_TRANSFER_DATE);
        assertThat(testGeoJob.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testGeoJob.getJobKind()).isEqualTo(UPDATED_JOB_KIND);
    }

    @Test
    @Transactional
    void putNonExistingGeoJob() throws Exception {
        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();
        geoJob.setId(count.incrementAndGet());

        // Create the GeoJob
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(geoJob);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoJobMockMvc
            .perform(
                put(ENTITY_API_URL_ID, geoJobDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoJobDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGeoJob() throws Exception {
        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();
        geoJob.setId(count.incrementAndGet());

        // Create the GeoJob
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(geoJob);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoJobMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoJobDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGeoJob() throws Exception {
        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();
        geoJob.setId(count.incrementAndGet());

        // Create the GeoJob
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(geoJob);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoJobMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoJobDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGeoJobWithPatch() throws Exception {
        // Initialize the database
        geoJobRepository.saveAndFlush(geoJob);

        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();

        // Update the geoJob using partial update
        GeoJob partialUpdatedGeoJob = new GeoJob();
        partialUpdatedGeoJob.setId(geoJob.getId());

        partialUpdatedGeoJob
            .geoJobId(UPDATED_GEO_JOB_ID)
            .salaryRange(UPDATED_SALARY_RANGE)
            .workPlaceDesc(UPDATED_WORK_PLACE_DESC)
            .publishDateEnd(UPDATED_PUBLISH_DATE_END)
            .publishStatus(UPDATED_PUBLISH_STATUS)
            .jobTitle(UPDATED_JOB_TITLE)
            .isApprove(UPDATED_IS_APPROVE)
            .rejectReason(UPDATED_REJECT_REASON)
            .isPublic(UPDATED_IS_PUBLIC)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .transferDate(UPDATED_TRANSFER_DATE);

        restGeoJobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGeoJob.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGeoJob))
            )
            .andExpect(status().isOk());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
        GeoJob testGeoJob = geoJobList.get(geoJobList.size() - 1);
        assertThat(testGeoJob.getGeoJobId()).isEqualTo(UPDATED_GEO_JOB_ID);
        assertThat(testGeoJob.getSalaryRange()).isEqualTo(UPDATED_SALARY_RANGE);
        assertThat(testGeoJob.getWorkPlaceList()).isEqualTo(DEFAULT_WORK_PLACE_LIST);
        assertThat(testGeoJob.getWorkPlaceDesc()).isEqualTo(UPDATED_WORK_PLACE_DESC);
        assertThat(testGeoJob.getOtherComment()).isEqualTo(DEFAULT_OTHER_COMMENT);
        assertThat(testGeoJob.getPublishDateStart()).isEqualTo(DEFAULT_PUBLISH_DATE_START);
        assertThat(testGeoJob.getPublishDateEnd()).isEqualTo(UPDATED_PUBLISH_DATE_END);
        assertThat(testGeoJob.getPublishStatus()).isEqualTo(UPDATED_PUBLISH_STATUS);
        assertThat(testGeoJob.getJobTitle()).isEqualTo(UPDATED_JOB_TITLE);
        assertThat(testGeoJob.getIsApprove()).isEqualTo(UPDATED_IS_APPROVE);
        assertThat(testGeoJob.getJobType()).isEqualTo(DEFAULT_JOB_TYPE);
        assertThat(testGeoJob.getType1List()).isEqualTo(DEFAULT_TYPE_1_LIST);
        assertThat(testGeoJob.getType2List()).isEqualTo(DEFAULT_TYPE_2_LIST);
        assertThat(testGeoJob.getType3List()).isEqualTo(DEFAULT_TYPE_3_LIST);
        assertThat(testGeoJob.getPersonCount()).isEqualByComparingTo(DEFAULT_PERSON_COUNT);
        assertThat(testGeoJob.getRejectReason()).isEqualTo(UPDATED_REJECT_REASON);
        assertThat(testGeoJob.getIsPublic()).isEqualTo(UPDATED_IS_PUBLIC);
        assertThat(testGeoJob.getMobileTel()).isEqualTo(DEFAULT_MOBILE_TEL);
        assertThat(testGeoJob.getContactTel()).isEqualTo(DEFAULT_CONTACT_TEL);
        assertThat(testGeoJob.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testGeoJob.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testGeoJob.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testGeoJob.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testGeoJob.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testGeoJob.getTransferDate()).isEqualTo(UPDATED_TRANSFER_DATE);
        assertThat(testGeoJob.getTransferFrom()).isEqualTo(DEFAULT_TRANSFER_FROM);
        assertThat(testGeoJob.getJobKind()).isEqualTo(DEFAULT_JOB_KIND);
    }

    @Test
    @Transactional
    void fullUpdateGeoJobWithPatch() throws Exception {
        // Initialize the database
        geoJobRepository.saveAndFlush(geoJob);

        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();

        // Update the geoJob using partial update
        GeoJob partialUpdatedGeoJob = new GeoJob();
        partialUpdatedGeoJob.setId(geoJob.getId());

        partialUpdatedGeoJob
            .geoJobId(UPDATED_GEO_JOB_ID)
            .salaryRange(UPDATED_SALARY_RANGE)
            .workPlaceList(UPDATED_WORK_PLACE_LIST)
            .workPlaceDesc(UPDATED_WORK_PLACE_DESC)
            .otherComment(UPDATED_OTHER_COMMENT)
            .publishDateStart(UPDATED_PUBLISH_DATE_START)
            .publishDateEnd(UPDATED_PUBLISH_DATE_END)
            .publishStatus(UPDATED_PUBLISH_STATUS)
            .jobTitle(UPDATED_JOB_TITLE)
            .isApprove(UPDATED_IS_APPROVE)
            .jobType(UPDATED_JOB_TYPE)
            .type1List(UPDATED_TYPE_1_LIST)
            .type2List(UPDATED_TYPE_2_LIST)
            .type3List(UPDATED_TYPE_3_LIST)
            .personCount(UPDATED_PERSON_COUNT)
            .rejectReason(UPDATED_REJECT_REASON)
            .isPublic(UPDATED_IS_PUBLIC)
            .mobileTel(UPDATED_MOBILE_TEL)
            .contactTel(UPDATED_CONTACT_TEL)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .transferDate(UPDATED_TRANSFER_DATE)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .jobKind(UPDATED_JOB_KIND);

        restGeoJobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGeoJob.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGeoJob))
            )
            .andExpect(status().isOk());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
        GeoJob testGeoJob = geoJobList.get(geoJobList.size() - 1);
        assertThat(testGeoJob.getGeoJobId()).isEqualTo(UPDATED_GEO_JOB_ID);
        assertThat(testGeoJob.getSalaryRange()).isEqualTo(UPDATED_SALARY_RANGE);
        assertThat(testGeoJob.getWorkPlaceList()).isEqualTo(UPDATED_WORK_PLACE_LIST);
        assertThat(testGeoJob.getWorkPlaceDesc()).isEqualTo(UPDATED_WORK_PLACE_DESC);
        assertThat(testGeoJob.getOtherComment()).isEqualTo(UPDATED_OTHER_COMMENT);
        assertThat(testGeoJob.getPublishDateStart()).isEqualTo(UPDATED_PUBLISH_DATE_START);
        assertThat(testGeoJob.getPublishDateEnd()).isEqualTo(UPDATED_PUBLISH_DATE_END);
        assertThat(testGeoJob.getPublishStatus()).isEqualTo(UPDATED_PUBLISH_STATUS);
        assertThat(testGeoJob.getJobTitle()).isEqualTo(UPDATED_JOB_TITLE);
        assertThat(testGeoJob.getIsApprove()).isEqualTo(UPDATED_IS_APPROVE);
        assertThat(testGeoJob.getJobType()).isEqualTo(UPDATED_JOB_TYPE);
        assertThat(testGeoJob.getType1List()).isEqualTo(UPDATED_TYPE_1_LIST);
        assertThat(testGeoJob.getType2List()).isEqualTo(UPDATED_TYPE_2_LIST);
        assertThat(testGeoJob.getType3List()).isEqualTo(UPDATED_TYPE_3_LIST);
        assertThat(testGeoJob.getPersonCount()).isEqualByComparingTo(UPDATED_PERSON_COUNT);
        assertThat(testGeoJob.getRejectReason()).isEqualTo(UPDATED_REJECT_REASON);
        assertThat(testGeoJob.getIsPublic()).isEqualTo(UPDATED_IS_PUBLIC);
        assertThat(testGeoJob.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testGeoJob.getContactTel()).isEqualTo(UPDATED_CONTACT_TEL);
        assertThat(testGeoJob.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testGeoJob.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testGeoJob.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testGeoJob.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testGeoJob.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testGeoJob.getTransferDate()).isEqualTo(UPDATED_TRANSFER_DATE);
        assertThat(testGeoJob.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testGeoJob.getJobKind()).isEqualTo(UPDATED_JOB_KIND);
    }

    @Test
    @Transactional
    void patchNonExistingGeoJob() throws Exception {
        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();
        geoJob.setId(count.incrementAndGet());

        // Create the GeoJob
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(geoJob);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoJobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, geoJobDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(geoJobDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGeoJob() throws Exception {
        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();
        geoJob.setId(count.incrementAndGet());

        // Create the GeoJob
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(geoJob);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoJobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(geoJobDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGeoJob() throws Exception {
        int databaseSizeBeforeUpdate = geoJobRepository.findAll().size();
        geoJob.setId(count.incrementAndGet());

        // Create the GeoJob
        GeoJobDTO geoJobDTO = geoJobMapper.toDto(geoJob);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoJobMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(geoJobDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GeoJob in the database
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGeoJob() throws Exception {
        // Initialize the database
        geoJobRepository.saveAndFlush(geoJob);

        int databaseSizeBeforeDelete = geoJobRepository.findAll().size();

        // Delete the geoJob
        restGeoJobMockMvc
            .perform(delete(ENTITY_API_URL_ID, geoJob.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoJob> geoJobList = geoJobRepository.findAll();
        assertThat(geoJobList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
