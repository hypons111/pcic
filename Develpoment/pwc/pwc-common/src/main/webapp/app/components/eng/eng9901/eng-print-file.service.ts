import axios, { AxiosResponse } from "axios";
import { useNotification } from "@/shared/notification";
import { notificationErrorHandler } from "@/shared/http/http-response-helper";
import { downloadFile, formOptionsFormatter } from "@/shared/formatter/common";
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { IAdmAccountContact } from '@/shared/model/adm/adm-account-contact.model';
import { EngPrintFile } from "@/shared/model/eng/eng-print-file.model";
import { Ref, unref } from "@vue/composition-api";
import { ApplyItemEnum, ReceiveTypeEnum, SexEnum } from "@/shared/enum";

export default class EngPrintFileService {
  private notificationService = useNotification();

  public async getEngrCertificatePrintFile(data: IEngEngrCertificate, formOptions: { subject: { value: string | boolean; text: string; enText: string; }[]; }) {
    axios
      .post('/eng-engr-certificates/print-file', await this.getPrintDate(data, formOptions), { responseType: 'blob' })
      .then((response: AxiosResponse) => downloadFile(response))
      .catch(notificationErrorHandler(this.notificationService));
    console.log(await this.getPrintDate(data, formOptions));
  }

  private async getPrintDate(data: IEngEngrCertificate, formOptions: any) {
    let engPrintFile = new EngPrintFile();

    engPrintFile.id = data.engEngrCertificateId;
    engPrintFile.version = String(data.engEngrCertificateVersion);
    engPrintFile.docNo = data.engrCertificateDocNo;
    engPrintFile.enDocNo = data.engrCertificateDocNo ? data.engrCertificateDocNo.split(/[第號]/)[1] : '';
    engPrintFile.applyType = data.applyType;
    engPrintFile.applyItems = data.applyItems;
    engPrintFile.chApplyItemsList = formOptionsFormatter(formOptions.applyItemsList, data.applyItemsList);
    engPrintFile.idno = data.idno;
    engPrintFile.chName = data.chName.replace(/\s+/g, '');
    engPrintFile.enName = data.enName.replace(/\s+/g, '');
    engPrintFile.chBirthDate = chDate(data.birthDate);
    engPrintFile.enBirthDate = enDate(data.birthDate);
    engPrintFile.chSex = data.sex === SexEnum.MALE ? '男' : data.sex === SexEnum.FEMALE ? '女' : '';
    engPrintFile.enSex = data.sex === SexEnum.MALE ? 'Male' : data.sex === SexEnum.FEMALE ? 'Female' : '';
    engPrintFile.enSexPrefix = data.sex === SexEnum.MALE ? 'Mr. ' : data.sex === SexEnum.FEMALE ? 'Ms. ' : '';
    engPrintFile.chSubject = formOptionsFormatter(formOptions.subject, data.subject);
    engPrintFile.enSubject = await this.enSubject(data.subject);
    engPrintFile.examNo = data.examNo;
    engPrintFile.enExamNo = enExamNo();
    engPrintFile.chReceiveDate = chDate(data.receiveDate);
    engPrintFile.enReceiveDate = enDate(data.receiveDate);
    engPrintFile.receiveNo = data.receiveNo ? data.receiveNo : '          ';
    engPrintFile.receiveAddr = await this.receiveAddr(data);
    engPrintFile.letterTitle = letterTitle();
    engPrintFile.chPublicDate_pre = chDate(await this.publicDate_pre(data));

    // 查不到會是空字串
    const contractorInfo = await this.contractorInfo(data.contractorId);
    engPrintFile.contractorName = !!contractorInfo ? contractorInfo.chName : '';
    engPrintFile.contractorTel = !!contractorInfo ? contractorInfo.workTel : '';
    engPrintFile.contractorEmail = !!contractorInfo ? contractorInfo.email : '';

    Object.entries(engPrintFile).forEach(([key, val]) => {
      if (!val) engPrintFile[key] = '';
    })

    return engPrintFile;

    function chDate(value: Ref<Date> | Date | null) {
      if (!!value) {
        if (typeof value === 'string') value = new Date(value); //publicDate_pre不知為何會變string，所以加這行
        value = unref(value);
        return `${value.getFullYear() - 1911}年${value.getMonth() + 1}月${value.getDate()}日`;
      }
      return '';
    }

    function enDate(value: Ref<Date> | Date | null) {
      const MMMM = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
      if (!!value) {
        value = unref(value);
        return `${MMMM[value.getMonth()]} ${value.getDate()}, ${value.getFullYear()}`;
      }
      return '';
    }

    function enExamNo() {
      // 格式須要為"(純數字)包含數字"，例如"(108)XXX第00593號"，否則皆返回空字串
      if (data.examNo) {
        const year = data.examNo.substring(0, data.examNo.indexOf(')')).replace('(', '');
        if (!year || /[^0-9]/.test(year)) return '';
        const no = data.examNo.replace(year, '').replace(/[^0-9]/g, ''); // 把所有數字以外字元取代為''
        if (!no) return '';
        return Number(year) + 1911 + '-' + no;
      }
      return '';
    }

    function letterTitle() {
      if (!data.applyItems.includes(ApplyItemEnum.APPLY_EN)) {
        return '技師證書1張';
      } else if (data.applyItems.includes(ApplyItemEnum.APPLY_EN) && data.applyItems.length === 1) {
        return '技師證書英文證明書1張';
      } else {
        return '技師證書及英文證明書各1張';
      }
    }
  }

