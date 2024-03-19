package gov.pcc.pwc.pwb.rest;

import static gov.pcc.pwc.pwb.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.AdmSysCode;
import gov.pcc.pwc.repository.AdmSysCodeRepository;
import java.math.BigDecimal;
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
 * Integration tests for the {@link AdmSysCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdmSysCodeResourceIT {

    private static final String DEFAULT_MODULE_TYPE = "AAA";
    private static final String UPDATED_MODULE_TYPE = "BBB";

    private static final String DEFAULT_DATA_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SORT_NO = new BigDecimal(0);
    private static final BigDecimal UPDATED_SORT_NO = new BigDecimal(1);

    private static final String DEFAULT_DATA_KEY = "AAAAAAAAAA";
    private static final String UPDATED_DATA_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_DESCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_DESCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_EN_CODE_DESCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_EN_CODE_DESCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_VALID = false;
    private static final Boolean UPDATED_IS_VALID = true;

    private static final Instant DEFAULT_VALID_FROM_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_FROM_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_MODIFY = false;
    private static final Boolean UPDATED_IS_MODIFY = true;

    private static final String DEFAULT_ATTR_1 = "AAAAAAAAAA";
    private static final String UPDATED_ATTR_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTR_2 = "AAAAAAAAAA";
    private static final String UPDATED_ATTR_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/adm-sys-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AdmSysCodeRepository admSysCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdmSysCodeMockMvc;

    private AdmSysCode admSysCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmSysCode createEntity(EntityManager em) {
        AdmSysCode admSysCode = new AdmSysCode()
            .moduleType(DEFAULT_MODULE_TYPE)
            .dataType(DEFAULT_DATA_TYPE)
            .sortNo(DEFAULT_SORT_NO)
            .dataKey(DEFAULT_DATA_KEY)
            .codeDescript(DEFAULT_CODE_DESCRIPT)
            .enCodeDescript(DEFAULT_EN_CODE_DESCRIPT)
            .remark(DEFAULT_REMARK)
            .isValid(DEFAULT_IS_VALID)
            .validFromDate(DEFAULT_VALID_FROM_DATE)
            .isModify(DEFAULT_IS_MODIFY)
            .attr1(DEFAULT_ATTR_1)
            .attr2(DEFAULT_ATTR_2)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME);
        return admSysCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmSysCode createUpdatedEntity(EntityManager em) {
        AdmSysCode admSysCode = new AdmSysCode()
            .moduleType(UPDATED_MODULE_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .sortNo(UPDATED_SORT_NO)
            .dataKey(UPDATED_DATA_KEY)
            .codeDescript(DEFAULT_CODE_DESCRIPT)
            .enCodeDescript(DEFAULT_EN_CODE_DESCRIPT)
            .remark(UPDATED_REMARK)
            .isValid(UPDATED_IS_VALID)
            .validFromDate(UPDATED_VALID_FROM_DATE)
            .isModify(UPDATED_IS_MODIFY)
            .attr1(UPDATED_ATTR_1)
            .attr2(UPDATED_ATTR_2)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME);
        return admSysCode;
    }

    @BeforeEach
    public void initTest() {
        admSysCode = createEntity(em);
    }

    @Test
    @Transactional
    void createAdmSysCode() throws Exception {
        int databaseSizeBeforeCreate = admSysCodeRepository.findAll().size();
        // Create the AdmSysCode
        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isCreated());

        // Validate the AdmSysCode in the database
        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeCreate + 1);
        AdmSysCode testAdmSysCode = admSysCodeList.get(admSysCodeList.size() - 1);
        assertThat(testAdmSysCode.getModuleType()).isEqualTo(DEFAULT_MODULE_TYPE);
        assertThat(testAdmSysCode.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testAdmSysCode.getSortNo()).isEqualByComparingTo(DEFAULT_SORT_NO);
        assertThat(testAdmSysCode.getDataKey()).isEqualTo(DEFAULT_DATA_KEY);
        assertThat(testAdmSysCode.getCodeDescript()).isEqualTo(DEFAULT_CODE_DESCRIPT);
        assertThat(testAdmSysCode.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testAdmSysCode.getIsValid()).isEqualTo(DEFAULT_IS_VALID);
        assertThat(testAdmSysCode.getValidFromDate()).isEqualTo(DEFAULT_VALID_FROM_DATE);
        assertThat(testAdmSysCode.getIsModify()).isEqualTo(DEFAULT_IS_MODIFY);
        assertThat(testAdmSysCode.getAttr1()).isEqualTo(DEFAULT_ATTR_1);
        assertThat(testAdmSysCode.getAttr2()).isEqualTo(DEFAULT_ATTR_2);
        assertThat(testAdmSysCode.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testAdmSysCode.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testAdmSysCode.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testAdmSysCode.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    void createAdmSysCodeWithExistingId() throws Exception {
        // Create the AdmSysCode with an existing ID
        admSysCode.setModuleType(DEFAULT_MODULE_TYPE);
        admSysCode.setDataType(DEFAULT_DATA_TYPE);
        admSysCode.setDataKey(DEFAULT_DATA_KEY);

        int databaseSizeBeforeCreate = admSysCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        // Validate the AdmSysCode in the database
        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkModuleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setModuleType(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDataTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setDataType(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSortNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setSortNo(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDataKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setDataKey(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsValidIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setIsValid(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValidFromDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setValidFromDate(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsModifyIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setIsModify(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setCreateUser(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setCreateTime(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setUpdateUser(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = admSysCodeRepository.findAll().size();
        // set the field null
        admSysCode.setUpdateTime(null);

        // Create the AdmSysCode, which fails.

        restAdmSysCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
            .andExpect(status().isBadRequest());

        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
        assertThat(admSysCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAdmSysCodes() throws Exception {
        // Initialize the database
        admSysCodeRepository.saveAndFlush(admSysCode);

        // Get all the admSysCodeList
        restAdmSysCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].moduleType").value(hasItem(DEFAULT_MODULE_TYPE)))
            .andExpect(jsonPath("$.[*].dataType").value(hasItem(DEFAULT_DATA_TYPE)))
            .andExpect(jsonPath("$.[*].sortNo").value(hasItem(sameNumber(DEFAULT_SORT_NO))))
            .andExpect(jsonPath("$.[*].dataKey").value(hasItem(DEFAULT_DATA_KEY)))
            .andExpect(jsonPath("$.[*].codeDescript").value(hasItem(DEFAULT_CODE_DESCRIPT)))
            .andExpect(jsonPath("$.[*].enCodeDescript").value(hasItem(DEFAULT_EN_CODE_DESCRIPT)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].isValid").value(hasItem(DEFAULT_IS_VALID.booleanValue())))
            .andExpect(jsonPath("$.[*].validFromDate").value(hasItem(DEFAULT_VALID_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].isModify").value(hasItem(DEFAULT_IS_MODIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].attr1").value(hasItem(DEFAULT_ATTR_1)))
            .andExpect(jsonPath("$.[*].attr2").value(hasItem(DEFAULT_ATTR_2)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getAdmSysCode() throws Exception {
        // Initialize the database
        admSysCodeRepository.saveAndFlush(admSysCode);

        // Get the admSysCode
        restAdmSysCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, admSysCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.moduleType").value(DEFAULT_MODULE_TYPE))
            .andExpect(jsonPath("$.dataType").value(DEFAULT_DATA_TYPE))
            .andExpect(jsonPath("$.sortNo").value(sameNumber(DEFAULT_SORT_NO)))
            .andExpect(jsonPath("$.dataKey").value(DEFAULT_DATA_KEY))
            .andExpect(jsonPath("$.codeDescript").value(DEFAULT_CODE_DESCRIPT))
            .andExpect(jsonPath("$.enCodeDescript").value(DEFAULT_EN_CODE_DESCRIPT))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.isValid").value(DEFAULT_IS_VALID.booleanValue()))
            .andExpect(jsonPath("$.validFromDate").value(DEFAULT_VALID_FROM_DATE.toString()))
            .andExpect(jsonPath("$.isModify").value(DEFAULT_IS_MODIFY.booleanValue()))
            .andExpect(jsonPath("$.attr1").value(DEFAULT_ATTR_1))
            .andExpect(jsonPath("$.attr2").value(DEFAULT_ATTR_2))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAdmSysCode() throws Exception {
        // Get the admSysCode
        restAdmSysCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
    //    @Test
    //    @Transactional
    //    void putNewAdmSysCode() throws Exception {
    //        // Initialize the database
    //        admSysCodeRepository.saveAndFlush(admSysCode);
    //
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //
    //        // Update the admSysCode
    //        AdmSysCode updatedAdmSysCode = admSysCodeRepository.findById(admSysCode.getId()).get();
    //        // Disconnect from session so that the updates on updatedAdmSysCode are not directly saved in db
    //        em.detach(updatedAdmSysCode);
    //        updatedAdmSysCode
    //            .moduleType(UPDATED_MODULE_TYPE)
    //            .dataType(UPDATED_DATA_TYPE)
    //            .sortNo(UPDATED_SORT_NO)
    //            .dataKey(UPDATED_DATA_KEY)
    //            .descript(UPDATED_DESCRIPT)
    //            .remark(UPDATED_REMARK)
    //            .isValid(UPDATED_IS_VALID)
    //            .validFromDate(UPDATED_VALID_FROM_DATE)
    //            .isModify(UPDATED_IS_MODIFY)
    //            .attr1(UPDATED_ATTR_1)
    //            .attr2(UPDATED_ATTR_2)
    //            .createUser(UPDATED_CREATE_USER)
    //            .createTime(UPDATED_CREATE_TIME)
    //            .updateUser(UPDATED_UPDATE_USER)
    //            .updateTime(UPDATED_UPDATE_TIME);
    //
    //        restAdmSysCodeMockMvc
    //            .perform(
    //                put(ENTITY_API_URL_ID, updatedAdmSysCode.getId())
    //                    .contentType(MediaType.APPLICATION_JSON)
    //                    .content(TestUtil.convertObjectToJsonBytes(updatedAdmSysCode))
    //            )
    //            .andExpect(status().isOk());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //        AdmSysCode testAdmSysCode = admSysCodeList.get(admSysCodeList.size() - 1);
    //        assertThat(testAdmSysCode.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
    //        assertThat(testAdmSysCode.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
    //        assertThat(testAdmSysCode.getSortNo()).isEqualTo(UPDATED_SORT_NO);
    //        assertThat(testAdmSysCode.getDataKey()).isEqualTo(UPDATED_DATA_KEY);
    //        assertThat(testAdmSysCode.getDescript()).isEqualTo(UPDATED_DESCRIPT);
    //        assertThat(testAdmSysCode.getRemark()).isEqualTo(UPDATED_REMARK);
    //        assertThat(testAdmSysCode.getIsValid()).isEqualTo(UPDATED_IS_VALID);
    //        assertThat(testAdmSysCode.getValidFromDate()).isEqualTo(UPDATED_VALID_FROM_DATE);
    //        assertThat(testAdmSysCode.getIsModify()).isEqualTo(UPDATED_IS_MODIFY);
    //        assertThat(testAdmSysCode.getAttr1()).isEqualTo(UPDATED_ATTR_1);
    //        assertThat(testAdmSysCode.getAttr2()).isEqualTo(UPDATED_ATTR_2);
    //        assertThat(testAdmSysCode.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    //        assertThat(testAdmSysCode.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    //        assertThat(testAdmSysCode.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    //        assertThat(testAdmSysCode.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void putNonExistingAdmSysCode() throws Exception {
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //        admSysCode.setId(count.incrementAndGet());
    //
    //        // If the entity doesn't have an ID, it will throw BadRequestAlertException
    //        restAdmSysCodeMockMvc
    //            .perform(
    //                put(ENTITY_API_URL_ID, admSysCode.getId())
    //                    .contentType(MediaType.APPLICATION_JSON)
    //                    .content(TestUtil.convertObjectToJsonBytes(admSysCode))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void putWithIdMismatchAdmSysCode() throws Exception {
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //        admSysCode.setId(count.incrementAndGet());
    //
    //        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    //        restAdmSysCodeMockMvc
    //            .perform(
    //                put(ENTITY_API_URL_ID, count.incrementAndGet())
    //                    .contentType(MediaType.APPLICATION_JSON)
    //                    .content(TestUtil.convertObjectToJsonBytes(admSysCode))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void putWithMissingIdPathParamAdmSysCode() throws Exception {
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //        admSysCode.setId(count.incrementAndGet());
    //
    //        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    //        restAdmSysCodeMockMvc
    //            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(admSysCode)))
    //            .andExpect(status().isMethodNotAllowed());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void partialUpdateAdmSysCodeWithPatch() throws Exception {
    //        // Initialize the database
    //        admSysCodeRepository.saveAndFlush(admSysCode);
    //
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //
    //        // Update the admSysCode using partial update
    //        AdmSysCode partialUpdatedAdmSysCode = new AdmSysCode();
    //        partialUpdatedAdmSysCode.setId(admSysCode.getId());
    //
    //        partialUpdatedAdmSysCode
    //            .moduleType(UPDATED_MODULE_TYPE)
    //            .dataType(UPDATED_DATA_TYPE)
    //            .sortNo(UPDATED_SORT_NO)
    //            .descript(UPDATED_DESCRIPT)
    //            .isValid(UPDATED_IS_VALID)
    //            .attr1(UPDATED_ATTR_1)
    //            .updateTime(UPDATED_UPDATE_TIME);
    //
    //        restAdmSysCodeMockMvc
    //            .perform(
    //                patch(ENTITY_API_URL_ID, partialUpdatedAdmSysCode.getId())
    //                    .contentType("application/merge-patch+json")
    //                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmSysCode))
    //            )
    //            .andExpect(status().isOk());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //        AdmSysCode testAdmSysCode = admSysCodeList.get(admSysCodeList.size() - 1);
    //        assertThat(testAdmSysCode.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
    //        assertThat(testAdmSysCode.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
    //        assertThat(testAdmSysCode.getSortNo()).isEqualByComparingTo(UPDATED_SORT_NO);
    //        assertThat(testAdmSysCode.getDataKey()).isEqualTo(DEFAULT_DATA_KEY);
    //        assertThat(testAdmSysCode.getDescript()).isEqualTo(UPDATED_DESCRIPT);
    //        assertThat(testAdmSysCode.getRemark()).isEqualTo(DEFAULT_REMARK);
    //        assertThat(testAdmSysCode.getIsValid()).isEqualTo(UPDATED_IS_VALID);
    //        assertThat(testAdmSysCode.getValidFromDate()).isEqualTo(DEFAULT_VALID_FROM_DATE);
    //        assertThat(testAdmSysCode.getIsModify()).isEqualTo(DEFAULT_IS_MODIFY);
    //        assertThat(testAdmSysCode.getAttr1()).isEqualTo(UPDATED_ATTR_1);
    //        assertThat(testAdmSysCode.getAttr2()).isEqualTo(DEFAULT_ATTR_2);
    //        assertThat(testAdmSysCode.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    //        assertThat(testAdmSysCode.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    //        assertThat(testAdmSysCode.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    //        assertThat(testAdmSysCode.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void fullUpdateAdmSysCodeWithPatch() throws Exception {
    //        // Initialize the database
    //        admSysCodeRepository.saveAndFlush(admSysCode);
    //
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //
    //        // Update the admSysCode using partial update
    //        AdmSysCode partialUpdatedAdmSysCode = new AdmSysCode();
    //        partialUpdatedAdmSysCode.setId(admSysCode.getId());
    //
    //        partialUpdatedAdmSysCode
    //            .moduleType(UPDATED_MODULE_TYPE)
    //            .dataType(UPDATED_DATA_TYPE)
    //            .sortNo(UPDATED_SORT_NO)
    //            .dataKey(UPDATED_DATA_KEY)
    //            .descript(UPDATED_DESCRIPT)
    //            .remark(UPDATED_REMARK)
    //            .isValid(UPDATED_IS_VALID)
    //            .validFromDate(UPDATED_VALID_FROM_DATE)
    //            .isModify(UPDATED_IS_MODIFY)
    //            .attr1(UPDATED_ATTR_1)
    //            .attr2(UPDATED_ATTR_2)
    //            .createUser(UPDATED_CREATE_USER)
    //            .createTime(UPDATED_CREATE_TIME)
    //            .updateUser(UPDATED_UPDATE_USER)
    //            .updateTime(UPDATED_UPDATE_TIME);
    //
    //        restAdmSysCodeMockMvc
    //            .perform(
    //                patch(ENTITY_API_URL_ID, partialUpdatedAdmSysCode.getId())
    //                    .contentType("application/merge-patch+json")
    //                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAdmSysCode))
    //            )
    //            .andExpect(status().isOk());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //        AdmSysCode testAdmSysCode = admSysCodeList.get(admSysCodeList.size() - 1);
    //        assertThat(testAdmSysCode.getModuleType()).isEqualTo(UPDATED_MODULE_TYPE);
    //        assertThat(testAdmSysCode.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
    //        assertThat(testAdmSysCode.getSortNo()).isEqualByComparingTo(UPDATED_SORT_NO);
    //        assertThat(testAdmSysCode.getDataKey()).isEqualTo(UPDATED_DATA_KEY);
    //        assertThat(testAdmSysCode.getDescript()).isEqualTo(UPDATED_DESCRIPT);
    //        assertThat(testAdmSysCode.getRemark()).isEqualTo(UPDATED_REMARK);
    //        assertThat(testAdmSysCode.getIsValid()).isEqualTo(UPDATED_IS_VALID);
    //        assertThat(testAdmSysCode.getValidFromDate()).isEqualTo(UPDATED_VALID_FROM_DATE);
    //        assertThat(testAdmSysCode.getIsModify()).isEqualTo(UPDATED_IS_MODIFY);
    //        assertThat(testAdmSysCode.getAttr1()).isEqualTo(UPDATED_ATTR_1);
    //        assertThat(testAdmSysCode.getAttr2()).isEqualTo(UPDATED_ATTR_2);
    //        assertThat(testAdmSysCode.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    //        assertThat(testAdmSysCode.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    //        assertThat(testAdmSysCode.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    //        assertThat(testAdmSysCode.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void patchNonExistingAdmSysCode() throws Exception {
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //        admSysCode.setId(count.incrementAndGet());
    //
    //        // If the entity doesn't have an ID, it will throw BadRequestAlertException
    //        restAdmSysCodeMockMvc
    //            .perform(
    //                patch(ENTITY_API_URL_ID, admSysCode.getId())
    //                    .contentType("application/merge-patch+json")
    //                    .content(TestUtil.convertObjectToJsonBytes(admSysCode))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void patchWithIdMismatchAdmSysCode() throws Exception {
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //        admSysCode.setId(count.incrementAndGet());
    //
    //        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    //        restAdmSysCodeMockMvc
    //            .perform(
    //                patch(ENTITY_API_URL_ID, count.incrementAndGet())
    //                    .contentType("application/merge-patch+json")
    //                    .content(TestUtil.convertObjectToJsonBytes(admSysCode))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void patchWithMissingIdPathParamAdmSysCode() throws Exception {
    //        int databaseSizeBeforeUpdate = admSysCodeRepository.findAll().size();
    //        admSysCode.setId(count.incrementAndGet());
    //
    //        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    //        restAdmSysCodeMockMvc
    //            .perform(
    //                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(admSysCode))
    //            )
    //            .andExpect(status().isMethodNotAllowed());
    //
    //        // Validate the AdmSysCode in the database
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //
    //    @Test
    //    @Transactional
    //    void deleteAdmSysCode() throws Exception {
    //        // Initialize the database
    //        admSysCodeRepository.saveAndFlush(admSysCode);
    //
    //        int databaseSizeBeforeDelete = admSysCodeRepository.findAll().size();
    //
    //        // Delete the admSysCode
    //        restAdmSysCodeMockMvc
    //            .perform(delete(ENTITY_API_URL_ID, admSysCode.getId()).accept(MediaType.APPLICATION_JSON))
    //            .andExpect(status().isNoContent());
    //
    //        // Validate the database contains one less item
    //        List<AdmSysCode> admSysCodeList = admSysCodeRepository.findAll();
    //        assertThat(admSysCodeList).hasSize(databaseSizeBeforeDelete - 1);
    //    }
}
