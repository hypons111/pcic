package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjScequip;
import gov.pcc.pwc.repository.BidPrjScequipRepository;
import gov.pcc.pwc.service.dto.BidPrjScequipDTO;
import gov.pcc.pwc.service.mapper.BidPrjScequipMapper;
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
 * Integration tests for the {@link BidPrjScequipResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjScequipResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CCUT = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CCUT = "BBBBBBBBBB";

    private static final String DEFAULT_E_CODE = "AAAAA";
    private static final String UPDATED_E_CODE = "BBBBB";

    private static final String DEFAULT_E_CNT = "AAAAAAAAAA";
    private static final String UPDATED_E_CNT = "BBBBBBBBBB";

    private static final String DEFAULT_E_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_E_REMARK = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_CONF_A = "A";
    private static final String UPDATED_CONF_A = "B";

    private static final String DEFAULT_CONF_B = "A";
    private static final String UPDATED_CONF_B = "B";

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-scequips";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjScequipRepository bidPrjScequipRepository;

    @Autowired
    private BidPrjScequipMapper bidPrjScequipMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjScequipMockMvc;

    private BidPrjScequip bidPrjScequip;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjScequip createEntity(EntityManager em) {
        BidPrjScequip bidPrjScequip = new BidPrjScequip()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .subCcut(DEFAULT_SUB_CCUT)
            .eCode(DEFAULT_E_CODE)
            .eCnt(DEFAULT_E_CNT)
            .eRemark(DEFAULT_E_REMARK)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .confA(DEFAULT_CONF_A)
            .confB(DEFAULT_CONF_B)
            .uIp(DEFAULT_U_IP);
        return bidPrjScequip;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjScequip createUpdatedEntity(EntityManager em) {
        BidPrjScequip bidPrjScequip = new BidPrjScequip()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .subCcut(UPDATED_SUB_CCUT)
            .eCode(UPDATED_E_CODE)
            .eCnt(UPDATED_E_CNT)
            .eRemark(UPDATED_E_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .confA(UPDATED_CONF_A)
            .confB(UPDATED_CONF_B)
            .uIp(UPDATED_U_IP);
        return bidPrjScequip;
    }

    @BeforeEach
    public void initTest() {
        bidPrjScequip = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjScequip() throws Exception {
        int databaseSizeBeforeCreate = bidPrjScequipRepository.findAll().size();
        // Create the BidPrjScequip
        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);
        restBidPrjScequipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidPrjScequip in the database
        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjScequip testBidPrjScequip = bidPrjScequipList.get(bidPrjScequipList.size() - 1);
        assertThat(testBidPrjScequip.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjScequip.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjScequip.getSubCcut()).isEqualTo(DEFAULT_SUB_CCUT);
        assertThat(testBidPrjScequip.geteCode()).isEqualTo(DEFAULT_E_CODE);
        assertThat(testBidPrjScequip.geteCnt()).isEqualTo(DEFAULT_E_CNT);
        assertThat(testBidPrjScequip.geteRemark()).isEqualTo(DEFAULT_E_REMARK);
        assertThat(testBidPrjScequip.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjScequip.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjScequip.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjScequip.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjScequip.getConfA()).isEqualTo(DEFAULT_CONF_A);
        assertThat(testBidPrjScequip.getConfB()).isEqualTo(DEFAULT_CONF_B);
        assertThat(testBidPrjScequip.getuIp()).isEqualTo(DEFAULT_U_IP);
    }

    @Test
    @Transactional
    void createBidPrjScequipWithExistingId() throws Exception {
        // Create the BidPrjScequip with an existing ID
//        bidPrjScequip.setId(1L);
//        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);
//
//        int databaseSizeBeforeCreate = bidPrjScequipRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjScequipMockMvc
//            .perform(
//                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjScequip in the database
//        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
//        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjScequipRepository.findAll().size();
        // set the field null
        bidPrjScequip.setWkut(null);

        // Create the BidPrjScequip, which fails.
        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);

        restBidPrjScequipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjScequipRepository.findAll().size();
        // set the field null
        bidPrjScequip.setPrjno(null);

        // Create the BidPrjScequip, which fails.
        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);

        restBidPrjScequipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjScequipRepository.findAll().size();
        // set the field null
        bidPrjScequip.setCreateDate(null);

        // Create the BidPrjScequip, which fails.
        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);

        restBidPrjScequipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidPrjScequipRepository.findAll().size();
        // set the field null
        bidPrjScequip.setCreateUser(null);

        // Create the BidPrjScequip, which fails.
        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);

        restBidPrjScequipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBidPrjScequips() throws Exception {
        // Initialize the database
//        bidPrjScequipRepository.saveAndFlush(bidPrjScequip);
//
//        // Get all the bidPrjScequipList
//        restBidPrjScequipMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjScequip.getId().intValue())))
//            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
//            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
//            .andExpect(jsonPath("$.[*].subCcut").value(hasItem(DEFAULT_SUB_CCUT)))
//            .andExpect(jsonPath("$.[*].eCode").value(hasItem(DEFAULT_E_CODE)))
//            .andExpect(jsonPath("$.[*].eCnt").value(hasItem(DEFAULT_E_CNT)))
//            .andExpect(jsonPath("$.[*].eRemark").value(hasItem(DEFAULT_E_REMARK)))
//            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
//            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
//            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
//            .andExpect(jsonPath("$.[*].confA").value(hasItem(DEFAULT_CONF_A)))
//            .andExpect(jsonPath("$.[*].confB").value(hasItem(DEFAULT_CONF_B)))
//            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)));
    }

    @Test
    @Transactional
    void getBidPrjScequip() throws Exception {
        // Initialize the database
//        bidPrjScequipRepository.saveAndFlush(bidPrjScequip);
//
//        // Get the bidPrjScequip
//        restBidPrjScequipMockMvc
//            .perform(get(ENTITY_API_URL_ID, bidPrjScequip.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(bidPrjScequip.getId().intValue()))
//            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
//            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
//            .andExpect(jsonPath("$.subCcut").value(DEFAULT_SUB_CCUT))
//            .andExpect(jsonPath("$.eCode").value(DEFAULT_E_CODE))
//            .andExpect(jsonPath("$.eCnt").value(DEFAULT_E_CNT))
//            .andExpect(jsonPath("$.eRemark").value(DEFAULT_E_REMARK))
//            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
//            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
//            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
//            .andExpect(jsonPath("$.confA").value(DEFAULT_CONF_A))
//            .andExpect(jsonPath("$.confB").value(DEFAULT_CONF_B))
//            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjScequip() throws Exception {
        // Get the bidPrjScequip
        restBidPrjScequipMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjScequip() throws Exception {
        // Initialize the database
        bidPrjScequipRepository.saveAndFlush(bidPrjScequip);

        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();

        // Update the bidPrjScequip
        BidPrjScequip updatedBidPrjScequip = bidPrjScequipRepository.findById(bidPrjScequip.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjScequip are not directly saved in db
        em.detach(updatedBidPrjScequip);
        updatedBidPrjScequip
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .subCcut(UPDATED_SUB_CCUT)
            .eCode(UPDATED_E_CODE)
            .eCnt(UPDATED_E_CNT)
            .eRemark(UPDATED_E_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .confA(UPDATED_CONF_A)
            .confB(UPDATED_CONF_B)
            .uIp(UPDATED_U_IP);
        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(updatedBidPrjScequip);

        restBidPrjScequipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjScequipDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjScequip in the database
        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
        BidPrjScequip testBidPrjScequip = bidPrjScequipList.get(bidPrjScequipList.size() - 1);
        assertThat(testBidPrjScequip.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjScequip.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjScequip.getSubCcut()).isEqualTo(UPDATED_SUB_CCUT);
        assertThat(testBidPrjScequip.geteCode()).isEqualTo(UPDATED_E_CODE);
        assertThat(testBidPrjScequip.geteCnt()).isEqualTo(UPDATED_E_CNT);
        assertThat(testBidPrjScequip.geteRemark()).isEqualTo(UPDATED_E_REMARK);
        assertThat(testBidPrjScequip.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjScequip.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjScequip.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjScequip.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjScequip.getConfA()).isEqualTo(UPDATED_CONF_A);
        assertThat(testBidPrjScequip.getConfB()).isEqualTo(UPDATED_CONF_B);
        assertThat(testBidPrjScequip.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjScequip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();
//        bidPrjScequip.setId(count.incrementAndGet());
//
//        // Create the BidPrjScequip
//        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjScequipMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjScequipDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjScequip in the database
//        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
//        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjScequip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();
//        bidPrjScequip.setId(count.incrementAndGet());
//
//        // Create the BidPrjScequip
//        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjScequipMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjScequip in the database
//        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
//        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjScequip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();
//        bidPrjScequip.setId(count.incrementAndGet());
//
//        // Create the BidPrjScequip
//        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjScequipMockMvc
//            .perform(
//                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjScequip in the database
//        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
//        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjScequipWithPatch() throws Exception {
        // Initialize the database
        bidPrjScequipRepository.saveAndFlush(bidPrjScequip);

        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();

        // Update the bidPrjScequip using partial update
        BidPrjScequip partialUpdatedBidPrjScequip = new BidPrjScequip();
        partialUpdatedBidPrjScequip.setId(bidPrjScequip.getId());

        partialUpdatedBidPrjScequip
            .prjno(UPDATED_PRJNO)
            .subCcut(UPDATED_SUB_CCUT)
            .eCode(UPDATED_E_CODE)
            .eCnt(UPDATED_E_CNT)
            .eRemark(UPDATED_E_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .confA(UPDATED_CONF_A)
            .uIp(UPDATED_U_IP);

        restBidPrjScequipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjScequip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjScequip))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjScequip in the database
        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
        BidPrjScequip testBidPrjScequip = bidPrjScequipList.get(bidPrjScequipList.size() - 1);
        assertThat(testBidPrjScequip.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjScequip.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjScequip.getSubCcut()).isEqualTo(UPDATED_SUB_CCUT);
        assertThat(testBidPrjScequip.geteCode()).isEqualTo(UPDATED_E_CODE);
        assertThat(testBidPrjScequip.geteCnt()).isEqualTo(UPDATED_E_CNT);
        assertThat(testBidPrjScequip.geteRemark()).isEqualTo(UPDATED_E_REMARK);
        assertThat(testBidPrjScequip.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjScequip.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjScequip.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjScequip.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjScequip.getConfA()).isEqualTo(UPDATED_CONF_A);
        assertThat(testBidPrjScequip.getConfB()).isEqualTo(DEFAULT_CONF_B);
        assertThat(testBidPrjScequip.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjScequipWithPatch() throws Exception {
        // Initialize the database
        bidPrjScequipRepository.saveAndFlush(bidPrjScequip);

        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();

        // Update the bidPrjScequip using partial update
        BidPrjScequip partialUpdatedBidPrjScequip = new BidPrjScequip();
        partialUpdatedBidPrjScequip.setId(bidPrjScequip.getId());

        partialUpdatedBidPrjScequip
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .subCcut(UPDATED_SUB_CCUT)
            .eCode(UPDATED_E_CODE)
            .eCnt(UPDATED_E_CNT)
            .eRemark(UPDATED_E_REMARK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .confA(UPDATED_CONF_A)
            .confB(UPDATED_CONF_B)
            .uIp(UPDATED_U_IP);

        restBidPrjScequipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjScequip.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjScequip))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjScequip in the database
        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
        BidPrjScequip testBidPrjScequip = bidPrjScequipList.get(bidPrjScequipList.size() - 1);
        assertThat(testBidPrjScequip.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjScequip.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjScequip.getSubCcut()).isEqualTo(UPDATED_SUB_CCUT);
        assertThat(testBidPrjScequip.geteCode()).isEqualTo(UPDATED_E_CODE);
        assertThat(testBidPrjScequip.geteCnt()).isEqualTo(UPDATED_E_CNT);
        assertThat(testBidPrjScequip.geteRemark()).isEqualTo(UPDATED_E_REMARK);
        assertThat(testBidPrjScequip.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjScequip.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjScequip.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjScequip.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjScequip.getConfA()).isEqualTo(UPDATED_CONF_A);
        assertThat(testBidPrjScequip.getConfB()).isEqualTo(UPDATED_CONF_B);
        assertThat(testBidPrjScequip.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjScequip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();
//        bidPrjScequip.setId(count.incrementAndGet());
//
//        // Create the BidPrjScequip
//        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjScequipMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjScequipDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjScequip in the database
//        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
//        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjScequip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();
//        bidPrjScequip.setId(count.incrementAndGet());
//
//        // Create the BidPrjScequip
//        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjScequipMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjScequip in the database
//        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
//        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjScequip() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjScequipRepository.findAll().size();
//        bidPrjScequip.setId(count.incrementAndGet());
//
//        // Create the BidPrjScequip
//        BidPrjScequipDTO bidPrjScequipDTO = bidPrjScequipMapper.toDto(bidPrjScequip);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjScequipMockMvc
//            .perform(
//                patch(ENTITY_API_URL)
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjScequipDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjScequip in the database
//        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
//        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjScequip() throws Exception {
        // Initialize the database
        bidPrjScequipRepository.saveAndFlush(bidPrjScequip);

        int databaseSizeBeforeDelete = bidPrjScequipRepository.findAll().size();

        // Delete the bidPrjScequip
        restBidPrjScequipMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjScequip.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjScequip> bidPrjScequipList = bidPrjScequipRepository.findAll();
        assertThat(bidPrjScequipList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
