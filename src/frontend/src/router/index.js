import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'Home',
		component: Home,
	},
	{
		path: '/loginForm',
		name: 'LoginForm',
		component: () => import('@/views/LoginForm.vue'),
	},
	{
		path: '/mainHome',
		name: 'MainHome',
		component: () => import('@/views/MainHome.vue'),
	},
	{
		/* 에러 페이지 */
		path: '/*',
		name: 'Error',
		component: () => import('@/views/Error.vue'),
	},
];

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes,
});

export default router;
