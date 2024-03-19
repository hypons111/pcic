package gov.pcc.pwc.web.rest;

import static gov.pcc.pwc.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.BidPrjPay2;
import gov.pcc.pwc.repository.BidPrjPay2Repository;
import gov.pcc.pwc.service.dto.BidPrjPay2DTO;
import gov.pcc.pwc.service.mapper.BidPrjPay2Mapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link BidPrjPay2Resource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BidPrjPay2ResourceIT {

    private static final String DEFAULT_WKUT = "AAAAAAAAAA";
    private static final String UPDATED_WKUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRJNO = "AAAAAAAAAA";
    private static final String UPDATED_PRJNO = "BBBBBBBBBB";

    private static final String DEFAULT_YEAR = "AAA";
    private static final String UPDATED_YEAR = "BBB";

    private static final String DEFAULT_MONTH = "AAA";
    private static final String UPDATED_MONTH = "BBB";

    private static final BigDecimal DEFAULT_TRUE_MON = new BigDecimal(1);
    private static final BigDecimal UPDATED_TRUE_MON = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PAY_MON_T = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAY_MON_T = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PAY_MON_U = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAY_MON_U = new BigDecimal(2);

    private static final LocalDate DEFAULT_TEN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TEN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_TEN_MON = new BigDecimal(1);
    private static final BigDecimal UPDATED_TEN_MON = new BigDecimal(2);

    private static final LocalDate DEFAULT_PAY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PAY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PAY_MON = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAY_MON = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PAY_MON_S = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAY_MON_S = new BigDecimal(2);

    private static final String DEFAULT_CHK_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHK_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_RMK = "AAAAAAAAAA";
    private static final String UPDATED_PAY_RMK = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TRUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DSTS = "A";
    private static final String UPDATED_DSTS = "B";

    private static final BigDecimal DEFAULT_CONF_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONF_OUT = new BigDecimal(2);

    private static final String DEFAULT_U_IP = "AAAAAAAAAA";
    private static final String UPDATED_U_IP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bid-prj-pay-2-s";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BidPrjPay2Repository bidPrjPay2Repository;

    @Autowired
    private BidPrjPay2Mapper bidPrjPay2Mapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBidPrjPay2MockMvc;

    private BidPrjPay2 bidPrjPay2;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjPay2 createEntity(EntityManager em) {
        BidPrjPay2 bidPrjPay2 = new BidPrjPay2()
            .wkut(DEFAULT_WKUT)
            .prjno(DEFAULT_PRJNO)
            .year(DEFAULT_YEAR)
            .month(DEFAULT_MONTH)
            .trueMon(DEFAULT_TRUE_MON)
            .payMonT(DEFAULT_PAY_MON_T)
            .payMonU(DEFAULT_PAY_MON_U)
            .tenDate(DEFAULT_TEN_DATE)
            .tenMon(DEFAULT_TEN_MON)
            .payDate(DEFAULT_PAY_DATE)
            .payMon(DEFAULT_PAY_MON)
            .payMonS(DEFAULT_PAY_MON_S)
            .chkNo(DEFAULT_CHK_NO)
            .payRmk(DEFAULT_PAY_RMK)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .updateDate(DEFAULT_UPDATE_DATE)
            .updateUser(DEFAULT_UPDATE_USER)
            .trueDate(DEFAULT_TRUE_DATE)
            .dsts(DEFAULT_DSTS)
            .confOut(DEFAULT_CONF_OUT)
            .uIp(DEFAULT_U_IP);
        return bidPrjPay2;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BidPrjPay2 createUpdatedEntity(EntityManager em) {
        BidPrjPay2 bidPrjPay2 = new BidPrjPay2()
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .trueMon(UPDATED_TRUE_MON)
            .payMonT(UPDATED_PAY_MON_T)
            .payMonU(UPDATED_PAY_MON_U)
            .tenDate(UPDATED_TEN_DATE)
            .tenMon(UPDATED_TEN_MON)
            .payDate(UPDATED_PAY_DATE)
            .payMon(UPDATED_PAY_MON)
            .payMonS(UPDATED_PAY_MON_S)
            .chkNo(UPDATED_CHK_NO)
            .payRmk(UPDATED_PAY_RMK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .trueDate(UPDATED_TRUE_DATE)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP);
        return bidPrjPay2;
    }

    @BeforeEach
    public void initTest() {
        bidPrjPay2 = createEntity(em);
    }

    @Test
    @Transactional
    void createBidPrjPay2() throws Exception {
        int databaseSizeBeforeCreate = bidPrjPay2Repository.findAll().size();
        // Create the BidPrjPay2
        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(bidPrjPay2);
        restBidPrjPay2MockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO)))
            .andExpect(status().isCreated());

        // Validate the BidPrjPay2 in the database
        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeCreate + 1);
        BidPrjPay2 testBidPrjPay2 = bidPrjPay2List.get(bidPrjPay2List.size() - 1);
        assertThat(testBidPrjPay2.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjPay2.getPrjno()).isEqualTo(DEFAULT_PRJNO);
        assertThat(testBidPrjPay2.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testBidPrjPay2.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBidPrjPay2.getTrueMon()).isEqualByComparingTo(DEFAULT_TRUE_MON);
        assertThat(testBidPrjPay2.getPayMonT()).isEqualByComparingTo(DEFAULT_PAY_MON_T);
        assertThat(testBidPrjPay2.getPayMonU()).isEqualByComparingTo(DEFAULT_PAY_MON_U);
        assertThat(testBidPrjPay2.getTenDate()).isEqualTo(DEFAULT_TEN_DATE);
        assertThat(testBidPrjPay2.getTenMon()).isEqualByComparingTo(DEFAULT_TEN_MON);
        assertThat(testBidPrjPay2.getPayDate()).isEqualTo(DEFAULT_PAY_DATE);
        assertThat(testBidPrjPay2.getPayMon()).isEqualByComparingTo(DEFAULT_PAY_MON);
        assertThat(testBidPrjPay2.getPayMonS()).isEqualByComparingTo(DEFAULT_PAY_MON_S);
        assertThat(testBidPrjPay2.getChkNo()).isEqualTo(DEFAULT_CHK_NO);
        assertThat(testBidPrjPay2.getPayRmk()).isEqualTo(DEFAULT_PAY_RMK);
        assertThat(testBidPrjPay2.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjPay2.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjPay2.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testBidPrjPay2.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjPay2.getTrueDate()).isEqualTo(DEFAULT_TRUE_DATE);
        assertThat(testBidPrjPay2.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjPay2.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjPay2.getuIp()).isEqualTo(DEFAULT_U_IP);
    }

    @Test
    @Transactional
    void createBidPrjPay2WithExistingId() throws Exception {
        // Create the BidPrjPay2 with an existing ID
//        bidPrjPay2.setId(1L);
//        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(bidPrjPay2);
//
//        int databaseSizeBeforeCreate = bidPrjPay2Repository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restBidPrjPay2MockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjPay2 in the database
//        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
//        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBidPrjPay2s() throws Exception {
        // Initialize the database
        bidPrjPay2Repository.saveAndFlush(bidPrjPay2);

        // Get all the bidPrjPay2List
        restBidPrjPay2MockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bidPrjPay2.getId())))
            .andExpect(jsonPath("$.[*].wkut").value(hasItem(DEFAULT_WKUT)))
            .andExpect(jsonPath("$.[*].prjno").value(hasItem(DEFAULT_PRJNO)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].trueMon").value(hasItem(sameNumber(DEFAULT_TRUE_MON))))
            .andExpect(jsonPath("$.[*].payMonT").value(hasItem(sameNumber(DEFAULT_PAY_MON_T))))
            .andExpect(jsonPath("$.[*].payMonU").value(hasItem(sameNumber(DEFAULT_PAY_MON_U))))
            .andExpect(jsonPath("$.[*].tenDate").value(hasItem(DEFAULT_TEN_DATE.toString())))
            .andExpect(jsonPath("$.[*].tenMon").value(hasItem(sameNumber(DEFAULT_TEN_MON))))
            .andExpect(jsonPath("$.[*].payDate").value(hasItem(DEFAULT_PAY_DATE.toString())))
            .andExpect(jsonPath("$.[*].payMon").value(hasItem(sameNumber(DEFAULT_PAY_MON))))
            .andExpect(jsonPath("$.[*].payMonS").value(hasItem(sameNumber(DEFAULT_PAY_MON_S))))
            .andExpect(jsonPath("$.[*].chkNo").value(hasItem(DEFAULT_CHK_NO)))
            .andExpect(jsonPath("$.[*].payRmk").value(hasItem(DEFAULT_PAY_RMK)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].trueDate").value(hasItem(DEFAULT_TRUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].dsts").value(hasItem(DEFAULT_DSTS)))
            .andExpect(jsonPath("$.[*].confOut").value(hasItem(sameNumber(DEFAULT_CONF_OUT))))
            .andExpect(jsonPath("$.[*].uIp").value(hasItem(DEFAULT_U_IP)));
    }

    @Test
    @Transactional
    void getBidPrjPay2() throws Exception {
        // Initialize the database
        bidPrjPay2Repository.saveAndFlush(bidPrjPay2);

        // Get the bidPrjPay2
        restBidPrjPay2MockMvc
            .perform(get(ENTITY_API_URL_ID, bidPrjPay2.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bidPrjPay2.getId()))
            .andExpect(jsonPath("$.wkut").value(DEFAULT_WKUT))
            .andExpect(jsonPath("$.prjno").value(DEFAULT_PRJNO))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
            .andExpect(jsonPath("$.trueMon").value(sameNumber(DEFAULT_TRUE_MON)))
            .andExpect(jsonPath("$.payMonT").value(sameNumber(DEFAULT_PAY_MON_T)))
            .andExpect(jsonPath("$.payMonU").value(sameNumber(DEFAULT_PAY_MON_U)))
            .andExpect(jsonPath("$.tenDate").value(DEFAULT_TEN_DATE.toString()))
            .andExpect(jsonPath("$.tenMon").value(sameNumber(DEFAULT_TEN_MON)))
            .andExpect(jsonPath("$.payDate").value(DEFAULT_PAY_DATE.toString()))
            .andExpect(jsonPath("$.payMon").value(sameNumber(DEFAULT_PAY_MON)))
            .andExpect(jsonPath("$.payMonS").value(sameNumber(DEFAULT_PAY_MON_S)))
            .andExpect(jsonPath("$.chkNo").value(DEFAULT_CHK_NO))
            .andExpect(jsonPath("$.payRmk").value(DEFAULT_PAY_RMK))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.trueDate").value(DEFAULT_TRUE_DATE.toString()))
            .andExpect(jsonPath("$.dsts").value(DEFAULT_DSTS))
            .andExpect(jsonPath("$.confOut").value(sameNumber(DEFAULT_CONF_OUT)))
            .andExpect(jsonPath("$.uIp").value(DEFAULT_U_IP));
    }

    @Test
    @Transactional
    void getNonExistingBidPrjPay2() throws Exception {
        // Get the bidPrjPay2
        restBidPrjPay2MockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBidPrjPay2() throws Exception {
        // Initialize the database
        bidPrjPay2Repository.saveAndFlush(bidPrjPay2);

        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();

        // Update the bidPrjPay2
        BidPrjPay2 updatedBidPrjPay2 = bidPrjPay2Repository.findById(bidPrjPay2.getId()).get();
        // Disconnect from session so that the updates on updatedBidPrjPay2 are not directly saved in db
        em.detach(updatedBidPrjPay2);
        updatedBidPrjPay2
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .trueMon(UPDATED_TRUE_MON)
            .payMonT(UPDATED_PAY_MON_T)
            .payMonU(UPDATED_PAY_MON_U)
            .tenDate(UPDATED_TEN_DATE)
            .tenMon(UPDATED_TEN_MON)
            .payDate(UPDATED_PAY_DATE)
            .payMon(UPDATED_PAY_MON)
            .payMonS(UPDATED_PAY_MON_S)
            .chkNo(UPDATED_CHK_NO)
            .payRmk(UPDATED_PAY_RMK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .trueDate(UPDATED_TRUE_DATE)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP);
        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(updatedBidPrjPay2);

        restBidPrjPay2MockMvc
            .perform(
                put(ENTITY_API_URL_ID, bidPrjPay2DTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjPay2 in the database
        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
        BidPrjPay2 testBidPrjPay2 = bidPrjPay2List.get(bidPrjPay2List.size() - 1);
        assertThat(testBidPrjPay2.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjPay2.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjPay2.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testBidPrjPay2.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjPay2.getTrueMon()).isEqualByComparingTo(UPDATED_TRUE_MON);
        assertThat(testBidPrjPay2.getPayMonT()).isEqualByComparingTo(UPDATED_PAY_MON_T);
        assertThat(testBidPrjPay2.getPayMonU()).isEqualByComparingTo(UPDATED_PAY_MON_U);
        assertThat(testBidPrjPay2.getTenDate()).isEqualTo(UPDATED_TEN_DATE);
        assertThat(testBidPrjPay2.getTenMon()).isEqualByComparingTo(UPDATED_TEN_MON);
        assertThat(testBidPrjPay2.getPayDate()).isEqualTo(UPDATED_PAY_DATE);
        assertThat(testBidPrjPay2.getPayMon()).isEqualByComparingTo(UPDATED_PAY_MON);
        assertThat(testBidPrjPay2.getPayMonS()).isEqualByComparingTo(UPDATED_PAY_MON_S);
        assertThat(testBidPrjPay2.getChkNo()).isEqualTo(UPDATED_CHK_NO);
        assertThat(testBidPrjPay2.getPayRmk()).isEqualTo(UPDATED_PAY_RMK);
        assertThat(testBidPrjPay2.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjPay2.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjPay2.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjPay2.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjPay2.getTrueDate()).isEqualTo(UPDATED_TRUE_DATE);
        assertThat(testBidPrjPay2.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjPay2.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjPay2.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void putNonExistingBidPrjPay2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();
//        bidPrjPay2.setId(count.incrementAndGet());
//
//        // Create the BidPrjPay2
//        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(bidPrjPay2);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjPay2MockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, bidPrjPay2DTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjPay2 in the database
//        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
//        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBidPrjPay2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();
//        bidPrjPay2.setId(count.incrementAndGet());
//
//        // Create the BidPrjPay2
//        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(bidPrjPay2);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjPay2MockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjPay2 in the database
//        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
//        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBidPrjPay2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();
//        bidPrjPay2.setId(count.incrementAndGet());
//
//        // Create the BidPrjPay2
//        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(bidPrjPay2);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjPay2MockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjPay2 in the database
//        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
//        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBidPrjPay2WithPatch() throws Exception {
        // Initialize the database
        bidPrjPay2Repository.saveAndFlush(bidPrjPay2);

        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();

        // Update the bidPrjPay2 using partial update
        BidPrjPay2 partialUpdatedBidPrjPay2 = new BidPrjPay2();
        partialUpdatedBidPrjPay2.setId(bidPrjPay2.getId());

        partialUpdatedBidPrjPay2
            .prjno(UPDATED_PRJNO)
            .month(UPDATED_MONTH)
            .trueMon(UPDATED_TRUE_MON)
            .tenMon(UPDATED_TEN_MON)
            .payDate(UPDATED_PAY_DATE)
            .payMonS(UPDATED_PAY_MON_S)
            .payRmk(UPDATED_PAY_RMK)
            .updateDate(UPDATED_UPDATE_DATE)
            .trueDate(UPDATED_TRUE_DATE);

        restBidPrjPay2MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjPay2.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjPay2))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjPay2 in the database
        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
        BidPrjPay2 testBidPrjPay2 = bidPrjPay2List.get(bidPrjPay2List.size() - 1);
        assertThat(testBidPrjPay2.getWkut()).isEqualTo(DEFAULT_WKUT);
        assertThat(testBidPrjPay2.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjPay2.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testBidPrjPay2.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjPay2.getTrueMon()).isEqualByComparingTo(UPDATED_TRUE_MON);
        assertThat(testBidPrjPay2.getPayMonT()).isEqualByComparingTo(DEFAULT_PAY_MON_T);
        assertThat(testBidPrjPay2.getPayMonU()).isEqualByComparingTo(DEFAULT_PAY_MON_U);
        assertThat(testBidPrjPay2.getTenDate()).isEqualTo(DEFAULT_TEN_DATE);
        assertThat(testBidPrjPay2.getTenMon()).isEqualByComparingTo(UPDATED_TEN_MON);
        assertThat(testBidPrjPay2.getPayDate()).isEqualTo(UPDATED_PAY_DATE);
        assertThat(testBidPrjPay2.getPayMon()).isEqualByComparingTo(DEFAULT_PAY_MON);
        assertThat(testBidPrjPay2.getPayMonS()).isEqualByComparingTo(UPDATED_PAY_MON_S);
        assertThat(testBidPrjPay2.getChkNo()).isEqualTo(DEFAULT_CHK_NO);
        assertThat(testBidPrjPay2.getPayRmk()).isEqualTo(UPDATED_PAY_RMK);
        assertThat(testBidPrjPay2.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testBidPrjPay2.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testBidPrjPay2.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjPay2.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testBidPrjPay2.getTrueDate()).isEqualTo(UPDATED_TRUE_DATE);
        assertThat(testBidPrjPay2.getDsts()).isEqualTo(DEFAULT_DSTS);
        assertThat(testBidPrjPay2.getConfOut()).isEqualByComparingTo(DEFAULT_CONF_OUT);
        assertThat(testBidPrjPay2.getuIp()).isEqualTo(DEFAULT_U_IP);
    }

    @Test
    @Transactional
    void fullUpdateBidPrjPay2WithPatch() throws Exception {
        // Initialize the database
        bidPrjPay2Repository.saveAndFlush(bidPrjPay2);

        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();

        // Update the bidPrjPay2 using partial update
        BidPrjPay2 partialUpdatedBidPrjPay2 = new BidPrjPay2();
        partialUpdatedBidPrjPay2.setId(bidPrjPay2.getId());

        partialUpdatedBidPrjPay2
            .wkut(UPDATED_WKUT)
            .prjno(UPDATED_PRJNO)
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH)
            .trueMon(UPDATED_TRUE_MON)
            .payMonT(UPDATED_PAY_MON_T)
            .payMonU(UPDATED_PAY_MON_U)
            .tenDate(UPDATED_TEN_DATE)
            .tenMon(UPDATED_TEN_MON)
            .payDate(UPDATED_PAY_DATE)
            .payMon(UPDATED_PAY_MON)
            .payMonS(UPDATED_PAY_MON_S)
            .chkNo(UPDATED_CHK_NO)
            .payRmk(UPDATED_PAY_RMK)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .updateDate(UPDATED_UPDATE_DATE)
            .updateUser(UPDATED_UPDATE_USER)
            .trueDate(UPDATED_TRUE_DATE)
            .dsts(UPDATED_DSTS)
            .confOut(UPDATED_CONF_OUT)
            .uIp(UPDATED_U_IP);

        restBidPrjPay2MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBidPrjPay2.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBidPrjPay2))
            )
            .andExpect(status().isOk());

        // Validate the BidPrjPay2 in the database
        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
        BidPrjPay2 testBidPrjPay2 = bidPrjPay2List.get(bidPrjPay2List.size() - 1);
        assertThat(testBidPrjPay2.getWkut()).isEqualTo(UPDATED_WKUT);
        assertThat(testBidPrjPay2.getPrjno()).isEqualTo(UPDATED_PRJNO);
        assertThat(testBidPrjPay2.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testBidPrjPay2.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBidPrjPay2.getTrueMon()).isEqualByComparingTo(UPDATED_TRUE_MON);
        assertThat(testBidPrjPay2.getPayMonT()).isEqualByComparingTo(UPDATED_PAY_MON_T);
        assertThat(testBidPrjPay2.getPayMonU()).isEqualByComparingTo(UPDATED_PAY_MON_U);
        assertThat(testBidPrjPay2.getTenDate()).isEqualTo(UPDATED_TEN_DATE);
        assertThat(testBidPrjPay2.getTenMon()).isEqualByComparingTo(UPDATED_TEN_MON);
        assertThat(testBidPrjPay2.getPayDate()).isEqualTo(UPDATED_PAY_DATE);
        assertThat(testBidPrjPay2.getPayMon()).isEqualByComparingTo(UPDATED_PAY_MON);
        assertThat(testBidPrjPay2.getPayMonS()).isEqualByComparingTo(UPDATED_PAY_MON_S);
        assertThat(testBidPrjPay2.getChkNo()).isEqualTo(UPDATED_CHK_NO);
        assertThat(testBidPrjPay2.getPayRmk()).isEqualTo(UPDATED_PAY_RMK);
        assertThat(testBidPrjPay2.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testBidPrjPay2.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testBidPrjPay2.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testBidPrjPay2.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testBidPrjPay2.getTrueDate()).isEqualTo(UPDATED_TRUE_DATE);
        assertThat(testBidPrjPay2.getDsts()).isEqualTo(UPDATED_DSTS);
        assertThat(testBidPrjPay2.getConfOut()).isEqualByComparingTo(UPDATED_CONF_OUT);
        assertThat(testBidPrjPay2.getuIp()).isEqualTo(UPDATED_U_IP);
    }

    @Test
    @Transactional
    void patchNonExistingBidPrjPay2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();
