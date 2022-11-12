package com.foocode.manager.controller.sys;

import com.foocode.manager.model.sys.Bus;
import com.foocode.manager.service.sys.BusService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
public class BusController {
    @Resource(name = "bus")
    private BusService busService;

    @GetMapping(value = "/bus/getBusList")
    public Object getBusList() {
        Object res = busService.getBusList();
        return res;
    }

    @GetMapping(value = "/bus/queryBus")
    public Object queryBus(@RequestParam Map<String, String> data) {
        Object res = busService.queryBus(data);
        return res;
    }

    @PostMapping(value = "/bus/createBus")
    public Object createBus(@RequestBody Bus bus) {
        Object res = busService.createBus(bus);
        return res;
    }

    @PostMapping(value = "/bus/updateBus")
    public Object updateBus(@RequestBody Bus bus) {
        Object res = busService.updateBus(bus);
        return res;
    }

    @PostMapping(value = "/bus/deleteBus")
    public Object deleteBus(@RequestParam("busId") String id) {
        Object res = busService.deleteBus(id);
        return res;
    }
}
