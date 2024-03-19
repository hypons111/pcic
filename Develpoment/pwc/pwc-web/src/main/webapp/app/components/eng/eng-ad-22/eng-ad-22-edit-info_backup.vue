<template>
  <div>
    <!-- 基本資料 -->
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          詳細資料
        </h5>
      </div>
      <div class="card-body">
        <!-- 編輯模式 到時候包v-if-->
        <div>
          <!-- 基本資料 b-col-->

          <div v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE">
            <h3>本來的寫法...用b-col,有驗證的部分=>所屬單位和email</h3>
            <b-form-row class="align-items-center">
              <b-col cols="2" class="text-right label">
                <span>所屬單位<span class="red-text">*</span></span>
              </b-col>
              <b-col cols="1">
                <b-form-select :options="belongUnit" :disabled="formStatusRef === FormStatusEnum.INFO">
                  <template #first>
                    <option value="undefined">-- 請選擇 --</option>
                  </template>
                </b-form-select>
              </b-col>
              <b-col cols="3">
                <b-form-input
                  v-model.trim="$v.belongOf.$model"
                  :state="validateState($v.belongOf)"
                  placeholder="請輸入所屬單位"
                ></b-form-input>
              </b-col>
              <b-col cols="2" class="text-right label">
                <span> 主辦單位名稱</span>
              </b-col>
              <b-col cols="4">
                <b-form-input
                  v-model.trim="$v.belongOf.$model"
                  :state="validateState($v.belongOf)"
                  placeholder="請輸入主辦單位"
                ></b-form-input>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="3"> </b-col>
              <b-col cols="3">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.belongOf.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
              <b-col cols="3"> </b-col>
              <b-col cols="3">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.belongOf.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-center">
              <b-col cols="2" class="text-right label">
                <span>課程名稱</span>
              </b-col>
              <b-col cols="4">
                <div v-if="formStatusRef === FormStatusEnum.INFO">
                  {{ $v.name.$model }}
                </div>
                <div v-else>
                  <b-form-input v-model.trim="$v.name.$model" :state="validateState($v.name)" placeholder="請輸入課程名稱"></b-form-input>
                </div>
              </b-col>
              <b-col cols="2" class="text-right label">
                <span>申請日期</span>
              </b-col>
              <b-col cols="4">
                <div v-if="formStatusRef === FormStatusEnum.INFO">
                  {{ formatDate($v.createDate.$model, '/') }}
                </div>
                <div v-else>
                  <i-date-picker v-model="$v.createDate.$model" :state="validateState($v.createDate)"> </i-date-picker>
                </div>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="2"> </b-col>
              <b-col cols="4">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
              <b-col cols="2"> </b-col>
              <b-col cols="4">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.createDate.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-stretch">
              <b-col cols="2"></b-col>
              <b-col cols="4">
                <span class="red-text little-font">如有採用視訊或網路教學，請於其後加註「本課程有採用視訊或網路教學」</span>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-center">
              <b-col cols="2" class="text-right label">
                <span>課程日期</span>
              </b-col>
              <b-col cols="3">
                <i-date-picker v-model="$v.date_Start.$model" :state="validateState($v.date_Start)"> </i-date-picker>
              </b-col>
              <b-col cols="1"><span>至</span></b-col>
              <b-col cols="3">
                <i-date-picker v-model="$v.date_End.$model" :state="validateState($v.date_End)"> </i-date-picker>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="2"> </b-col>
              <b-col cols="3">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.date_Start.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
              <b-col cols="1"> </b-col>
              <b-col cols="3">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.date_End.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>

            <b-form-row class="align-items-top">
              <b-col cols="2" class="text-right label">
                <span>上傳截止日期</span>
              </b-col>
              <b-col cols="4">
                <i-date-picker v-model="$v.UploadDeadline.$model" :state="validateState($v.UploadDeadline)"> </i-date-picker>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="2"> </b-col>
              <b-col cols="3">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.UploadDeadline.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>

            <b-form-row class="align-items-top">
              <b-col cols="2" class="text-right label">
                <span>上課地點</span>
              </b-col>
              <b-col cols="6">
                <b-form-input v-model="$v.place.$model" :state="validateState($v.place)" placeholder="請輸入上課地點"></b-form-input>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="2"> </b-col>
              <b-col cols="6">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.place.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>

            <b-form-row class="align-items-top">
              <b-col cols="2" class="text-right label">
                <span>聯絡人姓名</span>
              </b-col>
              <b-col cols="2">
                <b-form-input
                  v-model.trim="$v.contact_Name.$model"
                  :state="validateState($v.contact_Name)"
                  placeholder="請輸入聯絡人姓名"
                ></b-form-input>
              </b-col>
              <b-col cols="2"></b-col>
              <b-col cols="2" class="text-right label">
                <span>聯絡人電話</span>
              </b-col>
              <b-col cols="2">
                <b-form-input
                  v-model.trim="$v.contact_Tel.$model"
                  :state="validateState($v.contact_Tel)"
                  placeholder="請輸入聯絡人電話"
                ></b-form-input>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="2"> </b-col>
              <b-col cols="2">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Name.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
              <b-col cols="2"> </b-col>
              <b-col cols="2">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Tel.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>

            <b-form-row class="align-items-top">
              <b-col cols="2" class="text-right label">
                <span>電子郵件</span>
              </b-col>
              <b-col cols="6">
                <b-form-input v-model="$v.contact_Mail.$model" :state="validateState($v.contact_Mail)" placeholder="請輸入電子郵件">
                </b-form-input>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="2"> </b-col>
              <b-col cols="6">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Mail.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>

            <b-form-row class="align-items-top">
              <b-col cols="2" class="text-right label">
                <span>活動網址</span>
              </b-col>
              <b-col cols="6">
                <b-form-input v-model="$v.webUrl.$model" :state="validateState($v.webUrl)" placeholder="請輸入活動網址"></b-form-input>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="2"> </b-col>
              <b-col cols="6">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.webUrl.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>

            <b-form-row class="align-items-top">
              <b-col cols="2" class="text-right label">
                <span>備註</span>
              </b-col>
              <b-col cols="6">
                <b-form-textarea
                  v-model="$v.remark.$model"
                  :state="validateState($v.remark)"
                  placeholder="備註..."
                  rows="3"
                  max-rows="3"
                ></b-form-textarea>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top error-message little-font">
              <b-col cols="2"> </b-col>
              <b-col cols="6">
                <div class="is-invalid"></div>
                <b-form-invalid-feedback v-for="(error, index) of $v.remark.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-top little-font">
              <b-col cols="2"></b-col>
              <b-col cols="6">
                <span class="red-text"
                  >如有採用視訊或網路教學，請於備註欄說明本課程之學員身分確認機制及簽到/退管理方式，相關資料並應建檔留存。</span
                >
              </b-col>
            </b-form-row>
          </div>
          <!-- END OF 基本資料 b-col-->

          <!-- 基本資料 b-form group-->

          <div v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE">
            <h3>改用b-form group</h3>
            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-3"
                label-class="label"
                label-cols-md="8"
                content-cols-md="4"
                :label="$t('label.eng.companyName')"
                label-align-md="right"
              >
                <b-form-select :options="belongUnit" :disabled="formStatusRef === FormStatusEnum.INFO">
                  <template #first>
                    <option value="undefined">-- 請選擇 --</option>
                  </template>
                </b-form-select>
              </b-form-group>
              <b-form-group class="col-sm-3" label-sr-only label-align-md="right" content-cols-md="12">
                <b-form-input
                  v-model.trim="$v.belongOf.$model"
                  :state="validateState($v.belongOf)"
                  placeholder="請輸入所屬單位"
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.belongOf.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.belongUnit')"
                label-align-md="right"
              >
                <b-form-input
                  v-model.trim="$v.belongOf.$model"
                  :state="validateState($v.belongOf)"
                  placeholder="請輸入主辦單位"
                ></b-form-input>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.courseName')"
                label-align-md="right"
              >
                <b-form-input v-model.trim="$v.name.$model" :state="validateState($v.name)" placeholder="請輸入課程名稱"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.createDate')"
                label-align-md="right"
              >
                <i-date-picker v-model="$v.createDate.$model" :state="$v.createDate"> </i-date-picker>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-top little-font pt-0">
              <b-col cols="2"></b-col>
              <b-col cols="4">
                <span class="red-text little-font">如有採用視訊或網路教學，請於其後加註「本課程有採用視訊或網路教學」</span>
              </b-col>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-4"
                label-cols-md="6"
                label-class="label"
                content-cols-md="6"
                :label="$t('label.eng.date_Start')"
                label-align-md="right"
              >
                <i-date-picker v-model="$v.date_Start.$model" :state="$v.date_Start"></i-date-picker>
              </b-form-group>
              <b-form-group
                class="col-sm-4"
                label-cols-md="2"
                content-cols-md="10"
                :state="validateState($v.date_End)"
                :label="$t('label.eng.date_End')"
                label-align-md="right"
              >
                <i-date-picker v-model="$v.date_End.$model" :state="$v.date_End"> </i-date-picker>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-4"
                label-cols-md="6"
                label-class="label"
                content-cols-md="6"
                :state="validateState($v.date_End)"
                :label="$t('label.eng.UploadDeadline')"
                label-align-md="right"
              >
                <i-date-picker v-model="$v.UploadDeadline.$model" :state="$v.UploadDeadline"> </i-date-picker>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-12"
                label-cols-md="2"
                label-class="label"
                content-cols-md="6"
                :label="$t('label.eng.place')"
                label-align-md="right"
              >
                <b-form-input v-model="$v.place.$model" :state="validateState($v.place)" placeholder="請輸入上課地點"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.place.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.contact_Name')"
                label-align-md="right"
              >
                <b-form-input
                  v-model.trim="$v.contact_Name.$model"
                  :state="validateState($v.contact_Name)"
                  placeholder="請輸入聯絡人姓名"
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Name.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.contact_Tel')"
                label-align-md="right"
              >
                <b-form-input
                  v-model.trim="$v.contact_Tel.$model"
                  :state="validateState($v.contact_Tel)"
                  placeholder="請輸入聯絡人電話"
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Tel.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center pb-0 mb-0">
              <b-form-group
                class="col-sm-12"
                label-class="label"
                label-cols-md="2"
                content-cols-md="6"
                :label="$t('label.eng.contact_Mail')"
                label-align-md="right"
              >
                <b-form-input v-model="$v.contact_Mail.$model" :state="validateState($v.contact_Mail)" placeholder="請輸入電子郵件">
                </b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Mail.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-12"
                label-cols-md="2"
                label-class="label"
                content-cols-md="6"
                :label="$t('label.eng.webUrl')"
                label-align-md="right"
              >
                <b-form-input v-model="$v.webUrl.$model" :state="validateState($v.webUrl)" placeholder="請輸入活動網址"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.webUrl.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-12"
                label-cols-md="2"
                label-class="label"
                content-cols-md="6"
                :label="$t('label.eng.remark')"
                label-align-md="right"
              >
                <b-form-textarea
                  v-model="$v.remark.$model"
                  :state="validateState($v.remark)"
                  placeholder="備註..."
                  rows="3"
                  max-rows="3"
                ></b-form-textarea>
                <b-form-invalid-feedback v-for="(error, index) of $v.remark.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-top error-message little-font">
              <b-form-group class="col-sm-2" label-sr-only label-align-md="right" content-cols-md="12"> </b-form-group>
              <b-form-group class="col-sm-8" label-sr-only label-align-md="right" content-cols-md="12">
                <span class="red-text"
                  >如有採用視訊或網路教學，請於備註欄說明本課程之學員身分確認機制及簽到/退管理方式，相關資料並應建檔留存。</span
                >
              </b-form-group>
            </b-form-row>
          </div>
          <!-- END OF 基本資料 b-form group-->
          <eng22courseHeadInfo v-if="formStatusRef === FormStatusEnum.INFO" :courseHead="form" :key="form.ID"> </eng22courseHeadInfo>

          <!-- 核准的欄位 -->
          <div>
            <b-form-row class="align-items-center mt-4">
              <b-form-group class="col-sm-6" label-cols-md="4" content-cols-md="8" label="課程核備文號" label-align-md="right">
                <b-form-input placeholder="課程核備文號"></b-form-input>
              </b-form-group>
              <b-form-group
                class="col-sm-6 align-items-center"
                label-cols-md="4"
                content-cols-md="8"
                label="審核狀態"
                label-align-md="right"
              >
                <div class="align-items-center">審核狀態</div>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group class="col-sm-12" label-cols-md="2" content-cols-md="6" label="初審" label-align-md="right"> </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group class="col-sm-12" label-cols-md="2" content-cols-md="6" label="復審" label-align-md="right"> </b-form-group>
            </b-form-row>
          </div>
          <!-- 功能按鈕 -->
          <b-button-toolbar class="float-right mt-5">
            <b-button size="sm" v-show="formStatusRef === FormStatusEnum.INFO" @click="changeStatusToEdit" variant="outline-secondary">
              <font-awesome-icon icon="edit"></font-awesome-icon>
              {{ $t('button.modify') }}
            </b-button>
            <b-button size="sm" v-show="formStatusRef === FormStatusEnum.MODIFY" @click="changeStatusToInfo" variant="outline-secondary">
              <font-awesome-icon icon="tag"> </font-awesome-icon>
              {{ $t('button.temporary') }}
            </b-button>
            <b-button size="sm" @click="toQueryView" variant="outline-secondary">
              <font-awesome-icon icon="arrow-left"> </font-awesome-icon>
              {{ $t('button.go_back') }}
            </b-button>
          </b-button-toolbar>
          <!-- <b-button-toolbar v-show="formStatusRef !== FormStatusEnum.INFO" class="float-right mt-5">
                        <b-button size="sm" variant="outline-secondary" v-promise-btn="{ promise: dataPromise }" @click="submitForm">儲存 </b-button>
                        <b-button size="sm" variant="outline-secondary" @click="reset">重設</b-button>
                        <b-button size="sm" variant="outline-secondary" @click="changeFormStatus(FormStatusEnum.INFO)">取消</b-button>
                    </b-button-toolbar> -->
        </div>
      </div>
    </div>
    <!-- 教師資訊 -->
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          教師資訊
        </h5>
      </div>
      <div class="card-body">
        <eng22Teacher :teachers="teachers" :key="form.ID" @saveTeacherData="saveTeacherData"> </eng22Teacher>
      </div>
    </div>
    <!-- 詳細課程資訊 -->
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          詳細課程
        </h5>
      </div>
      <div class="card-body">
        <eng22courseD :teachers="teachers" :key="form.ID" :courseDetailArr="courseDetailArr" @saveCourseDetailData="saveCourseDetailData">
        </eng22courseD>
      </div>
    </div>
    <b-button-toolbar class="float-right mt-5">
      <b-button size="sm" variant="outline-secondary">作廢</b-button>
      <b-button size="sm" @click="sendAllData" variant="outline-secondary">儲存</b-button>
      <b-button size="sm" @click="toQueryView" variant="outline-secondary">返回列表</b-button>
    </b-button-toolbar>
    <!-- 放modal假裝送data -->
    <b-modal id="pretend-send-data-modal" hide-footer>
      <template> Using <code>$bvModal</code> Methods </template>
      <div class="d-block text-center">
        send data!!
        <h3>form:</h3>
        {{ form }}
        <br />
        <h3>courseDetailArr:</h3>
        {{ courseDetailArr }}
      </div>
      <b-button class="mt-3" block @click="$bvModal.hide('pretend-send-data-modal')">Close Me</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { Ref, ref, reactive, computed, onActivated, watch } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { email, required } from '@/shared/validators';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import eng22Teacher from '@/components/eng/eng-ad-22/eng22-teacher.vue';
