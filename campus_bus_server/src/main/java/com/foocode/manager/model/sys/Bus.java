package com.foocode.manager.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName :bus
 * @Description :
 * @Author :Mox
 * @Date :2022/11/8 21:45
 * @Version : v1.0
 **/
@TableName(value = "sys_bus")
public class Bus {
    @TableId(type = IdType.AUTO)
    private Integer busId;
    private String busName;
    private Integer busRows;
    private Integer busColumns;
    private String busImage;


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

    @Override
    public String toString() {
        return "Bus{" +
                "busId='" + busId + '\'' +
                ", busName='" + busName + '\'' +
                ", busRows='" + busRows + '\'' +
                ", busColumns='" + busColumns + '\'' +
                ", busImage='" + busImage + '\'' +
                '}';
    }
}
