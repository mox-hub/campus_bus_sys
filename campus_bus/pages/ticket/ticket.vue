<template>
<view class="container">
		<!-- 自定义导航栏 -->
		<tui-navigation-bar :isOpacity="false" title="确认订单" backgroundColor="#5677fc" color="#f1f1f1">
			<view class="header-box">
				<tui-icon name="arrowleft" :color="'#fff'" @click="navigateBack"></tui-icon>
			</view>
		</tui-navigation-bar>
		<view class="header">
			<view>您共有</view>
			<view class="coupon-num">{{pageTotal}}张</view>
			<view>车票</view>
		</view>
	
		<view class="coupon-list">
			<view class="coupon-item" v-for="(item, index) in orderDataRes" :key="index">
				<view class="coupon">
					<image src="/static/images/ticket/img_fuwuquan_blue_3x.png" class="coupon-img"></image>
					<view class="left-tit-box">
						<view class="tit">{{ item.startLocation}}->{{item.endLocation}}</view>
						<view class="term">期限：{{ item.orderStatus }}</view>
					</view>
					<view class="right-detail" @tap="spread(index)">
						<text class="detail-txt">查看详情</text>
						<image src="/static/images/ticket/icon_next_3x.png" class="arrow" :class="{ 'tui-rotate_90': item.spread }"></image>
					</view>
				</view>
				<view class="hidden-box" v-show="item.spread">
					<view class="code-tit">订单号</view>
					<view class="code-num">{{ item.orderId }}</view>
					<view class="qrcode-box">
						<view class="qrcode">
							<canvas
								:class="{ 'my-canvas': !item.spread }"
								:style="{ width: qrcode_w + 'px', height: qrcode_w + 'px' }"
								:canvas-id="'couponQrcode' + index"
								:id="'couponQrcode' + index"
							></canvas>
						</view>
					</view>
					<view class="list-item">
						<view class="item-tit">乘车时间</view>
						<view class="item-con">{{ item.orderStatus }}</view>
					</view>
					<view class="list-item">
						<view class="item-tit">乘坐车辆</view>
						<view class="item-con">{{ item.busName }}: {{item.seatInfo}}座</view>
					</view>
					<view class="list-item">
						<view class="item-tit">乘车地点</view>
						<view class="item-con">{{item.startStation}}</view>
					</view>
				</view>
			</view>
		</view>
		<view class="none" v-show="couponNum == 0">暂无可用洗车券~</view>
	</view>
</template>

<script>
import util from '@/utils/util.js'
import qrCode from '@/libs/weapp-qrcode.js';
import { getDataNoParam, getDataParam, insertData, deleteData, updateData } from "../../api/api.js";
export default {
	data() {
		return {
			show: false,
			pageTotal: 0,
			query: {
				mode:"user",
				options:"1",   
				pageIndex:1,
				pageSize:10,
			},
			orderDataRes: [],
			couponList: [
				{
					name: '购物券',
					code: 'xyz0900100200',
					invalidTime: '2019-07-01',
					spread: false,
					sendTime: '2019-06-01',
					suitStore: '全部',
					useDescribe: ['1、可在任何适用商家内消费', '2、解释权归Thor所有']
				},
				{
					name: '打车券',
					code: 'xyz0900100300',
					invalidTime: '2019-07-01',
					spread: false,
					sendTime: '2019-06-01',
					suitStore: '滴滴打车',
					useDescribe: ['1、可在任何适用商家内消费', '2、解释权归Thor所有']
				},
			],
			qrcode_w: uni.upx2px(240)
		};
	},
	onLoad: function(options) {
		//const W = uni.getSystemInfoSync().windowWidth;
		//const qrcode_w = uni.upx2px(260);
		//this.qrcode_w = qrcode_w;
		uni.hideTabBar({})
		this.getOrderData()
		// #ifdef APP-PLUS || MP
		setTimeout(() => {
			this.couponQrCode(this.couponList[0].code, 'couponQrcode0');
		}, 60);
		// #endif
	},
	methods: {
		// 获取排班信息
		getOrderData: function(){
			getDataParam(this.query,'/order/queryOrderAssociated').then((res) => {
				console.log(res);
				this.orderDataRes = res.data;
				console.log(this.orderDataRes);
				for(var item in this.orderDataRes){
					console.log(this.orderDataRes[item])
					this.orderDataRes[item].spread = false;
				}
				console.log(this.orderDataRes);
				this.pageTotal = res.pageTotal || 10;
			})
		},
		
		spread: function(index) {
			let orderDataRes = this.orderDataRes;
			if (!orderDataRes[index].spread) {
				setTimeout(() => {
					this.couponQrCode(orderDataRes[index].orderId, 'couponQrcode' + index);
				}, 60);
			}
			orderDataRes[index].spread = !orderDataRes[index].spread;
			this.orderDataRes = orderDataRes;
		},
		// 二维码生成工具
		couponQrCode(text, canvasId) {
			new qrCode(canvasId, {
				text: text,
				width: this.qrcode_w,
				height: this.qrcode_w,
				colorDark: '#333333',
				colorLight: '#FFFFFF',
				correctLevel: qrCode.CorrectLevel.H
			});
			if (canvasId == 'couponQrcode0') {
				setTimeout(() => {
					if (!this.show) {
						this.show = true;
					}
				}, 0);
			}
		},
		navigateBack() {
			uni.navigateBack();
		}
	}
};
</script>

