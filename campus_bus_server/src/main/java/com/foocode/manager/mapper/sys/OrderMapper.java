package com.foocode.manager.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.foocode.manager.model.sys.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
