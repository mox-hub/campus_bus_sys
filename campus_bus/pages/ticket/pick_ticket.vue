<template>
	<!-- 页面 -->
	<view class="content">
	<view class="card" v-for="item in scheduleDataRes">
		<!-- 站点显示 -->
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
			<tui-tag class="card-time-tag" type="light-blue" shape="square">2023年11月24日 {{item.startTime}}发出</tui-tag>
			<!-- 车辆ID -->
			<tui-tag class="card-bus-tag" type="light-blue" shape="square" plain>车辆：{{item.busName}}</tui-tag>
		</view>
	</view>
		<!-- 提示信息 -->
		<view class="ticket-info">
			<img class="ticket-info-seat" src="@/static/images/seat/seat_blank.png"/>
			<text class="ticket-info-font">可选</text>
			<img class="ticket-info-seat" src="@/static/images/seat/seat_select.png"/>
			<text class="ticket-info-font">已选</text>
			<img class="ticket-info-seat" src="@/static/images/seat/seat_selected.png"/>
			<text class="ticket-info-font">不可选</text>
		</view>
		<!-- 选座面板 -->
		<view class="seat-panel" v-for="bus in scheduleDataRes">
			<!-- 左列排号 -->
			<view class="column-container">
				<view v-for="(col,index) in bus.busColumns" :key="index" class="info-font">{{col}}</view>
			</view>
			<!-- 右侧 -->
			<view class="right-container">
				<!-- 车辆前方 -->
				<view class="bus-info">
					<text class="bus-info-font">车辆前方</text>
				</view>
				<!-- 车辆内部 -->
				<view v-if="seatShow" class="seat-container">
					<view v-for="(itemI,indexI) in seatIJ" :key="indexI" class="seat-row">
						<view v-for="(itemJ,indexJ) in itemI" :key="indexJ" class="seat-column">
							<view v-if="indexJ == 2" class="blank"></view>						
								<img v-on:click="selectSeat(indexI,indexJ)" v-if="itemJ == 0" class="seat-img" src="@/static/images/seat/seat_blank.png"/>
								<img v-on:click="deleteSeat(indexI,indexJ)" v-else-if="itemJ == 1" class="seat-img" src="@/static/images/seat/seat_select.png"/>
								<img v-else-if="itemJ == 2" class="seat-img" src="@/static/images/seat/seat_selected.png"/>
						</view>
					</view>
				</view>
				<!-- 车辆后方 -->
				<view class="bus-info">
					<text class="bus-info-font">车辆前方</text>
				</view>
			</view>
		</view>
		<view class="blank-bar"/>
		<tui-footer copyright="Copyright © 2022-至今 Foocode." :fixed="false"></tui-footer>
		<view class="blank-bar"/>
		<view class="button-bar">
			<view v-if="selected" class="select-panel">
				<view class="select-panel-title">
					<text>{{column}}排{{row}}座位</text>
				</view>
			</view>
			<tui-button v-bind:disabled="!selected">购票</tui-button>
		</view>
	</view>
</template>

