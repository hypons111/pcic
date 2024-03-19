import { ReviewCaseTypeEnum, ReviewStatusEnum, StatusEnum } from "@/shared/enum";
import { IEngEngrCertificate } from "@/shared/model/eng/eng-engr-certificate.model";
import { IEngReviewLog, EngReviewLog } from "@/shared/model/eng/eng-review-log.model";
import { useStore } from "@u3u/vue-hooks";

export function addReviewLogAndUpdateFunction(
  reviewStatus: ReviewStatusEnum,
  reviewCaseType: ReviewCaseTypeEnum,
  engFunction: IEngEngrCertificate, // | IEngEngrLicense | ICstLicense | ... 
): IEngReviewLog {
  const store = useStore();
  const engReviewLog = new EngReviewLog();

  switch (reviewStatus) {
    case ReviewStatusEnum.APPLY:
      engFunction.status = isOnceReview(reviewCaseType) ? StatusEnum.REVIEW1 : StatusEnum.REVIEW2_1;
      break;
    case ReviewStatusEnum.REAPPLY:
      engFunction.status = isOnceReview(reviewCaseType) ? StatusEnum.REVIEW1 : StatusEnum.REVIEW2_1;
      break;
    case ReviewStatusEnum.APPROVE:
      engFunction.status = isOnceReview(reviewCaseType) ? StatusEnum.CLOSE : (Number(engFunction.status) + 1).toString();
      break;
    case ReviewStatusEnum.RETURN:
      engFunction.status = StatusEnum.RETURN;
      break;
    case ReviewStatusEnum.CANCEL:
      engFunction.status = isOnceReview(reviewCaseType) ? StatusEnum.REVIEW1 : (Number(engFunction.status) - 1).toString();
      break;
    case ReviewStatusEnum.DISCARD:
      engFunction.status = StatusEnum.DISCARD;
  }
  engReviewLog.reviewCaseType = reviewCaseType;
  engReviewLog.reviewStatus = reviewStatus;
  engReviewLog.reviewDesc = engFunction.reviewDesc;
  engReviewLog.reviewStaff = store.value.getters.account.id;
  engReviewLog.reviewTime = new Date();
  engReviewLog.createUser = store.value.getters.account.id;
  engReviewLog.createTime = new Date();
  engReviewLog.updateUser = store.value.getters.account.id;
  engReviewLog.updateTime = new Date();

  engFunction.reviewStaff = engReviewLog.reviewStaff;
  engFunction.reviewTime = engReviewLog.reviewTime;

  switch (reviewCaseType) {
    case ReviewCaseTypeEnum.ENGR_CERTIFICATE:
      engReviewLog.externalId = engFunction.engEngrCertificateId;
      engReviewLog.externalVersion = engFunction.engEngrCertificateVersion;
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

  return engReviewLog;
}

// 審核是否為一關，否則為二關
function isOnceReview(reviewCaseType: ReviewCaseTypeEnum): boolean {
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