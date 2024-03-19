package gov.pcc.pwc.pwb.rest; //package gov.pcc.pwc.pwb.rest;
//
//import gov.pcc.pwc.IntegrationTest;
//import gov.pcc.pwc.domain.AdmSysParameter;
//import gov.pcc.pwc.repository.AdmSysParameterRepository;
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
// * Integration tests for the {@link AdmSysParameterResource} REST controller.
// */
//@IntegrationTest
//@AutoConfigureMockMvc
//@WithMockUser
//class AdmSysParameterResourceIT {
//
//    private static final String DEFAULT_SYS_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_SYS_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_SYS_TYPE = "AAAAAAAAAA";
//    private static final String UPDATED_SYS_TYPE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_SYS_VALUE = "AAAAAAAAAA";
//    private static final String UPDATED_SYS_VALUE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DESCRIPT = "AAAAAAAAAA";
//    private static final String UPDATED_DESCRIPT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_STATUS = "A";
//    private static final String UPDATED_STATUS = "B";
//
//    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
//    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";
//
//    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
//    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//    private static final String ENTITY_API_URL = "/api/adm-sys-parameters";
//    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
//
//    private static Random random = new Random();
//    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
//
//    @Autowired
//    private AdmSysParameterRepository admSysParameterRepository;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private MockMvc restAdmSysParameterMockMvc;
//
//    private AdmSysParameter admSysParameter;
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static AdmSysParameter createEntity(EntityManager em) {
//        AdmSysParameter admSysParameter = new AdmSysParameter()
//            .sysName(DEFAULT_SYS_NAME)
//            .sysType(DEFAULT_SYS_TYPE)
//            .sysValue(DEFAULT_SYS_VALUE)
//            .descript(DEFAULT_DESCRIPT)
//            .status(DEFAULT_STATUS)
//            .updateUser(DEFAULT_UPDATE_USER)
//            .updateTime(DEFAULT_UPDATE_TIME);
//        return admSysParameter;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static AdmSysParameter createUpdatedEntity(EntityManager em) {
//        AdmSysParameter admSysParameter = new AdmSysParameter()
//            .sysName(UPDATED_SYS_NAME)
//            .sysType(UPDATED_SYS_TYPE)
//            .sysValue(UPDATED_SYS_VALUE)
//            .descript(UPDATED_DESCRIPT)
//            .status(UPDATED_STATUS)
//            .updateUser(UPDATED_UPDATE_USER)
//            .updateTime(UPDATED_UPDATE_TIME);
//        return admSysParameter;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        admSysParameter = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createAdmSysParameter() throws Exception {
//        int databaseSizeBeforeCreate = admSysParameterRepository.findAll().size();
//        // Create the AdmSysParameter
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isCreated());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeCreate + 1);
//        AdmSysParameter testAdmSysParameter = admSysParameterList.get(admSysParameterList.size() - 1);
//        assertThat(testAdmSysParameter.getSysName()).isEqualTo(DEFAULT_SYS_NAME);
//        assertThat(testAdmSysParameter.getSysType()).isEqualTo(DEFAULT_SYS_TYPE);
//        assertThat(testAdmSysParameter.getSysValue()).isEqualTo(DEFAULT_SYS_VALUE);
//        assertThat(testAdmSysParameter.getDescript()).isEqualTo(DEFAULT_DESCRIPT);
//        assertThat(testAdmSysParameter.getStatus()).isEqualTo(DEFAULT_STATUS);
//        assertThat(testAdmSysParameter.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
//        assertThat(testAdmSysParameter.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    void createAdmSysParameterWithExistingId() throws Exception {
//        // Create the AdmSysParameter with an existing ID
//        admSysParameter.setSysName(DEFAULT_SYS_NAME);
//        admSysParameter.setSysType(DEFAULT_SYS_TYPE);
//
//        int databaseSizeBeforeCreate = admSysParameterRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void checkSysNameIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admSysParameterRepository.findAll().size();
//        // set the field null
//        admSysParameter.setSysName(null);
//
//        // Create the AdmSysParameter, which fails.
//
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkSysTypeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admSysParameterRepository.findAll().size();
//        // set the field null
//        admSysParameter.setSysType(null);
//
//        // Create the AdmSysParameter, which fails.
//
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkSysValueIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admSysParameterRepository.findAll().size();
//        // set the field null
//        admSysParameter.setSysValue(null);
//
//        // Create the AdmSysParameter, which fails.
//
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkDescriptIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admSysParameterRepository.findAll().size();
//        // set the field null
//        admSysParameter.setDescript(null);
//
//        // Create the AdmSysParameter, which fails.
//
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkStatusIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admSysParameterRepository.findAll().size();
//        // set the field null
//        admSysParameter.setStatus(null);
//
//        // Create the AdmSysParameter, which fails.
//
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkUpdateUserIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admSysParameterRepository.findAll().size();
//        // set the field null
//        admSysParameter.setUpdateUser(null);
//
//        // Create the AdmSysParameter, which fails.
//
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkUpdateTimeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = admSysParameterRepository.findAll().size();
//        // set the field null
//        admSysParameter.setUpdateTime(null);
//
//        // Create the AdmSysParameter, which fails.
//
//        restAdmSysParameterMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void getAllAdmSysParameters() throws Exception {
//        // Initialize the database
//        admSysParameterRepository.saveAndFlush(admSysParameter);
//
//        // Get all the admSysParameterList
//        restAdmSysParameterMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].sysName").value(hasItem(DEFAULT_SYS_NAME)))
//            .andExpect(jsonPath("$.[*].sysType").value(hasItem(DEFAULT_SYS_TYPE)))
//            .andExpect(jsonPath("$.[*].sysValue").value(hasItem(DEFAULT_SYS_VALUE)))
//            .andExpect(jsonPath("$.[*].descript").value(hasItem(DEFAULT_DESCRIPT)))
//            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
//    }
//
//    @Test
//    @Transactional
//    void getAdmSysParameter() throws Exception {
//        // Initialize the database
//        admSysParameterRepository.saveAndFlush(admSysParameter);
//
//        // Get the admSysParameter
//        restAdmSysParameterMockMvc
//            .perform(get(ENTITY_API_URL_ID, admSysParameter.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.sysName").value(DEFAULT_SYS_NAME))
//            .andExpect(jsonPath("$.sysType").value(DEFAULT_SYS_TYPE))
//            .andExpect(jsonPath("$.sysValue").value(DEFAULT_SYS_VALUE))
//            .andExpect(jsonPath("$.descript").value(DEFAULT_DESCRIPT))
//            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingAdmSysParameter() throws Exception {
//        // Get the admSysParameter
//        restAdmSysParameterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewAdmSysParameter() throws Exception {
//        // Initialize the database
//        admSysParameterRepository.saveAndFlush(admSysParameter);
//
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//
//        // Update the admSysParameter
//        AdmSysParameter updatedAdmSysParameter = admSysParameterRepository.findById(admSysParameter.getId()).get();
//        // Disconnect from session so that the updates on updatedAdmSysParameter are not directly saved in db
//        em.detach(updatedAdmSysParameter);
//        updatedAdmSysParameter
//            .sysName(UPDATED_SYS_NAME)
//            .sysType(UPDATED_SYS_TYPE)
//            .sysValue(UPDATED_SYS_VALUE)
//            .descript(UPDATED_DESCRIPT)
//            .status(UPDATED_STATUS)
//            .updateUser(UPDATED_UPDATE_USER)
//            .updateTime(UPDATED_UPDATE_TIME);
//
//        restAdmSysParameterMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedAdmSysParameter.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmSysParameter))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//        AdmSysParameter testAdmSysParameter = admSysParameterList.get(admSysParameterList.size() - 1);
//        assertThat(testAdmSysParameter.getSysName()).isEqualTo(UPDATED_SYS_NAME);
//        assertThat(testAdmSysParameter.getSysType()).isEqualTo(UPDATED_SYS_TYPE);
//        assertThat(testAdmSysParameter.getSysValue()).isEqualTo(UPDATED_SYS_VALUE);
//        assertThat(testAdmSysParameter.getDescript()).isEqualTo(UPDATED_DESCRIPT);
//        assertThat(testAdmSysParameter.getStatus()).isEqualTo(UPDATED_STATUS);
//        assertThat(testAdmSysParameter.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//        assertThat(testAdmSysParameter.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingAdmSysParameter() throws Exception {
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//        admSysParameter.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restAdmSysParameterMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, admSysParameter.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchAdmSysParameter() throws Exception {
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//        admSysParameter.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restAdmSysParameterMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamAdmSysParameter() throws Exception {
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//        admSysParameter.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restAdmSysParameterMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateAdmSysParameterWithPatch() throws Exception {
//        // Initialize the database
//        admSysParameterRepository.saveAndFlush(admSysParameter);
//
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//
//        // Update the admSysParameter using partial update
//        AdmSysParameter partialUpdatedAdmSysParameter = new AdmSysParameter();
//        partialUpdatedAdmSysParameter.setId(admSysParameter.getId());
//
//        partialUpdatedAdmSysParameter.sysType(UPDATED_SYS_TYPE).updateTime(UPDATED_UPDATE_TIME);
//
//        restAdmSysParameterMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedAdmSysParameter.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmSysParameter))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//        AdmSysParameter testAdmSysParameter = admSysParameterList.get(admSysParameterList.size() - 1);
//        assertThat(testAdmSysParameter.getSysName()).isEqualTo(DEFAULT_SYS_NAME);
//        assertThat(testAdmSysParameter.getSysType()).isEqualTo(UPDATED_SYS_TYPE);
//        assertThat(testAdmSysParameter.getSysValue()).isEqualTo(DEFAULT_SYS_VALUE);
//        assertThat(testAdmSysParameter.getDescript()).isEqualTo(DEFAULT_DESCRIPT);
//        assertThat(testAdmSysParameter.getStatus()).isEqualTo(DEFAULT_STATUS);
//        assertThat(testAdmSysParameter.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
//        assertThat(testAdmSysParameter.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateAdmSysParameterWithPatch() throws Exception {
//        // Initialize the database
//        admSysParameterRepository.saveAndFlush(admSysParameter);
//
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//
//        // Update the admSysParameter using partial update
//        AdmSysParameter partialUpdatedAdmSysParameter = new AdmSysParameter();
//        partialUpdatedAdmSysParameter.setId(admSysParameter.getId());
//
//        partialUpdatedAdmSysParameter
//            .sysName(UPDATED_SYS_NAME)
//            .sysType(UPDATED_SYS_TYPE)
//            .sysValue(UPDATED_SYS_VALUE)
//            .descript(UPDATED_DESCRIPT)
//            .status(UPDATED_STATUS)
//            .updateUser(UPDATED_UPDATE_USER)
//            .updateTime(UPDATED_UPDATE_TIME);
//
//        restAdmSysParameterMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedAdmSysParameter.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmSysParameter))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//        AdmSysParameter testAdmSysParameter = admSysParameterList.get(admSysParameterList.size() - 1);
//        assertThat(testAdmSysParameter.getSysName()).isEqualTo(UPDATED_SYS_NAME);
//        assertThat(testAdmSysParameter.getSysType()).isEqualTo(UPDATED_SYS_TYPE);
//        assertThat(testAdmSysParameter.getSysValue()).isEqualTo(UPDATED_SYS_VALUE);
//        assertThat(testAdmSysParameter.getDescript()).isEqualTo(UPDATED_DESCRIPT);
//        assertThat(testAdmSysParameter.getStatus()).isEqualTo(UPDATED_STATUS);
//        assertThat(testAdmSysParameter.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
//        assertThat(testAdmSysParameter.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingAdmSysParameter() throws Exception {
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//        admSysParameter.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restAdmSysParameterMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, admSysParameter.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchAdmSysParameter() throws Exception {
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//        admSysParameter.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restAdmSysParameterMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamAdmSysParameter() throws Exception {
//        int databaseSizeBeforeUpdate = admSysParameterRepository.findAll().size();
//        admSysParameter.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restAdmSysParameterMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(admSysParameter))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the AdmSysParameter in the database
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteAdmSysParameter() throws Exception {
//        // Initialize the database
//        admSysParameterRepository.saveAndFlush(admSysParameter);
//
//        int databaseSizeBeforeDelete = admSysParameterRepository.findAll().size();
//
//        // Delete the admSysParameter
//        restAdmSysParameterMockMvc
//            .perform(delete(ENTITY_API_URL_ID, admSysParameter.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<AdmSysParameter> admSysParameterList = admSysParameterRepository.findAll();
//        assertThat(admSysParameterList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//}
