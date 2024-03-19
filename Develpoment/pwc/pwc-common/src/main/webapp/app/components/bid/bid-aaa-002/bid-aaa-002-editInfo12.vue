<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <!-- 適用營造業法 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="'適用營造業法：'" :item="$v.untnd">
            <b-form-radio-group v-model="$v.untnd.$model">
              <b-form-radio value="Y">屬適用營造業法</b-form-radio>
              <b-form-radio value="N">非屬營造業法</b-form-radio>
            </b-form-radio-group>
            <div class="col-3">
              <div class="d-inline">規定辦理之工程</div>
            </div>
          </i-form-group-check>
        </b-row>
      </div>
    </div>

    <div class="card">
      <div class="card-body col-12">
        <b-row>
          <b-col class="text-right" cols="2">本案內涵之專案工程項目：</b-col>
          <b-col cols="10">
            <!-- 是否專業工程項目  這個radio還沒有綁$V-->
            <b-row>
              <b-col cols="6">
                <b-form-group>
                  <b-form-radio-group v-model="$v.isSpcw.$model">
                    <b-form-radio value="Y">有專案工程項目</b-form-radio>
                    <b-form-radio value="N">無專案工程項目</b-form-radio>
                  </b-form-radio-group>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3">
                <b-form-group>
                  <b-form-checkbox v-model="$v.spcw01.$model" value="Y">鋼構工程</b-form-checkbox>
                </b-form-group>
              </b-col>
              <!-- 鋼構購件吊裝及組裝(不含材料費) -->
              <b-col cols="5">
                <i-form-group-check class="col-sm-12" label-cols-md="7" content-cols-md="5" :label="'鋼構購件吊裝及組裝(不含材料費)'" :item="$v.spcw01Amt">
                  <b-form-input class="col-9" v-model="$v.spcw01Amt.$model" :state="validateState($v.spcw01Amt)"></b-form-input>
                  <span class="ml-1">千元</span>
                </i-form-group-check>
              </b-col>
              <!-- 鋼構技術士設置 -->
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="9" :label="'技術士設置'" :item="$v.spcw01Nm">
                  <b-form-input class="col-4" v-model="$v.spcw01Nm.$model" :state="validateState($v.spcw01Nm)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <!-- 擋土支援及土方工程 -->
            <b-row>
              <b-col cols="3" class="mb-3 pb-3">
                <b-form-checkbox v-model="$v.spcw02.$model" value="Y">擋土支援及土方工程</b-form-checkbox>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3">
                <b-form-checkbox v-model="$v.spcw03.$model" value="Y">基礎工程</b-form-checkbox>
              </b-col>
              <!-- 擋土牆、土質改良及灌漿、錨樁工程 -->
              <b-col cols="5">
                <i-form-group-check class="col-sm-12" label-cols-md="7" content-cols-md="5" :label="'擋土牆、土質改良及灌漿、錨樁工程'" :item="$v.spcw03Amt">
                  <b-form-input class="col-9" v-model="$v.spcw03Amt.$model" :state="validateState($v.spcw03Amt)"></b-form-input>
                  <span class="ml-1">千元</span>
                </i-form-group-check>
              </b-col>
              <!-- 擋土技術士設置 -->
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="9" :label="'技術士設置'" :item="$v.spcw03Nm">
                  <b-form-input class="col-4" v-model="$v.spcw03Nm.$model" :state="validateState($v.spcw03Nm)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3">
                <b-form-checkbox v-model="$v.spcw04.$model" value="Y">施工搭架吊裝及模板工程</b-form-checkbox>
              </b-col>
              <!-- 結構體板模工程 -->
              <b-col cols="5">
                <i-form-group-check class="col-sm-12" label-cols-md="7" content-cols-md="5" :label="'結構體板模工程'" :item="$v.spcw04Amt">
                  <b-form-input class="col-9" v-model="$v.spcw04Amt.$model" :state="validateState($v.spcw04Amt)"></b-form-input>
                  <span class="ml-1">千元</span>
                </i-form-group-check>
              </b-col>
              <!-- 結構技術士設置 -->
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="9" :label="'技術士設置'" :item="$v.spcw04Nm">
                  <b-form-input class="col-4" v-model="$v.spcw04Nm.$model" :state="validateState($v.spcw04Nm)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3">
                <b-form-checkbox v-model="$v.spcw05.$model" value="Y">預拌混凝土工程</b-form-checkbox>
              </b-col>
              <!-- 預拌混凝土澆置工程(不含材料費) -->
              <b-col cols="5">
                <i-form-group-check class="col-sm-12" label-cols-md="7" content-cols-md="5" :label="'預拌混凝土澆置工程(不含材料費)'" :item="$v.spcw05Amt">
                  <b-form-input class="col-9" v-model="$v.spcw05Amt.$model" :state="validateState($v.spcw05Amt)"></b-form-input>
                  <span class="ml-1">千元</span>
                </i-form-group-check>
              </b-col>
              <!-- 預拌技術士設置 -->
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="9" :label="'技術士設置'" :item="$v.spcw05Nm">
                  <b-form-input class="col-4" v-model="$v.spcw05Nm.$model" :state="validateState($v.spcw05Nm)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3" class="mb-3 pb-3">
                <b-form-checkbox v-model="$v.spcw06.$model" value="Y">營建鑽探工程</b-form-checkbox>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3" class="mb-3 pb-3">
                <b-form-checkbox v-model="$v.spcw07.$model" value="Y">地下管線工程</b-form-checkbox>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3" class="mb-3 pb-3">
                <b-form-checkbox v-model="$v.spcw08.$model" value="Y">帷幕牆工程</b-form-checkbox>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3" class="mb-3 pb-3">
                <b-form-checkbox v-model="$v.spcw09.$model" value="Y">庭園、景觀工程</b-form-checkbox>
              </b-col>
              <!-- 造園景觀施工、植生綠化及養護 -->
              <b-col cols="5">
                <i-form-group-check class="col-sm-12" label-cols-md="7" content-cols-md="5" :label="'造園景觀施工、植生綠化及養護'" :item="$v.spcw09Amt">
                  <b-form-input class="col-9" v-model="$v.spcw09Amt.$model" :state="validateState($v.spcw09Amt)"></b-form-input>
                  <span class="ml-1">千元</span>
                </i-form-group-check>
              </b-col>
              <!-- 造園技術士設置 -->
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="9" :label="'技術士設置'" :item="$v.spcw09Nm">
                  <b-form-input class="col-4" v-model="$v.spcw09Nm.$model" :state="validateState($v.spcw09Nm)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3" class="mb-3 pb-3">
                <b-form-checkbox v-model="$v.spcw10.$model" value="Y">環境保護工程</b-form-checkbox>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="3" class="mb-3 pb-3">
                <b-form-checkbox v-model="$v.spcw11.$model" value="Y">防水工程</b-form-checkbox>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="12" class="mb-3 pb-3">
                <b-form-checkbox v-model="$v.spcw12.$model" value="Y">其他經中央主管機關會同主管機關增訂或變更，並公告之項目</b-form-checkbox>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="10">技術士種類：</b-col>
            </b-row>
            <b-row>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'1.一般手工電焊技術士：'" :item="$v.job01Type">
                  <b-form-input class="col-8" v-model="$v.job01Type.$model" :state="validateState($v.job01Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'2.半自動電銲技術士：'" :item="$v.job02Type">
                  <b-form-input class="col-8" v-model="$v.job02Type.$model" :state="validateState($v.job02Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'3.氬氣鎢極電銲技術士：'" :item="$v.job03Type">
                  <b-form-input class="col-8" v-model="$v.job03Type.$model" :state="validateState($v.job03Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'4.測量技術士：'" :item="$v.job04Type">
                  <b-form-input class="col-8" v-model="$v.job04Type.$model" :state="validateState($v.job04Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'5.建築塗裝技術士：'" :item="$v.job05Type">
                  <b-form-input class="col-8" v-model="$v.job05Type.$model" :state="validateState($v.job05Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'6.鋼筋技術士：'" :item="$v.job06Type">
                  <b-form-input class="col-8" v-model="$v.job06Type.$model" :state="validateState($v.job06Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'7.模板技術士：'" :item="$v.job07Type">
                  <b-form-input class="col-8" v-model="$v.job07Type.$model" :state="validateState($v.job07Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'8.混凝土技術士：'" :item="$v.job08Type">
                  <b-form-input class="col-8" v-model="$v.job08Type.$model" :state="validateState($v.job08Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'9.造園景觀技術士：'" :item="$v.job09Type">
                  <b-form-input class="col-8" v-model="$v.job09Type.$model" :state="validateState($v.job09Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'10.園藝技術士：'" :item="$v.job10Type">
                  <b-form-input class="col-8" v-model="$v.job10Type.$model" :state="validateState($v.job10Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'11.泥水(粉刷項)技術士：'" :item="$v.job11Type">
                  <b-form-input class="col-8" v-model="$v.job11Type.$model" :state="validateState($v.job11Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'12.營建防水技術士：'" :item="$v.job12Type">
                  <b-form-input class="col-8" v-model="$v.job12Type.$model" :state="validateState($v.job12Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="6">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="3" :label="'13.下水道用戶排水設備配管技術士：'" :item="$v.job13Type">
                  <b-form-input class="col-8" v-model="$v.job13Type.$model" :state="validateState($v.job13Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
              <b-col cols="4">
                <i-form-group-check class="col-sm-12" label-cols="7" content-cols-md="5" :label="'14.其他技術士：'" :item="$v.job14Type">
                  <b-form-input class="col-8" v-model="$v.job14Type.$model" :state="validateState($v.job14Type)"></b-form-input>
                  <span class="ml-1">人</span>
                </i-form-group-check>
              </b-col>
            </b-row>
          </b-col>
        </b-row>
      </div>
    </div>

    <div class="card">
      <div class="card-body col-12">
        <!-- 使用預拌混凝土之提供方式 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="'使用預拌混凝土之提供方式：'" :item="$v.semset">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.semset.$model">
                <b-form-radio value="1">由「領有工廠登記證」之預拌混凝土廠供應</b-form-radio>
                <b-form-radio value="2">經機關同意，依「公共工程工地型預拌混凝土設備設置及拆除管理要點」規定辦理</b-form-radio>
                <b-form-radio value="3">本工程處離島地區，且境內無符合「工廠管理輔導法」之預拌混凝土廠</b-form-radio>
                <b-form-radio value="4">本工程無預拌混凝土需求或需求少量採人工拌合</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
      </div>
    </div>

    <div class="card">
      <div class="card-body col-12">
        <!-- 循環資源 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'循環資源：'" :item="$v.isRcb">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.isRcb.$model">
                <b-form-radio value="Y">有使用AC刨除料、煉鋼爐碴或焚化再生粒料等循環資源</b-form-radio>
                <b-form-radio value="N">未使用循環資源</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
      </div>
    </div>

    <div class="card">
      <div class="card-body col-12">
        <b-row>
          <b-col cols="2" class="text-right label pr-1">
            <div class="pt-2">大眾營建資材需求：</div>
          </b-col>
          <b-col cols="8">
            <!-- 預拌混凝土(M033102) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'預拌混凝土(M033102)：'" :item="$v.sorc1">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc1.$model" :state="validateState($v.sorc1)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 機拌混凝土(M033101) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'機拌混凝土(M033101)：'" :item="$v.sorc1b">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc1b.$model" :state="validateState($v.sorc1b)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 瀝青混凝土(M027420) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'瀝青混凝土(M027420)：'" :item="$v.sorc1c">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc1c.$model" :state="validateState($v.sorc1c)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 低密度再生透水混凝土(M03341) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'低密度再生透水混凝土(M03341)：'" :item="$v.sorc6">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc6.$model" :state="validateState($v.sorc6)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 控制性低強度回填材料(CLSM)(M03377) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8":label="'控制性低強度回填材料(CLSM)(M03377)：'" :item="$v.sorc3e">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc3e.$model" :state="validateState($v.sorc3e)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 水泥砂漿(M040611) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'水泥砂漿(M040611)：'" :item="$v.sorc1d">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc1d.$model" :state="validateState($v.sorc1d)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 砂(M040610) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'砂(M040610)：'" :item="$v.sorc2">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc2.$model" :state="validateState($v.sorc2)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 級配(M023191) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'級配(M023191)：'" :item="$v.sorc3">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc3.$model" :state="validateState($v.sorc3)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 土石方(M02331) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'土石方(M02331)：'" :item="$v.sorc3a">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc3a.$model" :state="validateState($v.sorc3a)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 鋼筋(M032100) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'鋼筋(M032100)：'" :item="$v.sorc4">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc4.$model" :state="validateState($v.sorc4)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 鋼筋及型鋼(M051240) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'土石方(M02331)：'" :item="$v.sorc5">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc5.$model" :state="validateState($v.sorc5)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
            <!-- 板模(M03110) -->
            <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="8" :label="'板模(M03110)：'" :item="$v.sorc9">
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.sorc9.$model" :state="validateState($v.sorc9)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">立方米</div>
              </div>
            </i-form-group-check>
          </b-col>
        </b-row>
      </div>
    </div>

    <div class="card">
      <div class="card-body col-12">
        <b-row>
          <b-col cols="2" class="text-right label pr-1">
            <div class="pt-2">營建挖(刨)除產出量：</div>
          </b-col>
          <b-col cols="10">
            <b-row>
              <!-- AC刨除料計劃產出量 -->
              <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="9" :label="'AC刨除料計劃產出量：'" :item="$v.sorc7">
                <div class="d-inline">
                  <b-form-input class="col-12" v-model="$v.sorc7.$model" :state="validateState($v.sorc7)"></b-form-input>
                </div>
                <div class="col-8">
                  <div class="d-inline">立方米</div>
                </div>
              </i-form-group-check>
              <!-- 營建剩土石方計畫產出量 -->
              <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="9" :label="'營建剩土石方計畫產出量：'" :item="$v.sorc8">
                <div class="d-inline">
                  <b-form-input class="col-12" v-model="$v.sorc8.$model" :state="validateState($v.sorc8)"></b-form-input>
                </div>
                <div class="col-8">
                  <div class="d-inline">立方米</div>
                </div>
              </i-form-group-check>
            </b-row>
          </b-col>
        </b-row>
      </div>
    </div>

    <!-- 錯誤訊息 -->
    <b-modal
      id="valid-error-modal"
      size="lg"
      title="資料驗證有誤:"
      :header-text-variant="'light'"
      :header-bg-variant="'danger'"
      hide-footer
    >
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { Ref, ref, toRef, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAaa002EditInfo12',
  props: {
    bidProjectInfo: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectInfo');

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      untnd: '', //適用營造業法
      isSpcw: '', //是否專業工程項目
      spcw01: '', //鋼構工程
      spcw02: '', //擋土支撐及土方工程
      spcw03: '', //基礎工程
      spcw04: '', //施工搭架吊裝及模板工程
      spcw05: '', //預拌混凝土工程
      spcw06: '', //營建鑽探工程
      spcw07: '', //地下管線工程
      spcw08: '', //帷幕牆工程
      spcw09: '', //庭園景觀工程
      spcw10: '', //環境保護工程
      spcw11: '', //防水工程
      spcw12: '', // 其他經中央主管機關會同主管機關增訂或變更，並公告之項目
      spcw01Amt: '', //鋼構購件吊裝及組裝(不含材料費)
      spcw01Nm: '', //(鋼購)技術士設置
      spcw03Amt: '', //擋土牆、土質改良及灌漿、錨樁工程
      spcw03Nm: '', //擋土技術士設置
      spcw04Amt: '', //結構體板模工程
      spcw04Nm: '', //結構技術士設置
      spcw05Amt: '', //預拌混凝土澆置工程(不含材料費)
      spcw05Nm: '', //預拌技術士設置
      spcw09Amt: '', //造園景觀施工、植生綠化及養護
      spcw09Nm: '', //造園技術士設置
      job01Type: '', //一般手工電焊技術士
      job02Type: '', //半自動電銲技術士
      job03Type: '', //氬氣鎢極電銲技術士
      job04Type: '', //測量技術士
      job05Type: '', //建築塗裝技術士
      job06Type: '', //鋼筋技術士
      job07Type: '', //模板技術士
      job08Type: '', //混凝土技術士
      job09Type: '', //造園景觀技術士
      job10Type: '', //園藝技術士
      job11Type: '', //泥水(粉刷項)技術士
      job12Type: '', //營建防水技術士
      job13Type: '', //下水道用戶排水設備配管技術士
      job14Type: '', //其他技術士
      semset: '', //使用預拌混凝土之提供方式
      isRcb: '', //循環資源
      sorc1: '', //預拌混凝土(M033102)
      sorc1b: '', //機拌混凝土(M033101)
      sorc1c: '', //瀝青混凝土(M027420)
      sorc6: '', //低密度再生透水混凝土(M03377)
      sorc3e: '', //控制性低強度回填材料(CLSM)(M03377)
      sorc1d: '', //水泥砂漿(M040611))
      sorc2: '', //砂(M040610)
      sorc3: '', //級配(M023191)
      sorc3a: '', //土石方(M02331)
      sorc4: '', // 鋼筋(M032100)
      sorc5: '', // 土石方(M02331)
      sorc9: '', // 板模(M03110)
      sorc7: '', //AC刨除料計劃產出量
      sorc8: '', //營建剩土石方計畫產出量
    };
    let formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      untnd: {},
      isSpcw: {},
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
      spcw01Amt: {},
      spcw01Nm: {},
      spcw03Amt: {},
      spcw03Nm: {},
      spcw04Amt: {},
      spcw04Nm: {},
      spcw05Amt: {},
      spcw05Nm: {},
      spcw09Amt: {},
      spcw09Nm: {},
      job01Type: {},
      job02Type: {},
      job03Type: {},
      job04Type: {},
      job05Type: {},
      job06Type: {},
      job07Type: {},
      job08Type: {},
      job09Type: {},
      job10Type: {},
      job11Type: {},
      job12Type: {},
      job13Type: {},
      job14Type: {},
      semset: {},
      isRcb: {},
      sorc1: {},
      sorc1b: {},
      sorc1c: {},
      sorc6: {},
      sorc3e: {},
      sorc1d: {},
      sorc2: {},
      sorc3: {},
      sorc3a: {},
      sorc4: {},
      sorc5: {},
      sorc9: {},
      sorc7: {},
      sorc8: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa002Query');
    }

    function putForm() {
      return form;
    }

    watch(() => props.bidProjectInfo, (newVal: object) => {
      Object.assign(formDefault, formEmpty)
      for(let key in formDefault){
        if(newVal[key]) formDefault[key] = newVal[key];
      }
      reset();
    },{immediate: true, deep: true});

    return {
      $v,
      checkValidity,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      putForm,
    };
  },
};
</script>

<style></style>
