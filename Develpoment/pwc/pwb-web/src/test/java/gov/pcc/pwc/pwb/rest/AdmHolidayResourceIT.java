//package gov.pcc.pwc.pwb.rest;
//
//import gov.pcc.pwc.IntegrationTest;
//import gov.pcc.pwc.domain.AdmHoliday;
//import gov.pcc.pwc.repository.AdmHolidayRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicLong;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
///**
// * Integration tests for the {@link AdmHolidayResource} REST controller.
// */
//@IntegrationTest
//@AutoConfigureMockMvc
//@WithMockUser
//class AdmHolidayResourceIT {
//
//    private static final String DEFAULT_HOLIDAY_DATE = "AAAAAAAAAA";
//    private static final String UPDATED_HOLIDAY_DATE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_HOLIDAY_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_HOLIDAY_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
//    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";
//
//    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
//    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//    private static final String ENTITY_API_URL = "/api/adm-holidays";
//    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
//
//    private static Random random = new Random();
//    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
//
//    @Autowired
//    private AdmHolidayRepository admHolidayRepository;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private MockMvc restAdmHolidayMockMvc;
//
//    private AdmHoliday admHoliday;
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static AdmHoliday createEntity(EntityManager em) {
//        AdmHoliday admHoliday = new AdmHoliday()
//            .holidayDate(DEFAULT_HOLIDAY_DATE)
//            .holidayName(DEFAULT_HOLIDAY_NAME)
//            .updateUser(DEFAULT_UPDATE_USER)
//            .updateTime(DEFAULT_UPDATE_TIME);
//        return admHoliday;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static AdmHoliday createUpdatedEntity(EntityManager em) {
//        AdmHoliday admHoliday = new AdmHoliday()
//            .holidayDate(UPDATED_HOLIDAY_DATE)
//            .holidayName(UPDATED_HOLIDAY_NAME)
//            .updateUser(UPDATED_UPDATE_USER)
//            .updateTime(UPDATED_UPDATE_TIME);
//        return admHoliday;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        admHoliday = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createAdmHoliday() throws Exception {
//        int databaseSizeBeforeCreate = admHolidayRepository.findAll().size();
//        // Create the AdmHoliday
//        restAdmHolidayMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admHoliday)))
//            .andExpect(status().isCreated());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeCreate + 1);
//        AdmHoliday testAdmHoliday = admHolidayList.get(admHolidayList.size() - 1);
//        assertThat(testAdmHoliday.getHolidayDate()).isEqualTo(DEFAULT_HOLIDAY_DATE);
//        assertThat(testAdmHoliday.getHolidayName()).isEqualTo(DEFAULT_HOLIDAY_NAME);
//        assertThat(testAdmHoliday.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
//        assertThat(testAdmHoliday.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    void createAdmHolidayWithExistingId() throws Exception {
//        // Create the AdmHoliday with an existing ID
//        admHoliday.setId(1L);
//
//        int databaseSizeBeforeCreate = admHolidayRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restAdmHolidayMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admHoliday)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void checkHolidayDateIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admHolidayRepository.findAll().size();
//        // set the field null
//        admHoliday.setHolidayDate(null);
//
//        // Create the AdmHoliday, which fails.
//
//        restAdmHolidayMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admHoliday)))
//            .andExpect(status().isBadRequest());
//
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkHolidayNameIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admHolidayRepository.findAll().size();
//        // set the field null
//        admHoliday.setHolidayName(null);
//
//        // Create the AdmHoliday, which fails.
//
//        restAdmHolidayMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admHoliday)))
//            .andExpect(status().isBadRequest());
//
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkUpdateUserIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admHolidayRepository.findAll().size();
//        // set the field null
//        admHoliday.setUpdateUser(null);
//
//        // Create the AdmHoliday, which fails.
//
//        restAdmHolidayMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admHoliday)))
//            .andExpect(status().isBadRequest());
//
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkUpdateTimeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admHolidayRepository.findAll().size();
//        // set the field null
//        admHoliday.setUpdateTime(null);
//
//        // Create the AdmHoliday, which fails.
//
//        restAdmHolidayMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admHoliday)))
//            .andExpect(status().isBadRequest());
//
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void getAllAdmHolidays() throws Exception {
//        // Initialize the database
//        admHolidayRepository.saveAndFlush(admHoliday);
//
//        // Get all the admHolidayList
//        restAdmHolidayMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(admHoliday.getId().intValue())))
//            .andExpect(jsonPath("$.[*].holidayDate").value(hasItem(DEFAULT_HOLIDAY_DATE)))
//            .andExpect(jsonPath("$.[*].holidayName").value(hasItem(DEFAULT_HOLIDAY_NAME)))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
//    }
//
//    @Test
//    @Transactional
//    void getAdmHoliday() throws Exception {
//        // Initialize the database
//        admHolidayRepository.saveAndFlush(admHoliday);
//
//        // Get the admHoliday
//        restAdmHolidayMockMvc
//            .perform(get(ENTITY_API_URL_ID, admHoliday.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(admHoliday.getId().intValue()))
//            .andExpect(jsonPath("$.holidayDate").value(DEFAULT_HOLIDAY_DATE))
//            .andExpect(jsonPath("$.holidayName").value(DEFAULT_HOLIDAY_NAME))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingAdmHoliday() throws Exception {
//        // Get the admHoliday
//        restAdmHolidayMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewAdmHoliday() throws Exception {
//        // Initialize the database
//        admHolidayRepository.saveAndFlush(admHoliday);
//
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//
//        // Update the admHoliday
//        AdmHoliday updatedAdmHoliday = admHolidayRepository.findById(admHoliday.getId()).get();
//        // Disconnect from session so that the updates on updatedAdmHoliday are not directly saved in db
//        em.detach(updatedAdmHoliday);
//        updatedAdmHoliday
//            .holidayDate(UPDATED_HOLIDAY_DATE)
//            .holidayName(UPDATED_HOLIDAY_NAME)
//            .updateUser(UPDATED_UPDATE_USER)
//            .updateTime(UPDATED_UPDATE_TIME);
//
//        restAdmHolidayMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedAdmHoliday.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmHoliday))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//        AdmHoliday testAdmHoliday = admHolidayList.get(admHolidayList.size() - 1);
//        assertThat(testAdmHoliday.getHolidayDate()).isEqualTo(UPDATED_HOLIDAY_DATE);
//        assertThat(testAdmHoliday.getHolidayName()).isEqualTo(UPDATED_HOLIDAY_NAME);
//        assertThat(testAdmHoliday.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//        assertThat(testAdmHoliday.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingAdmHoliday() throws Exception {
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//        admHoliday.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restAdmHolidayMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, admHoliday.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(admHoliday))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchAdmHoliday() throws Exception {
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//        admHoliday.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restAdmHolidayMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(admHoliday))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamAdmHoliday() throws Exception {
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//        admHoliday.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restAdmHolidayMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admHoliday)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateAdmHolidayWithPatch() throws Exception {
//        // Initialize the database
//        admHolidayRepository.saveAndFlush(admHoliday);
//
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//
//        // Update the admHoliday using partial update
//        AdmHoliday partialUpdatedAdmHoliday = new AdmHoliday();
//        partialUpdatedAdmHoliday.setId(admHoliday.getId());
//
//        partialUpdatedAdmHoliday.holidayDate(UPDATED_HOLIDAY_DATE).holidayName(UPDATED_HOLIDAY_NAME).updateTime(UPDATED_UPDATE_TIME);
//
//        restAdmHolidayMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedAdmHoliday.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmHoliday))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//        AdmHoliday testAdmHoliday = admHolidayList.get(admHolidayList.size() - 1);
//        assertThat(testAdmHoliday.getHolidayDate()).isEqualTo(UPDATED_HOLIDAY_DATE);
//        assertThat(testAdmHoliday.getHolidayName()).isEqualTo(UPDATED_HOLIDAY_NAME);
//        assertThat(testAdmHoliday.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
//        assertThat(testAdmHoliday.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateAdmHolidayWithPatch() throws Exception {
//        // Initialize the database
//        admHolidayRepository.saveAndFlush(admHoliday);
//
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//
//        // Update the admHoliday using partial update
//        AdmHoliday partialUpdatedAdmHoliday = new AdmHoliday();
//        partialUpdatedAdmHoliday.setId(admHoliday.getId());
//
//        partialUpdatedAdmHoliday
//            .holidayDate(UPDATED_HOLIDAY_DATE)
//            .holidayName(UPDATED_HOLIDAY_NAME)
//            .updateUser(UPDATED_UPDATE_USER)
//            .updateTime(UPDATED_UPDATE_TIME);
//
//        restAdmHolidayMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedAdmHoliday.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmHoliday))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//        AdmHoliday testAdmHoliday = admHolidayList.get(admHolidayList.size() - 1);
//        assertThat(testAdmHoliday.getHolidayDate()).isEqualTo(UPDATED_HOLIDAY_DATE);
//        assertThat(testAdmHoliday.getHolidayName()).isEqualTo(UPDATED_HOLIDAY_NAME);
//        assertThat(testAdmHoliday.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//        assertThat(testAdmHoliday.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingAdmHoliday() throws Exception {
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//        admHoliday.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restAdmHolidayMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, admHoliday.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(admHoliday))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchAdmHoliday() throws Exception {
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//        admHoliday.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restAdmHolidayMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(admHoliday))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamAdmHoliday() throws Exception {
//        int databaseSizeBeforeUpdate = admHolidayRepository.findAll().size();
//        admHoliday.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restAdmHolidayMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admHoliday))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the AdmHoliday in the database
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteAdmHoliday() throws Exception {
//        // Initialize the database
//        admHolidayRepository.saveAndFlush(admHoliday);
//
//        int databaseSizeBeforeDelete = admHolidayRepository.findAll().size();
//
//        // Delete the admHoliday
//        restAdmHolidayMockMvc
//            .perform(delete(ENTITY_API_URL_ID, admHoliday.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<AdmHoliday> admHolidayList = admHolidayRepository.findAll();
//        assertThat(admHolidayList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//}
