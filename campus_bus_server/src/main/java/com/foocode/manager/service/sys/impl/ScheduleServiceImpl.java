package com.foocode.manager.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.foocode.manager.mapper.sys.ScheduleMapper;
import com.foocode.manager.model.Response;
import com.foocode.manager.model.sys.Schedule;

import com.foocode.manager.model.sys.ScheduleVo;
import com.foocode.manager.service.sys.ScheduleService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
            Response<Object> response = new Response<>(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object getScheduleListAssociated() {
        try {
            List<ScheduleVo> scheduleVoList = scheduleMapper.getListAssociated();
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, scheduleVoList);
            return new Response<>(scheduleVoList);
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object querySchedule(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        String startLocation = data.get("startLocation");
        String endLocation = data.get("endLocation");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage<Schedule> page = new Page<>(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if ("all".equals(options)) {
                scheduleMapper.selectPage(page, null);
                List<Schedule> schedules = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询所有{}信息 >>> 查询成功[{}]", projectName, text, schedules);
                return new Response<>(schedules, pageTotal);
            } else if ("id".equals(mode)) {
                Schedule schedule = scheduleMapper.selectById(options);
                List<Schedule> schedules = new ArrayList<>();
                schedules.add(schedule);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 [{}]", projectName, text, mode, schedules);
                return new Response<>(schedules);
            } else if ("date".equals(mode)) {
                QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
                wrapper.like("date", options);
                scheduleMapper.selectPage(page, wrapper);
                List<Schedule> schedules = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功[{}]", projectName, text, mode, schedules);
                return new Response<>(schedules, pageTotal);
            } else if ("location".equals(mode)) {
                if (startLocation != null && endLocation != null) {
                    QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
                    wrapper.like("start_location", startLocation)
                            .like("end_location", endLocation);
                    scheduleMapper.selectPage(page, wrapper);
                    List<Schedule> schedules = page.getRecords();
                    int pageTotal = (int) page.getTotal();
                    logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功[{}]", projectName, text, mode, schedules);
                    return new Response<>(schedules, pageTotal);
                } else {
                    Response<Object> response = new Response<>(-3, "查询信息不完整！");
                    logger.error("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询失败 [{}]", projectName, text, mode, response);
                    return response;
                }
            } else if ("startLocation".equals(mode)) {
                if (startLocation != null) {
                    QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
                    wrapper.like("start_location", startLocation);
                    scheduleMapper.selectPage(page, wrapper);
                    List<Schedule> schedules = page.getRecords();
                    int pageTotal = (int) page.getTotal();
                    logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功[{}]", projectName, text, mode, schedules);
                    return new Response<>(schedules, pageTotal);
                } else {
                    Response<Object> response = new Response<>(-3, "查询信息不完整！");
                    logger.error("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询失败 [{}]", projectName, text, mode, response);
                    return response;
                }
            } else if ("endLocation".equals(mode)) {
                if (endLocation != null) {
                    QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
                    wrapper.like("end_location", endLocation);
                    scheduleMapper.selectPage(page, wrapper);
                    List<Schedule> schedules = page.getRecords();
                    int pageTotal = (int) page.getTotal();
                    logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功[{}]", projectName, text, mode, schedules);
                    return new Response<>(schedules, pageTotal);
                } else {
                    Response<Object> response = new Response<>(-3, "查询信息不完整！");
                    logger.error("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询失败 [{}]", projectName, text, mode, response);
                    return response;
                }
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
    public Object queryScheduleAssociated(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        String startLocation = data.get("startLocation");
        String endLocation = data.get("endLocation");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage<ScheduleVo> page = new Page<>(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if ("all".equals(options)) {
                QueryWrapper<ScheduleVo> wrapper = new QueryWrapper<>();
                wrapper.isNotNull("schedule_id").orderByAsc("schedule_id");
                scheduleMapper.queryAssociated(page, wrapper);
                List<ScheduleVo> scheduleVoList = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询所有{}信息 >>> 查询成功[{}]", projectName, text, scheduleVoList);
                return new Response<>(scheduleVoList, pageTotal);
            } else if ("id".equals(mode)) {
                QueryWrapper<ScheduleVo> wrapper = new QueryWrapper<>();
                wrapper.eq("schedule_id", options);
                scheduleMapper.queryAssociated(page, wrapper);
                List<ScheduleVo> scheduleVoList = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 [{}]", projectName, text, mode, scheduleVoList);
                return new Response<>(scheduleVoList, pageTotal);
            } else if ("date".equals(mode)) {
                QueryWrapper<ScheduleVo> wrapper = new QueryWrapper<>();
                wrapper.like("date", options);
                scheduleMapper.queryAssociated(page, wrapper);
                List<ScheduleVo> scheduleVoList = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功[{}]", projectName, text, mode, scheduleVoList);
                return new Response<>(scheduleVoList, pageTotal);
            } else if ("location".equals(mode)) {
                if (startLocation != null && endLocation != null) {
                    QueryWrapper<ScheduleVo> wrapper = new QueryWrapper<>();
                    wrapper.like("start_location", startLocation)
                            .like("end_location", endLocation);
                    scheduleMapper.queryAssociated(page, wrapper);
                    List<ScheduleVo> scheduleVoList = page.getRecords();
                    int pageTotal = (int) page.getTotal();
                    logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功[{}]", projectName, text, mode, scheduleVoList);
                    return new Response<>(scheduleVoList, pageTotal);
                } else {
                    Response<Object> response = new Response<>(-3, "查询信息不完整！");
                    logger.error("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询失败 [{}]", projectName, text, mode, response);
                    return response;
                }
            } else if ("startLocation".equals(mode)) {
                if (startLocation != null) {
                    QueryWrapper<ScheduleVo> wrapper = new QueryWrapper<>();
                    wrapper.like("start_location", startLocation);
                    scheduleMapper.queryAssociated(page, wrapper);
                    List<ScheduleVo> scheduleVoList = page.getRecords();
                    int pageTotal = (int) page.getTotal();
                    logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功[{}]", projectName, text, mode, scheduleVoList);
                    return new Response<>(scheduleVoList, pageTotal);
                } else {
                    Response<Object> response = new Response<>(-3, "查询信息不完整！");
                    logger.error("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询失败 [{}]", projectName, text, mode, response);
                    return response;
                }
            } else if ("endLocation".equals(mode)) {
                if (endLocation != null) {
                    QueryWrapper<ScheduleVo> wrapper = new QueryWrapper<>();
                    wrapper.like("end_location", endLocation);
                    scheduleMapper.queryAssociated(page, wrapper);
                    List<ScheduleVo> scheduleVoList = page.getRecords();
                    int pageTotal = (int) page.getTotal();
                    logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功[{}]", projectName, text, mode, scheduleVoList);
                    return new Response<>(scheduleVoList, pageTotal);
                } else {
                    Response<Object> response = new Response<>(-3, "查询信息不完整！");
                    logger.error("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询失败 [{}]", projectName, text, mode, response);
                    return response;
                }
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
    public Object updateSeat(String state, String id, String seatId) {
        try {
            Schedule schedule = scheduleMapper.selectById(id);
            String seatInfo = schedule.getSeatInfo();
            Set<String> seats = new TreeSet<>(Arrays.asList(seatInfo.substring(1, seatInfo.length() - 1).split(",")));
            if ("0".equals(state)) {
                if (seats.add(seatId)) {
                    String newSeats = "[" + StringUtils.join(seats.toArray(), ",") + "]";
                    LambdaUpdateWrapper<Schedule> updateWrapper = new UpdateWrapper<Schedule>().lambda();
                    updateWrapper.set(Schedule::getSeatInfo, newSeats)
                            .eq(Schedule::getScheduleId, id);
                    int res = scheduleMapper.update(null, updateWrapper);
                    Response<Object> response = new Response<>(res, "已更新一条数据");
                    logger.info("[{}]::更新选座数据 >>> 更新成功！[{}]", projectName, newSeats);
                    return response;
                } else {
                    Response<Object> response = new Response<>(1, seatId + "座已被占用更新失败！");
                    logger.info("[{}]::更新选座数据 >>> {}座已被占用 >>> 更新失败！[{}]", projectName, seatId, response);
                    return response;
                }
            } else if ("1".equals(state)) {
                if (seats.remove(seatId)) {
                    String newSeats = "[" + StringUtils.join(seats.toArray(), ",") + "]";
                    LambdaUpdateWrapper<Schedule> updateWrapper = new UpdateWrapper<Schedule>().lambda();
                    updateWrapper.set(Schedule::getSeatInfo, newSeats)
                            .eq(Schedule::getScheduleId, id);
                    int res = scheduleMapper.update(null, updateWrapper);
                    Response<Object> response = new Response<>(res, "已更新一条数据");
                    logger.info("[{}]::更新选座数据 >>> 更新成功！[{}]", projectName, newSeats);
                    return response;
                } else {
                    Response<Object> response = new Response<>(2, seatId + "座已为空更新失败！");
                    logger.info("[{}]::更新选座数据 >>> {}座已为空 >>> 更新失败！[{}]", projectName, seatId, response);
                    return response;
                }
            } else {
                Response<Object> response = new Response<>(3, seatId + "座已被占用更新失败！");
                logger.info("[{}]::更新选座数据 >>> 状态码错误 >>> 更新失败！[{}]", projectName, response);
                return response;
            }
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "更新失败！");
            logger.error("[{}]::更新选座数据 >>> 更新失败！[{}]", projectName, e);
            return response;
        }
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