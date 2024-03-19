<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            技師證書
          </h5>
        </div>
        <div class="card-body">
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 承辦人員 -->
            <i-form-group-check :label="$t('label.contractorId') + '：'" :item="$v.contractorId">
              <div>{{ $v.contractorName.$model }}</div>
            </i-form-group-check>
            <!-- 狀態 -->
            <i-form-group-check :label="$t('label.status') + '：'" :item="$v.status">
              <div>{{ formOptionsFormatter(formOptions.status, $v.status.$model) }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 申請日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.applyDate') + '：'" :item="$v.applyDate">
              <div v-if="formStatusRef === FormStatusEnum.READONLY || role === RoleEnum.USER">{{ $v.applyDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.applyDate.$model"
                :state="validateState($v.applyDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 申請項目 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.applyItems') + '：'"
              :item="$v.applyItems"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.applyItemsList, $v.applyItemsList.$model) }}
              </div>
              <b-form-checkbox-group
                v-else
                v-model="$v.applyItems.$model"
                :options="formOptions.applyItemsList"
                :state="validateState($v.applyItems)"
              ></b-form-checkbox-group>
            </i-form-group-check>
          </b-form-row>

          <!-- User只在檢視畫面才會看到 -->
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || role === RoleEnum.ADMIN">
            <!-- 收文日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.receiveDate') + '：'" :item="$v.receiveDate">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.receiveDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.receiveDate.$model"
                :state="validateState($v.receiveDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
              <!-- i-date-picker屬性可加入valueType="format"改為接收字串 -->
            </i-form-group-check>
            <!-- 收文文號 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.receiveNo') + '：'" :item="$v.receiveNo">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.receiveNo.$model }}</div>
              <b-form-input v-else v-model="$v.receiveNo.$model" :state="validateState($v.receiveNo)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <!-- User只在檢視畫面才會看到 -->
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || role === RoleEnum.ADMIN">
            <!-- 發證日期 -->
            <i-form-group-check class="col-4" label-cols="6" content-cols="6" :label="$t('label.publicDate') + '：'" :item="$v.publicDate">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.publicDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.publicDate.$model"
                :state="validateState($v.publicDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
            <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="remark-text pt-2 ml-3">註：須大於或等於收文日期</span>
          </b-form-row>

          <b-form-row>
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
            </i-form-group-check>
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
          </b-form-row>

          <b-form-row>
            <!-- 原居住地 -->
            <i-form-group-check
              v-show="$v.identityType.$model === '2'"
              label-star
              class="col-12"
              label-cols="2"
              content-cols="8"
              :label="$t('label.addrForeign') + '：'"
              :item="$v.addrForeign"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.addrForeign.$model }}</div>
              <b-form-input v-else v-model="$v.addrForeign.$model" trim lazy></b-form-input>
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
            <!-- 連絡電話(公) -->
            <i-form-group-check labelStar content-cols="8" :label="$t('label.contactTelOffice') + '：'" :item="$v.contactTelOffice">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.contactTelOffice.$model }}</div>
              <i-tel v-else v-model="$v.contactTelOffice.$model" :state="validateState($v.contactTelOffice)" showExt trim></i-tel>
            </i-form-group-check>
            <!-- 連絡電話(住) -->
            <i-form-group-check content-cols="6" :label="$t('label.contactTelHome') + '：'" :item="$v.contactTelHome">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.contactTelHome.$model }}</div>
              <i-tel v-else v-model="$v.contactTelHome.$model" :state="validateState($v.contactTelHome)" trim></i-tel>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 行動電話 -->
            <i-form-group-check content-cols="6" :label="$t('label.mobileTel') + '：'" :item="$v.mobileTel">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.mobileTel.$model }}</div>
              <i-tel v-else v-model="$v.mobileTel.$model" :state="validateState($v.mobileTel)" isMobile trim></i-tel>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電子信箱 -->
            <i-form-group-check :label="$t('label.email') + '：'" :item="$v.email">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.email.$model }}</div>
              <b-form-input v-else v-model="$v.email.$model" :state="validateState($v.email)" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 傳真 -->
            <i-form-group-check content-cols="6" :label="$t('label.fax') + '：'" :item="$v.fax">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.fax.$model }}</div>
              <b-form-input v-else v-model="$v.fax.$model" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 證書字號 -->
            <i-form-group-check labelStar content-cols="6" :label="$t('label.engrCertificateDocNo') + '：'" :item="$v.engrCertificateDocNo">
              <div>{{ $v.engrCertificateDocNo.$model }}</div>
            </i-form-group-check>
            <!-- 證書字號(舊) -->
            <i-form-group-check content-cols="6" :label="$t('label.engrCertificateDocNoOld') + '：'" :item="$v.engrCertificateDocNoOld">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.engrCertificateDocNoOld.$model }}</div>
              <b-form-input
                v-else
                v-model="$v.engrCertificateDocNoOld.$model"
                :state="validateState($v.engrCertificateDocNoOld)"
                trim
                lazy
              ></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 科別 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.subject') + '：'" :item="$v.subject">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ formOptionsFormatter(formOptions.subject, $v.subject.$model) }}</div>
              <b-form-select v-else v-model="$v.subject.$model" :options="formOptions.subject" :state="validateState($v.subject)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 考試種類 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.examType') + '：'" :item="$v.examType">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.examType, $v.examType.$model) }}
              </div>
              <b-form-select v-else v-model="$v.examType.$model" :options="formOptions.examType" :state="validateState($v.examType)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 考試及格證字號 -->
            <i-form-group-check labelStar content-cols="6" :label="$t('label.examNo') + '：'" :item="$v.examNo">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.examNo.$model }}</div>
              <b-form-input
                v-else
                placeholder="格式：(YYY)______字第_____號"
                v-model="$v.examNo.$model"
                :state="validateState($v.examNo)"
                trim
                lazy
              ></b-form-input>
              <!-- <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="col-8 remark-text">範例：(107)專高技免字第000001號</span> -->
            </i-form-group-check>
            <!-- 考試及格證字號(舊) -->
            <i-form-group-check content-cols="6" :label="$t('label.examNoOld') + '：'" :item="$v.examNoOld">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.examNoOld.$model }}</div>
              <b-form-input v-else placeholder="格式：(YYY)______字第_____號" v-model="$v.examNoOld.$model" trim lazy></b-form-input>
              <!-- <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="col-8 remark-text">範例：(107)專高技免字第000001號</span> -->
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
              <b-table-simple
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
              </b-table-simple>
            </i-form-group-check>
            <b-table-simple
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
            </b-table-simple>
          </b-form-row>

          <b-form-row>
            <!-- 虛擬帳號 -->
            <i-form-group-check :label="$t('label.virtualAccount') + '：'">
              <div>{{ engEbillingCheckRef.code1 }}{{ engEbillingCheckRef.code2 }}{{ engEbillingCheckRef.code3 }}</div>
              <span class="pl-3 remark-text">中國信託銀行代碼:822</span>
            </i-form-group-check>
            <!-- 付款狀態 -->
            <i-form-group-check :label="$t('label.isClose') + '：'">
              <div>{{ formOptionsFormatter(formOptions.isClose, engEbillingCheckRef.isClose) }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 審核說明 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.reviewDesc') + '：'" :item="$v.reviewDesc">
              <b-form-textarea
                :plaintext="role === RoleEnum.USER || formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.reviewDesc.$model"
                rows="4"
                maxlength="2000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <!-- 審核元件 -->
          <!-- <b-form-row class="mt-3 mb-2" v-show="eng0101">
            <b-col cols="10" offset="1">
              <eng-review-log
                ref="engReviewLog"
                :id="$v.engEngrCertificateId.$model"
                :version="$v.engEngrCertificateVersion.$model"
                :type="ReviewCaseTypeEnum.ENGR_CERTIFICATE"
                :formStatus="formStatusRef"
                :role="role"
              ></eng-review-log>
            </b-col>
          </b-form-row> -->
        </div>
        <div class="text-center mb-2">
          <!-- 考試院資料檢核 -->
          <i-button
            v-if="
              formStatusRef !== FormStatusEnum.READONLY &&
              ($v.status.$model === '3' ||
                $v.status.$model === '4' ||
                ($v.status.$model === '5' && formStatusRef !== FormStatusEnum.EDIT)) &&
              role === RoleEnum.ADMIN
            "
            type="card-checklist"
            @click="checkExam"
          />
          <!-- 套印 -->
          <i-button
            v-if="
              $v.status.$model === '5' &&
              role === RoleEnum.ADMIN &&
              (formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT)
            "
            type="layers-fill"
            @click="downloadPrintFile"
          />
          <!-- 儲存 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="save" @click="submitForm(false)" />
          <!-- 清除 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="x-circle" @click="pageReset" />
          <!-- 送出 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="send-check" @click="submitForm(true)" />
          <!-- 申請(新增另案) -->
          <i-button
            v-if="
              $v.status.$model === '5' &&
              role === RoleEnum.ADMIN &&
              (formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT)
            "
            type="node-plus"
            @click="changeFormStatus(FormStatusEnum.REMAKE)"
          ></i-button>
          <!-- 申請(變更申請) -->
          <i-button
            v-if="formStatusRef === FormStatusEnum.READONLY && $v.status.$model === '5' && role === RoleEnum.USER"
            type="node-plus"
            @click="changeFormStatus(FormStatusEnum.CHANGE_APPLY)"
          ></i-button>
          <!-- 作廢 -->
          <i-button
            v-if="
              ($v.status.$model !== '5' || role === RoleEnum.ADMIN) &&
              $v.status.$model !== '6' &&
              (formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT)
            "
            type="clipboard-x"
            @click="discard"
          ></i-button>
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
        </div>
        <div class="text-center mb-3">
          <!-- 上一筆 -->
          <!-- <i-button type="arrow-bar-left" @click="switchCase(false)" /> -->
          <i-button
            v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT"
            type="arrow-bar-left"
            @click="switchCase(false)"
            :disabled="criteriaRef.currentPage === 0"
          />
          <!-- 下一筆 -->
          <!-- <i-button type="arrow-bar-right" @click="switchCase(true)" /> -->
          <i-button
            v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT"
            type="arrow-bar-right"
            @click="switchCase(true)"
            :disabled="criteriaRef.currentPage === criteriaRef.totalItems - 1"
          />
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import AccountService from '@/account/account.service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import EngPrintFileService from '../eng9901/eng-print-file.service';
import EngEngrCertificateService from './eng-engr-certificate.service';
import EngReviewLog from '@/components/eng/eng-review-log/eng-review-log.vue';
// import EngReviewLogService from '@/components/eng/eng-review-log/eng-review-log.service';
import { addReviewLogAndUpdateFunction } from '@/components/eng/eng-review-log/eng-review-log';
import { IEng0101 } from '@/shared/model/eng/eng0101.model';
import { EngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { EngEbillingCheck } from '@/shared/model/eng/eng-ebilling-check.model';
import { IEngReviewLog } from '@/shared/model/eng/eng-review-log.model';
import { AdmFile } from '@/shared/model/adm/adm-file.model';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { downloadFile, formOptionsFormatter } from '@/shared/formatter/common';
import { reactive, ref, toRefs, onMounted, defineComponent, inject, watch, unref, h, computed } from '@vue/composition-api';
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

interface propsType {
  eng0101: IEng0101;
  formStatus: FormStatusEnum;
  role: RoleEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0101EditInfo',
  props: {
    eng0101: {
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
  components: {
    EngReviewLog,
  },
  setup(props) {
    const accountService = inject<() => AccountService>('accountService')();
    const engEngrCertificateService = new EngEngrCertificateService();
    const engPrintFileService = new EngPrintFileService();
    const admSysCodeService = new AdmSysCodeService();

    const { eng0101 } = toRefs(props);
    const formStatusRef = ref(props.formStatus);
    const criteriaRef = ref(props.criteria);
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    const engEbillingCheckRef = ref(new EngEbillingCheck());
    const isReload = ref(false); // 是否需要重新查詢
    const isSamePermanentAddr = ref(false); // 同戶籍地址checkbox
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true

    const picPreview = ref(null); // 照片預覽
    const picPreviewDefault = ref(null); // 照片預覽

    // const engReviewLogService = new EngReviewLogService();
    // const engReviewLog = ref(null); // 綁定EngReviewLog元件

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
    });

    let formDefault = new EngEngrCertificate();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      engEngrCertificateId: {},
      engEngrCertificateVersion: {},
      engEngrCertificateNo: {},
      status: {},
      reviewStaff: {},
      reviewTime: {},
      reviewDesc: {},
      applyItemsList: {},
      applyItems: { required: required, applyItems: applyItems },
      applyType: {},
      applyDate: { requiredIf: requiredIf(() => isCheckRequired.value) },
      idno: { requiredIf: requiredIf(() => isCheckRequired.value), nationalId: nationalId },
      chName: { requiredIf: requiredIf(() => isCheckRequired.value), maxLength: maxLength(20) },
      enName: { requiredIf: requiredIf(() => isCheckRequired.value), enName: enName, maxLength: maxLength(100) },
      birthDate: { requiredIf: requiredIf(() => isCheckRequired.value) },
      sex: { requiredIf: requiredIf(() => isCheckRequired.value) },
      contactTelOffice: { requiredIf: requiredIf(() => isCheckRequired.value), lineTel: lineTel },
      contactTelHome: { lineTel: lineTel },
      mobileTel: { requiredIf: requiredIf(() => isCheckRequired.value && form.receiveType === ReceiveTypeEnum.SELF), mobileTel: mobileTel },
      fax: {},
      email: { email: email, maxLength: maxLength(50) },
      permanentAddrZip: {},
      permanentAddrCity: { requiredIf: requiredIf(() => isCheckRequired.value) },
      permanentAddrTown: { equalsCityBoolean: address(() => form.permanentAddrCity) },
      permanentAddrOther: { equalsCityBoolean: address(() => form.permanentAddrCity) },
      contactAddrZip: {},
      contactAddrCity: { requiredIf: requiredIf(() => isCheckRequired.value) },
      contactAddrTown: { equalsCityBoolean: address(() => form.contactAddrCity) },
      contactAddrOther: { equalsCityBoolean: address(() => form.contactAddrCity) },
      identityType: { required: required },
      addrForeign: { maxLength: maxLength(300) },
      subject: { required: required },
      engrCertificateDocNo: {},
      engrCertificateDocNoOld: {
        requiredIf: requiredIf(
          () => isCheckRequired.value && form.applyItems.includes(ApplyItemEnum.REISSUE) && !form.engrCertificateDocNo
        ),
        maxLength: maxLength(50),
      },
      examType: { required: required },
      examNo: { requiredIf: requiredIf(() => isCheckRequired.value), maxLength: maxLength(100) },
      examNoOld: { maxLength: maxLength(100) },
      receiveDate: { requiredIf: requiredIf(() => isCheckRequired.value && form.status === StatusEnum.REVIEW2_1) },
      receiveNo: {
        requiredIf: requiredIf(() => isCheckRequired.value && form.status === StatusEnum.REVIEW2_1),
        maxLength: maxLength(20),
      },
      publicDate: {
        requiredIf: requiredIf(() => isCheckRequired.value && form.status === StatusEnum.REVIEW2_2),
        greaterThan: greaterThan(
          computed(() => form.receiveDate),
          true
        ),
      },
      receiveType: { requiredIf: requiredIf(() => isCheckRequired.value) },
      receiveAddrZip: {},
      receiveAddrCity: { requiredIf: requiredIf(() => isCheckRequired.value) },
      receiveAddrTown: { equalsCityBoolean: address(() => form.receiveAddrCity) },
      receiveAddrOther: { equalsCityBoolean: address(() => form.receiveAddrCity) },
      contractorId: {},
      contractorName: {},
      remark: { maxLength: maxLength(1000) },
      createTime: {},
      picFile: {
        requiredIf: requiredIf(() => isCheckRequired.value && !form.picFileInfo),
        file: file(1 * 1024 * 1024, ['image/jpeg', 'image/png', 'application/pdf']),
        // verticalPicture: verticalPicture,
      },
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

    onMounted(() => {
      getAdmSysCodes();
      createDefaultValue(unref(eng0101));
    });

    function getAdmSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      // setApplyItems();
      formOptions.status = [
        { value: '0', text: '暫存' },
        { value: '1', text: '核退' },
        { value: '3', text: '已送出' },
        { value: '4', text: '已收文' },
        { value: '5', text: '已結案' },
        { value: '6', text: '作廢' },
      ];
      formOptions.receiveType = formOptions.receiveType.filter(item => item.value !== ReceiveTypeEnum.COMPANY);
    }

    function createDefaultValue(data: IEng0101) {
      Object.assign(formDefault, data.engEngrCertificateDTO);
      setDate();
      setApplyItems();
      reset();
      getContractorName();
      getPic();
      engEbillingCheckRef.value = data.engEbillingCheckDTO ? data.engEbillingCheckDTO : new EngEbillingCheck();
    }

    function setDate() {
      formDefault.applyDate = parseToDate(formDefault.applyDate, '-');
      formDefault.receiveDate = parseToDate(formDefault.receiveDate, '-');
      formDefault.publicDate = parseToDate(formDefault.publicDate, '-');
      formDefault.birthDate = parseToDate(formDefault.birthDate, '-');
    }

    function setApplyItems() {
      formDefault.applyItems = formDefault.applyItemsList ? formDefault.applyItemsList.split(',') : [];
      switch (formStatusRef.value) {
        case FormStatusEnum.CREATE:
          formDefault.applyItems = [ApplyItemEnum.NEW_APPLY];
          break;
        case FormStatusEnum.REMAKE:
          formOptions.applyItemsList = formOptions.applyItemsList.filter(item => item.value !== ApplyItemEnum.NEW_APPLY);
          formDefault.applyItems = [];
          break;
        case FormStatusEnum.CHANGE_APPLY:
          formOptions.applyItemsList = formOptions.applyItemsList.filter(item => item.value !== ApplyItemEnum.NEW_APPLY);
          formDefault.applyItems = [ApplyItemEnum.REISSUE];
          break;
        case FormStatusEnum.EDIT:
          if (!formDefault.applyItems.includes(ApplyItemEnum.NEW_APPLY)) {
            formOptions.applyItemsList = formOptions.applyItemsList.filter(item => item.value !== ApplyItemEnum.NEW_APPLY);
          }
      }
    }

    function getContractorName() {
      axios
        .get(`/service/adm-account/${form.contractorId}`)
        .then(res => {
          formDefault.contractorName = res.data?.chName;
          form.contractorName = res.data?.chName;
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function getPic() {
      if (!form.engEngrCertificateId) return;

      const admFile = new AdmFile();
      admFile.moduleType = 'ENG';
      admFile.businessType = 'ENG_ENGR_CERTIFICATE';
      admFile.sourceId = form.engEngrCertificateId + '_' + form.engEngrCertificateVersion.toFixed(3) + '@0';

      axios
        .post('/adm-files/pic', admFile, { responseType: 'blob' })
        .then((res: AxiosResponse<Blob>) => {
          picPreviewDefault.value = res.data;
          picSelected(res.data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function submitForm(isCheckReq: boolean) {
      isCheckRequired.value = isCheckReq;
      $v.value.$reset(); // 先清除欄位$dirty狀態，才能依據isCheckRequired(是否驗證必填)重新驗證

      // 不加this.$nextTick，checkValidity() 會比上方 $v.value.$reset() 先執行!!!
      this.$nextTick(() => {
        checkValidity().then((isValid: boolean) => {
          if (isValid) {
            if (isCheckReq) {
              $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
                if (isOK) {
                  putForm();
                }
              });
            } else {
              putForm();
            }
          } else {
            $bvModal.msgBoxOk(isCheckReq ? '欄位尚未填寫完畢，請於輸入完畢後再行送出。' : '欄位尚未填寫完整，請輸入完整或不輸入再行儲存。');
          }
        });
      });
    }

    async function putForm() {
      const formData = await setFormBeforeSubmit();

      if (formStatusRef.value === FormStatusEnum.EDIT) {
        engEngrCertificateService
          .update(formData)
          .then(eng0101 => {
            createDefaultValue(eng0101);
            isReload.value = true;
          })
          .catch(notificationErrorHandler(notificationService));
      } else {
        engEngrCertificateService
          .create(formData)
          .then(eng0101 => {
            // 1.新增完繼續按儲存或送出才會變修改 2.要在createDefaultValue上面才不會改到applyItems
            formStatusRef.value = FormStatusEnum.EDIT;
            createDefaultValue(eng0101);
            isReload.value = true;
          })
          .catch(notificationErrorHandler(notificationService));
      }
    }

    async function setFormBeforeSubmit(isDiscard: boolean = false): Promise<FormData> {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.applyDate = form.applyDate ? new Date(form.applyDate.getTime() - offset * 60 * 1000) : null;
      form.receiveDate = form.receiveDate ? new Date(form.receiveDate.getTime() - offset * 60 * 1000) : null;
      form.publicDate = form.publicDate ? new Date(form.publicDate.getTime() - offset * 60 * 1000) : null;
      form.birthDate = form.birthDate ? new Date(form.birthDate.getTime() - offset * 60 * 1000) : null;

      form.applyItemsList = form.applyItems.join(',');
      form.updateTime = new Date();
      form.updateUser = accountService.user.id;
      // READONLY 可以作廢會修改資料，所以也要擋
      if (formStatusRef.value !== FormStatusEnum.EDIT && formStatusRef.value !== FormStatusEnum.READONLY) {
        form.engEngrCertificateVersion = formStatusRef.value === FormStatusEnum.CREATE ? 1 : await getVersion();
        form.newFrom = formStatusRef.value;
        form.status = isCheckRequired.value ? StatusEnum.REVIEW2_1 : StatusEnum.TEMP;
        form.contractorId = props.role === RoleEnum.ADMIN ? accountService.user.id : null;
        form.applyType = props.role === RoleEnum.ADMIN ? ApplyTypeEnum.PAPER_APPLY : ApplyTypeEnum.ONLINE_APPLY;
        form.createTime = new Date();
        form.createUser = accountService.user.id;
        form.reviewStaff = null;
        form.reviewTime = null;
        form.reviewDesc = null;
        form.transferTime = null;
        form.transferFrom = null;
      }

      let reviewLog: IEngReviewLog = null;
      if (isDiscard) {
        reviewLog = addReviewLogAndUpdateFunction(ReviewStatusEnum.DISCARD, ReviewCaseTypeEnum.ENGR_CERTIFICATE, form);
      } else {
        if (form.status === StatusEnum.REVIEW2_1 && !!form.receiveDate) {
          reviewLog = addReviewLogAndUpdateFunction(ReviewStatusEnum.APPROVE, ReviewCaseTypeEnum.ENGR_CERTIFICATE, form);
        }
        if (form.status === StatusEnum.REVIEW2_2 && !!form.publicDate) {
          reviewLog = addReviewLogAndUpdateFunction(ReviewStatusEnum.APPROVE, ReviewCaseTypeEnum.ENGR_CERTIFICATE, form);
        }
      }

      const formData = new FormData();
      formData.append('form', new Blob([JSON.stringify(form)], { type: 'application/json' }));
      formData.append('picFile', form.picFile);
      form.appendixFiles.forEach(file => {
        formData.append('appendixFiles', file);
      });
      formData.append('reviewLog', new Blob([JSON.stringify(reviewLog)], { type: 'application/json' }));

      return formData;
    }

    async function getVersion(): Promise<number> {
      let status: number;
      const nextVersion = (await engEngrCertificateService.findNextVersionOne(form)).engEngrCertificateVersion;
      if (nextVersion) {
        const versionDiff = parseFloat((nextVersion - form.engEngrCertificateVersion).toPrecision(12));
        if (1 >= versionDiff && versionDiff > 0.1) {
          status = form.engEngrCertificateVersion + 0.1;
        } else if (0.1 >= versionDiff && versionDiff > 0.01) {
          status = form.engEngrCertificateVersion + 0.01;
        } else if (0.01 >= versionDiff && versionDiff > 0.001) {
          status = form.engEngrCertificateVersion + 0.001;
        } else if (versionDiff === 0.001) {
          notificationService.danger('無法新增：版號（version）小數位已達上限。');
          return null;
        }
      } else {
        status = form.engEngrCertificateVersion + 1;
      }
      return parseFloat(status.toPrecision(12));
    }

    function changeFormStatus(formStatus: FormStatusEnum) {
      formStatusRef.value = formStatus;
      setApplyItems();
      reset();
    }

    function toQueryView() {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    }

    function pageReset() {
      reset();
      // engReviewLog.value.reviewDesc = '';
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

    function downloadAdmFile(id: number) {
      axios
        .get(`/adm-files/download/${id}`, { responseType: 'blob' })
        .then((response: AxiosResponse) => downloadFile(response))
        .catch(notificationErrorHandler(notificationService));
    }

    function deleteAppendix(id: number) {
      axios
        .delete(`/adm-files/${id}`)
        .then(() => {
          formDefault.appendixFilesInfo = formDefault.appendixFilesInfo.filter(item => item.id !== id);
          form.appendixFilesInfo = form.appendixFilesInfo.filter(item => item.id !== id);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function discard() {
      $bvModal.msgBoxConfirm('是否確認作廢？').then(async (isOK: boolean) => {
        if (isOK) {
          const formData = await setFormBeforeSubmit(true);
          engEngrCertificateService
            .update(formData)
            .then(eng0101 => {
              formStatusRef.value = FormStatusEnum.READONLY;
              createDefaultValue(eng0101);
              isReload.value = true;
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    function checkExam() {
      axios
        .get(`/eng-engr-exams/${form.idno}/${form.subject}`)
        .then(({ data }) => {
          if (!data) {
            $bvModal.msgBoxOk('查無資料', { title: '考試院資料檢核' });
          } else {
            const messageVNode = h('table', [
              h('tr', [h('th', '科別名稱：'), h('td', `${formOptionsFormatter(formOptions.subject, data.subject)}`)]),
              h('tr', [h('th', '姓名：'), h('td', `${data.name}`)]),
              h('tr', [h('th', '性別：'), h('td', `${formOptionsFormatter(formOptions.sex, data.sex)}`)]),
              h('tr', [h('th', '出生年月日：'), h('td', `${data.birthday}`)]),
              h('tr', [h('th', '身分證字號：'), h('td', `${data.idno}`)]),
            ]);
            $bvModal.msgBoxOk([messageVNode], { title: '考試院資料檢核' });
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function queryEngMember() {
      if (form.idno && !$v.value.idno.$error) {
        axios
          .get(`/eng-members/${MemberTypeEnum.ENGR}/${form.idno}`)
          .then(copyMemberInfo)
          .catch(notificationErrorHandler(notificationService));
      }
    }

    function copyMemberInfo(res: AxiosResponse) {
      if (!res.data) return;
      const data = res.data;
      form.engrId = data.engMemberId;
      form.chName = data.chName;
      form.enName = data.enName;
      form.birthDate = new Date(data.birthDate);
      form.sex = data.sex;
      form.contactTelOffice = data.tel1;
      form.contactTelHome = data.tel2;
      form.mobileTel = data.tel3;
      form.fax = data.fax;
      form.email = data.email1;
      form.permanentAddrZip = data.permanentAddrZip;
      form.permanentAddrCity = data.permanentAddrCity;
      form.permanentAddrTown = data.permanentAddrTown;
      form.permanentAddrOther = data.permanentAddrOther;
      form.contactAddrZip = data.contactAddrZip;
      form.contactAddrCity = data.contactAddrCity;
      form.contactAddrTown = data.contactAddrTown;
      form.contactAddrOther = data.contactAddrOther;
      form.identityType = data.identityType;
    }

    function downloadPrintFile() {
      engPrintFileService.getEngrCertificatePrintFile(formDefault, formOptions);
    }

    function switchCase(isNext: boolean) {
      if (isNext) {
        if (unref(criteriaRef).currentPage === unref(criteriaRef).totalItems - 1) {
          $bvModal.msgBoxOk('無下一筆');
          return;
        }
        unref(criteriaRef).currentPage += 1;
      } else {
        if (unref(criteriaRef).currentPage === 0) {
          $bvModal.msgBoxOk('無上一筆');
          return;
        }
        unref(criteriaRef).currentPage -= 1;
      }
      changeFormStatus(FormStatusEnum.READONLY);
      engEngrCertificateService
        .switchCase(unref(criteriaRef))
        .then(createDefaultValue)
        .catch(notificationErrorHandler(notificationService));
    }

    watch(
      [
        () => form.permanentAddrCity,
        () => form.permanentAddrTown,
        () => form.permanentAddrOther,
        () => form.contactAddrCity,
        () => form.contactAddrTown,
        () => form.contactAddrOther,
      ],
      copyAddr
    );

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

    // function addReviewLog(reviewStatus: ReviewStatusEnum, msgBoxStr: string) {
    //   // isCheckRequired.value = true;
    //   checkValidity().then((isValid: boolean) => {
    //     if (isValid) {
    //       $bvModal.msgBoxConfirm(msgBoxStr).then((isOK: boolean) => {
    //         if (isOK) {
    //           setFormBeforeSubmit();
    //           engReviewLogService
    //             .addReviewLogAndUpdateFunction(reviewStatus, ReviewCaseTypeEnum.ENGR_CERTIFICATE, form, engReviewLog.value.reviewDesc)
    //             .then(data => {
    //               // createDefaultValue(data.engEngrCertificateDTO);
    //               isReload.value = true;
    //               engReviewLog.value.reviewDesc = '';
    //               engReviewLog.value.query();
    //             });
    //         }
    //       });
    //     } else {
    //       $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
    //     }
    //   });
    // }

    function appendixSelected(files: File[]) {}

    return {
      FormStatusEnum,
      RoleEnum,
      ReviewCaseTypeEnum,
      ReviewStatusEnum,
      engEbillingCheckRef,
      formStatusRef,
      changeFormStatus,
      $v,
      validateState,
      pageReset,
      toQueryView,
      submitForm,
      putForm,
      formOptions,
      formOptionsFormatter,
      // engReviewLog,
      // addReviewLog,
      copyAddr,
      samePermanentAddr,
      isSamePermanentAddr,
      picPreview,
      picSelected,
      appendixSelected,
      downloadAdmFile,
      deleteAppendix,
      checkExam,
      queryEngMember,
      downloadPrintFile,
      discard,
      switchCase,
      criteriaRef,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>
