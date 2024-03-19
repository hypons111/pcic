import applyItems from "../../raw/eng/apply-items";
import { ApplyItemEnum } from "@/shared/enum";

export default {
  $validator: applyItems,
  $message: ({ $model }) => {
    if ($model && $model.includes(ApplyItemEnum.NEW_APPLY) && $model.includes(ApplyItemEnum.REISSUE)) {
      return '不得同時選取［新申請］及［補發］';
    }
  }
};