<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            技師執業執照
          </h5>
        </div>
        <div class="card-body">
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 編號 -->
            <i-form-group-check content-cols="4" :label="$t('label.engEngrNo') + '：'" :item="$v.engEngrLicenseId">
              <div></div>
            </i-form-group-check>
            <!-- 狀態 -->
            <i-form-group-check content-cols="4" :label="$t('label.status') + '：'" :item="$v.status">
              <div></div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 收文日期 -->
            <i-form-group-check content-cols="4" :label="$t('label.receiveDate') + '：'" :item="$v.receiveDate">
              <div v-if="formStatusRef === FormStatusEnum.READONLY || role === RoleEnum.USER">
                {{ $v.receiveDate.$model | dateToString }}
              </div>
              <i-date-picker
                v-else
                v-model="$v.receiveDate.$model"
                :state="validateState($v.receiveDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
            <!-- 承辦人員 -->
            <i-form-group-check :label="$t('label.contractorId') + '：'" :item="$v.contractorId">
              <div></div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 身分別 -->
            <i-form-group-check labelStar content-cols="8" :label="$t('label.identityType') + '：'" :item="$v.identityType">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.identityType, $v.identityType.$model) }}
              </div>
              <b-form-radio-group
                v-else
                v-model="$v.identityType.$model"
                :options="formOptions.identityType"
                :state="validateState($v.identityType)"
              ></b-form-radio-group>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.idno') + '：'" :item="$v.idno">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.idno.$model }}</div>
              <b-form-input
                v-else
                v-model="$v.idno.$model"
                :state="validateState($v.idno)"
                id="idnoInputPopover"
                maxlength="10"
                @blur="queryEngMember"
                trim
                lazy
              ></b-form-input>
              <b-popover
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                target="idnoInputPopover"
                placement="right"
                triggers="hover focus"
                title="會員查詢"
                content="如為會員，輸入正確身分證字號將帶入相關資料。"
              ></b-popover>
              <b-col v-if="formStatusRef !== FormStatusEnum.READONLY">
                <b-button class="d-inline-block" variant="info">公司登記查詢</b-button>&nbsp;&nbsp;
                <b-button class="d-inline-block" variant="info">專任工程人員查詢</b-button>
              </b-col>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 姓名(中) -->
            <i-form-group-check labelStar content-cols="6" :label="$t('label.chName') + '：'" :item="$v.chName">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.chName.$model }}</div>
              <b-form-input v-else v-model="$v.chName.$model" :state="validateState($v.chName)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 姓名(英) -->
            <i-form-group-check labelStar content-cols="6" :label="$t('label.enName') + '：'" :item="$v.enName">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.enName.$model }}</div>
              <b-form-input
                v-else
                v-model="$v.enName.$model"
                :state="validateState($v.enName)"
                placeholder="格式：LI, TA-TUNG"
                trim
                lazy
              ></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.sex') + '：'" :item="$v.sex">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ formOptionsFormatter(formOptions.sex, $v.sex.$model) }}</div>
              <b-form-select v-else v-model="$v.sex.$model" :options="formOptions.sex" :state="validateState($v.sex)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 出生日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.birthDate') + '：'" :item="$v.birthDate">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.birthDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.birthDate.$model"
                :state="validateState($v.birthDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 申請項目 -->
            <i-form-group-check :label="$t('label.applyItems') + '：'" :item="$v.applyItem" content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.applyItemsList, $v.applyItem.$model) }}
              </div>
              <b-form-select v-else v-model="$v.applyItem.$model" :options="formOptions.applyItemsList"> </b-form-select>
            </i-form-group-check>
            <!-- 死亡註記 -->
            <i-form-group-check content-cols="4" :label="$t('label.engEngrDeathRemark') + '：'" :item="$v.engEngrDeathRemark">
              <b-form-checkbox
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                id="isDeath"
                v-model="status"
                name="checkbox-1"
                value="accepted"
                unchecked-value="not_accepted"
              >
                技師已死亡，註記如下：
              </b-form-checkbox>
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.isDeathRemark.$model }}</div>
              <b-form-input v-else v-model="$v.isDeathRemark.$model" :state="validateState($v.isDeathRemark)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 發證日期 -->
            <i-form-group-check content-cols="4" :label="$t('label.engEngrLicensePublicData') + '：'" :item="$v.engEngrLicensePublicData">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.publicData.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.publicData.$model"
                :state="validateState($v.publicData)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 有效日期(起) -->
            <i-form-group-check content-cols="4" :label="$t('label.engEngrEffectDataStart') + '：'" :item="$v.engEngrEffectDataStart">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.effectDateStart.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.effectDateStart.$model"
                :state="validateState($v.effectDateStart)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
            <!-- 有效日期(迄) -->
            <i-form-group-check content-cols="4" :label="$t('label.engEngrEffectDataEnd') + '：'" :item="$v.engEngrEffectDataEnd">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.effectDateEnd.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.effectDateEnd.$model"
                :state="validateState($v.effectDateEnd)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 戶籍地址 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.permanentAddr') + '：'"
              :item="[$v.permanentAddrTown, $v.permanentAddrOther, $v.permanentAddrCity]"
            >
              <i-address-picker
                :isText="formStatusRef === FormStatusEnum.READONLY"
                :zip.sync="$v.permanentAddrZip.$model"
                :city.sync="$v.permanentAddrCity.$model"
                :town.sync="$v.permanentAddrTown.$model"
                :other.sync="$v.permanentAddrOther.$model"
                :cityState="validateState($v.permanentAddrCity)"
                :townState="validateState($v.permanentAddrTown)"
                :otherState="validateState($v.permanentAddrOther)"
                :cols="[1, 2, 2, 5]"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 通訊地址 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.contactAddr') + '：'"
              :item="[$v.contactAddrTown, $v.contactAddrOther, $v.contactAddrCity]"
            >
              <i-address-picker
                :isText="formStatusRef === FormStatusEnum.READONLY"
                :zip.sync="$v.contactAddrZip.$model"
                :city.sync="$v.contactAddrCity.$model"
                :town.sync="$v.contactAddrTown.$model"
                :other.sync="$v.contactAddrOther.$model"
                :cityState="validateState($v.contactAddrCity)"
                :townState="validateState($v.contactAddrTown)"
                :otherState="validateState($v.contactAddrOther)"
                :cols="[1, 2, 2, 5, 2]"
                :disabled="isSamePermanentAddr"
              >
                <template #end>
                  <b-form-checkbox class="ml-2" v-model="isSamePermanentAddr" @change="samePermanentAddr">
                    <span class="small">同戶籍地址</span>
                  </b-form-checkbox>
                </template>
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 行動電話 -->
            <i-form-group-check content-cols="4" :label="$t('label.mobileTel') + '：'" :item="$v.mobileTel">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.mobileTel.$model }}</div>
              <b-form-input v-else v-model="$v.mobileTel.$model" :state="validateState($v.mobileTel)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 電子信箱 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.email') + '：'" :item="$v.email">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.email.$model }}</div>
              <b-form-input v-else v-model="$v.email.$model" :state="validateState($v.email)" trim lazy></b-form-input>
              <span class="remark-text" v-if="formStatusRef !== FormStatusEnum.READONLY"
                >為後續本會通知各類訊息所需，請務必填寫可做為聯絡之用e-mail</span
              >
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 科別及證書字號 -->
            <i-form-group-check
              content-cols="4"
              :label="$t('label.engEngrSubjectAndCertificateDocNo') + '：'"
              :item="$v.engEngrSubjectAndCertificateDocNo"
            >
              <b-form-checkbox v-if="formStatusRef === FormStatusEnum.READONLY" class="ml-2" disabled></b-form-checkbox>
              <b-form-checkbox v-else class="ml-2"></b-form-checkbox>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 執業方式 -->
            <i-form-group-check
              class="col-4"
              label-cols="6"
              content-cols="6"
              labelStar
              :label="$t('label.execType') + '：'"
              :item="$v.execType"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.execType, $v.execType.$model) }}
              </div>
              <b-form-select v-else v-model="$v.execType.$model" :options="formOptions.execType" :state="validateState($v.execType)">
              </b-form-select>
            </i-form-group-check>
            <b-col>
              <b-button variant="info" v-if="formStatusRef !== FormStatusEnum.READONLY">勞保局資料檢查</b-button>
            </b-col>
          </b-form-row>

          <b-form-row>
            <!-- 統一編號 -->
            <i-form-group-check content-cols="4" :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compIdno.$model }}</div>
              <b-form-input v-else v-model="$v.compIdno.$model" :state="validateState($v.compIdno)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 執業機構名稱 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.engCompChName') + '名稱：'" :item="$v.compChName">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compChName.$model }}</div>
              <b-form-input v-else v-model="$v.compChName.$model" :state="validateState($v.compChName)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 執業機構電話(一) -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.engCompTel1') + '：'" :item="$v.compTel1">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compTel1.$model }}</div>
              <b-form-input v-else v-model="$v.compTel1.$model" :state="validateState($v.compTel1)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 執業機構名稱 (英)-->
            <i-form-group-check content-cols="4" :label="$t('label.engCompEnName') + '：'" :item="$v.compEnName">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compEnName.$model }}</div>
              <b-form-input v-else v-model="$v.compEnName.$model" :state="validateState($v.compEnName)" trim lazy> </b-form-input>
              <span class="remark-text" v-if="formStatusRef !== FormStatusEnum.READONLY">請與經濟部國際貿易局所登記之公司英文名稱相同</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 執業機構電話(二) -->
            <i-form-group-check content-cols="4" :label="$t('label.engCompTel2') + '：'" :item="$v.compTel2">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compTel2.$model }}</div>
              <b-form-input v-else v-model="$v.compTel2.$model" :state="validateState($v.compTel2)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 執業機構傳真-->
            <i-form-group-check content-cols="4" :label="$t('label.engCompChName') + $t('label.fax') + '：'" :item="$v.compFax">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compFax.$model }}</div>
              <b-form-input v-else v-model="$v.compFax.$model" :state="validateState($v.compFax)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 執業機構所在地 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.engCompAddr') + '：'"
              :item="[$v.contactAddrTown, $v.contactAddrOther, $v.contactAddrCity]"
            >
              <i-address-picker
                :isText="formStatusRef === FormStatusEnum.READONLY"
                :zip.sync="$v.compAddrZip.$model"
                :city.sync="$v.compAddrCity.$model"
                :town.sync="$v.compAddrTown.$model"
                :other.sync="$v.compAddrOther.$model"
                :cityState="validateState($v.compAddrCity)"
                :townState="validateState($v.compAddrTown)"
                :otherState="validateState($v.compAddrOther)"
                :cols="[1, 2, 2, 5, 2]"
                :disabled="isSamePermanentAddr"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 執業機構所在地(英) -->
            <i-form-group-check labelStar content-cols="8" :label="$t('label.engCompAddrEn') + '：'" :item="$v.compAssrEn">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compAssrEn.$model }}</div>
              <b-form-input v-else v-model="$v.compAssrEn.$model" :state="validateState($v.compAssrEn)" trim lazy> </b-form-input>
            </i-form-group-check>
            <b-col v-if="formStatusRef !== FormStatusEnum.READONLY">
              <span class="remark-text"
                >如未有正式英文地址，請至中華郵政中文地址英譯查詢，格式：9F,No 3,Songren Rd, Xinyi Dist, Taipei City 110, Taiwan (R.O.C)
              </span>
            </b-col>
          </b-form-row>

          <b-form-row>
            <!-- 收文文號 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.receiveNo') + '：'" :item="$v.receiveNo">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.receiveNo.$model }}</div>
              <b-form-input v-else v-model="$v.receiveNo.$model" :state="validateState($v.receiveNo)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 執照字號 -->
            <i-form-group-check :label="$t('label.engrLicenseDocNo') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.receiveNo.$model }}第{{ $v.engrLicenseDocNo.$model }}號</div>
              <div v-else>
                <b-form-input
                  class="ml-2 col-4 d-inline-block"
                  v-model="$v.receiveNo.$model"
                  :state="validateState($v.receiveNo)"
                  trim
                  lazy
                ></b-form-input
                >&nbsp;第
                <b-form-input
                  class="ml-2 col-4 d-inline-block"
                  v-model="$v.engrLicenseDocNo.$model"
                  :state="validateState($v.engrLicenseDocNo)"
                  trim
                  lazy
                >
                </b-form-input
                >&nbsp;號
              </div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 具有七年以上實務經驗 -->
            <i-form-group-check content-cols="4" :label="$t('label.engEngrIsMore7Year') + '：'" :item="$v.isMore7Year">
              <b-form-checkbox v-if="formStatusRef === FormStatusEnum.READONLY" class="ml-2" disabled></b-form-checkbox>
              <b-form-checkbox v-else class="ml-2"></b-form-checkbox>
              是
            </i-form-group-check>
            <!-- 領有XCA憑證 -->
            <i-form-group-check content-cols="4" :label="$t('label.engEngrIsXcaCert') + '：'" :item="$v.isXcaCert">
              <b-form-checkbox v-if="formStatusRef === FormStatusEnum.READONLY" class="ml-2" disabled></b-form-checkbox>
              <b-form-checkbox v-else class="ml-2"></b-form-checkbox>
              是
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 取件方式 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.receiveType') + '：'"
              :item="$v.receiveType"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.receiveType, $v.receiveType.$model) }}
              </div>
              <b-form-radio-group
                v-else
                class="d-inline-block"
                v-model="$v.receiveType.$model"
                :options="formOptions.receiveType"
                :state="validateState($v.receiveType)"
                @change="copyAddr"
              >
              </b-form-radio-group>
              <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="remark-text">註：若是自取，請填寫行動電話</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 收件地址 -->
            <i-form-group-check
              v-show="$v.receiveType.$model && $v.receiveType.$model !== '3'"
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.receiveAddr') + '：'"
              :item="[$v.receiveAddrTown, $v.receiveAddrOther, $v.receiveAddrCity]"
            >
              <i-address-picker
                :isText="formStatusRef === FormStatusEnum.READONLY"
                :zip.sync="$v.receiveAddrZip.$model"
                :city.sync="$v.receiveAddrCity.$model"
                :town.sync="$v.receiveAddrTown.$model"
                :other.sync="$v.receiveAddrOther.$model"
                :cityState="validateState($v.receiveAddrCity)"
                :townState="validateState($v.receiveAddrTown)"
                :otherState="validateState($v.receiveAddrOther)"
                :cols="[1, 2, 2, 5]"
                :disabled="$v.receiveType.$model !== '4'"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 虛擬帳號 -->
            <i-form-group-check :label="$t('label.virtualAccount') + '：'">
              <div>{{ engEbillingCheckRef.code1 }}{{ engEbillingCheckRef.code2 }}{{ engEbillingCheckRef.code3 }}</div>
              <span class="pl-3 remark-text">中國信託銀行代碼:822</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 付款狀態 -->
            <i-form-group-check :label="$t('label.isClose') + '：'">
              <div>{{ formOptionsFormatter(formOptions.isClose, engEbillingCheckRef.isClose) }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="role === RoleEnum.ADMIN">
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.remark') + '：'" :item="$v.remark">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.remark.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 照片 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.picture') + '：'"
              :item="$v.picFile"
            >
              <b-form-file
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                accept="image/jpeg, image/png, application/pdf"
                browse-text="選擇檔案"
                placeholder="未選擇任何檔案"
                drop-placeholder="拖曳檔案至此"
                v-model="$v.picFile.$model"
                :state="validateState($v.picFile)"
                @input="picSelected"
                class="col-9"
              ></b-form-file>
              <!-- <b-button variant="outline-info" class="ml-2">取消</b-button> -->
              <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="col-3"></span>
              <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="remark-text col-12 p-0"
                >註：照片上傳以 2 吋大頭照 jpg、pdf 或 PNG 格式為限，檔案大小勿超過 1M，以利本會套印</span
              >
              <b-img
                v-if="formStatusRef === FormStatusEnum.READONLY && picPreview"
                class="mt-2 mr-4"
                style="height: 300px"
                alt="Image"
                fluid
                thumbnail
                :src="picPreview"
              />
              <i-button
                v-if="formStatusRef === FormStatusEnum.READONLY && picPreview"
                size="sm"
                type="file-earmark-arrow-down"
                @click="downloadAdmFile($v.picFileInfo.$model.id)"
              ></i-button>
            </i-form-group-check>
            <div v-if="formStatusRef !== FormStatusEnum.READONLY && picPreview" class="offset-2 mb-4">
              <b-img class="mr-4" style="height: 300px" alt="Image" fluid thumbnail :src="picPreview" />
              <i-button size="sm" type="file-earmark-arrow-down" @click="downloadAdmFile($v.picFileInfo.$model.id)"></i-button>
            </div>
          </b-form-row>

          <b-form-row>
            <!-- 附件 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.appendix') + '：'"
              :item="$v.appendixFiles"
            >
              <b-form-file
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                multiple
                accept="image/jpeg, image/png, application/pdf"
                browse-text="選擇檔案"
                placeholder="未選擇任何檔案（可選擇多個檔案）"
                drop-placeholder="拖曳檔案至此"
                v-model="$v.appendixFiles.$model"
                :state="validateState($v.appendixFiles)"
                @input="appendixSelected"
                class="col-9"
              >
                <template slot="file-name" slot-scope="{ names }">
                  <b-badge variant="info">{{ names[0] }}</b-badge>
                  <b-badge v-if="names.length > 1" variant="info" class="ml-1">{{ names[1] }}</b-badge>
                  <b-badge v-if="names.length > 2" variant="info" class="ml-1"> + {{ names.length - 2 }} 個檔案 </b-badge>
                </template>
              </b-form-file>
              <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="remark-text col-3"
                >新申請者附：考試及格證書<br />補發者附&emsp;：考試及格證書及遺失切結書</span
              >
              <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="remark-text col-12 p-0"
                >註：附件上傳以 jpg、pdf 或 PNG 格式為限，單筆檔案大小勿超過 2M</span
              >
              <!-- <b-table-simple
                v-if="formStatusRef === FormStatusEnum.READONLY && $v.appendixFilesInfo.$model.length > 0"
                class="mt-2"
                bordered
                small
              >
                <b-tr>
                  <b-th colspan="3" class="text-center">已上傳附件</b-th>
                </b-tr>
                <b-tr v-for="(admFile, index) of $v.appendixFilesInfo.$model" :key="admFile.id">
                  <b-th class="text-center">附件{{ index + 1 }}</b-th>
                  <b-td>&nbsp;{{ admFile.fileNameOri }}</b-td>
                  <b-td class="text-center col-3">
                    <i-button size="sm" type="file-earmark-arrow-down" @click="downloadAdmFile(admFile.id)"></i-button>
                  </b-td>
                </b-tr>
              </b-table-simple> -->
            </i-form-group-check>
            <!-- <b-table-simple
              v-if="formStatusRef !== FormStatusEnum.READONLY && $v.appendixFilesInfo.$model.length > 0"
              class="offset-2 col-9"
              bordered
              small
            >
              <b-tr>
                <b-th colspan="3" class="text-center">已上傳附件</b-th>
              </b-tr>
              <b-tr v-for="(admFile, index) of $v.appendixFilesInfo.$model" :key="admFile.id">
                <b-th class="text-center">附件{{ index + 1 }}</b-th>
                <b-td>&nbsp;{{ admFile.fileNameOri }}</b-td>
                <b-td class="text-center col-3">
                  <i-button size="sm" type="file-earmark-arrow-down" @click="downloadAdmFile(admFile.id)"></i-button>
                  <i-button size="sm" type="trash" @click="deleteAppendix(admFile.id)"></i-button>
                </b-td>
              </b-tr>
            </b-table-simple> -->
          </b-form-row>

          <div class="text-center mb-2">
            <!-- 儲存 -->
            <i-button type="save" @click="submitForm(false)" />
            <!-- 清除 -->
            <i-button type="x-circle" @click="pageReset" v-if="formStatusRef !== FormStatusEnum.READONLY" />
            <!-- 送出 -->
            <i-button type="send-check" @click="submitForm(true)" v-if="formStatusRef !== FormStatusEnum.READONLY"/>
            <!-- 返回 -->
            <i-button type="arrow-left" @click="toQueryView" />
          </div>
        </div>
      </div>

    </section>
  </div>
