package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwcDownloadRole;
import gov.pcc.pwc.repository.PwcDownloadRoleRepository;
import gov.pcc.pwc.service.dto.PwcDownloadRoleDTO;
import gov.pcc.pwc.service.mapper.PwcDownloadRoleMapper;
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
 * Integration tests for the {@link PwcDownloadRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwcDownloadRoleResourceIT {

    private static final Long DEFAULT_PRO_Download_ID = 1L;
    private static final Long UPDATED_PRO_Download_ID = 2L;

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pwc-download-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proDownloadRoleId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwcDownloadRoleRepository pwcDownloadRoleRepository;

    @Autowired
    private PwcDownloadRoleMapper pwcDownloadRoleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwcDownloadRoleMockMvc;

    private PwcDownloadRole pwcDownloadRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcDownloadRole createEntity(EntityManager em) {
        PwcDownloadRole pwcDownloadRole = new PwcDownloadRole()
            .proDownloadId(DEFAULT_PRO_Download_ID)
            .role(DEFAULT_ROLE)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER);
        return pwcDownloadRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcDownloadRole createUpdatedEntity(EntityManager em) {
        PwcDownloadRole pwcDownloadRole = new PwcDownloadRole()
            .proDownloadId(UPDATED_PRO_Download_ID)
            .role(UPDATED_ROLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        return pwcDownloadRole;
    }

    @BeforeEach
    public void initTest() {
        pwcDownloadRole = createEntity(em);
    }

    @Test
    @Transactional
    void createPwcDownloadRole() throws Exception {
        int databaseSizeBeforeCreate = pwcDownloadRoleRepository.findAll().size();
        // Create the PwcDownloadRole
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(pwcDownloadRole);
        restPwcDownloadRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeCreate + 1);
        PwcDownloadRole testPwcDownloadRole = pwcDownloadRoleList.get(pwcDownloadRoleList.size() - 1);
        assertThat(testPwcDownloadRole.getProDownloadId()).isEqualTo(DEFAULT_PRO_Download_ID);
        assertThat(testPwcDownloadRole.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testPwcDownloadRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcDownloadRole.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcDownloadRole.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcDownloadRole.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void createPwcDownloadRoleWithExistingId() throws Exception {
        // Create the PwcDownloadRole with an existing ID
        pwcDownloadRole.setProDownloadRoleId(1L);
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(pwcDownloadRole);

        int databaseSizeBeforeCreate = pwcDownloadRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwcDownloadRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPwcDownloadRoles() throws Exception {
        // Initialize the database
        pwcDownloadRoleRepository.saveAndFlush(pwcDownloadRole);

        // Get all the pwcDownloadRoleList
        restPwcDownloadRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proDownloadRoleId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proDownloadRoleId").value(hasItem(pwcDownloadRole.getProDownloadRoleId().intValue())))
            .andExpect(jsonPath("$.[*].proDownloadId").value(hasItem(DEFAULT_PRO_Download_ID.intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)));
    }

    @Test
    @Transactional
    void getPwcDownloadRole() throws Exception {
        // Initialize the database
        pwcDownloadRoleRepository.saveAndFlush(pwcDownloadRole);

        // Get the pwcDownloadRole
        restPwcDownloadRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, pwcDownloadRole.getProDownloadRoleId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proDownloadRoleId").value(pwcDownloadRole.getProDownloadRoleId().intValue()))
            .andExpect(jsonPath("$.proDownloadId").value(DEFAULT_PRO_Download_ID.intValue()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingPwcDownloadRole() throws Exception {
        // Get the pwcDownloadRole
        restPwcDownloadRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwcDownloadRole() throws Exception {
        // Initialize the database
        pwcDownloadRoleRepository.saveAndFlush(pwcDownloadRole);

        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();

        // Update the pwcDownloadRole
        PwcDownloadRole updatedPwcDownloadRole = pwcDownloadRoleRepository.findById(pwcDownloadRole.getProDownloadRoleId()).get();
        // Disconnect from session so that the updates on updatedPwcDownloadRole are not directly saved in db
        em.detach(updatedPwcDownloadRole);
        updatedPwcDownloadRole
            .proDownloadId(UPDATED_PRO_Download_ID)
            .role(UPDATED_ROLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(updatedPwcDownloadRole);

        restPwcDownloadRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcDownloadRoleDTO.getProDownloadRoleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadRole testPwcDownloadRole = pwcDownloadRoleList.get(pwcDownloadRoleList.size() - 1);
        assertThat(testPwcDownloadRole.getProDownloadId()).isEqualTo(UPDATED_PRO_Download_ID);
        assertThat(testPwcDownloadRole.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testPwcDownloadRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownloadRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownloadRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownloadRole.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingPwcDownloadRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();
        pwcDownloadRole.setProDownloadRoleId(count.incrementAndGet());

        // Create the PwcDownloadRole
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(pwcDownloadRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcDownloadRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcDownloadRoleDTO.getProDownloadRoleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwcDownloadRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();
        pwcDownloadRole.setProDownloadRoleId(count.incrementAndGet());

        // Create the PwcDownloadRole
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(pwcDownloadRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwcDownloadRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();
        pwcDownloadRole.setProDownloadRoleId(count.incrementAndGet());

        // Create the PwcDownloadRole
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(pwcDownloadRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadRoleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwcDownloadRoleWithPatch() throws Exception {
        // Initialize the database
        pwcDownloadRoleRepository.saveAndFlush(pwcDownloadRole);

        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();

        // Update the pwcDownloadRole using partial update
        PwcDownloadRole partialUpdatedPwcDownloadRole = new PwcDownloadRole();
        partialUpdatedPwcDownloadRole.setProDownloadRoleId(pwcDownloadRole.getProDownloadRoleId());

        partialUpdatedPwcDownloadRole
            .proDownloadId(UPDATED_PRO_Download_ID)
            .role(UPDATED_ROLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwcDownloadRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcDownloadRole.getProDownloadRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcDownloadRole))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadRole testPwcDownloadRole = pwcDownloadRoleList.get(pwcDownloadRoleList.size() - 1);
        assertThat(testPwcDownloadRole.getProDownloadId()).isEqualTo(UPDATED_PRO_Download_ID);
        assertThat(testPwcDownloadRole.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testPwcDownloadRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownloadRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownloadRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownloadRole.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void fullUpdatePwcDownloadRoleWithPatch() throws Exception {
        // Initialize the database
        pwcDownloadRoleRepository.saveAndFlush(pwcDownloadRole);

        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();

        // Update the pwcDownloadRole using partial update
        PwcDownloadRole partialUpdatedPwcDownloadRole = new PwcDownloadRole();
        partialUpdatedPwcDownloadRole.setProDownloadRoleId(pwcDownloadRole.getProDownloadRoleId());

        partialUpdatedPwcDownloadRole
            .proDownloadId(UPDATED_PRO_Download_ID)
            .role(UPDATED_ROLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwcDownloadRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcDownloadRole.getProDownloadRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcDownloadRole))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadRole testPwcDownloadRole = pwcDownloadRoleList.get(pwcDownloadRoleList.size() - 1);
        assertThat(testPwcDownloadRole.getProDownloadId()).isEqualTo(UPDATED_PRO_Download_ID);
        assertThat(testPwcDownloadRole.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testPwcDownloadRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownloadRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownloadRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownloadRole.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingPwcDownloadRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();
        pwcDownloadRole.setProDownloadRoleId(count.incrementAndGet());

        // Create the PwcDownloadRole
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(pwcDownloadRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcDownloadRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwcDownloadRoleDTO.getProDownloadRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwcDownloadRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();
        pwcDownloadRole.setProDownloadRoleId(count.incrementAndGet());

        // Create the PwcDownloadRole
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(pwcDownloadRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwcDownloadRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadRoleRepository.findAll().size();
        pwcDownloadRole.setProDownloadRoleId(count.incrementAndGet());

        // Create the PwcDownloadRole
        PwcDownloadRoleDTO pwcDownloadRoleDTO = pwcDownloadRoleMapper.toDto(pwcDownloadRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadRoleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcDownloadRole in the database
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwcDownloadRole() throws Exception {
        // Initialize the database
        pwcDownloadRoleRepository.saveAndFlush(pwcDownloadRole);

        int databaseSizeBeforeDelete = pwcDownloadRoleRepository.findAll().size();

        // Delete the pwcDownloadRole
        restPwcDownloadRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwcDownloadRole.getProDownloadRoleId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwcDownloadRole> pwcDownloadRoleList = pwcDownloadRoleRepository.findAll();
        assertThat(pwcDownloadRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
