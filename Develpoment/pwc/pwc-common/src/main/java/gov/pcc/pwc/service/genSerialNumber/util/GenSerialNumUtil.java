package gov.pcc.pwc.service.genSerialNumber.util;

import gov.pcc.pwc.domain.AdmNumber;
import gov.pcc.pwc.repository.AdmNumberRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Service
public class GenSerialNumUtil {
    //透過admNumberRepository把 generator產生的號碼存入ADM_NUMBER資料表
    //給generator產生完 serial num之後用的


    private final AdmNumberRepository admNumberRepository;

    public GenSerialNumUtil(AdmNumberRepository admNumberRepository) {
        this.admNumberRepository = admNumberRepository;
    }

    /**
     *
     * @param prefix   哪一個module(ex:REC-)
     * @param variable 對應到該module的哪個方法的變數(ex:New)
     * @param codeDate 這個serail num對應到的時間點(通常都直接用this.codeDate)
     * @param serialNumberBySequence 各個generator透過sequence產生的serial num
     * @return
     */
    public AdmNumber saveAndUpdateGenNumTableWithYear(String prefix, String variable, Date codeDate, String serialNumberBySequence){
        String codeType = prefix + variable;
        //處理年
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(codeDate);
        Integer year =  calendar.get(Calendar.YEAR);
        Integer mingouYear =  year-1911;

        // String codeYear = this.codeDate

        Optional<AdmNumber> genNumOp = admNumberRepository.findOneByCodeTypeAndCodeYear(codeType, mingouYear.toString());
        if(genNumOp.isPresent()){
            AdmNumber genNum = genNumOp.get();
            genNum.setNumber(serialNumberBySequence);
            genNum.setUpdateTime(Instant.now());
            genNum.setUpdateUser("SYS");
            admNumberRepository.save(genNum);
            return genNum;
        }else{
            AdmNumber newGenNum = new AdmNumber();
            newGenNum.setCodeType(codeType);
            newGenNum.setNumber(serialNumberBySequence);
            newGenNum.setCodeYear(mingouYear.toString());
            newGenNum.setCreateTime(Instant.now());
            newGenNum.setCreateUser("SYS");
            admNumberRepository.save(newGenNum);
            return newGenNum;
        }
    }
}
