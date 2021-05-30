<template>
	<div class="contents">
		<div class="form-wrapper form-wrapper-sm">
			<form @submit.prevent="actionSubmit" class="form">
				<div>
					<label for="username">id:</label>
					<input id="username" type="text" v-model="username" />
					<p class="validation-text">
						<span class="warning" v-if="!isUsernameValid && username">
							Please enter an email address
						</span>
					</p>
				</div>
				<div>
					<label for="password">pw:</label>
					<input id="password" type="text" v-model="password" />
				</div>
				<button type="submit" class="btn">로그인 {{ msg }}</button>
				<a href="/oauth2/authorization/google">GOOGLE</a>
				<a href="/oauth2/authorization/kakao">KAKAO</a>
				<a href="/oauth2/authorization/naver">NAVER</a>
			</form>
		</div>
	</div>
</template>

<script>
import { login } from '@/api/index';
import { validateEmail } from '@/utils/validation';

export default {
	name: 'loginForm',
	data() {
		return {
			username: '',
			password: '',
			msg: '',
		};
	},
	computed: {
		isUsernameValid() {
			return validateEmail(this.username);
		},
	},
	methods: {
		async actionSubmit() {
			const userData = {
				username: this.username,
				password: this.password,
			};

			const response = await login(userData);
			const message = response.data.message;

			if(message != 'BadCredentialException') {
				this.$store.commit('setLoginState', true); //store > mutaion
				//this.$store.commit('setToken', 'asdasdasd');
				this.$router.push('/mainHome');
			}
		},
		initForm() {
			this.username = '';
			this.pwd = '';
		},
	},
};
</script>

<style>
.leftmargin {
	margin-left: 20px;
	margin-top: 20px;
}
</style>
