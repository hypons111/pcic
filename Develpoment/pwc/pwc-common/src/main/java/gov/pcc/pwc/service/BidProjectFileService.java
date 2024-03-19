package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.domain.BidProjectFile;
import gov.pcc.pwc.domain.BidProjectFilePrimaryKey;
import gov.pcc.pwc.repository.AdmFileRepository;
import gov.pcc.pwc.repository.BidProjectFileRepository;
import gov.pcc.pwc.service.dto.BidProjectFileDTO;
import gov.pcc.pwc.service.mapper.BidProjectFileMapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link BidProjectFile}.
 */
@Service
@Transactional
public class BidProjectFileService {

    private final Logger log = LoggerFactory.getLogger(BidProjectFileService.class);

    private final BidProjectFileRepository bidProjectFileRepository;

    private final BidProjectFileMapper bidProjectFileMapper;

    private final AdmFileRepository admFileRepository;

    private final AdmFileService admFileService;

    public BidProjectFileService(BidProjectFileRepository bidProjectFileRepository, BidProjectFileMapper bidProjectFileMapper, AdmFileRepository admFileRepository, AdmFileService admFileService) {
        this.bidProjectFileRepository = bidProjectFileRepository;
        this.bidProjectFileMapper = bidProjectFileMapper;
        this.admFileRepository = admFileRepository;
        this.admFileService = admFileService;
    }

    /**
     * Save a bidProjectFile.
     *
     * @param bidProjectFileDTO the entity to save.
     * @return the persisted entity.
     */
    public BidProjectFileDTO save(BidProjectFileDTO bidProjectFileDTO) {
        log.debug("Request to save BidProjectFile : {}", bidProjectFileDTO);
        BidProjectFile bidProjectFile = bidProjectFileMapper.toEntity(bidProjectFileDTO);
        bidProjectFile = bidProjectFileRepository.save(bidProjectFile);
        return bidProjectFileMapper.toDto(bidProjectFile);
    }

    /**
     * Save a bidProjectFile.
     *
     * @param bidProjectFileDTO the entity to save.
     * @return the persisted entity.
     */
    public BidProjectFileDTO saveFile(BidProjectFileDTO bidProjectFileDTO, MultipartFile file) {
        log.debug("Request to save BidProjectFile : {}", bidProjectFileDTO);
        BidProjectFile bidProjectFile = bidProjectFileMapper.toEntity(bidProjectFileDTO);
        try {
            AdmFile admFile = new AdmFile();
            Long admFileId = bidProjectFile.getAdmFileId();
            if (admFileId != null) {
                Optional<AdmFile> oldAdmFile = admFileRepository.findById(admFileId);
                if(oldAdmFile.isPresent()) {
                    admFile = oldAdmFile.get();
                    admFile.setUpdateTime(bidProjectFile.getUpdateDate());
                    admFile.setUpdateUser(bidProjectFile.getUpdateUser());
                } else {
                    admFile = new AdmFile();
                    admFile.setModuleType("BID");
                    String type = bidProjectFile.getType();
                    if ("1".equals(type)) {
                        admFile.setBusinessType("AAC_002_CKP");
                    } else if ("2".equals(type)) {
                        admFile.setBusinessType("AAC_002_CKA");
                    } else if ("3".equals(type)) {
                        admFile.setBusinessType("AAC_002_CKD");
                    }
                    admFile.setSourceId(bidProjectFile.getPrjno());
                    admFile.setFileVirusStatus("1");
                    admFile.setCreateTime(bidProjectFile.getCreateDate());
                    admFile.setCreateUser(bidProjectFile.getCreateUser());
                }
            } else {
                admFile.setModuleType("BID");
                String type = bidProjectFile.getType();
                if ("1".equals(type)) {
                    admFile.setBusinessType("AAC_002_CKP");
                } else if ("2".equals(type)) {
                    admFile.setBusinessType("AAC_002_CKA");
                } else if ("3".equals(type)) {
                    admFile.setBusinessType("AAC_002_CKD");
                }
                admFile.setSourceId(bidProjectFile.getPrjno());
                admFile.setFileVirusStatus("1");
                admFile.setCreateTime(bidProjectFile.getCreateDate());
                admFile.setCreateUser(bidProjectFile.getCreateUser());
            }
            String functionPath = "BID/PRJ_FILE/" + bidProjectFile.getWkut() + "/" + bidProjectFile.getPrjno() + "/" + bidProjectFile.getType() + "/" + bidProjectFile.getSeq();
            admFile = admFileService.saveFile(admFile, file, functionPath);
            bidProjectFile.setAdmFileId(admFile.getId());

            bidProjectFile = bidProjectFileRepository.save(bidProjectFile);
            return bidProjectFileMapper.toDto(bidProjectFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Partially update a bidProjectFile.
     *
     * @param bidProjectFileDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidProjectFileDTO> partialUpdate(BidProjectFileDTO bidProjectFileDTO) {
        log.debug("Request to partially update BidProjectFile : {}", bidProjectFileDTO);

        return bidProjectFileRepository
            .findById(bidProjectFileDTO.getId())
            .map(existingBidProjectFile -> {
                bidProjectFileMapper.partialUpdate(existingBidProjectFile, bidProjectFileDTO);

                return existingBidProjectFile;
            })
            .map(bidProjectFileRepository::save)
            .map(bidProjectFileMapper::toDto);
    }

    /**
     * Get all the bidProjectFiles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidProjectFileDTO> findAll() {
        log.debug("Request to get all BidProjectFiles");
        return bidProjectFileRepository
            .findAll()
            .stream()
            .map(bidProjectFileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidProjectFile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidProjectFileDTO> findOne(BidProjectFilePrimaryKey id) {
        log.debug("Request to get BidProjectFile : {}", id);
        return bidProjectFileRepository.findById(id).map(bidProjectFileMapper::toDto);
    }

    /**
     * Delete the bidProjectFile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidProjectFilePrimaryKey id) {
        log.debug("Request to delete BidProjectFile : {}", id);
        bidProjectFileRepository.deleteById(id);
    }
}
