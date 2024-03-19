import { ApplyItemEnum } from "@/shared/enum";

export default function (applyItems: string[]): boolean {
  if (applyItems.includes(ApplyItemEnum.NEW_APPLY) && applyItems.includes(ApplyItemEnum.REISSUE)) return false;
  return true;
}