package com.foocode.manager.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName :Schedule
 * @Description :
 * @Author :Mox
 * @Date :2022/11/8 21:48
 * @Version : v1.0
 **/
@TableName(value = "sys_schedule")
public class Schedule {
    @TableId(type = IdType.INPUT)
    private String scheduleId;
    private String startLocation;
    private String endLocation;
    private String routeId;
    private String startTime;
    private String date;
    private String busId;
    private String seatInfo;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", routeId='" + routeId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", date='" + date + '\'' +
                ", busId='" + busId + '\'' +
                ", seatInfo='" + seatInfo + '\'' +
                '}';
    }
}
