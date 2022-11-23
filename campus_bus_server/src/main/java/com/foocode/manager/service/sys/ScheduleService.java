package com.foocode.manager.service.sys;

import com.foocode.manager.model.sys.Schedule;

import java.util.Map;

public interface ScheduleService {

    Object getScheduleList();

    Object getScheduleListAssociated();

    Object querySchedule(Map<String, String> data);

    Object queryScheduleAssociated(Map<String, String> data);

    Object createSchedule(Schedule schedule);

    Object updateSeat(String state, String id, String seatId);

    Object updateSchedule(Schedule schedule);

    Object deleteSchedule(String id);
}
