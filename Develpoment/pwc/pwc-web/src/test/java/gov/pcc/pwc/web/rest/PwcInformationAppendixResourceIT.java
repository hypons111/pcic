package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwcInformationAppendix;
import gov.pcc.pwc.repository.PwcInformationAppendixRepository;
import gov.pcc.pwc.service.dto.PwcInformationAppendixDTO;
import gov.pcc.pwc.service.mapper.PwcInformationAppendixMapper;
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
 * Integration tests for the {@link PwcInformationAppendixResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwcInformationAppendixResourceIT {

    private static final Long DEFAULT_PRO_INFORMATION_ID = 1L;
    private static final Long UPDATED_PRO_INFORMATION_ID = 2L;

    private static final String DEFAULT_ATTRIBUTES = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTES = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_APPENDIX = "AAAAAAAAAA";
    private static final String UPDATED_APPENDIX = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pwc-information-appendices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proInformationAppendixId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwcInformationAppendixRepository pwcInformationAppendixRepository;

    @Autowired
    private PwcInformationAppendixMapper pwcInformationAppendixMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwcInformationAppendixMockMvc;

    private PwcInformationAppendix pwcInformationAppendix;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcInformationAppendix createEntity(EntityManager em) {
        PwcInformationAppendix pwcInformationAppendix = new PwcInformationAppendix()
            .proInformationId(DEFAULT_PRO_INFORMATION_ID)
            .attributes(DEFAULT_ATTRIBUTES)
            .text(DEFAULT_TEXT)
            .appendix(DEFAULT_APPENDIX)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER);
        return pwcInformationAppendix;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcInformationAppendix createUpdatedEntity(EntityManager em) {
        PwcInformationAppendix pwcInformationAppendix = new PwcInformationAppendix()
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .attributes(UPDATED_ATTRIBUTES)
            .text(UPDATED_TEXT)
            .appendix(UPDATED_APPENDIX)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        return pwcInformationAppendix;
    }

    @BeforeEach
    public void initTest() {
        pwcInformationAppendix = createEntity(em);
    }

    @Test
    @Transactional
    void createPwcInformationAppendix() throws Exception {
        int databaseSizeBeforeCreate = pwcInformationAppendixRepository.findAll().size();
        // Create the PwcInformationAppendix
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(pwcInformationAppendix);
        restPwcInformationAppendixMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeCreate + 1);
        PwcInformationAppendix testPwcInformationAppendix = pwcInformationAppendixList.get(pwcInformationAppendixList.size() - 1);
        assertThat(testPwcInformationAppendix.getProInformationId()).isEqualTo(DEFAULT_PRO_INFORMATION_ID);
        assertThat(testPwcInformationAppendix.getAttributes()).isEqualTo(DEFAULT_ATTRIBUTES);
        assertThat(testPwcInformationAppendix.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testPwcInformationAppendix.getAppendix()).isEqualTo(DEFAULT_APPENDIX);
        assertThat(testPwcInformationAppendix.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcInformationAppendix.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcInformationAppendix.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcInformationAppendix.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void createPwcInformationAppendixWithExistingId() throws Exception {
        // Create the PwcInformationAppendix with an existing ID
        pwcInformationAppendix.setProInformationAppendixId(1L);
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(pwcInformationAppendix);

        int databaseSizeBeforeCreate = pwcInformationAppendixRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwcInformationAppendixMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPwcInformationAppendices() throws Exception {
        // Initialize the database
        pwcInformationAppendixRepository.saveAndFlush(pwcInformationAppendix);

        // Get all the pwcInformationAppendixList
        restPwcInformationAppendixMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proInformationAppendixId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].proInformationAppendixId").value(hasItem(pwcInformationAppendix.getProInformationAppendixId().intValue()))
            )
            .andExpect(jsonPath("$.[*].proInformationId").value(hasItem(DEFAULT_PRO_INFORMATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].attributes").value(hasItem(DEFAULT_ATTRIBUTES)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].appendix").value(hasItem(DEFAULT_APPENDIX)))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER)));
    }

    @Test
    @Transactional
    void getPwcInformationAppendix() throws Exception {
        // Initialize the database
        pwcInformationAppendixRepository.saveAndFlush(pwcInformationAppendix);

        // Get the pwcInformationAppendix
        restPwcInformationAppendixMockMvc
            .perform(get(ENTITY_API_URL_ID, pwcInformationAppendix.getProInformationAppendixId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proInformationAppendixId").value(pwcInformationAppendix.getProInformationAppendixId().intValue()))
            .andExpect(jsonPath("$.proInformationId").value(DEFAULT_PRO_INFORMATION_ID.intValue()))
            .andExpect(jsonPath("$.attributes").value(DEFAULT_ATTRIBUTES))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.appendix").value(DEFAULT_APPENDIX))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER));
    }

    @Test
    @Transactional
    void getNonExistingPwcInformationAppendix() throws Exception {
        // Get the pwcInformationAppendix
        restPwcInformationAppendixMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwcInformationAppendix() throws Exception {
        // Initialize the database
        pwcInformationAppendixRepository.saveAndFlush(pwcInformationAppendix);

        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();

        // Update the pwcInformationAppendix
        PwcInformationAppendix updatedPwcInformationAppendix = pwcInformationAppendixRepository
            .findById(pwcInformationAppendix.getProInformationAppendixId())
            .get();
        // Disconnect from session so that the updates on updatedPwcInformationAppendix are not directly saved in db
        em.detach(updatedPwcInformationAppendix);
        updatedPwcInformationAppendix
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .attributes(UPDATED_ATTRIBUTES)
            .text(UPDATED_TEXT)
            .appendix(UPDATED_APPENDIX)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(updatedPwcInformationAppendix);

        restPwcInformationAppendixMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcInformationAppendixDTO.getProInformationAppendixId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationAppendix testPwcInformationAppendix = pwcInformationAppendixList.get(pwcInformationAppendixList.size() - 1);
        assertThat(testPwcInformationAppendix.getProInformationId()).isEqualTo(UPDATED_PRO_INFORMATION_ID);
        assertThat(testPwcInformationAppendix.getAttributes()).isEqualTo(UPDATED_ATTRIBUTES);
        assertThat(testPwcInformationAppendix.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcInformationAppendix.getAppendix()).isEqualTo(UPDATED_APPENDIX);
        assertThat(testPwcInformationAppendix.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformationAppendix.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformationAppendix.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformationAppendix.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingPwcInformationAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();
        pwcInformationAppendix.setProInformationAppendixId(count.incrementAndGet());

        // Create the PwcInformationAppendix
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(pwcInformationAppendix);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcInformationAppendixMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcInformationAppendixDTO.getProInformationAppendixId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwcInformationAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();
        pwcInformationAppendix.setProInformationAppendixId(count.incrementAndGet());

        // Create the PwcInformationAppendix
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(pwcInformationAppendix);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationAppendixMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwcInformationAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();
        pwcInformationAppendix.setProInformationAppendixId(count.incrementAndGet());

        // Create the PwcInformationAppendix
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(pwcInformationAppendix);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationAppendixMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwcInformationAppendixWithPatch() throws Exception {
        // Initialize the database
        pwcInformationAppendixRepository.saveAndFlush(pwcInformationAppendix);

        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();

        // Update the pwcInformationAppendix using partial update
        PwcInformationAppendix partialUpdatedPwcInformationAppendix = new PwcInformationAppendix();
        partialUpdatedPwcInformationAppendix.setProInformationAppendixId(pwcInformationAppendix.getProInformationAppendixId());

        partialUpdatedPwcInformationAppendix
            .attributes(UPDATED_ATTRIBUTES)
            .text(UPDATED_TEXT)
            .appendix(UPDATED_APPENDIX)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restPwcInformationAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcInformationAppendix.getProInformationAppendixId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcInformationAppendix))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationAppendix testPwcInformationAppendix = pwcInformationAppendixList.get(pwcInformationAppendixList.size() - 1);
        assertThat(testPwcInformationAppendix.getProInformationId()).isEqualTo(DEFAULT_PRO_INFORMATION_ID);
        assertThat(testPwcInformationAppendix.getAttributes()).isEqualTo(UPDATED_ATTRIBUTES);
        assertThat(testPwcInformationAppendix.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcInformationAppendix.getAppendix()).isEqualTo(UPDATED_APPENDIX);
        assertThat(testPwcInformationAppendix.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcInformationAppendix.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformationAppendix.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformationAppendix.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void fullUpdatePwcInformationAppendixWithPatch() throws Exception {
        // Initialize the database
        pwcInformationAppendixRepository.saveAndFlush(pwcInformationAppendix);

        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();

        // Update the pwcInformationAppendix using partial update
        PwcInformationAppendix partialUpdatedPwcInformationAppendix = new PwcInformationAppendix();
        partialUpdatedPwcInformationAppendix.setProInformationAppendixId(pwcInformationAppendix.getProInformationAppendixId());

        partialUpdatedPwcInformationAppendix
            .proInformationId(UPDATED_PRO_INFORMATION_ID)
            .attributes(UPDATED_ATTRIBUTES)
            .text(UPDATED_TEXT)
            .appendix(UPDATED_APPENDIX)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwcInformationAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcInformationAppendix.getProInformationAppendixId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcInformationAppendix))
            )
            .andExpect(status().isOk());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
        PwcInformationAppendix testPwcInformationAppendix = pwcInformationAppendixList.get(pwcInformationAppendixList.size() - 1);
        assertThat(testPwcInformationAppendix.getProInformationId()).isEqualTo(UPDATED_PRO_INFORMATION_ID);
        assertThat(testPwcInformationAppendix.getAttributes()).isEqualTo(UPDATED_ATTRIBUTES);
        assertThat(testPwcInformationAppendix.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcInformationAppendix.getAppendix()).isEqualTo(UPDATED_APPENDIX);
        assertThat(testPwcInformationAppendix.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcInformationAppendix.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcInformationAppendix.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcInformationAppendix.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingPwcInformationAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();
        pwcInformationAppendix.setProInformationAppendixId(count.incrementAndGet());

        // Create the PwcInformationAppendix
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(pwcInformationAppendix);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcInformationAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwcInformationAppendixDTO.getProInformationAppendixId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwcInformationAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();
        pwcInformationAppendix.setProInformationAppendixId(count.incrementAndGet());

        // Create the PwcInformationAppendix
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(pwcInformationAppendix);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwcInformationAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcInformationAppendixRepository.findAll().size();
        pwcInformationAppendix.setProInformationAppendixId(count.incrementAndGet());

        // Create the PwcInformationAppendix
        PwcInformationAppendixDTO pwcInformationAppendixDTO = pwcInformationAppendixMapper.toDto(pwcInformationAppendix);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcInformationAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcInformationAppendixDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcInformationAppendix in the database
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwcInformationAppendix() throws Exception {
        // Initialize the database
        pwcInformationAppendixRepository.saveAndFlush(pwcInformationAppendix);

        int databaseSizeBeforeDelete = pwcInformationAppendixRepository.findAll().size();

        // Delete the pwcInformationAppendix
        restPwcInformationAppendixMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwcInformationAppendix.getProInformationAppendixId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwcInformationAppendix> pwcInformationAppendixList = pwcInformationAppendixRepository.findAll();
        assertThat(pwcInformationAppendixList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
