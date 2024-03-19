<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo"
                      :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>

    <section class="container mt-2">
      <div class="card">
        <div></div>
        <div class="card-body col-12">
          <div class="container">

            <!-- 序號 -->
            <b-row>
              <i-form-group-check :label="'序號：'" :labelStar="true" :item="$v.subcoNo">
                <b-form-select v-model="$v.subcoNo.$model" :options=" formOptions.subjectList"
                               :state="validateState($v.subcoNo)"></b-form-select>
              </i-form-group-check>
            </b-row>
            <!-- 分包(協力)廠商 -->
            <!--            <b-row>-->
            <!--              <i-form-group-check :label="'分包(協力)廠商：'" :labelStar="true" :item="$v.subCcut">-->
            <!--                <b-form-input class="text-right" v-model="$v.subCcut.$model" :state="validateState($v.subCcut)" lazy-->
            <!--                              trim></b-form-input>-->
            <!--                <b-button variant="outline-dark">廠商統編查詢</b-button>-->
            <!--              </i-form-group-check>-->
            <!--            </b-row>-->

            <b-form-row>
              <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="'分包(協力)廠商：'"
                                  :item="$v.subCcut" :labelStar="true">
                <b-form-input class="col-2" v-model="$v.subCcut.$model"
                              :state="validateState($v.subCcut)"></b-form-input>
                <b-col class="col-3">
                  <bid-unit10-name :orgId="$v.subCcut.$model"/>
                  <b-button variant="outline-dark" @click="showFirmModal">廠商統編查詢</b-button>
                  <!--                  @click="showOrgaModal"-->
                </b-col>
              </i-form-group-check>
            </b-form-row>

            <!-- 工廠登記編號 -->
            <!--            <b-row>-->
            <!--              <i-form-group-check :label="'工廠登記編號：'" :item="$v.coRegNo">-->
            <!--                <b-form-input class="text-right" v-model="$v.coRegNo.$model" :state="validateState($v.coRegNo)" lazy-->
            <!--                              trim></b-form-input>-->
            <!--                <b-button variant="outline-dark">工廠登記編號查詢</b-button>-->
            <!--              </i-form-group-check>-->
            <!--            </b-row>-->

            <b-form-row>
              <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="'工廠登記編號：'" :item="$v.coRegNo">
                <b-form-input class="col-2" v-model="$v.coRegNo.$model"
                              :state="validateState($v.coRegNo)"></b-form-input>
                <b-col class="col-3">
                  <bid-unit10-name :orgId="$v.coRegNo.$model"/>
                  <b-button variant="outline-dark">工廠登記編號查詢</b-button>
                  <!--                  @click="showOrgaModal"-->
                </b-col>
              </i-form-group-check>
            </b-form-row>


            <!-- 分包承攬金額 -->
            <b-row>
              <i-form-group-check label="分包承攬金額：" :labelStar="true" :item="$v.subBdgt1">
                <b-form-input class="text-right" v-model="$v.subBdgt1.$model"
                              :state="validateState($v.subBdgt1)"></b-form-input>
              </i-form-group-check>
              <div class="pt-2">
                <label>千元</label>
              </div>
            </b-row>
            <!-- 簽約業主 -->
            <b-row>
              <i-form-group-check :label="'簽約業主：'" :item="$v.upCcut">
                <b-form-select v-model="$v.upCcut.$model" :options="queryOptions.stype"
                               :state="validateState($v.upCcut)"></b-form-select>
              </i-form-group-check>
            </b-row>
            <!-- 服務類別 -->
            <b-row>
              <i-form-group-check :label="'服務類別：'" :item="$v.stype">
                <b-form-select v-model="$v.stype.$model" :options="queryOptions.stype"
                               :state="validateState($v.stype)"></b-form-select>
              </i-form-group-check>
            </b-row>
            <!-- 本分包廠商辦理之專業工程項目 -->
            <b-row>
              <i-form-group-check :label="'本分包廠商辦理之專業工程項目：'">
                <p>依營造業法第8條專業營造業登記之專業工程項目...第25條除與訂做人約定需自行施工者外,得交由專業營造業承攬...</p>
              </i-form-group-check>
            </b-row>
            <!-- 本分包廠商辦理之專業工程項目 -->
            <b-row>
              <b-col cols="2">
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw01State"> 鋼構工程
                </b-form-checkbox>
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw07State"> 地下管線工程
                </b-form-checkbox>
              </b-col>
            </b-row>
            <b-row>
            </b-row>
            <b-row>
              <b-col cols="2">
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw02State"> 擋土支撐及土方工程
                </b-form-checkbox>
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw08State"> 帷幕牆工程
                </b-form-checkbox>
              </b-col>
            </b-row>

            <b-row>
              <b-col cols="2">
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw03State"> 基礎工程
                </b-form-checkbox>
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw09State"> 庭園、景觀工程
                </b-form-checkbox>
              </b-col>
            </b-row>

            <b-row>
              <b-col cols="2">
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw04State"> 施工塔架吊裝及模板工程
                </b-form-checkbox>
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw10State"> 環境保護工程
                </b-form-checkbox>
              </b-col>
            </b-row>

            <b-row>
              <b-col cols="2">
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw05State"> 預拌混凝土工程
                </b-form-checkbox>
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw06State"> 防水工程
                </b-form-checkbox>
              </b-col>
            </b-row>

            <b-row>
              <b-col cols="2">
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw11State"> 營建鑽探工程
                </b-form-checkbox>
              </b-col>
              <b-col cols="4">
                <b-form-checkbox v-model="spcCheckbox.spcw12State">
                  其他經中央主管機關會同主管機關增訂或變更，並公告之項目
                </b-form-checkbox>
              </b-col>
            </b-row>

            <!-- 佔位符 -->
            <b-row>
              <i-form-group-check></i-form-group-check>
            </b-row>

            <!-- 工作內容 -->
            <b-row>
              <i-form-group-check :label="'工作內容　(主要工項)：'" :labelStar="true" :item="$v.subcoMemo" class="col-12"
                                  :label-cols="2" :content-cols="6" 　style="width:500px">

                <b-form-textarea
                  v-model="$v.subcoMemo.$model"
                  placeholder="工作內容說明..."
                  rows="3"
                  max-rows="6"
                  :state="validateState($v.subcoMemo)"
                  lazy
                  trim
                ></b-form-textarea>
              </i-form-group-check>
            </b-row>
            <!-- 畫面文字 -->
            <b-row>
              <b-col cols="2">
              </b-col>
              <b-col cols="10">
                <p>(下表材料設備數量內容,請參考品質監造計畫之[材料設備送審管制總表]內容)</p>
              </b-col>

            </b-row>
            <!-- 預拌混凝土(M033102) met01-->
            <b-row>
              <b-col cols="2">
                <p>本分包廠商供應材料:</p>
              </b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met01State"> 預拌混凝土(M033102)
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met01v"
                    >
                      <b-form-input class="col-6" v-model="$v.met01v.$model"
                                    :state="validateState($v.met01v)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 機拌混凝土(M033101) met01b-->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met01bState"> 機拌混凝土(M033101)
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met01bv"
                    >
                      <b-form-input class="col-6" v-model="$v.met01bv.$model"
                                    :state="validateState($v.met01bv)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 瀝青混凝土(M027420) met01c -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met01cState"> 瀝青混凝土(M027420)
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met01cv"
                    >
                      <b-form-input class="col-6" v-model="$v.met01cv.$model"
                                    :state="validateState($v.met01cv)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- CLSM  met02f -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model=spcCheckbox.met02fState> CLSM
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met02fv"
                    >
                      <b-form-input class="col-6" v-model="$v.met02fv.$model"
                                    :state="validateState($v.met02fv)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 低密度再生透水混凝土 met05 -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met05State"> 低密度再生透水混凝土
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met05v"
                    >
                      <b-form-input class="col-6" v-model="$v.met05v.$model"
                                    :state="validateState($v.met05v)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 水泥砂漿(M040611)  met01d -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met01dState"> 水泥砂漿(M040611)
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met01dv"
                    >
                      <b-form-input class="col-6" v-model="$v.met01dv.$model"
                                    :state="validateState($v.met01dv)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 砂(M040610) met02 -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met02State"> 砂(M040610)
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met02v"
                    >
                      <b-form-input class="col-6" v-model="$v.met02v.$model"
                                    :state="validateState($v.met02v)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 級配(M023191)  met02b -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met02bState"> 級配(M023191)
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met02bv"
                    >
                      <b-form-input class="col-6" v-model="$v.met02bv.$model"
                                    :state="validateState($v.met02bv)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 土石方 met04 -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met04State"> 土石方
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met04v"
                    >
                      <b-form-input class="col-6" v-model="$v.met04v.$model"
                                    :state="validateState($v.met04v)"></b-form-input>
                      <span class="m-2">立方米</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 鋼筋(M032100) met03 -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met03State"> 鋼筋
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met03v"
                    >
                      <b-form-input class="col-6" v-model="$v.met03v.$model"
                                    :state="validateState($v.met03v)"></b-form-input>
                      <span class="m-2">噸</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>
            <!-- 鋼板型鋼(M051240) met03b -->
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="10">
                <b-row>
                  <b-col cols="4">
                    <b-form-checkbox v-model="spcCheckbox.met03bState"> 鋼板型鋼(M051240)
                    </b-form-checkbox>
                  </b-col>
                  <b-col cols="8">
                    <i-form-group-check
                      :label="'提供量'"
                      :item="$v.met03bv"
                    >
                      <b-form-input class="col-6" v-model="$v.met03bv.$model"
                                    :state="validateState($v.met03bv)"></b-form-input>
                      <span class="m-2">噸</span>
                    </i-form-group-check>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>

            <!-- 佔位符 -->
            <b-row>
              <i-form-group-check></i-form-group-check>
            </b-row>

            <!-- 本分包廠商機具提供 -->
            <b-row>
              <b-col cols="2">
                <p>本分包廠商機具提供:</p>
              </b-col>

              <b-col cols="10">
                <div class="container">

                  <div class="card">
                    <div class="card-header py-1 text-left">

                      <b-row>
                        <b-col cols="2" class="text-center">
                          <p>序號</p>
                        </b-col>

                        <b-col cols="" class="text-center">
                          <p>機具名稱</p>
                        </b-col>

                        <b-col cols="2" class="text-center">
                          <p>數量</p>
                        </b-col>

                        <b-col cols="3" class="text-center">
                          <p>說明</p>
                        </b-col>

                        <b-col cols="2" class="text-center">
                          <p>動作</p>
                        </b-col>
                      </b-row>
                    </div>


                    <div class="card-body col-12">

                      <template v-for="(item, index) in formInput.scequipList">

                        <b-row style="margin: 5px">
                          <!--序號-->
                          <b-col cols="2" class="text-center">
                            <i-form-group-check class="col-sm-6" :label="''+(index+1)" v-model="item.cnt">
                            </i-form-group-check>
                          </b-col>
                          <!--機具名稱-->
                          <b-col cols="3">
                            <b-form-select v-model="item.eCode"
                                           :options=" formOptions2.bid030">
                              <template #first>
                                <option value="">不拘</option>
                              </template>
                            </b-form-select>
                          </b-col>
                          <!--數量-->
                          <b-col cols="2">
                            <b-form-input v-model="item.eCnt"></b-form-input>
                          </b-col>
                          <!--動作-->
                          <b-col cols="3">
                            <b-form-input v-model="item.eRemark"></b-form-input>
                          </b-col>

                          <b-col cols="2">
                            <div>
                              <i-button type="trash" @click="removeScutUnits(index)"
                                        v-if="formInput.scequipList.length > 1"></i-button>
                              <i-button type="plus-circle" @click="addScutUnits"
                                        v-if="index == formInput.scequipList.length - 1"></i-button>
                            </div>
                          </b-col>
                        </b-row>

                      </template>
                    </div>
                  </div>

                </div>
              </b-col>
            </b-row>

            <!-- 佔位符 -->
            <b-row>
              <i-form-group-check></i-form-group-check>
            </b-row>

            <div class="text-center mt-2">
              <i-button type="save" @click="submitFormData"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
              <i-button type="arrow-left" @click="backToPreviousPage"></i-button>
            </div>

          </div>
        </div>
      </div>
    </section>

    <bid-unit10-list ref="bidUnit10List"/>
  </div>
