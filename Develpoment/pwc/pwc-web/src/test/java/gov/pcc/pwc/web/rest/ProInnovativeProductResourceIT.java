package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.ProInnovativeProduct;
import gov.pcc.pwc.repository.ProInnovativeProductRepository;
import gov.pcc.pwc.service.dto.ProInnovativeProductDTO;
import gov.pcc.pwc.service.mapper.ProInnovativeProductMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
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
 * Integration tests for the {@link ProInnovativeProductResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProInnovativeProductResourceIT {

    private static final String DEFAULT_ACCEPTED_ORGANIZATION = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPTED_ORGANIZATION = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INNOVATE_TYPE = "AA";
    private static final String UPDATED_INNOVATE_TYPE = "BB";

    private static final String DEFAULT_INNOVATE_FUNCTION = "AAAAAAAAAA";
    private static final String UPDATED_INNOVATE_FUNCTION = "BBBBBBBBBB";

    private static final String DEFAULT_INNOVATE_QUALITY = "AAAAAAAAAA";
    private static final String UPDATED_INNOVATE_QUALITY = "BBBBBBBBBB";

    private static final String DEFAULT_INNOVATE_PROVE = "AAAAAAAAAA";
    private static final String UPDATED_INNOVATE_PROVE = "BBBBBBBBBB";

    private static final String DEFAULT_INNOVATE_CASE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INNOVATE_CASE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final String DEFAULT_INNOVATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INNOVATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATENT = "A";
    private static final String UPDATED_PATENT = "B";

    private static final Instant DEFAULT_PATENT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PATENT_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PATENT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PATENT_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CONSTRUCT_COST = "AAAAAAAAAA";
    private static final String UPDATED_CONSTRUCT_COST = "BBBBBBBBBB";

    private static final String DEFAULT_MAINTAIN_COST = "AAAAAAAAAA";
    private static final String UPDATED_MAINTAIN_COST = "BBBBBBBBBB";

    private static final Instant DEFAULT_COMMUNICATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_COMMUNICATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_COMMUNICATE_DOC_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATE_DOC_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_INNOVATE_CASE_VENDOR = "AAAAAAAAAA";
    private static final String UPDATED_INNOVATE_CASE_VENDOR = "BBBBBBBBBB";

    private static final String DEFAULT_INNOVATE_CASE_SCALE = "AAAAAAAAAA";
    private static final String UPDATED_INNOVATE_CASE_SCALE = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/pro-innovative-products";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proInnovativeProductId}";

    @Autowired
    private ProInnovativeProductRepository proInnovativeProductRepository;

    @Autowired
    private ProInnovativeProductMapper proInnovativeProductMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProInnovativeProductMockMvc;

    private ProInnovativeProduct proInnovativeProduct;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProInnovativeProduct createEntity(EntityManager em) {
        ProInnovativeProduct proInnovativeProduct = new ProInnovativeProduct()
            .userId(DEFAULT_USER_ID)
            .innovateType(DEFAULT_INNOVATE_TYPE)
            .innovateFunction(DEFAULT_INNOVATE_FUNCTION)
            .innovateQuality(DEFAULT_INNOVATE_QUALITY)
            .innovateProve(DEFAULT_INNOVATE_PROVE)
            .innovateCaseName(DEFAULT_INNOVATE_CASE_NAME)
            .status(DEFAULT_STATUS)
            .innovateName(DEFAULT_INNOVATE_NAME)
            .patent(DEFAULT_PATENT)
            .patentStartDate(DEFAULT_PATENT_START_DATE)
            .patentEndDate(DEFAULT_PATENT_END_DATE)
            .constructCost(DEFAULT_CONSTRUCT_COST)
            .maintainCost(DEFAULT_MAINTAIN_COST)
            .communicateDate(DEFAULT_COMMUNICATE_DATE)
            .communicateDocNumber(DEFAULT_COMMUNICATE_DOC_NUMBER)
            .innovateCaseVendor(DEFAULT_INNOVATE_CASE_VENDOR)
            .innovateCaseScale(DEFAULT_INNOVATE_CASE_SCALE)
            .updateUser(DEFAULT_UPDATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return proInnovativeProduct;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProInnovativeProduct createUpdatedEntity(EntityManager em) {
        ProInnovativeProduct proInnovativeProduct = new ProInnovativeProduct()
            .userId(UPDATED_USER_ID)
            .innovateType(UPDATED_INNOVATE_TYPE)
            .innovateFunction(UPDATED_INNOVATE_FUNCTION)
            .innovateQuality(UPDATED_INNOVATE_QUALITY)
            .innovateProve(UPDATED_INNOVATE_PROVE)
            .innovateCaseName(UPDATED_INNOVATE_CASE_NAME)
            .status(UPDATED_STATUS)
            .innovateName(UPDATED_INNOVATE_NAME)
            .patent(UPDATED_PATENT)
            .patentStartDate(UPDATED_PATENT_START_DATE)
            .patentEndDate(UPDATED_PATENT_END_DATE)
            .constructCost(UPDATED_CONSTRUCT_COST)
            .maintainCost(UPDATED_MAINTAIN_COST)
            .communicateDate(UPDATED_COMMUNICATE_DATE)
            .communicateDocNumber(UPDATED_COMMUNICATE_DOC_NUMBER)
            .innovateCaseVendor(UPDATED_INNOVATE_CASE_VENDOR)
            .innovateCaseScale(UPDATED_INNOVATE_CASE_SCALE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        return proInnovativeProduct;
    }

    @BeforeEach
    public void initTest() {
        proInnovativeProduct = createEntity(em);
    }

    @Test
    @Transactional
    void createProInnovativeProduct() throws Exception {
        int databaseSizeBeforeCreate = proInnovativeProductRepository.findAll().size();
        // Create the ProInnovativeProduct
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(proInnovativeProduct);
        restProInnovativeProductMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeCreate + 1);
        ProInnovativeProduct testProInnovativeProduct = proInnovativeProductList.get(proInnovativeProductList.size() - 1);
        assertThat(testProInnovativeProduct.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testProInnovativeProduct.getInnovateType()).isEqualTo(DEFAULT_INNOVATE_TYPE);
        assertThat(testProInnovativeProduct.getInnovateFunction()).isEqualTo(DEFAULT_INNOVATE_FUNCTION);
        assertThat(testProInnovativeProduct.getInnovateQuality()).isEqualTo(DEFAULT_INNOVATE_QUALITY);
        assertThat(testProInnovativeProduct.getInnovateProve()).isEqualTo(DEFAULT_INNOVATE_PROVE);
        assertThat(testProInnovativeProduct.getInnovateCaseName()).isEqualTo(DEFAULT_INNOVATE_CASE_NAME);
        assertThat(testProInnovativeProduct.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProInnovativeProduct.getInnovateName()).isEqualTo(DEFAULT_INNOVATE_NAME);
        assertThat(testProInnovativeProduct.getPatent()).isEqualTo(DEFAULT_PATENT);
        assertThat(testProInnovativeProduct.getPatentStartDate()).isEqualTo(DEFAULT_PATENT_START_DATE);
        assertThat(testProInnovativeProduct.getPatentEndDate()).isEqualTo(DEFAULT_PATENT_END_DATE);
        assertThat(testProInnovativeProduct.getConstructCost()).isEqualTo(DEFAULT_CONSTRUCT_COST);
        assertThat(testProInnovativeProduct.getMaintainCost()).isEqualTo(DEFAULT_MAINTAIN_COST);
        assertThat(testProInnovativeProduct.getCommunicateDate()).isEqualTo(DEFAULT_COMMUNICATE_DATE);
        assertThat(testProInnovativeProduct.getCommunicateDocNumber()).isEqualTo(DEFAULT_COMMUNICATE_DOC_NUMBER);
        assertThat(testProInnovativeProduct.getInnovateCaseVendor()).isEqualTo(DEFAULT_INNOVATE_CASE_VENDOR);
        assertThat(testProInnovativeProduct.getInnovateCaseScale()).isEqualTo(DEFAULT_INNOVATE_CASE_SCALE);
        assertThat(testProInnovativeProduct.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProInnovativeProduct.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProInnovativeProduct.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProInnovativeProduct.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void createProInnovativeProductWithExistingId() throws Exception {
        // Create the ProInnovativeProduct with an existing ID
        proInnovativeProduct.setProInnovativeProductId("existing_id");
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(proInnovativeProduct);

        int databaseSizeBeforeCreate = proInnovativeProductRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProInnovativeProductMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProInnovativeProducts() throws Exception {
        // Initialize the database
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());
        proInnovativeProductRepository.saveAndFlush(proInnovativeProduct);

        // Get all the proInnovativeProductList
        restProInnovativeProductMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proInnovativeProductId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proInnovativeProductId").value(hasItem(proInnovativeProduct.getProInnovativeProductId())))
            .andExpect(jsonPath("$.[*].acceptedOrganization").value(hasItem(DEFAULT_ACCEPTED_ORGANIZATION)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].innovateType").value(hasItem(DEFAULT_INNOVATE_TYPE)))
            .andExpect(jsonPath("$.[*].innovateFunction").value(hasItem(DEFAULT_INNOVATE_FUNCTION)))
            .andExpect(jsonPath("$.[*].innovateQuality").value(hasItem(DEFAULT_INNOVATE_QUALITY)))
            .andExpect(jsonPath("$.[*].innovateProve").value(hasItem(DEFAULT_INNOVATE_PROVE)))
            .andExpect(jsonPath("$.[*].innovateCaseName").value(hasItem(DEFAULT_INNOVATE_CASE_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].innovateName").value(hasItem(DEFAULT_INNOVATE_NAME)))
            .andExpect(jsonPath("$.[*].patent").value(hasItem(DEFAULT_PATENT)))
            .andExpect(jsonPath("$.[*].patentStartDate").value(hasItem(DEFAULT_PATENT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].patentEndDate").value(hasItem(DEFAULT_PATENT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].constructCost").value(hasItem(DEFAULT_CONSTRUCT_COST)))
            .andExpect(jsonPath("$.[*].maintainCost").value(hasItem(DEFAULT_MAINTAIN_COST)))
            .andExpect(jsonPath("$.[*].communicateDate").value(hasItem(DEFAULT_COMMUNICATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].communicateDocNumber").value(hasItem(DEFAULT_COMMUNICATE_DOC_NUMBER)))
            .andExpect(jsonPath("$.[*].innovateCaseVendor").value(hasItem(DEFAULT_INNOVATE_CASE_VENDOR)))
            .andExpect(jsonPath("$.[*].innovateCaseScale").value(hasItem(DEFAULT_INNOVATE_CASE_SCALE)))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }

    @Test
    @Transactional
    void getProInnovativeProduct() throws Exception {
        // Initialize the database
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());
        proInnovativeProductRepository.saveAndFlush(proInnovativeProduct);

        // Get the proInnovativeProduct
        restProInnovativeProductMockMvc
            .perform(get(ENTITY_API_URL_ID, proInnovativeProduct.getProInnovativeProductId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proInnovativeProductId").value(proInnovativeProduct.getProInnovativeProductId()))
            .andExpect(jsonPath("$.acceptedOrganization").value(DEFAULT_ACCEPTED_ORGANIZATION))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.innovateType").value(DEFAULT_INNOVATE_TYPE))
            .andExpect(jsonPath("$.innovateFunction").value(DEFAULT_INNOVATE_FUNCTION))
            .andExpect(jsonPath("$.innovateQuality").value(DEFAULT_INNOVATE_QUALITY))
            .andExpect(jsonPath("$.innovateProve").value(DEFAULT_INNOVATE_PROVE))
            .andExpect(jsonPath("$.innovateCaseName").value(DEFAULT_INNOVATE_CASE_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.innovateName").value(DEFAULT_INNOVATE_NAME))
            .andExpect(jsonPath("$.patent").value(DEFAULT_PATENT))
            .andExpect(jsonPath("$.patentStartDate").value(DEFAULT_PATENT_START_DATE.toString()))
            .andExpect(jsonPath("$.patentEndDate").value(DEFAULT_PATENT_END_DATE.toString()))
            .andExpect(jsonPath("$.constructCost").value(DEFAULT_CONSTRUCT_COST))
            .andExpect(jsonPath("$.maintainCost").value(DEFAULT_MAINTAIN_COST))
            .andExpect(jsonPath("$.communicateDate").value(DEFAULT_COMMUNICATE_DATE.toString()))
            .andExpect(jsonPath("$.communicateDocNumber").value(DEFAULT_COMMUNICATE_DOC_NUMBER))
            .andExpect(jsonPath("$.innovateCaseVendor").value(DEFAULT_INNOVATE_CASE_VENDOR))
            .andExpect(jsonPath("$.innovateCaseScale").value(DEFAULT_INNOVATE_CASE_SCALE))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProInnovativeProduct() throws Exception {
        // Get the proInnovativeProduct
        restProInnovativeProductMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProInnovativeProduct() throws Exception {
        // Initialize the database
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());
        proInnovativeProductRepository.saveAndFlush(proInnovativeProduct);

        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();

        // Update the proInnovativeProduct
        ProInnovativeProduct updatedProInnovativeProduct = proInnovativeProductRepository
            .findById(proInnovativeProduct.getProInnovativeProductId())
            .get();
        // Disconnect from session so that the updates on updatedProInnovativeProduct are not directly saved in db
        em.detach(updatedProInnovativeProduct);
        updatedProInnovativeProduct
            .userId(UPDATED_USER_ID)
            .innovateType(UPDATED_INNOVATE_TYPE)
            .innovateFunction(UPDATED_INNOVATE_FUNCTION)
            .innovateQuality(UPDATED_INNOVATE_QUALITY)
            .innovateProve(UPDATED_INNOVATE_PROVE)
            .innovateCaseName(UPDATED_INNOVATE_CASE_NAME)
            .status(UPDATED_STATUS)
            .innovateName(UPDATED_INNOVATE_NAME)
            .patent(UPDATED_PATENT)
            .patentStartDate(UPDATED_PATENT_START_DATE)
            .patentEndDate(UPDATED_PATENT_END_DATE)
            .constructCost(UPDATED_CONSTRUCT_COST)
            .maintainCost(UPDATED_MAINTAIN_COST)
            .communicateDate(UPDATED_COMMUNICATE_DATE)
            .communicateDocNumber(UPDATED_COMMUNICATE_DOC_NUMBER)
            .innovateCaseVendor(UPDATED_INNOVATE_CASE_VENDOR)
            .innovateCaseScale(UPDATED_INNOVATE_CASE_SCALE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(updatedProInnovativeProduct);

        restProInnovativeProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proInnovativeProductDTO.getProInnovativeProductId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
        ProInnovativeProduct testProInnovativeProduct = proInnovativeProductList.get(proInnovativeProductList.size() - 1);
        assertThat(testProInnovativeProduct.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProInnovativeProduct.getInnovateType()).isEqualTo(UPDATED_INNOVATE_TYPE);
        assertThat(testProInnovativeProduct.getInnovateFunction()).isEqualTo(UPDATED_INNOVATE_FUNCTION);
        assertThat(testProInnovativeProduct.getInnovateQuality()).isEqualTo(UPDATED_INNOVATE_QUALITY);
        assertThat(testProInnovativeProduct.getInnovateProve()).isEqualTo(UPDATED_INNOVATE_PROVE);
        assertThat(testProInnovativeProduct.getInnovateCaseName()).isEqualTo(UPDATED_INNOVATE_CASE_NAME);
        assertThat(testProInnovativeProduct.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProInnovativeProduct.getInnovateName()).isEqualTo(UPDATED_INNOVATE_NAME);
        assertThat(testProInnovativeProduct.getPatent()).isEqualTo(UPDATED_PATENT);
        assertThat(testProInnovativeProduct.getPatentStartDate()).isEqualTo(UPDATED_PATENT_START_DATE);
        assertThat(testProInnovativeProduct.getPatentEndDate()).isEqualTo(UPDATED_PATENT_END_DATE);
        assertThat(testProInnovativeProduct.getConstructCost()).isEqualTo(UPDATED_CONSTRUCT_COST);
        assertThat(testProInnovativeProduct.getMaintainCost()).isEqualTo(UPDATED_MAINTAIN_COST);
        assertThat(testProInnovativeProduct.getCommunicateDate()).isEqualTo(UPDATED_COMMUNICATE_DATE);
        assertThat(testProInnovativeProduct.getCommunicateDocNumber()).isEqualTo(UPDATED_COMMUNICATE_DOC_NUMBER);
        assertThat(testProInnovativeProduct.getInnovateCaseVendor()).isEqualTo(UPDATED_INNOVATE_CASE_VENDOR);
        assertThat(testProInnovativeProduct.getInnovateCaseScale()).isEqualTo(UPDATED_INNOVATE_CASE_SCALE);
        assertThat(testProInnovativeProduct.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProInnovativeProduct.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProInnovativeProduct.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProInnovativeProduct.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void putNonExistingProInnovativeProduct() throws Exception {
        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());

        // Create the ProInnovativeProduct
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(proInnovativeProduct);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProInnovativeProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proInnovativeProductDTO.getProInnovativeProductId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProInnovativeProduct() throws Exception {
        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());

        // Create the ProInnovativeProduct
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(proInnovativeProduct);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProInnovativeProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProInnovativeProduct() throws Exception {
        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());

        // Create the ProInnovativeProduct
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(proInnovativeProduct);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProInnovativeProductMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProInnovativeProductWithPatch() throws Exception {
        // Initialize the database
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());
        proInnovativeProductRepository.saveAndFlush(proInnovativeProduct);

        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();

        // Update the proInnovativeProduct using partial update
        ProInnovativeProduct partialUpdatedProInnovativeProduct = new ProInnovativeProduct();
        partialUpdatedProInnovativeProduct.setProInnovativeProductId(proInnovativeProduct.getProInnovativeProductId());

        partialUpdatedProInnovativeProduct
            .innovateType(UPDATED_INNOVATE_TYPE)
            .innovateCaseName(UPDATED_INNOVATE_CASE_NAME)
            .status(UPDATED_STATUS)
            .patent(UPDATED_PATENT)
            .patentStartDate(UPDATED_PATENT_START_DATE)
            .patentEndDate(UPDATED_PATENT_END_DATE)
            .communicateDocNumber(UPDATED_COMMUNICATE_DOC_NUMBER)
            .innovateCaseVendor(UPDATED_INNOVATE_CASE_VENDOR)
            .innovateCaseScale(UPDATED_INNOVATE_CASE_SCALE);

        restProInnovativeProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProInnovativeProduct.getProInnovativeProductId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProInnovativeProduct))
            )
            .andExpect(status().isOk());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
        ProInnovativeProduct testProInnovativeProduct = proInnovativeProductList.get(proInnovativeProductList.size() - 1);
        assertThat(testProInnovativeProduct.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testProInnovativeProduct.getInnovateType()).isEqualTo(UPDATED_INNOVATE_TYPE);
        assertThat(testProInnovativeProduct.getInnovateFunction()).isEqualTo(DEFAULT_INNOVATE_FUNCTION);
        assertThat(testProInnovativeProduct.getInnovateQuality()).isEqualTo(DEFAULT_INNOVATE_QUALITY);
        assertThat(testProInnovativeProduct.getInnovateProve()).isEqualTo(DEFAULT_INNOVATE_PROVE);
        assertThat(testProInnovativeProduct.getInnovateCaseName()).isEqualTo(UPDATED_INNOVATE_CASE_NAME);
        assertThat(testProInnovativeProduct.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProInnovativeProduct.getInnovateName()).isEqualTo(DEFAULT_INNOVATE_NAME);
        assertThat(testProInnovativeProduct.getPatent()).isEqualTo(UPDATED_PATENT);
        assertThat(testProInnovativeProduct.getPatentStartDate()).isEqualTo(UPDATED_PATENT_START_DATE);
        assertThat(testProInnovativeProduct.getPatentEndDate()).isEqualTo(UPDATED_PATENT_END_DATE);
        assertThat(testProInnovativeProduct.getConstructCost()).isEqualTo(DEFAULT_CONSTRUCT_COST);
        assertThat(testProInnovativeProduct.getMaintainCost()).isEqualTo(DEFAULT_MAINTAIN_COST);
        assertThat(testProInnovativeProduct.getCommunicateDate()).isEqualTo(DEFAULT_COMMUNICATE_DATE);
        assertThat(testProInnovativeProduct.getCommunicateDocNumber()).isEqualTo(UPDATED_COMMUNICATE_DOC_NUMBER);
        assertThat(testProInnovativeProduct.getInnovateCaseVendor()).isEqualTo(UPDATED_INNOVATE_CASE_VENDOR);
        assertThat(testProInnovativeProduct.getInnovateCaseScale()).isEqualTo(UPDATED_INNOVATE_CASE_SCALE);
        assertThat(testProInnovativeProduct.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testProInnovativeProduct.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testProInnovativeProduct.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testProInnovativeProduct.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    void fullUpdateProInnovativeProductWithPatch() throws Exception {
        // Initialize the database
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());
        proInnovativeProductRepository.saveAndFlush(proInnovativeProduct);

        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();

        // Update the proInnovativeProduct using partial update
        ProInnovativeProduct partialUpdatedProInnovativeProduct = new ProInnovativeProduct();
        partialUpdatedProInnovativeProduct.setProInnovativeProductId(proInnovativeProduct.getProInnovativeProductId());

        partialUpdatedProInnovativeProduct
            .userId(UPDATED_USER_ID)
            .innovateType(UPDATED_INNOVATE_TYPE)
            .innovateFunction(UPDATED_INNOVATE_FUNCTION)
            .innovateQuality(UPDATED_INNOVATE_QUALITY)
            .innovateProve(UPDATED_INNOVATE_PROVE)
            .innovateCaseName(UPDATED_INNOVATE_CASE_NAME)
            .status(UPDATED_STATUS)
            .innovateName(UPDATED_INNOVATE_NAME)
            .patent(UPDATED_PATENT)
            .patentStartDate(UPDATED_PATENT_START_DATE)
            .patentEndDate(UPDATED_PATENT_END_DATE)
            .constructCost(UPDATED_CONSTRUCT_COST)
            .maintainCost(UPDATED_MAINTAIN_COST)
            .communicateDate(UPDATED_COMMUNICATE_DATE)
            .communicateDocNumber(UPDATED_COMMUNICATE_DOC_NUMBER)
            .innovateCaseVendor(UPDATED_INNOVATE_CASE_VENDOR)
            .innovateCaseScale(UPDATED_INNOVATE_CASE_SCALE)
            .updateUser(UPDATED_UPDATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restProInnovativeProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProInnovativeProduct.getProInnovativeProductId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProInnovativeProduct))
            )
            .andExpect(status().isOk());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
        ProInnovativeProduct testProInnovativeProduct = proInnovativeProductList.get(proInnovativeProductList.size() - 1);
        assertThat(testProInnovativeProduct.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProInnovativeProduct.getInnovateType()).isEqualTo(UPDATED_INNOVATE_TYPE);
        assertThat(testProInnovativeProduct.getInnovateFunction()).isEqualTo(UPDATED_INNOVATE_FUNCTION);
        assertThat(testProInnovativeProduct.getInnovateQuality()).isEqualTo(UPDATED_INNOVATE_QUALITY);
        assertThat(testProInnovativeProduct.getInnovateProve()).isEqualTo(UPDATED_INNOVATE_PROVE);
        assertThat(testProInnovativeProduct.getInnovateCaseName()).isEqualTo(UPDATED_INNOVATE_CASE_NAME);
        assertThat(testProInnovativeProduct.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProInnovativeProduct.getInnovateName()).isEqualTo(UPDATED_INNOVATE_NAME);
        assertThat(testProInnovativeProduct.getPatent()).isEqualTo(UPDATED_PATENT);
        assertThat(testProInnovativeProduct.getPatentStartDate()).isEqualTo(UPDATED_PATENT_START_DATE);
        assertThat(testProInnovativeProduct.getPatentEndDate()).isEqualTo(UPDATED_PATENT_END_DATE);
        assertThat(testProInnovativeProduct.getConstructCost()).isEqualTo(UPDATED_CONSTRUCT_COST);
        assertThat(testProInnovativeProduct.getMaintainCost()).isEqualTo(UPDATED_MAINTAIN_COST);
        assertThat(testProInnovativeProduct.getCommunicateDate()).isEqualTo(UPDATED_COMMUNICATE_DATE);
        assertThat(testProInnovativeProduct.getCommunicateDocNumber()).isEqualTo(UPDATED_COMMUNICATE_DOC_NUMBER);
        assertThat(testProInnovativeProduct.getInnovateCaseVendor()).isEqualTo(UPDATED_INNOVATE_CASE_VENDOR);
        assertThat(testProInnovativeProduct.getInnovateCaseScale()).isEqualTo(UPDATED_INNOVATE_CASE_SCALE);
        assertThat(testProInnovativeProduct.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testProInnovativeProduct.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testProInnovativeProduct.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testProInnovativeProduct.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingProInnovativeProduct() throws Exception {
        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());

        // Create the ProInnovativeProduct
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(proInnovativeProduct);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProInnovativeProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, proInnovativeProductDTO.getProInnovativeProductId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProInnovativeProduct() throws Exception {
        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());

        // Create the ProInnovativeProduct
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(proInnovativeProduct);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProInnovativeProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProInnovativeProduct() throws Exception {
        int databaseSizeBeforeUpdate = proInnovativeProductRepository.findAll().size();
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());

        // Create the ProInnovativeProduct
        ProInnovativeProductDTO proInnovativeProductDTO = proInnovativeProductMapper.toDto(proInnovativeProduct);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProInnovativeProductMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proInnovativeProductDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProInnovativeProduct in the database
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProInnovativeProduct() throws Exception {
        // Initialize the database
        proInnovativeProduct.setProInnovativeProductId(UUID.randomUUID().toString());
        proInnovativeProductRepository.saveAndFlush(proInnovativeProduct);

        int databaseSizeBeforeDelete = proInnovativeProductRepository.findAll().size();

        // Delete the proInnovativeProduct
        restProInnovativeProductMockMvc
            .perform(delete(ENTITY_API_URL_ID, proInnovativeProduct.getProInnovativeProductId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProInnovativeProduct> proInnovativeProductList = proInnovativeProductRepository.findAll();
        assertThat(proInnovativeProductList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
