<template>
	<div>
		<section class="container">
			<div class="card">
				<div class="card-header">
						<h5 class="m-0">
								<font-awesome-icon icon="project-diagram"></font-awesome-icon>
								公會基本資料
						</h5>
				</div>
				<div class="card-body">
					<!-- 公會類型 -->
						<b-form-row>
							<i-form-group-check labelStar
							:label="$t('label.guildType') + '：'"
							:item="$v.type">
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
								{{ formOptionsFormatter(formOptions.type, $v.type.$model) }}
								</div>
								<b-form-select v-else v-model="$v.type.$model"
                :options="formOptions.type"
                >
                  <template #first>
                    <option value=""></option>
                  </template>
                </b-form-select>
							</i-form-group-check>
						</b-form-row>
						<!-- 公會名稱 -->
						<b-form-row>
							<i-form-group-check labelStar
							:label="$t('label.guildName') + '：'"
							:item="$v.chName">
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ $v.chName.$model }}
								</div>
								<b-form-input v-else
									v-model="$v.chName.$model"
									maxlength="50">
								</b-form-input>
							</i-form-group-check>
							<!-- 啟用 -->
							<i-form-group-check labelStar
							v-if="formStatusRef !== FormStatusEnum.READONLY"
								:label="$t('label.enable') + '：'"
								:item="$v.isUse">
								<!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ formOptionsFormatter(formOptions.status, $v.isUse.$model) }}
								</div> -->
								<b-form-checkbox 
								v-model="$v.isUse.$model"	@change="changeUse">
								</b-form-checkbox>
							</i-form-group-check>
						</b-form-row>
						<!-- 理事長 -->
						<b-form-row>
								<i-form-group-check labelStar
								:label="$t('label.chairman') + '：'"
								:item="$v.masterName">
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
								{{ $v.masterName.$model }}</div>
									<b-form-input v-else
									v-model="$v.masterName.$model"
									maxlength="50">
									</b-form-input>
								</i-form-group-check>
						<!-- 秘書長 -->
							<i-form-group-check labelStar
							:label="$t('label.secretaryGeneral') + '：'"
							:item="$v.personName">
							<div v-if="formStatusRef === FormStatusEnum.READONLY">
							{{ $v.personName.$model }}</div>
								<b-form-input v-else
								v-model="$v.personName.$model"
								maxlength="50">
								</b-form-input>
							</i-form-group-check>
						</b-form-row>
						<!-- 電話(一) -->
						<b-form-row>
							<i-form-group-check 
								labelStar
								:label="$t('label.guildPhone1') + '：' " :item="$v.tel1">
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
								{{$v.tel1.$model}}</div>
								<b-form-input  v-else 
								maxlength="50" v-model="$v.tel1.$model">
                </b-form-input>
							</i-form-group-check>
							<!-- 電話(二) -->
							<i-form-group-check
								:label="$t('label.guildPhone2') + '：' " :item="$v.tel2">
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
								{{$v.tel2.$model}}</div>
								<b-form-input v-else 
								maxlength="20" v-model="$v.tel2.$model">
								</b-form-input>
							</i-form-group-check>
						</b-form-row>
						<!-- 理事長手機 -->
						<b-form-row>
							<i-form-group-check
								:label="$t('label.masterTel') + '：' " :item="$v.tel3">
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
								{{$v.tel3.$model}}</div>
								<b-form-input v-else 
								maxlength="20" v-model="$v.tel3.$model">
								</b-form-input>
							</i-form-group-check>
							<!-- 傳真 -->
							<i-form-group-check 
							:label="$t('label.guildFax') + '：'" :item="$v.fax">
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
								{{$v.fax.$model}}</div>
								<b-form-input v-else
									maxlength="20" v-model="$v.fax.$model">
								</b-form-input>
							</i-form-group-check>
						</b-form-row>
						<!-- 地址 -->
						<b-form-row>
							<i-form-group-check labelStar
							:label="$t('label.guildAddr') + '：'"
							:item="[$v.address1Town, $v.address1Other, $v.address1City]"
							class="col-12"
							label-cols="2"
							content-cols="8">
								<i-address-picker
								:is-text="formStatusRef === FormStatusEnum.READONLY"
								maxlength="100" 
								:zip.sync="$v.address1Zip.$model"
                :city.sync="$v.address1City.$model"
                :town.sync="$v.address1Town.$model"
                :other.sync="$v.address1Other.$model"
              >
              </i-address-picker>
							</i-form-group-check>
						</b-form-row>
						<!-- email -->
						<b-form-row>
								<i-form-group-check labelStar
								label="Email："
								:item="$v.mail1">
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{$v.mail1.$model}}</div>
									<b-form-input v-else
									v-model="$v.mail1.$model"
									maxlength="50">
									</b-form-input>
								</i-form-group-check>
						</b-form-row>

						<b-form-row>
            <!-- 業務範圍 -->
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.businScope') + '：'"
              :item="$v.applyItems"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.subjectList, $v.subjectList.$model) }}
              </div>
              <b-form-checkbox-group
								v-else
                v-model="$v.subjects.$model"
                :options="formOptions.subjectList"
              ></b-form-checkbox-group>
            </i-form-group-check>
          </b-form-row>

						<b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" 
              :label="$t('label.remark') + '：'" :item="$v.remark">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.remark.$model"
                rows="4"
                maxlength="160"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
						<b-form-row>
							<b-col offset="5">
								<!-- 返回 -->
          			<i-button type="arrow-left" @click="toQueryView" />
								<!-- 確認送出 -->
								<i-button v-if="formStatusRef !== FormStatusEnum.READONLY" 
								type="send-check" @click="submitForm"/>
							</b-col>
						</b-form-row>	
				</div>
			</div>
		</section>
	</div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import { RoleEnum } from "@/shared/enum";
