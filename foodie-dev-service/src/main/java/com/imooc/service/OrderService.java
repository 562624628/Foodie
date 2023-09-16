package com.imooc.service;

import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.SubmitOrderBO;

public interface OrderService {
    String createOrder(SubmitOrderBO submitOrderBO);

    void updateOrderStatus(String merchantOrderId, Integer orderStatus);

    OrderStatus queryOfOrderStatusByOrderId(String orderId);
}
