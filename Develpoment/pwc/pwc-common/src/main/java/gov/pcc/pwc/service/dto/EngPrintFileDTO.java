package gov.pcc.pwc.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EngPrintFileDTO {
    private String id;
    private String version;
    private String docNo;
    private String enDocNo;
    private String applyType;
    private List<String> applyItems;
    private String chApplyItemsList;
    private String idno;
    private String chName;
    private String enName;
    private String chBirthDate;
    private String enBirthDate;
    private String chSex;
    private String enSex;
    private String enSexPrefix;
    private String chSubject;
    private String enSubject;
    private String examNo;
    private String enExamNo;
    private String chReceiveDate;
    private String enReceiveDate;
    private String receiveNo;
    private String receiveAddr;
    private String letterTitle;
    private String chPublicDate_pre;
    private String contractorName;
    private String contractorTel;
    private String contractorEmail;
}
