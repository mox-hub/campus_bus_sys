package com.foocode.manager.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName :Campus
 * @Description :
 * @Author :Mox
 * @Date :2022/10/25 18:57
 * @Version : v1.0
 **/
@TableName(value = "sys_campus")
public class Campus {
    @TableId(type = IdType.AUTO)
    private Integer campusId;
    private String campusName;

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

    @Override
    public String toString() {
        return "Campus{" +
                "campusId='" + campusId + '\'' +
                ", campusName='" + campusName + '\'' +
                '}';
    }
}
