package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmAccountRole;
import gov.pcc.pwc.repository.AdmAccountRoleRepository;
import gov.pcc.pwc.service.dto.AdmAccountRoleDTO;
import gov.pcc.pwc.service.mapper.AdmAccountRoleMapper;
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
 * Integration tests for the {@link AdmAccountRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmAccountRoleResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-account-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmAccountRoleRepository admAccountRoleRepository;

    @Autowired
    private AdmAccountRoleMapper admAccountRoleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmAccountRoleMockMvc;

    private AdmAccountRole admAccountRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmAccountRole createEntity(EntityManager em) {
        AdmAccountRole admAccountRole = new AdmAccountRole()
            .userId(DEFAULT_USER_ID)
            .roleId(DEFAULT_ROLE_ID)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admAccountRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmAccountRole createUpdatedEntity(EntityManager em) {
        AdmAccountRole admAccountRole = new AdmAccountRole()
            .userId(UPDATED_USER_ID)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admAccountRole;
    }

    @BeforeEach
    public void initTest() {
        admAccountRole = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmAccountRole() throws Exception {
        int databaseSizeBeforeCreate = admAccountRoleRepository.findAll().size();
        // Create the AdmAccountRole
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);
        restAdmAccountRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeCreate + 1);
        AdmAccountRole testAdmAccountRole = admAccountRoleList.get(admAccountRoleList.size() - 1);
        assertThat(testAdmAccountRole.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testAdmAccountRole.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testAdmAccountRole.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmAccountRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmAccountRoleWithExistingId() throws Exception {
        // Create the AdmAccountRole with an existing ID
        admAccountRole.setId(1L);
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        int databaseSizeBeforeCreate = admAccountRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmAccountRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAccountRoleRepository.findAll().size();
        // set the field null
        admAccountRole.setUserId(null);

        // Create the AdmAccountRole, which fails.
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        restAdmAccountRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRoleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAccountRoleRepository.findAll().size();
        // set the field null
        admAccountRole.setRoleId(null);

        // Create the AdmAccountRole, which fails.
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        restAdmAccountRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAccountRoleRepository.findAll().size();
        // set the field null
        admAccountRole.setUpdateUser(null);

        // Create the AdmAccountRole, which fails.
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        restAdmAccountRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admAccountRoleRepository.findAll().size();
        // set the field null
        admAccountRole.setUpdateTime(null);

        // Create the AdmAccountRole, which fails.
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        restAdmAccountRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmAccountRoles() throws Exception {
        // Initialize the database
        admAccountRoleRepository.saveAndFlush(admAccountRole);

        // Get all the admAccountRoleList
        restAdmAccountRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admAccountRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmAccountRole() throws Exception {
        // Initialize the database
        admAccountRoleRepository.saveAndFlush(admAccountRole);

        // Get the admAccountRole
        restAdmAccountRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, admAccountRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admAccountRole.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmAccountRole() throws Exception {
        // Get the admAccountRole
        restAdmAccountRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmAccountRole() throws Exception {
        // Initialize the database
        admAccountRoleRepository.saveAndFlush(admAccountRole);

        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();

        // Update the admAccountRole
        AdmAccountRole updatedAdmAccountRole = admAccountRoleRepository.findById(admAccountRole.getId()).get();
        // Disconnect from session so that the updates on updatedAdmAccountRole are not directly saved in db
        em.detach(updatedAdmAccountRole);
        updatedAdmAccountRole
            .userId(UPDATED_USER_ID)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(updatedAdmAccountRole);

        restAdmAccountRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admAccountRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isOk());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
        AdmAccountRole testAdmAccountRole = admAccountRoleList.get(admAccountRoleList.size() - 1);
        assertThat(testAdmAccountRole.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAdmAccountRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testAdmAccountRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmAccountRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmAccountRole() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();
        admAccountRole.setId(count.incrementAndGet());

        // Create the AdmAccountRole
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmAccountRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admAccountRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmAccountRole() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();
        admAccountRole.setId(count.incrementAndGet());

        // Create the AdmAccountRole
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAccountRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmAccountRole() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();
        admAccountRole.setId(count.incrementAndGet());

        // Create the AdmAccountRole
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAccountRoleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmAccountRoleWithPatch() throws Exception {
        // Initialize the database
        admAccountRoleRepository.saveAndFlush(admAccountRole);

        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();

        // Update the admAccountRole using partial update
        AdmAccountRole partialUpdatedAdmAccountRole = new AdmAccountRole();
        partialUpdatedAdmAccountRole.setId(admAccountRole.getId());

        partialUpdatedAdmAccountRole.userId(UPDATED_USER_ID).roleId(UPDATED_ROLE_ID);

        restAdmAccountRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmAccountRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmAccountRole))
            )
            .andExpect(status().isOk());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
        AdmAccountRole testAdmAccountRole = admAccountRoleList.get(admAccountRoleList.size() - 1);
        assertThat(testAdmAccountRole.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAdmAccountRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testAdmAccountRole.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmAccountRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmAccountRoleWithPatch() throws Exception {
        // Initialize the database
        admAccountRoleRepository.saveAndFlush(admAccountRole);

        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();

        // Update the admAccountRole using partial update
        AdmAccountRole partialUpdatedAdmAccountRole = new AdmAccountRole();
        partialUpdatedAdmAccountRole.setId(admAccountRole.getId());

        partialUpdatedAdmAccountRole
            .userId(UPDATED_USER_ID)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmAccountRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmAccountRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmAccountRole))
            )
            .andExpect(status().isOk());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
        AdmAccountRole testAdmAccountRole = admAccountRoleList.get(admAccountRoleList.size() - 1);
        assertThat(testAdmAccountRole.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testAdmAccountRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testAdmAccountRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmAccountRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmAccountRole() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();
        admAccountRole.setId(count.incrementAndGet());

        // Create the AdmAccountRole
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmAccountRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admAccountRoleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmAccountRole() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();
        admAccountRole.setId(count.incrementAndGet());

        // Create the AdmAccountRole
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAccountRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmAccountRole() throws Exception {
        int databaseSizeBeforeUpdate = admAccountRoleRepository.findAll().size();
        admAccountRole.setId(count.incrementAndGet());

        // Create the AdmAccountRole
        AdmAccountRoleDTO admAccountRoleDTO = admAccountRoleMapper.toDto(admAccountRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmAccountRoleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admAccountRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmAccountRole in the database
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmAccountRole() throws Exception {
        // Initialize the database
        admAccountRoleRepository.saveAndFlush(admAccountRole);

        int databaseSizeBeforeDelete = admAccountRoleRepository.findAll().size();

        // Delete the admAccountRole
        restAdmAccountRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, admAccountRole.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmAccountRole> admAccountRoleList = admAccountRoleRepository.findAll();
        assertThat(admAccountRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
