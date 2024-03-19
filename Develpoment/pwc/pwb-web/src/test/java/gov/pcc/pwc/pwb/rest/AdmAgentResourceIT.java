package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmAgent;
import gov.pcc.pwc.repository.AdmAgentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static gov.pcc.pwc.pwb.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdmAgentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmAgentResourceIT {

    private static final String DEFAULT_PCC_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_PCC_ORG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_ADMIN_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_ADMIN_ORG_ID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ORG_LEVEL = new BigDecimal(0);
    private static final BigDecimal UPDATED_ORG_LEVEL = new BigDecimal(1);

    private static final String DEFAULT_CUT_MARK = "A";
    private static final String UPDATED_CUT_MARK = "B";

    private static final String DEFAULT_OLD_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_OLD_ORG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OLD_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OLD_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-agents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{orgId}";

    @Autowired
    private AdmAgentRepository admAgentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmAgentMockMvc;

    private AdmAgent admAgent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmAgent createEntity(EntityManager em) {
        AdmAgent admAgent = new AdmAgent()
            .pccOrgId(DEFAULT_PCC_ORG_ID)
            .orgName(DEFAULT_ORG_NAME)
            .orgNameEn(DEFAULT_ORG_NAME_EN)
            .addr(DEFAULT_ADDR)
            .tel(DEFAULT_TEL)
            .fax(DEFAULT_FAX)
            .descript(DEFAULT_DESCRIPT)
            .adminOrgId(DEFAULT_ADMIN_ORG_ID)
            .orgLevel(DEFAULT_ORG_LEVEL)
            .cutMark(DEFAULT_CUT_MARK)
            .oldOrgId(DEFAULT_OLD_ORG_ID)
            .oldOrgName(DEFAULT_OLD_ORG_NAME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admAgent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmAgent createUpdatedEntity(EntityManager em) {
        AdmAgent admAgent = new AdmAgent()
            .pccOrgId(UPDATED_PCC_ORG_ID)
            .orgName(UPDATED_ORG_NAME)
            .orgNameEn(UPDATED_ORG_NAME_EN)
            .addr(UPDATED_ADDR)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .descript(UPDATED_DESCRIPT)
            .adminOrgId(UPDATED_ADMIN_ORG_ID)
            .orgLevel(UPDATED_ORG_LEVEL)
            .cutMark(UPDATED_CUT_MARK)
            .oldOrgId(UPDATED_OLD_ORG_ID)
            .oldOrgName(UPDATED_OLD_ORG_NAME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admAgent;
    }

    @BeforeEach
    public void initTest() {
        admAgent = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmAgent() throws Exception {
        int databaseSizeBeforeCreate = admAgentRepository.findAll().size();
        // Create the AdmAgent
        restAdmAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAgent)))
            .andExpect(status().isCreated());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeCreate + 1);
        AdmAgent testAdmAgent = admAgentList.get(admAgentList.size() - 1);
        assertThat(testAdmAgent.getPccOrgId()).isEqualTo(DEFAULT_PCC_ORG_ID);
        assertThat(testAdmAgent.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testAdmAgent.getOrgNameEn()).isEqualTo(DEFAULT_ORG_NAME_EN);
        assertThat(testAdmAgent.getAddr()).isEqualTo(DEFAULT_ADDR);
        assertThat(testAdmAgent.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testAdmAgent.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testAdmAgent.getDescript()).isEqualTo(DEFAULT_DESCRIPT);
        assertThat(testAdmAgent.getAdminOrgId()).isEqualTo(DEFAULT_ADMIN_ORG_ID);
        assertThat(testAdmAgent.getOrgLevel()).isEqualByComparingTo(DEFAULT_ORG_LEVEL);
        assertThat(testAdmAgent.getCutMark()).isEqualTo(DEFAULT_CUT_MARK);
        assertThat(testAdmAgent.getOldOrgId()).isEqualTo(DEFAULT_OLD_ORG_ID);
        assertThat(testAdmAgent.getOldOrgName()).isEqualTo(DEFAULT_OLD_ORG_NAME);
        assertThat(testAdmAgent.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmAgent.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmAgentWithExistingId() throws Exception {
        // Create the AdmAgent with an existing ID
        admAgent.setOrgId("existing_id");

        int databaseSizeBeforeCreate = admAgentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAgent)))
            .andExpect(status().isBadRequest());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPccOrgIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAgentRepository.findAll().size();
        // set the field null
        admAgent.setPccOrgId(null);

        // Create the AdmAgent, which fails.

        restAdmAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAgent)))
            .andExpect(status().isBadRequest());

        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrgNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAgentRepository.findAll().size();
        // set the field null
        admAgent.setOrgName(null);

        // Create the AdmAgent, which fails.

        restAdmAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAgent)))
            .andExpect(status().isBadRequest());

        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAgentRepository.findAll().size();
        // set the field null
        admAgent.setUpdateUser(null);

        // Create the AdmAgent, which fails.

        restAdmAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAgent)))
            .andExpect(status().isBadRequest());

        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAgentRepository.findAll().size();
        // set the field null
        admAgent.setUpdateTime(null);

        // Create the AdmAgent, which fails.

        restAdmAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAgent)))
            .andExpect(status().isBadRequest());

        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmAgents() throws Exception {
        // Initialize the database
        admAgentRepository.saveAndFlush(admAgent);

        // Get all the admAgentList
        restAdmAgentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=orgId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].orgId").value(hasItem(admAgent.getOrgId())))
            .andExpect(jsonPath("$.[*].pccOrgId").value(hasItem(DEFAULT_PCC_ORG_ID)))
            .andExpect(jsonPath("$.[*].orgName").value(hasItem(DEFAULT_ORG_NAME)))
            .andExpect(jsonPath("$.[*].orgNameEn").value(hasItem(DEFAULT_ORG_NAME_EN)))
            .andExpect(jsonPath("$.[*].addr").value(hasItem(DEFAULT_ADDR)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].descript").value(hasItem(DEFAULT_DESCRIPT)))
            .andExpect(jsonPath("$.[*].adminOrgId").value(hasItem(DEFAULT_ADMIN_ORG_ID)))
            .andExpect(jsonPath("$.[*].orgLevel").value(hasItem(sameNumber(DEFAULT_ORG_LEVEL))))
            .andExpect(jsonPath("$.[*].cutMark").value(hasItem(DEFAULT_CUT_MARK)))
            .andExpect(jsonPath("$.[*].oldOrgId").value(hasItem(DEFAULT_OLD_ORG_ID)))
            .andExpect(jsonPath("$.[*].oldOrgName").value(hasItem(DEFAULT_OLD_ORG_NAME)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmAgent() throws Exception {
        // Initialize the database
        admAgentRepository.saveAndFlush(admAgent);

        // Get the admAgent
        restAdmAgentMockMvc
            .perform(get(ENTITY_API_URL_ID, admAgent.getOrgId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.orgId").value(admAgent.getOrgId()))
            .andExpect(jsonPath("$.pccOrgId").value(DEFAULT_PCC_ORG_ID))
            .andExpect(jsonPath("$.orgName").value(DEFAULT_ORG_NAME))
            .andExpect(jsonPath("$.orgNameEn").value(DEFAULT_ORG_NAME_EN))
            .andExpect(jsonPath("$.addr").value(DEFAULT_ADDR))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.descript").value(DEFAULT_DESCRIPT))
            .andExpect(jsonPath("$.adminOrgId").value(DEFAULT_ADMIN_ORG_ID))
            .andExpect(jsonPath("$.orgLevel").value(sameNumber(DEFAULT_ORG_LEVEL)))
            .andExpect(jsonPath("$.cutMark").value(DEFAULT_CUT_MARK))
            .andExpect(jsonPath("$.oldOrgId").value(DEFAULT_OLD_ORG_ID))
            .andExpect(jsonPath("$.oldOrgName").value(DEFAULT_OLD_ORG_NAME))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmAgent() throws Exception {
        // Get the admAgent
        restAdmAgentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmAgent() throws Exception {
        // Initialize the database
        admAgentRepository.saveAndFlush(admAgent);

        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();

        // Update the admAgent
        AdmAgent updatedAdmAgent = admAgentRepository.findById(admAgent.getOrgId()).get();
        // Disconnect from session so that the updates on updatedAdmAgent are not directly saved in db
        em.detach(updatedAdmAgent);
        updatedAdmAgent
            .pccOrgId(UPDATED_PCC_ORG_ID)
            .orgName(UPDATED_ORG_NAME)
            .orgNameEn(UPDATED_ORG_NAME_EN)
            .addr(UPDATED_ADDR)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .descript(UPDATED_DESCRIPT)
            .adminOrgId(UPDATED_ADMIN_ORG_ID)
            .orgLevel(UPDATED_ORG_LEVEL)
            .cutMark(UPDATED_CUT_MARK)
            .oldOrgId(UPDATED_OLD_ORG_ID)
            .oldOrgName(UPDATED_OLD_ORG_NAME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmAgent.getOrgId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmAgent))
            )
            .andExpect(status().isOk());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
        AdmAgent testAdmAgent = admAgentList.get(admAgentList.size() - 1);
        assertThat(testAdmAgent.getPccOrgId()).isEqualTo(UPDATED_PCC_ORG_ID);
        assertThat(testAdmAgent.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testAdmAgent.getOrgNameEn()).isEqualTo(UPDATED_ORG_NAME_EN);
        assertThat(testAdmAgent.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testAdmAgent.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testAdmAgent.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testAdmAgent.getDescript()).isEqualTo(UPDATED_DESCRIPT);
        assertThat(testAdmAgent.getAdminOrgId()).isEqualTo(UPDATED_ADMIN_ORG_ID);
        assertThat(testAdmAgent.getOrgLevel()).isEqualTo(UPDATED_ORG_LEVEL);
        assertThat(testAdmAgent.getCutMark()).isEqualTo(UPDATED_CUT_MARK);
        assertThat(testAdmAgent.getOldOrgId()).isEqualTo(UPDATED_OLD_ORG_ID);
        assertThat(testAdmAgent.getOldOrgName()).isEqualTo(UPDATED_OLD_ORG_NAME);
        assertThat(testAdmAgent.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmAgent.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmAgent() throws Exception {
        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();
        admAgent.setOrgId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admAgent.getOrgId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admAgent))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmAgent() throws Exception {
        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();
        admAgent.setOrgId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admAgent))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmAgent() throws Exception {
        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();
        admAgent.setOrgId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAgentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAgent)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmAgentWithPatch() throws Exception {
        // Initialize the database
        admAgentRepository.saveAndFlush(admAgent);

        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();

        // Update the admAgent using partial update
        AdmAgent partialUpdatedAdmAgent = new AdmAgent();
        partialUpdatedAdmAgent.setOrgId(admAgent.getOrgId());

        partialUpdatedAdmAgent
            .pccOrgId(UPDATED_PCC_ORG_ID)
            .orgName(UPDATED_ORG_NAME)
            .orgNameEn(UPDATED_ORG_NAME_EN)
            .addr(UPDATED_ADDR)
            .fax(UPDATED_FAX)
            .descript(UPDATED_DESCRIPT)
            .adminOrgId(UPDATED_ADMIN_ORG_ID)
            .cutMark(UPDATED_CUT_MARK)
            .oldOrgId(UPDATED_OLD_ORG_ID)
            .oldOrgName(UPDATED_OLD_ORG_NAME);

        restAdmAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmAgent.getOrgId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmAgent))
            )
            .andExpect(status().isOk());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
        AdmAgent testAdmAgent = admAgentList.get(admAgentList.size() - 1);
        assertThat(testAdmAgent.getPccOrgId()).isEqualTo(UPDATED_PCC_ORG_ID);
        assertThat(testAdmAgent.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testAdmAgent.getOrgNameEn()).isEqualTo(UPDATED_ORG_NAME_EN);
        assertThat(testAdmAgent.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testAdmAgent.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testAdmAgent.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testAdmAgent.getDescript()).isEqualTo(UPDATED_DESCRIPT);
        assertThat(testAdmAgent.getAdminOrgId()).isEqualTo(UPDATED_ADMIN_ORG_ID);
        assertThat(testAdmAgent.getOrgLevel()).isEqualByComparingTo(DEFAULT_ORG_LEVEL);
        assertThat(testAdmAgent.getCutMark()).isEqualTo(UPDATED_CUT_MARK);
        assertThat(testAdmAgent.getOldOrgId()).isEqualTo(UPDATED_OLD_ORG_ID);
        assertThat(testAdmAgent.getOldOrgName()).isEqualTo(UPDATED_OLD_ORG_NAME);
        assertThat(testAdmAgent.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmAgent.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmAgentWithPatch() throws Exception {
        // Initialize the database
        admAgentRepository.saveAndFlush(admAgent);

        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();

        // Update the admAgent using partial update
        AdmAgent partialUpdatedAdmAgent = new AdmAgent();
        partialUpdatedAdmAgent.setOrgId(admAgent.getOrgId());

        partialUpdatedAdmAgent
            .pccOrgId(UPDATED_PCC_ORG_ID)
            .orgName(UPDATED_ORG_NAME)
            .orgNameEn(UPDATED_ORG_NAME_EN)
            .addr(UPDATED_ADDR)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .descript(UPDATED_DESCRIPT)
            .adminOrgId(UPDATED_ADMIN_ORG_ID)
            .orgLevel(UPDATED_ORG_LEVEL)
            .cutMark(UPDATED_CUT_MARK)
            .oldOrgId(UPDATED_OLD_ORG_ID)
            .oldOrgName(UPDATED_OLD_ORG_NAME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmAgent.getOrgId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmAgent))
            )
            .andExpect(status().isOk());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
        AdmAgent testAdmAgent = admAgentList.get(admAgentList.size() - 1);
        assertThat(testAdmAgent.getPccOrgId()).isEqualTo(UPDATED_PCC_ORG_ID);
        assertThat(testAdmAgent.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testAdmAgent.getOrgNameEn()).isEqualTo(UPDATED_ORG_NAME_EN);
        assertThat(testAdmAgent.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testAdmAgent.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testAdmAgent.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testAdmAgent.getDescript()).isEqualTo(UPDATED_DESCRIPT);
        assertThat(testAdmAgent.getAdminOrgId()).isEqualTo(UPDATED_ADMIN_ORG_ID);
        assertThat(testAdmAgent.getOrgLevel()).isEqualByComparingTo(UPDATED_ORG_LEVEL);
        assertThat(testAdmAgent.getCutMark()).isEqualTo(UPDATED_CUT_MARK);
        assertThat(testAdmAgent.getOldOrgId()).isEqualTo(UPDATED_OLD_ORG_ID);
        assertThat(testAdmAgent.getOldOrgName()).isEqualTo(UPDATED_OLD_ORG_NAME);
        assertThat(testAdmAgent.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmAgent.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmAgent() throws Exception {
        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();
        admAgent.setOrgId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admAgent.getOrgId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admAgent))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmAgent() throws Exception {
        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();
        admAgent.setOrgId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admAgent))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmAgent() throws Exception {
        int databaseSizeBeforeUpdate = admAgentRepository.findAll().size();
        admAgent.setOrgId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAgentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admAgent)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmAgent in the database
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmAgent() throws Exception {
        // Initialize the database
        admAgentRepository.saveAndFlush(admAgent);

        int databaseSizeBeforeDelete = admAgentRepository.findAll().size();

        // Delete the admAgent
        restAdmAgentMockMvc
            .perform(delete(ENTITY_API_URL_ID, admAgent.getOrgId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmAgent> admAgentList = admAgentRepository.findAll();
        assertThat(admAgentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
