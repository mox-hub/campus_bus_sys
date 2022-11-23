package com.foocode.manager.mapper.sys;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.foocode.manager.model.sys.Order;
import com.foocode.manager.model.sys.OrderVo;
import com.foocode.manager.model.sys.ScheduleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select o.*, u.*, s.*, r.*, b.* " +
            "from sys_order o, sys_user u, sys_schedule s, sys_route r, sys_bus b " +
            "where o.user_id=u.user_id and o.schedule_id=s.schedule_id " +
            "and s.route_id=r.route_id and s.bus_id=b.bus_id")
    List<OrderVo> getListAssociated();

    @Select("select o.*, u.*, s.*, r.*, b.* " +
            "from sys_order o, sys_user u, sys_schedule s, sys_route r, sys_bus b " +
            "where o.user_id=u.user_id and o.schedule_id=s.schedule_id " +
            "and s.route_id=r.route_id and s.bus_id=b.bus_id and ${ew.sqlSegment}")
    IPage<OrderVo> queryAssociated(IPage<OrderVo> page, @Param("ew") Wrapper<OrderVo> wrapper);
}
