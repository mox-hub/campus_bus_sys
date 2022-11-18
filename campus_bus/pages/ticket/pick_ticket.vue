<template>
<view id="select-seat">
    <view class="top">
      <span class="icon-back" @click="$router.go(-1)"></span>
      <span class="name ellipsis">{{cinemaInfo.cinema_name}}</span>
    </view>
    <view class="movie-info">
      <view class="name">{{movieInfo.name}}</view>
      <view class="intro"><span class="date">{{scheduleInfo.show_date|dateFilter}}</span><span class="time">{{scheduleInfo.show_time}}</span><span class="language">{{movieInfo.language}}3D</span></view>
    </view>
    <view class="seat-block">
      <view class="screen">{{scheduleInfo.hall_name}}银幕</view>
      <view class="center">银幕中央</view>
      <view class="screen-line"></view>
      <view class="row-container">
        <span>1</span>
        <span>2</span>
        <span>3</span>
        <span>4</span>
        <span>5</span>
        <span>6</span>
      </view>
      <view class="seat-container">
        <view class="row" v-if="hackReset" v-for="(itemI,indexI) in seatIJ" :key="indexI">
          <span class="seat"
            v-for="(itemJ,indexJ) in itemI"
            :key="indexJ"
            :class="{'icon-sold-seat':itemJ===1,'icon-empty-seat':itemJ===0,'icon-selected-seat':itemJ===2}"
            @click.prevent="handleSelectSeat(indexI,indexJ)"
          ></span>
        </view>
      </view>
      <view class="seat-example">
        <view class="example empty-example"><span class="icon icon-empty-seat"></span>可选</view>
        <view class="example sold-example"><span class="icon icon-sold-seat"></span>不可选</view>
        <view class="example selected-example"><span class="icon icon-selected-seat"></span>已选</view>
      </view>
    </view>
    <view class="bottom">
      <view class="title" v-if="selectedSeat">已选座位</view>
      <view class="selected-seat" v-if="selectedSeat">
        <view class="seat-item" v-for="(item,index) in selectedSeatInfo" :key="index">
          <view class="left">
            <span class="seat">{{item[0]+1}}排{{item[1]+1}}座</span>
            <span class="price">{{(scheduleInfo.price).toFixed(2)}}元</span>
          </view>
          <span class="right icon-close" @click="cancelSelectedSeat(item[0],item[1])"></span>
        </view>
      </view>
      <span class="btn" v-if="!selectedSeat">请先选座</span>
      <span class="btn active" v-else @click="ensureSeatBtn">确认选座</span>
    </view>
  </view>
</template>

