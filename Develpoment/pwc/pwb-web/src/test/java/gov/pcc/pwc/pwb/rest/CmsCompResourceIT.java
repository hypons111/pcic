package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.repository.CmsCompRepository;
import gov.pcc.pwc.service.dto.CmsCompDTO;
import gov.pcc.pwc.service.mapper.CmsCompMapper;
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
 * Integration tests for the {@link CmsCompResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CmsCompResourceIT {

    private static final String DEFAULT_TYPE = "A";
    private static final String UPDATED_TYPE = "B";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USE_STATUS = "A";
    private static final String UPDATED_USE_STATUS = "B";

    private static final String DEFAULT_CHAIRMAN = "AAAAAAAAAA";
    private static final String UPDATED_CHAIRMAN = "BBBBBBBBBB";

    private static final String DEFAULT_SECRETARY_GENERAL = "AAAAAAAAAA";
    private static final String UPDATED_SECRETARY_GENERAL = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_1 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_2 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CHAIRMAN_TEL = "AAAAAAAAAA";
    private static final String UPDATED_CHAIRMAN_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT_LIST = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/cms-comps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CmsCompRepository cmsCompRepository;

    @Autowired
    private CmsCompMapper cmsCompMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCmsCompMockMvc;

    private CmsComp cmsComp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CmsComp createEntity(EntityManager em) {
        CmsComp cmsComp = new CmsComp()
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .useStatus(DEFAULT_USE_STATUS)
            .chairman(DEFAULT_CHAIRMAN)
            .secretaryGeneral(DEFAULT_SECRETARY_GENERAL)
            .tel1(DEFAULT_TEL_1)
            .tel2(DEFAULT_TEL_2)
            .chairmanTel(DEFAULT_CHAIRMAN_TEL)
            .fax(DEFAULT_FAX)
            .address(DEFAULT_ADDRESS)
            .mail(DEFAULT_MAIL)
            .subjectList(DEFAULT_SUBJECT_LIST)
            .remark(DEFAULT_REMARK)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return cmsComp;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CmsComp createUpdatedEntity(EntityManager em) {
        CmsComp cmsComp = new CmsComp()
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .useStatus(UPDATED_USE_STATUS)
            .chairman(UPDATED_CHAIRMAN)
            .secretaryGeneral(UPDATED_SECRETARY_GENERAL)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .chairmanTel(UPDATED_CHAIRMAN_TEL)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .mail(UPDATED_MAIL)
            .subjectList(UPDATED_SUBJECT_LIST)
            .remark(UPDATED_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        return cmsComp;
    }

    @BeforeEach
    public void initTest() {
        cmsComp = createEntity(em);
    }

    @Test
    @Transactional
    void createCmsComp() throws Exception {
        int databaseSizeBeforeCreate = cmsCompRepository.findAll().size();
        // Create the CmsComp
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);
        restCmsCompMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cmsCompDTO)))
            .andExpect(status().isCreated());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeCreate + 1);
        CmsComp testCmsComp = cmsCompList.get(cmsCompList.size() - 1);
        assertThat(testCmsComp.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCmsComp.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCmsComp.getUseStatus()).isEqualTo(DEFAULT_USE_STATUS);
        assertThat(testCmsComp.getChairman()).isEqualTo(DEFAULT_CHAIRMAN);
        assertThat(testCmsComp.getSecretaryGeneral()).isEqualTo(DEFAULT_SECRETARY_GENERAL);
        assertThat(testCmsComp.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testCmsComp.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testCmsComp.getChairmanTel()).isEqualTo(DEFAULT_CHAIRMAN_TEL);
        assertThat(testCmsComp.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testCmsComp.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCmsComp.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testCmsComp.getSubjectList()).isEqualTo(DEFAULT_SUBJECT_LIST);
        assertThat(testCmsComp.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testCmsComp.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testCmsComp.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    void createCmsCompWithExistingId() throws Exception {
        // Create the CmsComp with an existing ID
        cmsComp.setId("existing_id");
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        int databaseSizeBeforeCreate = cmsCompRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCmsCompMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cmsCompDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cmsCompRepository.findAll().size();
        // set the field null
        cmsComp.setType(null);

        // Create the CmsComp, which fails.
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        restCmsCompMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cmsCompDTO)))
            .andExpect(status().isBadRequest());

        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cmsCompRepository.findAll().size();
        // set the field null
        cmsComp.setName(null);

        // Create the CmsComp, which fails.
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        restCmsCompMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cmsCompDTO)))
            .andExpect(status().isBadRequest());

        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUseStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cmsCompRepository.findAll().size();
        // set the field null
        cmsComp.setUseStatus(null);

        // Create the CmsComp, which fails.
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        restCmsCompMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cmsCompDTO)))
            .andExpect(status().isBadRequest());

        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCmsComps() throws Exception {
        // Initialize the database
        cmsCompRepository.saveAndFlush(cmsComp);

        // Get all the cmsCompList
        restCmsCompMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cmsComp.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].useStatus").value(hasItem(DEFAULT_USE_STATUS)))
            .andExpect(jsonPath("$.[*].chairman").value(hasItem(DEFAULT_CHAIRMAN)))
            .andExpect(jsonPath("$.[*].secretaryGeneral").value(hasItem(DEFAULT_SECRETARY_GENERAL)))
            .andExpect(jsonPath("$.[*].tel1").value(hasItem(DEFAULT_TEL_1)))
            .andExpect(jsonPath("$.[*].tel2").value(hasItem(DEFAULT_TEL_2)))
            .andExpect(jsonPath("$.[*].chairmanTel").value(hasItem(DEFAULT_CHAIRMAN_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].subjectList").value(hasItem(DEFAULT_SUBJECT_LIST)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }

    @Test
    @Transactional
    void getCmsComp() throws Exception {
        // Initialize the database
        cmsCompRepository.saveAndFlush(cmsComp);

        // Get the cmsComp
        restCmsCompMockMvc
            .perform(get(ENTITY_API_URL_ID, cmsComp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cmsComp.getId()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.useStatus").value(DEFAULT_USE_STATUS))
            .andExpect(jsonPath("$.chairman").value(DEFAULT_CHAIRMAN))
            .andExpect(jsonPath("$.secretaryGeneral").value(DEFAULT_SECRETARY_GENERAL))
            .andExpect(jsonPath("$.tel1").value(DEFAULT_TEL_1))
            .andExpect(jsonPath("$.tel2").value(DEFAULT_TEL_2))
            .andExpect(jsonPath("$.chairmanTel").value(DEFAULT_CHAIRMAN_TEL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL))
            .andExpect(jsonPath("$.subjectList").value(DEFAULT_SUBJECT_LIST))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCmsComp() throws Exception {
        // Get the cmsComp
        restCmsCompMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCmsComp() throws Exception {
        // Initialize the database
        cmsCompRepository.saveAndFlush(cmsComp);

        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();

        // Update the cmsComp
        CmsComp updatedCmsComp = cmsCompRepository.findById(cmsComp.getId()).get();
        // Disconnect from session so that the updates on updatedCmsComp are not directly saved in db
        em.detach(updatedCmsComp);
        updatedCmsComp
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .useStatus(UPDATED_USE_STATUS)
            .chairman(UPDATED_CHAIRMAN)
            .secretaryGeneral(UPDATED_SECRETARY_GENERAL)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .chairmanTel(UPDATED_CHAIRMAN_TEL)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .mail(UPDATED_MAIL)
            .subjectList(UPDATED_SUBJECT_LIST)
            .remark(UPDATED_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(updatedCmsComp);

        restCmsCompMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cmsCompDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cmsCompDTO))
            )
            .andExpect(status().isOk());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
        CmsComp testCmsComp = cmsCompList.get(cmsCompList.size() - 1);
        assertThat(testCmsComp.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCmsComp.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCmsComp.getUseStatus()).isEqualTo(UPDATED_USE_STATUS);
        assertThat(testCmsComp.getChairman()).isEqualTo(UPDATED_CHAIRMAN);
        assertThat(testCmsComp.getSecretaryGeneral()).isEqualTo(UPDATED_SECRETARY_GENERAL);
        assertThat(testCmsComp.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testCmsComp.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testCmsComp.getChairmanTel()).isEqualTo(UPDATED_CHAIRMAN_TEL);
        assertThat(testCmsComp.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testCmsComp.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCmsComp.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testCmsComp.getSubjectList()).isEqualTo(UPDATED_SUBJECT_LIST);
        assertThat(testCmsComp.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCmsComp.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testCmsComp.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    void putNonExistingCmsComp() throws Exception {
        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();
        cmsComp.setId(UUID.randomUUID().toString());

        // Create the CmsComp
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCmsCompMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cmsCompDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cmsCompDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCmsComp() throws Exception {
        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();
        cmsComp.setId(UUID.randomUUID().toString());

        // Create the CmsComp
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCmsCompMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cmsCompDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCmsComp() throws Exception {
        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();
        cmsComp.setId(UUID.randomUUID().toString());

        // Create the CmsComp
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCmsCompMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cmsCompDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCmsCompWithPatch() throws Exception {
        // Initialize the database
        cmsCompRepository.saveAndFlush(cmsComp);

        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();

        // Update the cmsComp using partial update
        CmsComp partialUpdatedCmsComp = new CmsComp();
        partialUpdatedCmsComp.setId(cmsComp.getId());

        partialUpdatedCmsComp
            .type(UPDATED_TYPE)
            .chairman(UPDATED_CHAIRMAN)
            .secretaryGeneral(UPDATED_SECRETARY_GENERAL)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .chairmanTel(UPDATED_CHAIRMAN_TEL)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .mail(UPDATED_MAIL)
            .subjectList(UPDATED_SUBJECT_LIST)
            .remark(UPDATED_REMARK);

        restCmsCompMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCmsComp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCmsComp))
            )
            .andExpect(status().isOk());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
        CmsComp testCmsComp = cmsCompList.get(cmsCompList.size() - 1);
        assertThat(testCmsComp.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCmsComp.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCmsComp.getUseStatus()).isEqualTo(DEFAULT_USE_STATUS);
        assertThat(testCmsComp.getChairman()).isEqualTo(UPDATED_CHAIRMAN);
        assertThat(testCmsComp.getSecretaryGeneral()).isEqualTo(UPDATED_SECRETARY_GENERAL);
        assertThat(testCmsComp.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testCmsComp.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testCmsComp.getChairmanTel()).isEqualTo(UPDATED_CHAIRMAN_TEL);
        assertThat(testCmsComp.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testCmsComp.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCmsComp.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testCmsComp.getSubjectList()).isEqualTo(UPDATED_SUBJECT_LIST);
        assertThat(testCmsComp.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCmsComp.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testCmsComp.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    void fullUpdateCmsCompWithPatch() throws Exception {
        // Initialize the database
        cmsCompRepository.saveAndFlush(cmsComp);

        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();

        // Update the cmsComp using partial update
        CmsComp partialUpdatedCmsComp = new CmsComp();
        partialUpdatedCmsComp.setId(cmsComp.getId());

        partialUpdatedCmsComp
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .useStatus(UPDATED_USE_STATUS)
            .chairman(UPDATED_CHAIRMAN)
            .secretaryGeneral(UPDATED_SECRETARY_GENERAL)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .chairmanTel(UPDATED_CHAIRMAN_TEL)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .mail(UPDATED_MAIL)
            .subjectList(UPDATED_SUBJECT_LIST)
            .remark(UPDATED_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);

        restCmsCompMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCmsComp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCmsComp))
            )
            .andExpect(status().isOk());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
        CmsComp testCmsComp = cmsCompList.get(cmsCompList.size() - 1);
        assertThat(testCmsComp.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCmsComp.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCmsComp.getUseStatus()).isEqualTo(UPDATED_USE_STATUS);
        assertThat(testCmsComp.getChairman()).isEqualTo(UPDATED_CHAIRMAN);
        assertThat(testCmsComp.getSecretaryGeneral()).isEqualTo(UPDATED_SECRETARY_GENERAL);
        assertThat(testCmsComp.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testCmsComp.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testCmsComp.getChairmanTel()).isEqualTo(UPDATED_CHAIRMAN_TEL);
        assertThat(testCmsComp.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testCmsComp.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCmsComp.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testCmsComp.getSubjectList()).isEqualTo(UPDATED_SUBJECT_LIST);
        assertThat(testCmsComp.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCmsComp.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testCmsComp.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingCmsComp() throws Exception {
        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();
        cmsComp.setId(UUID.randomUUID().toString());

        // Create the CmsComp
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCmsCompMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cmsCompDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cmsCompDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCmsComp() throws Exception {
        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();
        cmsComp.setId(UUID.randomUUID().toString());

        // Create the CmsComp
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCmsCompMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cmsCompDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCmsComp() throws Exception {
        int databaseSizeBeforeUpdate = cmsCompRepository.findAll().size();
        cmsComp.setId(UUID.randomUUID().toString());

        // Create the CmsComp
        CmsCompDTO cmsCompDTO = cmsCompMapper.toDto(cmsComp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCmsCompMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(cmsCompDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CmsComp in the database
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCmsComp() throws Exception {
        // Initialize the database
        cmsCompRepository.saveAndFlush(cmsComp);

        int databaseSizeBeforeDelete = cmsCompRepository.findAll().size();

        // Delete the cmsComp
        restCmsCompMockMvc
            .perform(delete(ENTITY_API_URL_ID, cmsComp.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CmsComp> cmsCompList = cmsCompRepository.findAll();
        assertThat(cmsCompList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