//        bidPrjPay2.setId(count.incrementAndGet());
//
//        // Create the BidPrjPay2
//        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(bidPrjPay2);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restBidPrjPay2MockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, bidPrjPay2DTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjPay2 in the database
//        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
//        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBidPrjPay2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();
//        bidPrjPay2.setId(count.incrementAndGet());
//
//        // Create the BidPrjPay2
//        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(bidPrjPay2);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjPay2MockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the BidPrjPay2 in the database
//        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
//        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBidPrjPay2() throws Exception {
//        int databaseSizeBeforeUpdate = bidPrjPay2Repository.findAll().size();
//        bidPrjPay2.setId(count.incrementAndGet());
//
//        // Create the BidPrjPay2
//        BidPrjPay2DTO bidPrjPay2DTO = bidPrjPay2Mapper.toDto(bidPrjPay2);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restBidPrjPay2MockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bidPrjPay2DTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the BidPrjPay2 in the database
//        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
//        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBidPrjPay2() throws Exception {
        // Initialize the database
        bidPrjPay2Repository.saveAndFlush(bidPrjPay2);

        int databaseSizeBeforeDelete = bidPrjPay2Repository.findAll().size();

        // Delete the bidPrjPay2
        restBidPrjPay2MockMvc
            .perform(delete(ENTITY_API_URL_ID, bidPrjPay2.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BidPrjPay2> bidPrjPay2List = bidPrjPay2Repository.findAll();
        assertThat(bidPrjPay2List).hasSize(databaseSizeBeforeDelete - 1);
    }
}
