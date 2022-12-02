<template>
	<view class="content">
		<!-- 自定义导航栏 -->
		<tui-navigation-bar :isOpacity="false" title="确认订单" backgroundColor="#5677fc" color="#f1f1f1">
			<view class="header-box">
				<tui-icon name="arrowleft" :color="'#fff'" @click="navigateBack"></tui-icon>
				<view class="timing-box">
					<tui-icon name="alarm" color="#fff" :size="22"></tui-icon>
					<tui-countdown :time="timer" color="#fff" borderColor="#5677fc" colonColor="#fff" backgroundColor="#5677fc" :size="30" :hours="false" :scale="true"></tui-countdown>
				</view>
			</view>
		</tui-navigation-bar>
		
		<!-- tips -->
		<view class='tui-notice-board'>
			<view class="tui-icon-bg">
				<tui-icon name="news-fill" :size='24' color='#f54f46'></tui-icon>
			</view>
			<view class="tui-scorll-view" @tap='detail'>
				<view class="tui-notice" :class="[animation?'tui-animation':'']">订单将在下单后10分钟自动关闭，请尽快确定</view>
			</view>
		</view>
		
		<!-- 站点显示 -->
		<view class="card" v-for="item in orderDataRes">
			<view class="card-flex card-station">
				<!-- 始发站 -->
				<view class="title-outline">					
					<text class="card-title">{{item.startLocation}}</text>
					<text class="card-sub-title sub-title-size">{{item.startStation}}</text>
				</view>
				<!-- 经停站显示 -->
				<text class="card-title">发往</text>
				 <!-- 终点站 -->
				 <view class="title-outline">
					 <text class="card-title">{{item.endLocation}}</text>
					 <text class="card-sub-title sub-title-size">{{item.endStation}}</text>							 
				 </view>
			</view>
			<!-- 分割线 -->
			<tui-divider width="90%" v-bind:height="20" gradual></tui-divider>
			<!-- 其他信息 -->
			<view class="card-flex card-info">
				<!-- 时间信息 -->
				<tui-tag class="card-bus-tag" shape="square">座位号：{{item.seatInfo}}</tui-tag>
				<!-- 车辆ID -->
				<tui-tag class="card-bus-tag" shape="square" >车辆：{{item.busName}}</tui-tag>
			</view>
			<view class="card-flex card-info">
				<tui-tag class="card-time-tag" type="light-blue" margin="20rpx 220rpx" shape="square">2023年11月24日 {{item.startTime}}发出</tui-tag>
			</view>
		</view>
		
		<view class="tips-panel">
			<view class="title">
				<text>购票须知</text>
			</view>
			<view class="body">
				<text class="body-font">1.请确认车辆和时间无误，购买成功后不予退还。<br/></text>
				<text class="body-font">2.如存在天气等不确定因素，请以实际情况为主。<br/></text>
				<text class="body-font">3.购票成功后，可以在个人页面进行查看，也可以在底部导航栏找到出票窗口。<br/></text>
				<text class="body-font">4.请在下订单10分钟内尽快确定，否则订单将失效。<br/></text>
				<text class="body-font">5.下单即代表你认同我们的服务条款。<br/></text>
			</view>
		</view>
		<view class="person-panel">
			<view class="title">
				<text>您的信息</text>
			</view>
			<view class="body" v-for="item in orderDataRes">
				<view class="body-item">
					<tui-tag padding="4rpx 10rpx" size="24rpx" plain>学工号</tui-tag> 
					<text class="body-font"> : {{item.workId}}<br/></text>
				</view>
				<view class="body-item">
					<tui-tag padding="8rpx 22rpx" size="24rpx" plain>电话</tui-tag> 
					<text class="body-font"> : {{item.phone}}<br/></text>
				</view>
			</view>
		</view>
		
		<!-- 操作面板 -->
		<view class="button-bar">
			<tui-button @click="commit()">确认订单</tui-button>
		</view>
	</view>
</template>

