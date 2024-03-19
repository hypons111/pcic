package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.domain.GeoJob;
import gov.pcc.pwc.repository.AdmMailSendRepository;
import gov.pcc.pwc.repository.GeoJobRepository;
import gov.pcc.pwc.service.criteria.GeoJobCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.Geo0607DTO;
import gov.pcc.pwc.service.dto.GeoCompanyDTO;
import gov.pcc.pwc.service.dto.GeoJobDTO;
import gov.pcc.pwc.service.mapper.GeoJobMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static gov.pcc.pwc.repository.impl.RecProjectRepositoryImpl.parseNull;

/**
 * Service Implementation for managing {@link GeoJob}.
 */
@Service
@Transactional
public class GeoJobService {

    private final Logger log = LoggerFactory.getLogger(GeoJobService.class);

    private final GeoJobRepository geoJobRepository;

    private final GeoJobMapper geoJobMapper;

    private final AdmMailSendRepository admMailSendRepository;

    public GeoJobService(GeoJobRepository geoJobRepository, GeoJobMapper geoJobMapper, AdmMailSendRepository admMailSendRepository) {
        this.geoJobRepository = geoJobRepository;
        this.geoJobMapper = geoJobMapper;
        this.admMailSendRepository = admMailSendRepository;
    }

    /**
     * Save a geoJob.
     *
     * @param geoJobDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoJobDTO save(GeoJobDTO geoJobDTO) {
        log.debug("Request to save GeoJob : {}", geoJobDTO);

        GeoJob geoJob = geoJobMapper.toEntity(geoJobDTO);
        geoJob = geoJobRepository.save(geoJob);

        return geoJobMapper.toDto(geoJob);
    }

    /**
     * Partially update a geoJob.
     *
     * @param geoJobDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GeoJobDTO> partialUpdate(GeoJobDTO geoJobDTO) {
        log.debug("Request to partially update GeoJob : {}", geoJobDTO);

        return geoJobRepository
                .findById(geoJobDTO.getId())
                .map(existingGeoJob -> {
                    geoJobMapper.partialUpdate(existingGeoJob, geoJobDTO);

                    return existingGeoJob;
                })
                .map(geoJobRepository::save)
                .map(geoJobMapper::toDto);
    }

    /**
     * Get all the geoJobs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GeoJobDTO> findAll() {
        log.debug("Request to get all GeoJobs");
        return geoJobRepository.findAll().stream().map(geoJobMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one geoJob by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoJobDTO> findOne(Long id) {
        log.debug("Request to get GeoJob : {}", id);
        return geoJobRepository.findById(id).map(geoJobMapper::toDto);
    }

    //    @Transactional(readOnly = true)
//    public Page<GeoJobDTO> findByCriteria(GeoJobCriteria criteria) {
//        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
//            criteria.setSortBy(new String[] { "id" });
//        }
//        final Page<GeoJob> geoJobs = geoJobRepository.findAll(criteria, criteria.toPageable());
//        return geoJobs.map(geoJobMapper::toDto);
//    }
    @Transactional(readOnly = true)
    public Page<GeoJobDTO> findByCriteriaImpl(GeoJobCriteria criteria) {
        log.debug("Request to get all GeoJobs by criteria");
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"id"});
        }
        return geoJobRepository.findAllByCriteria(criteria);
    }


    /**
     * Delete the geoJob by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoJob : {}", id);

        geoJobRepository.deleteById(id);
    }


    /**
     * Get one admAccountContactDTO
     *
     * @param geoJobDTO the id of the entity.
     * @return the AdmMailSend.
     */
    public void saveMailSend(GeoJobDTO geoJobDTO, Authentication authentication, String receiver, String subject, String content, String action) {
        log.debug("Request to get AdmAccount & AdmContact : {}", geoJobDTO.getId());

        //郵件發送主檔
        AdmMailSend admMailSend = new AdmMailSend();
        admMailSend.setMailType(action);
        admMailSend.setSourceId(authentication.getName());
        admMailSend.setReceiver(receiver);
        admMailSend.setSubject(subject);
        admMailSend.setContent(content);
        admMailSend.setExpectSendTime(Instant.now());
        admMailSend.setStatus("W");
        admMailSend.setCreateUser(authentication.getName());
        admMailSend.setCreateTime(Instant.now());
        admMailSend.setUpdateUser(authentication.getName());
        admMailSend.setUpdateTime(Instant.now());
        admMailSendRepository.save(admMailSend);
    }