<script>
	    export default {
	        name: "SelectSeat",
	        data(){
	          return{
	            cinemaInfo:{},
	            movieInfo:{},
	            scheduleInfo:{},
	            seatInfo:'',
	            seatCount:0,
	            selectedSeat:false,
	            hackReset:true,
	            selectedSeatInfo:[],
	            seatIJ:[
	              [0,0,0,0,0,0,0,0,0,0],
	              [0,0,0,0,0,0,0,0,0,0],
	              [0,0,0,0,0,0,0,0,0,0],
	              [0,0,0,0,0,0,0,0,0,0],
	              [0,0,0,0,0,0,0,0,0,0],
	              [0,0,0,0,0,0,0,0,0,0]
	            ],
	          }
	        },
	        created() {
	          Indicator.open('Loading...');
	          this.loadInfo();
	        },
	        methods:{
	          //加载信息
	          async loadInfo(){
	            // if (this.$route.query.cinema_id&&this.$route.query.movie_id&&this.$route.query.schedule_id){
	            //   let json = await getCurrentCinemaDetail(this.$route.query.cinema_id);
	            //   if (json.success_code===200) {
	            //     this.cinemaInfo = json.data;
	            //   }
	            //   json = await getMovieDetail(this.$route.query.movie_id);
	            //   if (json.success_code===200) {
	            //     this.movieInfo = json.data[0];
	            //   }
	            //   json = await getScheduleById(this.$route.query.schedule_id);
	            //   if (json.success_code===200) {
	            //     this.scheduleInfo = json.data;
	            //     this.seatInfo = this.scheduleInfo.seat_info;
	            //     if (this.seatInfo){
	            //       this.seatInfo = JSON.parse(this.seatInfo);
	            //       this.seatInfo.forEach((value)=>{
	            //         if (value%10!==0){
	            //           this.seatIJ[parseInt(value/10)][value%10-1] = 1;
	            //         } else{
	            //           this.seatIJ[parseInt(value/10)-1][9] = 1;
	            //         }
	            //       });
	            //     }
	            //   }
	            // }
	            // Indicator.close();
	          },
	          //选择座位
	          handleSelectSeat(indexI,indexJ){
	            // if (this.seatCount===4&&this.seatIJ[indexI][indexJ]===0){
	            //   MessageBox.alert('一次最多选择4个座位哦！');
	            // } else{
	            //   if (this.seatIJ[indexI][indexJ]===0){
	            //     this.seatIJ[indexI][indexJ]=2;
	            //     this.selectedSeatInfo.push([indexI,indexJ]);
	            //     this.seatCount+=1;
	            //     if (!this.selectedSeat){
	            //       this.selectedSeat = true;
	            //     }
	            //   }
	            //   else if (this.seatIJ[indexI][indexJ]===2){
	            //     this.seatIJ[indexI][indexJ]=0;
	            //     this.seatCount-=1;
	            //     let currentIndex;
	            //     this.selectedSeatInfo.forEach((value,index)=>{
	            //       if (indexI===value[0]&&indexJ===value[1]){
	            //         currentIndex = index;
	            //       }
	            //     });
	            //     this.selectedSeatInfo.splice(currentIndex,1);
	            //     if (this.seatCount===0){
	            //       this.selectedSeat = false;
	            //     }
	            //   }
	            //   this.hackReset = false;
	            //   this.$nextTick(() => {
	            //     this.hackReset = true;
	            //   });
	            // }
	          },
	          //取消选座
	          cancelSelectedSeat(indexI,indexJ){
	            // this.seatIJ[indexI][indexJ]=0;
	            // this.seatCount-=1;
	            // let currentIndex;
	            // this.selectedSeatInfo.forEach((value,index)=>{
	            //   if (indexI===value[0]&&indexJ===value[1]){
	            //     currentIndex = index;
	            //   }
	            // });
	            // this.selectedSeatInfo.splice(currentIndex,1);
	            // if (this.seatCount===0){
	            //   this.selectedSeat = false;
	            // }
	          },
	          //确认选座
	          async ensureSeatBtn(){
	            // if (this.$cookies.get('user_id')){
	            //   if (!this.seatInfo) {
	            //     this.seatInfo = [];
	            //   }
	            //   this.selectedSeatInfo.forEach((value,index)=>{
	            //     this.seatInfo.push(value[0]*10+value[1]+1);
	            //     this.$cookies.set('seat_'+(index+1),value[0]*10+value[1]+1);
	            //   });
	            //   this.$cookies.set('seat_count',this.selectedSeatInfo.length);
	            //   this.seatInfo = JSON.stringify(this.seatInfo);
	            //   let json = await updateScheduleSeat(this.$route.query.schedule_id,this.seatInfo);
	            //   if (json.success_code===200){
	            //     Toast({
	            //       message: '锁定座位成功',
	            //       position: 'middle',
	            //       duration: 2000
	            //     });
	            //     this.$router.push({path:'/submit_order',query:{cinema_id:this.$route.query.cinema_id,movie_id:this.$route.query.movie_id,schedule_id:this.$route.query.schedule_id,}});
	            //   }
	            // } else{
	            //   this.$router.push('./login');
	            // }
	          // }
	        },
	        filters:{
	          dateFilter(props){
	            props = props+'';
	            return props.split('-')[0]+'年'+props.split('-')[1]+'月'+props.split('-')[2]+'日';
	          }
	        }
	    },
	}
</script>

