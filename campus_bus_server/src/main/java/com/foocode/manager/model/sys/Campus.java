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
    @TableId(type = IdType.INPUT)
    private String campusId;
    private String campusName;

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }
}
