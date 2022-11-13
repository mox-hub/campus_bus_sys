package com.foocode.manager.controller.sys;

import com.foocode.manager.service.sys.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName :UserController
 * @Description :
 * @Author :Mox
 * @Date :2022/11/9 18:56
 * @Version : v1.0
 **/

@CrossOrigin
@RestController
public class UserController {

    @Resource(name = "user")
    private UserService userService;

    @GetMapping(value = "/user/getUserInfo")
    public Object getUserInfo(@RequestParam Map<String, String> data) {
        Object res = userService.getUserInfo(data);
        return res;
    }
}
