package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmUnit;
import gov.pcc.pwc.repository.AdmUnitRepository;

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
 * Integration tests for the {@link AdmUnitResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmUnitResourceIT {

    private static final String DEFAULT_MASTER_UNIT_ID = "AAAAAAAAAA";
    private static final String UPDATED_MASTER_UNIT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ADMIN_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_ADMIN_ORG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-units";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{unitId}";

    @Autowired
    private AdmUnitRepository admUnitRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmUnitMockMvc;

    private AdmUnit admUnit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmUnit createEntity(EntityManager em) {
        AdmUnit admUnit = new AdmUnit()
            .masterUnitId(DEFAULT_MASTER_UNIT_ID)
            .adminOrgId(DEFAULT_ADMIN_ORG_ID)
            .unitName(DEFAULT_UNIT_NAME)
            .addr(DEFAULT_ADDR)
            .tel(DEFAULT_TEL)
            .fax(DEFAULT_FAX)
            .descript(DEFAULT_DESCRIPT)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admUnit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmUnit createUpdatedEntity(EntityManager em) {
        AdmUnit admUnit = new AdmUnit()
            .masterUnitId(UPDATED_MASTER_UNIT_ID)
            .adminOrgId(UPDATED_ADMIN_ORG_ID)
            .unitName(UPDATED_UNIT_NAME)
            .addr(UPDATED_ADDR)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .descript(UPDATED_DESCRIPT)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admUnit;
    }

    @BeforeEach
    public void initTest() {
        admUnit = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmUnit() throws Exception {
        int databaseSizeBeforeCreate = admUnitRepository.findAll().size();
        // Create the AdmUnit
        restAdmUnitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admUnit)))
            .andExpect(status().isCreated());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeCreate + 1);
        AdmUnit testAdmUnit = admUnitList.get(admUnitList.size() - 1);
        assertThat(testAdmUnit.getMasterUnitId()).isEqualTo(DEFAULT_MASTER_UNIT_ID);
        assertThat(testAdmUnit.getAdminOrgId()).isEqualTo(DEFAULT_ADMIN_ORG_ID);
        assertThat(testAdmUnit.getUnitName()).isEqualTo(DEFAULT_UNIT_NAME);
        assertThat(testAdmUnit.getAddr()).isEqualTo(DEFAULT_ADDR);
        assertThat(testAdmUnit.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testAdmUnit.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testAdmUnit.getDescript()).isEqualTo(DEFAULT_DESCRIPT);
        assertThat(testAdmUnit.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmUnit.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmUnitWithExistingId() throws Exception {
        // Create the AdmUnit with an existing ID
        admUnit.setUnitId("existing_id");

        int databaseSizeBeforeCreate = admUnitRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmUnitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admUnit)))
            .andExpect(status().isBadRequest());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admUnitRepository.findAll().size();
        // set the field null
        admUnit.setUpdateUser(null);

        // Create the AdmUnit, which fails.

        restAdmUnitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admUnit)))
            .andExpect(status().isBadRequest());

        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admUnitRepository.findAll().size();
        // set the field null
        admUnit.setUpdateTime(null);

        // Create the AdmUnit, which fails.

        restAdmUnitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admUnit)))
            .andExpect(status().isBadRequest());

        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmUnits() throws Exception {
        // Initialize the database
        admUnitRepository.saveAndFlush(admUnit);

        // Get all the admUnitList
        restAdmUnitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=unitId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].unitId").value(hasItem(admUnit.getUnitId())))
            .andExpect(jsonPath("$.[*].masterUnitId").value(hasItem(DEFAULT_MASTER_UNIT_ID)))
            .andExpect(jsonPath("$.[*].adminOrgId").value(hasItem(DEFAULT_ADMIN_ORG_ID)))
            .andExpect(jsonPath("$.[*].unitName").value(hasItem(DEFAULT_UNIT_NAME)))
            .andExpect(jsonPath("$.[*].addr").value(hasItem(DEFAULT_ADDR)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].descript").value(hasItem(DEFAULT_DESCRIPT)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmUnit() throws Exception {
        // Initialize the database
        admUnitRepository.saveAndFlush(admUnit);

        // Get the admUnit
        restAdmUnitMockMvc
            .perform(get(ENTITY_API_URL_ID, admUnit.getUnitId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.unitId").value(admUnit.getUnitId()))
            .andExpect(jsonPath("$.masterUnitId").value(DEFAULT_MASTER_UNIT_ID))
            .andExpect(jsonPath("$.adminOrgId").value(DEFAULT_ADMIN_ORG_ID))
            .andExpect(jsonPath("$.unitName").value(DEFAULT_UNIT_NAME))
            .andExpect(jsonPath("$.addr").value(DEFAULT_ADDR))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.descript").value(DEFAULT_DESCRIPT))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmUnit() throws Exception {
        // Get the admUnit
        restAdmUnitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmUnit() throws Exception {
        // Initialize the database
        admUnitRepository.saveAndFlush(admUnit);

        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();

        // Update the admUnit
        AdmUnit updatedAdmUnit = admUnitRepository.findById(admUnit.getUnitId()).get();
        // Disconnect from session so that the updates on updatedAdmUnit are not directly saved in db
        em.detach(updatedAdmUnit);
        updatedAdmUnit
            .masterUnitId(UPDATED_MASTER_UNIT_ID)
            .adminOrgId(UPDATED_ADMIN_ORG_ID)
            .unitName(UPDATED_UNIT_NAME)
            .addr(UPDATED_ADDR)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .descript(UPDATED_DESCRIPT)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmUnit.getUnitId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmUnit))
            )
            .andExpect(status().isOk());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
        AdmUnit testAdmUnit = admUnitList.get(admUnitList.size() - 1);
        assertThat(testAdmUnit.getMasterUnitId()).isEqualTo(UPDATED_MASTER_UNIT_ID);
        assertThat(testAdmUnit.getAdminOrgId()).isEqualTo(UPDATED_ADMIN_ORG_ID);
        assertThat(testAdmUnit.getUnitName()).isEqualTo(UPDATED_UNIT_NAME);
        assertThat(testAdmUnit.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testAdmUnit.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testAdmUnit.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testAdmUnit.getDescript()).isEqualTo(UPDATED_DESCRIPT);
        assertThat(testAdmUnit.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmUnit.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmUnit() throws Exception {
        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();
        admUnit.setUnitId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admUnit.getUnitId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admUnit))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmUnit() throws Exception {
        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();
        admUnit.setUnitId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admUnit))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmUnit() throws Exception {
        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();
        admUnit.setUnitId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmUnitMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admUnit)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmUnitWithPatch() throws Exception {
        // Initialize the database
        admUnitRepository.saveAndFlush(admUnit);

        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();

        // Update the admUnit using partial update
        AdmUnit partialUpdatedAdmUnit = new AdmUnit();
        partialUpdatedAdmUnit.setUnitId(admUnit.getUnitId());

        partialUpdatedAdmUnit.masterUnitId(UPDATED_MASTER_UNIT_ID).addr(UPDATED_ADDR);

        restAdmUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmUnit.getUnitId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmUnit))
            )
            .andExpect(status().isOk());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
        AdmUnit testAdmUnit = admUnitList.get(admUnitList.size() - 1);
        assertThat(testAdmUnit.getMasterUnitId()).isEqualTo(UPDATED_MASTER_UNIT_ID);
        assertThat(testAdmUnit.getAdminOrgId()).isEqualTo(DEFAULT_ADMIN_ORG_ID);
        assertThat(testAdmUnit.getUnitName()).isEqualTo(DEFAULT_UNIT_NAME);
        assertThat(testAdmUnit.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testAdmUnit.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testAdmUnit.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testAdmUnit.getDescript()).isEqualTo(DEFAULT_DESCRIPT);
        assertThat(testAdmUnit.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmUnit.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmUnitWithPatch() throws Exception {
        // Initialize the database
        admUnitRepository.saveAndFlush(admUnit);

        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();

        // Update the admUnit using partial update
        AdmUnit partialUpdatedAdmUnit = new AdmUnit();
        partialUpdatedAdmUnit.setUnitId(admUnit.getUnitId());

        partialUpdatedAdmUnit
            .masterUnitId(UPDATED_MASTER_UNIT_ID)
            .adminOrgId(UPDATED_ADMIN_ORG_ID)
            .unitName(UPDATED_UNIT_NAME)
            .addr(UPDATED_ADDR)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .descript(UPDATED_DESCRIPT)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmUnit.getUnitId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmUnit))
            )
            .andExpect(status().isOk());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
        AdmUnit testAdmUnit = admUnitList.get(admUnitList.size() - 1);
        assertThat(testAdmUnit.getMasterUnitId()).isEqualTo(UPDATED_MASTER_UNIT_ID);
        assertThat(testAdmUnit.getAdminOrgId()).isEqualTo(UPDATED_ADMIN_ORG_ID);
        assertThat(testAdmUnit.getUnitName()).isEqualTo(UPDATED_UNIT_NAME);
        assertThat(testAdmUnit.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testAdmUnit.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testAdmUnit.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testAdmUnit.getDescript()).isEqualTo(UPDATED_DESCRIPT);
        assertThat(testAdmUnit.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmUnit.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmUnit() throws Exception {
        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();
        admUnit.setUnitId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admUnit.getUnitId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admUnit))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmUnit() throws Exception {
        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();
        admUnit.setUnitId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admUnit))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmUnit() throws Exception {
        int databaseSizeBeforeUpdate = admUnitRepository.findAll().size();
        admUnit.setUnitId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmUnitMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admUnit)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmUnit in the database
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmUnit() throws Exception {
        // Initialize the database
        admUnitRepository.saveAndFlush(admUnit);

        int databaseSizeBeforeDelete = admUnitRepository.findAll().size();

        // Delete the admUnit
        restAdmUnitMockMvc
            .perform(delete(ENTITY_API_URL_ID, admUnit.getUnitId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmUnit> admUnitList = admUnitRepository.findAll();
        assertThat(admUnitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
