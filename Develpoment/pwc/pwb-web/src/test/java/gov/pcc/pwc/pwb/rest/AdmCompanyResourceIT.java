package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmCompany;
import gov.pcc.pwc.repository.AdmCompanyRepository;

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
 * Integration tests for the {@link AdmCompanyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmCompanyResourceIT {

    private static final String DEFAULT_RP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_TOWN = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_TOWN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_VILLAGE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LIN = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LIN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_ROAD = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_ROAD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(0);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(1);

    private static final Instant DEFAULT_CON_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CON_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-companies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{compIdno}";

    @Autowired
    private AdmCompanyRepository admCompanyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmCompanyMockMvc;

    private AdmCompany admCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmCompany createEntity(EntityManager em) {
        AdmCompany admCompany = new AdmCompany()
            .rpName(DEFAULT_RP_NAME)
            .name(DEFAULT_NAME)
            .addressCity(DEFAULT_ADDRESS_CITY)
            .addressTown(DEFAULT_ADDRESS_TOWN)
            .addressVillage(DEFAULT_ADDRESS_VILLAGE)
            .addressLin(DEFAULT_ADDRESS_LIN)
            .addressRoad(DEFAULT_ADDRESS_ROAD)
            .amount(DEFAULT_AMOUNT)
            .conDate(DEFAULT_CON_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admCompany;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmCompany createUpdatedEntity(EntityManager em) {
        AdmCompany admCompany = new AdmCompany()
            .rpName(UPDATED_RP_NAME)
            .name(UPDATED_NAME)
            .addressCity(UPDATED_ADDRESS_CITY)
            .addressTown(UPDATED_ADDRESS_TOWN)
            .addressVillage(UPDATED_ADDRESS_VILLAGE)
            .addressLin(UPDATED_ADDRESS_LIN)
            .addressRoad(UPDATED_ADDRESS_ROAD)
            .amount(UPDATED_AMOUNT)
            .conDate(UPDATED_CON_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admCompany;
    }

    @BeforeEach
    public void initTest() {
        admCompany = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmCompany() throws Exception {
        int databaseSizeBeforeCreate = admCompanyRepository.findAll().size();
        // Create the AdmCompany
        restAdmCompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCompany)))
            .andExpect(status().isCreated());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        AdmCompany testAdmCompany = admCompanyList.get(admCompanyList.size() - 1);
        assertThat(testAdmCompany.getRpName()).isEqualTo(DEFAULT_RP_NAME);
        assertThat(testAdmCompany.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdmCompany.getAddressCity()).isEqualTo(DEFAULT_ADDRESS_CITY);
        assertThat(testAdmCompany.getAddressTown()).isEqualTo(DEFAULT_ADDRESS_TOWN);
        assertThat(testAdmCompany.getAddressVillage()).isEqualTo(DEFAULT_ADDRESS_VILLAGE);
        assertThat(testAdmCompany.getAddressLin()).isEqualTo(DEFAULT_ADDRESS_LIN);
        assertThat(testAdmCompany.getAddressRoad()).isEqualTo(DEFAULT_ADDRESS_ROAD);
        assertThat(testAdmCompany.getAmount()).isEqualByComparingTo(DEFAULT_AMOUNT);
        assertThat(testAdmCompany.getConDate()).isEqualTo(DEFAULT_CON_DATE);
        assertThat(testAdmCompany.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmCompany.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmCompanyWithExistingId() throws Exception {
        // Create the AdmCompany with an existing ID
        admCompany.setCompIdno("existing_id");

        int databaseSizeBeforeCreate = admCompanyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmCompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCompany)))
            .andExpect(status().isBadRequest());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCompanyRepository.findAll().size();
        // set the field null
        admCompany.setUpdateUser(null);

        // Create the AdmCompany, which fails.

        restAdmCompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCompany)))
            .andExpect(status().isBadRequest());

        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admCompanyRepository.findAll().size();
        // set the field null
        admCompany.setUpdateTime(null);

        // Create the AdmCompany, which fails.

        restAdmCompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCompany)))
            .andExpect(status().isBadRequest());

        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmCompanies() throws Exception {
        // Initialize the database
        admCompanyRepository.saveAndFlush(admCompany);

        // Get all the admCompanyList
        restAdmCompanyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=compIdno,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].compIdno").value(hasItem(admCompany.getCompIdno())))
            .andExpect(jsonPath("$.[*].rpName").value(hasItem(DEFAULT_RP_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].addressCity").value(hasItem(DEFAULT_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].addressTown").value(hasItem(DEFAULT_ADDRESS_TOWN)))
            .andExpect(jsonPath("$.[*].addressVillage").value(hasItem(DEFAULT_ADDRESS_VILLAGE)))
            .andExpect(jsonPath("$.[*].addressLin").value(hasItem(DEFAULT_ADDRESS_LIN)))
            .andExpect(jsonPath("$.[*].addressRoad").value(hasItem(DEFAULT_ADDRESS_ROAD)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))))
            .andExpect(jsonPath("$.[*].conDate").value(hasItem(DEFAULT_CON_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmCompany() throws Exception {
        // Initialize the database
        admCompanyRepository.saveAndFlush(admCompany);

        // Get the admCompany
        restAdmCompanyMockMvc
            .perform(get(ENTITY_API_URL_ID, admCompany.getCompIdno()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.compIdno").value(admCompany.getCompIdno()))
            .andExpect(jsonPath("$.rpName").value(DEFAULT_RP_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.addressCity").value(DEFAULT_ADDRESS_CITY))
            .andExpect(jsonPath("$.addressTown").value(DEFAULT_ADDRESS_TOWN))
            .andExpect(jsonPath("$.addressVillage").value(DEFAULT_ADDRESS_VILLAGE))
            .andExpect(jsonPath("$.addressLin").value(DEFAULT_ADDRESS_LIN))
            .andExpect(jsonPath("$.addressRoad").value(DEFAULT_ADDRESS_ROAD))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.conDate").value(DEFAULT_CON_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmCompany() throws Exception {
        // Get the admCompany
        restAdmCompanyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmCompany() throws Exception {
        // Initialize the database
        admCompanyRepository.saveAndFlush(admCompany);

        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();

        // Update the admCompany
        AdmCompany updatedAdmCompany = admCompanyRepository.findById(admCompany.getCompIdno()).get();
        // Disconnect from session so that the updates on updatedAdmCompany are not directly saved in db
        em.detach(updatedAdmCompany);
        updatedAdmCompany
            .rpName(UPDATED_RP_NAME)
            .name(UPDATED_NAME)
            .addressCity(UPDATED_ADDRESS_CITY)
            .addressTown(UPDATED_ADDRESS_TOWN)
            .addressVillage(UPDATED_ADDRESS_VILLAGE)
            .addressLin(UPDATED_ADDRESS_LIN)
            .addressRoad(UPDATED_ADDRESS_ROAD)
            .amount(UPDATED_AMOUNT)
            .conDate(UPDATED_CON_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmCompany.getCompIdno())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmCompany))
            )
            .andExpect(status().isOk());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
        AdmCompany testAdmCompany = admCompanyList.get(admCompanyList.size() - 1);
        assertThat(testAdmCompany.getRpName()).isEqualTo(UPDATED_RP_NAME);
        assertThat(testAdmCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdmCompany.getAddressCity()).isEqualTo(UPDATED_ADDRESS_CITY);
        assertThat(testAdmCompany.getAddressTown()).isEqualTo(UPDATED_ADDRESS_TOWN);
        assertThat(testAdmCompany.getAddressVillage()).isEqualTo(UPDATED_ADDRESS_VILLAGE);
        assertThat(testAdmCompany.getAddressLin()).isEqualTo(UPDATED_ADDRESS_LIN);
        assertThat(testAdmCompany.getAddressRoad()).isEqualTo(UPDATED_ADDRESS_ROAD);
        assertThat(testAdmCompany.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAdmCompany.getConDate()).isEqualTo(UPDATED_CON_DATE);
        assertThat(testAdmCompany.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmCompany.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingAdmCompany() throws Exception {
        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();
        admCompany.setCompIdno(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admCompany.getCompIdno())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admCompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmCompany() throws Exception {
        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();
        admCompany.setCompIdno(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admCompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmCompany() throws Exception {
        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();
        admCompany.setCompIdno(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmCompanyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admCompany)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmCompanyWithPatch() throws Exception {
        // Initialize the database
        admCompanyRepository.saveAndFlush(admCompany);

        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();

        // Update the admCompany using partial update
        AdmCompany partialUpdatedAdmCompany = new AdmCompany();
        partialUpdatedAdmCompany.setCompIdno(admCompany.getCompIdno());

        partialUpdatedAdmCompany.compIdno(UPDATED_RP_NAME).addressCity(UPDATED_ADDRESS_CITY).amount(UPDATED_AMOUNT);

        restAdmCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmCompany.getCompIdno())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmCompany))
            )
            .andExpect(status().isOk());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
        AdmCompany testAdmCompany = admCompanyList.get(admCompanyList.size() - 1);
        assertThat(testAdmCompany.getRpName()).isEqualTo(UPDATED_RP_NAME);
        assertThat(testAdmCompany.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdmCompany.getAddressCity()).isEqualTo(UPDATED_ADDRESS_CITY);
        assertThat(testAdmCompany.getAddressTown()).isEqualTo(DEFAULT_ADDRESS_TOWN);
        assertThat(testAdmCompany.getAddressVillage()).isEqualTo(DEFAULT_ADDRESS_VILLAGE);
        assertThat(testAdmCompany.getAddressLin()).isEqualTo(DEFAULT_ADDRESS_LIN);
        assertThat(testAdmCompany.getAddressRoad()).isEqualTo(DEFAULT_ADDRESS_ROAD);
        assertThat(testAdmCompany.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testAdmCompany.getConDate()).isEqualTo(DEFAULT_CON_DATE);
        assertThat(testAdmCompany.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmCompany.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateAdmCompanyWithPatch() throws Exception {
        // Initialize the database
        admCompanyRepository.saveAndFlush(admCompany);

        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();

        // Update the admCompany using partial update
        AdmCompany partialUpdatedAdmCompany = new AdmCompany();
        partialUpdatedAdmCompany.setCompIdno(admCompany.getCompIdno());

        partialUpdatedAdmCompany
            .rpName(UPDATED_RP_NAME)
            .name(UPDATED_NAME)
            .addressCity(UPDATED_ADDRESS_CITY)
            .addressTown(UPDATED_ADDRESS_TOWN)
            .addressVillage(UPDATED_ADDRESS_VILLAGE)
            .addressLin(UPDATED_ADDRESS_LIN)
            .addressRoad(UPDATED_ADDRESS_ROAD)
            .amount(UPDATED_AMOUNT)
            .conDate(UPDATED_CON_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);

        restAdmCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmCompany.getCompIdno())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmCompany))
            )
            .andExpect(status().isOk());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
        AdmCompany testAdmCompany = admCompanyList.get(admCompanyList.size() - 1);
        assertThat(testAdmCompany.getRpName()).isEqualTo(UPDATED_RP_NAME);
        assertThat(testAdmCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdmCompany.getAddressCity()).isEqualTo(UPDATED_ADDRESS_CITY);
        assertThat(testAdmCompany.getAddressTown()).isEqualTo(UPDATED_ADDRESS_TOWN);
        assertThat(testAdmCompany.getAddressVillage()).isEqualTo(UPDATED_ADDRESS_VILLAGE);
        assertThat(testAdmCompany.getAddressLin()).isEqualTo(UPDATED_ADDRESS_LIN);
        assertThat(testAdmCompany.getAddressRoad()).isEqualTo(UPDATED_ADDRESS_ROAD);
        assertThat(testAdmCompany.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testAdmCompany.getConDate()).isEqualTo(UPDATED_CON_DATE);
        assertThat(testAdmCompany.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testAdmCompany.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingAdmCompany() throws Exception {
        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();
        admCompany.setCompIdno(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admCompany.getCompIdno())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admCompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmCompany() throws Exception {
        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();
        admCompany.setCompIdno(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admCompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmCompany() throws Exception {
        int databaseSizeBeforeUpdate = admCompanyRepository.findAll().size();
        admCompany.setCompIdno(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admCompany))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmCompany in the database
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmCompany() throws Exception {
        // Initialize the database
        admCompanyRepository.saveAndFlush(admCompany);

        int databaseSizeBeforeDelete = admCompanyRepository.findAll().size();

        // Delete the admCompany
        restAdmCompanyMockMvc
            .perform(delete(ENTITY_API_URL_ID, admCompany.getCompIdno()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmCompany> admCompanyList = admCompanyRepository.findAll();
        assertThat(admCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
