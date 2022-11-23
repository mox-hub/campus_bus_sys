package com.foocode.manager.service.sys;

import com.foocode.manager.model.sys.Order;

import java.util.Map;

public interface OrderService {
    Object getOrderList();

    Object getOrderListAssociated();

    Object queryOrder(Map<String, String> data);

    Object queryOrderAssociated(Map<String, String> data);

    Object createOrder(Order order);

    Object updateOrder(Order order);

    Object deleteOrder(String id);
}