</template>

<script lang="ts">
import {Ref, ref, toRef, reactive, watch, onActivated, computed, onMounted} from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import {navigateByNameAndParams} from '@/router/router';
import BidPrjSubcoService from "@/components/bid/bidService/bid-prj-subco.service";
import {alphaNum, decimal, maxValue, numeric, required} from "@/shared/validators";
import {useValidation, validateState} from "@/shared/form";
import {BidPrjModify, IBidPrjModify} from "@/shared/model/bidModel/bid-prj-modify.model";
import BidPrjModifyService from "@/components/bid/bidService/bid-prj-modify.service";
import {useBvModal} from "@/shared/modal";
import {BidPrjSubco, IBidPrjSubco} from "@/shared/model/bidModel/bid-prj-subco.model";
import BidUnit10Name from "@/components/bid/bid-common-component/bid-unit10-name.vue";
import BidUnit10List from "@/components/bid/bid-common-component/bid-unit10-list.vue";
import {BidPrjScequip} from "@/shared/model/bidModel/bid-prj-scequip.model";
import BidPrjScequipService from "@/components/bid/bidService/bid-prj-scequip.service";
import AdmSysCodeService from "@/shared/common-service/adm-sys-code.service";
import BidProjectUnitService from "@/components/bid/bidService/bid-project-unit.service";

