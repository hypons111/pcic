package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.MrpSynonymDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MrpSynonymCriteria extends Criteria<MrpSynonymDTO> {

    private Long mrpSynonymId;

    private String sectionName;

    private String keyword;

    private String phrase;

    private String updateId;

    private String createId;

    private String dataKeyType;

    private String name;

    private String email;

    private String phone;

    private String start_time;

    private String end_time;

    private String check_budget_date;

    private String project_id;

    private String code;
    private String unit;
    private String price;
    private String purchase_quantity;
    private String person_name;
    private String persontel;
    private String person_mail;
    private String wrong_return_description;
    private String account_id;
    private String btn_name;
    private String keyword_name;
    private String create_id;
    public Long getMrpSynonymId() {
        return mrpSynonymId;
    }

    public void setMrpSynonymId(Long mrpSynonymId) {
        this.mrpSynonymId = mrpSynonymId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getDataKeyType() {
        return dataKeyType;
    }

    public void setDataKeyType(String dataKeyType) {
        this.dataKeyType = dataKeyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCheck_budget_date() {
        return check_budget_date;
    }

    public void setCheck_budget_date(String check_budget_date) {
        this.check_budget_date = check_budget_date;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurchase_quantity() {
        return purchase_quantity;
    }

    public void setPurchase_quantity(String purchase_quantity) {
        this.purchase_quantity = purchase_quantity;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPersontel() {
        return persontel;
    }

    public void setPersontel(String persontel) {
        this.persontel = persontel;
    }

    public String getPerson_mail() {
        return person_mail;
    }

    public void setPerson_mail(String person_mail) {
        this.person_mail = person_mail;
    }

    public String getWrong_return_description() {
        return wrong_return_description;
    }

    public void setWrong_return_description(String wrong_return_description) {
        this.wrong_return_description = wrong_return_description;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getBtn_name() {
        return btn_name;
    }

    public void setBtn_name(String btn_name) {
        this.btn_name = btn_name;
    }

    public String getKeyword_name() {
        return keyword_name;
    }

    public void setKeyword_name(String keyword_name) {
        this.keyword_name = keyword_name;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MrpSynonymCriteria that = (MrpSynonymCriteria) o;
        return Objects.equals(mrpSynonymId, that.mrpSynonymId) && Objects.equals(sectionName, that.sectionName)
                && Objects.equals(keyword, that.keyword)&& Objects.equals(phrase, that.phrase)
                && Objects.equals(updateId, that.updateId)&& Objects.equals(createId, that.createId)
                && Objects.equals(dataKeyType, that.dataKeyType) && Objects.equals(name, that.name)
                && Objects.equals(email, that.email) && Objects.equals(phone, that.phone)
                && Objects.equals(start_time, that.start_time) && Objects.equals(end_time, that.end_time)
                && Objects.equals(check_budget_date, that.check_budget_date) && Objects.equals(project_id, that.project_id)
                && Objects.equals(code, that.code) && Objects.equals(unit, that.unit)
                && Objects.equals(price, that.price) && Objects.equals(purchase_quantity, that.purchase_quantity)
                && Objects.equals(person_name, that.person_name) && Objects.equals(persontel, that.persontel)
                && Objects.equals(person_mail, that.person_mail) && Objects.equals(wrong_return_description, that.wrong_return_description)
                && Objects.equals(account_id, that.account_id) && Objects.equals(btn_name, that.btn_name)
                && Objects.equals(keyword_name, that.keyword_name) && Objects.equals(create_id, that.create_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mrpSynonymId, sectionName, keyword, phrase, updateId, createId, dataKeyType, name, email, phone, start_time, end_time, check_budget_date, project_id, code, unit, price, purchase_quantity, person_name, persontel, person_mail, wrong_return_description, account_id, btn_name, keyword_name, create_id);
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<MrpSynonymDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (mrpSynonymId != null) { // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("mrpSynonymId"), mrpSynonymId
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(sectionName)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("sectionName"), sectionName
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(keyword)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("keyword"), keyword
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(phrase)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("phrase"), phrase
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(updateId)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("updateId"), updateId
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(createId)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("createId"), createId
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(dataKeyType)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("dataKeyType"), dataKeyType
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(name)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("name"), name
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(email)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("email"), email
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(phone)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("phone"), phone
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(start_time)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("start_time"), start_time
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(end_time)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("end_time"), end_time
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(check_budget_date)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("check_budget_date"), check_budget_date
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(project_id)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("project_id"), project_id
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(code)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("code"), code
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(unit)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("unit"), unit
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(price)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("price"), price
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(purchase_quantity)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("purchase_quantity"), purchase_quantity
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(person_name)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("person_name"), person_name
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(persontel)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("persontel"), persontel
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(person_mail)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("person_mail"), person_mail
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(wrong_return_description)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("wrong_return_description"), wrong_return_description
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(account_id)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("account_id"), account_id
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(btn_name)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("btn_name"), btn_name
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(keyword_name)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("keyword_name"), keyword_name
            )); // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(create_id)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("create_id"), create_id
            )); // 資料庫欄位名稱
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String toString() {
        return "Eng0822R1DTOCriteria{" +
                "mrpSynonymId='" + mrpSynonymId + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", keyword='" + keyword + '\'' +
                ", phrase='" + phrase + '\'' +
                ", updateId='" + updateId + '\'' +
                ", createId='" + createId + '\'' +
                ", dataKeyType='" + dataKeyType + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", check_budget_date='" + check_budget_date + '\'' +
                ", project_id='" + project_id + '\'' +
                ", code='" + code + '\'' +
                ", unit='" + unit + '\'' +
                ", price='" + price + '\'' +
                ", purchase_quantity='" + purchase_quantity + '\'' +
                ", person_name='" + person_name + '\'' +
                ", persontel='" + persontel + '\'' +
                ", person_mail='" + person_mail + '\'' +
                ", wrong_return_description='" + wrong_return_description + '\'' +
                ", account_id='" + account_id + '\'' +
                ", btn_name='" + btn_name + '\'' +
                ", keyword_name='" + keyword_name + '\'' +
                ", create_id='" + create_id + '\'' +
                '}';
    }
}