  private enSubject(value: string): Promise<string> {
    return new Promise<string>((resolve) => {
      axios
        .get(`/adm-sys-codes/ENG/subject/${value}`)
        .then((response: AxiosResponse) => resolve(response.data.attr2))
        .catch(notificationErrorHandler(this.notificationService));
    })
  }

  private receiveAddr(data: IEngEngrCertificate): Promise<string> {
    return new Promise<string>((resolve) => {
      axios
        .get(`/service/adm-post-addrs/${data.receiveAddrCity + data.receiveAddrTown}`)
        .then((res: AxiosResponse) => {
          const zip = res.data ? res.data.zip + " " : "";
          const city = res.data ? res.data.city : "";
          const town = res.data ? res.data.area : "";

          if (data.receiveType !== ReceiveTypeEnum.SELF) {
            resolve(zip + city + town + data.receiveAddrOther);
          } else {
            resolve(`${zip} 自取:${data.mobileTel} ${city}${town}${data.contactAddrOther}`);
          }
        })
        .catch(notificationErrorHandler(this.notificationService))
    })
  }

  private publicDate_pre(data: IEngEngrCertificate): Promise<Date> {
    return new Promise<Date>((resolve) => {
      axios
        .get(`/eng-engr-certificates/${data.engEngrCertificateId}`)
        .then((response: AxiosResponse) => {
          const engEngrCertificates: IEngEngrCertificate[] = response.data;
          if (engEngrCertificates) {
            const engEngrCertificate_pre = engEngrCertificates
              .sort((itemA, itemB) => itemB.engEngrCertificateVersion - itemA.engEngrCertificateVersion)
              .find(item => item.engEngrCertificateVersion < data.engEngrCertificateVersion);
            resolve(engEngrCertificate_pre ? engEngrCertificate_pre.publicDate : null);
          }
        })
        .catch(notificationErrorHandler(this.notificationService));
    })
  }

  private contractorInfo(userId: string): Promise<IAdmAccountContact> {
    return new Promise<IAdmAccountContact>((resolve) => {
      axios
        .get(`/service/adm-account/${userId}`)
        .then((response: AxiosResponse) => resolve(response.data))
        .catch(notificationErrorHandler(this.notificationService));
    })
  }
}