const webpack = require('webpack');

module.exports = {
	productionSourceMap: false,
	outputDir: '../target/classes/static',
	publicPath: '/',
	devServer: {
		proxy: {
			'/api': { target: process.env.VUE_APP_API_HOST }
		},
		port: 9090,
		disableHostCheck: true
	},
	configureWebpack: () => {
		return {
			resolveLoader: {
				alias: {
					i18n: 'amdi18n-loader'
				}
			},
			plugins: [
				new webpack.ProvidePlugin({
					$: 'jquery',
					jquery: 'jquery',
					jQuery: 'jquery',
					'window.jQuery': 'jquery'
				})
			],
			performance: {
				hints: false
			}
		};
	},
	css: {
		loaderOptions: {
			sass: {
				additionalData: '@import "~@/assets/style/variables.scss"'
			},
			scss: {
				additionalData: '@import "~@/assets/style/variables.scss";'
			}
		}
	},
	transpileDependencies: [
		'vuetify'
	]
};
