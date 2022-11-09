package com.foocode.manager.service.sys;

import com.foocode.manager.model.sys.Campus;

import java.util.Map;

public interface CampusService {
    Object getCampusList();

    Object queryCampus(Map<String, String> data);

    Object createCampus(Campus campus);

    Object deleteCampus(String id);
}
