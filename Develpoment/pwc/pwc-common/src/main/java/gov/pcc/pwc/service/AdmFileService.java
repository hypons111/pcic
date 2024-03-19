package gov.pcc.pwc.service;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.MessageCodeException;
import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.repository.AdmFileRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AdmFileService {

    private final String dir = "/mnt/stsdat/pwc/";

    private final Logger log = LoggerFactory.getLogger(AdmFileService.class);

    private final AdmFileRepository admFileRepository;

    public AdmFileService(AdmFileRepository admFileRepository) {
        this.admFileRepository = admFileRepository;
    }

    public AdmFile save(AdmFile admFile) throws IOException {
        log.debug("Request to update AdmFile : {}", admFile);

        return admFileRepository.save(admFile);
    }
    public AdmFile save(AdmFile admFile, MultipartFile file, String functionPath) throws IOException {
        log.debug("Request to save AdmFile : {}", file);

        Path path = Paths.get(dir + functionPath);
        if(!Files.exists(path)) Files.createDirectories(path);
        Optional<AdmFile> opt = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId(admFile.getModuleType(), admFile.getBusinessType(), admFile.getSourceId());
        if(opt.isPresent()) {
            AdmFile af = opt.get();
            admFile.setId(af.getId());
            Files.delete(Path.of(af.getS3Url()));
        }
        String fileName = file.getOriginalFilename();
        String fe = "";
        if (fileName.contains("."))
            fe = fileName.substring(fileName.lastIndexOf(".")+1);
        admFile.setFileType(fe);
        if(admFile.getFilePathName() == null) admFile.setFilePathName(file.getOriginalFilename());
        admFile.setFileNameOri(file.getOriginalFilename());
        admFile.setS3Url(path + File.separator + admFile.getFilePathName());
        admFile.setFileVirusStatus("1");

        File file1 = new File(path + File.separator + admFile.getFilePathName());
        if(file1.exists()) {
            throw new MessageCodeException(MessageCodes.PWC_COMMON_0013_E(file1.getName()));
        }
        file.transferTo(file1);
        return admFileRepository.save(admFile);
    }

    public AdmFile saveFile(AdmFile admFile, MultipartFile file, String functionPath) throws IOException {
        log.debug("Request to save AdmFile : {}", file);

        Path path = Paths.get(dir + functionPath);
        if(!Files.exists(path)) Files.createDirectories(path);

        if(StringUtils.isNotBlank(admFile.getS3Url())) {
            Files.delete(Path.of(admFile.getS3Url()));
        }
        String fileName = file.getOriginalFilename();
        String fe = "";
        if (fileName.contains(".")) {
            fe = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        admFile.setFileType(fe);
        admFile.setFileNameOri(file.getOriginalFilename());
        UUID uuid = UUID.randomUUID();
        admFile.setFilePathName(path + File.separator + uuid);
        admFile.setS3Url(path + File.separator + uuid);
        admFile.setFileVirusStatus("1");

        File file1 = new File(path + File.separator + uuid);
        if(file1.exists()) {
            throw new MessageCodeException(MessageCodes.PWC_COMMON_0013_E(file1.getName()));
        }
        file.transferTo(file1);
        return admFileRepository.save(admFile);
    }

    public void delete(Long id) throws IOException {
        log.debug("Request to delete AdmFile : {}", id);

        Optional<AdmFile> admFile = admFileRepository.findById(id);
        if(admFile.isPresent()) {
            Files.delete(Path.of(admFile.get().getS3Url()));
            admFileRepository.delete(admFile.get());
        }
    }

    public void delete(AdmFile admFile) throws IOException {
        log.debug("Request to delete AdmFile : {}", admFile);

        Files.delete(Path.of(admFile.getS3Url()));
        admFileRepository.delete(admFile);
    }
}
