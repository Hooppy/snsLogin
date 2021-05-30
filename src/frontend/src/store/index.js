import Vue from 'vue';
import Vuex from 'vuex';
import {
	saveLoginStateToCookie,
	saveUsernameToCookie,
	getLoginStateFromCookie,
	getUsernameFromCookie,
} from '@/utils/cookies';

import { signin } from '@/api/index';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		//여러 컴포넌트의 데이터
		username: getUsernameFromCookie() || '',
		loginState: getLoginStateFromCookie() || '',
	},
	getters: {
		//computed 유사
		isLogin(state) {
			return state.loginState === ''; // true, false
		},
	},
	mutations: {
		//state 변경
		setLoginState(state, loginState) {
			state.loginState = loginState;
		},
		setUsername(state, username) {
			state.username = username;
		},
	},
	actions: {
		async LOGIN({ commit }, userData) {
			const { data } = await signin(userData);
			console.log(data);
			commit('setToken', data.token);
			commit('setLoginState', data.loginState);
			commit('setUsername', data.username);
			saveLoginStateToCookie(data.loginState);
			saveUsernameToCookie(data.username);

			return data;
		},
	},
	modules: {},
});
