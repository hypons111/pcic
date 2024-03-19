package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwcInformationRole;
import gov.pcc.pwc.repository.PwcInformationRoleRepository;
import gov.pcc.pwc.service.dto.PwcInformationRoleDTO;
import gov.pcc.pwc.service.mapper.PwcInformationRoleMapper;
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
 * Integration tests for the {@link PwcInformationRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwcInformationRoleResourceIT {

    private static final Long DEFAULT_PRO_INFORMATION_ID = 1L;
    private static final Long UPDATED_PRO_INFORMATION_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/pwc-information-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proInformationRoleId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwcInformationRoleRepository pwcInformationRoleRepository;

    @Autowired
    private PwcInformationRoleMapper pwcInformationRoleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwcInformationRoleMockMvc;

    private PwcInformationRole pwcInformationRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcInformationRole createEntity(EntityManager em) {
        PwcInformationRole pwcInformationRole = new PwcInformationRole()
            .proInformationId(DEFAULT_PRO_INFORMATION_ID)
            .role(DEFAULT_ROLE)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER);
        return pwcInformationRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcInformationRole createUpdatedEntity(EntityManager em) {
        PwcInformationRole pwcInformationRole = new PwcInformationRole()
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .role(UPDATED_ROLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        return pwcInformationRole;
    }

    @BeforeEach
    public void initTest() {
        pwcInformationRole = createEntity(em);
    }

    @Test
    @Transactional
    void createPwcInformationRole() throws Exception {
        int databaseSizeBeforeCreate = pwcInformationRoleRepository.findAll().size();
        // Create the PwcInformationRole
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(pwcInformationRole);
        restPwcInformationRoleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeCreate + 1);
        PwcInformationRole testPwcInformationRole = pwcInformationRoleList.get(pwcInformationRoleList.size() - 1);
        assertThat(testPwcInformationRole.getProInformationId()).isEqualTo(DEFAULT_PRO_INFORMATION_ID);
        assertThat(testPwcInformationRole.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testPwcInformationRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcInformationRole.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcInformationRole.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcInformationRole.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void createPwcInformationRoleWithExistingId() throws Exception {
        // Create the PwcInformationRole with an existing ID
        pwcInformationRole.setProInformationRoleId(1L);
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(pwcInformationRole);

        int databaseSizeBeforeCreate = pwcInformationRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwcInformationRoleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPwcInformationRoles() throws Exception {
        // Initialize the database
        pwcInformationRoleRepository.saveAndFlush(pwcInformationRole);

        // Get all the pwcInformationRoleList
        restPwcInformationRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proInformationRoleId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proInformationRoleId").value(hasItem(pwcInformationRole.getProInformationRoleId().intValue())))
            .andExpect(jsonPath("$.[*].proInformationId").value(hasItem(DEFAULT_PRO_INFORMATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)));
    }

    @Test
    @Transactional
    void getPwcInformationRole() throws Exception {
        // Initialize the database
        pwcInformationRoleRepository.saveAndFlush(pwcInformationRole);

        // Get the pwcInformationRole
        restPwcInformationRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, pwcInformationRole.getProInformationRoleId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proInformationRoleId").value(pwcInformationRole.getProInformationRoleId().intValue()))
            .andExpect(jsonPath("$.proInformationId").value(DEFAULT_PRO_INFORMATION_ID.intValue()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingPwcInformationRole() throws Exception {
        // Get the pwcInformationRole
        restPwcInformationRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwcInformationRole() throws Exception {
        // Initialize the database
        pwcInformationRoleRepository.saveAndFlush(pwcInformationRole);

        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();

        // Update the pwcInformationRole
        PwcInformationRole updatedPwcInformationRole = pwcInformationRoleRepository
            .findById(pwcInformationRole.getProInformationRoleId())
            .get();
        // Disconnect from session so that the updates on updatedPwcInformationRole are not directly saved in db
        em.detach(updatedPwcInformationRole);
        updatedPwcInformationRole
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .role(UPDATED_ROLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(updatedPwcInformationRole);

        restPwcInformationRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcInformationRoleDTO.getProInformationRoleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationRole testPwcInformationRole = pwcInformationRoleList.get(pwcInformationRoleList.size() - 1);
        assertThat(testPwcInformationRole.getProInformationId()).isEqualTo(UPDATED_PRO_INFORMATION_ID);
        assertThat(testPwcInformationRole.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testPwcInformationRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformationRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformationRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformationRole.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingPwcInformationRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();
        pwcInformationRole.setProInformationRoleId(count.incrementAndGet());

        // Create the PwcInformationRole
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(pwcInformationRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcInformationRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcInformationRoleDTO.getProInformationRoleId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwcInformationRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();
        pwcInformationRole.setProInformationRoleId(count.incrementAndGet());

        // Create the PwcInformationRole
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(pwcInformationRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwcInformationRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();
        pwcInformationRole.setProInformationRoleId(count.incrementAndGet());

        // Create the PwcInformationRole
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(pwcInformationRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationRoleMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwcInformationRoleWithPatch() throws Exception {
        // Initialize the database
        pwcInformationRoleRepository.saveAndFlush(pwcInformationRole);

        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();

        // Update the pwcInformationRole using partial update
        PwcInformationRole partialUpdatedPwcInformationRole = new PwcInformationRole();
        partialUpdatedPwcInformationRole.setProInformationRoleId(pwcInformationRole.getProInformationRoleId());

        partialUpdatedPwcInformationRole.role(UPDATED_ROLE).updateTime(UPDATED_UPDATE_TIME).createUser(UPDATED_CREATE_USER);

        restPwcInformationRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcInformationRole.getProInformationRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcInformationRole))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationRole testPwcInformationRole = pwcInformationRoleList.get(pwcInformationRoleList.size() - 1);
        assertThat(testPwcInformationRole.getProInformationId()).isEqualTo(DEFAULT_PRO_INFORMATION_ID);
        assertThat(testPwcInformationRole.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testPwcInformationRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformationRole.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcInformationRole.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcInformationRole.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void fullUpdatePwcInformationRoleWithPatch() throws Exception {
        // Initialize the database
        pwcInformationRoleRepository.saveAndFlush(pwcInformationRole);

        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();

        // Update the pwcInformationRole using partial update
        PwcInformationRole partialUpdatedPwcInformationRole = new PwcInformationRole();
        partialUpdatedPwcInformationRole.setProInformationRoleId(pwcInformationRole.getProInformationRoleId());

        partialUpdatedPwcInformationRole
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .role(UPDATED_ROLE)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwcInformationRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcInformationRole.getProInformationRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcInformationRole))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationRole testPwcInformationRole = pwcInformationRoleList.get(pwcInformationRoleList.size() - 1);
        assertThat(testPwcInformationRole.getProInformationId()).isEqualTo(UPDATED_PRO_INFORMATION_ID);
        assertThat(testPwcInformationRole.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testPwcInformationRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformationRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformationRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformationRole.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingPwcInformationRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();
        pwcInformationRole.setProInformationRoleId(count.incrementAndGet());

        // Create the PwcInformationRole
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(pwcInformationRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcInformationRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwcInformationRoleDTO.getProInformationRoleId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwcInformationRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();
        pwcInformationRole.setProInformationRoleId(count.incrementAndGet());

        // Create the PwcInformationRole
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(pwcInformationRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwcInformationRole() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationRoleRepository.findAll().size();
        pwcInformationRole.setProInformationRoleId(count.incrementAndGet());

        // Create the PwcInformationRole
        PwcInformationRoleDTO pwcInformationRoleDTO = pwcInformationRoleMapper.toDto(pwcInformationRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationRoleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcInformationRole in the database
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwcInformationRole() throws Exception {
        // Initialize the database
        pwcInformationRoleRepository.saveAndFlush(pwcInformationRole);

        int databaseSizeBeforeDelete = pwcInformationRoleRepository.findAll().size();

        // Delete the pwcInformationRole
        restPwcInformationRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwcInformationRole.getProInformationRoleId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwcInformationRole> pwcInformationRoleList = pwcInformationRoleRepository.findAll();
        assertThat(pwcInformationRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
