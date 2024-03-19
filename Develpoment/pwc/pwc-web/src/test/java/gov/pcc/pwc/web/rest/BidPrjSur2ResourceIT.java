package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjSur2;
import gov.pcc.pwc.repository.BidPrjSur2Repository;
import gov.pcc.pwc.service.dto.BidPrjSur2DTO;
import gov.pcc.pwc.service.mapper.BidPrjSur2Mapper;
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
 * Integration tests for the {@link BidPrjSur2Resource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjSur2ResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_DIS_NO = "AAAAAAAAAA";
    private static final String UPDATED_DIS_NO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMNTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMNTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMNTB = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMNTB = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMNT = new BigDecimal(2);

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final String DEFAULT_DSTS = "A";
    private static final String UPDATED_DSTS = "B";

    private static final BigDecimal DEFAULT_CONF_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT = new BigDecimal(2);

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-sur-2-s";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjSur2Repository bidPrjSur2Repository;

    @Autowired
    private BidPrjSur2Mapper bidPrjSur2Mapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjSur2MockMvc;

    private BidPrjSur2 bidPrjSur2;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjSur2 createEntity(EntityManager em) {
        BidPrjSur2 bidPrjSur2 = new BidPrjSur2()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .disNo(DEFAULT_DIS_NO)
            .amnta(DEFAULT_AMNTA)
            .amntb(DEFAULT_AMNTB)
            .amnt(DEFAULT_AMNT)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .dsts(DEFAULT_DSTS)
            .confOut(DEFAULT_CONF_OUT)
            .uIp(DEFAULT_U_IP);
        return bidPrjSur2;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjSur2 createUpdatedEntity(EntityManager em) {
        BidPrjSur2 bidPrjSur2 = new BidPrjSur2()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .disNo(UPDATED_DIS_NO)
            .amnta(UPDATED_AMNTA)
            .amntb(UPDATED_AMNTB)
            .amnt(UPDATED_AMNT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP);
        return bidPrjSur2;
    }

    @BeforeEach
    public void initTest() {
        bidPrjSur2 = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjSur2() throws Exception {
        int databaseSizeBeforeCreate = bidPrjSur2Repository.findAll().size();
        // Create the BidPrjSur2
        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(bidPrjSur2);
        restBidPrjSur2MockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO)))
            .andExpect(status().isCreated());

        // Validate the BidPrjSur2 in the database
        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjSur2 testBidPrjSur2 = bidPrjSur2List.get(bidPrjSur2List.size() - 1);
        assertThat(testBidPrjSur2.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjSur2.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjSur2.getDisNo()).isEqualTo(DEFAULT_DIS_NO);
        assertThat(testBidPrjSur2.getAmnta()).isEqualByComparingTo(DEFAULT_AMNTA);
        assertThat(testBidPrjSur2.getAmntb()).isEqualByComparingTo(DEFAULT_AMNTB);
        assertThat(testBidPrjSur2.getAmnt()).isEqualByComparingTo(DEFAULT_AMNT);
        assertThat(testBidPrjSur2.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjSur2.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjSur2.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjSur2.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjSur2.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjSur2.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjSur2.getuIp()).isEqualTo(DEFAULT_U_IP);
    }

    @Test
    @Transactional
    void createBidPrjSur2WithExistingId() throws Exception {
//        // Create the BidPrjSur2 with an existing ID
//        bidPrjSur2.setId(1L);
//        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(bidPrjSur2);
//
//        int databaseSizeBeforeCreate = bidPrjSur2Repository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjSur2MockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSur2 in the database
//        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
//        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidPrjSur2s() throws Exception {
        // Initialize the database
        bidPrjSur2Repository.saveAndFlush(bidPrjSur2);

        // Get all the bidPrjSur2List
        restBidPrjSur2MockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjSur2.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].disNo").value(hasItem(DEFAULT_DIS_NO)))
            .andExpect(jsonPath("$.[*].amnta").value(hasItem(sameNumber(DEFAULT_AMNTA))))
            .andExpect(jsonPath("$.[*].amntb").value(hasItem(sameNumber(DEFAULT_AMNTB))))
            .andExpect(jsonPath("$.[*].amnt").value(hasItem(sameNumber(DEFAULT_AMNT))))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].dsts").value(hasItem(DEFAULT_DSTS)))
            .andExpect(jsonPath("$.[*].confOut").value(hasItem(sameNumber(DEFAULT_CONF_OUT))))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)));
    }

    @Test
    @Transactional
    void getBidPrjSur2() throws Exception {
        // Initialize the database
        bidPrjSur2Repository.saveAndFlush(bidPrjSur2);

        // Get the bidPrjSur2
        restBidPrjSur2MockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjSur2.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjSur2.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.disNo").value(DEFAULT_DIS_NO))
            .andExpect(jsonPath("$.amnta").value(sameNumber(DEFAULT_AMNTA)))
            .andExpect(jsonPath("$.amntb").value(sameNumber(DEFAULT_AMNTB)))
            .andExpect(jsonPath("$.amnt").value(sameNumber(DEFAULT_AMNT)))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.dsts").value(DEFAULT_DSTS))
            .andExpect(jsonPath("$.confOut").value(sameNumber(DEFAULT_CONF_OUT)))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjSur2() throws Exception {
        // Get the bidPrjSur2
        restBidPrjSur2MockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjSur2() throws Exception {
        // Initialize the database
        bidPrjSur2Repository.saveAndFlush(bidPrjSur2);

        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();

        // Update the bidPrjSur2
        BidPrjSur2 updatedBidPrjSur2 = bidPrjSur2Repository.findById(bidPrjSur2.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjSur2 are not directly saved in db
        em.detach(updatedBidPrjSur2);
        updatedBidPrjSur2
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .disNo(UPDATED_DIS_NO)
            .amnta(UPDATED_AMNTA)
            .amntb(UPDATED_AMNTB)
            .amnt(UPDATED_AMNT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP);
        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(updatedBidPrjSur2);

        restBidPrjSur2MockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjSur2DTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSur2 in the database
        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
        BidPrjSur2 testBidPrjSur2 = bidPrjSur2List.get(bidPrjSur2List.size() - 1);
        assertThat(testBidPrjSur2.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSur2.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSur2.getDisNo()).isEqualTo(UPDATED_DIS_NO);
        assertThat(testBidPrjSur2.getAmnta()).isEqualByComparingTo(UPDATED_AMNTA);
        assertThat(testBidPrjSur2.getAmntb()).isEqualByComparingTo(UPDATED_AMNTB);
        assertThat(testBidPrjSur2.getAmnt()).isEqualByComparingTo(UPDATED_AMNT);
        assertThat(testBidPrjSur2.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSur2.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSur2.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSur2.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSur2.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjSur2.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjSur2.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjSur2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();
//        bidPrjSur2.setId(count.incrementAndGet());
//
//        // Create the BidPrjSur2
//        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(bidPrjSur2);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjSur2MockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjSur2DTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSur2 in the database
//        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
//        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjSur2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();
//        bidPrjSur2.setId(count.incrementAndGet());
//
//        // Create the BidPrjSur2
//        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(bidPrjSur2);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSur2MockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSur2 in the database
//        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
//        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjSur2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();
//        bidPrjSur2.setId(count.incrementAndGet());
//
//        // Create the BidPrjSur2
//        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(bidPrjSur2);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSur2MockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjSur2 in the database
//        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
//        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjSur2WithPatch() throws Exception {
        // Initialize the database
        bidPrjSur2Repository.saveAndFlush(bidPrjSur2);

        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();

        // Update the bidPrjSur2 using partial update
        BidPrjSur2 partialUpdatedBidPrjSur2 = new BidPrjSur2();
        partialUpdatedBidPrjSur2.setId(bidPrjSur2.getId());

        partialUpdatedBidPrjSur2
            .prjno(UPDATED_PRJNO)
            .disNo(UPDATED_DIS_NO)
            .amntb(UPDATED_AMNTB)
            .amnt(UPDATED_AMNT)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .confOut(UPDATED_CONF_OUT);

        restBidPrjSur2MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjSur2.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjSur2))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSur2 in the database
        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
        BidPrjSur2 testBidPrjSur2 = bidPrjSur2List.get(bidPrjSur2List.size() - 1);
        assertThat(testBidPrjSur2.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjSur2.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSur2.getDisNo()).isEqualTo(UPDATED_DIS_NO);
        assertThat(testBidPrjSur2.getAmnta()).isEqualByComparingTo(DEFAULT_AMNTA);
        assertThat(testBidPrjSur2.getAmntb()).isEqualByComparingTo(UPDATED_AMNTB);
        assertThat(testBidPrjSur2.getAmnt()).isEqualByComparingTo(UPDATED_AMNT);
        assertThat(testBidPrjSur2.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjSur2.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSur2.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSur2.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjSur2.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjSur2.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjSur2.getuIp()).isEqualTo(DEFAULT_U_IP);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjSur2WithPatch() throws Exception {
        // Initialize the database
        bidPrjSur2Repository.saveAndFlush(bidPrjSur2);

        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();

        // Update the bidPrjSur2 using partial update
        BidPrjSur2 partialUpdatedBidPrjSur2 = new BidPrjSur2();
        partialUpdatedBidPrjSur2.setId(bidPrjSur2.getId());

        partialUpdatedBidPrjSur2
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .disNo(UPDATED_DIS_NO)
            .amnta(UPDATED_AMNTA)
            .amntb(UPDATED_AMNTB)
            .amnt(UPDATED_AMNT)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP);

        restBidPrjSur2MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjSur2.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjSur2))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjSur2 in the database
        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
        BidPrjSur2 testBidPrjSur2 = bidPrjSur2List.get(bidPrjSur2List.size() - 1);
        assertThat(testBidPrjSur2.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjSur2.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjSur2.getDisNo()).isEqualTo(UPDATED_DIS_NO);
        assertThat(testBidPrjSur2.getAmnta()).isEqualByComparingTo(UPDATED_AMNTA);
        assertThat(testBidPrjSur2.getAmntb()).isEqualByComparingTo(UPDATED_AMNTB);
        assertThat(testBidPrjSur2.getAmnt()).isEqualByComparingTo(UPDATED_AMNT);
        assertThat(testBidPrjSur2.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjSur2.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjSur2.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjSur2.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjSur2.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjSur2.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjSur2.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjSur2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();
//        bidPrjSur2.setId(count.incrementAndGet());
//
//        // Create the BidPrjSur2
//        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(bidPrjSur2);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjSur2MockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjSur2DTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSur2 in the database
//        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
//        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjSur2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();
//        bidPrjSur2.setId(count.incrementAndGet());
//
//        // Create the BidPrjSur2
//        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(bidPrjSur2);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSur2MockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjSur2 in the database
//        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
//        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjSur2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjSur2Repository.findAll().size();
//        bidPrjSur2.setId(count.incrementAndGet());
//
//        // Create the BidPrjSur2
//        BidPrjSur2DTO bidPrjSur2DTO = bidPrjSur2Mapper.toDto(bidPrjSur2);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjSur2MockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjSur2DTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjSur2 in the database
//        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
//        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjSur2() throws Exception {
        // Initialize the database
        bidPrjSur2Repository.saveAndFlush(bidPrjSur2);

        int databaseSizeBeforeDelete = bidPrjSur2Repository.findAll().size();

        // Delete the bidPrjSur2
        restBidPrjSur2MockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjSur2.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjSur2> bidPrjSur2List = bidPrjSur2Repository.findAll();
        assertThat(bidPrjSur2List).hasSize(databaseSizeBeforeDelete - 1);
    }
}