</template>

<script  lang="ts">
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { EngEngrLicense } from '@/shared/model/eng/eng-engr-license.model';
import { IEng0102 } from '@/shared/model/eng/eng0102.model';
import { defineComponent, onActivated, onMounted, reactive, ref, toRefs, unref } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { downloadFile, formOptionsFormatter } from '@/shared/formatter/common';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import {
  required,
  requiredIf,
  maxLength,
  nationalId,
  email,
  enName,
  mobileTel,
  lineTel,
  applyItems,
  address,
  file,
  greaterThan,
  verticalPicture,
} from '@/shared/validators';
import {
  RoleEnum,
  FormStatusEnum,
  ReviewCaseTypeEnum,
  ReviewStatusEnum,
  MemberTypeEnum,
  ApplyItemEnum,
  ReceiveTypeEnum,
  ApplyTypeEnum,
  StatusEnum,
} from '@/shared/enum';
import { EngEbillingCheck } from '@/shared/model/eng/eng-ebilling-check.model';
import { handleBack } from '@/router/router';

interface propsType {
  eng0102: IEng0102;
  formStatus: FormStatusEnum;
  role: RoleEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0102EditInfo',
  props: {
    eng0102: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    role: {
      required: false,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();

    const { eng0102 } = toRefs(props);
    const formStatusRef = ref(props.formStatus);
    const engEbillingCheckRef = ref(new EngEbillingCheck());
    const isSamePermanentAddr = ref(false); // 同戶籍地址checkbox
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true
    const picPreview = ref(null); // 照片預覽
    const picPreviewDefault = ref(null); // 照片預覽
    const isReload = ref(false); // 是否需要重新查詢

    onActivated(() => {
      formStatusRef.value = props.formStatus;
    })

    const formOptions = reactive({
      status: [],
      applyItemsList: [],
      applyType: [],
      subject: [],
      examType: [],
      identityType: [],
      sex: [],
      receiveType: [],
      isClose: [],
      execType: [],
    });

    let formDefault = new EngEngrLicense();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      engEngrLicenseId: {},
      engEngrLicenseVersion: {},
      engEngrLicenseNo: {},
      receiveDate: {},
      publicData: {},
      engrLicenseDocNo: {},
      engrId: {},
      identityType: {},
      idno: {},
      chName: {},
      chNameRome: {},
      enName: {},
      birthDate: {},
      sex: {},
      mobileTel: {},
      email: {},
      permanentAddrZip: {},
      permanentAddrCity: {},
      permanentAddrTown: {},
      permanentAddrOther: {},
      contactAddrZip: {},
      contactAddrCity: {},
      contactAddrTown: {},
      contactAddrOther: {},
      execType: {},
      compId: {},
      compType: {},
      compIdno: {},
      compChName: {},
      compEnName: {},
      compTel1: {},
      compTel2: {},
      compFax: {},
      compAddrZip: {},
      compAddrCity: {},
      compAddrTown: {},
      compAddrOther: {},
      compAssrEn: {},
      applyItem: {},
      applyItemsList: {},
      applyType: {},
      receiveNo: {},
      publicDate: {},
      effectDateStart: {},
      effectDateEnd: {},
      remark: {},
      receiveType: {},
      receiveAddrZip: {},
      receiveAddrCity: {},
      receiveAddrTown: {},
      receiveAddrOther: {},
      certificateIdList: {},
      subjectList: {},
      isMore7Year: {},
      isXcaCert: {},
      changeItems: {},
      changeItemsDesc: {},
      contractorId: {},
      status: {},
      reviewStaff: {},
      reviewTime: {},
      reviewDesc: {},
      creatUser: {},
      creatTime: {},
      updateUser: {},
      updateTime: {},
      transferFrom: {},
      transferTime: {},

      isDeath: {},
      isDeathRemark: {},
      picFile: {},
      picFileInfo: {},
      appendixFiles: {
        requiredIf: requiredIf(() => isCheckRequired.value && form.appendixFilesInfo.length == 0),
        file: file(2 * 1024 * 1024, ['image/jpeg', 'image/png', 'application/pdf']),
      },
      appendixFilesInfo: {},
    };

    const groups = [
      ['permanentAddrCity', 'permanentAddrTown', 'permanentAddrOther'],
      ['contactAddrCity', 'contactAddrTown', 'contactAddrOther'],
      ['receiveAddrCity', 'receiveAddrTown', 'receiveAddrOther'],
      ['receiveDate', 'publicDate'],
    ];

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault, groups);