<script>
	import { getDataNoParam, getDataParam, insertData, deleteData, updateData } from "../../api/api.js";
	export default {
		data(){
			return{
				// 全局变量
				top: 0,
				countdownm:'',
				countdowns:'',
				timer: null,
				animation: false,
				query: {
					mode:"id",
					options:"1596140446891089922",   
					pageIndex:1,
					pageSize:10,
				},
				orderDataRes:[],
			}
		},
		
		methods: {
			/* 网络请求 */
			
			// 获取订单信息
			getOrderData(){
				getDataParam(this.query,'/order/queryOrderAssociated').then((res) => {
					console.log(res);
					this.orderDataRes = res.data;
					console.log(this.orderDataRes);
					this.pageTotal = res.pageTotal || 10;
					this.setTime();
				})
			},
			
			navigateBack() {
				uni.navigateBack();
			},
			/* button 事件 */
			
			
			/* 工具函数 */
			setTime() {
				var nowtime = new Date();  //获取当前时间
				var endtime = this.orderDataRes[0].orderTime.replace(/-/g,"/");
				endtime = new Date(endtime);  //定义结束时间
				var lefttime = (endtime.getTime() - nowtime.getTime()) / 1000;  //距离结束时间的毫秒数
				console.log(lefttime);
				if(lefttime <= 0) {
					lefttime = 0;
				}
				this.timer = lefttime;
				console.log(123)
			},
			
			showtime () {
				var nowtime = new Date(),  //获取当前时间
				endtime = this.orderDataRes[0].orderTime.replace(/-/g,"/");
				endtime = new Date(endtime);  //定义结束时间
				var lefttime = endtime.getTime() - nowtime.getTime(),  //距离结束时间的毫秒数
				leftm = Math.floor(lefttime/(1000*60)%60) < 10 ? "0" + Math.floor(lefttime/(1000*60)%60) : Math.floor(lefttime/(1000*60)%60),  //计算分钟数
				lefts = Math.floor(lefttime/1000%60) < 10 ? "0" + Math.floor(lefttime/1000%60) : Math.floor(lefttime/1000%60);  //计算秒数
				this.countdownm = leftm//返回倒计时的字符串
				this.countdowns = lefts  //返回倒计时的字符串
				// 倒计时结束时，显示00:00:00
				if(lefttime < 0) {
					this.countdownh = this.countdownm= this.countdowns = "00"
				}
			},
			detail(e) {
				this.tui.toast('详情功能尚未完善~')
			},
			
			commit() {
				uni.switchTab({
					url:"/pages/index/index"
				})
			},

		},
		
		// 加载
		onLoad(e) {
			this.query.options = e.orderId;
			this.getOrderData();
			setTimeout(() => {
				this.animation = true
			}, 600)

		},
	}
</script>

<style>
	.content {
		background-color: #f1f1f1;
		height: 100vh;
		width: 100%;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.header-box {
		width: 100%;
		position: fixed;
		top: 10rpx;
		padding: 0 12rpx;
		display: flex;
		align-items: center;
		height: 32px;
		transform: translateZ(0);
		z-index: 99999;
		box-sizing: border-box;
	}
	.timing-box {
		display: flex;
		position: fixed;
		top: 12rpx;
		right: 20rpx;
	}
	.time-font {
		margin: 4rpx;
		color: #f1f1f1;
	}
	
	/* 卡片相关 */
	.card {
		padding-top: 80rpx;
		background-color: #5677fc;
		height: 300rpx;
		padding-bottom: 10rpx;
	}
	
	.card-flex {
		display: flex;
	}
	.card-station {
		margin-left: 50rpx;
	}
	.card-info {
		margin-left: 20px;
		padding-left: 80rpx;
	}
	.card-time-tag {
		margin-left: 100rpx;
	}
	.title-outline {
		display: grid;
	}
	.card-title {
		color: #f1f1f1;
		margin-top: 20rpx;
		margin-left: 10rpx;
		margin-right: 10rpx;
		text-align: center;
		font-size: 40rpx;
		font-weight: 600;	
	}
	.card-sub-title {
		color: #f1f1f1;
		margin-top: 5rpx;
		margin-left: 20rpx;
		margin-right: 10rpx;
		text-align: center;
		font-size: 30rpx;
		font-weight: 500;
	}
	.card-icon {
		margin: 10rpx;
	}
	
	/* tips */
	.tips-panel {
		margin: 18rpx;
		padding: 20rpx;
		border-radius: 20rpx;
		background-color: #fff;
	}
	.title {
		margin-bottom: 20rpx;
		font-size: 40rpx;
		font-weight: 600;
	}
	.body {
		margin-bottom: 20rpx;
	}
	
	.body-font {
		padding: 10rpx;
		font-size: 30rpx;
		color: #767676;
	}
	.body-item {
		padding: 10rpx;
		display: flex;
	}
	.person-panel {
		margin: 18rpx;
		padding: 20rpx;
		border-radius: 20rpx;
		background-color: #fff;
	}
	/* 操作面板 */
	.button-bar {
		position:fixed;
		bottom: 0rpx;
		width: 96%;
		padding: 10rpx;
	}
	
	.tui-notice-board {
		width: 100%;
		padding-right: 30rpx;
		box-sizing: border-box;
		font-size: 28rpx;
		height: 60rpx;
		background: #fff8d5;
		display: flex;
		align-items: center;
		position: fixed;
		top: 0;
		/* #ifdef H5 */
		top: 44px;
		/* #endif */
		z-index: 999;
	}
	
	.tui-icon-bg {
		background: #fff8d5;
		padding-left: 30rpx;
		position: relative;
		z-index: 10;
	}
	
	.tui-icon-class {
		margin-right: 12rpx;
	}
	
	.tui-scorll-view {
		flex: 1;
		line-height: 1;
		white-space: nowrap;
		overflow: hidden;
		color: #f54f46;
	}
	
	.tui-notice {
		-webkit-backface-visibility: hidden;
		-webkit-perspective: 1000;
		transform: translate3d(100%, 0, 0);
	}
	
	.tui-animation {
		-webkit-animation: tui-rolling 12s linear infinite;
		animation: tui-rolling 12s linear infinite;
	}
	
	@-webkit-keyframes tui-rolling {
		0% {
			transform: translate3d(100%, 0, 0);
		}
	
		100% {
			transform: translate3d(-170%, 0, 0);
		}
	}
	
	@keyframes tui-rolling {
		0% {
			transform: translate3d(100%, 0, 0);
		}
	
		100% {
			transform: translate3d(-170%, 0, 0);
		}
	}
</style>