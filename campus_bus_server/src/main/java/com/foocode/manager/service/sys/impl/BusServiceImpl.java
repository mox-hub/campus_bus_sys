package com.foocode.manager.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foocode.manager.mapper.sys.BusMapper;
import com.foocode.manager.model.Response;
import com.foocode.manager.model.sys.Bus;
import com.foocode.manager.service.sys.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("bus")
public class BusServiceImpl implements BusService {

    @Resource
    private BusMapper busMapper;

    //  配置logger
    private final static Logger logger = LoggerFactory.getLogger(BusService.class);
    private final static String projectName = "campus-bus-server";
    private final static String text = "校车";

    @Override
    public Object getBusList() {
        try {
            List<Bus> buses = busMapper.selectList(null);
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, buses);
            return new Response<>(buses);
        } catch (NullPointerException e) {
            Response<Object> response = new Response(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object queryBus(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage page = new Page(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if (options.equals("all")) {
                QueryWrapper<Bus> wrapper = new QueryWrapper<>();
                busMapper.selectPage(page, null);
                List<Bus> buses = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new Response<>(buses, pageTotal);
            } else if (mode.equals("id")) {
                Bus bus = busMapper.selectById(options);
                List<Bus> buses = new ArrayList<>();
                buses.add(bus);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, buses);
                return new Response<>(buses);
            } else if (mode.equals("name")) {
                QueryWrapper<Bus> wrapper = new QueryWrapper<>();
                wrapper.eq("company_name", options);
                busMapper.selectPage(page, wrapper);
                List<Bus> buses = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功", projectName, mode, text);
                return new Response<>(buses, pageTotal);
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
    public Object createBus(Bus bus) {
        try {
            int res = busMapper.insert(bus);
            Response<Object> response = new Response<>(res, "已添加一条数据！");
            logger.info("[{}] BusService::添加{}数据 >>> 添加成功！[{}]", projectName, text, response);
            return response;
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "创建失败！");
            logger.error("[{}] BusService::添加{}数据 >>> 添加失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object updateBus(Bus bus) {
        try {
            UpdateWrapper<Bus> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("bus_id", bus.getBusId());
            int res = busMapper.update(bus, updateWrapper);
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
    public Object deleteBus(String id) {
        try {
            System.out.println(id);
            int res = busMapper.deleteById(id);
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
