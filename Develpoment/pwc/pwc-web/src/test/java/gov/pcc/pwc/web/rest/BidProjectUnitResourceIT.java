package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidProjectUnit;
import gov.pcc.pwc.repository.BidProjectUnitRepository;
import gov.pcc.pwc.service.dto.BidProjectUnitDTO;
import gov.pcc.pwc.service.mapper.BidProjectUnitMapper;
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
 * Integration tests for the {@link BidProjectUnitResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidProjectUnitResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AA";
    private static final String UPDATED_TYPE = "BB";

    private static final String DEFAULT_SEQ = "AA";
    private static final String UPDATED_SEQ = "BB";

    private static final String DEFAULT_DSUT = "AAAAAAAAAA";
    private static final String UPDATED_DSUT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DSCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DSCT = new BigDecimal(2);

    private static final String DEFAULT_SCUT = "AAAAAAAAAA";
    private static final String UPDATED_SCUT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SCCT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SCCT = new BigDecimal(2);

    private static final String DEFAULT_CCUT = "AAAAAAAAAA";
    private static final String UPDATED_CCUT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CRAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CRAT = new BigDecimal(2);

    private static final String DEFAULT_CCWK_A = "A";
    private static final String UPDATED_CCWK_A = "B";

    private static final String DEFAULT_CCWK_B = "A";
    private static final String UPDATED_CCWK_B = "B";

    private static final String DEFAULT_CCWK_C = "A";
    private static final String UPDATED_CCWK_C = "B";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-project-units";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidProjectUnitRepository bidProjectUnitRepository;

    @Autowired
    private BidProjectUnitMapper bidProjectUnitMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidProjectUnitMockMvc;

    private BidProjectUnit bidProjectUnit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidProjectUnit createEntity(EntityManager em) {
        BidProjectUnit bidProjectUnit = new BidProjectUnit()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .type(DEFAULT_TYPE)
            .seq(DEFAULT_SEQ)
            .dsut(DEFAULT_DSUT)
            .dsct(DEFAULT_DSCT)
            .scut(DEFAULT_SCUT)
            .scct(DEFAULT_SCCT)
            .ccut(DEFAULT_CCUT)
            .crat(DEFAULT_CRAT)
            .ccwkA(DEFAULT_CCWK_A)
            .ccwkB(DEFAULT_CCWK_B)
            .ccwkC(DEFAULT_CCWK_C)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER);
        return bidProjectUnit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidProjectUnit createUpdatedEntity(EntityManager em) {
        BidProjectUnit bidProjectUnit = new BidProjectUnit()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .type(UPDATED_TYPE)
            .seq(UPDATED_SEQ)
            .dsut(UPDATED_DSUT)
            .dsct(UPDATED_DSCT)
            .scut(UPDATED_SCUT)
            .scct(UPDATED_SCCT)
            .ccut(UPDATED_CCUT)
            .crat(UPDATED_CRAT)
            .ccwkA(UPDATED_CCWK_A)
            .ccwkB(UPDATED_CCWK_B)
            .ccwkC(UPDATED_CCWK_C)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        return bidProjectUnit;
    }

    @BeforeEach
    public void initTest() {
        bidProjectUnit = createEntity(em);
    }

    @Test
    @Transactional
    void createBidProjectUnit() throws Exception {
        int databaseSizeBeforeCreate = bidProjectUnitRepository.findAll().size();
        // Create the BidProjectUnit
        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);
        restBidProjectUnitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BidProjectUnit in the database
        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeCreate + 1);
        BidProjectUnit testBidProjectUnit = bidProjectUnitList.get(bidProjectUnitList.size() - 1);
        assertThat(testBidProjectUnit.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidProjectUnit.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidProjectUnit.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBidProjectUnit.getSeq()).isEqualTo(DEFAULT_SEQ);
        assertThat(testBidProjectUnit.getDsut()).isEqualTo(DEFAULT_DSUT);
        assertThat(testBidProjectUnit.getDsct()).isEqualByComparingTo(DEFAULT_DSCT);
        assertThat(testBidProjectUnit.getScut()).isEqualTo(DEFAULT_SCUT);
        assertThat(testBidProjectUnit.getScct()).isEqualByComparingTo(DEFAULT_SCCT);
        assertThat(testBidProjectUnit.getCcut()).isEqualTo(DEFAULT_CCUT);
        assertThat(testBidProjectUnit.getCrat()).isEqualByComparingTo(DEFAULT_CRAT);
        assertThat(testBidProjectUnit.getCcwkA()).isEqualTo(DEFAULT_CCWK_A);
        assertThat(testBidProjectUnit.getCcwkB()).isEqualTo(DEFAULT_CCWK_B);
        assertThat(testBidProjectUnit.getCcwkC()).isEqualTo(DEFAULT_CCWK_C);
        assertThat(testBidProjectUnit.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidProjectUnit.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidProjectUnit.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidProjectUnit.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    //    @Test
    //    @Transactional
    //    void createBidProjectUnitWithExistingId() throws Exception {
    //        // Create the BidProjectUnit with an existing ID
    //        bidProjectUnit.setId(1L);
    //        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);
    //
    //        int databaseSizeBeforeCreate = bidProjectUnitRepository.findAll().size();
    //
    //        // An entity with an existing ID cannot be created, so this API call must fail
    //        restBidProjectUnitMockMvc
    //            .perform(
    //                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the BidProjectUnit in the database
    //        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
    //        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeCreate);
    //    }

    @Test
    @Transactional
    void checkWkutIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectUnitRepository.findAll().size();
        // set the field null
        bidProjectUnit.setWkut(null);

        // Create the BidProjectUnit, which fails.
        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);

        restBidProjectUnitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrjnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectUnitRepository.findAll().size();
        // set the field null
        bidProjectUnit.setPrjno(null);

        // Create the BidProjectUnit, which fails.
        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);

        restBidProjectUnitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectUnitRepository.findAll().size();
        // set the field null
        bidProjectUnit.setType(null);

        // Create the BidProjectUnit, which fails.
        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);

        restBidProjectUnitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSeqIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectUnitRepository.findAll().size();
        // set the field null
        bidProjectUnit.setSeq(null);

        // Create the BidProjectUnit, which fails.
        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);

        restBidProjectUnitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectUnitRepository.findAll().size();
        // set the field null
        bidProjectUnit.setCreateDate(null);

        // Create the BidProjectUnit, which fails.
        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);

        restBidProjectUnitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidProjectUnitRepository.findAll().size();
        // set the field null
        bidProjectUnit.setCreateUser(null);

        // Create the BidProjectUnit, which fails.
        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);

        restBidProjectUnitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
            )
            .andExpect(status().isBadRequest());

        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeTest);
    }

    //    @Test
    //    @Transactional
    //    void getAllBidProjectUnits() throws Exception {
    //        // Initialize the database
    //        bidProjectUnitRepository.saveAndFlush(bidProjectUnit);
    //
    //        // Get all the bidProjectUnitList
    //        restBidProjectUnitMockMvc
    //            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
    //            .andExpect(status().isOk())
    //            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    //            .andExpect(jsonPath("$.[*].id").value(hasItem(bidProjectUnit.getId().intValue())))
    //            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
    //            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
    //            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
    //            .andExpect(jsonPath("$.[*].seq").value(hasItem(DEFAULT_SEQ)))
    //            .andExpect(jsonPath("$.[*].dsut").value(hasItem(DEFAULT_DSUT)))
    //            .andExpect(jsonPath("$.[*].dsct").value(hasItem(sameNumber(DEFAULT_DSCT))))
    //            .andExpect(jsonPath("$.[*].scut").value(hasItem(DEFAULT_SCUT)))
    //            .andExpect(jsonPath("$.[*].scct").value(hasItem(sameNumber(DEFAULT_SCCT))))
    //            .andExpect(jsonPath("$.[*].ccut").value(hasItem(DEFAULT_CCUT)))
    //            .andExpect(jsonPath("$.[*].crat").value(hasItem(sameNumber(DEFAULT_CRAT))))
    //            .andExpect(jsonPath("$.[*].ccwkA").value(hasItem(DEFAULT_CCWK_A)))
    //            .andExpect(jsonPath("$.[*].ccwkB").value(hasItem(DEFAULT_CCWK_B)))
    //            .andExpect(jsonPath("$.[*].ccwkC").value(hasItem(DEFAULT_CCWK_C)))
    //            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
    //            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
    //            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
    //            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)));
    //    }

    //    @Test
    //    @Transactional
    //    void getBidProjectUnit() throws Exception {
    //        // Initialize the database
    //        bidProjectUnitRepository.saveAndFlush(bidProjectUnit);
    //
    //        // Get the bidProjectUnit
    //        restBidProjectUnitMockMvc
    //            .perform(get(ENTITY_API_URL_ID, bidProjectUnit.getId()))
    //            .andExpect(status().isOk())
    //            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    //            .andExpect(jsonPath("$.id").value(bidProjectUnit.getId().intValue()))
    //            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
    //            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
    //            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
    //            .andExpect(jsonPath("$.seq").value(DEFAULT_SEQ))
    //            .andExpect(jsonPath("$.dsut").value(DEFAULT_DSUT))
    //            .andExpect(jsonPath("$.dsct").value(sameNumber(DEFAULT_DSCT)))
    //            .andExpect(jsonPath("$.scut").value(DEFAULT_SCUT))
    //            .andExpect(jsonPath("$.scct").value(sameNumber(DEFAULT_SCCT)))
    //            .andExpect(jsonPath("$.ccut").value(DEFAULT_CCUT))
    //            .andExpect(jsonPath("$.crat").value(sameNumber(DEFAULT_CRAT)))
    //            .andExpect(jsonPath("$.ccwkA").value(DEFAULT_CCWK_A))
    //            .andExpect(jsonPath("$.ccwkB").value(DEFAULT_CCWK_B))
    //            .andExpect(jsonPath("$.ccwkC").value(DEFAULT_CCWK_C))
    //            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
    //            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
    //            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
    //            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER));
    //    }

    @Test
    @Transactional
    void getNonExistingBidProjectUnit() throws Exception {
        // Get the bidProjectUnit
        restBidProjectUnitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidProjectUnit() throws Exception {
        // Initialize the database
        bidProjectUnitRepository.saveAndFlush(bidProjectUnit);

        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();

        // Update the bidProjectUnit
        BidProjectUnit updatedBidProjectUnit = bidProjectUnitRepository.findById(bidProjectUnit.getId()).get();
        // Disconnect from session so that the updates on updatedBidProjectUnit are not directly saved in db
        em.detach(updatedBidProjectUnit);
        updatedBidProjectUnit
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .type(UPDATED_TYPE)
            .seq(UPDATED_SEQ)
            .dsut(UPDATED_DSUT)
            .dsct(UPDATED_DSCT)
            .scut(UPDATED_SCUT)
            .scct(UPDATED_SCCT)
            .ccut(UPDATED_CCUT)
            .crat(UPDATED_CRAT)
            .ccwkA(UPDATED_CCWK_A)
            .ccwkB(UPDATED_CCWK_B)
            .ccwkC(UPDATED_CCWK_C)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);
        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(updatedBidProjectUnit);

        restBidProjectUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidProjectUnitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
            )
            .andExpect(status().isOk());

        // Validate the BidProjectUnit in the database
        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
        BidProjectUnit testBidProjectUnit = bidProjectUnitList.get(bidProjectUnitList.size() - 1);
        assertThat(testBidProjectUnit.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidProjectUnit.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidProjectUnit.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBidProjectUnit.getSeq()).isEqualTo(UPDATED_SEQ);
        assertThat(testBidProjectUnit.getDsut()).isEqualTo(UPDATED_DSUT);
        assertThat(testBidProjectUnit.getDsct()).isEqualByComparingTo(UPDATED_DSCT);
        assertThat(testBidProjectUnit.getScut()).isEqualTo(UPDATED_SCUT);
        assertThat(testBidProjectUnit.getScct()).isEqualByComparingTo(UPDATED_SCCT);
        assertThat(testBidProjectUnit.getCcut()).isEqualTo(UPDATED_CCUT);
        assertThat(testBidProjectUnit.getCrat()).isEqualByComparingTo(UPDATED_CRAT);
        assertThat(testBidProjectUnit.getCcwkA()).isEqualTo(UPDATED_CCWK_A);
        assertThat(testBidProjectUnit.getCcwkB()).isEqualTo(UPDATED_CCWK_B);
        assertThat(testBidProjectUnit.getCcwkC()).isEqualTo(UPDATED_CCWK_C);
        assertThat(testBidProjectUnit.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidProjectUnit.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidProjectUnit.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidProjectUnit.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    //    @Test
    //    @Transactional
    //    void putNonExistingBidProjectUnit() throws Exception {
    //        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();
    //        bidProjectUnit.setId(count.incrementAndGet());
    //
    //        // Create the BidProjectUnit
    //        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);
    //
    //        // If the entity doesn't have an ID, it will throw BadRequestAlertException
    //        restBidProjectUnitMockMvc
    //            .perform(
    //                put(ENTITY_API_URL_ID, bidProjectUnitDTO.getId())
    //                    .contentType(MediaType.APPLICATION_JSON)
    //                    .content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the BidProjectUnit in the database
    //        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
    //        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //    @Test
    //    @Transactional
    //    void putWithIdMismatchBidProjectUnit() throws Exception {
    //        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();
    //        bidProjectUnit.setId(count.incrementAndGet());
    //
    //        // Create the BidProjectUnit
    //        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);
    //
    //        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    //        restBidProjectUnitMockMvc
    //            .perform(
    //                put(ENTITY_API_URL_ID, count.incrementAndGet())
    //                    .contentType(MediaType.APPLICATION_JSON)
    //                    .content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the BidProjectUnit in the database
    //        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
    //        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //    @Test
    //    @Transactional
    //    void putWithMissingIdPathParamBidProjectUnit() throws Exception {
    //        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();
    //        bidProjectUnit.setId(count.incrementAndGet());
    //
    //        // Create the BidProjectUnit
    //        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);
    //
    //        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    //        restBidProjectUnitMockMvc
    //            .perform(
    //                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
    //            )
    //            .andExpect(status().isMethodNotAllowed());
    //
    //        // Validate the BidProjectUnit in the database
    //        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
    //        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
    //    }

    @Test
    @Transactional
    void partialUpdateBidProjectUnitWithPatch() throws Exception {
        // Initialize the database
        bidProjectUnitRepository.saveAndFlush(bidProjectUnit);

        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();

        // Update the bidProjectUnit using partial update
        BidProjectUnit partialUpdatedBidProjectUnit = new BidProjectUnit();
        partialUpdatedBidProjectUnit.setId(bidProjectUnit.getId());

        partialUpdatedBidProjectUnit
            .prjno(UPDATED_PRJNO)
            .type(UPDATED_TYPE)
            .dsut(UPDATED_DSUT)
            .ccwkB(UPDATED_CCWK_B)
            .ccwkC(UPDATED_CCWK_C)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER);

        restBidProjectUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidProjectUnit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidProjectUnit))
            )
            .andExpect(status().isOk());

        // Validate the BidProjectUnit in the database
        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
        BidProjectUnit testBidProjectUnit = bidProjectUnitList.get(bidProjectUnitList.size() - 1);
        assertThat(testBidProjectUnit.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidProjectUnit.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidProjectUnit.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBidProjectUnit.getSeq()).isEqualTo(DEFAULT_SEQ);
        assertThat(testBidProjectUnit.getDsut()).isEqualTo(UPDATED_DSUT);
        assertThat(testBidProjectUnit.getDsct()).isEqualByComparingTo(DEFAULT_DSCT);
        assertThat(testBidProjectUnit.getScut()).isEqualTo(DEFAULT_SCUT);
        assertThat(testBidProjectUnit.getScct()).isEqualByComparingTo(DEFAULT_SCCT);
        assertThat(testBidProjectUnit.getCcut()).isEqualTo(DEFAULT_CCUT);
        assertThat(testBidProjectUnit.getCrat()).isEqualByComparingTo(DEFAULT_CRAT);
        assertThat(testBidProjectUnit.getCcwkA()).isEqualTo(DEFAULT_CCWK_A);
        assertThat(testBidProjectUnit.getCcwkB()).isEqualTo(UPDATED_CCWK_B);
        assertThat(testBidProjectUnit.getCcwkC()).isEqualTo(UPDATED_CCWK_C);
        assertThat(testBidProjectUnit.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidProjectUnit.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidProjectUnit.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidProjectUnit.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    void fullUpdateBidProjectUnitWithPatch() throws Exception {
        // Initialize the database
        bidProjectUnitRepository.saveAndFlush(bidProjectUnit);

        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();

        // Update the bidProjectUnit using partial update
        BidProjectUnit partialUpdatedBidProjectUnit = new BidProjectUnit();
        partialUpdatedBidProjectUnit.setId(bidProjectUnit.getId());

        partialUpdatedBidProjectUnit
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .type(UPDATED_TYPE)
            .seq(UPDATED_SEQ)
            .dsut(UPDATED_DSUT)
            .dsct(UPDATED_DSCT)
            .scut(UPDATED_SCUT)
            .scct(UPDATED_SCCT)
            .ccut(UPDATED_CCUT)
            .crat(UPDATED_CRAT)
            .ccwkA(UPDATED_CCWK_A)
            .ccwkB(UPDATED_CCWK_B)
            .ccwkC(UPDATED_CCWK_C)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER);

        restBidProjectUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidProjectUnit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidProjectUnit))
            )
            .andExpect(status().isOk());

        // Validate the BidProjectUnit in the database
        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
        BidProjectUnit testBidProjectUnit = bidProjectUnitList.get(bidProjectUnitList.size() - 1);
        assertThat(testBidProjectUnit.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidProjectUnit.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidProjectUnit.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBidProjectUnit.getSeq()).isEqualTo(UPDATED_SEQ);
        assertThat(testBidProjectUnit.getDsut()).isEqualTo(UPDATED_DSUT);
        assertThat(testBidProjectUnit.getDsct()).isEqualByComparingTo(UPDATED_DSCT);
        assertThat(testBidProjectUnit.getScut()).isEqualTo(UPDATED_SCUT);
        assertThat(testBidProjectUnit.getScct()).isEqualByComparingTo(UPDATED_SCCT);
        assertThat(testBidProjectUnit.getCcut()).isEqualTo(UPDATED_CCUT);
        assertThat(testBidProjectUnit.getCrat()).isEqualByComparingTo(UPDATED_CRAT);
        assertThat(testBidProjectUnit.getCcwkA()).isEqualTo(UPDATED_CCWK_A);
        assertThat(testBidProjectUnit.getCcwkB()).isEqualTo(UPDATED_CCWK_B);
        assertThat(testBidProjectUnit.getCcwkC()).isEqualTo(UPDATED_CCWK_C);
        assertThat(testBidProjectUnit.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidProjectUnit.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidProjectUnit.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidProjectUnit.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    //    @Test
    //    @Transactional
    //    void patchNonExistingBidProjectUnit() throws Exception {
    //        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();
    //        bidProjectUnit.setId(count.incrementAndGet());
    //
    //        // Create the BidProjectUnit
    //        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);
    //
    //        // If the entity doesn't have an ID, it will throw BadRequestAlertException
    //        restBidProjectUnitMockMvc
    //            .perform(
    //                patch(ENTITY_API_URL_ID, bidProjectUnitDTO.getId())
    //                    .contentType("application/merge-patch+json")
    //                    .content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the BidProjectUnit in the database
    //        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
    //        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //    @Test
    //    @Transactional
    //    void patchWithIdMismatchBidProjectUnit() throws Exception {
    //        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();
    //        bidProjectUnit.setId(count.incrementAndGet());
    //
    //        // Create the BidProjectUnit
    //        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);
    //
    //        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    //        restBidProjectUnitMockMvc
    //            .perform(
    //                patch(ENTITY_API_URL_ID, count.incrementAndGet())
    //                    .contentType("application/merge-patch+json")
    //                    .content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
    //            )
    //            .andExpect(status().isBadRequest());
    //
    //        // Validate the BidProjectUnit in the database
    //        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
    //        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
    //    }
    //    @Test
    //    @Transactional
    //    void patchWithMissingIdPathParamBidProjectUnit() throws Exception {
    //        int databaseSizeBeforeUpdate = bidProjectUnitRepository.findAll().size();
    //        bidProjectUnit.setId(count.incrementAndGet());
    //
    //        // Create the BidProjectUnit
    //        BidProjectUnitDTO bidProjectUnitDTO = bidProjectUnitMapper.toDto(bidProjectUnit);
    //
    //        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    //        restBidProjectUnitMockMvc
    //            .perform(
    //                patch(ENTITY_API_URL)
    //                    .contentType("application/merge-patch+json")
    //                    .content(TestUtil.convertObjectToJsonBytes(bidProjectUnitDTO))
    //            )
    //            .andExpect(status().isMethodNotAllowed());
    //
    //        // Validate the BidProjectUnit in the database
    //        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
    //        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeUpdate);
    //    }

    @Test
    @Transactional
    void deleteBidProjectUnit() throws Exception {
        // Initialize the database
        bidProjectUnitRepository.saveAndFlush(bidProjectUnit);

        int databaseSizeBeforeDelete = bidProjectUnitRepository.findAll().size();

        // Delete the bidProjectUnit
        restBidProjectUnitMockMvc
            .perform(delete(ENTITY_API_URL_ID, bidProjectUnit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidProjectUnit> bidProjectUnitList = bidProjectUnitRepository.findAll();
        assertThat(bidProjectUnitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
