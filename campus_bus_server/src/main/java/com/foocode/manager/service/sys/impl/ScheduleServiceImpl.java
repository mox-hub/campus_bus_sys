package com.foocode.manager.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foocode.manager.mapper.sys.ScheduleMapper;
import com.foocode.manager.model.Response;
import com.foocode.manager.model.sys.Schedule;
import com.foocode.manager.service.sys.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service("schedule")
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleMapper scheduleMapper;

    //  配置logger
    private final static Logger logger = LoggerFactory.getLogger(ScheduleService.class);
    private final static String projectName = "campus-bus-server";
    private final static String text = "排班";

    @Override
    public Object getScheduleList() {
        try {
            List<Schedule> schedules = scheduleMapper.selectList(null);
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, schedules);
            return new Response<>(schedules);
        } catch (NullPointerException e) {
            Response<Object> response = new Response(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object querySchedule(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式->" + mode + " 查询参数->" + options, projectName, text);
        IPage page = new Page(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if (options.equals("all")) {
                QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
                scheduleMapper.selectPage(page, null);
                List<Schedule> schedules = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new Response<>(schedules, pageTotal);
            } else if (mode.equals("id")) {
                Schedule schedule = scheduleMapper.selectById(options);
                List<Schedule> schedules = new ArrayList<>();
                schedules.add(schedule);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, schedules);
                return new Response<>(schedules);
            } else if (mode.equals("name")) {
                QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
                wrapper.eq("company_name", options);
                scheduleMapper.selectPage(page, wrapper);
                List<Schedule> schedules = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功", projectName, mode, text);
                return new Response<>(schedules, pageTotal);
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
    public Object createSchedule(Schedule schedule) {
        try {
            int res = scheduleMapper.insert(schedule);
            Response<Object> response = new Response<>(res, "已添加一条数据！");
            logger.info("[{}] ScheduleService::添加{}数据 >>> 添加成功！[{}]", projectName, text, response);
            return response;
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "创建失败！");
            logger.error("[{}] ScheduleService::添加{}数据 >>> 添加失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object updateSeat(String id, String seatId) {
        //Schedule schedule = scheduleMapper.selectById(id);
        //List<String> seats = Arrays.asList(schedule.getSeatInfo().split(","));
        List<String> seats = Arrays.asList("0403,0506,0708".split(","));
        System.out.println(seats.getClass());
//        LambdaUpdateWrapper<Schedule> lambda = new UpdateWrapper<Schedule>().lambda();
//        lambda.set(Schedule::getSeatInfo, seatId)
//                .eq(Schedule::getScheduleId, id);
/*
 LambdaUpdateWrapper<Schedule> lambda = new LambdaUpdateWrapper<>();
        lambda.set(实体类::getName, "张三")
                .eq(实体类::getId, id);
        this.update(lambda);//提交
         LambdaUpdateWrapper<实体类> lambda = new UpdateWrapper<>().lambda();
        lambda.set(Schedule::getSeatInfo(), id)
                .eq(Schedule::getScheduleId(), id);
        this.update(lambda);//提交
 */

//        UpdateWrapper<Schedule> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("schedule_id", id);
//        int res = scheduleMapper.update(null, updateWrapper);
        /*

                List<Schedule> schedules = new ArrayList<>();
                schedules.add(schedule);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, schedules);
                return new Response<>(schedules);
         */
        return null;
    }

    @Override
    public Object updateSchedule(Schedule schedule) {
        try {
            UpdateWrapper<Schedule> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("schedule_id", schedule.getScheduleId());
            int res = scheduleMapper.update(schedule, updateWrapper);
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
    public Object deleteSchedule(String id) {
        try {
            System.out.println(id);
            int res = scheduleMapper.deleteById(id);
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
