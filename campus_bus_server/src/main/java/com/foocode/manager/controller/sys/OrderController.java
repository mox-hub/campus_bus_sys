package com.foocode.manager.controller.sys;

import com.foocode.manager.model.sys.Order;
import com.foocode.manager.service.sys.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
public class OrderController {
    @Resource(name = "order")
    private OrderService orderService;

    @GetMapping(value = "/order/getOrderList")
    public Object getOrderList() {
        Object res = orderService.getOrderList();
        return res;
    }

    @GetMapping(value = "/order/queryOrder")
    public Object queryOrder(@RequestParam Map<String, String> data) {
        Object res = orderService.queryOrder(data);
        return res;
    }

    @PostMapping(value = "/order/createOrder")
    public Object createOrder(@RequestBody Order order) {
        Object res = orderService.createOrder(order);
        return res;
    }

    @PostMapping(value = "/order/updateOrder")
    public Object updateOrder(@RequestBody Order order) {
        Object res = orderService.updateOrder(order);
        return res;
    }

    @PostMapping(value = "/order/deleteOrder")
    public Object deleteOrder(@RequestParam("orderId") String id) {
        Object res = orderService.deleteOrder(id);
        return res;
    }
}