<script>
	import { getDataNoParam, getDataParam, insertData, deleteData, updateData } from "../../api/api.js";
	export default {
		data(){
			return{
				id:1,
				current: 1,
				column: 1,
				row: 1,
				flag: 0,
				selected: false,
				query: {
					mode:"id",
					options:"1",   
					pageIndex:1,
					pageSize:10,
				},
				scheduleDataRes: [],
				seatShow: true,
				seatIJ: [
					[0,0,2,0],
					[0,0,2,0],
					[0,0,0,0],
					[0,0,2,2],
					[0,0,2,2],
					[0,0,0,0],
					[0,0,0,0],
					[0,0,0,0],
					[0,0,0,0],
				],
			}
		},
		methods: {
			// 获取排班信息
			getScheduleData(){
				getDataParam(this.query,'/schedule/queryScheduleAssociated').then((res) => {
					console.log(res)
					this.scheduleDataRes = res.data
					console.log(this.scheduleDataRes)
					this.pageTotal = res.pageTotal || 10
				})
			},
			
			// 切换tabbar
			tabbarSwitch(e){
				console.log(e)
				uni.switchTab({
					url:e.pagePath
				})
			},
			selectSeat(i,j) {
				if(this.flag < 1) {
					this.seatIJ[i][j] = 1;
					this.column = i + 1;
					this.row = j + 1;
					this.flag ++;
					this.selected = true;
				} else {
					uni.showToast({
						title: '您只能选一张票',
						icon:'none',
						duration: 2000
					})
				}
			},
			deleteSeat(i,j) {
				this.flag --;
				this.selected = false;
				this.seatIJ[i][j] = 0;
			},
		},
		onLoad(e) {
			this.id = e.id
			this.getScheduleData();
		},
	}
</script>

<style>
	.content {
		background-color: #f1f1f1;
		height: 100%;
		width: 100%;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	/* 卡片相关 */
	.card {
		background-color: #ffffff;
		height: 200rpx;
		padding-bottom: 10rpx;
	}
	
	.card-flex {
		display: flex;
	}
	.card-station {
		margin-left: 40rpx;
	}
	.card-info {
		margin-left: 20px;
	}
	.card-time-tag {
		
	}
	.title-outline {
		display: grid;
	}
	.card-title {
		margin-top: 20rpx;
		margin-left: 10rpx;
		margin-right: 10rpx;
		text-align: center;
		font-size: 40rpx;
		font-weight: 600;
		
	}
	.card-sub-title {
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
	
	/* 选择座位模块 */
	
	.ticket-info {
		display: flex;
		margin-top: 20rpx;
		margin-bottom: 10rpx;
		margin-left: 150rpx;
		text-align:center;
	}
	.ticket-info-font {
		margin-left: 5rpx;
		margin-right: 10rpx;
		padding: 5rpx;
		font-size: 28rpx;
		color: #b6b6b6;
	}
	.ticket-info-seat {
		width: 40rpx;
		height: 40rpx;
		padding: 5rpx;
	}
	.seat-panel {
		border-radius: 20rpx;
		display: flex;
		width: 100%;
	}
	.bus-info {
		width: 130rpx;
		background-color: #ffffff;
		border: 1rpx solid #acacac;
		border-radius: 20rpx;
		margin-left: 225rpx;
	}
	.bus-info-font {
		margin: 4rpx;
		padding: 4rpx;
		font-weight: 500;
		font-size: 28rpx;
		color: #b6b6b6;
	}
	.column-container {
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		margin-left: 20rpx;
		padding-top: 70rpx;
		padding-bottom: 20rpx;
		text-align:center;
		width: 50rpx;
		border: 5rpx solid #b6b6b6;
		border-radius: 30rpx;
		background-color: #b6b6b6;
	}
	
	.info-font {
		height: 50rpx;
		padding: 8rpx;
		text-align:center;
		font-size: 28rpx;
		font-weight: 600;
		color: #2f2f2f;
	}
	
	.seat-container {
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		margin-left: 140rpx;
		padding: 20rpx;
		padding-left: 10rpx;
		border-radius: 20rpx;
		border: 5rpx solid #b6b6b6;
	}
	
	.seat-row {
		padding: 8rpx;
		display: flex;
	}
	
	.seat-column {
		display: flex;
		margin-left:10rpx;
	}
	.blank {
		width: 40rpx;
	}
	.seat-img {
		width: 50rpx;
		height: 50rpx;
	}
	.blank-bar {
		height: 200rpx;
	}
	/* 操作面板 */
	.button-bar {
		position:fixed;
		bottom: 0rpx;
		width: 96%;
		padding: 10rpx;
	}
	.select-panel {
		background: #fff;
		height: 200rpx;
	}
	
	.select-panel-title {
		
	}
</style>