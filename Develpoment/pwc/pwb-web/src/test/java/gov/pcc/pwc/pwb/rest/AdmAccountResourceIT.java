package gov.pcc.pwc.pwb.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmAccount;
import gov.pcc.pwc.repository.AdmAccountRepository;
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
 * Integration tests for the {@link AdmAccountResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmAccountResourceIT {

    private static final String DEFAULT_PCC_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_PCC_ORG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COMP_IDNO = "AAAAAAAA";
    private static final String UPDATED_COMP_IDNO = "BBBBBBBB";

    private static final Long DEFAULT_NO = 1L;
    private static final Long UPDATED_NO = 2L;

    private static final String DEFAULT_USER_PAD_HASH = "AAAAAAAAAA";
    private static final String UPDATED_USER_PAD_HASH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final String DEFAULT_USER_TYPE = "A";
    private static final String UPDATED_USER_TYPE = "B";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_PAD_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAD_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{userId}";

    @Autowired
    private AdmAccountRepository admAccountRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmAccountMockMvc;

    private AdmAccount admAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmAccount createEntity(EntityManager em) {
        AdmAccount admAccount = new AdmAccount();
        admAccount.setPccOrgId(DEFAULT_PCC_ORG_ID);
        admAccount.setCompIdno(DEFAULT_COMP_IDNO);
        admAccount.setNo(DEFAULT_NO);
        admAccount.setUserPadHash(DEFAULT_USER_PAD_HASH);
        admAccount.setStatus(DEFAULT_STATUS);
        admAccount.setUserType(DEFAULT_USER_TYPE);
        admAccount.setUpdateUser(DEFAULT_UPDATE_USER);
        admAccount.setPadUpdateTime(DEFAULT_PAD_UPDATE_TIME);
        admAccount.setUpdateTime(DEFAULT_UPDATE_TIME);
        return admAccount;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmAccount createUpdatedEntity(EntityManager em) {
        AdmAccount admAccount = new AdmAccount();
        admAccount.setPccOrgId(UPDATED_PCC_ORG_ID);
        admAccount.setCompIdno(UPDATED_COMP_IDNO);
        admAccount.setNo(UPDATED_NO);
        admAccount.setUserPadHash(UPDATED_USER_PAD_HASH);
        admAccount.setStatus(UPDATED_STATUS);
        admAccount.setUserType(UPDATED_USER_TYPE);
        admAccount.setUpdateUser(UPDATED_UPDATE_USER);
        admAccount.setPadUpdateTime(UPDATED_PAD_UPDATE_TIME);
        admAccount.setUpdateTime(UPDATED_UPDATE_TIME);
        return admAccount;
    }

    @BeforeEach
    public void initTest() {
        admAccount = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmAccount() throws Exception {
        int databaseSizeBeforeCreate = admAccountRepository.findAll().size();
        // Create the AdmAccount
        restAdmAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccount)))
            .andExpect(status().isCreated());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeCreate + 1);
        AdmAccount testAdmAccount = admAccountList.get(admAccountList.size() - 1);
        assertThat(testAdmAccount.getPccOrgId()).isEqualTo(DEFAULT_PCC_ORG_ID);
        assertThat(testAdmAccount.getCompIdno()).isEqualTo(DEFAULT_COMP_IDNO);
        assertThat(testAdmAccount.getNo()).isEqualTo(DEFAULT_NO);
        assertThat(testAdmAccount.getUserPadHash()).isEqualTo(DEFAULT_USER_PAD_HASH);
        assertThat(testAdmAccount.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmAccount.getUserType()).isEqualTo(DEFAULT_USER_TYPE);
        assertThat(testAdmAccount.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmAccount.getPadUpdateTime()).isEqualTo(DEFAULT_PAD_UPDATE_TIME);
        assertThat(testAdmAccount.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmAccountWithExistingId() throws Exception {
        // Create the AdmAccount with an existing ID
        admAccount.setUserId("existing_id");

        int databaseSizeBeforeCreate = admAccountRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccount)))
            .andExpect(status().isBadRequest());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAccountRepository.findAll().size();
        // set the field null
        admAccount.setUpdateUser(null);

        // Create the AdmAccount, which fails.

        restAdmAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccount)))
            .andExpect(status().isBadRequest());

        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPadUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAccountRepository.findAll().size();
        // set the field null
        admAccount.setPadUpdateTime(null);

        // Create the AdmAccount, which fails.

        restAdmAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccount)))
            .andExpect(status().isBadRequest());

        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAccountRepository.findAll().size();
        // set the field null
        admAccount.setUpdateTime(null);

        // Create the AdmAccount, which fails.

        restAdmAccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccount)))
            .andExpect(status().isBadRequest());

        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmAccounts() throws Exception {
        // Initialize the database
        admAccountRepository.saveAndFlush(admAccount);

        // Get all the admAccountList
        restAdmAccountMockMvc
            .perform(get(ENTITY_API_URL + "?sort=userId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(admAccount.getUserId())))
            .andExpect(jsonPath("$.[*].pccOrgId").value(hasItem(DEFAULT_PCC_ORG_ID)))
            .andExpect(jsonPath("$.[*].compIdno").value(hasItem(DEFAULT_COMP_IDNO)))
            .andExpect(jsonPath("$.[*].no").value(hasItem(DEFAULT_NO.intValue())))
            .andExpect(jsonPath("$.[*].userPadHash").value(hasItem(DEFAULT_USER_PAD_HASH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].userType").value(hasItem(DEFAULT_USER_TYPE)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].padUpdateTime").value(hasItem(DEFAULT_PAD_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmAccount() throws Exception {
        // Initialize the database
        admAccountRepository.saveAndFlush(admAccount);

        // Get the admAccount
        restAdmAccountMockMvc
            .perform(get(ENTITY_API_URL_ID, admAccount.getUserId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.userId").value(admAccount.getUserId()))
            .andExpect(jsonPath("$.pccOrgId").value(DEFAULT_PCC_ORG_ID))
            .andExpect(jsonPath("$.compIdno").value(DEFAULT_COMP_IDNO))
            .andExpect(jsonPath("$.no").value(DEFAULT_NO.intValue()))
            .andExpect(jsonPath("$.userPadHash").value(DEFAULT_USER_PAD_HASH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.userType").value(DEFAULT_USER_TYPE))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.padUpdateTime").value(DEFAULT_PAD_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmAccount() throws Exception {
        // Get the admAccount
        restAdmAccountMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmAccount() throws Exception {
        // Initialize the database
        admAccountRepository.saveAndFlush(admAccount);

        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();

        // Update the admAccount
        AdmAccount updatedAdmAccount = admAccountRepository.findById(admAccount.getUserId()).get();
        // Disconnect from session so that the updates on updatedAdmAccount are not directly saved in db
        em.detach(updatedAdmAccount);
        updatedAdmAccount.setPccOrgId(UPDATED_PCC_ORG_ID);
        updatedAdmAccount.setCompIdno(UPDATED_COMP_IDNO);
        updatedAdmAccount.setNo(UPDATED_NO);
        updatedAdmAccount.setUserPadHash(UPDATED_USER_PAD_HASH);
        updatedAdmAccount.setStatus(UPDATED_STATUS);
        updatedAdmAccount.setUserType(UPDATED_USER_TYPE);
        updatedAdmAccount.setUpdateUser(UPDATED_UPDATE_USER);
        updatedAdmAccount.setPadUpdateTime(UPDATED_PAD_UPDATE_TIME);
        updatedAdmAccount.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmAccount.getUserId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmAccount))
            )
            .andExpect(status().isOk());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
        AdmAccount testAdmAccount = admAccountList.get(admAccountList.size() - 1);
        assertThat(testAdmAccount.getPccOrgId()).isEqualTo(UPDATED_PCC_ORG_ID);
        assertThat(testAdmAccount.getCompIdno()).isEqualTo(UPDATED_COMP_IDNO);
        assertThat(testAdmAccount.getNo()).isEqualTo(UPDATED_NO);
        assertThat(testAdmAccount.getUserPadHash()).isEqualTo(UPDATED_USER_PAD_HASH);
        assertThat(testAdmAccount.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmAccount.getUserType()).isEqualTo(UPDATED_USER_TYPE);
        assertThat(testAdmAccount.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmAccount.getPadUpdateTime()).isEqualTo(UPDATED_PAD_UPDATE_TIME);
        assertThat(testAdmAccount.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmAccount() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();
        admAccount.setUserId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admAccount.getUserId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmAccount() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();
        admAccount.setUserId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmAccount() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();
        admAccount.setUserId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAccountMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccount)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmAccountWithPatch() throws Exception {
        // Initialize the database
        admAccountRepository.saveAndFlush(admAccount);

        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();

        // Update the admAccount using partial update
        AdmAccount partialUpdatedAdmAccount = new AdmAccount();
        partialUpdatedAdmAccount.setUserId(admAccount.getUserId());

        partialUpdatedAdmAccount.setPccOrgId(UPDATED_PCC_ORG_ID);
        partialUpdatedAdmAccount.setCompIdno(UPDATED_COMP_IDNO);
        partialUpdatedAdmAccount.setNo(UPDATED_NO);
        partialUpdatedAdmAccount.setUpdateUser(UPDATED_UPDATE_USER);
        partialUpdatedAdmAccount.setPadUpdateTime(UPDATED_PAD_UPDATE_TIME);

        restAdmAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmAccount.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmAccount))
            )
            .andExpect(status().isOk());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
        AdmAccount testAdmAccount = admAccountList.get(admAccountList.size() - 1);
        assertThat(testAdmAccount.getPccOrgId()).isEqualTo(UPDATED_PCC_ORG_ID);
        assertThat(testAdmAccount.getCompIdno()).isEqualTo(UPDATED_COMP_IDNO);
        assertThat(testAdmAccount.getNo()).isEqualTo(UPDATED_NO);
        assertThat(testAdmAccount.getUserPadHash()).isEqualTo(DEFAULT_USER_PAD_HASH);
        assertThat(testAdmAccount.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdmAccount.getUserType()).isEqualTo(DEFAULT_USER_TYPE);
        assertThat(testAdmAccount.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmAccount.getPadUpdateTime()).isEqualTo(UPDATED_PAD_UPDATE_TIME);
        assertThat(testAdmAccount.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmAccountWithPatch() throws Exception {
        // Initialize the database
        admAccountRepository.saveAndFlush(admAccount);

        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();

        // Update the admAccount using partial update
        AdmAccount partialUpdatedAdmAccount = new AdmAccount();
        partialUpdatedAdmAccount.setUserId(admAccount.getUserId());

        partialUpdatedAdmAccount.setPccOrgId(UPDATED_PCC_ORG_ID);
        partialUpdatedAdmAccount.setCompIdno(UPDATED_COMP_IDNO);
        partialUpdatedAdmAccount.setNo(UPDATED_NO);
        partialUpdatedAdmAccount.setUserPadHash(UPDATED_USER_PAD_HASH);
        partialUpdatedAdmAccount.setStatus(UPDATED_STATUS);
        partialUpdatedAdmAccount.setUserType(UPDATED_USER_TYPE);
        partialUpdatedAdmAccount.setUpdateUser(UPDATED_UPDATE_USER);
        partialUpdatedAdmAccount.setPadUpdateTime(UPDATED_PAD_UPDATE_TIME);
        partialUpdatedAdmAccount.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmAccount.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmAccount))
            )
            .andExpect(status().isOk());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
        AdmAccount testAdmAccount = admAccountList.get(admAccountList.size() - 1);
        assertThat(testAdmAccount.getPccOrgId()).isEqualTo(UPDATED_PCC_ORG_ID);
        assertThat(testAdmAccount.getCompIdno()).isEqualTo(UPDATED_COMP_IDNO);
        assertThat(testAdmAccount.getNo()).isEqualTo(UPDATED_NO);
        assertThat(testAdmAccount.getUserPadHash()).isEqualTo(UPDATED_USER_PAD_HASH);
        assertThat(testAdmAccount.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdmAccount.getUserType()).isEqualTo(UPDATED_USER_TYPE);
        assertThat(testAdmAccount.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmAccount.getPadUpdateTime()).isEqualTo(UPDATED_PAD_UPDATE_TIME);
        assertThat(testAdmAccount.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmAccount() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();
        admAccount.setUserId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admAccount.getUserId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmAccount() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();
        admAccount.setUserId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admAccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmAccount() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRepository.findAll().size();
        admAccount.setUserId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAccountMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admAccount))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmAccount in the database
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmAccount() throws Exception {
        // Initialize the database
        admAccountRepository.saveAndFlush(admAccount);

        int databaseSizeBeforeDelete = admAccountRepository.findAll().size();

        // Delete the admAccount
        restAdmAccountMockMvc
            .perform(delete(ENTITY_API_URL_ID, admAccount.getUserId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmAccount> admAccountList = admAccountRepository.findAll();
        assertThat(admAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