export default {
  name: 'bidAaa007Edit',
  components: {
    bidProjectInfo,
    BidUnit10List,
    BidUnit10Name,
  },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    bidProjectSubcoKey: {
      required: false,
    },
    formStatus: {
      require: true,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidUnit10List = ref(null);
    const tempProjectKey = {
      wkut: '',
      prjno: '',
      subcoNo: '',
    };
    if (bidProjectKeyProp.value) {
      tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
      tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      tempProjectKey.subcoNo = bidProjectKeyProp.value.subcoNo;
    }
    const bidProjectService = new BidProjectService();
    const bidPrjSubcoService = new BidPrjSubcoService();
    const bidPrjScequipService = new BidPrjScequipService();
    const bidProjectUnitService = new BidProjectUnitService();
    const admSysCodeService = new AdmSysCodeService();
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    const iTable = ref(null);
    const addNewModify = ref(false);
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({wkut: '', prjno: ''});

    const bidPrjScequipArray = reactive([]);
    let formDefault = new BidPrjSubco();

    let formDefault2 = new BidPrjScequip;

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    onMounted(() => {
      getAllSysCodes();
    });


    const queryOptions = reactive({
      stype: [
        {value: '', text: '請選擇'},
        {value: '1', text: '純勞務'},
        {value: '2', text: '材料或設備供應'},
        {value: '3', text: '機具租賃'},
        {value: '4', text: '連工帶料'},
      ],
    });

    const formOptions = reactive({
      subjectList: [],
    });

    const formOptions2 = reactive({
      bid030: [],
    });

    const formInput = reactive({
      scequipList: [],
    });

    const spcCheckbox = reactive({
      spcw01State: false,
      spcw02State: false,
      spcw03State: false,
      spcw04State: false,
      spcw05State: false,
      spcw06State: false,
      spcw07State: false,
      spcw08State: false,
      spcw09State: false,
      spcw10State: false,
      spcw11State: false,
      spcw12State: false,
      met01State: false,
      met01bState: false,
      met01cState: false,
      met02fState: false,
      met05State: false,
      met01dState: false,
      met02State: false,
      met02bState: false,
      met04State: false,
      met03State: false,
      met03bState: false,

    });

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }

    let form = reactive(Object.assign({}, formDefault));
    let form2 = reactive(Object.assign({}, formDefault2));

    let rules = {
      subcoNo: {required: required},
      subCcut: {}, //FIXME:欄位功能未實作 先不驗證 required: alphaNum
      coRegNo: {},
      subBdgt1: {required: numeric},
      upCcut: {},
      stype: {},
      subcoMemo: {required: required},
      spcw01: {},
      spcw02: {},
      spcw03: {},
      spcw04: {},
      spcw05: {},
      spcw06: {},
      spcw07: {},
      spcw08: {},
      spcw09: {},
      spcw10: {},
      spcw11: {},
      spcw12: {},
      met01: {},
      met01b: {},
      met01c: {},
      met02f: {},
      met05: {},
      met01d: {},
      met02: {},
      met02b: {},
      met04: {},
      met03: {},
      met03b: {},
      met01v: {},
      met01bv: {},
      met01cv: {},
      met02fv: {},
      met05v: {},
      met01dv: {},
      met02v: {},
      met02bv: {},
      met04v: {},
      met03v: {},
      met03bv: {},
    };

    let {$v, checkValidity, reset} = useValidation(rules, form, formDefault);

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    //新增.編輯
    function submitFormData() {
      checkValidity().then(bool => {
        if (bool) {
          if (formStatusProp.value === formStatusEnum.CREATE) {
            convertValue(form);
            form.scequips = formInput.scequipList;
            console.log('form', form)
            bidPrjSubcoService
              .saveBidProjectSubco(form)
              .then(() => {
                navigateByNameAndParams('bidAaa007InfoMenu', {
                  bidProjectKey: {
                    wkut: form.wkut,
                    prjno: form.prjno,
                  },
                });

              })
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                }
              });

          } else if (formStatusProp.value === formStatusEnum.MODIFY) {
            convertValue(form);
            console.log('formInput.scequipList', formInput.scequipList)
            console.log('form', form)
            form.scequips = formInput.scequipList;
            bidPrjSubcoService
              .updateExistingBidPrjSubco(form)
              .then(() => {

                navigateByNameAndParams('bidAaa007InfoMenu', {
                  bidProjectKey: {
                    wkut: form.wkut,
                    prjno: form.prjno,
                  },
                });

              })
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                }
              });
          }
        }
      });
    }

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa007InfoMenu', {
        bidProjectKey: {
          wkut: bidProjectKeyProp.value.wkut,
          prjno: bidProjectKeyProp.value.prjno,
        },
      });
    }

    //接收傳下來的key並判斷是新增還是修改
    watch(
      bidProjectKeyProp,
      newValue => {

        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
        }

        reset();

        if (formStatusProp.value && formStatusProp.value === formStatusEnum.CREATE) {
          Object.assign(formDefault, new BidPrjSubco());
          Object.assign(form, formDefault);
          form.wkut = newValue.wkut;
          form.prjno = newValue.prjno;

          //如果有殘存的id屬性則把它拿掉
          delete form['id'];
          bidPrjSubcoService.findProjectAllSubco(newValue.wkut, newValue.prjno).then(data => {
            if (data.content.length > 0) {

              //撈出subcoNo放到subjectList給畫面上的下拉選單
              let lastData = data.content.splice(-1);
              newValue.subcoNo = lastData[0].subcoNo;
              let splitSubcoNo = newValue.subcoNo.slice(2, 3);
              const parsed = parseInt(splitSubcoNo);
              let newSubcoNo = parsed + 1;
              formOptions.subjectList = [];
              formOptions.subjectList.push({value: newSubcoNo, text: newSubcoNo});

              bidProjectUnitService.findBidCcutByKeyWorld(newValue.wkut, newValue.prjno).then(projectUnit => {

                bidPrjScequipService.findPrjScequipAllData(newValue.wkut, newValue.prjno, projectUnit[0].ccut).then(scequip => {
                  formInput.scequipList = [];
                  formInput.scequipList.splice(0, formInput.scequipList.length, ...scequip);
                  if (formInput.scequipList.length == 0) {
                    const item = {cnt: 1, eCnt: '', eRemark: ''};
                    formInput.scequipList.push(item);
                  }
                });

              });

            }
          });

        } else if (formStatusProp.value && formStatusProp.value === formStatusEnum.MODIFY) {
          bidPrjSubcoService.getBidProjectSubcoByKey(newValue.wkut, newValue.prjno, newValue.subcoNo).then(subcoData => {

            console.log('subcoData>>>>>>',subcoData)

            //撈出subcoNo放到subjectList給畫面上的下拉選單
            formOptions.subjectList = [];
            let splitSubcoNo = newValue.subcoNo.slice(2, 3);
            formOptions.subjectList.push({value: splitSubcoNo, text: splitSubcoNo});
            checkboxStateToggle(subcoData)
            Object.assign(form, subcoData);
            Object.assign(formDefault, subcoData);

            // bidProjectUnitService.findBidCcutByKeyWorld(newValue.wkut, newValue.prjno).then(projectUnit => {

            bidPrjScequipService.findPrjScequipAllData(newValue.wkut, newValue.prjno, subcoData.subCcut).then(scequip => {
              formInput.scequipList = [];
              formInput.scequipList.splice(0, formInput.scequipList.length, ...scequip);
              if (formInput.scequipList.length == 0) {
                const item = {cnt: 1, eCnt: '', eRemark: ''};
                formInput.scequipList.push(item);
              }

            });

            // });

            reset();
          });
        }


      },
      {immediate: true}
    );

    function convertValue(value) {
      value.spcw01 = spcCheckbox.spcw01State ? 'Y' : 'N'
      value.spcw02 = spcCheckbox.spcw02State ? 'Y' : 'N'
      value.spcw03 = spcCheckbox.spcw03State ? 'Y' : 'N'
      value.spcw04 = spcCheckbox.spcw04State ? 'Y' : 'N'
      value.spcw05 = spcCheckbox.spcw05State ? 'Y' : 'N'
      value.spcw06 = spcCheckbox.spcw06State ? 'Y' : 'N'
      value.spcw07 = spcCheckbox.spcw07State ? 'Y' : 'N'
      value.spcw08 = spcCheckbox.spcw08State ? 'Y' : 'N'
      value.spcw09 = spcCheckbox.spcw09State ? 'Y' : 'N'
      value.spcw10 = spcCheckbox.spcw10State ? 'Y' : 'N'
      value.spcw11 = spcCheckbox.spcw11State ? 'Y' : 'N'
      value.spcw12 = spcCheckbox.spcw12State ? 'Y' : 'N'
      value.met01 = spcCheckbox.met01State ? 'Y' : 'N'
      value.met01b = spcCheckbox.met01bState ? 'Y' : 'N'
      value.met01c = spcCheckbox.met01cState ? 'Y' : 'N'
      value.met02f = spcCheckbox.met02fState ? 'Y' : 'N'
      value.met05 = spcCheckbox.met05State ? 'Y' : 'N'
      value.met01d = spcCheckbox.met01dState ? 'Y' : 'N'
      value.met02 = spcCheckbox.met02State ? 'Y' : 'N'
      value.met02b = spcCheckbox.met02bState ? 'Y' : 'N'
      value.met04 = spcCheckbox.met04State ? 'Y' : 'N'
      value.met03 = spcCheckbox.met03State ? 'Y' : 'N'
      value.met03b = spcCheckbox.met03bState ? 'Y' : 'N'
    }

    function checkboxStateToggle(value) {

      if (value.spcw01 === 'Y') {
        spcCheckbox.spcw01State = true
      }
      if (value.spcw02 === 'Y') {
        spcCheckbox.spcw02State = true
      }
      if (value.spcw03 === 'Y') {
        spcCheckbox.spcw03State = true
      }
      if (value.spcw04 === 'Y') {
        spcCheckbox.spcw04State = true
      }
      if (value.spcw05 === 'Y') {
        spcCheckbox.spcw05State = true
      }
      if (value.spcw06 === 'Y') {
        spcCheckbox.spcw06State = true
      }
      if (value.spcw07 === 'Y') {
        spcCheckbox.spcw07State = true
      }
      if (value.spcw08 === 'Y') {
        spcCheckbox.spcw08State = true
      }
      if (value.spcw09 === 'Y') {
        spcCheckbox.spcw09State = true
      }
      if (value.spcw10 === 'Y') {
        spcCheckbox.spcw10State = true
      }
      if (value.spcw11 === 'Y') {
        spcCheckbox.spcw11State = true
      }
      if (value.spcw12 === 'Y') {
        spcCheckbox.spcw12State = true
      }
      if (value.met01 === 'Y') {
        spcCheckbox.met01State = true
      }
      if (value.met01b === 'Y') {
        spcCheckbox.met01bState = true
      }
      if (value.met01c === 'Y') {
        spcCheckbox.met01cState = true
      }
      if (value.met02f === 'Y') {
        spcCheckbox.met02fState = true
      }
      if (value.met05 === 'Y') {
        spcCheckbox.met05State = true
      }
      if (value.met01d === 'Y') {
        spcCheckbox.met01dState = true
      }
      if (value.met02 === 'Y') {
        spcCheckbox.met02State = true
      }
      if (value.met02b === 'Y') {
        spcCheckbox.met02bState = true
      }
      if (value.met04 === 'Y') {
        spcCheckbox.met04State = true
      }
      if (value.met03 === 'Y') {
        spcCheckbox.met03State = true
      }
      if (value.met03b === 'Y') {
        spcCheckbox.met03bState = true
      }

    }

    function disabledInput(id) {
      console.log('哈摟哈搂', id)
    }

    function showFirmModal() {
      const queryKey = reactive({
        type: '1';
      });
      bidUnit10List.value.title = '廠商代碼查詢';
      bidUnit10List.value.isShowDia(queryKey, true);
    }

    function removeScutUnits(index) {

      formInput.scequipList.splice(index, 1,);
    }

    function addScutUnits() {

      const item = {eCnt: '', eRemark: ''};
      formInput.scequipList.push(item);
    }

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions2, 'BID');
    }

    return {
      showFirmModal,
      removeScutUnits,
      bidUnit10List,
      convertValue,
      checkboxStateToggle,
      projectInfo,
      bidProjectKeyProp,
      addNewModify,
      formStatusProp,
      formStatusEnum,
      $v,
      submitFormData,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      queryOptions,
      disabledInput,
      formOptions,
      formOptions2,
      spcCheckbox,
      formInput,
      addScutUnits,
      getAllSysCodes,
    };
  },
};
</script>
