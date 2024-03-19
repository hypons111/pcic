<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            基本資料 {{ formStatusRef }}
          </h5>
        </div>
        <div class="card-body">
          <div class="col-12 mb-2 label">個人資料</div>
          <b-form-row>
            <div class="col-12 mb-2 label">個人資料</div>
            <b-col cols="12">
              <div class="mb-2 label">個人資料</div>
            </b-col>
          </b-form-row>
          <b-form-row>
            <b-col cols="3">
              <b-form-group
                class="b-form-group-include-feedback"
                label="姓名"
                label-class="text-right b-form-group-label"
                label-cols="5"
                content-cols="7"
                :state="validateState($v.name)"
              >
                <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.name.$model }}</div>
                <b-form-input
                  v-if="formStatusRef === formStatusEnum.MODIFY"
                  v-model="$v.name.$model"
                  :state="validateState($v.name)"
                  trim
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">{{
                  error.$message
                }}</b-form-invalid-feedback>
              </b-form-group>
            </b-col>

            <b-col cols="3">
              <b-form-group
                class="b-form-group-include-feedback"
                label="身分證字號"
                label-class="text-right b-form-group-label"
                label-cols="5"
                content-cols="7"
                :state="validateState($v.IDNumber)"
              >
                <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.IDNumber.$model }}</div>
                <b-form-input
                  v-if="formStatusRef === formStatusEnum.MODIFY"
                  v-model="$v.IDNumber.$model"
                  :state="validateState($v.IDNumber)"
                  trim
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.IDNumber.$errors" :key="index">{{
                  error.$message
                }}</b-form-invalid-feedback>
              </b-form-group>
            </b-col>

            <b-col cols="3">
              <b-form-group
                class="b-form-group-include-feedback"
                label="出生日期"
                label-class="text-right b-form-group-label"
                label-cols="5"
                content-cols="7"
                :state="validateState($v.birthday)"
              >
                <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ formatDate($v.birthday.$model, '/') }}</div>
                <i-date-picker
                  v-if="formStatusRef === formStatusEnum.MODIFY"
                  v-model="$v.birthday.$model"
                  :state="validateState($v.birthday)"
                ></i-date-picker>
                <b-form-invalid-feedback v-for="(error, index) of $v.birthday.$errors" :key="index">{{
                  error.$message
                }}</b-form-invalid-feedback>
              </b-form-group>
            </b-col>

            <b-col cols="3">
              <b-form-group
                class="b-form-group-include-feedback"
                label="性別"
                label-class="text-right b-form-group-label"
                label-cols="5"
                content-cols="7"
                :state="validateState($v.sex)"
              >
                <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ formOptionsFormatter('sex', $v.sex.$model).text }}</div>
                <b-form-radio-group
                  v-if="formStatusRef === formStatusEnum.MODIFY"
                  v-model="$v.sex.$model"
                  :options="formOptions.sex"
                  button-variant="outline-info"
                  size="md"
                  buttons
                ></b-form-radio-group>
                <b-form-invalid-feedback v-for="(error, index) of $v.sex.$errors" :key="index">{{
                  error.$message
                }}</b-form-invalid-feedback>
              </b-form-group>
            </b-col>
          </b-form-row>

          <b-form-row>
            <b-form-group
              class="col-3 b-form-group-include-feedback"
              label="姓名"
              label-class="b-form-group-label"
              label-align="right"
              label-cols="5"
              content-cols="7"
              :state="validateState($v.name)"
            >
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.name.$model }}</div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.name.$model"
                :state="validateState($v.name)"
                trim
              ></b-form-input>
              <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">{{ error.$message }}</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group
              class="col-3 b-form-group-include-feedback"
              label="身分證字號"
              label-class="b-form-group-label"
              label-align="right"
              label-cols="5"
              content-cols="7"
              :state="validateState($v.IDNumber)"
            >
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.IDNumber.$model }}</div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.IDNumber.$model"
                :state="validateState($v.IDNumber)"
                trim
              ></b-form-input>
              <b-form-invalid-feedback v-for="(error, index) of $v.IDNumber.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group
              class="col-3 b-form-group-include-feedback"
              label="出生日期"
              label-class="b-form-group-label"
              label-align="right"
              label-cols="5"
              content-cols="7"
              :state="validateState($v.birthday)"
            >
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ formatDate($v.birthday.$model, '/') }}</div>
              <i-date-picker
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.birthday.$model"
                :state="validateState($v.birthday)"
              ></i-date-picker>
              <b-form-invalid-feedback v-for="(error, index) of $v.birthday.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group
              class="col-3 b-form-group-include-feedback"
              label="性別"
              label-class="b-form-group-label"
              label-align="right"
              label-cols="5"
              content-cols="7"
              :state="validateState($v.sex)"
            >
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ formOptionsFormatter('sex', $v.sex.$model).text }}</div>
              <b-form-radio-group
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.sex.$model"
                :options="formOptions.sex"
                button-variant="outline-info"
                size="md"
                buttons
              ></b-form-radio-group>
              <b-form-invalid-feedback v-for="(error, index) of $v.sex.$errors" :key="index">{{ error.$message }}</b-form-invalid-feedback>
            </b-form-group>
          </b-form-row>

          <!--          b-form-group 佔整頁不必用 b-form-row 及 b-col cols="12" 包住-->
          <b-form-row>
            <b-col cols="12">
              <b-form-group
                class="b-form-group-include-feedback"
                label="姓名"
                label-class="pl-1 b-form-group-label"
                label-cols="5"
                content-cols="7"
                :state="validateState($v.name)"
              >
                <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.name.$model }}</div>
                <b-form-input
                  v-if="formStatusRef === formStatusEnum.MODIFY"
                  v-model="$v.name.$model"
                  :state="validateState($v.name)"
                  trim
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">{{
                  error.$message
                }}</b-form-invalid-feedback>
              </b-form-group>
            </b-col>
          </b-form-row>

          <!--          上面與下面沒包住的效果相同-->
          <b-form-group
            class="b-form-group-include-feedback"
            label="姓名"
            label-class="pl-1 b-form-group-label"
            label-cols="5"
            content-cols="7"
            :state="validateState($v.name)"
          >
            <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.name.$model }}</div>
            <b-form-input
              v-if="formStatusRef === formStatusEnum.MODIFY"
              v-model="$v.name.$model"
              :state="validateState($v.name)"
              trim
            ></b-form-input>
            <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">{{ error.$message }}</b-form-invalid-feedback>
          </b-form-group>

          <b-row class="ml-0 mb-2 align-items-start">
            <b-col cols="2" class="text-right label">
              <div>個人資料</div>
            </b-col>
            <b-col cols="10">
              <b-row>
                <b-form-group
                  class="col-4 b-form-group-include-feedback"
                  label="姓名"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.name)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.name.$model }}</div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    v-model="$v.name.$model"
                    :state="validateState($v.name)"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>

                <b-form-group
                  class="col-3 b-form-group-include-feedback"
                  label="身分證字號"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.IDNumber)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.IDNumber.$model }}</div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    v-model="$v.IDNumber.$model"
                    :state="validateState($v.IDNumber)"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback v-for="(error, index) of $v.IDNumber.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>

                <b-form-group
                  class="col-3 b-form-group-include-feedback"
                  label="出生日期"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.birthday)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ formatDate($v.birthday.$model, '/') }}</div>
                  <i-date-picker
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    v-model="$v.birthday.$model"
                    :state="validateState($v.birthday)"
                  ></i-date-picker>
                  <b-form-invalid-feedback v-for="(error, index) of $v.birthday.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>

                <b-form-group
                  class="col-2 b-form-group-include-feedback"
                  label="性別"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.sex)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ formOptionsFormatter('sex', $v.sex.$model).text }}</div>
                  <b-form-radio-group
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    v-model="$v.sex.$model"
                    :options="formOptions.sex"
                    button-variant="outline-info"
                    size="md"
                    buttons
                  ></b-form-radio-group>
                  <b-form-invalid-feedback v-for="(error, index) of $v.sex.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>
              </b-row>

              <b-row>
                <b-form-group
                  class="col-8 b-form-group-include-feedback"
                  label="通訊地址"
                  label-for="city"
                  label-class="pl-1 b-form-group-label"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.address.$model }}</div>
                  <b-form-row v-if="formStatusRef === formStatusEnum.MODIFY">
                    <b-col cols="3">
                      <b-form-select
                        id="city"
                        v-model="$v.city.$model"
                        :options="formOptions.city"
                        @input="changeDist($v.city.$model)"
                        :state="validateState($v.city)"
                      ></b-form-select>
                      <b-form-invalid-feedback v-for="(error, index) of $v.city.$errors" :key="index">
                        {{ error.$message }}
                      </b-form-invalid-feedback>
                    </b-col>
                    <b-col cols="3">
                      <b-form-select
                        v-model="$v.dist.$model"
                        :options="formOptions.dist"
                        :disabled="!$v.city.$model"
                        :state="validateState($v.dist)"
                      ></b-form-select>
                      <b-form-invalid-feedback v-for="(error, index) of $v.dist.$errors" :key="index">
                        {{ error.$message }}
                      </b-form-invalid-feedback>
                    </b-col>
                    <b-col cols="6">
                      <b-form-input v-model="$v.road.$model" :state="validateState($v.road)" trim></b-form-input>
                      <b-form-invalid-feedback v-for="(error, index) of $v.road.$errors" :key="index">
                        {{ error.$message }}
                      </b-form-invalid-feedback>
                    </b-col>
                  </b-form-row>
                </b-form-group>

                <b-form-group
                  class="col-4 b-form-group-include-feedback"
                  label="電子信箱"
                  label-for="email"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.email)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.email.$model }}</div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    id="email"
                    v-model="$v.email.$model"
                    :state="validateState($v.email)"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback v-for="(error, index) of $v.email.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>
              </b-row>

              <b-row>
                <b-form-group
                  class="col-12 b-form-group-include-feedback"
                  label="連絡電話"
                  label-for="Tel"
                  label-class="pl-1 pb-0 mb-2 b-form-group-label"
                >
                  <b-form-row>
                    <b-col cols="1">
                      <div class="text-right">（公）</div>
                    </b-col>
                    <b-col cols="2">
                      <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.companyTel.$model }}</div>
                      <b-form-input
                        v-if="formStatusRef === formStatusEnum.MODIFY"
                        v-model="$v.companyTel.$model"
                        :state="validateState($v.companyTel)"
                        trim
                      ></b-form-input>
                      <b-form-invalid-feedback v-for="(error, index) of $v.companyTel.$errors" :key="index">
                        {{ error.$message }}
                      </b-form-invalid-feedback>
                    </b-col>

                    <!--                    <b-form-group-->
                    <!--                      class="col-3"-->
                    <!--                      label="（公）"-->
                    <!--                      label-align="right"-->
                    <!--                      label-cols="4"-->
                    <!--                      content-cols="8"-->
                    <!--                      label-for="companyTel"-->
                    <!--                      :state="validateState($v.companyTel)"-->
                    <!--                    >-->
                    <!--                      <div class=""-->
                    <!--                        v-if="formStatusRef === formStatusEnum.INFO">{{ $v.companyTel.$model }}</div>-->
                    <!--                      <b-form-input-->
                    <!--                        id="companyTel"-->
                    <!--                        v-if="formStatusRef === formStatusEnum.MODIFY"-->
                    <!--                        v-model="$v.companyTel.$model"-->
                    <!--                        :state="validateState($v.companyTel)"-->
                    <!--                        trim-->
                    <!--                      ></b-form-input>-->
                    <!--                      <b-form-invalid-feedback v-for="(error, index) of $v.companyTel.$errors" :key="index">-->
                    <!--                        {{ error.$message }}-->
                    <!--                      </b-form-invalid-feedback>-->
                    <!--                    </b-form-group>-->

                    <b-col cols="1">
                      <div class="text-right">（住）</div>
                    </b-col>
                    <b-col cols="2">
                      <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.homeTel.$model }}</div>
                      <b-form-input
                        v-if="formStatusRef === formStatusEnum.MODIFY"
                        v-model="$v.homeTel.$model"
                        :state="validateState($v.homeTel)"
                        trim
                      ></b-form-input>
                      <b-form-invalid-feedback v-for="(error, index) of $v.homeTel.$errors" :key="index">
                        {{ error.$message }}
                      </b-form-invalid-feedback>
                    </b-col>

                    <b-col cols="1">
                      <div class="text-right">（傳真）</div>
                    </b-col>
                    <b-col cols="2">
                      <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.fax.$model }}</div>
                      <b-form-input
                        v-if="formStatusRef === formStatusEnum.MODIFY"
                        v-model="$v.fax.$model"
                        :state="validateState($v.fax)"
                        trim
                      ></b-form-input>
                      <b-form-invalid-feedback v-for="(error, index) of $v.fax.$errors" :key="index">
                        {{ error.$message }}
                      </b-form-invalid-feedback>
                    </b-col>

                    <b-col cols="1">
                      <div class="text-right">（手機）</div>
                    </b-col>
                    <b-col cols="2">
                      <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.mobilePhone.$model }}</div>
                      <b-form-input
                        v-if="formStatusRef === formStatusEnum.MODIFY"
                        v-model="$v.mobilePhone.$model"
                        :state="validateState($v.mobilePhone)"
                        trim
                      ></b-form-input>
                      <b-form-invalid-feedback v-for="(error, index) of $v.mobilePhone.$errors" :key="index">
                        {{ error.$message }}
                      </b-form-invalid-feedback>
                    </b-col>
                  </b-form-row>
                </b-form-group>
              </b-row>
            </b-col>
          </b-row>

          <b-row class="ml-0 mb-2 align-items-start">
            <b-col cols="2" class="text-right label">
              <div>學歷(畢業年次)</div>
            </b-col>
            <b-col cols="10">
              <b-row>
                <b-form-group
                  class="col-6 b-form-group-include-feedback"
                  label="應考學歷"
                  label-for="examEducation"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.examEducation)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.examEducation.$model }}</div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    id="examEducation"
                    v-model="$v.examEducation.$model"
                    :state="validateState($v.examEducation)"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback v-for="(error, index) of $v.examEducation.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>

                <b-form-group
                  class="col-6 b-form-group-include-feedback"
                  label="最高學歷"
                  label-for="highestEducation"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.highestEducation)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.highestEducation.$model }}</div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    id="highestEducation"
                    v-model="$v.highestEducation.$model"
                    :state="validateState($v.highestEducation)"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback v-for="(error, index) of $v.highestEducation.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>
              </b-row>
            </b-col>
          </b-row>

          <b-row class="ml-0 mb-2 align-items-start">
            <b-col cols="2" class="text-right label">
              <div>第一階段考試及格或免試證明</div>
            </b-col>
            <b-col cols="10">
              <b-row>
                <b-form-group
                  class="col-6 b-form-group-include-feedback"
                  label="日期"
                  label-for="certificateDate"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.certificateDate)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.certificateDate.$model }}</div>
                  <i-date-picker
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    id="certificateDate"
                    v-model="$v.certificateDate.$model"
                    :state="validateState($v.certificateDate)"
                  ></i-date-picker>
                  <b-form-invalid-feedback v-for="(error, index) of $v.certificateDate.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>

                <b-form-group
                  class="col-6 b-form-group-include-feedback"
                  label="證號"
                  label-for="certificateID"
                  label-class="pl-1 b-form-group-label"
                  :state="validateState($v.certificateID)"
                >
                  <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.certificateID.$model }}</div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.MODIFY"
                    id="certificateID"
                    v-model="$v.certificateID.$model"
                    :state="validateState($v.certificateID)"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback v-for="(error, index) of $v.certificateID.$errors" :key="index">{{
                    error.$message
                  }}</b-form-invalid-feedback>
                </b-form-group>
              </b-row>
            </b-col>
          </b-row>

          <b-row class="ml-0 align-items-center">
            <b-col cols="2" class="text-right label">
              <div>工作屬性</div>
            </b-col>
            <b-col cols="10">
              <div v-if="formStatusRef === formStatusEnum.INFO">{{ formOptionsFormatter('jobType', $v.jobType.$model).text }}</div>
              <b-form-select
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.jobType.$model"
                :options="formOptions.jobType"
                :state="validateState($v.jobType)"
              ></b-form-select>
            </b-col>
          </b-row>
          <b-row class="ml-0 mb-2 feedback-row">
            <b-col offset="2">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.jobType.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-row class="ml-0 align-items-center">
            <b-col cols="2" class="text-right label">
              <div>希望待遇</div>
            </b-col>
            <b-col cols="10">
              <div v-if="formStatusRef === formStatusEnum.INFO">{{ formOptionsFormatter('salary', $v.salary.$model).text }}</div>
              <b-form-select
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.salary.$model"
                :options="formOptions.salary"
                :state="validateState($v.salary)"
              ></b-form-select>
            </b-col>
          </b-row>
          <b-row class="ml-0 mb-2 feedback-row">
            <b-col offset="2">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.salary.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-row class="ml-0 align-items-start">
            <b-col cols="2" class="text-right label">
              <div>希望工作地點</div>
            </b-col>
            <b-col cols="10" class="">
              <b-row>
                <b-col cols="2" v-for="option in formOptions.city.slice(1)" :key="option.value">
                  <b-form-checkbox
                    :disabled="formStatusRef === formStatusEnum.INFO"
                    :value="option.value"
                    v-model="$v.workplaces.$model"
                    :state="validateState($v.workplaces)"
                    >{{ option.text }}</b-form-checkbox
                  >
                </b-col>
              </b-row>
              <!--              <div v-for="option in formOptions.city" :key="option.value" class="col-2 d-inline-block">-->
              <!--                <b-form-checkbox-->
              <!--                  :disabled="formStatusRef === formStatusEnum.INFO"-->
              <!--                  :value="option.value"-->
              <!--                  v-model="$v.workplaces.$model"-->
              <!--                  :state="validateState($v.workplaces)"-->
              <!--                >{{ option.text }}</b-form-checkbox>-->
              <!--              </div>-->
            </b-col>
          </b-row>
          <b-row class="ml-0 mb-2 feedback-row">
            <b-col offset="2">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.workplaces.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-row class="ml-0 mb-2 align-items-start">
            <b-col cols="2" class="text-right label">
              <div>工作需求</div>
            </b-col>
            <b-col cols="10">
              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.workDemands.$model }}</div>
              <b-form-textarea
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.workDemands.$model"
                rows="4"
                trim
              ></b-form-textarea>
            </b-col>
          </b-row>

          <b-row class="ml-0 mb-2 align-items-start">
            <b-col cols="2" class="text-right label">
              <div>備註</div>
            </b-col>
            <b-col cols="10">
              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.remark.$model }}</div>
              <b-form-textarea v-if="formStatusRef === formStatusEnum.MODIFY" v-model="$v.remark.$model" rows="4" trim></b-form-textarea>
            </b-col>
          </b-row>

          <b-row class="ml-0 mb-2 align-items-center">
            <b-col cols="2" class="text-right label">
              <div>開放求才單位查詢</div>
            </b-col>
            <b-col cols="10">
              <b-form-radio-group
                :disabled="formStatusRef === formStatusEnum.INFO"
                :options="formOptions.publicStatus"
                v-model="$v.publicStatus.$model"
              ></b-form-radio-group>
            </b-col>
          </b-row>

          <b-button-toolbar v-show="formStatusRef === formStatusEnum.INFO" class="float-right mt-5">
            <b-button size="sm" variant="outline-secondary" @click="changeFormStatus(formStatusEnum.MODIFY)">
              <font-awesome-icon :icon="['far', 'edit']" />
              {{ $t('button.modify') }}
            </b-button>
            <b-button size="sm" variant="outline-secondary" @click="">
              <font-awesome-icon :icon="['fas', 'arrow-left']" />
              返回
            </b-button>
          </b-button-toolbar>
          <b-button-toolbar v-show="formStatusRef === formStatusEnum.MODIFY" class="float-right mt-5">
            <b-button size="sm" variant="outline-secondary" v-promise-btn="{ promise: dataPromise }" @click="submitForm">
              <font-awesome-icon :icon="['fas', 'save']" />
              儲存
            </b-button>
            <b-button size="sm" variant="outline-secondary" @click="reset">重設</b-button>
            <b-button size="sm" variant="outline-secondary" @click="changeFormStatus(formStatusEnum.INFO)">
              <font-awesome-icon :icon="['fas', 'undo-alt']" />
              取消
            </b-button>
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onMounted, onActivated, reactive, Ref, ref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import { parseRocDate, formatDate } from '@/shared/date/minguo-calendar-utils';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { statusFormatter } from '@/shared/formatter/common';
import { guildTypeFormatter, statusFormatter } from '@/shared/formatter/common';
import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoMn01EditInfo',
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      MODIFY = 'modify',
    }

    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);

    const formOptions = reactive({
      sex: [
        { value: '1', text: '男' },
        { value: '2', text: '女' },
      ],
      publicStatus: [
        { value: '1', text: '是' },
        { value: '0', text: '否' },
      ],
      jobType: [
        { value: '', text: '不拘' },
        { value: '1', text: '工程技術顧問公司且有合格指導人' },
        { value: '2', text: '技師事務所且有合格指導人' },
        { value: '3', text: '營造業且有合格指導人' },
        { value: '4', text: '政府工程機關且有合格指導人' },
        { value: '5', text: '其他類型機構或無合格指導人' },
      ],
      salary: [
        { value: '', text: '不拘' },
        { value: '1', text: '年薪 40萬(不含)以下' },
        { value: '2', text: '年薪 40萬(含)~55萬(不含)' },
        { value: '3', text: '年薪 55萬(含)~70萬(不含)' },
        { value: '4', text: '年薪 70萬(含)~85萬(不含)' },
        { value: '5', text: '年薪 85萬(含)~100萬(不含)' },
        { value: '6', text: '年薪 100萬(含)以上' },
        { value: '0', text: '面議' },
      ],
      city: [
        { value: '', text: '請選擇' },
        { value: '基隆市', text: '基隆市' },
        { value: '台北市', text: '台北市' },
        { value: '新北市', text: '新北市' },
        { value: '桃園市', text: '桃園市' },
        { value: '新竹縣', text: '新竹縣' },
        { value: '新竹市', text: '新竹市' },
        { value: '苗栗縣', text: '苗栗縣' },
        { value: '台中市', text: '台中市' },
        { value: '南投縣', text: '南投縣' },
        { value: '彰化縣', text: '彰化縣' },
        { value: '雲林縣', text: '雲林縣' },
        { value: '嘉義縣', text: '嘉義縣' },
        { value: '嘉義市', text: '嘉義市' },
        { value: '台南市', text: '台南市' },
        { value: '高雄市', text: '高雄市' },
        { value: '屏東縣', text: '屏東縣' },
        { value: '台東縣', text: '台東縣' },
        { value: '花蓮縣', text: '花蓮縣' },
        { value: '宜蘭縣', text: '宜蘭縣' },
        { value: '澎湖縣', text: '澎湖縣' },
        { value: '金門縣', text: '金門縣' },
        { value: '連江縣', text: '連江縣' },
      ],
      dist: [],
      distList: [
        { city: '台北市', text: '中正區', value: '中正區' },
        { city: '台北市', text: '大同區', value: '大同區' },
        { city: '台北市', text: '中山區', value: '中山區' },
        { city: '台北市', text: '松山區', value: '松山區' },
        { city: '台北市', text: '大安區', value: '大安區' },
        { city: '台北市', text: '萬華區', value: '萬華區' },
        { city: '台北市', text: '信義區', value: '信義區' },
        { city: '台北市', text: '士林區', value: '士林區' },
        { city: '台北市', text: '北投區', value: '北投區' },
        { city: '台北市', text: '內湖區', value: '內湖區' },
        { city: '台北市', text: '南港區', value: '南港區' },
        { city: '台北市', text: '文山區', value: '文山區' },
        { city: '新北市', text: '板橋區', value: '板橋區' },
        { city: '新北市', text: '中和區', value: '中和區' },
        { city: '新北市', text: '永和區', value: '永和區' },
        { city: '新北市', text: '土城區', value: '土城區' },
        { city: '新北市', text: '三峽區', value: '三峽區' },
        { city: '新北市', text: '鶯歌區', value: '鶯歌區' },
        { city: '新北市', text: '樹林區', value: '樹林區' },
        { city: '新北市', text: '三重區', value: '三重區' },
        { city: '新北市', text: '蘆洲區', value: '蘆洲區' },
        { city: '新北市', text: '新莊區', value: '新莊區' },
        { city: '新北市', text: '五股區', value: '五股區' },
        { city: '新北市', text: '泰山區', value: '泰山區' },
        { city: '新北市', text: '林口區', value: '林口區' },
        { city: '新北市', text: '八里區', value: '八里區' },
        { city: '新北市', text: '淡水區', value: '淡水區' },
        { city: '新北市', text: '三芝區', value: '三芝區' },
        { city: '新北市', text: '石門區', value: '石門區' },
        { city: '新北市', text: '金山區', value: '金山區' },
        { city: '新北市', text: '萬里區', value: '萬里區' },
        { city: '新北市', text: '汐止區', value: '汐止區' },
        { city: '新北市', text: '瑞芳區', value: '瑞芳區' },
        { city: '新北市', text: '貢寮區', value: '貢寮區' },
        { city: '新北市', text: '平溪區', value: '平溪區' },
        { city: '新北市', text: '雙溪區', value: '雙溪區' },
        { city: '新北市', text: '新店區', value: '新店區' },
        { city: '新北市', text: '深坑區', value: '深坑區' },
        { city: '新北市', text: '坪林區', value: '坪林區' },
        { city: '新北市', text: '石碇區', value: '石碇區' },
      ],
    });

    onMounted(() => {
      const data = {
        name: '咚咚', //姓名
        IDNumber: 'G123456789', //身分證字號
        sex: '1', //性別
        // birthday: '088/09/03', //生日
        birthday: parseRocDate('088/09/03', '/'), //生日
        address: '新北市@板橋區@中山路一段161號22樓', //地址
        city: '', // 縣市
        dist: '', // 鄉鎮
        road: '', // 路
        email: '', //信箱
        companyTel: '02-22222535', //公司電話
        homeTel: '', //住家電話
        fax: '', //傳真
        mobilePhone: '0987654321', //手機
        examEducation: '', //應考學歷
        highestEducation: '大學', //最高學歷
        certificateDate: '', //第一階段考試及格或免試證明-日期
        certificateID: '', //第一階段考試及格或免試證明-證號
        publicStatus: '1', //開放求才單位查詢
        jobType: '3', //工作屬性
        salary: '4', //待遇
        workplaces: ['基隆市', '宜蘭縣'], //工作地點
        workDemands:
          '後備軍人原本是每2年召訓一次，明年起將局部試行「1年1召、訓期14天」，以「後退先用」為原則，預估1萬5000人將接受這次教召。' +
          '本月1日起，民眾可上後備軍人網路服務台網站，輸入身分證字號、出生日期以及戶籍縣市就可以查詢最新教召資訊。第一批新制教召將於明年2、3月實施，' +
          '北中南各有一個營兵力，主要課目為射擊訓練與戰鬥教練，部隊分批實施射擊、行軍等課程。\n' +
          '\n' +
          '國防部全民防衛動員室日前說明，為降低對後備軍人職場工作之壓力，保障接受召集之相關權益，擬訂各項配套措施，藉以鼓勵後備軍人積極參與召集訓練，' +
          '並爭取企業支持。例如放寛企業核免條件為兼顧國家安全及減少雇主負擔，經常僱用員工數20人以下之中小企業，有2人以上員工同時接受召集，有影響營運之虞，' +
          '經擇定半數以下員工，可憑相關證明，辦理免除本次召集。', //工作需求
        remark: '', //備註
      };
      Object.assign(formDefault, data);
      setCityAndDistAndRoad();
      Object.assign(form, formDefault);
    });

    // onActivated(() => {
    //   if (cmsCompProp.value) {
    //     Object.assign(formDefault, cmsCompProp.value);
    //     Object.assign(form, formDefault);
    //   } else {
    //     Object.assign(formDefault, formEmpty);
    //     Object.assign(form, formDefault);
    //   }
    //   formStatusRef.value = formStatus.value.value;
    // });

    const setCityAndDistAndRoad = () => {
      const addressArray = formDefault.address.split('@');
      changeDist(addressArray[0]);
      formDefault.city = addressArray[0];
      formDefault.dist = addressArray[1];
      formDefault.road = addressArray[2];
      formDefault.address = formDefault.address.replace(/@/g, '');
    };

    const changeFormStatus = (status: formStatusEnum) => {
      if (status === formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    let citySelect = reactive(Object.assign({}, formOptions.city));
    // citySelect.unshift({value: '', text: '請選擇'});

    const changeDist = (city: string) => {
      const distPick = formOptions.distList.filter(item => item.city === city);
      formOptions.dist.splice(0, formOptions.dist.length, ...distPick);
      formOptions.dist.unshift({ value: '', text: '請選擇' });
    };

    let formDefault = {
      name: '', //姓名
      IDNumber: '', //身分證字號
      sex: '', //性別
      birthday: '', //生日
      address: '', //地址
      city: '', // 縣市
      dist: '', // 鄉鎮
      road: '', // 路
      email: '', //信箱
      companyTel: '', //公司電話
      homeTel: '', //住家電話
      fax: '', //傳真
      mobilePhone: '', //手機
      examEducation: '', //應考學歷
      highestEducation: '', //最高學歷
      certificateDate: '', //第一階段考試及格或免試證明-日期
      certificateID: '', //第一階段考試及格或免試證明-證號
      publicStatus: '', //開放求才單位查詢
      jobType: '', //工作屬性
      salary: '', //待遇
      workplaces: [], //工作地點
      workDemands: '', //工作需求
      remark: '', //備註
    };

    let form = reactive(Object.assign({}, formDefault));

    const rules = {
      name: { notNull: required }, //姓名
      IDNumber: {}, //身分證字號
      sex: {}, //性別
      birthday: { notNull: required }, //生日
      address: { notNull: required }, //地址
      city: { notNull: required }, // 縣市
      dist: { notNull: required }, // 鄉鎮
      road: { notNull: required }, // 路
      email: { notNull: required, email: email }, //信箱
      companyTel: {}, //公司電話
      homeTel: {}, //住家電話
      fax: {}, //傳真
      mobilePhone: { notNull: required }, //手機
      examEducation: {}, //應考學歷
      highestEducation: { notNull: required }, //最高學歷
      certificateDate: {}, //第一階段考試及格或免試證明-日期
      certificateID: {}, //第一階段考試及格或免試證明-證號
      publicStatus: {}, //開放求才單位查詢
      jobType: { notNull: required }, //工作屬性
      salary: { notNull: required }, //待遇
      workplaces: { notNull: required }, //工作地點
      workDemands: {}, //工作需求
      remark: {}, //備註
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              setFormAddress();
              putForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const setFormAddress = () => {
      form.address = form.city + '@' + form.dist + '@' + form.road;
    };

    const putForm = () => {
      let url: string;
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/cms-comps/xxx`;
      }
      // dataPromise.value = axios
      //   .put(url, form)
      //   .then(({ data }) => {
      changeFormStatus(formStatusEnum.INFO);
      // createDefaultValue(data);
      notificationService.success(`${form.name}:修改成功`);
      // })
      // .finally(() => (dataPromise.value = null))
      // .catch(notificationErrorHandler(notificationService));

      Object.assign(formDefault, form);
    };

    // const createDefaultValue = (data: any) => {
    //   Object.assign(formDefault, data);
    //   Object.assign(form, formDefault);
    // };

    const formOptionsFormatter = (option: string, value: string) =>
      formOptions[option].find(function (item) {
        if (item.value === value) {
          return item.text; //為什還是傳出物件???
        }
      });
    // const formOptionsFormatter = (option: string, value: string) => formOptions[option].find((item) => item.value === value);

    return {
      formStatusEnum,
      formStatusRef,
      formOptions,
      dataPromise,
      changeFormStatus,
      citySelect,
      changeDist,
      $v,
      validateState,
      reset,
      submitForm,
      formOptionsFormatter,
      formatDate,
    };
  },
};
</script>
<style>
.red-text {
  color: red;
  font-weight: bold;
}
.label {
  background-color: #737373;
  font-weight: bolder;
  color: white;
  padding: 7px;
}
.b-form-group-include-feedback {
  margin-bottom: 0;
  height: 4rem;
}
.b-form-group-label {
  background-color: #e5e5e5;
  height: 38px;
}
.mx-datepicker {
  width: 100%;
}
.feedback-row {
  height: 24px;
}
/*.form-row > legend {*/
/*  height: 38px;*/
/*}*/
/*.form-row > legend + div {*/
/*  height: 3.5rem;*/
/*}*/
</style>
