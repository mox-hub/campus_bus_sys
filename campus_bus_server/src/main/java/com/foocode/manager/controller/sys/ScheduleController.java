package com.foocode.manager.controller.sys;

import com.foocode.manager.model.sys.Schedule;
import com.foocode.manager.service.sys.ScheduleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@CrossOrigin
@RestController
public class ScheduleController {
    @Resource(name = "schedule")
    private ScheduleService scheduleService;

    @GetMapping(value = "/schedule/getScheduleList")
    public Object getScheduleList() {
        Object res = scheduleService.getScheduleList();
        return res;
    }

    @GetMapping(value = "/schedule/getScheduleListAssociated")
    public Object getScheduleListAssociated() {
        Object res = scheduleService.getScheduleListAssociated();
        return res;
    }

    @GetMapping(value = "/schedule/querySchedule")
    public Object querySchedule(@RequestParam Map<String, String> data) {
        Object res = scheduleService.querySchedule(data);
        return res;
    }

    @GetMapping(value = "/schedule/queryScheduleAssociated")
    public Object queryScheduleAssociated(@RequestParam Map<String, String> data) {
        Object res = scheduleService.queryScheduleAssociated(data);
        return res;
    }

    @PostMapping(value = "/schedule/createSchedule")
    public Object createSchedule(@RequestBody Schedule schedule) {
        Object res = scheduleService.createSchedule(schedule);
        return res;
    }

    @PostMapping(value = "/schedule/updateSeat")
    public Object updateSeat(@RequestParam("updateState") String state, @RequestParam("scheduleId") String id, @RequestParam("seatId") String seatId) {
        Object res = scheduleService.updateSeat(state, id, seatId);
        return res;
    }

    @PostMapping(value = "/schedule/updateSchedule")
    public Object updateSchedule(@RequestBody Schedule schedule) {
        Object res = scheduleService.updateSchedule(schedule);
        return res;
    }

    @PostMapping(value = "/schedule/deleteSchedule")
    public Object deleteSchedule(@RequestParam("scheduleId") String id) {
        Object res = scheduleService.deleteSchedule(id);
        return res;
    }
}