    function setDate() {
      formDefault.receiveDate = parseToDate(formDefault.receiveDate, '-');
      formDefault.publicDate = parseToDate(formDefault.publicDate, '-');
      formDefault.birthDate = parseToDate(formDefault.birthDate, '-');
    }

    function setFormOptions() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      // setApplyItems();
      formOptions.status = [
        { value: '1', text: '待審核' },
        { value: '2', text: '審核中' },
        { value: '3', text: '已結案' },
        { value: '5', text: '暫存' },
        { value: '0', text: '作廢' },
      ];
      formOptions.applyItemsList = [
        { value: '0', text: '新申請' },
        { value: '1', text: '變更' },
        { value: '2', text: '換發' },
        { value: '3', text: '換照' },
        { value: '4', text: '補發' },
        { value: '5', text: '自行停業' },
        { value: '6', text: '變更及換發' },
        { value: '7', text: '廢止' },
        { value: '8', text: '核發英文證明書' },
      ];
      formOptions.receiveType = formOptions.receiveType.filter(item => item.value !== ReceiveTypeEnum.COMPANY);
    }

    onMounted(() => {
      setFormOptions();
      createDefaultValue(unref(eng0102));
    });

    function createDefaultValue(data: IEng0102) {

      // fake data
      let fakeData = {
        engEngrLicenseId : '1900010001',
        engEngrLicenseVersion : 1,
        engEngrLicenseNo : '',
        receiveDate : '2022-07-07',
        engrLicenseDocNo : '000348',
        engrId : '00012299',
        identityType : '0',
        idno : 'F228456464',
        chName : '林孟儒',
        chNameRome : '',
        enName : 'LIN, MENG-JU',
        birthDate : '1999-10-09',
        sex : '0',
        mobileTel : '0933724869',
        email : 'jkl34032@gmail.com',
        permanentAddrZip : '114',
        permanentAddrCity : '台北市',
        permanentAddrTown : '內湖區',
        permanentAddrOther : '陽光街323號',
        contactAddrZip : '',
        contactAddrCity : '',
        contactAddrTown : '',
        contactAddrOther : '',
        execType : '2',
        compId : '28412550',
        compType : '',
        // compIdno : '',
        compChName : '台灣世曦工程顧問股份有限公司',
        compEnName : '',
        compTel1 : '0287973567*1131',
        compTel2 : '',
        compFax : '',
        compAddrZip : '100',
        compAddrCity : '臺北市',
        compAddrTown : '中正區',
        compAddrOther : '汀洲路1段350號21樓',
        compAssrEn : '',
        applyItem : '',
        applyType : '',
        receiveNo : '',
        // publicDate : '',
        // effectDateStart : '',
        // effectDateEnd : '',
        remark : '',
        receiveType : '0',
        receiveAddrZip : '100',
        receiveAddrCity : '臺北市',
        receiveAddrTown : '中正區',
        receiveAddrOther : '汀洲路1段350號21樓',
        certificateIdList : '',
        subjectList : '',
        isMore7Year : '',
        isXcaCert : '',
        changeItems : '',
        changeItemsDesc : '',
        contractorId : '',
        status : '',
        reviewStaff : '',
        // reviewTime : '',
        reviewDesc : '',
        creatUser : '',
        // creatTime : '',
        updateUser : '',
        // updateTime : '',
        transferFrom : '',
        // transferTime : '',
      }

      // TODO: 暫時塞假資料
      if (ref(props.formStatus).value !== '1') {
        Object.assign(formDefault, fakeData);
      }

      // Object.assign(formDefault, data.engEngrLicenseDTO);
      setDate();
      setApplyItems();
      reset();
      getContractorName();
      getPic();
      engEbillingCheckRef.value = data.engEbillingCheckDTO ? data.engEbillingCheckDTO : new EngEbillingCheck();
    }

    function getContractorName() {
      // axios
      //   .get(`/service/adm-account/${form.contractorId}`)
      //   .then(res => {
      //     formDefault.contractorName = res.data?.chName;
      //     form.contractorName = res.data?.chName;
      //   })
      //   .catch(notificationErrorHandler(notificationService));
    }

    function setApplyItems() {
      // formDefault.applyItems = formDefault.applyItemsList ? formDefault.applyItemsList.split(',') : [];
      // switch (formStatusRef.value) {
      //   case FormStatusEnum.CREATE:
      //     formDefault.applyItems = [ApplyItemEnum.NEW_APPLY];
      //     break;
      //   case FormStatusEnum.REMAKE:
      //     formOptions.applyItemsList = formOptions.applyItemsList.filter(item => item.value !== ApplyItemEnum.NEW_APPLY);
      //     formDefault.applyItems = [];
      //     break;
      //   case FormStatusEnum.CHANGE_APPLY:
      //     formOptions.applyItemsList = formOptions.applyItemsList.filter(item => item.value !== ApplyItemEnum.NEW_APPLY);
      //     formDefault.applyItems = [ApplyItemEnum.REISSUE];
      //     break;
      //   case FormStatusEnum.EDIT:
      //     if (!formDefault.applyItems.includes(ApplyItemEnum.NEW_APPLY)) {
      //       formOptions.applyItemsList = formOptions.applyItemsList.filter(item => item.value !== ApplyItemEnum.NEW_APPLY);
      //     }
      // }
    }

    function getPic() {
      // if (!form.engEngrCertificateId) return;
      // const admFile = new AdmFile();
      // admFile.moduleType = 'ENG';
      // admFile.businessType = 'ENG_ENGR_CERTIFICATE';
      // admFile.sourceId = form.engEngrCertificateId + '_' + form.engEngrCertificateVersion.toFixed(3) + '@0';
      // axios
      //   .post('/adm-files/pic', admFile, { responseType: 'blob' })
      //   .then((res: AxiosResponse<Blob>) => {
      //     picPreviewDefault.value = res.data;
      //     picSelected(res.data);
      //   })
      //   .catch(notificationErrorHandler(notificationService));
    }

    function deleteAppendix(id: number) {
      // axios
      //   .delete(`/adm-files/${id}`)
      //   .then(() => {
      //     formDefault.appendixFilesInfo = formDefault.appendixFilesInfo.filter(item => item.id !== id);
      //     form.appendixFilesInfo = form.appendixFilesInfo.filter(item => item.id !== id);
      //   })
      //   .catch(notificationErrorHandler(notificationService));
    }

    function copyAddr() {
      if (isSamePermanentAddr.value) {
        form.contactAddrZip = form.permanentAddrZip;
        form.contactAddrCity = form.permanentAddrCity;
        form.contactAddrTown = form.permanentAddrTown;
        form.contactAddrOther = form.permanentAddrOther;
      }

      switch (form.receiveType) {
        case '0':
          form.receiveAddrZip = form.permanentAddrZip;
          form.receiveAddrCity = form.permanentAddrCity;
          form.receiveAddrTown = form.permanentAddrTown;
          form.receiveAddrOther = form.permanentAddrOther;
          break;
        case '1':
          form.receiveAddrZip = form.contactAddrZip;
          form.receiveAddrCity = form.contactAddrCity;
          form.receiveAddrTown = form.contactAddrTown;
          form.receiveAddrOther = form.contactAddrOther;
          break;
        case '3':
          form.receiveAddrZip = null;
          form.receiveAddrCity = null;
          form.receiveAddrTown = null;
          form.receiveAddrOther = null;
          break;
        case '4':
          form.receiveAddrZip = null;
          form.receiveAddrCity = null;
          form.receiveAddrTown = null;
          form.receiveAddrOther = null;
      }
    }

    function samePermanentAddr() {
      switch (isSamePermanentAddr.value) {
        case true:
          form.contactAddrZip = form.permanentAddrZip;
          form.contactAddrCity = form.permanentAddrCity;
          form.contactAddrTown = form.permanentAddrTown;
          form.contactAddrOther = form.permanentAddrOther;
          break;
        case false:
          form.contactAddrZip = formDefault.contactAddrZip;
          form.contactAddrCity = formDefault.contactAddrCity;
          form.contactAddrTown = formDefault.contactAddrTown;
          form.contactAddrOther = formDefault.contactAddrOther;
        // form.contactAddrZip = null;
        // form.contactAddrCity = null;
        // form.contactAddrTown = null;
        // form.contactAddrOther = null;
      }
    }

    function picSelected(file: File | Blob) {
      const fileReader = new FileReader();
      fileReader.onload = e => {
        picPreview.value = e.target.result;
      };
      if (file && file.size !== 0) {
        fileReader.readAsDataURL(file);
      } else if (unref(picPreviewDefault) && unref(picPreviewDefault).size !== 0) {
        fileReader.readAsDataURL(unref(picPreviewDefault));
      } else {
        picPreview.value = null;
      }
    }

    function queryEngMember() {
      if (form.idno && !$v.value.idno.$error) {
        // axios
        //   .get(`/eng-members/${MemberTypeEnum.ENGR}/${form.idno}`)
        //   .then(copyMemberInfo)
        //   .catch(notificationErrorHandler(notificationService));
      }
    }

    function appendixSelected(files: File[]) {}

    function pageReset() {
      reset();
    }

    function toQueryView() {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    }

    return {
      $v,
      checkValidity,
      engEbillingCheckRef,
      FormStatusEnum,
      formStatusRef,
      formOptions,
      formOptionsFormatter,
      reset,
      setDate,
      validateState,
      samePermanentAddr,
      isSamePermanentAddr,
      picPreview,
      picSelected,
      queryEngMember,
      copyAddr,
      appendixSelected,
      pageReset,
      toQueryView,
      RoleEnum,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>

<style></style>
