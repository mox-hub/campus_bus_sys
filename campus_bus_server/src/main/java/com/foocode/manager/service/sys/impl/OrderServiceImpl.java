package com.foocode.manager.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foocode.manager.mapper.sys.OrderMapper;
import com.foocode.manager.model.Response;
import com.foocode.manager.model.sys.Order;
import com.foocode.manager.service.sys.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("order")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    //  配置logger
    private final static Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final static String projectName = "campus-bus-server";
    private final static String text = "订单";

    @Override
    public Object getOrderList() {
        try {
            List<Order> orders = orderMapper.selectList(null);
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, orders);
            return new Response<>(orders);
        } catch (NullPointerException e) {
            Response<Object> response = new Response(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object queryOrder(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage page = new Page(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if (options.equals("all")) {
                QueryWrapper<Order> wrapper = new QueryWrapper<>();
                orderMapper.selectPage(page, null);
                List<Order> orders = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new Response<>(orders, pageTotal);
            } else if (mode.equals("id")) {
                Order order = orderMapper.selectById(options);
                List<Order> orders = new ArrayList<>();
                orders.add(order);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, orders);
                return new Response<>(orders);
            } else if (mode.equals("name")) {
                QueryWrapper<Order> wrapper = new QueryWrapper<>();
                wrapper.eq("company_name", options);
                orderMapper.selectPage(page, wrapper);
                List<Order> orders = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功", projectName, mode, text);
                return new Response<>(orders, pageTotal);
            } else {
                Response<Object> response = new Response<>(-2, "查询模式错误！");
                logger.error("[{}]:: 查询所有{}信息 >>> 查询失败 [{}]", projectName, text, response);
                return response;
            }
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "未查询到校区！");
            logger.error("[{}]::查询{}信息 >>> 查询失败！[{}]", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object createOrder(Order order) {
        try {
            int res = orderMapper.insert(order);
            Response<Object> response = new Response<>(res, "已添加一条数据！");
            logger.info("[{}] OrderService::添加{}数据 >>> 添加成功！[{}]", projectName, text, response);
            return response;
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "创建失败！");
            logger.error("[{}] OrderService::添加{}数据 >>> 添加失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object updateOrder(Order order) {
        try {
            UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("order_id", order.getOrderId());
            int res = orderMapper.update(order, updateWrapper);
            Response<Object> response = new Response<>(res, "已更新一条数据");
            logger.info("[{}]::更新{}数据 >>> 更新成功！[{}]", projectName, text, response);
            return response;
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "更新失败！");
            logger.error("[{}]::更新{}数据 >>> 更新失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object deleteOrder(String id) {
        try {
            System.out.println(id);
            int res = orderMapper.deleteById(id);
            Response<Object> response = new Response(res, "已删除一条数据！");
            logger.info("[{}]::删除{}数据 >>> 删除成功！[{}]", projectName, text, response);
            return response;
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "删除失败！");
            logger.error("[{}]::删除{}数据 >>> 删除失败！[{}]", projectName, text, e);
            return response;
        }
    }
}
