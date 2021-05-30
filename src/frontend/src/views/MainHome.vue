<template>
	<div>
		<div>Main</div>
		<div>{{msg}}</div>
	</div>

</template>

<script>

export default {
	name: 'mainHome',
	data() {
		return {
			msg: ''
		}
	},
	mounted() {
		fetch('/api/member/userInfo')
		.then((response) => response.text())
		.then((data) => {
			this.msg = data;
		})
	}
};
</script>
