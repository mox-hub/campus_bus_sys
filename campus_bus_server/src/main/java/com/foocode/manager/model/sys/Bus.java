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
    @TableId(type = IdType.INPUT)
    private String busId;
    private String busName;
    private String busImage;
    private String busType;

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusImage() {
        return busImage;
    }

    public void setBusImage(String busImage) {
        this.busImage = busImage;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }
}
