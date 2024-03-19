package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmLoginHis;
import gov.pcc.pwc.repository.AdmLoginHisRepository;
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
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdmLoginHisResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmLoginHisResourceIT {

    private static final String DEFAULT_SYS_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SYS_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT = "A";
    private static final String UPDATED_RESULT = "B";

    private static final String DEFAULT_DESCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_IP = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_IP = "BBBBBBBBBB";

    private static final String DEFAULT_BROWSER = "AAAAAAAAAA";
    private static final String UPDATED_BROWSER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER_SYS = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER_SYS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/adm-login-his";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmLoginHisRepository admLoginHisRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmLoginHisMockMvc;

    private AdmLoginHis admLoginHis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmLoginHis createEntity(EntityManager em) {
        AdmLoginHis admLoginHis = new AdmLoginHis()
            .sysCategory(DEFAULT_SYS_CATEGORY)
            .result(DEFAULT_RESULT)
            .descript(DEFAULT_DESCRIPT)
            .sourceIp(DEFAULT_SOURCE_IP)
            .browser(DEFAULT_BROWSER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUserSys(DEFAULT_CREATE_USER_SYS);
        return admLoginHis;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmLoginHis createUpdatedEntity(EntityManager em) {
        AdmLoginHis admLoginHis = new AdmLoginHis()
            .sysCategory(UPDATED_SYS_CATEGORY)
            .result(UPDATED_RESULT)
            .descript(UPDATED_DESCRIPT)
            .sourceIp(UPDATED_SOURCE_IP)
            .browser(UPDATED_BROWSER)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);
        return admLoginHis;
    }

    @BeforeEach
    public void initTest() {
        admLoginHis = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmLoginHis() throws Exception {
        int databaseSizeBeforeCreate = admLoginHisRepository.findAll().size();
        // Create the AdmLoginHis
        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isCreated());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeCreate + 1);
        AdmLoginHis testAdmLoginHis = admLoginHisList.get(admLoginHisList.size() - 1);
        assertThat(testAdmLoginHis.getSysCategory()).isEqualTo(DEFAULT_SYS_CATEGORY);
        assertThat(testAdmLoginHis.getResult()).isEqualTo(DEFAULT_RESULT);
        assertThat(testAdmLoginHis.getDescript()).isEqualTo(DEFAULT_DESCRIPT);
        assertThat(testAdmLoginHis.getSourceIp()).isEqualTo(DEFAULT_SOURCE_IP);
        assertThat(testAdmLoginHis.getBrowser()).isEqualTo(DEFAULT_BROWSER);
        assertThat(testAdmLoginHis.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmLoginHis.getCreateUserSys()).isEqualTo(DEFAULT_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void createAdmLoginHisWithExistingId() throws Exception {
        // Create the AdmLoginHis with an existing ID
        admLoginHis.setId(1L);

        int databaseSizeBeforeCreate = admLoginHisRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isBadRequest());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSysCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLoginHisRepository.findAll().size();
        // set the field null
        admLoginHis.setSysCategory(null);

        // Create the AdmLoginHis, which fails.

        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isBadRequest());

        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLoginHisRepository.findAll().size();
        // set the field null
        admLoginHis.setResult(null);

        // Create the AdmLoginHis, which fails.

        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isBadRequest());

        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLoginHisRepository.findAll().size();
        // set the field null
        admLoginHis.setDescript(null);

        // Create the AdmLoginHis, which fails.

        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isBadRequest());

        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSourceIpIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLoginHisRepository.findAll().size();
        // set the field null
        admLoginHis.setSourceIp(null);

        // Create the AdmLoginHis, which fails.

        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isBadRequest());

        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBrowserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLoginHisRepository.findAll().size();
        // set the field null
        admLoginHis.setBrowser(null);

        // Create the AdmLoginHis, which fails.

        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isBadRequest());

        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLoginHisRepository.findAll().size();
        // set the field null
        admLoginHis.setCreateTime(null);

        // Create the AdmLoginHis, which fails.

        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isBadRequest());

        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserSysIsRequired() throws Exception {
        int databaseSizeBeforeTest = admLoginHisRepository.findAll().size();
        // set the field null
        admLoginHis.setCreateUserSys(null);

        // Create the AdmLoginHis, which fails.

        restAdmLoginHisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isBadRequest());

        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmLoginHis() throws Exception {
        // Initialize the database
        admLoginHisRepository.saveAndFlush(admLoginHis);

        // Get all the admLoginHisList
        restAdmLoginHisMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admLoginHis.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysCategory").value(hasItem(DEFAULT_SYS_CATEGORY)))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)))
            .andExpect(jsonPath("$.[*].descript").value(hasItem(DEFAULT_DESCRIPT)))
            .andExpect(jsonPath("$.[*].sourceIp").value(hasItem(DEFAULT_SOURCE_IP)))
            .andExpect(jsonPath("$.[*].browser").value(hasItem(DEFAULT_BROWSER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUserSys").value(hasItem(DEFAULT_CREATE_USER_SYS)));
    }

    @Test
    @Transactional
    void getAdmLoginHis() throws Exception {
        // Initialize the database
        admLoginHisRepository.saveAndFlush(admLoginHis);

        // Get the admLoginHis
        restAdmLoginHisMockMvc
            .perform(get(ENTITY_API_URL_ID, admLoginHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(admLoginHis.getId().intValue()))
            .andExpect(jsonPath("$.sysCategory").value(DEFAULT_SYS_CATEGORY))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT))
            .andExpect(jsonPath("$.descript").value(DEFAULT_DESCRIPT))
            .andExpect(jsonPath("$.sourceIp").value(DEFAULT_SOURCE_IP))
            .andExpect(jsonPath("$.browser").value(DEFAULT_BROWSER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUserSys").value(DEFAULT_CREATE_USER_SYS));
    }

    @Test
    @Transactional
    void getNonExistingAdmLoginHis() throws Exception {
        // Get the admLoginHis
        restAdmLoginHisMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAdmLoginHis() throws Exception {
        // Initialize the database
        admLoginHisRepository.saveAndFlush(admLoginHis);

        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();

        // Update the admLoginHis
        AdmLoginHis updatedAdmLoginHis = admLoginHisRepository.findById(admLoginHis.getId()).get();
        // Disconnect from session so that the updates on updatedAdmLoginHis are not directly saved in db
        em.detach(updatedAdmLoginHis);
        updatedAdmLoginHis
            .sysCategory(UPDATED_SYS_CATEGORY)
            .result(UPDATED_RESULT)
            .descript(UPDATED_DESCRIPT)
            .sourceIp(UPDATED_SOURCE_IP)
            .browser(UPDATED_BROWSER)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);

        restAdmLoginHisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAdmLoginHis.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmLoginHis))
            )
            .andExpect(status().isOk());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
        AdmLoginHis testAdmLoginHis = admLoginHisList.get(admLoginHisList.size() - 1);
        assertThat(testAdmLoginHis.getSysCategory()).isEqualTo(UPDATED_SYS_CATEGORY);
        assertThat(testAdmLoginHis.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testAdmLoginHis.getDescript()).isEqualTo(UPDATED_DESCRIPT);
        assertThat(testAdmLoginHis.getSourceIp()).isEqualTo(UPDATED_SOURCE_IP);
        assertThat(testAdmLoginHis.getBrowser()).isEqualTo(UPDATED_BROWSER);
        assertThat(testAdmLoginHis.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmLoginHis.getCreateUserSys()).isEqualTo(UPDATED_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void putNonExistingAdmLoginHis() throws Exception {
        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();
        admLoginHis.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmLoginHisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, admLoginHis.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admLoginHis))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdmLoginHis() throws Exception {
        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();
        admLoginHis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLoginHisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(admLoginHis))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdmLoginHis() throws Exception {
        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();
        admLoginHis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLoginHisMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admLoginHis)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdmLoginHisWithPatch() throws Exception {
        // Initialize the database
        admLoginHisRepository.saveAndFlush(admLoginHis);

        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();

        // Update the admLoginHis using partial update
        AdmLoginHis partialUpdatedAdmLoginHis = new AdmLoginHis();
        partialUpdatedAdmLoginHis.setId(admLoginHis.getId());

        partialUpdatedAdmLoginHis.result(UPDATED_RESULT).createUserSys(UPDATED_CREATE_USER_SYS);

        restAdmLoginHisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmLoginHis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmLoginHis))
            )
            .andExpect(status().isOk());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
        AdmLoginHis testAdmLoginHis = admLoginHisList.get(admLoginHisList.size() - 1);
        assertThat(testAdmLoginHis.getSysCategory()).isEqualTo(DEFAULT_SYS_CATEGORY);
        assertThat(testAdmLoginHis.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testAdmLoginHis.getDescript()).isEqualTo(DEFAULT_DESCRIPT);
        assertThat(testAdmLoginHis.getSourceIp()).isEqualTo(DEFAULT_SOURCE_IP);
        assertThat(testAdmLoginHis.getBrowser()).isEqualTo(DEFAULT_BROWSER);
        assertThat(testAdmLoginHis.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmLoginHis.getCreateUserSys()).isEqualTo(UPDATED_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void fullUpdateAdmLoginHisWithPatch() throws Exception {
        // Initialize the database
        admLoginHisRepository.saveAndFlush(admLoginHis);

        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();

        // Update the admLoginHis using partial update
        AdmLoginHis partialUpdatedAdmLoginHis = new AdmLoginHis();
        partialUpdatedAdmLoginHis.setId(admLoginHis.getId());

        partialUpdatedAdmLoginHis
            .sysCategory(UPDATED_SYS_CATEGORY)
            .result(UPDATED_RESULT)
            .descript(UPDATED_DESCRIPT)
            .sourceIp(UPDATED_SOURCE_IP)
            .browser(UPDATED_BROWSER)
            .createTime(UPDATED_CREATE_TIME)
            .createUserSys(UPDATED_CREATE_USER_SYS);

        restAdmLoginHisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdmLoginHis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmLoginHis))
            )
            .andExpect(status().isOk());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
        AdmLoginHis testAdmLoginHis = admLoginHisList.get(admLoginHisList.size() - 1);
        assertThat(testAdmLoginHis.getSysCategory()).isEqualTo(UPDATED_SYS_CATEGORY);
        assertThat(testAdmLoginHis.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testAdmLoginHis.getDescript()).isEqualTo(UPDATED_DESCRIPT);
        assertThat(testAdmLoginHis.getSourceIp()).isEqualTo(UPDATED_SOURCE_IP);
        assertThat(testAdmLoginHis.getBrowser()).isEqualTo(UPDATED_BROWSER);
        assertThat(testAdmLoginHis.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testAdmLoginHis.getCreateUserSys()).isEqualTo(UPDATED_CREATE_USER_SYS);
    }

    @Test
    @Transactional
    void patchNonExistingAdmLoginHis() throws Exception {
        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();
        admLoginHis.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmLoginHisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, admLoginHis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admLoginHis))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdmLoginHis() throws Exception {
        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();
        admLoginHis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLoginHisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(admLoginHis))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdmLoginHis() throws Exception {
        int databaseSizeBeforeUpdate = admLoginHisRepository.findAll().size();
        admLoginHis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdmLoginHisMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admLoginHis))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdmLoginHis in the database
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdmLoginHis() throws Exception {
        // Initialize the database
        admLoginHisRepository.saveAndFlush(admLoginHis);

        int databaseSizeBeforeDelete = admLoginHisRepository.findAll().size();

        // Delete the admLoginHis
        restAdmLoginHisMockMvc
            .perform(delete(ENTITY_API_URL_ID, admLoginHis.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmLoginHis> admLoginHisList = admLoginHisRepository.findAll();
        assertThat(admLoginHisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
