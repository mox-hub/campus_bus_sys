package com.foocode.manager.mapper.sys;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.foocode.manager.model.sys.Schedule;
import com.foocode.manager.model.sys.ScheduleVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    @Select("select s.*, r.*, b.* " +
            "from sys_schedule s, sys_route r, sys_bus b " +
            "where s.route_id=r.route_id and s.bus_id=b.bus_id")
    List<ScheduleVo> getListAssociated();

    @Select("select s.*, r.*, b.* " +
            "from sys_schedule s, sys_route r, sys_bus b " +
            "where s.route_id=r.route_id and s.bus_id=b.bus_id and ${ew.sqlSegment}")
    IPage<ScheduleVo> queryAssociated(IPage<ScheduleVo> page, @Param("ew") Wrapper<ScheduleVo> wrapper);

}