<style>
/* #ifdef MP-ALIPAY */
.my-canvas {
	position: fixed;
	left: -200px;
	top: 0;
}

/* #endif */

.container {
	padding-bottom: env(safe-area-inset-bottom);
}

.header {
	width: 100%;
	padding: 0 30rpx;
	box-sizing: border-box;
	font-size: 24rpx;
	color: #999;
	height: 54rpx;
	line-height: 54rpx;
	background: #fff;
	display: flex;
	align-items: center;
	position: fixed;
	top: 0;
	/* #ifdef H5 */
	top: 44px;
	/* #endif */
	z-index: 999999;
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

.coupon-num {
	color: #5677fc;
}

.coupon-list {
	margin-top: 100rpx;
	width: 100%;
	padding: 54rpx 50rpx;
	box-sizing: border-box;
}

.coupon-item {
	margin-top: 20rpx;
	width: 100%;
	box-shadow: 0 8rpx 10rpx -8rpx #eaeef1;
	border-radius: 2rpx;
}

.coupon {
	height: 160rpx;
	position: relative;
	display: flex;
	align-items: center;
}

.coupon-img {
	width: 100%;
	height: 160rpx;
	position: absolute;
	left: 0;
	top: 0;
	z-index: 0;
}

.left-tit-box {
	width: 414rpx;
	padding-left: 75rpx;
	box-sizing: border-box;
	z-index: 10;
}

.tit {
	width: 290rpx;
	font-size: 30rpx;
	color: #333;
	line-height: 42rpx;
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}

.term {
	width: 228rpx;
	font-size: 24rpx;
	color: #999;
	line-height: 24rpx;
	padding-top: 7rpx;
	white-space: nowrap;
}

.right-detail {
	width: 236rpx;
	text-align: center;
	height: 160rpx;
	display: flex;
	align-items: center;
	justify-content: center;
	z-index: 10;
}

.detail-txt {
	font-size: 24rpx;
	color: #fff;
	padding-right: 12rpx;
}

.arrow {
	width: 16rpx;
	height: 24rpx;
	transition: all 0.15s ease;
}

.spread {
	width: 24rpx;
	height: 16rpx;
}

.qrcode {
	width: 260rpx;
	height: 260rpx;
	margin: 45rpx auto 43rpx auto;
}

.hidden-box {
	padding: 35rpx 40rpx 27rpx 40rpx;
	box-sizing: border-box;
	color: #333;
	border-top: 1rpx solid #eaeef1;
	background: #fff;
}

.code-tit {
	font-size: 28rpx;
	line-height: 28rpx;
}

.code-num {
	font-size: 30rpx;
	line-height: 30rpx;
	padding-top: 23rpx;
	font-weight: bold;
}

.qrcode-box {
	width: 100%;
	border-bottom: 1rpx dashed #eaeef1;
	margin-bottom: 6rpx;
}

.list-item {
	height: 90rpx;
	display: flex;
	align-items: center;
	justify-content: space-between;
	font-size: 28rpx;
}

.list-item::after {
	left: 0;
}

.item-tit {
	color: #666;
}

.item-con {
	color: #333;
}

.explain {
	font-size: 28rpx;
	line-height: 28rpx;
	color: #666;
	padding: 33rpx 0 25rpx 0;
}

.explain-text {
	font-size: 28rpx;
	line-height: 41rpx;
	color: #333;
}

.none {
	width: 100%;
	position: fixed;
	text-align: center;
	font-size: 30rpx;
	color: #ccc;
	top: 49%;
}
.tui-rotate_90 {
	transform: rotate(90deg);
}
</style>
