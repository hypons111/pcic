package gov.pcc.pwc.service.genSerialNumber.impl;

import gov.pcc.pwc.repository.impl.SequencesRepositoryImpl;
import gov.pcc.pwc.service.genSerialNumber.AbstractNumberGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.RevGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.SequenceNameEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.genSerialNumber.util.GenSerialNumUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
@Service
public class REVNumberGenerator extends AbstractNumberGenerator{
    private final String proPrefix = "PRO-";

    private final GenSerialNumUtil genSerialNumUtil;
    private final SequencesRepositoryImpl sequencesRepositoryImpl;

    private RevGenNumberVariableEnum variable;
    private String sectionAndCategory;
    private Date codeDate;
    private SequenceNameEnum sequenceEnum;


    public REVNumberGenerator(GenSerialNumUtil genSerialNumUtil, SequencesRepositoryImpl sequencesRepositoryImpl) {
        this.genSerialNumUtil = genSerialNumUtil;
        this.sequencesRepositoryImpl = sequencesRepositoryImpl;
    }

    @Override
    public AbstractNumberGenerator setVariable(SerialNumVariableParam variable) throws TypeVariableNotMatchException {
        switch (variable.getTypeVariable()){
            case ("CODE"):
                this.variable =RevGenNumberVariableEnum.CODE;
                this.sequenceEnum = SequenceNameEnum.REV_SEQ;
                this.sectionAndCategory = variable.getOtherVariable();
                break;
            default:
                throw new TypeVariableNotMatchException();
        }

        this.codeDate = variable.getDateVariable();

        return this;
    }

    @Override
    public String getGeneratedNumber() {
        //民國年 +科別1 +工程類別2 + 月日 + 流水號4

        StringBuilder proSerialNumBuilder = new StringBuilder();
        //利用codeDate取得 民國年+月+日
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.codeDate);
        Integer year =  calendar.get(Calendar.YEAR);
        Integer month =  calendar.get(Calendar.MONTH)+1;
        Integer date =  calendar.get(Calendar.DAY_OF_MONTH);

        //民國年
        proSerialNumBuilder.append(year.toString());
        //+科別1 +工程類別2
        proSerialNumBuilder.append(this.sectionAndCategory);

        //把月填滿兩碼
        String monthWithPad = StringUtils.leftPad(month.toString(),2,'0');
        proSerialNumBuilder.append(monthWithPad);
        //把日期填滿兩碼
        String dateWithPad = StringUtils.leftPad(date.toString(),2,'0');
        proSerialNumBuilder.append(dateWithPad);

        //利用sequece name來拿到sequence number
        //固定4碼,如果之後有變動再改
        String serialNum = (sequencesRepositoryImpl.getNextSequenceVal(this.sequenceEnum.getSeqName())).toString();
        String serialNumWithPad = StringUtils.leftPad(serialNum,4,'0');

        proSerialNumBuilder.append(serialNumWithPad);
        String returnInsSerial = proSerialNumBuilder.toString();
        //存進資料庫
        genSerialNumUtil.saveAndUpdateGenNumTableWithYear(
                this.proPrefix,
                this.variable.getVariable(),
                this.codeDate,
                serialNumWithPad
        );

        return returnInsSerial;
    }
}
