package com.foocode.manager.controller.sys;

import com.foocode.manager.model.sys.Route;
import com.foocode.manager.service.sys.RouteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
public class RouteController {
    @Resource(name = "route")
    private RouteService routeService;

    @GetMapping(value = "/route/getRouteList")
    public Object getRouteList() {
        Object res = routeService.getRouteList();
        return res;
    }

    @GetMapping(value = "/route/queryRoute")
    public Object queryRoute(@RequestParam Map<String, String> data) {
        Object res = routeService.queryRoute(data);
        return res;
    }

    @PostMapping(value = "/route/createRoute")
    public Object createRoute(@RequestBody Route route) {
        Object res = routeService.createRoute(route);
        return res;
    }

    @PostMapping(value = "/route/updateRoute")
    public Object updateRoute(@RequestBody Route route) {
        Object res = routeService.updateRoute(route);
        return res;
    }

    @PostMapping(value = "/route/deleteRoute")
    public Object deleteRoute(@RequestParam("routeId") String id) {
        Object res = routeService.deleteRoute(id);
        return res;
    }
}