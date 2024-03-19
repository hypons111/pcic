package gov.pcc.pwc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.domain.PwcDownloadAppendix;
import gov.pcc.pwc.repository.PwcDownloadAppendixRepository;
import gov.pcc.pwc.service.dto.PwcDownloadAppendixDTO;
import gov.pcc.pwc.service.mapper.PwcDownloadAppendixMapper;
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
 * Integration tests for the {@link PwcDownloadAppendixResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PwcDownloadAppendixResourceIT {

    private static final Long DEFAULT_PRO_Download_ID = 1L;
    private static final Long UPDATED_PRO_Download_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/pwc-download-appendices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{proDownloadAppendixId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PwcDownloadAppendixRepository pwcDownloadAppendixRepository;

    @Autowired
    private PwcDownloadAppendixMapper pwcDownloadAppendixMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPwcDownloadAppendixMockMvc;

    private PwcDownloadAppendix pwcDownloadAppendix;

    PwcDownloadAppendixResourceIT() {}

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcDownloadAppendix createEntity(EntityManager em) {
        PwcDownloadAppendix pwcDownloadAppendix = new PwcDownloadAppendix()
            .proDownloadId(DEFAULT_PRO_Download_ID)
            .attributes(DEFAULT_ATTRIBUTES)
            .text(DEFAULT_TEXT)
            .appendix(DEFAULT_APPENDIX)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER);
        return pwcDownloadAppendix;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PwcDownloadAppendix createUpdatedEntity(EntityManager em) {
        PwcDownloadAppendix pwcDownloadAppendix = new PwcDownloadAppendix()
            .proDownloadId(UPDATED_PRO_Download_ID)
            .attributes(UPDATED_ATTRIBUTES)
            .text(UPDATED_TEXT)
            .appendix(UPDATED_APPENDIX)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        return pwcDownloadAppendix;
    }

    @BeforeEach
    public void initTest() {
        pwcDownloadAppendix = createEntity(em);
    }

    @Test
    @Transactional
    void createPwcDownloadAppendix() throws Exception {
        int databaseSizeBeforeCreate = pwcDownloadAppendixRepository.findAll().size();
        // Create the PwcDownloadAppendix
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);
        restPwcDownloadAppendixMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeCreate + 1);
        PwcDownloadAppendix testPwcDownloadAppendix = pwcDownloadAppendixList.get(pwcDownloadAppendixList.size() - 1);
        assertThat(testPwcDownloadAppendix.getProDownloadId()).isEqualTo(DEFAULT_PRO_Download_ID);
        assertThat(testPwcDownloadAppendix.getAttributes()).isEqualTo(DEFAULT_ATTRIBUTES);
        assertThat(testPwcDownloadAppendix.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testPwcDownloadAppendix.getAppendix()).isEqualTo(DEFAULT_APPENDIX);
        assertThat(testPwcDownloadAppendix.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcDownloadAppendix.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
        assertThat(testPwcDownloadAppendix.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testPwcDownloadAppendix.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void createPwcDownloadAppendixWithExistingId() throws Exception {
        // Create the PwcDownloadAppendix with an existing ID
        pwcDownloadAppendix.setProDownloadAppendixId(1L);
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);

        int databaseSizeBeforeCreate = pwcDownloadAppendixRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPwcDownloadAppendixMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPwcDownloadAppendices() throws Exception {
        // Initialize the database
        pwcDownloadAppendixRepository.saveAndFlush(pwcDownloadAppendix);

        // Get all the pwcDownloadAppendixList
        restPwcDownloadAppendixMockMvc
            .perform(get(ENTITY_API_URL + "?sort=proDownloadAppendixId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].proDownloadAppendixId").value(hasItem(pwcDownloadAppendix.getProDownloadAppendixId().intValue())))
            .andExpect(jsonPath("$.[*].proDownloadId").value(hasItem(DEFAULT_PRO_Download_ID.intValue())))
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
    void getPwcDownloadAppendix() throws Exception {
        // Initialize the database
        pwcDownloadAppendixRepository.saveAndFlush(pwcDownloadAppendix);

        // Get the pwcDownloadAppendix
        restPwcDownloadAppendixMockMvc
            .perform(get(ENTITY_API_URL_ID, pwcDownloadAppendix.getProDownloadAppendixId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.proDownloadAppendixId").value(pwcDownloadAppendix.getProDownloadAppendixId().intValue()))
            .andExpect(jsonPath("$.proDownloadId").value(DEFAULT_PRO_Download_ID.intValue()))
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
    void getNonExistingPwcDownloadAppendix() throws Exception {
        // Get the pwcDownloadAppendix
        restPwcDownloadAppendixMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPwcDownloadAppendix() throws Exception {
        // Initialize the database
        pwcDownloadAppendixRepository.saveAndFlush(pwcDownloadAppendix);

        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();

        // Update the pwcDownloadAppendix
        PwcDownloadAppendix updatedPwcDownloadAppendix = pwcDownloadAppendixRepository
            .findById(pwcDownloadAppendix.getProDownloadAppendixId())
            .get();
        // Disconnect from session so that the updates on updatedPwcDownloadAppendix are not directly saved in db
        em.detach(updatedPwcDownloadAppendix);
        updatedPwcDownloadAppendix
            .proDownloadId(UPDATED_PRO_Download_ID)
            .attributes(UPDATED_ATTRIBUTES)
            .text(UPDATED_TEXT)
            .appendix(UPDATED_APPENDIX)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(updatedPwcDownloadAppendix);

        restPwcDownloadAppendixMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcDownloadAppendixDTO.getProDownloadAppendixId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadAppendix testPwcDownloadAppendix = pwcDownloadAppendixList.get(pwcDownloadAppendixList.size() - 1);
        assertThat(testPwcDownloadAppendix.getProDownloadId()).isEqualTo(UPDATED_PRO_Download_ID);
        assertThat(testPwcDownloadAppendix.getAttributes()).isEqualTo(UPDATED_ATTRIBUTES);
        assertThat(testPwcDownloadAppendix.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcDownloadAppendix.getAppendix()).isEqualTo(UPDATED_APPENDIX);
        assertThat(testPwcDownloadAppendix.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownloadAppendix.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownloadAppendix.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownloadAppendix.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void putNonExistingPwcDownloadAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();
        pwcDownloadAppendix.setProDownloadAppendixId(count.incrementAndGet());

        // Create the PwcDownloadAppendix
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcDownloadAppendixMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pwcDownloadAppendixDTO.getProDownloadAppendixId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPwcDownloadAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();
        pwcDownloadAppendix.setProDownloadAppendixId(count.incrementAndGet());

        // Create the PwcDownloadAppendix
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadAppendixMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPwcDownloadAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();
        pwcDownloadAppendix.setProDownloadAppendixId(count.incrementAndGet());

        // Create the PwcDownloadAppendix
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadAppendixMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePwcDownloadAppendixWithPatch() throws Exception {
        // Initialize the database
        pwcDownloadAppendixRepository.saveAndFlush(pwcDownloadAppendix);

        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();

        // Update the pwcDownloadAppendix using partial update
        PwcDownloadAppendix partialUpdatedPwcDownloadAppendix = new PwcDownloadAppendix();
        partialUpdatedPwcDownloadAppendix.setProDownloadAppendixId(pwcDownloadAppendix.getProDownloadAppendixId());

        partialUpdatedPwcDownloadAppendix
            .proDownloadId(UPDATED_PRO_Download_ID)
            .appendix(UPDATED_APPENDIX)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME);

        restPwcDownloadAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcDownloadAppendix.getProDownloadAppendixId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcDownloadAppendix))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadAppendix testPwcDownloadAppendix = pwcDownloadAppendixList.get(pwcDownloadAppendixList.size() - 1);
        assertThat(testPwcDownloadAppendix.getProDownloadId()).isEqualTo(UPDATED_PRO_Download_ID);
        assertThat(testPwcDownloadAppendix.getAttributes()).isEqualTo(DEFAULT_ATTRIBUTES);
        assertThat(testPwcDownloadAppendix.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testPwcDownloadAppendix.getAppendix()).isEqualTo(UPDATED_APPENDIX);
        assertThat(testPwcDownloadAppendix.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPwcDownloadAppendix.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownloadAppendix.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownloadAppendix.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    void fullUpdatePwcDownloadAppendixWithPatch() throws Exception {
        // Initialize the database
        pwcDownloadAppendixRepository.saveAndFlush(pwcDownloadAppendix);

        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();

        // Update the pwcDownloadAppendix using partial update
        PwcDownloadAppendix partialUpdatedPwcDownloadAppendix = new PwcDownloadAppendix();
        partialUpdatedPwcDownloadAppendix.setProDownloadAppendixId(pwcDownloadAppendix.getProDownloadAppendixId());

        partialUpdatedPwcDownloadAppendix
            .proDownloadId(UPDATED_PRO_Download_ID)
            .attributes(UPDATED_ATTRIBUTES)
            .text(UPDATED_TEXT)
            .appendix(UPDATED_APPENDIX)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER);

        restPwcDownloadAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPwcDownloadAppendix.getProDownloadAppendixId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPwcDownloadAppendix))
            )
            .andExpect(status().isOk());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
        PwcDownloadAppendix testPwcDownloadAppendix = pwcDownloadAppendixList.get(pwcDownloadAppendixList.size() - 1);
        assertThat(testPwcDownloadAppendix.getProDownloadId()).isEqualTo(UPDATED_PRO_Download_ID);
        assertThat(testPwcDownloadAppendix.getAttributes()).isEqualTo(UPDATED_ATTRIBUTES);
        assertThat(testPwcDownloadAppendix.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPwcDownloadAppendix.getAppendix()).isEqualTo(UPDATED_APPENDIX);
        assertThat(testPwcDownloadAppendix.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPwcDownloadAppendix.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
        assertThat(testPwcDownloadAppendix.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testPwcDownloadAppendix.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    void patchNonExistingPwcDownloadAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();
        pwcDownloadAppendix.setProDownloadAppendixId(count.incrementAndGet());

        // Create the PwcDownloadAppendix
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPwcDownloadAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pwcDownloadAppendixDTO.getProDownloadAppendixId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPwcDownloadAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();
        pwcDownloadAppendix.setProDownloadAppendixId(count.incrementAndGet());

        // Create the PwcDownloadAppendix
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPwcDownloadAppendix() throws Exception {
        int databaseSizeBeforeUpdate = pwcDownloadAppendixRepository.findAll().size();
        pwcDownloadAppendix.setProDownloadAppendixId(count.incrementAndGet());

        // Create the PwcDownloadAppendix
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPwcDownloadAppendixMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pwcDownloadAppendixDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PwcDownloadAppendix in the database
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePwcDownloadAppendix() throws Exception {
        // Initialize the database
        pwcDownloadAppendixRepository.saveAndFlush(pwcDownloadAppendix);

        int databaseSizeBeforeDelete = pwcDownloadAppendixRepository.findAll().size();

        // Delete the pwcDownloadAppendix
        restPwcDownloadAppendixMockMvc
            .perform(delete(ENTITY_API_URL_ID, pwcDownloadAppendix.getProDownloadAppendixId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PwcDownloadAppendix> pwcDownloadAppendixList = pwcDownloadAppendixRepository.findAll();
        assertThat(pwcDownloadAppendixList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
