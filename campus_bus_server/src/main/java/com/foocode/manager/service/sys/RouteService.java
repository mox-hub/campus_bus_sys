package com.foocode.manager.service.sys;

import com.foocode.manager.model.sys.Route;

import java.util.Map;

public interface RouteService {

    Object getRouteList();

    Object queryRoute(Map<String, String> data);

    Object createRoute(Route route);

    Object updateRoute(Route route);

    Object deleteRoute(String id);

}
