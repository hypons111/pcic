package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmLoginHisDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class AdmLoginHisCriteria extends Criteria<AdmLoginHisDTO> {

    private String createUserSys;

    private String chName;

    private String chNameRome;

    private String sysCategory;

    private String result;

    private Instant dateStart;

    private Instant dateEnd;

    public String getCreateUserSys() {
        return createUserSys;
    }

    public void setCreateUserSys(String createUserSys) {
        this.createUserSys = createUserSys;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChNameRome() {
        return chNameRome;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getSysCategory() {
        return sysCategory;
    }

    public void setSysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Instant getDateStart() {
        return dateStart;
    }

    public void setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public Predicate toPredicate(Root<AdmLoginHisDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(createUserSys)) {
            predicates.add(criteriaBuilder.like(root.get("createUserSys"), WILD_CARD + createUserSys + WILD_CARD));
        }
        if (StringUtils.isNotBlank(chName)) {
            predicates.add(criteriaBuilder.like(root.get("chName"), WILD_CARD + chName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(chNameRome)) {
            predicates.add(criteriaBuilder.like(root.get("chNameRome"), WILD_CARD + chNameRome + WILD_CARD));
        }
        if (StringUtils.isNotBlank(sysCategory)) {
            predicates.add(criteriaBuilder.equal(root.get("sysCategory"), sysCategory));
        }
        if (StringUtils.isNotBlank(result)) {
            predicates.add(criteriaBuilder.equal(root.get("result"), result));
        }
        if (dateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateStart"), dateStart));
        }
        if (dateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateEnd"), dateEnd));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("createUserSys")) {
            sortBy = "create_User_Sys";
        } else if (this.getSortBy()[0].equals("chName")) {
            sortBy = "ch_Name";
        } else if (this.getSortBy()[0].equals("chNameRome")) {
            sortBy = "ch_Name_Rome";
        } else if (this.getSortBy()[0].equals("sysCategory")) {
            sortBy = "sys_Category";
        } else if (this.getSortBy()[0].equals("result")) {
            sortBy = "result";
        } else if (this.getSortBy()[0].equals("createTime")) {
            sortBy = "create_Time";
        } else {
            sortBy = "create_Time";
        }
        return sortBy + " " + this.getSortDirection().name();
    }
}
