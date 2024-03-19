package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.MrpResourceList;
import gov.pcc.pwc.repository.impl.MrpResourceListRepositoryImpl;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MrpResourceList}.
 */
@Service
@Transactional
public class MrpResourceListService {

    private final Logger log = LoggerFactory.getLogger(MrpResourceListService.class);

//    private final MrpResourceListRepository mrpResourceListRepository;

//    private final MrpResourceListMapper mrpResourceListMapper;

    private final MrpResourceListRepositoryImpl mrpResourceListRepositoryImpl;

//    public MrpResourceListService(MrpResourceListRepository mrpResourceListRepository, MrpResourceListMapper mrpResourceListMapper) {
//        this.mrpResourceListRepository = mrpResourceListRepository;
//        this.mrpResourceListMapper = mrpResourceListMapper;
//    }

    public MrpResourceListService(MrpResourceListRepositoryImpl mrpResourceListRepositoryImpl) {
        this.mrpResourceListRepositoryImpl = mrpResourceListRepositoryImpl;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryKPI() {
        return mrpResourceListRepositoryImpl.queryKPI();
    }

//    @Transactional(readOnly = true)
//    public Map<String, Object> test(HttpServletRequest request) {
//        final Map<String, Object> rs = mrpResourceListRepositoryImpl.test(request);
//        return rs;
//    }



}
