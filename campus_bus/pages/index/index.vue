<template>
	<!-- 页面 -->
	<view class="content">	
		<!-- 导航栏 -->
<!-- 		<tui-navigation-bar splitLine title="NavBar自定义导航栏" backgroundColor="#fff" color="#333">
		</tui-navigation-bar> -->
		
	
		<!-- 路径选择 -->
		<view class="choose-station">
			<!-- 选择始发站 使用下拉选择框 dropdownlist-->
			<tui-dropdown-list  class="start-station" :show="startStationShow" :top="94" :height="400">		
				<!-- 按钮 -->
				<template v-slot:selectionbox>
					<tui-button type="white" shape="circle" width="240rpx" height="80rpx" margin="20rpx" @click="dropDownStartList(-1)">{{startStation}}
						<view class="tui-animation" :class="[startStationShow?'tui-animation-show':'']">
							<tui-icon name="turningdown" :size="20"></tui-icon>
						</view>
					</tui-button>
				</template>
				<!-- 下拉菜单 -->
				<template v-slot:dropdownbox>
					<view class="tui-selected-list">
						<scroll-view scroll-y class="tui-dropdown-scroll">
							<block v-for="(item,index) in startStationData" :key="index">
								<tui-list-cell padding="0" @click="pickStartStation(index)" :unlined="startStationData.length-1==index">
									<view class="tui-cell-class">
										<tui-icon class="icon" :name="item.icon" :size="item.size" :color="item.color"></tui-icon>
										<text class="tui-ml-20">{{item.name}}</text>
									</view>
								</tui-list-cell>
							</block>
						</scroll-view>
					</view>
				</template>
			</tui-dropdown-list>
			<!-- 切换始发站和重点站 -->
			<tui-button type="gray-primary" class="change-button" shape=circle width="80rpx" height="80rpx" margin="20rpx" @click="exchangeStation()">
				 <i class="iconfont	icon-exchange"></i>
			</tui-button>		
			<!-- 选择终点站 使用下拉选择框 dropdownlist-->
			<tui-dropdown-list  class="end-station" :show="endStationShow" :top="94" :height="400">				
				<template v-slot:selectionbox>
					<tui-button type="white" shape="circle" width="240rpx" height="80rpx" margin="20rpx" @click="dropDownEndList(-1)">{{endStation}}
						<view class="tui-animation" :class="[endStationShow?'tui-animation-show':'']">
							<tui-icon  name="turningdown" :size="20"></tui-icon>
						</view>
					</tui-button>
				</template>
				<template v-slot:dropdownbox>
					<view class="tui-selected-list">
						<scroll-view scroll-y class="tui-dropdown-scroll">
							<block v-for="(item,index) in endStationData" :key="index">
								<tui-list-cell padding="0" @click="pickEndStation(index)" :unlined="endStationData.length-1==index">
									<view class="tui-cell-class">
										<tui-icon class="icon" :name="item.icon" :size="item.size" :color="item.color"></tui-icon>
										<text class="tui-ml-20">{{item.name}}</text>
									</view>
								</tui-list-cell>
							</block>
						</scroll-view>
					</view>
				</template>
			</tui-dropdown-list>		
		</view>
		
		<!-- 查询按钮 -->
		<view class="update-button" v-if="changeFlag">
			<tui-button shape="circle" height="80rpx" width="80%">查询</tui-button>
		</view>
		
		<!-- 星期选择框 -->
		<tui-tabs color="#999" :tabs="tabs" selectedColor="#5677fc" :currentTab="currentDay" @change="chooseDay"></tui-tabs>
		
		<!-- 车票列表 -->
		<tui-list-view class="list" title="今日车票" color="#777">		
			<tui-list-cell class="list-cell" v-for="(item,index) in scheduleData" :key="index">
				<view class="card">
					<!-- 标签栏 -->
					<view class="card-flex">
						<tui-tag class="card-time-tag" type="light-blue" shape="circle">{{item.startTime}}发出</tui-tag>
					</view>
					<!-- 站点显示 -->
					<view class="card-flex card-station">
						<!-- 始发站 -->
						<view class="title-outline">					
							<text class="card-title">{{item.start}}</text>
							<text class="card-sub-title">{{item.startStation}}</text>
						</view>
						<!-- 经停站显示 -->
						 <tui-dropdown-list  class="start-station" :show="item.stopStationShow" :top="94" :height="400">
							<!-- 按钮：显示经停站 -->
							<template v-slot:selectionbox>
								<tui-button type="white" shape="circle" width="120rpx" height="60rpx" margin="20rpx" @click="dropDownStopList(index)">
									<i class="iconfont	icon-exchange"></i>
									<view class="tui-animation" :class="[item.stopStationShow?'tui-animation-show':'']">
										<tui-icon name="turningdown" :size="20"></tui-icon>
									</view>
								</tui-button>
							</template>
							<!-- 下拉菜单 -->
							<template v-slot:dropdownbox>
								<view class="tui-selected-list">
									<scroll-view scroll-y class="tui-dropdown-scroll">
										<block v-for="(item1,index) in item.stopStation" :key="index">
											<tui-list-cell padding="0" :unlined="item.stopStation.length-1==index">
												<view class="tui-cell-class">
													<text class="tui-ml-20">{{item1.name}}</text>
												</view>
											</tui-list-cell>
										</block>
									</scroll-view>
								</view>
							</template>
						 </tui-dropdown-list>
						 <!-- 终点站 -->
						 <view class="title-outline">
							 <text class="card-title">{{item.end}}</text>
							 <text class="card-sub-title">{{item.endStation}}</text>							 
						 </view>
					</view>
					<!-- 分割线 -->
					<tui-divider width="90%" v-bind:height="20" gradual></tui-divider>
					<!-- 其他信息 -->
					<view class="card-flex card-info">
						<tui-tag class="card-bus-tag" type="light-blue" shape="square" plain>车辆ID：{{item.busId}}</tui-tag>
						<view class="card-blank"></view>
						<tui-tag type="light-green" shape="square" padding="10rpx 20rpx">余票：{{item.ticket}} 张</tui-tag>
					</view>
				</view>
			</tui-list-cell>
		</tui-list-view>
		
		<!-- footer -->
		<tui-footer copyright="Copyright © 2014-2019 Thor UI." :navigate="navigate"></tui-footer>
		
		<!-- tabbar -->
		<tui-tabbar :isFixed="true" :tabBar="tabBar" hump :current="current" @click="tabbarSwitch"></tui-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 全局变量
				changeFlag:false,
				startStation: "始发校区",
				endStation: "终点校区",
				current: 0,
				currentDay: 0,
				tabs: [{
						name: "一"
					}, {
						name: "二"
					}, {
						name: "三"
					}, {
						name: "四",
					},{
						name: "五",
					},{
						name: "六",
					},{
						name: "日",
					},
				],
								
				startStationShow: false,
				endStationShow: false,
				
				startStationData: [{
					name: "翡翠湖校区",
					icon: "position",
					color: "#80D640",
					size: 22
				}, {
					name: "屯溪路校区",
					icon: "position",
					color: "#00AAEE",
					size: 22
				}],
				
				endStationData: [{
					name: "六安路校区",
					icon: "position",
					color: "#80D640",
					size: 22
				}, {
					name: "宣城校区",
					icon: "position",
					color: "#00AAEE",
					size: 22
				}, {
					name: "翡翠湖校区",
					icon: "position",
					color: "#ff7900",
					size: 22
				}],
				scheduleData:[
					{
						stopStationShow:false,
						startTime:"8:30",
						start:"屯溪路校区",
						end:"翡翠湖校区",
						startStation:"屯溪路校区北大门",
						endStation:"翡翠湖校区5教",
						stopStation:[
							{
								name: "长丰路",
							},
							{
								name: "仪表厂",
							}
						],
						busId:"1",
						ticket:10
					},
					{
						stopStationShow:false,
						startTime:"9:30",
						start:"屯溪路校区",
						end:"翡翠湖校区",
						startStation:"屯溪路校区北大门",
						endStation:"翡翠湖校区5教",
						stopStation:[
							{
								name: "暂无经停站",
							},
						],
						busId:"2",
						ticket:12
					},
				],
				
				stopStationData:[
					{
						name: "长丰路",
					},
					{
						name: "仪表厂",
				}],
				
				card: {
					img: {
						url: '/static/logo.png'
					},
					title: {
						text: 'CSDN云计算'
					},
					tag: {
						text: '1小时前'
					},
					header: {
						bgcolor: '#F7F7F7',
						line: true
					},
				},
				navigate: [{
					url: "/pages/index/index",
					type: "switchTab",
					text: "返回首页",
					color: "#5677fc"
				}, {
					url: "/pages/my/my",
					type: "switchTab",
					text: "个人中心",
					color: "#5677fc",
					size: 30
				}],
				
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
			
			// 按钮事件
			
			// 交换始发站终点站
			exchangeStation() {
				var tmp = this.startStation
				this.startStation = this.endStation
				this.endStation = tmp
				this.changeFlag = true
			},
			
			// 始发站下拉菜单
			dropDownStartList(index) {
				if (index !== -1) {
					this.tui.toast("index：" + index)
				}
				this.startStationShow = !this.startStationShow
			},
			
			// 终点站下拉菜单
			dropDownEndList(index) {
				if (index !== -1) {
					this.tui.toast("index：" + index)
				}
				this.endStationShow = !this.endStationShow
			},
			
			dropDownStopList(index) {
				this.scheduleData[index].stopStationShow = !this.scheduleData[index].stopStationShow
			},
			
			// 选择 始发站
			pickStartStation(index) {
				this.startStation = this.startStationData[index].name
				this.startStationShow = !this.startStationShow
				this.changeFlag = true
			},
			pickEndStation(index) {
				this.endStation = this.endStationData[index].name
				this.endStationShow = !this.endStationShow
				this.changeFlag = true
			},
			
			chooseDay(e) {
				this.currentDay = e.index
			},
			
			dropDownList(index) {
				if (index !== -1) {
					this.tui.toast("index：" + index)
				}
				this.dropdownShow = !this.dropdownShow
			},
			
			tabbarSwitch(e){
				console.log(e)
				uni.switchTab({
					url:e.pagePath
				})
			},
			change (){
				
			},
		}
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
	
	.icon {
		padding: 5rpx;
	}
		
	.update-button {
		margin-top: 30rpx;
		margin-left: 150rpx;
		margin-right: 40rpx;
		margin-bottom: 20rpx;
	}
	
	.list {
		width: 100%;
	}
	
	.choose-station {
		display: flex;
		margin-top: 20rpx;
		margin-left: 20rpx;
	}
	
	.start-button {
		margin-top: 20rpx;
		margin-bottom: 20rpx;
	}
	
	.time-list {
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		margin: 40rpx;
	}
	
	.list-cell {
		padding: 20rpx;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
	
	/*下拉选择*/

	.tui-animation {
		display: inline-block;
		transform: rotate(0deg);
		transition: all 0.2s;
	}
	
	.tui-animation-show {
		transform: rotate(180deg);
	}
	
	.tui-selected-list {
		background-color: #fff;
		border-radius: 20rpx;
		overflow: hidden;
		transform: translateZ(0);
	}
	
	.tui-dropdown-scroll {
		height: auto;
	}
	
	.tui-cell-class {
		display: flex;
		align-items: center;
		padding: 26rpx 30rpx !important;
	}
	
	/* 卡片部分 */
	.card {
		height: 260rpx;
		border:1px solid #c8c8ce;
		border-radius: 20rpx;
		-webkit-box-shadow: 8rpx 8rpx #c8c8ce;
	}
	
	.card-flex {
		display: flex;
	}
	.card-station {
		margin-left: 20rpx;
	}
	.card-info {
		margin-left: 300rpx;
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
	
</style>
