<template>
	<!-- 页面 -->
	<view class="content">
		
	<navigation-bar :title="123"/>
	<text>这是my.vue</text>
		
	<!-- tabbar -->
	<tui-tabbar :isFixed="true" :tabBar="tabBar" hump :current="current" @click="tabbarSwitch"></tui-tabbar>
	</view>
</template>

<script>
	export default {
		data(){
			return{
				current: 2,
				// 路由tabbar
				tabBar: [
					{
						pagePath: '/pages/index/index',
						text: '首页',
						iconPath: '/static/images/tabbar/home_gray.png',
						selectedIconPath: '/static/images/tabbar/home_active.png'
					},
					{
						pagePath: '/pages/ticket/ticket',
						text: '车票',
						iconPath: '/static/images/tabbar/ticket.png',
						hump: true,
						selectedIconPath: '/static/images/tabbar/ticket.png'
					},
					{
						pagePath: '/pages/my/my',
						text: '我的',
						iconPath: '/static/images/tabbar/me_gray.png',
						selectedIconPath: '/static/images/tabbar/me_active.png',
						num: 2,
						isDot: true,
						verify: true
					}
				],
			}
		},
		onLoad() {
		},
		
		methods: {
			// 切换tabbar
			tabbarSwitch(e){
				console.log(e)
				uni.switchTab({
					url:e.pagePath
				})
			},
			
		},
	}
</script>

<style>
	.content {
		height: 100%;
		width: 100%;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
</style>