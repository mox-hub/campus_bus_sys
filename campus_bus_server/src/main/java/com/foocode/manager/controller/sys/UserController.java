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

    @GetMapping(value = "/user/getUserList")
    public Object getUserList() {
        Object res = userService.getUserList();
        return res;
    }

    @GetMapping(value = "/user/getUserListAssociated")
    public Object getUserListAssociated() {
        Object res = userService.getUserListAssociated();
        return res;
    }
    
    @GetMapping(value = "/user/queryUser")
    public Object queryUser(@RequestParam Map<String, String> data) {
        Object res = userService.queryUser(data);
        return res;
    }
    
    @GetMapping(value = "/user/queryUserAssociated")
    public Object queryUserAssociated(@RequestParam Map<String, String> data) {
        Object res = userService.queryUserAssociated(data);
        return res;
    }

}
