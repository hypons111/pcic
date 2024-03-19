package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.GeoCompany;
import gov.pcc.pwc.repository.GeoCompanyRepository;
import gov.pcc.pwc.service.dto.GeoCompanyDTO;
import gov.pcc.pwc.service.mapper.GeoCompanyMapper;
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
 * Integration tests for the {@link GeoCompanyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GeoCompanyResourceIT {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_ID = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_IS_APPROVE = "A";
    private static final String UPDATED_IS_APPROVE = "B";

    private static final String DEFAULT_WORK_TEL = "AAAAAAAAAA";
    private static final String UPDATED_WORK_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPLY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPLY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MOBILE_TEL = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_TEL = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/geo-companies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{geoCompanyId}";

    @Autowired
    private GeoCompanyRepository geoCompanyRepository;

    @Autowired
    private GeoCompanyMapper geoCompanyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoCompanyMockMvc;

    private GeoCompany geoCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoCompany createEntity(EntityManager em) {
        GeoCompany geoCompany = new GeoCompany()
            .companyName(DEFAULT_COMPANY_NAME)
            .companyType(DEFAULT_COMPANY_TYPE)
            .companyId(DEFAULT_COMPANY_ID)
            .contactPerson(DEFAULT_CONTACT_PERSON)
            .isApprove(DEFAULT_IS_APPROVE)
            .workTel(DEFAULT_WORK_TEL)
            .email(DEFAULT_EMAIL)
            .remark(DEFAULT_REMARK)
            .applyDate(DEFAULT_APPLY_DATE)
            .mobileTel(DEFAULT_MOBILE_TEL)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .transferFrom(DEFAULT_TRANSFER_FROM)
            .transferTime(DEFAULT_TRANSFER_TIME);
        return geoCompany;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoCompany createUpdatedEntity(EntityManager em) {
        GeoCompany geoCompany = new GeoCompany()
            .companyName(UPDATED_COMPANY_NAME)
            .companyType(UPDATED_COMPANY_TYPE)
            .companyId(UPDATED_COMPANY_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .isApprove(UPDATED_IS_APPROVE)
            .workTel(UPDATED_WORK_TEL)
            .email(UPDATED_EMAIL)
            .remark(UPDATED_REMARK)
            .applyDate(UPDATED_APPLY_DATE)
            .mobileTel(UPDATED_MOBILE_TEL)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);
        return geoCompany;
    }

    @BeforeEach
    public void initTest() {
        geoCompany = createEntity(em);
    }

    @Test
    @Transactional
    void createGeoCompany() throws Exception {
        int databaseSizeBeforeCreate = geoCompanyRepository.findAll().size();
        // Create the GeoCompany
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(geoCompany);
        restGeoCompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        GeoCompany testGeoCompany = geoCompanyList.get(geoCompanyList.size() - 1);
        assertThat(testGeoCompany.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testGeoCompany.getCompanyType()).isEqualTo(DEFAULT_COMPANY_TYPE);
        assertThat(testGeoCompany.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testGeoCompany.getContactPerson()).isEqualTo(DEFAULT_CONTACT_PERSON);
        assertThat(testGeoCompany.getIsApprove()).isEqualTo(DEFAULT_IS_APPROVE);
        assertThat(testGeoCompany.getWorkTel()).isEqualTo(DEFAULT_WORK_TEL);
        assertThat(testGeoCompany.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testGeoCompany.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testGeoCompany.getApplyDate()).isEqualTo(DEFAULT_APPLY_DATE);
        assertThat(testGeoCompany.getMobileTel()).isEqualTo(DEFAULT_MOBILE_TEL);
        assertThat(testGeoCompany.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testGeoCompany.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testGeoCompany.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testGeoCompany.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testGeoCompany.getTransferFrom()).isEqualTo(DEFAULT_TRANSFER_FROM);
        assertThat(testGeoCompany.getTransferTime()).isEqualTo(DEFAULT_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void createGeoCompanyWithExistingId() throws Exception {
        // Create the GeoCompany with an existing ID
        geoCompany.setGeoCompanyId("existing_id");
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(geoCompany);

        int databaseSizeBeforeCreate = geoCompanyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoCompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGeoCompanies() throws Exception {
        // Initialize the database
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());
        geoCompanyRepository.saveAndFlush(geoCompany);

        // Get all the geoCompanyList
        restGeoCompanyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=geoCompanyId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].geoCompanyId").value(hasItem(geoCompany.getGeoCompanyId())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].companyType").value(hasItem(DEFAULT_COMPANY_TYPE)))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID)))
            .andExpect(jsonPath("$.[*].contactPerson").value(hasItem(DEFAULT_CONTACT_PERSON)))
            .andExpect(jsonPath("$.[*].isApprove").value(hasItem(DEFAULT_IS_APPROVE)))
            .andExpect(jsonPath("$.[*].workTel").value(hasItem(DEFAULT_WORK_TEL)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].applyDate").value(hasItem(DEFAULT_APPLY_DATE.toString())))
            .andExpect(jsonPath("$.[*].mobileTel").value(hasItem(DEFAULT_MOBILE_TEL)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].transferFrom").value(hasItem(DEFAULT_TRANSFER_FROM)))
            .andExpect(jsonPath("$.[*].transferTime").value(hasItem(DEFAULT_TRANSFER_TIME.toString())));
    }

    @Test
    @Transactional
    void getGeoCompany() throws Exception {
        // Initialize the database
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());
        geoCompanyRepository.saveAndFlush(geoCompany);

        // Get the geoCompany
        restGeoCompanyMockMvc
            .perform(get(ENTITY_API_URL_ID, geoCompany.getGeoCompanyId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.geoCompanyId").value(geoCompany.getGeoCompanyId()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.companyType").value(DEFAULT_COMPANY_TYPE))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID))
            .andExpect(jsonPath("$.contactPerson").value(DEFAULT_CONTACT_PERSON))
            .andExpect(jsonPath("$.isApprove").value(DEFAULT_IS_APPROVE))
            .andExpect(jsonPath("$.workTel").value(DEFAULT_WORK_TEL))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.applyDate").value(DEFAULT_APPLY_DATE.toString()))
            .andExpect(jsonPath("$.mobileTel").value(DEFAULT_MOBILE_TEL))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.transferFrom").value(DEFAULT_TRANSFER_FROM))
            .andExpect(jsonPath("$.transferTime").value(DEFAULT_TRANSFER_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingGeoCompany() throws Exception {
        // Get the geoCompany
        restGeoCompanyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGeoCompany() throws Exception {
        // Initialize the database
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());
        geoCompanyRepository.saveAndFlush(geoCompany);

        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();

        // Update the geoCompany
        GeoCompany updatedGeoCompany = geoCompanyRepository.findById(geoCompany.getGeoCompanyId()).get();
        // Disconnect from session so that the updates on updatedGeoCompany are not directly saved in db
        em.detach(updatedGeoCompany);
        updatedGeoCompany
            .companyName(UPDATED_COMPANY_NAME)
            .companyType(UPDATED_COMPANY_TYPE)
            .companyId(UPDATED_COMPANY_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .isApprove(UPDATED_IS_APPROVE)
            .workTel(UPDATED_WORK_TEL)
            .email(UPDATED_EMAIL)
            .remark(UPDATED_REMARK)
            .applyDate(UPDATED_APPLY_DATE)
            .mobileTel(UPDATED_MOBILE_TEL)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(updatedGeoCompany);

        restGeoCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, geoCompanyDTO.getGeoCompanyId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO))
            )
            .andExpect(status().isOk());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
        GeoCompany testGeoCompany = geoCompanyList.get(geoCompanyList.size() - 1);
        assertThat(testGeoCompany.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testGeoCompany.getCompanyType()).isEqualTo(UPDATED_COMPANY_TYPE);
        assertThat(testGeoCompany.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testGeoCompany.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testGeoCompany.getIsApprove()).isEqualTo(UPDATED_IS_APPROVE);
        assertThat(testGeoCompany.getWorkTel()).isEqualTo(UPDATED_WORK_TEL);
        assertThat(testGeoCompany.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testGeoCompany.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testGeoCompany.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testGeoCompany.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testGeoCompany.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testGeoCompany.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testGeoCompany.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testGeoCompany.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testGeoCompany.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testGeoCompany.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void putNonExistingGeoCompany() throws Exception {
        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());

        // Create the GeoCompany
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(geoCompany);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, geoCompanyDTO.getGeoCompanyId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGeoCompany() throws Exception {
        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());

        // Create the GeoCompany
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(geoCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGeoCompany() throws Exception {
        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());

        // Create the GeoCompany
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(geoCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoCompanyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGeoCompanyWithPatch() throws Exception {
        // Initialize the database
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());
        geoCompanyRepository.saveAndFlush(geoCompany);

        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();

        // Update the geoCompany using partial update
        GeoCompany partialUpdatedGeoCompany = new GeoCompany();
        partialUpdatedGeoCompany.setGeoCompanyId(geoCompany.getGeoCompanyId());

        partialUpdatedGeoCompany
            .companyType(UPDATED_COMPANY_TYPE)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .isApprove(UPDATED_IS_APPROVE)
            .workTel(UPDATED_WORK_TEL)
            .applyDate(UPDATED_APPLY_DATE)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);

        restGeoCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGeoCompany.getGeoCompanyId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGeoCompany))
            )
            .andExpect(status().isOk());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
        GeoCompany testGeoCompany = geoCompanyList.get(geoCompanyList.size() - 1);
        assertThat(testGeoCompany.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testGeoCompany.getCompanyType()).isEqualTo(UPDATED_COMPANY_TYPE);
        assertThat(testGeoCompany.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testGeoCompany.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testGeoCompany.getIsApprove()).isEqualTo(UPDATED_IS_APPROVE);
        assertThat(testGeoCompany.getWorkTel()).isEqualTo(UPDATED_WORK_TEL);
        assertThat(testGeoCompany.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testGeoCompany.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testGeoCompany.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testGeoCompany.getMobileTel()).isEqualTo(DEFAULT_MOBILE_TEL);
        assertThat(testGeoCompany.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testGeoCompany.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testGeoCompany.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testGeoCompany.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testGeoCompany.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testGeoCompany.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void fullUpdateGeoCompanyWithPatch() throws Exception {
        // Initialize the database
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());
        geoCompanyRepository.saveAndFlush(geoCompany);

        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();

        // Update the geoCompany using partial update
        GeoCompany partialUpdatedGeoCompany = new GeoCompany();
        partialUpdatedGeoCompany.setGeoCompanyId(geoCompany.getGeoCompanyId());

        partialUpdatedGeoCompany
            .companyName(UPDATED_COMPANY_NAME)
            .companyType(UPDATED_COMPANY_TYPE)
            .companyId(UPDATED_COMPANY_ID)
            .contactPerson(UPDATED_CONTACT_PERSON)
            .isApprove(UPDATED_IS_APPROVE)
            .workTel(UPDATED_WORK_TEL)
            .email(UPDATED_EMAIL)
            .remark(UPDATED_REMARK)
            .applyDate(UPDATED_APPLY_DATE)
            .mobileTel(UPDATED_MOBILE_TEL)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .transferFrom(UPDATED_TRANSFER_FROM)
            .transferTime(UPDATED_TRANSFER_TIME);

        restGeoCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGeoCompany.getGeoCompanyId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGeoCompany))
            )
            .andExpect(status().isOk());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
        GeoCompany testGeoCompany = geoCompanyList.get(geoCompanyList.size() - 1);
        assertThat(testGeoCompany.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testGeoCompany.getCompanyType()).isEqualTo(UPDATED_COMPANY_TYPE);
        assertThat(testGeoCompany.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testGeoCompany.getContactPerson()).isEqualTo(UPDATED_CONTACT_PERSON);
        assertThat(testGeoCompany.getIsApprove()).isEqualTo(UPDATED_IS_APPROVE);
        assertThat(testGeoCompany.getWorkTel()).isEqualTo(UPDATED_WORK_TEL);
        assertThat(testGeoCompany.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testGeoCompany.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testGeoCompany.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testGeoCompany.getMobileTel()).isEqualTo(UPDATED_MOBILE_TEL);
        assertThat(testGeoCompany.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testGeoCompany.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testGeoCompany.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testGeoCompany.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testGeoCompany.getTransferFrom()).isEqualTo(UPDATED_TRANSFER_FROM);
        assertThat(testGeoCompany.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingGeoCompany() throws Exception {
        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());

        // Create the GeoCompany
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(geoCompany);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, geoCompanyDTO.getGeoCompanyId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGeoCompany() throws Exception {
        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());

        // Create the GeoCompany
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(geoCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGeoCompany() throws Exception {
        int databaseSizeBeforeUpdate = geoCompanyRepository.findAll().size();
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());

        // Create the GeoCompany
        GeoCompanyDTO geoCompanyDTO = geoCompanyMapper.toDto(geoCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGeoCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(geoCompanyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GeoCompany in the database
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGeoCompany() throws Exception {
        // Initialize the database
        geoCompany.setGeoCompanyId(UUID.randomUUID().toString());
        geoCompanyRepository.saveAndFlush(geoCompany);

        int databaseSizeBeforeDelete = geoCompanyRepository.findAll().size();

        // Delete the geoCompany
        restGeoCompanyMockMvc
            .perform(delete(ENTITY_API_URL_ID, geoCompany.getGeoCompanyId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoCompany> geoCompanyList = geoCompanyRepository.findAll();
        assertThat(geoCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