    @Transactional(readOnly = true)
    public Page<GeoJobDTO> findByWorkPlace(GeoJobCriteria criteria) {
        log.debug("Request to get all GeoJobs by workPlaceList");
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"id"});
        }
        return geoJobRepository.findByWorkPlace(criteria);
    }

    @Transactional(readOnly = true)
    public Page<Geo0607DTO> findJobCounts(GeoJobCriteria criteria) {
        log.debug("Request to get JobCounts ");
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"id"});
        }

        return geoJobRepository.findJobCounts(criteria);
    }

    @Transactional(readOnly = true)
    public void exportJobCounts(GeoJobCriteria criteria, String downloadFilePath, String fileName) throws IOException {
        log.debug("Request to get exportJobCounts");

        Map<Integer, List<String>> paramMap = new TreeMap<>();
        String firstMon;
        String lastMon;
        List<String> column1 = new ArrayList<>();
        String year = String.valueOf(criteria.getPublishDate1().getYear());
        if (criteria.getPublishDate1().getMonthValue() < 10) {
            firstMon = "0" + criteria.getPublishDate1().getMonthValue();
        } else {
            firstMon = String.valueOf(criteria.getPublishDate1().getMonthValue());
        }

        if (criteria.getPublishDate2() == null) {
            Map<Integer, List<Geo0607DTO>> geo0607DTOsListMap = new HashMap<>();

            List<Geo0607DTO> geo0607DTOs = geoJobRepository.exportJobCounts(criteria.getPublishDate1(), criteria.getJobKind());
            geo0607DTOsListMap.put(criteria.getPublishDate1().getMonthValue(), geo0607DTOs);

            column1.add("各單位刊登次數與求才人數月報表(" + year + "/" + firstMon + ")");
            //內容設置
            int rowNum = 3;
            for (Geo0607DTO element : geo0607DTOs) {
                List<String> columnList = new LinkedList<>();
                columnList.add(parseNull(element.getCompanyName()));
                columnList.add(parseNull(element.getJobKind()));

                IntStream.range(1, 13)
                        .forEach(monthValue -> {
                            List<Geo0607DTO> list = geo0607DTOsListMap.get(monthValue);
                            if (list != null) {
                                columnList.add(parseNull(element.getPersonCount()));
                                columnList.add(parseNull(element.getOfferCount()));
                            } else {
                                columnList.add("-");
                                columnList.add("-");
                            }
                        });

                paramMap.put(rowNum, columnList);
                rowNum++;
            }
        } else {
            if (criteria.getPublishDate2().getMonthValue() < 10) {
                lastMon = "0" + criteria.getPublishDate2().getMonthValue();
            } else {
                lastMon = String.valueOf(criteria.getPublishDate2().getMonthValue());
            }
            Map<Integer, List<Geo0607DTO>> geo0607DTOsListMap = new HashMap<>();
            int diffMonth = criteria.getPublishDate2().getMonthValue() - criteria.getPublishDate1().getMonthValue();
            for (int i = 0; i <= diffMonth; i++) {
                geo0607DTOsListMap.put(i + criteria.getPublishDate1().getMonthValue(), geoJobRepository.exportJobCounts(criteria.getPublishDate1().plusMonths(i), criteria.getJobKind()));
            }
            column1.add("各單位刊登次數與求才人數月報表(" + year + "/" + firstMon + "~" + lastMon + ")");
            //內容設置
//            List<Geo0607DTO> geo0607DTOs = geo0607DTOsListMap.get(criteria.getPublishDate1().getMonthValue());
            Set<String> set = new TreeSet<>();
            geo0607DTOsListMap.forEach((monthValue, geo0607DTOs) -> {
                geo0607DTOs.forEach(geo0607DTO -> {
                    String pk = geo0607DTO.getCompanyName() + geo0607DTO.getJobKind();
                    set.add(pk);
                });
            });

            for(int i = 0; i < set.size(); i++) {
                List<String> columnList = new LinkedList<>();
                IntStream.range(0, 26).forEach(j -> columnList.add("-"));
                paramMap.put(i + 3, columnList);
            }

            String[] arr = set.toArray(String[]::new);
            for(int i = 0; i < arr.length; i++) {
                for(int monthValue : geo0607DTOsListMap.keySet()) {
                    List<Geo0607DTO> geo0607DTOS = geo0607DTOsListMap.get(monthValue);
                    for (Geo0607DTO geo0607DTO : geo0607DTOS) {
                        String pk = geo0607DTO.getCompanyName() + geo0607DTO.getJobKind();
                        if (Objects.equals(arr[i], pk)) {
                            List<String> columnList = paramMap.get(i + 3);
                            columnList.set(0, parseNull(geo0607DTO.getCompanyName()));
                            columnList.set(1, parseNull(geo0607DTO.getJobKind()));
                            IntStream.range(1, 13)
                                    .forEach(monthVal -> {
                                        if (monthVal == monthValue) {
                                            columnList.set(monthVal * 2, parseNull(geo0607DTO.getPersonCount()));
                                            columnList.set(monthVal * 2 + 1, parseNull(geo0607DTO.getOfferCount()));
                                        }
                                    });

                        }
                    }
                }
            }
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("各單位刊登次數與求才人數月報表");

        CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(region1);
        int j = 2;
        for (int i = 0; i < 12; i++) {
            CellRangeAddress region2 = new CellRangeAddress(1, 1, j + 0, j + 1);
            sheet.addMergedRegion(region2);
            j = j + 2;
        }
        List<String> column2 = Arrays.asList(new String[]{"日期:", "", year + "/01", "", year + "/02", "", year + "/03", "", year + "/04", "",
                year + "/05", "", year + "/06", "", year + "/07", "", year + "/08", "", year + "/09", "", year + "/10", "", year + "/11", "", year + "/12"
        });
        List<String> column3 = Arrays.asList(new String[]{"單位名稱", "類別", "求才總人數", "刊登次數", "求才總人數", "刊登次數",
                "求才總人數", "刊登次數", "求才總人數", "刊登次數", "求才總人數", "刊登次數", "求才總人數", "刊登次數", "求才總人數", "刊登次數", "求才總人數", "刊登次數", "求才總人數", "刊登次數", "求才總人數", "刊登次數", "求才總人數", "刊登次數", "求才總人數", "刊登次數",
        });

        paramMap.put(0, column1);
        paramMap.put(1, column2);
        paramMap.put(2, column3);

        // 展開取得map內容
        paramMap.forEach((k, v) -> {
            // 取得輸入列
            HSSFRow row = sheet.createRow(k);
            List<String> contentList = paramMap.get(k);
            int cellId = 0;
            for (String contentElement : contentList) {
                // 取得輸入儲存格
                Cell cell = row.createCell(cellId++);
                cell.setCellValue(contentElement);
            }
        });

        File file = new File(downloadFilePath + fileName);
        FileOutputStream destination = new FileOutputStream(file);
        workbook.write(destination);
        destination.close();
    }

}
