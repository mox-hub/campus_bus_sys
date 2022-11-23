package com.foocode.manager.model.sys;

public class ScheduleVo {
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

    @Override
    public String toString() {
        return "ScheduleVo{" +
                "scheduleId=" + scheduleId +
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
                '}';
    }
}
