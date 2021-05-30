import axios from 'axios';

const api = axios.create({
	baseURL: process.env.VUE_APP_API_URL,
	headers: {
		'content-type': 'application/json',
	},
});

// 회원가입
function login(userData) {
	return api.post('/api/login', userData);
}

export { login };
