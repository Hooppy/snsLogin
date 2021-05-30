module.exports = {
	transpileDependencies: ['vuetify'],
	devServer: {
		port: 3000,
		proxy: {
			'/api': {
				target: 'http://localhost:8083',
				ws: true,
				changeOrigin: true,
			},
		},
		overlay: false,
	},
};
