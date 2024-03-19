package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmRole;
import gov.pcc.pwc.repository.AdmRoleRepository;
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
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdmRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmRoleResourceIT {

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_DESCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_DESCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{roleId}";

    @Autowired
    private AdmRoleRepository admRoleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmRoleMockMvc;

    private AdmRole admRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmRole createEntity(EntityManager em) {
        AdmRole admRole = new AdmRole();
        admRole.setRoleName(DEFAULT_ROLE_NAME);
        admRole.setRoleDescript(DEFAULT_ROLE_DESCRIPT);
        admRole.setBusinessCategory(DEFAULT_BUSINESS_CATEGORY);
        admRole.setUpdateUser(DEFAULT_UPDATE_USER);
        admRole.setUpdateTime(DEFAULT_UPDATE_TIME);
        return admRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmRole createUpdatedEntity(EntityManager em) {
        AdmRole admRole = new AdmRole();
        admRole.setRoleName(UPDATED_ROLE_NAME);
        admRole.setRoleDescript(UPDATED_ROLE_DESCRIPT);
        admRole.setBusinessCategory(UPDATED_BUSINESS_CATEGORY);
        admRole.setUpdateUser(UPDATED_UPDATE_USER);
        admRole.setUpdateTime(UPDATED_UPDATE_TIME);;
        return admRole;
    }

    @BeforeEach
    public void initTest() {
        admRole = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmRole() throws Exception {
        int databaseSizeBeforeCreate = admRoleRepository.findAll().size();
        // Create the AdmRole
        restAdmRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isCreated());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeCreate + 1);
        AdmRole testAdmRole = admRoleList.get(admRoleList.size() - 1);
        assertThat(testAdmRole.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testAdmRole.getRoleDescript()).isEqualTo(DEFAULT_ROLE_DESCRIPT);
        assertThat(testAdmRole.getBusinessCategory()).isEqualTo(DEFAULT_BUSINESS_CATEGORY);
        assertThat(testAdmRole.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmRoleWithExistingId() throws Exception {
        // Create the AdmRole with an existing ID
        admRole.setRoleId("existing_id");

        int databaseSizeBeforeCreate = admRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isBadRequest());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRoleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = admRoleRepository.findAll().size();
        // set the field null
        admRole.setRoleName(null);

        // Create the AdmRole, which fails.

        restAdmRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isBadRequest());

        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRoleDescriptIsRequired() throws Exception {
        int databaseSizeBeforeTest = admRoleRepository.findAll().size();
        // set the field null
        admRole.setRoleDescript(null);

        // Create the AdmRole, which fails.

        restAdmRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isBadRequest());

        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBusinessCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admRoleRepository.findAll().size();
        // set the field null
        admRole.setBusinessCategory(null);

        // Create the AdmRole, which fails.

        restAdmRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isBadRequest());

        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admRoleRepository.findAll().size();
        // set the field null
        admRole.setUpdateUser(null);

        // Create the AdmRole, which fails.

        restAdmRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isBadRequest());

        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admRoleRepository.findAll().size();
        // set the field null
        admRole.setUpdateTime(null);

        // Create the AdmRole, which fails.

        restAdmRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isBadRequest());

        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmRoles() throws Exception {
        // Initialize the database
        admRoleRepository.saveAndFlush(admRole);

        // Get all the admRoleList
        restAdmRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=roleId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(admRole.getRoleId())))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME)))
            .andExpect(jsonPath("$.[*].roleDescript").value(hasItem(DEFAULT_ROLE_DESCRIPT)))
            .andExpect(jsonPath("$.[*].businessCategory").value(hasItem(DEFAULT_BUSINESS_CATEGORY)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmRole() throws Exception {
        // Initialize the database
        admRoleRepository.saveAndFlush(admRole);

        // Get the admRole
        restAdmRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, admRole.getRoleId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.roleId").value(admRole.getRoleId()))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME))
            .andExpect(jsonPath("$.roleDescript").value(DEFAULT_ROLE_DESCRIPT))
            .andExpect(jsonPath("$.businessCategory").value(DEFAULT_BUSINESS_CATEGORY))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmRole() throws Exception {
        // Get the admRole
        restAdmRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmRole() throws Exception {
        // Initialize the database
        admRoleRepository.saveAndFlush(admRole);

        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();

        // Update the admRole
        AdmRole updatedAdmRole = admRoleRepository.findById(admRole.getRoleId()).get();
        // Disconnect from session so that the updates on updatedAdmRole are not directly saved in db
        em.detach(updatedAdmRole);
        updatedAdmRole.setRoleName(UPDATED_ROLE_NAME);
        updatedAdmRole.setRoleDescript(UPDATED_ROLE_DESCRIPT);
        updatedAdmRole.setBusinessCategory(UPDATED_BUSINESS_CATEGORY);
        updatedAdmRole.setUpdateUser(UPDATED_UPDATE_USER);
        updatedAdmRole.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmRole.getRoleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmRole))
            )
            .andExpect(status().isOk());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
        AdmRole testAdmRole = admRoleList.get(admRoleList.size() - 1);
        assertThat(testAdmRole.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testAdmRole.getRoleDescript()).isEqualTo(UPDATED_ROLE_DESCRIPT);
        assertThat(testAdmRole.getBusinessCategory()).isEqualTo(UPDATED_BUSINESS_CATEGORY);
        assertThat(testAdmRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmRole() throws Exception {
        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();
        admRole.setRoleId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admRole.getRoleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admRole))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmRole() throws Exception {
        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();
        admRole.setRoleId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admRole))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmRole() throws Exception {
        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();
        admRole.setRoleId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmRoleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmRoleWithPatch() throws Exception {
        // Initialize the database
        admRoleRepository.saveAndFlush(admRole);

        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();

        // Update the admRole using partial update
        AdmRole partialUpdatedAdmRole = new AdmRole();
        partialUpdatedAdmRole.setRoleId(admRole.getRoleId());

        partialUpdatedAdmRole.setRoleDescript(UPDATED_ROLE_DESCRIPT);
        partialUpdatedAdmRole.setBusinessCategory(UPDATED_BUSINESS_CATEGORY);
        partialUpdatedAdmRole.setUpdateUser(UPDATED_UPDATE_USER);
        partialUpdatedAdmRole.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmRole.getRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmRole))
            )
            .andExpect(status().isOk());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
        AdmRole testAdmRole = admRoleList.get(admRoleList.size() - 1);
        assertThat(testAdmRole.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testAdmRole.getRoleDescript()).isEqualTo(UPDATED_ROLE_DESCRIPT);
        assertThat(testAdmRole.getBusinessCategory()).isEqualTo(UPDATED_BUSINESS_CATEGORY);
        assertThat(testAdmRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmRoleWithPatch() throws Exception {
        // Initialize the database
        admRoleRepository.saveAndFlush(admRole);

        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();

        // Update the admRole using partial update
        AdmRole partialUpdatedAdmRole = new AdmRole();
        partialUpdatedAdmRole.setRoleId(admRole.getRoleId());

        partialUpdatedAdmRole.setRoleName(UPDATED_ROLE_NAME);
        partialUpdatedAdmRole.setRoleDescript(UPDATED_ROLE_DESCRIPT);
        partialUpdatedAdmRole.setBusinessCategory(UPDATED_BUSINESS_CATEGORY);
        partialUpdatedAdmRole.setUpdateUser(UPDATED_UPDATE_USER);
        partialUpdatedAdmRole.setUpdateTime(UPDATED_UPDATE_TIME);

        restAdmRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmRole.getRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmRole))
            )
            .andExpect(status().isOk());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
        AdmRole testAdmRole = admRoleList.get(admRoleList.size() - 1);
        assertThat(testAdmRole.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testAdmRole.getRoleDescript()).isEqualTo(UPDATED_ROLE_DESCRIPT);
        assertThat(testAdmRole.getBusinessCategory()).isEqualTo(UPDATED_BUSINESS_CATEGORY);
        assertThat(testAdmRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmRole() throws Exception {
        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();
        admRole.setRoleId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admRole.getRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admRole))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmRole() throws Exception {
        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();
        admRole.setRoleId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admRole))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmRole() throws Exception {
        int databaseSizeBeforeUpdate = admRoleRepository.findAll().size();
        admRole.setRoleId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmRoleMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admRole)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmRole in the database
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmRole() throws Exception {
        // Initialize the database
        admRoleRepository.saveAndFlush(admRole);

        int databaseSizeBeforeDelete = admRoleRepository.findAll().size();

        // Delete the admRole
        restAdmRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, admRole.getRoleId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmRole> admRoleList = admRoleRepository.findAll();
        assertThat(admRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
