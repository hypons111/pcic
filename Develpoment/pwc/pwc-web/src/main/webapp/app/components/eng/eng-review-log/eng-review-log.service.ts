import axios from 'axios';
import { useStore } from '@u3u/vue-hooks';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { ReviewCaseTypeEnum, ReviewStatusEnum, StatusEnum } from '@/shared/enum';
import { EngReviewLog } from '@/shared/model/eng/eng-review-log.model';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { EngReviewAndFunction, IEngReviewAndFunction } from '@/shared/model/eng/eng-review-and-function.model';

const baseApiUrl = '/eng-review-logs';

export default class EngReviewLogService {
  private notificationService = useNotification();
  private store = useStore();

  public addReviewLogAndUpdateFunction(
    reviewStatus: ReviewStatusEnum,
    reviewCaseType: ReviewCaseTypeEnum,
    engFunction: IEngEngrCertificate, // | IEngEngrLicense | ICstLicense | ... 
    reviewDesc: string = null
  ): Promise<IEngReviewAndFunction> {
    return new Promise<IEngReviewAndFunction>((resolve) => {
      const forms = this.concatForm(reviewStatus, reviewCaseType, engFunction, reviewDesc);
      axios
        .post(`${baseApiUrl}`, forms)
        .then(res => {
          resolve(res.data);
          const reviewStatusToStr = this.reviewStatusToStr(reviewStatus);
          this.notificationService.success(`${reviewStatusToStr}成功`);
        })
        .catch(notificationErrorHandler(this.notificationService));
    });
  }

  private concatForm(
    reviewStatus: ReviewStatusEnum,
    reviewCaseType: ReviewCaseTypeEnum,
    engFunction: IEngEngrCertificate, // | IEngEngrLicense | ICstLicense | ... 
    reviewDesc: string = null
  ) {
    let engFunctionObj = Object.assign({}, engFunction);
    let engReviewLog = new EngReviewLog();
    let forms = new EngReviewAndFunction();

    switch (reviewStatus) {
      case ReviewStatusEnum.APPLY:
        engFunctionObj.status = this.isOnceReview(reviewCaseType) ? StatusEnum.REVIEW1 : StatusEnum.REVIEW2_1;
        break;
      case ReviewStatusEnum.REAPPLY:
        engFunctionObj.status = this.isOnceReview(reviewCaseType) ? StatusEnum.REVIEW1 : StatusEnum.REVIEW2_1;
        break;
      case ReviewStatusEnum.APPROVE:
        engFunctionObj.status = this.isOnceReview(reviewCaseType) ? StatusEnum.CLOSE : (Number(engFunctionObj.status) + 1).toString();
        break;
      case ReviewStatusEnum.RETURN:
        engFunctionObj.status = StatusEnum.RETURN;
        break;
      case ReviewStatusEnum.CANCEL:
        engFunctionObj.status = this.isOnceReview(reviewCaseType) ? StatusEnum.REVIEW1 : (Number(engFunctionObj.status) - 1).toString();
        break;
      case ReviewStatusEnum.DISCARD:
        engFunctionObj.status = StatusEnum.DISCARD;
    }
    engReviewLog.reviewCaseType = reviewCaseType;
    engReviewLog.reviewStatus = reviewStatus;
    engReviewLog.reviewDesc = reviewDesc;
    engReviewLog.reviewStaff = this.store.value.getters.account.id;
    engReviewLog.reviewTime = new Date();
    engReviewLog.createUser = this.store.value.getters.account.id;
    engReviewLog.createTime = new Date();
    engReviewLog.updateUser = this.store.value.getters.account.id;
    engReviewLog.updateTime = new Date();

    engFunctionObj.reviewStaff = engReviewLog.reviewStaff;
    engFunctionObj.reviewTime = engReviewLog.reviewTime;
    engFunctionObj.reviewDesc = engReviewLog.reviewDesc;

    switch (reviewCaseType) {
      case ReviewCaseTypeEnum.ENGR_CERTIFICATE:
        forms.engEngrCertificateDTO = engFunctionObj;
        engReviewLog.externalId = forms.engEngrCertificateDTO.engEngrCertificateId;
        engReviewLog.externalVersion = forms.engEngrCertificateDTO.engEngrCertificateVersion;
        break;
      case ReviewCaseTypeEnum.ENGR_LICENSE:
        break;
      case ReviewCaseTypeEnum.CST_LICENSE:
        break;
      case ReviewCaseTypeEnum.ENGR_DISCIPLINE:
        break; // 無審核
      case ReviewCaseTypeEnum.COURSE:
        break;
      case ReviewCaseTypeEnum.ENGRCLASS_RECORD:
        break;
      case ReviewCaseTypeEnum.ENGR_SUBJECTPOINT:
        break; // 無審核
      case ReviewCaseTypeEnum.COMP_CHECK:
        break;
      case ReviewCaseTypeEnum.YEARREPORT:
        break;
    }

    forms.engReviewLogDTO = engReviewLog;

    return forms;
  }

  // 審核是否為一關，否則為二關
  private isOnceReview(reviewCaseType: ReviewCaseTypeEnum): boolean {
    switch (reviewCaseType) {
      case ReviewCaseTypeEnum.ENGR_CERTIFICATE:
        return false;
      case ReviewCaseTypeEnum.ENGR_LICENSE:
        return false;
      case ReviewCaseTypeEnum.CST_LICENSE:
        return false;
      case ReviewCaseTypeEnum.ENGR_DISCIPLINE:
        return null; // 無審核
      case ReviewCaseTypeEnum.COURSE:
        return false;
      case ReviewCaseTypeEnum.ENGRCLASS_RECORD:
        return false;
      case ReviewCaseTypeEnum.ENGR_SUBJECTPOINT:
        return null; // 無審核
      case ReviewCaseTypeEnum.COMP_CHECK:
        return true;
      case ReviewCaseTypeEnum.YEARREPORT:
        return true;
    }
  }

  private reviewStatusToStr(reviewStatus: ReviewStatusEnum): string {
    switch (reviewStatus) {
      case ReviewStatusEnum.APPLY:
        return '申請';
      case ReviewStatusEnum.REAPPLY:
        return '重新申請';
      case ReviewStatusEnum.APPROVE:
        return '核准';
      case ReviewStatusEnum.RETURN:
        return '核退';
      case ReviewStatusEnum.CANCEL:
        return '取消核准';
      case ReviewStatusEnum.DISCARD:
        return '作廢';
    }
  }
}
