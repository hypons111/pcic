package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwbUnitRole;
import gov.pcc.pwc.repository.PwbUnitRoleRepository;
import gov.pcc.pwc.service.dto.PwbUnitRoleDTO;
import gov.pcc.pwc.service.mapper.PwbUnitRoleMapper;
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
 * Integration tests for the {@link PwbUnitRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwbUnitRoleResourceIT {

    private static final String DEFAULT_UNIT_ID = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pwb-unit-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwbUnitRoleRepository pwbUnitRoleRepository;

    @Autowired
    private PwbUnitRoleMapper pwbUnitRoleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwbUnitRoleMockMvc;

    private PwbUnitRole pwbUnitRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbUnitRole createEntity(EntityManager em) {
        PwbUnitRole pwbUnitRole = new PwbUnitRole()
            .unitId(DEFAULT_UNIT_ID)
            .roleId(DEFAULT_ROLE_ID)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return pwbUnitRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwbUnitRole createUpdatedEntity(EntityManager em) {
        PwbUnitRole pwbUnitRole = new PwbUnitRole()
            .unitId(UPDATED_UNIT_ID)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return pwbUnitRole;
    }

    @BeforeEach
    public void initTest() {
        pwbUnitRole = createEntity(em);
    }

    @Test
    @Transactional
    void createPwbUnitRole() throws Exception {
        int databaseSizeBeforeCreate = pwbUnitRoleRepository.findAll().size();
        // Create the PwbUnitRole
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);
        restPwbUnitRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeCreate + 1);
        PwbUnitRole testPwbUnitRole = pwbUnitRoleList.get(pwbUnitRoleList.size() - 1);
        assertThat(testPwbUnitRole.getUnitId()).isEqualTo(DEFAULT_UNIT_ID);
        assertThat(testPwbUnitRole.getRoleId()).isEqualTo(DEFAULT_ROLE_ID);
        assertThat(testPwbUnitRole.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbUnitRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwbUnitRole.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testPwbUnitRole.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createPwbUnitRoleWithExistingId() throws Exception {
        // Create the PwbUnitRole with an existing ID
        pwbUnitRole.setId(1L);
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        int databaseSizeBeforeCreate = pwbUnitRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwbUnitRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUnitIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitRoleRepository.findAll().size();
        // set the field null
        pwbUnitRole.setUnitId(null);

        // Create the PwbUnitRole, which fails.
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        restPwbUnitRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRoleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitRoleRepository.findAll().size();
        // set the field null
        pwbUnitRole.setRoleId(null);

        // Create the PwbUnitRole, which fails.
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        restPwbUnitRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitRoleRepository.findAll().size();
        // set the field null
        pwbUnitRole.setUpdateUser(null);

        // Create the PwbUnitRole, which fails.
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        restPwbUnitRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitRoleRepository.findAll().size();
        // set the field null
        pwbUnitRole.setUpdateTime(null);

        // Create the PwbUnitRole, which fails.
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        restPwbUnitRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitRoleRepository.findAll().size();
        // set the field null
        pwbUnitRole.setCreateUser(null);

        // Create the PwbUnitRole, which fails.
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        restPwbUnitRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pwbUnitRoleRepository.findAll().size();
        // set the field null
        pwbUnitRole.setCreateTime(null);

        // Create the PwbUnitRole, which fails.
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        restPwbUnitRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPwbUnitRoles() throws Exception {
        // Initialize the database
        pwbUnitRoleRepository.saveAndFlush(pwbUnitRole);

        // Get all the pwbUnitRoleList
        restPwbUnitRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pwbUnitRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitId").value(hasItem(DEFAULT_UNIT_ID)))
            .andExpect(jsonPath("$.[*].roleId").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getPwbUnitRole() throws Exception {
        // Initialize the database
        pwbUnitRoleRepository.saveAndFlush(pwbUnitRole);

        // Get the pwbUnitRole
        restPwbUnitRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, pwbUnitRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pwbUnitRole.getId().intValue()))
            .andExpect(jsonPath("$.unitId").value(DEFAULT_UNIT_ID))
            .andExpect(jsonPath("$.roleId").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPwbUnitRole() throws Exception {
        // Get the pwbUnitRole
        restPwbUnitRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwbUnitRole() throws Exception {
        // Initialize the database
        pwbUnitRoleRepository.saveAndFlush(pwbUnitRole);

        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();

        // Update the pwbUnitRole
        PwbUnitRole updatedPwbUnitRole = pwbUnitRoleRepository.findById(pwbUnitRole.getId()).get();
        // Disconnect from session so that the updates on updatedPwbUnitRole are not directly saved in db
        em.detach(updatedPwbUnitRole);
        updatedPwbUnitRole
            .unitId(UPDATED_UNIT_ID)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(updatedPwbUnitRole);

        restPwbUnitRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbUnitRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
        PwbUnitRole testPwbUnitRole = pwbUnitRoleList.get(pwbUnitRoleList.size() - 1);
        assertThat(testPwbUnitRole.getUnitId()).isEqualTo(UPDATED_UNIT_ID);
        assertThat(testPwbUnitRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testPwbUnitRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbUnitRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbUnitRole.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbUnitRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingPwbUnitRole() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();
        pwbUnitRole.setId(count.incrementAndGet());

        // Create the PwbUnitRole
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbUnitRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwbUnitRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwbUnitRole() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();
        pwbUnitRole.setId(count.incrementAndGet());

        // Create the PwbUnitRole
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUnitRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwbUnitRole() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();
        pwbUnitRole.setId(count.incrementAndGet());

        // Create the PwbUnitRole
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUnitRoleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwbUnitRoleWithPatch() throws Exception {
        // Initialize the database
        pwbUnitRoleRepository.saveAndFlush(pwbUnitRole);

        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();

        // Update the pwbUnitRole using partial update
        PwbUnitRole partialUpdatedPwbUnitRole = new PwbUnitRole();
        partialUpdatedPwbUnitRole.setId(pwbUnitRole.getId());

        partialUpdatedPwbUnitRole.unitId(UPDATED_UNIT_ID).roleId(UPDATED_ROLE_ID);

        restPwbUnitRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbUnitRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbUnitRole))
            )
            .andExpect(status().isOk());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
        PwbUnitRole testPwbUnitRole = pwbUnitRoleList.get(pwbUnitRoleList.size() - 1);
        assertThat(testPwbUnitRole.getUnitId()).isEqualTo(UPDATED_UNIT_ID);
        assertThat(testPwbUnitRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testPwbUnitRole.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwbUnitRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwbUnitRole.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testPwbUnitRole.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdatePwbUnitRoleWithPatch() throws Exception {
        // Initialize the database
        pwbUnitRoleRepository.saveAndFlush(pwbUnitRole);

        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();

        // Update the pwbUnitRole using partial update
        PwbUnitRole partialUpdatedPwbUnitRole = new PwbUnitRole();
        partialUpdatedPwbUnitRole.setId(pwbUnitRole.getId());

        partialUpdatedPwbUnitRole
            .unitId(UPDATED_UNIT_ID)
            .roleId(UPDATED_ROLE_ID)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restPwbUnitRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwbUnitRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwbUnitRole))
            )
            .andExpect(status().isOk());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
        PwbUnitRole testPwbUnitRole = pwbUnitRoleList.get(pwbUnitRoleList.size() - 1);
        assertThat(testPwbUnitRole.getUnitId()).isEqualTo(UPDATED_UNIT_ID);
        assertThat(testPwbUnitRole.getRoleId()).isEqualTo(UPDATED_ROLE_ID);
        assertThat(testPwbUnitRole.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwbUnitRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwbUnitRole.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testPwbUnitRole.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingPwbUnitRole() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();
        pwbUnitRole.setId(count.incrementAndGet());

        // Create the PwbUnitRole
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwbUnitRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwbUnitRoleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwbUnitRole() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();
        pwbUnitRole.setId(count.incrementAndGet());

        // Create the PwbUnitRole
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUnitRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwbUnitRole() throws Exception {
        int databaseSizeBeforeUpdate = pwbUnitRoleRepository.findAll().size();
        pwbUnitRole.setId(count.incrementAndGet());

        // Create the PwbUnitRole
        PwbUnitRoleDTO pwbUnitRoleDTO = pwbUnitRoleMapper.toDto(pwbUnitRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwbUnitRoleMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(pwbUnitRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwbUnitRole in the database
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwbUnitRole() throws Exception {
        // Initialize the database
        pwbUnitRoleRepository.saveAndFlush(pwbUnitRole);

        int databaseSizeBeforeDelete = pwbUnitRoleRepository.findAll().size();

        // Delete the pwbUnitRole
        restPwbUnitRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwbUnitRole.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwbUnitRole> pwbUnitRoleList = pwbUnitRoleRepository.findAll();
        assertThat(pwbUnitRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
