package com.foocode.manager.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foocode.manager.mapper.sys.UserMapper;
import com.foocode.manager.model.Response;
import com.foocode.manager.model.sys.User;
import com.foocode.manager.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName :UserServiceImpl
 * @Description :
 * @Author :Mox
 * @Date :2022/11/9 18:52
 * @Version : v1.0
 **/

@Service("user")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    //  配置logger
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final static String projectName = "campus-bus-server";
    private final static String text = "用户";

    @Override
    public Object getUserInfo(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage<User> page = new Page<>(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if ("all".equals(options)) {
                userMapper.selectPage(page, null);
                List<User> Users = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]getUserInfo:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new Response<>(Users, pageTotal);
            } else if ("id".equals(mode)) {
                User User = userMapper.selectById(options);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, User);
                return new Response<>(User);
            } else if ("name".equals(mode)) {
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.eq("user_name", options);
                userMapper.selectPage(page, wrapper);
                List<User> Users = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功", projectName, mode, text);
                return new Response<>(Users, pageTotal);
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
}