import { EngManage } from "@/shared/model/eng/eng-manage.model";
import EngManageService from '@/components/eng/eng0703/eng0703-manage.service';
import { Eng0703, IEng0703 } from "@/shared/model/eng/eng0703.model"
import { toRefs, ref, unref, defineComponent, inject, onMounted, reactive} from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { useBvModal } from '@/shared/modal';
import { email, address, maxLength, required, lineTel, tel, mobileTel} from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useStore } from "@u3u/vue-hooks";
import AccountService from "@/account/account.service";
import { FormStatusEnum } from '@/shared/enum';
import { handleBack } from '@/router/router';
import { formOptionsFormatter } from '@/shared/formatter/common';

interface propsType {
	eng0703: IEng0703;
	formStatus: FormStatusEnum;
	role: RoleEnum;
	criteria: any;
}
export default defineComponent<propsType>({
	name: 'Eng0502EditInfo',
	props: {
		eng0703: {
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
		const formStatusRef = ref(props.formStatus);
		const { eng0703 } = toRefs(props);
		const tel1Component = ref(null);
		const tel2Component = ref(null);
		const accountService = inject<() => AccountService>('accountService')();
		const engManageService = new EngManageService();
		const isReload = ref(false); // 是否需要重新查詢
		let formDefault = new EngManage(); // 預設空，等從後端or畫面撈
		const notificationService: NotificationService = useNotification();
		const $bvModal = useBvModal();
		const form = reactive(Object.assign({}, formDefault));
		// 驗證
		const rules = {
			type: { required: required},
      chName: { required: required, maxLength: maxLength(50) },
			isUse: {},
			masterName: { required: required, maxLength: maxLength(50) },
			personName: { maxLength: maxLength(50) },
			tel1: { required: required, maxLength: maxLength(50) },
			tel2: { maxLength: maxLength(20) },
			tel3: { maxLength: maxLength(20) },
			fax: { maxLength: maxLength(20)},
			address1Zip: {},
			address1City:{ required: required},
      address1Town:{ equalsCityBoolean: address(() => form.address1Town)},
      address1Other: { equalsCityBoolean: address(() => form.address1Other) },
			mail1:{ required: required, email: email, maxLength: maxLength(50) },
			remark: { maxLength: maxLength(160) },
			subjectList: {},
			subjects: {}
		}
		const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

		onMounted(() => {
				createDefaultValue(unref(eng0703));
		});

		function createDefaultValue(eng0703: IEng0703) {
			axios
        .get('/service/adm-sys-codes')
        .then(({ data }) => {
          formOptions.type.push(
            ...data
            .filter(item => item['moduleType'] === 'ENG' && item['dataType'] === 'COMP_TYPE'
            && (item['dataKey'] === '2' || item['dataKey'] === '5' || item['dataKey'] === '6'))
            .map(item => {
              return { value: item['dataKey'], text: item['value'] };
            })
          );
					formOptions.subjectList = [];
          formOptions.subjectList.push(
            ...data.filter(
              item => item['moduleType'] === 'ENG' && item['dataType'] === 'SUBJECT')
              .map(item => {
                return { value: item['dataKey'], text: item['value']};
              })
          );
					formOptions.status = [
            { value: false, text: '停用'},
            { value: true, text: '啟用'}
          ];
					setSubjects();
        })
        .catch(notificationErrorHandler(notificationService));
				Object.assign(formDefault, eng0703);  // 把eng0703複製到formDefault
				reset();
		}

		const formOptions = reactive({
			status: [],
      type:[],
      subjectList: [],
    });

		function submitForm() {
			// 先清除欄位$dirty狀態，才能依據isCheckRequired(是否驗證必填)重新驗證
			$v.value.$reset(); 

			// 不加this.$nextTick，checkValidity() 會比上方 $v.value.$reset() 先執行!!!
      this.$nextTick(() => {
				checkValidity().then((isValid: boolean) => {
					if(isValid) {
						$bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
							if(isOK) {
								putForm();
							}
						});
					} else {
						$bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
					}
				})
			})
		}

		async function putForm() {
			$v.value.subjectList.$model = "";
			for(let i = 0; i < $v.value.subjects.$model.length; i++) {
				$v.value.subjectList.$model += $v.value.subjects.$model[i] + ",";
			}
			const formData = await setFormBeforeSubmit();

			 if (formStatusRef.value === FormStatusEnum.EDIT) {
				engManageService.updateEng0502(formData)
				.then(eng0703 => {
					createDefaultValue(eng0703);
					isReload.value = true;
					$bvModal.msgBoxConfirm('儲存成功!').then((isOK: boolean) => {})
				})
				.catch(notificationErrorHandler(notificationService));
			 } else {
				engManageService
          .createEng0502(formData)
          .then(eng0703 => {
            // 1.新增完繼續按儲存或送出才會變修改 2.要在createDefaultValue上面才不會改到applyItems
            formStatusRef.value = FormStatusEnum.EDIT;
            createDefaultValue(eng0703);
            isReload.value = true;
            $bvModal.msgBoxConfirm('儲存成功!').then((isOK: boolean) => {

            })
          })
          .catch(notificationErrorHandler(notificationService));
			 }
		}

		async function setFormBeforeSubmit(): Promise<FormData> {
			form.updateTime = new Date();
			form.updateUser = accountService.user.id;

			if (formStatusRef.value !== FormStatusEnum.EDIT && formStatusRef.value !== FormStatusEnum.READONLY) {
				form.createTime = new Date();
        form.createUser = accountService.user.id;
			}

			const formData = new FormData();
			formData.append('form', new Blob([JSON.stringify(form)], 
			{ type: 'application/json'}));
			return formData;
		}

		function toQueryView() {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    }

		function setSubjects() {
			formDefault.subjects = 
			formDefault.subjectList ? formDefault.subjectList.split(',') : [];
			reset();
		}

		function changeUse() {
      formDefault.isUse = !formDefault.isUse;
    }
	
		return {
			FormStatusEnum,
			formStatusRef,
			$v,
			EngManage,
			validateState,
			submitForm,
      putForm,
			tel1Component,
			tel2Component,
			toQueryView,
			formOptions,
			changeUse,
			formOptionsFormatter
		};
	}
})

</script>