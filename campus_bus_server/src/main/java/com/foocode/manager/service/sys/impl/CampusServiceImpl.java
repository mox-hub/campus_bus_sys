package com.foocode.manager.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foocode.manager.mapper.sys.CampusMapper;
import com.foocode.manager.model.sys.Campus;
import com.foocode.manager.model.Response;
import com.foocode.manager.service.sys.CampusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName :CampusServiceImpl
 * @Description :
 * @Author :Mox
 * @Date :2022/10/25 19:06
 * @Version : v1.0
 **/

@Service("campus")
public class CampusServiceImpl implements CampusService {

    @Resource
    private CampusMapper campusMapper;

    //  配置logger
    private final static Logger logger = LoggerFactory.getLogger(CampusService.class);
    private final static String projectName = "campus-bus-server";
    private final static String text = "校区";

    @Override
    public Object getCampusList() {
        try {
            List<Campus> campuses = campusMapper.selectList(null);
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, campuses);
            return new Response<>(campuses);
        } catch (NullPointerException e) {
            Response<Object> response = new Response(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object queryCampus(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage page = new Page(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if (options.equals("all")) {
                QueryWrapper<Campus> wrapper = new QueryWrapper<>();
                campusMapper.selectPage(page, null);
                List<Campus> campuses = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new Response<>(campuses, pageTotal);
            } else if (mode.equals("id")) {
                Campus campus = campusMapper.selectById(options);
                List<Campus> campuses = new ArrayList<>();
                campuses.add(campus);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, campuses);
                return new Response<>(campuses);
            } else if (mode.equals("name")) {
                QueryWrapper<Campus> wrapper = new QueryWrapper<>();
                wrapper.eq("company_name", options);
                campusMapper.selectPage(page, wrapper);
                List<Campus> campuses = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功", projectName, mode, text);
                return new Response<>(campuses, pageTotal);
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
    public Object createCampus(Campus campus) {
        try {
            int res = campusMapper.insert(campus);
            Response<Object> response = new Response<>(res, "已添加一条数据！");
            logger.info("[{}] CampusService::添加{}数据 >>> 添加成功！[{}]", projectName, text, response);
            return response;
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "创建失败！");
            logger.error("[{}] CampusService::添加{}数据 >>> 添加失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object deleteCampus(String id) {
        try {
            System.out.println(id);
            int res = campusMapper.deleteById(id);
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
