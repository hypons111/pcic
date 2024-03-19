package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjWksorcPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjWksorc} entity.
 */
public class BidPrjWksorcDTO implements Serializable {

//    private Long id;

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 3)
    private String syr;

    @NotNull
    @Size(max = 2)
    private String month;

    private BigDecimal person01;

    private BigDecimal person02;

    private BigDecimal worker01;

    private BigDecimal worker02;

    private BigDecimal worker03;

    private BigDecimal worker04;

    private BigDecimal worker05;

    private BigDecimal worker06;

    private BigDecimal worker07;

    private BigDecimal worker08;

    private BigDecimal worker09;

    private BigDecimal worker10;

    private BigDecimal worker11;

    private BigDecimal worker12;

    private BigDecimal personA01;

    private BigDecimal personA02;

    private BigDecimal workerA01;

    private BigDecimal workerA02;

    private BigDecimal workerA03;

    private BigDecimal workerA04;

    private BigDecimal workerA05;

    private BigDecimal workerA06;

    private BigDecimal workerA07;

    private BigDecimal workerA08;

    private BigDecimal workerA09;

    private BigDecimal workerA10;

    private BigDecimal workerA11;

    private BigDecimal workerA12;

    private BigDecimal personB01;

    private BigDecimal personB02;

    private BigDecimal workerB01;

    private BigDecimal workerB02;

    private BigDecimal workerB03;

    private BigDecimal workerB04;

    private BigDecimal workerB05;

    private BigDecimal workerB06;

    private BigDecimal workerB07;

    private BigDecimal workerB08;

    private BigDecimal workerB09;

    private BigDecimal workerB10;

    private BigDecimal workerB11;

    private BigDecimal workerB12;

    private BigDecimal personC01;

    private BigDecimal personC02;

    private BigDecimal workerC01;

    private BigDecimal workerC02;

    private BigDecimal workerC03;

    private BigDecimal workerC04;

    private BigDecimal workerC05;

    private BigDecimal workerC06;

    private BigDecimal workerC07;

    private BigDecimal workerC08;

    private BigDecimal workerC09;

    private BigDecimal workerC10;

    private BigDecimal workerC11;

    private BigDecimal workerC12;

    private Integer person01Salary;

    private Integer person02Salary;

    private Integer worker01Salary;

    private Integer worker02Salary;

    private Integer worker03Salary;

    private Integer worker04Salary;

    private Integer worker05Salary;

    private Integer worker06Salary;

    private Integer worker07Salary;

    private Integer worker08Salary;

    private Integer worker09Salary;

    private Integer worker10Salary;

    private Integer worker11Salary;

    private Integer worker12Salary;

    private BigDecimal aperson01;

    private BigDecimal aperson02;

    private BigDecimal aworker01;

    private BigDecimal aworker02;

    private BigDecimal aworker03;

    private BigDecimal aworker04;

    private BigDecimal aworker05;

    private BigDecimal aworker06;

    private BigDecimal aworker07;

    private BigDecimal aworker08;

    private BigDecimal aworker09;

    private BigDecimal aworker10;

    private BigDecimal aworker11;

    private BigDecimal aworker12;

    private BigDecimal ajobtype01;

    private BigDecimal ajobtype02;

    private BigDecimal ajobtype03;

    private BigDecimal ajobtype04;

    private BigDecimal ajobtype05;

    private BigDecimal ajobtype06;

    private BigDecimal ajobtype07;

    private BigDecimal ajobtype08;

    private BigDecimal ajobtype09;

    private BigDecimal ajobtype10;

    private BigDecimal ajobtype11;

    private BigDecimal ajobtype12;

    private BigDecimal ajobtype13;

    private BigDecimal ajobtype14;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    private BigDecimal confOut;

    private BigDecimal confIn;

    @Size(max = 60)
    private String uIp;

    private BigDecimal worker13;

    private BigDecimal worker14;

    private BigDecimal worker15;

    private BigDecimal worker16;

    private BigDecimal workerA13;

    private BigDecimal workerA14;

    private BigDecimal workerA15;

    private BigDecimal workerA16;

    private BigDecimal workerB13;

    private BigDecimal workerB14;

    private BigDecimal workerB15;

    private BigDecimal workerB16;

    private BigDecimal workerC13;

    private BigDecimal workerC14;

    private BigDecimal workerC15;

    private BigDecimal workerC16;

    private BigDecimal aworker13;

    private BigDecimal aworker14;

    private BigDecimal aworker15;

    private BigDecimal aworker16;

    public BidPrjWksorcPrimaryKey getId() {
        return new BidPrjWksorcPrimaryKey(
                this.wkut,
                this.prjno,
                this.syr,
                this.month
        );
    }

    public void setId(BidPrjWksorcPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.syr = id.getSyr();
        this.month = id.getMonth();
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getPerson01() {
        return person01;
    }

    public void setPerson01(BigDecimal person01) {
        this.person01 = person01;
    }

    public BigDecimal getPerson02() {
        return person02;
    }

    public void setPerson02(BigDecimal person02) {
        this.person02 = person02;
    }

    public BigDecimal getWorker01() {
        return worker01;
    }

    public void setWorker01(BigDecimal worker01) {
        this.worker01 = worker01;
    }

    public BigDecimal getWorker02() {
        return worker02;
    }

    public void setWorker02(BigDecimal worker02) {
        this.worker02 = worker02;
    }

    public BigDecimal getWorker03() {
        return worker03;
    }

    public void setWorker03(BigDecimal worker03) {
        this.worker03 = worker03;
    }

    public BigDecimal getWorker04() {
        return worker04;
    }

    public void setWorker04(BigDecimal worker04) {
        this.worker04 = worker04;
    }

    public BigDecimal getWorker05() {
        return worker05;
    }

    public void setWorker05(BigDecimal worker05) {
        this.worker05 = worker05;
    }

    public BigDecimal getWorker06() {
        return worker06;
    }

    public void setWorker06(BigDecimal worker06) {
        this.worker06 = worker06;
    }

    public BigDecimal getWorker07() {
        return worker07;
    }

    public void setWorker07(BigDecimal worker07) {
        this.worker07 = worker07;
    }

    public BigDecimal getWorker08() {
        return worker08;
    }

    public void setWorker08(BigDecimal worker08) {
        this.worker08 = worker08;
    }

    public BigDecimal getWorker09() {
        return worker09;
    }

    public void setWorker09(BigDecimal worker09) {
        this.worker09 = worker09;
    }

    public BigDecimal getWorker10() {
        return worker10;
    }

    public void setWorker10(BigDecimal worker10) {
        this.worker10 = worker10;
    }

    public BigDecimal getWorker11() {
        return worker11;
    }

    public void setWorker11(BigDecimal worker11) {
        this.worker11 = worker11;
    }

    public BigDecimal getWorker12() {
        return worker12;
    }

    public void setWorker12(BigDecimal worker12) {
        this.worker12 = worker12;
    }

    public BigDecimal getPersonA01() {
        return personA01;
    }

    public void setPersonA01(BigDecimal personA01) {
        this.personA01 = personA01;
    }

    public BigDecimal getPersonA02() {
        return personA02;
    }

    public void setPersonA02(BigDecimal personA02) {
        this.personA02 = personA02;
    }

    public BigDecimal getWorkerA01() {
        return workerA01;
    }

    public void setWorkerA01(BigDecimal workerA01) {
        this.workerA01 = workerA01;
    }

    public BigDecimal getWorkerA02() {
        return workerA02;
    }

    public void setWorkerA02(BigDecimal workerA02) {
        this.workerA02 = workerA02;
    }

    public BigDecimal getWorkerA03() {
        return workerA03;
    }

    public void setWorkerA03(BigDecimal workerA03) {
        this.workerA03 = workerA03;
    }

    public BigDecimal getWorkerA04() {
        return workerA04;
    }

    public void setWorkerA04(BigDecimal workerA04) {
        this.workerA04 = workerA04;
    }

    public BigDecimal getWorkerA05() {
        return workerA05;
    }

    public void setWorkerA05(BigDecimal workerA05) {
        this.workerA05 = workerA05;
    }

    public BigDecimal getWorkerA06() {
        return workerA06;
    }

    public void setWorkerA06(BigDecimal workerA06) {
        this.workerA06 = workerA06;
    }

    public BigDecimal getWorkerA07() {
        return workerA07;
    }

    public void setWorkerA07(BigDecimal workerA07) {
        this.workerA07 = workerA07;
    }

    public BigDecimal getWorkerA08() {
        return workerA08;
    }

    public void setWorkerA08(BigDecimal workerA08) {
        this.workerA08 = workerA08;
    }

    public BigDecimal getWorkerA09() {
        return workerA09;
    }

    public void setWorkerA09(BigDecimal workerA09) {
        this.workerA09 = workerA09;
    }

    public BigDecimal getWorkerA10() {
        return workerA10;
    }

    public void setWorkerA10(BigDecimal workerA10) {
        this.workerA10 = workerA10;
    }

    public BigDecimal getWorkerA11() {
        return workerA11;
    }

    public void setWorkerA11(BigDecimal workerA11) {
        this.workerA11 = workerA11;
    }

    public BigDecimal getWorkerA12() {
        return workerA12;
    }

    public void setWorkerA12(BigDecimal workerA12) {
        this.workerA12 = workerA12;
    }

    public BigDecimal getPersonB01() {
        return personB01;
    }

    public void setPersonB01(BigDecimal personB01) {
        this.personB01 = personB01;
    }

    public BigDecimal getPersonB02() {
        return personB02;
    }

    public void setPersonB02(BigDecimal personB02) {
        this.personB02 = personB02;
    }

    public BigDecimal getWorkerB01() {
        return workerB01;
    }

    public void setWorkerB01(BigDecimal workerB01) {
        this.workerB01 = workerB01;
    }

    public BigDecimal getWorkerB02() {
        return workerB02;
    }

    public void setWorkerB02(BigDecimal workerB02) {
        this.workerB02 = workerB02;
    }

    public BigDecimal getWorkerB03() {
        return workerB03;
    }

    public void setWorkerB03(BigDecimal workerB03) {
        this.workerB03 = workerB03;
    }

    public BigDecimal getWorkerB04() {
        return workerB04;
    }

    public void setWorkerB04(BigDecimal workerB04) {
        this.workerB04 = workerB04;
    }

    public BigDecimal getWorkerB05() {
        return workerB05;
    }

    public void setWorkerB05(BigDecimal workerB05) {
        this.workerB05 = workerB05;
    }

    public BigDecimal getWorkerB06() {
        return workerB06;
    }

    public void setWorkerB06(BigDecimal workerB06) {
        this.workerB06 = workerB06;
    }

    public BigDecimal getWorkerB07() {
        return workerB07;
    }

    public void setWorkerB07(BigDecimal workerB07) {
        this.workerB07 = workerB07;
    }

    public BigDecimal getWorkerB08() {
        return workerB08;
    }

    public void setWorkerB08(BigDecimal workerB08) {
        this.workerB08 = workerB08;
    }

    public BigDecimal getWorkerB09() {
        return workerB09;
    }

    public void setWorkerB09(BigDecimal workerB09) {
        this.workerB09 = workerB09;
    }

    public BigDecimal getWorkerB10() {
        return workerB10;
    }

    public void setWorkerB10(BigDecimal workerB10) {
        this.workerB10 = workerB10;
    }

    public BigDecimal getWorkerB11() {
        return workerB11;
    }

    public void setWorkerB11(BigDecimal workerB11) {
        this.workerB11 = workerB11;
    }

    public BigDecimal getWorkerB12() {
        return workerB12;
    }

    public void setWorkerB12(BigDecimal workerB12) {
        this.workerB12 = workerB12;
    }

    public BigDecimal getPersonC01() {
        return personC01;
    }

    public void setPersonC01(BigDecimal personC01) {
        this.personC01 = personC01;
    }

    public BigDecimal getPersonC02() {
        return personC02;
    }

    public void setPersonC02(BigDecimal personC02) {
        this.personC02 = personC02;
    }

    public BigDecimal getWorkerC01() {
        return workerC01;
    }

    public void setWorkerC01(BigDecimal workerC01) {
        this.workerC01 = workerC01;
    }

    public BigDecimal getWorkerC02() {
        return workerC02;
    }

    public void setWorkerC02(BigDecimal workerC02) {
        this.workerC02 = workerC02;
    }

    public BigDecimal getWorkerC03() {
        return workerC03;
    }

    public void setWorkerC03(BigDecimal workerC03) {
        this.workerC03 = workerC03;
    }

    public BigDecimal getWorkerC04() {
        return workerC04;
    }

    public void setWorkerC04(BigDecimal workerC04) {
        this.workerC04 = workerC04;
    }

    public BigDecimal getWorkerC05() {
        return workerC05;
    }

    public void setWorkerC05(BigDecimal workerC05) {
        this.workerC05 = workerC05;
    }

    public BigDecimal getWorkerC06() {
        return workerC06;
    }

    public void setWorkerC06(BigDecimal workerC06) {
        this.workerC06 = workerC06;
    }

    public BigDecimal getWorkerC07() {
        return workerC07;
    }

    public void setWorkerC07(BigDecimal workerC07) {
        this.workerC07 = workerC07;
    }

    public BigDecimal getWorkerC08() {
        return workerC08;
    }

    public void setWorkerC08(BigDecimal workerC08) {
        this.workerC08 = workerC08;
    }

    public BigDecimal getWorkerC09() {
        return workerC09;
    }

    public void setWorkerC09(BigDecimal workerC09) {
        this.workerC09 = workerC09;
    }

    public BigDecimal getWorkerC10() {
        return workerC10;
    }

    public void setWorkerC10(BigDecimal workerC10) {
        this.workerC10 = workerC10;
    }

    public BigDecimal getWorkerC11() {
        return workerC11;
    }

    public void setWorkerC11(BigDecimal workerC11) {
        this.workerC11 = workerC11;
    }

    public BigDecimal getWorkerC12() {
        return workerC12;
    }

    public void setWorkerC12(BigDecimal workerC12) {
        this.workerC12 = workerC12;
    }

    public Integer getPerson01Salary() {
        return person01Salary;
    }

    public void setPerson01Salary(Integer person01Salary) {
        this.person01Salary = person01Salary;
    }

    public Integer getPerson02Salary() {
        return person02Salary;
    }

    public void setPerson02Salary(Integer person02Salary) {
        this.person02Salary = person02Salary;
    }

    public Integer getWorker01Salary() {
        return worker01Salary;
    }

    public void setWorker01Salary(Integer worker01Salary) {
        this.worker01Salary = worker01Salary;
    }

    public Integer getWorker02Salary() {
        return worker02Salary;
    }

    public void setWorker02Salary(Integer worker02Salary) {
        this.worker02Salary = worker02Salary;
    }

    public Integer getWorker03Salary() {
        return worker03Salary;
    }

    public void setWorker03Salary(Integer worker03Salary) {
        this.worker03Salary = worker03Salary;
    }

    public Integer getWorker04Salary() {
        return worker04Salary;
    }

    public void setWorker04Salary(Integer worker04Salary) {
        this.worker04Salary = worker04Salary;
    }

    public Integer getWorker05Salary() {
        return worker05Salary;
    }

    public void setWorker05Salary(Integer worker05Salary) {
        this.worker05Salary = worker05Salary;
    }

    public Integer getWorker06Salary() {
        return worker06Salary;
    }

    public void setWorker06Salary(Integer worker06Salary) {
        this.worker06Salary = worker06Salary;
    }

    public Integer getWorker07Salary() {
        return worker07Salary;
    }

    public void setWorker07Salary(Integer worker07Salary) {
        this.worker07Salary = worker07Salary;
    }

    public Integer getWorker08Salary() {
        return worker08Salary;
    }

    public void setWorker08Salary(Integer worker08Salary) {
        this.worker08Salary = worker08Salary;
    }

    public Integer getWorker09Salary() {
        return worker09Salary;
    }

    public void setWorker09Salary(Integer worker09Salary) {
        this.worker09Salary = worker09Salary;
    }

    public Integer getWorker10Salary() {
        return worker10Salary;
    }

    public void setWorker10Salary(Integer worker10Salary) {
        this.worker10Salary = worker10Salary;
    }

    public Integer getWorker11Salary() {
        return worker11Salary;
    }

    public void setWorker11Salary(Integer worker11Salary) {
        this.worker11Salary = worker11Salary;
    }

    public Integer getWorker12Salary() {
        return worker12Salary;
    }

    public void setWorker12Salary(Integer worker12Salary) {
        this.worker12Salary = worker12Salary;
    }

    public BigDecimal getAperson01() {
        return aperson01;
    }

    public void setAperson01(BigDecimal aperson01) {
        this.aperson01 = aperson01;
    }

    public BigDecimal getAperson02() {
        return aperson02;
    }

    public void setAperson02(BigDecimal aperson02) {
        this.aperson02 = aperson02;
    }

    public BigDecimal getAworker01() {
        return aworker01;
    }

    public void setAworker01(BigDecimal aworker01) {
        this.aworker01 = aworker01;
    }

    public BigDecimal getAworker02() {
        return aworker02;
    }

    public void setAworker02(BigDecimal aworker02) {
        this.aworker02 = aworker02;
    }

    public BigDecimal getAworker03() {
        return aworker03;
    }

    public void setAworker03(BigDecimal aworker03) {
        this.aworker03 = aworker03;
    }

    public BigDecimal getAworker04() {
        return aworker04;
    }

    public void setAworker04(BigDecimal aworker04) {
        this.aworker04 = aworker04;
    }

    public BigDecimal getAworker05() {
        return aworker05;
    }

    public void setAworker05(BigDecimal aworker05) {
        this.aworker05 = aworker05;
    }

    public BigDecimal getAworker06() {
        return aworker06;
    }

    public void setAworker06(BigDecimal aworker06) {
        this.aworker06 = aworker06;
    }

    public BigDecimal getAworker07() {
        return aworker07;
    }

    public void setAworker07(BigDecimal aworker07) {
        this.aworker07 = aworker07;
    }

    public BigDecimal getAworker08() {
        return aworker08;
    }

    public void setAworker08(BigDecimal aworker08) {
        this.aworker08 = aworker08;
    }

    public BigDecimal getAworker09() {
        return aworker09;
    }

    public void setAworker09(BigDecimal aworker09) {
        this.aworker09 = aworker09;
    }

    public BigDecimal getAworker10() {
        return aworker10;
    }

    public void setAworker10(BigDecimal aworker10) {
        this.aworker10 = aworker10;
    }

    public BigDecimal getAworker11() {
        return aworker11;
    }

    public void setAworker11(BigDecimal aworker11) {
        this.aworker11 = aworker11;
    }

    public BigDecimal getAworker12() {
        return aworker12;
    }

    public void setAworker12(BigDecimal aworker12) {
        this.aworker12 = aworker12;
    }

    public BigDecimal getAjobtype01() {
        return ajobtype01;
    }

    public void setAjobtype01(BigDecimal ajobtype01) {
        this.ajobtype01 = ajobtype01;
    }

    public BigDecimal getAjobtype02() {
        return ajobtype02;
    }

    public void setAjobtype02(BigDecimal ajobtype02) {
        this.ajobtype02 = ajobtype02;
    }

    public BigDecimal getAjobtype03() {
        return ajobtype03;
    }

    public void setAjobtype03(BigDecimal ajobtype03) {
        this.ajobtype03 = ajobtype03;
    }

    public BigDecimal getAjobtype04() {
        return ajobtype04;
    }

    public void setAjobtype04(BigDecimal ajobtype04) {
        this.ajobtype04 = ajobtype04;
    }

    public BigDecimal getAjobtype05() {
        return ajobtype05;
    }

    public void setAjobtype05(BigDecimal ajobtype05) {
        this.ajobtype05 = ajobtype05;
    }

    public BigDecimal getAjobtype06() {
        return ajobtype06;
    }

    public void setAjobtype06(BigDecimal ajobtype06) {
        this.ajobtype06 = ajobtype06;
    }

    public BigDecimal getAjobtype07() {
        return ajobtype07;
    }

    public void setAjobtype07(BigDecimal ajobtype07) {
        this.ajobtype07 = ajobtype07;
    }

    public BigDecimal getAjobtype08() {
        return ajobtype08;
    }

    public void setAjobtype08(BigDecimal ajobtype08) {
        this.ajobtype08 = ajobtype08;
    }

    public BigDecimal getAjobtype09() {
        return ajobtype09;
    }

    public void setAjobtype09(BigDecimal ajobtype09) {
        this.ajobtype09 = ajobtype09;
    }

    public BigDecimal getAjobtype10() {
        return ajobtype10;
    }

    public void setAjobtype10(BigDecimal ajobtype10) {
        this.ajobtype10 = ajobtype10;
    }

    public BigDecimal getAjobtype11() {
        return ajobtype11;
    }

    public void setAjobtype11(BigDecimal ajobtype11) {
        this.ajobtype11 = ajobtype11;
    }

    public BigDecimal getAjobtype12() {
        return ajobtype12;
    }

    public void setAjobtype12(BigDecimal ajobtype12) {
        this.ajobtype12 = ajobtype12;
    }

    public BigDecimal getAjobtype13() {
        return ajobtype13;
    }

    public void setAjobtype13(BigDecimal ajobtype13) {
        this.ajobtype13 = ajobtype13;
    }

    public BigDecimal getAjobtype14() {
        return ajobtype14;
    }

    public void setAjobtype14(BigDecimal ajobtype14) {
        this.ajobtype14 = ajobtype14;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public BigDecimal getConfOut() {
        return confOut;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public BigDecimal getConfIn() {
        return confIn;
    }

    public void setConfIn(BigDecimal confIn) {
        this.confIn = confIn;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public BigDecimal getWorker13() {
        return worker13;
    }

    public void setWorker13(BigDecimal worker13) {
        this.worker13 = worker13;
    }

    public BigDecimal getWorker14() {
        return worker14;
    }

    public void setWorker14(BigDecimal worker14) {
        this.worker14 = worker14;
    }

    public BigDecimal getWorker15() {
        return worker15;
    }

    public void setWorker15(BigDecimal worker15) {
        this.worker15 = worker15;
    }

    public BigDecimal getWorker16() {
        return worker16;
    }

    public void setWorker16(BigDecimal worker16) {
        this.worker16 = worker16;
    }

    public BigDecimal getWorkerA13() {
        return workerA13;
    }

    public void setWorkerA13(BigDecimal workerA13) {
        this.workerA13 = workerA13;
    }

    public BigDecimal getWorkerA14() {
        return workerA14;
    }

    public void setWorkerA14(BigDecimal workerA14) {
        this.workerA14 = workerA14;
    }

    public BigDecimal getWorkerA15() {
        return workerA15;
    }

    public void setWorkerA15(BigDecimal workerA15) {
        this.workerA15 = workerA15;
    }

    public BigDecimal getWorkerA16() {
        return workerA16;
    }

    public void setWorkerA16(BigDecimal workerA16) {
        this.workerA16 = workerA16;
    }

    public BigDecimal getWorkerB13() {
        return workerB13;
    }

    public void setWorkerB13(BigDecimal workerB13) {
        this.workerB13 = workerB13;
    }

    public BigDecimal getWorkerB14() {
        return workerB14;
    }

    public void setWorkerB14(BigDecimal workerB14) {
        this.workerB14 = workerB14;
    }

    public BigDecimal getWorkerB15() {
        return workerB15;
    }

    public void setWorkerB15(BigDecimal workerB15) {
        this.workerB15 = workerB15;
    }

    public BigDecimal getWorkerB16() {
        return workerB16;
    }

    public void setWorkerB16(BigDecimal workerB16) {
        this.workerB16 = workerB16;
    }

    public BigDecimal getWorkerC13() {
        return workerC13;
    }

    public void setWorkerC13(BigDecimal workerC13) {
        this.workerC13 = workerC13;
    }

    public BigDecimal getWorkerC14() {
        return workerC14;
    }

    public void setWorkerC14(BigDecimal workerC14) {
        this.workerC14 = workerC14;
    }

    public BigDecimal getWorkerC15() {
        return workerC15;
    }

    public void setWorkerC15(BigDecimal workerC15) {
        this.workerC15 = workerC15;
    }

    public BigDecimal getWorkerC16() {
        return workerC16;
    }

    public void setWorkerC16(BigDecimal workerC16) {
        this.workerC16 = workerC16;
    }

    public BigDecimal getAworker13() {
        return aworker13;
    }

    public void setAworker13(BigDecimal aworker13) {
        this.aworker13 = aworker13;
    }

    public BigDecimal getAworker14() {
        return aworker14;
    }

    public void setAworker14(BigDecimal aworker14) {
        this.aworker14 = aworker14;
    }

    public BigDecimal getAworker15() {
        return aworker15;
    }

    public void setAworker15(BigDecimal aworker15) {
        this.aworker15 = aworker15;
    }

    public BigDecimal getAworker16() {
        return aworker16;
    }

    public void setAworker16(BigDecimal aworker16) {
        this.aworker16 = aworker16;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjWksorcDTO)) {
            return false;
        }

        BidPrjWksorcDTO that = (BidPrjWksorcDTO) o;
        return (
                Objects.equals(wkut, that.wkut) &&
                        Objects.equals(prjno, that.prjno)
                        && Objects.equals(syr, that.syr)
                        && Objects.equals(month, that.month)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, syr, month);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjWksorcDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", syr='" + getSyr() + "'" +
            ", month='" + getMonth() + "'" +
            ", person01=" + getPerson01() +
            ", person02=" + getPerson02() +
            ", worker01=" + getWorker01() +
            ", worker02=" + getWorker02() +
            ", worker03=" + getWorker03() +
            ", worker04=" + getWorker04() +
            ", worker05=" + getWorker05() +
            ", worker06=" + getWorker06() +
            ", worker07=" + getWorker07() +
            ", worker08=" + getWorker08() +
            ", worker09=" + getWorker09() +
            ", worker10=" + getWorker10() +
            ", worker11=" + getWorker11() +
            ", worker12=" + getWorker12() +
            ", personA01=" + getPersonA01() +
            ", personA02=" + getPersonA02() +
            ", workerA01=" + getWorkerA01() +
            ", workerA02=" + getWorkerA02() +
            ", workerA03=" + getWorkerA03() +
            ", workerA04=" + getWorkerA04() +
            ", workerA05=" + getWorkerA05() +
            ", workerA06=" + getWorkerA06() +
            ", workerA07=" + getWorkerA07() +
            ", workerA08=" + getWorkerA08() +
            ", workerA09=" + getWorkerA09() +
            ", workerA10=" + getWorkerA10() +
            ", workerA11=" + getWorkerA11() +
            ", workerA12=" + getWorkerA12() +
            ", personB01=" + getPersonB01() +
            ", personB02=" + getPersonB02() +
            ", workerB01=" + getWorkerB01() +
            ", workerB02=" + getWorkerB02() +
            ", workerB03=" + getWorkerB03() +
            ", workerB04=" + getWorkerB04() +
            ", workerB05=" + getWorkerB05() +
            ", workerB06=" + getWorkerB06() +
            ", workerB07=" + getWorkerB07() +
            ", workerB08=" + getWorkerB08() +
            ", workerB09=" + getWorkerB09() +
            ", workerB10=" + getWorkerB10() +
            ", workerB11=" + getWorkerB11() +
            ", workerB12=" + getWorkerB12() +
            ", personC01=" + getPersonC01() +
            ", personC02=" + getPersonC02() +
            ", workerC01=" + getWorkerC01() +
            ", workerC02=" + getWorkerC02() +
            ", workerC03=" + getWorkerC03() +
            ", workerC04=" + getWorkerC04() +
            ", workerC05=" + getWorkerC05() +
            ", workerC06=" + getWorkerC06() +
            ", workerC07=" + getWorkerC07() +
            ", workerC08=" + getWorkerC08() +
            ", workerC09=" + getWorkerC09() +
            ", workerC10=" + getWorkerC10() +
            ", workerC11=" + getWorkerC11() +
            ", workerC12=" + getWorkerC12() +
            ", person01Salary=" + getPerson01Salary() +
            ", person02Salary=" + getPerson02Salary() +
            ", worker01Salary=" + getWorker01Salary() +
            ", worker02Salary=" + getWorker02Salary() +
            ", worker03Salary=" + getWorker03Salary() +
            ", worker04Salary=" + getWorker04Salary() +
            ", worker05Salary=" + getWorker05Salary() +
            ", worker06Salary=" + getWorker06Salary() +
            ", worker07Salary=" + getWorker07Salary() +
            ", worker08Salary=" + getWorker08Salary() +
            ", worker09Salary=" + getWorker09Salary() +
            ", worker10Salary=" + getWorker10Salary() +
            ", worker11Salary=" + getWorker11Salary() +
            ", worker12Salary=" + getWorker12Salary() +
            ", aperson01=" + getAperson01() +
            ", aperson02=" + getAperson02() +
            ", aworker01=" + getAworker01() +
            ", aworker02=" + getAworker02() +
            ", aworker03=" + getAworker03() +
            ", aworker04=" + getAworker04() +
            ", aworker05=" + getAworker05() +
            ", aworker06=" + getAworker06() +
            ", aworker07=" + getAworker07() +
            ", aworker08=" + getAworker08() +
            ", aworker09=" + getAworker09() +
            ", aworker10=" + getAworker10() +
            ", aworker11=" + getAworker11() +
            ", aworker12=" + getAworker12() +
            ", ajobtype01=" + getAjobtype01() +
            ", ajobtype02=" + getAjobtype02() +
            ", ajobtype03=" + getAjobtype03() +
            ", ajobtype04=" + getAjobtype04() +
            ", ajobtype05=" + getAjobtype05() +
            ", ajobtype06=" + getAjobtype06() +
            ", ajobtype07=" + getAjobtype07() +
            ", ajobtype08=" + getAjobtype08() +
            ", ajobtype09=" + getAjobtype09() +
            ", ajobtype10=" + getAjobtype10() +
            ", ajobtype11=" + getAjobtype11() +
            ", ajobtype12=" + getAjobtype12() +
            ", ajobtype13=" + getAjobtype13() +
            ", ajobtype14=" + getAjobtype14() +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", confOut=" + getConfOut() +
            ", confIn=" + getConfIn() +
            ", uIp='" + getuIp() + "'" +
            ", worker13=" + getWorker13() +
            ", worker14=" + getWorker14() +
            ", worker15=" + getWorker15() +
            ", worker16=" + getWorker16() +
            ", workerA13=" + getWorkerA13() +
            ", workerA14=" + getWorkerA14() +
            ", workerA15=" + getWorkerA15() +
            ", workerA16=" + getWorkerA16() +
            ", workerB13=" + getWorkerB13() +
            ", workerB14=" + getWorkerB14() +
            ", workerB15=" + getWorkerB15() +
            ", workerB16=" + getWorkerB16() +
            ", workerC13=" + getWorkerC13() +
            ", workerC14=" + getWorkerC14() +
            ", workerC15=" + getWorkerC15() +
            ", workerC16=" + getWorkerC16() +
            ", aworker13=" + getAworker13() +
            ", aworker14=" + getAworker14() +
            ", aworker15=" + getAworker15() +
            ", aworker16=" + getAworker16() +
            "}";
    }
}
