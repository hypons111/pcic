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
						<!-- 使用者帳號 -->
						<b-form-row>
								<i-form-group-check 
								:label="$t('label.userAccount') + '：'">
										{{userId}}
								</i-form-group-check>
						</b-form-row>
						<!-- 公會名稱 -->
						<b-form-row>
								<i-form-group-check labelStar
								:label="$t('label.guildName') + '：'"
								:item="$v.chName">
										<b-form-input
										v-model="$v.chName.$model"
										maxlength="50">
										</b-form-input>
								</i-form-group-check>
						</b-form-row>
						<!-- 理事長 -->
						<b-form-row>
								<i-form-group-check labelStar
								:label="$t('label.chairman') + '：'"
								:item="$v.masterName">
										<b-form-input
										v-model="$v.masterName.$model"
										maxlength="50">
										</b-form-input>
								</i-form-group-check>
						<!-- 秘書長 -->
								<i-form-group-check
								:label="$t('label.secretaryGeneral') + '：'"
								:item="$v.personName">
										<b-form-input
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
                <b-form-input maxlength="50" v-model="$v.tel1.$model">
                </b-form-input>
							</i-form-group-check>
              <!-- 電話(二) -->
              <i-form-group-check
                :label="$t('label.guildPhone2') + '：' " :item="$v.tel2">
                <b-form-input maxlength="20" v-model="$v.tel2.$model">
              </b-form-input>
              </i-form-group-check>
						</b-form-row>
						<!-- 傳真 -->
						<b-form-row>
								<i-form-group-check 
								:label="$t('label.guildFax') + '：'" :item="$v.fax">
										<b-form-input maxlength="20" v-model="$v.fax.$model">
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
										<b-form-input
										v-model="$v.mail1.$model"
										maxlength="50">
										</b-form-input>
								</i-form-group-check>
						</b-form-row>
						<b-form-row>
							<b-col offset="5">
								<!-- 清除 -->
								<i-button type="x-circle" @click="clear"/>
								<!-- 確認送出 -->
								<i-button type="send-check" @click="submitForm"/>
							</b-col>
						</b-form-row>	
				</div>
			</div>
		</section>
	</div>
</template>

<script lang="ts">
import { RoleEnum } from "@/shared/enum";
import { EngManage } from "@/shared/model/eng/eng-manage.model";
import EngManageService from '@/components/eng/eng0703/eng0703-manage.service';
import { Eng0703, IEng0703 } from "@/shared/model/eng/eng0703.model"
import { ref, unref, defineComponent, inject, onMounted, reactive} from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { useBvModal } from '@/shared/modal';
import { email, address, maxLength, required, lineTel, tel} from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useStore } from "@u3u/vue-hooks";
import AccountService from "@/account/account.service";

export default {
  name: 'Eng0507Edit',
  setup() {
    const tel1Component = ref(null);
		const tel2Component = ref(null);
		const accountService = inject<() => AccountService>('accountService')();
		const engManageService = new EngManageService();
		let formDefault = new EngManage(); // 預設空，等從後端or畫面撈
		const notificationService: NotificationService = useNotification();
		const userId = useStore().value.getters.account.id;
		const $bvModal = useBvModal();
		const form = reactive(Object.assign({}, formDefault));
		// 驗證
		const rules = {
      chName: { required: required, maxLength: maxLength(50) },
			masterName: { required: required, maxLength: maxLength(50) },
			personName: { maxLength: maxLength(50) },
			tel1: { required: required, maxLength: maxLength(50) },
			tel2: { maxLength: maxLength(20) },
			fax: { maxLength: maxLength(20)},
			address1Zip: {},
			address1City:{ required: required},
      address1Town:{ equalsCityBoolean: address(() => form.address1Town)},
      address1Other: { equalsCityBoolean: address(() => form.address1Other) },
			mail1:{ required: required, email: email, maxLength: maxLength(50) }
		}
		const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

		onMounted(() => {
			engManageService.viewEng0506(userId)  // 後端改完改這
			.then(eng0703 => {
				createDefaultValue(eng0703);
			})
			.catch(notificationErrorHandler(notificationService));
		});

		function createDefaultValue(eng0703: IEng0703) {
				Object.assign(formDefault, eng0703);  // 把eng0703複製到formDefault
				reset();
		}

		const clear = () => {
      reset();
    }

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
			const formData = await setFormBeforeSubmit();

			engManageService.updateEng0506(formData)
			.then(eng0703 => {
				createDefaultValue(eng0703);
				$bvModal.msgBoxConfirm('儲存成功!').then((isOK: boolean) => {})
			})
			.catch(notificationErrorHandler(notificationService));
		}

		async function setFormBeforeSubmit(): Promise<FormData> {
			form.updateTime = new Date();
			form.updateUser = accountService.user.id;
			const formData = new FormData();
			formData.append('form', new Blob([JSON.stringify(form)], 
			{ type: 'application/json'}));
			return formData;
		}

    return {
      $v,
			EngManage,
			validateState,
			clear,
			userId,
			submitForm,
      putForm,
			tel1Component,
			tel2Component
    }
  }
}
</script>