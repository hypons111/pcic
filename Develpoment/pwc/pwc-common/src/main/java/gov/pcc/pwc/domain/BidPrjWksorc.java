package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjWksorc.
 */
@Entity
@IdClass(BidPrjWksorcPrimaryKey.class)
@Table(name = "bid_prj_wksorc")
public class BidPrjWksorc implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

    @Id
    @NotNull
    @Size(max = 21)
    @Column(name = "wkut", length = 21, nullable = false)
    private String wkut;

    @Id
    @NotNull
    @Size(max = 40)
    @Column(name = "prjno", length = 40, nullable = false)
    private String prjno;

    @Id
    @NotNull
    @Size(max = 3)
    @Column(name = "syr", length = 3, nullable = false)
    private String syr;

    @Id
    @NotNull
    @Size(max = 2)
    @Column(name = "month", length = 2, nullable = false)
    private String month;

    @Column(name = "person01", precision = 21, scale = 2)
    private BigDecimal person01;

    @Column(name = "person02", precision = 21, scale = 2)
    private BigDecimal person02;

    @Column(name = "worker01", precision = 21, scale = 2)
    private BigDecimal worker01;

    @Column(name = "worker02", precision = 21, scale = 2)
    private BigDecimal worker02;

    @Column(name = "worker03", precision = 21, scale = 2)
    private BigDecimal worker03;

    @Column(name = "worker04", precision = 21, scale = 2)
    private BigDecimal worker04;

    @Column(name = "worker05", precision = 21, scale = 2)
    private BigDecimal worker05;

    @Column(name = "worker06", precision = 21, scale = 2)
    private BigDecimal worker06;

    @Column(name = "worker07", precision = 21, scale = 2)
    private BigDecimal worker07;

    @Column(name = "worker08", precision = 21, scale = 2)
    private BigDecimal worker08;

    @Column(name = "worker09", precision = 21, scale = 2)
    private BigDecimal worker09;

    @Column(name = "worker10", precision = 21, scale = 2)
    private BigDecimal worker10;

    @Column(name = "worker11", precision = 21, scale = 2)
    private BigDecimal worker11;

    @Column(name = "worker12", precision = 21, scale = 2)
    private BigDecimal worker12;

    @Column(name = "person_A01", precision = 21, scale = 2)
    private BigDecimal personA01;

    @Column(name = "person_A02", precision = 21, scale = 2)
    private BigDecimal personA02;

    @Column(name = "worker_A01", precision = 21, scale = 2)
    private BigDecimal workerA01;

    @Column(name = "worker_A02", precision = 21, scale = 2)
    private BigDecimal workerA02;

    @Column(name = "worker_A03", precision = 21, scale = 2)
    private BigDecimal workerA03;

    @Column(name = "worker_A04", precision = 21, scale = 2)
    private BigDecimal workerA04;

    @Column(name = "worker_A05", precision = 21, scale = 2)
    private BigDecimal workerA05;

    @Column(name = "worker_A06", precision = 21, scale = 2)
    private BigDecimal workerA06;

    @Column(name = "worker_A07", precision = 21, scale = 2)
    private BigDecimal workerA07;

    @Column(name = "worker_A08", precision = 21, scale = 2)
    private BigDecimal workerA08;

    @Column(name = "worker_A09", precision = 21, scale = 2)
    private BigDecimal workerA09;

    @Column(name = "worker_A10", precision = 21, scale = 2)
    private BigDecimal workerA10;

    @Column(name = "worker_A11", precision = 21, scale = 2)
    private BigDecimal workerA11;

    @Column(name = "worker_A12", precision = 21, scale = 2)
    private BigDecimal workerA12;

    @Column(name = "person_B01", precision = 21, scale = 2)
    private BigDecimal personB01;

    @Column(name = "person_B02", precision = 21, scale = 2)
    private BigDecimal personB02;

    @Column(name = "worker_B01", precision = 21, scale = 2)
    private BigDecimal workerB01;

    @Column(name = "worker_B02", precision = 21, scale = 2)
    private BigDecimal workerB02;

    @Column(name = "worker_B03", precision = 21, scale = 2)
    private BigDecimal workerB03;

    @Column(name = "worker_B04", precision = 21, scale = 2)
    private BigDecimal workerB04;

    @Column(name = "worker_B05", precision = 21, scale = 2)
    private BigDecimal workerB05;

    @Column(name = "worker_B06", precision = 21, scale = 2)
    private BigDecimal workerB06;

    @Column(name = "worker_B07", precision = 21, scale = 2)
    private BigDecimal workerB07;

    @Column(name = "worker_B08", precision = 21, scale = 2)
    private BigDecimal workerB08;

    @Column(name = "worker_B09", precision = 21, scale = 2)
    private BigDecimal workerB09;

    @Column(name = "worker_B10", precision = 21, scale = 2)
    private BigDecimal workerB10;

    @Column(name = "worker_B11", precision = 21, scale = 2)
    private BigDecimal workerB11;

    @Column(name = "worker_B12", precision = 21, scale = 2)
    private BigDecimal workerB12;

    @Column(name = "person_C01", precision = 21, scale = 2)
    private BigDecimal personC01;

    @Column(name = "person_C02", precision = 21, scale = 2)
    private BigDecimal personC02;

    @Column(name = "worker_C01", precision = 21, scale = 2)
    private BigDecimal workerC01;

    @Column(name = "worker_C02", precision = 21, scale = 2)
    private BigDecimal workerC02;

    @Column(name = "worker_C03", precision = 21, scale = 2)
    private BigDecimal workerC03;

    @Column(name = "worker_C04", precision = 21, scale = 2)
    private BigDecimal workerC04;

    @Column(name = "worker_C05", precision = 21, scale = 2)
    private BigDecimal workerC05;

    @Column(name = "worker_C06", precision = 21, scale = 2)
    private BigDecimal workerC06;

    @Column(name = "worker_C07", precision = 21, scale = 2)
    private BigDecimal workerC07;

    @Column(name = "worker_C08", precision = 21, scale = 2)
    private BigDecimal workerC08;

    @Column(name = "worker_C09", precision = 21, scale = 2)
    private BigDecimal workerC09;

    @Column(name = "worker_C10", precision = 21, scale = 2)
    private BigDecimal workerC10;

    @Column(name = "worker_C11", precision = 21, scale = 2)
    private BigDecimal workerC11;

    @Column(name = "worker_C12", precision = 21, scale = 2)
    private BigDecimal workerC12;

    @Column(name = "person01_salary")
    private Integer person01Salary;

    @Column(name = "person02_salary")
    private Integer person02Salary;

    @Column(name = "worker01_salary")
    private Integer worker01Salary;

    @Column(name = "worker02_salary")
    private Integer worker02Salary;

    @Column(name = "worker03_salary")
    private Integer worker03Salary;

    @Column(name = "worker04_salary")
    private Integer worker04Salary;

    @Column(name = "worker05_salary")
    private Integer worker05Salary;

    @Column(name = "worker06_salary")
    private Integer worker06Salary;

    @Column(name = "worker07_salary")
    private Integer worker07Salary;

    @Column(name = "worker08_salary")
    private Integer worker08Salary;

    @Column(name = "worker09_salary")
    private Integer worker09Salary;

    @Column(name = "worker10_salary")
    private Integer worker10Salary;

    @Column(name = "worker11_salary")
    private Integer worker11Salary;

    @Column(name = "worker12_salary")
    private Integer worker12Salary;

    @Column(name = "aperson01", precision = 21, scale = 2)
    private BigDecimal aperson01;

    @Column(name = "aperson02", precision = 21, scale = 2)
    private BigDecimal aperson02;

    @Column(name = "aworker01", precision = 21, scale = 2)
    private BigDecimal aworker01;

    @Column(name = "aworker02", precision = 21, scale = 2)
    private BigDecimal aworker02;

    @Column(name = "aworker03", precision = 21, scale = 2)
    private BigDecimal aworker03;

    @Column(name = "aworker04", precision = 21, scale = 2)
    private BigDecimal aworker04;

    @Column(name = "aworker05", precision = 21, scale = 2)
    private BigDecimal aworker05;

    @Column(name = "aworker06", precision = 21, scale = 2)
    private BigDecimal aworker06;

    @Column(name = "aworker07", precision = 21, scale = 2)
    private BigDecimal aworker07;

    @Column(name = "aworker08", precision = 21, scale = 2)
    private BigDecimal aworker08;

    @Column(name = "aworker09", precision = 21, scale = 2)
    private BigDecimal aworker09;

    @Column(name = "aworker10", precision = 21, scale = 2)
    private BigDecimal aworker10;

    @Column(name = "aworker11", precision = 21, scale = 2)
    private BigDecimal aworker11;

    @Column(name = "aworker12", precision = 21, scale = 2)
    private BigDecimal aworker12;

    @Column(name = "ajobtype01", precision = 21, scale = 2)
    private BigDecimal ajobtype01;

    @Column(name = "ajobtype02", precision = 21, scale = 2)
    private BigDecimal ajobtype02;

    @Column(name = "ajobtype03", precision = 21, scale = 2)
    private BigDecimal ajobtype03;

    @Column(name = "ajobtype04", precision = 21, scale = 2)
    private BigDecimal ajobtype04;

    @Column(name = "ajobtype05", precision = 21, scale = 2)
    private BigDecimal ajobtype05;

    @Column(name = "ajobtype06", precision = 21, scale = 2)
    private BigDecimal ajobtype06;

    @Column(name = "ajobtype07", precision = 21, scale = 2)
    private BigDecimal ajobtype07;

    @Column(name = "ajobtype08", precision = 21, scale = 2)
    private BigDecimal ajobtype08;

    @Column(name = "ajobtype09", precision = 21, scale = 2)
    private BigDecimal ajobtype09;

    @Column(name = "ajobtype10", precision = 21, scale = 2)
    private BigDecimal ajobtype10;

    @Column(name = "ajobtype11", precision = 21, scale = 2)
    private BigDecimal ajobtype11;

    @Column(name = "ajobtype12", precision = 21, scale = 2)
    private BigDecimal ajobtype12;

    @Column(name = "ajobtype13", precision = 21, scale = 2)
    private BigDecimal ajobtype13;

    @Column(name = "ajobtype14", precision = 21, scale = 2)
    private BigDecimal ajobtype14;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

    @Column(name = "conf_in", precision = 21, scale = 2)
    private BigDecimal confIn;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Column(name = "worker13", precision = 21, scale = 2)
    private BigDecimal worker13;

    @Column(name = "worker14", precision = 21, scale = 2)
    private BigDecimal worker14;

    @Column(name = "worker15", precision = 21, scale = 2)
    private BigDecimal worker15;

    @Column(name = "worker16", precision = 21, scale = 2)
    private BigDecimal worker16;

    @Column(name = "worker_A13", precision = 21, scale = 2)
    private BigDecimal workerA13;

    @Column(name = "worker_A14", precision = 21, scale = 2)
    private BigDecimal workerA14;

    @Column(name = "worker_A15", precision = 21, scale = 2)
    private BigDecimal workerA15;

    @Column(name = "worker_A16", precision = 21, scale = 2)
    private BigDecimal workerA16;

    @Column(name = "worker_B13", precision = 21, scale = 2)
    private BigDecimal workerB13;

    @Column(name = "worker_B14", precision = 21, scale = 2)
    private BigDecimal workerB14;

    @Column(name = "worker_B15", precision = 21, scale = 2)
    private BigDecimal workerB15;

    @Column(name = "worker_B16", precision = 21, scale = 2)
    private BigDecimal workerB16;

    @Column(name = "worker_C13", precision = 21, scale = 2)
    private BigDecimal workerC13;

    @Column(name = "worker_C14", precision = 21, scale = 2)
    private BigDecimal workerC14;

    @Column(name = "worker_C15", precision = 21, scale = 2)
    private BigDecimal workerC15;

    @Column(name = "worker_C16", precision = 21, scale = 2)
    private BigDecimal workerC16;

    @Column(name = "aworker13", precision = 21, scale = 2)
    private BigDecimal aworker13;

    @Column(name = "aworker14", precision = 21, scale = 2)
    private BigDecimal aworker14;

    @Column(name = "aworker15", precision = 21, scale = 2)
    private BigDecimal aworker15;

    @Column(name = "aworker16", precision = 21, scale = 2)
    private BigDecimal aworker16;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjWksorcPrimaryKey getId() {
        return new BidPrjWksorcPrimaryKey(wkut, prjno, syr, month);
    }

    public BidPrjWksorc id(BidPrjWksorcPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjWksorcPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.syr = id.getSyr();
        this.month = id.getMonth();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjWksorc wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjWksorc prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getSyr() {
        return this.syr;
    }

    public BidPrjWksorc syr(String syr) {
        this.setSyr(syr);
        return this;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getMonth() {
        return this.month;
    }

    public BidPrjWksorc month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getPerson01() {
        return this.person01;
    }

    public BidPrjWksorc person01(BigDecimal person01) {
        this.setPerson01(person01);
        return this;
    }

    public void setPerson01(BigDecimal person01) {
        this.person01 = person01;
    }

    public BigDecimal getPerson02() {
        return this.person02;
    }

    public BidPrjWksorc person02(BigDecimal person02) {
        this.setPerson02(person02);
        return this;
    }

    public void setPerson02(BigDecimal person02) {
        this.person02 = person02;
    }

    public BigDecimal getWorker01() {
        return this.worker01;
    }

    public BidPrjWksorc worker01(BigDecimal worker01) {
        this.setWorker01(worker01);
        return this;
    }

    public void setWorker01(BigDecimal worker01) {
        this.worker01 = worker01;
    }

    public BigDecimal getWorker02() {
        return this.worker02;
    }

    public BidPrjWksorc worker02(BigDecimal worker02) {
        this.setWorker02(worker02);
        return this;
    }

    public void setWorker02(BigDecimal worker02) {
        this.worker02 = worker02;
    }

    public BigDecimal getWorker03() {
        return this.worker03;
    }

    public BidPrjWksorc worker03(BigDecimal worker03) {
        this.setWorker03(worker03);
        return this;
    }

    public void setWorker03(BigDecimal worker03) {
        this.worker03 = worker03;
    }

    public BigDecimal getWorker04() {
        return this.worker04;
    }

    public BidPrjWksorc worker04(BigDecimal worker04) {
        this.setWorker04(worker04);
        return this;
    }

    public void setWorker04(BigDecimal worker04) {
        this.worker04 = worker04;
    }

    public BigDecimal getWorker05() {
        return this.worker05;
    }

    public BidPrjWksorc worker05(BigDecimal worker05) {
        this.setWorker05(worker05);
        return this;
    }

    public void setWorker05(BigDecimal worker05) {
        this.worker05 = worker05;
    }

    public BigDecimal getWorker06() {
        return this.worker06;
    }

    public BidPrjWksorc worker06(BigDecimal worker06) {
        this.setWorker06(worker06);
        return this;
    }

    public void setWorker06(BigDecimal worker06) {
        this.worker06 = worker06;
    }

    public BigDecimal getWorker07() {
        return this.worker07;
    }

    public BidPrjWksorc worker07(BigDecimal worker07) {
        this.setWorker07(worker07);
        return this;
    }

    public void setWorker07(BigDecimal worker07) {
        this.worker07 = worker07;
    }

    public BigDecimal getWorker08() {
        return this.worker08;
    }

    public BidPrjWksorc worker08(BigDecimal worker08) {
        this.setWorker08(worker08);
        return this;
    }

    public void setWorker08(BigDecimal worker08) {
        this.worker08 = worker08;
    }

    public BigDecimal getWorker09() {
        return this.worker09;
    }

    public BidPrjWksorc worker09(BigDecimal worker09) {
        this.setWorker09(worker09);
        return this;
    }

    public void setWorker09(BigDecimal worker09) {
        this.worker09 = worker09;
    }

    public BigDecimal getWorker10() {
        return this.worker10;
    }

    public BidPrjWksorc worker10(BigDecimal worker10) {
        this.setWorker10(worker10);
        return this;
    }

    public void setWorker10(BigDecimal worker10) {
        this.worker10 = worker10;
    }

    public BigDecimal getWorker11() {
        return this.worker11;
    }

    public BidPrjWksorc worker11(BigDecimal worker11) {
        this.setWorker11(worker11);
        return this;
    }

    public void setWorker11(BigDecimal worker11) {
        this.worker11 = worker11;
    }

    public BigDecimal getWorker12() {
        return this.worker12;
    }

    public BidPrjWksorc worker12(BigDecimal worker12) {
        this.setWorker12(worker12);
        return this;
    }

    public void setWorker12(BigDecimal worker12) {
        this.worker12 = worker12;
    }

    public BigDecimal getPersonA01() {
        return this.personA01;
    }

    public BidPrjWksorc personA01(BigDecimal personA01) {
        this.setPersonA01(personA01);
        return this;
    }

    public void setPersonA01(BigDecimal personA01) {
        this.personA01 = personA01;
    }

    public BigDecimal getPersonA02() {
        return this.personA02;
    }

    public BidPrjWksorc personA02(BigDecimal personA02) {
        this.setPersonA02(personA02);
        return this;
    }

    public void setPersonA02(BigDecimal personA02) {
        this.personA02 = personA02;
    }

    public BigDecimal getWorkerA01() {
        return this.workerA01;
    }

    public BidPrjWksorc workerA01(BigDecimal workerA01) {
        this.setWorkerA01(workerA01);
        return this;
    }

    public void setWorkerA01(BigDecimal workerA01) {
        this.workerA01 = workerA01;
    }

    public BigDecimal getWorkerA02() {
        return this.workerA02;
    }

    public BidPrjWksorc workerA02(BigDecimal workerA02) {
        this.setWorkerA02(workerA02);
        return this;
    }

    public void setWorkerA02(BigDecimal workerA02) {
        this.workerA02 = workerA02;
    }

    public BigDecimal getWorkerA03() {
        return this.workerA03;
    }

    public BidPrjWksorc workerA03(BigDecimal workerA03) {
        this.setWorkerA03(workerA03);
        return this;
    }

    public void setWorkerA03(BigDecimal workerA03) {
        this.workerA03 = workerA03;
    }

    public BigDecimal getWorkerA04() {
        return this.workerA04;
    }

    public BidPrjWksorc workerA04(BigDecimal workerA04) {
        this.setWorkerA04(workerA04);
        return this;
    }

    public void setWorkerA04(BigDecimal workerA04) {
        this.workerA04 = workerA04;
    }

    public BigDecimal getWorkerA05() {
        return this.workerA05;
    }

    public BidPrjWksorc workerA05(BigDecimal workerA05) {
        this.setWorkerA05(workerA05);
        return this;
    }

    public void setWorkerA05(BigDecimal workerA05) {
        this.workerA05 = workerA05;
    }

    public BigDecimal getWorkerA06() {
        return this.workerA06;
    }

    public BidPrjWksorc workerA06(BigDecimal workerA06) {
        this.setWorkerA06(workerA06);
        return this;
    }

    public void setWorkerA06(BigDecimal workerA06) {
        this.workerA06 = workerA06;
    }

    public BigDecimal getWorkerA07() {
        return this.workerA07;
    }

    public BidPrjWksorc workerA07(BigDecimal workerA07) {
        this.setWorkerA07(workerA07);
        return this;
    }

    public void setWorkerA07(BigDecimal workerA07) {
        this.workerA07 = workerA07;
    }

    public BigDecimal getWorkerA08() {
        return this.workerA08;
    }

    public BidPrjWksorc workerA08(BigDecimal workerA08) {
        this.setWorkerA08(workerA08);
        return this;
    }

    public void setWorkerA08(BigDecimal workerA08) {
        this.workerA08 = workerA08;
    }

    public BigDecimal getWorkerA09() {
        return this.workerA09;
    }

    public BidPrjWksorc workerA09(BigDecimal workerA09) {
        this.setWorkerA09(workerA09);
        return this;
    }

    public void setWorkerA09(BigDecimal workerA09) {
        this.workerA09 = workerA09;
    }

    public BigDecimal getWorkerA10() {
        return this.workerA10;
    }

    public BidPrjWksorc workerA10(BigDecimal workerA10) {
        this.setWorkerA10(workerA10);
        return this;
    }

    public void setWorkerA10(BigDecimal workerA10) {
        this.workerA10 = workerA10;
    }

    public BigDecimal getWorkerA11() {
        return this.workerA11;
    }

    public BidPrjWksorc workerA11(BigDecimal workerA11) {
        this.setWorkerA11(workerA11);
        return this;
    }

    public void setWorkerA11(BigDecimal workerA11) {
        this.workerA11 = workerA11;
    }

    public BigDecimal getWorkerA12() {
        return this.workerA12;
    }

    public BidPrjWksorc workerA12(BigDecimal workerA12) {
        this.setWorkerA12(workerA12);
        return this;
    }

    public void setWorkerA12(BigDecimal workerA12) {
        this.workerA12 = workerA12;
    }

    public BigDecimal getPersonB01() {
        return this.personB01;
    }

    public BidPrjWksorc personB01(BigDecimal personB01) {
        this.setPersonB01(personB01);
        return this;
    }

    public void setPersonB01(BigDecimal personB01) {
        this.personB01 = personB01;
    }

    public BigDecimal getPersonB02() {
        return this.personB02;
    }

    public BidPrjWksorc personB02(BigDecimal personB02) {
        this.setPersonB02(personB02);
        return this;
    }

    public void setPersonB02(BigDecimal personB02) {
        this.personB02 = personB02;
    }

    public BigDecimal getWorkerB01() {
        return this.workerB01;
    }

    public BidPrjWksorc workerB01(BigDecimal workerB01) {
        this.setWorkerB01(workerB01);
        return this;
    }

    public void setWorkerB01(BigDecimal workerB01) {
        this.workerB01 = workerB01;
    }

    public BigDecimal getWorkerB02() {
        return this.workerB02;
    }

    public BidPrjWksorc workerB02(BigDecimal workerB02) {
        this.setWorkerB02(workerB02);
        return this;
    }

    public void setWorkerB02(BigDecimal workerB02) {
        this.workerB02 = workerB02;
    }

    public BigDecimal getWorkerB03() {
        return this.workerB03;
    }

    public BidPrjWksorc workerB03(BigDecimal workerB03) {
        this.setWorkerB03(workerB03);
        return this;
    }

    public void setWorkerB03(BigDecimal workerB03) {
        this.workerB03 = workerB03;
    }

    public BigDecimal getWorkerB04() {
        return this.workerB04;
    }

    public BidPrjWksorc workerB04(BigDecimal workerB04) {
        this.setWorkerB04(workerB04);
        return this;
    }

    public void setWorkerB04(BigDecimal workerB04) {
        this.workerB04 = workerB04;
    }

    public BigDecimal getWorkerB05() {
        return this.workerB05;
    }

    public BidPrjWksorc workerB05(BigDecimal workerB05) {
        this.setWorkerB05(workerB05);
        return this;
    }

    public void setWorkerB05(BigDecimal workerB05) {
        this.workerB05 = workerB05;
    }

    public BigDecimal getWorkerB06() {
        return this.workerB06;
    }

    public BidPrjWksorc workerB06(BigDecimal workerB06) {
        this.setWorkerB06(workerB06);
        return this;
    }

    public void setWorkerB06(BigDecimal workerB06) {
        this.workerB06 = workerB06;
    }

    public BigDecimal getWorkerB07() {
        return this.workerB07;
    }

    public BidPrjWksorc workerB07(BigDecimal workerB07) {
        this.setWorkerB07(workerB07);
        return this;
    }

    public void setWorkerB07(BigDecimal workerB07) {
        this.workerB07 = workerB07;
    }

    public BigDecimal getWorkerB08() {
        return this.workerB08;
    }

    public BidPrjWksorc workerB08(BigDecimal workerB08) {
        this.setWorkerB08(workerB08);
        return this;
    }

    public void setWorkerB08(BigDecimal workerB08) {
        this.workerB08 = workerB08;
    }

    public BigDecimal getWorkerB09() {
        return this.workerB09;
    }

    public BidPrjWksorc workerB09(BigDecimal workerB09) {
        this.setWorkerB09(workerB09);
        return this;
    }

    public void setWorkerB09(BigDecimal workerB09) {
        this.workerB09 = workerB09;
    }

    public BigDecimal getWorkerB10() {
        return this.workerB10;
    }

    public BidPrjWksorc workerB10(BigDecimal workerB10) {
        this.setWorkerB10(workerB10);
        return this;
    }

    public void setWorkerB10(BigDecimal workerB10) {
        this.workerB10 = workerB10;
    }

    public BigDecimal getWorkerB11() {
        return this.workerB11;
    }

    public BidPrjWksorc workerB11(BigDecimal workerB11) {
        this.setWorkerB11(workerB11);
        return this;
    }

    public void setWorkerB11(BigDecimal workerB11) {
        this.workerB11 = workerB11;
    }

    public BigDecimal getWorkerB12() {
        return this.workerB12;
    }

    public BidPrjWksorc workerB12(BigDecimal workerB12) {
        this.setWorkerB12(workerB12);
        return this;
    }

    public void setWorkerB12(BigDecimal workerB12) {
        this.workerB12 = workerB12;
    }

    public BigDecimal getPersonC01() {
        return this.personC01;
    }

    public BidPrjWksorc personC01(BigDecimal personC01) {
        this.setPersonC01(personC01);
        return this;
    }

    public void setPersonC01(BigDecimal personC01) {
        this.personC01 = personC01;
    }

    public BigDecimal getPersonC02() {
        return this.personC02;
    }

    public BidPrjWksorc personC02(BigDecimal personC02) {
        this.setPersonC02(personC02);
        return this;
    }

    public void setPersonC02(BigDecimal personC02) {
        this.personC02 = personC02;
    }

    public BigDecimal getWorkerC01() {
        return this.workerC01;
    }

    public BidPrjWksorc workerC01(BigDecimal workerC01) {
        this.setWorkerC01(workerC01);
        return this;
    }

    public void setWorkerC01(BigDecimal workerC01) {
        this.workerC01 = workerC01;
    }

    public BigDecimal getWorkerC02() {
        return this.workerC02;
    }

    public BidPrjWksorc workerC02(BigDecimal workerC02) {
        this.setWorkerC02(workerC02);
        return this;
    }

    public void setWorkerC02(BigDecimal workerC02) {
        this.workerC02 = workerC02;
    }

    public BigDecimal getWorkerC03() {
        return this.workerC03;
    }

    public BidPrjWksorc workerC03(BigDecimal workerC03) {
        this.setWorkerC03(workerC03);
        return this;
    }

    public void setWorkerC03(BigDecimal workerC03) {
        this.workerC03 = workerC03;
    }

    public BigDecimal getWorkerC04() {
        return this.workerC04;
    }

    public BidPrjWksorc workerC04(BigDecimal workerC04) {
        this.setWorkerC04(workerC04);
        return this;
    }

    public void setWorkerC04(BigDecimal workerC04) {
        this.workerC04 = workerC04;
    }

    public BigDecimal getWorkerC05() {
        return this.workerC05;
    }

    public BidPrjWksorc workerC05(BigDecimal workerC05) {
        this.setWorkerC05(workerC05);
        return this;
    }

    public void setWorkerC05(BigDecimal workerC05) {
        this.workerC05 = workerC05;
    }

    public BigDecimal getWorkerC06() {
        return this.workerC06;
    }

    public BidPrjWksorc workerC06(BigDecimal workerC06) {
        this.setWorkerC06(workerC06);
        return this;
    }

    public void setWorkerC06(BigDecimal workerC06) {
        this.workerC06 = workerC06;
    }

    public BigDecimal getWorkerC07() {
        return this.workerC07;
    }

    public BidPrjWksorc workerC07(BigDecimal workerC07) {
        this.setWorkerC07(workerC07);
        return this;
    }

    public void setWorkerC07(BigDecimal workerC07) {
        this.workerC07 = workerC07;
    }

    public BigDecimal getWorkerC08() {
        return this.workerC08;
    }

    public BidPrjWksorc workerC08(BigDecimal workerC08) {
        this.setWorkerC08(workerC08);
        return this;
    }

    public void setWorkerC08(BigDecimal workerC08) {
        this.workerC08 = workerC08;
    }

    public BigDecimal getWorkerC09() {
        return this.workerC09;
    }

    public BidPrjWksorc workerC09(BigDecimal workerC09) {
        this.setWorkerC09(workerC09);
        return this;
    }

    public void setWorkerC09(BigDecimal workerC09) {
        this.workerC09 = workerC09;
    }

    public BigDecimal getWorkerC10() {
        return this.workerC10;
    }

    public BidPrjWksorc workerC10(BigDecimal workerC10) {
        this.setWorkerC10(workerC10);
        return this;
    }

    public void setWorkerC10(BigDecimal workerC10) {
        this.workerC10 = workerC10;
    }

    public BigDecimal getWorkerC11() {
        return this.workerC11;
    }

    public BidPrjWksorc workerC11(BigDecimal workerC11) {
        this.setWorkerC11(workerC11);
        return this;
    }

    public void setWorkerC11(BigDecimal workerC11) {
        this.workerC11 = workerC11;
    }

    public BigDecimal getWorkerC12() {
        return this.workerC12;
    }

    public BidPrjWksorc workerC12(BigDecimal workerC12) {
        this.setWorkerC12(workerC12);
        return this;
    }

    public void setWorkerC12(BigDecimal workerC12) {
        this.workerC12 = workerC12;
    }

    public Integer getPerson01Salary() {
        return this.person01Salary;
    }

    public BidPrjWksorc person01Salary(Integer person01Salary) {
        this.setPerson01Salary(person01Salary);
        return this;
    }

    public void setPerson01Salary(Integer person01Salary) {
        this.person01Salary = person01Salary;
    }

    public Integer getPerson02Salary() {
        return this.person02Salary;
    }

    public BidPrjWksorc person02Salary(Integer person02Salary) {
        this.setPerson02Salary(person02Salary);
        return this;
    }

    public void setPerson02Salary(Integer person02Salary) {
        this.person02Salary = person02Salary;
    }

    public Integer getWorker01Salary() {
        return this.worker01Salary;
    }

    public BidPrjWksorc worker01Salary(Integer worker01Salary) {
        this.setWorker01Salary(worker01Salary);
        return this;
    }

    public void setWorker01Salary(Integer worker01Salary) {
        this.worker01Salary = worker01Salary;
    }

    public Integer getWorker02Salary() {
        return this.worker02Salary;
    }

    public BidPrjWksorc worker02Salary(Integer worker02Salary) {
        this.setWorker02Salary(worker02Salary);
        return this;
    }

    public void setWorker02Salary(Integer worker02Salary) {
        this.worker02Salary = worker02Salary;
    }

    public Integer getWorker03Salary() {
        return this.worker03Salary;
    }

    public BidPrjWksorc worker03Salary(Integer worker03Salary) {
        this.setWorker03Salary(worker03Salary);
        return this;
    }

    public void setWorker03Salary(Integer worker03Salary) {
        this.worker03Salary = worker03Salary;
    }

    public Integer getWorker04Salary() {
        return this.worker04Salary;
    }

    public BidPrjWksorc worker04Salary(Integer worker04Salary) {
        this.setWorker04Salary(worker04Salary);
        return this;
    }

    public void setWorker04Salary(Integer worker04Salary) {
        this.worker04Salary = worker04Salary;
    }

    public Integer getWorker05Salary() {
        return this.worker05Salary;
    }

    public BidPrjWksorc worker05Salary(Integer worker05Salary) {
        this.setWorker05Salary(worker05Salary);
        return this;
    }

    public void setWorker05Salary(Integer worker05Salary) {
        this.worker05Salary = worker05Salary;
    }

    public Integer getWorker06Salary() {
        return this.worker06Salary;
    }

    public BidPrjWksorc worker06Salary(Integer worker06Salary) {
        this.setWorker06Salary(worker06Salary);
        return this;
    }

    public void setWorker06Salary(Integer worker06Salary) {
        this.worker06Salary = worker06Salary;
    }

    public Integer getWorker07Salary() {
        return this.worker07Salary;
    }

    public BidPrjWksorc worker07Salary(Integer worker07Salary) {
        this.setWorker07Salary(worker07Salary);
        return this;
    }

    public void setWorker07Salary(Integer worker07Salary) {
        this.worker07Salary = worker07Salary;
    }

    public Integer getWorker08Salary() {
        return this.worker08Salary;
    }

    public BidPrjWksorc worker08Salary(Integer worker08Salary) {
        this.setWorker08Salary(worker08Salary);
        return this;
    }

    public void setWorker08Salary(Integer worker08Salary) {
        this.worker08Salary = worker08Salary;
    }

    public Integer getWorker09Salary() {
        return this.worker09Salary;
    }

    public BidPrjWksorc worker09Salary(Integer worker09Salary) {
        this.setWorker09Salary(worker09Salary);
        return this;
    }

    public void setWorker09Salary(Integer worker09Salary) {
        this.worker09Salary = worker09Salary;
    }

    public Integer getWorker10Salary() {
        return this.worker10Salary;
    }

    public BidPrjWksorc worker10Salary(Integer worker10Salary) {
        this.setWorker10Salary(worker10Salary);
        return this;
    }

    public void setWorker10Salary(Integer worker10Salary) {
        this.worker10Salary = worker10Salary;
    }

    public Integer getWorker11Salary() {
        return this.worker11Salary;
    }

    public BidPrjWksorc worker11Salary(Integer worker11Salary) {
        this.setWorker11Salary(worker11Salary);
        return this;
    }

    public void setWorker11Salary(Integer worker11Salary) {
        this.worker11Salary = worker11Salary;
    }

    public Integer getWorker12Salary() {
        return this.worker12Salary;
    }

    public BidPrjWksorc worker12Salary(Integer worker12Salary) {
        this.setWorker12Salary(worker12Salary);
        return this;
    }

    public void setWorker12Salary(Integer worker12Salary) {
        this.worker12Salary = worker12Salary;
    }

    public BigDecimal getAperson01() {
        return this.aperson01;
    }

    public BidPrjWksorc aperson01(BigDecimal aperson01) {
        this.setAperson01(aperson01);
        return this;
    }

    public void setAperson01(BigDecimal aperson01) {
        this.aperson01 = aperson01;
    }

    public BigDecimal getAperson02() {
        return this.aperson02;
    }

    public BidPrjWksorc aperson02(BigDecimal aperson02) {
        this.setAperson02(aperson02);
        return this;
    }

    public void setAperson02(BigDecimal aperson02) {
        this.aperson02 = aperson02;
    }

    public BigDecimal getAworker01() {
        return this.aworker01;
    }

    public BidPrjWksorc aworker01(BigDecimal aworker01) {
        this.setAworker01(aworker01);
        return this;
    }

    public void setAworker01(BigDecimal aworker01) {
        this.aworker01 = aworker01;
    }

    public BigDecimal getAworker02() {
        return this.aworker02;
    }

    public BidPrjWksorc aworker02(BigDecimal aworker02) {
        this.setAworker02(aworker02);
        return this;
    }

    public void setAworker02(BigDecimal aworker02) {
        this.aworker02 = aworker02;
    }

    public BigDecimal getAworker03() {
        return this.aworker03;
    }

    public BidPrjWksorc aworker03(BigDecimal aworker03) {
        this.setAworker03(aworker03);
        return this;
    }

    public void setAworker03(BigDecimal aworker03) {
        this.aworker03 = aworker03;
    }

    public BigDecimal getAworker04() {
        return this.aworker04;
    }

    public BidPrjWksorc aworker04(BigDecimal aworker04) {
        this.setAworker04(aworker04);
        return this;
    }

    public void setAworker04(BigDecimal aworker04) {
        this.aworker04 = aworker04;
    }

    public BigDecimal getAworker05() {
        return this.aworker05;
    }

    public BidPrjWksorc aworker05(BigDecimal aworker05) {
        this.setAworker05(aworker05);
        return this;
    }

    public void setAworker05(BigDecimal aworker05) {
        this.aworker05 = aworker05;
    }

    public BigDecimal getAworker06() {
        return this.aworker06;
    }

    public BidPrjWksorc aworker06(BigDecimal aworker06) {
        this.setAworker06(aworker06);
        return this;
    }

    public void setAworker06(BigDecimal aworker06) {
        this.aworker06 = aworker06;
    }

    public BigDecimal getAworker07() {
        return this.aworker07;
    }

    public BidPrjWksorc aworker07(BigDecimal aworker07) {
        this.setAworker07(aworker07);
        return this;
    }

    public void setAworker07(BigDecimal aworker07) {
        this.aworker07 = aworker07;
    }

    public BigDecimal getAworker08() {
        return this.aworker08;
    }

    public BidPrjWksorc aworker08(BigDecimal aworker08) {
        this.setAworker08(aworker08);
        return this;
    }

    public void setAworker08(BigDecimal aworker08) {
        this.aworker08 = aworker08;
    }

    public BigDecimal getAworker09() {
        return this.aworker09;
    }

    public BidPrjWksorc aworker09(BigDecimal aworker09) {
        this.setAworker09(aworker09);
        return this;
    }

    public void setAworker09(BigDecimal aworker09) {
        this.aworker09 = aworker09;
    }

    public BigDecimal getAworker10() {
        return this.aworker10;
    }

    public BidPrjWksorc aworker10(BigDecimal aworker10) {
        this.setAworker10(aworker10);
        return this;
    }

    public void setAworker10(BigDecimal aworker10) {
        this.aworker10 = aworker10;
    }

    public BigDecimal getAworker11() {
        return this.aworker11;
    }

    public BidPrjWksorc aworker11(BigDecimal aworker11) {
        this.setAworker11(aworker11);
        return this;
    }

    public void setAworker11(BigDecimal aworker11) {
        this.aworker11 = aworker11;
    }

    public BigDecimal getAworker12() {
        return this.aworker12;
    }

    public BidPrjWksorc aworker12(BigDecimal aworker12) {
        this.setAworker12(aworker12);
        return this;
    }

    public void setAworker12(BigDecimal aworker12) {
        this.aworker12 = aworker12;
    }

    public BigDecimal getAjobtype01() {
        return this.ajobtype01;
    }

    public BidPrjWksorc ajobtype01(BigDecimal ajobtype01) {
        this.setAjobtype01(ajobtype01);
        return this;
    }

    public void setAjobtype01(BigDecimal ajobtype01) {
        this.ajobtype01 = ajobtype01;
    }

    public BigDecimal getAjobtype02() {
        return this.ajobtype02;
    }

    public BidPrjWksorc ajobtype02(BigDecimal ajobtype02) {
        this.setAjobtype02(ajobtype02);
        return this;
    }

    public void setAjobtype02(BigDecimal ajobtype02) {
        this.ajobtype02 = ajobtype02;
    }

    public BigDecimal getAjobtype03() {
        return this.ajobtype03;
    }

    public BidPrjWksorc ajobtype03(BigDecimal ajobtype03) {
        this.setAjobtype03(ajobtype03);
        return this;
    }

    public void setAjobtype03(BigDecimal ajobtype03) {
        this.ajobtype03 = ajobtype03;
    }

    public BigDecimal getAjobtype04() {
        return this.ajobtype04;
    }

    public BidPrjWksorc ajobtype04(BigDecimal ajobtype04) {
        this.setAjobtype04(ajobtype04);
        return this;
    }

    public void setAjobtype04(BigDecimal ajobtype04) {
        this.ajobtype04 = ajobtype04;
    }

    public BigDecimal getAjobtype05() {
        return this.ajobtype05;
    }

    public BidPrjWksorc ajobtype05(BigDecimal ajobtype05) {
        this.setAjobtype05(ajobtype05);
        return this;
    }

    public void setAjobtype05(BigDecimal ajobtype05) {
        this.ajobtype05 = ajobtype05;
    }

    public BigDecimal getAjobtype06() {
        return this.ajobtype06;
    }

    public BidPrjWksorc ajobtype06(BigDecimal ajobtype06) {
        this.setAjobtype06(ajobtype06);
        return this;
    }

    public void setAjobtype06(BigDecimal ajobtype06) {
        this.ajobtype06 = ajobtype06;
    }

    public BigDecimal getAjobtype07() {
        return this.ajobtype07;
    }

    public BidPrjWksorc ajobtype07(BigDecimal ajobtype07) {
        this.setAjobtype07(ajobtype07);
        return this;
    }

    public void setAjobtype07(BigDecimal ajobtype07) {
        this.ajobtype07 = ajobtype07;
    }

    public BigDecimal getAjobtype08() {
        return this.ajobtype08;
    }

    public BidPrjWksorc ajobtype08(BigDecimal ajobtype08) {
        this.setAjobtype08(ajobtype08);
        return this;
    }

    public void setAjobtype08(BigDecimal ajobtype08) {
        this.ajobtype08 = ajobtype08;
    }

    public BigDecimal getAjobtype09() {
        return this.ajobtype09;
    }

    public BidPrjWksorc ajobtype09(BigDecimal ajobtype09) {
        this.setAjobtype09(ajobtype09);
        return this;
    }

    public void setAjobtype09(BigDecimal ajobtype09) {
        this.ajobtype09 = ajobtype09;
    }

    public BigDecimal getAjobtype10() {
        return this.ajobtype10;
    }

    public BidPrjWksorc ajobtype10(BigDecimal ajobtype10) {
        this.setAjobtype10(ajobtype10);
        return this;
    }

    public void setAjobtype10(BigDecimal ajobtype10) {
        this.ajobtype10 = ajobtype10;
    }

    public BigDecimal getAjobtype11() {
        return this.ajobtype11;
    }

    public BidPrjWksorc ajobtype11(BigDecimal ajobtype11) {
        this.setAjobtype11(ajobtype11);
        return this;
    }

    public void setAjobtype11(BigDecimal ajobtype11) {
        this.ajobtype11 = ajobtype11;
    }

    public BigDecimal getAjobtype12() {
        return this.ajobtype12;
    }

    public BidPrjWksorc ajobtype12(BigDecimal ajobtype12) {
        this.setAjobtype12(ajobtype12);
        return this;
    }

    public void setAjobtype12(BigDecimal ajobtype12) {
        this.ajobtype12 = ajobtype12;
    }

    public BigDecimal getAjobtype13() {
        return this.ajobtype13;
    }

    public BidPrjWksorc ajobtype13(BigDecimal ajobtype13) {
        this.setAjobtype13(ajobtype13);
        return this;
    }

    public void setAjobtype13(BigDecimal ajobtype13) {
        this.ajobtype13 = ajobtype13;
    }

    public BigDecimal getAjobtype14() {
        return this.ajobtype14;
    }

    public BidPrjWksorc ajobtype14(BigDecimal ajobtype14) {
        this.setAjobtype14(ajobtype14);
        return this;
    }

    public void setAjobtype14(BigDecimal ajobtype14) {
        this.ajobtype14 = ajobtype14;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjWksorc createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjWksorc createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjWksorc updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjWksorc updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjWksorc confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public BigDecimal getConfIn() {
        return this.confIn;
    }

    public BidPrjWksorc confIn(BigDecimal confIn) {
        this.setConfIn(confIn);
        return this;
    }

    public void setConfIn(BigDecimal confIn) {
        this.confIn = confIn;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjWksorc uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public BigDecimal getWorker13() {
        return this.worker13;
    }

    public BidPrjWksorc worker13(BigDecimal worker13) {
        this.setWorker13(worker13);
        return this;
    }

    public void setWorker13(BigDecimal worker13) {
        this.worker13 = worker13;
    }

    public BigDecimal getWorker14() {
        return this.worker14;
    }

    public BidPrjWksorc worker14(BigDecimal worker14) {
        this.setWorker14(worker14);
        return this;
    }

    public void setWorker14(BigDecimal worker14) {
        this.worker14 = worker14;
    }

    public BigDecimal getWorker15() {
        return this.worker15;
    }

    public BidPrjWksorc worker15(BigDecimal worker15) {
        this.setWorker15(worker15);
        return this;
    }

    public void setWorker15(BigDecimal worker15) {
        this.worker15 = worker15;
    }

    public BigDecimal getWorker16() {
        return this.worker16;
    }

    public BidPrjWksorc worker16(BigDecimal worker16) {
        this.setWorker16(worker16);
        return this;
    }

    public void setWorker16(BigDecimal worker16) {
        this.worker16 = worker16;
    }

    public BigDecimal getWorkerA13() {
        return this.workerA13;
    }

    public BidPrjWksorc workerA13(BigDecimal workerA13) {
        this.setWorkerA13(workerA13);
        return this;
    }

    public void setWorkerA13(BigDecimal workerA13) {
        this.workerA13 = workerA13;
    }

    public BigDecimal getWorkerA14() {
        return this.workerA14;
    }

    public BidPrjWksorc workerA14(BigDecimal workerA14) {
        this.setWorkerA14(workerA14);
        return this;
    }

    public void setWorkerA14(BigDecimal workerA14) {
        this.workerA14 = workerA14;
    }

    public BigDecimal getWorkerA15() {
        return this.workerA15;
    }

    public BidPrjWksorc workerA15(BigDecimal workerA15) {
        this.setWorkerA15(workerA15);
        return this;
    }

    public void setWorkerA15(BigDecimal workerA15) {
        this.workerA15 = workerA15;
    }

    public BigDecimal getWorkerA16() {
        return this.workerA16;
    }

    public BidPrjWksorc workerA16(BigDecimal workerA16) {
        this.setWorkerA16(workerA16);
        return this;
    }

    public void setWorkerA16(BigDecimal workerA16) {
        this.workerA16 = workerA16;
    }

    public BigDecimal getWorkerB13() {
        return this.workerB13;
    }

    public BidPrjWksorc workerB13(BigDecimal workerB13) {
        this.setWorkerB13(workerB13);
        return this;
    }

    public void setWorkerB13(BigDecimal workerB13) {
        this.workerB13 = workerB13;
    }

    public BigDecimal getWorkerB14() {
        return this.workerB14;
    }

    public BidPrjWksorc workerB14(BigDecimal workerB14) {
        this.setWorkerB14(workerB14);
        return this;
    }

    public void setWorkerB14(BigDecimal workerB14) {
        this.workerB14 = workerB14;
    }

    public BigDecimal getWorkerB15() {
        return this.workerB15;
    }

    public BidPrjWksorc workerB15(BigDecimal workerB15) {
        this.setWorkerB15(workerB15);
        return this;
    }

    public void setWorkerB15(BigDecimal workerB15) {
        this.workerB15 = workerB15;
    }

    public BigDecimal getWorkerB16() {
        return this.workerB16;
    }

    public BidPrjWksorc workerB16(BigDecimal workerB16) {
        this.setWorkerB16(workerB16);
        return this;
    }

    public void setWorkerB16(BigDecimal workerB16) {
        this.workerB16 = workerB16;
    }

    public BigDecimal getWorkerC13() {
        return this.workerC13;
    }

    public BidPrjWksorc workerC13(BigDecimal workerC13) {
        this.setWorkerC13(workerC13);
        return this;
    }

    public void setWorkerC13(BigDecimal workerC13) {
        this.workerC13 = workerC13;
    }

    public BigDecimal getWorkerC14() {
        return this.workerC14;
    }

    public BidPrjWksorc workerC14(BigDecimal workerC14) {
        this.setWorkerC14(workerC14);
        return this;
    }

    public void setWorkerC14(BigDecimal workerC14) {
        this.workerC14 = workerC14;
    }

    public BigDecimal getWorkerC15() {
        return this.workerC15;
    }

    public BidPrjWksorc workerC15(BigDecimal workerC15) {
        this.setWorkerC15(workerC15);
        return this;
    }

    public void setWorkerC15(BigDecimal workerC15) {
        this.workerC15 = workerC15;
    }

    public BigDecimal getWorkerC16() {
        return this.workerC16;
    }

    public BidPrjWksorc workerC16(BigDecimal workerC16) {
        this.setWorkerC16(workerC16);
        return this;
    }

    public void setWorkerC16(BigDecimal workerC16) {
        this.workerC16 = workerC16;
    }

    public BigDecimal getAworker13() {
        return this.aworker13;
    }

    public BidPrjWksorc aworker13(BigDecimal aworker13) {
        this.setAworker13(aworker13);
        return this;
    }

    public void setAworker13(BigDecimal aworker13) {
        this.aworker13 = aworker13;
    }

    public BigDecimal getAworker14() {
        return this.aworker14;
    }

    public BidPrjWksorc aworker14(BigDecimal aworker14) {
        this.setAworker14(aworker14);
        return this;
    }

    public void setAworker14(BigDecimal aworker14) {
        this.aworker14 = aworker14;
    }

    public BigDecimal getAworker15() {
        return this.aworker15;
    }

    public BidPrjWksorc aworker15(BigDecimal aworker15) {
        this.setAworker15(aworker15);
        return this;
    }

    public void setAworker15(BigDecimal aworker15) {
        this.aworker15 = aworker15;
    }

    public BigDecimal getAworker16() {
        return this.aworker16;
    }

    public BidPrjWksorc aworker16(BigDecimal aworker16) {
        this.setAworker16(aworker16);
        return this;
    }

    public void setAworker16(BigDecimal aworker16) {
        this.aworker16 = aworker16;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BidPrjWksorc)) {return false;}
        BidPrjWksorc that =  (BidPrjWksorc)  o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && syr.equals(that.syr) && month.equals(that.month);
    }

    @Override
    public int hashCode() {
            return Objects.hash(wkut, prjno, syr, month);
        }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjWksorc{" +
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
