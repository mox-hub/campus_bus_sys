package com.foocode.manager.controller.sys;

import com.foocode.manager.model.sys.Campus;
import com.foocode.manager.service.sys.CampusService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName :CampusController
 * @Description :
 * @Author :Mox
 * @Date :2022/11/8 20:39
 * @Version : v1.0
 **/
@CrossOrigin
@RestController
public class CampusController {
    @Resource(name = "campus")
    private CampusService campusService;

    @GetMapping(value = "/campus/getCampusList")
    public Object getCampusList() {
        Object res = campusService.getCampusList();
        return res;
    }

    @GetMapping(value = "/campus/queryCampus")
    public Object queryCampus(@RequestParam Map<String, String> data) {
        Object res = campusService.queryCampus(data);
        return res;
    }

    @PostMapping(value = "/campus/createCampus")
    public Object createCampus(@RequestBody Campus campus) {
        Object res = campusService.createCampus(campus);
        return res;
    }

    @PostMapping(value = "/campus/deleteCampus")
    public Object deleteCampus(@RequestParam("campusId") String id) {
        Object res = campusService.deleteCampus(id);
        return res;
    }
}