<style>
#select-seat {
  width: 100%;
  height: 100%;
  color: #000;
  font-size: 0.3125rem;
}
#select-seat .top {
  width: 100%;
  height: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  background-color: #dd2727;
  color: #fff;
}
#select-seat .top .icon-back {
  font-size: 0.4rem;
  position: absolute;
  left: 0.3rem;
}
#select-seat .top .name {
  width: 60%;
  text-align: center;
  font-size: 0.375rem;
}
#select-seat .movie-info {
  margin-top: 1rem;
  display: flex;
  flex-flow: column;
  padding: 0.25rem;
}
#select-seat .movie-info .name {
  margin-bottom: 0.25rem;
  font-size: 0.345rem;
  font-weight: 700;
}
#select-seat .movie-info .intro {
  font-size: 0.28rem;
  margin-bottom: 0.25rem;
  color: #888;
}
#select-seat .movie-info .intro span {
  margin-right: 0.12rem;
}
#select-seat .seat-block {
  width: 100%;
  background: #f1f1f1;
  position: absolute;
  left: 0;
  top: 2.5rem;
  bottom: 0;
}
#select-seat .seat-block .screen {
  width: 4rem;
  margin: 0 auto;
  text-align: center;
  background: #dcdcdc;
  font-size: 0.25rem;
  padding: 0.08rem 0;
  border-bottom-left-radius: 0.4rem;
  border-bottom-right-radius: 0.4rem;
  position: relative;
  left: 0.28rem;
}
#select-seat .seat-block .center {
  font-size: 0.2rem;
  width: 1rem;
  position: absolute;
  text-align: center;
  padding: 0.08rem 0.1rem;
  top: 1.2rem;
  left: 50%;
  margin-left: -0.3125rem;
  letter-spacing: 0.02rem;
  background-color: #fff;
  color: #888;
  border-radius: 0.12rem;
}
#select-seat .seat-block .screen-line {
  width: 0;
  height: 4.8rem;
  border: 0.02rem dashed #dcdcdc;
  position: absolute;
  top: 1.6rem;
  left: 50%;
  margin-left: 0.28rem;
}
#select-seat .seat-block .row-container {
  position: absolute;
  top: 1.4rem;
  left: 0.25rem;
  width: 0.4rem;
  height: 4rem;
  padding: 0.6rem 0;
  background-color: rgba(168,168,168,0.8);
  display: flex;
  flex-flow: column;
  text-align: center;
  justify-content: space-between;
  align-items: center;
  color: #fff;
  border-radius: 0.2rem;
  font-size: 0.25rem;
}
#select-seat .seat-block .seat-container {
  padding: 0.12rem 0;
  position: absolute;
  top: 1.8rem;
  left: 0.8rem;
}
#select-seat .seat-block .seat-container .row {
  margin-bottom: 0.25rem;
}
#select-seat .seat-block .seat-container .row .seat {
  font-size: 0.5rem;
  margin-left: 0.134rem;
}
#select-seat .seat-block .seat-example {
  position: absolute;
  top: 0.6rem;
  width: 80%;
  margin-left: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
}
#select-seat .seat-block .seat-example .example {
  font-size: 0.25rem;
  color: #888;
  margin-right: 0.25rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
#select-seat .seat-block .seat-example .example .icon {
  font-size: 0.3rem;
  margin-right: 0.12rem;
}
#select-seat .bottom {
  position: fixed;
  width: 100%;
  left: 0;
  bottom: 0;
  background: #fff;
  z-index: 999;
  display: flex;
  flex-flow: column;
  padding: 0.25rem;
  padding-top: 0.32rem;
  box-sizing: border-box;
}
#select-seat .bottom .title {
  font-size: 0.28rem;
  font-weight: 700;
  margin-bottom: 0.25rem;
}
#select-seat .bottom .btn {
  height: 0.8rem;
  line-height: 0.8rem;
  background-color: #f7dbb3;
  color: #fff;
  text-align: center;
  border-radius: 0.12rem;
  font-size: 0.28rem;
}
#select-seat .bottom .btn.active {
  background-color: #fe9900;
}
#select-seat .bottom .selected-seat {
  height: 1rem;
  display: flex;
}
#select-seat .bottom .selected-seat .seat-item {
  width: 25%;
  height: 0.8rem;
  display: flex;
  box-sizing: border-box;
  justify-content: space-around;
  align-items: center;
  border: 0.02rem solid #f1f1f1;
  margin-right: 0.25rem;
}
#select-seat .bottom .selected-seat .seat-item:last-child {
  margin-right: 0;
}
#select-seat .bottom .selected-seat .seat-item .left {
  display: flex;
  flex-flow: column;
  font-size: 0.25rem;
}
#select-seat .bottom .selected-seat .seat-item .left .seat {
  font-size: 0.28rem;
  color: #666;
  margin-bottom: 0.12rem;
}
#select-seat .bottom .selected-seat .seat-item .left .price {
  color: #dd2727;
  font-size: 0.24rem;
}
#select-seat .bottom .selected-seat .seat-item .right {
  font-size: 0.25rem;
}

</style>