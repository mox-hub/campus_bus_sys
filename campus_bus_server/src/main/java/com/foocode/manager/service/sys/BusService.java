package com.foocode.manager.service.sys;

import com.foocode.manager.model.sys.Bus;

import java.util.Map;

public interface BusService {
    Object getBusList();

    Object queryBus(Map<String, String> data);

    Object createBus(Bus bus);

    Object updateBus(Bus bus);

    Object deleteBus(String id);
}
