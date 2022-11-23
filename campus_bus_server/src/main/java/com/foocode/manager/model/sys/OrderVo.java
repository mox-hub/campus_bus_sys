package com.foocode.manager.model.sys;

public class OrderVo {
    private Long orderId;
    private Integer userId;
    private String userName;
    private String avatar;
    private String phone;
    private Integer workId;
    private Integer campusId;
    private String campusName;
    private Integer scheduleId;
    private String startLocation;
    private String endLocation;
    private Integer routeId;
    private String startStation;
    private String endStation;
    private String stopStation;
    private String startTime;
    private String date;
    private Integer busId;
    private String busName;
    private Integer busRows;
    private Integer busColumns;
    private String busImage;
    private String seatInfo;
    private String orderTime;
    private String orderStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
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

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getStopStation() {
        return stopStation;
    }

    public void setStopStation(String stopStation) {
        this.stopStation = stopStation;
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

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public Integer getBusRows() {
        return busRows;
    }

    public void setBusRows(Integer busRows) {
        this.busRows = busRows;
    }

    public Integer getBusColumns() {
        return busColumns;
    }

    public void setBusColumns(Integer busColumns) {
        this.busColumns = busColumns;
    }

    public String getBusImage() {
        return busImage;
    }

    public void setBusImage(String busImage) {
        this.busImage = busImage;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", workId=" + workId +
                ", campusId=" + campusId +
                ", campusName='" + campusName + '\'' +
                ", scheduleId=" + scheduleId +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", routeId=" + routeId +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", stopStation='" + stopStation + '\'' +
                ", startTime='" + startTime + '\'' +
                ", date='" + date + '\'' +
                ", busId=" + busId +
                ", busName='" + busName + '\'' +
                ", busRows=" + busRows +
                ", busColumns=" + busColumns +
                ", busImage='" + busImage + '\'' +
                ", seatInfo='" + seatInfo + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
