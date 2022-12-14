package com.foocode.manager.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foocode.manager.mapper.sys.RouteMapper;
import com.foocode.manager.model.Response;
import com.foocode.manager.model.sys.Route;
import com.foocode.manager.service.sys.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("route")
public class RouteServiceImpl implements RouteService {

    @Resource
    private RouteMapper routeMapper;

    //  配置logger
    private final static Logger logger = LoggerFactory.getLogger(RouteService.class);
    private final static String projectName = "campus-bus-server";
    private final static String text = "路线";

    @Override
    public Object getRouteList() {
        try {
            List<Route> routes = routeMapper.selectList(null);
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, routes);
            return new Response<>(routes);
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object queryRoute(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage<Route> page = new Page<>(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if ("all".equals(options)) {
                routeMapper.selectPage(page, null);
                List<Route> routes = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new Response<>(routes, pageTotal);
            } else if ("id".equals(mode)) {
                Route route = routeMapper.selectById(options);
                List<Route> routes = new ArrayList<>();
                routes.add(route);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, routes);
                return new Response<>(routes);
            } else {
                Response<Object> response = new Response<>(-2, "查询模式错误！");
                logger.error("[{}]:: 查询所有{}信息 >>> 查询失败 [{}]", projectName, text, response);
                return response;
            }
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "查询失败！");
            logger.error("[{}]::查询{}信息 >>> 查询失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object createRoute(Route route) {
        try {
            int res = routeMapper.insert(route);
            Response<Object> response = new Response<>(res, "已添加一条数据！");
            logger.info("[{}] RouteService::添加{}数据 >>> 添加成功！[{}]", projectName, text, response);
            return response;
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "创建失败！");
            logger.error("[{}] RouteService::添加{}数据 >>> 添加失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object updateRoute(Route route) {
        try {
            UpdateWrapper<Route> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("route_id", route.getRouteId());
            int res = routeMapper.update(route, updateWrapper);
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
    public Object deleteRoute(String id) {
        try {
            System.out.println(id);
            int res = routeMapper.deleteById(id);
            Response<Object> response = new Response<>(res, "已删除一条数据！");
            logger.info("[{}]::删除{}数据 >>> 删除成功！[{}]", projectName, text, response);
            return response;
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "删除失败！");
            logger.error("[{}]::删除{}数据 >>> 删除失败！[{}]", projectName, text, e);
            return response;
        }
    }
}