import eng22courseD from '@/components/eng/eng-ad-22/eng22-courseD.vue';
import eng22courseHeadInfo from '@/components/eng/eng-ad-22/eng22-courseHeaderInfo.vue';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import { useBvModal } from '@/shared/modal';
export default {
  name: 'engAd22EditInfo',
  components: { eng22Teacher, eng22courseD, eng22courseHeadInfo },
  props: {
    course_H: {
      required: false,
    },
    formStatus: {
      required: false,
    },
  },
  setup(props, context) {
    let isReload = ref(false);
    const $bvModal = useBvModal(); //modal物件
    enum FormStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }
    // const formStatusRef: Ref<FormStatusEnum> = ref(FormStatusEnum.MODIFY);

    const toQueryView = () => {
      context.emit('changeComponent', 'engAd22Query', isReload.value);
    };

    const formStatusRefOrigin = computed(() => props.formStatus);
    //傳入的course
    const course_H = computed(() => props.course_H);

    //如果有傳參數進來就把default值設定成傳進來的
    onActivated(() => {
      // console.log('ad22 edit info', course_H);
      // console.log('formStatusRefOrigin', formStatusRefOrigin.value);
      if (course_H.value) {
        Object.assign(courseH_Default, course_H.value);
        //    console.log(courseH_Default);
        Object.assign(form, courseH_Default);
        form.date_Start = parseRocDate('110/01/01');
        // console.log($v);
      }
      switch (formStatusRefOrigin.value) {
        case FormStatusEnum.INFO:
          formStatusRef.value = FormStatusEnum.INFO;
          initOriginTeacherAndCourseData('123');
          break;
        case FormStatusEnum.CREATE:
          formStatusRef.value = FormStatusEnum.CREATE;
          clearDataFromKeepAlive();
          break;
      }
    });
    //放教師的data用的
    let teachers = ref([]);
    //放couseDetail的data用的
    let courseDetailArr = ref([]);

    let formStatusRef = ref();

    function generateRandomID() {
      var S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
      };
      return S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4();
    }

    const newcourseH_Default = {
      ID: '',
      companyName: '',
      belongOf: '',
      name: '',
      createDate: new Date(),
      date_Start: undefined,
      date_End: undefined,
      UploadDeadline: '',
      contact_Name: '',
      contact_Tel: '',
      contact_Mail: '',
      webUrl: '',
      remark: '',
      docNo: '',
      place: '',
      status: undefined,
      CheckStaff_1: '',
      CheckStatus_1: '',
      CheckDate_1: undefined,
      CheckStaff_2: '',
      CheckStatus_2: '',
      CheckDate_2: undefined,
    };

    let courseH_Default = {
      ID: '',
      companyName: '',
      belongOf: '',
      name: '',
      createDate: new Date(),
      date_Start: undefined,
      date_End: undefined,
      UploadDeadline: '',
      contact_Name: '',
      contact_Tel: '',
      contact_Mail: '',
      webUrl: '',
      remark: '',
      docNo: '',
      place: '',
      status: undefined,
      CheckStaff_1: '',
      CheckStatus_1: '',
      CheckDate_1: undefined,
      CheckStaff_2: '',
      CheckStatus_2: '',
      CheckDate_2: undefined,
    };
    const courseRules = {
      ID: {},
      companyName: {},
      belongOf: { required: required },
      name: {},
      createDate: {},
      date_Start: {},
      date_End: {},
      UploadDeadline: {},
      contact_Name: {},
      contact_Tel: {},
      contact_Mail: { email: email },
      webUrl: {},
      remark: {},
      docNo: {},
      place: {},
      status: {},
      CheckStaff_1: {},
      CheckStatus_1: {},
      CheckDate_1: {},
      CheckStaff_2: {},
      CheckStatus_2: {},
      CheckDate_2: {},
    };
    let form = reactive(Object.assign({}, courseH_Default));
    const { $v, checkValidity, reset } = useValidation(courseRules, form, courseH_Default);
    const belongUnit = ref(['顧問公司', '公會', '政府組織', '全聯會', '顧問公會']);

    function initOriginTeacherAndCourseData(courseHeaderID) {
      // console.log('到時候要用ID:', courseHeaderID, '去撈data');
      //初始化放data的地方
      teachers.value = [];
      courseDetailArr.value = [];
      //先寫死要撈的teacher data
      let t1 = {
        name: '顏玉明',
        highEdu: '英國曼徹斯特大學營建工程及法律博士',
        job: '政治大學法律科際整合研究所教授',
        experience: '寰瀛法律事務所律師  國立中興大學科技法律研究所助理教授',
      };
      let t2 = {
        name: '黃立',
        highEdu: '奧國維也納大學法學博士',
        job: '國立政治大學法律系教授兼法學碩士在職專班執行長',
        experience: '政大法律系系主任、政大法學院院長',
      };
      teachers.value.push(t1);
      teachers.value.push(t2);
      //再寫死要撈的course data
      //放courseDetail
      let cd1 = {
        ID: '123',
        name: '工程法律',
        itemNo: 1,
        date: new Date('2021/11/01'),
        time_Start: '08:00',
        time_End: '11:00',
        subjectID: '01,02,03,04',
        point: '30',
        teacher: t1,
        law: 1,
        modifyId: '',
        modifyDate: '',
      };
      let cd2 = {
        ID: '123',
        name: '台灣工程承攬契約中情勢變更之適用問題',
        itemNo: 2,
        date: new Date('2021/11/01'),
        time_Start: '13:00',
        time_End: '15:00',
        subjectID: '01',
        point: '30',
        law: 1,
        teacher: t2,
        modifyId: '',
        modifyDate: '',
      };
      courseDetailArr.value.push(cd1);
      courseDetailArr.value.push(cd2);
      // console.log('teachers:', teachers.value);
      // console.log('courseDetailArr:', courseDetailArr.value);
    }

    function clearDataFromKeepAlive() {
      courseH_Default = Object.assign(courseH_Default, newcourseH_Default);
      courseH_Default.ID = generateRandomID();
      Object.keys(form).forEach(key => {
        form[key] = courseH_Default[key];
      });
      teachers.value = [];
      courseDetailArr.value = [];
    }

    function sendAllData() {
      $bvModal.show('pretend-send-data-modal');
      // console.log('all data coureHeader:', form);
      // console.log('all data courseDetail:', courseDetailArr.value);
    }

    function saveTeacherData(teacherDataArr) {
      teachers.value = teacherDataArr;
    }

    function saveCourseDetailData(courseDetailArrData) {
      // console.log('father componemt', courseDetailArrData);
      courseDetailArr.value = courseDetailArrData;
    }
    function changeStatusToEdit() {
      window.scrollTo(0, 0);
      formStatusRef.value = FormStatusEnum.MODIFY;
    }
    function changeStatusToInfo() {
      // window.scrollTo(0,0);
      formStatusRef.value = FormStatusEnum.INFO;
    }
    return {
      FormStatusEnum,
      formStatusRef,
      form,
      teachers,
      courseDetailArr,
      belongUnit,
      $v,
      validateState,
      saveTeacherData,
      toQueryView,
      changeStatusToEdit,
      changeStatusToInfo,
      saveCourseDetailData,
      formatDate,
      sendAllData,
    };
  },
};
</script>

<style>
.red-text {
  color: red;
  /* font-weight: bold; */
}
.label {
  background-color: #737373;
  font-weight: bolder;
  color: white;
  padding-top: 5px;
  padding-bottom: 5px;
}
.form-group {
  height: 3.5rem;
}

.little-font {
  font-size: 0.5em;
}
.error-message {
  height: 24px;
}
</style>
